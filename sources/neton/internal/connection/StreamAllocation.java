package neton.internal.connection;

import com.coloros.neton.NetonException;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.net.Socket;
import neton.Address;
import neton.Call;
import neton.ConnectionPool;
import neton.EventListener;
import neton.Interceptor;
import neton.OkHttpClient;
import neton.Route;
import neton.internal.Internal;
import neton.internal.Util;
import neton.internal.connection.RouteSelector;
import neton.internal.http.HttpCodec;
import neton.internal.http2.ConnectionShutdownException;
import neton.internal.http2.ErrorCode;
import neton.internal.http2.StreamResetException;

public final class StreamAllocation {
    static final /* synthetic */ boolean $assertionsDisabled = (!StreamAllocation.class.desiredAssertionStatus());
    public final Address address;
    public final Call call;
    private final Object callStackTrace;
    private boolean canceled;
    private HttpCodec codec;
    private RealConnection connection;
    private final ConnectionPool connectionPool;
    public final EventListener eventListener;
    private int refusedStreamCount;
    private boolean released;
    private Route route;
    private RouteSelector.Selection routeSelection;
    private final RouteSelector routeSelector;

    public StreamAllocation(ConnectionPool connectionPool2, Address address2, Call call2, EventListener eventListener2, Object obj) {
        this.connectionPool = connectionPool2;
        this.address = address2;
        this.call = call2;
        this.eventListener = eventListener2;
        this.routeSelector = new RouteSelector(address2, routeDatabase(), call2, eventListener2);
        this.callStackTrace = obj;
    }

    public final HttpCodec newStream(OkHttpClient okHttpClient, Interceptor.Chain chain, boolean z) {
        try {
            HttpCodec newCodec = findHealthyConnection(chain.connectTimeoutMillis(), chain.readTimeoutMillis(), chain.writeTimeoutMillis(), okHttpClient.retryOnConnectionFailure(), z).newCodec(okHttpClient, chain, this);
            synchronized (this.connectionPool) {
                this.codec = newCodec;
            }
            return newCodec;
        } catch (IOException e) {
            throw new RouteException(e);
        }
    }

    private RealConnection findHealthyConnection(int i, int i2, int i3, boolean z, boolean z2) {
        RealConnection findConnection;
        while (true) {
            findConnection = findConnection(i, i2, i3, z);
            synchronized (this.connectionPool) {
                if (findConnection.successCount != 0) {
                    if (findConnection.isHealthy(z2)) {
                        break;
                    }
                    noNewStreams();
                } else {
                    break;
                }
            }
        }
        return findConnection;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:28:0x0053, code lost:
        if (r3 == false) goto L_0x0061;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x0055, code lost:
        r11.eventListener.connectionAcquired(r11.call, r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:0x0061, code lost:
        r0 = false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:0x0062, code lost:
        if (r2 != null) goto L_0x0079;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:0x0066, code lost:
        if (r11.routeSelection == null) goto L_0x0070;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:0x006e, code lost:
        if (r11.routeSelection.hasNext() != false) goto L_0x0079;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:0x0070, code lost:
        r0 = true;
        r11.routeSelection = r11.routeSelector.next();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:39:0x0079, code lost:
        r5 = r11.connectionPool;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:40:0x007b, code lost:
        monitor-enter(r5);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:43:0x007e, code lost:
        if (r11.canceled == false) goto L_0x008b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:45:0x0087, code lost:
        throw new java.io.IOException("Canceled");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:49:0x008b, code lost:
        if (r0 == false) goto L_0x011a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:51:?, code lost:
        r6 = r11.routeSelection.getAll();
        r7 = r6.size();
        r4 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:52:0x0099, code lost:
        if (r4 >= r7) goto L_0x011a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:53:0x009b, code lost:
        r0 = r6.get(r4);
        neton.internal.Internal.instance.get(r11.connectionPool, r11.address, r11, r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:54:0x00ac, code lost:
        if (r11.connection == null) goto L_0x0111;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:55:0x00ae, code lost:
        r3 = true;
        r1 = r11.connection;
        r11.route = r0;
        r0 = r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:56:0x00b4, code lost:
        if (r3 != false) goto L_0x00ce;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:57:0x00b6, code lost:
        if (r2 != null) goto L_0x0118;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:58:0x00b8, code lost:
        r1 = r11.routeSelection.next();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:59:0x00bf, code lost:
        r11.route = r1;
        r11.refusedStreamCount = 0;
        r0 = new neton.internal.connection.RealConnection(r11.connectionPool, r1);
        acquire(r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:60:0x00ce, code lost:
        monitor-exit(r5);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:61:0x00cf, code lost:
        r11.eventListener.connectionAcquired(r11.call, r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:62:0x00d6, code lost:
        if (r3 != false) goto L_?;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:63:0x00d8, code lost:
        r0.connect(r12, r13, r14, r15, r11.call, r11.eventListener);
        routeDatabase().connected(r0.route());
        r1 = null;
        r2 = r11.connectionPool;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:64:0x00f1, code lost:
        monitor-enter(r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:66:?, code lost:
        neton.internal.Internal.instance.put(r11.connectionPool, r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:67:0x00fd, code lost:
        if (r0.isMultiplexed() == false) goto L_0x010b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:68:0x00ff, code lost:
        r1 = neton.internal.Internal.instance.deduplicate(r11.connectionPool, r11.address, r11);
        r0 = r11.connection;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:69:0x010b, code lost:
        monitor-exit(r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:70:0x010c, code lost:
        neton.internal.Util.closeQuietly(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:71:0x0111, code lost:
        r4 = r4 + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:76:0x0118, code lost:
        r1 = r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:77:0x011a, code lost:
        r0 = r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:84:?, code lost:
        return r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:85:?, code lost:
        return r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:86:?, code lost:
        return r0;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private neton.internal.connection.RealConnection findConnection(int r12, int r13, int r14, boolean r15) {
        /*
            r11 = this;
            r3 = 0
            r1 = 0
            r2 = 0
            neton.ConnectionPool r4 = r11.connectionPool
            monitor-enter(r4)
            boolean r0 = r11.released     // Catch:{ all -> 0x0017 }
            if (r0 == 0) goto L_0x001a
            com.coloros.neton.NetonException r0 = new com.coloros.neton.NetonException     // Catch:{ all -> 0x0017 }
            java.lang.IllegalStateException r1 = new java.lang.IllegalStateException     // Catch:{ all -> 0x0017 }
            java.lang.String r2 = "released"
            r1.<init>(r2)     // Catch:{ all -> 0x0017 }
            r0.<init>((java.lang.Throwable) r1)     // Catch:{ all -> 0x0017 }
            throw r0     // Catch:{ all -> 0x0017 }
        L_0x0017:
            r0 = move-exception
            monitor-exit(r4)     // Catch:{ all -> 0x0017 }
            throw r0
        L_0x001a:
            neton.internal.http.HttpCodec r0 = r11.codec     // Catch:{ all -> 0x0017 }
            if (r0 == 0) goto L_0x002b
            com.coloros.neton.NetonException r0 = new com.coloros.neton.NetonException     // Catch:{ all -> 0x0017 }
            java.lang.IllegalStateException r1 = new java.lang.IllegalStateException     // Catch:{ all -> 0x0017 }
            java.lang.String r2 = "codec != null"
            r1.<init>(r2)     // Catch:{ all -> 0x0017 }
            r0.<init>((java.lang.Throwable) r1)     // Catch:{ all -> 0x0017 }
            throw r0     // Catch:{ all -> 0x0017 }
        L_0x002b:
            boolean r0 = r11.canceled     // Catch:{ all -> 0x0017 }
            if (r0 == 0) goto L_0x0037
            java.io.IOException r0 = new java.io.IOException     // Catch:{ all -> 0x0017 }
            java.lang.String r1 = "Canceled"
            r0.<init>(r1)     // Catch:{ all -> 0x0017 }
            throw r0     // Catch:{ all -> 0x0017 }
        L_0x0037:
            neton.internal.connection.RealConnection r0 = r11.connection     // Catch:{ all -> 0x0017 }
            if (r0 == 0) goto L_0x0041
            boolean r5 = r0.noNewStreams     // Catch:{ all -> 0x0017 }
            if (r5 != 0) goto L_0x0041
            monitor-exit(r4)     // Catch:{ all -> 0x0017 }
        L_0x0040:
            return r0
        L_0x0041:
            neton.internal.Internal r0 = neton.internal.Internal.instance     // Catch:{ all -> 0x0017 }
            neton.ConnectionPool r5 = r11.connectionPool     // Catch:{ all -> 0x0017 }
            neton.Address r6 = r11.address     // Catch:{ all -> 0x0017 }
            r7 = 0
            r0.get(r5, r6, r11, r7)     // Catch:{ all -> 0x0017 }
            neton.internal.connection.RealConnection r0 = r11.connection     // Catch:{ all -> 0x0017 }
            if (r0 == 0) goto L_0x005e
            r3 = 1
            neton.internal.connection.RealConnection r1 = r11.connection     // Catch:{ all -> 0x0017 }
        L_0x0052:
            monitor-exit(r4)     // Catch:{ all -> 0x0017 }
            if (r3 == 0) goto L_0x0061
            neton.EventListener r0 = r11.eventListener
            neton.Call r2 = r11.call
            r0.connectionAcquired(r2, r1)
            r0 = r1
            goto L_0x0040
        L_0x005e:
            neton.Route r2 = r11.route     // Catch:{ all -> 0x0017 }
            goto L_0x0052
        L_0x0061:
            r0 = 0
            if (r2 != 0) goto L_0x0079
            neton.internal.connection.RouteSelector$Selection r4 = r11.routeSelection
            if (r4 == 0) goto L_0x0070
            neton.internal.connection.RouteSelector$Selection r4 = r11.routeSelection
            boolean r4 = r4.hasNext()
            if (r4 != 0) goto L_0x0079
        L_0x0070:
            r0 = 1
            neton.internal.connection.RouteSelector r4 = r11.routeSelector
            neton.internal.connection.RouteSelector$Selection r4 = r4.next()
            r11.routeSelection = r4
        L_0x0079:
            neton.ConnectionPool r5 = r11.connectionPool
            monitor-enter(r5)
            boolean r4 = r11.canceled     // Catch:{ all -> 0x0088 }
            if (r4 == 0) goto L_0x008b
            java.io.IOException r0 = new java.io.IOException     // Catch:{ all -> 0x0088 }
            java.lang.String r1 = "Canceled"
            r0.<init>(r1)     // Catch:{ all -> 0x0088 }
            throw r0     // Catch:{ all -> 0x0088 }
        L_0x0088:
            r0 = move-exception
            monitor-exit(r5)     // Catch:{ all -> 0x0088 }
            throw r0
        L_0x008b:
            if (r0 == 0) goto L_0x011a
            neton.internal.connection.RouteSelector$Selection r0 = r11.routeSelection     // Catch:{ all -> 0x0088 }
            java.util.List r6 = r0.getAll()     // Catch:{ all -> 0x0088 }
            r0 = 0
            int r7 = r6.size()     // Catch:{ all -> 0x0088 }
            r4 = r0
        L_0x0099:
            if (r4 >= r7) goto L_0x011a
            java.lang.Object r0 = r6.get(r4)     // Catch:{ all -> 0x0088 }
            neton.Route r0 = (neton.Route) r0     // Catch:{ all -> 0x0088 }
            neton.internal.Internal r8 = neton.internal.Internal.instance     // Catch:{ all -> 0x0088 }
            neton.ConnectionPool r9 = r11.connectionPool     // Catch:{ all -> 0x0088 }
            neton.Address r10 = r11.address     // Catch:{ all -> 0x0088 }
            r8.get(r9, r10, r11, r0)     // Catch:{ all -> 0x0088 }
            neton.internal.connection.RealConnection r8 = r11.connection     // Catch:{ all -> 0x0088 }
            if (r8 == 0) goto L_0x0111
            r3 = 1
            neton.internal.connection.RealConnection r1 = r11.connection     // Catch:{ all -> 0x0088 }
            r11.route = r0     // Catch:{ all -> 0x0088 }
            r0 = r1
        L_0x00b4:
            if (r3 != 0) goto L_0x00ce
            if (r2 != 0) goto L_0x0118
            neton.internal.connection.RouteSelector$Selection r0 = r11.routeSelection     // Catch:{ all -> 0x0088 }
            neton.Route r0 = r0.next()     // Catch:{ all -> 0x0088 }
            r1 = r0
        L_0x00bf:
            r11.route = r1     // Catch:{ all -> 0x0088 }
            r0 = 0
            r11.refusedStreamCount = r0     // Catch:{ all -> 0x0088 }
            neton.internal.connection.RealConnection r0 = new neton.internal.connection.RealConnection     // Catch:{ all -> 0x0088 }
            neton.ConnectionPool r2 = r11.connectionPool     // Catch:{ all -> 0x0088 }
            r0.<init>(r2, r1)     // Catch:{ all -> 0x0088 }
            r11.acquire(r0)     // Catch:{ all -> 0x0088 }
        L_0x00ce:
            monitor-exit(r5)     // Catch:{ all -> 0x0088 }
            neton.EventListener r1 = r11.eventListener
            neton.Call r2 = r11.call
            r1.connectionAcquired(r2, r0)
            if (r3 != 0) goto L_0x0040
            neton.Call r5 = r11.call
            neton.EventListener r6 = r11.eventListener
            r1 = r12
            r2 = r13
            r3 = r14
            r4 = r15
            r0.connect(r1, r2, r3, r4, r5, r6)
            neton.internal.connection.RouteDatabase r1 = r11.routeDatabase()
            neton.Route r2 = r0.route()
            r1.connected(r2)
            r1 = 0
            neton.ConnectionPool r2 = r11.connectionPool
            monitor-enter(r2)
            neton.internal.Internal r3 = neton.internal.Internal.instance     // Catch:{ all -> 0x0115 }
            neton.ConnectionPool r4 = r11.connectionPool     // Catch:{ all -> 0x0115 }
            r3.put(r4, r0)     // Catch:{ all -> 0x0115 }
            boolean r3 = r0.isMultiplexed()     // Catch:{ all -> 0x0115 }
            if (r3 == 0) goto L_0x010b
            neton.internal.Internal r0 = neton.internal.Internal.instance     // Catch:{ all -> 0x0115 }
            neton.ConnectionPool r1 = r11.connectionPool     // Catch:{ all -> 0x0115 }
            neton.Address r3 = r11.address     // Catch:{ all -> 0x0115 }
            java.net.Socket r1 = r0.deduplicate(r1, r3, r11)     // Catch:{ all -> 0x0115 }
            neton.internal.connection.RealConnection r0 = r11.connection     // Catch:{ all -> 0x0115 }
        L_0x010b:
            monitor-exit(r2)     // Catch:{ all -> 0x0115 }
            neton.internal.Util.closeQuietly((java.net.Socket) r1)
            goto L_0x0040
        L_0x0111:
            int r0 = r4 + 1
            r4 = r0
            goto L_0x0099
        L_0x0115:
            r0 = move-exception
            monitor-exit(r2)     // Catch:{ all -> 0x0115 }
            throw r0
        L_0x0118:
            r1 = r2
            goto L_0x00bf
        L_0x011a:
            r0 = r1
            goto L_0x00b4
        */
        throw new UnsupportedOperationException("Method not decompiled: neton.internal.connection.StreamAllocation.findConnection(int, int, int, boolean):neton.internal.connection.RealConnection");
    }

    public final void streamFinished(boolean z, HttpCodec httpCodec) {
        RealConnection realConnection;
        Socket deallocate;
        synchronized (this.connectionPool) {
            if (httpCodec != null) {
                if (httpCodec == this.codec) {
                    if (!z) {
                        this.connection.successCount++;
                    }
                    realConnection = this.connection;
                    deallocate = deallocate(z, false, true);
                    if (this.connection != null) {
                        realConnection = null;
                    }
                }
            }
            throw new NetonException((Throwable) new IllegalStateException("expected " + this.codec + " but was " + httpCodec));
        }
        Util.closeQuietly(deallocate);
        if (realConnection != null) {
            this.eventListener.connectionReleased(this.call, realConnection);
        }
    }

    public final HttpCodec codec() {
        HttpCodec httpCodec;
        synchronized (this.connectionPool) {
            httpCodec = this.codec;
        }
        return httpCodec;
    }

    private RouteDatabase routeDatabase() {
        return Internal.instance.routeDatabase(this.connectionPool);
    }

    public final synchronized RealConnection connection() {
        return this.connection;
    }

    public final void release() {
        RealConnection realConnection;
        Socket deallocate;
        synchronized (this.connectionPool) {
            realConnection = this.connection;
            deallocate = deallocate(false, true, false);
            if (this.connection != null) {
                realConnection = null;
            }
        }
        Util.closeQuietly(deallocate);
        if (realConnection != null) {
            this.eventListener.connectionReleased(this.call, realConnection);
        }
    }

    public final void noNewStreams() {
        RealConnection realConnection;
        Socket deallocate;
        synchronized (this.connectionPool) {
            realConnection = this.connection;
            deallocate = deallocate(true, false, false);
            if (this.connection != null) {
                realConnection = null;
            }
        }
        Util.closeQuietly(deallocate);
        if (realConnection != null) {
            this.eventListener.connectionReleased(this.call, realConnection);
        }
    }

    private Socket deallocate(boolean z, boolean z2, boolean z3) {
        Socket socket;
        if ($assertionsDisabled || Thread.holdsLock(this.connectionPool)) {
            if (z3) {
                this.codec = null;
            }
            if (z2) {
                this.released = true;
            }
            if (this.connection == null) {
                return null;
            }
            if (z) {
                this.connection.noNewStreams = true;
            }
            if (this.codec != null) {
                return null;
            }
            if (!this.released && !this.connection.noNewStreams) {
                return null;
            }
            release(this.connection);
            if (this.connection.allocations.isEmpty()) {
                this.connection.idleAtNanos = System.nanoTime();
                if (Internal.instance.connectionBecameIdle(this.connectionPool, this.connection)) {
                    socket = this.connection.socket();
                    this.connection = null;
                    return socket;
                }
            }
            socket = null;
            this.connection = null;
            return socket;
        }
        throw new AssertionError();
    }

    public final void cancel() {
        HttpCodec httpCodec;
        RealConnection realConnection;
        synchronized (this.connectionPool) {
            this.canceled = true;
            httpCodec = this.codec;
            realConnection = this.connection;
        }
        if (httpCodec != null) {
            httpCodec.cancel();
        } else if (realConnection != null) {
            realConnection.cancel();
        }
    }

    public final void streamFailed(IOException iOException) {
        RealConnection realConnection;
        Socket deallocate;
        boolean z = false;
        boolean z2 = true;
        synchronized (this.connectionPool) {
            if (iOException instanceof StreamResetException) {
                StreamResetException streamResetException = (StreamResetException) iOException;
                if (streamResetException.errorCode == ErrorCode.REFUSED_STREAM) {
                    this.refusedStreamCount++;
                }
                if (streamResetException.errorCode != ErrorCode.REFUSED_STREAM || this.refusedStreamCount > 1) {
                    this.route = null;
                    z = true;
                }
                z2 = z;
            } else if (this.connection == null || (this.connection.isMultiplexed() && !(iOException instanceof ConnectionShutdownException))) {
                z2 = false;
            } else if (this.connection.successCount == 0) {
                if (!(this.route == null || iOException == null)) {
                    this.routeSelector.connectFailed(this.route, iOException);
                }
                this.route = null;
            }
            realConnection = this.connection;
            deallocate = deallocate(z2, false, true);
            if (this.connection != null) {
                realConnection = null;
            }
        }
        Util.closeQuietly(deallocate);
        if (realConnection != null) {
            this.eventListener.connectionReleased(this.call, realConnection);
        }
    }

    public final void acquire(RealConnection realConnection) {
        if (!$assertionsDisabled && !Thread.holdsLock(this.connectionPool)) {
            throw new AssertionError();
        } else if (this.connection != null) {
            throw new NetonException((Throwable) new IllegalStateException());
        } else {
            this.connection = realConnection;
            realConnection.allocations.add(new StreamAllocationReference(this, this.callStackTrace));
        }
    }

    private void release(RealConnection realConnection) {
        int size = realConnection.allocations.size();
        for (int i = 0; i < size; i++) {
            if (realConnection.allocations.get(i).get() == this) {
                realConnection.allocations.remove(i);
                return;
            }
        }
        throw new NetonException((Throwable) new IllegalStateException());
    }

    public final Socket releaseAndAcquire(RealConnection realConnection) {
        if (!$assertionsDisabled && !Thread.holdsLock(this.connectionPool)) {
            throw new AssertionError();
        } else if (this.codec == null && this.connection.allocations.size() == 1) {
            Socket deallocate = deallocate(true, false, false);
            this.connection = realConnection;
            realConnection.allocations.add(this.connection.allocations.get(0));
            return deallocate;
        } else {
            throw new NetonException((Throwable) new IllegalStateException());
        }
    }

    public final boolean hasMoreRoutes() {
        return this.route != null || (this.routeSelection != null && this.routeSelection.hasNext()) || this.routeSelector.hasNext();
    }

    public final String toString() {
        RealConnection connection2 = connection();
        return connection2 != null ? connection2.toString() : this.address.toString();
    }

    public final class StreamAllocationReference extends WeakReference<StreamAllocation> {
        public final Object callStackTrace;

        StreamAllocationReference(StreamAllocation streamAllocation, Object obj) {
            super(streamAllocation);
            this.callStackTrace = obj;
        }
    }
}
