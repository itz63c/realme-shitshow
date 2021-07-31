package com.coloros.deeptesting.p007a;

import android.os.SystemProperties;
import android.util.Log;

/* renamed from: com.coloros.deeptesting.a.g */
/* compiled from: OppoLog */
public final class C0150g {

    /* renamed from: a */
    private static final boolean f291a = SystemProperties.getBoolean("persist.sys.assert.panic", false);

    /* renamed from: a */
    public static void m348a(String str, String str2) {
        if (f291a) {
            Log.d("DeepTesting", str + ":" + str2);
        }
    }

    /* renamed from: b */
    public static void m349b(String str, String str2) {
        if (f291a) {
            Log.i("DeepTesting", str + ":" + str2);
        }
    }

    /* renamed from: c */
    public static void m350c(String str, String str2) {
        if (f291a) {
            Log.e("DeepTesting", str + ":" + str2);
        }
    }
}
