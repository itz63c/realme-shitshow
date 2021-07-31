package com.tencent.bugly.crashreport.crash;

import android.os.Parcel;
import android.os.Parcelable;

/* renamed from: com.tencent.bugly.crashreport.crash.b */
/* compiled from: BUGLY */
final class C0263b implements Parcelable.Creator<CrashDetailBean> {
    C0263b() {
    }

    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        return new CrashDetailBean(parcel);
    }

    public final /* bridge */ /* synthetic */ Object[] newArray(int i) {
        return new CrashDetailBean[i];
    }
}
