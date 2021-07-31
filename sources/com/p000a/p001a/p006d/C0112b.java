package com.p000a.p001a.p006d;

import com.p000a.p001a.p003b.C0098q;
import com.p000a.p001a.p003b.p004a.C0052g;

/* renamed from: com.a.a.d.b */
/* compiled from: JsonReader */
final class C0112b extends C0098q {
    C0112b() {
    }

    /* renamed from: a */
    public final void mo95a(C0111a aVar) {
        if (aVar instanceof C0052g) {
            ((C0052g) aVar).mo31o();
            return;
        }
        aVar.mo22f();
        if (aVar.f204l != C0115e.NAME) {
            throw new IllegalStateException("Expected a name but was " + aVar.mo22f() + "  at line " + aVar.m233r() + " column " + aVar.m234s());
        }
        String unused = aVar.f206n = aVar.f205m;
        String unused2 = aVar.f205m = null;
        C0115e unused3 = aVar.f204l = C0115e.STRING;
    }
}
