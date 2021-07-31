package com.oppo.usercenter.sdk;

import android.os.Parcel;
import android.os.Parcelable;

public class UCVerifyResultEntity implements Parcelable {
    public static final Parcelable.Creator<UCVerifyResultEntity> CREATOR = new C0203d();

    /* renamed from: a */
    public String f387a;

    /* renamed from: b */
    public String f388b;

    /* renamed from: c */
    public boolean f389c;

    /* renamed from: d */
    public String f390d;

    /* renamed from: e */
    public String f391e;

    /* renamed from: f */
    public String f392f;

    public UCVerifyResultEntity() {
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.f387a);
        parcel.writeString(this.f388b);
        parcel.writeByte(this.f389c ? (byte) 1 : 0);
        parcel.writeString(this.f390d);
        parcel.writeString(this.f391e);
        parcel.writeString(this.f392f);
    }

    protected UCVerifyResultEntity(Parcel parcel) {
        this.f387a = parcel.readString();
        this.f388b = parcel.readString();
        this.f389c = parcel.readByte() != 0;
        this.f390d = parcel.readString();
        this.f391e = parcel.readString();
        this.f392f = parcel.readString();
    }

    public String toString() {
        return "UCVerifyResultEntity{requestCode='" + this.f387a + '\'' + ", operateKey='" + this.f388b + '\'' + ", isSuccess=" + this.f389c + ", resultMessage='" + this.f390d + '\'' + ", ticketNo='" + this.f391e + '\'' + ", errorCode='" + this.f392f + '\'' + '}';
    }
}
