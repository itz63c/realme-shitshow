package com.p000a.p001a.p003b.p004a;

import com.p000a.p001a.C0010af;
import com.p000a.p001a.C0011ag;
import com.p000a.p001a.C0125j;
import com.p000a.p001a.p003b.C0083b;
import com.p000a.p001a.p003b.C0087f;
import com.p000a.p001a.p005c.C0109a;
import java.lang.reflect.Type;
import java.util.Collection;

/* renamed from: com.a.a.b.a.c */
/* compiled from: CollectionTypeAdapterFactory */
public final class C0048c implements C0011ag {

    /* renamed from: a */
    private final C0087f f22a;

    public C0048c(C0087f fVar) {
        this.f22a = fVar;
    }

    /* renamed from: a */
    public final <T> C0010af<T> mo11a(C0125j jVar, C0109a<T> aVar) {
        Type b = aVar.mo125b();
        Class<? super T> a = aVar.mo124a();
        if (!Collection.class.isAssignableFrom(a)) {
            return null;
        }
        Type a2 = C0083b.m162a(b, (Class<?>) a);
        return new C0049d(this, jVar, a2, jVar.mo141a(C0109a.m211a(a2)), this.f22a.mo89a(aVar));
    }
}
