package neton.internal.p017ws;

import com.coloros.neton.NetonException;
import java.io.Closeable;
import java.io.IOException;
import java.net.ProtocolException;
import java.util.ArrayDeque;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import neton.Call;
import neton.Callback;
import neton.OkHttpClient;
import neton.Protocol;
import neton.Request;
import neton.Response;
import neton.WebSocket;
import neton.WebSocketListener;
import neton.internal.Internal;
import neton.internal.Util;
import neton.internal.connection.StreamAllocation;
import neton.internal.http.HttpMethod;
import neton.internal.p017ws.WebSocketReader;
import okio.BufferedSink;
import okio.BufferedSource;
import okio.ByteString;

/* renamed from: neton.internal.ws.RealWebSocket */
public final class RealWebSocket implements WebSocket, WebSocketReader.FrameCallback {
    static final /* synthetic */ boolean $assertionsDisabled = (!RealWebSocket.class.desiredAssertionStatus());
    private static final long CANCEL_AFTER_CLOSE_MILLIS = 60000;
    private static final long MAX_QUEUE_SIZE = 16777216;
    private static final List<Protocol> ONLY_HTTP1 = Collections.singletonList(Protocol.HTTP_1_1);
    private Call call;
    private ScheduledFuture<?> cancelFuture;
    private boolean enqueuedClose;
    private ScheduledExecutorService executor;
    private boolean failed;
    private final String key;
    final WebSocketListener listener;
    private final ArrayDeque<Object> messageAndCloseQueue = new ArrayDeque<>();
    private final Request originalRequest;
    int pingCount;
    int pongCount;
    private final ArrayDeque<ByteString> pongQueue = new ArrayDeque<>();
    private long queueSize;
    private final Random random;
    private WebSocketReader reader;
    private int receivedCloseCode = -1;
    private String receivedCloseReason;
    private Streams streams;
    private WebSocketWriter writer;
    private final Runnable writerRunnable;

    public RealWebSocket(Request request, WebSocketListener webSocketListener, Random random2) {
        if (!HttpMethod.GET.equals(request.method())) {
            throw new NetonException((Throwable) new IllegalArgumentException("Request must be GET: " + request.method()));
        }
        this.originalRequest = request;
        this.listener = webSocketListener;
        this.random = random2;
        byte[] bArr = new byte[16];
        random2.nextBytes(bArr);
        this.key = ByteString.m801of(bArr).base64();
        this.writerRunnable = new Runnable() {
            public void run() {
                do {
                    try {
                    } catch (IOException e) {
                        RealWebSocket.this.failWebSocket(e, (Response) null);
                        return;
                    }
                } while (RealWebSocket.this.writeOneFrame());
            }
        };
    }

    public final Request request() {
        return this.originalRequest;
    }

    public final synchronized long queueSize() {
        return this.queueSize;
    }

    public final void cancel() {
        this.call.cancel();
    }

    public final void connect(OkHttpClient okHttpClient) {
        OkHttpClient build = okHttpClient.newBuilder().protocols(ONLY_HTTP1).build();
        final int pingIntervalMillis = build.pingIntervalMillis();
        final Request build2 = this.originalRequest.newBuilder().header("Upgrade", "websocket").header("Connection", "Upgrade").header("Sec-WebSocket-Key", this.key).header("Sec-WebSocket-Version", "13").build();
        this.call = Internal.instance.newWebSocketCall(build, build2);
        this.call.enqueue(new Callback() {
            public void onResponse(Call call, Response response) {
                try {
                    RealWebSocket.this.checkResponse(response);
                    StreamAllocation streamAllocation = Internal.instance.streamAllocation(call);
                    streamAllocation.noNewStreams();
                    Streams newWebSocketStreams = streamAllocation.connection().newWebSocketStreams(streamAllocation);
                    try {
                        RealWebSocket.this.listener.onOpen(RealWebSocket.this, response);
                        RealWebSocket.this.initReaderAndWriter("OkHttp WebSocket " + build2.url().redact(), (long) pingIntervalMillis, newWebSocketStreams);
                        streamAllocation.connection().socket().setSoTimeout(0);
                        RealWebSocket.this.loopReader();
                    } catch (Exception e) {
                        RealWebSocket.this.failWebSocket(e, (Response) null);
                    }
                } catch (ProtocolException e2) {
                    RealWebSocket.this.failWebSocket(e2, response);
                    Util.closeQuietly((Closeable) response);
                }
            }

            public void onFailure(Call call, IOException iOException) {
                RealWebSocket.this.failWebSocket(iOException, (Response) null);
            }
        });
    }

    /* access modifiers changed from: package-private */
    public final void checkResponse(Response response) {
        if (response.code() != 101) {
            throw new ProtocolException("Expected HTTP 101 response but was '" + response.code() + " " + response.message() + "'");
        }
        String header = response.header("Connection");
        if (!"Upgrade".equalsIgnoreCase(header)) {
            throw new ProtocolException("Expected 'Connection' header value 'Upgrade' but was '" + header + "'");
        }
        String header2 = response.header("Upgrade");
        if (!"websocket".equalsIgnoreCase(header2)) {
            throw new ProtocolException("Expected 'Upgrade' header value 'websocket' but was '" + header2 + "'");
        }
        String header3 = response.header("Sec-WebSocket-Accept");
        String base64 = ByteString.encodeUtf8(this.key + "258EAFA5-E914-47DA-95CA-C5AB0DC85B11").sha1().base64();
        if (!base64.equals(header3)) {
            throw new ProtocolException("Expected 'Sec-WebSocket-Accept' header value '" + base64 + "' but was '" + header3 + "'");
        }
    }

    public final void initReaderAndWriter(String str, long j, Streams streams2) {
        synchronized (this) {
            this.streams = streams2;
            this.writer = new WebSocketWriter(streams2.client, streams2.sink, this.random);
            this.executor = new ScheduledThreadPoolExecutor(1, Util.threadFactory(str, false));
            if (j != 0) {
                this.executor.scheduleAtFixedRate(new PingRunnable(), j, j, TimeUnit.MILLISECONDS);
            }
            if (!this.messageAndCloseQueue.isEmpty()) {
                runWriter();
            }
        }
        this.reader = new WebSocketReader(streams2.client, streams2.source, this);
    }

    public final void loopReader() {
        while (this.receivedCloseCode == -1) {
            this.reader.processNextFrame();
        }
    }

    /* access modifiers changed from: package-private */
    public final boolean processNextFrame() {
        try {
            this.reader.processNextFrame();
            if (this.receivedCloseCode == -1) {
                return true;
            }
            return false;
        } catch (Exception e) {
            failWebSocket(e, (Response) null);
            return false;
        }
    }

    /* access modifiers changed from: package-private */
    public final void awaitTermination(int i, TimeUnit timeUnit) {
        this.executor.awaitTermination((long) i, timeUnit);
    }

    /* access modifiers changed from: package-private */
    public final void tearDown() {
        if (this.cancelFuture != null) {
            this.cancelFuture.cancel(false);
        }
        this.executor.shutdown();
        this.executor.awaitTermination(10, TimeUnit.SECONDS);
    }

    /* access modifiers changed from: package-private */
    public final synchronized int pingCount() {
        return this.pingCount;
    }

    /* access modifiers changed from: package-private */
    public final synchronized int pongCount() {
        return this.pongCount;
    }

    public final void onReadMessage(String str) {
        this.listener.onMessage((WebSocket) this, str);
    }

    public final void onReadMessage(ByteString byteString) {
        this.listener.onMessage((WebSocket) this, byteString);
    }

    public final synchronized void onReadPing(ByteString byteString) {
        if (!this.failed && (!this.enqueuedClose || !this.messageAndCloseQueue.isEmpty())) {
            this.pongQueue.add(byteString);
            runWriter();
            this.pingCount++;
        }
    }

    public final synchronized void onReadPong(ByteString byteString) {
        this.pongCount++;
    }

    public final void onReadClose(int i, String str) {
        Streams streams2;
        if (i == -1) {
            throw new NetonException((Throwable) new IllegalArgumentException());
        }
        synchronized (this) {
            if (this.receivedCloseCode != -1) {
                throw new NetonException((Throwable) new IllegalStateException("already closed"));
            }
            this.receivedCloseCode = i;
            this.receivedCloseReason = str;
            if (!this.enqueuedClose || !this.messageAndCloseQueue.isEmpty()) {
                streams2 = null;
            } else {
                Streams streams3 = this.streams;
                this.streams = null;
                if (this.cancelFuture != null) {
                    this.cancelFuture.cancel(false);
                }
                this.executor.shutdown();
                streams2 = streams3;
            }
        }
        try {
            this.listener.onClosing(this, i, str);
            if (streams2 != null) {
                this.listener.onClosed(this, i, str);
            }
        } finally {
            Util.closeQuietly((Closeable) streams2);
        }
    }

    public final boolean send(String str) {
        if (str != null) {
            return send(ByteString.encodeUtf8(str), 1);
        }
        throw new NetonException((Throwable) new NullPointerException("text == null"));
    }

    public final boolean send(ByteString byteString) {
        if (byteString != null) {
            return send(byteString, 2);
        }
        throw new NetonException((Throwable) new NullPointerException("bytes == null"));
    }

    private synchronized boolean send(ByteString byteString, int i) {
        boolean z = false;
        synchronized (this) {
            if (!this.failed && !this.enqueuedClose) {
                if (this.queueSize + ((long) byteString.size()) > MAX_QUEUE_SIZE) {
                    close(1001, (String) null);
                } else {
                    this.queueSize += (long) byteString.size();
                    this.messageAndCloseQueue.add(new Message(i, byteString));
                    runWriter();
                    z = true;
                }
            }
        }
        return z;
    }

    /* access modifiers changed from: package-private */
    public final synchronized boolean pong(ByteString byteString) {
        boolean z;
        if (this.failed || (this.enqueuedClose && this.messageAndCloseQueue.isEmpty())) {
            z = false;
        } else {
            this.pongQueue.add(byteString);
            runWriter();
            z = true;
        }
        return z;
    }

    public final boolean close(int i, String str) {
        return close(i, str, CANCEL_AFTER_CLOSE_MILLIS);
    }

    /* access modifiers changed from: package-private */
    public final synchronized boolean close(int i, String str, long j) {
        boolean z = true;
        synchronized (this) {
            WebSocketProtocol.validateCloseCode(i);
            ByteString byteString = null;
            if (str != null) {
                byteString = ByteString.encodeUtf8(str);
                if (((long) byteString.size()) > 123) {
                    throw new NetonException((Throwable) new IllegalArgumentException("reason.size() > 123: " + str));
                }
            }
            if (this.failed || this.enqueuedClose) {
                z = false;
            } else {
                this.enqueuedClose = true;
                this.messageAndCloseQueue.add(new Close(i, byteString, j));
                runWriter();
            }
        }
        return z;
    }

    private void runWriter() {
        if (!$assertionsDisabled && !Thread.holdsLock(this)) {
            throw new AssertionError();
        } else if (this.executor != null) {
            this.executor.execute(this.writerRunnable);
        }
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0034, code lost:
        if (r2 == null) goto L_0x005e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:?, code lost:
        r10.writePong(r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0039, code lost:
        neton.internal.Util.closeQuietly((java.io.Closeable) r6);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x0060, code lost:
        if ((r3 instanceof neton.internal.p017ws.RealWebSocket.Message) == false) goto L_0x0095;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:0x0062, code lost:
        r2 = ((neton.internal.p017ws.RealWebSocket.Message) r3).data;
        r3 = okio.Okio.buffer(r10.newMessageSink(((neton.internal.p017ws.RealWebSocket.Message) r3).formatOpcode, (long) r2.size()));
        r3.write(r2);
        r3.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:0x007f, code lost:
        monitor-enter(r14);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:?, code lost:
        r14.queueSize -= (long) r2.size();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:34:0x008b, code lost:
        monitor-exit(r14);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:40:0x0090, code lost:
        r2 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:41:0x0091, code lost:
        neton.internal.Util.closeQuietly((java.io.Closeable) r6);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:42:0x0094, code lost:
        throw r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:45:0x0097, code lost:
        if ((r3 instanceof neton.internal.p017ws.RealWebSocket.Close) == false) goto L_0x00aa;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:46:0x0099, code lost:
        r3 = (neton.internal.p017ws.RealWebSocket.Close) r3;
        r10.writeClose(r3.code, r3.reason);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:47:0x00a2, code lost:
        if (r6 == null) goto L_0x0039;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:48:0x00a4, code lost:
        r14.listener.onClosed(r14, r9, r8);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:50:0x00af, code lost:
        throw new java.lang.AssertionError();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:54:?, code lost:
        return true;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean writeOneFrame() {
        /*
            r14 = this;
            r5 = 0
            r7 = 0
            monitor-enter(r14)
            boolean r2 = r14.failed     // Catch:{ all -> 0x005b }
            if (r2 == 0) goto L_0x000a
            monitor-exit(r14)     // Catch:{ all -> 0x005b }
            r2 = r5
        L_0x0009:
            return r2
        L_0x000a:
            neton.internal.ws.WebSocketWriter r10 = r14.writer     // Catch:{ all -> 0x005b }
            java.util.ArrayDeque<okio.ByteString> r2 = r14.pongQueue     // Catch:{ all -> 0x005b }
            java.lang.Object r2 = r2.poll()     // Catch:{ all -> 0x005b }
            okio.ByteString r2 = (okio.ByteString) r2     // Catch:{ all -> 0x005b }
            if (r2 != 0) goto L_0x00b6
            java.util.ArrayDeque<java.lang.Object> r3 = r14.messageAndCloseQueue     // Catch:{ all -> 0x005b }
            java.lang.Object r4 = r3.poll()     // Catch:{ all -> 0x005b }
            boolean r3 = r4 instanceof neton.internal.p017ws.RealWebSocket.Close     // Catch:{ all -> 0x005b }
            if (r3 == 0) goto L_0x0056
            int r9 = r14.receivedCloseCode     // Catch:{ all -> 0x005b }
            java.lang.String r8 = r14.receivedCloseReason     // Catch:{ all -> 0x005b }
            r3 = -1
            if (r9 == r3) goto L_0x003e
            neton.internal.ws.RealWebSocket$Streams r5 = r14.streams     // Catch:{ all -> 0x005b }
            r3 = 0
            r14.streams = r3     // Catch:{ all -> 0x005b }
            java.util.concurrent.ScheduledExecutorService r3 = r14.executor     // Catch:{ all -> 0x005b }
            r3.shutdown()     // Catch:{ all -> 0x005b }
            r6 = r5
            r3 = r4
        L_0x0033:
            monitor-exit(r14)     // Catch:{ all -> 0x005b }
            if (r2 == 0) goto L_0x005e
            r10.writePong(r2)     // Catch:{ all -> 0x0090 }
        L_0x0039:
            neton.internal.Util.closeQuietly((java.io.Closeable) r6)
            r2 = 1
            goto L_0x0009
        L_0x003e:
            java.util.concurrent.ScheduledExecutorService r5 = r14.executor     // Catch:{ all -> 0x005b }
            neton.internal.ws.RealWebSocket$CancelRunnable r6 = new neton.internal.ws.RealWebSocket$CancelRunnable     // Catch:{ all -> 0x005b }
            r6.<init>()     // Catch:{ all -> 0x005b }
            r0 = r4
            neton.internal.ws.RealWebSocket$Close r0 = (neton.internal.p017ws.RealWebSocket.Close) r0     // Catch:{ all -> 0x005b }
            r3 = r0
            long r12 = r3.cancelAfterCloseMillis     // Catch:{ all -> 0x005b }
            java.util.concurrent.TimeUnit r3 = java.util.concurrent.TimeUnit.MILLISECONDS     // Catch:{ all -> 0x005b }
            java.util.concurrent.ScheduledFuture r3 = r5.schedule(r6, r12, r3)     // Catch:{ all -> 0x005b }
            r14.cancelFuture = r3     // Catch:{ all -> 0x005b }
            r6 = r7
            r3 = r4
            goto L_0x0033
        L_0x0056:
            if (r4 != 0) goto L_0x00b0
            monitor-exit(r14)     // Catch:{ all -> 0x005b }
            r2 = r5
            goto L_0x0009
        L_0x005b:
            r2 = move-exception
            monitor-exit(r14)     // Catch:{ all -> 0x005b }
            throw r2
        L_0x005e:
            boolean r2 = r3 instanceof neton.internal.p017ws.RealWebSocket.Message     // Catch:{ all -> 0x0090 }
            if (r2 == 0) goto L_0x0095
            r0 = r3
            neton.internal.ws.RealWebSocket$Message r0 = (neton.internal.p017ws.RealWebSocket.Message) r0     // Catch:{ all -> 0x0090 }
            r2 = r0
            okio.ByteString r2 = r2.data     // Catch:{ all -> 0x0090 }
            neton.internal.ws.RealWebSocket$Message r3 = (neton.internal.p017ws.RealWebSocket.Message) r3     // Catch:{ all -> 0x0090 }
            int r3 = r3.formatOpcode     // Catch:{ all -> 0x0090 }
            int r4 = r2.size()     // Catch:{ all -> 0x0090 }
            long r4 = (long) r4     // Catch:{ all -> 0x0090 }
            okio.Sink r3 = r10.newMessageSink(r3, r4)     // Catch:{ all -> 0x0090 }
            okio.BufferedSink r3 = okio.Okio.buffer((okio.Sink) r3)     // Catch:{ all -> 0x0090 }
            r3.write((okio.ByteString) r2)     // Catch:{ all -> 0x0090 }
            r3.close()     // Catch:{ all -> 0x0090 }
            monitor-enter(r14)     // Catch:{ all -> 0x0090 }
            long r4 = r14.queueSize     // Catch:{ all -> 0x008d }
            int r2 = r2.size()     // Catch:{ all -> 0x008d }
            long r2 = (long) r2     // Catch:{ all -> 0x008d }
            long r2 = r4 - r2
            r14.queueSize = r2     // Catch:{ all -> 0x008d }
            monitor-exit(r14)     // Catch:{ all -> 0x008d }
            goto L_0x0039
        L_0x008d:
            r2 = move-exception
            monitor-exit(r14)     // Catch:{ all -> 0x008d }
            throw r2     // Catch:{ all -> 0x0090 }
        L_0x0090:
            r2 = move-exception
            neton.internal.Util.closeQuietly((java.io.Closeable) r6)
            throw r2
        L_0x0095:
            boolean r2 = r3 instanceof neton.internal.p017ws.RealWebSocket.Close     // Catch:{ all -> 0x0090 }
            if (r2 == 0) goto L_0x00aa
            neton.internal.ws.RealWebSocket$Close r3 = (neton.internal.p017ws.RealWebSocket.Close) r3     // Catch:{ all -> 0x0090 }
            int r2 = r3.code     // Catch:{ all -> 0x0090 }
            okio.ByteString r3 = r3.reason     // Catch:{ all -> 0x0090 }
            r10.writeClose(r2, r3)     // Catch:{ all -> 0x0090 }
            if (r6 == 0) goto L_0x0039
            neton.WebSocketListener r2 = r14.listener     // Catch:{ all -> 0x0090 }
            r2.onClosed(r14, r9, r8)     // Catch:{ all -> 0x0090 }
            goto L_0x0039
        L_0x00aa:
            java.lang.AssertionError r2 = new java.lang.AssertionError     // Catch:{ all -> 0x0090 }
            r2.<init>()     // Catch:{ all -> 0x0090 }
            throw r2     // Catch:{ all -> 0x0090 }
        L_0x00b0:
            r6 = r7
            r8 = r7
            r9 = r5
            r3 = r4
            goto L_0x0033
        L_0x00b6:
            r6 = r7
            r8 = r7
            r9 = r5
            r3 = r7
            goto L_0x0033
        */
        throw new UnsupportedOperationException("Method not decompiled: neton.internal.p017ws.RealWebSocket.writeOneFrame():boolean");
    }

    /* renamed from: neton.internal.ws.RealWebSocket$PingRunnable */
    final class PingRunnable implements Runnable {
        PingRunnable() {
        }

        public final void run() {
            RealWebSocket.this.writePingFrame();
        }
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: No exception handlers in catch block: Catch:{  } */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void writePingFrame() {
        /*
            r2 = this;
            monitor-enter(r2)
            boolean r0 = r2.failed     // Catch:{ all -> 0x0016 }
            if (r0 == 0) goto L_0x0007
            monitor-exit(r2)     // Catch:{ all -> 0x0016 }
        L_0x0006:
            return
        L_0x0007:
            neton.internal.ws.WebSocketWriter r0 = r2.writer     // Catch:{ all -> 0x0016 }
            monitor-exit(r2)     // Catch:{ all -> 0x0016 }
            okio.ByteString r1 = okio.ByteString.EMPTY     // Catch:{ IOException -> 0x0010 }
            r0.writePing(r1)     // Catch:{ IOException -> 0x0010 }
            goto L_0x0006
        L_0x0010:
            r0 = move-exception
            r1 = 0
            r2.failWebSocket(r0, r1)
            goto L_0x0006
        L_0x0016:
            r0 = move-exception
            monitor-exit(r2)     // Catch:{ all -> 0x0016 }
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: neton.internal.p017ws.RealWebSocket.writePingFrame():void");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:13:?, code lost:
        r3.listener.onFailure(r3, r4, r5);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x002f, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0030, code lost:
        neton.internal.Util.closeQuietly((java.io.Closeable) r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0033, code lost:
        throw r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:?, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void failWebSocket(java.lang.Exception r4, neton.Response r5) {
        /*
            r3 = this;
            monitor-enter(r3)
            boolean r0 = r3.failed     // Catch:{ all -> 0x002c }
            if (r0 == 0) goto L_0x0007
            monitor-exit(r3)     // Catch:{ all -> 0x002c }
        L_0x0006:
            return
        L_0x0007:
            r0 = 1
            r3.failed = r0     // Catch:{ all -> 0x002c }
            neton.internal.ws.RealWebSocket$Streams r1 = r3.streams     // Catch:{ all -> 0x002c }
            r0 = 0
            r3.streams = r0     // Catch:{ all -> 0x002c }
            java.util.concurrent.ScheduledFuture<?> r0 = r3.cancelFuture     // Catch:{ all -> 0x002c }
            if (r0 == 0) goto L_0x0019
            java.util.concurrent.ScheduledFuture<?> r0 = r3.cancelFuture     // Catch:{ all -> 0x002c }
            r2 = 0
            r0.cancel(r2)     // Catch:{ all -> 0x002c }
        L_0x0019:
            java.util.concurrent.ScheduledExecutorService r0 = r3.executor     // Catch:{ all -> 0x002c }
            if (r0 == 0) goto L_0x0022
            java.util.concurrent.ScheduledExecutorService r0 = r3.executor     // Catch:{ all -> 0x002c }
            r0.shutdown()     // Catch:{ all -> 0x002c }
        L_0x0022:
            monitor-exit(r3)     // Catch:{ all -> 0x002c }
            neton.WebSocketListener r0 = r3.listener     // Catch:{ all -> 0x002f }
            r0.onFailure(r3, r4, r5)     // Catch:{ all -> 0x002f }
            neton.internal.Util.closeQuietly((java.io.Closeable) r1)
            goto L_0x0006
        L_0x002c:
            r0 = move-exception
            monitor-exit(r3)     // Catch:{ all -> 0x002c }
            throw r0
        L_0x002f:
            r0 = move-exception
            neton.internal.Util.closeQuietly((java.io.Closeable) r1)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: neton.internal.p017ws.RealWebSocket.failWebSocket(java.lang.Exception, neton.Response):void");
    }

    /* renamed from: neton.internal.ws.RealWebSocket$Message */
    final class Message {
        final ByteString data;
        final int formatOpcode;

        Message(int i, ByteString byteString) {
            this.formatOpcode = i;
            this.data = byteString;
        }
    }

    /* renamed from: neton.internal.ws.RealWebSocket$Close */
    final class Close {
        final long cancelAfterCloseMillis;
        final int code;
        final ByteString reason;

        Close(int i, ByteString byteString, long j) {
            this.code = i;
            this.reason = byteString;
            this.cancelAfterCloseMillis = j;
        }
    }

    /* renamed from: neton.internal.ws.RealWebSocket$Streams */
    public abstract class Streams implements Closeable {
        public final boolean client;
        public final BufferedSink sink;
        public final BufferedSource source;

        public Streams(boolean z, BufferedSource bufferedSource, BufferedSink bufferedSink) {
            this.client = z;
            this.source = bufferedSource;
            this.sink = bufferedSink;
        }
    }

    /* renamed from: neton.internal.ws.RealWebSocket$CancelRunnable */
    final class CancelRunnable implements Runnable {
        CancelRunnable() {
        }

        public final void run() {
            RealWebSocket.this.cancel();
        }
    }
}
