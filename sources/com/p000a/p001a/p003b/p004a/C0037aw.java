package com.p000a.p001a.p003b.p004a;

import com.p000a.p001a.C0010af;
import com.p000a.p001a.C0011ag;
import com.p000a.p001a.C0125j;
import com.p000a.p001a.p005c.C0109a;

/* renamed from: com.a.a.b.a.aw */
/* compiled from: TypeAdapters */
final class C0037aw implements C0011ag {

    /* renamed from: a */
    final /* synthetic */ Class f14a;

    /* renamed from: b */
    final /* synthetic */ Class f15b;

    /* renamed from: c */
    final /* synthetic */ C0010af f16c;

    C0037aw(Class cls, Class cls2, C0010af afVar) {
        this.f14a = cls;
        this.f15b = cls2;
        this.f16c = afVar;
    }

    /* renamed from: a */
    public final <T> C0010af<T> mo11a(C0125j jVar, C0109a<T> aVar) {
        Class<? super T> a = aVar.mo124a();
        if (a == this.f14a || a == this.f15b) {
            return this.f16c;
        }
        return null;
    }

    public final String toString() {
        return "Factory[type=" + this.f14a.getName() + "+" + this.f15b.getName() + ",adapter=" + this.f16c + "]";
    }
}
