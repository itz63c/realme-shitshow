package com.tencent.bugly.p014a;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Array;
import java.nio.BufferUnderflowException;
import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.Map;
import neton.client.dns.DnsMode;
import neton.internal.platform.Platform;

/* renamed from: com.tencent.bugly.a.x */
/* compiled from: BUGLY */
public final class C0247x {

    /* renamed from: a */
    private ByteBuffer f612a;

    /* renamed from: b */
    private String f613b = "GBK";

    public C0247x() {
    }

    public C0247x(byte[] bArr) {
        this.f612a = ByteBuffer.wrap(bArr);
    }

    public C0247x(byte[] bArr, byte b) {
        this.f612a = ByteBuffer.wrap(bArr);
        this.f612a.position(4);
    }

    /* renamed from: a */
    public final void mo406a(byte[] bArr) {
        if (this.f612a != null) {
            this.f612a.clear();
        }
        this.f612a = ByteBuffer.wrap(bArr);
    }

    /* renamed from: a */
    private static int m671a(C0248y yVar, ByteBuffer byteBuffer) {
        byte b = byteBuffer.get();
        yVar.f614a = (byte) (b & 15);
        yVar.f615b = (b & 240) >> 4;
        if (yVar.f615b != 15) {
            return 1;
        }
        yVar.f615b = byteBuffer.get();
        return 2;
    }

    /* renamed from: a */
    private boolean m675a(int i) {
        try {
            C0248y yVar = new C0248y();
            while (true) {
                int a = m671a(yVar, this.f612a.duplicate());
                if (i > yVar.f615b && yVar.f614a != 11) {
                    this.f612a.position(a + this.f612a.position());
                    m674a(yVar.f614a);
                }
            }
            if (i == yVar.f615b) {
                return true;
            }
            return false;
        } catch (C0246w | BufferUnderflowException e) {
            return false;
        }
    }

    /* renamed from: a */
    private void m673a() {
        C0248y yVar = new C0248y();
        do {
            m671a(yVar, this.f612a);
            m674a(yVar.f614a);
        } while (yVar.f614a != 11);
    }

    /* renamed from: a */
    private void m674a(byte b) {
        int i = 0;
        switch (b) {
            case 0:
                this.f612a.position(this.f612a.position() + 1);
                return;
            case 1:
                this.f612a.position(this.f612a.position() + 2);
                return;
            case DnsMode.DNS_MODE_HTTP /*2*/:
                this.f612a.position(this.f612a.position() + 4);
                return;
            case 3:
                this.f612a.position(this.f612a.position() + 8);
                return;
            case Platform.INFO /*4*/:
                this.f612a.position(this.f612a.position() + 4);
                return;
            case Platform.WARN /*5*/:
                this.f612a.position(this.f612a.position() + 8);
                return;
            case 6:
                int i2 = this.f612a.get();
                if (i2 < 0) {
                    i2 += 256;
                }
                this.f612a.position(i2 + this.f612a.position());
                return;
            case 7:
                this.f612a.position(this.f612a.getInt() + this.f612a.position());
                return;
            case 8:
                int a = mo399a(0, 0, true);
                while (i < (a << 1)) {
                    C0248y yVar = new C0248y();
                    m671a(yVar, this.f612a);
                    m674a(yVar.f614a);
                    i++;
                }
                return;
            case 9:
                int a2 = mo399a(0, 0, true);
                while (i < a2) {
                    C0248y yVar2 = new C0248y();
                    m671a(yVar2, this.f612a);
                    m674a(yVar2.f614a);
                    i++;
                }
                return;
            case 10:
                m673a();
                return;
            case 11:
            case 12:
                return;
            case 13:
                C0248y yVar3 = new C0248y();
                m671a(yVar3, this.f612a);
                if (yVar3.f614a != 0) {
                    throw new C0246w("skipField with invalid type, type value: " + b + ", " + yVar3.f614a);
                }
                this.f612a.position(mo399a(0, 0, true) + this.f612a.position());
                return;
            default:
                throw new C0246w("invalid type.");
        }
    }

    /* renamed from: a */
    public final boolean mo407a(int i, boolean z) {
        if (mo398a((byte) 0, i, z) != 0) {
            return true;
        }
        return false;
    }

    /* renamed from: a */
    public final byte mo398a(byte b, int i, boolean z) {
        if (m675a(i)) {
            C0248y yVar = new C0248y();
            m671a(yVar, this.f612a);
            switch (yVar.f614a) {
                case 0:
                    return this.f612a.get();
                case 12:
                    return 0;
                default:
                    throw new C0246w("type mismatch.");
            }
        } else if (!z) {
            return b;
        } else {
            throw new C0246w("require field not exist.");
        }
    }

    /* renamed from: a */
    public final short mo405a(short s, int i, boolean z) {
        if (m675a(i)) {
            C0248y yVar = new C0248y();
            m671a(yVar, this.f612a);
            switch (yVar.f614a) {
                case 0:
                    return (short) this.f612a.get();
                case 1:
                    return this.f612a.getShort();
                case 12:
                    return 0;
                default:
                    throw new C0246w("type mismatch.");
            }
        } else if (!z) {
            return s;
        } else {
            throw new C0246w("require field not exist.");
        }
    }

    /* renamed from: a */
    public final int mo399a(int i, int i2, boolean z) {
        if (m675a(i2)) {
            C0248y yVar = new C0248y();
            m671a(yVar, this.f612a);
            switch (yVar.f614a) {
                case 0:
                    return this.f612a.get();
                case 1:
                    return this.f612a.getShort();
                case DnsMode.DNS_MODE_HTTP /*2*/:
                    return this.f612a.getInt();
                case 12:
                    return 0;
                default:
                    throw new C0246w("type mismatch.");
            }
        } else if (!z) {
            return i;
        } else {
            throw new C0246w("require field not exist.");
        }
    }

    /* renamed from: a */
    public final long mo401a(long j, int i, boolean z) {
        if (m675a(i)) {
            C0248y yVar = new C0248y();
            m671a(yVar, this.f612a);
            switch (yVar.f614a) {
                case 0:
                    return (long) this.f612a.get();
                case 1:
                    return (long) this.f612a.getShort();
                case DnsMode.DNS_MODE_HTTP /*2*/:
                    return (long) this.f612a.getInt();
                case 3:
                    return this.f612a.getLong();
                case 12:
                    return 0;
                default:
                    throw new C0246w("type mismatch.");
            }
        } else if (!z) {
            return j;
        } else {
            throw new C0246w("require field not exist.");
        }
    }

    /* renamed from: a */
    private float m670a(float f, int i, boolean z) {
        if (m675a(i)) {
            C0248y yVar = new C0248y();
            m671a(yVar, this.f612a);
            switch (yVar.f614a) {
                case Platform.INFO /*4*/:
                    return this.f612a.getFloat();
                case 12:
                    return 0.0f;
                default:
                    throw new C0246w("type mismatch.");
            }
        } else if (!z) {
            return f;
        } else {
            throw new C0246w("require field not exist.");
        }
    }

    /* renamed from: a */
    private double m669a(double d, int i, boolean z) {
        if (m675a(i)) {
            C0248y yVar = new C0248y();
            m671a(yVar, this.f612a);
            switch (yVar.f614a) {
                case Platform.INFO /*4*/:
                    return (double) this.f612a.getFloat();
                case Platform.WARN /*5*/:
                    return this.f612a.getDouble();
                case 12:
                    return 0.0d;
                default:
                    throw new C0246w("type mismatch.");
            }
        } else if (!z) {
            return d;
        } else {
            throw new C0246w("require field not exist.");
        }
    }

    /* renamed from: b */
    public final String mo408b(int i, boolean z) {
        if (m675a(i)) {
            C0248y yVar = new C0248y();
            m671a(yVar, this.f612a);
            switch (yVar.f614a) {
                case 6:
                    int i2 = this.f612a.get();
                    if (i2 < 0) {
                        i2 += 256;
                    }
                    byte[] bArr = new byte[i2];
                    this.f612a.get(bArr);
                    try {
                        return new String(bArr, this.f613b);
                    } catch (UnsupportedEncodingException e) {
                        return new String(bArr);
                    }
                case 7:
                    int i3 = this.f612a.getInt();
                    if (i3 > 104857600 || i3 < 0) {
                        throw new C0246w("String too long: " + i3);
                    }
                    byte[] bArr2 = new byte[i3];
                    this.f612a.get(bArr2);
                    try {
                        return new String(bArr2, this.f613b);
                    } catch (UnsupportedEncodingException e2) {
                        return new String(bArr2);
                    }
                default:
                    throw new C0246w("type mismatch.");
            }
        } else if (!z) {
            return null;
        } else {
            throw new C0246w("require field not exist.");
        }
    }

    /* renamed from: a */
    public final <K, V> HashMap<K, V> mo404a(Map<K, V> map) {
        return (HashMap) m672a(new HashMap(), map, 0, false);
    }

    /* renamed from: a */
    private <K, V> Map<K, V> m672a(Map<K, V> map, Map<K, V> map2, int i, boolean z) {
        if (map2 == null || map2.isEmpty()) {
            return new HashMap();
        }
        Map.Entry next = map2.entrySet().iterator().next();
        Object key = next.getKey();
        Object value = next.getValue();
        if (m675a(i)) {
            C0248y yVar = new C0248y();
            m671a(yVar, this.f612a);
            switch (yVar.f614a) {
                case 8:
                    int a = mo399a(0, 0, true);
                    if (a < 0) {
                        throw new C0246w("size invalid: " + a);
                    }
                    for (int i2 = 0; i2 < a; i2++) {
                        map.put(mo403a(key, 0, true), mo403a(value, 1, true));
                    }
                    return map;
                default:
                    throw new C0246w("type mismatch.");
            }
        } else if (!z) {
            return map;
        } else {
            throw new C0246w("require field not exist.");
        }
    }

    /* renamed from: d */
    private boolean[] m678d(int i, boolean z) {
        boolean z2;
        if (m675a(i)) {
            C0248y yVar = new C0248y();
            m671a(yVar, this.f612a);
            switch (yVar.f614a) {
                case 9:
                    int a = mo399a(0, 0, true);
                    if (a < 0) {
                        throw new C0246w("size invalid: " + a);
                    }
                    boolean[] zArr = new boolean[a];
                    for (int i2 = 0; i2 < a; i2++) {
                        if (mo398a((byte) 0, 0, true) != 0) {
                            z2 = true;
                        } else {
                            z2 = false;
                        }
                        zArr[i2] = z2;
                    }
                    return zArr;
                default:
                    throw new C0246w("type mismatch.");
            }
        } else if (!z) {
            return null;
        } else {
            throw new C0246w("require field not exist.");
        }
    }

    /* renamed from: c */
    public final byte[] mo409c(int i, boolean z) {
        if (m675a(i)) {
            C0248y yVar = new C0248y();
            m671a(yVar, this.f612a);
            switch (yVar.f614a) {
                case 9:
                    int a = mo399a(0, 0, true);
                    if (a < 0) {
                        throw new C0246w("size invalid: " + a);
                    }
                    byte[] bArr = new byte[a];
                    for (int i2 = 0; i2 < a; i2++) {
                        bArr[i2] = mo398a(bArr[0], 0, true);
                    }
                    return bArr;
                case 13:
                    C0248y yVar2 = new C0248y();
                    m671a(yVar2, this.f612a);
                    if (yVar2.f614a != 0) {
                        throw new C0246w("type mismatch, tag: " + i + ", type: " + yVar.f614a + ", " + yVar2.f614a);
                    }
                    int a2 = mo399a(0, 0, true);
                    if (a2 < 0) {
                        throw new C0246w("invalid size, tag: " + i + ", type: " + yVar.f614a + ", " + yVar2.f614a + ", size: " + a2);
                    }
                    byte[] bArr2 = new byte[a2];
                    this.f612a.get(bArr2);
                    return bArr2;
                default:
                    throw new C0246w("type mismatch.");
            }
        } else if (!z) {
            return null;
        } else {
            throw new C0246w("require field not exist.");
        }
    }

    /* renamed from: e */
    private short[] m679e(int i, boolean z) {
        short[] sArr = null;
        if (m675a(i)) {
            C0248y yVar = new C0248y();
            m671a(yVar, this.f612a);
            switch (yVar.f614a) {
                case 9:
                    int a = mo399a(0, 0, true);
                    if (a >= 0) {
                        sArr = new short[a];
                        for (int i2 = 0; i2 < a; i2++) {
                            sArr[i2] = mo405a(sArr[0], 0, true);
                        }
                        break;
                    } else {
                        throw new C0246w("size invalid: " + a);
                    }
                default:
                    throw new C0246w("type mismatch.");
            }
        } else if (z) {
            throw new C0246w("require field not exist.");
        }
        return sArr;
    }

    /* renamed from: f */
    private int[] m680f(int i, boolean z) {
        int[] iArr = null;
        if (m675a(i)) {
            C0248y yVar = new C0248y();
            m671a(yVar, this.f612a);
            switch (yVar.f614a) {
                case 9:
                    int a = mo399a(0, 0, true);
                    if (a >= 0) {
                        iArr = new int[a];
                        for (int i2 = 0; i2 < a; i2++) {
                            iArr[i2] = mo399a(iArr[0], 0, true);
                        }
                        break;
                    } else {
                        throw new C0246w("size invalid: " + a);
                    }
                default:
                    throw new C0246w("type mismatch.");
            }
        } else if (z) {
            throw new C0246w("require field not exist.");
        }
        return iArr;
    }

    /* renamed from: g */
    private long[] m681g(int i, boolean z) {
        long[] jArr = null;
        if (m675a(i)) {
            C0248y yVar = new C0248y();
            m671a(yVar, this.f612a);
            switch (yVar.f614a) {
                case 9:
                    int a = mo399a(0, 0, true);
                    if (a >= 0) {
                        jArr = new long[a];
                        for (int i2 = 0; i2 < a; i2++) {
                            jArr[i2] = mo401a(jArr[0], 0, true);
                        }
                        break;
                    } else {
                        throw new C0246w("size invalid: " + a);
                    }
                default:
                    throw new C0246w("type mismatch.");
            }
        } else if (z) {
            throw new C0246w("require field not exist.");
        }
        return jArr;
    }

    /* renamed from: h */
    private float[] m682h(int i, boolean z) {
        float[] fArr = null;
        if (m675a(i)) {
            C0248y yVar = new C0248y();
            m671a(yVar, this.f612a);
            switch (yVar.f614a) {
                case 9:
                    int a = mo399a(0, 0, true);
                    if (a >= 0) {
                        fArr = new float[a];
                        for (int i2 = 0; i2 < a; i2++) {
                            fArr[i2] = m670a(fArr[0], 0, true);
                        }
                        break;
                    } else {
                        throw new C0246w("size invalid: " + a);
                    }
                default:
                    throw new C0246w("type mismatch.");
            }
        } else if (z) {
            throw new C0246w("require field not exist.");
        }
        return fArr;
    }

    /* renamed from: i */
    private double[] m683i(int i, boolean z) {
        double[] dArr = null;
        if (m675a(i)) {
            C0248y yVar = new C0248y();
            m671a(yVar, this.f612a);
            switch (yVar.f614a) {
                case 9:
                    int a = mo399a(0, 0, true);
                    if (a >= 0) {
                        dArr = new double[a];
                        for (int i2 = 0; i2 < a; i2++) {
                            dArr[i2] = m669a(dArr[0], 0, true);
                        }
                        break;
                    } else {
                        throw new C0246w("size invalid: " + a);
                    }
                default:
                    throw new C0246w("type mismatch.");
            }
        } else if (z) {
            throw new C0246w("require field not exist.");
        }
        return dArr;
    }

    /* renamed from: a */
    private <T> T[] m676a(T[] tArr, int i, boolean z) {
        if (tArr != null && tArr.length != 0) {
            return m677b(tArr[0], i, z);
        }
        throw new C0246w("unable to get type of key and value.");
    }

    /* renamed from: b */
    private <T> T[] m677b(T t, int i, boolean z) {
        if (m675a(i)) {
            C0248y yVar = new C0248y();
            m671a(yVar, this.f612a);
            switch (yVar.f614a) {
                case 9:
                    int a = mo399a(0, 0, true);
                    if (a < 0) {
                        throw new C0246w("size invalid: " + a);
                    }
                    T[] tArr = (Object[]) Array.newInstance(t.getClass(), a);
                    for (int i2 = 0; i2 < a; i2++) {
                        tArr[i2] = mo403a(t, 0, true);
                    }
                    return tArr;
                default:
                    throw new C0246w("type mismatch.");
            }
        } else if (!z) {
            return null;
        } else {
            throw new C0246w("require field not exist.");
        }
    }

    /* renamed from: a */
    public final C0209aa mo402a(C0209aa aaVar, int i, boolean z) {
        C0209aa aaVar2 = null;
        if (m675a(i)) {
            try {
                aaVar2 = (C0209aa) aaVar.getClass().newInstance();
                C0248y yVar = new C0248y();
                m671a(yVar, this.f612a);
                if (yVar.f614a != 10) {
                    throw new C0246w("type mismatch.");
                }
                aaVar2.mo352a(this);
                m673a();
            } catch (Exception e) {
                throw new C0246w(e.getMessage());
            }
        } else if (z) {
            throw new C0246w("require field not exist.");
        }
        return aaVar2;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v45, resolved type: boolean} */
    /* JADX WARNING: type inference failed for: r0v0 */
    /* JADX WARNING: type inference failed for: r0v23, types: [int] */
    /* JADX WARNING: type inference failed for: r0v47 */
    /* JADX WARNING: type inference failed for: r0v50 */
    /* JADX WARNING: Multi-variable type inference failed */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final <T> java.lang.Object mo403a(T r5, int r6, boolean r7) {
        /*
            r4 = this;
            r0 = 0
            boolean r1 = r5 instanceof java.lang.Byte
            if (r1 == 0) goto L_0x000e
            byte r0 = r4.mo398a((byte) r0, (int) r6, (boolean) r7)
            java.lang.Byte r0 = java.lang.Byte.valueOf(r0)
        L_0x000d:
            return r0
        L_0x000e:
            boolean r1 = r5 instanceof java.lang.Boolean
            if (r1 == 0) goto L_0x001e
            byte r1 = r4.mo398a((byte) r0, (int) r6, (boolean) r7)
            if (r1 == 0) goto L_0x0019
            r0 = 1
        L_0x0019:
            java.lang.Boolean r0 = java.lang.Boolean.valueOf(r0)
            goto L_0x000d
        L_0x001e:
            boolean r1 = r5 instanceof java.lang.Short
            if (r1 == 0) goto L_0x002b
            short r0 = r4.mo405a((short) r0, (int) r6, (boolean) r7)
            java.lang.Short r0 = java.lang.Short.valueOf(r0)
            goto L_0x000d
        L_0x002b:
            boolean r1 = r5 instanceof java.lang.Integer
            if (r1 == 0) goto L_0x0038
            int r0 = r4.mo399a((int) r0, (int) r6, (boolean) r7)
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)
            goto L_0x000d
        L_0x0038:
            boolean r1 = r5 instanceof java.lang.Long
            if (r1 == 0) goto L_0x0047
            r0 = 0
            long r0 = r4.mo401a((long) r0, (int) r6, (boolean) r7)
            java.lang.Long r0 = java.lang.Long.valueOf(r0)
            goto L_0x000d
        L_0x0047:
            boolean r1 = r5 instanceof java.lang.Float
            if (r1 == 0) goto L_0x0055
            r0 = 0
            float r0 = r4.m670a((float) r0, (int) r6, (boolean) r7)
            java.lang.Float r0 = java.lang.Float.valueOf(r0)
            goto L_0x000d
        L_0x0055:
            boolean r1 = r5 instanceof java.lang.Double
            if (r1 == 0) goto L_0x0064
            r0 = 0
            double r0 = r4.m669a((double) r0, (int) r6, (boolean) r7)
            java.lang.Double r0 = java.lang.Double.valueOf(r0)
            goto L_0x000d
        L_0x0064:
            boolean r1 = r5 instanceof java.lang.String
            if (r1 == 0) goto L_0x0071
            java.lang.String r0 = r4.mo408b(r6, r7)
            java.lang.String r0 = java.lang.String.valueOf(r0)
            goto L_0x000d
        L_0x0071:
            boolean r1 = r5 instanceof java.util.Map
            if (r1 == 0) goto L_0x0083
            java.util.Map r5 = (java.util.Map) r5
            java.util.HashMap r0 = new java.util.HashMap
            r0.<init>()
            java.util.Map r0 = r4.m672a(r0, r5, r6, r7)
            java.util.HashMap r0 = (java.util.HashMap) r0
            goto L_0x000d
        L_0x0083:
            boolean r1 = r5 instanceof java.util.List
            if (r1 == 0) goto L_0x00b8
            java.util.List r5 = (java.util.List) r5
            if (r5 == 0) goto L_0x0091
            boolean r1 = r5.isEmpty()
            if (r1 == 0) goto L_0x0098
        L_0x0091:
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            goto L_0x000d
        L_0x0098:
            java.lang.Object r1 = r5.get(r0)
            java.lang.Object[] r2 = r4.m677b(r1, r6, r7)
            if (r2 != 0) goto L_0x00a5
            r0 = 0
            goto L_0x000d
        L_0x00a5:
            java.util.ArrayList r1 = new java.util.ArrayList
            r1.<init>()
        L_0x00aa:
            int r3 = r2.length
            if (r0 >= r3) goto L_0x00b5
            r3 = r2[r0]
            r1.add(r3)
            int r0 = r0 + 1
            goto L_0x00aa
        L_0x00b5:
            r0 = r1
            goto L_0x000d
        L_0x00b8:
            boolean r0 = r5 instanceof com.tencent.bugly.p014a.C0209aa
            if (r0 == 0) goto L_0x00c4
            com.tencent.bugly.a.aa r5 = (com.tencent.bugly.p014a.C0209aa) r5
            com.tencent.bugly.a.aa r0 = r4.mo402a((com.tencent.bugly.p014a.C0209aa) r5, (int) r6, (boolean) r7)
            goto L_0x000d
        L_0x00c4:
            java.lang.Class r0 = r5.getClass()
            boolean r0 = r0.isArray()
            if (r0 == 0) goto L_0x0120
            boolean r0 = r5 instanceof byte[]
            if (r0 != 0) goto L_0x00d6
            boolean r0 = r5 instanceof java.lang.Byte[]
            if (r0 == 0) goto L_0x00dc
        L_0x00d6:
            byte[] r0 = r4.mo409c(r6, r7)
            goto L_0x000d
        L_0x00dc:
            boolean r0 = r5 instanceof boolean[]
            if (r0 == 0) goto L_0x00e6
            boolean[] r0 = r4.m678d(r6, r7)
            goto L_0x000d
        L_0x00e6:
            boolean r0 = r5 instanceof short[]
            if (r0 == 0) goto L_0x00f0
            short[] r0 = r4.m679e(r6, r7)
            goto L_0x000d
        L_0x00f0:
            boolean r0 = r5 instanceof int[]
            if (r0 == 0) goto L_0x00fa
            int[] r0 = r4.m680f(r6, r7)
            goto L_0x000d
        L_0x00fa:
            boolean r0 = r5 instanceof long[]
            if (r0 == 0) goto L_0x0104
            long[] r0 = r4.m681g(r6, r7)
            goto L_0x000d
        L_0x0104:
            boolean r0 = r5 instanceof float[]
            if (r0 == 0) goto L_0x010e
            float[] r0 = r4.m682h(r6, r7)
            goto L_0x000d
        L_0x010e:
            boolean r0 = r5 instanceof double[]
            if (r0 == 0) goto L_0x0118
            double[] r0 = r4.m683i(r6, r7)
            goto L_0x000d
        L_0x0118:
            java.lang.Object[] r5 = (java.lang.Object[]) r5
            java.lang.Object[] r0 = r4.m676a((T[]) r5, (int) r6, (boolean) r7)
            goto L_0x000d
        L_0x0120:
            com.tencent.bugly.a.w r0 = new com.tencent.bugly.a.w
            java.lang.String r1 = "read object error: unsupport type."
            r0.<init>(r1)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.bugly.p014a.C0247x.mo403a(java.lang.Object, int, boolean):java.lang.Object");
    }

    /* renamed from: a */
    public final int mo400a(String str) {
        this.f613b = str;
        return 0;
    }
}
