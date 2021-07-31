package com.tencent.bugly.p014a;

import com.coloros.neton.BuildConfig;
import com.tencent.bugly.crashreport.crash.p015a.C0262b;
import java.util.HashMap;
import java.util.Map;

/* renamed from: com.tencent.bugly.a.v */
/* compiled from: BUGLY */
public final class C0245v extends C0209aa {

    /* renamed from: k */
    private static byte[] f599k = null;

    /* renamed from: l */
    private static Map<String, String> f600l = null;

    /* renamed from: m */
    private static /* synthetic */ boolean f601m;

    /* renamed from: a */
    public short f602a = 0;

    /* renamed from: b */
    public int f603b = 0;

    /* renamed from: c */
    public String f604c = null;

    /* renamed from: d */
    public String f605d = null;

    /* renamed from: e */
    public byte[] f606e;

    /* renamed from: f */
    private byte f607f = 0;

    /* renamed from: g */
    private int f608g = 0;

    /* renamed from: h */
    private int f609h = 0;

    /* renamed from: i */
    private Map<String, String> f610i;

    /* renamed from: j */
    private Map<String, String> f611j;

    static {
        boolean z;
        if (!C0245v.class.desiredAssertionStatus()) {
            z = true;
        } else {
            z = false;
        }
        f601m = z;
    }

    public final boolean equals(Object obj) {
        C0245v vVar = (C0245v) obj;
        if (!C0210ab.m518a((int) vVar.f602a) || !C0210ab.m518a((int) vVar.f607f) || !C0210ab.m518a(vVar.f608g) || !C0210ab.m518a(vVar.f603b) || !C0210ab.m519a(1, vVar.f604c) || !C0210ab.m519a(1, vVar.f605d) || !C0210ab.m519a(1, vVar.f606e) || !C0210ab.m518a(vVar.f609h) || !C0210ab.m519a(1, vVar.f610i) || !C0210ab.m519a(1, vVar.f611j)) {
            return false;
        }
        return true;
    }

    public final Object clone() {
        try {
            return super.clone();
        } catch (CloneNotSupportedException e) {
            if (f601m) {
                return null;
            }
            throw new AssertionError();
        }
    }

    /* renamed from: a */
    public final void mo353a(C0249z zVar) {
        zVar.mo420a(this.f602a, 1);
        zVar.mo412a(this.f607f, 2);
        zVar.mo413a(this.f608g, 3);
        zVar.mo413a(this.f603b, 4);
        zVar.mo417a(this.f604c, 5);
        zVar.mo417a(this.f605d, 6);
        zVar.mo422a(this.f606e, 7);
        zVar.mo413a(this.f609h, 8);
        zVar.mo419a(this.f610i, 9);
        zVar.mo419a(this.f611j, 10);
    }

    /* renamed from: a */
    public final void mo352a(C0247x xVar) {
        try {
            this.f602a = xVar.mo405a(this.f602a, 1, true);
            this.f607f = xVar.mo398a(this.f607f, 2, true);
            this.f608g = xVar.mo399a(this.f608g, 3, true);
            this.f603b = xVar.mo399a(this.f603b, 4, true);
            this.f604c = xVar.mo408b(5, true);
            this.f605d = xVar.mo408b(6, true);
            if (f599k == null) {
                f599k = new byte[]{0};
            }
            this.f606e = xVar.mo409c(7, true);
            this.f609h = xVar.mo399a(this.f609h, 8, true);
            if (f600l == null) {
                HashMap hashMap = new HashMap();
                f600l = hashMap;
                hashMap.put(BuildConfig.FLAVOR, BuildConfig.FLAVOR);
            }
            this.f610i = (Map) xVar.mo403a(f600l, 9, true);
            if (f600l == null) {
                HashMap hashMap2 = new HashMap();
                f600l = hashMap2;
                hashMap2.put(BuildConfig.FLAVOR, BuildConfig.FLAVOR);
            }
            this.f611j = (Map) xVar.mo403a(f600l, 10, true);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("RequestPacket decode error " + C0244u.m665a(this.f606e));
            throw new RuntimeException(e);
        }
    }

    /* renamed from: a */
    public final void mo354a(StringBuilder sb, int i) {
        C0262b bVar = new C0262b(sb, i);
        bVar.mo469a(this.f602a, "iVersion");
        bVar.mo465a(this.f607f, "cPacketType");
        bVar.mo466a(this.f608g, "iMessageType");
        bVar.mo466a(this.f603b, "iRequestId");
        bVar.mo467a(this.f604c, "sServantName");
        bVar.mo467a(this.f605d, "sFuncName");
        bVar.mo470a(this.f606e, "sBuffer");
        bVar.mo466a(this.f609h, "iTimeout");
        bVar.mo468a(this.f610i, "context");
        bVar.mo468a(this.f611j, "status");
    }
}
