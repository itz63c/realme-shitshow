package com.tencent.bugly.crashreport.biz;

import com.tencent.bugly.p014a.C0215ag;
import com.tencent.bugly.p014a.C0221am;
import java.util.List;

/* renamed from: com.tencent.bugly.crashreport.biz.c */
/* compiled from: BUGLY */
final class C0253c implements C0215ag {

    /* renamed from: a */
    private /* synthetic */ List f647a;

    /* renamed from: b */
    private /* synthetic */ C0252b f648b;

    C0253c(C0252b bVar, List list) {
        this.f648b = bVar;
        this.f647a = list;
    }

    /* renamed from: a */
    public final void mo365a(boolean z) {
        if (z) {
            C0221am.m597c("up success do final", new Object[0]);
            long currentTimeMillis = System.currentTimeMillis();
            for (UserInfoBean userInfoBean : this.f647a) {
                userInfoBean.f631f = currentTimeMillis;
                C0252b.m714a(userInfoBean);
            }
        }
    }
}
