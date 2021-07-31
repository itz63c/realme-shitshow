package com.p000a.p001a.p003b.p004a;

import com.p000a.p001a.C0006ab;
import com.p000a.p001a.C0010af;
import com.p000a.p001a.C0125j;
import com.p000a.p001a.C0134s;
import com.p000a.p001a.C0136u;
import com.p000a.p001a.C0138w;
import com.p000a.p001a.C0139x;
import com.p000a.p001a.C0141z;
import com.p000a.p001a.p003b.C0098q;
import com.p000a.p001a.p003b.C0100s;
import com.p000a.p001a.p003b.C0102u;
import com.p000a.p001a.p006d.C0111a;
import com.p000a.p001a.p006d.C0115e;
import com.p000a.p001a.p006d.C0116f;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Map;

/* renamed from: com.a.a.b.a.l */
/* compiled from: MapTypeAdapterFactory */
final class C0057l<K, V> extends C0010af<Map<K, V>> {

    /* renamed from: a */
    final /* synthetic */ C0056k f40a;

    /* renamed from: b */
    private final C0010af<K> f41b;

    /* renamed from: c */
    private final C0010af<V> f42c;

    /* renamed from: d */
    private final C0100s<? extends Map<K, V>> f43d;

    /* renamed from: a */
    public final /* synthetic */ void mo10a(C0116f fVar, Object obj) {
        String str;
        boolean z;
        int i = 0;
        Map map = (Map) obj;
        if (map == null) {
            fVar.mo46f();
        } else if (!this.f40a.f39b) {
            fVar.mo44d();
            for (Map.Entry entry : map.entrySet()) {
                fVar.mo37a(String.valueOf(entry.getKey()));
                this.f42c.mo10a(fVar, entry.getValue());
            }
            fVar.mo45e();
        } else {
            ArrayList arrayList = new ArrayList(map.size());
            ArrayList arrayList2 = new ArrayList(map.size());
            boolean z2 = false;
            for (Map.Entry entry2 : map.entrySet()) {
                C0136u a = this.f41b.mo8a(entry2.getKey());
                arrayList.add(a);
                arrayList2.add(entry2.getValue());
                if ((a instanceof C0134s) || (a instanceof C0139x)) {
                    z = true;
                } else {
                    z = false;
                }
                z2 = z | z2;
            }
            if (z2) {
                fVar.mo40b();
                while (i < arrayList.size()) {
                    fVar.mo40b();
                    C0102u.m199a((C0136u) arrayList.get(i), fVar);
                    this.f42c.mo10a(fVar, arrayList2.get(i));
                    fVar.mo42c();
                    i++;
                }
                fVar.mo42c();
                return;
            }
            fVar.mo44d();
            while (i < arrayList.size()) {
                C0136u uVar = (C0136u) arrayList.get(i);
                if (uVar instanceof C0141z) {
                    C0141z g = uVar.mo159g();
                    if (g.mo170i()) {
                        str = String.valueOf(g.mo149a());
                    } else if (g.mo168h()) {
                        str = Boolean.toString(g.mo156f());
                    } else if (g.mo171j()) {
                        str = g.mo151b();
                    } else {
                        throw new AssertionError();
                    }
                } else if (uVar instanceof C0138w) {
                    str = "null";
                } else {
                    throw new AssertionError();
                }
                fVar.mo37a(str);
                this.f42c.mo10a(fVar, arrayList2.get(i));
                i++;
            }
            fVar.mo45e();
        }
    }

    public C0057l(C0056k kVar, C0125j jVar, Type type, C0010af<K> afVar, Type type2, C0010af<V> afVar2, C0100s<? extends Map<K, V>> sVar) {
        this.f40a = kVar;
        this.f41b = new C0069x(jVar, afVar, type);
        this.f42c = new C0069x(jVar, afVar2, type2);
        this.f43d = sVar;
    }

    /* renamed from: a */
    public final /* synthetic */ Object mo9a(C0111a aVar) {
        C0115e f = aVar.mo22f();
        if (f == C0115e.NULL) {
            aVar.mo26j();
            return null;
        }
        Map map = (Map) this.f43d.mo91a();
        if (f == C0115e.BEGIN_ARRAY) {
            aVar.mo16a();
            while (aVar.mo21e()) {
                aVar.mo16a();
                K a = this.f41b.mo9a(aVar);
                if (map.put(a, this.f42c.mo9a(aVar)) != null) {
                    throw new C0006ab("duplicate key: " + a);
                }
                aVar.mo17b();
            }
            aVar.mo17b();
            return map;
        }
        aVar.mo18c();
        while (aVar.mo21e()) {
            C0098q.f166a.mo95a(aVar);
            K a2 = this.f41b.mo9a(aVar);
            if (map.put(a2, this.f42c.mo9a(aVar)) != null) {
                throw new C0006ab("duplicate key: " + a2);
            }
        }
        aVar.mo20d();
        return map;
    }
}
