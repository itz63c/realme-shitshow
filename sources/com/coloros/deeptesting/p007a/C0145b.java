package com.coloros.deeptesting.p007a;

import org.apache.commons.codec.BinaryDecoder;
import org.apache.commons.codec.BinaryEncoder;

/* renamed from: com.coloros.deeptesting.a.b */
/* compiled from: AesEncryptUtils */
abstract class C0145b implements BinaryDecoder, BinaryEncoder {
    @Deprecated

    /* renamed from: a */
    final byte f267a;

    /* renamed from: b */
    final int f268b;

    /* renamed from: c */
    private final int f269c;

    /* renamed from: d */
    private final int f270d;

    /* renamed from: e */
    private final int f271e;

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public abstract void mo172a(byte[] bArr, int i, int i2, C0146c cVar);

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public abstract boolean mo173a(byte b);

    /* access modifiers changed from: package-private */
    /* renamed from: b */
    public abstract void mo176b(byte[] bArr, int i, int i2, C0146c cVar);

    C0145b(int i) {
        this(0, i);
    }

    private C0145b(int i, int i2) {
        this.f269c = 3;
        this.f270d = 4;
        this.f268b = 0;
        this.f271e = i2;
        this.f267a = 61;
    }

    /* renamed from: a */
    static byte[] m337a(int i, C0146c cVar) {
        if (cVar.f274c != null && cVar.f274c.length >= cVar.f275d + i) {
            return cVar.f274c;
        }
        if (cVar.f274c == null) {
            cVar.f274c = new byte[8192];
            cVar.f275d = 0;
            cVar.f276e = 0;
        } else {
            byte[] bArr = new byte[(cVar.f274c.length * 2)];
            System.arraycopy(cVar.f274c, 0, bArr, 0, cVar.f274c.length);
            cVar.f274c = bArr;
        }
        return cVar.f274c;
    }

    /* renamed from: a */
    private static int m336a(byte[] bArr, int i, C0146c cVar) {
        int i2;
        if (cVar.f274c != null) {
            if (cVar.f274c != null) {
                i2 = cVar.f275d - cVar.f276e;
            } else {
                i2 = 0;
            }
            int min = Math.min(i2, i);
            System.arraycopy(cVar.f274c, cVar.f276e, bArr, 0, min);
            cVar.f276e += min;
            if (cVar.f276e >= cVar.f275d) {
                cVar.f274c = null;
            }
            return min;
        } else if (cVar.f277f) {
            return -1;
        } else {
            return 0;
        }
    }

    public byte[] decode(byte[] bArr) {
        if (bArr == null || bArr.length == 0) {
            return bArr;
        }
        C0146c cVar = new C0146c();
        mo176b(bArr, 0, bArr.length, cVar);
        mo176b(bArr, 0, -1, cVar);
        byte[] bArr2 = new byte[cVar.f275d];
        m336a(bArr2, bArr2.length, cVar);
        return bArr2;
    }

    public byte[] encode(byte[] bArr) {
        if (bArr == null || bArr.length == 0) {
            return bArr;
        }
        C0146c cVar = new C0146c();
        mo172a(bArr, 0, bArr.length, cVar);
        mo172a(bArr, 0, -1, cVar);
        byte[] bArr2 = new byte[(cVar.f275d - cVar.f276e)];
        m336a(bArr2, bArr2.length, cVar);
        return bArr2;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public final boolean mo174a(byte[] bArr) {
        if (bArr == null) {
            return false;
        }
        for (byte b : bArr) {
            if (this.f267a == b || mo173a(b)) {
                return true;
            }
        }
        return false;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: b */
    public final long mo175b(byte[] bArr) {
        long length = ((((long) (bArr.length + this.f269c)) - 1) / ((long) this.f269c)) * ((long) this.f270d);
        if (this.f268b > 0) {
            return length + ((((((long) this.f268b) + length) - 1) / ((long) this.f268b)) * ((long) this.f271e));
        }
        return length;
    }

    public Object decode(Object obj) {
        return null;
    }

    public Object encode(Object obj) {
        return null;
    }
}
