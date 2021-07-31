package com.p000a.p001a.p003b;

import com.p000a.p001a.C0010af;
import com.p000a.p001a.C0011ag;
import com.p000a.p001a.C0125j;
import com.p000a.p001a.p005c.C0109a;
import com.p000a.p001a.p006d.C0111a;
import com.p000a.p001a.p006d.C0116f;

/* renamed from: com.a.a.b.p */
/* compiled from: Excluder */
final class C0097p extends C0010af<T> {

    /* renamed from: a */
    final /* synthetic */ boolean f160a;

    /* renamed from: b */
    final /* synthetic */ boolean f161b;

    /* renamed from: c */
    final /* synthetic */ C0125j f162c;

    /* renamed from: d */
    final /* synthetic */ C0109a f163d;

    /* renamed from: e */
    final /* synthetic */ C0096o f164e;

    /* renamed from: f */
    private C0010af<T> f165f;

    C0097p(C0096o oVar, boolean z, boolean z2, C0125j jVar, C0109a aVar) {
        this.f164e = oVar;
        this.f160a = z;
        this.f161b = z2;
        this.f162c = jVar;
        this.f163d = aVar;
    }

    /* renamed from: a */
    public final T mo9a(C0111a aVar) {
        if (!this.f160a) {
            return m190a().mo9a(aVar);
        }
        aVar.mo30n();
        return null;
    }

    /* renamed from: a */
    public final void mo10a(C0116f fVar, T t) {
        if (this.f161b) {
            fVar.mo46f();
        } else {
            m190a().mo10a(fVar, t);
        }
    }

    /* renamed from: a */
    private C0010af<T> m190a() {
        C0010af<T> afVar = this.f165f;
        if (afVar != null) {
            return afVar;
        }
        C0010af<T> a = this.f162c.mo140a((C0011ag) this.f164e, this.f163d);
        this.f165f = a;
        return a;
    }
}
