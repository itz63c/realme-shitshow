package neton.internal.tls;

import android.net.SSLSessionCache;
import android.os.Build;
import com.coloros.neton.NetonException;
import java.security.GeneralSecurityException;
import java.security.KeyStore;
import java.security.SecureRandom;
import java.util.Arrays;
import javax.net.ssl.KeyManager;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;
import neton.client.config.ConfigManager;
import neton.client.config.Constants;

public class NetonSslSocketFactory {
    public static SSLSocketFactory createNetonSslSocketFactory(TrustManager trustManager, SSLSessionCache sSLSessionCache) {
        SSLContext instance = SSLContext.getInstance("TLS");
        instance.init((KeyManager[]) null, new TrustManager[]{trustManager}, (SecureRandom) null);
        instance.getClientSessionContext().setSessionCacheSize(ConfigManager.getInstance().getIntData(Constants.KEY_SESSION_CACHE_SIZE, 0));
        instance.getClientSessionContext().setSessionTimeout(ConfigManager.getInstance().getIntData(Constants.KEY_SESSION_TIMEOUT, Constants.DEFAULT_SESSION_TIMEOUT));
        if (sSLSessionCache != null && Build.VERSION.SDK_INT > 19) {
            try {
                SSLSessionCache.install(sSLSessionCache, instance);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return instance.getSocketFactory();
    }

    public static X509TrustManager systemDefaultTrustManager() {
        try {
            TrustManagerFactory instance = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
            instance.init((KeyStore) null);
            TrustManager[] trustManagers = instance.getTrustManagers();
            if (trustManagers.length == 1 && (trustManagers[0] instanceof X509TrustManager)) {
                return (X509TrustManager) trustManagers[0];
            }
            throw new NetonException((Throwable) new IllegalStateException("Unexpected default trust managers:" + Arrays.toString(trustManagers)));
        } catch (GeneralSecurityException e) {
            throw new AssertionError();
        }
    }
}
