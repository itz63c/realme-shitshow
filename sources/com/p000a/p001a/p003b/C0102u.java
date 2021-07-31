package com.p000a.p001a.p003b;

import com.p000a.p001a.C0136u;
import com.p000a.p001a.p003b.p004a.C0070y;
import com.p000a.p001a.p006d.C0116f;
import java.io.Writer;

/* renamed from: com.a.a.b.u */
/* compiled from: Streams */
public final class C0102u {
    /* renamed from: a */
    public static void m199a(C0136u uVar, C0116f fVar) {
        C0070y.f84P.mo10a(fVar, uVar);
    }

    /* renamed from: a */
    public static Writer m198a(Appendable appendable) {
        return appendable instanceof Writer ? (Writer) appendable : new C0103v(appendable, (byte) 0);
    }
}
