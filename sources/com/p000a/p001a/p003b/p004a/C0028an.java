package com.p000a.p001a.p003b.p004a;

import com.p000a.p001a.C0010af;
import com.p000a.p001a.C0011ag;
import com.p000a.p001a.C0125j;
import com.p000a.p001a.p005c.C0109a;
import java.sql.Timestamp;
import java.util.Date;

/* renamed from: com.a.a.b.a.an */
/* compiled from: TypeAdapters */
final class C0028an implements C0011ag {
    C0028an() {
    }

    /* renamed from: a */
    public final <T> C0010af<T> mo11a(C0125j jVar, C0109a<T> aVar) {
        if (aVar.mo124a() != Timestamp.class) {
            return null;
        }
        return new C0029ao(this, jVar.mo142a(Date.class));
    }
}
