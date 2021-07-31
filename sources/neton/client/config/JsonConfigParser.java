package neton.client.config;

import android.text.TextUtils;
import java.util.Arrays;
import neton.client.Utils.LogUtil;
import org.json.JSONException;
import org.json.JSONObject;

public class JsonConfigParser implements Parser {
    public void parse(String str) {
        try {
            JSONObject jSONObject = new JSONObject(str);
            String string = jSONObject.getString(Constants.KEY_VERSION);
            if (!TextUtils.isEmpty(string) && string.compareTo(ConfigManager.getInstance().getStringData(Constants.KEY_VERSION)) > 0) {
                if (jSONObject.has(Constants.KEY_ENCYPT_VERSION)) {
                    ConfigManager.getInstance().setStringData(Constants.KEY_ENCYPT_VERSION, jSONObject.getString(Constants.KEY_ENCYPT_VERSION));
                }
                if (jSONObject.has(Constants.KEY_ENCYPT_SECRET)) {
                    ConfigManager.getInstance().setStringData(Constants.KEY_ENCYPT_SECRET, jSONObject.getString(Constants.KEY_ENCYPT_SECRET));
                }
                if (jSONObject.has(Constants.KEY_TRACE_HIT)) {
                    ConfigManager.getInstance().setIntData(Constants.KEY_TRACE_HIT, Integer.parseInt(jSONObject.getString(Constants.KEY_TRACE_HIT)));
                }
                if (jSONObject.has(Constants.KEY_DNS_MODE)) {
                    ConfigManager.getInstance().setIntData(Constants.KEY_DNS_MODE, Integer.parseInt(jSONObject.getString(Constants.KEY_DNS_MODE)));
                }
                if (jSONObject.has(Constants.KEY_SESSION_TIMEOUT)) {
                    ConfigManager.getInstance().setIntData(Constants.KEY_SESSION_TIMEOUT, Integer.parseInt(jSONObject.getString(Constants.KEY_SESSION_TIMEOUT)));
                }
                if (jSONObject.has(Constants.KEY_SESSION_CACHE_SIZE)) {
                    ConfigManager.getInstance().setIntData(Constants.KEY_SESSION_CACHE_SIZE, Integer.parseInt(jSONObject.getString(Constants.KEY_SESSION_CACHE_SIZE)));
                }
                if (jSONObject.has(Constants.KEY_RETRY_TIMES)) {
                    ConfigManager.getInstance().setIntData(Constants.KEY_RETRY_TIMES, jSONObject.getInt(Constants.KEY_RETRY_TIMES));
                }
                if (jSONObject.has(Constants.KEY_RETRY_INTERVAL_TIME)) {
                    ConfigManager.getInstance().setLongData(Constants.KEY_RETRY_INTERVAL_TIME, Long.valueOf(jSONObject.getLong(Constants.KEY_RETRY_INTERVAL_TIME)));
                }
                if (jSONObject.has(Constants.KEY_LIVE_ON_TIME)) {
                    ConfigManager.getInstance().setLongData(Constants.KEY_LIVE_ON_TIME, Long.valueOf(jSONObject.getLong(Constants.KEY_LIVE_ON_TIME)));
                }
                if (jSONObject.has(Constants.KEY_TRACE_URL)) {
                    ConfigManager.getInstance().setStringData(Constants.KEY_TRACE_URL, jSONObject.getString(Constants.KEY_TRACE_URL));
                }
                if (jSONObject.has(Constants.KEY_FOREIGN_TRACE_URL)) {
                    ConfigManager.getInstance().setStringData(Constants.KEY_FOREIGN_TRACE_URL, jSONObject.getString(Constants.KEY_FOREIGN_TRACE_URL));
                }
                if (jSONObject.has(Constants.KEY_HTTP_LAST_DNS)) {
                    ConfigManager.getInstance().setStringData(Constants.KEY_HTTP_LAST_DNS, jSONObject.getString(Constants.KEY_HTTP_LAST_DNS));
                }
                if (jSONObject.has(Constants.KEY_FOREIGN_HTTP_LAST_DNS)) {
                    ConfigManager.getInstance().setStringData(Constants.KEY_FOREIGN_HTTP_LAST_DNS, jSONObject.getString(Constants.KEY_FOREIGN_HTTP_LAST_DNS));
                }
                if (jSONObject.has(Constants.KEY_HTTP_DNS_LIST)) {
                    String string2 = jSONObject.getString(Constants.KEY_HTTP_DNS_LIST);
                    if (!TextUtils.isEmpty(string2)) {
                        ConfigManager.getInstance().setListData(Constants.KEY_HTTP_DNS_LIST, Arrays.asList(string2.split(Constants.SPLITER)));
                    }
                }
                if (jSONObject.has(Constants.KEY_FOREIGN_HTTP_DNS_LIST)) {
                    String string3 = jSONObject.getString(Constants.KEY_FOREIGN_HTTP_DNS_LIST);
                    if (!TextUtils.isEmpty(string3)) {
                        ConfigManager.getInstance().setListData(Constants.KEY_FOREIGN_HTTP_DNS_LIST, Arrays.asList(string3.split(Constants.SPLITER)));
                    }
                }
                if (jSONObject.has(Constants.KEY_VERSION)) {
                    ConfigManager.getInstance().setStringData(Constants.KEY_VERSION, string);
                }
                ConfigManager.getInstance().commit();
            }
        } catch (JSONException e) {
            LogUtil.m787d("JsonConfigParser--parse-JSONException:" + e.getMessage());
        } catch (Exception e2) {
            LogUtil.m787d("JsonConfigParser--parse-Exception:" + e2.getMessage());
        }
    }
}
