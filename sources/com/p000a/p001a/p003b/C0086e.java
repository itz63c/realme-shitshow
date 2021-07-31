package com.p000a.p001a.p003b;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.lang.reflect.WildcardType;

/* renamed from: com.a.a.b.e */
/* compiled from: $Gson$Types */
final class C0086e implements Serializable, WildcardType {
    private static final long serialVersionUID = 0;

    /* renamed from: a */
    private final Type f136a;

    /* renamed from: b */
    private final Type f137b;

    public C0086e(Type[] typeArr, Type[] typeArr2) {
        boolean z;
        boolean z2 = true;
        C0013a.m12a(typeArr2.length <= 1);
        if (typeArr.length == 1) {
            z = true;
        } else {
            z = false;
        }
        C0013a.m12a(z);
        if (typeArr2.length == 1) {
            C0013a.m11a(typeArr2[0]);
            C0083b.m171e(typeArr2[0]);
            C0013a.m12a(typeArr[0] != Object.class ? false : z2);
            this.f137b = C0083b.m161a(typeArr2[0]);
            this.f136a = Object.class;
            return;
        }
        C0013a.m11a(typeArr[0]);
        C0083b.m171e(typeArr[0]);
        this.f137b = null;
        this.f136a = C0083b.m161a(typeArr[0]);
    }

    public final Type[] getUpperBounds() {
        return new Type[]{this.f136a};
    }

    public final Type[] getLowerBounds() {
        if (this.f137b == null) {
            return C0083b.f131a;
        }
        return new Type[]{this.f137b};
    }

    public final boolean equals(Object obj) {
        return (obj instanceof WildcardType) && C0083b.m165a((Type) this, (Type) (WildcardType) obj);
    }

    public final int hashCode() {
        return (this.f137b != null ? this.f137b.hashCode() + 31 : 1) ^ (this.f136a.hashCode() + 31);
    }

    public final String toString() {
        if (this.f137b != null) {
            return "? super " + C0083b.m169c(this.f137b);
        }
        if (this.f136a == Object.class) {
            return "?";
        }
        return "? extends " + C0083b.m169c(this.f136a);
    }
}
