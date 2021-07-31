package com.tencent.bugly.crashreport.crash;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import com.tencent.bugly.crashreport.biz.C0254d;
import com.tencent.bugly.crashreport.common.info.C0257c;
import com.tencent.bugly.crashreport.common.strategy.C0259b;
import com.tencent.bugly.p014a.C0208a;
import com.tencent.bugly.p014a.C0216ah;
import com.tencent.bugly.p014a.C0219ak;
import com.tencent.bugly.p014a.C0221am;

/* compiled from: BUGLY */
public class BuglyBroadcastRecevier extends BroadcastReceiver {

    /* renamed from: a */
    public static String f738a = "com.tencent.feedback.A01";

    /* renamed from: b */
    public static String f739b = "com.tencent.feedback.A02";

    /* renamed from: f */
    private static BuglyBroadcastRecevier f740f = null;

    /* renamed from: c */
    private IntentFilter f741c = new IntentFilter();

    /* renamed from: d */
    private Context f742d;

    /* renamed from: e */
    private String f743e;
    /* access modifiers changed from: private */

    /* renamed from: g */
    public C0265d f744g;

    /* access modifiers changed from: protected */
    public void finalize() {
        super.finalize();
        if (this.f742d != null) {
            this.f742d.unregisterReceiver(this);
        }
    }

    public final void onReceive(Context context, Intent intent) {
        try {
            m755a(context, intent);
        } catch (Throwable th) {
            if (!C0221am.m594a(th)) {
                th.printStackTrace();
            }
        }
    }

    /* renamed from: a */
    private synchronized boolean m755a(Context context, Intent intent) {
        boolean z = true;
        synchronized (this) {
            if (!(context == null || intent == null)) {
                if (intent.getAction().equals("android.net.conn.CONNECTIVITY_CHANGE")) {
                    String e = C0208a.m502e(this.f742d);
                    C0221am.m597c("is Connect BC " + e, new Object[0]);
                    C0221am.m593a("network %s changed to %s", this.f743e, e);
                    if (e == null) {
                        this.f743e = null;
                    } else {
                        String str = this.f743e;
                        this.f743e = e;
                        long currentTimeMillis = System.currentTimeMillis();
                        C0259b a = C0259b.m751a();
                        C0216ah a2 = C0216ah.m553a();
                        C0257c a3 = C0257c.m731a(context);
                        if (a == null || a2 == null || a3 == null) {
                            C0221am.m598d("not inited BC not work", new Object[0]);
                        } else if (!e.equals(str)) {
                            if (currentTimeMillis - a2.mo366a(C0267f.f806a) > 60000) {
                                C0219ak.m587a().mo382b(new C0260a(this));
                            }
                            if (currentTimeMillis - a2.mo366a(1001) > 60000) {
                                C0221am.m593a("upManager.getLastUpTime(LaunchBizManager.MODULE_ID = %d", Long.valueOf(a2.mo366a(1001)));
                                C0254d.f649a.mo428a();
                            }
                        }
                    }
                }
            }
            z = false;
        }
        return z;
    }
}
