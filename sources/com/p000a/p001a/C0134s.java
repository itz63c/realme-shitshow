package com.p000a.p001a;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* renamed from: com.a.a.s */
/* compiled from: JsonArray */
public final class C0134s extends C0136u implements Iterable<C0136u> {

    /* renamed from: a */
    private final List<C0136u> f260a = new ArrayList();

    /* renamed from: a */
    public final void mo150a(C0136u uVar) {
        if (uVar == null) {
            uVar = C0138w.f261a;
        }
        this.f260a.add(uVar);
    }

    public final Iterator<C0136u> iterator() {
        return this.f260a.iterator();
    }

    /* renamed from: a */
    public final Number mo149a() {
        if (this.f260a.size() == 1) {
            return this.f260a.get(0).mo149a();
        }
        throw new IllegalStateException();
    }

    /* renamed from: b */
    public final String mo151b() {
        if (this.f260a.size() == 1) {
            return this.f260a.get(0).mo151b();
        }
        throw new IllegalStateException();
    }

    /* renamed from: c */
    public final double mo152c() {
        if (this.f260a.size() == 1) {
            return this.f260a.get(0).mo152c();
        }
        throw new IllegalStateException();
    }

    /* renamed from: d */
    public final long mo153d() {
        if (this.f260a.size() == 1) {
            return this.f260a.get(0).mo153d();
        }
        throw new IllegalStateException();
    }

    /* renamed from: e */
    public final int mo154e() {
        if (this.f260a.size() == 1) {
            return this.f260a.get(0).mo154e();
        }
        throw new IllegalStateException();
    }

    /* renamed from: f */
    public final boolean mo156f() {
        if (this.f260a.size() == 1) {
            return this.f260a.get(0).mo156f();
        }
        throw new IllegalStateException();
    }

    public final boolean equals(Object obj) {
        return obj == this || ((obj instanceof C0134s) && ((C0134s) obj).f260a.equals(this.f260a));
    }

    public final int hashCode() {
        return this.f260a.hashCode();
    }
}
