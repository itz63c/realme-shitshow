package com.tencent.bugly.p014a;

import android.util.Log;
import java.util.Locale;
import neton.client.dns.DnsMode;
import neton.internal.platform.Platform;

/* renamed from: com.tencent.bugly.a.am */
/* compiled from: BUGLY */
public final class C0221am {

    /* renamed from: a */
    public static String f461a = "CrashReportInfo";

    /* renamed from: b */
    public static String f462b = "CrashReport";

    /* renamed from: c */
    public static boolean f463c = false;

    /* renamed from: a */
    private static boolean m592a(int i, String str, Object... objArr) {
        if (!f463c) {
            return false;
        }
        if (str == null) {
            str = "null";
        } else if (!(objArr == null || objArr.length == 0)) {
            str = String.format(Locale.US, str, objArr);
        }
        switch (i) {
            case 0:
                Log.i(f462b, str);
                return true;
            case 1:
                Log.d(f462b, str);
                return true;
            case DnsMode.DNS_MODE_HTTP /*2*/:
                Log.w(f462b, str);
                C0222an.m602a("W", f462b, str);
                return true;
            case 3:
                Log.e(f462b, str);
                C0222an.m602a("E", f462b, str);
                return true;
            case Platform.WARN /*5*/:
                Log.i(f461a, str);
                C0222an.m602a("I", f461a, str);
                return true;
            default:
                return false;
        }
    }

    /* renamed from: a */
    public static boolean m593a(String str, Object... objArr) {
        return m592a(0, str, objArr);
    }

    /* renamed from: b */
    public static boolean m595b(String str, Object... objArr) {
        return m592a(5, str, objArr);
    }

    /* renamed from: c */
    public static boolean m597c(String str, Object... objArr) {
        return m592a(1, str, objArr);
    }

    /* renamed from: d */
    public static boolean m598d(String str, Object... objArr) {
        return m592a(2, str, objArr);
    }

    /* renamed from: a */
    public static boolean m594a(Throwable th) {
        if (!f463c) {
            return false;
        }
        return m592a(2, C0208a.m476a(th), new Object[0]);
    }

    /* renamed from: e */
    public static boolean m599e(String str, Object... objArr) {
        return m592a(3, str, objArr);
    }

    /* renamed from: b */
    public static boolean m596b(Throwable th) {
        if (!f463c) {
            return false;
        }
        return m592a(3, C0208a.m476a(th), new Object[0]);
    }
}
