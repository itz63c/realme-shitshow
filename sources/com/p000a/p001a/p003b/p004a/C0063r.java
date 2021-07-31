package com.p000a.p001a.p003b.p004a;

import com.p000a.p001a.C0006ab;
import com.p000a.p001a.C0010af;
import com.p000a.p001a.p003b.C0100s;
import com.p000a.p001a.p006d.C0111a;
import com.p000a.p001a.p006d.C0115e;
import com.p000a.p001a.p006d.C0116f;
import java.util.Map;

/* renamed from: com.a.a.b.a.r */
/* compiled from: ReflectiveTypeAdapterFactory */
public final class C0063r<T> extends C0010af<T> {

    /* renamed from: a */
    final /* synthetic */ C0061p f56a;

    /* renamed from: b */
    private final C0100s<T> f57b;

    /* renamed from: c */
    private final Map<String, C0064s> f58c;

    /* synthetic */ C0063r(C0061p pVar, C0100s sVar, Map map, byte b) {
        this(pVar, sVar, map);
    }

    private C0063r(C0061p pVar, C0100s<T> sVar, Map<String, C0064s> map) {
        this.f56a = pVar;
        this.f57b = sVar;
        this.f58c = map;
    }

    /* renamed from: a */
    public final T mo9a(C0111a aVar) {
        if (aVar.mo22f() == C0115e.NULL) {
            aVar.mo26j();
            return null;
        }
        T a = this.f57b.mo91a();
        try {
            aVar.mo18c();
            while (aVar.mo21e()) {
                C0064s sVar = this.f58c.get(aVar.mo23g());
                if (sVar == null || !sVar.f61i) {
                    aVar.mo30n();
                } else {
                    sVar.mo51a(aVar, (Object) a);
                }
            }
            aVar.mo20d();
            return a;
        } catch (IllegalStateException e) {
            throw new C0006ab((Throwable) e);
        } catch (IllegalAccessException e2) {
            throw new AssertionError(e2);
        }
    }

    /* renamed from: a */
    public final void mo10a(C0116f fVar, T t) {
        if (t == null) {
            fVar.mo46f();
            return;
        }
        fVar.mo44d();
        try {
            for (C0064s next : this.f58c.values()) {
                if (next.f60h) {
                    fVar.mo37a(next.f59g);
                    next.mo52a(fVar, (Object) t);
                }
            }
            fVar.mo45e();
        } catch (IllegalAccessException e) {
            throw new AssertionError();
        }
    }
}
