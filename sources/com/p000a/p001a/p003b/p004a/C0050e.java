package com.p000a.p001a.p003b.p004a;

import com.p000a.p001a.C0006ab;
import com.p000a.p001a.C0010af;
import com.p000a.p001a.C0011ag;
import com.p000a.p001a.p006d.C0111a;
import com.p000a.p001a.p006d.C0115e;
import com.p000a.p001a.p006d.C0116f;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/* renamed from: com.a.a.b.a.e */
/* compiled from: DateTypeAdapter */
public final class C0050e extends C0010af<Date> {

    /* renamed from: a */
    public static final C0011ag f26a = new C0051f();

    /* renamed from: b */
    private final DateFormat f27b = DateFormat.getDateTimeInstance(2, 2, Locale.US);

    /* renamed from: c */
    private final DateFormat f28c = DateFormat.getDateTimeInstance(2, 2);

    /* renamed from: d */
    private final DateFormat f29d;

    public C0050e() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.US);
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        this.f29d = simpleDateFormat;
    }

    /* renamed from: a */
    private synchronized Date m85a(String str) {
        Date parse;
        try {
            parse = this.f28c.parse(str);
        } catch (ParseException e) {
            try {
                parse = this.f27b.parse(str);
            } catch (ParseException e2) {
                try {
                    parse = this.f29d.parse(str);
                } catch (ParseException e3) {
                    throw new C0006ab(str, e3);
                }
            }
        }
        return parse;
    }

    /* access modifiers changed from: private */
    /* renamed from: a */
    public synchronized void mo10a(C0116f fVar, Date date) {
        if (date == null) {
            fVar.mo46f();
        } else {
            fVar.mo41b(this.f27b.format(date));
        }
    }

    /* renamed from: a */
    public final /* synthetic */ Object mo9a(C0111a aVar) {
        if (aVar.mo22f() != C0115e.NULL) {
            return m85a(aVar.mo24h());
        }
        aVar.mo26j();
        return null;
    }
}
