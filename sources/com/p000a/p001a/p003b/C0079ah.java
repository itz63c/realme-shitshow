package com.p000a.p001a.p003b;

import java.lang.reflect.Method;

/* renamed from: com.a.a.b.ah */
/* compiled from: UnsafeAllocator */
final class C0079ah extends C0078ag {

    /* renamed from: a */
    final /* synthetic */ Method f126a;

    /* renamed from: b */
    final /* synthetic */ Object f127b;

    C0079ah(Method method, Object obj) {
        this.f126a = method;
        this.f127b = obj;
    }

    /* renamed from: a */
    public final <T> T mo73a(Class<T> cls) {
        return this.f126a.invoke(this.f127b, new Object[]{cls});
    }
}
