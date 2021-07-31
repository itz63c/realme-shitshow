package com.p000a.p001a.p006d;

/* renamed from: com.a.a.d.i */
/* compiled from: StringPool */
final class C0119i {

    /* renamed from: a */
    private final String[] f242a = new String[512];

    C0119i() {
    }

    /* renamed from: a */
    public final String mo139a(char[] cArr, int i, int i2) {
        int i3 = 0;
        for (int i4 = i; i4 < i + i2; i4++) {
            i3 = (i3 * 31) + cArr[i4];
        }
        int i5 = ((i3 >>> 20) ^ (i3 >>> 12)) ^ i3;
        int length = (this.f242a.length - 1) & (i5 ^ ((i5 >>> 7) ^ (i5 >>> 4)));
        String str = this.f242a[length];
        if (str == null || str.length() != i2) {
            String str2 = new String(cArr, i, i2);
            this.f242a[length] = str2;
            return str2;
        }
        for (int i6 = 0; i6 < i2; i6++) {
            if (str.charAt(i6) != cArr[i + i6]) {
                String str3 = new String(cArr, i, i2);
                this.f242a[length] = str3;
                return str3;
            }
        }
        return str;
    }
}
