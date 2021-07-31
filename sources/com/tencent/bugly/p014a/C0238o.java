package com.tencent.bugly.p014a;

import com.coloros.neton.BuildConfig;
import java.util.HashMap;
import java.util.Map;

/* renamed from: com.tencent.bugly.a.o */
/* compiled from: BUGLY */
public final class C0238o extends C0209aa {

    /* renamed from: k */
    private static C0237n f564k = new C0237n();

    /* renamed from: l */
    private static Map<String, String> f565l = new HashMap();

    /* renamed from: a */
    public boolean f566a = true;

    /* renamed from: b */
    public boolean f567b = true;

    /* renamed from: c */
    public boolean f568c = true;

    /* renamed from: d */
    public String f569d = BuildConfig.FLAVOR;

    /* renamed from: e */
    public String f570e = BuildConfig.FLAVOR;

    /* renamed from: f */
    public C0237n f571f = null;

    /* renamed from: g */
    public Map<String, String> f572g = null;

    /* renamed from: h */
    public long f573h = 0;

    /* renamed from: i */
    private String f574i = BuildConfig.FLAVOR;

    /* renamed from: j */
    private String f575j = BuildConfig.FLAVOR;

    /* renamed from: a */
    public final void mo353a(C0249z zVar) {
        zVar.mo421a(this.f566a, 0);
        zVar.mo421a(this.f567b, 1);
        zVar.mo421a(this.f568c, 2);
        if (this.f569d != null) {
            zVar.mo417a(this.f569d, 3);
        }
        if (this.f570e != null) {
            zVar.mo417a(this.f570e, 4);
        }
        if (this.f571f != null) {
            zVar.mo415a((C0209aa) this.f571f, 5);
        }
        if (this.f572g != null) {
            zVar.mo419a(this.f572g, 6);
        }
        zVar.mo414a(this.f573h, 7);
        if (this.f574i != null) {
            zVar.mo417a(this.f574i, 8);
        }
        if (this.f575j != null) {
            zVar.mo417a(this.f575j, 9);
        }
    }

    static {
        f565l.put(BuildConfig.FLAVOR, BuildConfig.FLAVOR);
    }

    /* renamed from: a */
    public final void mo352a(C0247x xVar) {
        this.f566a = xVar.mo407a(0, true);
        this.f567b = xVar.mo407a(1, true);
        this.f568c = xVar.mo407a(2, true);
        this.f569d = xVar.mo408b(3, false);
        this.f570e = xVar.mo408b(4, false);
        this.f571f = (C0237n) xVar.mo402a((C0209aa) f564k, 5, false);
        this.f572g = (Map) xVar.mo403a(f565l, 6, false);
        this.f573h = xVar.mo401a(this.f573h, 7, false);
        this.f574i = xVar.mo408b(8, false);
        this.f575j = xVar.mo408b(9, false);
    }
}
