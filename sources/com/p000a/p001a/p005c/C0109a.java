package com.p000a.p001a.p005c;

import com.p000a.p001a.p003b.C0013a;
import com.p000a.p001a.p003b.C0083b;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/* renamed from: com.a.a.c.a */
/* compiled from: TypeToken */
public final class C0109a<T> {

    /* renamed from: a */
    final Class<? super T> f190a;

    /* renamed from: b */
    final Type f191b;

    /* renamed from: c */
    final int f192c;

    protected C0109a() {
        Type genericSuperclass = getClass().getGenericSuperclass();
        if (genericSuperclass instanceof Class) {
            throw new RuntimeException("Missing type parameter.");
        }
        this.f191b = C0083b.m161a(((ParameterizedType) genericSuperclass).getActualTypeArguments()[0]);
        this.f190a = C0083b.m166b(this.f191b);
        this.f192c = this.f191b.hashCode();
    }

    private C0109a(Type type) {
        this.f191b = C0083b.m161a((Type) C0013a.m11a(type));
        this.f190a = C0083b.m166b(this.f191b);
        this.f192c = this.f191b.hashCode();
    }

    /* renamed from: a */
    public final Class<? super T> mo124a() {
        return this.f190a;
    }

    /* renamed from: b */
    public final Type mo125b() {
        return this.f191b;
    }

    public final int hashCode() {
        return this.f192c;
    }

    public final boolean equals(Object obj) {
        return (obj instanceof C0109a) && C0083b.m165a(this.f191b, ((C0109a) obj).f191b);
    }

    public final String toString() {
        return C0083b.m169c(this.f191b);
    }

    /* renamed from: a */
    public static C0109a<?> m211a(Type type) {
        return new C0109a<>(type);
    }

    /* renamed from: a */
    public static <T> C0109a<T> m210a(Class<T> cls) {
        return new C0109a<>(cls);
    }
}
