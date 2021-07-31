package neton.internal.http;

import java.io.IOException;
import java.net.ProtocolException;
import neton.Interceptor;
import neton.Request;
import neton.Response;
import neton.internal.Util;
import neton.internal.connection.RealConnection;
import neton.internal.connection.StreamAllocation;
import okio.BufferedSink;
import okio.Okio;

public final class CallServerInterceptor implements Interceptor {
    private final boolean forWebSocket;

    public CallServerInterceptor(boolean z) {
        this.forWebSocket = z;
    }

    public final Response intercept(Interceptor.Chain chain) {
        Response.Builder builder;
        Response build;
        Response.Builder builder2 = null;
        RealInterceptorChain realInterceptorChain = (RealInterceptorChain) chain;
        HttpCodec httpStream = realInterceptorChain.httpStream();
        StreamAllocation streamAllocation = realInterceptorChain.streamAllocation();
        RealConnection realConnection = (RealConnection) realInterceptorChain.connection();
        Request request = realInterceptorChain.request();
        long currentTimeMillis = System.currentTimeMillis();
        realInterceptorChain.eventListener().requestHeadersStart(realInterceptorChain.call());
        try {
            httpStream.writeRequestHeaders(request);
            realInterceptorChain.eventListener().requestHeadersEnd(realInterceptorChain.call(), (Throwable) null);
            if (!HttpMethod.permitsRequestBody(request.method()) || request.body() == null) {
                builder = null;
            } else {
                realInterceptorChain.eventListener().requestBodyStart(realInterceptorChain.call());
                try {
                    if ("100-continue".equalsIgnoreCase(request.header("Expect"))) {
                        httpStream.flushRequest();
                        builder2 = httpStream.readResponseHeaders(true);
                    }
                    if (builder2 == null) {
                        BufferedSink buffer = Okio.buffer(httpStream.createRequestBody(request, request.body().contentLength()));
                        request.body().writeTo(buffer);
                        buffer.close();
                    } else if (!realConnection.isMultiplexed()) {
                        streamAllocation.noNewStreams();
                    }
                    realInterceptorChain.eventListener().requestBodyEnd(realInterceptorChain.call(), (Throwable) null);
                    builder = builder2;
                } catch (IOException e) {
                    realInterceptorChain.eventListener().requestBodyEnd(realInterceptorChain.call(), e);
                    throw e;
                }
            }
            httpStream.finishRequest();
            if (builder == null) {
                realInterceptorChain.eventListener().responseHeadersStart(realInterceptorChain.call());
                try {
                    builder = httpStream.readResponseHeaders(false);
                    realInterceptorChain.eventListener().responseHeadersEnd(realInterceptorChain.call(), (Throwable) null);
                } catch (IOException e2) {
                    realInterceptorChain.eventListener().responseHeadersEnd(realInterceptorChain.call(), e2);
                    throw e2;
                }
            }
            Response build2 = builder.request(request).handshake(streamAllocation.connection().handshake()).sentRequestAtMillis(currentTimeMillis).receivedResponseAtMillis(System.currentTimeMillis()).build();
            int code = build2.code();
            if (!this.forWebSocket || code != 101) {
                build = build2.newBuilder().body(httpStream.openResponseBody(build2)).build();
            } else {
                build = build2.newBuilder().body(Util.EMPTY_RESPONSE).build();
            }
            if ("close".equalsIgnoreCase(build.request().header("Connection")) || "close".equalsIgnoreCase(build.header("Connection"))) {
                streamAllocation.noNewStreams();
            }
            if ((code != 204 && code != 205) || build.body().contentLength() <= 0) {
                return build;
            }
            throw new ProtocolException("HTTP " + code + " had non-zero Content-Length: " + build.body().contentLength());
        } catch (IOException e3) {
            realInterceptorChain.eventListener().requestHeadersEnd(realInterceptorChain.call(), e3);
            throw e3;
        }
    }
}
