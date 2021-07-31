package com.tencent.bugly.crashreport.common.info;

import android.os.Parcel;
import android.os.Parcelable;

/* renamed from: com.tencent.bugly.crashreport.common.info.b */
/* compiled from: BUGLY */
final class C0256b implements Parcelable.Creator<PlugInBean> {
    C0256b() {
    }

    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        return new PlugInBean(parcel);
    }

    public final /* bridge */ /* synthetic */ Object[] newArray(int i) {
        return new PlugInBean[i];
    }
}
