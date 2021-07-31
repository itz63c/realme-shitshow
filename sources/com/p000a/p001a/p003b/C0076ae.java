package com.p000a.p001a.p003b;

import java.util.AbstractCollection;
import java.util.Iterator;

/* renamed from: com.a.a.b.ae */
/* compiled from: StringMap */
final class C0076ae extends AbstractCollection<V> {

    /* renamed from: a */
    final /* synthetic */ C0105x f124a;

    private C0076ae(C0105x xVar) {
        this.f124a = xVar;
    }

    /* synthetic */ C0076ae(C0105x xVar, byte b) {
        this(xVar);
    }

    /* JADX WARNING: type inference failed for: r0v0, types: [com.a.a.b.af, java.util.Iterator<V>] */
    public final Iterator<V> iterator() {
        return new C0077af(this);
    }

    public final int size() {
        return this.f124a.f177d;
    }

    public final boolean contains(Object obj) {
        return this.f124a.containsValue(obj);
    }

    public final void clear() {
        this.f124a.clear();
    }
}
