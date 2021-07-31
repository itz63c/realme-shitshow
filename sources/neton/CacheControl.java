package neton;

import com.coloros.neton.BuildConfig;
import com.coloros.neton.NetonException;
import java.util.concurrent.TimeUnit;
import neton.internal.http.HttpHeaders;

public final class CacheControl {
    public static final CacheControl FORCE_CACHE;
    public static final CacheControl FORCE_NETWORK = new Builder().noCache().build();
    String headerValue;
    private final boolean immutable;
    private final boolean isPrivate;
    private final boolean isPublic;
    private final int maxAgeSeconds;
    private final int maxStaleSeconds;
    private final int minFreshSeconds;
    private final boolean mustRevalidate;
    private final boolean noCache;
    private final boolean noStore;
    private final boolean noTransform;
    private final boolean onlyIfCached;
    private final int sMaxAgeSeconds;

    static {
        Builder builder = new Builder();
        try {
            builder.onlyIfCached().maxStale(Integer.MAX_VALUE, TimeUnit.SECONDS);
        } catch (NetonException e) {
            e.printStackTrace();
        }
        FORCE_CACHE = builder.build();
    }

    private CacheControl(boolean z, boolean z2, int i, int i2, boolean z3, boolean z4, boolean z5, int i3, int i4, boolean z6, boolean z7, boolean z8, String str) {
        this.noCache = z;
        this.noStore = z2;
        this.maxAgeSeconds = i;
        this.sMaxAgeSeconds = i2;
        this.isPrivate = z3;
        this.isPublic = z4;
        this.mustRevalidate = z5;
        this.maxStaleSeconds = i3;
        this.minFreshSeconds = i4;
        this.onlyIfCached = z6;
        this.noTransform = z7;
        this.immutable = z8;
        this.headerValue = str;
    }

    CacheControl(Builder builder) {
        this.noCache = builder.noCache;
        this.noStore = builder.noStore;
        this.maxAgeSeconds = builder.maxAgeSeconds;
        this.sMaxAgeSeconds = -1;
        this.isPrivate = false;
        this.isPublic = false;
        this.mustRevalidate = false;
        this.maxStaleSeconds = builder.maxStaleSeconds;
        this.minFreshSeconds = builder.minFreshSeconds;
        this.onlyIfCached = builder.onlyIfCached;
        this.noTransform = builder.noTransform;
        this.immutable = builder.immutable;
    }

    public final boolean noCache() {
        return this.noCache;
    }

    public final boolean noStore() {
        return this.noStore;
    }

    public final int maxAgeSeconds() {
        return this.maxAgeSeconds;
    }

    public final int sMaxAgeSeconds() {
        return this.sMaxAgeSeconds;
    }

    public final boolean isPrivate() {
        return this.isPrivate;
    }

    public final boolean isPublic() {
        return this.isPublic;
    }

    public final boolean mustRevalidate() {
        return this.mustRevalidate;
    }

    public final int maxStaleSeconds() {
        return this.maxStaleSeconds;
    }

    public final int minFreshSeconds() {
        return this.minFreshSeconds;
    }

    public final boolean onlyIfCached() {
        return this.onlyIfCached;
    }

    public final boolean noTransform() {
        return this.noTransform;
    }

    public final boolean immutable() {
        return this.immutable;
    }

    public static CacheControl parse(Headers headers) {
        String str;
        boolean z;
        String str2;
        int i;
        boolean z2 = false;
        boolean z3 = false;
        int i2 = -1;
        int i3 = -1;
        boolean z4 = false;
        boolean z5 = false;
        boolean z6 = false;
        int i4 = -1;
        int i5 = -1;
        boolean z7 = false;
        boolean z8 = false;
        boolean z9 = false;
        boolean z10 = true;
        String str3 = null;
        int size = headers.size();
        int i6 = 0;
        while (i6 < size) {
            String name = headers.name(i6);
            String value = headers.value(i6);
            if (name.equalsIgnoreCase("Cache-Control")) {
                if (str3 != null) {
                    z10 = false;
                } else {
                    str3 = value;
                }
            } else if (name.equalsIgnoreCase("Pragma")) {
                z10 = false;
            } else {
                z = z2;
                i6++;
                z2 = z;
            }
            int i7 = 0;
            z = z2;
            while (i7 < value.length()) {
                int skipUntil = HttpHeaders.skipUntil(value, i7, "=,;");
                String trim = value.substring(i7, skipUntil).trim();
                if (skipUntil == value.length() || value.charAt(skipUntil) == ',' || value.charAt(skipUntil) == ';') {
                    i = skipUntil + 1;
                    str2 = null;
                } else {
                    int skipWhitespace = HttpHeaders.skipWhitespace(value, skipUntil + 1);
                    if (skipWhitespace >= value.length() || value.charAt(skipWhitespace) != '\"') {
                        i = HttpHeaders.skipUntil(value, skipWhitespace, ",;");
                        str2 = value.substring(skipWhitespace, i).trim();
                    } else {
                        int i8 = skipWhitespace + 1;
                        int skipUntil2 = HttpHeaders.skipUntil(value, i8, "\"");
                        str2 = value.substring(i8, skipUntil2);
                        i = skipUntil2 + 1;
                    }
                }
                if ("no-cache".equalsIgnoreCase(trim)) {
                    z = true;
                    i7 = i;
                } else if ("no-store".equalsIgnoreCase(trim)) {
                    z3 = true;
                    i7 = i;
                } else if ("max-age".equalsIgnoreCase(trim)) {
                    i2 = HttpHeaders.parseSeconds(str2, -1);
                    i7 = i;
                } else if ("s-maxage".equalsIgnoreCase(trim)) {
                    i3 = HttpHeaders.parseSeconds(str2, -1);
                    i7 = i;
                } else if ("private".equalsIgnoreCase(trim)) {
                    z4 = true;
                    i7 = i;
                } else if ("public".equalsIgnoreCase(trim)) {
                    z5 = true;
                    i7 = i;
                } else if ("must-revalidate".equalsIgnoreCase(trim)) {
                    z6 = true;
                    i7 = i;
                } else if ("max-stale".equalsIgnoreCase(trim)) {
                    i4 = HttpHeaders.parseSeconds(str2, Integer.MAX_VALUE);
                    i7 = i;
                } else if ("min-fresh".equalsIgnoreCase(trim)) {
                    i5 = HttpHeaders.parseSeconds(str2, -1);
                    i7 = i;
                } else if ("only-if-cached".equalsIgnoreCase(trim)) {
                    z7 = true;
                    i7 = i;
                } else if ("no-transform".equalsIgnoreCase(trim)) {
                    z8 = true;
                    i7 = i;
                } else {
                    if ("immutable".equalsIgnoreCase(trim)) {
                        z9 = true;
                    }
                    i7 = i;
                }
            }
            i6++;
            z2 = z;
        }
        if (!z10) {
            str = null;
        } else {
            str = str3;
        }
        return new CacheControl(z2, z3, i2, i3, z4, z5, z6, i4, i5, z7, z8, z9, str);
    }

    public final String toString() {
        String str = this.headerValue;
        if (str != null) {
            return str;
        }
        String headerValue2 = headerValue();
        this.headerValue = headerValue2;
        return headerValue2;
    }

    private String headerValue() {
        StringBuilder sb = new StringBuilder();
        if (this.noCache) {
            sb.append("no-cache, ");
        }
        if (this.noStore) {
            sb.append("no-store, ");
        }
        if (this.maxAgeSeconds != -1) {
            sb.append("max-age=").append(this.maxAgeSeconds).append(", ");
        }
        if (this.sMaxAgeSeconds != -1) {
            sb.append("s-maxage=").append(this.sMaxAgeSeconds).append(", ");
        }
        if (this.isPrivate) {
            sb.append("private, ");
        }
        if (this.isPublic) {
            sb.append("public, ");
        }
        if (this.mustRevalidate) {
            sb.append("must-revalidate, ");
        }
        if (this.maxStaleSeconds != -1) {
            sb.append("max-stale=").append(this.maxStaleSeconds).append(", ");
        }
        if (this.minFreshSeconds != -1) {
            sb.append("min-fresh=").append(this.minFreshSeconds).append(", ");
        }
        if (this.onlyIfCached) {
            sb.append("only-if-cached, ");
        }
        if (this.noTransform) {
            sb.append("no-transform, ");
        }
        if (this.immutable) {
            sb.append("immutable, ");
        }
        if (sb.length() == 0) {
            return BuildConfig.FLAVOR;
        }
        sb.delete(sb.length() - 2, sb.length());
        return sb.toString();
    }

    public final class Builder {
        boolean immutable;
        int maxAgeSeconds = -1;
        int maxStaleSeconds = -1;
        int minFreshSeconds = -1;
        boolean noCache;
        boolean noStore;
        boolean noTransform;
        boolean onlyIfCached;

        public final Builder noCache() {
            this.noCache = true;
            return this;
        }

        public final Builder noStore() {
            this.noStore = true;
            return this;
        }

        public final Builder maxAge(int i, TimeUnit timeUnit) {
            if (i < 0) {
                throw new NetonException((Throwable) new IllegalArgumentException("maxAge < 0: " + i));
            }
            long seconds = timeUnit.toSeconds((long) i);
            this.maxAgeSeconds = seconds > 2147483647L ? Integer.MAX_VALUE : (int) seconds;
            return this;
        }

        public final Builder maxStale(int i, TimeUnit timeUnit) {
            if (i < 0) {
                throw new NetonException((Throwable) new IllegalArgumentException("maxStale < 0: " + i));
            }
            long seconds = timeUnit.toSeconds((long) i);
            this.maxStaleSeconds = seconds > 2147483647L ? Integer.MAX_VALUE : (int) seconds;
            return this;
        }

        public final Builder minFresh(int i, TimeUnit timeUnit) {
            if (i < 0) {
                throw new NetonException((Throwable) new IllegalArgumentException("minFresh < 0: " + i));
            }
            long seconds = timeUnit.toSeconds((long) i);
            this.minFreshSeconds = seconds > 2147483647L ? Integer.MAX_VALUE : (int) seconds;
            return this;
        }

        public final Builder onlyIfCached() {
            this.onlyIfCached = true;
            return this;
        }

        public final Builder noTransform() {
            this.noTransform = true;
            return this;
        }

        public final Builder immutable() {
            this.immutable = true;
            return this;
        }

        public final CacheControl build() {
            return new CacheControl(this);
        }
    }
}
