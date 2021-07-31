package com.p000a.p001a.p003b;

import com.p000a.p001a.C0000a;
import com.p000a.p001a.C0010af;
import com.p000a.p001a.C0011ag;
import com.p000a.p001a.C0012b;
import com.p000a.p001a.C0125j;
import com.p000a.p001a.p002a.C0001a;
import com.p000a.p001a.p002a.C0003c;
import com.p000a.p001a.p002a.C0004d;
import com.p000a.p001a.p005c.C0109a;
import java.lang.reflect.Field;
import java.util.Collections;
import java.util.List;

/* renamed from: com.a.a.b.o */
/* compiled from: Excluder */
public final class C0096o implements C0011ag, Cloneable {

    /* renamed from: a */
    public static final C0096o f153a = new C0096o();

    /* renamed from: b */
    private double f154b = -1.0d;

    /* renamed from: c */
    private int f155c = 136;

    /* renamed from: d */
    private boolean f156d = true;

    /* renamed from: e */
    private boolean f157e;

    /* renamed from: f */
    private List<C0000a> f158f = Collections.emptyList();

    /* renamed from: g */
    private List<C0000a> f159g = Collections.emptyList();

    /* access modifiers changed from: private */
    /* renamed from: a */
    public C0096o clone() {
        try {
            return (C0096o) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }

    /* renamed from: a */
    public final <T> C0010af<T> mo11a(C0125j jVar, C0109a<T> aVar) {
        Class<? super T> a = aVar.mo124a();
        boolean a2 = mo92a((Class<?>) a, true);
        boolean a3 = mo92a((Class<?>) a, false);
        if (a2 || a3) {
            return new C0097p(this, a3, a2, jVar, aVar);
        }
        return null;
    }

    /* renamed from: a */
    public final boolean mo93a(Field field, boolean z) {
        C0001a aVar;
        if ((this.f155c & field.getModifiers()) != 0) {
            return true;
        }
        if (this.f154b != -1.0d && !m184a((C0003c) field.getAnnotation(C0003c.class), (C0004d) field.getAnnotation(C0004d.class))) {
            return true;
        }
        if (field.isSynthetic()) {
            return true;
        }
        if (this.f157e && ((aVar = (C0001a) field.getAnnotation(C0001a.class)) == null || (!z ? !aVar.mo4b() : !aVar.mo3a()))) {
            return true;
        }
        if (!this.f156d && m186b(field.getType())) {
            return true;
        }
        if (m185a(field.getType())) {
            return true;
        }
        List<C0000a> list = z ? this.f158f : this.f159g;
        if (!list.isEmpty()) {
            new C0012b(field);
            for (C0000a a : list) {
                if (a.mo1a()) {
                    return true;
                }
            }
        }
        return false;
    }

    /* renamed from: a */
    public final boolean mo92a(Class<?> cls, boolean z) {
        if (this.f154b != -1.0d && !m184a((C0003c) cls.getAnnotation(C0003c.class), (C0004d) cls.getAnnotation(C0004d.class))) {
            return true;
        }
        if (!this.f156d && m186b(cls)) {
            return true;
        }
        if (m185a(cls)) {
            return true;
        }
        for (C0000a b : z ? this.f158f : this.f159g) {
            if (b.mo2b()) {
                return true;
            }
        }
        return false;
    }

    /* renamed from: a */
    private static boolean m185a(Class<?> cls) {
        return !Enum.class.isAssignableFrom(cls) && (cls.isAnonymousClass() || cls.isLocalClass());
    }

    /* renamed from: b */
    private static boolean m186b(Class<?> cls) {
        boolean z;
        if (cls.isMemberClass()) {
            if ((cls.getModifiers() & 8) != 0) {
                z = true;
            } else {
                z = false;
            }
            if (!z) {
                return true;
            }
        }
        return false;
    }

    /* renamed from: a */
    private boolean m184a(C0003c cVar, C0004d dVar) {
        boolean z;
        boolean z2;
        if (cVar == null || cVar.mo6a() <= this.f154b) {
            z = true;
        } else {
            z = false;
        }
        if (z) {
            if (dVar == null || dVar.mo7a() > this.f154b) {
                z2 = true;
            } else {
                z2 = false;
            }
            if (z2) {
                return true;
            }
        }
        return false;
    }
}
