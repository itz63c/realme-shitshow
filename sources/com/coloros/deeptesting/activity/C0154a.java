package com.coloros.deeptesting.activity;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;

/* renamed from: com.coloros.deeptesting.activity.a */
/* compiled from: ApplyActivity */
final class C0154a extends Handler {

    /* renamed from: a */
    final /* synthetic */ ApplyActivity f312a;

    C0154a(ApplyActivity applyActivity) {
        this.f312a = applyActivity;
    }

    public final void handleMessage(Message message) {
        this.f312a.f298e.dismiss();
        Intent intent = new Intent(this.f312a, StatusActivity.class);
        intent.putExtra("resultCode", message.what);
        intent.putExtra("data", (String) message.obj);
        this.f312a.startActivity(intent);
        this.f312a.finish();
    }
}
