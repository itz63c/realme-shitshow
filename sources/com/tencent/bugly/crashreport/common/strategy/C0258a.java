package com.tencent.bugly.crashreport.common.strategy;

import android.os.Parcel;
import android.os.Parcelable;

/* renamed from: com.tencent.bugly.crashreport.common.strategy.a */
/* compiled from: BUGLY */
final class C0258a implements Parcelable.Creator<StrategyBean> {
    C0258a() {
    }

    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        return new StrategyBean(parcel);
    }

    public final /* bridge */ /* synthetic */ Object[] newArray(int i) {
        return new StrategyBean[i];
    }
}
