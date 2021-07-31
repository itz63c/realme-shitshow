package neton.internal.p017ws;

import com.coloros.neton.NetonException;
import java.io.IOException;
import java.util.Random;
import okio.Buffer;
import okio.BufferedSink;
import okio.ByteString;
import okio.Sink;
import okio.Timeout;

/* renamed from: neton.internal.ws.WebSocketWriter */
final class WebSocketWriter {
    static final /* synthetic */ boolean $assertionsDisabled = (!WebSocketWriter.class.desiredAssertionStatus());
    boolean activeWriter;
    final Buffer buffer = new Buffer();
    final FrameSink frameSink = new FrameSink();
    final boolean isClient;
    final byte[] maskBuffer;
    final byte[] maskKey;
    final Random random;
    final BufferedSink sink;
    boolean writerClosed;

    WebSocketWriter(boolean z, BufferedSink bufferedSink, Random random2) {
        byte[] bArr;
        byte[] bArr2 = null;
        if (bufferedSink == null) {
            throw new NetonException((Throwable) new NullPointerException("sink == null"));
        } else if (random2 == null) {
            throw new NetonException((Throwable) new NullPointerException("random == null"));
        } else {
            this.isClient = z;
            this.sink = bufferedSink;
            this.random = random2;
            if (z) {
                bArr = new byte[4];
            } else {
                bArr = null;
            }
            this.maskKey = bArr;
            this.maskBuffer = z ? new byte[8192] : bArr2;
        }
    }

    /* access modifiers changed from: package-private */
    public final void writePing(ByteString byteString) {
        synchronized (this) {
            writeControlFrameSynchronized(9, byteString);
        }
    }

    /* access modifiers changed from: package-private */
    public final void writePong(ByteString byteString) {
        synchronized (this) {
            writeControlFrameSynchronized(10, byteString);
        }
    }

    /* access modifiers changed from: package-private */
    public final void writeClose(int i, ByteString byteString) {
        ByteString byteString2 = ByteString.EMPTY;
        if (!(i == 0 && byteString == null)) {
            if (i != 0) {
                WebSocketProtocol.validateCloseCode(i);
            }
            Buffer buffer2 = new Buffer();
            buffer2.writeShort(i);
            if (byteString != null) {
                buffer2.write(byteString);
            }
            byteString2 = buffer2.readByteString();
        }
        synchronized (this) {
            try {
                writeControlFrameSynchronized(8, byteString2);
                this.writerClosed = true;
            } catch (Throwable th) {
                this.writerClosed = true;
                throw th;
            }
        }
    }

    private void writeControlFrameSynchronized(int i, ByteString byteString) {
        if (!$assertionsDisabled && !Thread.holdsLock(this)) {
            throw new AssertionError();
        } else if (this.writerClosed) {
            throw new IOException("closed");
        } else {
            int size = byteString.size();
            if (((long) size) > 125) {
                throw new NetonException((Throwable) new IllegalArgumentException("Payload size must be less than or equal to 125"));
            }
            this.sink.writeByte(i | 128);
            if (this.isClient) {
                this.sink.writeByte(size | 128);
                this.random.nextBytes(this.maskKey);
                this.sink.write(this.maskKey);
                byte[] byteArray = byteString.toByteArray();
                WebSocketProtocol.toggleMask(byteArray, (long) byteArray.length, this.maskKey, 0);
                this.sink.write(byteArray);
            } else {
                this.sink.writeByte(size);
                this.sink.write(byteString);
            }
            this.sink.flush();
        }
    }

    /* access modifiers changed from: package-private */
    public final Sink newMessageSink(int i, long j) {
        if (this.activeWriter) {
            throw new NetonException((Throwable) new IllegalStateException("Another message writer is active. Did you call close()?"));
        }
        this.activeWriter = true;
        this.frameSink.formatOpcode = i;
        this.frameSink.contentLength = j;
        this.frameSink.isFirstFrame = true;
        this.frameSink.closed = false;
        return this.frameSink;
    }

    /* access modifiers changed from: package-private */
    public final void writeMessageFrameSynchronized(int i, long j, boolean z, boolean z2) {
        int i2;
        if (!$assertionsDisabled && !Thread.holdsLock(this)) {
            throw new AssertionError();
        } else if (this.writerClosed) {
            throw new IOException("closed");
        } else {
            int i3 = z ? i : 0;
            if (z2) {
                i3 |= 128;
            }
            this.sink.writeByte(i3);
            if (this.isClient) {
                i2 = 128;
            } else {
                i2 = 0;
            }
            if (j <= 125) {
                this.sink.writeByte(i2 | ((int) j));
            } else if (j <= 65535) {
                this.sink.writeByte(i2 | 126);
                this.sink.writeShort((int) j);
            } else {
                this.sink.writeByte(i2 | 127);
                this.sink.writeLong(j);
            }
            if (this.isClient) {
                this.random.nextBytes(this.maskKey);
                this.sink.write(this.maskKey);
                long j2 = 0;
                while (j2 < j) {
                    int read = this.buffer.read(this.maskBuffer, 0, (int) Math.min(j, (long) this.maskBuffer.length));
                    if (read == -1) {
                        throw new AssertionError();
                    }
                    WebSocketProtocol.toggleMask(this.maskBuffer, (long) read, this.maskKey, j2);
                    this.sink.write(this.maskBuffer, 0, read);
                    j2 += (long) read;
                }
            } else {
                this.sink.write(this.buffer, j);
            }
            this.sink.emit();
        }
    }

    /* renamed from: neton.internal.ws.WebSocketWriter$FrameSink */
    final class FrameSink implements Sink {
        boolean closed;
        long contentLength;
        int formatOpcode;
        boolean isFirstFrame;

        FrameSink() {
        }

        public final void write(Buffer buffer, long j) {
            if (this.closed) {
                throw new IOException("closed");
            }
            WebSocketWriter.this.buffer.write(buffer, j);
            boolean z = this.isFirstFrame && this.contentLength != -1 && WebSocketWriter.this.buffer.size() > this.contentLength - 8192;
            long completeSegmentByteCount = WebSocketWriter.this.buffer.completeSegmentByteCount();
            if (completeSegmentByteCount > 0 && !z) {
                synchronized (WebSocketWriter.this) {
                    WebSocketWriter.this.writeMessageFrameSynchronized(this.formatOpcode, completeSegmentByteCount, this.isFirstFrame, false);
                }
                this.isFirstFrame = false;
            }
        }

        public final void flush() {
            if (this.closed) {
                throw new IOException("closed");
            }
            synchronized (WebSocketWriter.this) {
                WebSocketWriter.this.writeMessageFrameSynchronized(this.formatOpcode, WebSocketWriter.this.buffer.size(), this.isFirstFrame, false);
            }
            this.isFirstFrame = false;
        }

        public final Timeout timeout() {
            return WebSocketWriter.this.sink.timeout();
        }

        public final void close() {
            if (this.closed) {
                throw new IOException("closed");
            }
            synchronized (WebSocketWriter.this) {
                WebSocketWriter.this.writeMessageFrameSynchronized(this.formatOpcode, WebSocketWriter.this.buffer.size(), this.isFirstFrame, true);
            }
            this.closed = true;
            WebSocketWriter.this.activeWriter = false;
        }
    }
}
