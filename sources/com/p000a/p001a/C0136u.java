package com.p000a.p001a;

import com.p000a.p001a.p003b.C0102u;
import com.p000a.p001a.p006d.C0116f;
import java.io.IOException;
import java.io.StringWriter;

/* renamed from: com.a.a.u */
/* compiled from: JsonElement */
public abstract class C0136u {
    /* renamed from: f */
    public boolean mo156f() {
        throw new UnsupportedOperationException(getClass().getSimpleName());
    }

    /* renamed from: a */
    public Number mo149a() {
        throw new UnsupportedOperationException(getClass().getSimpleName());
    }

    /* renamed from: b */
    public String mo151b() {
        throw new UnsupportedOperationException(getClass().getSimpleName());
    }

    /* renamed from: c */
    public double mo152c() {
        throw new UnsupportedOperationException(getClass().getSimpleName());
    }

    /* renamed from: d */
    public long mo153d() {
        throw new UnsupportedOperationException(getClass().getSimpleName());
    }

    /* renamed from: e */
    public int mo154e() {
        throw new UnsupportedOperationException(getClass().getSimpleName());
    }

    public String toString() {
        try {
            StringWriter stringWriter = new StringWriter();
            C0116f fVar = new C0116f(stringWriter);
            fVar.mo132b(true);
            C0102u.m199a(this, fVar);
            return stringWriter.toString();
        } catch (IOException e) {
            throw new AssertionError(e);
        }
    }

    /* renamed from: g */
    public final C0141z mo159g() {
        if (this instanceof C0141z) {
            return (C0141z) this;
        }
        throw new IllegalStateException("This is not a JSON Primitive.");
    }
}
