package com.p000a.p001a.p003b.p004a;

import com.p000a.p001a.C0010af;
import com.p000a.p001a.C0011ag;
import com.p000a.p001a.C0125j;
import com.p000a.p001a.p006d.C0111a;
import com.p000a.p001a.p006d.C0115e;
import com.p000a.p001a.p006d.C0116f;
import java.lang.reflect.Array;
import java.util.ArrayList;

/* renamed from: com.a.a.b.a.a */
/* compiled from: ArrayTypeAdapter */
public final class C0014a<E> extends C0010af<Object> {

    /* renamed from: a */
    public static final C0011ag f4a = new C0041b();

    /* renamed from: b */
    private final Class<E> f5b;

    /* renamed from: c */
    private final C0010af<E> f6c;

    public C0014a(C0125j jVar, C0010af<E> afVar, Class<E> cls) {
        this.f6c = new C0069x(jVar, afVar, cls);
        this.f5b = cls;
    }

    /* renamed from: a */
    public final Object mo9a(C0111a aVar) {
        if (aVar.mo22f() == C0115e.NULL) {
            aVar.mo26j();
            return null;
        }
        ArrayList arrayList = new ArrayList();
        aVar.mo16a();
        while (aVar.mo21e()) {
            arrayList.add(this.f6c.mo9a(aVar));
        }
        aVar.mo17b();
        Object newInstance = Array.newInstance(this.f5b, arrayList.size());
        for (int i = 0; i < arrayList.size(); i++) {
            Array.set(newInstance, i, arrayList.get(i));
        }
        return newInstance;
    }

    /* renamed from: a */
    public final void mo10a(C0116f fVar, Object obj) {
        if (obj == null) {
            fVar.mo46f();
            return;
        }
        fVar.mo40b();
        int length = Array.getLength(obj);
        for (int i = 0; i < length; i++) {
            this.f6c.mo10a(fVar, Array.get(obj, i));
        }
        fVar.mo42c();
    }
}
