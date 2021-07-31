package com.tencent.bugly.p014a;

import android.util.Log;
import com.tencent.bugly.C0250b;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* renamed from: com.tencent.bugly.a.ak */
/* compiled from: BUGLY */
public final class C0219ak {

    /* renamed from: a */
    private static C0219ak f457a;

    /* renamed from: b */
    private ScheduledExecutorService f458b = null;

    /* renamed from: c */
    private ThreadPoolExecutor f459c = null;

    /* renamed from: d */
    private ThreadPoolExecutor f460d = null;

    protected C0219ak() {
        C0220al alVar = new C0220al();
        this.f458b = Executors.newScheduledThreadPool(3, alVar);
        this.f459c = new ThreadPoolExecutor(1, 1, 0, TimeUnit.SECONDS, new LinkedBlockingQueue(100), alVar);
        this.f460d = new ThreadPoolExecutor(2, Integer.MAX_VALUE, 10, TimeUnit.SECONDS, new LinkedBlockingQueue(), alVar);
        if (this.f458b == null || this.f458b.isShutdown()) {
            throw new IllegalArgumentException("ScheduledExecutorService is not valiable!");
        } else if (this.f459c == null || this.f459c.isShutdown()) {
            throw new IllegalArgumentException("QueueExecutorService is not valiable!");
        } else if (this.f460d == null || this.f460d.isShutdown()) {
            throw new IllegalArgumentException("ploadExecutorService is not valiable!");
        }
    }

    /* renamed from: a */
    public static synchronized C0219ak m587a() {
        C0219ak akVar;
        synchronized (C0219ak.class) {
            if (f457a == null) {
                f457a = new C0219ak();
            }
            akVar = f457a;
        }
        return akVar;
    }

    /* renamed from: a */
    public final synchronized boolean mo381a(Runnable runnable) {
        boolean z;
        if (!m588b()) {
            if (C0250b.f618a) {
                Log.w(C0221am.f462b, "queue handler was closed , should not post task!");
            }
            z = false;
        } else {
            try {
                this.f459c.submit(runnable);
            } catch (Throwable th) {
                if (C0250b.f618a) {
                    th.printStackTrace();
                }
            }
            z = true;
        }
        return z;
    }

    /* renamed from: b */
    public final synchronized boolean mo382b(Runnable runnable) {
        boolean z = false;
        synchronized (this) {
            if (!m588b()) {
                C0221am.m598d("async handler was closed , should not post task!", new Object[0]);
            } else {
                C0221am.m597c("normal task %s", runnable.getClass().getName());
                try {
                    this.f458b.execute(runnable);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                z = true;
            }
        }
        return z;
    }

    /* renamed from: c */
    public final synchronized boolean mo383c(Runnable runnable) {
        boolean z = false;
        synchronized (this) {
            if (!m588b()) {
                if (C0250b.f618a) {
                    Log.w(C0221am.f462b, "queue handler was closed , should not post task!");
                }
            } else if (runnable != null) {
                try {
                    this.f460d.submit(runnable);
                } catch (Throwable th) {
                    if (C0250b.f618a) {
                        th.printStackTrace();
                    }
                }
                z = true;
            } else if (C0250b.f618a) {
                Log.w(C0221am.f462b, "queue task is null");
            }
        }
        return z;
    }

    /* renamed from: b */
    private synchronized boolean m588b() {
        return this.f458b != null && !this.f458b.isShutdown() && this.f459c != null && !this.f459c.isShutdown() && this.f460d != null && !this.f460d.isShutdown();
    }
}
