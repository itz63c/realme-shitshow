package com.p000a.p001a.p003b.p004a;

import com.p000a.p001a.C0010af;
import com.p000a.p001a.C0011ag;
import com.p000a.p001a.C0125j;
import com.p000a.p001a.p005c.C0109a;
import java.sql.Date;

/* renamed from: com.a.a.b.a.u */
/* compiled from: SqlDateTypeAdapter */
final class C0066u implements C0011ag {
    C0066u() {
    }

    /* renamed from: a */
    public final <T> C0010af<T> mo11a(C0125j jVar, C0109a<T> aVar) {
        if (aVar.mo124a() == Date.class) {
            return new C0065t();
        }
        return null;
    }
}
