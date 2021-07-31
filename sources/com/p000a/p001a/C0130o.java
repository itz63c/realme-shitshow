package com.p000a.p001a;

import com.p000a.p001a.p006d.C0111a;
import com.p000a.p001a.p006d.C0115e;
import com.p000a.p001a.p006d.C0116f;

/* renamed from: com.a.a.o */
/* compiled from: Gson */
final class C0130o extends C0010af<Number> {

    /* renamed from: a */
    final /* synthetic */ C0125j f257a;

    C0130o(C0125j jVar) {
        this.f257a = jVar;
    }

    /* renamed from: a */
    public final /* synthetic */ void mo10a(C0116f fVar, Object obj) {
        Number number = (Number) obj;
        if (number == null) {
            fVar.mo46f();
            return;
        }
        C0125j.m287a((double) number.floatValue());
        fVar.mo36a(number);
    }

    /* renamed from: a */
    public final /* synthetic */ Object mo9a(C0111a aVar) {
        if (aVar.mo22f() != C0115e.NULL) {
            return Float.valueOf((float) aVar.mo27k());
        }
        aVar.mo26j();
        return null;
    }
}
