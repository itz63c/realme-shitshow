package com.p000a.p001a.p003b;

import java.io.Writer;

/* renamed from: com.a.a.b.v */
/* compiled from: Streams */
final class C0103v extends Writer {

    /* renamed from: a */
    private final Appendable f170a;

    /* renamed from: b */
    private final C0104w f171b;

    /* synthetic */ C0103v(Appendable appendable, byte b) {
        this(appendable);
    }

    private C0103v(Appendable appendable) {
        this.f171b = new C0104w();
        this.f170a = appendable;
    }

    public final void write(char[] cArr, int i, int i2) {
        this.f171b.f172a = cArr;
        this.f170a.append(this.f171b, i, i + i2);
    }

    public final void write(int i) {
        this.f170a.append((char) i);
    }

    public final void flush() {
    }

    public final void close() {
    }
}
