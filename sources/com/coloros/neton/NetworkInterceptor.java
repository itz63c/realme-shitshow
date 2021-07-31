package com.coloros.neton;

import neton.Interceptor;
import neton.Request;
import neton.Response;
import neton.client.Utils.LogUtil;

public class NetworkInterceptor implements Interceptor {
    public Response intercept(Interceptor.Chain chain) {
        Request request = chain.request();
        long nanoTime = System.nanoTime();
        LogUtil.m787d(String.format("NetworkInterceptor--send request: [%s] %s%n%s", new Object[]{request.url(), chain.connection(), request.headers()}));
        Response proceed = chain.proceed(request);
        LogUtil.m787d(String.format("NetworkInterceptor--receive response: [%s] %.1fms%n%s", new Object[]{proceed.request().url(), Double.valueOf(((double) (System.nanoTime() - nanoTime)) / 1000000.0d), proceed.code() + "-" + proceed.message()}));
        return proceed;
    }
}
