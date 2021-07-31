package com.p000a.p001a.p003b.p004a;

import com.p000a.p001a.C0006ab;
import com.p000a.p001a.C0010af;
import com.p000a.p001a.C0011ag;
import com.p000a.p001a.p006d.C0111a;
import com.p000a.p001a.p006d.C0115e;
import com.p000a.p001a.p006d.C0116f;
import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/* renamed from: com.a.a.b.a.v */
/* compiled from: TimeTypeAdapter */
public final class C0067v extends C0010af<Time> {

    /* renamed from: a */
    public static final C0011ag f64a = new C0068w();

    /* renamed from: b */
    private final DateFormat f65b = new SimpleDateFormat("hh:mm:ss a");

    /* access modifiers changed from: private */
    /* renamed from: b */
    public synchronized Time mo9a(C0111a aVar) {
        Time time;
        if (aVar.mo22f() == C0115e.NULL) {
            aVar.mo26j();
            time = null;
        } else {
            try {
                time = new Time(this.f65b.parse(aVar.mo24h()).getTime());
            } catch (ParseException e) {
                throw new C0006ab((Throwable) e);
            }
        }
        return time;
    }

    /* access modifiers changed from: private */
    /* renamed from: a */
    public synchronized void mo10a(C0116f fVar, Time time) {
        fVar.mo41b(time == null ? null : this.f65b.format(time));
    }
}
