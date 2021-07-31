package com.p000a.p001a.p003b.p004a;

import com.p000a.p001a.C0006ab;
import com.p000a.p001a.C0010af;
import com.p000a.p001a.p006d.C0111a;
import com.p000a.p001a.p006d.C0115e;
import com.p000a.p001a.p006d.C0116f;

/* renamed from: com.a.a.b.a.ac */
/* compiled from: TypeAdapters */
final class C0017ac extends C0010af<Character> {
    C0017ac() {
    }

    /* renamed from: a */
    public final /* synthetic */ void mo10a(C0116f fVar, Object obj) {
        String valueOf;
        Character ch = (Character) obj;
        if (ch == null) {
            valueOf = null;
        } else {
            valueOf = String.valueOf(ch);
        }
        fVar.mo41b(valueOf);
    }

    /* renamed from: a */
    public final /* synthetic */ Object mo9a(C0111a aVar) {
        if (aVar.mo22f() == C0115e.NULL) {
            aVar.mo26j();
            return null;
        }
        String h = aVar.mo24h();
        if (h.length() == 1) {
            return Character.valueOf(h.charAt(0));
        }
        throw new C0006ab("Expecting character, got: " + h);
    }
}
