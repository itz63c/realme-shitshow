package com.coloros.deeptesting.activity;

import android.content.Context;
import android.os.Handler;
import android.view.View;
import com.coloros.deeptesting.R;
import com.coloros.deeptesting.p007a.C0151h;

/* renamed from: com.coloros.deeptesting.activity.f */
/* compiled from: MainActivity */
final class C0159f implements View.OnClickListener {

    /* renamed from: a */
    final /* synthetic */ MainActivity f317a;

    C0159f(MainActivity mainActivity) {
        this.f317a = mainActivity;
    }

    public final void onClick(View view) {
        if (C0151h.m356a((Context) this.f317a)) {
            this.f317a.mo187a();
            int unused = this.f317a.f301b = 11;
            C0151h.m354a(this.f317a, 1001, this.f317a.f305f);
            return;
        }
        C0151h.m355a(this.f317a, this.f317a.getString(R.string.dialog_title), this.f317a.getString(R.string.dialog_nonetwork), (Handler) null);
    }
}
