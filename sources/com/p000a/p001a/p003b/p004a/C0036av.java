package com.p000a.p001a.p003b.p004a;

import com.p000a.p001a.C0010af;
import com.p000a.p001a.p006d.C0111a;
import com.p000a.p001a.p006d.C0115e;
import com.p000a.p001a.p006d.C0116f;

/* renamed from: com.a.a.b.a.av */
/* compiled from: TypeAdapters */
final class C0036av extends C0010af<Boolean> {
    C0036av() {
    }

    /* renamed from: a */
    public final /* synthetic */ void mo10a(C0116f fVar, Object obj) {
        Boolean bool = (Boolean) obj;
        if (bool == null) {
            fVar.mo46f();
        } else {
            fVar.mo38a(bool.booleanValue());
        }
    }

    /* renamed from: a */
    public final /* synthetic */ Object mo9a(C0111a aVar) {
        if (aVar.mo22f() == C0115e.NULL) {
            aVar.mo26j();
            return null;
        } else if (aVar.mo22f() == C0115e.STRING) {
            return Boolean.valueOf(Boolean.parseBoolean(aVar.mo24h()));
        } else {
            return Boolean.valueOf(aVar.mo25i());
        }
    }
}
