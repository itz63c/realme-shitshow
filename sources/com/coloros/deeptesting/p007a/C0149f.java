package com.coloros.deeptesting.p007a;

import java.io.UnsupportedEncodingException;
import neton.client.config.Constants;
import neton.client.dns.DnsMode;

/* renamed from: com.coloros.deeptesting.a.f */
/* compiled from: AesEncryptUtils */
final class C0149f extends C0145b {

    /* renamed from: c */
    private static final byte[] f282c = {13, 10};

    /* renamed from: d */
    private static final byte[] f283d = {65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 43, 47};

    /* renamed from: e */
    private static final byte[] f284e = {65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 45, 95};

    /* renamed from: f */
    private static final byte[] f285f = {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 62, -1, 62, -1, 63, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, -1, -1, -1, -1, -1, -1, -1, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, -1, -1, -1, -1, 63, -1, 26, 27, 28, 29, 30, 31, Constants.PROTOCOL_VERSION, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51};

    /* renamed from: g */
    private final byte[] f286g;

    /* renamed from: h */
    private final byte[] f287h;

    /* renamed from: i */
    private final byte[] f288i;

    /* renamed from: j */
    private final int f289j;

    /* renamed from: k */
    private final int f290k;

    C0149f() {
        this((byte) 0);
    }

    private C0149f(byte b) {
        this(f282c);
    }

    private C0149f(byte[] bArr) {
        this(bArr, (byte) 0);
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private C0149f(byte[] r5, byte r6) {
        /*
            r4 = this;
            r2 = 4
            r1 = 0
            if (r5 != 0) goto L_0x0036
            r0 = 0
        L_0x0005:
            r4.<init>(r0)
            byte[] r0 = f285f
            r4.f287h = r0
            if (r5 == 0) goto L_0x004d
            boolean r0 = r4.mo174a((byte[]) r5)
            if (r0 == 0) goto L_0x003e
            java.lang.String r0 = new java.lang.String     // Catch:{ UnsupportedEncodingException -> 0x0038 }
            java.lang.String r2 = "UTF-8"
            r0.<init>(r5, r2)     // Catch:{ UnsupportedEncodingException -> 0x0038 }
        L_0x001b:
            java.lang.IllegalArgumentException r1 = new java.lang.IllegalArgumentException
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            java.lang.String r3 = "lineSeparator must not contain base64 characters: ["
            r2.<init>(r3)
            java.lang.StringBuilder r0 = r2.append(r0)
            java.lang.String r2 = "]"
            java.lang.StringBuilder r0 = r0.append(r2)
            java.lang.String r0 = r0.toString()
            r1.<init>(r0)
            throw r1
        L_0x0036:
            int r0 = r5.length
            goto L_0x0005
        L_0x0038:
            r0 = move-exception
            r0.printStackTrace()
            r0 = r1
            goto L_0x001b
        L_0x003e:
            r4.f290k = r2
            r4.f288i = r1
        L_0x0042:
            int r0 = r4.f290k
            int r0 = r0 + -1
            r4.f289j = r0
            byte[] r0 = f283d
            r4.f286g = r0
            return
        L_0x004d:
            r4.f290k = r2
            r4.f288i = r1
            goto L_0x0042
        */
        throw new UnsupportedOperationException("Method not decompiled: com.coloros.deeptesting.p007a.C0149f.<init>(byte[], byte):void");
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v6, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v30, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v31, resolved type: byte} */
    /* access modifiers changed from: package-private */
    /* JADX WARNING: Multi-variable type inference failed */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void mo172a(byte[] r8, int r9, int r10, com.coloros.deeptesting.p007a.C0146c r11) {
        /*
            r7 = this;
            r2 = 0
            boolean r0 = r11.f277f
            if (r0 == 0) goto L_0x0006
        L_0x0005:
            return
        L_0x0006:
            if (r10 >= 0) goto L_0x00e1
            r0 = 1
            r11.f277f = r0
            int r0 = r11.f279h
            if (r0 != 0) goto L_0x0013
            int r0 = r7.f268b
            if (r0 == 0) goto L_0x0005
        L_0x0013:
            int r0 = r7.f290k
            byte[] r0 = m337a(r0, r11)
            int r1 = r11.f275d
            int r3 = r11.f279h
            switch(r3) {
                case 0: goto L_0x0075;
                case 1: goto L_0x0037;
                case 2: goto L_0x009a;
                default: goto L_0x0020;
            }
        L_0x0020:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r2 = "Impossible modulus "
            r1.<init>(r2)
            int r2 = r11.f279h
            java.lang.StringBuilder r1 = r1.append(r2)
            java.lang.String r1 = r1.toString()
            r0.<init>(r1)
            throw r0
        L_0x0037:
            int r3 = r11.f275d
            int r4 = r3 + 1
            r11.f275d = r4
            byte[] r4 = r7.f286g
            int r5 = r11.f272a
            int r5 = r5 >> 2
            r5 = r5 & 63
            byte r4 = r4[r5]
            r0[r3] = r4
            int r3 = r11.f275d
            int r4 = r3 + 1
            r11.f275d = r4
            byte[] r4 = r7.f286g
            int r5 = r11.f272a
            int r5 = r5 << 4
            r5 = r5 & 63
            byte r4 = r4[r5]
            r0[r3] = r4
            byte[] r3 = r7.f286g
            byte[] r4 = f283d
            if (r3 != r4) goto L_0x0075
            int r3 = r11.f275d
            int r4 = r3 + 1
            r11.f275d = r4
            byte r4 = r7.f267a
            r0[r3] = r4
            int r3 = r11.f275d
            int r4 = r3 + 1
            r11.f275d = r4
            byte r4 = r7.f267a
            r0[r3] = r4
        L_0x0075:
            int r3 = r11.f278g
            int r4 = r11.f275d
            int r1 = r4 - r1
            int r1 = r1 + r3
            r11.f278g = r1
            int r1 = r7.f268b
            if (r1 <= 0) goto L_0x0005
            int r1 = r11.f278g
            if (r1 <= 0) goto L_0x0005
            byte[] r1 = r7.f288i
            int r3 = r11.f275d
            byte[] r4 = r7.f288i
            int r4 = r4.length
            java.lang.System.arraycopy(r1, r2, r0, r3, r4)
            int r0 = r11.f275d
            byte[] r1 = r7.f288i
            int r1 = r1.length
            int r0 = r0 + r1
            r11.f275d = r0
            goto L_0x0005
        L_0x009a:
            int r3 = r11.f275d
            int r4 = r3 + 1
            r11.f275d = r4
            byte[] r4 = r7.f286g
            int r5 = r11.f272a
            int r5 = r5 >> 10
            r5 = r5 & 63
            byte r4 = r4[r5]
            r0[r3] = r4
            int r3 = r11.f275d
            int r4 = r3 + 1
            r11.f275d = r4
            byte[] r4 = r7.f286g
            int r5 = r11.f272a
            int r5 = r5 >> 4
            r5 = r5 & 63
            byte r4 = r4[r5]
            r0[r3] = r4
            int r3 = r11.f275d
            int r4 = r3 + 1
            r11.f275d = r4
            byte[] r4 = r7.f286g
            int r5 = r11.f272a
            int r5 = r5 << 2
            r5 = r5 & 63
            byte r4 = r4[r5]
            r0[r3] = r4
            byte[] r3 = r7.f286g
            byte[] r4 = f283d
            if (r3 != r4) goto L_0x0075
            int r3 = r11.f275d
            int r4 = r3 + 1
            r11.f275d = r4
            byte r4 = r7.f267a
            r0[r3] = r4
            goto L_0x0075
        L_0x00e1:
            r1 = r2
        L_0x00e2:
            if (r1 >= r10) goto L_0x0005
            int r0 = r7.f290k
            byte[] r4 = m337a(r0, r11)
            int r0 = r11.f279h
            int r0 = r0 + 1
            int r0 = r0 % 3
            r11.f279h = r0
            int r3 = r9 + 1
            byte r0 = r8[r9]
            if (r0 >= 0) goto L_0x00fa
            int r0 = r0 + 256
        L_0x00fa:
            int r5 = r11.f272a
            int r5 = r5 << 8
            int r0 = r0 + r5
            r11.f272a = r0
            int r0 = r11.f279h
            if (r0 != 0) goto L_0x016f
            int r0 = r11.f275d
            int r5 = r0 + 1
            r11.f275d = r5
            byte[] r5 = r7.f286g
            int r6 = r11.f272a
            int r6 = r6 >> 18
            r6 = r6 & 63
            byte r5 = r5[r6]
            r4[r0] = r5
            int r0 = r11.f275d
            int r5 = r0 + 1
            r11.f275d = r5
            byte[] r5 = r7.f286g
            int r6 = r11.f272a
            int r6 = r6 >> 12
            r6 = r6 & 63
            byte r5 = r5[r6]
            r4[r0] = r5
            int r0 = r11.f275d
            int r5 = r0 + 1
            r11.f275d = r5
            byte[] r5 = r7.f286g
            int r6 = r11.f272a
            int r6 = r6 >> 6
            r6 = r6 & 63
            byte r5 = r5[r6]
            r4[r0] = r5
            int r0 = r11.f275d
            int r5 = r0 + 1
            r11.f275d = r5
            byte[] r5 = r7.f286g
            int r6 = r11.f272a
            r6 = r6 & 63
            byte r5 = r5[r6]
            r4[r0] = r5
            int r0 = r11.f278g
            int r0 = r0 + 4
            r11.f278g = r0
            int r0 = r7.f268b
            if (r0 <= 0) goto L_0x016f
            int r0 = r7.f268b
            int r5 = r11.f278g
            if (r0 > r5) goto L_0x016f
            byte[] r0 = r7.f288i
            int r5 = r11.f275d
            byte[] r6 = r7.f288i
            int r6 = r6.length
            java.lang.System.arraycopy(r0, r2, r4, r5, r6)
            int r0 = r11.f275d
            byte[] r4 = r7.f288i
            int r4 = r4.length
            int r0 = r0 + r4
            r11.f275d = r0
            r11.f278g = r2
        L_0x016f:
            int r0 = r1 + 1
            r1 = r0
            r9 = r3
            goto L_0x00e2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.coloros.deeptesting.p007a.C0149f.mo172a(byte[], int, int, com.coloros.deeptesting.a.c):void");
    }

    /* access modifiers changed from: package-private */
    /* renamed from: b */
    public final void mo176b(byte[] bArr, int i, int i2, C0146c cVar) {
        byte b;
        if (!cVar.f277f) {
            if (i2 < 0) {
                cVar.f277f = true;
            }
            int i3 = 0;
            while (true) {
                if (i3 >= i2) {
                    break;
                }
                byte[] a = m337a(this.f289j, cVar);
                int i4 = i + 1;
                byte b2 = bArr[i];
                if (b2 == this.f267a) {
                    cVar.f277f = true;
                    break;
                }
                if (b2 >= 0 && b2 < f285f.length && (b = f285f[b2]) >= 0) {
                    cVar.f279h = (cVar.f279h + 1) % 4;
                    cVar.f272a = b + (cVar.f272a << 6);
                    if (cVar.f279h == 0) {
                        int i5 = cVar.f275d;
                        cVar.f275d = i5 + 1;
                        a[i5] = (byte) ((cVar.f272a >> 16) & 255);
                        int i6 = cVar.f275d;
                        cVar.f275d = i6 + 1;
                        a[i6] = (byte) ((cVar.f272a >> 8) & 255);
                        int i7 = cVar.f275d;
                        cVar.f275d = i7 + 1;
                        a[i7] = (byte) (cVar.f272a & 255);
                    }
                }
                i3++;
                i = i4;
            }
            if (cVar.f277f && cVar.f279h != 0) {
                byte[] a2 = m337a(this.f289j, cVar);
                switch (cVar.f279h) {
                    case 1:
                        return;
                    case DnsMode.DNS_MODE_HTTP /*2*/:
                        cVar.f272a >>= 4;
                        int i8 = cVar.f275d;
                        cVar.f275d = i8 + 1;
                        a2[i8] = (byte) (cVar.f272a & 255);
                        return;
                    case 3:
                        cVar.f272a >>= 2;
                        int i9 = cVar.f275d;
                        cVar.f275d = i9 + 1;
                        a2[i9] = (byte) ((cVar.f272a >> 8) & 255);
                        int i10 = cVar.f275d;
                        cVar.f275d = i10 + 1;
                        a2[i10] = (byte) (cVar.f272a & 255);
                        return;
                    default:
                        throw new IllegalStateException("Impossible modulus " + cVar.f279h);
                }
            }
        }
    }

    /* renamed from: c */
    static String m343c(byte[] bArr) {
        try {
            if (!(bArr == null || bArr.length == 0)) {
                C0149f fVar = new C0149f(f282c, (byte) 0);
                long b = fVar.mo175b(bArr);
                if (b > 2147483647L) {
                    throw new IllegalArgumentException("Input array too big, the output array would be bigger (" + b + ") than the specified maximum size of 2147483647");
                }
                bArr = fVar.encode(bArr);
            }
            return new String(bArr, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
    }

    /* renamed from: d */
    static byte[] m344d(byte[] bArr) {
        return new C0149f().decode(bArr);
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public final boolean mo173a(byte b) {
        return b >= 0 && b < this.f287h.length && this.f287h[b] != -1;
    }
}
