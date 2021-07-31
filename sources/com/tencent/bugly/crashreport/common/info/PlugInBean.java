package com.tencent.bugly.crashreport.common.info;

import android.os.Parcel;
import android.os.Parcelable;

/* compiled from: BUGLY */
public class PlugInBean implements Parcelable {
    public static final Parcelable.Creator<PlugInBean> CREATOR = new C0256b();

    /* renamed from: a */
    public final String f653a;

    /* renamed from: b */
    public final String f654b;

    /* renamed from: c */
    public final String f655c;

    public PlugInBean(String str, String str2, String str3) {
        this.f653a = str;
        this.f654b = str2;
        this.f655c = str3;
    }

    public String toString() {
        return "plid:" + this.f653a + " plV:" + this.f654b + " plUUID:" + this.f655c;
    }

    public PlugInBean(Parcel parcel) {
        this.f653a = parcel.readString();
        this.f654b = parcel.readString();
        this.f655c = parcel.readString();
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.f653a);
        parcel.writeString(this.f654b);
        parcel.writeString(this.f655c);
    }
}
