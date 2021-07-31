package com.coloros.mcssdk.p008a;

import android.content.Context;
import android.content.Intent;
import com.coloros.mcssdk.C0167a;
import com.coloros.mcssdk.p010c.C0178a;
import com.coloros.mcssdk.p010c.C0179b;
import com.coloros.mcssdk.p012e.C0185c;
import com.coloros.mcssdk.p012e.C0186d;

/* renamed from: com.coloros.mcssdk.a.e */
public final class C0172e extends C0170c {
    /* renamed from: a */
    private static C0185c m403a(Intent intent) {
        try {
            C0186d dVar = new C0186d();
            dVar.mo240d(Integer.parseInt(C0178a.m410a(intent.getStringExtra("messageID"))));
            dVar.mo242e(C0178a.m410a(intent.getStringExtra("taskID")));
            dVar.mo244f(C0178a.m410a(intent.getStringExtra("appPackage")));
            dVar.mo247b(C0178a.m410a(intent.getStringExtra("content")));
            dVar.mo248c(C0178a.m410a(intent.getStringExtra("description")));
            dVar.mo249d(C0178a.m410a(intent.getStringExtra("appID")));
            dVar.mo246a(C0178a.m410a(intent.getStringExtra("globalID")));
            return dVar;
        } catch (Exception e) {
            C0179b.m412a("OnHandleIntent--" + e.getMessage());
            return null;
        }
    }

    /* renamed from: a */
    public final C0185c mo218a(Context context, int i, Intent intent) {
        if (4103 != i) {
            return null;
        }
        C0185c a = m403a(intent);
        C0167a.m389a(context, (C0186d) a, "push_transmit");
        return a;
    }
}
