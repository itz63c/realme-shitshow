package neton.client.statistics;

import android.content.Context;
import android.text.TextUtils;
import com.coloros.neton.BuildConfig;
import com.coloros.neton.NetonClient;
import com.coloros.neton.NetonException;
import java.io.DataOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.HashMap;
import neton.CacheControl;
import neton.MediaType;
import neton.Request;
import neton.RequestBody;
import neton.Timeout;
import neton.client.Utils.ApkInfoUtil;
import neton.client.Utils.AppSecurityUtils;
import neton.client.Utils.ClientIdUtils;
import neton.client.Utils.LogUtil;
import neton.client.Utils.ModeUtil;
import neton.client.Utils.NetHelper;
import neton.client.config.ConfigManager;
import neton.client.config.Constants;
import okio.BufferedSink;

public class StatisticUtil {
    public static final String EVENT_ID_MESSAGE = "netton";
    public static final String LOGTAG_APP = "10001";

    public static void statisticMessage(Context context, RequestStatistic requestStatistic) {
        HashMap hashMap = new HashMap();
        hashMap.put(RequestStatistic.TRACE_ID, requestStatistic.getTraceID());
        hashMap.put(RequestStatistic.REQUEST_URL, requestStatistic.getUrl());
        hashMap.put("ip", requestStatistic.getIp());
        hashMap.put(RequestStatistic.START_TIME, new StringBuilder().append(requestStatistic.getStartTime()).toString());
        hashMap.put("dnsStartTime", new StringBuilder().append(requestStatistic.getDnsStartTime()).toString());
        hashMap.put("dnsStartTime", new StringBuilder().append(requestStatistic.getDnsStartTime()).toString());
        hashMap.put(RequestStatistic.NETWORK_REQUEST_START_TIME, new StringBuilder().append(requestStatistic.getNetworkRequestStartTime()).toString());
        hashMap.put(RequestStatistic.START_SSL_TIME, new StringBuilder().append(requestStatistic.getStartSslTime()).toString());
        hashMap.put(RequestStatistic.END_SSL_TIME, new StringBuilder().append(requestStatistic.getEndSslTime()).toString());
        hashMap.put(RequestStatistic.END_TIME, new StringBuilder().append(requestStatistic.getEndTime()).toString());
        hashMap.put(RequestStatistic.RESPONSE_CODE, new StringBuilder().append(requestStatistic.getResponseCode()).toString());
        hashMap.put(RequestStatistic.RESPONSE_MESSAGE, requestStatistic.getResponseMessage());
        hashMap.put(RequestStatistic.NETWORK_TYPE, requestStatistic.getNetworkType());
        hashMap.put(RequestStatistic.SERVICE_METHOD, requestStatistic.getServiceMethod());
        LogUtil.m787d("StatisticUtil--statisticMessage--logMap:" + hashMap);
        Statistics.onCommon(context, LOGTAG_APP, EVENT_ID_MESSAGE, hashMap, false);
    }

    public static void statisticTrace(final Context context, final RequestStatistic requestStatistic) {
        String str;
        if (requestStatistic != null && !TextUtils.isEmpty(requestStatistic.getTraceID()) && NetHelper.getNetworkAccess(context)) {
            if (ModeUtil.getMode() == 0) {
                str = ConfigManager.getInstance().getStringData(Constants.KEY_TRACE_URL, ApkInfoUtil.isRegionCN() ? Constants.DEFAULT_TRACE_URL : Constants.DEFAULT_FOREIGN_TRACE_URL);
            } else {
                str = Constants.DEFAULT_TEST_TRACE_URL;
            }
            String valueOf = String.valueOf(System.currentTimeMillis());
            try {
                LogUtil.m790e("statisticTrace,url=" + str + ",response:" + NetonClient.getProcessor().process(new Request.Builder().url(str).post(new RequestBody() {
                    public final MediaType contentType() {
                        return MediaType.parse(MediaType.OCTET_STREAM);
                    }

                    public final void writeTo(BufferedSink bufferedSink) {
                        StatisticUtil.write(context, new DataOutputStream(bufferedSink.outputStream()), requestStatistic);
                    }
                }).timeout(new Timeout(3000, 10000, 10000)).cacheControl(CacheControl.FORCE_NETWORK).header("Content-Type", MediaType.OCTET_STREAM).header("t", valueOf).header("s", AppSecurityUtils.getHmacSHA1(valueOf.getBytes(Charset.defaultCharset()), "snake_sdk")).build(), true));
            } catch (NetonException e) {
                LogUtil.m790e("statisticTrace--NetonException:" + e.toString());
            } catch (IOException e2) {
                LogUtil.m790e("statisticTrace--IOException:" + e2.toString());
            } catch (Exception e3) {
                LogUtil.m790e("statisticTrace--Exception:" + e3.toString());
            }
        }
    }

    static void write(Context context, DataOutputStream dataOutputStream, RequestStatistic requestStatistic) {
        if (requestStatistic != null) {
            dataOutputStream.writeByte(32);
            if (requestStatistic.getServiceMethod() == null || requestStatistic.getResponseCode() == 0) {
                LogUtil.m787d("statisticTrace-write error");
                return;
            }
            dataOutputStream.writeUTF(requestStatistic.getTraceID());
            dataOutputStream.writeUTF(requestStatistic.getServiceMethod());
            dataOutputStream.writeUTF("neton_" + context.getPackageName());
            dataOutputStream.writeUTF(requestStatistic.getIp());
            dataOutputStream.writeLong(requestStatistic.getStartTime());
            dataOutputStream.writeLong(requestStatistic.getDnsStartTime());
            dataOutputStream.writeLong(requestStatistic.getDnsEndTime());
            dataOutputStream.writeLong(requestStatistic.getNetworkRequestStartTime());
            dataOutputStream.writeLong(requestStatistic.getStartSslTime());
            dataOutputStream.writeLong(requestStatistic.getEndSslTime());
            dataOutputStream.writeLong(requestStatistic.getEndTime());
            dataOutputStream.writeUTF("imei=" + ClientIdUtils.getClientId(context) + "&appVersion=" + ApkInfoUtil.getVersionName(context) + "&model=" + ApkInfoUtil.getModel());
            dataOutputStream.writeUTF(new StringBuilder().append(requestStatistic.getResponseCode()).toString());
            if (requestStatistic.getResponseCode() == 200) {
                dataOutputStream.writeUTF(BuildConfig.FLAVOR);
            } else {
                dataOutputStream.writeUTF(requestStatistic.getResponseMessage());
            }
            dataOutputStream.flush();
        }
    }
}
