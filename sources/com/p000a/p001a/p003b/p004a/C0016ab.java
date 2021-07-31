package com.p000a.p001a.p003b.p004a;

import com.p000a.p001a.C0006ab;
import com.p000a.p001a.C0010af;
import com.p000a.p001a.p003b.C0099r;
import com.p000a.p001a.p006d.C0111a;
import com.p000a.p001a.p006d.C0115e;
import com.p000a.p001a.p006d.C0116f;
import neton.internal.platform.Platform;

/* renamed from: com.a.a.b.a.ab */
/* compiled from: TypeAdapters */
final class C0016ab extends C0010af<Number> {
    C0016ab() {
    }

    /* renamed from: a */
    public final /* bridge */ /* synthetic */ void mo10a(C0116f fVar, Object obj) {
        fVar.mo36a((Number) obj);
    }

    /* renamed from: a */
    public final /* synthetic */ Object mo9a(C0111a aVar) {
        C0115e f = aVar.mo22f();
        switch (C0039ay.f19a[f.ordinal()]) {
            case 1:
                return new C0099r(aVar.mo24h());
            case Platform.INFO /*4*/:
                aVar.mo26j();
                return null;
            default:
                throw new C0006ab("Expecting number, got: " + f);
        }
    }
}
