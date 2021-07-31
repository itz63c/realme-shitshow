package com.coloros.deeptesting.p007a;

import android.text.TextUtils;
import com.p000a.p001a.C0125j;
import java.util.ArrayList;
import java.util.Random;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

/* renamed from: com.coloros.deeptesting.a.a */
/* compiled from: AesEncryptUtils */
public final class C0144a {

    /* renamed from: a */
    private static final String[] f265a = {"oppo1997", "baed2017", "java7865", "231uiedn", "09e32ji6", "0oiu3jdy", "0pej387l", "2dkliuyt", "20odiuye", "87j3id7w"};

    /* renamed from: b */
    private static Random f266b = new Random();

    /* renamed from: b */
    public static String m333b(String str) {
        C0148e eVar;
        try {
            eVar = (C0148e) new C0125j().mo143a(str, C0148e.class);
        } catch (Exception e) {
            C0150g.m348a("AesEncryptUtils", "parse json response error");
            eVar = null;
        }
        if (eVar == null || eVar.f281a == null || eVar.f281a.length() < 15) {
            return null;
        }
        return m332a(eVar.f281a.substring(0, eVar.f281a.length() - 15), m335c(eVar.f281a.substring(eVar.f281a.length() - 15, eVar.f281a.length())));
    }

    /* renamed from: a */
    private static String m332a(String str, String str2) {
        try {
            SecretKeySpec secretKeySpec = new SecretKeySpec(str2.getBytes("UTF-8"), "AES");
            Cipher instance = Cipher.getInstance("AES/ECB/PKCS5Padding");
            instance.init(2, secretKeySpec);
            return new String(instance.doFinal(C0149f.m344d(str.getBytes("UTF-8"))), "UTF-8");
        } catch (Exception e) {
            return null;
        }
    }

    /* renamed from: b */
    private static String m334b(String str, String str2) {
        try {
            SecretKeySpec secretKeySpec = new SecretKeySpec(str2.getBytes("UTF-8"), "AES");
            Cipher instance = Cipher.getInstance("AES/ECB/PKCS5Padding");
            instance.init(1, secretKeySpec);
            return C0149f.m343c(instance.doFinal(str.getBytes("UTF-8")));
        } catch (Exception e) {
            return null;
        }
    }

    /* renamed from: c */
    private static String m335c(String str) {
        int i = 0;
        String substring = str.substring(0, 1);
        if (!TextUtils.isEmpty(substring)) {
            i = Integer.parseInt(substring);
        }
        String str2 = f265a[i];
        return str2 + str.substring(4, 12);
    }

    /* renamed from: a */
    private static String m330a(int i) {
        ArrayList arrayList = new ArrayList();
        for (int i2 = 64; i2 <= 90; i2++) {
            arrayList.add(String.valueOf((char) i2));
        }
        for (int i3 = 97; i3 <= 122; i3++) {
            arrayList.add(String.valueOf((char) i3));
        }
        for (int i4 = 33; i4 <= 43; i4++) {
            if (!(i4 == 34 || i4 == 39 || i4 == 42)) {
                arrayList.add(String.valueOf((char) i4));
            }
        }
        arrayList.add("_");
        for (int i5 = 0; i5 < 10; i5++) {
            arrayList.add(String.valueOf(i5));
        }
        int size = arrayList.size();
        StringBuilder sb = new StringBuilder();
        do {
            sb.append((String) arrayList.get(f266b.nextInt(size - 1) + 1));
            i--;
        } while (i > 0);
        return sb.toString();
    }

    /* renamed from: a */
    public static String m331a(String str) {
        String str2 = f266b.nextInt(10) + m330a(14);
        String b = m334b(str, m335c(str2));
        if (b != null) {
            return C0151h.m352a((Object) new C0147d(b + str2));
        }
        C0150g.m348a("AesEncryptUtils", "encryptText is null");
        return null;
    }
}
