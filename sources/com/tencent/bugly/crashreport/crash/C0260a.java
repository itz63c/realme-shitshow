package com.tencent.bugly.crashreport.crash;

import com.tencent.bugly.p014a.C0221am;
import java.util.List;

/* renamed from: com.tencent.bugly.crashreport.crash.a */
/* compiled from: BUGLY */
final class C0260a implements Runnable {

    /* renamed from: a */
    private /* synthetic */ BuglyBroadcastRecevier f791a;

    C0260a(BuglyBroadcastRecevier buglyBroadcastRecevier) {
        this.f791a = buglyBroadcastRecevier;
    }

    public final void run() {
        try {
            List<CrashDetailBean> a = C0265d.m773a();
            if (a != null && a.size() > 0) {
                C0221am.m593a("upload crash on network changed ", new Object[0]);
                this.f791a.f744g.mo474a(a);
            }
        } catch (Throwable th) {
            if (!C0221am.m594a(th)) {
                th.printStackTrace();
            }
        }
    }
}
