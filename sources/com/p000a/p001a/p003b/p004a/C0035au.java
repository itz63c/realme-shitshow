package com.p000a.p001a.p003b.p004a;

import com.p000a.p001a.C0010af;
import com.p000a.p001a.C0011ag;
import com.p000a.p001a.C0125j;
import com.p000a.p001a.p005c.C0109a;

/* renamed from: com.a.a.b.a.au */
/* compiled from: TypeAdapters */
final class C0035au implements C0011ag {

    /* renamed from: a */
    final /* synthetic */ Class f11a;

    /* renamed from: b */
    final /* synthetic */ Class f12b;

    /* renamed from: c */
    final /* synthetic */ C0010af f13c;

    C0035au(Class cls, Class cls2, C0010af afVar) {
        this.f11a = cls;
        this.f12b = cls2;
        this.f13c = afVar;
    }

    /* renamed from: a */
    public final <T> C0010af<T> mo11a(C0125j jVar, C0109a<T> aVar) {
        Class<? super T> a = aVar.mo124a();
        if (a == this.f11a || a == this.f12b) {
            return this.f13c;
        }
        return null;
    }

    public final String toString() {
        return "Factory[type=" + this.f12b.getName() + "+" + this.f11a.getName() + ",adapter=" + this.f13c + "]";
    }
}
