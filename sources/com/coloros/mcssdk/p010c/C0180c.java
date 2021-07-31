package com.coloros.mcssdk.p010c;

import android.text.TextUtils;

/* renamed from: com.coloros.mcssdk.c.c */
public final class C0180c {
    /* renamed from: a */
    public static int m414a(String str) {
        if (TextUtils.isEmpty(str)) {
            return -1;
        }
        try {
            return Integer.parseInt(str);
        } catch (NumberFormatException e) {
            C0179b.m413b("parseInt--NumberFormatException" + e.getMessage());
            return -1;
        }
    }
}
