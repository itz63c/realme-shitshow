package neton.internal.platform;

import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.X509TrustManager;
import neton.OkHttpClient;
import neton.Protocol;
import neton.internal.tls.BasicCertificateChainCleaner;
import neton.internal.tls.BasicTrustRootIndex;
import neton.internal.tls.CertificateChainCleaner;
import neton.internal.tls.TrustRootIndex;
import okio.Buffer;

public class Platform {
    public static final int INFO = 4;
    private static final Platform PLATFORM = findPlatform();
    public static final int WARN = 5;
    private static final Logger logger = Logger.getLogger(OkHttpClient.class.getName());

    public static Platform get() {
        return PLATFORM;
    }

    public String getPrefix() {
        return "OkHttp";
    }

    public X509TrustManager trustManager(SSLSocketFactory sSLSocketFactory) {
        try {
            Object readFieldOrNull = readFieldOrNull(sSLSocketFactory, Class.forName("sun.security.ssl.SSLContextImpl"), "context");
            if (readFieldOrNull == null) {
                return null;
            }
            return (X509TrustManager) readFieldOrNull(readFieldOrNull, X509TrustManager.class, "trustManager");
        } catch (ClassNotFoundException e) {
            return null;
        }
    }

    public void configureTlsExtensions(SSLSocket sSLSocket, String str, List<Protocol> list) {
    }

    public void afterHandshake(SSLSocket sSLSocket) {
    }

    public String getSelectedProtocol(SSLSocket sSLSocket) {
        return null;
    }

    public void connectSocket(Socket socket, InetSocketAddress inetSocketAddress, int i) {
        socket.connect(inetSocketAddress, i);
    }

    public void log(int i, String str, Throwable th) {
        logger.log(i == 5 ? Level.WARNING : Level.INFO, str, th);
    }

    public boolean isCleartextTrafficPermitted(String str) {
        return true;
    }

    public Object getStackTraceForCloseable(String str) {
        if (logger.isLoggable(Level.FINE)) {
            return new Throwable(str);
        }
        return null;
    }

    public void logCloseableLeak(String str, Object obj) {
        if (obj == null) {
            str = str + " To see where this was allocated, set the OkHttpClient logger level to FINE: Logger.getLogger(OkHttpClient.class.getName()).setLevel(Level.FINE);";
        }
        log(5, str, (Throwable) obj);
    }

    public static List<String> alpnProtocolNames(List<Protocol> list) {
        ArrayList arrayList = new ArrayList(list.size());
        int size = list.size();
        for (int i = 0; i < size; i++) {
            Protocol protocol = list.get(i);
            if (protocol != Protocol.HTTP_1_0) {
                arrayList.add(protocol.toString());
            }
        }
        return arrayList;
    }

    public CertificateChainCleaner buildCertificateChainCleaner(X509TrustManager x509TrustManager) {
        return new BasicCertificateChainCleaner(buildTrustRootIndex(x509TrustManager));
    }

    private static Platform findPlatform() {
        Platform buildIfSupported = AndroidPlatform.buildIfSupported();
        if (buildIfSupported != null) {
            return buildIfSupported;
        }
        Jdk9Platform buildIfSupported2 = Jdk9Platform.buildIfSupported();
        if (buildIfSupported2 != null) {
            return buildIfSupported2;
        }
        Platform buildIfSupported3 = JdkWithJettyBootPlatform.buildIfSupported();
        return buildIfSupported3 == null ? new Platform() : buildIfSupported3;
    }

    static byte[] concatLengthPrefixed(List<Protocol> list) {
        Buffer buffer = new Buffer();
        int size = list.size();
        for (int i = 0; i < size; i++) {
            Protocol protocol = list.get(i);
            if (protocol != Protocol.HTTP_1_0) {
                buffer.writeByte(protocol.toString().length());
                buffer.writeUtf8(protocol.toString());
            }
        }
        return buffer.readByteArray();
    }

    /* JADX WARNING: Removed duplicated region for block: B:4:0x0009 A[SYNTHETIC, Splitter:B:4:0x0009] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static <T> T readFieldOrNull(java.lang.Object r4, java.lang.Class<T> r5, java.lang.String r6) {
        /*
            r1 = 0
        L_0x0001:
            java.lang.Class r0 = r4.getClass()
        L_0x0005:
            java.lang.Class<java.lang.Object> r2 = java.lang.Object.class
            if (r0 == r2) goto L_0x0031
            java.lang.reflect.Field r2 = r0.getDeclaredField(r6)     // Catch:{ NoSuchFieldException -> 0x002b, IllegalAccessException -> 0x0024 }
            r3 = 1
            r2.setAccessible(r3)     // Catch:{ NoSuchFieldException -> 0x002b, IllegalAccessException -> 0x0024 }
            java.lang.Object r2 = r2.get(r4)     // Catch:{ NoSuchFieldException -> 0x002b, IllegalAccessException -> 0x0024 }
            if (r2 == 0) goto L_0x001d
            boolean r3 = r5.isInstance(r2)     // Catch:{ NoSuchFieldException -> 0x002b, IllegalAccessException -> 0x0024 }
            if (r3 != 0) goto L_0x001f
        L_0x001d:
            r0 = r1
        L_0x001e:
            return r0
        L_0x001f:
            java.lang.Object r0 = r5.cast(r2)     // Catch:{ NoSuchFieldException -> 0x002b, IllegalAccessException -> 0x0024 }
            goto L_0x001e
        L_0x0024:
            r0 = move-exception
            java.lang.AssertionError r0 = new java.lang.AssertionError
            r0.<init>()
            throw r0
        L_0x002b:
            r2 = move-exception
            java.lang.Class r0 = r0.getSuperclass()
            goto L_0x0005
        L_0x0031:
            java.lang.String r0 = "delegate"
            boolean r0 = r6.equals(r0)
            if (r0 != 0) goto L_0x0043
            java.lang.Class<java.lang.Object> r0 = java.lang.Object.class
            java.lang.String r2 = "delegate"
            java.lang.Object r4 = readFieldOrNull(r4, r0, r2)
            if (r4 != 0) goto L_0x0001
        L_0x0043:
            r0 = r1
            goto L_0x001e
        */
        throw new UnsupportedOperationException("Method not decompiled: neton.internal.platform.Platform.readFieldOrNull(java.lang.Object, java.lang.Class, java.lang.String):java.lang.Object");
    }

    public TrustRootIndex buildTrustRootIndex(X509TrustManager x509TrustManager) {
        return new BasicTrustRootIndex(x509TrustManager.getAcceptedIssuers());
    }
}
