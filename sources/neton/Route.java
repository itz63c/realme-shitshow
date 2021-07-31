package neton;

import com.coloros.neton.NetonException;
import java.net.InetSocketAddress;
import java.net.Proxy;

public final class Route {
    final Address address;
    final InetSocketAddress inetSocketAddress;
    final Proxy proxy;

    public Route(Address address2, Proxy proxy2, InetSocketAddress inetSocketAddress2) {
        if (address2 == null) {
            throw new NetonException((Throwable) new NullPointerException("address == null"));
        } else if (proxy2 == null) {
            throw new NetonException((Throwable) new NullPointerException("proxy == null"));
        } else if (inetSocketAddress2 == null) {
            throw new NetonException((Throwable) new NullPointerException("inetSocketAddress == null"));
        } else {
            this.address = address2;
            this.proxy = proxy2;
            this.inetSocketAddress = inetSocketAddress2;
        }
    }

    public final Address address() {
        return this.address;
    }

    public final Proxy proxy() {
        return this.proxy;
    }

    public final InetSocketAddress socketAddress() {
        return this.inetSocketAddress;
    }

    public final boolean requiresTunnel() {
        return this.address.sslSocketFactory != null && this.proxy.type() == Proxy.Type.HTTP;
    }

    public final boolean equals(Object obj) {
        return (obj instanceof Route) && ((Route) obj).address.equals(this.address) && ((Route) obj).proxy.equals(this.proxy) && ((Route) obj).inetSocketAddress.equals(this.inetSocketAddress);
    }

    public final int hashCode() {
        return ((((this.address.hashCode() + 527) * 31) + this.proxy.hashCode()) * 31) + this.inetSocketAddress.hashCode();
    }

    public final String toString() {
        return "Route{" + this.inetSocketAddress + "}";
    }
}
