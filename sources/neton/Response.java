package neton;

import com.coloros.neton.NetonException;
import java.io.Closeable;
import java.util.Collections;
import java.util.List;
import neton.Headers;
import neton.client.Utils.ResponseCode;
import neton.internal.http.HttpHeaders;
import neton.internal.http.StatusLine;
import okio.Buffer;
import okio.BufferedSource;

public final class Response implements Closeable {
    final ResponseBody body;
    private volatile CacheControl cacheControl;
    final Response cacheResponse;
    final int code;
    final Handshake handshake;
    final Headers headers;
    final String message;
    final Response networkResponse;
    final Response priorResponse;
    final Protocol protocol;
    final long receivedResponseAtMillis;
    final Request request;
    final long sentRequestAtMillis;

    Response(Builder builder) {
        this.request = builder.request;
        this.protocol = builder.protocol;
        this.code = builder.code;
        this.message = builder.message;
        this.handshake = builder.handshake;
        this.headers = builder.headers.build();
        this.body = builder.body;
        this.networkResponse = builder.networkResponse;
        this.cacheResponse = builder.cacheResponse;
        this.priorResponse = builder.priorResponse;
        this.sentRequestAtMillis = builder.sentRequestAtMillis;
        this.receivedResponseAtMillis = builder.receivedResponseAtMillis;
    }

    public final Request request() {
        return this.request;
    }

    public final Protocol protocol() {
        return this.protocol;
    }

    public final int code() {
        return this.code;
    }

    public final boolean isSuccessful() {
        return this.code >= 200 && this.code < 300;
    }

    public final String message() {
        return this.message;
    }

    public final Handshake handshake() {
        return this.handshake;
    }

    public final List<String> headers(String str) {
        return this.headers.values(str);
    }

    public final String header(String str) {
        return header(str, (String) null);
    }

    public final String header(String str, String str2) {
        String str3 = this.headers.get(str);
        return str3 != null ? str3 : str2;
    }

    public final Headers headers() {
        return this.headers;
    }

    public final ResponseBody peekBody(long j) {
        Buffer buffer;
        BufferedSource source = this.body.source();
        source.request(j);
        Buffer clone = source.buffer().clone();
        if (clone.size() > j) {
            buffer = new Buffer();
            buffer.write(clone, j);
            clone.clear();
        } else {
            buffer = clone;
        }
        return ResponseBody.create(this.body.contentType(), buffer.size(), buffer);
    }

    public final ResponseBody body() {
        return this.body;
    }

    public final Builder newBuilder() {
        return new Builder(this);
    }

    public final boolean isRedirect() {
        switch (this.code) {
            case ResponseCode.CODE_3XX_MULTIPLE_CHOICES /*300*/:
            case ResponseCode.CODE_3XX_MOVED_PERMANENTLY /*301*/:
            case ResponseCode.CODE_3XX_FOUND /*302*/:
            case ResponseCode.CODE_3XX_SEE_OTHER /*303*/:
            case 307:
            case StatusLine.HTTP_PERM_REDIRECT /*308*/:
                return true;
            default:
                return false;
        }
    }

    public final Response networkResponse() {
        return this.networkResponse;
    }

    public final Response cacheResponse() {
        return this.cacheResponse;
    }

    public final Response priorResponse() {
        return this.priorResponse;
    }

    public final List<Challenge> challenges() {
        String str;
        if (this.code == 401) {
            str = "WWW-Authenticate";
        } else if (this.code != 407) {
            return Collections.emptyList();
        } else {
            str = "Proxy-Authenticate";
        }
        return HttpHeaders.parseChallenges(headers(), str);
    }

    public final CacheControl cacheControl() {
        CacheControl cacheControl2 = this.cacheControl;
        if (cacheControl2 != null) {
            return cacheControl2;
        }
        CacheControl parse = CacheControl.parse(this.headers);
        this.cacheControl = parse;
        return parse;
    }

    public final long sentRequestAtMillis() {
        return this.sentRequestAtMillis;
    }

    public final long receivedResponseAtMillis() {
        return this.receivedResponseAtMillis;
    }

    public final void close() {
        if (this.body == null) {
            throw new NetonException((Throwable) new IllegalStateException("response is not eligible for a body and must not be closed"));
        }
        this.body.close();
    }

    public final String toString() {
        return "Response{protocol=" + this.protocol + ", code=" + this.code + ", message=" + this.message + ", url=" + this.request.url() + '}';
    }

    public class Builder {
        ResponseBody body;
        Response cacheResponse;
        int code;
        Handshake handshake;
        Headers.Builder headers;
        String message;
        Response networkResponse;
        Response priorResponse;
        Protocol protocol;
        long receivedResponseAtMillis;
        Request request;
        long sentRequestAtMillis;

        public Builder() {
            this.code = -1;
            this.headers = new Headers.Builder();
        }

        Builder(Response response) {
            this.code = -1;
            this.request = response.request;
            this.protocol = response.protocol;
            this.code = response.code;
            this.message = response.message;
            this.handshake = response.handshake;
            this.headers = response.headers.newBuilder();
            this.body = response.body;
            this.networkResponse = response.networkResponse;
            this.cacheResponse = response.cacheResponse;
            this.priorResponse = response.priorResponse;
            this.sentRequestAtMillis = response.sentRequestAtMillis;
            this.receivedResponseAtMillis = response.receivedResponseAtMillis;
        }

        public Builder request(Request request2) {
            this.request = request2;
            return this;
        }

        public Builder protocol(Protocol protocol2) {
            this.protocol = protocol2;
            return this;
        }

        public Builder code(int i) {
            this.code = i;
            return this;
        }

        public Builder message(String str) {
            this.message = str;
            return this;
        }

        public Builder handshake(Handshake handshake2) {
            this.handshake = handshake2;
            return this;
        }

        public Builder header(String str, String str2) {
            this.headers.set(str, str2);
            return this;
        }

        public Builder addHeader(String str, String str2) {
            this.headers.add(str, str2);
            return this;
        }

        public Builder removeHeader(String str) {
            this.headers.removeAll(str);
            return this;
        }

        public Builder headers(Headers headers2) {
            this.headers = headers2.newBuilder();
            return this;
        }

        public Builder body(ResponseBody responseBody) {
            this.body = responseBody;
            return this;
        }

        public Builder networkResponse(Response response) {
            if (response != null) {
                checkSupportResponse("networkResponse", response);
            }
            this.networkResponse = response;
            return this;
        }

        public Builder cacheResponse(Response response) {
            if (response != null) {
                checkSupportResponse("cacheResponse", response);
            }
            this.cacheResponse = response;
            return this;
        }

        private void checkSupportResponse(String str, Response response) {
            if (response.body != null) {
                throw new NetonException((Throwable) new IllegalArgumentException(str + ".body != null"));
            } else if (response.networkResponse != null) {
                throw new NetonException((Throwable) new IllegalArgumentException(str + ".networkResponse != null"));
            } else if (response.cacheResponse != null) {
                throw new NetonException((Throwable) new IllegalArgumentException(str + ".cacheResponse != null"));
            } else if (response.priorResponse != null) {
                throw new NetonException((Throwable) new IllegalArgumentException(str + ".priorResponse != null"));
            }
        }

        public Builder priorResponse(Response response) {
            if (response != null) {
                checkPriorResponse(response);
            }
            this.priorResponse = response;
            return this;
        }

        private void checkPriorResponse(Response response) {
            if (response.body != null) {
                throw new NetonException((Throwable) new IllegalArgumentException("priorResponse.body != null"));
            }
        }

        public Builder sentRequestAtMillis(long j) {
            this.sentRequestAtMillis = j;
            return this;
        }

        public Builder receivedResponseAtMillis(long j) {
            this.receivedResponseAtMillis = j;
            return this;
        }

        public Response build() {
            if (this.request == null) {
                throw new NetonException((Throwable) new IllegalStateException("request == null"));
            } else if (this.protocol == null) {
                throw new NetonException((Throwable) new IllegalStateException("protocol == null"));
            } else if (this.code < 0) {
                throw new NetonException((Throwable) new IllegalStateException("code < 0: " + this.code));
            } else if (this.message != null) {
                return new Response(this);
            } else {
                throw new NetonException((Throwable) new IllegalStateException("message == null"));
            }
        }
    }
}
