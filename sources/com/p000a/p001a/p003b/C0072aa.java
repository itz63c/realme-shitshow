package com.p000a.p001a.p003b;

import java.util.AbstractSet;
import java.util.Iterator;

/* renamed from: com.a.a.b.aa */
/* compiled from: StringMap */
final class C0072aa extends AbstractSet<String> {

    /* renamed from: a */
    final /* synthetic */ C0105x f113a;

    private C0072aa(C0105x xVar) {
        this.f113a = xVar;
    }

    /* synthetic */ C0072aa(C0105x xVar, byte b) {
        this(xVar);
    }

    /* JADX WARNING: type inference failed for: r0v0, types: [java.util.Iterator<java.lang.String>, com.a.a.b.ab] */
    public final Iterator<String> iterator() {
        return new C0073ab(this);
    }

    public final int size() {
        return this.f113a.f177d;
    }

    public final boolean contains(Object obj) {
        return this.f113a.containsKey(obj);
    }

    public final boolean remove(Object obj) {
        int b = this.f113a.f177d;
        this.f113a.remove(obj);
        return this.f113a.f177d != b;
    }

    public final void clear() {
        this.f113a.clear();
    }
}
