package com.tencent.bugly.p014a;

/* renamed from: com.tencent.bugly.a.u */
/* compiled from: BUGLY */
public final class C0244u {

    /* renamed from: a */
    private static final char[] f598a = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

    /* renamed from: a */
    public static String m665a(byte[] bArr) {
        if (bArr == null || bArr.length == 0) {
            return null;
        }
        char[] cArr = new char[(bArr.length * 2)];
        for (int i = 0; i < bArr.length; i++) {
            byte b = bArr[i];
            cArr[(i * 2) + 1] = f598a[b & 15];
            cArr[i * 2] = f598a[((byte) (b >>> 4)) & 15];
        }
        return new String(cArr);
    }
}
