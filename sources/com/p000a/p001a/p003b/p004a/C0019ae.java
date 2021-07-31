package com.p000a.p001a.p003b.p004a;

import com.p000a.p001a.C0006ab;
import com.p000a.p001a.C0010af;
import com.p000a.p001a.p006d.C0111a;
import com.p000a.p001a.p006d.C0115e;
import com.p000a.p001a.p006d.C0116f;
import java.math.BigDecimal;

/* renamed from: com.a.a.b.a.ae */
/* compiled from: TypeAdapters */
final class C0019ae extends C0010af<BigDecimal> {
    C0019ae() {
    }

    /* renamed from: a */
    public final /* synthetic */ Object mo9a(C0111a aVar) {
        return m23b(aVar);
    }

    /* renamed from: a */
    public final /* bridge */ /* synthetic */ void mo10a(C0116f fVar, Object obj) {
        fVar.mo36a((Number) (BigDecimal) obj);
    }

    /* renamed from: b */
    private static BigDecimal m23b(C0111a aVar) {
        if (aVar.mo22f() == C0115e.NULL) {
            aVar.mo26j();
            return null;
        }
        try {
            return new BigDecimal(aVar.mo24h());
        } catch (NumberFormatException e) {
            throw new C0006ab((Throwable) e);
        }
    }
}
