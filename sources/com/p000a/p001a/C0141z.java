package com.p000a.p001a;

import com.p000a.p001a.p003b.C0099r;
import java.math.BigInteger;

/* renamed from: com.a.a.z */
/* compiled from: JsonPrimitive */
public final class C0141z extends C0136u {

    /* renamed from: a */
    private static final Class<?>[] f263a = {Integer.TYPE, Long.TYPE, Short.TYPE, Float.TYPE, Double.TYPE, Byte.TYPE, Boolean.TYPE, Character.TYPE, Integer.class, Long.class, Short.class, Float.class, Double.class, Byte.class, Boolean.class, Character.class};

    /* renamed from: b */
    private Object f264b;

    public C0141z(Boolean bool) {
        m319a((Object) bool);
    }

    public C0141z(Number number) {
        m319a((Object) number);
    }

    public C0141z(String str) {
        m319a((Object) str);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:8:0x001c, code lost:
        if (r2 != false) goto L_0x001e;
     */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void m319a(java.lang.Object r8) {
        /*
            r7 = this;
            r1 = 1
            r0 = 0
            boolean r2 = r8 instanceof java.lang.Character
            if (r2 == 0) goto L_0x0013
            java.lang.Character r8 = (java.lang.Character) r8
            char r0 = r8.charValue()
            java.lang.String r0 = java.lang.String.valueOf(r0)
            r7.f264b = r0
        L_0x0012:
            return
        L_0x0013:
            boolean r2 = r8 instanceof java.lang.Number
            if (r2 != 0) goto L_0x001e
            boolean r2 = r8 instanceof java.lang.String
            if (r2 == 0) goto L_0x0025
            r2 = r1
        L_0x001c:
            if (r2 == 0) goto L_0x001f
        L_0x001e:
            r0 = r1
        L_0x001f:
            com.p000a.p001a.p003b.C0013a.m12a((boolean) r0)
            r7.f264b = r8
            goto L_0x0012
        L_0x0025:
            java.lang.Class r3 = r8.getClass()
            java.lang.Class<?>[] r4 = f263a
            int r5 = r4.length
            r2 = r0
        L_0x002d:
            if (r2 >= r5) goto L_0x003c
            r6 = r4[r2]
            boolean r6 = r6.isAssignableFrom(r3)
            if (r6 == 0) goto L_0x0039
            r2 = r1
            goto L_0x001c
        L_0x0039:
            int r2 = r2 + 1
            goto L_0x002d
        L_0x003c:
            r2 = r0
            goto L_0x001c
        */
        throw new UnsupportedOperationException("Method not decompiled: com.p000a.p001a.C0141z.m319a(java.lang.Object):void");
    }

    /* renamed from: h */
    public final boolean mo168h() {
        return this.f264b instanceof Boolean;
    }

    /* renamed from: i */
    public final boolean mo170i() {
        return this.f264b instanceof Number;
    }

    /* renamed from: a */
    public final Number mo149a() {
        return this.f264b instanceof String ? new C0099r((String) this.f264b) : (Number) this.f264b;
    }

    /* renamed from: j */
    public final boolean mo171j() {
        return this.f264b instanceof String;
    }

    public final int hashCode() {
        if (this.f264b == null) {
            return 31;
        }
        if (m320a(this)) {
            long longValue = mo149a().longValue();
            return (int) (longValue ^ (longValue >>> 32));
        } else if (!(this.f264b instanceof Number)) {
            return this.f264b.hashCode();
        } else {
            long doubleToLongBits = Double.doubleToLongBits(mo149a().doubleValue());
            return (int) (doubleToLongBits ^ (doubleToLongBits >>> 32));
        }
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        C0141z zVar = (C0141z) obj;
        if (this.f264b == null) {
            if (zVar.f264b != null) {
                return false;
            }
            return true;
        } else if (!m320a(this) || !m320a(zVar)) {
            if (!(this.f264b instanceof Number) || !(zVar.f264b instanceof Number)) {
                return this.f264b.equals(zVar.f264b);
            }
            double doubleValue = mo149a().doubleValue();
            double doubleValue2 = zVar.mo149a().doubleValue();
            if (doubleValue == doubleValue2) {
                return true;
            }
            if (!Double.isNaN(doubleValue) || !Double.isNaN(doubleValue2)) {
                return false;
            }
            return true;
        } else if (mo149a().longValue() != zVar.mo149a().longValue()) {
            return false;
        } else {
            return true;
        }
    }

    /* renamed from: a */
    private static boolean m320a(C0141z zVar) {
        if (!(zVar.f264b instanceof Number)) {
            return false;
        }
        Number number = (Number) zVar.f264b;
        if ((number instanceof BigInteger) || (number instanceof Long) || (number instanceof Integer) || (number instanceof Short) || (number instanceof Byte)) {
            return true;
        }
        return false;
    }

    /* renamed from: f */
    public final boolean mo156f() {
        if (this.f264b instanceof Boolean) {
            return ((Boolean) this.f264b).booleanValue();
        }
        return Boolean.parseBoolean(mo151b());
    }

    /* renamed from: b */
    public final String mo151b() {
        if (this.f264b instanceof Number) {
            return mo149a().toString();
        }
        if (this.f264b instanceof Boolean) {
            return ((Boolean) this.f264b).toString();
        }
        return (String) this.f264b;
    }

    /* renamed from: c */
    public final double mo152c() {
        return this.f264b instanceof Number ? mo149a().doubleValue() : Double.parseDouble(mo151b());
    }

    /* renamed from: d */
    public final long mo153d() {
        return this.f264b instanceof Number ? mo149a().longValue() : Long.parseLong(mo151b());
    }

    /* renamed from: e */
    public final int mo154e() {
        return this.f264b instanceof Number ? mo149a().intValue() : Integer.parseInt(mo151b());
    }
}
