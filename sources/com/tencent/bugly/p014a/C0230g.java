package com.tencent.bugly.p014a;

import com.coloros.neton.BuildConfig;

/* renamed from: com.tencent.bugly.a.g */
/* compiled from: BUGLY */
public final class C0230g extends C0209aa implements Cloneable {

    /* renamed from: a */
    public String f483a = BuildConfig.FLAVOR;

    /* renamed from: b */
    public String f484b = BuildConfig.FLAVOR;

    /* renamed from: c */
    public String f485c = BuildConfig.FLAVOR;

    /* renamed from: d */
    public String f486d = BuildConfig.FLAVOR;

    /* renamed from: e */
    private String f487e = BuildConfig.FLAVOR;

    /* renamed from: a */
    public final void mo353a(C0249z zVar) {
        zVar.mo417a(this.f483a, 0);
        if (this.f484b != null) {
            zVar.mo417a(this.f484b, 1);
        }
        if (this.f485c != null) {
            zVar.mo417a(this.f485c, 2);
        }
        if (this.f487e != null) {
            zVar.mo417a(this.f487e, 3);
        }
        if (this.f486d != null) {
            zVar.mo417a(this.f486d, 4);
        }
    }

    /* renamed from: a */
    public final void mo352a(C0247x xVar) {
        this.f483a = xVar.mo408b(0, true);
        this.f484b = xVar.mo408b(1, false);
        this.f485c = xVar.mo408b(2, false);
        this.f487e = xVar.mo408b(3, false);
        this.f486d = xVar.mo408b(4, false);
    }

    /* renamed from: a */
    public final void mo354a(StringBuilder sb, int i) {
    }
}
