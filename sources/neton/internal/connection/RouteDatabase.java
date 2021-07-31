package neton.internal.connection;

import java.util.LinkedHashSet;
import java.util.Set;
import neton.Route;

public final class RouteDatabase {
    private final Set<Route> failedRoutes = new LinkedHashSet();

    public final synchronized void failed(Route route) {
        this.failedRoutes.add(route);
    }

    public final synchronized void connected(Route route) {
        this.failedRoutes.remove(route);
    }

    public final synchronized boolean shouldPostpone(Route route) {
        return this.failedRoutes.contains(route);
    }
}
