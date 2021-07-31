package neton;

import com.coloros.neton.NetonException;

public final class Challenge {
    private final String realm;
    private final String scheme;

    public Challenge(String str, String str2) {
        if (str == null) {
            throw new NetonException((Throwable) new NullPointerException("scheme == null"));
        } else if (str2 == null) {
            throw new NetonException((Throwable) new NullPointerException("realm == null"));
        } else {
            this.scheme = str;
            this.realm = str2;
        }
    }

    public final String scheme() {
        return this.scheme;
    }

    public final String realm() {
        return this.realm;
    }

    public final boolean equals(Object obj) {
        return (obj instanceof Challenge) && ((Challenge) obj).scheme.equals(this.scheme) && ((Challenge) obj).realm.equals(this.realm);
    }

    public final int hashCode() {
        return ((this.realm.hashCode() + 899) * 31) + this.scheme.hashCode();
    }

    public final String toString() {
        return this.scheme + " realm=\"" + this.realm + "\"";
    }
}
