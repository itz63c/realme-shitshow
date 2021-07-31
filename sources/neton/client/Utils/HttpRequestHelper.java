package neton.client.Utils;

import android.content.Context;
import android.text.TextUtils;
import com.coloros.neton.BuildConfig;
import com.coloros.neton.NetonClient;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.charset.UnsupportedCharsetException;
import java.text.MessageFormat;
import java.util.Arrays;
import java.util.List;
import neton.Request;
import neton.Response;
import neton.Timeout;
import neton.client.config.ConfigManager;
import neton.client.config.Constants;

public class HttpRequestHelper {
    private static final String DNS_ACCEPT = "plain";
    public static final String HTTPDNS_SIGNATURE = "httpdns-signature";
    private static final String HTTPDNS_SIGNATURE_SALT = "cdRXxH952clSK";
    private static final String HTTP_DNS_HEADER = "httpdns.basic.oppomobile.com";
    private static final String JSON = "json";
    private static final String PLAIN = "plain";

    public static List<String> getIpList(String str) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.m787d("HttpRequestHelper--getIpList--body:" + str);
            return null;
        }
        List<String> asList = Arrays.asList(str.split("\n"));
        LogUtil.m793i("HttpRequestHelper--getIpList--list:" + asList);
        return asList;
    }

    public static List<String> getRequestDns(Context context, String str, String str2) {
        Response response = null;
        LogUtil.m787d("HttpRequestHelper--getRequestDns--dns:" + str + ",host:" + str2);
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            return null;
        }
        try {
            response = getResponseFromServer(context, str + getParams(str2));
            LogUtil.m787d("HttpRequestHelper--getRequestDns--response:" + response);
        } catch (IOException e) {
            LogUtil.m790e("HttpRequestHelper--getRequestDns--ioe:" + e.getMessage());
            e.printStackTrace();
        } catch (IllegalArgumentException e2) {
            LogUtil.m790e("HttpRequestHelper--getRequestDns--iae:" + e2.getMessage());
            e2.printStackTrace();
        } catch (Exception e3) {
            LogUtil.m790e("HttpRequestHelper--getRequestDns--e" + e3.getMessage());
            e3.printStackTrace();
        }
        return getIpList(getCheckResultMD5(response));
    }

    private static String getParams(String str) {
        return "/d?_t=" + System.currentTimeMillis() + "&dn=" + str + "&region=" + ConfigManager.getInstance().getStringData(Constants.KEY_REGIONCODE, BuildConfig.FLAVOR);
    }

    public static Response getResponseFromServer(Context context, String str) {
        Response process = NetonClient.getProcessor().process(new Request.Builder().addHeader("User-Agent", getFormatHeader(context)).addHeader("Host", new URL(ApkInfoUtil.isRegionCN() ? Constants.DEFAULT_HTTP_LAST_DNS : Constants.DEFAULT_FOREIGN_HTTP_LAST_DNS).getHost()).addHeader("Dns-Accept", "plain").timeout(new Timeout(3000, 3000, 3000)).url(str).build(), true);
        if (!process.isSuccessful() || TextUtils.isEmpty(process.header(HTTPDNS_SIGNATURE))) {
            return null;
        }
        return process;
    }

    public static String getFormatHeader(Context context) {
        int versionCode = ApkInfoUtil.getVersionCode(context);
        return MessageFormat.format("os={0};mcs={1};net={2};model={3}", new Object[]{BuildConfig.FLAVOR, Integer.valueOf(versionCode), NetHelper.getNetworkType(context), BuildConfig.FLAVOR});
    }

    private static String getCheckResultMD5(Response response) {
        String str;
        if (response == null) {
            return null;
        }
        try {
            String str2 = new String(response.body().bytes(), Charset.defaultCharset());
            String header = response.header(HTTPDNS_SIGNATURE);
            StringBuilder sb = new StringBuilder();
            sb.append(HTTPDNS_SIGNATURE_SALT);
            sb.append(str2);
            sb.append(HTTPDNS_SIGNATURE_SALT);
            String calcMD5 = MD5Tools.calcMD5(sb.toString());
            if (TextUtils.isEmpty(header) || TextUtils.isEmpty(calcMD5) || !header.equals(calcMD5)) {
                str = null;
            } else {
                str = str2;
            }
            LogUtil.m787d("HttpRequestHelper--getCheckResultMD5--responseUrl:" + str2 + ",md5:" + header + ",sb:" + sb.toString() + ",myMd5:" + calcMD5 + ",result:" + str);
            return str;
        } catch (IOException e) {
            LogUtil.m790e("HttpRequestHelper--getCheckResultMD5--IOException" + e.getMessage());
            return null;
        } catch (UnsupportedCharsetException e2) {
            LogUtil.m790e("HttpRequestHelper--getCheckResultMD5--UnsupportedCharsetException:" + e2.getMessage());
            return null;
        } catch (Exception e3) {
            LogUtil.m790e("HttpRequestHelper--getCheckResultMD5--Exception:" + e3.getMessage());
            return null;
        }
    }
}
