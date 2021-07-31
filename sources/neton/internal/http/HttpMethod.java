package neton.internal.http;

public final class HttpMethod {
    public static final String CONNECT = "CONNECT";
    public static final String COPY = "COPY";
    public static final String DELETE = "DELETE";
    public static final String GET = "GET";
    public static final String HEAD = "HEAD";
    public static final String LOCK = "LOCK";
    public static final String MKCOL = "MKCOL";
    public static final String MOVE = "MOVE";
    public static final String OPTIONS = "OPTIONS";
    public static final String POST = "POST";
    public static final String PUT = "PUT";
    public static final String TRACE = "TRACE";

    public static boolean invalidatesCache(String str) {
        return str.equals(POST) || str.equals("PATCH") || str.equals(PUT) || str.equals(DELETE) || str.equals(MOVE);
    }

    public static boolean requiresRequestBody(String str) {
        return str.equals(POST) || str.equals(PUT) || str.equals("PATCH") || str.equals("PROPPATCH") || str.equals("REPORT");
    }

    public static boolean permitsRequestBody(String str) {
        return requiresRequestBody(str) || str.equals(OPTIONS) || str.equals(DELETE) || str.equals("PROPFIND") || str.equals(MKCOL) || str.equals(LOCK);
    }

    public static boolean redirectsWithBody(String str) {
        return str.equals("PROPFIND");
    }

    public static boolean redirectsToGet(String str) {
        return !str.equals("PROPFIND");
    }

    private HttpMethod() {
    }
}
