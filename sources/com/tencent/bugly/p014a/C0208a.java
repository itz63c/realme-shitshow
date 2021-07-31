package com.tencent.bugly.p014a;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.StatFs;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.util.Log;
import com.coloros.neton.BuildConfig;
import com.tencent.bugly.crashreport.biz.UserInfoBean;
import com.tencent.bugly.crashreport.common.info.C0255a;
import com.tencent.bugly.crashreport.common.info.C0257c;
import com.tencent.bugly.crashreport.common.info.PlugInBean;
import com.tencent.bugly.crashreport.common.strategy.C0259b;
import com.tencent.bugly.crashreport.common.strategy.StrategyBean;
import java.io.File;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Array;
import java.lang.reflect.Method;
import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import neton.client.Utils.AppSecurityUtils;
import neton.client.Utils.NetHelper;
import neton.client.dns.DnsMode;
import neton.internal.platform.Platform;

/* renamed from: com.tencent.bugly.a.a */
/* compiled from: BUGLY */
public class C0208a {

    /* renamed from: a */
    protected HashMap<String, HashMap<String, byte[]>> f401a = new HashMap<>();

    /* renamed from: b */
    protected String f402b;

    /* renamed from: c */
    C0247x f403c;

    /* renamed from: d */
    private HashMap<String, Object> f404d;

    C0208a() {
        new HashMap();
        this.f404d = new HashMap<>();
        this.f402b = "GBK";
        this.f403c = new C0247x();
    }

    /* renamed from: b */
    public static String m489b() {
        try {
            return Build.MODEL;
        } catch (Throwable th) {
            if (!C0221am.m594a(th)) {
                th.printStackTrace();
            }
            return "fail";
        }
    }

    /* renamed from: a */
    private static C0239p m471a(UserInfoBean userInfoBean) {
        if (userInfoBean == null) {
            return null;
        }
        C0239p pVar = new C0239p();
        pVar.f577a = userInfoBean.f630e;
        pVar.f581e = userInfoBean.f635j;
        pVar.f580d = userInfoBean.f628c;
        pVar.f579c = userInfoBean.f629d;
        pVar.f583g = C0257c.m730a().mo439e();
        pVar.f584h = userInfoBean.f640o == 1;
        switch (userInfoBean.f627b) {
            case 1:
                pVar.f578b = 1;
                break;
            case DnsMode.DNS_MODE_HTTP /*2*/:
                pVar.f578b = 4;
                break;
            case 3:
                pVar.f578b = 2;
                break;
            case Platform.INFO /*4*/:
                pVar.f578b = 3;
                break;
            default:
                if (userInfoBean.f627b >= 10 && userInfoBean.f627b < 20) {
                    pVar.f578b = (byte) userInfoBean.f627b;
                    break;
                } else {
                    C0221am.m599e("unknown uinfo type %d ", Integer.valueOf(userInfoBean.f627b));
                    return null;
                }
                break;
        }
        pVar.f582f = new HashMap();
        if (userInfoBean.f641p >= 0) {
            pVar.f582f.put("C01", new StringBuilder().append(userInfoBean.f641p).toString());
        }
        if (userInfoBean.f642q >= 0) {
            pVar.f582f.put("C02", new StringBuilder().append(userInfoBean.f642q).toString());
        }
        if (userInfoBean.f643r != null && userInfoBean.f643r.size() > 0) {
            for (Map.Entry next : userInfoBean.f643r.entrySet()) {
                pVar.f582f.put("C03_" + ((String) next.getKey()), next.getValue());
            }
        }
        if (userInfoBean.f644s != null && userInfoBean.f644s.size() > 0) {
            for (Map.Entry next2 : userInfoBean.f644s.entrySet()) {
                pVar.f582f.put("C04_" + ((String) next2.getKey()), next2.getValue());
            }
        }
        pVar.f582f.put("A36", new StringBuilder().append(!userInfoBean.f637l).toString());
        pVar.f582f.put("F02", new StringBuilder().append(userInfoBean.f632g).toString());
        pVar.f582f.put("F03", new StringBuilder().append(userInfoBean.f633h).toString());
        pVar.f582f.put("F04", userInfoBean.f635j);
        pVar.f582f.put("F05", new StringBuilder().append(userInfoBean.f634i).toString());
        pVar.f582f.put("F06", userInfoBean.f638m);
        pVar.f582f.put("F10", new StringBuilder().append(userInfoBean.f636k).toString());
        C0221am.m597c("summary type %d vm:%d", Byte.valueOf(pVar.f578b), Integer.valueOf(pVar.f582f.size()));
        return pVar;
    }

    /* renamed from: a */
    public void mo348a(String str) {
        this.f402b = str;
    }

    /* renamed from: c */
    public static String m495c() {
        try {
            return Build.VERSION.RELEASE;
        } catch (Throwable th) {
            if (!C0221am.m594a(th)) {
                th.printStackTrace();
            }
            return "fail";
        }
    }

    /* renamed from: d */
    public static int m498d() {
        try {
            return Build.VERSION.SDK_INT;
        } catch (Throwable th) {
            if (!C0221am.m594a(th)) {
                th.printStackTrace();
            }
            return -1;
        }
    }

    /* renamed from: a */
    public static String m476a(Throwable th) {
        if (th == null) {
            return BuildConfig.FLAVOR;
        }
        try {
            StringWriter stringWriter = new StringWriter();
            th.printStackTrace(new PrintWriter(stringWriter));
            return stringWriter.getBuffer().toString();
        } catch (Throwable th2) {
            if (!C0221am.m594a(th2)) {
                th2.printStackTrace();
            }
            return "fail";
        }
    }

    /* renamed from: a */
    public static String m475a(Context context) {
        if (!C0255a.m725a(context, "android.permission.READ_PHONE_STATE")) {
            C0221am.m598d("no READ_PHONE_STATE permission to get IMEI", new Object[0]);
            return "null";
        } else if (context == null) {
            return null;
        } else {
            try {
                String deviceId = ((TelephonyManager) context.getSystemService("phone")).getDeviceId();
                if (deviceId == null) {
                    return deviceId;
                }
                try {
                    return deviceId.toLowerCase();
                } catch (Throwable th) {
                }
            } catch (Throwable th2) {
            }
        }
        Log.i(C0221am.f462b, "failed to get IMEI");
        return null;
    }

    /* renamed from: a */
    private static String m477a(ArrayList<String> arrayList) {
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < arrayList.size(); i++) {
            String str = arrayList.get(i);
            if (str.equals("java.lang.Integer") || str.equals("int")) {
                str = "int32";
            } else if (str.equals("java.lang.Boolean") || str.equals("boolean")) {
                str = "bool";
            } else if (str.equals("java.lang.Byte") || str.equals("byte")) {
                str = "char";
            } else if (str.equals("java.lang.Double") || str.equals("double")) {
                str = "double";
            } else if (str.equals("java.lang.Float") || str.equals("float")) {
                str = "float";
            } else if (str.equals("java.lang.Long") || str.equals("long")) {
                str = "int64";
            } else if (str.equals("java.lang.Short") || str.equals("short")) {
                str = "short";
            } else if (str.equals("java.lang.Character")) {
                throw new IllegalArgumentException("can not support java.lang.Character");
            } else if (str.equals("java.lang.String")) {
                str = "string";
            } else if (str.equals("java.util.List")) {
                str = "list";
            } else if (str.equals("java.util.Map")) {
                str = "map";
            }
            arrayList.set(i, str);
        }
        Collections.reverse(arrayList);
        for (int i2 = 0; i2 < arrayList.size(); i2++) {
            String str2 = arrayList.get(i2);
            if (str2.equals("list")) {
                arrayList.set(i2 - 1, "<" + arrayList.get(i2 - 1));
                arrayList.set(0, arrayList.get(0) + ">");
            } else if (str2.equals("map")) {
                arrayList.set(i2 - 1, "<" + arrayList.get(i2 - 1) + ",");
                arrayList.set(0, arrayList.get(0) + ">");
            } else if (str2.equals("Array")) {
                arrayList.set(i2 - 1, "<" + arrayList.get(i2 - 1));
                arrayList.set(0, arrayList.get(0) + ">");
            }
        }
        Collections.reverse(arrayList);
        Iterator<String> it = arrayList.iterator();
        while (it.hasNext()) {
            stringBuffer.append(it.next());
        }
        return stringBuffer.toString();
    }

    /* renamed from: a */
    public <T> void mo349a(String str, T t) {
        if (str == null) {
            throw new IllegalArgumentException("put key can not is null");
        } else if (t == null) {
            throw new IllegalArgumentException("put value can not is null");
        } else if (t instanceof Set) {
            throw new IllegalArgumentException("can not support Set");
        } else {
            C0249z zVar = new C0249z();
            zVar.mo410a(this.f402b);
            zVar.mo416a((Object) t, 0);
            byte[] a = C0210ab.m520a(zVar.mo411a());
            HashMap hashMap = new HashMap(1);
            ArrayList arrayList = new ArrayList(1);
            m481a((ArrayList<String>) arrayList, (Object) t);
            hashMap.put(m477a((ArrayList<String>) arrayList), a);
            this.f404d.remove(str);
            this.f401a.put(str, hashMap);
        }
    }

    /* renamed from: b */
    public static String m490b(Context context) {
        if (!C0255a.m725a(context, "android.permission.READ_PHONE_STATE")) {
            C0221am.m598d("no READ_PHONE_STATE permission to get IMSI", new Object[0]);
            return "null";
        } else if (context == null) {
            return null;
        } else {
            try {
                String subscriberId = ((TelephonyManager) context.getSystemService("phone")).getSubscriberId();
                if (subscriberId == null) {
                    return subscriberId;
                }
                try {
                    return subscriberId.toLowerCase();
                } catch (Throwable th) {
                }
            } catch (Throwable th2) {
            }
        }
        Log.i(C0221am.f462b, "failed to get IMSI");
        return null;
    }

    /* renamed from: c */
    public static String m496c(Context context) {
        if (context == null) {
            return "fail";
        }
        try {
            String string = Settings.Secure.getString(context.getContentResolver(), "android_id");
            if (string == null) {
                return "null";
            }
            return string.toLowerCase();
        } catch (Throwable th) {
            if (C0221am.m594a(th)) {
                return "fail";
            }
            Log.i(C0221am.f462b, "failed to get Android ID");
            return "fail";
        }
    }

    /* renamed from: a */
    private static byte[] m487a(byte[] bArr, String str) {
        if (bArr == null) {
            return bArr;
        }
        try {
            C0228e eVar = new C0228e();
            eVar.mo389a(str);
            return eVar.mo390a(bArr);
        } catch (Throwable th) {
            if (!C0221am.m594a(th)) {
                th.printStackTrace();
            }
            C0221am.m598d("encrytype %d %s", 1, str);
            return null;
        }
    }

    /* renamed from: a */
    public static C0240q m472a(List<UserInfoBean> list, int i) {
        if (list == null || list.size() == 0) {
            return null;
        }
        C0257c a = C0257c.m730a();
        C0240q qVar = new C0240q();
        qVar.f588b = a.f689d;
        qVar.f589c = a.mo438d();
        ArrayList<C0239p> arrayList = new ArrayList<>();
        for (UserInfoBean a2 : list) {
            C0239p a3 = m471a(a2);
            if (a3 != null) {
                arrayList.add(a3);
            }
        }
        qVar.f590d = arrayList;
        qVar.f591e = new HashMap();
        qVar.f591e.put("A7", a.f690e);
        qVar.f591e.put("A6", a.mo448n());
        qVar.f591e.put("A5", a.mo447m());
        qVar.f591e.put("A2", new StringBuilder().append(a.mo445k()).toString());
        qVar.f591e.put("A1", new StringBuilder().append(a.mo445k()).toString());
        qVar.f591e.put("A24", a.f692g);
        qVar.f591e.put("A17", new StringBuilder().append(a.mo446l()).toString());
        qVar.f591e.put("A15", a.mo449o());
        qVar.f591e.put("A13", new StringBuilder().append(a.mo450p()).toString());
        qVar.f591e.put("F08", a.f705t);
        qVar.f591e.put("F09", a.f706u);
        Map<String, String> q = a.mo451q();
        if (q != null && q.size() > 0) {
            for (Map.Entry next : q.entrySet()) {
                qVar.f591e.put("C04_" + ((String) next.getKey()), next.getValue());
            }
        }
        switch (i) {
            case 1:
                qVar.f587a = 1;
                break;
            case DnsMode.DNS_MODE_HTTP /*2*/:
                qVar.f587a = 2;
                break;
            default:
                C0221am.m599e("unknown up type %d ", Integer.valueOf(i));
                return null;
        }
        return qVar;
    }

    /* renamed from: d */
    public static String m500d(Context context) {
        String str;
        if (context == null) {
            return "fail";
        }
        try {
            String macAddress = ((WifiManager) context.getSystemService(NetHelper.CARRIER_WIFI)).getConnectionInfo().getMacAddress();
            if (macAddress == null) {
                return "null";
            }
            try {
                return macAddress.toLowerCase();
            } catch (Throwable th) {
                th = th;
            }
        } catch (Throwable th2) {
            th = th2;
            str = "fail";
        }
        if (C0221am.m594a(th)) {
            return str;
        }
        th.printStackTrace();
        return str;
    }

    /* JADX WARNING: Removed duplicated region for block: B:37:0x006e A[Catch:{ all -> 0x00ba }] */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x0073 A[SYNTHETIC, Splitter:B:39:0x0073] */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x0078 A[SYNTHETIC, Splitter:B:42:0x0078] */
    /* JADX WARNING: Removed duplicated region for block: B:56:0x0098 A[SYNTHETIC, Splitter:B:56:0x0098] */
    /* JADX WARNING: Removed duplicated region for block: B:59:0x009d A[SYNTHETIC, Splitter:B:59:0x009d] */
    /* renamed from: l */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static java.lang.String mo392l() {
        /*
            r6 = 2
            r1 = 0
            java.lang.String r0 = "/system/build.prop"
            java.io.FileReader r3 = new java.io.FileReader     // Catch:{ Throwable -> 0x0065, all -> 0x0093 }
            r3.<init>(r0)     // Catch:{ Throwable -> 0x0065, all -> 0x0093 }
            java.io.BufferedReader r2 = new java.io.BufferedReader     // Catch:{ Throwable -> 0x00bc, all -> 0x00b7 }
            r0 = 2048(0x800, float:2.87E-42)
            r2.<init>(r3, r0)     // Catch:{ Throwable -> 0x00bc, all -> 0x00b7 }
        L_0x0010:
            java.lang.String r0 = r2.readLine()     // Catch:{ Throwable -> 0x00bf }
            if (r0 == 0) goto L_0x00c1
            java.lang.String r4 = "="
            r5 = 2
            java.lang.String[] r0 = r0.split(r4, r5)     // Catch:{ Throwable -> 0x00bf }
            int r4 = r0.length     // Catch:{ Throwable -> 0x00bf }
            if (r4 != r6) goto L_0x0010
            r4 = 0
            r4 = r0[r4]     // Catch:{ Throwable -> 0x00bf }
            java.lang.String r5 = "ro.product.cpu.abilist"
            boolean r4 = r4.equals(r5)     // Catch:{ Throwable -> 0x00bf }
            if (r4 == 0) goto L_0x0040
            r4 = 1
            r0 = r0[r4]     // Catch:{ Throwable -> 0x00bf }
        L_0x002e:
            if (r0 == 0) goto L_0x0039
            java.lang.String r4 = ","
            java.lang.String[] r0 = r0.split(r4)     // Catch:{ Throwable -> 0x00bf }
            r4 = 0
            r0 = r0[r4]     // Catch:{ Throwable -> 0x00bf }
        L_0x0039:
            r2.close()     // Catch:{ IOException -> 0x004f }
        L_0x003c:
            r3.close()     // Catch:{ IOException -> 0x005a }
        L_0x003f:
            return r0
        L_0x0040:
            r4 = 0
            r4 = r0[r4]     // Catch:{ Throwable -> 0x00bf }
            java.lang.String r5 = "ro.product.cpu.abi"
            boolean r4 = r4.equals(r5)     // Catch:{ Throwable -> 0x00bf }
            if (r4 == 0) goto L_0x0010
            r4 = 1
            r0 = r0[r4]     // Catch:{ Throwable -> 0x00bf }
            goto L_0x002e
        L_0x004f:
            r1 = move-exception
            boolean r2 = com.tencent.bugly.p014a.C0221am.m594a(r1)
            if (r2 != 0) goto L_0x003c
            r1.printStackTrace()
            goto L_0x003c
        L_0x005a:
            r1 = move-exception
            boolean r2 = com.tencent.bugly.p014a.C0221am.m594a(r1)
            if (r2 != 0) goto L_0x003f
            r1.printStackTrace()
            goto L_0x003f
        L_0x0065:
            r0 = move-exception
            r2 = r1
            r3 = r1
        L_0x0068:
            boolean r4 = com.tencent.bugly.p014a.C0221am.m594a(r0)     // Catch:{ all -> 0x00ba }
            if (r4 != 0) goto L_0x0071
            r0.printStackTrace()     // Catch:{ all -> 0x00ba }
        L_0x0071:
            if (r2 == 0) goto L_0x0076
            r2.close()     // Catch:{ IOException -> 0x007d }
        L_0x0076:
            if (r3 == 0) goto L_0x007b
            r3.close()     // Catch:{ IOException -> 0x0088 }
        L_0x007b:
            r0 = r1
            goto L_0x003f
        L_0x007d:
            r0 = move-exception
            boolean r2 = com.tencent.bugly.p014a.C0221am.m594a(r0)
            if (r2 != 0) goto L_0x0076
            r0.printStackTrace()
            goto L_0x0076
        L_0x0088:
            r0 = move-exception
            boolean r2 = com.tencent.bugly.p014a.C0221am.m594a(r0)
            if (r2 != 0) goto L_0x007b
            r0.printStackTrace()
            goto L_0x007b
        L_0x0093:
            r0 = move-exception
            r2 = r1
            r3 = r1
        L_0x0096:
            if (r2 == 0) goto L_0x009b
            r2.close()     // Catch:{ IOException -> 0x00a1 }
        L_0x009b:
            if (r3 == 0) goto L_0x00a0
            r3.close()     // Catch:{ IOException -> 0x00ac }
        L_0x00a0:
            throw r0
        L_0x00a1:
            r1 = move-exception
            boolean r2 = com.tencent.bugly.p014a.C0221am.m594a(r1)
            if (r2 != 0) goto L_0x009b
            r1.printStackTrace()
            goto L_0x009b
        L_0x00ac:
            r1 = move-exception
            boolean r2 = com.tencent.bugly.p014a.C0221am.m594a(r1)
            if (r2 != 0) goto L_0x00a0
            r1.printStackTrace()
            goto L_0x00a0
        L_0x00b7:
            r0 = move-exception
            r2 = r1
            goto L_0x0096
        L_0x00ba:
            r0 = move-exception
            goto L_0x0096
        L_0x00bc:
            r0 = move-exception
            r2 = r1
            goto L_0x0068
        L_0x00bf:
            r0 = move-exception
            goto L_0x0068
        L_0x00c1:
            r0 = r1
            goto L_0x002e
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.bugly.p014a.C0208a.mo392l():java.lang.String");
    }

    /* renamed from: a */
    public static <T extends C0209aa> T m468a(byte[] bArr, Class<T> cls) {
        if (bArr == null || bArr.length <= 0) {
            return null;
        }
        try {
            T t = (C0209aa) cls.newInstance();
            C0247x xVar = new C0247x(bArr);
            xVar.mo400a("utf-8");
            t.mo352a(xVar);
            return t;
        } catch (Throwable th) {
            if (!C0221am.m596b(th)) {
                th.printStackTrace();
            }
            return null;
        }
    }

    /* renamed from: b */
    public static byte[] m493b(byte[] bArr) {
        if (bArr == null) {
            return bArr;
        }
        C0221am.m597c("rqdp{  zp:} %s rqdp{  len:} %s", 2, Integer.valueOf(bArr.length));
        try {
            return C0225b.m615a().mo387a(bArr);
        } catch (Throwable th) {
            if (!C0221am.m594a(th)) {
                th.printStackTrace();
            }
            return null;
        }
    }

    /* renamed from: a */
    public static C0235l m469a(Context context, int i, byte[] bArr) {
        C0257c a = C0257c.m730a();
        StrategyBean b = C0259b.m751a().mo458b();
        if (a == null || b == null) {
            C0221am.m599e("illigle access to create req pkg!", new Object[0]);
            return null;
        }
        try {
            C0235l lVar = new C0235l();
            synchronized (a) {
                lVar.f528a = 1;
                lVar.f529b = a.mo437c();
                lVar.f530c = a.f688c;
                lVar.f531d = a.f694i;
                lVar.f532e = a.f695j;
                a.getClass();
                lVar.f533f = "2.1";
                lVar.f534g = i;
                if (bArr == null) {
                    bArr = BuildConfig.FLAVOR.getBytes();
                }
                lVar.f535h = bArr;
                lVar.f536i = a.f691f;
                lVar.f537j = a.f692g;
                lVar.f538k = new HashMap();
                lVar.f539l = a.mo435b();
                lVar.f540m = b.f725l;
                lVar.f542o = a.mo438d();
                lVar.f543p = m502e(context);
                lVar.f544q = System.currentTimeMillis();
                lVar.f545r = a.mo441g();
                lVar.f546s = a.mo440f();
                lVar.f547t = a.mo443i();
                lVar.f548u = a.mo442h();
                lVar.f549v = a.mo444j();
                lVar.f550w = lVar.f543p;
                a.getClass();
                lVar.f541n = "com.tencent.bugly";
            }
            return lVar;
        } catch (Throwable th) {
            if (!C0221am.m596b(th)) {
                th.printStackTrace();
            }
            return null;
        }
    }

    /* renamed from: e */
    public static String m501e() {
        try {
            String l = mo392l();
            if (l == null) {
                return System.getProperty("os.arch");
            }
            return l;
        } catch (Throwable th) {
            if (!C0221am.m594a(th)) {
                th.printStackTrace();
            }
            return "fail";
        }
    }

    /* renamed from: c */
    public static byte[] m497c(byte[] bArr) {
        if (bArr == null) {
            return bArr;
        }
        C0221am.m597c("rqdp{  unzp:} %s rqdp{  len:} %s", 2, Integer.valueOf(bArr.length));
        try {
            return C0225b.m615a().mo388b(bArr);
        } catch (Throwable th) {
            if (!C0221am.m594a(th)) {
                th.printStackTrace();
            }
            return null;
        }
    }

    /* renamed from: a */
    private static void m481a(ArrayList<String> arrayList, Object obj) {
        Object obj2 = obj;
        while (true) {
            if (obj2.getClass().isArray()) {
                if (!obj2.getClass().getComponentType().toString().equals("byte")) {
                    throw new IllegalArgumentException("only byte[] is supported");
                } else if (Array.getLength(obj2) > 0) {
                    arrayList.add("java.util.List");
                    obj2 = Array.get(obj2, 0);
                } else {
                    arrayList.add("Array");
                    arrayList.add("?");
                    return;
                }
            } else if (obj2 instanceof Array) {
                throw new IllegalArgumentException("can not support Array, please use List");
            } else if (obj2 instanceof List) {
                arrayList.add("java.util.List");
                List list = (List) obj2;
                if (list.size() > 0) {
                    obj2 = list.get(0);
                } else {
                    arrayList.add("?");
                    return;
                }
            } else if (obj2 instanceof Map) {
                arrayList.add("java.util.Map");
                Map map = (Map) obj2;
                if (map.size() > 0) {
                    Object next = map.keySet().iterator().next();
                    obj2 = map.get(next);
                    arrayList.add(next.getClass().getName());
                } else {
                    arrayList.add("?");
                    arrayList.add("?");
                    return;
                }
            } else {
                arrayList.add(obj2.getClass().getName());
                return;
            }
        }
    }

    /* renamed from: a */
    public static byte[] m486a(C0235l lVar) {
        try {
            C0243t tVar = new C0243t();
            tVar.mo392l();
            tVar.mo348a("utf-8");
            tVar.mo395m();
            tVar.mo393b("RqdServer");
            tVar.mo394c("sync");
            tVar.mo349a("detail", lVar);
            return tVar.mo351a();
        } catch (Throwable th) {
            if (!C0221am.m596b(th)) {
                th.printStackTrace();
            }
            return null;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:25:0x005a A[Catch:{ all -> 0x00a9 }] */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x005f A[SYNTHETIC, Splitter:B:27:0x005f] */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x0064 A[SYNTHETIC, Splitter:B:30:0x0064] */
    /* JADX WARNING: Removed duplicated region for block: B:44:0x0085 A[SYNTHETIC, Splitter:B:44:0x0085] */
    /* JADX WARNING: Removed duplicated region for block: B:47:0x008a A[SYNTHETIC, Splitter:B:47:0x008a] */
    /* renamed from: f */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static long m503f() {
        /*
            r3 = 0
            java.lang.String r0 = "/proc/meminfo"
            java.io.FileReader r4 = new java.io.FileReader     // Catch:{ Throwable -> 0x0051, all -> 0x0080 }
            r4.<init>(r0)     // Catch:{ Throwable -> 0x0051, all -> 0x0080 }
            java.io.BufferedReader r2 = new java.io.BufferedReader     // Catch:{ Throwable -> 0x00ac, all -> 0x00a4 }
            r0 = 2048(0x800, float:2.87E-42)
            r2.<init>(r4, r0)     // Catch:{ Throwable -> 0x00ac, all -> 0x00a4 }
            java.lang.String r0 = r2.readLine()     // Catch:{ Throwable -> 0x00af, all -> 0x00a7 }
            java.lang.String r1 = ":\\s+"
            r3 = 2
            java.lang.String[] r0 = r0.split(r1, r3)     // Catch:{ Throwable -> 0x00af, all -> 0x00a7 }
            r1 = 1
            r0 = r0[r1]     // Catch:{ Throwable -> 0x00af, all -> 0x00a7 }
            java.lang.String r0 = r0.toLowerCase()     // Catch:{ Throwable -> 0x00af, all -> 0x00a7 }
            java.lang.String r1 = "kb"
            java.lang.String r3 = ""
            java.lang.String r0 = r0.replace(r1, r3)     // Catch:{ Throwable -> 0x00af, all -> 0x00a7 }
            java.lang.String r0 = r0.trim()     // Catch:{ Throwable -> 0x00af, all -> 0x00a7 }
            long r0 = java.lang.Long.parseLong(r0)     // Catch:{ Throwable -> 0x00af, all -> 0x00a7 }
            r3 = 10
            long r0 = r0 << r3
            r2.close()     // Catch:{ IOException -> 0x003b }
        L_0x0037:
            r4.close()     // Catch:{ IOException -> 0x0046 }
        L_0x003a:
            return r0
        L_0x003b:
            r2 = move-exception
            boolean r3 = com.tencent.bugly.p014a.C0221am.m594a(r2)
            if (r3 != 0) goto L_0x0037
            r2.printStackTrace()
            goto L_0x0037
        L_0x0046:
            r2 = move-exception
            boolean r3 = com.tencent.bugly.p014a.C0221am.m594a(r2)
            if (r3 != 0) goto L_0x003a
            r2.printStackTrace()
            goto L_0x003a
        L_0x0051:
            r0 = move-exception
            r1 = r3
            r4 = r3
        L_0x0054:
            boolean r2 = com.tencent.bugly.p014a.C0221am.m594a(r0)     // Catch:{ all -> 0x00a9 }
            if (r2 != 0) goto L_0x005d
            r0.printStackTrace()     // Catch:{ all -> 0x00a9 }
        L_0x005d:
            if (r1 == 0) goto L_0x0062
            r1.close()     // Catch:{ IOException -> 0x006a }
        L_0x0062:
            if (r4 == 0) goto L_0x0067
            r4.close()     // Catch:{ IOException -> 0x0075 }
        L_0x0067:
            r0 = -2
            goto L_0x003a
        L_0x006a:
            r0 = move-exception
            boolean r1 = com.tencent.bugly.p014a.C0221am.m594a(r0)
            if (r1 != 0) goto L_0x0062
            r0.printStackTrace()
            goto L_0x0062
        L_0x0075:
            r0 = move-exception
            boolean r1 = com.tencent.bugly.p014a.C0221am.m594a(r0)
            if (r1 != 0) goto L_0x0067
            r0.printStackTrace()
            goto L_0x0067
        L_0x0080:
            r0 = move-exception
            r2 = r3
            r4 = r3
        L_0x0083:
            if (r2 == 0) goto L_0x0088
            r2.close()     // Catch:{ IOException -> 0x008e }
        L_0x0088:
            if (r4 == 0) goto L_0x008d
            r4.close()     // Catch:{ IOException -> 0x0099 }
        L_0x008d:
            throw r0
        L_0x008e:
            r1 = move-exception
            boolean r2 = com.tencent.bugly.p014a.C0221am.m594a(r1)
            if (r2 != 0) goto L_0x0088
            r1.printStackTrace()
            goto L_0x0088
        L_0x0099:
            r1 = move-exception
            boolean r2 = com.tencent.bugly.p014a.C0221am.m594a(r1)
            if (r2 != 0) goto L_0x008d
            r1.printStackTrace()
            goto L_0x008d
        L_0x00a4:
            r0 = move-exception
            r2 = r3
            goto L_0x0083
        L_0x00a7:
            r0 = move-exception
            goto L_0x0083
        L_0x00a9:
            r0 = move-exception
            r2 = r1
            goto L_0x0083
        L_0x00ac:
            r0 = move-exception
            r1 = r3
            goto L_0x0054
        L_0x00af:
            r0 = move-exception
            r1 = r2
            goto L_0x0054
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.bugly.p014a.C0208a.m503f():long");
    }

    /* renamed from: b */
    private static byte[] m494b(byte[] bArr, String str) {
        try {
            return m497c(m487a(bArr, str));
        } catch (Exception e) {
            if (!C0221am.m594a(e)) {
                e.printStackTrace();
            }
            return null;
        }
    }

    /* renamed from: j */
    public static long m508j() {
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
            return simpleDateFormat.parse(simpleDateFormat.format(new Date())).getTime();
        } catch (Throwable th) {
            if (!C0221am.m594a(th)) {
                th.printStackTrace();
            }
            return -1;
        }
    }

    /* renamed from: a */
    public static C0236m m470a(byte[] bArr, boolean z) {
        C0236m mVar;
        if (bArr != null) {
            try {
                C0243t tVar = new C0243t();
                tVar.mo392l();
                tVar.mo348a("utf-8");
                tVar.mo350a(bArr);
                Object b = tVar.mo391b("detail", new C0236m());
                if (C0236m.class.isInstance(b)) {
                    mVar = C0236m.class.cast(b);
                } else {
                    mVar = null;
                }
                if (z || mVar == null || mVar.f556c == null || mVar.f556c.length <= 0) {
                    return mVar;
                }
                C0221am.m597c("resp buf %d", Integer.valueOf(mVar.f556c.length));
                mVar.f556c = m494b(mVar.f556c, StrategyBean.f712a);
                if (mVar.f556c != null) {
                    return mVar;
                }
                C0221am.m599e("resp sbuffer error!", new Object[0]);
                return null;
            } catch (Throwable th) {
                if (!C0221am.m596b(th)) {
                    th.printStackTrace();
                }
            }
        }
        return null;
    }

    /* renamed from: a */
    public byte[] mo351a() {
        C0249z zVar = new C0249z(0);
        zVar.mo410a(this.f402b);
        zVar.mo419a(this.f401a, 0);
        return C0210ab.m520a(zVar.mo411a());
    }

    /* renamed from: a */
    public void mo350a(byte[] bArr) {
        this.f403c.mo406a(bArr);
        this.f403c.mo400a(this.f402b);
        HashMap hashMap = new HashMap(1);
        HashMap hashMap2 = new HashMap(1);
        hashMap2.put(BuildConfig.FLAVOR, new byte[0]);
        hashMap.put(BuildConfig.FLAVOR, hashMap2);
        this.f401a = this.f403c.mo404a(hashMap);
    }

    /* renamed from: a */
    public static byte[] m485a(C0209aa aaVar) {
        try {
            C0249z zVar = new C0249z();
            zVar.mo410a("utf-8");
            aaVar.mo353a(zVar);
            return zVar.mo423b();
        } catch (Throwable th) {
            if (!C0221am.m596b(th)) {
                th.printStackTrace();
            }
            return null;
        }
    }

    /* renamed from: g */
    public static long m505g() {
        if (!(Environment.getExternalStorageState().equals("mounted"))) {
            return 0;
        }
        try {
            StatFs statFs = new StatFs(Environment.getExternalStorageDirectory().getPath());
            return ((long) statFs.getBlockSize()) * ((long) statFs.getBlockCount());
        } catch (Throwable th) {
            if (!C0221am.m594a(th)) {
                th.printStackTrace();
            }
            return -2;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:35:0x008d A[Catch:{ all -> 0x00fa }] */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x0092 A[SYNTHETIC, Splitter:B:37:0x0092] */
    /* JADX WARNING: Removed duplicated region for block: B:40:0x0097 A[SYNTHETIC, Splitter:B:40:0x0097] */
    /* JADX WARNING: Removed duplicated region for block: B:65:0x00dd A[SYNTHETIC, Splitter:B:65:0x00dd] */
    /* JADX WARNING: Removed duplicated region for block: B:68:0x00e2 A[SYNTHETIC, Splitter:B:68:0x00e2] */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static boolean m482a(java.io.File r6, java.io.File r7) {
        /*
            r3 = 0
            r0 = 0
            java.lang.String r1 = "rqdp{  ZF start}"
            java.lang.Object[] r2 = new java.lang.Object[r0]
            com.tencent.bugly.p014a.C0221am.m597c(r1, r2)
            boolean r1 = r6.equals(r7)
            if (r1 == 0) goto L_0x0017
            java.lang.String r1 = "rqdp{  err ZF 1R!}"
            java.lang.Object[] r2 = new java.lang.Object[r0]
            com.tencent.bugly.p014a.C0221am.m598d(r1, r2)
        L_0x0016:
            return r0
        L_0x0017:
            boolean r1 = r6.exists()
            if (r1 == 0) goto L_0x0023
            boolean r1 = r6.canRead()
            if (r1 != 0) goto L_0x002b
        L_0x0023:
            java.lang.String r1 = "rqdp{  !sFile.exists() || !sFile.canRead(),pls check ,return!}"
            java.lang.Object[] r2 = new java.lang.Object[r0]
            com.tencent.bugly.p014a.C0221am.m598d(r1, r2)
            goto L_0x0016
        L_0x002b:
            java.io.File r1 = r7.getParentFile()     // Catch:{ Throwable -> 0x00a3 }
            if (r1 == 0) goto L_0x0042
            java.io.File r1 = r7.getParentFile()     // Catch:{ Throwable -> 0x00a3 }
            boolean r1 = r1.exists()     // Catch:{ Throwable -> 0x00a3 }
            if (r1 != 0) goto L_0x0042
            java.io.File r1 = r7.getParentFile()     // Catch:{ Throwable -> 0x00a3 }
            r1.mkdirs()     // Catch:{ Throwable -> 0x00a3 }
        L_0x0042:
            boolean r1 = r7.exists()     // Catch:{ Throwable -> 0x00a3 }
            if (r1 != 0) goto L_0x004b
            r7.createNewFile()     // Catch:{ Throwable -> 0x00a3 }
        L_0x004b:
            boolean r1 = r7.exists()
            if (r1 == 0) goto L_0x0016
            boolean r1 = r7.canRead()
            if (r1 == 0) goto L_0x0016
            java.io.FileInputStream r4 = new java.io.FileInputStream     // Catch:{ Throwable -> 0x00fc, all -> 0x00d8 }
            r4.<init>(r6)     // Catch:{ Throwable -> 0x00fc, all -> 0x00d8 }
            java.util.zip.ZipOutputStream r2 = new java.util.zip.ZipOutputStream     // Catch:{ Throwable -> 0x0100, all -> 0x00f7 }
            java.io.FileOutputStream r1 = new java.io.FileOutputStream     // Catch:{ Throwable -> 0x0100, all -> 0x00f7 }
            r1.<init>(r7)     // Catch:{ Throwable -> 0x0100, all -> 0x00f7 }
            r2.<init>(r1)     // Catch:{ Throwable -> 0x0100, all -> 0x00f7 }
            r1 = 8
            r2.setMethod(r1)     // Catch:{ Throwable -> 0x0086 }
            java.util.zip.ZipEntry r1 = new java.util.zip.ZipEntry     // Catch:{ Throwable -> 0x0086 }
            java.lang.String r3 = r6.getName()     // Catch:{ Throwable -> 0x0086 }
            r1.<init>(r3)     // Catch:{ Throwable -> 0x0086 }
            r2.putNextEntry(r1)     // Catch:{ Throwable -> 0x0086 }
            r1 = 5000(0x1388, float:7.006E-42)
            byte[] r1 = new byte[r1]     // Catch:{ Throwable -> 0x0086 }
        L_0x007b:
            int r3 = r4.read(r1)     // Catch:{ Throwable -> 0x0086 }
            if (r3 <= 0) goto L_0x00ae
            r5 = 0
            r2.write(r1, r5, r3)     // Catch:{ Throwable -> 0x0086 }
            goto L_0x007b
        L_0x0086:
            r1 = move-exception
        L_0x0087:
            boolean r3 = com.tencent.bugly.p014a.C0221am.m594a(r1)     // Catch:{ all -> 0x00fa }
            if (r3 != 0) goto L_0x0090
            r1.printStackTrace()     // Catch:{ all -> 0x00fa }
        L_0x0090:
            if (r4 == 0) goto L_0x0095
            r4.close()     // Catch:{ IOException -> 0x00ce }
        L_0x0095:
            if (r2 == 0) goto L_0x009a
            r2.close()     // Catch:{ IOException -> 0x00d3 }
        L_0x009a:
            java.lang.String r1 = "rqdp{  ZF end}"
            java.lang.Object[] r2 = new java.lang.Object[r0]
            com.tencent.bugly.p014a.C0221am.m597c(r1, r2)
            goto L_0x0016
        L_0x00a3:
            r1 = move-exception
            boolean r2 = com.tencent.bugly.p014a.C0221am.m594a(r1)
            if (r2 != 0) goto L_0x004b
            r1.printStackTrace()
            goto L_0x004b
        L_0x00ae:
            r2.flush()     // Catch:{ Throwable -> 0x0086 }
            r2.closeEntry()     // Catch:{ Throwable -> 0x0086 }
            r4.close()     // Catch:{ IOException -> 0x00c4 }
        L_0x00b7:
            r2.close()     // Catch:{ IOException -> 0x00c9 }
        L_0x00ba:
            java.lang.String r1 = "rqdp{  ZF end}"
            java.lang.Object[] r0 = new java.lang.Object[r0]
            com.tencent.bugly.p014a.C0221am.m597c(r1, r0)
            r0 = 1
            goto L_0x0016
        L_0x00c4:
            r1 = move-exception
            r1.printStackTrace()
            goto L_0x00b7
        L_0x00c9:
            r1 = move-exception
            r1.printStackTrace()
            goto L_0x00ba
        L_0x00ce:
            r1 = move-exception
            r1.printStackTrace()
            goto L_0x0095
        L_0x00d3:
            r1 = move-exception
            r1.printStackTrace()
            goto L_0x009a
        L_0x00d8:
            r1 = move-exception
            r2 = r3
            r4 = r3
        L_0x00db:
            if (r4 == 0) goto L_0x00e0
            r4.close()     // Catch:{ IOException -> 0x00ed }
        L_0x00e0:
            if (r2 == 0) goto L_0x00e5
            r2.close()     // Catch:{ IOException -> 0x00f2 }
        L_0x00e5:
            java.lang.String r2 = "rqdp{  ZF end}"
            java.lang.Object[] r0 = new java.lang.Object[r0]
            com.tencent.bugly.p014a.C0221am.m597c(r2, r0)
            throw r1
        L_0x00ed:
            r3 = move-exception
            r3.printStackTrace()
            goto L_0x00e0
        L_0x00f2:
            r2 = move-exception
            r2.printStackTrace()
            goto L_0x00e5
        L_0x00f7:
            r1 = move-exception
            r2 = r3
            goto L_0x00db
        L_0x00fa:
            r1 = move-exception
            goto L_0x00db
        L_0x00fc:
            r1 = move-exception
            r2 = r3
            r4 = r3
            goto L_0x0087
        L_0x0100:
            r1 = move-exception
            r2 = r3
            goto L_0x0087
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.bugly.p014a.C0208a.m482a(java.io.File, java.io.File):boolean");
    }

    /* renamed from: h */
    public static String m506h() {
        try {
            return Locale.getDefault().getCountry();
        } catch (Throwable th) {
            if (C0221am.m594a(th)) {
                return "fail";
            }
            th.printStackTrace();
            return "fail";
        }
    }

    /* renamed from: i */
    public static String m507i() {
        try {
            return Build.BRAND;
        } catch (Throwable th) {
            if (C0221am.m594a(th)) {
                return "fail";
            }
            th.printStackTrace();
            return "fail";
        }
    }

    /* renamed from: e */
    public static String m502e(Context context) {
        String str;
        TelephonyManager telephonyManager;
        try {
            NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
            if (activeNetworkInfo == null) {
                return null;
            }
            if (activeNetworkInfo.getType() == 1) {
                return "WIFI";
            }
            if (activeNetworkInfo.getType() == 0 && (telephonyManager = (TelephonyManager) context.getSystemService("phone")) != null) {
                int networkType = telephonyManager.getNetworkType();
                switch (networkType) {
                    case 1:
                        return "GPRS";
                    case DnsMode.DNS_MODE_HTTP /*2*/:
                        return "EDGE";
                    case 3:
                        return "UMTS";
                    case Platform.INFO /*4*/:
                        return "CDMA";
                    case Platform.WARN /*5*/:
                        return "EVDO_0";
                    case 6:
                        return "EVDO_A";
                    case 7:
                        return "1xRTT";
                    case 8:
                        return "HSDPA";
                    case 9:
                        return "HSUPA";
                    case 10:
                        return "HSPA";
                    case 11:
                        return "iDen";
                    case 12:
                        return "EVDO_B";
                    case 13:
                        return "LTE";
                    case 14:
                        return "eHRPD";
                    case 15:
                        return "HSPA+";
                    default:
                        str = "MOBILE(" + networkType + ")";
                        break;
                }
            } else {
                str = "unknown";
            }
            return str;
        } catch (Exception e) {
            if (C0221am.m594a(e)) {
                return "unknown";
            }
            e.printStackTrace();
            return "unknown";
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:14:0x0048 A[Catch:{ all -> 0x00a7 }] */
    /* JADX WARNING: Removed duplicated region for block: B:16:0x004d A[SYNTHETIC, Splitter:B:16:0x004d] */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x0052 A[SYNTHETIC, Splitter:B:19:0x0052] */
    /* JADX WARNING: Removed duplicated region for block: B:44:0x0091 A[SYNTHETIC, Splitter:B:44:0x0091] */
    /* JADX WARNING: Removed duplicated region for block: B:47:0x0096 A[SYNTHETIC, Splitter:B:47:0x0096] */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.util.ArrayList<java.lang.String> m478a(android.content.Context r6, java.lang.String[] r7) {
        /*
            r1 = 0
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            com.tencent.bugly.crashreport.common.info.c r2 = com.tencent.bugly.crashreport.common.info.C0257c.m731a((android.content.Context) r6)
            boolean r2 = r2.mo452r()
            if (r2 == 0) goto L_0x0020
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            java.lang.String r1 = new java.lang.String
            java.lang.String r2 = "unknown(low memory)"
            r1.<init>(r2)
            r0.add(r1)
        L_0x001f:
            return r0
        L_0x0020:
            java.lang.Runtime r2 = java.lang.Runtime.getRuntime()     // Catch:{ Throwable -> 0x00a9, all -> 0x008c }
            java.lang.Process r4 = r2.exec(r7)     // Catch:{ Throwable -> 0x00a9, all -> 0x008c }
            java.io.BufferedReader r3 = new java.io.BufferedReader     // Catch:{ Throwable -> 0x00a9, all -> 0x008c }
            java.io.InputStreamReader r2 = new java.io.InputStreamReader     // Catch:{ Throwable -> 0x00a9, all -> 0x008c }
            java.io.InputStream r5 = r4.getInputStream()     // Catch:{ Throwable -> 0x00a9, all -> 0x008c }
            r2.<init>(r5)     // Catch:{ Throwable -> 0x00a9, all -> 0x008c }
            r3.<init>(r2)     // Catch:{ Throwable -> 0x00a9, all -> 0x008c }
        L_0x0036:
            java.lang.String r2 = r3.readLine()     // Catch:{ Throwable -> 0x0040, all -> 0x00a4 }
            if (r2 == 0) goto L_0x0057
            r0.add(r2)     // Catch:{ Throwable -> 0x0040, all -> 0x00a4 }
            goto L_0x0036
        L_0x0040:
            r0 = move-exception
            r2 = r1
        L_0x0042:
            boolean r4 = com.tencent.bugly.p014a.C0221am.m594a(r0)     // Catch:{ all -> 0x00a7 }
            if (r4 != 0) goto L_0x004b
            r0.printStackTrace()     // Catch:{ all -> 0x00a7 }
        L_0x004b:
            if (r3 == 0) goto L_0x0050
            r3.close()     // Catch:{ IOException -> 0x0082 }
        L_0x0050:
            if (r2 == 0) goto L_0x0055
            r2.close()     // Catch:{ IOException -> 0x0087 }
        L_0x0055:
            r0 = r1
            goto L_0x001f
        L_0x0057:
            java.io.BufferedReader r2 = new java.io.BufferedReader     // Catch:{ Throwable -> 0x0040, all -> 0x00a4 }
            java.io.InputStreamReader r5 = new java.io.InputStreamReader     // Catch:{ Throwable -> 0x0040, all -> 0x00a4 }
            java.io.InputStream r4 = r4.getErrorStream()     // Catch:{ Throwable -> 0x0040, all -> 0x00a4 }
            r5.<init>(r4)     // Catch:{ Throwable -> 0x0040, all -> 0x00a4 }
            r2.<init>(r5)     // Catch:{ Throwable -> 0x0040, all -> 0x00a4 }
        L_0x0065:
            java.lang.String r4 = r2.readLine()     // Catch:{ Throwable -> 0x006f }
            if (r4 == 0) goto L_0x0071
            r0.add(r4)     // Catch:{ Throwable -> 0x006f }
            goto L_0x0065
        L_0x006f:
            r0 = move-exception
            goto L_0x0042
        L_0x0071:
            r3.close()     // Catch:{ IOException -> 0x007d }
        L_0x0074:
            r2.close()     // Catch:{ IOException -> 0x0078 }
            goto L_0x001f
        L_0x0078:
            r1 = move-exception
            r1.printStackTrace()
            goto L_0x001f
        L_0x007d:
            r1 = move-exception
            r1.printStackTrace()
            goto L_0x0074
        L_0x0082:
            r0 = move-exception
            r0.printStackTrace()
            goto L_0x0050
        L_0x0087:
            r0 = move-exception
            r0.printStackTrace()
            goto L_0x0055
        L_0x008c:
            r0 = move-exception
            r2 = r1
            r3 = r1
        L_0x008f:
            if (r3 == 0) goto L_0x0094
            r3.close()     // Catch:{ IOException -> 0x009a }
        L_0x0094:
            if (r2 == 0) goto L_0x0099
            r2.close()     // Catch:{ IOException -> 0x009f }
        L_0x0099:
            throw r0
        L_0x009a:
            r1 = move-exception
            r1.printStackTrace()
            goto L_0x0094
        L_0x009f:
            r1 = move-exception
            r1.printStackTrace()
            goto L_0x0099
        L_0x00a4:
            r0 = move-exception
            r2 = r1
            goto L_0x008f
        L_0x00a7:
            r0 = move-exception
            goto L_0x008f
        L_0x00a9:
            r0 = move-exception
            r2 = r1
            r3 = r1
            goto L_0x0042
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.bugly.p014a.C0208a.m478a(android.content.Context, java.lang.String[]):java.util.ArrayList");
    }

    /* renamed from: f */
    public static boolean m504f(Context context) {
        boolean z;
        boolean z2;
        boolean z3 = Build.TAGS != null && Build.TAGS.contains("test-keys");
        try {
            z = new File("/system/app/Superuser.apk").exists();
        } catch (Throwable th) {
            z = false;
        }
        Boolean bool = null;
        ArrayList<String> a = m478a(context, new String[]{"/system/bin/sh", "-c", "type su"});
        if (a != null && a.size() > 0) {
            Iterator<String> it = a.iterator();
            while (it.hasNext()) {
                String next = it.next();
                C0221am.m597c(next, new Object[0]);
                if (next.contains("not found")) {
                    z2 = false;
                } else {
                    z2 = bool;
                }
                bool = z2;
            }
            if (bool == null) {
                bool = true;
            }
        }
        Boolean valueOf = Boolean.valueOf(bool == null ? false : bool.booleanValue());
        if (z3 || z || valueOf.booleanValue()) {
            return true;
        }
        return false;
    }

    /* renamed from: a */
    public static byte[] m484a(long j) {
        try {
            return String.valueOf(j).getBytes("utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
    }

    /* renamed from: d */
    public static long m499d(byte[] bArr) {
        if (bArr == null) {
            return -1;
        }
        try {
            return Long.parseLong(new String(bArr, "utf-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return -1;
        }
    }

    /* renamed from: a */
    public static Object m473a(String str, String str2) {
        try {
            Method declaredMethod = Class.forName(str).getDeclaredMethod(str2, (Class[]) null);
            declaredMethod.setAccessible(true);
            return declaredMethod.invoke((Object) null, (Object[]) null);
        } catch (Exception e) {
            return null;
        }
    }

    /* renamed from: a */
    public static void m480a(Parcel parcel, Map<String, PlugInBean> map) {
        if (map == null || map == null || map.size() <= 0) {
            parcel.writeBundle((Bundle) null);
            return;
        }
        int size = map.size();
        ArrayList arrayList = new ArrayList(size);
        ArrayList arrayList2 = new ArrayList(size);
        for (Map.Entry next : map.entrySet()) {
            arrayList.add(next.getKey());
            arrayList2.add(next.getValue());
        }
        Bundle bundle = new Bundle();
        bundle.putInt("pluginNum", arrayList.size());
        for (int i = 0; i < arrayList.size(); i++) {
            bundle.putString("pluginKey" + i, (String) arrayList.get(i));
        }
        for (int i2 = 0; i2 < arrayList.size(); i2++) {
            bundle.putString("pluginVal" + i2 + "plugInId", ((PlugInBean) arrayList2.get(i2)).f653a);
            bundle.putString("pluginVal" + i2 + "plugInUUID", ((PlugInBean) arrayList2.get(i2)).f655c);
            bundle.putString("pluginVal" + i2 + "plugInVersion", ((PlugInBean) arrayList2.get(i2)).f654b);
        }
        parcel.writeBundle(bundle);
    }

    /* renamed from: a */
    public static Map<String, PlugInBean> m479a(Parcel parcel) {
        HashMap hashMap;
        Bundle readBundle = parcel.readBundle();
        if (readBundle == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        int intValue = ((Integer) readBundle.get("pluginNum")).intValue();
        for (int i = 0; i < intValue; i++) {
            arrayList.add(readBundle.getString("pluginKey" + i));
        }
        for (int i2 = 0; i2 < intValue; i2++) {
            arrayList2.add(new PlugInBean(readBundle.getString("pluginVal" + i2 + "plugInId"), readBundle.getString("pluginVal" + i2 + "plugInVersion"), readBundle.getString("pluginVal" + i2 + "plugInUUID")));
        }
        if (arrayList.size() == arrayList2.size()) {
            HashMap hashMap2 = new HashMap(arrayList.size());
            for (int i3 = 0; i3 < arrayList.size(); i3++) {
                hashMap2.put(arrayList.get(i3), PlugInBean.class.cast(arrayList2.get(i3)));
            }
            hashMap = hashMap2;
        } else {
            C0221am.m599e("map plugin parcel error!", new Object[0]);
            hashMap = null;
        }
        return hashMap;
    }

    /* renamed from: b */
    public static void m492b(Parcel parcel, Map<String, String> map) {
        if (map == null || map == null || map.size() <= 0) {
            parcel.writeBundle((Bundle) null);
            return;
        }
        int size = map.size();
        ArrayList arrayList = new ArrayList(size);
        ArrayList arrayList2 = new ArrayList(size);
        for (Map.Entry next : map.entrySet()) {
            arrayList.add(next.getKey());
            arrayList2.add(next.getValue());
        }
        Bundle bundle = new Bundle();
        bundle.putStringArrayList("keys", arrayList);
        bundle.putStringArrayList("values", arrayList2);
        parcel.writeBundle(bundle);
    }

    /* renamed from: b */
    public static Map<String, String> m491b(Parcel parcel) {
        HashMap hashMap;
        Bundle readBundle = parcel.readBundle();
        if (readBundle == null) {
            return null;
        }
        ArrayList<String> stringArrayList = readBundle.getStringArrayList("keys");
        ArrayList<String> stringArrayList2 = readBundle.getStringArrayList("values");
        if (stringArrayList == null || stringArrayList2 == null || stringArrayList.size() != stringArrayList2.size()) {
            C0221am.m599e("map parcel error!", new Object[0]);
            hashMap = null;
        } else {
            HashMap hashMap2 = new HashMap(stringArrayList.size());
            for (int i = 0; i < stringArrayList.size(); i++) {
                hashMap2.put(stringArrayList.get(i), stringArrayList2.get(i));
            }
            hashMap = hashMap2;
        }
        return hashMap;
    }

    /* renamed from: a */
    public static <T> T m474a(byte[] bArr, Parcelable.Creator<T> creator) {
        Parcel obtain = Parcel.obtain();
        obtain.unmarshall(bArr, 0, bArr.length);
        obtain.setDataPosition(0);
        try {
            T createFromParcel = creator.createFromParcel(obtain);
            if (obtain == null) {
                return createFromParcel;
            }
            obtain.recycle();
            return createFromParcel;
        } catch (Throwable th) {
            if (obtain != null) {
                obtain.recycle();
            }
            throw th;
        }
    }

    /* renamed from: k */
    public static byte[] m509k() {
        try {
            KeyGenerator instance = KeyGenerator.getInstance("AES");
            instance.init(128);
            return instance.generateKey().getEncoded();
        } catch (Exception e) {
            if (!C0221am.m596b(e)) {
                e.printStackTrace();
            }
            return null;
        }
    }

    /* renamed from: a */
    public static byte[] m483a(int i, byte[] bArr, byte[] bArr2) {
        try {
            SecretKeySpec secretKeySpec = new SecretKeySpec(bArr2, "AES");
            Cipher instance = Cipher.getInstance("AES/GCM/NoPadding");
            instance.init(i, secretKeySpec, new IvParameterSpec(bArr2));
            return instance.doFinal(bArr);
        } catch (Exception e) {
            if (!C0221am.m596b(e)) {
                e.printStackTrace();
            }
            return null;
        }
    }

    /* renamed from: a */
    public static byte[] m488a(byte[] bArr, byte[] bArr2) {
        try {
            PublicKey generatePublic = KeyFactory.getInstance(AppSecurityUtils.RSA.KEY_ALGORITHM).generatePublic(new X509EncodedKeySpec(bArr2));
            Cipher instance = Cipher.getInstance("RSA/ECB/PKCS1Padding");
            instance.init(1, generatePublic);
            return instance.doFinal(bArr);
        } catch (Exception e) {
            if (!C0221am.m596b(e)) {
                e.printStackTrace();
            }
            return null;
        }
    }
}
