package com.p000a.p001a.p003b.p004a;

import com.p000a.p001a.C0010af;
import com.p000a.p001a.p006d.C0111a;
import com.p000a.p001a.p006d.C0115e;
import com.p000a.p001a.p006d.C0116f;
import java.net.InetAddress;

/* renamed from: com.a.a.b.a.al */
/* compiled from: TypeAdapters */
final class C0026al extends C0010af<InetAddress> {
    C0026al() {
    }

    /* renamed from: a */
    public final /* synthetic */ void mo10a(C0116f fVar, Object obj) {
        String hostAddress;
        InetAddress inetAddress = (InetAddress) obj;
        if (inetAddress == null) {
            hostAddress = null;
        } else {
            hostAddress = inetAddress.getHostAddress();
        }
        fVar.mo41b(hostAddress);
    }

    /* renamed from: a */
    public final /* synthetic */ Object mo9a(C0111a aVar) {
        if (aVar.mo22f() != C0115e.NULL) {
            return InetAddress.getByName(aVar.mo24h());
        }
        aVar.mo26j();
        return null;
    }
}
