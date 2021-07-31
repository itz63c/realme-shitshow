package com.coloros.neton;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import neton.HttpUrl;
import neton.OkHttpClient;
import neton.Timeout;

public class NetonConfig {
    public static final int DEFAULT_INT = -1;
    private String cacheDirectory;
    /* access modifiers changed from: private */
    public int dnsMode;
    /* access modifiers changed from: private */
    public boolean isCookie;
    /* access modifiers changed from: private */
    public boolean isDebug;
    /* access modifiers changed from: private */
    public boolean isPersistSession;
    /* access modifiers changed from: private */
    public boolean isRequestCache;
    /* access modifiers changed from: private */
    public boolean isStatistics;
    /* access modifiers changed from: private */
    public boolean isTrace;
    /* access modifiers changed from: private */
    public boolean isVerify;
    /* access modifiers changed from: private */
    public Map<String, String> liveOnHttpsMap;
    /* access modifiers changed from: private */
    public long liveOnTime;
    /* access modifiers changed from: private */
    public int mode;
    /* access modifiers changed from: private */
    public OkHttpClient.Builder okHttpBuilder;
    /* access modifiers changed from: private */
    public String regionCode;
    /* access modifiers changed from: private */
    public long retryIntervalTime;
    /* access modifiers changed from: private */
    public int retryTimes;
    /* access modifiers changed from: private */
    public int sessionCacheSize;
    /* access modifiers changed from: private */
    public int sessionTimeout;
    /* access modifiers changed from: private */
    public Timeout timeout;
    /* access modifiers changed from: private */
    public int traceHit;

    private NetonConfig(Builder builder) {
        this.isVerify = true;
        this.isRequestCache = true;
        this.isPersistSession = true;
        this.isStatistics = false;
        this.isDebug = false;
        this.isTrace = false;
        this.isCookie = true;
        this.timeout = null;
        this.okHttpBuilder = null;
        this.cacheDirectory = null;
        this.dnsMode = -1;
        this.retryTimes = -1;
        this.retryIntervalTime = -1;
        this.traceHit = -1;
        this.sessionTimeout = -1;
        this.sessionCacheSize = -1;
        this.liveOnHttpsMap = null;
        this.liveOnTime = -1;
        this.mode = -1;
        this.regionCode = null;
        this.isVerify = builder.isVerify;
        this.isRequestCache = builder.isRequestCache;
        this.cacheDirectory = builder.cacheDirectory;
        this.isPersistSession = builder.isPersistSession;
        this.isStatistics = builder.isStatistics;
        this.dnsMode = builder.dnsMode;
        this.retryTimes = builder.retryTimes;
        this.retryIntervalTime = builder.retryIntervalTime;
        this.traceHit = builder.traceHit;
        this.isDebug = builder.isDebug;
        this.isTrace = builder.isTrace;
        this.sessionTimeout = builder.sessionTimeout;
        this.sessionCacheSize = builder.sessionCacheSize;
        this.okHttpBuilder = builder.okHttpBuilder;
        this.isCookie = builder.isCookie;
        this.timeout = builder.timeout;
        this.liveOnHttpsMap = builder.liveOnHttpsMap;
        this.liveOnTime = builder.liveOnTime;
        this.mode = builder.mode;
        this.regionCode = builder.regionCode;
    }

    public OkHttpClient.Builder okHttpBuilder() {
        return this.okHttpBuilder;
    }

    public boolean isVerify() {
        return this.isVerify;
    }

    public boolean isRequestCache() {
        return this.isRequestCache;
    }

    public String getCacheDirectory() {
        return this.cacheDirectory;
    }

    public boolean isPersistSession() {
        return this.isPersistSession;
    }

    public boolean isStatistics() {
        return this.isStatistics;
    }

    public boolean isDebug() {
        return this.isDebug;
    }

    public boolean isTrace() {
        return this.isTrace;
    }

    public boolean isCookie() {
        return this.isCookie;
    }

    public Timeout timeout() {
        return this.timeout;
    }

    public int getSessionTimeout() {
        return this.sessionTimeout;
    }

    public int getRetryTimes() {
        return this.retryTimes;
    }

    public long getRetryIntervalTime() {
        return this.retryIntervalTime;
    }

    public int getTraceHit() {
        return this.traceHit;
    }

    public int getDnsMode() {
        return this.dnsMode;
    }

    public int getSessionCacheSize() {
        return this.sessionCacheSize;
    }

    public Map<String, String> getLiveOnHttpsMap() {
        return this.liveOnHttpsMap;
    }

    public long getLiveOnTime() {
        return this.liveOnTime;
    }

    public int getMode() {
        return this.mode;
    }

    public String getRegionCode() {
        return this.regionCode;
    }

    public Builder newBuilder() {
        return new Builder(this);
    }

    public final class Builder {
        /* access modifiers changed from: private */
        public String cacheDirectory = null;
        /* access modifiers changed from: private */
        public int dnsMode = -1;
        /* access modifiers changed from: private */
        public boolean isCookie = true;
        /* access modifiers changed from: private */
        public boolean isDebug = false;
        /* access modifiers changed from: private */
        public boolean isPersistSession = true;
        /* access modifiers changed from: private */
        public boolean isRequestCache = false;
        /* access modifiers changed from: private */
        public boolean isStatistics = false;
        /* access modifiers changed from: private */
        public boolean isTrace = true;
        /* access modifiers changed from: private */
        public boolean isVerify = true;
        /* access modifiers changed from: private */
        public Map<String, String> liveOnHttpsMap = null;
        /* access modifiers changed from: private */
        public long liveOnTime = -1;
        /* access modifiers changed from: private */
        public int mode = -1;
        /* access modifiers changed from: private */
        public OkHttpClient.Builder okHttpBuilder = null;
        /* access modifiers changed from: private */
        public String regionCode = null;
        /* access modifiers changed from: private */
        public long retryIntervalTime = -1;
        /* access modifiers changed from: private */
        public int retryTimes = -1;
        /* access modifiers changed from: private */
        public int sessionCacheSize = -1;
        /* access modifiers changed from: private */
        public int sessionTimeout = -1;
        /* access modifiers changed from: private */
        public Timeout timeout = null;
        /* access modifiers changed from: private */
        public int traceHit = -1;

        public Builder(NetonConfig netonConfig) {
            this.isRequestCache = netonConfig.isRequestCache;
            this.isVerify = netonConfig.isVerify;
            this.isPersistSession = netonConfig.isPersistSession;
            this.isStatistics = netonConfig.isStatistics;
            this.dnsMode = netonConfig.dnsMode;
            this.retryIntervalTime = netonConfig.retryIntervalTime;
            this.retryTimes = netonConfig.retryTimes;
            this.traceHit = netonConfig.traceHit;
            this.isDebug = netonConfig.isDebug;
            this.isTrace = netonConfig.isTrace;
            this.isCookie = netonConfig.isCookie;
            this.sessionTimeout = netonConfig.sessionTimeout;
            this.sessionCacheSize = netonConfig.sessionCacheSize;
            this.okHttpBuilder = netonConfig.okHttpBuilder;
            this.timeout = netonConfig.timeout;
            this.liveOnHttpsMap = netonConfig.liveOnHttpsMap;
            this.liveOnTime = netonConfig.liveOnTime;
            this.mode = netonConfig.mode;
            this.regionCode = netonConfig.regionCode;
        }

        public final Builder okHttpBuilder(OkHttpClient.Builder builder) {
            this.okHttpBuilder = builder;
            return this;
        }

        public final Builder timeout(Timeout timeout2) {
            this.timeout = timeout2;
            return this;
        }

        @Deprecated
        public final Builder verify(boolean z) {
            this.isVerify = z;
            return this;
        }

        public final Builder requestCache(boolean z) {
            this.isRequestCache = z;
            return this;
        }

        public final Builder cacheDirectory(String str) {
            this.cacheDirectory = str;
            return this;
        }

        public final Builder cookie(boolean z) {
            this.isCookie = z;
            return this;
        }

        public final Builder persistSession(boolean z) {
            this.isPersistSession = z;
            return this;
        }

        public final Builder statistics(boolean z) {
            this.isStatistics = z;
            return this;
        }

        public final Builder debug(boolean z) {
            this.isDebug = z;
            return this;
        }

        public final Builder dnsMode(int i) {
            this.dnsMode = i;
            return this;
        }

        public final Builder sessionTimeout(int i) {
            this.sessionTimeout = i;
            return this;
        }

        public final Builder sessionCacheSize(int i) {
            this.sessionCacheSize = i;
            return this;
        }

        public final Builder retryTimes(int i) {
            this.retryTimes = i;
            return this;
        }

        public final Builder trace(boolean z) {
            this.isTrace = z;
            return this;
        }

        public final Builder retryIntervalTime(long j) {
            this.retryIntervalTime = j;
            return this;
        }

        @Deprecated
        public final Builder traceHit(int i) {
            this.traceHit = i;
            return this;
        }

        public final Builder liveOnHttpsMap(Map<String, String> map) {
            this.liveOnHttpsMap = map;
            return this;
        }

        public final Builder liveOnHttpsMap(List<String> list) {
            if (list != null && !list.isEmpty()) {
                this.liveOnHttpsMap = new HashMap();
            }
            for (String next : list) {
                HttpUrl parse = HttpUrl.parse(next);
                if (parse == null) {
                    this.liveOnHttpsMap.put(next, next);
                } else {
                    this.liveOnHttpsMap.put(next, parse.host());
                }
            }
            return this;
        }

        public final Builder liveOnHttpsMap(String... strArr) {
            if (strArr != null && strArr.length > 0) {
                liveOnHttpsMap((List<String>) Arrays.asList(strArr));
            }
            return this;
        }

        public final Builder liveOnTime(long j) {
            this.liveOnTime = j;
            return this;
        }

        public final Builder mode(int i) {
            this.mode = i;
            return this;
        }

        public final Builder regionCode(String str) {
            this.regionCode = str;
            return this;
        }

        public final NetonConfig build() {
            return new NetonConfig(this);
        }
    }
}
