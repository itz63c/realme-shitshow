package com.p000a.p001a.p003b;

import com.p000a.p001a.C0133r;
import com.p000a.p001a.p005c.C0109a;
import java.lang.reflect.Constructor;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.SortedSet;

/* renamed from: com.a.a.b.f */
/* compiled from: ConstructorConstructor */
public final class C0087f {

    /* renamed from: a */
    private final Map<Type, C0133r<?>> f138a;

    public C0087f(Map<Type, C0133r<?>> map) {
        this.f138a = map;
    }

    public C0087f() {
        this(Collections.emptyMap());
    }

    /* renamed from: a */
    public final <T> C0100s<T> mo89a(C0109a<T> aVar) {
        C0100s<T> sVar;
        Type b = aVar.mo125b();
        Class<? super T> a = aVar.mo124a();
        C0133r rVar = this.f138a.get(b);
        if (rVar != null) {
            return new C0088g(this, rVar, b);
        }
        C0100s<T> a2 = m173a(a);
        if (a2 != null) {
            return a2;
        }
        if (Collection.class.isAssignableFrom(a)) {
            if (SortedSet.class.isAssignableFrom(a)) {
                sVar = new C0090i(this);
            } else if (Set.class.isAssignableFrom(a)) {
                sVar = new C0091j(this);
            } else if (Queue.class.isAssignableFrom(a)) {
                sVar = new C0092k(this);
            } else {
                sVar = new C0093l(this);
            }
        } else if (Map.class.isAssignableFrom(a)) {
            sVar = new C0094m(this);
        } else {
            sVar = null;
        }
        if (sVar == null) {
            return new C0095n(this, a, b);
        }
        return sVar;
    }

    /* renamed from: a */
    private <T> C0100s<T> m173a(Class<? super T> cls) {
        try {
            Constructor<? super T> declaredConstructor = cls.getDeclaredConstructor(new Class[0]);
            if (!declaredConstructor.isAccessible()) {
                declaredConstructor.setAccessible(true);
            }
            return new C0089h(this, declaredConstructor);
        } catch (NoSuchMethodException e) {
            return null;
        }
    }

    public final String toString() {
        return this.f138a.toString();
    }
}
