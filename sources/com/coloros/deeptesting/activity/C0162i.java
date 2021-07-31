package com.coloros.deeptesting.activity;

import android.content.Context;
import android.os.Handler;
import android.view.View;
import com.coloros.deeptesting.R;
import com.coloros.deeptesting.p007a.C0151h;

/* renamed from: com.coloros.deeptesting.activity.i */
/* compiled from: StatusActivity */
final class C0162i implements View.OnClickListener {

    /* renamed from: a */
    final /* synthetic */ StatusActivity f320a;

    C0162i(StatusActivity statusActivity) {
        this.f320a = statusActivity;
    }

    public final void onClick(View view) {
        if (!C0151h.m361c(this.f320a) || !C0151h.m351a().startsWith("RMX")) {
            this.f320a.finish();
        } else if (!C0151h.m356a((Context) this.f320a)) {
            C0151h.m355a(this.f320a, this.f320a.getString(R.string.dialog_title), this.f320a.getString(R.string.dialog_nonetwork), (Handler) null);
        } else {
            this.f320a.mo190a();
            C0151h.m354a(this.f320a, 1002, this.f320a.f311f);
        }
    }
}
