package com.p000a.p001a.p003b.p004a;

import com.p000a.p001a.C0010af;
import com.p000a.p001a.C0011ag;
import com.p000a.p001a.C0125j;
import com.p000a.p001a.p005c.C0109a;

/* renamed from: com.a.a.b.a.at */
/* compiled from: TypeAdapters */
final class C0034at implements C0011ag {

    /* renamed from: a */
    final /* synthetic */ Class f9a;

    /* renamed from: b */
    final /* synthetic */ C0010af f10b;

    C0034at(Class cls, C0010af afVar) {
        this.f9a = cls;
        this.f10b = afVar;
    }

    /* renamed from: a */
    public final <T> C0010af<T> mo11a(C0125j jVar, C0109a<T> aVar) {
        if (aVar.mo124a() == this.f9a) {
            return this.f10b;
        }
        return null;
    }

    public final String toString() {
        return "Factory[type=" + this.f9a.getName() + ",adapter=" + this.f10b + "]";
    }
}
