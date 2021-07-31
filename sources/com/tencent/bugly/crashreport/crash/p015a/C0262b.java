package com.tencent.bugly.crashreport.crash.p015a;

import com.tencent.bugly.p014a.C0209aa;
import com.tencent.bugly.p014a.C0241r;
import java.util.List;
import java.util.Map;

/* renamed from: com.tencent.bugly.crashreport.crash.a.b */
/* compiled from: BUGLY */
public final class C0262b {

    /* renamed from: a */
    private StringBuilder f793a;

    /* renamed from: b */
    private int f794b = 0;

    /* renamed from: a */
    private void m762a(String str) {
        for (int i = 0; i < this.f794b; i++) {
            this.f793a.append(9);
        }
        if (str != null) {
            this.f793a.append(str).append(": ");
        }
    }

    public C0262b(StringBuilder sb, int i) {
        this.f793a = sb;
        this.f794b = i;
    }

    /* renamed from: a */
    public final C0262b mo465a(byte b, String str) {
        m762a(str);
        this.f793a.append(b).append(10);
        return this;
    }

    /* renamed from: a */
    private C0262b m756a(char c, String str) {
        m762a(str);
        this.f793a.append(c).append(10);
        return this;
    }

    /* renamed from: a */
    public final C0262b mo469a(short s, String str) {
        m762a(str);
        this.f793a.append(s).append(10);
        return this;
    }

    /* renamed from: a */
    public final C0262b mo466a(int i, String str) {
        m762a(str);
        this.f793a.append(i).append(10);
        return this;
    }

    /* renamed from: a */
    private C0262b m759a(long j, String str) {
        m762a(str);
        this.f793a.append(j).append(10);
        return this;
    }

    /* renamed from: a */
    private C0262b m758a(float f, String str) {
        m762a(str);
        this.f793a.append(f).append(10);
        return this;
    }

    /* renamed from: a */
    private C0262b m757a(double d, String str) {
        m762a(str);
        this.f793a.append(d).append(10);
        return this;
    }

    /* renamed from: a */
    public final C0262b mo467a(String str, String str2) {
        m762a(str2);
        if (str == null) {
            this.f793a.append("null\n");
        } else {
            this.f793a.append(str).append(10);
        }
        return this;
    }

    /* renamed from: a */
    public final C0262b mo470a(byte[] bArr, String str) {
        m762a(str);
        if (bArr == null) {
            this.f793a.append("null\n");
        } else if (bArr.length == 0) {
            this.f793a.append(bArr.length).append(", []\n");
        } else {
            this.f793a.append(bArr.length).append(", [\n");
            C0262b bVar = new C0262b(this.f793a, this.f794b + 1);
            for (byte a : bArr) {
                bVar.mo465a(a, (String) null);
            }
            m756a(']', (String) null);
        }
        return this;
    }

    /* renamed from: a */
    public final <K, V> C0262b mo468a(Map<K, V> map, String str) {
        m762a(str);
        if (map == null) {
            this.f793a.append("null\n");
        } else if (map.isEmpty()) {
            this.f793a.append(map.size()).append(", {}\n");
        } else {
            this.f793a.append(map.size()).append(", {\n");
            C0262b bVar = new C0262b(this.f793a, this.f794b + 1);
            C0262b bVar2 = new C0262b(this.f793a, this.f794b + 2);
            for (Map.Entry next : map.entrySet()) {
                bVar.m756a('(', (String) null);
                bVar2.m760a(next.getKey(), (String) null);
                bVar2.m760a(next.getValue(), (String) null);
                bVar.m756a(')', (String) null);
            }
            m756a('}', (String) null);
        }
        return this;
    }

    /* renamed from: a */
    private <T> C0262b m761a(T[] tArr, String str) {
        m762a(str);
        if (tArr == null) {
            this.f793a.append("null\n");
        } else if (tArr.length == 0) {
            this.f793a.append(tArr.length).append(", []\n");
        } else {
            this.f793a.append(tArr.length).append(", [\n");
            C0262b bVar = new C0262b(this.f793a, this.f794b + 1);
            for (T a : tArr) {
                bVar.m760a(a, (String) null);
            }
            m756a(']', (String) null);
        }
        return this;
    }

    /* renamed from: a */
    private <T> C0262b m760a(T t, String str) {
        int i = 0;
        if (t == null) {
            this.f793a.append("null\n");
        } else if (t instanceof Byte) {
            mo465a(((Byte) t).byteValue(), str);
        } else if (t instanceof Boolean) {
            boolean booleanValue = ((Boolean) t).booleanValue();
            m762a(str);
            this.f793a.append(booleanValue ? 'T' : 'F').append(10);
        } else if (t instanceof Short) {
            mo469a(((Short) t).shortValue(), str);
        } else if (t instanceof Integer) {
            mo466a(((Integer) t).intValue(), str);
        } else if (t instanceof Long) {
            m759a(((Long) t).longValue(), str);
        } else if (t instanceof Float) {
            m758a(((Float) t).floatValue(), str);
        } else if (t instanceof Double) {
            m757a(((Double) t).doubleValue(), str);
        } else if (t instanceof String) {
            mo467a((String) t, str);
        } else if (t instanceof Map) {
            mo468a((Map) t, str);
        } else if (t instanceof List) {
            List list = (List) t;
            if (list == null) {
                m762a(str);
                this.f793a.append("null\t");
            } else {
                m761a((T[]) list.toArray(), str);
            }
        } else if (t instanceof C0209aa) {
            C0209aa aaVar = (C0209aa) t;
            m756a('{', str);
            if (aaVar == null) {
                this.f793a.append(9).append("null");
            } else {
                aaVar.mo354a(this.f793a, this.f794b + 1);
            }
            m756a('}', (String) null);
        } else if (t instanceof byte[]) {
            mo470a((byte[]) t, str);
        } else if (t instanceof boolean[]) {
            m760a((boolean[]) t, str);
        } else if (t instanceof short[]) {
            short[] sArr = (short[]) t;
            m762a(str);
            if (sArr == null) {
                this.f793a.append("null\n");
            } else if (sArr.length == 0) {
                this.f793a.append(sArr.length).append(", []\n");
            } else {
                this.f793a.append(sArr.length).append(", [\n");
                C0262b bVar = new C0262b(this.f793a, this.f794b + 1);
                int length = sArr.length;
                while (i < length) {
                    bVar.mo469a(sArr[i], (String) null);
                    i++;
                }
                m756a(']', (String) null);
            }
        } else if (t instanceof int[]) {
            int[] iArr = (int[]) t;
            m762a(str);
            if (iArr == null) {
                this.f793a.append("null\n");
            } else if (iArr.length == 0) {
                this.f793a.append(iArr.length).append(", []\n");
            } else {
                this.f793a.append(iArr.length).append(", [\n");
                C0262b bVar2 = new C0262b(this.f793a, this.f794b + 1);
                int length2 = iArr.length;
                while (i < length2) {
                    bVar2.mo466a(iArr[i], (String) null);
                    i++;
                }
                m756a(']', (String) null);
            }
        } else if (t instanceof long[]) {
            long[] jArr = (long[]) t;
            m762a(str);
            if (jArr == null) {
                this.f793a.append("null\n");
            } else if (jArr.length == 0) {
                this.f793a.append(jArr.length).append(", []\n");
            } else {
                this.f793a.append(jArr.length).append(", [\n");
                C0262b bVar3 = new C0262b(this.f793a, this.f794b + 1);
                int length3 = jArr.length;
                while (i < length3) {
                    bVar3.m759a(jArr[i], (String) null);
                    i++;
                }
                m756a(']', (String) null);
            }
        } else if (t instanceof float[]) {
            float[] fArr = (float[]) t;
            m762a(str);
            if (fArr == null) {
                this.f793a.append("null\n");
            } else if (fArr.length == 0) {
                this.f793a.append(fArr.length).append(", []\n");
            } else {
                this.f793a.append(fArr.length).append(", [\n");
                C0262b bVar4 = new C0262b(this.f793a, this.f794b + 1);
                int length4 = fArr.length;
                while (i < length4) {
                    bVar4.m758a(fArr[i], (String) null);
                    i++;
                }
                m756a(']', (String) null);
            }
        } else if (t instanceof double[]) {
            double[] dArr = (double[]) t;
            m762a(str);
            if (dArr == null) {
                this.f793a.append("null\n");
            } else if (dArr.length == 0) {
                this.f793a.append(dArr.length).append(", []\n");
            } else {
                this.f793a.append(dArr.length).append(", [\n");
                C0262b bVar5 = new C0262b(this.f793a, this.f794b + 1);
                int length5 = dArr.length;
                while (i < length5) {
                    bVar5.m757a(dArr[i], (String) null);
                    i++;
                }
                m756a(']', (String) null);
            }
        } else if (t.getClass().isArray()) {
            m761a((T[]) (Object[]) t, str);
        } else {
            throw new C0241r("write object error: unsupport type.");
        }
        return this;
    }
}
