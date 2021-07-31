package com.p000a.p001a.p003b;

import java.util.AbstractMap;
import java.util.Arrays;
import java.util.Collection;
import java.util.Map;
import java.util.Random;
import java.util.Set;

/* renamed from: com.a.a.b.x */
/* compiled from: StringMap */
public final class C0105x<V> extends AbstractMap<String, V> {

    /* renamed from: b */
    private static final Map.Entry[] f173b = new C0074ac[2];

    /* renamed from: i */
    private static final int f174i = new Random().nextInt();
    /* access modifiers changed from: private */

    /* renamed from: a */
    public C0074ac<V> f175a = new C0074ac<>();

    /* renamed from: c */
    private C0074ac<V>[] f176c = ((C0074ac[]) f173b);
    /* access modifiers changed from: private */

    /* renamed from: d */
    public int f177d;

    /* renamed from: e */
    private int f178e = -1;

    /* renamed from: f */
    private Set<String> f179f;

    /* renamed from: g */
    private Set<Map.Entry<String, V>> f180g;

    /* renamed from: h */
    private Collection<V> f181h;

    public final int size() {
        return this.f177d;
    }

    public final boolean containsKey(Object obj) {
        return (obj instanceof String) && m201a((String) obj) != null;
    }

    public final V get(Object obj) {
        C0074ac a;
        if (!(obj instanceof String) || (a = m201a((String) obj)) == null) {
            return null;
        }
        return a.f116b;
    }

    /* renamed from: a */
    private C0074ac<V> m201a(String str) {
        if (str == null) {
            return null;
        }
        int b = m206b(str);
        C0074ac<V>[] acVarArr = this.f176c;
        for (C0074ac<V> acVar = acVarArr[(acVarArr.length - 1) & b]; acVar != null; acVar = acVar.f118d) {
            String str2 = acVar.f115a;
            if (str2 == str || (acVar.f117c == b && str.equals(str2))) {
                return acVar;
            }
        }
        return null;
    }

    /* renamed from: a */
    public final V put(String str, V v) {
        int i;
        if (str == null) {
            throw new NullPointerException("key == null");
        }
        int b = m206b(str);
        C0074ac<V>[] acVarArr = this.f176c;
        int length = (acVarArr.length - 1) & b;
        C0074ac<V> acVar = acVarArr[length];
        while (acVar != null) {
            if (acVar.f117c != b || !str.equals(acVar.f115a)) {
                acVar = acVar.f118d;
            } else {
                V v2 = acVar.f116b;
                acVar.f116b = v;
                return v2;
            }
        }
        int i2 = this.f177d;
        this.f177d = i2 + 1;
        if (i2 > this.f178e) {
            i = (m204a().length - 1) & b;
        } else {
            i = length;
        }
        C0074ac<V> acVar2 = this.f175a;
        C0074ac<V> acVar3 = acVar2.f120f;
        C0074ac<V> acVar4 = new C0074ac<>(str, v, b, this.f176c[i], acVar2, acVar3);
        C0074ac<V>[] acVarArr2 = this.f176c;
        acVar2.f120f = acVar4;
        acVar3.f119e = acVar4;
        acVarArr2[i] = acVar4;
        return null;
    }

    /* renamed from: a */
    private C0074ac<V>[] m204a() {
        C0074ac<V>[] acVarArr = this.f176c;
        int length = acVarArr.length;
        if (length == 1073741824) {
            return acVarArr;
        }
        int i = length * 2;
        C0074ac<V>[] acVarArr2 = (C0074ac[]) new C0074ac[i];
        this.f176c = acVarArr2;
        this.f178e = (i >> 2) + (i >> 1);
        if (this.f177d == 0) {
            return acVarArr2;
        }
        for (int i2 = 0; i2 < length; i2++) {
            C0074ac<V> acVar = acVarArr[i2];
            if (acVar != null) {
                int i3 = acVar.f117c & length;
                acVarArr2[i2 | i3] = acVar;
                C0074ac<V> acVar2 = null;
                for (C0074ac<V> acVar3 = acVar.f118d; acVar3 != null; acVar3 = acVar3.f118d) {
                    int i4 = acVar3.f117c & length;
                    if (i4 != i3) {
                        if (acVar2 == null) {
                            acVarArr2[i2 | i4] = acVar3;
                        } else {
                            acVar2.f118d = acVar3;
                        }
                        acVar2 = acVar;
                    } else {
                        i4 = i3;
                    }
                    i3 = i4;
                    acVar = acVar3;
                }
                if (acVar2 != null) {
                    acVar2.f118d = null;
                }
            }
        }
        return acVarArr2;
    }

    public final V remove(Object obj) {
        if (obj == null || !(obj instanceof String)) {
            return null;
        }
        int b = m206b((String) obj);
        C0074ac<V>[] acVarArr = this.f176c;
        int length = b & (acVarArr.length - 1);
        C0074ac<V> acVar = acVarArr[length];
        C0074ac<V> acVar2 = null;
        while (acVar != null) {
            if (acVar.f117c != b || !obj.equals(acVar.f115a)) {
                acVar2 = acVar;
                acVar = acVar.f118d;
            } else {
                if (acVar2 == null) {
                    acVarArr[length] = acVar.f118d;
                } else {
                    acVar2.f118d = acVar.f118d;
                }
                this.f177d--;
                m202a(acVar);
                return acVar.f116b;
            }
        }
        return null;
    }

    /* renamed from: a */
    private static void m202a(C0074ac<V> acVar) {
        acVar.f120f.f119e = acVar.f119e;
        acVar.f119e.f120f = acVar.f120f;
        acVar.f120f = null;
        acVar.f119e = null;
    }

    public final void clear() {
        if (this.f177d != 0) {
            Arrays.fill(this.f176c, (Object) null);
            this.f177d = 0;
        }
        C0074ac<V> acVar = this.f175a;
        C0074ac<V> acVar2 = acVar.f119e;
        while (acVar2 != acVar) {
            C0074ac<V> acVar3 = acVar2.f119e;
            acVar2.f120f = null;
            acVar2.f119e = null;
            acVar2 = acVar3;
        }
        acVar.f120f = acVar;
        acVar.f119e = acVar;
    }

    public final Set<String> keySet() {
        Set<String> set = this.f179f;
        if (set != null) {
            return set;
        }
        C0072aa aaVar = new C0072aa(this, (byte) 0);
        this.f179f = aaVar;
        return aaVar;
    }

    public final Collection<V> values() {
        Collection<V> collection = this.f181h;
        if (collection != null) {
            return collection;
        }
        C0076ae aeVar = new C0076ae(this, (byte) 0);
        this.f181h = aeVar;
        return aeVar;
    }

    public final Set<Map.Entry<String, V>> entrySet() {
        Set<Map.Entry<String, V>> set = this.f180g;
        if (set != null) {
            return set;
        }
        C0106y yVar = new C0106y(this, (byte) 0);
        this.f180g = yVar;
        return yVar;
    }

    /* renamed from: b */
    private static int m206b(String str) {
        int i = f174i;
        for (int i2 = 0; i2 < str.length(); i2++) {
            int charAt = i + str.charAt(i2);
            int i3 = (charAt + charAt) << 10;
            i = i3 ^ (i3 >>> 6);
        }
        int i4 = ((i >>> 20) ^ (i >>> 12)) ^ i;
        return (i4 >>> 4) ^ ((i4 >>> 7) ^ i4);
    }

    /* renamed from: a */
    static /* synthetic */ boolean m203a(C0105x xVar, Object obj, Object obj2) {
        if (obj == null || !(obj instanceof String)) {
            return false;
        }
        int b = m206b((String) obj);
        C0074ac<V>[] acVarArr = xVar.f176c;
        int length = b & (acVarArr.length - 1);
        C0074ac<V> acVar = acVarArr[length];
        C0074ac<V> acVar2 = null;
        while (acVar != null) {
            if (acVar.f117c != b || !obj.equals(acVar.f115a)) {
                acVar2 = acVar;
                acVar = acVar.f118d;
            } else if (obj2 != null ? !obj2.equals(acVar.f116b) : acVar.f116b != null) {
                return false;
            } else {
                if (acVar2 == null) {
                    acVarArr[length] = acVar.f118d;
                } else {
                    acVar2.f118d = acVar.f118d;
                }
                xVar.f177d--;
                m202a(acVar);
                return true;
            }
        }
        return false;
    }
}
