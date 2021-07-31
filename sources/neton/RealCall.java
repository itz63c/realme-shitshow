package neton;

import com.coloros.neton.BuildConfig;
import com.coloros.neton.NetonException;
import java.io.IOException;
import java.util.ArrayList;
import neton.internal.NamedRunnable;
import neton.internal.cache.CacheInterceptor;
import neton.internal.connection.ConnectInterceptor;
import neton.internal.connection.RealConnection;
import neton.internal.connection.StreamAllocation;
import neton.internal.http.BridgeInterceptor;
import neton.internal.http.CallServerInterceptor;
import neton.internal.http.HttpCodec;
import neton.internal.http.RealInterceptorChain;
import neton.internal.http.RetryAndFollowUpInterceptor;
import neton.internal.platform.Platform;

final class RealCall implements Call {
    final OkHttpClient client;
    /* access modifiers changed from: private */
    public EventListener eventListener;
    private boolean executed;
    final boolean forWebSocket;
    final Request originalRequest;
    final RetryAndFollowUpInterceptor retryAndFollowUpInterceptor;

    private RealCall(OkHttpClient okHttpClient, Request request, boolean z) {
        this.client = okHttpClient;
        this.originalRequest = request;
        this.forWebSocket = z;
        this.retryAndFollowUpInterceptor = new RetryAndFollowUpInterceptor(okHttpClient, z);
    }

    static RealCall newRealCall(OkHttpClient okHttpClient, Request request, boolean z) {
        RealCall realCall = new RealCall(okHttpClient, request, z);
        realCall.eventListener = okHttpClient.eventListenerFactory().create(realCall);
        return realCall;
    }

    public final Request request() {
        return this.originalRequest;
    }

    public final Response execute() {
        synchronized (this) {
            if (this.executed) {
                throw new NetonException((Throwable) new IllegalStateException("Already Executed"));
            }
            this.executed = true;
        }
        captureCallStackTrace();
        this.eventListener.fetchStart(this);
        try {
            if (this.originalRequest.requestStatistic() != null) {
                this.originalRequest.requestStatistic().setNetworkRequestStartTime(System.currentTimeMillis());
            }
            this.client.dispatcher().executed(this);
            Response responseWithInterceptorChain = getResponseWithInterceptorChain();
            if (responseWithInterceptorChain == null) {
                throw new IOException("Canceled");
            }
            this.eventListener.fetchEnd(this, (Throwable) null);
            this.client.dispatcher().finished(this);
            return responseWithInterceptorChain;
        } catch (IOException e) {
            this.eventListener.fetchEnd(this, e);
            throw e;
        } catch (Throwable th) {
            this.client.dispatcher().finished(this);
            throw th;
        }
    }

    private void captureCallStackTrace() {
        this.retryAndFollowUpInterceptor.setCallStackTrace(Platform.get().getStackTraceForCloseable("response.body().close()"));
    }

    public final void enqueue(Callback callback) {
        synchronized (this) {
            if (this.executed) {
                throw new IllegalStateException("Already Executed");
            }
            this.executed = true;
        }
        captureCallStackTrace();
        this.eventListener.fetchStart(this);
        this.client.dispatcher().enqueue(new AsyncCall(callback));
    }

    public final void cancel() {
        this.retryAndFollowUpInterceptor.cancel();
    }

    public final synchronized boolean isExecuted() {
        return this.executed;
    }

    public final boolean isCanceled() {
        return this.retryAndFollowUpInterceptor.isCanceled();
    }

    public final RealCall clone() {
        return newRealCall(this.client, this.originalRequest, this.forWebSocket);
    }

    /* access modifiers changed from: package-private */
    public final StreamAllocation streamAllocation() {
        return this.retryAndFollowUpInterceptor.streamAllocation();
    }

    final class AsyncCall extends NamedRunnable {
        private final Callback responseCallback;

        AsyncCall(Callback callback) {
            super("OkHttp %s", RealCall.this.redactedUrl());
            this.responseCallback = callback;
        }

        /* access modifiers changed from: package-private */
        public final String host() {
            return RealCall.this.originalRequest.url().host();
        }

        /* access modifiers changed from: package-private */
        public final Request request() {
            return RealCall.this.originalRequest;
        }

        /* access modifiers changed from: package-private */
        public final RealCall get() {
            return RealCall.this;
        }

        /* access modifiers changed from: protected */
        public final void execute() {
            boolean z = false;
            try {
                if (RealCall.this.originalRequest.requestStatistic() != null) {
                    RealCall.this.originalRequest.requestStatistic().setNetworkRequestStartTime(System.currentTimeMillis());
                }
                Response responseWithInterceptorChain = RealCall.this.getResponseWithInterceptorChain();
                if (RealCall.this.retryAndFollowUpInterceptor.isCanceled()) {
                    try {
                        this.responseCallback.onFailure(RealCall.this, new IOException("Canceled"));
                    } catch (IOException e) {
                        e = e;
                        z = true;
                    }
                } else {
                    this.responseCallback.onResponse(RealCall.this, responseWithInterceptorChain);
                }
                RealCall.this.eventListener.fetchEnd(RealCall.this, (Throwable) null);
                RealCall.this.client.dispatcher().finished(this);
            } catch (IOException e2) {
                e = e2;
                if (z) {
                    try {
                        Platform.get().log(4, "Callback failure for " + RealCall.this.toLoggableString(), e);
                    } catch (Throwable th) {
                        RealCall.this.client.dispatcher().finished(this);
                        throw th;
                    }
                } else {
                    RealCall.this.eventListener.fetchEnd(RealCall.this, e);
                    this.responseCallback.onFailure(RealCall.this, e);
                }
                RealCall.this.client.dispatcher().finished(this);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public final String toLoggableString() {
        return (isCanceled() ? "canceled " : BuildConfig.FLAVOR) + (this.forWebSocket ? "web socket" : "call") + " to " + redactedUrl();
    }

    /* access modifiers changed from: package-private */
    public final String redactedUrl() {
        return this.originalRequest.url().redact();
    }

    /* access modifiers changed from: package-private */
    public final Response getResponseWithInterceptorChain() {
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(this.client.interceptors());
        arrayList.add(this.retryAndFollowUpInterceptor);
        arrayList.add(new BridgeInterceptor(this.client.cookieJar()));
        arrayList.add(new CacheInterceptor(this.client.internalCache()));
        arrayList.add(new ConnectInterceptor(this.client));
        if (!this.forWebSocket) {
            arrayList.addAll(this.client.networkInterceptors());
        }
        arrayList.add(new CallServerInterceptor(this.forWebSocket));
        Timeout timeout = this.originalRequest.timeout() != null ? this.originalRequest.timeout() : this.client.timeout();
        return new RealInterceptorChain(arrayList, (StreamAllocation) null, (HttpCodec) null, (RealConnection) null, 0, this.originalRequest, this, this.eventListener, timeout.getConnectTimeout(), timeout.getReadTimeout(), timeout.getWriteTimeout()).proceed(this.originalRequest);
    }
}
