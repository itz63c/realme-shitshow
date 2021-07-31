package neton.client.cookie;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import neton.Cookie;

public class SerializableOkHttpCookies implements Serializable {
    private transient Cookie clientCookies;
    private final transient Cookie cookies;

    public SerializableOkHttpCookies(Cookie cookie) {
        this.cookies = cookie;
    }

    public Cookie getCookies() {
        Cookie cookie = this.cookies;
        if (this.clientCookies != null) {
            return this.clientCookies;
        }
        return cookie;
    }

    private void writeObject(ObjectOutputStream objectOutputStream) {
        objectOutputStream.writeObject(this.cookies.name());
        objectOutputStream.writeObject(this.cookies.value());
        objectOutputStream.writeLong(this.cookies.expiresAt());
        objectOutputStream.writeObject(this.cookies.domain());
        objectOutputStream.writeObject(this.cookies.path());
        objectOutputStream.writeBoolean(this.cookies.secure());
        objectOutputStream.writeBoolean(this.cookies.httpOnly());
        objectOutputStream.writeBoolean(this.cookies.hostOnly());
        objectOutputStream.writeBoolean(this.cookies.persistent());
    }

    private void readObject(ObjectInputStream objectInputStream) {
        long readLong = objectInputStream.readLong();
        String str = (String) objectInputStream.readObject();
        String str2 = (String) objectInputStream.readObject();
        boolean readBoolean = objectInputStream.readBoolean();
        boolean readBoolean2 = objectInputStream.readBoolean();
        boolean readBoolean3 = objectInputStream.readBoolean();
        objectInputStream.readBoolean();
        Cookie.Builder expiresAt = new Cookie.Builder().name((String) objectInputStream.readObject()).value((String) objectInputStream.readObject()).expiresAt(readLong);
        Cookie.Builder path = (readBoolean3 ? expiresAt.hostOnlyDomain(str) : expiresAt.domain(str)).path(str2);
        if (readBoolean) {
            path = path.secure();
        }
        if (readBoolean2) {
            path = path.httpOnly();
        }
        this.clientCookies = path.build();
    }
}
