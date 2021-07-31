package com.p000a.p001a.p003b.p004a;

import com.p000a.p001a.C0010af;
import com.p000a.p001a.p002a.C0002b;
import com.p000a.p001a.p006d.C0111a;
import com.p000a.p001a.p006d.C0115e;
import com.p000a.p001a.p006d.C0116f;
import java.lang.Enum;
import java.util.HashMap;
import java.util.Map;

/* renamed from: com.a.a.b.a.bf */
/* compiled from: TypeAdapters */
final class C0047bf<T extends Enum<T>> extends C0010af<T> {

    /* renamed from: a */
    private final Map<String, T> f20a = new HashMap();

    /* renamed from: b */
    private final Map<T, String> f21b = new HashMap();

    /* renamed from: a */
    public final /* synthetic */ void mo10a(C0116f fVar, Object obj) {
        String str;
        Enum enumR = (Enum) obj;
        if (enumR == null) {
            str = null;
        } else {
            str = this.f21b.get(enumR);
        }
        fVar.mo41b(str);
    }

    public C0047bf(Class<T> cls) {
        String str;
        try {
            for (Enum enumR : (Enum[]) cls.getEnumConstants()) {
                String name = enumR.name();
                C0002b bVar = (C0002b) cls.getField(name).getAnnotation(C0002b.class);
                if (bVar != null) {
                    str = bVar.mo5a();
                } else {
                    str = name;
                }
                this.f20a.put(str, enumR);
                this.f21b.put(enumR, str);
            }
        } catch (NoSuchFieldException e) {
            throw new AssertionError();
        }
    }

    /* renamed from: a */
    public final /* synthetic */ Object mo9a(C0111a aVar) {
        if (aVar.mo22f() != C0115e.NULL) {
            return (Enum) this.f20a.get(aVar.mo24h());
        }
        aVar.mo26j();
        return null;
    }
}
