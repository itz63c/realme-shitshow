package com.coloros.mcssdk.p008a;

import android.content.Context;
import android.content.Intent;
import com.coloros.mcssdk.C0167a;
import com.coloros.mcssdk.p010c.C0178a;
import com.coloros.mcssdk.p010c.C0179b;
import com.coloros.mcssdk.p012e.C0185c;
import java.util.ArrayList;
import java.util.List;

/* renamed from: com.coloros.mcssdk.a.c */
public abstract class C0170c implements C0171d {
    /* renamed from: a */
    public static List<C0185c> m401a(Context context, Intent intent) {
        int i;
        C0185c a;
        if (intent == null) {
            return null;
        }
        try {
            i = Integer.parseInt(C0178a.m410a(intent.getStringExtra("type")));
        } catch (Exception e) {
            C0179b.m413b("MessageParser--getMessageByIntent--Exception:" + e.getMessage());
            i = 4096;
        }
        C0179b.m412a("MessageParser--getMessageByIntent--type:" + i);
        ArrayList arrayList = new ArrayList();
        for (C0171d next : C0167a.m392c().mo214a()) {
            if (!(next == null || (a = next.mo218a(context, i, intent)) == null)) {
                arrayList.add(a);
            }
        }
        return arrayList;
    }
}
