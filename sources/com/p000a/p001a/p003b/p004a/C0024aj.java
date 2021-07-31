package com.p000a.p001a.p003b.p004a;

import com.p000a.p001a.C0010af;
import com.p000a.p001a.C0137v;
import com.p000a.p001a.p006d.C0111a;
import com.p000a.p001a.p006d.C0115e;
import com.p000a.p001a.p006d.C0116f;
import java.net.URI;
import java.net.URISyntaxException;

/* renamed from: com.a.a.b.a.aj */
/* compiled from: TypeAdapters */
final class C0024aj extends C0010af<URI> {
    C0024aj() {
    }

    /* renamed from: a */
    public final /* synthetic */ Object mo9a(C0111a aVar) {
        return m35b(aVar);
    }

    /* renamed from: a */
    public final /* synthetic */ void mo10a(C0116f fVar, Object obj) {
        String aSCIIString;
        URI uri = (URI) obj;
        if (uri == null) {
            aSCIIString = null;
        } else {
            aSCIIString = uri.toASCIIString();
        }
        fVar.mo41b(aSCIIString);
    }

    /* renamed from: b */
    private static URI m35b(C0111a aVar) {
        if (aVar.mo22f() == C0115e.NULL) {
            aVar.mo26j();
            return null;
        }
        try {
            String h = aVar.mo24h();
            if (!"null".equals(h)) {
                return new URI(h);
            }
            return null;
        } catch (URISyntaxException e) {
            throw new C0137v((Throwable) e);
        }
    }
}
