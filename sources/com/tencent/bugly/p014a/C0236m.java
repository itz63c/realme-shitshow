package com.tencent.bugly.p014a;

import com.coloros.neton.BuildConfig;
import java.util.HashMap;
import java.util.Map;

/* renamed from: com.tencent.bugly.a.m */
/* compiled from: BUGLY */
public final class C0236m extends C0209aa {

    /* renamed from: i */
    private static byte[] f552i;

    /* renamed from: j */
    private static Map<String, String> f553j = new HashMap();

    /* renamed from: a */
    public byte f554a = 0;

    /* renamed from: b */
    public int f555b = 0;

    /* renamed from: c */
    public byte[] f556c = null;

    /* renamed from: d */
    public String f557d = BuildConfig.FLAVOR;

    /* renamed from: e */
    public long f558e = 0;

    /* renamed from: f */
    public String f559f = BuildConfig.FLAVOR;

    /* renamed from: g */
    public String f560g = BuildConfig.FLAVOR;

    /* renamed from: h */
    public Map<String, String> f561h = null;

    /* renamed from: a */
    public final void mo353a(C0249z zVar) {
        zVar.mo412a(this.f554a, 0);
        zVar.mo413a(this.f555b, 1);
        if (this.f556c != null) {
            zVar.mo422a(this.f556c, 2);
        }
        if (this.f557d != null) {
            zVar.mo417a(this.f557d, 3);
        }
        zVar.mo414a(this.f558e, 4);
        if (this.f559f != null) {
            zVar.mo417a(this.f559f, 5);
        }
        if (this.f560g != null) {
            zVar.mo417a(this.f560g, 6);
        }
        if (this.f561h != null) {
            zVar.mo419a(this.f561h, 7);
        }
    }

    static {
        byte[] bArr = new byte[1];
        f552i = bArr;
        bArr[0] = 0;
        f553j.put(BuildConfig.FLAVOR, BuildConfig.FLAVOR);
    }

    /* renamed from: a */
    public final void mo352a(C0247x xVar) {
        this.f554a = xVar.mo398a(this.f554a, 0, true);
        this.f555b = xVar.mo399a(this.f555b, 1, true);
        this.f556c = xVar.mo409c(2, false);
        this.f557d = xVar.mo408b(3, false);
        this.f558e = xVar.mo401a(this.f558e, 4, false);
        this.f559f = xVar.mo408b(5, false);
        this.f560g = xVar.mo408b(6, false);
        this.f561h = (Map) xVar.mo403a(f553j, 7, false);
    }
}
