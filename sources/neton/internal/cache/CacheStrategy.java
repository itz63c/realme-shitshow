package neton.internal.cache;

import com.coloros.neton.NetonException;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import neton.CacheControl;
import neton.Headers;
import neton.Request;
import neton.Response;
import neton.client.Utils.ResponseCode;
import neton.internal.Internal;
import neton.internal.http.HttpDate;
import neton.internal.http.HttpHeaders;
import neton.internal.http.StatusLine;

public final class CacheStrategy {
    public final Response cacheResponse;
    public final Request networkRequest;

    CacheStrategy(Request request, Response response) {
        this.networkRequest = request;
        this.cacheResponse = response;
    }

    public static boolean isCacheable(Response response, Request request) {
        switch (response.code()) {
            case ResponseCode.CODE_2XX_SUCCESS:
            case ResponseCode.CODE_2XX_NON_AUTHORITATIVE_INFORMATION:
            case ResponseCode.CODE_2XX_NO_CONTENT:
            case ResponseCode.CODE_3XX_MULTIPLE_CHOICES:
            case ResponseCode.CODE_3XX_MOVED_PERMANENTLY:
            case StatusLine.HTTP_PERM_REDIRECT /*308*/:
            case ResponseCode.CODE_4XX_NOT_FOUND:
            case ResponseCode.CODE_4XX_METHOD_NOT_ALLOWED:
            case ResponseCode.CODE_4XX_GONE:
            case ResponseCode.CODE_4XX_REQUEST_URI_TOO_LARGE:
            case ResponseCode.CODE_5XX_NOT_IMPLEMENTED:
                break;
            case ResponseCode.CODE_3XX_FOUND:
            case 307:
                if (response.header("Expires") == null && response.cacheControl().maxAgeSeconds() == -1 && !response.cacheControl().isPublic() && !response.cacheControl().isPrivate()) {
                    return false;
                }
            default:
                return false;
        }
        return !response.cacheControl().noStore() && !request.cacheControl().noStore();
    }

    public class Factory {
        private int ageSeconds = -1;
        final Response cacheResponse;
        private String etag;
        private Date expires;
        private Date lastModified;
        private String lastModifiedString;
        final long nowMillis;
        private long receivedResponseMillis;
        final Request request;
        private long sentRequestMillis;
        private Date servedDate;
        private String servedDateString;

        public Factory(long j, Request request2, Response response) {
            this.nowMillis = j;
            this.request = request2;
            this.cacheResponse = response;
            if (response != null) {
                this.sentRequestMillis = response.sentRequestAtMillis();
                this.receivedResponseMillis = response.receivedResponseAtMillis();
                Headers headers = response.headers();
                int size = headers.size();
                for (int i = 0; i < size; i++) {
                    String name = headers.name(i);
                    String value = headers.value(i);
                    if ("Date".equalsIgnoreCase(name)) {
                        this.servedDate = HttpDate.parse(value);
                        this.servedDateString = value;
                    } else if ("Expires".equalsIgnoreCase(name)) {
                        this.expires = HttpDate.parse(value);
                    } else if ("Last-Modified".equalsIgnoreCase(name)) {
                        this.lastModified = HttpDate.parse(value);
                        this.lastModifiedString = value;
                    } else if ("ETag".equalsIgnoreCase(name)) {
                        this.etag = value;
                    } else if ("Age".equalsIgnoreCase(name)) {
                        this.ageSeconds = HttpHeaders.parseSeconds(value, -1);
                    }
                }
            }
        }

        public CacheStrategy get() {
            CacheStrategy candidate = getCandidate();
            if (candidate.networkRequest == null || !this.request.cacheControl().onlyIfCached()) {
                return candidate;
            }
            return new CacheStrategy((Request) null, (Response) null);
        }

        private CacheStrategy getCandidate() {
            long j;
            String str;
            String str2;
            Request request2;
            long j2 = 0;
            if (this.cacheResponse == null) {
                return new CacheStrategy(this.request, (Response) null);
            }
            if (this.request.isHttps() && this.cacheResponse.handshake() == null) {
                return new CacheStrategy(this.request, (Response) null);
            }
            if (!CacheStrategy.isCacheable(this.cacheResponse, this.request)) {
                return new CacheStrategy(this.request, (Response) null);
            }
            CacheControl cacheControl = this.request.cacheControl();
            if (cacheControl.noCache() || hasConditions(this.request)) {
                return new CacheStrategy(this.request, (Response) null);
            }
            CacheControl cacheControl2 = this.cacheResponse.cacheControl();
            if (cacheControl2.immutable()) {
                return new CacheStrategy((Request) null, this.cacheResponse);
            }
            long cacheResponseAge = cacheResponseAge();
            long computeFreshnessLifetime = computeFreshnessLifetime();
            if (cacheControl.maxAgeSeconds() != -1) {
                computeFreshnessLifetime = Math.min(computeFreshnessLifetime, TimeUnit.SECONDS.toMillis((long) cacheControl.maxAgeSeconds()));
            }
            if (cacheControl.minFreshSeconds() != -1) {
                j = TimeUnit.SECONDS.toMillis((long) cacheControl.minFreshSeconds());
            } else {
                j = 0;
            }
            if (!cacheControl2.mustRevalidate() && cacheControl.maxStaleSeconds() != -1) {
                j2 = TimeUnit.SECONDS.toMillis((long) cacheControl.maxStaleSeconds());
            }
            if (cacheControl2.noCache() || cacheResponseAge + j >= j2 + computeFreshnessLifetime) {
                if (this.etag != null) {
                    str = "If-None-Match";
                    str2 = this.etag;
                } else if (this.lastModified != null) {
                    str = "If-Modified-Since";
                    str2 = this.lastModifiedString;
                } else if (this.servedDate == null) {
                    return new CacheStrategy(this.request, (Response) null);
                } else {
                    str = "If-Modified-Since";
                    str2 = this.servedDateString;
                }
                Headers.Builder newBuilder = this.request.headers().newBuilder();
                Internal.instance.addLenient(newBuilder, str, str2);
                try {
                    request2 = this.request.newBuilder().headers(newBuilder.build()).build();
                } catch (NetonException e) {
                    NetonException netonException = e;
                    request2 = this.request;
                    netonException.printStackTrace();
                }
                return new CacheStrategy(request2, this.cacheResponse);
            }
            Response.Builder newBuilder2 = this.cacheResponse.newBuilder();
            if (j + cacheResponseAge >= computeFreshnessLifetime) {
                try {
                    newBuilder2.addHeader("Warning", "110 HttpURLConnection \"Response is stale\"");
                } catch (NetonException e2) {
                }
            }
            if (cacheResponseAge > 86400000 && isFreshnessLifetimeHeuristic()) {
                try {
                    newBuilder2.addHeader("Warning", "113 HttpURLConnection \"Heuristic expiration\"");
                } catch (NetonException e3) {
                }
            }
            return new CacheStrategy((Request) null, newBuilder2.build());
        }

        private long computeFreshnessLifetime() {
            CacheControl cacheControl = this.cacheResponse.cacheControl();
            if (cacheControl.maxAgeSeconds() != -1) {
                return TimeUnit.SECONDS.toMillis((long) cacheControl.maxAgeSeconds());
            }
            if (this.expires != null) {
                long time = this.expires.getTime() - (this.servedDate != null ? this.servedDate.getTime() : this.receivedResponseMillis);
                if (time <= 0) {
                    return 0;
                }
                return time;
            } else if (this.lastModified == null || this.cacheResponse.request().url().query() != null) {
                return 0;
            } else {
                long time2 = (this.servedDate != null ? this.servedDate.getTime() : this.sentRequestMillis) - this.lastModified.getTime();
                if (time2 > 0) {
                    return time2 / 10;
                }
                return 0;
            }
        }

        private long cacheResponseAge() {
            long j = 0;
            if (this.servedDate != null) {
                j = Math.max(0, this.receivedResponseMillis - this.servedDate.getTime());
            }
            if (this.ageSeconds != -1) {
                j = Math.max(j, TimeUnit.SECONDS.toMillis((long) this.ageSeconds));
            }
            return j + (this.receivedResponseMillis - this.sentRequestMillis) + (this.nowMillis - this.receivedResponseMillis);
        }

        private boolean isFreshnessLifetimeHeuristic() {
            return this.cacheResponse.cacheControl().maxAgeSeconds() == -1 && this.expires == null;
        }

        private static boolean hasConditions(Request request2) {
            return (request2.header("If-Modified-Since") == null && request2.header("If-None-Match") == null) ? false : true;
        }
    }
}
