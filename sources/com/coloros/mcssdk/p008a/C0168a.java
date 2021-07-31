package com.coloros.mcssdk.p008a;

import android.content.Context;
import android.content.Intent;
import com.coloros.mcssdk.C0167a;
import com.coloros.mcssdk.p010c.C0178a;
import com.coloros.mcssdk.p010c.C0179b;
import com.coloros.mcssdk.p012e.C0183a;
import com.coloros.mcssdk.p012e.C0185c;

/* renamed from: com.coloros.mcssdk.a.a */
public final class C0168a extends C0170c {
    /* renamed from: a */
    private static C0185c m397a(Intent intent) {
        try {
            C0183a aVar = new C0183a();
            aVar.mo240d(Integer.parseInt(C0178a.m410a(intent.getStringExtra("messageID"))));
            aVar.mo242e(C0178a.m410a(intent.getStringExtra("taskID")));
            aVar.mo244f(C0178a.m410a(intent.getStringExtra("appPackage")));
            aVar.mo223a(C0178a.m410a(intent.getStringExtra("content")));
            aVar.mo221a(Integer.parseInt(C0178a.m410a(intent.getStringExtra("balanceTime"))));
            aVar.mo222a(Long.parseLong(C0178a.m410a(intent.getStringExtra("startDate"))));
            aVar.mo225b(Long.parseLong(C0178a.m410a(intent.getStringExtra("endDate"))));
            aVar.mo226b(C0178a.m410a(intent.getStringExtra("timeRanges")));
            aVar.mo228c(C0178a.m410a(intent.getStringExtra("title")));
            aVar.mo229d(C0178a.m410a(intent.getStringExtra("rule")));
            aVar.mo224b(Integer.parseInt(C0178a.m410a(intent.getStringExtra("forcedDelivery"))));
            aVar.mo227c(Integer.parseInt(C0178a.m410a(intent.getStringExtra("distinctBycontent"))));
            C0179b.m412a("OnHandleIntent-message:" + aVar.toString());
            return aVar;
        } catch (Exception e) {
            C0179b.m412a("OnHandleIntent--" + e.getMessage());
            return null;
        }
    }

    /* renamed from: a */
    public final C0185c mo218a(Context context, int i, Intent intent) {
        if (4098 != i) {
            return null;
        }
        C0185c a = m397a(intent);
        C0167a.m388a(context, (C0183a) a, "push_transmit");
        return a;
    }
}
