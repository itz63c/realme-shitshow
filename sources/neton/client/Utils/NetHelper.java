package neton.client.Utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import com.coloros.neton.BuildConfig;
import java.net.Inet4Address;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;
import neton.client.config.ConfigManager;
import neton.client.config.Constants;
import neton.client.dns.DnsMode;

public class NetHelper {
    public static final String CARRIER_BGP = "bgp";
    private static final String CARRIER_CHINA_MOBILE = "cm";
    public static final String CARRIER_CHINA_TELCOM = "ct";
    private static final String CARRIER_CHINA_UNION = "cu";
    private static final String CARRIER_NONE = "none";
    public static final String CARRIER_OTHER = "ot";
    public static final String CARRIER_WIFI = "wifi";
    private static final int NETWORK_CLASS_2_G = 1;
    private static final int NETWORK_CLASS_3_G = 2;
    private static final int NETWORK_CLASS_4_G = 3;
    private static final int NETWORK_CLASS_UNAVAILABLE = -1;
    private static final int NETWORK_CLASS_UNKNOWN = 0;
    private static final int NETWORK_CLASS_WIFI = -101;
    private static final int NETWORK_TYPE_1xRTT = 7;
    private static final int NETWORK_TYPE_CDMA = 4;
    private static final int NETWORK_TYPE_EDGE = 2;
    private static final int NETWORK_TYPE_EHRPD = 14;
    private static final int NETWORK_TYPE_EVDO_0 = 5;
    private static final int NETWORK_TYPE_EVDO_A = 6;
    private static final int NETWORK_TYPE_EVDO_B = 12;
    private static final int NETWORK_TYPE_GPRS = 1;
    private static final int NETWORK_TYPE_HSDPA = 8;
    private static final int NETWORK_TYPE_HSPA = 10;
    private static final int NETWORK_TYPE_HSPAP = 15;
    private static final int NETWORK_TYPE_HSUPA = 9;
    private static final int NETWORK_TYPE_IDEN = 11;
    private static final int NETWORK_TYPE_LTE = 13;
    private static final int NETWORK_TYPE_UMTS = 3;
    private static final int NETWORK_TYPE_UNAVAILABLE = -1;
    private static final int NETWORK_TYPE_UNKNOWN = 0;
    private static final int NETWORK_TYPE_WIFI = -101;
    public static final String OPPO_CTA_USER_EXPERIENCE = "oppo_cta_user_experience";
    private static final String TAG = NetHelper.class.getSimpleName();
    private static String sCarrierStatus = CARRIER_NONE;
    private static String sLastCarrierStatus = CARRIER_NONE;

    public static String getCarrierStatus() {
        return sCarrierStatus;
    }

    public static String getLastCarrierStatus() {
        return sLastCarrierStatus;
    }

    public static void setCarrierStatus(Context context) {
        sLastCarrierStatus = sCarrierStatus;
        if (isWifiConnecting(context)) {
            sCarrierStatus = CARRIER_WIFI;
        } else {
            String carrierName = getCarrierName(context);
            if (CARRIER_CHINA_MOBILE.equals(carrierName)) {
                sCarrierStatus = CARRIER_CHINA_MOBILE;
            } else if (CARRIER_CHINA_UNION.equals(carrierName)) {
                sCarrierStatus = CARRIER_CHINA_UNION;
            } else if (CARRIER_CHINA_TELCOM.equals(carrierName)) {
                sCarrierStatus = CARRIER_CHINA_TELCOM;
            } else {
                sCarrierStatus = CARRIER_NONE;
            }
        }
        LogUtil.m787d("setCarrierStatus--:" + sLastCarrierStatus + "-->" + sCarrierStatus);
    }

    public static boolean isConnectNet(Context context) {
        try {
            NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
            if (activeNetworkInfo != null) {
                return activeNetworkInfo.isAvailable() || activeNetworkInfo.isConnected();
            }
        } catch (Exception e) {
            LogUtil.m792e(TAG, (Throwable) e);
        }
        return false;
    }

    public static boolean hasAccessAndNetwork(Context context) {
        boolean networkAccess = getNetworkAccess(context);
        int intData = ConfigManager.getInstance().getIntData(Constants.NETTON_STATUS);
        boolean isConnectNet = isConnectNet(context);
        LogUtil.m787d("hasAccessAndNetwork....access:" + networkAccess + ",net:" + isConnectNet + ",isConfigAccess:" + intData);
        return networkAccess && intData != -1 && isConnectNet;
    }

    public static boolean isWifiConnecting(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
        if (connectivityManager == null) {
            return false;
        }
        try {
            NetworkInfo.State state = connectivityManager.getNetworkInfo(1).getState();
            if (state != null && NetworkInfo.State.CONNECTED == state) {
                return true;
            }
        } catch (Exception e) {
            LogUtil.m790e("isWifiConnecting--Exception");
        }
        return false;
    }

    private static String intToIp(int i) {
        return (i & 255) + "." + ((i >> NETWORK_TYPE_HSDPA) & 255) + "." + ((i >> 16) & 255) + "." + ((i >> 24) & 255);
    }

    public static String getLocalIp6Address() {
        try {
            Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
            while (networkInterfaces.hasMoreElements()) {
                Enumeration<InetAddress> inetAddresses = networkInterfaces.nextElement().getInetAddresses();
                while (true) {
                    if (inetAddresses.hasMoreElements()) {
                        InetAddress nextElement = inetAddresses.nextElement();
                        if (!nextElement.isLoopbackAddress() && (nextElement instanceof Inet6Address)) {
                            return nextElement.getHostAddress();
                        }
                    }
                }
            }
        } catch (SocketException e) {
            LogUtil.m791e("WifiPreference IpAddress", e.toString());
        }
        return null;
    }

    private static String getLocalIp4Address() {
        try {
            Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
            while (networkInterfaces.hasMoreElements()) {
                Enumeration<InetAddress> inetAddresses = networkInterfaces.nextElement().getInetAddresses();
                while (true) {
                    if (inetAddresses.hasMoreElements()) {
                        InetAddress nextElement = inetAddresses.nextElement();
                        if (!nextElement.isLoopbackAddress() && (nextElement instanceof Inet4Address)) {
                            return nextElement.getHostAddress();
                        }
                    }
                }
            }
        } catch (Exception e) {
            LogUtil.m790e(e.getMessage());
        }
        return BuildConfig.FLAVOR;
    }

    public static boolean getNetworkAccess(Context context) {
        int i;
        boolean z;
        try {
            i = Settings.System.getInt(context.getContentResolver(), OPPO_CTA_USER_EXPERIENCE);
        } catch (Settings.SettingNotFoundException e) {
            LogUtil.m790e("getNetworkAccess--SettingNotFoundException");
            i = 0;
        }
        StringBuilder sb = new StringBuilder("NetHelper-getNetworkAccess val:");
        if (i == 1) {
            z = true;
        } else {
            z = false;
        }
        LogUtil.m793i(sb.append(z).toString());
        if (i == 1) {
            return true;
        }
        return false;
    }

    /* JADX WARNING: Removed duplicated region for block: B:22:0x0047 A[RETURN, SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x004a A[RETURN, SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x004d A[RETURN, SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x0050 A[RETURN, SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x0053 A[RETURN, SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x0056 A[RETURN, SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:28:? A[ORIG_RETURN, RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String getNetworkType(android.content.Context r3) {
        /*
            r1 = 0
            java.lang.String r0 = "connectivity"
            java.lang.Object r0 = r3.getSystemService(r0)     // Catch:{ Exception -> 0x003f }
            android.net.ConnectivityManager r0 = (android.net.ConnectivityManager) r0     // Catch:{ Exception -> 0x003f }
            android.net.NetworkInfo r0 = r0.getActiveNetworkInfo()     // Catch:{ Exception -> 0x003f }
            if (r0 == 0) goto L_0x003d
            boolean r2 = r0.isAvailable()     // Catch:{ Exception -> 0x003f }
            if (r2 == 0) goto L_0x003d
            boolean r2 = r0.isConnected()     // Catch:{ Exception -> 0x003f }
            if (r2 == 0) goto L_0x003d
            int r0 = r0.getType()     // Catch:{ Exception -> 0x003f }
            r2 = 1
            if (r0 != r2) goto L_0x002e
            r0 = -101(0xffffffffffffff9b, float:NaN)
        L_0x0024:
            int r1 = getNetworkClassByType(r0)
            java.lang.String r0 = "UNKNOWN"
            switch(r1) {
                case -101: goto L_0x004a;
                case -1: goto L_0x0047;
                case 0: goto L_0x0056;
                case 1: goto L_0x004d;
                case 2: goto L_0x0050;
                case 3: goto L_0x0053;
                default: goto L_0x002d;
            }
        L_0x002d:
            return r0
        L_0x002e:
            if (r0 != 0) goto L_0x0045
            java.lang.String r0 = "phone"
            java.lang.Object r0 = r3.getSystemService(r0)     // Catch:{ Exception -> 0x003f }
            android.telephony.TelephonyManager r0 = (android.telephony.TelephonyManager) r0     // Catch:{ Exception -> 0x003f }
            int r0 = r0.getNetworkType()     // Catch:{ Exception -> 0x003f }
            goto L_0x0024
        L_0x003d:
            r0 = -1
            goto L_0x0024
        L_0x003f:
            r0 = move-exception
            java.lang.String r2 = TAG
            neton.client.Utils.LogUtil.m792e((java.lang.String) r2, (java.lang.Throwable) r0)
        L_0x0045:
            r0 = r1
            goto L_0x0024
        L_0x0047:
            java.lang.String r0 = "UNKNOWN"
            goto L_0x002d
        L_0x004a:
            java.lang.String r0 = "WIFI"
            goto L_0x002d
        L_0x004d:
            java.lang.String r0 = "2G"
            goto L_0x002d
        L_0x0050:
            java.lang.String r0 = "3G"
            goto L_0x002d
        L_0x0053:
            java.lang.String r0 = "4G"
            goto L_0x002d
        L_0x0056:
            java.lang.String r0 = "UNKNOWN"
            goto L_0x002d
        */
        throw new UnsupportedOperationException("Method not decompiled: neton.client.Utils.NetHelper.getNetworkType(android.content.Context):java.lang.String");
    }

    private static int getNetworkClassByType(int i) {
        switch (i) {
            case -101:
                return -101;
            case -1:
                return -1;
            case 1:
            case DnsMode.DNS_MODE_HTTP /*2*/:
            case 4:
            case NETWORK_TYPE_1xRTT /*7*/:
            case NETWORK_TYPE_IDEN /*11*/:
                return 1;
            case 3:
            case 5:
            case NETWORK_TYPE_EVDO_A /*6*/:
            case NETWORK_TYPE_HSDPA /*8*/:
            case NETWORK_TYPE_HSUPA /*9*/:
            case NETWORK_TYPE_HSPA /*10*/:
            case NETWORK_TYPE_EVDO_B /*12*/:
            case NETWORK_TYPE_EHRPD /*14*/:
            case NETWORK_TYPE_HSPAP /*15*/:
                return 2;
            case NETWORK_TYPE_LTE /*13*/:
                return 3;
            default:
                return 0;
        }
    }

    public static String getCarrier(Context context) {
        try {
            return ((TelephonyManager) context.getSystemService("phone")).getNetworkOperatorName();
        } catch (Exception e) {
            LogUtil.m792e(TAG, (Throwable) e);
            return CARRIER_NONE;
        }
    }

    public static String getCarrierName(Context context) {
        String str;
        try {
            String subscriberId = ((TelephonyManager) context.getSystemService("phone")).getSubscriberId();
            if (TextUtils.isEmpty(subscriberId)) {
                return CARRIER_BGP;
            }
            if (subscriberId.startsWith("46000") || subscriberId.startsWith("46002")) {
                str = CARRIER_CHINA_MOBILE;
            } else if (subscriberId.startsWith("46001")) {
                str = CARRIER_CHINA_UNION;
            } else {
                str = (subscriberId.startsWith("46003") || subscriberId.startsWith("46011") || subscriberId.startsWith("45502")) ? CARRIER_CHINA_TELCOM : CARRIER_BGP;
            }
            try {
                LogUtil.m787d("getProvidersName--" + subscriberId + ",providersName:" + str);
                return str;
            } catch (Exception e) {
            }
        } catch (Exception e2) {
            str = CARRIER_BGP;
            LogUtil.m790e("getCarrierName--Exception");
            return str;
        }
    }
}
