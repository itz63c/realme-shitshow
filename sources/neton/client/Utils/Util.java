package neton.client.Utils;

import android.content.Context;
import android.text.TextUtils;
import com.coloros.neton.BuildConfig;
import java.nio.charset.Charset;
import neton.client.Utils.AppSecurityUtils;
import neton.client.config.ConfigManager;
import neton.client.config.Constants;

public class Util {
    public static <T> String toString(T t) {
        if (t != null) {
            return t.toString();
        }
        return null;
    }

    public static int parseInt(String str) {
        if (TextUtils.isEmpty(str)) {
            return 0;
        }
        try {
            return Integer.parseInt(str);
        } catch (NumberFormatException e) {
            LogUtil.m790e("parseInt--NumberFormatException");
            return 0;
        }
    }

    public static long parseLong(String str) {
        if (TextUtils.isEmpty(str)) {
            return 0;
        }
        try {
            return Long.parseLong(str);
        } catch (NumberFormatException e) {
            LogUtil.m790e("parseLong--NumberFormatException");
            return 0;
        }
    }

    public static String getTraceID(Context context) {
        String str = null;
        int intData = ConfigManager.getInstance().getIntData(Constants.KEY_TRACE_HIT, Constants.DEFAULT_TRACE_HIT);
        int randomInt = MathUtils.randomInt(intData);
        if (randomInt <= 0) {
            str = ClientIdUtils.getClientId(context) + System.currentTimeMillis();
        }
        LogUtil.m787d("getTraceID--randomInt:" + randomInt + ",traceHit:" + intData + ",traceID:" + str);
        return str;
    }

    public static String getNetonHeader(Context context) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(ClientIdUtils.getClientId(context) + Constants.SPLITER);
        stringBuffer.append(ApkInfoUtil.getSsoID(context) + Constants.SPLITER);
        stringBuffer.append(ApkInfoUtil.getModel() + Constants.SPLITER);
        stringBuffer.append(ApkInfoUtil.getOsVersion() + Constants.SPLITER);
        stringBuffer.append(ApkInfoUtil.getRomVersion() + Constants.SPLITER);
        stringBuffer.append(ApkInfoUtil.getAndroidOSVersion() + Constants.SPLITER);
        stringBuffer.append(ConfigManager.getInstance().getStringData(Constants.KEY_VERSION, Constants.DEFAULT_VERSION) + Constants.SPLITER);
        stringBuffer.append(NetHelper.getNetworkType(context) + Constants.SPLITER);
        stringBuffer.append(ApkInfoUtil.getVersionName(context));
        String str = BuildConfig.FLAVOR;
        LogUtil.m787d("getNetonHeader--origin:" + stringBuffer.toString());
        if (stringBuffer.toString().equals(ConfigManager.getInstance().getStringData(Constants.KEY_HEADER))) {
            return ConfigManager.getInstance().getStringData(Constants.KEY_ENCRYPT_HEADER);
        }
        try {
            ConfigManager.getInstance().setStringData(Constants.KEY_HEADER, stringBuffer.toString());
            str = AppSecurityUtils.binToHex(AppSecurityUtils.AES.ECB.encrypt(stringBuffer.toString().getBytes(Charset.defaultCharset()), Constants.DEFAULT_ENCYPT_SECRET));
            ConfigManager.getInstance().setStringData(Constants.KEY_ENCRYPT_HEADER, str);
            LogUtil.m787d("getNetonHeader--header:" + str);
            return str;
        } catch (Exception e) {
            LogUtil.m790e("getNetonHeader--" + e.getMessage());
            return str;
        }
    }
}
