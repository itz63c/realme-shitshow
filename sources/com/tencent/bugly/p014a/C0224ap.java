package com.tencent.bugly.p014a;

import com.coloros.neton.BuildConfig;
import java.io.File;
import java.io.FileOutputStream;

/* renamed from: com.tencent.bugly.a.ap */
/* compiled from: BUGLY */
public final class C0224ap {
    /* access modifiers changed from: private */

    /* renamed from: a */
    public boolean f477a;
    /* access modifiers changed from: private */

    /* renamed from: b */
    public File f478b;

    /* renamed from: c */
    private String f479c;

    /* renamed from: d */
    private long f480d;
    /* access modifiers changed from: private */

    /* renamed from: e */
    public long f481e = 30720;

    public C0224ap(String str) {
        if (str != null && !str.equals(BuildConfig.FLAVOR)) {
            this.f479c = str;
            this.f477a = m610a();
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: a */
    public synchronized boolean m610a() {
        boolean z = false;
        synchronized (this) {
            try {
                this.f478b = new File(this.f479c);
                if (!this.f478b.exists() || this.f478b.delete()) {
                    if (!this.f478b.createNewFile()) {
                        this.f477a = false;
                    }
                    z = true;
                } else {
                    this.f477a = false;
                }
            } catch (Throwable th) {
                this.f477a = false;
            }
        }
        return z;
    }

    /* renamed from: a */
    public final synchronized boolean mo386a(String str) {
        boolean z = false;
        synchronized (this) {
            if (this.f477a) {
                try {
                    FileOutputStream fileOutputStream = new FileOutputStream(this.f478b, true);
                    byte[] bytes = str.getBytes("UTF-8");
                    fileOutputStream.write(bytes);
                    fileOutputStream.flush();
                    fileOutputStream.close();
                    this.f480d = ((long) bytes.length) + this.f480d;
                    z = true;
                } catch (Throwable th) {
                    this.f477a = false;
                }
            }
        }
        return z;
    }
}
