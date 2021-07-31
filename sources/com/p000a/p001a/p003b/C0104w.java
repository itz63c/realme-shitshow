package com.p000a.p001a.p003b;

/* renamed from: com.a.a.b.w */
/* compiled from: Streams */
final class C0104w implements CharSequence {

    /* renamed from: a */
    char[] f172a;

    C0104w() {
    }

    public final int length() {
        return this.f172a.length;
    }

    public final char charAt(int i) {
        return this.f172a[i];
    }

    public final CharSequence subSequence(int i, int i2) {
        return new String(this.f172a, i, i2 - i);
    }
}
