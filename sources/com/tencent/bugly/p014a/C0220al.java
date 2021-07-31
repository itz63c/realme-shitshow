package com.tencent.bugly.p014a;

import java.util.concurrent.ThreadFactory;

/* renamed from: com.tencent.bugly.a.al */
/* compiled from: BUGLY */
final class C0220al implements ThreadFactory {
    C0220al() {
    }

    public final Thread newThread(Runnable runnable) {
        Thread thread = new Thread(runnable);
        thread.setName("BUGLY_THREAD");
        return thread;
    }
}
