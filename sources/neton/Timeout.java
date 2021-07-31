package neton;

import com.coloros.neton.NetonException;
import java.util.concurrent.TimeUnit;

public class Timeout {
    private int connectTimeout = 10000;
    private int readTimeout = 10000;
    private TimeUnit timeUnit = TimeUnit.MILLISECONDS;
    private int writeTimeout = 10000;

    public Timeout(int i, int i2, int i3, TimeUnit timeUnit2) {
        this.timeUnit = timeUnit2;
        setConnectTimeout(i);
        setReadTimeout(i2);
        setWriteTimeout(i3);
    }

    public Timeout(int i, int i2, int i3) {
        setConnectTimeout(i);
        setReadTimeout(i2);
        setWriteTimeout(i3);
    }

    public Timeout() {
    }

    public int getConnectTimeout() {
        return this.connectTimeout;
    }

    public void setConnectTimeout(int i) {
        if (i < 0) {
            throw new NetonException((Throwable) new IllegalArgumentException("timeout < 0"));
        } else if (this.timeUnit == null) {
            throw new NetonException((Throwable) new NullPointerException("unit == null"));
        } else {
            long millis = this.timeUnit.toMillis((long) i);
            if (millis > 2147483647L) {
                throw new NetonException((Throwable) new IllegalArgumentException("Timeout too large."));
            } else if (millis != 0 || i <= 0) {
                this.connectTimeout = (int) millis;
            } else {
                throw new NetonException((Throwable) new IllegalArgumentException("Timeout too small."));
            }
        }
    }

    public int getReadTimeout() {
        return this.readTimeout;
    }

    public void setReadTimeout(int i) {
        if (i < 0) {
            throw new NetonException((Throwable) new IllegalArgumentException("timeout < 0"));
        } else if (this.timeUnit == null) {
            throw new NetonException((Throwable) new NullPointerException("unit == null"));
        } else {
            long millis = this.timeUnit.toMillis((long) i);
            if (millis > 2147483647L) {
                throw new NetonException((Throwable) new IllegalArgumentException("Timeout too large."));
            } else if (millis != 0 || i <= 0) {
                this.readTimeout = (int) millis;
            } else {
                throw new NetonException((Throwable) new IllegalArgumentException("Timeout too small."));
            }
        }
    }

    public int getWriteTimeout() {
        return this.writeTimeout;
    }

    public void setWriteTimeout(int i) {
        if (i < 0) {
            throw new NetonException((Throwable) new IllegalArgumentException("timeout < 0"));
        } else if (this.timeUnit == null) {
            throw new NetonException((Throwable) new NullPointerException("unit == null"));
        } else {
            long millis = this.timeUnit.toMillis((long) i);
            if (millis > 2147483647L) {
                throw new NetonException((Throwable) new IllegalArgumentException("Timeout too large."));
            } else if (millis != 0 || i <= 0) {
                this.writeTimeout = (int) millis;
            } else {
                throw new NetonException((Throwable) new IllegalArgumentException("Timeout too small."));
            }
        }
    }

    public String toString() {
        return "Timeout{connectTimeout:" + this.connectTimeout + "readTimeout" + this.readTimeout + "writeTimeout" + this.writeTimeout + "}";
    }
}
