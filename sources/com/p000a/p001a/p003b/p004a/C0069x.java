package com.p000a.p001a.p003b.p004a;

import com.p000a.p001a.C0010af;
import com.p000a.p001a.C0125j;
import com.p000a.p001a.p005c.C0109a;
import com.p000a.p001a.p006d.C0111a;
import com.p000a.p001a.p006d.C0116f;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;

/* renamed from: com.a.a.b.a.x */
/* compiled from: TypeAdapterRuntimeTypeWrapper */
final class C0069x<T> extends C0010af<T> {

    /* renamed from: a */
    private final C0125j f66a;

    /* renamed from: b */
    private final C0010af<T> f67b;

    /* renamed from: c */
    private final Type f68c;

    C0069x(C0125j jVar, C0010af<T> afVar, Type type) {
        this.f66a = jVar;
        this.f67b = afVar;
        this.f68c = type;
    }

    /* renamed from: a */
    public final T mo9a(C0111a aVar) {
        return this.f67b.mo9a(aVar);
    }

    /* renamed from: a */
    public final void mo10a(C0116f fVar, T t) {
        C0010af<T> afVar;
        C0010af<T> afVar2 = this.f67b;
        Type type = this.f68c;
        if (t != null && (type == Object.class || (type instanceof TypeVariable) || (type instanceof Class))) {
            type = t.getClass();
        }
        if (type != this.f68c) {
            afVar = this.f66a.mo141a(C0109a.m211a(type));
            if ((afVar instanceof C0063r) && !(this.f67b instanceof C0063r)) {
                afVar = this.f67b;
            }
        } else {
            afVar = afVar2;
        }
        afVar.mo10a(fVar, t);
    }
}
