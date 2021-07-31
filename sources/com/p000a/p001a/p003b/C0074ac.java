package com.p000a.p001a.p003b;

import java.util.Map;

/* renamed from: com.a.a.b.ac */
/* compiled from: StringMap */
final class C0074ac<V> implements Map.Entry<String, V> {

    /* renamed from: a */
    final String f115a;

    /* renamed from: b */
    V f116b;

    /* renamed from: c */
    final int f117c;

    /* renamed from: d */
    C0074ac<V> f118d;

    /* renamed from: e */
    C0074ac<V> f119e;

    /* renamed from: f */
    C0074ac<V> f120f;

    C0074ac() {
        this((String) null, (Object) null, 0, (C0074ac) null, (C0074ac) null, (C0074ac) null);
        this.f120f = this;
        this.f119e = this;
    }

    C0074ac(String str, V v, int i, C0074ac<V> acVar, C0074ac<V> acVar2, C0074ac<V> acVar3) {
        this.f115a = str;
        this.f116b = v;
        this.f117c = i;
        this.f118d = acVar;
        this.f119e = acVar2;
        this.f120f = acVar3;
    }

    public final V getValue() {
        return this.f116b;
    }

    public final V setValue(V v) {
        V v2 = this.f116b;
        this.f116b = v;
        return v2;
    }

    /* JADX WARNING: Removed duplicated region for block: B:7:0x001e A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean equals(java.lang.Object r5) {
        /*
            r4 = this;
            r0 = 0
            boolean r1 = r5 instanceof java.util.Map.Entry
            if (r1 != 0) goto L_0x0006
        L_0x0005:
            return r0
        L_0x0006:
            java.util.Map$Entry r5 = (java.util.Map.Entry) r5
            java.lang.Object r1 = r5.getValue()
            java.lang.String r2 = r4.f115a
            java.lang.Object r3 = r5.getKey()
            boolean r2 = r2.equals(r3)
            if (r2 == 0) goto L_0x0005
            V r2 = r4.f116b
            if (r2 != 0) goto L_0x0020
            if (r1 != 0) goto L_0x0005
        L_0x001e:
            r0 = 1
            goto L_0x0005
        L_0x0020:
            V r2 = r4.f116b
            boolean r1 = r2.equals(r1)
            if (r1 == 0) goto L_0x0005
            goto L_0x001e
        */
        throw new UnsupportedOperationException("Method not decompiled: com.p000a.p001a.p003b.C0074ac.equals(java.lang.Object):boolean");
    }

    public final int hashCode() {
        int i = 0;
        int hashCode = this.f115a == null ? 0 : this.f115a.hashCode();
        if (this.f116b != null) {
            i = this.f116b.hashCode();
        }
        return hashCode ^ i;
    }

    public final String toString() {
        return this.f115a + "=" + this.f116b;
    }

    public final /* bridge */ /* synthetic */ Object getKey() {
        return this.f115a;
    }
}
