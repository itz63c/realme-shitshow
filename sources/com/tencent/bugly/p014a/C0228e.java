package com.tencent.bugly.p014a;

import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;

/* renamed from: com.tencent.bugly.a.e */
/* compiled from: BUGLY */
public final class C0228e implements C0229f {

    /* renamed from: a */
    private String f482a = null;

    /* renamed from: a */
    public final byte[] mo390a(byte[] bArr) {
        if (this.f482a == null || bArr == null) {
            return null;
        }
        Cipher instance = Cipher.getInstance("DES/CBC/PKCS5Padding");
        instance.init(2, SecretKeyFactory.getInstance("DES").generateSecret(new DESKeySpec(this.f482a.getBytes("UTF-8"))), new IvParameterSpec(this.f482a.getBytes("UTF-8")));
        return instance.doFinal(bArr);
    }

    /* renamed from: a */
    public final void mo389a(String str) {
        if (str != null) {
            this.f482a = str;
        }
    }
}
