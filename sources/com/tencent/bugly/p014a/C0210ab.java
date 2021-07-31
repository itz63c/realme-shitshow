package com.tencent.bugly.p014a;

import java.nio.ByteBuffer;

/* renamed from: com.tencent.bugly.a.ab */
/* compiled from: BUGLY */
public final class C0210ab {
    /* renamed from: a */
    public static boolean m518a(int i) {
        return 1 == i;
    }

    /* renamed from: a */
    public static boolean m519a(Object obj, Object obj2) {
        return obj.equals(obj2);
    }

    /* renamed from: a */
    public static byte[] m520a(ByteBuffer byteBuffer) {
        byte[] bArr = new byte[byteBuffer.position()];
        System.arraycopy(byteBuffer.array(), 0, bArr, 0, bArr.length);
        return bArr;
    }

    static {
        byte[] bArr = {48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 65, 66, 67, 68, 69, 70};
        byte[] bArr2 = new byte[256];
        byte[] bArr3 = new byte[256];
        for (int i = 0; i < 256; i++) {
            bArr2[i] = bArr[i >>> 4];
            bArr3[i] = bArr[i & 15];
        }
    }
}
