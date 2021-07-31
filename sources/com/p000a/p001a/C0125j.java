package com.p000a.p001a;

import com.p000a.p001a.p003b.C0087f;
import com.p000a.p001a.p003b.C0096o;
import com.p000a.p001a.p003b.C0101t;
import com.p000a.p001a.p003b.C0102u;
import com.p000a.p001a.p003b.p004a.C0014a;
import com.p000a.p001a.p003b.p004a.C0048c;
import com.p000a.p001a.p003b.p004a.C0050e;
import com.p000a.p001a.p003b.p004a.C0056k;
import com.p000a.p001a.p003b.p004a.C0058m;
import com.p000a.p001a.p003b.p004a.C0061p;
import com.p000a.p001a.p003b.p004a.C0065t;
import com.p000a.p001a.p003b.p004a.C0067v;
import com.p000a.p001a.p003b.p004a.C0070y;
import com.p000a.p001a.p005c.C0109a;
import com.p000a.p001a.p006d.C0111a;
import com.p000a.p001a.p006d.C0115e;
import com.p000a.p001a.p006d.C0116f;
import com.p000a.p001a.p006d.C0118h;
import java.io.EOFException;
import java.io.IOException;
import java.io.StringReader;
import java.io.Writer;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* renamed from: com.a.a.j */
/* compiled from: Gson */
public final class C0125j {

    /* renamed from: a */
    final C0135t f243a;

    /* renamed from: b */
    final C0005aa f244b;

    /* renamed from: c */
    private final ThreadLocal<Map<C0109a<?>, C0132q<?>>> f245c;

    /* renamed from: d */
    private final Map<C0109a<?>, C0010af<?>> f246d;

    /* renamed from: e */
    private final List<C0011ag> f247e;

    /* renamed from: f */
    private final C0087f f248f;

    /* renamed from: g */
    private final boolean f249g;

    /* renamed from: h */
    private final boolean f250h;

    /* renamed from: i */
    private final boolean f251i;

    /* renamed from: j */
    private final boolean f252j;

    public C0125j() {
        this(C0096o.f153a, C0108c.f184a, Collections.emptyMap(), C0007ac.f0a, Collections.emptyList());
    }

    private C0125j(C0096o oVar, C0124i iVar, Map<Type, C0133r<?>> map, C0007ac acVar, List<C0011ag> list) {
        C0010af pVar;
        this.f245c = new C0126k(this);
        this.f246d = Collections.synchronizedMap(new HashMap());
        this.f243a = new C0127l(this);
        this.f244b = new C0128m(this);
        this.f248f = new C0087f(map);
        this.f249g = false;
        this.f251i = false;
        this.f250h = true;
        this.f252j = false;
        ArrayList arrayList = new ArrayList();
        arrayList.add(C0070y.f85Q);
        arrayList.add(C0058m.f44a);
        arrayList.addAll(list);
        arrayList.add(C0070y.f110x);
        arrayList.add(C0070y.f99m);
        arrayList.add(C0070y.f93g);
        arrayList.add(C0070y.f95i);
        arrayList.add(C0070y.f97k);
        Class cls = Long.TYPE;
        Class<Long> cls2 = Long.class;
        if (acVar == C0007ac.f0a) {
            pVar = C0070y.f100n;
        } else {
            pVar = new C0131p(this);
        }
        arrayList.add(C0070y.m150a(cls, cls2, pVar));
        arrayList.add(C0070y.m150a(Double.TYPE, Double.class, new C0129n(this)));
        arrayList.add(C0070y.m150a(Float.TYPE, Float.class, new C0130o(this)));
        arrayList.add(C0070y.f104r);
        arrayList.add(C0070y.f106t);
        arrayList.add(C0070y.f112z);
        arrayList.add(C0070y.f70B);
        arrayList.add(C0070y.m149a(BigDecimal.class, C0070y.f108v));
        arrayList.add(C0070y.m149a(BigInteger.class, C0070y.f109w));
        arrayList.add(C0070y.f72D);
        arrayList.add(C0070y.f74F);
        arrayList.add(C0070y.f78J);
        arrayList.add(C0070y.f83O);
        arrayList.add(C0070y.f76H);
        arrayList.add(C0070y.f90d);
        arrayList.add(C0050e.f26a);
        arrayList.add(C0070y.f81M);
        arrayList.add(C0067v.f64a);
        arrayList.add(C0065t.f62a);
        arrayList.add(C0070y.f79K);
        arrayList.add(C0014a.f4a);
        arrayList.add(C0070y.f86R);
        arrayList.add(C0070y.f88b);
        arrayList.add(oVar);
        arrayList.add(new C0048c(this.f248f));
        arrayList.add(new C0056k(this.f248f));
        arrayList.add(new C0061p(this.f248f, iVar, oVar));
        this.f247e = Collections.unmodifiableList(arrayList);
    }

    /* JADX INFO: finally extract failed */
    /* renamed from: a */
    public final <T> C0010af<T> mo141a(C0109a<T> aVar) {
        C0010af<T> afVar = this.f246d.get(aVar);
        if (afVar != null) {
            return afVar;
        }
        Map map = this.f245c.get();
        C0132q qVar = (C0132q) map.get(aVar);
        if (qVar != null) {
            return qVar;
        }
        C0132q qVar2 = new C0132q();
        map.put(aVar, qVar2);
        try {
            for (C0011ag a : this.f247e) {
                C0010af<T> a2 = a.mo11a(this, aVar);
                if (a2 != null) {
                    qVar2.mo147a(a2);
                    this.f246d.put(aVar, a2);
                    map.remove(aVar);
                    return a2;
                }
            }
            throw new IllegalArgumentException("GSON cannot handle " + aVar);
        } catch (Throwable th) {
            map.remove(aVar);
            throw th;
        }
    }

    /* renamed from: a */
    public final <T> C0010af<T> mo140a(C0011ag agVar, C0109a<T> aVar) {
        boolean z = false;
        for (C0011ag next : this.f247e) {
            if (z) {
                C0010af<T> a = next.mo11a(this, aVar);
                if (a != null) {
                    return a;
                }
            } else if (next == agVar) {
                z = true;
            }
        }
        throw new IllegalArgumentException("GSON cannot serialize " + aVar);
    }

    /* renamed from: a */
    public final <T> C0010af<T> mo142a(Class<T> cls) {
        return mo141a(C0109a.m210a(cls));
    }

    /* renamed from: a */
    public final void mo144a(Object obj, Type type, Appendable appendable) {
        try {
            Writer a = C0102u.m198a(appendable);
            if (this.f251i) {
                a.write(")]}'\n");
            }
            C0116f fVar = new C0116f(a);
            if (this.f252j) {
                fVar.mo133c("  ");
            }
            fVar.mo135d(this.f249g);
            C0010af<?> a2 = mo141a(C0109a.m211a(type));
            boolean g = fVar.mo136g();
            fVar.mo132b(true);
            boolean h = fVar.mo137h();
            fVar.mo134c(this.f250h);
            boolean i = fVar.mo138i();
            fVar.mo135d(this.f249g);
            try {
                a2.mo10a(fVar, obj);
                fVar.mo132b(g);
                fVar.mo134c(h);
                fVar.mo135d(i);
            } catch (IOException e) {
                throw new C0137v((Throwable) e);
            } catch (Throwable th) {
                fVar.mo132b(g);
                fVar.mo134c(h);
                fVar.mo135d(i);
                throw th;
            }
        } catch (IOException e2) {
            throw new C0137v((Throwable) e2);
        }
    }

    /* renamed from: a */
    private <T> T m286a(C0111a aVar, Type type) {
        boolean z = true;
        boolean p = aVar.mo131p();
        aVar.mo130a(true);
        try {
            aVar.mo22f();
            z = false;
            T a = mo141a(C0109a.m211a(type)).mo9a(aVar);
            aVar.mo130a(p);
            return a;
        } catch (EOFException e) {
            if (z) {
                aVar.mo130a(p);
                return null;
            }
            throw new C0006ab((Throwable) e);
        } catch (IllegalStateException e2) {
            throw new C0006ab((Throwable) e2);
        } catch (IOException e3) {
            throw new C0006ab((Throwable) e3);
        } catch (Throwable th) {
            aVar.mo130a(p);
            throw th;
        }
    }

    public final String toString() {
        return "{serializeNulls:" + this.f249g + "factories:" + this.f247e + ",instanceCreators:" + this.f248f + "}";
    }

    /* renamed from: a */
    public final <T> T mo143a(String str, Class<T> cls) {
        T a;
        if (str == null) {
            a = null;
        } else {
            C0111a aVar = new C0111a(new StringReader(str));
            a = m286a(aVar, (Type) cls);
            if (a != null) {
                try {
                    if (aVar.mo22f() != C0115e.END_DOCUMENT) {
                        throw new C0137v("JSON document was not fully consumed.");
                    }
                } catch (C0118h e) {
                    throw new C0006ab((Throwable) e);
                } catch (IOException e2) {
                    throw new C0137v((Throwable) e2);
                }
            }
        }
        return C0101t.m195a(cls).cast(a);
    }

    /* renamed from: a */
    static /* synthetic */ void m287a(double d) {
        if (Double.isNaN(d) || Double.isInfinite(d)) {
            throw new IllegalArgumentException(d + " is not a valid double value as per JSON specification. To override this behavior, use GsonBuilder.serializeSpecialDoubleValues() method.");
        }
    }
}
