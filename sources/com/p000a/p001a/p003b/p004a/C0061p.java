package com.p000a.p001a.p003b.p004a;

import com.p000a.p001a.C0010af;
import com.p000a.p001a.C0011ag;
import com.p000a.p001a.C0124i;
import com.p000a.p001a.C0125j;
import com.p000a.p001a.p003b.C0087f;
import com.p000a.p001a.p003b.C0096o;
import com.p000a.p001a.p005c.C0109a;
import java.lang.reflect.Field;

/* renamed from: com.a.a.b.a.p */
/* compiled from: ReflectiveTypeAdapterFactory */
public final class C0061p implements C0011ag {

    /* renamed from: a */
    private final C0087f f47a;

    /* renamed from: b */
    private final C0124i f48b;

    /* renamed from: c */
    private final C0096o f49c;

    public C0061p(C0087f fVar, C0124i iVar, C0096o oVar) {
        this.f47a = fVar;
        this.f48b = iVar;
        this.f49c = oVar;
    }

    /* renamed from: a */
    private boolean m129a(Field field, boolean z) {
        return !this.f49c.mo92a(field.getType(), z) && !this.f49c.mo93a(field, z);
    }

    /* renamed from: a */
    public final <T> C0010af<T> mo11a(C0125j jVar, C0109a<T> aVar) {
        Class<? super T> a = aVar.mo124a();
        if (!Object.class.isAssignableFrom(a)) {
            return null;
        }
        return new C0063r(this, this.f47a.mo89a(aVar), m128a(jVar, aVar, a), (byte) 0);
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v0, resolved type: java.lang.Class<java.lang.Object>} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r17v2, resolved type: com.a.a.c.a<?>} */
    /* JADX WARNING: Incorrect type for immutable var: ssa=java.lang.Class<?>, code=java.lang.Class, for r18v0, types: [java.lang.Class<?>, java.lang.Class] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private java.util.Map<java.lang.String, com.p000a.p001a.p003b.p004a.C0064s> m128a(com.p000a.p001a.C0125j r16, com.p000a.p001a.p005c.C0109a<?> r17, java.lang.Class r18) {
        /*
            r15 = this;
            java.util.LinkedHashMap r10 = new java.util.LinkedHashMap
            r10.<init>()
            boolean r1 = r18.isInterface()
            if (r1 == 0) goto L_0x000d
            r1 = r10
        L_0x000c:
            return r1
        L_0x000d:
            java.lang.reflect.Type r12 = r17.mo125b()
        L_0x0011:
            java.lang.Class<java.lang.Object> r1 = java.lang.Object.class
            r0 = r18
            if (r0 == r1) goto L_0x00b0
            java.lang.reflect.Field[] r13 = r18.getDeclaredFields()
            int r14 = r13.length
            r1 = 0
            r11 = r1
        L_0x001e:
            if (r11 >= r14) goto L_0x0098
            r8 = r13[r11]
            r1 = 1
            boolean r4 = r15.m129a((java.lang.reflect.Field) r8, (boolean) r1)
            r1 = 0
            boolean r5 = r15.m129a((java.lang.reflect.Field) r8, (boolean) r1)
            if (r4 != 0) goto L_0x0030
            if (r5 == 0) goto L_0x0094
        L_0x0030:
            r1 = 1
            r8.setAccessible(r1)
            java.lang.reflect.Type r1 = r17.mo125b()
            java.lang.reflect.Type r2 = r8.getGenericType()
            r0 = r18
            java.lang.reflect.Type r2 = com.p000a.p001a.p003b.C0083b.m164a((java.lang.reflect.Type) r1, (java.lang.Class<?>) r0, (java.lang.reflect.Type) r2)
            java.lang.Class<com.a.a.a.b> r1 = com.p000a.p001a.p002a.C0002b.class
            java.lang.annotation.Annotation r1 = r8.getAnnotation(r1)
            com.a.a.a.b r1 = (com.p000a.p001a.p002a.C0002b) r1
            if (r1 != 0) goto L_0x008f
            com.a.a.i r1 = r15.f48b
            java.lang.String r3 = r1.mo129a(r8)
        L_0x0052:
            com.a.a.c.a r7 = com.p000a.p001a.p005c.C0109a.m211a((java.lang.reflect.Type) r2)
            java.lang.Class r1 = r7.mo124a()
            boolean r9 = com.p000a.p001a.p003b.C0101t.m197a((java.lang.reflect.Type) r1)
            com.a.a.b.a.q r1 = new com.a.a.b.a.q
            r2 = r15
            r6 = r16
            r1.<init>(r2, r3, r4, r5, r6, r7, r8, r9)
            java.lang.String r2 = r1.f59g
            java.lang.Object r1 = r10.put(r2, r1)
            com.a.a.b.a.s r1 = (com.p000a.p001a.p003b.p004a.C0064s) r1
            if (r1 == 0) goto L_0x0094
            java.lang.IllegalArgumentException r2 = new java.lang.IllegalArgumentException
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.StringBuilder r3 = r3.append(r12)
            java.lang.String r4 = " declares multiple JSON fields named "
            java.lang.StringBuilder r3 = r3.append(r4)
            java.lang.String r1 = r1.f59g
            java.lang.StringBuilder r1 = r3.append(r1)
            java.lang.String r1 = r1.toString()
            r2.<init>(r1)
            throw r2
        L_0x008f:
            java.lang.String r3 = r1.mo5a()
            goto L_0x0052
        L_0x0094:
            int r1 = r11 + 1
            r11 = r1
            goto L_0x001e
        L_0x0098:
            java.lang.reflect.Type r1 = r17.mo125b()
            java.lang.reflect.Type r2 = r18.getGenericSuperclass()
            r0 = r18
            java.lang.reflect.Type r1 = com.p000a.p001a.p003b.C0083b.m164a((java.lang.reflect.Type) r1, (java.lang.Class<?>) r0, (java.lang.reflect.Type) r2)
            com.a.a.c.a r17 = com.p000a.p001a.p005c.C0109a.m211a((java.lang.reflect.Type) r1)
            java.lang.Class r18 = r17.mo124a()
            goto L_0x0011
        L_0x00b0:
            r1 = r10
            goto L_0x000c
        */
        throw new UnsupportedOperationException("Method not decompiled: com.p000a.p001a.p003b.p004a.C0061p.m128a(com.a.a.j, com.a.a.c.a, java.lang.Class):java.util.Map");
    }
}
