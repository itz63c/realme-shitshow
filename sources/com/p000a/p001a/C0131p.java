package com.p000a.p001a;

import com.p000a.p001a.p006d.C0111a;
import com.p000a.p001a.p006d.C0115e;
import com.p000a.p001a.p006d.C0116f;

/* renamed from: com.a.a.p */
/* compiled from: Gson */
final class C0131p extends C0010af<Number> {

    /* renamed from: a */
    final /* synthetic */ C0125j f258a;

    C0131p(C0125j jVar) {
        this.f258a = jVar;
    }

    /* renamed from: a */
    public final /* synthetic */ void mo10a(C0116f fVar, Object obj) {
        Number number = (Number) obj;
        if (number == null) {
            fVar.mo46f();
        } else {
            fVar.mo41b(number.toString());
        }
    }

    /* renamed from: a */
    public final /* synthetic */ Object mo9a(C0111a aVar) {
        if (aVar.mo22f() != C0115e.NULL) {
            return Long.valueOf(aVar.mo28l());
        }
        aVar.mo26j();
        return null;
    }
}
