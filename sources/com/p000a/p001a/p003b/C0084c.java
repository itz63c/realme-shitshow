package com.p000a.p001a.p003b;

import java.io.Serializable;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.Type;

/* renamed from: com.a.a.b.c */
/* compiled from: $Gson$Types */
final class C0084c implements Serializable, GenericArrayType {
    private static final long serialVersionUID = 0;

    /* renamed from: a */
    private final Type f132a;

    public C0084c(Type type) {
        this.f132a = C0083b.m161a(type);
    }

    public final Type getGenericComponentType() {
        return this.f132a;
    }

    public final boolean equals(Object obj) {
        return (obj instanceof GenericArrayType) && C0083b.m165a((Type) this, (Type) (GenericArrayType) obj);
    }

    public final int hashCode() {
        return this.f132a.hashCode();
    }

    public final String toString() {
        return C0083b.m169c(this.f132a) + "[]";
    }
}
