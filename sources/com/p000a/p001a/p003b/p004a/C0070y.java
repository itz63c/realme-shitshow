package com.p000a.p001a.p003b.p004a;

import com.p000a.p001a.C0010af;
import com.p000a.p001a.C0011ag;
import com.p000a.p001a.C0136u;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.InetAddress;
import java.net.URI;
import java.net.URL;
import java.util.BitSet;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.UUID;

/* renamed from: com.a.a.b.a.y */
/* compiled from: TypeAdapters */
public final class C0070y {

    /* renamed from: A */
    public static final C0010af<StringBuffer> f69A = new C0022ah();

    /* renamed from: B */
    public static final C0011ag f70B = m149a(StringBuffer.class, f69A);

    /* renamed from: C */
    public static final C0010af<URL> f71C = new C0023ai();

    /* renamed from: D */
    public static final C0011ag f72D = m149a(URL.class, f71C);

    /* renamed from: E */
    public static final C0010af<URI> f73E = new C0024aj();

    /* renamed from: F */
    public static final C0011ag f74F = m149a(URI.class, f73E);

    /* renamed from: G */
    public static final C0010af<InetAddress> f75G = new C0026al();

    /* renamed from: H */
    public static final C0011ag f76H = new C0038ax(InetAddress.class, f75G);

    /* renamed from: I */
    public static final C0010af<UUID> f77I = new C0027am();

    /* renamed from: J */
    public static final C0011ag f78J = m149a(UUID.class, f77I);

    /* renamed from: K */
    public static final C0011ag f79K = new C0028an();

    /* renamed from: L */
    public static final C0010af<Calendar> f80L = new C0030ap();

    /* renamed from: M */
    public static final C0011ag f81M = new C0037aw(Calendar.class, GregorianCalendar.class, f80L);

    /* renamed from: N */
    public static final C0010af<Locale> f82N = new C0031aq();

    /* renamed from: O */
    public static final C0011ag f83O = m149a(Locale.class, f82N);

    /* renamed from: P */
    public static final C0010af<C0136u> f84P = new C0032ar();

    /* renamed from: Q */
    public static final C0011ag f85Q = m149a(C0136u.class, f84P);

    /* renamed from: R */
    public static final C0011ag f86R = new C0033as();

    /* renamed from: a */
    public static final C0010af<Class> f87a = new C0071z();

    /* renamed from: b */
    public static final C0011ag f88b = m149a(Class.class, f87a);

    /* renamed from: c */
    public static final C0010af<BitSet> f89c = new C0025ak();

    /* renamed from: d */
    public static final C0011ag f90d = m149a(BitSet.class, f89c);

    /* renamed from: e */
    public static final C0010af<Boolean> f91e = new C0036av();

    /* renamed from: f */
    public static final C0010af<Boolean> f92f = new C0040az();

    /* renamed from: g */
    public static final C0011ag f93g = m150a(Boolean.TYPE, Boolean.class, f91e);

    /* renamed from: h */
    public static final C0010af<Number> f94h = new C0042ba();

    /* renamed from: i */
    public static final C0011ag f95i = m150a(Byte.TYPE, Byte.class, f94h);

    /* renamed from: j */
    public static final C0010af<Number> f96j = new C0043bb();

    /* renamed from: k */
    public static final C0011ag f97k = m150a(Short.TYPE, Short.class, f96j);

    /* renamed from: l */
    public static final C0010af<Number> f98l = new C0044bc();

    /* renamed from: m */
    public static final C0011ag f99m = m150a(Integer.TYPE, Integer.class, f98l);

    /* renamed from: n */
    public static final C0010af<Number> f100n = new C0045bd();

    /* renamed from: o */
    public static final C0010af<Number> f101o = new C0046be();

    /* renamed from: p */
    public static final C0010af<Number> f102p = new C0015aa();

    /* renamed from: q */
    public static final C0010af<Number> f103q = new C0016ab();

    /* renamed from: r */
    public static final C0011ag f104r = m149a(Number.class, f103q);

    /* renamed from: s */
    public static final C0010af<Character> f105s = new C0017ac();

    /* renamed from: t */
    public static final C0011ag f106t = m150a(Character.TYPE, Character.class, f105s);

    /* renamed from: u */
    public static final C0010af<String> f107u = new C0018ad();

    /* renamed from: v */
    public static final C0010af<BigDecimal> f108v = new C0019ae();

    /* renamed from: w */
    public static final C0010af<BigInteger> f109w = new C0020af();

    /* renamed from: x */
    public static final C0011ag f110x = m149a(String.class, f107u);

    /* renamed from: y */
    public static final C0010af<StringBuilder> f111y = new C0021ag();

    /* renamed from: z */
    public static final C0011ag f112z = m149a(StringBuilder.class, f111y);

    /* renamed from: a */
    public static <TT> C0011ag m149a(Class<TT> cls, C0010af<TT> afVar) {
        return new C0034at(cls, afVar);
    }

    /* renamed from: a */
    public static <TT> C0011ag m150a(Class<TT> cls, Class<TT> cls2, C0010af<? super TT> afVar) {
        return new C0035au(cls, cls2, afVar);
    }
}
