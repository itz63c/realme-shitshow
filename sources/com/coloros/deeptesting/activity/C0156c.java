package com.coloros.deeptesting.activity;

import android.content.Context;
import android.os.Handler;
import android.view.View;
import com.coloros.deeptesting.R;
import com.coloros.deeptesting.p007a.C0151h;

/* renamed from: com.coloros.deeptesting.activity.c */
/* compiled from: ApplyActivity */
final class C0156c implements View.OnClickListener {

    /* renamed from: a */
    final /* synthetic */ ApplyActivity f314a;

    C0156c(ApplyActivity applyActivity) {
        this.f314a = applyActivity;
    }

    public final void onClick(View view) {
        if (!this.f314a.f297d) {
            return;
        }
        if (C0151h.m356a((Context) this.f314a)) {
            this.f314a.mo184a();
            C0151h.m354a(this.f314a, 1000, this.f314a.f299f);
            return;
        }
        C0151h.m355a(this.f314a, this.f314a.getString(R.string.dialog_title), this.f314a.getString(R.string.dialog_nonetwork), (Handler) null);
    }
}
