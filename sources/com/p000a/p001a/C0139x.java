package com.p000a.p001a;

import com.p000a.p001a.p003b.C0013a;
import com.p000a.p001a.p003b.C0105x;
import java.util.Map;
import java.util.Set;

/* renamed from: com.a.a.x */
/* compiled from: JsonObject */
public final class C0139x extends C0136u {

    /* renamed from: a */
    private final C0105x<C0136u> f262a = new C0105x<>();

    /* renamed from: a */
    public final void mo163a(String str, C0136u uVar) {
        if (uVar == null) {
            uVar = C0138w.f261a;
        }
        this.f262a.put((String) C0013a.m11a(str), uVar);
    }

    /* renamed from: h */
    public final Set<Map.Entry<String, C0136u>> mo165h() {
        return this.f262a.entrySet();
    }

    public final boolean equals(Object obj) {
        return obj == this || ((obj instanceof C0139x) && ((C0139x) obj).f262a.equals(this.f262a));
    }

    public final int hashCode() {
        return this.f262a.hashCode();
    }
}
