package com.coloros.neton;

import neton.Interceptor;
import neton.Request;
import neton.Response;
import neton.client.Utils.LogUtil;

public class LoggingInterceptor implements Interceptor {
    public Response intercept(Interceptor.Chain chain) {
        Request request = chain.request();
        long nanoTime = System.nanoTime();
        if (LogUtil.isDebugs()) {
            LogUtil.m787d(String.format("LoggingInterceptor--send request: [%s] %s%n%s", new Object[]{request, chain.connection(), request.headers()}));
        }
        Response proceed = chain.proceed(request);
        long nanoTime2 = System.nanoTime();
        if (LogUtil.isDebugs()) {
            LogUtil.m787d(String.format("LoggingInterceptor--receive response: [%s] %.1fms%n%s", new Object[]{proceed, Double.valueOf(((double) (nanoTime2 - nanoTime)) / 1000000.0d), proceed.headers()}));
        }
        return proceed;
    }
}
