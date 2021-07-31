package neton.internal.http2;

import com.coloros.neton.NetonException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

final class Ping {
    private final CountDownLatch latch = new CountDownLatch(1);
    private long received = -1;
    private long sent = -1;

    Ping() {
    }

    /* access modifiers changed from: package-private */
    public final void send() {
        if (this.sent != -1) {
            throw new NetonException((Throwable) new IllegalStateException());
        }
        this.sent = System.nanoTime();
    }

    /* access modifiers changed from: package-private */
    public final void receive() {
        if (this.received != -1 || this.sent == -1) {
            throw new NetonException((Throwable) new IllegalStateException());
        }
        this.received = System.nanoTime();
        this.latch.countDown();
    }

    /* access modifiers changed from: package-private */
    public final void cancel() {
        if (this.received != -1 || this.sent == -1) {
            throw new NetonException((Throwable) new IllegalStateException());
        }
        this.received = this.sent - 1;
        this.latch.countDown();
    }

    public final long roundTripTime() {
        this.latch.await();
        return this.received - this.sent;
    }

    public final long roundTripTime(long j, TimeUnit timeUnit) {
        if (this.latch.await(j, timeUnit)) {
            return this.received - this.sent;
        }
        return -2;
    }
}
