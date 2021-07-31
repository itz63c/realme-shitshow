package com.p000a.p001a.p003b.p004a;

import com.p000a.p001a.C0010af;
import com.p000a.p001a.p006d.C0111a;
import com.p000a.p001a.p006d.C0115e;
import com.p000a.p001a.p006d.C0116f;

/* renamed from: com.a.a.b.a.ad */
/* compiled from: TypeAdapters */
final class C0018ad extends C0010af<String> {
    C0018ad() {
    }

    /* renamed from: a */
    public final /* synthetic */ void mo10a(C0116f fVar, Object obj) {
        fVar.mo41b((String) obj);
    }

    /* renamed from: a */
    public final /* synthetic */ Object mo9a(C0111a aVar) {
        C0115e f = aVar.mo22f();
        if (f == C0115e.NULL) {
            aVar.mo26j();
            return null;
        } else if (f == C0115e.BOOLEAN) {
            return Boolean.toString(aVar.mo25i());
        } else {
            return aVar.mo24h();
        }
    }
}
