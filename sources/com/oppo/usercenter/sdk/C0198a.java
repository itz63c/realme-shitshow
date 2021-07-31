package com.oppo.usercenter.sdk;

import android.os.Handler;
import android.os.Message;
import com.nearme.aidl.UserEntity;
import com.oppo.usercenter.sdk.p013a.C0199a;
import java.lang.ref.WeakReference;

/* renamed from: com.oppo.usercenter.sdk.a */
/* compiled from: AccountAgent */
public final class C0198a {

    /* renamed from: a */
    private static WeakReference<Handler> f393a;

    /* renamed from: b */
    private static Handler f394b;

    /* renamed from: c */
    private static int f395c = -1;

    /* renamed from: a */
    public static void m458a(UserEntity userEntity) {
        if (f393a != null) {
            C0199a.m459a("mSingleAccountReqHandler.get() = " + f393a.get());
            Handler handler = (Handler) f393a.get();
            if (handler != null) {
                Message message = new Message();
                message.obj = userEntity;
                handler.sendMessage(message);
                f394b = null;
            }
        }
    }
}
