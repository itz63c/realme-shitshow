package com.oppo.usercenter.sdk;

import android.os.Messenger;
import android.os.Parcel;
import android.os.Parcelable;

public class UCVerifyRequestEntity implements Parcelable {
    public static final Parcelable.Creator<UCVerifyRequestEntity> CREATOR = new C0202c();

    /* renamed from: a */
    public Messenger f383a;

    /* renamed from: b */
    public String f384b;

    /* renamed from: c */
    public String f385c;

    /* renamed from: d */
    public String f386d;

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeParcelable(this.f383a, i);
        parcel.writeString(this.f384b);
        parcel.writeString(this.f385c);
        parcel.writeString(this.f386d);
    }

    protected UCVerifyRequestEntity(Parcel parcel) {
        this.f383a = (Messenger) parcel.readParcelable(Messenger.class.getClassLoader());
        this.f384b = parcel.readString();
        this.f385c = parcel.readString();
        this.f386d = parcel.readString();
    }
}
