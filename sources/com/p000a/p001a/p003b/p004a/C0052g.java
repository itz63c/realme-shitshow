package com.p000a.p001a.p003b.p004a;

import com.p000a.p001a.C0134s;
import com.p000a.p001a.C0138w;
import com.p000a.p001a.C0139x;
import com.p000a.p001a.C0141z;
import com.p000a.p001a.p006d.C0111a;
import com.p000a.p001a.p006d.C0115e;
import java.io.Reader;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* renamed from: com.a.a.b.a.g */
/* compiled from: JsonTreeReader */
public final class C0052g extends C0111a {

    /* renamed from: a */
    private static final Reader f30a = new C0053h();

    /* renamed from: b */
    private static final Object f31b = new Object();

    /* renamed from: c */
    private final List<Object> f32c;

    /* renamed from: a */
    public final void mo16a() {
        m90a(C0115e.BEGIN_ARRAY);
        this.f32c.add(((C0134s) m91q()).iterator());
    }

    /* renamed from: b */
    public final void mo17b() {
        m90a(C0115e.END_ARRAY);
        m92r();
        m92r();
    }

    /* renamed from: c */
    public final void mo18c() {
        m90a(C0115e.BEGIN_OBJECT);
        this.f32c.add(((C0139x) m91q()).mo165h().iterator());
    }

    /* renamed from: d */
    public final void mo20d() {
        m90a(C0115e.END_OBJECT);
        m92r();
        m92r();
    }

    /* renamed from: e */
    public final boolean mo21e() {
        C0115e f = mo22f();
        return (f == C0115e.END_OBJECT || f == C0115e.END_ARRAY) ? false : true;
    }

    /* renamed from: f */
    public final C0115e mo22f() {
        while (!this.f32c.isEmpty()) {
            Object q = m91q();
            if (q instanceof Iterator) {
                boolean z = this.f32c.get(this.f32c.size() - 2) instanceof C0139x;
                Iterator it = (Iterator) q;
                if (!it.hasNext()) {
                    return z ? C0115e.END_OBJECT : C0115e.END_ARRAY;
                }
                if (z) {
                    return C0115e.NAME;
                }
                this.f32c.add(it.next());
            } else if (q instanceof C0139x) {
                return C0115e.BEGIN_OBJECT;
            } else {
                if (q instanceof C0134s) {
                    return C0115e.BEGIN_ARRAY;
                }
                if (q instanceof C0141z) {
                    C0141z zVar = (C0141z) q;
                    if (zVar.mo171j()) {
                        return C0115e.STRING;
                    }
                    if (zVar.mo168h()) {
                        return C0115e.BOOLEAN;
                    }
                    if (zVar.mo170i()) {
                        return C0115e.NUMBER;
                    }
                    throw new AssertionError();
                } else if (q instanceof C0138w) {
                    return C0115e.NULL;
                } else {
                    if (q == f31b) {
                        throw new IllegalStateException("JsonReader is closed");
                    }
                    throw new AssertionError();
                }
            }
        }
        return C0115e.END_DOCUMENT;
    }

    /* renamed from: q */
    private Object m91q() {
        return this.f32c.get(this.f32c.size() - 1);
    }

    /* renamed from: r */
    private Object m92r() {
        return this.f32c.remove(this.f32c.size() - 1);
    }

    /* renamed from: a */
    private void m90a(C0115e eVar) {
        if (mo22f() != eVar) {
            throw new IllegalStateException("Expected " + eVar + " but was " + mo22f());
        }
    }

    /* renamed from: g */
    public final String mo23g() {
        m90a(C0115e.NAME);
        Map.Entry entry = (Map.Entry) ((Iterator) m91q()).next();
        this.f32c.add(entry.getValue());
        return (String) entry.getKey();
    }

    /* renamed from: h */
    public final String mo24h() {
        C0115e f = mo22f();
        if (f == C0115e.STRING || f == C0115e.NUMBER) {
            return ((C0141z) m92r()).mo151b();
        }
        throw new IllegalStateException("Expected " + C0115e.STRING + " but was " + f);
    }

    /* renamed from: i */
    public final boolean mo25i() {
        m90a(C0115e.BOOLEAN);
        return ((C0141z) m92r()).mo156f();
    }

    /* renamed from: j */
    public final void mo26j() {
        m90a(C0115e.NULL);
        m92r();
    }

    /* renamed from: k */
    public final double mo27k() {
        C0115e f = mo22f();
        if (f == C0115e.NUMBER || f == C0115e.STRING) {
            double c = ((C0141z) m91q()).mo152c();
            if (mo131p() || (!Double.isNaN(c) && !Double.isInfinite(c))) {
                m92r();
                return c;
            }
            throw new NumberFormatException("JSON forbids NaN and infinities: " + c);
        }
        throw new IllegalStateException("Expected " + C0115e.NUMBER + " but was " + f);
    }

    /* renamed from: l */
    public final long mo28l() {
        C0115e f = mo22f();
        if (f == C0115e.NUMBER || f == C0115e.STRING) {
            long d = ((C0141z) m91q()).mo153d();
            m92r();
            return d;
        }
        throw new IllegalStateException("Expected " + C0115e.NUMBER + " but was " + f);
    }

    /* renamed from: m */
    public final int mo29m() {
        C0115e f = mo22f();
        if (f == C0115e.NUMBER || f == C0115e.STRING) {
            int e = ((C0141z) m91q()).mo154e();
            m92r();
            return e;
        }
        throw new IllegalStateException("Expected " + C0115e.NUMBER + " but was " + f);
    }

    public final void close() {
        this.f32c.clear();
        this.f32c.add(f31b);
    }

    /* renamed from: n */
    public final void mo30n() {
        if (mo22f() == C0115e.NAME) {
            mo23g();
        } else {
            m92r();
        }
    }

    public final String toString() {
        return getClass().getSimpleName();
    }

    /* renamed from: o */
    public final void mo31o() {
        m90a(C0115e.NAME);
        Map.Entry entry = (Map.Entry) ((Iterator) m91q()).next();
        this.f32c.add(entry.getValue());
        this.f32c.add(new C0141z((String) entry.getKey()));
    }
}
