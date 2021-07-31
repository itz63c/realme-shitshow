package com.coloros.neton;

import android.content.Context;
import android.content.Intent;
import java.io.File;
import java.io.IOException;
import java.util.Map;
import neton.Call;
import neton.Callback;
import neton.Headers;
import neton.MediaType;
import neton.Request;
import neton.RequestBody;
import neton.Response;
import neton.Timeout;
import neton.client.Utils.LogUtil;
import neton.client.config.ConfigManager;
import neton.client.config.NetonReceiver;
import neton.client.dns.DnsManager;

public class NetonClient {
    private static Processor sMProcessor;
    private Context mContext;

    class InstanceHolder {
        static NetonClient ourInstance = new NetonClient();

        private InstanceHolder() {
        }
    }

    public static NetonClient getInstance() {
        return InstanceHolder.ourInstance;
    }

    private NetonClient() {
    }

    public Context getContext() {
        return this.mContext;
    }

    public synchronized void init(Context context) {
        init(context, (NetonConfig) null);
    }

    public synchronized void init(Context context, NetonConfig netonConfig) {
        if (this.mContext == null) {
            try {
                this.mContext = context.getApplicationContext();
                ConfigManager.getInstance().init(this.mContext);
                DnsManager.getInstance().init(this.mContext);
                sMProcessor = new Processor(this.mContext, netonConfig);
            } catch (Throwable th) {
                LogUtil.m787d("init fatal error exception:" + th.toString());
                throw new NetonException(th);
            }
        }
    }

    public static Processor getProcessor() {
        checkInit();
        return sMProcessor;
    }

    private static void checkInit() {
        if (sMProcessor == null) {
            throw new NetonException((Throwable) new IllegalStateException("please init Neton first,or set region code when use http dns"));
        }
    }

    public static Response execute(Request request) {
        checkInit();
        try {
            return sMProcessor.process(request, false);
        } catch (IOException e) {
            throw e;
        } catch (Exception e2) {
            throw new NetonException((Throwable) e2);
        }
    }

    public static void executeAsync(Request request, Callback callback) {
        checkInit();
        try {
            sMProcessor.process(request, callback, false);
        } catch (Exception e) {
            throw new NetonException((Throwable) e);
        }
    }

    public static Response get(String str) {
        return execute(new Request.Builder().get().url(str).build());
    }

    public static String getString(String str) {
        return execute(new Request.Builder().get().url(str).build()).body().string();
    }

    public static void getAsync(String str, Callback callback) {
        executeAsync(new Request.Builder().url(str).build(), callback);
    }

    public static void getAsync(String str, Timeout timeout, Callback callback) {
        executeAsync(new Request.Builder().timeout(timeout).url(str).build(), callback);
    }

    public static Response post(String str, String str2) {
        return execute(new Request.Builder().post(RequestBody.create(MediaType.parse(MediaType.TEXT_PLAIN), str2)).url(str).build());
    }

    public static Response post(String str, byte[] bArr) {
        return execute(new Request.Builder().post(RequestBody.create(MediaType.parse(MediaType.OCTET_STREAM), bArr)).url(str).build());
    }

    public static void postAsync(String str, String str2, Callback callback) {
        executeAsync(new Request.Builder().post(RequestBody.create(MediaType.parse(MediaType.TEXT_PLAIN), str2)).url(str).build(), callback);
    }

    public static void postAsync(String str, byte[] bArr, Callback callback) {
        executeAsync(new Request.Builder().post(RequestBody.create(MediaType.parse(MediaType.OCTET_STREAM), bArr)).url(str).build(), callback);
    }

    public static void postAsync(String str, String str2, byte[] bArr, Timeout timeout, Callback callback) {
        executeAsync(new Request.Builder().post(RequestBody.create(MediaType.parse(str2), bArr)).timeout(timeout).url(str).build(), callback);
    }

    public static Response post(String str, File file) {
        return execute(new Request.Builder().post(RequestBody.create(MediaType.parse(MediaType.OCTET_STREAM), file)).url(str).build());
    }

    public static void postFileAsync(String str, File file, Timeout timeout, Callback callback) {
        executeAsync(new Request.Builder().post(RequestBody.create(MediaType.parse(MediaType.OCTET_STREAM), file)).timeout(timeout).url(str).build(), callback);
    }

    public static void postFileAsync(String str, Map<String, String> map, Map<String, Object> map2, Timeout timeout, final ProgressCallBack progressCallBack) {
        executeAsync(new Request.Builder().post(RequestBody.create(map2, progressCallBack)).headers(Headers.m783of(map)).timeout(timeout).url(str).build(), new Callback() {
            public final void onFailure(Call call, IOException iOException) {
                ProgressCallBack.this.onFailure(call, iOException);
            }

            public final void onResponse(Call call, Response response) {
                if (!response.isSuccessful()) {
                    ProgressCallBack.this.onFailure(call, new IOException("response is not success!"));
                }
                ProgressCallBack.this.onResponse(call, response);
            }
        });
    }

    public static void getFileAsync(String str, Map<String, String> map, final File file, Timeout timeout, final ProgressCallBack progressCallBack) {
        executeAsync(new Request.Builder().timeout(timeout).headers(Headers.m783of(map)).url(str).build(), new Callback() {
            public final void onFailure(Call call, IOException iOException) {
                ProgressCallBack.this.onFailure(call, iOException);
            }

            /* JADX WARNING: Removed duplicated region for block: B:15:0x006b A[SYNTHETIC, Splitter:B:15:0x006b] */
            /* JADX WARNING: Removed duplicated region for block: B:18:0x0070 A[Catch:{ IOException -> 0x0087 }] */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public final void onResponse(neton.Call r11, neton.Response r12) {
                /*
                    r10 = this;
                    r2 = 0
                    java.lang.String r0 = "getFileAsync--onResponse start"
                    neton.client.Utils.LogUtil.m787d(r0)
                    r0 = 2048(0x800, float:2.87E-42)
                    byte[] r0 = new byte[r0]
                    neton.ResponseBody r1 = r12.body()     // Catch:{ IOException -> 0x0090, all -> 0x0089 }
                    long r6 = r1.contentLength()     // Catch:{ IOException -> 0x0090, all -> 0x0089 }
                    r4 = 0
                    neton.ResponseBody r1 = r12.body()     // Catch:{ IOException -> 0x0090, all -> 0x0089 }
                    java.io.InputStream r3 = r1.byteStream()     // Catch:{ IOException -> 0x0090, all -> 0x0089 }
                    java.io.FileOutputStream r1 = new java.io.FileOutputStream     // Catch:{ IOException -> 0x0094, all -> 0x008d }
                    java.io.File r8 = r4     // Catch:{ IOException -> 0x0094, all -> 0x008d }
                    r1.<init>(r8)     // Catch:{ IOException -> 0x0094, all -> 0x008d }
                    java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x0066 }
                    java.lang.String r8 = "getFileAsync--onResponse dstFile:"
                    r2.<init>(r8)     // Catch:{ IOException -> 0x0066 }
                    java.io.File r8 = r4     // Catch:{ IOException -> 0x0066 }
                    java.lang.StringBuilder r2 = r2.append(r8)     // Catch:{ IOException -> 0x0066 }
                    java.lang.String r2 = r2.toString()     // Catch:{ IOException -> 0x0066 }
                    neton.client.Utils.LogUtil.m787d(r2)     // Catch:{ IOException -> 0x0066 }
                L_0x0037:
                    int r2 = r3.read(r0)     // Catch:{ IOException -> 0x0066 }
                    r8 = -1
                    if (r2 == r8) goto L_0x0074
                    long r8 = (long) r2     // Catch:{ IOException -> 0x0066 }
                    long r4 = r4 + r8
                    r8 = 0
                    r1.write(r0, r8, r2)     // Catch:{ IOException -> 0x0066 }
                    java.lang.StringBuilder r8 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x0066 }
                    java.lang.String r9 = "getFileAsync--current:"
                    r8.<init>(r9)     // Catch:{ IOException -> 0x0066 }
                    java.lang.StringBuilder r8 = r8.append(r4)     // Catch:{ IOException -> 0x0066 }
                    java.lang.String r9 = ",len:"
                    java.lang.StringBuilder r8 = r8.append(r9)     // Catch:{ IOException -> 0x0066 }
                    java.lang.StringBuilder r2 = r8.append(r2)     // Catch:{ IOException -> 0x0066 }
                    java.lang.String r2 = r2.toString()     // Catch:{ IOException -> 0x0066 }
                    neton.client.Utils.LogUtil.m787d(r2)     // Catch:{ IOException -> 0x0066 }
                    com.coloros.neton.ProgressCallBack r2 = com.coloros.neton.ProgressCallBack.this     // Catch:{ IOException -> 0x0066 }
                    r2.onProgress(r6, r4)     // Catch:{ IOException -> 0x0066 }
                    goto L_0x0037
                L_0x0066:
                    r0 = move-exception
                L_0x0067:
                    throw r0     // Catch:{ all -> 0x0068 }
                L_0x0068:
                    r0 = move-exception
                L_0x0069:
                    if (r3 == 0) goto L_0x006e
                    r3.close()     // Catch:{ IOException -> 0x0087 }
                L_0x006e:
                    if (r1 == 0) goto L_0x0073
                    r1.close()     // Catch:{ IOException -> 0x0087 }
                L_0x0073:
                    throw r0
                L_0x0074:
                    r1.flush()     // Catch:{ IOException -> 0x0066 }
                    if (r3 == 0) goto L_0x007c
                    r3.close()     // Catch:{ IOException -> 0x0085 }
                L_0x007c:
                    r1.close()     // Catch:{ IOException -> 0x0085 }
                    com.coloros.neton.ProgressCallBack r0 = com.coloros.neton.ProgressCallBack.this
                    r0.onResponse(r11, r12)
                    return
                L_0x0085:
                    r0 = move-exception
                    throw r0
                L_0x0087:
                    r0 = move-exception
                    throw r0
                L_0x0089:
                    r0 = move-exception
                    r1 = r2
                    r3 = r2
                    goto L_0x0069
                L_0x008d:
                    r0 = move-exception
                    r1 = r2
                    goto L_0x0069
                L_0x0090:
                    r0 = move-exception
                    r1 = r2
                    r3 = r2
                    goto L_0x0067
                L_0x0094:
                    r0 = move-exception
                    r1 = r2
                    goto L_0x0067
                */
                throw new UnsupportedOperationException("Method not decompiled: com.coloros.neton.NetonClient.C01892.onResponse(neton.Call, neton.Response):void");
            }
        });
    }

    public void cancel(Object obj) {
        checkInit();
        sMProcessor.cancel(obj);
    }

    public void close() {
        checkInit();
        sMProcessor.close();
    }

    public void processNetonAction(Intent intent) {
        checkInit();
        NetonReceiver.processNetonAction(this.mContext, intent);
    }
}
