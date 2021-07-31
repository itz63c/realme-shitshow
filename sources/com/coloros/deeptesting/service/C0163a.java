package com.coloros.deeptesting.service;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.RemoteException;

/* renamed from: com.coloros.deeptesting.service.a */
/* compiled from: RequestService */
final class C0163a extends Handler {

    /* renamed from: a */
    final /* synthetic */ RequestService f327a;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    C0163a(RequestService requestService, Looper looper) {
        super(looper);
        this.f327a = requestService;
    }

    public final void handleMessage(Message message) {
        Message message2 = new Message();
        C0165c a = this.f327a.mo203a();
        if (a == null) {
            message2.what = 100;
        } else {
            message2.what = a.f334a;
            message2.obj = a.f335b;
            if (a.f336c != null) {
                if (a.f336c.f337a != null) {
                    message2.what = a.f334a + 1;
                    message2.obj = a.f336c.f337a;
                } else {
                    message2.what = a.f334a;
                    message2.obj = Integer.valueOf(a.f336c.f338b);
                }
            }
        }
        try {
            this.f327a.f325d.send(message2);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
