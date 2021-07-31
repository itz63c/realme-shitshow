package com.coloros.neton;

import java.io.IOException;

public class NetonException extends IOException {
    static final long serialVersionUID = 1;
    private Class<?> mExceptionClass = IOException.class;

    public Class<?> getExceptionClass() {
        return this.mExceptionClass;
    }

    public NetonException() {
    }

    public NetonException(String str) {
        super(str);
    }

    public NetonException(String str, Throwable th) {
        super(str, th);
        this.mExceptionClass = th.getClass();
    }

    public NetonException(Throwable th) {
        super(th);
        this.mExceptionClass = th.getClass();
    }
}
