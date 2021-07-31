package com.tencent.bugly.p014a;

import android.content.Context;
import android.os.Process;

/* renamed from: com.tencent.bugly.a.ai */
/* compiled from: BUGLY */
final class C0217ai implements Runnable {

    /* renamed from: a */
    private final Context f436a;

    /* renamed from: b */
    private final Runnable f437b;

    /* renamed from: c */
    private final long f438c;

    /* renamed from: d */
    private /* synthetic */ C0216ah f439d;

    public C0217ai(C0216ah ahVar, Context context) {
        this(ahVar, context, (byte) 0);
    }

    private C0217ai(C0216ah ahVar, Context context, byte b) {
        this.f439d = ahVar;
        this.f436a = context;
        this.f437b = null;
        this.f438c = 0;
    }

    public final void run() {
        if (!C0216ah.m565c(this.f436a)) {
            C0219ak a = C0219ak.m587a();
            if (a == null) {
                C0221am.m599e("[UploadManager] async task handler has not been initialized", new Object[0]);
                return;
            }
            C0221am.m597c("[UploadManager] sleep %d try to lock security file again", 5000);
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            a.mo383c(new C0217ai(this.f439d, this.f439d.f421b));
            return;
        }
        if (!this.f439d.m571f()) {
            C0221am.m598d("[UploadManager] failed to load security info from database", new Object[0]);
            this.f439d.mo372a(false);
        }
        if (this.f439d.f430k != null) {
            if (this.f439d.mo376c()) {
                C0221am.m597c("[UploadManager] sucessfully got session ID, try to execute upload tasks now (pid=%d | tid=%d)", Integer.valueOf(Process.myPid()), Integer.valueOf(Process.myTid()));
                if (this.f437b != null) {
                    C0216ah.m554a(this.f439d, this.f437b, this.f438c);
                }
                boolean unused = this.f439d.m561b(0);
                boolean unused2 = C0216ah.m568d(this.f436a);
                synchronized (this.f439d.f434o) {
                    boolean unused3 = this.f439d.f433n = false;
                }
                return;
            }
            C0221am.m593a("[UploadManager] session ID is expired, drop it", new Object[0]);
            this.f439d.mo372a(true);
        }
        byte[] k = C0208a.m509k();
        if (k == null || (k.length << 3) != 128) {
            C0221am.m598d("[UploadManager] failed to create AES key (pid=%d | tid=%d)", Integer.valueOf(Process.myPid()), Integer.valueOf(Process.myTid()));
        } else {
            byte[] unused4 = this.f439d.f428i = k;
            C0221am.m597c("[UploadManager] execute one upload task for requesting session ID (pid=%d | tid=%d)", Integer.valueOf(Process.myPid()), Integer.valueOf(Process.myTid()));
            if (this.f437b != null) {
                C0216ah.m554a(this.f439d, this.f437b, this.f438c);
                return;
            } else if (!this.f439d.m561b(1)) {
                C0221am.m598d("[UploadManager] failed to execute one upload task (pid=%d | tid=%d)", Integer.valueOf(Process.myPid()), Integer.valueOf(Process.myTid()));
            } else {
                return;
            }
        }
        this.f439d.mo372a(false);
        boolean unused5 = C0216ah.m568d(this.f436a);
        synchronized (this.f439d.f434o) {
            boolean unused6 = this.f439d.f433n = false;
        }
    }
}
