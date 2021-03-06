package neton;

public interface Authenticator {
    public static final Authenticator NONE = new Authenticator() {
        public final Request authenticate(Route route, Response response) {
            return null;
        }
    };

    Request authenticate(Route route, Response response);
}
