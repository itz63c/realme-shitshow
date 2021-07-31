package com.tencent.bugly.p014a;

import com.coloros.neton.BuildConfig;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/* renamed from: com.tencent.bugly.a.j */
/* compiled from: BUGLY */
public final class C0233j extends C0209aa {

    /* renamed from: A */
    private static ArrayList<C0232i> f495A = new ArrayList<>();

    /* renamed from: B */
    private static Map<String, String> f496B = new HashMap();

    /* renamed from: C */
    private static Map<String, String> f497C = new HashMap();

    /* renamed from: v */
    private static Map<String, String> f498v = new HashMap();

    /* renamed from: w */
    private static C0231h f499w = new C0231h();

    /* renamed from: x */
    private static C0230g f500x = new C0230g();

    /* renamed from: y */
    private static ArrayList<C0230g> f501y = new ArrayList<>();

    /* renamed from: z */
    private static ArrayList<C0230g> f502z = new ArrayList<>();

    /* renamed from: a */
    public String f503a = BuildConfig.FLAVOR;

    /* renamed from: b */
    public long f504b = 0;

    /* renamed from: c */
    public String f505c = BuildConfig.FLAVOR;

    /* renamed from: d */
    public String f506d = BuildConfig.FLAVOR;

    /* renamed from: e */
    public String f507e = BuildConfig.FLAVOR;

    /* renamed from: f */
    public String f508f = BuildConfig.FLAVOR;

    /* renamed from: g */
    public String f509g = BuildConfig.FLAVOR;

    /* renamed from: h */
    public Map<String, String> f510h = null;

    /* renamed from: i */
    public String f511i = BuildConfig.FLAVOR;

    /* renamed from: j */
    public C0231h f512j = null;

    /* renamed from: k */
    public int f513k = 0;

    /* renamed from: l */
    public String f514l = BuildConfig.FLAVOR;

    /* renamed from: m */
    public String f515m = BuildConfig.FLAVOR;

    /* renamed from: n */
    public C0230g f516n = null;

    /* renamed from: o */
    public ArrayList<C0230g> f517o = null;

    /* renamed from: p */
    public ArrayList<C0230g> f518p = null;

    /* renamed from: q */
    public ArrayList<C0232i> f519q = null;

    /* renamed from: r */
    public Map<String, String> f520r = null;

    /* renamed from: s */
    public Map<String, String> f521s = null;

    /* renamed from: t */
    public String f522t = BuildConfig.FLAVOR;

    /* renamed from: u */
    private boolean f523u = true;

    /* renamed from: a */
    public final void mo353a(C0249z zVar) {
        zVar.mo417a(this.f503a, 0);
        zVar.mo414a(this.f504b, 1);
        zVar.mo417a(this.f505c, 2);
        if (this.f506d != null) {
            zVar.mo417a(this.f506d, 3);
        }
        if (this.f507e != null) {
            zVar.mo417a(this.f507e, 4);
        }
        if (this.f508f != null) {
            zVar.mo417a(this.f508f, 5);
        }
        if (this.f509g != null) {
            zVar.mo417a(this.f509g, 6);
        }
        if (this.f510h != null) {
            zVar.mo419a(this.f510h, 7);
        }
        if (this.f511i != null) {
            zVar.mo417a(this.f511i, 8);
        }
        if (this.f512j != null) {
            zVar.mo415a((C0209aa) this.f512j, 9);
        }
        zVar.mo413a(this.f513k, 10);
        if (this.f514l != null) {
            zVar.mo417a(this.f514l, 11);
        }
        if (this.f515m != null) {
            zVar.mo417a(this.f515m, 12);
        }
        if (this.f516n != null) {
            zVar.mo415a((C0209aa) this.f516n, 13);
        }
        if (this.f517o != null) {
            zVar.mo418a(this.f517o, 14);
        }
        if (this.f518p != null) {
            zVar.mo418a(this.f518p, 15);
        }
        if (this.f519q != null) {
            zVar.mo418a(this.f519q, 16);
        }
        if (this.f520r != null) {
            zVar.mo419a(this.f520r, 17);
        }
        if (this.f521s != null) {
            zVar.mo419a(this.f521s, 18);
        }
        if (this.f522t != null) {
            zVar.mo417a(this.f522t, 19);
        }
        zVar.mo421a(this.f523u, 20);
    }

    static {
        f498v.put(BuildConfig.FLAVOR, BuildConfig.FLAVOR);
        f501y.add(new C0230g());
        f502z.add(new C0230g());
        f495A.add(new C0232i());
        f496B.put(BuildConfig.FLAVOR, BuildConfig.FLAVOR);
        f497C.put(BuildConfig.FLAVOR, BuildConfig.FLAVOR);
    }

    /* renamed from: a */
    public final void mo352a(C0247x xVar) {
        this.f503a = xVar.mo408b(0, true);
        this.f504b = xVar.mo401a(this.f504b, 1, true);
        this.f505c = xVar.mo408b(2, true);
        this.f506d = xVar.mo408b(3, false);
        this.f507e = xVar.mo408b(4, false);
        this.f508f = xVar.mo408b(5, false);
        this.f509g = xVar.mo408b(6, false);
        this.f510h = (Map) xVar.mo403a(f498v, 7, false);
        this.f511i = xVar.mo408b(8, false);
        this.f512j = (C0231h) xVar.mo402a((C0209aa) f499w, 9, false);
        this.f513k = xVar.mo399a(this.f513k, 10, false);
        this.f514l = xVar.mo408b(11, false);
        this.f515m = xVar.mo408b(12, false);
        this.f516n = (C0230g) xVar.mo402a((C0209aa) f500x, 13, false);
        this.f517o = (ArrayList) xVar.mo403a(f501y, 14, false);
        this.f518p = (ArrayList) xVar.mo403a(f502z, 15, false);
        this.f519q = (ArrayList) xVar.mo403a(f495A, 16, false);
        this.f520r = (Map) xVar.mo403a(f496B, 17, false);
        this.f521s = (Map) xVar.mo403a(f497C, 18, false);
        this.f522t = xVar.mo408b(19, false);
        this.f523u = xVar.mo407a(20, false);
    }
}
