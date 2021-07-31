package com.tencent.bugly.p014a;

import android.content.Context;
import android.os.Process;
import android.util.Base64;
import com.coloros.neton.BuildConfig;
import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.LinkedBlockingQueue;

/* renamed from: com.tencent.bugly.a.ah */
/* compiled from: BUGLY */
public final class C0216ah {

    /* renamed from: a */
    private static C0216ah f420a = null;
    /* access modifiers changed from: private */

    /* renamed from: b */
    public final Context f421b;

    /* renamed from: c */
    private Map<Integer, Long> f422c;

    /* renamed from: d */
    private LinkedBlockingQueue<Runnable> f423d;

    /* renamed from: e */
    private final Object f424e;

    /* renamed from: f */
    private String f425f;

    /* renamed from: g */
    private byte[] f426g;

    /* renamed from: h */
    private long f427h;
    /* access modifiers changed from: private */

    /* renamed from: i */
    public byte[] f428i;

    /* renamed from: j */
    private long f429j;
    /* access modifiers changed from: private */

    /* renamed from: k */
    public String f430k;

    /* renamed from: l */
    private long f431l;

    /* renamed from: m */
    private final Object f432m;
    /* access modifiers changed from: private */

    /* renamed from: n */
    public boolean f433n;
    /* access modifiers changed from: private */

    /* renamed from: o */
    public final Object f434o;

    /* renamed from: p */
    private boolean f435p;

    /* renamed from: a */
    static /* synthetic */ void m554a(C0216ah ahVar, Runnable runnable, long j) {
        if (runnable == null) {
            C0221am.m598d("[UploadManager] upload task should not be null", new Object[0]);
            return;
        }
        C0221am.m597c("[UploadManager] execute synchronized upload task (pid=%d | tid=%d)", Integer.valueOf(Process.myPid()), Integer.valueOf(Process.myTid()));
        Thread thread = new Thread(runnable);
        thread.setName("BuglySyncUploadThread");
        thread.start();
        try {
            thread.join(j);
        } catch (Throwable th) {
            C0221am.m599e("[UploadManager] failed to execute upload synchronized task with message: %s. Add it to queue", th.getMessage());
            ahVar.m557a(runnable);
        }
    }

    /* renamed from: a */
    public static synchronized C0216ah m553a() {
        C0216ah ahVar;
        synchronized (C0216ah.class) {
            ahVar = f420a;
        }
        return ahVar;
    }

    /* renamed from: a */
    public final void mo367a(int i, int i2, byte[] bArr, C0215ag agVar) {
        try {
            if (this.f435p) {
                m560b((Runnable) new C0218aj(this.f421b, i, i2, bArr, agVar, true));
                return;
            }
            C0219ak.m587a().mo382b(new C0218aj(this.f421b, i, i2, bArr, agVar, false));
        } catch (Throwable th) {
            if (!C0221am.m594a(th)) {
                th.printStackTrace();
            }
        }
    }

    /* renamed from: b */
    public static long m559b() {
        long j;
        long j2 = 0;
        long j3 = C0208a.m508j();
        List<C0213ae> c = C0211ac.m537c();
        if (c == null || c.size() <= 0) {
            j = 0;
        } else {
            try {
                C0213ae aeVar = c.get(0);
                if (aeVar.f414e >= j3) {
                    j2 = C0208a.m499d(aeVar.f416g);
                    c.remove(aeVar);
                }
                j = j2;
            } catch (Throwable th) {
                j = 0;
                C0221am.m599e("error local type %d", 3);
            }
            if (c.size() > 0) {
                C0211ac.m524a(c);
            }
        }
        C0221am.m597c("consume getted %d", Long.valueOf(j));
        return j;
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public final synchronized void mo371a(long j) {
        C0213ae aeVar = new C0213ae();
        aeVar.f411b = 3;
        aeVar.f414e = C0208a.m508j();
        aeVar.f412c = BuildConfig.FLAVOR;
        aeVar.f413d = BuildConfig.FLAVOR;
        aeVar.f416g = C0208a.m484a(j);
        C0211ac.m523a(3);
        C0211ac.m526a(aeVar);
        C0221am.m597c("consume update %d", Long.valueOf(j));
    }

    /* renamed from: a */
    public final synchronized void mo368a(int i, long j) {
        if (i >= 0) {
            this.f422c.put(Integer.valueOf(i), Long.valueOf(j));
            C0221am.m597c("up %d %d", Integer.valueOf(i), Long.valueOf(j));
        } else {
            C0221am.m599e("unknown up %d", Integer.valueOf(i));
        }
    }

    /* renamed from: a */
    public final synchronized long mo366a(int i) {
        long j;
        if (i >= 0) {
            Long l = this.f422c.get(Integer.valueOf(i));
            j = l == null ? -2 : l.longValue();
        } else {
            C0221am.m599e("unknown up %d", Integer.valueOf(i));
            j = -2;
        }
        return j;
    }

    /* renamed from: d */
    private static boolean m567d() {
        C0221am.m597c("[UploadManager] drop security info of database (pid=%d | tid=%d)", Integer.valueOf(Process.myPid()), Integer.valueOf(Process.myTid()));
        try {
            if (C0211ac.m521a() != null) {
                return C0211ac.m527a("security_info");
            }
            C0221am.m598d("[UploadManager] failed to get Database", new Object[0]);
            return false;
        } catch (Throwable th) {
            C0221am.m594a(th);
            return false;
        }
    }

    /* renamed from: e */
    private boolean m569e() {
        C0221am.m597c("[UploadManager] record security info to database (pid=%d | tid=%d)", Integer.valueOf(Process.myPid()), Integer.valueOf(Process.myTid()));
        try {
            if (C0211ac.m521a() == null) {
                C0221am.m598d("[UploadManager] failed to get Database", new Object[0]);
                return false;
            }
            StringBuilder sb = new StringBuilder();
            if (this.f428i != null) {
                sb.append(Base64.encodeToString(this.f428i, 0));
                sb.append("#");
                if (this.f431l != 0) {
                    sb.append(Long.toString(this.f429j));
                } else {
                    sb.append("null");
                }
                sb.append("#");
                if (this.f430k != null) {
                    sb.append(this.f430k);
                } else {
                    sb.append("null");
                }
                sb.append("#");
                if (this.f431l != 0) {
                    sb.append(Long.toString(this.f431l));
                } else {
                    sb.append("null");
                }
                C0211ac.m525a(555, "security_info", sb.toString().getBytes());
                return true;
            }
            C0221am.m597c("[UploadManager] AES key is null, will not record", new Object[0]);
            return false;
        } catch (Throwable th) {
            C0221am.m594a(th);
            m567d();
            return false;
        }
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: No exception handlers in catch block: Catch:{  } */
    /* renamed from: f */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean m571f() {
        /*
            r8 = this;
            r3 = 2
            r2 = 1
            r1 = 0
            java.lang.String r0 = "[UploadManager] load security info from dataBase (pid=%d | tid=%d)"
            java.lang.Object[] r3 = new java.lang.Object[r3]
            int r4 = android.os.Process.myPid()
            java.lang.Integer r4 = java.lang.Integer.valueOf(r4)
            r3[r1] = r4
            int r4 = android.os.Process.myTid()
            java.lang.Integer r4 = java.lang.Integer.valueOf(r4)
            r3[r2] = r4
            com.tencent.bugly.p014a.C0221am.m597c(r0, r3)
            com.tencent.bugly.a.ac r0 = com.tencent.bugly.p014a.C0211ac.m521a()     // Catch:{ Throwable -> 0x00fa }
            if (r0 != 0) goto L_0x002e
            java.lang.String r0 = "[UploadManager] failed to get Database"
            r2 = 0
            java.lang.Object[] r2 = new java.lang.Object[r2]     // Catch:{ Throwable -> 0x00fa }
            com.tencent.bugly.p014a.C0221am.m598d(r0, r2)     // Catch:{ Throwable -> 0x00fa }
            r0 = r1
        L_0x002d:
            return r0
        L_0x002e:
            java.util.Map r0 = com.tencent.bugly.p014a.C0211ac.m532b()     // Catch:{ Throwable -> 0x00fa }
            if (r0 == 0) goto L_0x00d0
            java.lang.String r3 = "security_info"
            boolean r3 = r0.containsKey(r3)     // Catch:{ Throwable -> 0x00fa }
            if (r3 == 0) goto L_0x00d0
            java.lang.String r3 = new java.lang.String     // Catch:{ Throwable -> 0x00fa }
            java.lang.String r4 = "security_info"
            java.lang.Object r0 = r0.get(r4)     // Catch:{ Throwable -> 0x00fa }
            byte[] r0 = (byte[]) r0     // Catch:{ Throwable -> 0x00fa }
            r3.<init>(r0)     // Catch:{ Throwable -> 0x00fa }
            java.lang.String r0 = "#"
            java.lang.String[] r4 = r3.split(r0)     // Catch:{ Throwable -> 0x00fa }
            int r0 = r4.length     // Catch:{ Throwable -> 0x00fa }
            r5 = 4
            if (r0 != r5) goto L_0x00e5
            r0 = 0
            r0 = r4[r0]     // Catch:{ Throwable -> 0x00fa }
            boolean r0 = r0.isEmpty()     // Catch:{ Throwable -> 0x00fa }
            if (r0 != 0) goto L_0x0101
            r0 = 0
            r0 = r4[r0]     // Catch:{ Throwable -> 0x00fa }
            java.lang.String r3 = "null"
            boolean r0 = r0.equals(r3)     // Catch:{ Throwable -> 0x00fa }
            if (r0 != 0) goto L_0x0101
            r0 = 0
            r0 = r4[r0]     // Catch:{ Throwable -> 0x00d3 }
            r3 = 0
            byte[] r0 = android.util.Base64.decode(r0, r3)     // Catch:{ Throwable -> 0x00d3 }
            r8.f428i = r0     // Catch:{ Throwable -> 0x00d3 }
            r0 = r1
        L_0x0072:
            if (r0 != 0) goto L_0x0091
            r3 = 1
            r3 = r4[r3]     // Catch:{ Throwable -> 0x00fa }
            boolean r3 = r3.isEmpty()     // Catch:{ Throwable -> 0x00fa }
            if (r3 != 0) goto L_0x0091
            r3 = 1
            r3 = r4[r3]     // Catch:{ Throwable -> 0x00fa }
            java.lang.String r5 = "null"
            boolean r3 = r3.equals(r5)     // Catch:{ Throwable -> 0x00fa }
            if (r3 != 0) goto L_0x0091
            r3 = 1
            r3 = r4[r3]     // Catch:{ Throwable -> 0x00d9 }
            long r6 = java.lang.Long.parseLong(r3)     // Catch:{ Throwable -> 0x00d9 }
            r8.f429j = r6     // Catch:{ Throwable -> 0x00d9 }
        L_0x0091:
            if (r0 != 0) goto L_0x00ac
            r3 = 2
            r3 = r4[r3]     // Catch:{ Throwable -> 0x00fa }
            boolean r3 = r3.isEmpty()     // Catch:{ Throwable -> 0x00fa }
            if (r3 != 0) goto L_0x00ac
            r3 = 2
            r3 = r4[r3]     // Catch:{ Throwable -> 0x00fa }
            java.lang.String r5 = "null"
            boolean r3 = r3.equals(r5)     // Catch:{ Throwable -> 0x00fa }
            if (r3 != 0) goto L_0x00ac
            r3 = 2
            r3 = r4[r3]     // Catch:{ Throwable -> 0x00fa }
            r8.f430k = r3     // Catch:{ Throwable -> 0x00fa }
        L_0x00ac:
            if (r0 != 0) goto L_0x00cb
            r3 = 3
            r3 = r4[r3]     // Catch:{ Throwable -> 0x00fa }
            boolean r3 = r3.isEmpty()     // Catch:{ Throwable -> 0x00fa }
            if (r3 != 0) goto L_0x00cb
            r3 = 3
            r3 = r4[r3]     // Catch:{ Throwable -> 0x00fa }
            java.lang.String r5 = "null"
            boolean r3 = r3.equals(r5)     // Catch:{ Throwable -> 0x00fa }
            if (r3 != 0) goto L_0x00cb
            r3 = 3
            r3 = r4[r3]     // Catch:{ Throwable -> 0x00df }
            long r4 = java.lang.Long.parseLong(r3)     // Catch:{ Throwable -> 0x00df }
            r8.f431l = r4     // Catch:{ Throwable -> 0x00df }
        L_0x00cb:
            if (r0 == 0) goto L_0x00d0
            m567d()     // Catch:{ Throwable -> 0x00fa }
        L_0x00d0:
            r0 = r2
            goto L_0x002d
        L_0x00d3:
            r0 = move-exception
            com.tencent.bugly.p014a.C0221am.m594a(r0)     // Catch:{ Throwable -> 0x00fa }
            r0 = r2
            goto L_0x0072
        L_0x00d9:
            r0 = move-exception
            com.tencent.bugly.p014a.C0221am.m594a(r0)     // Catch:{ Throwable -> 0x00fa }
            r0 = r2
            goto L_0x0091
        L_0x00df:
            r0 = move-exception
            com.tencent.bugly.p014a.C0221am.m594a(r0)     // Catch:{ Throwable -> 0x00fa }
            r0 = r2
            goto L_0x00cb
        L_0x00e5:
            java.lang.String r0 = "securityInfo = %s, strings.length = %d"
            r5 = 2
            java.lang.Object[] r5 = new java.lang.Object[r5]     // Catch:{ Throwable -> 0x00fa }
            r6 = 0
            r5[r6] = r3     // Catch:{ Throwable -> 0x00fa }
            r3 = 1
            int r4 = r4.length     // Catch:{ Throwable -> 0x00fa }
            java.lang.Integer r4 = java.lang.Integer.valueOf(r4)     // Catch:{ Throwable -> 0x00fa }
            r5[r3] = r4     // Catch:{ Throwable -> 0x00fa }
            com.tencent.bugly.p014a.C0221am.m593a(r0, r5)     // Catch:{ Throwable -> 0x00fa }
            r0 = r2
            goto L_0x00cb
        L_0x00fa:
            r0 = move-exception
            com.tencent.bugly.p014a.C0221am.m594a(r0)
            r0 = r1
            goto L_0x002d
        L_0x0101:
            r0 = r1
            goto L_0x0072
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.bugly.p014a.C0216ah.m571f():boolean");
    }

    /* access modifiers changed from: protected */
    /* renamed from: c */
    public final boolean mo376c() {
        if (this.f430k == null || this.f431l == 0) {
            return false;
        }
        long currentTimeMillis = System.currentTimeMillis() + this.f427h;
        if (this.f431l >= currentTimeMillis) {
            return true;
        }
        C0221am.m597c("[UploadManager] session ID expired time from server is: %d(%s), but now is: %d(%s)", Long.valueOf(this.f431l), new Date(this.f431l).toString(), Long.valueOf(currentTimeMillis), new Date(currentTimeMillis).toString());
        return false;
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public final void mo372a(boolean z) {
        synchronized (this.f432m) {
            C0221am.m597c("[UploadManager] clear security context (pid=%d | tid=%d)", Integer.valueOf(Process.myPid()), Integer.valueOf(Process.myTid()));
            this.f428i = null;
            this.f430k = null;
            this.f431l = 0;
        }
        if (z) {
            m567d();
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: c */
    public static boolean m565c(Context context) {
        C0221am.m597c("[UploadManager] try to lock security file (pid=%d | tid=%d)", Integer.valueOf(Process.myPid()), Integer.valueOf(Process.myTid()));
        try {
            File file = new File(context.getFilesDir() + File.separator + "security_info");
            if (file.exists()) {
                if (System.currentTimeMillis() - file.lastModified() < 30000) {
                    return false;
                }
                C0221am.m597c("[UploadManager] security file is expired, unlock it", new Object[0]);
                m568d(context);
            }
            if (!file.createNewFile()) {
                return false;
            }
            C0221am.m597c("[UploadManager] successfully locked security file (pid=%d | tid=%d)", Integer.valueOf(Process.myPid()), Integer.valueOf(Process.myTid()));
            return true;
        } catch (Throwable th) {
            C0221am.m594a(th);
            return false;
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: d */
    public static boolean m568d(Context context) {
        C0221am.m597c("[UploadManager] try to unlock security file (pid=%d | tid=%d)", Integer.valueOf(Process.myPid()), Integer.valueOf(Process.myTid()));
        try {
            File file = new File(context.getFilesDir() + File.separator + "security_info");
            if (!file.exists()) {
                return true;
            }
            if (!file.delete()) {
                return false;
            }
            C0221am.m597c("[UploadManager] successfully unlocked security file (pid=%d | tid=%d)", Integer.valueOf(Process.myPid()), Integer.valueOf(Process.myTid()));
            return true;
        } catch (Throwable th) {
            C0221am.m594a(th);
            return false;
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: b */
    public boolean m561b(int i) {
        if (i < 0) {
            C0221am.m593a("[UploadManager] number of task to execute should >= 0", new Object[0]);
            return false;
        }
        synchronized (this.f424e) {
            if (this.f423d.isEmpty()) {
                return true;
            }
            C0221am.m597c("[UploadManager] execute upload tasks of queue which has %d tasks (pid=%d | tid=%d)", Integer.valueOf(this.f423d.size()), Integer.valueOf(Process.myPid()), Long.valueOf(Thread.currentThread().getId()));
            if (i == 0 || i > this.f423d.size()) {
                i = this.f423d.size();
            }
            C0219ak a = C0219ak.m587a();
            if (a == null) {
                C0221am.m598d("[UploadManager] async task handler has not been initialized", new Object[0]);
                return false;
            }
            int i2 = 0;
            while (i2 < i) {
                try {
                    Runnable poll = this.f423d.poll();
                    if (poll != null) {
                        a.mo383c(poll);
                    } else {
                        C0221am.m598d("[UploadManager] upload task poll from queue is null", new Object[0]);
                    }
                    i2++;
                } catch (Throwable th) {
                    C0221am.m599e("[UploadManager] failed to execute upload task with message: %s", th.getMessage());
                    return false;
                }
            }
            return true;
        }
    }

    /* renamed from: a */
    private boolean m557a(Runnable runnable) {
        if (runnable == null) {
            C0221am.m598d("[UploadManager] upload task should not be null", new Object[0]);
            return false;
        }
        try {
            C0221am.m597c("[UploadManager] add upload task to queue (pid=%d | tid=%d)", Integer.valueOf(Process.myPid()), Integer.valueOf(Process.myTid()));
            synchronized (this.f424e) {
                this.f423d.put(runnable);
            }
            return true;
        } catch (Throwable th) {
            C0221am.m599e("[UploadManager] failed to add upload task to queue: %s", th.getMessage());
            return false;
        }
    }

    /* renamed from: b */
    private void m560b(Runnable runnable) {
        C0219ak a = C0219ak.m587a();
        if (a == null) {
            C0221am.m598d("[UploadManager] async task handler has not been initialized", new Object[0]);
        }
        C0221am.m597c("[UploadManager] add upload task (pid=%d | tid=%d)", Integer.valueOf(Process.myPid()), Integer.valueOf(Process.myTid()));
        if (this.f430k != null) {
            if (mo376c()) {
                C0221am.m597c("[UploadManager] sucessfully got session ID, try to execute upload task now (pid=%d | tid=%d)", Integer.valueOf(Process.myPid()), Integer.valueOf(Process.myTid()));
                if (a == null) {
                    m557a(runnable);
                } else {
                    a.mo383c(runnable);
                }
                m561b(0);
                return;
            }
            C0221am.m593a("[UploadManager] session ID is expired, drop it (pid=%d | tid=%d)", Integer.valueOf(Process.myPid()), Integer.valueOf(Process.myTid()));
            mo372a(false);
        }
        synchronized (this.f434o) {
            if (!this.f433n) {
                this.f433n = true;
                C0221am.m597c("[UploadManager] try to request session ID now (pid=%d | tid=%d)", Integer.valueOf(Process.myPid()), Integer.valueOf(Process.myTid()));
                m557a(runnable);
                a.mo383c(new C0217ai(this, this.f421b));
            } else {
                m557a(runnable);
            }
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:38:0x00de  */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void mo370a(int r9, com.tencent.bugly.p014a.C0236m r10) {
        /*
            r8 = this;
            r5 = 2
            r2 = 1
            r1 = 0
            boolean r0 = r8.f435p
            if (r0 != 0) goto L_0x0008
        L_0x0007:
            return
        L_0x0008:
            if (r9 != r5) goto L_0x0021
            r8.mo372a((boolean) r2)
        L_0x000d:
            java.lang.Object r1 = r8.f434o
            monitor-enter(r1)
            boolean r0 = r8.f433n     // Catch:{ all -> 0x001e }
            if (r0 == 0) goto L_0x001c
            r0 = 0
            r8.f433n = r0     // Catch:{ all -> 0x001e }
            android.content.Context r0 = r8.f421b     // Catch:{ all -> 0x001e }
            m568d((android.content.Context) r0)     // Catch:{ all -> 0x001e }
        L_0x001c:
            monitor-exit(r1)     // Catch:{ all -> 0x001e }
            goto L_0x0007
        L_0x001e:
            r0 = move-exception
            monitor-exit(r1)
            throw r0
        L_0x0021:
            boolean r0 = r8.f433n
            if (r0 == 0) goto L_0x0007
            java.lang.String r0 = "[UploadManager] record security context (pid=%d | tid=%d)"
            java.lang.Object[] r3 = new java.lang.Object[r5]
            int r4 = android.os.Process.myPid()
            java.lang.Integer r4 = java.lang.Integer.valueOf(r4)
            r3[r1] = r4
            int r4 = android.os.Process.myTid()
            java.lang.Integer r4 = java.lang.Integer.valueOf(r4)
            r3[r2] = r4
            com.tencent.bugly.p014a.C0221am.m597c(r0, r3)
            if (r10 == 0) goto L_0x010c
            java.util.Map<java.lang.String, java.lang.String> r3 = r10.f561h     // Catch:{ Throwable -> 0x00f2 }
            if (r3 == 0) goto L_0x010a
            java.lang.String r0 = "S1"
            boolean r0 = r3.containsKey(r0)     // Catch:{ Throwable -> 0x00f2 }
            if (r0 == 0) goto L_0x010a
            java.lang.String r0 = "S2"
            boolean r0 = r3.containsKey(r0)     // Catch:{ Throwable -> 0x00f2 }
            if (r0 == 0) goto L_0x010a
            long r4 = r10.f558e     // Catch:{ Throwable -> 0x00f2 }
            long r6 = java.lang.System.currentTimeMillis()     // Catch:{ Throwable -> 0x00f2 }
            long r4 = r4 - r6
            r8.f427h = r4     // Catch:{ Throwable -> 0x00f2 }
            java.lang.String r0 = "[UploadManager] time lag of server is: %d"
            r4 = 1
            java.lang.Object[] r4 = new java.lang.Object[r4]     // Catch:{ Throwable -> 0x00f2 }
            r5 = 0
            long r6 = r8.f427h     // Catch:{ Throwable -> 0x00f2 }
            java.lang.Long r6 = java.lang.Long.valueOf(r6)     // Catch:{ Throwable -> 0x00f2 }
            r4[r5] = r6     // Catch:{ Throwable -> 0x00f2 }
            com.tencent.bugly.p014a.C0221am.m597c(r0, r4)     // Catch:{ Throwable -> 0x00f2 }
            java.lang.String r0 = "S1"
            java.lang.Object r0 = r3.get(r0)     // Catch:{ Throwable -> 0x00f2 }
            java.lang.String r0 = (java.lang.String) r0     // Catch:{ Throwable -> 0x00f2 }
            r8.f430k = r0     // Catch:{ Throwable -> 0x00f2 }
            java.lang.String r0 = "[UploadManager] session ID from server is: %s"
            r4 = 1
            java.lang.Object[] r4 = new java.lang.Object[r4]     // Catch:{ Throwable -> 0x00f2 }
            r5 = 0
            java.lang.String r6 = r8.f430k     // Catch:{ Throwable -> 0x00f2 }
            r4[r5] = r6     // Catch:{ Throwable -> 0x00f2 }
            com.tencent.bugly.p014a.C0221am.m597c(r0, r4)     // Catch:{ Throwable -> 0x00f2 }
            java.lang.String r0 = r8.f430k     // Catch:{ Throwable -> 0x00f2 }
            int r0 = r0.length()     // Catch:{ Throwable -> 0x00f2 }
            if (r0 <= 0) goto L_0x0102
            java.lang.String r0 = "S2"
            java.lang.Object r0 = r3.get(r0)     // Catch:{ NumberFormatException -> 0x00e3 }
            java.lang.String r0 = (java.lang.String) r0     // Catch:{ NumberFormatException -> 0x00e3 }
            long r4 = java.lang.Long.parseLong(r0)     // Catch:{ NumberFormatException -> 0x00e3 }
            r8.f431l = r4     // Catch:{ NumberFormatException -> 0x00e3 }
            java.lang.String r0 = "[UploadManager] session expired time from server is: %d(%s)"
            r3 = 2
            java.lang.Object[] r3 = new java.lang.Object[r3]     // Catch:{ NumberFormatException -> 0x00e3 }
            r4 = 0
            long r6 = r8.f431l     // Catch:{ NumberFormatException -> 0x00e3 }
            java.lang.Long r5 = java.lang.Long.valueOf(r6)     // Catch:{ NumberFormatException -> 0x00e3 }
            r3[r4] = r5     // Catch:{ NumberFormatException -> 0x00e3 }
            r4 = 1
            java.util.Date r5 = new java.util.Date     // Catch:{ NumberFormatException -> 0x00e3 }
            long r6 = r8.f431l     // Catch:{ NumberFormatException -> 0x00e3 }
            r5.<init>(r6)     // Catch:{ NumberFormatException -> 0x00e3 }
            java.lang.String r5 = r5.toString()     // Catch:{ NumberFormatException -> 0x00e3 }
            r3[r4] = r5     // Catch:{ NumberFormatException -> 0x00e3 }
            com.tencent.bugly.p014a.C0221am.m597c(r0, r3)     // Catch:{ NumberFormatException -> 0x00e3 }
            long r4 = r8.f431l     // Catch:{ NumberFormatException -> 0x00e3 }
            r6 = 1000(0x3e8, double:4.94E-321)
            int r0 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r0 >= 0) goto L_0x00d1
            java.lang.String r0 = "[UploadManager] session expired time from server is less than 1 second, will set to default value"
            r3 = 0
            java.lang.Object[] r3 = new java.lang.Object[r3]     // Catch:{ NumberFormatException -> 0x00e3 }
            com.tencent.bugly.p014a.C0221am.m598d(r0, r3)     // Catch:{ NumberFormatException -> 0x00e3 }
            r4 = 259200000(0xf731400, double:1.280618154E-315)
            r8.f431l = r4     // Catch:{ NumberFormatException -> 0x00e3 }
        L_0x00d1:
            r0 = 0
            r8.m561b((int) r0)     // Catch:{ Throwable -> 0x00f2 }
            boolean r0 = r8.m569e()     // Catch:{ Throwable -> 0x00f2 }
            if (r0 == 0) goto L_0x00f8
            r0 = r1
        L_0x00dc:
            if (r0 == 0) goto L_0x000d
            r8.mo372a((boolean) r1)
            goto L_0x000d
        L_0x00e3:
            r0 = move-exception
            java.lang.String r0 = "[UploadManager] session expired time is invalid, will set to default value"
            r3 = 0
            java.lang.Object[] r3 = new java.lang.Object[r3]     // Catch:{ Throwable -> 0x00f2 }
            com.tencent.bugly.p014a.C0221am.m598d(r0, r3)     // Catch:{ Throwable -> 0x00f2 }
            r4 = 259200000(0xf731400, double:1.280618154E-315)
            r8.f431l = r4     // Catch:{ Throwable -> 0x00f2 }
            goto L_0x00d1
        L_0x00f2:
            r0 = move-exception
            com.tencent.bugly.p014a.C0221am.m594a(r0)
            r0 = r2
            goto L_0x00dc
        L_0x00f8:
            java.lang.String r0 = "[UploadManager] failed to record database"
            r3 = 0
            java.lang.Object[] r3 = new java.lang.Object[r3]     // Catch:{ Throwable -> 0x00f2 }
            com.tencent.bugly.p014a.C0221am.m597c(r0, r3)     // Catch:{ Throwable -> 0x00f2 }
            r0 = r2
            goto L_0x00dc
        L_0x0102:
            java.lang.String r0 = "[UploadManager] session ID from server is invalid, try next time"
            r3 = 0
            java.lang.Object[] r3 = new java.lang.Object[r3]     // Catch:{ Throwable -> 0x00f2 }
            com.tencent.bugly.p014a.C0221am.m597c(r0, r3)     // Catch:{ Throwable -> 0x00f2 }
        L_0x010a:
            r0 = r2
            goto L_0x00dc
        L_0x010c:
            java.lang.String r0 = "[UploadManager] fail to init security context and clear local info (pid=%d | tid=%d)"
            java.lang.Object[] r3 = new java.lang.Object[r5]
            int r4 = android.os.Process.myPid()
            java.lang.Integer r4 = java.lang.Integer.valueOf(r4)
            r3[r1] = r4
            int r4 = android.os.Process.myTid()
            java.lang.Integer r4 = java.lang.Integer.valueOf(r4)
            r3[r2] = r4
            com.tencent.bugly.p014a.C0221am.m597c(r0, r3)
            r8.mo372a((boolean) r1)
            goto L_0x000d
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.bugly.p014a.C0216ah.mo370a(int, com.tencent.bugly.a.m):void");
    }

    /* renamed from: a */
    public final byte[] mo374a(byte[] bArr) {
        if (this.f428i != null && (this.f428i.length << 3) == 128) {
            return C0208a.m483a(1, bArr, this.f428i);
        }
        C0221am.m598d("[UploadManager] AES key is invalid (pid=%d | tid=%d)", Integer.valueOf(Process.myPid()), Integer.valueOf(Process.myTid()));
        return null;
    }

    /* renamed from: b */
    public final byte[] mo375b(byte[] bArr) {
        if (this.f428i != null && (this.f428i.length << 3) == 128) {
            return C0208a.m483a(2, bArr, this.f428i);
        }
        C0221am.m598d("[UploadManager] AES key is invalid (pid=%d | tid=%d)", Integer.valueOf(Process.myPid()), Integer.valueOf(Process.myTid()));
        return null;
    }

    /* renamed from: a */
    public final boolean mo373a(Map<String, String> map) {
        C0221am.m597c("[UploadManager] integrate security to HTTP headers (pid=%d | tid=%d)", Integer.valueOf(Process.myPid()), Integer.valueOf(Process.myTid()));
        if (this.f430k != null) {
            map.put("secureSessionId", this.f430k);
            return true;
        } else if (this.f428i == null || (this.f428i.length << 3) != 128) {
            C0221am.m598d("[UploadManager] AES key is invalid", new Object[0]);
            return false;
        } else {
            if (this.f426g == null) {
                this.f426g = Base64.decode(this.f425f, 0);
                if (this.f426g == null) {
                    C0221am.m598d("[UploadManager] failed to decode RSA public key", new Object[0]);
                    return false;
                }
            }
            byte[] a = C0208a.m488a(this.f428i, this.f426g);
            if (a == null) {
                C0221am.m598d("[UploadManager] failed to encrypt AES key", new Object[0]);
                return false;
            }
            String encodeToString = Base64.encodeToString(a, 0);
            if (encodeToString == null) {
                C0221am.m598d("[UploadManager] failed to encode AES key", new Object[0]);
                return false;
            }
            map.put("raKey", encodeToString);
            return true;
        }
    }

    /* renamed from: a */
    public final void mo369a(int i, C0235l lVar, C0215ag agVar) {
        try {
            if (this.f435p) {
                m560b((Runnable) new C0218aj(this.f421b, i, lVar, agVar, true));
                return;
            }
            C0219ak.m587a().mo382b(new C0218aj(this.f421b, i, lVar, agVar, false));
        } catch (Throwable th) {
            if (!C0221am.m594a(th)) {
                th.printStackTrace();
            }
        }
    }
}
