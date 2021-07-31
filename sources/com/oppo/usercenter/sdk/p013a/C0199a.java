package com.oppo.usercenter.sdk.p013a;

import android.util.Log;
import com.oppo.usercenter.sdk.C0201b;

/* renamed from: com.oppo.usercenter.sdk.a.a */
/* compiled from: UCLogUtil */
public final class C0199a {

    /* renamed from: a */
    private static final boolean f396a = Log.isLoggable("UserCenterSDK", 2);

    /* renamed from: b */
    private static boolean f397b;

    static {
        String a = C0200b.m460a("persist.sys.assert.panic", "false");
        String a2 = C0200b.m460a("persist.sys.assert.enable", "false");
        if ("true".equalsIgnoreCase(a) || "true".equalsIgnoreCase(a2)) {
            f397b = true;
        } else {
            f397b = false;
        }
    }

    /* renamed from: a */
    public static void m459a(String str) {
        boolean z;
        if (C0201b.f398a || f396a || f397b) {
            z = true;
        } else {
            z = false;
        }
        if (z) {
            Log.i("UserCenterSDK", str);
        }
    }
}
