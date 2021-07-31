package neton.client.liveon;

import neton.HttpUrl;
import neton.client.config.ConfigManager;
import neton.client.config.Constants;

public class LiveOnHttps {
    private HttpUrl httpUrl;
    private HttpUrl httpsUrl;
    private boolean isHttpsLiveOn = true;
    private long lastTime;

    public boolean isHttpsLiveOn() {
        if (System.currentTimeMillis() - getLastTime() > ConfigManager.getInstance().getLongData(Constants.KEY_LIVE_ON_TIME, Long.valueOf(Constants.DEFAULT_LIVE_ON_TIME)).longValue()) {
            this.isHttpsLiveOn = true;
            setLastTime(System.currentTimeMillis());
        }
        return this.isHttpsLiveOn;
    }

    public void setHttpsLiveOn(boolean z) {
        this.isHttpsLiveOn = z;
        setLastTime(System.currentTimeMillis());
    }

    public long getLastTime() {
        return this.lastTime;
    }

    public void setLastTime(long j) {
        this.lastTime = j;
    }

    public HttpUrl getHttpsUrl() {
        return this.httpsUrl;
    }

    public void setHttpsUrl(HttpUrl httpUrl2) {
        this.httpsUrl = httpUrl2;
    }

    public HttpUrl getHttpUrl() {
        return this.httpUrl;
    }

    public void setHttpUrl(HttpUrl httpUrl2) {
        this.httpUrl = httpUrl2;
    }

    public static String getKey(HttpUrl httpUrl2) {
        if (httpUrl2 != null) {
            return httpUrl2.host() + ":" + httpUrl2.port();
        }
        return null;
    }

    public String toString() {
        return "httpsUrl:" + this.httpsUrl + ",httpUrl:" + this.httpUrl + ",isHttpsLiveOn:" + this.isHttpsLiveOn + ",lastTime:" + this.lastTime;
    }
}
