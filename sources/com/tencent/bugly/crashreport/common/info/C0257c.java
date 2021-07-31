package com.tencent.bugly.crashreport.common.info;

import android.content.Context;
import android.content.pm.PackageInfo;
import com.coloros.neton.BuildConfig;
import com.tencent.bugly.p014a.C0208a;
import com.tencent.bugly.p014a.C0221am;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import neton.client.config.Constants;

/* renamed from: com.tencent.bugly.crashreport.common.info.c */
/* compiled from: BUGLY */
public final class C0257c {

    /* renamed from: R */
    private static C0257c f662R = null;

    /* renamed from: A */
    private String f663A = BuildConfig.FLAVOR;

    /* renamed from: B */
    private String f664B = null;

    /* renamed from: C */
    private String f665C = null;

    /* renamed from: D */
    private String f666D = null;

    /* renamed from: E */
    private String f667E = null;

    /* renamed from: F */
    private long f668F = -1;

    /* renamed from: G */
    private long f669G = -1;

    /* renamed from: H */
    private long f670H = -1;

    /* renamed from: I */
    private String f671I = null;

    /* renamed from: J */
    private String f672J = null;

    /* renamed from: K */
    private Map<String, PlugInBean> f673K = null;

    /* renamed from: L */
    private boolean f674L = true;

    /* renamed from: M */
    private String f675M = null;

    /* renamed from: N */
    private String f676N = null;

    /* renamed from: O */
    private Boolean f677O = null;

    /* renamed from: P */
    private String f678P = null;

    /* renamed from: Q */
    private Map<String, PlugInBean> f679Q = null;

    /* renamed from: S */
    private int f680S = -1;

    /* renamed from: T */
    private int f681T = -1;

    /* renamed from: U */
    private Map<String, String> f682U = new HashMap();

    /* renamed from: V */
    private Map<String, String> f683V = new HashMap();

    /* renamed from: W */
    private final Object f684W = new Object();

    /* renamed from: X */
    private final Object f685X = new Object();

    /* renamed from: a */
    public final long f686a = System.currentTimeMillis();

    /* renamed from: b */
    public final byte f687b;

    /* renamed from: c */
    public String f688c;

    /* renamed from: d */
    public final String f689d;

    /* renamed from: e */
    public final String f690e;

    /* renamed from: f */
    public final String f691f;

    /* renamed from: g */
    public final String f692g;

    /* renamed from: h */
    public long f693h;

    /* renamed from: i */
    public String f694i = null;

    /* renamed from: j */
    public String f695j = null;

    /* renamed from: k */
    public String f696k = null;

    /* renamed from: l */
    public String f697l = null;

    /* renamed from: m */
    public List<String> f698m = null;

    /* renamed from: n */
    public String f699n = "unknown";

    /* renamed from: o */
    public long f700o = 0;

    /* renamed from: p */
    public long f701p = 0;

    /* renamed from: q */
    public long f702q = 0;

    /* renamed from: r */
    public long f703r = 0;

    /* renamed from: s */
    public boolean f704s = false;

    /* renamed from: t */
    public String f705t = null;

    /* renamed from: u */
    public String f706u = null;

    /* renamed from: v */
    private final Context f707v;

    /* renamed from: w */
    private String f708w;

    /* renamed from: x */
    private String f709x;

    /* renamed from: y */
    private String f710y = "unknown";

    /* renamed from: z */
    private String f711z = "unknown";

    private C0257c(Context context) {
        Context applicationContext;
        boolean z;
        if (context == null) {
            applicationContext = context;
        } else {
            applicationContext = context.getApplicationContext();
            if (applicationContext == null) {
                applicationContext = context;
            }
        }
        this.f707v = applicationContext;
        this.f687b = 1;
        PackageInfo b = C0255a.m726b(context);
        if (b != null) {
            this.f694i = C0255a.m723a(b);
            this.f705t = this.f694i;
            this.f706u = C0255a.m727b(b);
        }
        this.f688c = C0255a.m722a(context);
        this.f689d = C0255a.m720a();
        this.f690e = C0208a.m507i();
        this.f691f = C0208a.m489b();
        this.f692g = "Android " + C0208a.m495c() + ",level " + C0208a.m498d();
        new StringBuilder().append(this.f691f).append(Constants.SPLITER).append(this.f692g);
        Map<String, String> c = C0255a.m728c(context);
        if (c != null) {
            try {
                this.f698m = C0255a.m724a(c);
                String str = c.get(C0255a.f656a);
                if (str != null) {
                    this.f676N = str;
                }
                String str2 = c.get(C0255a.f658c);
                if (str2 != null) {
                    this.f694i = str2;
                }
                String str3 = c.get(C0255a.f657b);
                if (str3 != null) {
                    this.f695j = str3;
                }
                String str4 = c.get(C0255a.f659d);
                if (str4 != null) {
                    if (str4.toLowerCase().equals("true")) {
                        z = true;
                    } else {
                        z = false;
                    }
                    this.f704s = z;
                }
            } catch (Throwable th) {
                if (!C0221am.m594a(th)) {
                    th.printStackTrace();
                }
            }
        }
        C0221am.m597c("com info create end", new Object[0]);
    }

    /* renamed from: a */
    public static synchronized C0257c m731a(Context context) {
        C0257c cVar;
        synchronized (C0257c.class) {
            if (f662R == null) {
                f662R = new C0257c(context);
            }
            cVar = f662R;
        }
        return cVar;
    }

    /* renamed from: a */
    public static synchronized C0257c m730a() {
        C0257c cVar;
        synchronized (C0257c.class) {
            cVar = f662R;
        }
        return cVar;
    }

    /* renamed from: b */
    public final String mo435b() {
        if (this.f708w == null) {
            synchronized (this.f684W) {
                if (this.f708w == null) {
                    this.f708w = UUID.randomUUID().toString();
                }
            }
        }
        return this.f708w;
    }

    /* renamed from: c */
    public final String mo437c() {
        return this.f676N;
    }

    /* renamed from: d */
    public final synchronized String mo438d() {
        String str;
        if (this.f709x != null) {
            str = this.f709x;
        } else {
            this.f709x = mo441g() + "|" + mo443i() + "|" + mo444j();
            str = this.f709x;
        }
        return str;
    }

    /* renamed from: e */
    public final synchronized String mo439e() {
        return this.f711z;
    }

    /* renamed from: a */
    public final synchronized void mo434a(String str) {
        this.f711z = str;
    }

    /* renamed from: f */
    public final synchronized String mo440f() {
        return this.f663A;
    }

    /* renamed from: b */
    public final synchronized void mo436b(String str) {
        this.f663A = str;
    }

    /* renamed from: g */
    public final synchronized String mo441g() {
        String str;
        if (!this.f674L) {
            str = BuildConfig.FLAVOR;
        } else {
            if (this.f664B == null) {
                this.f664B = C0208a.m475a(this.f707v);
            }
            str = this.f664B;
        }
        return str;
    }

    /* renamed from: h */
    public final synchronized String mo442h() {
        String str;
        if (!this.f674L) {
            str = BuildConfig.FLAVOR;
        } else {
            if (this.f665C == null) {
                this.f665C = C0208a.m500d(this.f707v);
            }
            str = this.f665C;
        }
        return str;
    }

    /* renamed from: i */
    public final synchronized String mo443i() {
        String str;
        if (!this.f674L) {
            str = BuildConfig.FLAVOR;
        } else {
            if (this.f666D == null) {
                this.f666D = C0208a.m490b(this.f707v);
            }
            str = this.f666D;
        }
        return str;
    }

    /* renamed from: j */
    public final synchronized String mo444j() {
        String str;
        if (!this.f674L) {
            str = BuildConfig.FLAVOR;
        } else {
            if (this.f667E == null) {
                this.f667E = C0208a.m496c(this.f707v);
            }
            str = this.f667E;
        }
        return str;
    }

    /* renamed from: k */
    public final synchronized long mo445k() {
        if (this.f669G <= 0) {
            this.f669G = C0208a.m503f();
        }
        return this.f669G;
    }

    /* renamed from: l */
    public final synchronized long mo446l() {
        if (this.f670H <= 0) {
            this.f670H = C0208a.m505g();
        }
        return this.f670H;
    }

    /* renamed from: m */
    public final synchronized String mo447m() {
        if (this.f671I == null) {
            this.f671I = C0208a.m501e();
        }
        return this.f671I;
    }

    /* renamed from: n */
    public final String mo448n() {
        String str;
        if (this.f672J == null) {
            synchronized (this.f685X) {
                if (this.f672J == null) {
                    Context context = this.f707v;
                    if ("ro.board.platform".trim().equals(BuildConfig.FLAVOR)) {
                        str = BuildConfig.FLAVOR;
                    } else {
                        ArrayList<String> a = C0208a.m478a(context, new String[]{"/system/bin/sh", "-c", "getprop " + "ro.board.platform"});
                        if (a == null || a.size() <= 0) {
                            str = "fail";
                        } else {
                            str = a.get(0);
                        }
                    }
                    this.f672J = str;
                }
            }
        }
        return this.f672J;
    }

    /* renamed from: o */
    public final String mo449o() {
        if (this.f675M == null) {
            this.f675M = C0208a.m506h();
        }
        return this.f675M;
    }

    /* renamed from: p */
    public final synchronized Boolean mo450p() {
        if (this.f677O == null) {
            this.f677O = Boolean.valueOf(C0208a.m504f(this.f707v));
        }
        return this.f677O;
    }

    /* renamed from: q */
    public final synchronized Map<String, String> mo451q() {
        HashMap hashMap;
        if (this.f683V.size() <= 0) {
            hashMap = null;
        } else {
            hashMap = new HashMap(this.f683V);
        }
        return hashMap;
    }

    /* renamed from: r */
    public final synchronized boolean mo452r() {
        return C0255a.m729d(this.f707v);
    }
}
