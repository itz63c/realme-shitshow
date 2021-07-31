package neton.internal.http1;

import com.coloros.neton.NetonException;
import java.io.EOFException;
import java.io.IOException;
import java.net.ProtocolException;
import java.util.concurrent.TimeUnit;
import neton.Headers;
import neton.HttpUrl;
import neton.OkHttpClient;
import neton.Request;
import neton.Response;
import neton.ResponseBody;
import neton.client.config.Constants;
import neton.internal.Internal;
import neton.internal.Util;
import neton.internal.connection.RealConnection;
import neton.internal.connection.StreamAllocation;
import neton.internal.http.HttpCodec;
import neton.internal.http.HttpHeaders;
import neton.internal.http.RealResponseBody;
import neton.internal.http.RequestLine;
import neton.internal.http.StatusLine;
import okio.Buffer;
import okio.BufferedSink;
import okio.BufferedSource;
import okio.ForwardingTimeout;
import okio.Okio;
import okio.Sink;
import okio.Source;
import okio.Timeout;

public final class Http1Codec implements HttpCodec {
    private static final int STATE_CLOSED = 6;
    private static final int STATE_IDLE = 0;
    private static final int STATE_OPEN_REQUEST_BODY = 1;
    private static final int STATE_OPEN_RESPONSE_BODY = 4;
    private static final int STATE_READING_RESPONSE_BODY = 5;
    private static final int STATE_READ_RESPONSE_HEADERS = 3;
    private static final int STATE_WRITING_REQUEST_BODY = 2;
    final OkHttpClient client;
    final BufferedSink sink;
    final BufferedSource source;
    int state = 0;
    final StreamAllocation streamAllocation;

    public Http1Codec(OkHttpClient okHttpClient, StreamAllocation streamAllocation2, BufferedSource bufferedSource, BufferedSink bufferedSink) {
        this.client = okHttpClient;
        this.streamAllocation = streamAllocation2;
        this.source = bufferedSource;
        this.sink = bufferedSink;
    }

    public final Sink createRequestBody(Request request, long j) {
        if ("chunked".equalsIgnoreCase(request.header("Transfer-Encoding"))) {
            return newChunkedSink();
        }
        if (j != -1) {
            return newFixedLengthSink(j);
        }
        throw new NetonException((Throwable) new IllegalStateException("Cannot stream a request body without chunked encoding or a known content length!"));
    }

    public final void cancel() {
        RealConnection connection = this.streamAllocation.connection();
        if (connection != null) {
            connection.cancel();
        }
    }

    public final void writeRequestHeaders(Request request) {
        writeRequest(request.headers(), RequestLine.get(request, this.streamAllocation.connection().route().proxy().type()));
    }

    public final ResponseBody openResponseBody(Response response) {
        this.streamAllocation.eventListener.responseBodyStart(this.streamAllocation.call);
        return new RealResponseBody(response.headers(), Okio.buffer(getTransferStream(response)));
    }

    private Source getTransferStream(Response response) {
        if (!HttpHeaders.hasBody(response)) {
            return newFixedLengthSource(0);
        }
        if ("chunked".equalsIgnoreCase(response.header("Transfer-Encoding"))) {
            return newChunkedSource(response.request().url());
        }
        long contentLength = HttpHeaders.contentLength(response);
        if (contentLength != -1) {
            return newFixedLengthSource(contentLength);
        }
        return newUnknownLengthSource();
    }

    public final boolean isClosed() {
        return this.state == STATE_CLOSED;
    }

    public final void flushRequest() {
        this.sink.flush();
    }

    public final void finishRequest() {
        this.sink.flush();
    }

    public final void writeRequest(Headers headers, String str) {
        if (this.state != 0) {
            throw new NetonException((Throwable) new IllegalStateException("state: " + this.state));
        }
        this.sink.writeUtf8(str).writeUtf8("\r\n");
        int size = headers.size();
        for (int i = 0; i < size; i++) {
            this.sink.writeUtf8(headers.name(i)).writeUtf8(": ").writeUtf8(headers.value(i)).writeUtf8("\r\n");
        }
        this.sink.writeUtf8("\r\n");
        this.state = 1;
    }

    public final Response.Builder readResponseHeaders(boolean z) {
        if (this.state == 1 || this.state == STATE_READ_RESPONSE_HEADERS) {
            try {
                StatusLine parse = StatusLine.parse(this.source.readUtf8LineStrict());
                Response.Builder headers = new Response.Builder().protocol(parse.protocol).code(parse.code).message(parse.message).headers(readHeaders());
                if (z && parse.code == 100) {
                    return null;
                }
                this.state = 4;
                return headers;
            } catch (EOFException e) {
                IOException iOException = new IOException("unexpected end of stream on " + this.streamAllocation);
                iOException.initCause(e);
                throw iOException;
            }
        } else {
            throw new NetonException((Throwable) new IllegalStateException("state: " + this.state));
        }
    }

    public final Headers readHeaders() {
        Headers.Builder builder = new Headers.Builder();
        while (true) {
            String readUtf8LineStrict = this.source.readUtf8LineStrict();
            if (readUtf8LineStrict.length() == 0) {
                return builder.build();
            }
            Internal.instance.addLenient(builder, readUtf8LineStrict);
        }
    }

    public final Sink newChunkedSink() {
        if (this.state != 1) {
            throw new NetonException((Throwable) new IllegalStateException("state: " + this.state));
        }
        this.state = 2;
        return new ChunkedSink();
    }

    public final Sink newFixedLengthSink(long j) {
        if (this.state != 1) {
            throw new NetonException((Throwable) new IllegalStateException("state: " + this.state));
        }
        this.state = 2;
        return new FixedLengthSink(j);
    }

    public final Source newFixedLengthSource(long j) {
        if (this.state != 4) {
            throw new NetonException((Throwable) new IllegalStateException("state: " + this.state));
        }
        this.state = 5;
        return new FixedLengthSource(j);
    }

    public final Source newChunkedSource(HttpUrl httpUrl) {
        if (this.state != 4) {
            throw new NetonException((Throwable) new IllegalStateException("state: " + this.state));
        }
        this.state = 5;
        return new ChunkedSource(httpUrl);
    }

    public final Source newUnknownLengthSource() {
        if (this.state != 4) {
            throw new NetonException((Throwable) new IllegalStateException("state: " + this.state));
        } else if (this.streamAllocation == null) {
            throw new NetonException((Throwable) new IllegalStateException("streamAllocation == null"));
        } else {
            this.state = 5;
            this.streamAllocation.noNewStreams();
            return new UnknownLengthSource();
        }
    }

    /* access modifiers changed from: package-private */
    public final void detachTimeout(ForwardingTimeout forwardingTimeout) {
        Timeout delegate = forwardingTimeout.delegate();
        forwardingTimeout.setDelegate(Timeout.NONE);
        delegate.clearDeadline();
        delegate.clearTimeout();
    }

    final class FixedLengthSink implements Sink {
        private long bytesRemaining;
        private boolean closed;
        private final ForwardingTimeout timeout = new ForwardingTimeout(Http1Codec.this.sink.timeout());

        FixedLengthSink(long j) {
            this.bytesRemaining = j;
        }

        public final Timeout timeout() {
            return this.timeout;
        }

        public final void write(Buffer buffer, long j) {
            if (this.closed) {
                throw new NetonException((Throwable) new IllegalStateException("closed"));
            }
            Util.checkOffsetAndCount(buffer.size(), 0, j);
            if (j > this.bytesRemaining) {
                throw new ProtocolException("expected " + this.bytesRemaining + " bytes but received " + j);
            }
            Http1Codec.this.sink.write(buffer, j);
            this.bytesRemaining -= j;
        }

        public final void flush() {
            if (!this.closed) {
                Http1Codec.this.sink.flush();
            }
        }

        public final void close() {
            if (!this.closed) {
                this.closed = true;
                if (this.bytesRemaining > 0) {
                    throw new ProtocolException("unexpected end of stream");
                }
                Http1Codec.this.detachTimeout(this.timeout);
                Http1Codec.this.state = Http1Codec.STATE_READ_RESPONSE_HEADERS;
            }
        }
    }

    final class ChunkedSink implements Sink {
        private boolean closed;
        private final ForwardingTimeout timeout = new ForwardingTimeout(Http1Codec.this.sink.timeout());

        ChunkedSink() {
        }

        public final Timeout timeout() {
            return this.timeout;
        }

        public final void write(Buffer buffer, long j) {
            if (this.closed) {
                throw new NetonException((Throwable) new IllegalStateException("closed"));
            } else if (j != 0) {
                Http1Codec.this.sink.writeHexadecimalUnsignedLong(j);
                Http1Codec.this.sink.writeUtf8("\r\n");
                Http1Codec.this.sink.write(buffer, j);
                Http1Codec.this.sink.writeUtf8("\r\n");
            }
        }

        public final synchronized void flush() {
            if (!this.closed) {
                Http1Codec.this.sink.flush();
            }
        }

        public final synchronized void close() {
            if (!this.closed) {
                this.closed = true;
                Http1Codec.this.sink.writeUtf8("0\r\n\r\n");
                Http1Codec.this.detachTimeout(this.timeout);
                Http1Codec.this.state = Http1Codec.STATE_READ_RESPONSE_HEADERS;
            }
        }
    }

    abstract class AbstractSource implements Source {
        protected boolean closed;
        protected final ForwardingTimeout timeout;

        private AbstractSource() {
            this.timeout = new ForwardingTimeout(Http1Codec.this.source.timeout());
        }

        public Timeout timeout() {
            return this.timeout;
        }

        public long read(Buffer buffer, long j) {
            try {
                return Http1Codec.this.source.read(buffer, j);
            } catch (IOException e) {
                endOfInput(false, e);
                throw e;
            }
        }

        /* access modifiers changed from: protected */
        public final void endOfInput(boolean z, IOException iOException) {
            if (Http1Codec.this.state != Http1Codec.STATE_CLOSED) {
                if (Http1Codec.this.state != 5) {
                    throw new NetonException((Throwable) new IllegalStateException("state: " + Http1Codec.this.state));
                }
                Http1Codec.this.detachTimeout(this.timeout);
                Http1Codec.this.state = Http1Codec.STATE_CLOSED;
                if (Http1Codec.this.streamAllocation != null) {
                    Http1Codec.this.streamAllocation.eventListener.responseBodyEnd(Http1Codec.this.streamAllocation.call, iOException);
                    Http1Codec.this.streamAllocation.streamFinished(!z, Http1Codec.this);
                }
            }
        }
    }

    class FixedLengthSource extends AbstractSource {
        private long bytesRemaining;

        FixedLengthSource(long j) {
            super();
            this.bytesRemaining = j;
            if (this.bytesRemaining == 0) {
                endOfInput(true, (IOException) null);
            }
        }

        public long read(Buffer buffer, long j) {
            if (j < 0) {
                throw new NetonException((Throwable) new IllegalArgumentException("byteCount < 0: " + j));
            } else if (this.closed) {
                throw new NetonException((Throwable) new IllegalStateException("closed"));
            } else if (this.bytesRemaining == 0) {
                return -1;
            } else {
                long read = super.read(buffer, Math.min(this.bytesRemaining, j));
                if (read == -1) {
                    ProtocolException protocolException = new ProtocolException("unexpected end of stream");
                    endOfInput(false, protocolException);
                    throw protocolException;
                }
                this.bytesRemaining -= read;
                if (this.bytesRemaining == 0) {
                    endOfInput(true, (IOException) null);
                }
                return read;
            }
        }

        public void close() {
            if (!this.closed) {
                if (this.bytesRemaining != 0 && !Util.discard(this, 100, TimeUnit.MILLISECONDS)) {
                    endOfInput(false, (IOException) null);
                }
                this.closed = true;
            }
        }
    }

    class ChunkedSource extends AbstractSource {
        private static final long NO_CHUNK_YET = -1;
        private long bytesRemainingInChunk = NO_CHUNK_YET;
        private boolean hasMoreChunks = true;
        private final HttpUrl url;

        ChunkedSource(HttpUrl httpUrl) {
            super();
            this.url = httpUrl;
        }

        public long read(Buffer buffer, long j) {
            if (j < 0) {
                throw new NetonException((Throwable) new IllegalArgumentException("byteCount < 0: " + j));
            } else if (this.closed) {
                throw new NetonException((Throwable) new IllegalStateException("closed"));
            } else if (!this.hasMoreChunks) {
                return NO_CHUNK_YET;
            } else {
                if (this.bytesRemainingInChunk == 0 || this.bytesRemainingInChunk == NO_CHUNK_YET) {
                    readChunkSize();
                    if (!this.hasMoreChunks) {
                        return NO_CHUNK_YET;
                    }
                }
                long read = super.read(buffer, Math.min(j, this.bytesRemainingInChunk));
                if (read == NO_CHUNK_YET) {
                    ProtocolException protocolException = new ProtocolException("unexpected end of stream");
                    endOfInput(false, protocolException);
                    throw protocolException;
                }
                this.bytesRemainingInChunk -= read;
                return read;
            }
        }

        private void readChunkSize() {
            if (this.bytesRemainingInChunk != NO_CHUNK_YET) {
                Http1Codec.this.source.readUtf8LineStrict();
            }
            try {
                this.bytesRemainingInChunk = Http1Codec.this.source.readHexadecimalUnsignedLong();
                String trim = Http1Codec.this.source.readUtf8LineStrict().trim();
                if (this.bytesRemainingInChunk < 0 || (!trim.isEmpty() && !trim.startsWith(Constants.SPLITER))) {
                    throw new ProtocolException("expected chunk size and optional extensions but was \"" + this.bytesRemainingInChunk + trim + "\"");
                } else if (this.bytesRemainingInChunk == 0) {
                    this.hasMoreChunks = false;
                    HttpHeaders.receiveHeaders(Http1Codec.this.client.cookieJar(), this.url, Http1Codec.this.readHeaders());
                    endOfInput(true, (IOException) null);
                }
            } catch (NumberFormatException e) {
                throw new ProtocolException(e.getMessage());
            }
        }

        public void close() {
            if (!this.closed) {
                if (this.hasMoreChunks && !Util.discard(this, 100, TimeUnit.MILLISECONDS)) {
                    endOfInput(false, (IOException) null);
                }
                this.closed = true;
            }
        }
    }

    class UnknownLengthSource extends AbstractSource {
        private boolean inputExhausted;

        UnknownLengthSource() {
            super();
        }

        public long read(Buffer buffer, long j) {
            if (j < 0) {
                throw new NetonException((Throwable) new IllegalArgumentException("byteCount < 0: " + j));
            } else if (this.closed) {
                throw new NetonException((Throwable) new IllegalStateException("closed"));
            } else if (this.inputExhausted) {
                return -1;
            } else {
                long read = super.read(buffer, j);
                if (read != -1) {
                    return read;
                }
                this.inputExhausted = true;
                endOfInput(true, (IOException) null);
                return -1;
            }
        }

        public void close() {
            if (!this.closed) {
                if (!this.inputExhausted) {
                    endOfInput(false, (IOException) null);
                }
                this.closed = true;
            }
        }
    }
}
