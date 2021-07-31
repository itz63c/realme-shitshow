package neton.client.statistics;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.json.JSONObject;

public class Statistics {
    private static final String APP_ID = "appID";
    private static final String APP_NAME = "appName";
    private static final String APP_PACKAGE = "appPackage";
    private static final String APP_VERSION = "appVersion";
    private static final int COMMON = 1006;
    private static final String DATA_TYPE = "dataType";
    private static final String EVENT_ID = "eventID";
    private static final String LOG_MAP = "logMap";
    private static final String LOG_TAG = "logTag";
    private static final String SSOID = "ssoid";
    private static final String UPLOAD_NOW = "uploadNow";
    private static final String VERSION = "1.0.0";
    /* access modifiers changed from: private */
    public static int appID = 20120;
    private static ExecutorService sSingleThreadExecutor = Executors.newSingleThreadExecutor();

    public static void onCommon(Context context, String str, String str2, Map<String, String> map, boolean z) {
        try {
            Log.d("Statistics", "onCommonWithoutJar logTag is " + str + ",logmap:" + map);
            if (str != null && !TextUtils.isEmpty(str)) {
                final Map<String, String> map2 = map;
                final Context context2 = context;
                final String str3 = str2;
                final boolean z2 = z;
                final String str4 = str;
                sSingleThreadExecutor.execute(new Runnable() {
                    public final void run() {
                        JSONObject jSONObject = new JSONObject();
                        if (map2 != null && !map2.isEmpty()) {
                            try {
                                for (String str : map2.keySet()) {
                                    jSONObject.put(str, map2.get(str));
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                        Intent intent = new Intent();
                        intent.setComponent(new ComponentName("com.nearme.statistics.rom", "com.nearme.statistics.rom.service.ReceiverService"));
                        intent.putExtra(Statistics.APP_PACKAGE, context2.getPackageName());
                        intent.putExtra(Statistics.APP_NAME, context2.getApplicationInfo().name);
                        intent.putExtra(Statistics.APP_VERSION, "1.0.0");
                        intent.putExtra("ssoid", "system");
                        intent.putExtra(Statistics.APP_ID, Statistics.appID);
                        intent.putExtra(Statistics.EVENT_ID, str3);
                        intent.putExtra(Statistics.UPLOAD_NOW, z2);
                        intent.putExtra(Statistics.LOG_TAG, str4);
                        intent.putExtra(Statistics.LOG_MAP, jSONObject.toString());
                        intent.putExtra(Statistics.DATA_TYPE, Statistics.COMMON);
                        context2.startService(intent);
                    }
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
