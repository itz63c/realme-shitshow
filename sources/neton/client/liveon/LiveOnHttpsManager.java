package neton.client.liveon;

import android.content.Context;
import android.text.TextUtils;
import com.coloros.neton.NetonClient;
import com.coloros.neton.NetonException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import neton.HttpUrl;
import neton.Request;
import neton.client.Utils.LogUtil;
import neton.client.Utils.NetHelper;
import neton.client.Utils.ThreadPoolUtil;
import neton.client.config.ConfigManager;
import neton.client.config.Constants;

public class LiveOnHttpsManager {
    private static final LiveOnHttpsManager ourInstance = new LiveOnHttpsManager();
    /* access modifiers changed from: private */
    public boolean isStartLiveOn;
    /* access modifiers changed from: private */
    public Map<String, LiveOnHttps> liveOnHttpsMap = new HashMap();

    public static LiveOnHttpsManager getInstance() {
        return ourInstance;
    }

    private LiveOnHttpsManager() {
    }

    public void parseLiveOnHttpsMap(Map<String, String> map) {
        HttpUrl httpUrl;
        if (map != null && !map.isEmpty()) {
            for (Map.Entry next : map.entrySet()) {
                LiveOnHttps liveOnHttps = new LiveOnHttps();
                try {
                    HttpUrl parse = HttpUrl.parse((String) next.getKey());
                    if (parse == null) {
                        parse = new HttpUrl.Builder().scheme("https").host((String) next.getKey()).port(443).build();
                    }
                    if (!parse.isHttps()) {
                        parse = parse.newBuilder().scheme("https").build();
                    }
                    liveOnHttps.setHttpsUrl(parse);
                    HttpUrl parse2 = HttpUrl.parse((String) next.getValue());
                    if (parse2 == null) {
                        httpUrl = new HttpUrl.Builder().scheme("http").host((String) next.getValue()).port(80).build();
                    } else {
                        httpUrl = parse2;
                    }
                    liveOnHttps.setHttpUrl(httpUrl);
                } catch (Exception e) {
                    LogUtil.m790e("parseLiveOnHttpsMap--e:" + e.toString());
                }
                if (!(liveOnHttps.getHttpsUrl() == null || liveOnHttps.getHttpUrl() == null)) {
                    String key = LiveOnHttps.getKey(liveOnHttps.getHttpsUrl());
                    if (!TextUtils.isEmpty(key)) {
                        this.liveOnHttpsMap.put(key, liveOnHttps);
                    }
                }
            }
            LogUtil.m787d("parseLiveOnHttpsMap--liveOnHttpsMap:" + this.liveOnHttpsMap);
        }
    }

    public LiveOnHttps getLiveOnHttps(HttpUrl httpUrl) {
        String key = LiveOnHttps.getKey(httpUrl);
        if (this.liveOnHttpsMap == null || this.liveOnHttpsMap.isEmpty() || TextUtils.isEmpty(key) || !this.liveOnHttpsMap.containsKey(key)) {
            return null;
        }
        return this.liveOnHttpsMap.get(key);
    }

    public void updateLiveOn(Request request, Request request2, boolean z) {
        LiveOnHttps liveOnHttps;
        if (request.url().isHttps() && (liveOnHttps = getInstance().getLiveOnHttps(request.url())) != null) {
            if (request2.url().isHttps()) {
                liveOnHttps.setHttpsLiveOn(z);
            } else if (!request2.url().isHttps() && !z) {
                liveOnHttps.setHttpsLiveOn(true);
            }
        }
    }

    public synchronized void startLiveOn(Context context) {
        LogUtil.m787d("startLiveOn--start");
        if (!checkNeedLiveOn(context)) {
            LogUtil.m787d("startLiveOn--check not need live on");
        } else if (this.isStartLiveOn) {
            LogUtil.m787d("startLiveOn--isStartLiveOn:" + this.isStartLiveOn);
        } else {
            this.isStartLiveOn = true;
            ConfigManager.getInstance().setLongData(Constants.KEY_LAST_LIVE_ON_TIME, Long.valueOf(System.currentTimeMillis()));
            ConfigManager.getInstance().commit();
            ThreadPoolUtil.execute(new Runnable() {
                public void run() {
                    for (Map.Entry value : LiveOnHttpsManager.this.liveOnHttpsMap.entrySet()) {
                        LiveOnHttps liveOnHttps = (LiveOnHttps) value.getValue();
                        LogUtil.m787d("startLiveOn--real--liveOnHttps:" + liveOnHttps);
                        if (!liveOnHttps.isHttpsLiveOn()) {
                            try {
                                if (NetonClient.getProcessor().process(new Request.Builder().url(liveOnHttps.getHttpUrl()).build(), true).isSuccessful()) {
                                    liveOnHttps.setHttpsLiveOn(true);
                                } else {
                                    liveOnHttps.setHttpsLiveOn(false);
                                }
                            } catch (NetonException e) {
                                LogUtil.m787d("startLiveOn--NetonException:" + e.toString());
                            } catch (IOException e2) {
                                LogUtil.m787d("startLiveOn--IOException:" + e2.toString());
                            } catch (Exception e3) {
                                LogUtil.m787d("startLiveOn--Exception:" + e3.toString());
                            }
                        }
                    }
                    boolean unused = LiveOnHttpsManager.this.isStartLiveOn = false;
                }
            });
        }
    }

    private boolean checkNeedLiveOn(Context context) {
        boolean z;
        if (this.liveOnHttpsMap == null || this.liveOnHttpsMap.isEmpty() || !NetHelper.isConnectNet(context)) {
            return false;
        }
        long longValue = ConfigManager.getInstance().getLongData(Constants.KEY_LAST_LIVE_ON_TIME).longValue();
        long longValue2 = ConfigManager.getInstance().getLongData(Constants.KEY_LIVE_ON_TIME, Long.valueOf(Constants.DEFAULT_LIVE_ON_TIME)).longValue();
        if (System.currentTimeMillis() - longValue > longValue2) {
            z = true;
        } else {
            z = false;
        }
        LogUtil.m787d("startLiveOn--" + this.liveOnHttpsMap + ",lastTime:" + longValue + ",liveOnTime:" + longValue2 + ",needLiveOn:" + z);
        if (z) {
            return true;
        }
        return false;
    }
}
