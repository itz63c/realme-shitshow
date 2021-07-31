package com.coloros.deeptesting.p007a;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.os.Messenger;
import android.os.SystemProperties;
import android.service.persistentdata.PersistentDataBlockManager;
import android.telephony.ColorOSTelephonyManager;
import android.widget.TextView;
import com.coloros.deeptesting.R;
import com.coloros.deeptesting.service.RequestService;
import com.coloros.neton.BuildConfig;
import com.coloros.neton.NetonClient;
import com.p000a.p001a.C0125j;
import java.io.StringWriter;
import neton.MediaType;
import neton.Request;
import neton.RequestBody;
import neton.Response;

/* renamed from: com.coloros.deeptesting.a.h */
/* compiled from: Utils */
public final class C0151h {
    /* renamed from: a */
    public static String m352a(Object obj) {
        C0125j jVar = new C0125j();
        Class<?> cls = obj.getClass();
        StringWriter stringWriter = new StringWriter();
        jVar.mo144a(obj, cls, stringWriter);
        return stringWriter.toString();
    }

    /* renamed from: a */
    public static boolean m356a(Context context) {
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getApplicationContext().getSystemService("connectivity")).getActiveNetworkInfo();
        if (activeNetworkInfo == null || !activeNetworkInfo.isAvailable()) {
            return false;
        }
        return true;
    }

    /* renamed from: a */
    public static Response m353a(String str, String str2) {
        if (str == null || str2 == null) {
            return null;
        }
        try {
            return NetonClient.execute(new Request.Builder().url(str).post(RequestBody.create(MediaType.parse(MediaType.JSON), str2)).build());
        } catch (Exception e) {
            C0150g.m348a("Utils", "neton post error");
            e.printStackTrace();
            return null;
        }
    }

    /* renamed from: a */
    public static String m351a() {
        String str = SystemProperties.get("ro.product.name", BuildConfig.FLAVOR);
        if (str == BuildConfig.FLAVOR) {
            return SystemProperties.get("ro.product.model", BuildConfig.FLAVOR);
        }
        return str;
    }

    /* renamed from: b */
    public static int m358b(Context context) {
        try {
            return ((PersistentDataBlockManager) context.getSystemService("persistent_data_block")).getFlashLockState();
        } catch (SecurityException e) {
            return 1;
        }
    }

    /* renamed from: c */
    public static boolean m361c(Context context) {
        return context.getPackageManager().hasSystemFeature("oppo.version.exp");
    }

    /* renamed from: d */
    public static String m362d(Context context) {
        try {
            return ColorOSTelephonyManager.getDefault(context).colorGetImei(0);
        } catch (Exception e) {
            C0150g.m350c("Utils", "getdeviceid error" + e);
            return "00";
        }
    }

    /* renamed from: a */
    public static void m355a(Context context, String str, String str2, Handler handler) {
        TextView textView = new TextView(context);
        textView.setText(str2);
        textView.setGravity(17);
        textView.setTextSize(16.0f);
        textView.setLineSpacing(6.0f, 1.1f);
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        String string = context.getString(R.string.dialog_ok);
        builder.setTitle(str);
        builder.setView(textView);
        builder.setPositiveButton(string, new C0152i(handler, context));
        if (handler != null) {
            builder.setNegativeButton(context.getString(R.string.dialog_no), new C0153j());
        }
        AlertDialog create = builder.create();
        create.getWindow().setType(2);
        create.show();
    }

    /* renamed from: a */
    public static void m354a(Context context, int i, Handler handler) {
        Intent intent = new Intent(context, RequestService.class);
        intent.putExtra("MessengerFlag", i);
        intent.putExtra("Messenger", new Messenger(handler));
        context.startService(intent);
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v7, resolved type: java.lang.Object[]} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static boolean m357a(java.lang.String r7) {
        /*
            r0 = 0
            r1 = 0
            if (r7 != 0) goto L_0x0037
        L_0x0004:
            java.lang.String r2 = "android.engineer.OppoEngineerManager"
            java.lang.Class r2 = java.lang.Class.forName(r2)     // Catch:{ Exception -> 0x0077 }
            java.lang.String r3 = "fastbootUnlock"
            r4 = 2
            java.lang.Class[] r4 = new java.lang.Class[r4]     // Catch:{ Exception -> 0x0077 }
            r5 = 0
            java.lang.Class<byte[]> r6 = byte[].class
            r4[r5] = r6     // Catch:{ Exception -> 0x0077 }
            r5 = 1
            java.lang.Class r6 = java.lang.Integer.TYPE     // Catch:{ Exception -> 0x0077 }
            r4[r5] = r6     // Catch:{ Exception -> 0x0077 }
            java.lang.reflect.Method r2 = r2.getMethod(r3, r4)     // Catch:{ Exception -> 0x0077 }
            r3 = 0
            r4 = 2
            java.lang.Object[] r4 = new java.lang.Object[r4]     // Catch:{ Exception -> 0x0077 }
            r5 = 0
            r4[r5] = r0     // Catch:{ Exception -> 0x0077 }
            r5 = 1
            int r0 = r0.length     // Catch:{ Exception -> 0x0077 }
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)     // Catch:{ Exception -> 0x0077 }
            r4[r5] = r0     // Catch:{ Exception -> 0x0077 }
            java.lang.Object r0 = r2.invoke(r3, r4)     // Catch:{ Exception -> 0x0077 }
            java.lang.Boolean r0 = (java.lang.Boolean) r0     // Catch:{ Exception -> 0x0077 }
            boolean r1 = r0.booleanValue()     // Catch:{ Exception -> 0x0077 }
        L_0x0036:
            return r1
        L_0x0037:
            int r0 = r7.length()
            if (r0 != 0) goto L_0x0040
            byte[] r0 = new byte[r1]
            goto L_0x0004
        L_0x0040:
            int r0 = r7.length()
            int r0 = r0 / 2
            byte[] r2 = new byte[r0]
            r0 = r1
        L_0x0049:
            int r3 = r2.length
            if (r0 >= r3) goto L_0x0062
            int r3 = r0 * 2
            int r4 = r0 * 2
            int r4 = r4 + 2
            java.lang.String r3 = r7.substring(r3, r4)
            r4 = 16
            int r3 = java.lang.Integer.parseInt(r3, r4)
            byte r3 = (byte) r3
            r2[r0] = r3
            int r0 = r0 + 1
            goto L_0x0049
        L_0x0062:
            java.lang.String r0 = "Utils"
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            int r4 = r2.length
            java.lang.StringBuilder r3 = r3.append(r4)
            java.lang.String r3 = r3.toString()
            com.coloros.deeptesting.p007a.C0150g.m348a(r0, r3)
            r0 = r2
            goto L_0x0004
        L_0x0077:
            r0 = move-exception
            r0.printStackTrace()
            goto L_0x0036
        */
        throw new UnsupportedOperationException("Method not decompiled: com.coloros.deeptesting.p007a.C0151h.m357a(java.lang.String):boolean");
    }

    /* renamed from: b */
    public static boolean m359b() {
        byte[] bArr = {0};
        try {
            return ((Boolean) Class.forName("android.engineer.OppoEngineerManager").getMethod("fastbootUnlock", new Class[]{byte[].class, Integer.TYPE}).invoke((Object) null, new Object[]{bArr, 1})).booleanValue();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:14:0x004b A[SYNTHETIC, Splitter:B:14:0x004b] */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x0050 A[SYNTHETIC, Splitter:B:17:0x0050] */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x00c8 A[SYNTHETIC, Splitter:B:37:0x00c8] */
    /* JADX WARNING: Removed duplicated region for block: B:40:0x00cd A[SYNTHETIC, Splitter:B:40:0x00cd] */
    /* JADX WARNING: Removed duplicated region for block: B:56:? A[RETURN, SYNTHETIC] */
    /* renamed from: c */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void m360c() {
        /*
            r2 = 0
            r7 = 0
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.Runtime r1 = java.lang.Runtime.getRuntime()     // Catch:{ IOException -> 0x0109, all -> 0x00c3 }
            java.lang.String r3 = "reboot bootloader"
            java.lang.Process r1 = r1.exec(r3)     // Catch:{ IOException -> 0x0109, all -> 0x00c3 }
            java.io.BufferedReader r3 = new java.io.BufferedReader     // Catch:{ IOException -> 0x010e, all -> 0x0104 }
            java.io.InputStreamReader r4 = new java.io.InputStreamReader     // Catch:{ IOException -> 0x010e, all -> 0x0104 }
            java.io.InputStream r5 = r1.getInputStream()     // Catch:{ IOException -> 0x010e, all -> 0x0104 }
            java.lang.String r6 = "UTF-8"
            r4.<init>(r5, r6)     // Catch:{ IOException -> 0x010e, all -> 0x0104 }
            r3.<init>(r4)     // Catch:{ IOException -> 0x010e, all -> 0x0104 }
        L_0x0021:
            java.lang.String r2 = r3.readLine()     // Catch:{ IOException -> 0x0031 }
            if (r2 == 0) goto L_0x0057
            java.lang.StringBuilder r2 = r0.append(r2)     // Catch:{ IOException -> 0x0031 }
            java.lang.String r4 = "\n"
            r2.append(r4)     // Catch:{ IOException -> 0x0031 }
            goto L_0x0021
        L_0x0031:
            r0 = move-exception
        L_0x0032:
            java.lang.String r2 = "bootloader"
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ all -> 0x0107 }
            java.lang.String r5 = "failed parsing sensor map  "
            r4.<init>(r5)     // Catch:{ all -> 0x0107 }
            java.lang.StringBuilder r0 = r4.append(r0)     // Catch:{ all -> 0x0107 }
            java.lang.String r0 = r0.toString()     // Catch:{ all -> 0x0107 }
            r4 = 0
            java.lang.Object[] r4 = new java.lang.Object[r4]     // Catch:{ all -> 0x0107 }
            android.telecom.Log.w(r2, r0, r4)     // Catch:{ all -> 0x0107 }
            if (r3 == 0) goto L_0x004e
            r3.close()     // Catch:{ IOException -> 0x0093 }
        L_0x004e:
            if (r1 == 0) goto L_0x0056
            r1.waitFor()     // Catch:{ InterruptedException -> 0x00ab }
        L_0x0053:
            r1.destroy()
        L_0x0056:
            return
        L_0x0057:
            r3.close()     // Catch:{ IOException -> 0x0063 }
        L_0x005a:
            if (r1 == 0) goto L_0x0056
            r1.waitFor()     // Catch:{ InterruptedException -> 0x007b }
        L_0x005f:
            r1.destroy()
            goto L_0x0056
        L_0x0063:
            r0 = move-exception
            java.lang.String r2 = "bootloader"
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            java.lang.String r4 = "failed closing reader  "
            r3.<init>(r4)
            java.lang.StringBuilder r0 = r3.append(r0)
            java.lang.String r0 = r0.toString()
            java.lang.Object[] r3 = new java.lang.Object[r7]
            android.telecom.Log.w(r2, r0, r3)
            goto L_0x005a
        L_0x007b:
            r0 = move-exception
            java.lang.String r2 = "bootloader"
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            java.lang.String r4 = "failed process waitfor "
            r3.<init>(r4)
            java.lang.StringBuilder r0 = r3.append(r0)
            java.lang.String r0 = r0.toString()
            java.lang.Object[] r3 = new java.lang.Object[r7]
            android.telecom.Log.w(r2, r0, r3)
            goto L_0x005f
        L_0x0093:
            r0 = move-exception
            java.lang.String r2 = "bootloader"
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            java.lang.String r4 = "failed closing reader  "
            r3.<init>(r4)
            java.lang.StringBuilder r0 = r3.append(r0)
            java.lang.String r0 = r0.toString()
            java.lang.Object[] r3 = new java.lang.Object[r7]
            android.telecom.Log.w(r2, r0, r3)
            goto L_0x004e
        L_0x00ab:
            r0 = move-exception
            java.lang.String r2 = "bootloader"
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            java.lang.String r4 = "failed process waitfor "
            r3.<init>(r4)
            java.lang.StringBuilder r0 = r3.append(r0)
            java.lang.String r0 = r0.toString()
            java.lang.Object[] r3 = new java.lang.Object[r7]
            android.telecom.Log.w(r2, r0, r3)
            goto L_0x0053
        L_0x00c3:
            r0 = move-exception
            r1 = r2
            r3 = r2
        L_0x00c6:
            if (r3 == 0) goto L_0x00cb
            r3.close()     // Catch:{ IOException -> 0x00d4 }
        L_0x00cb:
            if (r1 == 0) goto L_0x00d3
            r1.waitFor()     // Catch:{ InterruptedException -> 0x00ec }
        L_0x00d0:
            r1.destroy()
        L_0x00d3:
            throw r0
        L_0x00d4:
            r2 = move-exception
            java.lang.String r3 = "bootloader"
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            java.lang.String r5 = "failed closing reader  "
            r4.<init>(r5)
            java.lang.StringBuilder r2 = r4.append(r2)
            java.lang.String r2 = r2.toString()
            java.lang.Object[] r4 = new java.lang.Object[r7]
            android.telecom.Log.w(r3, r2, r4)
            goto L_0x00cb
        L_0x00ec:
            r2 = move-exception
            java.lang.String r3 = "bootloader"
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            java.lang.String r5 = "failed process waitfor "
            r4.<init>(r5)
            java.lang.StringBuilder r2 = r4.append(r2)
            java.lang.String r2 = r2.toString()
            java.lang.Object[] r4 = new java.lang.Object[r7]
            android.telecom.Log.w(r3, r2, r4)
            goto L_0x00d0
        L_0x0104:
            r0 = move-exception
            r3 = r2
            goto L_0x00c6
        L_0x0107:
            r0 = move-exception
            goto L_0x00c6
        L_0x0109:
            r0 = move-exception
            r1 = r2
            r3 = r2
            goto L_0x0032
        L_0x010e:
            r0 = move-exception
            r3 = r2
            goto L_0x0032
        */
        throw new UnsupportedOperationException("Method not decompiled: com.coloros.deeptesting.p007a.C0151h.m360c():void");
    }
}
