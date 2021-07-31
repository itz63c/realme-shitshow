package com.tencent.bugly.crashreport.biz;

import android.os.Parcel;
import android.os.Parcelable;

/* renamed from: com.tencent.bugly.crashreport.biz.a */
/* compiled from: BUGLY */
final class C0251a implements Parcelable.Creator<UserInfoBean> {
    C0251a() {
    }

    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        return new UserInfoBean(parcel);
    }

    public final /* bridge */ /* synthetic */ Object[] newArray(int i) {
        return new UserInfoBean[i];
    }
}
