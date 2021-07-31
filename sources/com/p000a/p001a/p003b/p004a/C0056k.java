package com.p000a.p001a.p003b.p004a;

import com.p000a.p001a.C0010af;
import com.p000a.p001a.C0011ag;
import com.p000a.p001a.C0125j;
import com.p000a.p001a.p003b.C0083b;
import com.p000a.p001a.p003b.C0087f;
import com.p000a.p001a.p003b.C0100s;
import com.p000a.p001a.p005c.C0109a;
import java.lang.reflect.Type;
import java.util.Map;

/* renamed from: com.a.a.b.a.k */
/* compiled from: MapTypeAdapterFactory */
public final class C0056k implements C0011ag {

    /* renamed from: a */
    private final C0087f f38a;
    /* access modifiers changed from: private */

    /* renamed from: b */
    public final boolean f39b = false;

    public C0056k(C0087f fVar) {
        this.f38a = fVar;
    }

    /* renamed from: a */
    public final <T> C0010af<T> mo11a(C0125j jVar, C0109a<T> aVar) {
        C0010af<Boolean> afVar;
        Type b = aVar.mo125b();
        if (!Map.class.isAssignableFrom(aVar.mo124a())) {
            return null;
        }
        Type[] b2 = C0083b.m168b(b, C0083b.m166b(b));
        Type type = b2[0];
        if (type == Boolean.TYPE || type == Boolean.class) {
            afVar = C0070y.f92f;
        } else {
            afVar = jVar.mo141a(C0109a.m211a(type));
        }
        C0010af<?> a = jVar.mo141a(C0109a.m211a(b2[1]));
        C0100s<T> a2 = this.f38a.mo89a(aVar);
        return new C0057l(this, jVar, b2[0], afVar, b2[1], a, a2);
    }
}
