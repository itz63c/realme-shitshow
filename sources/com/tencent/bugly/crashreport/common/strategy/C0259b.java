package com.tencent.bugly.crashreport.common.strategy;

import android.os.Parcel;
import com.tencent.bugly.C0207a;
import com.tencent.bugly.crashreport.biz.C0254d;
import com.tencent.bugly.p014a.C0211ac;
import com.tencent.bugly.p014a.C0213ae;
import com.tencent.bugly.p014a.C0221am;
import com.tencent.bugly.p014a.C0238o;
import java.util.List;

/* renamed from: com.tencent.bugly.crashreport.common.strategy.b */
/* compiled from: BUGLY */
public final class C0259b {

    /* renamed from: a */
    public static int f733a = 1000;

    /* renamed from: b */
    private static C0259b f734b = null;

    /* renamed from: c */
    private final List<C0207a> f735c;

    /* renamed from: d */
    private final StrategyBean f736d;

    /* renamed from: e */
    private StrategyBean f737e;

    /* renamed from: a */
    public static synchronized C0259b m751a() {
        C0259b bVar;
        synchronized (C0259b.class) {
            bVar = f734b;
        }
        return bVar;
    }

    /* renamed from: b */
    public final StrategyBean mo458b() {
        if (this.f737e != null) {
            return this.f737e;
        }
        return this.f736d;
    }

    /* renamed from: a */
    public final void mo457a(C0238o oVar) {
        boolean z;
        long j;
        boolean z2;
        if (oVar != null) {
            if (this.f737e == null || oVar.f573h != this.f737e.f725l) {
                StrategyBean strategyBean = new StrategyBean();
                strategyBean.f717d = oVar.f566a;
                strategyBean.f719f = oVar.f568c;
                strategyBean.f718e = oVar.f567b;
                String str = oVar.f569d;
                if (!(str == null || str.trim().length() <= 0)) {
                    strategyBean.f727n = oVar.f569d;
                }
                String str2 = oVar.f570e;
                if (str2 == null || str2.trim().length() <= 0) {
                    z = true;
                } else {
                    z = false;
                }
                if (!z) {
                    strategyBean.f728o = oVar.f570e;
                }
                if (oVar.f571f != null) {
                    String str3 = oVar.f571f.f562a;
                    if (str3 == null || str3.trim().length() <= 0) {
                        z2 = true;
                    } else {
                        z2 = false;
                    }
                    if (!z2) {
                        strategyBean.f730q = oVar.f571f.f562a;
                    }
                }
                if (oVar.f573h != 0) {
                    strategyBean.f725l = oVar.f573h;
                }
                if (oVar.f572g != null && oVar.f572g.size() > 0) {
                    strategyBean.f731r = oVar.f572g;
                    String str4 = oVar.f572g.get("B11");
                    if (str4 == null || !str4.equals("1")) {
                        strategyBean.f720g = false;
                    } else {
                        strategyBean.f720g = true;
                    }
                    String str5 = oVar.f572g.get("B16");
                    if (str5 != null && str5.length() > 0) {
                        try {
                            Long valueOf = Long.valueOf(Long.parseLong(str5));
                            if (valueOf.longValue() > 0) {
                                if (valueOf.longValue() > 30000) {
                                    j = valueOf.longValue();
                                } else {
                                    j = 30000;
                                }
                                strategyBean.f726m = Long.valueOf(j).longValue();
                            }
                        } catch (Exception e) {
                            if (!C0221am.m594a(e)) {
                                e.printStackTrace();
                            }
                        }
                    }
                    String str6 = oVar.f572g.get("B27");
                    if (str6 != null && str6.length() > 0) {
                        try {
                            int parseInt = Integer.parseInt(str6);
                            if (parseInt > 0) {
                                strategyBean.f732s = parseInt;
                            }
                        } catch (Exception e2) {
                            if (!C0221am.m594a(e2)) {
                                e2.printStackTrace();
                            }
                        }
                    }
                    String str7 = oVar.f572g.get("B25");
                    if (str7 == null || !str7.equals("1")) {
                        strategyBean.f721h = false;
                    } else {
                        strategyBean.f721h = true;
                    }
                }
                C0221am.m593a("cr:%b,qu:%b,uin:%b,an:%b,ss:%b,ssT:%b,ssOT:%d,cos:%b,lstT:%d", Boolean.valueOf(strategyBean.f717d), Boolean.valueOf(strategyBean.f719f), Boolean.valueOf(strategyBean.f718e), Boolean.valueOf(strategyBean.f720g), Boolean.valueOf(strategyBean.f723j), Boolean.valueOf(strategyBean.f724k), Long.valueOf(strategyBean.f726m), Boolean.valueOf(strategyBean.f721h), Long.valueOf(strategyBean.f725l));
                this.f737e = strategyBean;
                C0211ac.m521a();
                C0211ac.m523a(2);
                C0213ae aeVar = new C0213ae();
                aeVar.f411b = 2;
                aeVar.f410a = strategyBean.f715b;
                aeVar.f414e = strategyBean.f716c;
                Parcel obtain = Parcel.obtain();
                strategyBean.writeToParcel(obtain, 0);
                byte[] marshall = obtain.marshall();
                obtain.recycle();
                aeVar.f416g = marshall;
                C0211ac.m521a();
                C0211ac.m526a(aeVar);
                for (C0207a aVar : this.f735c) {
                    try {
                        C0221am.m597c("[strategy] Notify %s", aVar.getClass().getName());
                    } catch (Throwable th) {
                        if (!C0221am.m594a(th)) {
                            th.printStackTrace();
                        }
                    }
                }
                C0254d.m719a(strategyBean);
            }
        }
    }
}
