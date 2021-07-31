package com.nearme.aidl;

import android.os.Parcel;
import android.os.Parcelable;

/* renamed from: com.nearme.aidl.a */
/* compiled from: UserEntity */
final class C0196a implements Parcelable.Creator<UserEntity> {
    C0196a() {
    }

    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        int readInt = parcel.readInt();
        String readString = parcel.readString();
        String readString2 = parcel.readString();
        String readString3 = parcel.readString();
        UserEntity userEntity = new UserEntity();
        userEntity.mo327a(readInt);
        userEntity.mo331c(readString);
        userEntity.mo328a(readString2);
        userEntity.mo330b(readString3);
        return userEntity;
    }

    public final /* bridge */ /* synthetic */ Object[] newArray(int i) {
        return new UserEntity[i];
    }
}
