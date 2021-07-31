package com.p000a.p001a.p003b.p004a;

import com.p000a.p001a.C0010af;
import com.p000a.p001a.C0125j;
import com.p000a.p001a.p003b.C0100s;
import com.p000a.p001a.p006d.C0111a;
import com.p000a.p001a.p006d.C0115e;
import com.p000a.p001a.p006d.C0116f;
import java.lang.reflect.Type;
import java.util.Collection;

/* renamed from: com.a.a.b.a.d */
/* compiled from: CollectionTypeAdapterFactory */
final class C0049d<E> extends C0010af<Collection<E>> {

    /* renamed from: a */
    final /* synthetic */ C0048c f23a;

    /* renamed from: b */
    private final C0010af<E> f24b;

    /* renamed from: c */
    private final C0100s<? extends Collection<E>> f25c;

    /* renamed from: a */
    public final /* synthetic */ void mo10a(C0116f fVar, Object obj) {
        Collection<Object> collection = (Collection) obj;
        if (collection == null) {
            fVar.mo46f();
            return;
        }
        fVar.mo40b();
        for (Object a : collection) {
            this.f24b.mo10a(fVar, a);
        }
        fVar.mo42c();
    }

    public C0049d(C0048c cVar, C0125j jVar, Type type, C0010af<E> afVar, C0100s<? extends Collection<E>> sVar) {
        this.f23a = cVar;
        this.f24b = new C0069x(jVar, afVar, type);
        this.f25c = sVar;
    }

    /* renamed from: a */
    public final /* synthetic */ Object mo9a(C0111a aVar) {
        if (aVar.mo22f() == C0115e.NULL) {
            aVar.mo26j();
            return null;
        }
        Collection collection = (Collection) this.f25c.mo91a();
        aVar.mo16a();
        while (aVar.mo21e()) {
            collection.add(this.f24b.mo9a(aVar));
        }
        aVar.mo17b();
        return collection;
    }
}
