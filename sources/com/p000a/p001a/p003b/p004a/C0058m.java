package com.p000a.p001a.p003b.p004a;

import com.p000a.p001a.C0010af;
import com.p000a.p001a.C0011ag;
import com.p000a.p001a.C0125j;
import com.p000a.p001a.p003b.C0105x;
import com.p000a.p001a.p006d.C0111a;
import com.p000a.p001a.p006d.C0116f;
import java.util.ArrayList;
import neton.client.dns.DnsMode;
import neton.internal.platform.Platform;

/* renamed from: com.a.a.b.a.m */
/* compiled from: ObjectTypeAdapter */
public final class C0058m extends C0010af<Object> {

    /* renamed from: a */
    public static final C0011ag f44a = new C0059n();

    /* renamed from: b */
    private final C0125j f45b;

    /* synthetic */ C0058m(C0125j jVar, byte b) {
        this(jVar);
    }

    private C0058m(C0125j jVar) {
        this.f45b = jVar;
    }

    /* renamed from: a */
    public final Object mo9a(C0111a aVar) {
        switch (C0060o.f46a[aVar.mo22f().ordinal()]) {
            case 1:
                ArrayList arrayList = new ArrayList();
                aVar.mo16a();
                while (aVar.mo21e()) {
                    arrayList.add(mo9a(aVar));
                }
                aVar.mo17b();
                return arrayList;
            case DnsMode.DNS_MODE_HTTP /*2*/:
                C0105x xVar = new C0105x();
                aVar.mo18c();
                while (aVar.mo21e()) {
                    xVar.put(aVar.mo23g(), mo9a(aVar));
                }
                aVar.mo20d();
                return xVar;
            case 3:
                return aVar.mo24h();
            case Platform.INFO /*4*/:
                return Double.valueOf(aVar.mo27k());
            case Platform.WARN /*5*/:
                return Boolean.valueOf(aVar.mo25i());
            case 6:
                aVar.mo26j();
                return null;
            default:
                throw new IllegalStateException();
        }
    }

    /* renamed from: a */
    public final void mo10a(C0116f fVar, Object obj) {
        if (obj == null) {
            fVar.mo46f();
            return;
        }
        C0010af<?> a = this.f45b.mo142a(obj.getClass());
        if (a instanceof C0058m) {
            fVar.mo44d();
            fVar.mo45e();
            return;
        }
        a.mo10a(fVar, obj);
    }
}
