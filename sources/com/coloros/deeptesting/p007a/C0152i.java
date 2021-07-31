package com.coloros.deeptesting.p007a;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Handler;

/* renamed from: com.coloros.deeptesting.a.i */
/* compiled from: Utils */
final class C0152i implements DialogInterface.OnClickListener {

    /* renamed from: a */
    final /* synthetic */ Handler f292a;

    /* renamed from: b */
    final /* synthetic */ Context f293b;

    C0152i(Handler handler, Context context) {
        this.f292a = handler;
        this.f293b = context;
    }

    public final void onClick(DialogInterface dialogInterface, int i) {
        if (this.f292a != null) {
            C0151h.m354a(this.f293b, 1004, this.f292a);
        }
    }
}
