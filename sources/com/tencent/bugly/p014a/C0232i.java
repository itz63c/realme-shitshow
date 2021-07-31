package com.tencent.bugly.p014a;

import com.coloros.neton.BuildConfig;

/* renamed from: com.tencent.bugly.a.i */
/* compiled from: BUGLY */
public final class C0232i extends C0209aa implements Cloneable {

    /* renamed from: d */
    private static byte[] f491d;

    /* renamed from: a */
    private byte f492a = 0;

    /* renamed from: b */
    private String f493b = BuildConfig.FLAVOR;

    /* renamed from: c */
    private byte[] f494c = null;

    public C0232i() {
    }

    public C0232i(byte b, String str, byte[] bArr) {
        this.f492a = b;
        this.f493b = str;
        this.f494c = bArr;
    }

    /* renamed from: a */
    public final void mo353a(C0249z zVar) {
        zVar.mo412a(this.f492a, 0);
        zVar.mo417a(this.f493b, 1);
        if (this.f494c != null) {
            zVar.mo422a(this.f494c, 2);
        }
    }

    /* renamed from: a */
    public final void mo352a(C0247x xVar) {
        this.f492a = xVar.mo398a(this.f492a, 0, true);
        this.f493b = xVar.mo408b(1, true);
        if (f491d == null) {
            byte[] bArr = new byte[1];
            f491d = bArr;
            bArr[0] = 0;
        }
        this.f494c = xVar.mo409c(2, false);
    }

    /* renamed from: a */
    public final void mo354a(StringBuilder sb, int i) {
    }
}
