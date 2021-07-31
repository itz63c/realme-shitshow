package com.coloros.deeptesting.activity;

import android.os.Handler;
import android.os.Message;
import android.service.persistentdata.PersistentDataBlockManager;
import com.coloros.deeptesting.R;
import com.coloros.deeptesting.p007a.C0150g;
import com.coloros.deeptesting.p007a.C0151h;

/* renamed from: com.coloros.deeptesting.activity.g */
/* compiled from: StatusActivity */
final class C0160g extends Handler {

    /* renamed from: a */
    final /* synthetic */ StatusActivity f318a;

    C0160g(StatusActivity statusActivity) {
        this.f318a = statusActivity;
    }

    public final void handleMessage(Message message) {
        switch (message.what) {
            case 0:
                this.f318a.f310e.dismiss();
                if (!C0151h.m361c(this.f318a) || !C0151h.m351a().startsWith("RMX")) {
                    this.f318a.finish();
                    return;
                }
                C0150g.m348a("Status", "result:" + C0151h.m357a(this.f318a.getIntent().getStringExtra("data")));
                try {
                    ((PersistentDataBlockManager) this.f318a.getSystemService("persistent_data_block")).setOemUnlockEnabled(true);
                    C0150g.m348a("Utils", "Set oem");
                } catch (SecurityException e) {
                    C0150g.m348a("Utils", "Fail to set oem");
                }
                C0151h.m360c();
                return;
            default:
                this.f318a.f310e.dismiss();
                C0151h.m355a(this.f318a, this.f318a.getString(R.string.dialog_title), this.f318a.getString(R.string.dialog_nonetwork), (Handler) null);
                return;
        }
    }
}
