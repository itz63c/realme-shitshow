package com.coloros.deeptesting.activity;

import android.content.Intent;
import android.os.Handler;
import android.view.View;
import com.coloros.deeptesting.R;
import com.coloros.deeptesting.p007a.C0151h;

/* renamed from: com.coloros.deeptesting.activity.e */
/* compiled from: MainActivity */
final class C0158e implements View.OnClickListener {

    /* renamed from: a */
    final /* synthetic */ MainActivity f316a;

    C0158e(MainActivity mainActivity) {
        this.f316a = mainActivity;
    }

    public final void onClick(View view) {
        switch (this.f316a.f300a) {
            case -2:
                int unused = this.f316a.f301b = 12;
                C0151h.m355a(this.f316a, this.f316a.getString(R.string.dialog_exittest), this.f316a.getString(R.string.dialog_locktext), this.f316a.f305f);
                return;
            case -1:
                this.f316a.startActivity(new Intent(this.f316a, ApplyActivity.class));
                return;
            case 0:
                C0151h.m355a(this.f316a, this.f316a.getString(R.string.dialog_exittest), this.f316a.getString(R.string.dialog_unlocktext), (Handler) null);
                return;
            default:
                this.f316a.m373b();
                return;
        }
    }
}
