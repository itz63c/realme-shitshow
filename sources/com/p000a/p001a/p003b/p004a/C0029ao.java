package com.p000a.p001a.p003b.p004a;

import com.p000a.p001a.C0010af;
import com.p000a.p001a.p006d.C0111a;
import com.p000a.p001a.p006d.C0116f;
import java.sql.Timestamp;
import java.util.Date;

/* renamed from: com.a.a.b.a.ao */
/* compiled from: TypeAdapters */
final class C0029ao extends C0010af<Timestamp> {

    /* renamed from: a */
    final /* synthetic */ C0010af f7a;

    /* renamed from: b */
    final /* synthetic */ C0028an f8b;

    C0029ao(C0028an anVar, C0010af afVar) {
        this.f8b = anVar;
        this.f7a = afVar;
    }

    /* renamed from: a */
    public final /* bridge */ /* synthetic */ void mo10a(C0116f fVar, Object obj) {
        this.f7a.mo10a(fVar, (Timestamp) obj);
    }

    /* renamed from: a */
    public final /* synthetic */ Object mo9a(C0111a aVar) {
        Date date = (Date) this.f7a.mo9a(aVar);
        if (date != null) {
            return new Timestamp(date.getTime());
        }
        return null;
    }
}
