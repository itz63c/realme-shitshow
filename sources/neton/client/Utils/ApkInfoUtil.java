package neton.client.Utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.SystemProperties;
import com.coloros.neton.BuildConfig;

public class ApkInfoUtil {
    private static final String OPPO_MODEL = "ro.product.model";
    private static final String OPPO_OS_VERSION = "ro.build_bak.version.opporom";
    private static final String OPPO_ROM_VERSION = "ro.build_bak.display.id";
    public static final String OPPO_VERSION_CN = "CN";
    public static final String OPPO_VERSION_EXPORT_PROPERTY = "ro.oppo.version";
    public static final String OPPO_VERSION_PROPERTY = "persist.sys.oppo.region";
    public static final String SSOID = "ssoid";
    private static final String SSOID_DEFAULT = "0";
    public static String sRegionCode = getRegionCode();

    public static String getRegionCode() {
        String str = SystemProperties.get(OPPO_VERSION_EXPORT_PROPERTY, OPPO_VERSION_CN);
        LogUtil.m787d("getRegionCode:" + str);
        return str;
    }

    public static boolean isRegionCN() {
        return OPPO_VERSION_CN.equals(sRegionCode);
    }

    public static int getAndroidOSVersion() {
        return Build.VERSION.SDK_INT;
    }

    public static String getVersionName(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
        } catch (Exception e) {
            e.printStackTrace();
            return SSOID_DEFAULT;
        }
    }

    public static String getRomVersion() {
        return SystemProperties.get(OPPO_ROM_VERSION, BuildConfig.FLAVOR);
    }

    public static int getVersionCode(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionCode;
        } catch (Exception e) {
            LogUtil.m790e("getVersionCode--Exception");
            return 0;
        }
    }

    public static String getPackageName(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).packageName;
        } catch (Exception e) {
            LogUtil.m790e("getPackageName:" + e.toString());
            return SSOID_DEFAULT;
        }
    }

    public static int getAppCode(Context context) {
        try {
            return context.getPackageManager().getApplicationInfo(context.getPackageName(), 128).metaData.getInt("AppCode");
        } catch (Exception e) {
            LogUtil.m790e("getPackageName:" + e.toString());
            return 0;
        }
    }

    public static String getModel() {
        return SystemProperties.get(OPPO_MODEL, BuildConfig.FLAVOR);
    }

    public static String getOsVersion() {
        return SystemProperties.get(OPPO_OS_VERSION, "V1.0.0");
    }

    public static SharedPreferences getSettingPref(Context context) {
        if (context != null) {
            return context.getSharedPreferences("nearme_setting_" + getPackageName(context), 0);
        }
        return null;
    }

    public static String getSsoID(Context context) {
        String str = SSOID_DEFAULT;
        SharedPreferences settingPref = getSettingPref(context);
        if (settingPref != null) {
            str = settingPref.getString(SSOID, SSOID_DEFAULT);
        }
        if (str.equals(SSOID_DEFAULT)) {
            LogUtil.m790e("getSsoID not set.");
        }
        return str;
    }

    public static boolean isExistPackage(Context context, String str) {
        try {
            context.getPackageManager().getPackageInfo(str, 1);
            return true;
        } catch (PackageManager.NameNotFoundException e) {
            LogUtil.m790e("isExistPackage NameNotFoundException");
            return false;
        }
    }
}
