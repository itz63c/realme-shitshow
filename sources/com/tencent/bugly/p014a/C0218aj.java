package com.tencent.bugly.p014a;

import android.content.Context;
import android.os.Process;
import com.coloros.neton.BuildConfig;
import com.tencent.bugly.crashreport.common.info.C0257c;
import com.tencent.bugly.crashreport.common.strategy.C0259b;

/* renamed from: com.tencent.bugly.a.aj */
/* compiled from: BUGLY */
public final class C0218aj implements Runnable {

    /* renamed from: a */
    private int f440a;

    /* renamed from: b */
    private int f441b;

    /* renamed from: c */
    private final Context f442c;

    /* renamed from: d */
    private final int f443d;

    /* renamed from: e */
    private final byte[] f444e;

    /* renamed from: f */
    private final C0257c f445f;

    /* renamed from: g */
    private final C0259b f446g;

    /* renamed from: h */
    private final C0214af f447h;

    /* renamed from: i */
    private final C0216ah f448i;

    /* renamed from: j */
    private final int f449j;

    /* renamed from: k */
    private final C0215ag f450k;

    /* renamed from: l */
    private final C0215ag f451l;

    /* renamed from: m */
    private String f452m;

    /* renamed from: n */
    private int f453n;

    /* renamed from: o */
    private long f454o;

    /* renamed from: p */
    private long f455p;

    /* renamed from: q */
    private boolean f456q;

    public C0218aj(Context context, int i, C0235l lVar, C0215ag agVar, boolean z) {
        this(context, i, lVar.f534g, C0208a.m486a(lVar), agVar, z);
    }

    public C0218aj(Context context, int i, int i2, byte[] bArr, C0215ag agVar, boolean z) {
        this(context, i, i2, bArr, agVar, z, (byte) 0);
    }

    private C0218aj(Context context, int i, int i2, byte[] bArr, C0215ag agVar, boolean z, byte b) {
        this.f440a = 3;
        this.f441b = 30000;
        this.f452m = BuildConfig.FLAVOR;
        this.f453n = 0;
        this.f454o = 0;
        this.f455p = 0;
        this.f456q = true;
        this.f442c = context;
        this.f445f = C0257c.m731a(context);
        this.f444e = bArr;
        this.f446g = C0259b.m751a();
        this.f447h = C0214af.m545a(context);
        this.f448i = C0216ah.m553a();
        this.f449j = i;
        this.f452m = null;
        this.f450k = agVar;
        this.f451l = null;
        this.f456q = z;
        if (z) {
            switch (i2) {
                case 510:
                case 640:
                    i2 = 840;
                    break;
                case 630:
                    i2 = 830;
                    break;
            }
        }
        this.f443d = i2;
        this.f440a = 5;
        this.f441b = 60000;
    }

    /* renamed from: a */
    private void m583a(boolean z, int i, String str) {
        String str2;
        switch (this.f443d) {
            case 630:
            case 830:
                str2 = "crash";
                break;
            case 640:
            case 840:
                str2 = "userinfo";
                break;
            default:
                str2 = String.valueOf(this.f443d);
                break;
        }
        if (z) {
            C0221am.m593a("[upload] success: %s", str2);
        } else {
            C0221am.m599e("[upload] fail! %s %d %s", str2, Integer.valueOf(i), str);
            if (this.f456q) {
                C0221am.m597c("[upload] failed to request, should clear security context (pid=%d | tid=%d)", Integer.valueOf(Process.myPid()), Integer.valueOf(Process.myTid()));
                this.f448i.mo370a(0, (C0236m) null);
            }
        }
        if (this.f454o + this.f455p > 0) {
            this.f448i.mo371a(C0216ah.m559b() + this.f454o + this.f455p);
        }
        if (this.f450k != null) {
            this.f450k.mo365a(z);
        }
        if (this.f451l != null) {
            this.f451l.mo365a(z);
        }
    }

    /* renamed from: a */
    private static boolean m584a(C0236m mVar, C0257c cVar, C0259b bVar) {
        boolean z;
        if (mVar == null) {
            C0221am.m598d("resp == null!", new Object[0]);
            return false;
        } else if (mVar.f554a != 0) {
            C0221am.m599e("resp result error %d", Byte.valueOf(mVar.f554a));
            return false;
        } else {
            try {
                String str = mVar.f557d;
                if (!(str == null || str.trim().length() <= 0) && C0257c.m730a().mo439e() != mVar.f557d) {
                    C0211ac.m521a();
                    C0211ac.m525a(C0259b.f733a, "key_ip", mVar.f557d.getBytes("UTF-8"));
                    cVar.mo434a(mVar.f557d);
                }
                String str2 = mVar.f560g;
                if (str2 == null || str2.trim().length() <= 0) {
                    z = true;
                } else {
                    z = false;
                }
                if (!z && C0257c.m730a().mo440f() != mVar.f560g) {
                    C0211ac.m521a();
                    C0211ac.m525a(C0259b.f733a, "key_imei", mVar.f560g.getBytes("UTF-8"));
                    cVar.mo436b(mVar.f560g);
                }
            } catch (Throwable th) {
            }
            cVar.f693h = mVar.f558e;
            if (mVar.f555b == 510) {
                if (mVar.f556c == null) {
                    C0221am.m599e("remote data is miss! %d", Integer.valueOf(mVar.f555b));
                    return false;
                }
                C0238o oVar = (C0238o) C0208a.m468a(mVar.f556c, C0238o.class);
                if (oVar == null) {
                    C0221am.m599e("remote data is error! %d", Integer.valueOf(mVar.f555b));
                    return false;
                }
                Object[] objArr = new Object[4];
                objArr[0] = Boolean.valueOf(oVar.f566a);
                objArr[1] = Boolean.valueOf(oVar.f568c);
                objArr[2] = Boolean.valueOf(oVar.f567b);
                objArr[3] = Integer.valueOf(oVar.f572g == null ? -1 : oVar.f572g.size());
                C0221am.m597c("en:%b qu:%b uin:%b vm:%d", objArr);
                bVar.mo457a(oVar);
            }
            return true;
        }
    }

    /* JADX WARNING: No exception handlers in catch block: Catch:{  } */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void run() {
        /*
            r10 = this;
            byte[] r0 = r10.f444e     // Catch:{ Throwable -> 0x0035 }
            r1 = 0
            r10.f453n = r1     // Catch:{ Throwable -> 0x0035 }
            r2 = 0
            r10.f454o = r2     // Catch:{ Throwable -> 0x0035 }
            r2 = 0
            r10.f455p = r2     // Catch:{ Throwable -> 0x0035 }
            com.tencent.bugly.a.ah r1 = r10.f448i     // Catch:{ Throwable -> 0x0035 }
            int r2 = r10.f449j     // Catch:{ Throwable -> 0x0035 }
            long r4 = java.lang.System.currentTimeMillis()     // Catch:{ Throwable -> 0x0035 }
            r1.mo368a((int) r2, (long) r4)     // Catch:{ Throwable -> 0x0035 }
            android.content.Context r1 = r10.f442c     // Catch:{ Throwable -> 0x0035 }
            java.lang.String r1 = com.tencent.bugly.p014a.C0208a.m502e(r1)     // Catch:{ Throwable -> 0x0035 }
            if (r1 != 0) goto L_0x0028
            r0 = 0
            r1 = 0
            java.lang.String r2 = "network is not availiable!"
            r10.m583a((boolean) r0, (int) r1, (java.lang.String) r2)     // Catch:{ Throwable -> 0x0035 }
        L_0x0027:
            return
        L_0x0028:
            if (r0 == 0) goto L_0x002d
            int r1 = r0.length     // Catch:{ Throwable -> 0x0035 }
            if (r1 != 0) goto L_0x0040
        L_0x002d:
            r0 = 0
            r1 = 0
            java.lang.String r2 = "[upload] fail, request package is empty!"
            r10.m583a((boolean) r0, (int) r1, (java.lang.String) r2)     // Catch:{ Throwable -> 0x0035 }
            goto L_0x0027
        L_0x0035:
            r0 = move-exception
            boolean r1 = com.tencent.bugly.p014a.C0221am.m594a(r0)
            if (r1 != 0) goto L_0x0027
            r0.printStackTrace()
            goto L_0x0027
        L_0x0040:
            long r2 = com.tencent.bugly.p014a.C0216ah.m559b()     // Catch:{ Throwable -> 0x0035 }
            r4 = 2097152(0x200000, double:1.0361308E-317)
            int r1 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r1 < 0) goto L_0x006c
            java.lang.String r0 = "up too much wait to next time ! %d %d "
            r1 = 2
            java.lang.Object[] r1 = new java.lang.Object[r1]     // Catch:{ Throwable -> 0x0035 }
            r4 = 0
            java.lang.Long r2 = java.lang.Long.valueOf(r2)     // Catch:{ Throwable -> 0x0035 }
            r1[r4] = r2     // Catch:{ Throwable -> 0x0035 }
            r2 = 1
            r4 = 2097152(0x200000, double:1.0361308E-317)
            java.lang.Long r3 = java.lang.Long.valueOf(r4)     // Catch:{ Throwable -> 0x0035 }
            r1[r2] = r3     // Catch:{ Throwable -> 0x0035 }
            com.tencent.bugly.p014a.C0221am.m599e(r0, r1)     // Catch:{ Throwable -> 0x0035 }
            r0 = 0
            r1 = 0
            java.lang.String r2 = "[upload] fail, over net consume: 2048K"
            r10.m583a((boolean) r0, (int) r1, (java.lang.String) r2)     // Catch:{ Throwable -> 0x0035 }
            goto L_0x0027
        L_0x006c:
            java.lang.String r1 = "do upload task %d"
            r2 = 1
            java.lang.Object[] r2 = new java.lang.Object[r2]     // Catch:{ Throwable -> 0x0035 }
            r3 = 0
            int r4 = r10.f443d     // Catch:{ Throwable -> 0x0035 }
            java.lang.Integer r4 = java.lang.Integer.valueOf(r4)     // Catch:{ Throwable -> 0x0035 }
            r2[r3] = r4     // Catch:{ Throwable -> 0x0035 }
            com.tencent.bugly.p014a.C0221am.m597c(r1, r2)     // Catch:{ Throwable -> 0x0035 }
            android.content.Context r1 = r10.f442c     // Catch:{ Throwable -> 0x0035 }
            if (r1 == 0) goto L_0x008f
            if (r0 == 0) goto L_0x008f
            com.tencent.bugly.crashreport.common.info.c r1 = r10.f445f     // Catch:{ Throwable -> 0x0035 }
            if (r1 == 0) goto L_0x008f
            com.tencent.bugly.crashreport.common.strategy.b r1 = r10.f446g     // Catch:{ Throwable -> 0x0035 }
            if (r1 == 0) goto L_0x008f
            com.tencent.bugly.a.af r1 = r10.f447h     // Catch:{ Throwable -> 0x0035 }
            if (r1 != 0) goto L_0x0097
        L_0x008f:
            r0 = 0
            r1 = 0
            java.lang.String r2 = "[upload] fail, illegal access error! "
            r10.m583a((boolean) r0, (int) r1, (java.lang.String) r2)     // Catch:{ Throwable -> 0x0035 }
            goto L_0x0027
        L_0x0097:
            com.tencent.bugly.crashreport.common.strategy.b r1 = r10.f446g     // Catch:{ Throwable -> 0x0035 }
            com.tencent.bugly.crashreport.common.strategy.StrategyBean r6 = r1.mo458b()     // Catch:{ Throwable -> 0x0035 }
            if (r6 != 0) goto L_0x00a7
            r0 = 0
            r1 = 0
            java.lang.String r2 = "[upload] fail, illegal local strategy!"
            r10.m583a((boolean) r0, (int) r1, (java.lang.String) r2)     // Catch:{ Throwable -> 0x0035 }
            goto L_0x0027
        L_0x00a7:
            r4 = 0
            java.util.HashMap r7 = new java.util.HashMap     // Catch:{ Throwable -> 0x0035 }
            r7.<init>()     // Catch:{ Throwable -> 0x0035 }
            java.lang.String r1 = "prodId"
            com.tencent.bugly.crashreport.common.info.c r2 = r10.f445f     // Catch:{ Throwable -> 0x0035 }
            java.lang.String r2 = r2.mo437c()     // Catch:{ Throwable -> 0x0035 }
            r7.put(r1, r2)     // Catch:{ Throwable -> 0x0035 }
            java.lang.String r1 = "bundleId"
            com.tencent.bugly.crashreport.common.info.c r2 = r10.f445f     // Catch:{ Throwable -> 0x0035 }
            java.lang.String r2 = r2.f688c     // Catch:{ Throwable -> 0x0035 }
            r7.put(r1, r2)     // Catch:{ Throwable -> 0x0035 }
            java.lang.String r1 = "appVer"
            com.tencent.bugly.crashreport.common.info.c r2 = r10.f445f     // Catch:{ Throwable -> 0x0035 }
            java.lang.String r2 = r2.f694i     // Catch:{ Throwable -> 0x0035 }
            r7.put(r1, r2)     // Catch:{ Throwable -> 0x0035 }
            boolean r1 = r10.f456q     // Catch:{ Throwable -> 0x0035 }
            if (r1 == 0) goto L_0x012b
            java.lang.String r1 = "cmd"
            int r2 = r10.f443d     // Catch:{ Throwable -> 0x0035 }
            java.lang.String r2 = java.lang.Integer.toString(r2)     // Catch:{ Throwable -> 0x0035 }
            r7.put(r1, r2)     // Catch:{ Throwable -> 0x0035 }
            java.lang.String r1 = "platformId"
            r2 = 1
            java.lang.String r2 = java.lang.Byte.toString(r2)     // Catch:{ Throwable -> 0x0035 }
            r7.put(r1, r2)     // Catch:{ Throwable -> 0x0035 }
            java.lang.String r1 = "sdkVer"
            com.tencent.bugly.crashreport.common.info.c r2 = r10.f445f     // Catch:{ Throwable -> 0x0035 }
            r2.getClass()     // Catch:{ Throwable -> 0x0035 }
            java.lang.String r2 = "2.1"
            r7.put(r1, r2)     // Catch:{ Throwable -> 0x0035 }
            java.lang.String r1 = "strategylastUpdateTime"
            long r2 = r6.f725l     // Catch:{ Throwable -> 0x0035 }
            java.lang.String r2 = java.lang.Long.toString(r2)     // Catch:{ Throwable -> 0x0035 }
            r7.put(r1, r2)     // Catch:{ Throwable -> 0x0035 }
            com.tencent.bugly.a.ah r1 = r10.f448i     // Catch:{ Throwable -> 0x0035 }
            boolean r1 = r1.mo373a((java.util.Map<java.lang.String, java.lang.String>) r7)     // Catch:{ Throwable -> 0x0035 }
            if (r1 != 0) goto L_0x010b
            r0 = 0
            r1 = 0
            java.lang.String r2 = "[upload] fail, failed to add security info to HTTP headers"
            r10.m583a((boolean) r0, (int) r1, (java.lang.String) r2)     // Catch:{ Throwable -> 0x0035 }
            goto L_0x0027
        L_0x010b:
            byte[] r0 = com.tencent.bugly.p014a.C0208a.m493b((byte[]) r0)     // Catch:{ Throwable -> 0x0035 }
            if (r0 != 0) goto L_0x011a
            r0 = 0
            r1 = 0
            java.lang.String r2 = "[upload] fail, failed to zip request body"
            r10.m583a((boolean) r0, (int) r1, (java.lang.String) r2)     // Catch:{ Throwable -> 0x0035 }
            goto L_0x0027
        L_0x011a:
            com.tencent.bugly.a.ah r1 = r10.f448i     // Catch:{ Throwable -> 0x0035 }
            byte[] r0 = r1.mo374a((byte[]) r0)     // Catch:{ Throwable -> 0x0035 }
            if (r0 != 0) goto L_0x012b
            r0 = 0
            r1 = 0
            java.lang.String r2 = "[upload] fail, failed to encrypt request body"
            r10.m583a((boolean) r0, (int) r1, (java.lang.String) r2)     // Catch:{ Throwable -> 0x0035 }
            goto L_0x0027
        L_0x012b:
            r1 = r0
            r2 = -1
            r0 = 0
            r3 = r0
            r5 = r4
        L_0x0130:
            int r0 = r10.f440a     // Catch:{ Throwable -> 0x0035 }
            if (r3 >= r0) goto L_0x0385
            int r4 = r3 + 1
            if (r3 == 0) goto L_0x014d
            java.lang.String r0 = "failed to upload last time, wait and try(%d) again"
            r3 = 1
            java.lang.Object[] r3 = new java.lang.Object[r3]     // Catch:{ Throwable -> 0x0035 }
            r5 = 0
            java.lang.Integer r8 = java.lang.Integer.valueOf(r4)     // Catch:{ Throwable -> 0x0035 }
            r3[r5] = r8     // Catch:{ Throwable -> 0x0035 }
            com.tencent.bugly.p014a.C0221am.m598d(r0, r3)     // Catch:{ Throwable -> 0x0035 }
            int r0 = r10.f441b     // Catch:{ Throwable -> 0x0035 }
            long r8 = (long) r0
            java.lang.Thread.sleep(r8)     // Catch:{ InterruptedException -> 0x01c7 }
        L_0x014d:
            java.lang.String r0 = "send %d"
            r3 = 1
            java.lang.Object[] r3 = new java.lang.Object[r3]     // Catch:{ Throwable -> 0x0035 }
            r5 = 0
            int r8 = r1.length     // Catch:{ Throwable -> 0x0035 }
            java.lang.Integer r8 = java.lang.Integer.valueOf(r8)     // Catch:{ Throwable -> 0x0035 }
            r3[r5] = r8     // Catch:{ Throwable -> 0x0035 }
            com.tencent.bugly.p014a.C0221am.m597c(r0, r3)     // Catch:{ Throwable -> 0x0035 }
            java.lang.String r0 = r10.f452m     // Catch:{ Throwable -> 0x0035 }
            if (r0 == 0) goto L_0x01cc
            java.lang.String r0 = r0.trim()     // Catch:{ Throwable -> 0x0035 }
            int r0 = r0.length()     // Catch:{ Throwable -> 0x0035 }
            if (r0 <= 0) goto L_0x01cc
            r0 = 0
        L_0x016c:
            if (r0 == 0) goto L_0x0176
            boolean r0 = r10.f456q     // Catch:{ Throwable -> 0x0035 }
            if (r0 == 0) goto L_0x01ce
            java.lang.String r0 = r6.f727n     // Catch:{ Throwable -> 0x0035 }
            r10.f452m = r0     // Catch:{ Throwable -> 0x0035 }
        L_0x0176:
            java.lang.String r0 = "do upload to %s with cmd %d (pid=%d | tid=%d)"
            r3 = 4
            java.lang.Object[] r3 = new java.lang.Object[r3]     // Catch:{ Throwable -> 0x0035 }
            r5 = 0
            java.lang.String r8 = r10.f452m     // Catch:{ Throwable -> 0x0035 }
            r3[r5] = r8     // Catch:{ Throwable -> 0x0035 }
            r5 = 1
            int r8 = r10.f443d     // Catch:{ Throwable -> 0x0035 }
            java.lang.Integer r8 = java.lang.Integer.valueOf(r8)     // Catch:{ Throwable -> 0x0035 }
            r3[r5] = r8     // Catch:{ Throwable -> 0x0035 }
            r5 = 2
            int r8 = android.os.Process.myPid()     // Catch:{ Throwable -> 0x0035 }
            java.lang.Integer r8 = java.lang.Integer.valueOf(r8)     // Catch:{ Throwable -> 0x0035 }
            r3[r5] = r8     // Catch:{ Throwable -> 0x0035 }
            r5 = 3
            int r8 = android.os.Process.myTid()     // Catch:{ Throwable -> 0x0035 }
            java.lang.Integer r8 = java.lang.Integer.valueOf(r8)     // Catch:{ Throwable -> 0x0035 }
            r3[r5] = r8     // Catch:{ Throwable -> 0x0035 }
            com.tencent.bugly.p014a.C0221am.m597c(r0, r3)     // Catch:{ Throwable -> 0x0035 }
            com.tencent.bugly.a.af r0 = r10.f447h     // Catch:{ Throwable -> 0x0035 }
            java.lang.String r3 = r10.f452m     // Catch:{ Throwable -> 0x0035 }
            byte[] r3 = r0.mo364a((java.lang.String) r3, (byte[]) r1, (com.tencent.bugly.p014a.C0218aj) r10, (java.util.Map<java.lang.String, java.lang.String>) r7)     // Catch:{ Throwable -> 0x0035 }
            if (r3 != 0) goto L_0x01d3
            java.lang.String r0 = "upload fail, no response!"
            java.lang.String r3 = "try upload fail! %d %s"
            r5 = 2
            java.lang.Object[] r5 = new java.lang.Object[r5]     // Catch:{ Throwable -> 0x0035 }
            r8 = 0
            int r9 = r10.f443d     // Catch:{ Throwable -> 0x0035 }
            java.lang.Integer r9 = java.lang.Integer.valueOf(r9)     // Catch:{ Throwable -> 0x0035 }
            r5[r8] = r9     // Catch:{ Throwable -> 0x0035 }
            r8 = 1
            r5[r8] = r0     // Catch:{ Throwable -> 0x0035 }
            com.tencent.bugly.p014a.C0221am.m599e(r3, r5)     // Catch:{ Throwable -> 0x0035 }
            r0 = 1
            r3 = r4
            r5 = r0
            goto L_0x0130
        L_0x01c7:
            r0 = move-exception
            r0.printStackTrace()     // Catch:{ Throwable -> 0x0035 }
            goto L_0x014d
        L_0x01cc:
            r0 = 1
            goto L_0x016c
        L_0x01ce:
            java.lang.String r0 = r6.f729p     // Catch:{ Throwable -> 0x0035 }
            r10.f452m = r0     // Catch:{ Throwable -> 0x0035 }
            goto L_0x0176
        L_0x01d3:
            com.tencent.bugly.a.af r0 = r10.f447h     // Catch:{ Throwable -> 0x0035 }
            java.util.Map<java.lang.String, java.lang.String> r0 = r0.f418a     // Catch:{ Throwable -> 0x0035 }
            boolean r5 = r10.f456q     // Catch:{ Throwable -> 0x0035 }
            if (r5 == 0) goto L_0x02fa
            if (r0 == 0) goto L_0x01eb
            int r5 = r0.size()     // Catch:{ Throwable -> 0x0035 }
            if (r5 == 0) goto L_0x01eb
            java.lang.String r5 = "status"
            boolean r5 = r0.containsKey(r5)     // Catch:{ Throwable -> 0x0035 }
            if (r5 != 0) goto L_0x0224
        L_0x01eb:
            java.lang.String r0 = "no headers from server, just try again (pid=%d | tid=%d)"
            r3 = 2
            java.lang.Object[] r3 = new java.lang.Object[r3]     // Catch:{ Throwable -> 0x0035 }
            r5 = 0
            int r8 = android.os.Process.myPid()     // Catch:{ Throwable -> 0x0035 }
            java.lang.Integer r8 = java.lang.Integer.valueOf(r8)     // Catch:{ Throwable -> 0x0035 }
            r3[r5] = r8     // Catch:{ Throwable -> 0x0035 }
            r5 = 1
            int r8 = android.os.Process.myTid()     // Catch:{ Throwable -> 0x0035 }
            java.lang.Integer r8 = java.lang.Integer.valueOf(r8)     // Catch:{ Throwable -> 0x0035 }
            r3[r5] = r8     // Catch:{ Throwable -> 0x0035 }
            com.tencent.bugly.p014a.C0221am.m597c(r0, r3)     // Catch:{ Throwable -> 0x0035 }
            java.lang.String r0 = "upload fail, no status header"
            java.lang.String r3 = "try upload fail! %d %s"
            r5 = 2
            java.lang.Object[] r5 = new java.lang.Object[r5]     // Catch:{ Throwable -> 0x0035 }
            r8 = 0
            int r9 = r10.f443d     // Catch:{ Throwable -> 0x0035 }
            java.lang.Integer r9 = java.lang.Integer.valueOf(r9)     // Catch:{ Throwable -> 0x0035 }
            r5[r8] = r9     // Catch:{ Throwable -> 0x0035 }
            r8 = 1
            r5[r8] = r0     // Catch:{ Throwable -> 0x0035 }
            com.tencent.bugly.p014a.C0221am.m599e(r3, r5)     // Catch:{ Throwable -> 0x0035 }
            r0 = 1
            r3 = r4
            r5 = r0
            goto L_0x0130
        L_0x0224:
            java.lang.String r5 = "status"
            java.lang.Object r0 = r0.get(r5)     // Catch:{ Throwable -> 0x029f }
            java.lang.String r0 = (java.lang.String) r0     // Catch:{ Throwable -> 0x029f }
            int r0 = java.lang.Integer.parseInt(r0)     // Catch:{ Throwable -> 0x029f }
            java.lang.String r2 = "status from server is %d (pid=%d | tid=%d)"
            r5 = 3
            java.lang.Object[] r5 = new java.lang.Object[r5]     // Catch:{ Throwable -> 0x038d }
            r8 = 0
            java.lang.Integer r9 = java.lang.Integer.valueOf(r0)     // Catch:{ Throwable -> 0x038d }
            r5[r8] = r9     // Catch:{ Throwable -> 0x038d }
            r8 = 1
            int r9 = android.os.Process.myPid()     // Catch:{ Throwable -> 0x038d }
            java.lang.Integer r9 = java.lang.Integer.valueOf(r9)     // Catch:{ Throwable -> 0x038d }
            r5[r8] = r9     // Catch:{ Throwable -> 0x038d }
            r8 = 2
            int r9 = android.os.Process.myTid()     // Catch:{ Throwable -> 0x038d }
            java.lang.Integer r9 = java.lang.Integer.valueOf(r9)     // Catch:{ Throwable -> 0x038d }
            r5[r8] = r9     // Catch:{ Throwable -> 0x038d }
            com.tencent.bugly.p014a.C0221am.m597c(r2, r5)     // Catch:{ Throwable -> 0x038d }
            if (r0 == 0) goto L_0x02f9
            r2 = 2
            if (r0 != r2) goto L_0x02cd
            java.lang.String r1 = "session ID is expired, will try again"
            java.lang.String r2 = "try upload fail! %d %s"
            r3 = 2
            java.lang.Object[] r3 = new java.lang.Object[r3]     // Catch:{ Throwable -> 0x0035 }
            r4 = 0
            int r5 = r10.f443d     // Catch:{ Throwable -> 0x0035 }
            java.lang.Integer r5 = java.lang.Integer.valueOf(r5)     // Catch:{ Throwable -> 0x0035 }
            r3[r4] = r5     // Catch:{ Throwable -> 0x0035 }
            r4 = 1
            r3[r4] = r1     // Catch:{ Throwable -> 0x0035 }
            com.tencent.bugly.p014a.C0221am.m599e(r2, r3)     // Catch:{ Throwable -> 0x0035 }
            com.tencent.bugly.a.ah r1 = r10.f448i     // Catch:{ Throwable -> 0x0035 }
            r2 = 0
            r1.mo370a((int) r0, (com.tencent.bugly.p014a.C0236m) r2)     // Catch:{ Throwable -> 0x0035 }
            com.tencent.bugly.a.ah r0 = r10.f448i     // Catch:{ Throwable -> 0x0035 }
            int r1 = r10.f449j     // Catch:{ Throwable -> 0x0035 }
            int r2 = r10.f443d     // Catch:{ Throwable -> 0x0035 }
            byte[] r3 = r10.f444e     // Catch:{ Throwable -> 0x0035 }
            com.tencent.bugly.a.ag r4 = r10.f451l     // Catch:{ Throwable -> 0x0035 }
            r0.mo367a(r1, r2, r3, r4)     // Catch:{ Throwable -> 0x0035 }
            long r0 = r10.f454o     // Catch:{ Throwable -> 0x0035 }
            long r2 = r10.f455p     // Catch:{ Throwable -> 0x0035 }
            long r0 = r0 + r2
            r2 = 0
            int r0 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r0 <= 0) goto L_0x0027
            long r0 = com.tencent.bugly.p014a.C0216ah.m559b()     // Catch:{ Throwable -> 0x0035 }
            long r2 = r10.f454o     // Catch:{ Throwable -> 0x0035 }
            long r0 = r0 + r2
            long r2 = r10.f455p     // Catch:{ Throwable -> 0x0035 }
            long r0 = r0 + r2
            com.tencent.bugly.a.ah r2 = r10.f448i     // Catch:{ Throwable -> 0x0035 }
            r2.mo371a((long) r0)     // Catch:{ Throwable -> 0x0035 }
            goto L_0x0027
        L_0x029f:
            r0 = move-exception
            r0 = r2
        L_0x02a1:
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x0035 }
            java.lang.String r3 = "upload fail, format of status header is invalid: "
            r2.<init>(r3)     // Catch:{ Throwable -> 0x0035 }
            java.lang.String r3 = java.lang.Integer.toString(r0)     // Catch:{ Throwable -> 0x0035 }
            java.lang.StringBuilder r2 = r2.append(r3)     // Catch:{ Throwable -> 0x0035 }
            java.lang.String r2 = r2.toString()     // Catch:{ Throwable -> 0x0035 }
            java.lang.String r3 = "try upload fail! %d %s"
            r5 = 2
            java.lang.Object[] r5 = new java.lang.Object[r5]     // Catch:{ Throwable -> 0x0035 }
            r8 = 0
            int r9 = r10.f443d     // Catch:{ Throwable -> 0x0035 }
            java.lang.Integer r9 = java.lang.Integer.valueOf(r9)     // Catch:{ Throwable -> 0x0035 }
            r5[r8] = r9     // Catch:{ Throwable -> 0x0035 }
            r8 = 1
            r5[r8] = r2     // Catch:{ Throwable -> 0x0035 }
            com.tencent.bugly.p014a.C0221am.m599e(r3, r5)     // Catch:{ Throwable -> 0x0035 }
            r5 = 1
            r3 = r4
            r2 = r0
            goto L_0x0130
        L_0x02cd:
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x0035 }
            java.lang.String r3 = "upload fail, status: "
            r2.<init>(r3)     // Catch:{ Throwable -> 0x0035 }
            java.lang.String r3 = java.lang.Integer.toString(r0)     // Catch:{ Throwable -> 0x0035 }
            java.lang.StringBuilder r2 = r2.append(r3)     // Catch:{ Throwable -> 0x0035 }
            java.lang.String r2 = r2.toString()     // Catch:{ Throwable -> 0x0035 }
            java.lang.String r3 = "try upload fail! %d %s"
            r5 = 2
            java.lang.Object[] r5 = new java.lang.Object[r5]     // Catch:{ Throwable -> 0x0035 }
            r8 = 0
            int r9 = r10.f443d     // Catch:{ Throwable -> 0x0035 }
            java.lang.Integer r9 = java.lang.Integer.valueOf(r9)     // Catch:{ Throwable -> 0x0035 }
            r5[r8] = r9     // Catch:{ Throwable -> 0x0035 }
            r8 = 1
            r5[r8] = r2     // Catch:{ Throwable -> 0x0035 }
            com.tencent.bugly.p014a.C0221am.m599e(r3, r5)     // Catch:{ Throwable -> 0x0035 }
            r5 = 1
            r3 = r4
            r2 = r0
            goto L_0x0130
        L_0x02f9:
            r2 = r0
        L_0x02fa:
            java.lang.String r0 = "recv %d"
            r1 = 1
            java.lang.Object[] r1 = new java.lang.Object[r1]     // Catch:{ Throwable -> 0x0035 }
            r4 = 0
            int r5 = r3.length     // Catch:{ Throwable -> 0x0035 }
            java.lang.Integer r5 = java.lang.Integer.valueOf(r5)     // Catch:{ Throwable -> 0x0035 }
            r1[r4] = r5     // Catch:{ Throwable -> 0x0035 }
            com.tencent.bugly.p014a.C0221am.m597c(r0, r1)     // Catch:{ Throwable -> 0x0035 }
            boolean r0 = r10.f456q     // Catch:{ Throwable -> 0x0035 }
            if (r0 == 0) goto L_0x032e
            com.tencent.bugly.a.ah r0 = r10.f448i     // Catch:{ Throwable -> 0x0035 }
            byte[] r0 = r0.mo375b((byte[]) r3)     // Catch:{ Throwable -> 0x0035 }
            if (r0 != 0) goto L_0x031f
            r0 = 0
            r1 = 1
            java.lang.String r2 = "upload fail, failed to decrypt response from server"
            r10.m583a((boolean) r0, (int) r1, (java.lang.String) r2)     // Catch:{ Throwable -> 0x0035 }
            goto L_0x0027
        L_0x031f:
            byte[] r0 = com.tencent.bugly.p014a.C0208a.m497c((byte[]) r0)     // Catch:{ Throwable -> 0x0035 }
            if (r0 != 0) goto L_0x032f
            r0 = 0
            r1 = 1
            java.lang.String r2 = "upload fail, failed to unzip(gzip) response from server"
            r10.m583a((boolean) r0, (int) r1, (java.lang.String) r2)     // Catch:{ Throwable -> 0x0035 }
            goto L_0x0027
        L_0x032e:
            r0 = r3
        L_0x032f:
            boolean r1 = r10.f456q     // Catch:{ Throwable -> 0x0035 }
            com.tencent.bugly.a.m r1 = com.tencent.bugly.p014a.C0208a.m470a((byte[]) r0, (boolean) r1)     // Catch:{ Throwable -> 0x0035 }
            if (r1 != 0) goto L_0x0340
            r0 = 0
            r1 = 1
            java.lang.String r2 = "upload fail, resp null!"
            r10.m583a((boolean) r0, (int) r1, (java.lang.String) r2)     // Catch:{ Throwable -> 0x0035 }
            goto L_0x0027
        L_0x0340:
            boolean r0 = r10.f456q     // Catch:{ Throwable -> 0x0035 }
            if (r0 == 0) goto L_0x0349
            com.tencent.bugly.a.ah r0 = r10.f448i     // Catch:{ Throwable -> 0x0035 }
            r0.mo370a((int) r2, (com.tencent.bugly.p014a.C0236m) r1)     // Catch:{ Throwable -> 0x0035 }
        L_0x0349:
            java.lang.String r2 = "response %d %d"
            r0 = 2
            java.lang.Object[] r3 = new java.lang.Object[r0]     // Catch:{ Throwable -> 0x0035 }
            r0 = 0
            int r4 = r1.f555b     // Catch:{ Throwable -> 0x0035 }
            java.lang.Integer r4 = java.lang.Integer.valueOf(r4)     // Catch:{ Throwable -> 0x0035 }
            r3[r0] = r4     // Catch:{ Throwable -> 0x0035 }
            r4 = 1
            byte[] r0 = r1.f556c     // Catch:{ Throwable -> 0x0035 }
            if (r0 != 0) goto L_0x0379
            r0 = 0
        L_0x035d:
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)     // Catch:{ Throwable -> 0x0035 }
            r3[r4] = r0     // Catch:{ Throwable -> 0x0035 }
            com.tencent.bugly.p014a.C0221am.m597c(r2, r3)     // Catch:{ Throwable -> 0x0035 }
            com.tencent.bugly.crashreport.common.info.c r0 = r10.f445f     // Catch:{ Throwable -> 0x0035 }
            com.tencent.bugly.crashreport.common.strategy.b r2 = r10.f446g     // Catch:{ Throwable -> 0x0035 }
            boolean r0 = m584a((com.tencent.bugly.p014a.C0236m) r1, (com.tencent.bugly.crashreport.common.info.C0257c) r0, (com.tencent.bugly.crashreport.common.strategy.C0259b) r2)     // Catch:{ Throwable -> 0x0035 }
            if (r0 != 0) goto L_0x037d
            r0 = 0
            r2 = 2
            java.lang.String r1 = r1.f559f     // Catch:{ Throwable -> 0x0035 }
            r10.m583a((boolean) r0, (int) r2, (java.lang.String) r1)     // Catch:{ Throwable -> 0x0035 }
            goto L_0x0027
        L_0x0379:
            byte[] r0 = r1.f556c     // Catch:{ Throwable -> 0x0035 }
            int r0 = r0.length     // Catch:{ Throwable -> 0x0035 }
            goto L_0x035d
        L_0x037d:
            r0 = 1
            r1 = 2
            r2 = 0
            r10.m583a((boolean) r0, (int) r1, (java.lang.String) r2)     // Catch:{ Throwable -> 0x0035 }
            goto L_0x0027
        L_0x0385:
            r0 = 0
            java.lang.String r1 = "try OT Fail!"
            r10.m583a((boolean) r0, (int) r5, (java.lang.String) r1)     // Catch:{ Throwable -> 0x0035 }
            goto L_0x0027
        L_0x038d:
            r2 = move-exception
            goto L_0x02a1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.bugly.p014a.C0218aj.run():void");
    }

    /* renamed from: a */
    public final void mo378a(long j) {
        this.f453n++;
        this.f454o += j;
    }

    /* renamed from: b */
    public final void mo379b(long j) {
        this.f455p += j;
    }
}
