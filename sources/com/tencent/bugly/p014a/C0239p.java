package com.tencent.bugly.p014a;

import com.coloros.neton.BuildConfig;
import java.util.HashMap;
import java.util.Map;

/* renamed from: com.tencent.bugly.a.p */
/* compiled from: BUGLY */
public final class C0239p extends C0209aa {

    /* renamed from: i */
    private static Map<String, String> f576i = new HashMap();

    /* renamed from: a */
    public long f577a = 0;

    /* renamed from: b */
    public byte f578b = 0;

    /* renamed from: c */
    public String f579c = BuildConfig.FLAVOR;

    /* renamed from: d */
    public String f580d = BuildConfig.FLAVOR;

    /* renamed from: e */
    public String f581e = BuildConfig.FLAVOR;

    /* renamed from: f */
    public Map<String, String> f582f = null;

    /* renamed from: g */
    public String f583g = BuildConfig.FLAVOR;

    /* renamed from: h */
    public boolean f584h = true;

    /* renamed from: a */
    public final void mo353a(C0249z zVar) {
        zVar.mo414a(this.f577a, 0);
        zVar.mo412a(this.f578b, 1);
        if (this.f579c != null) {
            zVar.mo417a(this.f579c, 2);
        }
        if (this.f580d != null) {
            zVar.mo417a(this.f580d, 3);
        }
        if (this.f581e != null) {
            zVar.mo417a(this.f581e, 4);
        }
        if (this.f582f != null) {
            zVar.mo419a(this.f582f, 5);
        }
        if (this.f583g != null) {
            zVar.mo417a(this.f583g, 6);
        }
        zVar.mo421a(this.f584h, 7);
    }

    static {
        f576i.put(BuildConfig.FLAVOR, BuildConfig.FLAVOR);
    }

    /* renamed from: a */
    public final void mo352a(C0247x xVar) {
        this.f577a = xVar.mo401a(this.f577a, 0, true);
        this.f578b = xVar.mo398a(this.f578b, 1, true);
        this.f579c = xVar.mo408b(2, false);
        this.f580d = xVar.mo408b(3, false);
        this.f581e = xVar.mo408b(4, false);
        this.f582f = (Map) xVar.mo403a(f576i, 5, false);
        this.f583g = xVar.mo408b(6, false);
        this.f584h = xVar.mo407a(7, false);
    }
}
