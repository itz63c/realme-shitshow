package com.tencent.bugly.crashreport.crash;

/* renamed from: com.tencent.bugly.crashreport.crash.c */
/* compiled from: BUGLY */
public final class C0264c implements Comparable<C0264c> {

    /* renamed from: a */
    public long f795a = -1;

    /* renamed from: b */
    public long f796b = -1;

    /* renamed from: c */
    public String f797c = null;

    /* renamed from: d */
    public boolean f798d = false;

    /* renamed from: e */
    public boolean f799e = false;

    /* renamed from: f */
    public int f800f = 0;

    public final /* bridge */ /* synthetic */ int compareTo(Object obj) {
        C0264c cVar = (C0264c) obj;
        if (cVar != null) {
            long j = this.f796b - cVar.f796b;
            if (j <= 0) {
                return j < 0 ? -1 : 0;
            }
        }
        return 1;
    }
}
