package com.coloros.deeptesting.activity;

import android.view.View;

/* renamed from: com.coloros.deeptesting.activity.b */
/* compiled from: ApplyActivity */
final class C0155b implements View.OnClickListener {

    /* renamed from: a */
    final /* synthetic */ ApplyActivity f313a;

    C0155b(ApplyActivity applyActivity) {
        this.f313a = applyActivity;
    }

    public final void onClick(View view) {
        boolean unused = this.f313a.f297d = !this.f313a.f297d;
        this.f313a.f295b.setEnabled(this.f313a.f297d);
    }
}
