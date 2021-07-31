package com.oppo.usercenter.sdk.helper;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.text.TextUtils;
import com.nearme.aidl.UserEntity;

/* renamed from: com.oppo.usercenter.sdk.helper.a */
/* compiled from: AccountPrefUtils */
public final class C0204a {
    /* renamed from: c */
    private static SharedPreferences m465c(Context context) {
        int i = 4;
        if (Build.VERSION.SDK_INT <= 8) {
            i = 0;
        }
        return context.getSharedPreferences(context.getPackageName() + "_suffix_usercenter_sharepreference", i);
    }

    /* renamed from: a */
    public static void m462a(Context context, UserEntity userEntity) {
        if (userEntity != null) {
            m465c(context).edit().putString("usercenter_account_key", UserEntity.m450a(userEntity)).commit();
        }
    }

    /* renamed from: d */
    private static UserEntity m466d(Context context) {
        String string = m465c(context).getString("usercenter_account_key", (String) null);
        if (!TextUtils.isEmpty(string)) {
            return UserEntity.m451d(string);
        }
        return null;
    }

    /* renamed from: a */
    public static void m461a(Context context) {
        m465c(context).edit().clear().commit();
    }

    /* renamed from: b */
    public static String m464b(Context context) {
        UserEntity d = m466d(context);
        if (d != null) {
            return d.mo326a();
        }
        return null;
    }

    /* renamed from: a */
    public static void m463a(Context context, String str) {
        UserEntity d = m466d(context);
        if (d != null) {
            d.mo328a(str);
            m462a(context, d);
        }
    }
}
