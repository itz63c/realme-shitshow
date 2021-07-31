package neton;

import com.coloros.neton.NetonException;
import java.net.URL;
import java.util.List;
import neton.Headers;
import neton.client.statistics.RequestStatistic;
import neton.internal.Util;
import neton.internal.http.HttpMethod;

public final class Request {
    final RequestBody body;
    private volatile CacheControl cacheControl;
    /* access modifiers changed from: private */
    public final Headers headers;
    /* access modifiers changed from: private */
    public final String method;
    /* access modifiers changed from: private */
    public final RequestStatistic requestStatistic;
    /* access modifiers changed from: private */
    public final int retryTimes;
    /* access modifiers changed from: private */
    public final Object tag;
    /* access modifiers changed from: private */
    public final Timeout timeout;
    /* access modifiers changed from: private */
    public final HttpUrl url;

    private Request(Builder builder) {
        Object obj;
        this.url = builder.url;
        this.method = builder.method;
        this.headers = builder.headers.build();
        this.body = builder.body;
        if (builder.tag != null) {
            obj = builder.tag;
        } else {
            obj = this;
        }
        this.tag = obj;
        this.timeout = builder.timeout;
        this.requestStatistic = builder.requestStatistic;
        this.retryTimes = builder.retryTimes;
    }

    public final HttpUrl url() {
        return this.url;
    }

    public final String method() {
        return this.method;
    }

    public final Headers headers() {
        return this.headers;
    }

    public final String header(String str) {
        return this.headers.get(str);
    }

    public final List<String> headers(String str) {
        return this.headers.values(str);
    }

    public final RequestBody body() {
        return this.body;
    }

    public final Object tag() {
        return this.tag;
    }

    public final Builder newBuilder() {
        return new Builder();
    }

    public final Timeout timeout() {
        return this.timeout;
    }

    public final int retryTimes() {
        return this.retryTimes;
    }

    public final RequestStatistic requestStatistic() {
        return this.requestStatistic;
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

    public final boolean isHttps() {
        return this.url.isHttps();
    }

    public final String toString() {
        return "Request{method=" + this.method + ", url=" + this.url + ", tag=" + (this.tag != this ? this.tag : null) + '}';
    }

    public class Builder {
        /* access modifiers changed from: private */
        public RequestBody body;
        /* access modifiers changed from: private */
        public Headers.Builder headers;
        /* access modifiers changed from: private */
        public String method;
        /* access modifiers changed from: private */
        public RequestStatistic requestStatistic;
        /* access modifiers changed from: private */
        public int retryTimes;
        /* access modifiers changed from: private */
        public Object tag;
        /* access modifiers changed from: private */
        public Timeout timeout;
        /* access modifiers changed from: private */
        public HttpUrl url;

        public Builder() {
            this.retryTimes = -1;
            this.method = HttpMethod.GET;
            this.headers = new Headers.Builder();
        }

        private Builder(Request request) {
            this.retryTimes = -1;
            this.url = request.url;
            this.method = request.method;
            this.body = request.body;
            this.tag = request.tag;
            this.headers = request.headers.newBuilder();
            this.timeout = request.timeout;
            this.requestStatistic = request.requestStatistic;
            this.retryTimes = request.retryTimes;
        }

        public Builder url(HttpUrl httpUrl) {
            if (httpUrl == null) {
                throw new NetonException((Throwable) new NullPointerException("url == null"));
            }
            this.url = httpUrl;
            return this;
        }

        public Builder url(String str) {
            if (str == null) {
                throw new NetonException((Throwable) new NullPointerException("url == null"));
            }
            if (str.regionMatches(true, 0, "ws:", 0, 3)) {
                str = "http:" + str.substring(3);
            } else if (str.regionMatches(true, 0, "wss:", 0, 4)) {
                str = "https:" + str.substring(4);
            }
            HttpUrl parse = HttpUrl.parse(str);
            if (parse != null) {
                return url(parse);
            }
            throw new NetonException((Throwable) new IllegalArgumentException("unexpected url: " + str));
        }

        public Builder url(URL url2) {
            if (url2 == null) {
                throw new NetonException((Throwable) new NullPointerException("url == null"));
            }
            HttpUrl httpUrl = HttpUrl.get(url2);
            if (httpUrl != null) {
                return url(httpUrl);
            }
            throw new NetonException((Throwable) new IllegalArgumentException("unexpected url: " + url2));
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

        public Builder cacheControl(CacheControl cacheControl) {
            String cacheControl2 = cacheControl.toString();
            if (cacheControl2.isEmpty()) {
                return removeHeader("Cache-Control");
            }
            return header("Cache-Control", cacheControl2);
        }

        public Builder get() {
            return method(HttpMethod.GET, (RequestBody) null);
        }

        public Builder head() {
            return method(HttpMethod.HEAD, (RequestBody) null);
        }

        public Builder post(RequestBody requestBody) {
            return method(HttpMethod.POST, requestBody);
        }

        public Builder delete(RequestBody requestBody) {
            return method(HttpMethod.DELETE, requestBody);
        }

        public Builder delete() {
            return delete(Util.EMPTY_REQUEST);
        }

        public Builder put(RequestBody requestBody) {
            return method(HttpMethod.PUT, requestBody);
        }

        public Builder patch(RequestBody requestBody) {
            return method("PATCH", requestBody);
        }

        public Builder retryTimes(int i) {
            this.retryTimes = i;
            return this;
        }

        public Builder method(String str, RequestBody requestBody) {
            if (str == null) {
                throw new NetonException((Throwable) new NullPointerException("method == null"));
            } else if (str.length() == 0) {
                throw new NetonException((Throwable) new IllegalArgumentException("method.length() == 0"));
            } else if (requestBody != null && !HttpMethod.permitsRequestBody(str)) {
                throw new NetonException((Throwable) new IllegalArgumentException("method " + str + " must not have a request body."));
            } else if (requestBody != null || !HttpMethod.requiresRequestBody(str)) {
                this.method = str;
                this.body = requestBody;
                return this;
            } else {
                throw new NetonException((Throwable) new IllegalArgumentException("method " + str + " must have a request body."));
            }
        }

        public Builder tag(Object obj) {
            this.tag = obj;
            return this;
        }

        public Builder timeout(Timeout timeout2) {
            if (timeout2 != null) {
                this.timeout = timeout2;
            }
            return this;
        }

        public Builder requestStatistic(RequestStatistic requestStatistic2) {
            if (requestStatistic2 != null) {
                this.requestStatistic = requestStatistic2;
            }
            return this;
        }

        public Request build() {
            if (this.url != null) {
                return new Request(this);
            }
            throw new NetonException((Throwable) new IllegalStateException("url == null"));
        }
    }
}
