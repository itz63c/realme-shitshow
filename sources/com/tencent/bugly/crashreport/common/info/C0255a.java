package com.tencent.bugly.crashreport.common.info;

import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.os.Process;
import com.tencent.bugly.p014a.C0221am;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* renamed from: com.tencent.bugly.crashreport.common.info.a */
/* compiled from: BUGLY */
public final class C0255a {

    /* renamed from: a */
    public static String f656a = "BUGLY_APPID";

    /* renamed from: b */
    public static String f657b = "BUGLY_APP_CHANNEL";

    /* renamed from: c */
    public static String f658c = "BUGLY_APP_VERSION";

    /* renamed from: d */
    public static String f659d = "BUGLY_ENABLE_DEBUG";

    /* renamed from: e */
    private static String f660e = "BUGLY_DISABLE";

    /* renamed from: f */
    private static ActivityManager f661f;

    static {
        "@buglyAllChannel@".split(",");
        "@buglyAllChannelPriority@".split(",");
    }

    /* renamed from: a */
    public static String m722a(Context context) {
        if (context == null) {
            return null;
        }
        try {
            return context.getPackageName();
        } catch (Throwable th) {
            if (!C0221am.m594a(th)) {
                th.printStackTrace();
            }
            return "fail";
        }
    }

    /* renamed from: b */
    public static PackageInfo m726b(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(m722a(context), 0);
        } catch (Throwable th) {
            if (!C0221am.m594a(th)) {
                th.printStackTrace();
            }
            return null;
        }
    }

    /* renamed from: a */
    public static String m723a(PackageInfo packageInfo) {
        if (packageInfo == null) {
            return null;
        }
        try {
            return packageInfo.versionName;
        } catch (Throwable th) {
            if (!C0221am.m594a(th)) {
                th.printStackTrace();
            }
            return "fail";
        }
    }

    /* renamed from: b */
    public static String m727b(PackageInfo packageInfo) {
        if (packageInfo == null) {
            return null;
        }
        try {
            return Integer.toString(packageInfo.versionCode);
        } catch (Throwable th) {
            if (!C0221am.m594a(th)) {
                th.printStackTrace();
            }
            return "fail";
        }
    }

    /* renamed from: a */
    public static boolean m725a(Context context, String str) {
        if (context == null || str.trim().length() <= 0) {
            return false;
        }
        try {
            String[] strArr = context.getPackageManager().getPackageInfo(context.getPackageName(), 4096).requestedPermissions;
            if (strArr == null) {
                return false;
            }
            for (String equals : strArr) {
                if (str.equals(equals)) {
                    return true;
                }
            }
            return false;
        } catch (Throwable th) {
            if (C0221am.m594a(th)) {
                return false;
            }
            th.printStackTrace();
            return false;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:19:0x0044 A[Catch:{ all -> 0x005f }] */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x004d A[SYNTHETIC, Splitter:B:22:0x004d] */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x0057 A[SYNTHETIC, Splitter:B:28:0x0057] */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static java.lang.String m721a(int r6) {
        /*
            r5 = 512(0x200, float:7.175E-43)
            r0 = 0
            r2 = 0
            java.io.FileReader r1 = new java.io.FileReader     // Catch:{ Throwable -> 0x003c, all -> 0x0053 }
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x003c, all -> 0x0053 }
            java.lang.String r4 = "/proc/"
            r3.<init>(r4)     // Catch:{ Throwable -> 0x003c, all -> 0x0053 }
            java.lang.StringBuilder r3 = r3.append(r6)     // Catch:{ Throwable -> 0x003c, all -> 0x0053 }
            java.lang.String r4 = "/cmdline"
            java.lang.StringBuilder r3 = r3.append(r4)     // Catch:{ Throwable -> 0x003c, all -> 0x0053 }
            java.lang.String r3 = r3.toString()     // Catch:{ Throwable -> 0x003c, all -> 0x0053 }
            r1.<init>(r3)     // Catch:{ Throwable -> 0x003c, all -> 0x0053 }
            r2 = 512(0x200, float:7.175E-43)
            char[] r2 = new char[r2]     // Catch:{ Throwable -> 0x0061 }
            r1.read(r2)     // Catch:{ Throwable -> 0x0061 }
        L_0x0025:
            if (r0 >= r5) goto L_0x002e
            char r3 = r2[r0]     // Catch:{ Throwable -> 0x0061 }
            if (r3 == 0) goto L_0x002e
            int r0 = r0 + 1
            goto L_0x0025
        L_0x002e:
            java.lang.String r3 = new java.lang.String     // Catch:{ Throwable -> 0x0061 }
            r3.<init>(r2)     // Catch:{ Throwable -> 0x0061 }
            r2 = 0
            java.lang.String r0 = r3.substring(r2, r0)     // Catch:{ Throwable -> 0x0061 }
            r1.close()     // Catch:{ Throwable -> 0x005b }
        L_0x003b:
            return r0
        L_0x003c:
            r0 = move-exception
            r1 = r2
        L_0x003e:
            boolean r2 = com.tencent.bugly.p014a.C0221am.m594a(r0)     // Catch:{ all -> 0x005f }
            if (r2 != 0) goto L_0x0047
            r0.printStackTrace()     // Catch:{ all -> 0x005f }
        L_0x0047:
            java.lang.String r0 = java.lang.String.valueOf(r6)     // Catch:{ all -> 0x005f }
            if (r1 == 0) goto L_0x003b
            r1.close()     // Catch:{ Throwable -> 0x0051 }
            goto L_0x003b
        L_0x0051:
            r1 = move-exception
            goto L_0x003b
        L_0x0053:
            r0 = move-exception
            r1 = r2
        L_0x0055:
            if (r1 == 0) goto L_0x005a
            r1.close()     // Catch:{ Throwable -> 0x005d }
        L_0x005a:
            throw r0
        L_0x005b:
            r1 = move-exception
            goto L_0x003b
        L_0x005d:
            r1 = move-exception
            goto L_0x005a
        L_0x005f:
            r0 = move-exception
            goto L_0x0055
        L_0x0061:
            r0 = move-exception
            goto L_0x003e
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.bugly.crashreport.common.info.C0255a.m721a(int):java.lang.String");
    }

    /* renamed from: a */
    public static String m720a() {
        try {
            return m721a(Process.myPid());
        } catch (Throwable th) {
            th.printStackTrace();
            return null;
        }
    }

    /* renamed from: c */
    public static Map<String, String> m728c(Context context) {
        HashMap hashMap;
        if (context == null) {
            return null;
        }
        try {
            ApplicationInfo applicationInfo = context.getPackageManager().getApplicationInfo(context.getPackageName(), 128);
            if (applicationInfo.metaData != null) {
                hashMap = new HashMap();
                Object obj = applicationInfo.metaData.get(f660e);
                if (obj != null) {
                    hashMap.put(f660e, obj.toString());
                }
                Object obj2 = applicationInfo.metaData.get(f656a);
                if (obj2 != null) {
                    hashMap.put(f656a, obj2.toString());
                }
                Object obj3 = applicationInfo.metaData.get(f657b);
                if (obj3 != null) {
                    hashMap.put(f657b, obj3.toString());
                }
                Object obj4 = applicationInfo.metaData.get(f658c);
                if (obj4 != null) {
                    hashMap.put(f658c, obj4.toString());
                }
                Object obj5 = applicationInfo.metaData.get(f659d);
                if (obj5 != null) {
                    hashMap.put(f659d, obj5.toString());
                }
            } else {
                hashMap = null;
            }
            return hashMap;
        } catch (Throwable th) {
            if (C0221am.m594a(th)) {
                return null;
            }
            th.printStackTrace();
            return null;
        }
    }

    /* renamed from: a */
    public static List<String> m724a(Map<String, String> map) {
        if (map == null) {
            return null;
        }
        try {
            String str = map.get(f660e);
            if (str == null || str.length() == 0) {
                return null;
            }
            String[] split = str.split(",");
            for (int i = 0; i < split.length; i++) {
                split[i] = split[i].trim();
            }
            return Arrays.asList(split);
        } catch (Throwable th) {
            if (!C0221am.m594a(th)) {
                th.printStackTrace();
            }
            return null;
        }
    }

    /* renamed from: d */
    public static boolean m729d(Context context) {
        if (context == null) {
            return false;
        }
        if (f661f == null) {
            f661f = (ActivityManager) context.getSystemService("activity");
        }
        try {
            ActivityManager.MemoryInfo memoryInfo = new ActivityManager.MemoryInfo();
            f661f.getMemoryInfo(memoryInfo);
            if (!memoryInfo.lowMemory) {
                return false;
            }
            C0221am.m597c("Memory is low.", new Object[0]);
            return true;
        } catch (Throwable th) {
            if (!C0221am.m594a(th)) {
                th.printStackTrace();
            }
            return false;
        }
    }
}
