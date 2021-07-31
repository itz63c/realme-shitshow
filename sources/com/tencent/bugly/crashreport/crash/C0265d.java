package com.tencent.bugly.crashreport.crash;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.os.Parcel;
import com.tencent.bugly.crashreport.common.info.C0257c;
import com.tencent.bugly.crashreport.common.info.PlugInBean;
import com.tencent.bugly.crashreport.common.strategy.C0259b;
import com.tencent.bugly.crashreport.common.strategy.StrategyBean;
import com.tencent.bugly.p014a.C0208a;
import com.tencent.bugly.p014a.C0209aa;
import com.tencent.bugly.p014a.C0211ac;
import com.tencent.bugly.p014a.C0215ag;
import com.tencent.bugly.p014a.C0216ah;
import com.tencent.bugly.p014a.C0221am;
import com.tencent.bugly.p014a.C0230g;
import com.tencent.bugly.p014a.C0232i;
import com.tencent.bugly.p014a.C0233j;
import com.tencent.bugly.p014a.C0234k;
import com.tencent.bugly.p014a.C0235l;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import neton.client.dns.DnsMode;
import neton.internal.platform.Platform;

/* renamed from: com.tencent.bugly.crashreport.crash.d */
/* compiled from: BUGLY */
public final class C0265d {

    /* renamed from: a */
    private static int f801a = 0;

    /* renamed from: b */
    private Context f802b;

    /* renamed from: c */
    private C0259b f803c;

    /* renamed from: a */
    public static List<CrashDetailBean> m773a() {
        StrategyBean b = C0259b.m751a().mo458b();
        if (b == null) {
            C0221am.m598d("have not synced remote!", new Object[0]);
            return null;
        } else if (!b.f717d) {
            C0221am.m598d("Crashreport remote closed, please check your APPID correct and Version available, then uninstall and reinstall your app.", new Object[0]);
            C0221am.m595b("[init] WARNING! Crashreport closed by server, please check your APPID correct and Version available, then uninstall and reinstall your app.", new Object[0]);
            return null;
        } else {
            long currentTimeMillis = System.currentTimeMillis();
            long j = C0208a.m508j();
            List<C0264c> b2 = m776b();
            if (b2 == null || b2.size() <= 0) {
                return null;
            }
            ArrayList arrayList = new ArrayList();
            Iterator<C0264c> it = b2.iterator();
            while (it.hasNext()) {
                C0264c next = it.next();
                if (next.f796b < j - C0267f.f811f) {
                    it.remove();
                    arrayList.add(next);
                } else if (next.f798d) {
                    if (next.f796b >= currentTimeMillis - 86400000) {
                        it.remove();
                    } else if (!next.f799e) {
                        it.remove();
                        arrayList.add(next);
                    }
                } else if (((long) next.f800f) >= 3 && next.f796b < currentTimeMillis - 86400000) {
                    it.remove();
                    arrayList.add(next);
                }
            }
            if (arrayList.size() > 0) {
                m778c(arrayList);
            }
            ArrayList arrayList2 = new ArrayList();
            List<CrashDetailBean> b3 = m777b(b2);
            if (b3 != null && b3.size() > 0) {
                String str = C0257c.m730a().f694i;
                Iterator<CrashDetailBean> it2 = b3.iterator();
                while (it2.hasNext()) {
                    CrashDetailBean next2 = it2.next();
                    if (!str.equals(next2.f770f)) {
                        it2.remove();
                        arrayList2.add(next2);
                    }
                }
            }
            if (arrayList2.size() > 0) {
                m779d(arrayList2);
            }
            return b3;
        }
    }

    /* renamed from: a */
    public final void mo474a(List<CrashDetailBean> list) {
        C0234k kVar;
        if (!this.f803c.mo458b().f717d) {
            C0221am.m598d("remote report is disable!", new Object[0]);
            C0221am.m595b("[crash] server closed bugly in this app. please check your appid if is correct, and re-install it", new Object[0]);
        } else if (list != null && list.size() != 0) {
            try {
                Context context = this.f802b;
                C0257c a = C0257c.m730a();
                if (context == null || list == null || list.size() == 0 || a == null) {
                    C0221am.m598d("enEXPPkg args == null!", new Object[0]);
                    kVar = null;
                } else {
                    C0234k kVar2 = new C0234k();
                    kVar2.f525a = new ArrayList<>();
                    for (CrashDetailBean a2 : list) {
                        kVar2.f525a.add(m771a(context, a2, a));
                    }
                    kVar = kVar2;
                }
                if (kVar == null) {
                    C0221am.m598d("create eupPkg fail!", new Object[0]);
                    return;
                }
                byte[] a3 = C0208a.m485a((C0209aa) kVar);
                if (a3 == null) {
                    C0221am.m598d("send encode fail!", new Object[0]);
                    return;
                }
                C0235l a4 = C0208a.m469a(this.f802b, 630, a3);
                if (a4 == null) {
                    C0221am.m598d("request package is null.", new Object[0]);
                    return;
                }
                C0216ah.m553a().mo369a(f801a, a4, (C0215ag) new C0266e(this, list));
            } catch (Throwable th) {
                C0221am.m599e("req cr error %s", th.toString());
                if (!C0221am.m596b(th)) {
                    th.printStackTrace();
                }
            }
        }
    }

    /* renamed from: a */
    public static void m774a(boolean z, List<CrashDetailBean> list) {
        ContentValues a;
        if (list != null && list.size() > 0) {
            C0221am.m597c("up finish update state %b", Boolean.valueOf(z));
            for (CrashDetailBean next : list) {
                C0221am.m597c("pre uid:%s uc:%d re:%b me:%b", next.f767c, Integer.valueOf(next.f776l), Boolean.valueOf(next.f768d), Boolean.valueOf(next.f774j));
                next.f776l++;
                next.f768d = z;
                C0221am.m597c("set uid:%s uc:%d re:%b me:%b", next.f767c, Integer.valueOf(next.f776l), Boolean.valueOf(next.f768d), Boolean.valueOf(next.f774j));
            }
            for (CrashDetailBean next2 : list) {
                if (!(next2 == null || (a = m769a(next2)) == null)) {
                    long a2 = C0211ac.m521a().mo357a("t_cr", a);
                    if (a2 >= 0) {
                        C0221am.m597c("insert %s success!", "t_cr");
                        next2.f765a = a2;
                    }
                }
            }
            C0221am.m597c("update state size %d", Integer.valueOf(list.size()));
        }
        if (!z) {
            C0221am.m595b("[crash] upload fail.", new Object[0]);
        }
    }

    /* renamed from: a */
    private static ContentValues m769a(CrashDetailBean crashDetailBean) {
        int i;
        int i2 = 1;
        if (crashDetailBean == null) {
            return null;
        }
        try {
            ContentValues contentValues = new ContentValues();
            if (crashDetailBean.f765a > 0) {
                contentValues.put("_id", Long.valueOf(crashDetailBean.f765a));
            }
            contentValues.put("_tm", Long.valueOf(crashDetailBean.f782r));
            contentValues.put("_s1", crashDetailBean.f785u);
            if (crashDetailBean.f768d) {
                i = 1;
            } else {
                i = 0;
            }
            contentValues.put("_up", Integer.valueOf(i));
            if (!crashDetailBean.f774j) {
                i2 = 0;
            }
            contentValues.put("_me", Integer.valueOf(i2));
            contentValues.put("_uc", Integer.valueOf(crashDetailBean.f776l));
            Parcel obtain = Parcel.obtain();
            crashDetailBean.writeToParcel(obtain, 0);
            byte[] marshall = obtain.marshall();
            obtain.recycle();
            contentValues.put("_dt", marshall);
            return contentValues;
        } catch (Throwable th) {
            if (C0221am.m594a(th)) {
                return null;
            }
            th.printStackTrace();
            return null;
        }
    }

    /* renamed from: a */
    private static CrashDetailBean m772a(Cursor cursor) {
        if (cursor == null) {
            return null;
        }
        try {
            byte[] blob = cursor.getBlob(cursor.getColumnIndex("_dt"));
            if (blob == null) {
                return null;
            }
            long j = cursor.getLong(cursor.getColumnIndex("_id"));
            CrashDetailBean crashDetailBean = (CrashDetailBean) C0208a.m474a(blob, CrashDetailBean.CREATOR);
            if (crashDetailBean == null) {
                return crashDetailBean;
            }
            crashDetailBean.f765a = j;
            return crashDetailBean;
        } catch (Throwable th) {
            if (!C0221am.m594a(th)) {
                th.printStackTrace();
            }
            return null;
        }
    }

    /* renamed from: b */
    private static List<CrashDetailBean> m777b(List<C0264c> list) {
        Cursor cursor;
        if (list == null || list.size() == 0) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        for (C0264c cVar : list) {
            sb.append(" or _id = ").append(cVar.f795a);
        }
        String sb2 = sb.toString();
        if (sb2.length() > 0) {
            sb2 = sb2.substring(4);
        }
        sb.setLength(0);
        try {
            cursor = C0211ac.m521a().mo358a("t_cr", (String[]) null, sb2);
            if (cursor == null) {
                if (cursor != null && !cursor.isClosed()) {
                    cursor.close();
                }
                return null;
            }
            try {
                ArrayList arrayList = new ArrayList();
                while (cursor.moveToNext()) {
                    CrashDetailBean a = m772a(cursor);
                    if (a != null) {
                        arrayList.add(a);
                    } else {
                        sb.append(" or _id = ").append(cursor.getLong(cursor.getColumnIndex("_id")));
                    }
                }
                String sb3 = sb.toString();
                if (sb3.length() > 0) {
                    C0221am.m598d("deleted %s illegle data %d", "t_cr", Integer.valueOf(C0211ac.m521a().mo356a("t_cr", sb3.substring(4))));
                }
                if (cursor == null || cursor.isClosed()) {
                    return arrayList;
                }
                cursor.close();
                return arrayList;
            } catch (Throwable th) {
                th = th;
            }
        } catch (Throwable th2) {
            th = th2;
            cursor = null;
            if (cursor != null && !cursor.isClosed()) {
                cursor.close();
            }
            throw th;
        }
        try {
            if (!C0221am.m594a(th)) {
                th.printStackTrace();
            }
            if (cursor != null && !cursor.isClosed()) {
                cursor.close();
            }
            return null;
        } catch (Throwable th3) {
            th = th3;
            cursor.close();
            throw th;
        }
    }

    /* renamed from: b */
    private static C0264c m775b(Cursor cursor) {
        boolean z = true;
        if (cursor == null) {
            return null;
        }
        try {
            C0264c cVar = new C0264c();
            cVar.f795a = cursor.getLong(cursor.getColumnIndex("_id"));
            cVar.f796b = cursor.getLong(cursor.getColumnIndex("_tm"));
            cVar.f797c = cursor.getString(cursor.getColumnIndex("_s1"));
            cVar.f798d = cursor.getInt(cursor.getColumnIndex("_up")) == 1;
            if (cursor.getInt(cursor.getColumnIndex("_me")) != 1) {
                z = false;
            }
            cVar.f799e = z;
            cVar.f800f = cursor.getInt(cursor.getColumnIndex("_uc"));
            return cVar;
        } catch (Throwable th) {
            if (C0221am.m594a(th)) {
                return null;
            }
            th.printStackTrace();
            return null;
        }
    }

    /* renamed from: b */
    private static List<C0264c> m776b() {
        Cursor cursor;
        ArrayList arrayList = new ArrayList();
        try {
            cursor = C0211ac.m521a().mo358a("t_cr", new String[]{"_id", "_tm", "_s1", "_up", "_me", "_uc"}, (String) null);
            if (cursor != null) {
                try {
                    StringBuilder sb = new StringBuilder();
                    while (cursor.moveToNext()) {
                        C0264c b = m775b(cursor);
                        if (b != null) {
                            arrayList.add(b);
                        } else {
                            sb.append(" or _id = ").append(cursor.getLong(cursor.getColumnIndex("_id")));
                        }
                    }
                    String sb2 = sb.toString();
                    if (sb2.length() > 0) {
                        C0221am.m598d("deleted %s illegle data %d", "t_cr", Integer.valueOf(C0211ac.m521a().mo356a("t_cr", sb2.substring(4))));
                    }
                    if (cursor != null && !cursor.isClosed()) {
                        cursor.close();
                    }
                    return arrayList;
                } catch (Throwable th) {
                    th = th;
                }
            } else if (cursor == null || cursor.isClosed()) {
                return null;
            } else {
                cursor.close();
                return null;
            }
        } catch (Throwable th2) {
            th = th2;
            cursor = null;
            if (cursor != null && !cursor.isClosed()) {
                cursor.close();
            }
            throw th;
        }
        try {
            if (!C0221am.m594a(th)) {
                th.printStackTrace();
            }
            if (cursor != null && !cursor.isClosed()) {
                cursor.close();
            }
            return arrayList;
        } catch (Throwable th3) {
            th = th3;
            cursor.close();
            throw th;
        }
    }

    /* renamed from: c */
    private static void m778c(List<C0264c> list) {
        if (list.size() != 0) {
            StringBuilder sb = new StringBuilder();
            for (C0264c cVar : list) {
                sb.append(" or _id = ").append(cVar.f795a);
            }
            String sb2 = sb.toString();
            if (sb2.length() > 0) {
                sb2 = sb2.substring(4);
            }
            sb.setLength(0);
            try {
                C0221am.m597c("deleted %s data %d", "t_cr", Integer.valueOf(C0211ac.m521a().mo356a("t_cr", sb2)));
            } catch (Throwable th) {
                if (!C0221am.m594a(th)) {
                    th.printStackTrace();
                }
            }
        }
    }

    /* renamed from: d */
    private static void m779d(List<CrashDetailBean> list) {
        try {
            if (list.size() != 0) {
                StringBuilder sb = new StringBuilder();
                for (CrashDetailBean crashDetailBean : list) {
                    sb.append(" or _id = ").append(crashDetailBean.f765a);
                }
                String sb2 = sb.toString();
                if (sb2.length() > 0) {
                    sb2 = sb2.substring(4);
                }
                sb.setLength(0);
                C0221am.m597c("deleted %s data %d", "t_cr", Integer.valueOf(C0211ac.m521a().mo356a("t_cr", sb2)));
            }
        } catch (Throwable th) {
            if (!C0221am.m594a(th)) {
                th.printStackTrace();
            }
        }
    }

    /* renamed from: a */
    private static C0233j m771a(Context context, CrashDetailBean crashDetailBean, C0257c cVar) {
        C0232i a;
        C0232i a2;
        int i;
        boolean z = true;
        if (context == null || crashDetailBean == null || cVar == null) {
            C0221am.m598d("enExp args == null", new Object[0]);
            return null;
        }
        C0233j jVar = new C0233j();
        switch (crashDetailBean.f766b) {
            case 0:
                jVar.f503a = crashDetailBean.f774j ? "200" : "100";
                break;
            case 1:
                jVar.f503a = crashDetailBean.f774j ? "201" : "101";
                break;
            case DnsMode.DNS_MODE_HTTP /*2*/:
                jVar.f503a = crashDetailBean.f774j ? "202" : "102";
                break;
            case 3:
                jVar.f503a = crashDetailBean.f774j ? "203" : "103";
                break;
            case Platform.INFO /*4*/:
                jVar.f503a = crashDetailBean.f774j ? "204" : "104";
                break;
            case Platform.WARN /*5*/:
                jVar.f503a = crashDetailBean.f774j ? "207" : "107";
                break;
            case 6:
                jVar.f503a = crashDetailBean.f774j ? "206" : "106";
                break;
            default:
                C0221am.m599e("crash type error! %d", Integer.valueOf(crashDetailBean.f766b));
                break;
        }
        jVar.f504b = crashDetailBean.f782r;
        jVar.f505c = crashDetailBean.f778n;
        jVar.f506d = crashDetailBean.f779o;
        jVar.f507e = crashDetailBean.f780p;
        jVar.f509g = crashDetailBean.f781q;
        jVar.f510h = crashDetailBean.f789y;
        jVar.f511i = crashDetailBean.f767c;
        jVar.f512j = null;
        jVar.f514l = crashDetailBean.f777m;
        jVar.f515m = crashDetailBean.f769e;
        jVar.f508f = crashDetailBean.f745A;
        jVar.f522t = C0257c.m730a().mo439e();
        jVar.f516n = null;
        if (crashDetailBean.f773i != null && crashDetailBean.f773i.size() > 0) {
            jVar.f517o = new ArrayList<>();
            for (Map.Entry next : crashDetailBean.f773i.entrySet()) {
                C0230g gVar = new C0230g();
                gVar.f483a = ((PlugInBean) next.getValue()).f653a;
                gVar.f485c = ((PlugInBean) next.getValue()).f655c;
                gVar.f486d = ((PlugInBean) next.getValue()).f654b;
                gVar.f484b = cVar.mo447m();
                jVar.f517o.add(gVar);
            }
        }
        if (crashDetailBean.f772h != null && crashDetailBean.f772h.size() > 0) {
            jVar.f518p = new ArrayList<>();
            for (Map.Entry next2 : crashDetailBean.f772h.entrySet()) {
                C0230g gVar2 = new C0230g();
                gVar2.f483a = ((PlugInBean) next2.getValue()).f653a;
                gVar2.f485c = ((PlugInBean) next2.getValue()).f655c;
                gVar2.f486d = ((PlugInBean) next2.getValue()).f654b;
                jVar.f518p.add(gVar2);
            }
        }
        if (crashDetailBean.f774j) {
            jVar.f513k = crashDetailBean.f784t;
            if (crashDetailBean.f783s != null && crashDetailBean.f783s.length() > 0) {
                if (jVar.f519q == null) {
                    jVar.f519q = new ArrayList<>();
                }
                try {
                    jVar.f519q.add(new C0232i((byte) 1, "alltimes.txt", crashDetailBean.f783s.getBytes("utf-8")));
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                    jVar.f519q = null;
                }
            }
            Object[] objArr = new Object[2];
            objArr[0] = Integer.valueOf(jVar.f513k);
            if (jVar.f519q != null) {
                i = jVar.f519q.size();
            } else {
                i = 0;
            }
            objArr[1] = Integer.valueOf(i);
            C0221am.m597c("crashcount:%d sz:%d", objArr);
        }
        if (crashDetailBean.f787w != null) {
            if (jVar.f519q == null) {
                jVar.f519q = new ArrayList<>();
            }
            try {
                jVar.f519q.add(new C0232i((byte) 1, "log.txt", crashDetailBean.f787w.getBytes("utf-8")));
            } catch (UnsupportedEncodingException e2) {
                e2.printStackTrace();
                jVar.f519q = null;
            }
        }
        if (crashDetailBean.f788x != null && crashDetailBean.f788x.length > 0) {
            C0232i iVar = new C0232i((byte) 2, "buglylog.zip", crashDetailBean.f788x);
            C0221am.m597c("attach user log", new Object[0]);
            if (jVar.f519q == null) {
                jVar.f519q = new ArrayList<>();
            }
            jVar.f519q.add(iVar);
        }
        if (crashDetailBean.f766b == 3) {
            if (jVar.f519q == null) {
                jVar.f519q = new ArrayList<>();
            }
            if (crashDetailBean.f758N != null && crashDetailBean.f758N.containsKey("BUGLY_CR_01")) {
                try {
                    jVar.f519q.add(new C0232i((byte) 1, "anrMessage.txt", crashDetailBean.f758N.get("BUGLY_CR_01").getBytes("utf-8")));
                    C0221am.m597c("attach anr message", new Object[0]);
                } catch (UnsupportedEncodingException e3) {
                    e3.printStackTrace();
                    jVar.f519q = null;
                }
                crashDetailBean.f758N.remove("BUGLY_CR_01");
            }
            if (!(crashDetailBean.f786v == null || (a2 = m770a("trace.zip", context, crashDetailBean.f786v)) == null)) {
                C0221am.m597c("attach traces", new Object[0]);
                jVar.f519q.add(a2);
            }
        }
        if (crashDetailBean.f766b == 1) {
            if (jVar.f519q == null) {
                jVar.f519q = new ArrayList<>();
            }
            if (!(crashDetailBean.f786v == null || (a = m770a("tomb.zip", context, crashDetailBean.f786v)) == null)) {
                C0221am.m597c("attach tombs", new Object[0]);
                jVar.f519q.add(a);
            }
        }
        if (crashDetailBean.f763S != null && crashDetailBean.f763S.length > 0) {
            if (jVar.f519q == null) {
                jVar.f519q = new ArrayList<>();
            }
            jVar.f519q.add(new C0232i((byte) 1, "userExtraByteData", crashDetailBean.f763S));
            C0221am.m597c("attach extraData", new Object[0]);
        }
        jVar.f520r = new HashMap();
        jVar.f520r.put("A9", new StringBuilder().append(crashDetailBean.f746B).toString());
        jVar.f520r.put("A11", new StringBuilder().append(crashDetailBean.f747C).toString());
        jVar.f520r.put("A10", new StringBuilder().append(crashDetailBean.f748D).toString());
        jVar.f520r.put("A23", crashDetailBean.f770f);
        jVar.f520r.put("A7", cVar.f690e);
        jVar.f520r.put("A6", cVar.mo448n());
        jVar.f520r.put("A5", cVar.mo447m());
        jVar.f520r.put("A22", cVar.mo438d());
        jVar.f520r.put("A2", new StringBuilder().append(crashDetailBean.f750F).toString());
        jVar.f520r.put("A1", new StringBuilder().append(crashDetailBean.f749E).toString());
        jVar.f520r.put("A24", cVar.f692g);
        jVar.f520r.put("A17", new StringBuilder().append(crashDetailBean.f751G).toString());
        jVar.f520r.put("A3", cVar.mo441g());
        jVar.f520r.put("A16", cVar.mo443i());
        jVar.f520r.put("A25", cVar.mo444j());
        jVar.f520r.put("A14", cVar.mo442h());
        jVar.f520r.put("A15", cVar.mo449o());
        jVar.f520r.put("A13", new StringBuilder().append(cVar.mo450p()).toString());
        jVar.f520r.put("A34", crashDetailBean.f790z);
        try {
            jVar.f520r.put("A26", URLEncoder.encode(crashDetailBean.f752H, "utf-8"));
        } catch (UnsupportedEncodingException e4) {
            e4.printStackTrace();
        }
        if (crashDetailBean.f766b == 1) {
            jVar.f520r.put("A27", crashDetailBean.f754J);
            jVar.f520r.put("A28", crashDetailBean.f753I);
            jVar.f520r.put("A29", new StringBuilder().append(crashDetailBean.f775k).toString());
        }
        jVar.f520r.put("A30", crashDetailBean.f755K);
        jVar.f520r.put("A18", new StringBuilder().append(crashDetailBean.f756L).toString());
        jVar.f520r.put("A36", new StringBuilder().append(!crashDetailBean.f757M).toString());
        jVar.f520r.put("F02", new StringBuilder().append(cVar.f700o).toString());
        jVar.f520r.put("F03", new StringBuilder().append(cVar.f701p).toString());
        jVar.f520r.put("F04", cVar.mo435b());
        jVar.f520r.put("F05", new StringBuilder().append(cVar.f702q).toString());
        jVar.f520r.put("F06", cVar.f699n);
        jVar.f520r.put("F08", cVar.f705t);
        jVar.f520r.put("F09", cVar.f706u);
        jVar.f520r.put("F10", new StringBuilder().append(cVar.f703r).toString());
        if (crashDetailBean.f759O >= 0) {
            jVar.f520r.put("C01", new StringBuilder().append(crashDetailBean.f759O).toString());
        }
        if (crashDetailBean.f760P >= 0) {
            jVar.f520r.put("C02", new StringBuilder().append(crashDetailBean.f760P).toString());
        }
        if (crashDetailBean.f761Q != null && crashDetailBean.f761Q.size() > 0) {
            for (Map.Entry next3 : crashDetailBean.f761Q.entrySet()) {
                jVar.f520r.put("C03_" + ((String) next3.getKey()), next3.getValue());
            }
        }
        if (crashDetailBean.f762R != null && crashDetailBean.f762R.size() > 0) {
            for (Map.Entry next4 : crashDetailBean.f762R.entrySet()) {
                jVar.f520r.put("C04_" + ((String) next4.getKey()), next4.getValue());
            }
        }
        jVar.f521s = null;
        if (crashDetailBean.f758N != null && crashDetailBean.f758N.size() > 0) {
            jVar.f521s = crashDetailBean.f758N;
            C0221am.m593a("setted message size %d", Integer.valueOf(jVar.f521s.size()));
        }
        Object[] objArr2 = new Object[12];
        objArr2[0] = crashDetailBean.f778n;
        objArr2[1] = crashDetailBean.f767c;
        objArr2[2] = cVar.mo435b();
        objArr2[3] = Long.valueOf((crashDetailBean.f782r - crashDetailBean.f756L) / 1000);
        objArr2[4] = Boolean.valueOf(crashDetailBean.f775k);
        objArr2[5] = Boolean.valueOf(crashDetailBean.f757M);
        objArr2[6] = Boolean.valueOf(crashDetailBean.f774j);
        if (crashDetailBean.f766b != 1) {
            z = false;
        }
        objArr2[7] = Boolean.valueOf(z);
        objArr2[8] = Integer.valueOf(crashDetailBean.f784t);
        objArr2[9] = crashDetailBean.f783s;
        objArr2[10] = Boolean.valueOf(crashDetailBean.f768d);
        objArr2[11] = Integer.valueOf(jVar.f520r.size());
        C0221am.m597c("%s rid:%s sess:%s ls:%ds isR:%b isF:%b isM:%b isN:%b mc:%d ,%s ,isUp:%b ,vm:%d", objArr2);
        return jVar;
    }

    /* JADX WARNING: Removed duplicated region for block: B:20:0x0057 A[Catch:{ all -> 0x00dd }] */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x005c A[SYNTHETIC, Splitter:B:22:0x005c] */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x0065  */
    /* JADX WARNING: Removed duplicated region for block: B:46:0x00be A[SYNTHETIC, Splitter:B:46:0x00be] */
    /* JADX WARNING: Removed duplicated region for block: B:50:0x00c7  */
    /* JADX WARNING: Removed duplicated region for block: B:62:? A[RETURN, SYNTHETIC] */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static com.tencent.bugly.p014a.C0232i m770a(java.lang.String r9, android.content.Context r10, java.lang.String r11) {
        /*
            r2 = 1
            r0 = 0
            r8 = 0
            if (r11 == 0) goto L_0x0007
            if (r10 != 0) goto L_0x000f
        L_0x0007:
            java.lang.String r1 = "rqdp{  createZipAttachment sourcePath == null || context == null ,pls check}"
            java.lang.Object[] r2 = new java.lang.Object[r8]
            com.tencent.bugly.p014a.C0221am.m598d(r1, r2)
        L_0x000e:
            return r0
        L_0x000f:
            java.lang.String r1 = "zip %s"
            java.lang.Object[] r2 = new java.lang.Object[r2]
            r2[r8] = r11
            com.tencent.bugly.p014a.C0221am.m597c(r1, r2)
            java.io.File r1 = new java.io.File
            r1.<init>(r11)
            java.io.File r3 = new java.io.File
            java.io.File r2 = r10.getCacheDir()
            r3.<init>(r2, r9)
            boolean r1 = com.tencent.bugly.p014a.C0208a.m482a((java.io.File) r1, (java.io.File) r3)
            if (r1 != 0) goto L_0x0034
            java.lang.String r1 = "zip fail!"
            java.lang.Object[] r2 = new java.lang.Object[r8]
            com.tencent.bugly.p014a.C0221am.m598d(r1, r2)
            goto L_0x000e
        L_0x0034:
            java.io.ByteArrayOutputStream r1 = new java.io.ByteArrayOutputStream
            r1.<init>()
            java.io.FileInputStream r2 = new java.io.FileInputStream     // Catch:{ Throwable -> 0x00e0, all -> 0x00ba }
            r2.<init>(r3)     // Catch:{ Throwable -> 0x00e0, all -> 0x00ba }
            r4 = 1000(0x3e8, float:1.401E-42)
            byte[] r4 = new byte[r4]     // Catch:{ Throwable -> 0x0050 }
        L_0x0042:
            int r5 = r2.read(r4)     // Catch:{ Throwable -> 0x0050 }
            if (r5 <= 0) goto L_0x0070
            r6 = 0
            r1.write(r4, r6, r5)     // Catch:{ Throwable -> 0x0050 }
            r1.flush()     // Catch:{ Throwable -> 0x0050 }
            goto L_0x0042
        L_0x0050:
            r1 = move-exception
        L_0x0051:
            boolean r4 = com.tencent.bugly.p014a.C0221am.m594a(r1)     // Catch:{ all -> 0x00dd }
            if (r4 != 0) goto L_0x005a
            r1.printStackTrace()     // Catch:{ all -> 0x00dd }
        L_0x005a:
            if (r2 == 0) goto L_0x005f
            r2.close()     // Catch:{ IOException -> 0x00af }
        L_0x005f:
            boolean r1 = r3.exists()
            if (r1 == 0) goto L_0x000e
            java.lang.String r1 = "del tmp"
            java.lang.Object[] r2 = new java.lang.Object[r8]
            com.tencent.bugly.p014a.C0221am.m597c(r1, r2)
            r3.delete()
            goto L_0x000e
        L_0x0070:
            byte[] r4 = r1.toByteArray()     // Catch:{ Throwable -> 0x0050 }
            java.lang.String r1 = "read bytes :%d"
            r5 = 1
            java.lang.Object[] r5 = new java.lang.Object[r5]     // Catch:{ Throwable -> 0x0050 }
            r6 = 0
            int r7 = r4.length     // Catch:{ Throwable -> 0x0050 }
            java.lang.Integer r7 = java.lang.Integer.valueOf(r7)     // Catch:{ Throwable -> 0x0050 }
            r5[r6] = r7     // Catch:{ Throwable -> 0x0050 }
            com.tencent.bugly.p014a.C0221am.m597c(r1, r5)     // Catch:{ Throwable -> 0x0050 }
            com.tencent.bugly.a.i r1 = new com.tencent.bugly.a.i     // Catch:{ Throwable -> 0x0050 }
            r5 = 2
            java.lang.String r6 = r3.getName()     // Catch:{ Throwable -> 0x0050 }
            r1.<init>(r5, r6, r4)     // Catch:{ Throwable -> 0x0050 }
            r2.close()     // Catch:{ IOException -> 0x00a4 }
        L_0x0091:
            boolean r0 = r3.exists()
            if (r0 == 0) goto L_0x00a1
            java.lang.String r0 = "del tmp"
            java.lang.Object[] r2 = new java.lang.Object[r8]
            com.tencent.bugly.p014a.C0221am.m597c(r0, r2)
            r3.delete()
        L_0x00a1:
            r0 = r1
            goto L_0x000e
        L_0x00a4:
            r0 = move-exception
            boolean r2 = com.tencent.bugly.p014a.C0221am.m594a(r0)
            if (r2 != 0) goto L_0x0091
            r0.printStackTrace()
            goto L_0x0091
        L_0x00af:
            r1 = move-exception
            boolean r2 = com.tencent.bugly.p014a.C0221am.m594a(r1)
            if (r2 != 0) goto L_0x005f
            r1.printStackTrace()
            goto L_0x005f
        L_0x00ba:
            r1 = move-exception
            r2 = r0
        L_0x00bc:
            if (r2 == 0) goto L_0x00c1
            r2.close()     // Catch:{ IOException -> 0x00d2 }
        L_0x00c1:
            boolean r0 = r3.exists()
            if (r0 == 0) goto L_0x00d1
            java.lang.String r0 = "del tmp"
            java.lang.Object[] r2 = new java.lang.Object[r8]
            com.tencent.bugly.p014a.C0221am.m597c(r0, r2)
            r3.delete()
        L_0x00d1:
            throw r1
        L_0x00d2:
            r0 = move-exception
            boolean r2 = com.tencent.bugly.p014a.C0221am.m594a(r0)
            if (r2 != 0) goto L_0x00c1
            r0.printStackTrace()
            goto L_0x00c1
        L_0x00dd:
            r0 = move-exception
            r1 = r0
            goto L_0x00bc
        L_0x00e0:
            r1 = move-exception
            r2 = r0
            goto L_0x0051
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.bugly.crashreport.crash.C0265d.m770a(java.lang.String, android.content.Context, java.lang.String):com.tencent.bugly.a.i");
    }
}
