package neton.internal.connection;

import com.coloros.neton.BuildConfig;
import com.coloros.neton.NetonException;
import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.SocketAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import neton.Address;
import neton.Call;
import neton.EventListener;
import neton.HttpUrl;
import neton.Route;
import neton.internal.Util;

public final class RouteSelector {
    private final Address address;
    private final Call call;
    private final EventListener eventListener;
    private List<InetSocketAddress> inetSocketAddresses = Collections.emptyList();
    private int nextProxyIndex;
    private final List<Route> postponedRoutes = new ArrayList();
    private List<Proxy> proxies = Collections.emptyList();
    private final RouteDatabase routeDatabase;

    public RouteSelector(Address address2, RouteDatabase routeDatabase2, Call call2, EventListener eventListener2) {
        this.address = address2;
        this.routeDatabase = routeDatabase2;
        this.call = call2;
        this.eventListener = eventListener2;
        resetNextProxy(address2.url(), address2.proxy());
    }

    public final boolean hasNext() {
        return hasNextProxy() || !this.postponedRoutes.isEmpty();
    }

    public final Selection next() {
        if (!hasNext()) {
            throw new NetonException((Throwable) new NoSuchElementException());
        }
        ArrayList arrayList = new ArrayList();
        while (hasNextProxy()) {
            Proxy nextProxy = nextProxy();
            int size = this.inetSocketAddresses.size();
            for (int i = 0; i < size; i++) {
                Route route = new Route(this.address, nextProxy, this.inetSocketAddresses.get(i));
                if (this.routeDatabase.shouldPostpone(route)) {
                    this.postponedRoutes.add(route);
                } else {
                    arrayList.add(route);
                }
            }
            if (!arrayList.isEmpty()) {
                break;
            }
        }
        if (arrayList.isEmpty()) {
            arrayList.addAll(this.postponedRoutes);
            this.postponedRoutes.clear();
        }
        return new Selection(arrayList);
    }

    public final void connectFailed(Route route, IOException iOException) {
        if (!(route.proxy().type() == Proxy.Type.DIRECT || this.address.proxySelector() == null)) {
            this.address.proxySelector().connectFailed(this.address.url().uri(), route.proxy().address(), iOException);
        }
        this.routeDatabase.failed(route);
    }

    private void resetNextProxy(HttpUrl httpUrl, Proxy proxy) {
        List<Proxy> immutableList;
        RouteSelector routeSelector;
        if (proxy != null) {
            immutableList = Collections.singletonList(proxy);
            routeSelector = this;
        } else {
            List<Proxy> select = this.address.proxySelector().select(httpUrl.uri());
            if (select == null || select.isEmpty()) {
                immutableList = Util.immutableList((T[]) new Proxy[]{Proxy.NO_PROXY});
                routeSelector = this;
            } else {
                immutableList = Util.immutableList(select);
                routeSelector = this;
            }
        }
        routeSelector.proxies = immutableList;
        this.nextProxyIndex = 0;
    }

    private boolean hasNextProxy() {
        return this.nextProxyIndex < this.proxies.size();
    }

    private Proxy nextProxy() {
        if (!hasNextProxy()) {
            throw new SocketException("No route to " + this.address.url().host() + "; exhausted proxy configurations: " + this.proxies);
        }
        List<Proxy> list = this.proxies;
        int i = this.nextProxyIndex;
        this.nextProxyIndex = i + 1;
        Proxy proxy = list.get(i);
        resetNextInetSocketAddress(proxy);
        return proxy;
    }

    private void resetNextInetSocketAddress(Proxy proxy) {
        int i;
        String str;
        this.inetSocketAddresses = new ArrayList();
        if (proxy.type() == Proxy.Type.DIRECT || proxy.type() == Proxy.Type.SOCKS) {
            str = this.address.url().host();
            i = this.address.url().port();
        } else {
            SocketAddress address2 = proxy.address();
            if (!(address2 instanceof InetSocketAddress)) {
                throw new NetonException((Throwable) new IllegalArgumentException("Proxy.address() is not an InetSocketAddress: " + address2.getClass()));
            }
            InetSocketAddress inetSocketAddress = (InetSocketAddress) address2;
            str = getHostString(inetSocketAddress);
            i = inetSocketAddress.getPort();
        }
        if (i <= 0 || i > 65535) {
            throw new SocketException("No route to " + str + ":" + i + "; port is out of range");
        } else if (proxy.type() == Proxy.Type.SOCKS) {
            this.inetSocketAddresses.add(InetSocketAddress.createUnresolved(str, i));
        } else if (proxy.type() != Proxy.Type.DIRECT || this.address.url().mo722ip() == null || BuildConfig.FLAVOR == this.address.url().mo722ip()) {
            this.eventListener.dnsStart(this.call, str);
            try {
                List<InetAddress> lookup = this.address.dns().lookup(str);
                if (lookup.isEmpty()) {
                    UnknownHostException unknownHostException = new UnknownHostException(this.address.dns() + " returned no addresses for " + str);
                    this.eventListener.dnsEnd(this.call, str, (List<InetAddress>) null, unknownHostException);
                    throw unknownHostException;
                }
                this.eventListener.dnsEnd(this.call, str, lookup, (Throwable) null);
                int size = lookup.size();
                for (int i2 = 0; i2 < size; i2++) {
                    this.inetSocketAddresses.add(new InetSocketAddress(lookup.get(i2), i));
                }
            } catch (Exception e) {
                this.eventListener.dnsEnd(this.call, str, (List<InetAddress>) null, e);
                throw e;
            }
        } else {
            this.inetSocketAddresses.add(new InetSocketAddress(this.address.url().mo722ip(), i));
        }
    }

    static String getHostString(InetSocketAddress inetSocketAddress) {
        InetAddress address2 = inetSocketAddress.getAddress();
        if (address2 == null) {
            return inetSocketAddress.getHostName();
        }
        return address2.getHostAddress();
    }

    public final class Selection {
        private int nextRouteIndex = 0;
        private final List<Route> routes;

        Selection(List<Route> list) {
            this.routes = list;
        }

        public final boolean hasNext() {
            return this.nextRouteIndex < this.routes.size();
        }

        public final Route next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            List<Route> list = this.routes;
            int i = this.nextRouteIndex;
            this.nextRouteIndex = i + 1;
            return list.get(i);
        }

        public final List<Route> getAll() {
            return new ArrayList(this.routes);
        }
    }
}
