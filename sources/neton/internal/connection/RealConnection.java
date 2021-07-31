package neton.internal.connection;

import java.io.IOException;
import java.lang.ref.Reference;
import java.net.ConnectException;
import java.net.Proxy;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;
import javax.net.ssl.SSLPeerUnverifiedException;
import neton.Address;
import neton.Call;
import neton.Connection;
import neton.ConnectionPool;
import neton.EventListener;
import neton.Handshake;
import neton.HttpUrl;
import neton.Interceptor;
import neton.OkHttpClient;
import neton.Protocol;
import neton.Request;
import neton.Response;
import neton.Route;
import neton.client.Utils.ResponseCode;
import neton.internal.Internal;
import neton.internal.Util;
import neton.internal.Version;
import neton.internal.http.HttpCodec;
import neton.internal.http.HttpHeaders;
import neton.internal.http1.Http1Codec;
import neton.internal.http2.ErrorCode;
import neton.internal.http2.Http2Codec;
import neton.internal.http2.Http2Connection;
import neton.internal.http2.Http2Stream;
import neton.internal.p017ws.RealWebSocket;
import neton.internal.platform.Platform;
import neton.internal.tls.OkHostnameVerifier;
import okio.BufferedSink;
import okio.BufferedSource;
import okio.Okio;
import okio.Source;

public final class RealConnection extends Http2Connection.Listener implements Connection {
    private static final String ECDHE_ECDSA_128 = "TLS_ECDHE_ECDSA_WITH_AES_128_GCM_SHA256";
    private static final String ECDHE_RSA_128 = "TLS_ECDHE_RSA_WITH_AES_128_GCM_SHA256";
    private static final int MAX_TUNNEL_ATTEMPTS = 21;
    private static final String NPE_THROW_WITH_NULL = "throw with null exception";
    public int allocationLimit = 1;
    public final List<Reference<StreamAllocation>> allocations = new ArrayList();
    private final ConnectionPool connectionPool;
    private Handshake handshake;
    private Http2Connection http2Connection;
    public long idleAtNanos = Long.MAX_VALUE;
    public boolean noNewStreams;
    private Protocol protocol;
    private Socket rawSocket;
    private final Route route;
    private BufferedSink sink;
    private Socket socket;
    private BufferedSource source;
    public int successCount;

    public RealConnection(ConnectionPool connectionPool2, Route route2) {
        this.connectionPool = connectionPool2;
        this.route = route2;
    }

    public static RealConnection testConnection(ConnectionPool connectionPool2, Route route2, Socket socket2, long j) {
        RealConnection realConnection = new RealConnection(connectionPool2, route2);
        realConnection.socket = socket2;
        realConnection.idleAtNanos = j;
        return realConnection;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0098, code lost:
        if (r9.rawSocket == null) goto L_0x009a;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void connect(int r10, int r11, int r12, boolean r13, neton.Call r14, neton.EventListener r15) {
        /*
            r9 = this;
            r7 = 0
            neton.Protocol r0 = r9.protocol
            if (r0 == 0) goto L_0x0012
            com.coloros.neton.NetonException r0 = new com.coloros.neton.NetonException
            java.lang.IllegalStateException r1 = new java.lang.IllegalStateException
            java.lang.String r2 = "already connected"
            r1.<init>(r2)
            r0.<init>((java.lang.Throwable) r1)
            throw r0
        L_0x0012:
            neton.Route r0 = r9.route
            neton.Address r0 = r0.address()
            java.util.List r0 = r0.connectionSpecs()
            neton.internal.connection.ConnectionSpecSelector r8 = new neton.internal.connection.ConnectionSpecSelector
            r8.<init>(r0)
            neton.Route r1 = r9.route
            neton.Address r1 = r1.address()
            javax.net.ssl.SSLSocketFactory r1 = r1.sslSocketFactory()
            if (r1 != 0) goto L_0x0122
            neton.ConnectionSpec r1 = neton.ConnectionSpec.CLEARTEXT
            boolean r0 = r0.contains(r1)
            if (r0 != 0) goto L_0x0047
            com.coloros.neton.NetonException r0 = new com.coloros.neton.NetonException
            neton.internal.connection.RouteException r1 = new neton.internal.connection.RouteException
            java.net.UnknownServiceException r2 = new java.net.UnknownServiceException
            java.lang.String r3 = "CLEARTEXT communication not enabled for client"
            r2.<init>(r3)
            r1.<init>(r2)
            r0.<init>((java.lang.Throwable) r1)
            throw r0
        L_0x0047:
            neton.Route r0 = r9.route
            neton.Address r0 = r0.address()
            neton.HttpUrl r0 = r0.url()
            java.lang.String r0 = r0.host()
            neton.internal.platform.Platform r1 = neton.internal.platform.Platform.get()
            boolean r1 = r1.isCleartextTrafficPermitted(r0)
            if (r1 != 0) goto L_0x0122
            com.coloros.neton.NetonException r1 = new com.coloros.neton.NetonException
            neton.internal.connection.RouteException r2 = new neton.internal.connection.RouteException
            java.net.UnknownServiceException r3 = new java.net.UnknownServiceException
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            java.lang.String r5 = "CLEARTEXT communication to "
            r4.<init>(r5)
            java.lang.StringBuilder r0 = r4.append(r0)
            java.lang.String r4 = " not permitted by network security policy"
            java.lang.StringBuilder r0 = r0.append(r4)
            java.lang.String r0 = r0.toString()
            r3.<init>(r0)
            r2.<init>(r3)
            r1.<init>((java.lang.Throwable) r2)
            throw r1
        L_0x0084:
            r6 = r0
        L_0x0085:
            neton.Route r0 = r9.route     // Catch:{ IOException -> 0x00ce }
            boolean r0 = r0.requiresTunnel()     // Catch:{ IOException -> 0x00ce }
            if (r0 == 0) goto L_0x00b3
            r0 = r9
            r1 = r10
            r2 = r11
            r3 = r12
            r4 = r14
            r5 = r15
            r0.connectTunnel(r1, r2, r3, r4, r5)     // Catch:{ IOException -> 0x00ce }
            java.net.Socket r0 = r9.rawSocket     // Catch:{ IOException -> 0x00ce }
            if (r0 != 0) goto L_0x00b6
        L_0x009a:
            neton.Route r0 = r9.route
            boolean r0 = r0.requiresTunnel()
            if (r0 == 0) goto L_0x010e
            java.net.Socket r0 = r9.rawSocket
            if (r0 != 0) goto L_0x010e
            java.net.ProtocolException r0 = new java.net.ProtocolException
            java.lang.String r1 = "Too many tunnel connections attempted: 21"
            r0.<init>(r1)
            neton.internal.connection.RouteException r1 = new neton.internal.connection.RouteException
            r1.<init>(r0)
            throw r1
        L_0x00b3:
            r9.connectSocket(r10, r11, r14, r15)     // Catch:{ IOException -> 0x00ce }
        L_0x00b6:
            r9.establishProtocol(r8, r14, r15)     // Catch:{ IOException -> 0x00ce }
            neton.Route r0 = r9.route     // Catch:{ IOException -> 0x00ce }
            java.net.InetSocketAddress r2 = r0.socketAddress()     // Catch:{ IOException -> 0x00ce }
            neton.Route r0 = r9.route     // Catch:{ IOException -> 0x00ce }
            java.net.Proxy r3 = r0.proxy()     // Catch:{ IOException -> 0x00ce }
            neton.Protocol r4 = r9.protocol     // Catch:{ IOException -> 0x00ce }
            r5 = 0
            r0 = r15
            r1 = r14
            r0.connectEnd(r1, r2, r3, r4, r5)     // Catch:{ IOException -> 0x00ce }
            goto L_0x009a
        L_0x00ce:
            r5 = move-exception
            java.net.Socket r0 = r9.socket
            neton.internal.Util.closeQuietly((java.net.Socket) r0)
            java.net.Socket r0 = r9.rawSocket
            neton.internal.Util.closeQuietly((java.net.Socket) r0)
            r9.socket = r7
            r9.rawSocket = r7
            r9.source = r7
            r9.sink = r7
            r9.handshake = r7
            r9.protocol = r7
            r9.http2Connection = r7
            neton.Route r0 = r9.route
            java.net.InetSocketAddress r2 = r0.socketAddress()
            neton.Route r0 = r9.route
            java.net.Proxy r3 = r0.proxy()
            r0 = r15
            r1 = r14
            r4 = r7
            r0.connectEnd(r1, r2, r3, r4, r5)
            if (r6 != 0) goto L_0x0109
            neton.internal.connection.RouteException r0 = new neton.internal.connection.RouteException
            r0.<init>(r5)
        L_0x0100:
            if (r13 == 0) goto L_0x0108
            boolean r1 = r8.connectionFailed(r5)
            if (r1 != 0) goto L_0x0084
        L_0x0108:
            throw r0
        L_0x0109:
            r6.addConnectException(r5)
            r0 = r6
            goto L_0x0100
        L_0x010e:
            neton.internal.http2.Http2Connection r0 = r9.http2Connection
            if (r0 == 0) goto L_0x011e
            neton.ConnectionPool r1 = r9.connectionPool
            monitor-enter(r1)
            neton.internal.http2.Http2Connection r0 = r9.http2Connection     // Catch:{ all -> 0x011f }
            int r0 = r0.maxConcurrentStreams()     // Catch:{ all -> 0x011f }
            r9.allocationLimit = r0     // Catch:{ all -> 0x011f }
            monitor-exit(r1)     // Catch:{ all -> 0x011f }
        L_0x011e:
            return
        L_0x011f:
            r0 = move-exception
            monitor-exit(r1)     // Catch:{ all -> 0x011f }
            throw r0
        L_0x0122:
            r6 = r7
            goto L_0x0085
        */
        throw new UnsupportedOperationException("Method not decompiled: neton.internal.connection.RealConnection.connect(int, int, int, boolean, neton.Call, neton.EventListener):void");
    }

    private void connectTunnel(int i, int i2, int i3, Call call, EventListener eventListener) {
        Request createTunnelRequest = createTunnelRequest();
        HttpUrl url = createTunnelRequest.url();
        int i4 = 0;
        while (i4 < MAX_TUNNEL_ATTEMPTS) {
            connectSocket(i, i2, call, eventListener);
            Request createTunnel = createTunnel(i2, i3, createTunnelRequest, url);
            if (createTunnel != null) {
                Util.closeQuietly(this.rawSocket);
                this.rawSocket = null;
                this.sink = null;
                this.source = null;
                eventListener.connectEnd(call, this.route.socketAddress(), this.route.proxy(), (Protocol) null, (Throwable) null);
                i4++;
                createTunnelRequest = createTunnel;
            } else {
                return;
            }
        }
    }

    private void connectSocket(int i, int i2, Call call, EventListener eventListener) {
        Proxy proxy = this.route.proxy();
        this.rawSocket = (proxy.type() == Proxy.Type.DIRECT || proxy.type() == Proxy.Type.HTTP) ? this.route.address().socketFactory().createSocket() : new Socket(proxy);
        eventListener.connectStart(call, this.route.socketAddress(), proxy);
        this.rawSocket.setSoTimeout(i2);
        try {
            Platform.get().connectSocket(this.rawSocket, this.route.socketAddress(), i);
            try {
                this.source = Okio.buffer(Okio.source(this.rawSocket));
                this.sink = Okio.buffer(Okio.sink(this.rawSocket));
            } catch (NullPointerException e) {
                if (NPE_THROW_WITH_NULL.equals(e.getMessage())) {
                    throw new IOException(e);
                }
            }
        } catch (ConnectException e2) {
            ConnectException connectException = new ConnectException("Failed to connect to " + this.route.socketAddress());
            connectException.initCause(e2);
            throw connectException;
        }
    }

    private void establishProtocol(ConnectionSpecSelector connectionSpecSelector, Call call, EventListener eventListener) {
        if (this.route.address().sslSocketFactory() == null) {
            this.protocol = Protocol.HTTP_1_1;
            this.socket = this.rawSocket;
            return;
        }
        eventListener.secureConnectStart(call);
        try {
            connectTls(connectionSpecSelector);
            eventListener.secureConnectEnd(call, this.handshake, (Throwable) null);
            if (this.protocol == Protocol.HTTP_2) {
                this.socket.setSoTimeout(0);
                this.http2Connection = new Http2Connection.Builder(true).socket(this.socket, this.route.address().url().host(), this.source, this.sink).listener(this).build();
                this.http2Connection.start();
            }
        } catch (Exception e) {
            eventListener.secureConnectEnd(call, (Handshake) null, e);
            throw e;
        }
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v0, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v1, resolved type: javax.net.ssl.SSLSocket} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v2, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v8, resolved type: javax.net.ssl.SSLSocket} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v3, resolved type: javax.net.ssl.SSLSocket} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v4, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v5, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v9, resolved type: javax.net.ssl.SSLSocket} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v10, resolved type: java.lang.String} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void connectTls(neton.internal.connection.ConnectionSpecSelector r9) {
        /*
            r8 = this;
            r2 = 0
            neton.Route r0 = r8.route
            neton.Address r3 = r0.address()
            javax.net.ssl.SSLSocketFactory r0 = r3.sslSocketFactory()
            java.net.Socket r1 = r8.rawSocket     // Catch:{ AssertionError -> 0x0162 }
            neton.HttpUrl r4 = r3.url()     // Catch:{ AssertionError -> 0x0162 }
            java.lang.String r4 = r4.host()     // Catch:{ AssertionError -> 0x0162 }
            neton.HttpUrl r5 = r3.url()     // Catch:{ AssertionError -> 0x0162 }
            int r5 = r5.port()     // Catch:{ AssertionError -> 0x0162 }
            r6 = 1
            java.net.Socket r0 = r0.createSocket(r1, r4, r5, r6)     // Catch:{ AssertionError -> 0x0162 }
            javax.net.ssl.SSLSocket r0 = (javax.net.ssl.SSLSocket) r0     // Catch:{ AssertionError -> 0x0162 }
            java.lang.String[] r1 = r0.getEnabledCipherSuites()     // Catch:{ AssertionError -> 0x00ef, all -> 0x015f }
            java.lang.String[] r1 = r8.getNetonCipherSuites(r1)     // Catch:{ AssertionError -> 0x00ef, all -> 0x015f }
            r0.setEnabledCipherSuites(r1)     // Catch:{ AssertionError -> 0x00ef, all -> 0x015f }
            neton.ConnectionSpec r1 = r9.configureSecureSocket(r0)     // Catch:{ AssertionError -> 0x00ef, all -> 0x015f }
            boolean r4 = r1.supportsTlsExtensions()     // Catch:{ AssertionError -> 0x00ef, all -> 0x015f }
            if (r4 == 0) goto L_0x004c
            neton.internal.platform.Platform r4 = neton.internal.platform.Platform.get()     // Catch:{ AssertionError -> 0x00ef, all -> 0x015f }
            neton.HttpUrl r5 = r3.url()     // Catch:{ AssertionError -> 0x00ef, all -> 0x015f }
            java.lang.String r5 = r5.host()     // Catch:{ AssertionError -> 0x00ef, all -> 0x015f }
            java.util.List r6 = r3.protocols()     // Catch:{ AssertionError -> 0x00ef, all -> 0x015f }
            r4.configureTlsExtensions(r0, r5, r6)     // Catch:{ AssertionError -> 0x00ef, all -> 0x015f }
        L_0x004c:
            java.lang.String r4 = "connectTls--startHandshake"
            neton.client.Utils.LogUtil.m787d(r4)     // Catch:{ AssertionError -> 0x00ef, all -> 0x015f }
            neton.client.statistics.RequestStatistic r4 = r3.requestStatistic()     // Catch:{ AssertionError -> 0x00ef, all -> 0x015f }
            if (r4 == 0) goto L_0x0062
            neton.client.statistics.RequestStatistic r4 = r3.requestStatistic()     // Catch:{ AssertionError -> 0x00ef, all -> 0x015f }
            long r6 = java.lang.System.currentTimeMillis()     // Catch:{ AssertionError -> 0x00ef, all -> 0x015f }
            r4.setStartSslTime(r6)     // Catch:{ AssertionError -> 0x00ef, all -> 0x015f }
        L_0x0062:
            r0.startHandshake()     // Catch:{ AssertionError -> 0x00ef, all -> 0x015f }
            javax.net.ssl.SSLSession r4 = r0.getSession()     // Catch:{ AssertionError -> 0x00ef, all -> 0x015f }
            neton.Handshake r4 = neton.Handshake.get(r4)     // Catch:{ AssertionError -> 0x00ef, all -> 0x015f }
            neton.client.statistics.RequestStatistic r5 = r3.requestStatistic()     // Catch:{ AssertionError -> 0x00ef, all -> 0x015f }
            if (r5 == 0) goto L_0x007e
            neton.client.statistics.RequestStatistic r5 = r3.requestStatistic()     // Catch:{ AssertionError -> 0x00ef, all -> 0x015f }
            long r6 = java.lang.System.currentTimeMillis()     // Catch:{ AssertionError -> 0x00ef, all -> 0x015f }
            r5.setEndSslTime(r6)     // Catch:{ AssertionError -> 0x00ef, all -> 0x015f }
        L_0x007e:
            java.lang.String r5 = "connectTls--startHandshake end"
            neton.client.Utils.LogUtil.m787d(r5)     // Catch:{ AssertionError -> 0x00ef, all -> 0x015f }
            javax.net.ssl.HostnameVerifier r5 = r3.hostnameVerifier()     // Catch:{ AssertionError -> 0x00ef, all -> 0x015f }
            neton.HttpUrl r6 = r3.url()     // Catch:{ AssertionError -> 0x00ef, all -> 0x015f }
            java.lang.String r6 = r6.host()     // Catch:{ AssertionError -> 0x00ef, all -> 0x015f }
            javax.net.ssl.SSLSession r7 = r0.getSession()     // Catch:{ AssertionError -> 0x00ef, all -> 0x015f }
            boolean r5 = r5.verify(r6, r7)     // Catch:{ AssertionError -> 0x00ef, all -> 0x015f }
            if (r5 != 0) goto L_0x010c
            java.util.List r1 = r4.peerCertificates()     // Catch:{ AssertionError -> 0x00ef, all -> 0x015f }
            r2 = 0
            java.lang.Object r1 = r1.get(r2)     // Catch:{ AssertionError -> 0x00ef, all -> 0x015f }
            java.security.cert.X509Certificate r1 = (java.security.cert.X509Certificate) r1     // Catch:{ AssertionError -> 0x00ef, all -> 0x015f }
            javax.net.ssl.SSLPeerUnverifiedException r2 = new javax.net.ssl.SSLPeerUnverifiedException     // Catch:{ AssertionError -> 0x00ef, all -> 0x015f }
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ AssertionError -> 0x00ef, all -> 0x015f }
            java.lang.String r5 = "Hostname "
            r4.<init>(r5)     // Catch:{ AssertionError -> 0x00ef, all -> 0x015f }
            neton.HttpUrl r3 = r3.url()     // Catch:{ AssertionError -> 0x00ef, all -> 0x015f }
            java.lang.String r3 = r3.host()     // Catch:{ AssertionError -> 0x00ef, all -> 0x015f }
            java.lang.StringBuilder r3 = r4.append(r3)     // Catch:{ AssertionError -> 0x00ef, all -> 0x015f }
            java.lang.String r4 = " not verified:\n    certificate: "
            java.lang.StringBuilder r3 = r3.append(r4)     // Catch:{ AssertionError -> 0x00ef, all -> 0x015f }
            java.lang.String r4 = neton.CertificatePinner.pin(r1)     // Catch:{ AssertionError -> 0x00ef, all -> 0x015f }
            java.lang.StringBuilder r3 = r3.append(r4)     // Catch:{ AssertionError -> 0x00ef, all -> 0x015f }
            java.lang.String r4 = "\n    DN: "
            java.lang.StringBuilder r3 = r3.append(r4)     // Catch:{ AssertionError -> 0x00ef, all -> 0x015f }
            java.security.Principal r4 = r1.getSubjectDN()     // Catch:{ AssertionError -> 0x00ef, all -> 0x015f }
            java.lang.String r4 = r4.getName()     // Catch:{ AssertionError -> 0x00ef, all -> 0x015f }
            java.lang.StringBuilder r3 = r3.append(r4)     // Catch:{ AssertionError -> 0x00ef, all -> 0x015f }
            java.lang.String r4 = "\n    subjectAltNames: "
            java.lang.StringBuilder r3 = r3.append(r4)     // Catch:{ AssertionError -> 0x00ef, all -> 0x015f }
            java.util.List r1 = neton.internal.tls.OkHostnameVerifier.allSubjectAltNames(r1)     // Catch:{ AssertionError -> 0x00ef, all -> 0x015f }
            java.lang.StringBuilder r1 = r3.append(r1)     // Catch:{ AssertionError -> 0x00ef, all -> 0x015f }
            java.lang.String r1 = r1.toString()     // Catch:{ AssertionError -> 0x00ef, all -> 0x015f }
            r2.<init>(r1)     // Catch:{ AssertionError -> 0x00ef, all -> 0x015f }
            throw r2     // Catch:{ AssertionError -> 0x00ef, all -> 0x015f }
        L_0x00ef:
            r1 = move-exception
            r2 = r0
        L_0x00f1:
            boolean r0 = neton.internal.Util.isAndroidGetsocknameError(r1)     // Catch:{ all -> 0x00fd }
            if (r0 == 0) goto L_0x015e
            java.io.IOException r0 = new java.io.IOException     // Catch:{ all -> 0x00fd }
            r0.<init>(r1)     // Catch:{ all -> 0x00fd }
            throw r0     // Catch:{ all -> 0x00fd }
        L_0x00fd:
            r0 = move-exception
            r1 = r0
        L_0x00ff:
            if (r2 == 0) goto L_0x0108
            neton.internal.platform.Platform r0 = neton.internal.platform.Platform.get()
            r0.afterHandshake(r2)
        L_0x0108:
            neton.internal.Util.closeQuietly((java.net.Socket) r2)
            throw r1
        L_0x010c:
            neton.CertificatePinner r5 = r3.certificatePinner()     // Catch:{ AssertionError -> 0x00ef, all -> 0x015f }
            neton.HttpUrl r3 = r3.url()     // Catch:{ AssertionError -> 0x00ef, all -> 0x015f }
            java.lang.String r3 = r3.host()     // Catch:{ AssertionError -> 0x00ef, all -> 0x015f }
            java.util.List r6 = r4.peerCertificates()     // Catch:{ AssertionError -> 0x00ef, all -> 0x015f }
            r5.check((java.lang.String) r3, (java.util.List<java.security.cert.Certificate>) r6)     // Catch:{ AssertionError -> 0x00ef, all -> 0x015f }
            boolean r1 = r1.supportsTlsExtensions()     // Catch:{ AssertionError -> 0x00ef, all -> 0x015f }
            if (r1 == 0) goto L_0x012d
            neton.internal.platform.Platform r1 = neton.internal.platform.Platform.get()     // Catch:{ AssertionError -> 0x00ef, all -> 0x015f }
            java.lang.String r2 = r1.getSelectedProtocol(r0)     // Catch:{ AssertionError -> 0x00ef, all -> 0x015f }
        L_0x012d:
            r8.socket = r0     // Catch:{ AssertionError -> 0x00ef, all -> 0x015f }
            java.net.Socket r1 = r8.socket     // Catch:{ AssertionError -> 0x00ef, all -> 0x015f }
            okio.Source r1 = okio.Okio.source((java.net.Socket) r1)     // Catch:{ AssertionError -> 0x00ef, all -> 0x015f }
            okio.BufferedSource r1 = okio.Okio.buffer((okio.Source) r1)     // Catch:{ AssertionError -> 0x00ef, all -> 0x015f }
            r8.source = r1     // Catch:{ AssertionError -> 0x00ef, all -> 0x015f }
            java.net.Socket r1 = r8.socket     // Catch:{ AssertionError -> 0x00ef, all -> 0x015f }
            okio.Sink r1 = okio.Okio.sink((java.net.Socket) r1)     // Catch:{ AssertionError -> 0x00ef, all -> 0x015f }
            okio.BufferedSink r1 = okio.Okio.buffer((okio.Sink) r1)     // Catch:{ AssertionError -> 0x00ef, all -> 0x015f }
            r8.sink = r1     // Catch:{ AssertionError -> 0x00ef, all -> 0x015f }
            r8.handshake = r4     // Catch:{ AssertionError -> 0x00ef, all -> 0x015f }
            if (r2 == 0) goto L_0x015b
            neton.Protocol r1 = neton.Protocol.get(r2)     // Catch:{ AssertionError -> 0x00ef, all -> 0x015f }
        L_0x014f:
            r8.protocol = r1     // Catch:{ AssertionError -> 0x00ef, all -> 0x015f }
            if (r0 == 0) goto L_0x015a
            neton.internal.platform.Platform r1 = neton.internal.platform.Platform.get()
            r1.afterHandshake(r0)
        L_0x015a:
            return
        L_0x015b:
            neton.Protocol r1 = neton.Protocol.HTTP_1_1     // Catch:{ AssertionError -> 0x00ef, all -> 0x015f }
            goto L_0x014f
        L_0x015e:
            throw r1     // Catch:{ all -> 0x00fd }
        L_0x015f:
            r1 = move-exception
            r2 = r0
            goto L_0x00ff
        L_0x0162:
            r0 = move-exception
            r1 = r0
            goto L_0x00f1
        */
        throw new UnsupportedOperationException("Method not decompiled: neton.internal.connection.RealConnection.connectTls(neton.internal.connection.ConnectionSpecSelector):void");
    }

    private String[] getNetonCipherSuites(String[] strArr) {
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(Arrays.asList(strArr));
        if (arrayList.size() > 0) {
            if (arrayList.contains(ECDHE_RSA_128)) {
                arrayList.remove(ECDHE_RSA_128);
                arrayList.add(0, ECDHE_RSA_128);
            }
            if (arrayList.contains(ECDHE_ECDSA_128)) {
                arrayList.remove(ECDHE_ECDSA_128);
                arrayList.add(0, ECDHE_ECDSA_128);
            }
            arrayList.toArray(strArr);
        }
        return strArr;
    }

    private Request createTunnel(int i, int i2, Request request, HttpUrl httpUrl) {
        Response build;
        String str = "CONNECT " + Util.hostHeader(httpUrl, true) + " HTTP/1.1";
        do {
            Http1Codec http1Codec = new Http1Codec((OkHttpClient) null, (StreamAllocation) null, this.source, this.sink);
            this.source.timeout().timeout((long) i, TimeUnit.MILLISECONDS);
            this.sink.timeout().timeout((long) i2, TimeUnit.MILLISECONDS);
            http1Codec.writeRequest(request.headers(), str);
            http1Codec.finishRequest();
            build = http1Codec.readResponseHeaders(false).request(request).build();
            long contentLength = HttpHeaders.contentLength(build);
            if (contentLength == -1) {
                contentLength = 0;
            }
            Source newFixedLengthSource = http1Codec.newFixedLengthSource(contentLength);
            Util.skipAll(newFixedLengthSource, Integer.MAX_VALUE, TimeUnit.MILLISECONDS);
            newFixedLengthSource.close();
            switch (build.code()) {
                case ResponseCode.CODE_2XX_SUCCESS:
                    if (this.source.buffer().exhausted() && this.sink.buffer().exhausted()) {
                        return null;
                    }
                    throw new IOException("TLS tunnel buffered too many bytes!");
                case ResponseCode.CODE_4XX_PROXY_AUTHENTICATION_REQUIRED:
                    request = this.route.address().proxyAuthenticator().authenticate(this.route, build);
                    if (request != null) {
                        break;
                    } else {
                        throw new IOException("Failed to authenticate with proxy");
                    }
                default:
                    throw new IOException("Unexpected response code for CONNECT: " + build.code());
            }
        } while (!"close".equalsIgnoreCase(build.header("Connection")));
        return request;
    }

    private Request createTunnelRequest() {
        return new Request.Builder().url(this.route.address().url()).header("Host", Util.hostHeader(this.route.address().url(), true)).header("Proxy-Connection", "Keep-Alive").header("User-Agent", Version.userAgent()).build();
    }

    public final boolean isEligible(Address address, Route route2) {
        if (this.allocations.size() >= this.allocationLimit || this.noNewStreams || !Internal.instance.equalsNonHost(this.route.address(), address)) {
            return false;
        }
        if (address.url().host().equals(route().address().url().host())) {
            return true;
        }
        if (this.http2Connection == null || route2 == null || route2.proxy().type() != Proxy.Type.DIRECT || this.route.proxy().type() != Proxy.Type.DIRECT || !this.route.socketAddress().equals(route2.socketAddress()) || route2.address().hostnameVerifier() != OkHostnameVerifier.INSTANCE || !supportsUrl(address.url())) {
            return false;
        }
        try {
            address.certificatePinner().check(address.url().host(), handshake().peerCertificates());
            return true;
        } catch (SSLPeerUnverifiedException e) {
            return false;
        }
    }

    public final boolean supportsUrl(HttpUrl httpUrl) {
        if (httpUrl.port() != this.route.address().url().port()) {
            return false;
        }
        if (!httpUrl.host().equals(this.route.address().url().host())) {
            return this.handshake != null && OkHostnameVerifier.INSTANCE.verify(httpUrl.host(), (X509Certificate) this.handshake.peerCertificates().get(0));
        }
        return true;
    }

    public final HttpCodec newCodec(OkHttpClient okHttpClient, Interceptor.Chain chain, StreamAllocation streamAllocation) {
        if (this.http2Connection != null) {
            return new Http2Codec(okHttpClient, chain, streamAllocation, this.http2Connection);
        }
        this.socket.setSoTimeout(chain.readTimeoutMillis());
        this.source.timeout().timeout((long) chain.readTimeoutMillis(), TimeUnit.MILLISECONDS);
        this.sink.timeout().timeout((long) chain.writeTimeoutMillis(), TimeUnit.MILLISECONDS);
        return new Http1Codec(okHttpClient, streamAllocation, this.source, this.sink);
    }

    public final RealWebSocket.Streams newWebSocketStreams(StreamAllocation streamAllocation) {
        final StreamAllocation streamAllocation2 = streamAllocation;
        return new RealWebSocket.Streams(true, this.source, this.sink) {
            public void close() {
                streamAllocation2.streamFinished(true, streamAllocation2.codec());
            }
        };
    }

    public final Route route() {
        return this.route;
    }

    public final void cancel() {
        Util.closeQuietly(this.rawSocket);
    }

    public final Socket socket() {
        return this.socket;
    }

    public final boolean isHealthy(boolean z) {
        int soTimeout;
        if (this.socket.isClosed() || this.socket.isInputShutdown() || this.socket.isOutputShutdown()) {
            return false;
        }
        if (this.http2Connection != null) {
            if (this.http2Connection.isShutdown()) {
                return false;
            }
            return true;
        } else if (!z) {
            return true;
        } else {
            try {
                soTimeout = this.socket.getSoTimeout();
                this.socket.setSoTimeout(1);
                if (this.source.exhausted()) {
                    this.socket.setSoTimeout(soTimeout);
                    return false;
                }
                this.socket.setSoTimeout(soTimeout);
                return true;
            } catch (SocketTimeoutException e) {
                return true;
            } catch (IOException e2) {
                return false;
            } catch (Throwable th) {
                this.socket.setSoTimeout(soTimeout);
                throw th;
            }
        }
    }

    public final void onStream(Http2Stream http2Stream) {
        http2Stream.close(ErrorCode.REFUSED_STREAM);
    }

    public final void onSettings(Http2Connection http2Connection2) {
        synchronized (this.connectionPool) {
            this.allocationLimit = http2Connection2.maxConcurrentStreams();
        }
    }

    public final Handshake handshake() {
        return this.handshake;
    }

    public final boolean isMultiplexed() {
        return this.http2Connection != null;
    }

    public final Protocol protocol() {
        return this.protocol;
    }

    public final String toString() {
        return "Connection{" + this.route.address().url().host() + ":" + this.route.address().url().port() + ", proxy=" + this.route.proxy() + " hostAddress=" + this.route.socketAddress() + " cipherSuite=" + (this.handshake != null ? this.handshake.cipherSuite() : "none") + " protocol=" + this.protocol + '}';
    }
}
