package com.p000a.p001a.p003b.p004a;

import com.p000a.p001a.C0010af;
import com.p000a.p001a.C0011ag;
import com.p000a.p001a.C0125j;
import com.p000a.p001a.p005c.C0109a;

/* renamed from: com.a.a.b.a.ax */
/* compiled from: TypeAdapters */
final class C0038ax implements C0011ag {

    /* renamed from: a */
    final /* synthetic */ Class f17a;

    /* renamed from: b */
    final /* synthetic */ C0010af f18b;

    C0038ax(Class cls, C0010af afVar) {
        this.f17a = cls;
        this.f18b = afVar;
    }

    /* renamed from: a */
    public final <T> C0010af<T> mo11a(C0125j jVar, C0109a<T> aVar) {
        if (this.f17a.isAssignableFrom(aVar.mo124a())) {
            return this.f18b;
        }
        return null;
    }

    public final String toString() {
        return "Factory[typeHierarchy=" + this.f17a.getName() + ",adapter=" + this.f18b + "]";
    }
}
