package com.tencent.bugly.p014a;

import com.coloros.neton.BuildConfig;
import java.util.ArrayList;

/* renamed from: com.tencent.bugly.a.h */
/* compiled from: BUGLY */
public final class C0231h extends C0209aa implements Cloneable {

    /* renamed from: c */
    private static ArrayList<String> f488c;

    /* renamed from: a */
    private String f489a = BuildConfig.FLAVOR;

    /* renamed from: b */
    private ArrayList<String> f490b = null;

    /* renamed from: a */
    public final void mo353a(C0249z zVar) {
        zVar.mo417a(this.f489a, 0);
        if (this.f490b != null) {
            zVar.mo418a(this.f490b, 1);
        }
    }

    /* renamed from: a */
    public final void mo352a(C0247x xVar) {
        this.f489a = xVar.mo408b(0, true);
        if (f488c == null) {
            f488c = new ArrayList<>();
            f488c.add(BuildConfig.FLAVOR);
        }
        this.f490b = (ArrayList) xVar.mo403a(f488c, 1, false);
    }

    /* renamed from: a */
    public final void mo354a(StringBuilder sb, int i) {
    }
}
