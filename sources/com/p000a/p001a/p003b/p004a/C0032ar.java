package com.p000a.p001a.p003b.p004a;

import com.p000a.p001a.C0010af;
import com.p000a.p001a.C0134s;
import com.p000a.p001a.C0136u;
import com.p000a.p001a.C0138w;
import com.p000a.p001a.C0139x;
import com.p000a.p001a.C0141z;
import com.p000a.p001a.p003b.C0099r;
import com.p000a.p001a.p006d.C0111a;
import com.p000a.p001a.p006d.C0116f;
import java.util.Iterator;
import java.util.Map;
import neton.client.dns.DnsMode;
import neton.internal.platform.Platform;

/* renamed from: com.a.a.b.a.ar */
/* compiled from: TypeAdapters */
final class C0032ar extends C0010af<C0136u> {
    C0032ar() {
    }

    /* access modifiers changed from: private */
    /* renamed from: b */
    public C0136u mo9a(C0111a aVar) {
        switch (C0039ay.f19a[aVar.mo22f().ordinal()]) {
            case 1:
                return new C0141z((Number) new C0099r(aVar.mo24h()));
            case DnsMode.DNS_MODE_HTTP /*2*/:
                return new C0141z(Boolean.valueOf(aVar.mo25i()));
            case 3:
                return new C0141z(aVar.mo24h());
            case Platform.INFO /*4*/:
                aVar.mo26j();
                return C0138w.f261a;
            case Platform.WARN /*5*/:
                C0134s sVar = new C0134s();
                aVar.mo16a();
                while (aVar.mo21e()) {
                    sVar.mo150a(mo9a(aVar));
                }
                aVar.mo17b();
                return sVar;
            case 6:
                C0139x xVar = new C0139x();
                aVar.mo18c();
                while (aVar.mo21e()) {
                    xVar.mo163a(aVar.mo23g(), mo9a(aVar));
                }
                aVar.mo20d();
                return xVar;
            default:
                throw new IllegalArgumentException();
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: a */
    public void mo10a(C0116f fVar, C0136u uVar) {
        if (uVar == null || (uVar instanceof C0138w)) {
            fVar.mo46f();
        } else if (uVar instanceof C0141z) {
            C0141z g = uVar.mo159g();
            if (g.mo170i()) {
                fVar.mo36a(g.mo149a());
            } else if (g.mo168h()) {
                fVar.mo38a(g.mo156f());
            } else {
                fVar.mo41b(g.mo151b());
            }
        } else if (uVar instanceof C0134s) {
            fVar.mo40b();
            if (uVar instanceof C0134s) {
                Iterator<C0136u> it = ((C0134s) uVar).iterator();
                while (it.hasNext()) {
                    mo10a(fVar, it.next());
                }
                fVar.mo42c();
                return;
            }
            throw new IllegalStateException("This is not a JSON Array.");
        } else if (uVar instanceof C0139x) {
            fVar.mo44d();
            if (uVar instanceof C0139x) {
                for (Map.Entry next : ((C0139x) uVar).mo165h()) {
                    fVar.mo37a((String) next.getKey());
                    mo10a(fVar, (C0136u) next.getValue());
                }
                fVar.mo45e();
                return;
            }
            throw new IllegalStateException("Not a JSON Object: " + uVar);
        } else {
            throw new IllegalArgumentException("Couldn't write " + uVar.getClass());
        }
    }
}
