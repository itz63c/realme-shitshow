package com.p000a.p001a.p003b;

import java.util.AbstractSet;
import java.util.Iterator;
import java.util.Map;

/* renamed from: com.a.a.b.y */
/* compiled from: StringMap */
final class C0106y extends AbstractSet<Map.Entry<String, V>> {

    /* renamed from: a */
    final /* synthetic */ C0105x f182a;

    private C0106y(C0105x xVar) {
        this.f182a = xVar;
    }

    /* synthetic */ C0106y(C0105x xVar, byte b) {
        this(xVar);
    }

    /* JADX WARNING: type inference failed for: r0v0, types: [java.util.Iterator<java.util.Map$Entry<java.lang.String, V>>, com.a.a.b.z] */
    public final Iterator<Map.Entry<String, V>> iterator() {
        return new C0107z(this);
    }

    public final boolean contains(Object obj) {
        if (!(obj instanceof Map.Entry)) {
            return false;
        }
        Map.Entry entry = (Map.Entry) obj;
        Object obj2 = this.f182a.get(entry.getKey());
        if (obj2 == null || !obj2.equals(entry.getValue())) {
            return false;
        }
        return true;
    }

    public final boolean remove(Object obj) {
        if (!(obj instanceof Map.Entry)) {
            return false;
        }
        Map.Entry entry = (Map.Entry) obj;
        return C0105x.m203a(this.f182a, entry.getKey(), entry.getValue());
    }

    public final int size() {
        return this.f182a.f177d;
    }

    public final void clear() {
        this.f182a.clear();
    }
}
