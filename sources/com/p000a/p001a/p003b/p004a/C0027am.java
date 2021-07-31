package com.p000a.p001a.p003b.p004a;

import com.p000a.p001a.C0010af;
import com.p000a.p001a.p006d.C0111a;
import com.p000a.p001a.p006d.C0115e;
import com.p000a.p001a.p006d.C0116f;
import java.util.UUID;

/* renamed from: com.a.a.b.a.am */
/* compiled from: TypeAdapters */
final class C0027am extends C0010af<UUID> {
    C0027am() {
    }

    /* renamed from: a */
    public final /* synthetic */ void mo10a(C0116f fVar, Object obj) {
        String uuid;
        UUID uuid2 = (UUID) obj;
        if (uuid2 == null) {
            uuid = null;
        } else {
            uuid = uuid2.toString();
        }
        fVar.mo41b(uuid);
    }

    /* renamed from: a */
    public final /* synthetic */ Object mo9a(C0111a aVar) {
        if (aVar.mo22f() != C0115e.NULL) {
            return UUID.fromString(aVar.mo24h());
        }
        aVar.mo26j();
        return null;
    }
}
