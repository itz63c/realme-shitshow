package com.coloros.mcssdk;

import android.content.Context;
import android.content.Intent;
import com.coloros.mcssdk.p008a.C0168a;
import com.coloros.mcssdk.p008a.C0169b;
import com.coloros.mcssdk.p008a.C0171d;
import com.coloros.mcssdk.p008a.C0172e;
import com.coloros.mcssdk.p009b.C0174a;
import com.coloros.mcssdk.p009b.C0175b;
import com.coloros.mcssdk.p009b.C0176c;
import com.coloros.mcssdk.p009b.C0177d;
import com.coloros.mcssdk.p010c.C0179b;
import com.coloros.mcssdk.p011d.C0182b;
import com.coloros.mcssdk.p012e.C0183a;
import com.coloros.mcssdk.p012e.C0186d;
import java.util.ArrayList;
import java.util.List;

/* renamed from: com.coloros.mcssdk.a */
public class C0167a {

    /* renamed from: e */
    private static int f339e = 0;

    /* renamed from: a */
    private List<C0176c> f340a;

    /* renamed from: b */
    private List<C0171d> f341b;

    /* renamed from: c */
    private String f342c;

    /* renamed from: d */
    private C0182b f343d;

    private C0167a() {
        this.f340a = new ArrayList();
        this.f341b = new ArrayList();
        synchronized (C0167a.class) {
            if (f339e > 0) {
                throw new RuntimeException("PushManager can't create again!");
            }
            f339e++;
        }
        m390a((C0171d) new C0168a());
        m390a((C0171d) new C0172e());
        m390a((C0171d) new C0169b());
        m391a((C0176c) new C0174a());
        m391a((C0176c) new C0177d());
        m391a((C0176c) new C0175b());
    }

    /* synthetic */ C0167a(byte b) {
        this();
    }

    /* renamed from: a */
    private synchronized void m390a(C0171d dVar) {
        this.f341b.add(dVar);
    }

    /* renamed from: a */
    private synchronized void m391a(C0176c cVar) {
        this.f340a.add(cVar);
    }

    /* renamed from: c */
    public static C0167a m392c() {
        return C0173b.f344a;
    }

    /* renamed from: a */
    public final List<C0171d> mo214a() {
        return this.f341b;
    }

    /* renamed from: a */
    public final void mo215a(String str) {
        this.f342c = str;
    }

    /* renamed from: b */
    public final List<C0176c> mo216b() {
        return this.f340a;
    }

    /* renamed from: d */
    public final C0182b mo217d() {
        return this.f343d;
    }

    /* renamed from: a */
    public static void m388a(Context context, C0183a aVar, String str) {
        try {
            Intent intent = new Intent();
            intent.setAction("com.coloros.mcssdk.action.RECEIVE_SDK_MESSAGE");
            intent.setPackage("com.coloros.mcs");
            intent.putExtra("type", 12291);
            intent.putExtra("taskID", aVar.mo241e());
            intent.putExtra("appPackage", aVar.mo243f());
            intent.putExtra("messageID", new StringBuilder().append(aVar.mo245g()).toString());
            intent.putExtra("messageType", 4098);
            intent.putExtra("eventID", str);
            context.startService(intent);
        } catch (Exception e) {
            C0179b.m413b("statisticMessage--Exception" + e.getMessage());
        }
    }

    /* renamed from: a */
    public static void m389a(Context context, C0186d dVar, String str) {
        try {
            Intent intent = new Intent();
            intent.setAction("com.coloros.mcssdk.action.RECEIVE_SDK_MESSAGE");
            intent.setPackage("com.coloros.mcs");
            intent.putExtra("type", 12291);
            intent.putExtra("taskID", dVar.mo241e());
            intent.putExtra("appPackage", dVar.mo243f());
            intent.putExtra("messageID", new StringBuilder().append(dVar.mo245g()).toString());
            intent.putExtra("messageType", 4103);
            intent.putExtra("eventID", str);
            context.startService(intent);
        } catch (Exception e) {
            C0179b.m413b("statisticMessage--Exception" + e.getMessage());
        }
    }
}
