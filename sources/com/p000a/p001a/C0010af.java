package com.p000a.p001a;

import com.p000a.p001a.p003b.p004a.C0054i;
import com.p000a.p001a.p006d.C0111a;
import com.p000a.p001a.p006d.C0116f;
import java.io.IOException;

/* renamed from: com.a.a.af */
/* compiled from: TypeAdapter */
public abstract class C0010af<T> {
    /* renamed from: a */
    public abstract T mo9a(C0111a aVar);

    /* renamed from: a */
    public abstract void mo10a(C0116f fVar, T t);

    /* renamed from: a */
    public final C0136u mo8a(T t) {
        try {
            C0054i iVar = new C0054i();
            mo10a(iVar, t);
            return iVar.mo39a();
        } catch (IOException e) {
            throw new C0137v((Throwable) e);
        }
    }
}
