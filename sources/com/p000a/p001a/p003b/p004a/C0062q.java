package com.p000a.p001a.p003b.p004a;

import com.p000a.p001a.C0010af;
import com.p000a.p001a.C0125j;
import com.p000a.p001a.p005c.C0109a;
import com.p000a.p001a.p006d.C0111a;
import com.p000a.p001a.p006d.C0116f;
import java.lang.reflect.Field;

/* renamed from: com.a.a.b.a.q */
/* compiled from: ReflectiveTypeAdapterFactory */
final class C0062q extends C0064s {

    /* renamed from: a */
    final C0010af<?> f50a = this.f51b.mo141a(this.f52c);

    /* renamed from: b */
    final /* synthetic */ C0125j f51b;

    /* renamed from: c */
    final /* synthetic */ C0109a f52c;

    /* renamed from: d */
    final /* synthetic */ Field f53d;

    /* renamed from: e */
    final /* synthetic */ boolean f54e;

    /* renamed from: f */
    final /* synthetic */ C0061p f55f;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    C0062q(C0061p pVar, String str, boolean z, boolean z2, C0125j jVar, C0109a aVar, Field field, boolean z3) {
        super(str, z, z2);
        this.f55f = pVar;
        this.f51b = jVar;
        this.f52c = aVar;
        this.f53d = field;
        this.f54e = z3;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public final void mo52a(C0116f fVar, Object obj) {
        new C0069x(this.f51b, this.f50a, this.f52c.mo125b()).mo10a(fVar, this.f53d.get(obj));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public final void mo51a(C0111a aVar, Object obj) {
        Object a = this.f50a.mo9a(aVar);
        if (a != null || !this.f54e) {
            this.f53d.set(obj, a);
        }
    }
}
