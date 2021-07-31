package neton;

import com.coloros.neton.NetonException;
import java.net.Proxy;
import java.net.ProxySelector;
import java.util.List;
import javax.net.SocketFactory;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSocketFactory;
import neton.HttpUrl;
import neton.client.statistics.RequestStatistic;
import neton.internal.Util;

public final class Address {
    final CertificatePinner certificatePinner;
    final List<ConnectionSpec> connectionSpecs;
    final Dns dns;
    final HostnameVerifier hostnameVerifier;
    final List<Protocol> protocols;
    final Proxy proxy;
    final Authenticator proxyAuthenticator;
    final ProxySelector proxySelector;
    final RequestStatistic requestStatistic;
    final SocketFactory socketFactory;
    final SSLSocketFactory sslSocketFactory;
    final HttpUrl url;

    public Address(RequestStatistic requestStatistic2, String str, String str2, int i, Dns dns2, SocketFactory socketFactory2, SSLSocketFactory sSLSocketFactory, HostnameVerifier hostnameVerifier2, CertificatePinner certificatePinner2, Authenticator authenticator, Proxy proxy2, List<Protocol> list, List<ConnectionSpec> list2, ProxySelector proxySelector2) {
        this.url = new HttpUrl.Builder().scheme(sSLSocketFactory != null ? "https" : "http").host(str2).mo760ip(str).port(i).build();
        this.requestStatistic = requestStatistic2;
        if (dns2 == null) {
            throw new NetonException((Throwable) new NullPointerException("dns == null"));
        }
        this.dns = dns2;
        if (socketFactory2 == null) {
            throw new NetonException((Throwable) new NullPointerException("socketFactory == null"));
        }
        this.socketFactory = socketFactory2;
        if (authenticator == null) {
            throw new NetonException((Throwable) new NullPointerException("proxyAuthenticator == null"));
        }
        this.proxyAuthenticator = authenticator;
        if (list == null) {
            throw new NetonException((Throwable) new NullPointerException("protocols == null"));
        }
        this.protocols = Util.immutableList(list);
        if (list2 == null) {
            throw new NetonException((Throwable) new NullPointerException("connectionSpecs == null"));
        }
        this.connectionSpecs = Util.immutableList(list2);
        if (proxySelector2 == null) {
            throw new NetonException((Throwable) new NullPointerException("proxySelector == null"));
        }
        this.proxySelector = proxySelector2;
        this.proxy = proxy2;
        this.sslSocketFactory = sSLSocketFactory;
        this.hostnameVerifier = hostnameVerifier2;
        this.certificatePinner = certificatePinner2;
    }

    public final RequestStatistic requestStatistic() {
        return this.requestStatistic;
    }

    public final HttpUrl url() {
        return this.url;
    }

    public final Dns dns() {
        return this.dns;
    }

    public final SocketFactory socketFactory() {
        return this.socketFactory;
    }

    public final Authenticator proxyAuthenticator() {
        return this.proxyAuthenticator;
    }

    public final List<Protocol> protocols() {
        return this.protocols;
    }

    public final List<ConnectionSpec> connectionSpecs() {
        return this.connectionSpecs;
    }

    public final ProxySelector proxySelector() {
        return this.proxySelector;
    }

    public final Proxy proxy() {
        return this.proxy;
    }

    public final SSLSocketFactory sslSocketFactory() {
        return this.sslSocketFactory;
    }

    public final HostnameVerifier hostnameVerifier() {
        return this.hostnameVerifier;
    }

    public final CertificatePinner certificatePinner() {
        return this.certificatePinner;
    }

    public final boolean equals(Object obj) {
        return (obj instanceof Address) && this.url.equals(((Address) obj).url) && equalsNonHost((Address) obj);
    }

    public final int hashCode() {
        int i;
        int i2;
        int i3;
        int i4 = 0;
        int hashCode = (((((((((((this.url.hashCode() + 527) * 31) + this.dns.hashCode()) * 31) + this.proxyAuthenticator.hashCode()) * 31) + this.protocols.hashCode()) * 31) + this.connectionSpecs.hashCode()) * 31) + this.proxySelector.hashCode()) * 31;
        if (this.proxy != null) {
            i = this.proxy.hashCode();
        } else {
            i = 0;
        }
        int i5 = (i + hashCode) * 31;
        if (this.sslSocketFactory != null) {
            i2 = this.sslSocketFactory.hashCode();
        } else {
            i2 = 0;
        }
        int i6 = (i2 + i5) * 31;
        if (this.hostnameVerifier != null) {
            i3 = this.hostnameVerifier.hashCode();
        } else {
            i3 = 0;
        }
        int i7 = (i3 + i6) * 31;
        if (this.certificatePinner != null) {
            i4 = this.certificatePinner.hashCode();
        }
        return i7 + i4;
    }

    /* access modifiers changed from: package-private */
    public final boolean equalsNonHost(Address address) {
        return this.dns.equals(address.dns) && this.proxyAuthenticator.equals(address.proxyAuthenticator) && this.protocols.equals(address.protocols) && this.connectionSpecs.equals(address.connectionSpecs) && this.proxySelector.equals(address.proxySelector) && Util.equal(this.proxy, address.proxy) && Util.equal(this.sslSocketFactory, address.sslSocketFactory) && Util.equal(this.hostnameVerifier, address.hostnameVerifier) && Util.equal(this.certificatePinner, address.certificatePinner) && url().port() == address.url().port();
    }

    public final String toString() {
        StringBuilder append = new StringBuilder("Address{").append(this.url.host()).append(":").append(this.url.port());
        if (this.proxy != null) {
            append.append(", proxy=").append(this.proxy);
        } else {
            append.append(", proxySelector=").append(this.proxySelector);
        }
        append.append("}");
        return append.toString();
    }
}
