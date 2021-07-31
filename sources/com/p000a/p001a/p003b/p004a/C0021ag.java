package com.p000a.p001a.p003b.p004a;

import com.p000a.p001a.C0010af;
import com.p000a.p001a.p006d.C0111a;
import com.p000a.p001a.p006d.C0115e;
import com.p000a.p001a.p006d.C0116f;

/* renamed from: com.a.a.b.a.ag */
/* compiled from: TypeAdapters */
final class C0021ag extends C0010af<StringBuilder> {
    C0021ag() {
    }

    /* renamed from: a */
    public final /* synthetic */ void mo10a(C0116f fVar, Object obj) {
        String sb;
        StringBuilder sb2 = (StringBuilder) obj;
        if (sb2 == null) {
            sb = null;
        } else {
            sb = sb2.toString();
        }
        fVar.mo41b(sb);
    }

    /* renamed from: a */
    public final /* synthetic */ Object mo9a(C0111a aVar) {
        if (aVar.mo22f() != C0115e.NULL) {
            return new StringBuilder(aVar.mo24h());
        }
        aVar.mo26j();
        return null;
    }
}
