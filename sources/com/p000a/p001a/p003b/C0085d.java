package com.p000a.p001a.p003b;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Arrays;

/* renamed from: com.a.a.b.d */
/* compiled from: $Gson$Types */
final class C0085d implements Serializable, ParameterizedType {
    private static final long serialVersionUID = 0;

    /* renamed from: a */
    private final Type f133a;

    /* renamed from: b */
    private final Type f134b;

    /* renamed from: c */
    private final Type[] f135c;

    public C0085d(Type type, Type type2, Type... typeArr) {
        boolean z = true;
        if (type2 instanceof Class) {
            Class cls = (Class) type2;
            C0013a.m12a(type != null || cls.getEnclosingClass() == null);
            if (type != null && cls.getEnclosingClass() == null) {
                z = false;
            }
            C0013a.m12a(z);
        }
        this.f133a = type == null ? null : C0083b.m161a(type);
        this.f134b = C0083b.m161a(type2);
        this.f135c = (Type[]) typeArr.clone();
        for (int i = 0; i < this.f135c.length; i++) {
            C0013a.m11a(this.f135c[i]);
            C0083b.m171e(this.f135c[i]);
            this.f135c[i] = C0083b.m161a(this.f135c[i]);
        }
    }

    public final Type[] getActualTypeArguments() {
        return (Type[]) this.f135c.clone();
    }

    public final Type getRawType() {
        return this.f134b;
    }

    public final Type getOwnerType() {
        return this.f133a;
    }

    public final boolean equals(Object obj) {
        return (obj instanceof ParameterizedType) && C0083b.m165a((Type) this, (Type) (ParameterizedType) obj);
    }

    public final int hashCode() {
        return (Arrays.hashCode(this.f135c) ^ this.f134b.hashCode()) ^ C0083b.m160a((Object) this.f133a);
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder((this.f135c.length + 1) * 30);
        sb.append(C0083b.m169c(this.f134b));
        if (this.f135c.length == 0) {
            return sb.toString();
        }
        sb.append("<").append(C0083b.m169c(this.f135c[0]));
        for (int i = 1; i < this.f135c.length; i++) {
            sb.append(", ").append(C0083b.m169c(this.f135c[i]));
        }
        return sb.append(">").toString();
    }
}
