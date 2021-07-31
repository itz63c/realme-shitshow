package com.tencent.bugly.p014a;

/* renamed from: com.tencent.bugly.a.ao */
/* compiled from: BUGLY */
final class C0223ao implements Runnable {

    /* renamed from: a */
    private /* synthetic */ String f476a;

    C0223ao(String str) {
        this.f476a = str;
    }

    public final void run() {
        synchronized (C0222an.f473j) {
            try {
                if (C0222an.f468e.length() > C0222an.f466c) {
                    if (C0222an.f469f == null) {
                        C0224ap unused = C0222an.f469f = new C0224ap(C0222an.f470g);
                    } else if (C0222an.f469f.f478b.length() + ((long) C0222an.f468e.length()) > C0222an.f469f.f481e) {
                        boolean unused2 = C0222an.f469f.m610a();
                    }
                    if (C0222an.f469f.f477a) {
                        C0222an.f469f.mo386a(C0222an.f468e.toString());
                        C0222an.f468e.setLength(0);
                    } else {
                        C0222an.f468e.setLength(0);
                        C0222an.f468e.append(this.f476a);
                    }
                }
            } catch (Throwable th) {
            }
        }
    }
}
