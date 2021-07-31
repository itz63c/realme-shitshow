package com.p000a.p001a.p003b;

import java.lang.reflect.Method;

/* renamed from: com.a.a.b.aj */
/* compiled from: UnsafeAllocator */
final class C0081aj extends C0078ag {

    /* renamed from: a */
    final /* synthetic */ Method f129a;

    /* renamed from: b */
    final /* synthetic */ int f130b;

    C0081aj(Method method, int i) {
        this.f129a = method;
        this.f130b = i;
    }

    /* renamed from: a */
    public final <T> T mo73a(Class<T> cls) {
        return this.f129a.invoke((Object) null, new Object[]{cls, Integer.valueOf(this.f130b)});
    }
}
