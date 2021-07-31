package com.p000a.p001a.p003b.p004a;

import com.p000a.p001a.C0010af;
import com.p000a.p001a.p006d.C0111a;
import com.p000a.p001a.p006d.C0115e;
import com.p000a.p001a.p006d.C0116f;
import java.util.Calendar;
import java.util.GregorianCalendar;

/* renamed from: com.a.a.b.a.ap */
/* compiled from: TypeAdapters */
final class C0030ap extends C0010af<Calendar> {
    C0030ap() {
    }

    /* renamed from: a */
    public final /* synthetic */ Object mo9a(C0111a aVar) {
        if (aVar.mo22f() == C0115e.NULL) {
            aVar.mo26j();
            return null;
        }
        aVar.mo18c();
        int i = 0;
        int i2 = 0;
        int i3 = 0;
        int i4 = 0;
        int i5 = 0;
        int i6 = 0;
        while (aVar.mo22f() != C0115e.END_OBJECT) {
            String g = aVar.mo23g();
            int m = aVar.mo29m();
            if ("year".equals(g)) {
                i6 = m;
            } else if ("month".equals(g)) {
                i5 = m;
            } else if ("dayOfMonth".equals(g)) {
                i4 = m;
            } else if ("hourOfDay".equals(g)) {
                i3 = m;
            } else if ("minute".equals(g)) {
                i2 = m;
            } else if ("second".equals(g)) {
                i = m;
            }
        }
        aVar.mo20d();
        return new GregorianCalendar(i6, i5, i4, i3, i2, i);
    }

    /* renamed from: a */
    public final /* synthetic */ void mo10a(C0116f fVar, Object obj) {
        Calendar calendar = (Calendar) obj;
        if (calendar == null) {
            fVar.mo46f();
            return;
        }
        fVar.mo44d();
        fVar.mo37a("year");
        fVar.mo35a((long) calendar.get(1));
        fVar.mo37a("month");
        fVar.mo35a((long) calendar.get(2));
        fVar.mo37a("dayOfMonth");
        fVar.mo35a((long) calendar.get(5));
        fVar.mo37a("hourOfDay");
        fVar.mo35a((long) calendar.get(11));
        fVar.mo37a("minute");
        fVar.mo35a((long) calendar.get(12));
        fVar.mo37a("second");
        fVar.mo35a((long) calendar.get(13));
        fVar.mo45e();
    }
}
