package com.coloros.deeptesting.service;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Messenger;
import com.coloros.deeptesting.p007a.C0150g;
import com.coloros.deeptesting.p007a.C0151h;
import com.coloros.neton.NetonClient;
import com.coloros.neton.NetonException;
import com.p000a.p001a.C0125j;

public class RequestService extends Service {

    /* renamed from: a */
    private static Context f322a;

    /* renamed from: b */
    private HandlerThread f323b;

    /* renamed from: c */
    private Handler f324c;
    /* access modifiers changed from: private */

    /* renamed from: d */
    public Messenger f325d;

    /* renamed from: e */
    private String f326e;

    public IBinder onBind(Intent intent) {
        return null;
    }

    public void onCreate() {
        f322a = this;
        try {
            NetonClient.getInstance().init(getApplicationContext());
        } catch (NetonException e) {
            C0150g.m348a("Utils", "neton init error");
            e.printStackTrace();
        }
        this.f323b = new HandlerThread("Request");
        this.f323b.start();
        if (this.f323b.getLooper() != null) {
            this.f324c = new C0163a(this, this.f323b.getLooper());
        } else {
            stopSelf();
        }
        super.onCreate();
    }

    public int onStartCommand(Intent intent, int i, int i2) {
        if (intent.getExtras() == null) {
            return super.onStartCommand(intent, i, i2);
        }
        this.f325d = (Messenger) intent.getExtras().get("Messenger");
        switch (((Integer) intent.getExtras().get("MessengerFlag")).intValue()) {
            case 1000:
                this.f326e = "https://lkf.realmemobile.com/realme/v1/applyLkUnlock";
                break;
            case 1001:
                this.f326e = "https://lkf.realmemobile.com/realme/v1/checkApproveResult";
                break;
            case 1002:
                this.f326e = "https://lkf.realmemobile.com/realme/v1/updateLockStatus";
                break;
            case 1003:
                this.f326e = "https://lkf.realmemobile.com/realme/v1/acquireClientStatus";
                break;
            case 1004:
                this.f326e = "https://lkf.realmemobile.com/realme/v1/closeApply";
                break;
            case 1005:
                this.f326e = "https://lkf.realmemobile.com/realme/v1/acquireApplyStatus";
                break;
        }
        this.f324c.sendEmptyMessage(0);
        return 2;
    }

    public void onDestroy() {
        super.onDestroy();
        this.f323b.quit();
    }

    /* renamed from: a */
    public final C0165c mo203a() {
        C0164b bVar = new C0164b();
        bVar.mo209a(f322a);
        try {
            return (C0165c) new C0125j().mo143a(m382a(this.f326e, C0151h.m352a((Object) bVar)), C0165c.class);
        } catch (Exception e) {
            C0150g.m349b("RequestService", "parser error gson when parsing update query response from server");
            e.printStackTrace();
            return null;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:50:0x008b A[SYNTHETIC, Splitter:B:50:0x008b] */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static java.lang.String m382a(java.lang.String r5, java.lang.String r6) {
        /*
            r0 = 0
            if (r5 != 0) goto L_0x000b
            java.lang.String r1 = "RequestService"
            java.lang.String r2 = "request url is null"
            com.coloros.deeptesting.p007a.C0150g.m348a(r1, r2)
        L_0x000a:
            return r0
        L_0x000b:
            if (r6 != 0) goto L_0x0015
            java.lang.String r1 = "RequestService"
            java.lang.String r2 = "request string is null"
            com.coloros.deeptesting.p007a.C0150g.m348a(r1, r2)
            goto L_0x000a
        L_0x0015:
            java.lang.String r1 = com.coloros.deeptesting.p007a.C0144a.m331a((java.lang.String) r6)
            if (r1 != 0) goto L_0x0023
            java.lang.String r1 = "RequestService"
            java.lang.String r2 = " json request is null after encrypt"
            com.coloros.deeptesting.p007a.C0150g.m348a(r1, r2)
            goto L_0x000a
        L_0x0023:
            neton.Response r2 = com.coloros.deeptesting.p007a.C0151h.m353a(r5, r1)     // Catch:{ Exception -> 0x0070, all -> 0x0087 }
            if (r2 != 0) goto L_0x003b
            java.lang.String r1 = "RequestService"
            java.lang.String r3 = "neton response is null"
            com.coloros.deeptesting.p007a.C0150g.m348a(r1, r3)     // Catch:{ Exception -> 0x0097 }
            if (r2 == 0) goto L_0x000a
            r2.close()     // Catch:{ NetonException -> 0x0036 }
            goto L_0x000a
        L_0x0036:
            r1 = move-exception
            r1.printStackTrace()
            goto L_0x000a
        L_0x003b:
            r2.code()     // Catch:{ Exception -> 0x0097 }
            neton.ResponseBody r1 = r2.body()     // Catch:{ Exception -> 0x0097 }
            byte[] r3 = r1.bytes()     // Catch:{ Exception -> 0x0097 }
            if (r3 == 0) goto L_0x004b
            int r1 = r3.length     // Catch:{ Exception -> 0x0097 }
            if (r1 != 0) goto L_0x006e
        L_0x004b:
            r1 = 1
        L_0x004c:
            if (r1 != 0) goto L_0x0063
            java.lang.String r1 = new java.lang.String     // Catch:{ Exception -> 0x0097 }
            java.lang.String r4 = "UTF-8"
            r1.<init>(r3, r4)     // Catch:{ Exception -> 0x0097 }
            java.lang.String r1 = com.coloros.deeptesting.p007a.C0144a.m333b(r1)     // Catch:{ Exception -> 0x0097 }
            if (r1 != 0) goto L_0x0062
            java.lang.String r3 = "RequestService"
            java.lang.String r4 = "decryptJsonFromServer reslut is null"
            com.coloros.deeptesting.p007a.C0150g.m348a(r3, r4)     // Catch:{ Exception -> 0x0097 }
        L_0x0062:
            r0 = r1
        L_0x0063:
            if (r2 == 0) goto L_0x000a
            r2.close()     // Catch:{ NetonException -> 0x0069 }
            goto L_0x000a
        L_0x0069:
            r1 = move-exception
            r1.printStackTrace()
            goto L_0x000a
        L_0x006e:
            r1 = 0
            goto L_0x004c
        L_0x0070:
            r1 = move-exception
            r2 = r0
        L_0x0072:
            java.lang.String r3 = "RequestService"
            java.lang.String r4 = "post to server occur"
            com.coloros.deeptesting.p007a.C0150g.m350c(r3, r4)     // Catch:{ all -> 0x0094 }
            r1.printStackTrace()     // Catch:{ all -> 0x0094 }
            if (r2 == 0) goto L_0x000a
            r2.close()     // Catch:{ NetonException -> 0x0082 }
            goto L_0x000a
        L_0x0082:
            r1 = move-exception
            r1.printStackTrace()
            goto L_0x000a
        L_0x0087:
            r1 = move-exception
            r2 = r0
        L_0x0089:
            if (r2 == 0) goto L_0x008e
            r2.close()     // Catch:{ NetonException -> 0x008f }
        L_0x008e:
            throw r1
        L_0x008f:
            r0 = move-exception
            r0.printStackTrace()
            goto L_0x008e
        L_0x0094:
            r0 = move-exception
            r1 = r0
            goto L_0x0089
        L_0x0097:
            r1 = move-exception
            goto L_0x0072
        */
        throw new UnsupportedOperationException("Method not decompiled: com.coloros.deeptesting.service.RequestService.m382a(java.lang.String, java.lang.String):java.lang.String");
    }
}
