package neton.client.Utils;

import android.os.SystemProperties;
import android.util.Log;

public class LogUtil {

    /* renamed from: D */
    private static boolean f818D = true;

    /* renamed from: E */
    private static boolean f819E = true;

    /* renamed from: I */
    private static boolean f820I = false;
    public static final String TAG = "neton---";

    /* renamed from: V */
    private static boolean f821V = false;

    /* renamed from: W */
    private static boolean f822W = true;
    private static boolean isDebug = SystemProperties.getBoolean("persist.sys.assert.panic", false);
    private static String seprateor = "-->";
    private static String special = "Neton";

    /* renamed from: e */
    public static void m792e(String str, Throwable th) {
        if (f819E) {
            Log.e(str, th.toString());
        }
    }

    /* renamed from: e */
    public static void m789e(Exception exc) {
        if (f819E) {
            exc.printStackTrace();
        }
    }

    /* renamed from: v */
    public static void m796v(String str, String str2) {
        if (f821V && isDebug) {
            Log.v(str, special + seprateor + str2);
        }
    }

    /* renamed from: d */
    public static void m788d(String str, String str2) {
        if (f818D && isDebug) {
            Log.d(str, special + seprateor + str2);
        }
    }

    /* renamed from: i */
    public static void m794i(String str, String str2) {
        if (f820I && isDebug) {
            Log.i(str, special + seprateor + str2);
        }
    }

    /* renamed from: w */
    public static void m798w(String str, String str2) {
        if (f822W && isDebug) {
            Log.w(str, special + seprateor + str2);
        }
    }

    /* renamed from: e */
    public static void m791e(String str, String str2) {
        if (f819E && isDebug) {
            Log.e(str, special + seprateor + str2);
        }
    }

    /* renamed from: v */
    public static void m795v(String str) {
        if (f821V && isDebug) {
            Log.v(TAG, special + seprateor + str);
        }
    }

    /* renamed from: d */
    public static void m787d(String str) {
        if (f818D && isDebug) {
            Log.d(TAG, special + seprateor + str);
        }
    }

    /* renamed from: i */
    public static void m793i(String str) {
        if (f820I && isDebug) {
            Log.i(TAG, special + seprateor + str);
        }
    }

    /* renamed from: w */
    public static void m797w(String str) {
        if (f822W && isDebug) {
            Log.w(TAG, special + seprateor + str);
        }
    }

    /* renamed from: e */
    public static void m790e(String str) {
        if (f819E && isDebug) {
            Log.e(TAG, special + seprateor + str);
        }
    }

    public static String getSpecial() {
        return special;
    }

    public static void setSpecial(String str) {
        special = str;
    }

    public static boolean isV() {
        return f821V;
    }

    public static void setV(boolean z) {
        f821V = z;
    }

    public static boolean isD() {
        return f818D;
    }

    public static void setD(boolean z) {
        f818D = z;
    }

    public static boolean isI() {
        return f820I;
    }

    public static void setI(boolean z) {
        f820I = z;
    }

    public static boolean isW() {
        return f822W;
    }

    public static void setW(boolean z) {
        f822W = z;
    }

    public static boolean isE() {
        return f819E;
    }

    public static void setE(boolean z) {
        f819E = z;
    }

    public static void setDebugs(boolean z) {
        isDebug = z;
        if (z) {
            f821V = true;
            f818D = true;
            f820I = true;
            f822W = true;
            f819E = true;
            return;
        }
        f821V = false;
        f818D = false;
        f820I = false;
        f822W = false;
        f819E = false;
    }

    public static boolean isDebugs() {
        return isDebug;
    }

    public static String getSeprateor() {
        return seprateor;
    }

    public static void setSeprateor(String str) {
        seprateor = str;
    }
}
