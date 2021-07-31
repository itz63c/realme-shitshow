package com.p000a.p001a.p003b.p004a;

import com.p000a.p001a.C0134s;
import com.p000a.p001a.C0136u;
import com.p000a.p001a.C0138w;
import com.p000a.p001a.C0139x;
import com.p000a.p001a.C0141z;
import com.p000a.p001a.p006d.C0116f;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

/* renamed from: com.a.a.b.a.i */
/* compiled from: JsonTreeWriter */
public final class C0054i extends C0116f {

    /* renamed from: a */
    private static final Writer f33a = new C0055j();

    /* renamed from: b */
    private static final C0141z f34b = new C0141z("closed");

    /* renamed from: c */
    private final List<C0136u> f35c = new ArrayList();

    /* renamed from: d */
    private String f36d;

    /* renamed from: e */
    private C0136u f37e = C0138w.f261a;

    public C0054i() {
        super(f33a);
    }

    /* renamed from: a */
    public final C0136u mo39a() {
        if (this.f35c.isEmpty()) {
            return this.f37e;
        }
        throw new IllegalStateException("Expected one JSON element but was " + this.f35c);
    }

    /* renamed from: j */
    private C0136u m109j() {
        return this.f35c.get(this.f35c.size() - 1);
    }

    /* renamed from: a */
    private void m108a(C0136u uVar) {
        if (this.f36d != null) {
            if (!(uVar instanceof C0138w) || mo138i()) {
                ((C0139x) m109j()).mo163a(this.f36d, uVar);
            }
            this.f36d = null;
        } else if (this.f35c.isEmpty()) {
            this.f37e = uVar;
        } else {
            C0136u j = m109j();
            if (j instanceof C0134s) {
                ((C0134s) j).mo150a(uVar);
                return;
            }
            throw new IllegalStateException();
        }
    }

    /* renamed from: b */
    public final C0116f mo40b() {
        C0134s sVar = new C0134s();
        m108a((C0136u) sVar);
        this.f35c.add(sVar);
        return this;
    }

    /* renamed from: c */
    public final C0116f mo42c() {
        if (this.f35c.isEmpty() || this.f36d != null) {
            throw new IllegalStateException();
        } else if (m109j() instanceof C0134s) {
            this.f35c.remove(this.f35c.size() - 1);
            return this;
        } else {
            throw new IllegalStateException();
        }
    }

    /* renamed from: d */
    public final C0116f mo44d() {
        C0139x xVar = new C0139x();
        m108a((C0136u) xVar);
        this.f35c.add(xVar);
        return this;
    }

    /* renamed from: e */
    public final C0116f mo45e() {
        if (this.f35c.isEmpty() || this.f36d != null) {
            throw new IllegalStateException();
        } else if (m109j() instanceof C0139x) {
            this.f35c.remove(this.f35c.size() - 1);
            return this;
        } else {
            throw new IllegalStateException();
        }
    }

    /* renamed from: a */
    public final C0116f mo37a(String str) {
        if (this.f35c.isEmpty() || this.f36d != null) {
            throw new IllegalStateException();
        } else if (m109j() instanceof C0139x) {
            this.f36d = str;
            return this;
        } else {
            throw new IllegalStateException();
        }
    }

    /* renamed from: b */
    public final C0116f mo41b(String str) {
        if (str == null) {
            return mo46f();
        }
        m108a((C0136u) new C0141z(str));
        return this;
    }

    /* renamed from: f */
    public final C0116f mo46f() {
        m108a((C0136u) C0138w.f261a);
        return this;
    }

    /* renamed from: a */
    public final C0116f mo38a(boolean z) {
        m108a((C0136u) new C0141z(Boolean.valueOf(z)));
        return this;
    }

    /* renamed from: a */
    public final C0116f mo35a(long j) {
        m108a((C0136u) new C0141z((Number) Long.valueOf(j)));
        return this;
    }

    /* renamed from: a */
    public final C0116f mo36a(Number number) {
        if (number == null) {
            return mo46f();
        }
        if (!mo136g()) {
            double doubleValue = number.doubleValue();
            if (Double.isNaN(doubleValue) || Double.isInfinite(doubleValue)) {
                throw new IllegalArgumentException("JSON forbids NaN and infinities: " + number);
            }
        }
        m108a((C0136u) new C0141z(number));
        return this;
    }

    public final void flush() {
    }

    public final void close() {
        if (!this.f35c.isEmpty()) {
            throw new IOException("Incomplete document");
        }
        this.f35c.add(f34b);
    }
}
