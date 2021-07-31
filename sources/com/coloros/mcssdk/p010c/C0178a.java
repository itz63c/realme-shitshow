package com.coloros.mcssdk.p010c;

import android.text.TextUtils;
import android.util.Base64;
import com.coloros.neton.BuildConfig;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

/* renamed from: com.coloros.mcssdk.c.a */
public final class C0178a {
    /* renamed from: b */
    private static byte[] m411b(String str) {
        try {
            return str.getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            return new byte[0];
        }
    }

    /* renamed from: a */
    public static String m410a(String str) {
        if (!TextUtils.isEmpty(str)) {
            try {
                byte[] b = m411b("com.nearme.mcs");
                int length = b.length % 2 == 0 ? b.length : b.length - 1;
                for (int i = 0; i < length; i += 2) {
                    byte b2 = b[i];
                    b[i] = b[i + 1];
                    b[i + 1] = b2;
                }
                String str2 = b != null ? new String(b, Charset.forName("UTF-8")) : BuildConfig.FLAVOR;
                Cipher instance = Cipher.getInstance("DES");
                instance.init(2, SecretKeyFactory.getInstance("DES").generateSecret(new DESKeySpec(Base64.decode(str2, 0))));
                return new String(instance.doFinal(Base64.decode(str, 0)), Charset.defaultCharset()).trim();
            } catch (Exception e) {
                C0179b.m413b("desDecrypt-" + e.getMessage());
            }
        }
        return BuildConfig.FLAVOR;
    }
}
