package com.tencent.bugly.p014a;

import com.coloros.neton.BuildConfig;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/* renamed from: com.tencent.bugly.a.q */
/* compiled from: BUGLY */
public final class C0240q extends C0209aa implements Cloneable {

    /* renamed from: f */
    private static ArrayList<C0239p> f585f;

    /* renamed from: g */
    private static Map<String, String> f586g;

    /* renamed from: a */
    public byte f587a = 0;

    /* renamed from: b */
    public String f588b = BuildConfig.FLAVOR;

    /* renamed from: c */
    public String f589c = BuildConfig.FLAVOR;

    /* renamed from: d */
    public ArrayList<C0239p> f590d = null;

    /* renamed from: e */
    public Map<String, String> f591e = null;

    /* renamed from: a */
    public final void mo353a(C0249z zVar) {
        zVar.mo412a(this.f587a, 0);
        if (this.f588b != null) {
            zVar.mo417a(this.f588b, 1);
        }
        if (this.f589c != null) {
            zVar.mo417a(this.f589c, 2);
        }
        if (this.f590d != null) {
            zVar.mo418a(this.f590d, 3);
        }
        if (this.f591e != null) {
            zVar.mo419a(this.f591e, 4);
        }
    }

    /* renamed from: a */
    public final void mo352a(C0247x xVar) {
        this.f587a = xVar.mo398a(this.f587a, 0, true);
        this.f588b = xVar.mo408b(1, false);
        this.f589c = xVar.mo408b(2, false);
        if (f585f == null) {
            f585f = new ArrayList<>();
            f585f.add(new C0239p());
        }
        this.f590d = (ArrayList) xVar.mo403a(f585f, 3, false);
        if (f586g == null) {
            f586g = new HashMap();
            f586g.put(BuildConfig.FLAVOR, BuildConfig.FLAVOR);
        }
        this.f591e = (Map) xVar.mo403a(f586g, 4, false);
    }

    /* renamed from: a */
    public final void mo354a(StringBuilder sb, int i) {
    }
}
