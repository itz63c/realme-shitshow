package com.coloros.mcssdk;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import com.coloros.mcssdk.p008a.C0170c;
import com.coloros.mcssdk.p009b.C0176c;
import com.coloros.mcssdk.p010c.C0179b;
import com.coloros.mcssdk.p010c.C0180c;
import com.coloros.mcssdk.p011d.C0181a;
import com.coloros.mcssdk.p012e.C0184b;
import com.coloros.mcssdk.p012e.C0185c;
import java.util.List;

public class PushService extends Service implements C0181a {
    /* renamed from: a */
    public final void mo210a(C0184b bVar) {
        if (C0167a.m392c().mo217d() != null) {
            switch (bVar.mo233b()) {
                case 12289:
                    if (bVar.mo238d() == 0) {
                        C0167a.m392c().mo215a(bVar.mo236c());
                    }
                    C0167a.m392c();
                    return;
                case 12290:
                    C0167a.m392c();
                    return;
                case 12292:
                    C0167a.m392c();
                    C0184b.m426a(bVar.mo236c(), "alias", "aliasId", "aliasName");
                    return;
                case 12293:
                    C0167a.m392c();
                    C0184b.m426a(bVar.mo236c(), "alias", "aliasId", "aliasName");
                    return;
                case 12294:
                    C0167a.m392c();
                    C0184b.m426a(bVar.mo236c(), "alias", "aliasId", "aliasName");
                    return;
                case 12295:
                    C0167a.m392c();
                    C0184b.m426a(bVar.mo236c(), "tags", "tagId", "tagName");
                    return;
                case 12296:
                    C0167a.m392c();
                    C0184b.m426a(bVar.mo236c(), "tags", "tagId", "tagName");
                    return;
                case 12297:
                    C0167a.m392c();
                    C0184b.m426a(bVar.mo236c(), "tags", "tagId", "tagName");
                    return;
                case 12298:
                    C0167a.m392c();
                    return;
                case 12301:
                    C0167a.m392c();
                    C0184b.m426a(bVar.mo236c(), "tags", "accountId", "accountName");
                    return;
                case 12302:
                    C0167a.m392c();
                    C0184b.m426a(bVar.mo236c(), "tags", "accountId", "accountName");
                    return;
                case 12303:
                    C0167a.m392c();
                    C0184b.m426a(bVar.mo236c(), "tags", "accountId", "accountName");
                    return;
                case 12306:
                    C0167a.m392c();
                    C0180c.m414a(bVar.mo236c());
                    return;
                case 12309:
                    C0167a.m392c();
                    C0180c.m414a(bVar.mo236c());
                    return;
                default:
                    return;
            }
        }
    }

    public IBinder onBind(Intent intent) {
        return null;
    }

    public void onCreate() {
        super.onCreate();
    }

    public int onStartCommand(Intent intent, int i, int i2) {
        List<C0185c> a = C0170c.m401a(getApplicationContext(), intent);
        List<C0176c> b = C0167a.m392c().mo216b();
        if (a == null || a.size() == 0 || b == null || b.size() == 0) {
            return super.onStartCommand(intent, i, i2);
        }
        for (C0185c next : a) {
            if (next != null) {
                for (C0176c next2 : b) {
                    if (next2 != null) {
                        try {
                            getApplicationContext();
                            next2.mo219a(next, this);
                        } catch (Exception e) {
                            C0179b.m413b("process Exception:" + e.getMessage());
                        }
                    }
                }
            }
        }
        return super.onStartCommand(intent, i, i2);
    }
}
