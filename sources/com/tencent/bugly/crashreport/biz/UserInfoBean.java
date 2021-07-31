package com.tencent.bugly.crashreport.biz;

import android.os.Parcel;
import android.os.Parcelable;
import com.tencent.bugly.p014a.C0208a;
import java.util.Map;

/* compiled from: BUGLY */
public class UserInfoBean implements Parcelable {
    public static final Parcelable.Creator<UserInfoBean> CREATOR = new C0251a();

    /* renamed from: a */
    public long f626a;

    /* renamed from: b */
    public int f627b;

    /* renamed from: c */
    public String f628c;

    /* renamed from: d */
    public String f629d;

    /* renamed from: e */
    public long f630e;

    /* renamed from: f */
    public long f631f;

    /* renamed from: g */
    public long f632g;

    /* renamed from: h */
    public long f633h;

    /* renamed from: i */
    public long f634i;

    /* renamed from: j */
    public String f635j;

    /* renamed from: k */
    public long f636k = 0;

    /* renamed from: l */
    public boolean f637l = false;

    /* renamed from: m */
    public String f638m = "unknown";

    /* renamed from: n */
    public String f639n;

    /* renamed from: o */
    public int f640o;

    /* renamed from: p */
    public int f641p = -1;

    /* renamed from: q */
    public int f642q = -1;

    /* renamed from: r */
    public Map<String, String> f643r = null;

    /* renamed from: s */
    public Map<String, String> f644s = null;

    public UserInfoBean() {
    }

    public UserInfoBean(Parcel parcel) {
        boolean z = true;
        this.f627b = parcel.readInt();
        this.f628c = parcel.readString();
        this.f629d = parcel.readString();
        this.f630e = parcel.readLong();
        this.f631f = parcel.readLong();
        this.f632g = parcel.readLong();
        this.f633h = parcel.readLong();
        this.f634i = parcel.readLong();
        this.f635j = parcel.readString();
        this.f636k = parcel.readLong();
        this.f637l = parcel.readByte() != 1 ? false : z;
        this.f638m = parcel.readString();
        this.f641p = parcel.readInt();
        this.f642q = parcel.readInt();
        this.f643r = C0208a.m491b(parcel);
        this.f644s = C0208a.m491b(parcel);
        this.f639n = parcel.readString();
        this.f640o = parcel.readInt();
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.f627b);
        parcel.writeString(this.f628c);
        parcel.writeString(this.f629d);
        parcel.writeLong(this.f630e);
        parcel.writeLong(this.f631f);
        parcel.writeLong(this.f632g);
        parcel.writeLong(this.f633h);
        parcel.writeLong(this.f634i);
        parcel.writeString(this.f635j);
        parcel.writeLong(this.f636k);
        parcel.writeByte((byte) (this.f637l ? 1 : 0));
        parcel.writeString(this.f638m);
        parcel.writeInt(this.f641p);
        parcel.writeInt(this.f642q);
        C0208a.m492b(parcel, this.f643r);
        C0208a.m492b(parcel, this.f644s);
        parcel.writeString(this.f639n);
        parcel.writeInt(this.f640o);
    }
}
