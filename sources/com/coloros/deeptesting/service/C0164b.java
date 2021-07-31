package com.coloros.deeptesting.service;

import android.content.Context;
import android.os.SystemProperties;
import com.coloros.deeptesting.p007a.C0151h;
import com.coloros.neton.BuildConfig;
import com.p000a.p001a.p002a.C0002b;

/* renamed from: com.coloros.deeptesting.service.b */
/* compiled from: RequestService */
public final class C0164b {
    @C0002b(mo5a = "pcb")

    /* renamed from: a */
    private String f328a;
    @C0002b(mo5a = "imei")

    /* renamed from: b */
    private String f329b;
    @C0002b(mo5a = "model")

    /* renamed from: c */
    private String f330c;
    @C0002b(mo5a = "otaVersion")

    /* renamed from: d */
    private String f331d;
    @C0002b(mo5a = "clientStatus")

    /* renamed from: e */
    private int f332e;
    @C0002b(mo5a = "adbDvice")

    /* renamed from: f */
    private String f333f;

    /* renamed from: a */
    public final void mo209a(Context context) {
        boolean z = true;
        this.f329b = C0151h.m362d(context);
        this.f330c = C0151h.m351a();
        String str = SystemProperties.get("ro.build.version.ota", BuildConfig.FLAVOR);
        if (SystemProperties.getInt("oppo.rus.debug.boot", 0) != 1) {
            z = false;
        }
        if (z && str != null && str.contains("PDTTest")) {
            str = str.replace("PDTTest", BuildConfig.FLAVOR);
        }
        this.f331d = str;
        this.f328a = SystemProperties.get("ro.vold.serialno", BuildConfig.FLAVOR);
        this.f332e = 0;
        this.f333f = SystemProperties.get("ro.rom.featrue", BuildConfig.FLAVOR);
    }
}
