package com.oppo.usercenter.sdk.p013a;

/* renamed from: com.oppo.usercenter.sdk.a.b */
/* compiled from: UCSystemInfoHelper */
public final class C0200b {
    /* renamed from: a */
    public static String m460a(String str, String str2) {
        try {
            Class<?> cls = Class.forName("android.os.SystemProperties");
            return (String) cls.getMethod("get", new Class[]{String.class, String.class}).invoke(cls, new Object[]{new String(str), new String(str2)});
        } catch (IllegalArgumentException e) {
            throw e;
        } catch (Exception e2) {
            return str2;
        }
    }
}
