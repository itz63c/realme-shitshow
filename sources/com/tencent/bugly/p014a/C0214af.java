package com.tencent.bugly.p014a;

import android.content.Context;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import neton.internal.http.HttpMethod;

/* renamed from: com.tencent.bugly.a.af */
/* compiled from: BUGLY */
public final class C0214af {

    /* renamed from: b */
    private static C0214af f417b;

    /* renamed from: a */
    public Map<String, String> f418a = null;

    /* renamed from: c */
    private Context f419c;

    private C0214af(Context context) {
        this.f419c = context;
    }

    /* renamed from: a */
    public static C0214af m545a(Context context) {
        if (f417b == null) {
            f417b = new C0214af(context);
        }
        return f417b;
    }

    /* JADX WARNING: Removed duplicated region for block: B:61:0x0132  */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final byte[] mo364a(java.lang.String r15, byte[] r16, com.tencent.bugly.p014a.C0218aj r17, java.util.Map<java.lang.String, java.lang.String> r18) {
        /*
            r14 = this;
            if (r15 != 0) goto L_0x000c
            java.lang.String r2 = "rqdp{  no destUrl!}"
            r3 = 0
            java.lang.Object[] r3 = new java.lang.Object[r3]
            com.tencent.bugly.p014a.C0221am.m599e(r2, r3)
            r2 = 0
        L_0x000b:
            return r2
        L_0x000c:
            r8 = 0
            r7 = 0
            if (r16 != 0) goto L_0x005a
            r2 = 0
        L_0x0012:
            java.lang.String r4 = "req %s sz:%d (pid=%d | tid=%d)"
            r5 = 4
            java.lang.Object[] r5 = new java.lang.Object[r5]
            r6 = 0
            r5[r6] = r15
            r6 = 1
            java.lang.Long r9 = java.lang.Long.valueOf(r2)
            r5[r6] = r9
            r6 = 2
            int r9 = android.os.Process.myPid()
            java.lang.Integer r9 = java.lang.Integer.valueOf(r9)
            r5[r6] = r9
            r6 = 3
            int r9 = android.os.Process.myTid()
            java.lang.Integer r9 = java.lang.Integer.valueOf(r9)
            r5[r6] = r9
            com.tencent.bugly.p014a.C0221am.m597c(r4, r5)
            r4 = 0
            r5 = r8
            r9 = r15
        L_0x003d:
            int r8 = r5 + 1
            r6 = 3
            if (r5 >= r6) goto L_0x014b
            r5 = 2
            if (r7 >= r5) goto L_0x014b
            if (r4 == 0) goto L_0x005f
            r4 = 0
        L_0x0048:
            android.content.Context r5 = r14.f419c
            java.lang.String r5 = com.tencent.bugly.p014a.C0208a.m502e(r5)
            if (r5 != 0) goto L_0x007d
            java.lang.String r5 = "req but network not avail,so drop!"
            r6 = 0
            java.lang.Object[] r6 = new java.lang.Object[r6]
            com.tencent.bugly.p014a.C0221am.m598d(r5, r6)
            r5 = r8
            goto L_0x003d
        L_0x005a:
            r0 = r16
            int r2 = r0.length
            long r2 = (long) r2
            goto L_0x0012
        L_0x005f:
            r5 = 1
            if (r8 <= r5) goto L_0x0048
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            java.lang.String r6 = "try time "
            r5.<init>(r6)
            java.lang.StringBuilder r5 = r5.append(r8)
            java.lang.String r5 = r5.toString()
            r6 = 0
            java.lang.Object[] r6 = new java.lang.Object[r6]
            com.tencent.bugly.p014a.C0221am.m597c(r5, r6)
            r10 = 10000(0x2710, double:4.9407E-320)
            android.os.SystemClock.sleep(r10)
            goto L_0x0048
        L_0x007d:
            r0 = r17
            r0.mo378a(r2)
            r0 = r16
            r1 = r18
            java.net.HttpURLConnection r11 = r14.m547a((java.lang.String) r9, (byte[]) r0, (java.lang.String) r5, (java.util.Map<java.lang.String, java.lang.String>) r1)
            if (r11 == 0) goto L_0x0139
            int r12 = r11.getResponseCode()     // Catch:{ IOException -> 0x012a }
            r5 = 200(0xc8, float:2.8E-43)
            if (r12 != r5) goto L_0x00ad
            java.util.Map r5 = m548a((java.net.HttpURLConnection) r11)     // Catch:{ IOException -> 0x012a }
            r14.f418a = r5     // Catch:{ IOException -> 0x012a }
            byte[] r5 = m549b(r11)     // Catch:{ IOException -> 0x012a }
            if (r5 != 0) goto L_0x00aa
            r10 = 0
        L_0x00a2:
            r0 = r17
            r0.mo379b(r10)     // Catch:{ IOException -> 0x012a }
            r2 = r5
            goto L_0x000b
        L_0x00aa:
            int r6 = r5.length     // Catch:{ IOException -> 0x012a }
            long r10 = (long) r6
            goto L_0x00a2
        L_0x00ad:
            r5 = 301(0x12d, float:4.22E-43)
            if (r12 == r5) goto L_0x00bd
            r5 = 302(0x12e, float:4.23E-43)
            if (r12 == r5) goto L_0x00bd
            r5 = 303(0x12f, float:4.25E-43)
            if (r12 == r5) goto L_0x00bd
            r5 = 307(0x133, float:4.3E-43)
            if (r12 != r5) goto L_0x00e7
        L_0x00bd:
            r5 = 1
        L_0x00be:
            if (r5 == 0) goto L_0x0155
            r6 = 1
            java.lang.String r4 = "Location"
            java.lang.String r10 = r11.getHeaderField(r4)     // Catch:{ IOException -> 0x014e }
            if (r10 != 0) goto L_0x00e9
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x014e }
            java.lang.String r5 = "rqdp{  redirect code:}"
            r4.<init>(r5)     // Catch:{ IOException -> 0x014e }
            java.lang.StringBuilder r4 = r4.append(r12)     // Catch:{ IOException -> 0x014e }
            java.lang.String r5 = "rqdp{   Location is null! return}"
            java.lang.StringBuilder r4 = r4.append(r5)     // Catch:{ IOException -> 0x014e }
            java.lang.String r4 = r4.toString()     // Catch:{ IOException -> 0x014e }
            r5 = 0
            java.lang.Object[] r5 = new java.lang.Object[r5]     // Catch:{ IOException -> 0x014e }
            com.tencent.bugly.p014a.C0221am.m599e(r4, r5)     // Catch:{ IOException -> 0x014e }
            r2 = 0
            goto L_0x000b
        L_0x00e7:
            r5 = 0
            goto L_0x00be
        L_0x00e9:
            int r7 = r7 + 1
            r8 = 0
            java.lang.String r4 = "redirect code: %d ,to:%s"
            r5 = 2
            java.lang.Object[] r5 = new java.lang.Object[r5]     // Catch:{ IOException -> 0x0151 }
            r9 = 0
            java.lang.Integer r13 = java.lang.Integer.valueOf(r12)     // Catch:{ IOException -> 0x0151 }
            r5[r9] = r13     // Catch:{ IOException -> 0x0151 }
            r9 = 1
            r5[r9] = r10     // Catch:{ IOException -> 0x0151 }
            com.tencent.bugly.p014a.C0221am.m597c(r4, r5)     // Catch:{ IOException -> 0x0151 }
            r9 = r10
        L_0x00ff:
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x014e }
            java.lang.String r5 = "response code "
            r4.<init>(r5)     // Catch:{ IOException -> 0x014e }
            java.lang.StringBuilder r4 = r4.append(r12)     // Catch:{ IOException -> 0x014e }
            java.lang.String r4 = r4.toString()     // Catch:{ IOException -> 0x014e }
            r5 = 0
            java.lang.Object[] r5 = new java.lang.Object[r5]     // Catch:{ IOException -> 0x014e }
            com.tencent.bugly.p014a.C0221am.m598d(r4, r5)     // Catch:{ IOException -> 0x014e }
            int r4 = r11.getContentLength()     // Catch:{ IOException -> 0x014e }
            long r4 = (long) r4     // Catch:{ IOException -> 0x014e }
            r10 = 0
            int r10 = (r4 > r10 ? 1 : (r4 == r10 ? 0 : -1))
            if (r10 >= 0) goto L_0x0121
            r4 = 0
        L_0x0121:
            r0 = r17
            r0.mo379b(r4)     // Catch:{ IOException -> 0x014e }
            r4 = r6
            r5 = r8
            goto L_0x003d
        L_0x012a:
            r5 = move-exception
            r6 = r4
        L_0x012c:
            boolean r4 = com.tencent.bugly.p014a.C0221am.m594a(r5)
            if (r4 != 0) goto L_0x0135
            r5.printStackTrace()
        L_0x0135:
            r4 = r6
            r5 = r8
            goto L_0x003d
        L_0x0139:
            java.lang.String r5 = "Failed to execute post."
            r6 = 0
            java.lang.Object[] r6 = new java.lang.Object[r6]
            com.tencent.bugly.p014a.C0221am.m597c(r5, r6)
            r10 = 0
            r0 = r17
            r0.mo379b(r10)
            r5 = r8
            goto L_0x003d
        L_0x014b:
            r2 = 0
            goto L_0x000b
        L_0x014e:
            r4 = move-exception
            r5 = r4
            goto L_0x012c
        L_0x0151:
            r4 = move-exception
            r5 = r4
            r9 = r10
            goto L_0x012c
        L_0x0155:
            r6 = r4
            goto L_0x00ff
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.bugly.p014a.C0214af.mo364a(java.lang.String, byte[], com.tencent.bugly.a.aj, java.util.Map):byte[]");
    }

    /* renamed from: a */
    private static Map<String, String> m548a(HttpURLConnection httpURLConnection) {
        HashMap hashMap = new HashMap();
        Map headerFields = httpURLConnection.getHeaderFields();
        if (headerFields == null || headerFields.size() == 0) {
            return null;
        }
        for (String str : headerFields.keySet()) {
            List list = (List) headerFields.get(str);
            if (list.size() > 0) {
                hashMap.put(str, list.get(0));
            }
        }
        return hashMap;
    }

    /* JADX WARNING: Removed duplicated region for block: B:29:0x004a A[SYNTHETIC, Splitter:B:29:0x004a] */
    /* renamed from: b */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static byte[] m549b(java.net.HttpURLConnection r6) {
        /*
            r0 = 0
            if (r6 != 0) goto L_0x0004
        L_0x0003:
            return r0
        L_0x0004:
            java.io.BufferedInputStream r2 = new java.io.BufferedInputStream     // Catch:{ Throwable -> 0x0056, all -> 0x0046 }
            java.io.InputStream r1 = r6.getInputStream()     // Catch:{ Throwable -> 0x0056, all -> 0x0046 }
            r2.<init>(r1)     // Catch:{ Throwable -> 0x0056, all -> 0x0046 }
            java.io.ByteArrayOutputStream r1 = new java.io.ByteArrayOutputStream     // Catch:{ Throwable -> 0x0021 }
            r1.<init>()     // Catch:{ Throwable -> 0x0021 }
            r3 = 1024(0x400, float:1.435E-42)
            byte[] r3 = new byte[r3]     // Catch:{ Throwable -> 0x0021 }
        L_0x0016:
            int r4 = r2.read(r3)     // Catch:{ Throwable -> 0x0021 }
            if (r4 <= 0) goto L_0x0036
            r5 = 0
            r1.write(r3, r5, r4)     // Catch:{ Throwable -> 0x0021 }
            goto L_0x0016
        L_0x0021:
            r1 = move-exception
        L_0x0022:
            boolean r3 = com.tencent.bugly.p014a.C0221am.m594a(r1)     // Catch:{ all -> 0x0053 }
            if (r3 != 0) goto L_0x002b
            r1.printStackTrace()     // Catch:{ all -> 0x0053 }
        L_0x002b:
            if (r2 == 0) goto L_0x0003
            r2.close()     // Catch:{ Throwable -> 0x0031 }
            goto L_0x0003
        L_0x0031:
            r1 = move-exception
            r1.printStackTrace()
            goto L_0x0003
        L_0x0036:
            r1.flush()     // Catch:{ Throwable -> 0x0021 }
            byte[] r0 = r1.toByteArray()     // Catch:{ Throwable -> 0x0021 }
            r2.close()     // Catch:{ Throwable -> 0x0041 }
            goto L_0x0003
        L_0x0041:
            r1 = move-exception
            r1.printStackTrace()
            goto L_0x0003
        L_0x0046:
            r1 = move-exception
            r2 = r0
        L_0x0048:
            if (r2 == 0) goto L_0x004d
            r2.close()     // Catch:{ Throwable -> 0x004e }
        L_0x004d:
            throw r1
        L_0x004e:
            r0 = move-exception
            r0.printStackTrace()
            goto L_0x004d
        L_0x0053:
            r0 = move-exception
            r1 = r0
            goto L_0x0048
        L_0x0056:
            r1 = move-exception
            r2 = r0
            goto L_0x0022
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.bugly.p014a.C0214af.m549b(java.net.HttpURLConnection):byte[]");
    }

    /* renamed from: a */
    private HttpURLConnection m547a(String str, byte[] bArr, String str2, Map<String, String> map) {
        if (str == null) {
            C0221am.m599e("destUrl is null.", new Object[0]);
            return null;
        }
        HttpURLConnection a = m546a(str2, str);
        if (a == null) {
            C0221am.m599e("Failed to get HttpURLConnection object.", new Object[0]);
            return null;
        }
        try {
            a.setRequestProperty("wup_version", "3.0");
            if (map != null && map.size() > 0) {
                for (Map.Entry next : map.entrySet()) {
                    a.setRequestProperty((String) next.getKey(), URLEncoder.encode((String) next.getValue(), "utf-8"));
                }
            }
            a.setRequestProperty("A37", URLEncoder.encode(str2, "utf-8"));
            a.setRequestProperty("A38", URLEncoder.encode(C0208a.m502e(this.f419c), "utf-8"));
            a.connect();
            OutputStream outputStream = a.getOutputStream();
            if (bArr == null) {
                outputStream.write(0);
            } else {
                outputStream.write(bArr);
            }
            return a;
        } catch (Throwable th) {
            if (!C0221am.m594a(th)) {
                th.printStackTrace();
            }
            C0221am.m599e("Failed to upload crash, please check your network.", new Object[0]);
            return null;
        }
    }

    /* renamed from: a */
    private static HttpURLConnection m546a(String str, String str2) {
        HttpURLConnection httpURLConnection;
        try {
            URL url = new URL(str2);
            if (str == null || !str.toLowerCase(Locale.US).contains("wap")) {
                httpURLConnection = (HttpURLConnection) url.openConnection();
            } else {
                httpURLConnection = (HttpURLConnection) url.openConnection(new Proxy(Proxy.Type.HTTP, new InetSocketAddress(System.getProperty("http.proxyHost"), Integer.parseInt(System.getProperty("http.proxyPort")))));
            }
            httpURLConnection.setConnectTimeout(30000);
            httpURLConnection.setReadTimeout(10000);
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setDoInput(true);
            httpURLConnection.setRequestMethod(HttpMethod.POST);
            httpURLConnection.setUseCaches(false);
            httpURLConnection.setInstanceFollowRedirects(false);
            return httpURLConnection;
        } catch (Throwable th) {
            if (!C0221am.m594a(th)) {
                th.printStackTrace();
            }
            return null;
        }
    }
}
