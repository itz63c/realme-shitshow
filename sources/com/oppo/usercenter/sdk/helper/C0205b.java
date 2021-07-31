package com.oppo.usercenter.sdk.helper;

import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import com.coloros.neton.BuildConfig;

/* renamed from: com.oppo.usercenter.sdk.helper.b */
/* compiled from: Base64Helper */
public final class C0205b {
    /* renamed from: a */
    public static String m467a(String str) {
        try {
            if (TextUtils.isEmpty(str)) {
                return BuildConfig.FLAVOR;
            }
            return new String(Base64.decode(str, 0), "UTF-8");
        } catch (Exception e) {
            Log.e("USERCENTERSDK", String.valueOf(e));
            return BuildConfig.FLAVOR;
        }
    }
}
