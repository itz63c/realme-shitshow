package com.coloros.deeptesting.activity;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import com.coloros.deeptesting.R;
import com.coloros.deeptesting.p007a.C0151h;

/* renamed from: com.coloros.deeptesting.activity.d */
/* compiled from: MainActivity */
final class C0157d extends Handler {

    /* renamed from: a */
    final /* synthetic */ MainActivity f315a;

    C0157d(MainActivity mainActivity) {
        this.f315a = mainActivity;
    }

    public final void handleMessage(Message message) {
        switch (this.f315a.f301b) {
            case 11:
                this.f315a.f304e.dismiss();
                Intent intent = new Intent(this.f315a, StatusActivity.class);
                intent.putExtra("resultCode", message.what);
                intent.putExtra("data", (String) message.obj);
                this.f315a.startActivity(intent);
                return;
            case 12:
                switch (message.what) {
                    case 0:
                        C0151h.m359b();
                        this.f315a.f300a = -1;
                        this.f315a.onStart();
                        return;
                    default:
                        C0151h.m355a(this.f315a, this.f315a.getString(R.string.dialog_title), this.f315a.getString(R.string.dialog_nonetwork), (Handler) null);
                        return;
                }
            default:
                this.f315a.f304e.dismiss();
                switch (message.what) {
                    case 0:
                        if (((Integer) message.obj).intValue() == 0) {
                            this.f315a.f300a = -2;
                            this.f315a.onStart();
                            return;
                        }
                        this.f315a.f300a = -1;
                        this.f315a.onStart();
                        return;
                    default:
                        C0151h.m355a(this.f315a, this.f315a.getString(R.string.dialog_title), this.f315a.getString(R.string.dialog_nonetwork), (Handler) null);
                        return;
                }
        }
    }
}
