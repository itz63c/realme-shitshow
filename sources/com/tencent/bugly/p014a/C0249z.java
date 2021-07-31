package com.tencent.bugly.p014a;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/* renamed from: com.tencent.bugly.a.z */
/* compiled from: BUGLY */
public final class C0249z {

    /* renamed from: a */
    private ByteBuffer f616a;

    /* renamed from: b */
    private String f617b;

    public C0249z(int i) {
        this.f617b = "GBK";
        this.f616a = ByteBuffer.allocate(i);
    }

    public C0249z() {
        this(128);
    }

    /* renamed from: a */
    public final ByteBuffer mo411a() {
        return this.f616a;
    }

    /* renamed from: b */
    public final byte[] mo423b() {
        byte[] bArr = new byte[this.f616a.position()];
        System.arraycopy(this.f616a.array(), 0, bArr, 0, this.f616a.position());
        return bArr;
    }

    /* renamed from: a */
    private void m696a(int i) {
        if (this.f616a.remaining() < i) {
            ByteBuffer allocate = ByteBuffer.allocate((this.f616a.capacity() + i) << 1);
            allocate.put(this.f616a.array(), 0, this.f616a.position());
            this.f616a = allocate;
        }
    }

    /* renamed from: b */
    private void m697b(byte b, int i) {
        if (i < 15) {
            this.f616a.put((byte) ((i << 4) | b));
        } else if (i < 256) {
            this.f616a.put((byte) (b | 240));
            this.f616a.put((byte) i);
        } else {
            throw new C0241r("tag is too large: " + i);
        }
    }

    /* renamed from: a */
    public final void mo421a(boolean z, int i) {
        mo412a((byte) (z ? 1 : 0), i);
    }

    /* renamed from: a */
    public final void mo412a(byte b, int i) {
        m696a(3);
        if (b == 0) {
            m697b((byte) 12, i);
            return;
        }
        m697b((byte) 0, i);
        this.f616a.put(b);
    }

    /* renamed from: a */
    public final void mo420a(short s, int i) {
        m696a(4);
        if (s < -128 || s > 127) {
            m697b((byte) 1, i);
            this.f616a.putShort(s);
            return;
        }
        mo412a((byte) s, i);
    }

    /* renamed from: a */
    public final void mo413a(int i, int i2) {
        m696a(6);
        if (i < -32768 || i > 32767) {
            m697b((byte) 2, i2);
            this.f616a.putInt(i);
            return;
        }
        mo420a((short) i, i2);
    }

    /* renamed from: a */
    public final void mo414a(long j, int i) {
        m696a(10);
        if (j < -2147483648L || j > 2147483647L) {
            m697b((byte) 3, i);
            this.f616a.putLong(j);
            return;
        }
        mo413a((int) j, i);
    }

    /* renamed from: a */
    public final void mo417a(String str, int i) {
        byte[] bytes;
        try {
            bytes = str.getBytes(this.f617b);
        } catch (UnsupportedEncodingException e) {
            bytes = str.getBytes();
        }
        m696a(bytes.length + 10);
        if (bytes.length > 255) {
            m697b((byte) 7, i);
            this.f616a.putInt(bytes.length);
            this.f616a.put(bytes);
            return;
        }
        m697b((byte) 6, i);
        this.f616a.put((byte) bytes.length);
        this.f616a.put(bytes);
    }

    /* renamed from: a */
    public final <K, V> void mo419a(Map<K, V> map, int i) {
        m696a(8);
        m697b((byte) 8, i);
        mo413a(map == null ? 0 : map.size(), 0);
        if (map != null) {
            for (Map.Entry next : map.entrySet()) {
                mo416a(next.getKey(), 0);
                mo416a(next.getValue(), 1);
            }
        }
    }

    /* renamed from: a */
    public final void mo422a(byte[] bArr, int i) {
        m696a(bArr.length + 8);
        m697b((byte) 13, i);
        m697b((byte) 0, 0);
        mo413a(bArr.length, 0);
        this.f616a.put(bArr);
    }

    /* renamed from: a */
    public final <T> void mo418a(Collection<T> collection, int i) {
        m696a(8);
        m697b((byte) 9, i);
        mo413a(collection == null ? 0 : collection.size(), 0);
        if (collection != null) {
            for (T a : collection) {
                mo416a((Object) a, 0);
            }
        }
    }

    /* renamed from: a */
    public final void mo415a(C0209aa aaVar, int i) {
        m696a(2);
        m697b((byte) 10, i);
        aaVar.mo353a(this);
        m696a(2);
        m697b((byte) 11, 0);
    }

    /* renamed from: a */
    public final void mo416a(Object obj, int i) {
        int i2 = 1;
        if (obj instanceof Byte) {
            mo412a(((Byte) obj).byteValue(), i);
        } else if (obj instanceof Boolean) {
            if (!((Boolean) obj).booleanValue()) {
                i2 = 0;
            }
            mo412a((byte) i2, i);
        } else if (obj instanceof Short) {
            mo420a(((Short) obj).shortValue(), i);
        } else if (obj instanceof Integer) {
            mo413a(((Integer) obj).intValue(), i);
        } else if (obj instanceof Long) {
            mo414a(((Long) obj).longValue(), i);
        } else if (obj instanceof Float) {
            float floatValue = ((Float) obj).floatValue();
            m696a(6);
            m697b((byte) 4, i);
            this.f616a.putFloat(floatValue);
        } else if (obj instanceof Double) {
            double doubleValue = ((Double) obj).doubleValue();
            m696a(10);
            m697b((byte) 5, i);
            this.f616a.putDouble(doubleValue);
        } else if (obj instanceof String) {
            mo417a((String) obj, i);
        } else if (obj instanceof Map) {
            mo419a((Map) obj, i);
        } else if (obj instanceof List) {
            mo418a((List) obj, i);
        } else if (obj instanceof C0209aa) {
            m696a(2);
            m697b((byte) 10, i);
            ((C0209aa) obj).mo353a(this);
            m696a(2);
            m697b((byte) 11, 0);
        } else if (obj instanceof byte[]) {
            mo422a((byte[]) obj, i);
        } else if (obj instanceof boolean[]) {
            boolean[] zArr = (boolean[]) obj;
            m696a(8);
            m697b((byte) 9, i);
            mo413a(zArr.length, 0);
            int length = zArr.length;
            for (int i3 = 0; i3 < length; i3++) {
                mo412a((byte) (zArr[i3] ? 1 : 0), 0);
            }
        } else if (obj instanceof short[]) {
            short[] sArr = (short[]) obj;
            m696a(8);
            m697b((byte) 9, i);
            mo413a(sArr.length, 0);
            for (short a : sArr) {
                mo420a(a, 0);
            }
        } else if (obj instanceof int[]) {
            int[] iArr = (int[]) obj;
            m696a(8);
            m697b((byte) 9, i);
            mo413a(iArr.length, 0);
            for (int a2 : iArr) {
                mo413a(a2, 0);
            }
        } else if (obj instanceof long[]) {
            long[] jArr = (long[]) obj;
            m696a(8);
            m697b((byte) 9, i);
            mo413a(jArr.length, 0);
            for (long a3 : jArr) {
                mo414a(a3, 0);
            }
        } else if (obj instanceof float[]) {
            float[] fArr = (float[]) obj;
            m696a(8);
            m697b((byte) 9, i);
            mo413a(fArr.length, 0);
            for (float putFloat : fArr) {
                m696a(6);
                m697b((byte) 4, 0);
                this.f616a.putFloat(putFloat);
            }
        } else if (obj instanceof double[]) {
            double[] dArr = (double[]) obj;
            m696a(8);
            m697b((byte) 9, i);
            mo413a(dArr.length, 0);
            for (double putDouble : dArr) {
                m696a(10);
                m697b((byte) 5, 0);
                this.f616a.putDouble(putDouble);
            }
        } else if (obj.getClass().isArray()) {
            Object[] objArr = (Object[]) obj;
            m696a(8);
            m697b((byte) 9, i);
            mo413a(objArr.length, 0);
            for (Object a4 : objArr) {
                mo416a(a4, 0);
            }
        } else if (obj instanceof Collection) {
            mo418a((Collection) obj, i);
        } else {
            throw new C0241r("write object error: unsupport type. " + obj.getClass());
        }
    }

    /* renamed from: a */
    public final int mo410a(String str) {
        this.f617b = str;
        return 0;
    }
}
