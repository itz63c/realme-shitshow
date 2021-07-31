package com.p000a.p001a.p003b.p004a;

import com.p000a.p001a.C0006ab;
import com.p000a.p001a.C0010af;
import com.p000a.p001a.p006d.C0111a;
import com.p000a.p001a.p006d.C0115e;
import com.p000a.p001a.p006d.C0116f;
import java.util.BitSet;
import neton.client.dns.DnsMode;

/* renamed from: com.a.a.b.a.ak */
/* compiled from: TypeAdapters */
final class C0025ak extends C0010af<BitSet> {
    C0025ak() {
    }

    /* renamed from: a */
    public final /* synthetic */ Object mo9a(C0111a aVar) {
        return m38b(aVar);
    }

    /* renamed from: a */
    public final /* synthetic */ void mo10a(C0116f fVar, Object obj) {
        int i;
        BitSet bitSet = (BitSet) obj;
        if (bitSet == null) {
            fVar.mo46f();
            return;
        }
        fVar.mo40b();
        for (int i2 = 0; i2 < bitSet.length(); i2++) {
            if (bitSet.get(i2)) {
                i = 1;
            } else {
                i = 0;
            }
            fVar.mo35a((long) i);
        }
        fVar.mo42c();
    }

    /* renamed from: b */
    private static BitSet m38b(C0111a aVar) {
        boolean z;
        if (aVar.mo22f() == C0115e.NULL) {
            aVar.mo26j();
            return null;
        }
        BitSet bitSet = new BitSet();
        aVar.mo16a();
        C0115e f = aVar.mo22f();
        int i = 0;
        while (f != C0115e.END_ARRAY) {
            switch (C0039ay.f19a[f.ordinal()]) {
                case 1:
                    if (aVar.mo29m() == 0) {
                        z = false;
                        break;
                    } else {
                        z = true;
                        break;
                    }
                case DnsMode.DNS_MODE_HTTP /*2*/:
                    z = aVar.mo25i();
                    break;
                case 3:
                    String h = aVar.mo24h();
                    try {
                        if (Integer.parseInt(h) == 0) {
                            z = false;
                            break;
                        } else {
                            z = true;
                            break;
                        }
                    } catch (NumberFormatException e) {
                        throw new C0006ab("Error: Expecting: bitset number value (1, 0), Found: " + h);
                    }
                default:
                    throw new C0006ab("Invalid bitset value type: " + f);
            }
            if (z) {
                bitSet.set(i);
            }
            i++;
            f = aVar.mo22f();
        }
        aVar.mo17b();
        return bitSet;
    }
}
