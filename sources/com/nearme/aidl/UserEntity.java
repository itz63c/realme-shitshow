package com.nearme.aidl;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import com.coloros.neton.BuildConfig;
import org.json.JSONException;
import org.json.JSONObject;

public class UserEntity implements Parcelable {
    public static final Parcelable.Creator<UserEntity> CREATOR = new C0196a();

    /* renamed from: a */
    private int f379a;

    /* renamed from: b */
    private String f380b;

    /* renamed from: c */
    private String f381c;

    /* renamed from: d */
    private String f382d;

    public UserEntity() {
        this.f379a = 0;
        this.f380b = BuildConfig.FLAVOR;
        this.f381c = BuildConfig.FLAVOR;
        this.f382d = BuildConfig.FLAVOR;
    }

    public UserEntity(String str, String str2, String str3) {
        this.f379a = 30001004;
        this.f380b = str;
        this.f381c = str2;
        this.f382d = str3;
    }

    /* renamed from: a */
    public final String mo326a() {
        return this.f381c;
    }

    /* renamed from: a */
    public final void mo328a(String str) {
        this.f381c = str;
    }

    /* renamed from: b */
    public final void mo330b(String str) {
        this.f382d = str;
    }

    /* renamed from: a */
    public final void mo327a(int i) {
        this.f379a = i;
    }

    /* renamed from: b */
    public final int mo329b() {
        return this.f379a;
    }

    /* renamed from: c */
    public final void mo331c(String str) {
        this.f380b = str;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.f379a);
        parcel.writeString(this.f380b);
        parcel.writeString(this.f381c);
        parcel.writeString(this.f382d);
    }

    /* renamed from: d */
    public static UserEntity m451d(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        UserEntity userEntity = new UserEntity();
        try {
            JSONObject jSONObject = new JSONObject(str);
            if (!jSONObject.isNull("result") && jSONObject.get("result") != JSONObject.NULL) {
                userEntity.f379a = jSONObject.getInt("result");
            }
            if (!jSONObject.isNull("resultMsg") && jSONObject.get("resultMsg") != JSONObject.NULL) {
                userEntity.f380b = jSONObject.getString("resultMsg");
            }
            if (!jSONObject.isNull("username") && jSONObject.get("username") != JSONObject.NULL) {
                userEntity.f381c = jSONObject.getString("username");
            }
            if (!jSONObject.isNull("authToken") && jSONObject.get("authToken") != JSONObject.NULL) {
                userEntity.f382d = jSONObject.getString("authToken");
            }
            return userEntity;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    /* renamed from: a */
    public static String m450a(UserEntity userEntity) {
        if (userEntity == null) {
            return null;
        }
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("result", userEntity.f379a);
            jSONObject.put("resultMsg", userEntity.f380b);
            jSONObject.put("username", userEntity.f381c);
            jSONObject.put("authToken", userEntity.f382d);
            return jSONObject.toString();
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    public String toString() {
        return "{UserEntity : [result = " + this.f379a + "],[resultMsg = " + this.f380b + "],[username = " + this.f381c + "],[authToken = " + this.f382d + "}";
    }
}
