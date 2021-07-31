package com.p000a.p001a.p003b.p004a;

import com.p000a.p001a.C0006ab;
import com.p000a.p001a.C0010af;
import com.p000a.p001a.C0011ag;
import com.p000a.p001a.p006d.C0111a;
import com.p000a.p001a.p006d.C0115e;
import com.p000a.p001a.p006d.C0116f;
import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/* renamed from: com.a.a.b.a.t */
/* compiled from: SqlDateTypeAdapter */
public final class C0065t extends C0010af<Date> {

    /* renamed from: a */
    public static final C0011ag f62a = new C0066u();

    /* renamed from: b */
    private final DateFormat f63b = new SimpleDateFormat("MMM d, yyyy");

    /* access modifiers changed from: private */
    /* renamed from: b */
    public synchronized Date mo9a(C0111a aVar) {
        Date date;
        if (aVar.mo22f() == C0115e.NULL) {
            aVar.mo26j();
            date = null;
        } else {
            try {
                date = new Date(this.f63b.parse(aVar.mo24h()).getTime());
            } catch (ParseException e) {
                throw new C0006ab((Throwable) e);
            }
        }
        return date;
    }

    /* access modifiers changed from: private */
    /* renamed from: a */
    public synchronized void mo10a(C0116f fVar, Date date) {
        fVar.mo41b(date == null ? null : this.f63b.format(date));
    }
}
