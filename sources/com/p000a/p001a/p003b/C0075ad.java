package com.p000a.p001a.p003b;

import java.util.Iterator;
import java.util.NoSuchElementException;

/* renamed from: com.a.a.b.ad */
/* compiled from: StringMap */
abstract class C0075ad<T> implements Iterator<T> {

    /* renamed from: b */
    C0074ac<V> f121b;

    /* renamed from: c */
    C0074ac<V> f122c;

    /* renamed from: d */
    final /* synthetic */ C0105x f123d;

    private C0075ad(C0105x xVar) {
        this.f123d = xVar;
        this.f121b = this.f123d.f175a.f119e;
        this.f122c = null;
    }

    /* synthetic */ C0075ad(C0105x xVar, byte b) {
        this(xVar);
    }

    public final boolean hasNext() {
        return this.f121b != this.f123d.f175a;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public final C0074ac<V> mo65a() {
        C0074ac<V> acVar = this.f121b;
        if (acVar == this.f123d.f175a) {
            throw new NoSuchElementException();
        }
        this.f121b = acVar.f119e;
        this.f122c = acVar;
        return acVar;
    }

    public final void remove() {
        if (this.f122c == null) {
            throw new IllegalStateException();
        }
        this.f123d.remove(this.f122c.f115a);
        this.f122c = null;
    }
}
