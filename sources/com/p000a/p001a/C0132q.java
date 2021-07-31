package com.p000a.p001a;

import com.p000a.p001a.p006d.C0111a;
import com.p000a.p001a.p006d.C0116f;

/* renamed from: com.a.a.q */
/* compiled from: Gson */
final class C0132q<T> extends C0010af<T> {

    /* renamed from: a */
    private C0010af<T> f259a;

    C0132q() {
    }

    /* renamed from: a */
    public final void mo147a(C0010af<T> afVar) {
        if (this.f259a != null) {
            throw new AssertionError();
        }
        this.f259a = afVar;
    }

    /* renamed from: a */
    public final T mo9a(C0111a aVar) {
        if (this.f259a != null) {
            return this.f259a.mo9a(aVar);
        }
        throw new IllegalStateException();
    }

    /* renamed from: a */
    public final void mo10a(C0116f fVar, T t) {
        if (this.f259a == null) {
            throw new IllegalStateException();
        }
        this.f259a.mo10a(fVar, t);
    }
}
