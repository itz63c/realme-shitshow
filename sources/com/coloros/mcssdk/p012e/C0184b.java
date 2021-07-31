package com.coloros.mcssdk.p012e;

import android.text.TextUtils;
import com.coloros.mcssdk.p010c.C0179b;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: com.coloros.mcssdk.e.b */
public final class C0184b extends C0185c {

    /* renamed from: a */
    public static final String f362a = null;

    /* renamed from: b */
    private String f363b;

    /* renamed from: c */
    private String f364c;

    /* renamed from: d */
    private String f365d;

    /* renamed from: e */
    private String f366e;

    /* renamed from: f */
    private int f367f;

    /* renamed from: g */
    private String f368g;

    /* renamed from: h */
    private int f369h = -2;

    /* renamed from: a */
    public static List<C0187e> m426a(String str, String str2, String str3, String str4) {
        ArrayList arrayList;
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        try {
            JSONArray jSONArray = new JSONObject(str).getJSONArray(str2);
            arrayList = new ArrayList();
            int i = 0;
            while (i < jSONArray.length()) {
                try {
                    JSONObject jSONObject = jSONArray.getJSONObject(i);
                    C0187e eVar = new C0187e();
                    eVar.mo252b(jSONObject.getString(str4));
                    eVar.mo251a(jSONObject.getString(str3));
                    arrayList.add(eVar);
                    i++;
                } catch (JSONException e) {
                    e = e;
                    e.printStackTrace();
                    C0179b.m412a("parseToSubscribeResultList--" + arrayList);
                    return arrayList;
                }
            }
        } catch (JSONException e2) {
            e = e2;
            arrayList = null;
            e.printStackTrace();
            C0179b.m412a("parseToSubscribeResultList--" + arrayList);
            return arrayList;
        }
        C0179b.m412a("parseToSubscribeResultList--" + arrayList);
        return arrayList;
    }

    /* renamed from: a */
    public final int mo220a() {
        return 4105;
    }

    /* renamed from: a */
    public final void mo231a(int i) {
        this.f367f = i;
    }

    /* renamed from: a */
    public final void mo232a(String str) {
        this.f363b = str;
    }

    /* renamed from: b */
    public final int mo233b() {
        return this.f367f;
    }

    /* renamed from: b */
    public final void mo234b(int i) {
        this.f369h = i;
    }

    /* renamed from: b */
    public final void mo235b(String str) {
        this.f364c = str;
    }

    /* renamed from: c */
    public final String mo236c() {
        return this.f368g;
    }

    /* renamed from: c */
    public final void mo237c(String str) {
        this.f368g = str;
    }

    /* renamed from: d */
    public final int mo238d() {
        return this.f369h;
    }

    public final String toString() {
        return "type:4105,messageID:" + this.f370j + ",taskID:" + this.f372l + ",appPackage:" + this.f371k + ",registerID:" + this.f365d + ",sdkVersion:" + this.f366e + ",command:" + this.f367f + ",responseCode:" + this.f369h + ",content:" + this.f368g;
    }
}
