package com.p000a.p001a.p003b;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/* renamed from: com.a.a.b.h */
/* compiled from: ConstructorConstructor */
final class C0089h implements C0100s<T> {

    /* renamed from: a */
    final /* synthetic */ Constructor f142a;

    /* renamed from: b */
    final /* synthetic */ C0087f f143b;

    C0089h(C0087f fVar, Constructor constructor) {
        this.f143b = fVar;
        this.f142a = constructor;
    }

    /* renamed from: a */
    public final T mo91a() {
        try {
            return this.f142a.newInstance((Object[]) null);
        } catch (InstantiationException e) {
            throw new RuntimeException("Failed to invoke " + this.f142a + " with no args", e);
        } catch (InvocationTargetException e2) {
            throw new RuntimeException("Failed to invoke " + this.f142a + " with no args", e2.getTargetException());
        } catch (IllegalAccessException e3) {
            throw new AssertionError(e3);
        }
    }
}
