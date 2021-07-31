package com.p000a.p001a.p003b.p004a;

import com.p000a.p001a.C0010af;
import com.p000a.p001a.C0011ag;
import com.p000a.p001a.C0125j;
import com.p000a.p001a.p003b.C0083b;
import com.p000a.p001a.p005c.C0109a;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.Type;

/* renamed from: com.a.a.b.a.b */
/* compiled from: ArrayTypeAdapter */
final class C0041b implements C0011ag {
    C0041b() {
    }

    /* renamed from: a */
    public final <T> C0010af<T> mo11a(C0125j jVar, C0109a<T> aVar) {
        Type b = aVar.mo125b();
        if (!(b instanceof GenericArrayType) && (!(b instanceof Class) || !((Class) b).isArray())) {
            return null;
        }
        Type d = C0083b.m170d(b);
        return new C0014a(jVar, jVar.mo141a(C0109a.m211a(d)), C0083b.m166b(d));
    }
}
