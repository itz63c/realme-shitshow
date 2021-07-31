package com.tencent.bugly.p014a;

import java.util.ArrayList;

/* renamed from: com.tencent.bugly.a.k */
/* compiled from: BUGLY */
public final class C0234k extends C0209aa implements Cloneable {

    /* renamed from: b */
    private static ArrayList<C0233j> f524b;

    /* renamed from: a */
    public ArrayList<C0233j> f525a = null;

    /* renamed from: a */
    public final void mo353a(C0249z zVar) {
        zVar.mo418a(this.f525a, 0);
    }

    /* renamed from: a */
    public final void mo352a(C0247x xVar) {
        if (f524b == null) {
            f524b = new ArrayList<>();
            f524b.add(new C0233j());
        }
        this.f525a = (ArrayList) xVar.mo403a(f524b, 0, true);
    }

    /* renamed from: a */
    public final void mo354a(StringBuilder sb, int i) {
    }
}
