package com.tencent.bugly.p014a;

import android.os.Process;
import android.util.Log;
import com.tencent.bugly.C0250b;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;

/* renamed from: com.tencent.bugly.a.an */
/* compiled from: BUGLY */
public final class C0222an {

    /* renamed from: a */
    public static boolean f464a = true;

    /* renamed from: b */
    private static SimpleDateFormat f465b = new SimpleDateFormat("MM-dd HH:mm:ss");
    /* access modifiers changed from: private */

    /* renamed from: c */
    public static int f466c = 5120;

    /* renamed from: d */
    private static StringBuilder f467d;
    /* access modifiers changed from: private */

    /* renamed from: e */
    public static StringBuilder f468e;
    /* access modifiers changed from: private */

    /* renamed from: f */
    public static C0224ap f469f;
    /* access modifiers changed from: private */

    /* renamed from: g */
    public static String f470g;

    /* renamed from: h */
    private static boolean f471h;

    /* renamed from: i */
    private static int f472i;
    /* access modifiers changed from: private */

    /* renamed from: j */
    public static Object f473j = new Object();

    /* renamed from: k */
    private static Object f474k = null;

    /* renamed from: l */
    private static Method f475l;

    static {
        f475l = null;
        try {
            f475l = Class.forName("com.tencent.bugly.crashreport.crash.a.a").getDeclaredMethod("appendLogToNative", new Class[]{String.class, String.class, String.class});
        } catch (Throwable th) {
            Log.w(C0221am.f462b, th.getCause());
        }
    }

    /* renamed from: b */
    private static boolean m604b(String str, String str2, String str3) {
        if (f475l == null) {
            return false;
        }
        if (f474k == null) {
            Object a = C0208a.m473a("com.tencent.bugly.crashreport.crash.a.a", "getInstance");
            f474k = a;
            if (a == null) {
                return false;
            }
        }
        try {
            return ((Boolean) f475l.invoke(f474k, new Object[]{str, str2, str3})).booleanValue();
        } catch (Throwable th) {
            Log.w(C0221am.f462b, th.getMessage());
            return false;
        }
    }

    /* renamed from: a */
    public static void m602a(String str, String str2, String str3) {
        if (C0250b.f618a) {
            m606c(str, str2, str3);
        }
    }

    /* renamed from: c */
    private static synchronized void m606c(String str, String str2, String str3) {
        synchronized (C0222an.class) {
            if (f471h && f464a) {
                m604b(str, str2, str3);
                int myTid = Process.myTid();
                f467d.setLength(0);
                if (str3.length() > 30720) {
                    str3 = str3.substring(str3.length() - 30720, str3.length() - 1);
                }
                f467d.append(f465b.format(new Date())).append(" ").append(f472i).append(" ").append(myTid).append(" ").append(str).append(" ").append(str2).append(": ").append(str3).append("\u0001\r\n");
                String sb = f467d.toString();
                synchronized (f473j) {
                    f468e.append(sb);
                }
                if (f468e.length() > f466c) {
                    C0219ak.m587a().mo381a(new C0223ao(sb));
                }
            }
        }
    }
}
