package com.coloros.mcssdk.p008a;

import android.content.Context;
import android.content.Intent;
import com.coloros.mcssdk.p010c.C0178a;
import com.coloros.mcssdk.p010c.C0179b;
import com.coloros.mcssdk.p012e.C0184b;
import com.coloros.mcssdk.p012e.C0185c;

/* renamed from: com.coloros.mcssdk.a.b */
public final class C0169b extends C0170c {
    /* renamed from: a */
    private static C0185c m399a(Intent intent) {
        try {
            C0184b bVar = new C0184b();
            bVar.mo231a(Integer.parseInt(C0178a.m410a(intent.getStringExtra("command"))));
            bVar.mo234b(Integer.parseInt(C0178a.m410a(intent.getStringExtra("code"))));
            bVar.mo237c(C0178a.m410a(intent.getStringExtra("content")));
            bVar.mo232a(C0178a.m410a(intent.getStringExtra("appKey")));
            bVar.mo235b(C0178a.m410a(intent.getStringExtra("appSecret")));
            bVar.mo244f(C0178a.m410a(intent.getStringExtra("appPackage")));
            C0179b.m412a("OnHandleIntent-message:" + bVar.toString());
            return bVar;
        } catch (Exception e) {
            C0179b.m412a("OnHandleIntent--" + e.getMessage());
            return null;
        }
    }

    /* renamed from: a */
    public final C0185c mo218a(Context context, int i, Intent intent) {
        if (4105 == i) {
            return m399a(intent);
        }
        return null;
    }
}
