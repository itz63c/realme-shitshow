package com.p000a.p001a.p003b.p004a;

import com.p000a.p001a.C0010af;
import com.p000a.p001a.p006d.C0111a;
import com.p000a.p001a.p006d.C0115e;
import com.p000a.p001a.p006d.C0116f;
import java.net.URL;

/* renamed from: com.a.a.b.a.ai */
/* compiled from: TypeAdapters */
final class C0023ai extends C0010af<URL> {
    C0023ai() {
    }

    /* renamed from: a */
    public final /* synthetic */ Object mo9a(C0111a aVar) {
        if (aVar.mo22f() == C0115e.NULL) {
            aVar.mo26j();
            return null;
        }
        String h = aVar.mo24h();
        if (!"null".equals(h)) {
            return new URL(h);
        }
        return null;
    }

    /* renamed from: a */
    public final /* synthetic */ void mo10a(C0116f fVar, Object obj) {
        String externalForm;
        URL url = (URL) obj;
        if (url == null) {
            externalForm = null;
        } else {
            externalForm = url.toExternalForm();
        }
        fVar.mo41b(externalForm);
    }
}
