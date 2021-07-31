package neton.client.dns;

import android.content.Context;
import android.database.Cursor;
import android.text.TextUtils;
import com.coloros.neton.BuildConfig;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import neton.client.Utils.LogUtil;
import neton.client.Utils.NetHelper;
import neton.client.Utils.ThreadPoolUtil;
import neton.client.Utils.Util;
import neton.client.config.ConfigManager;
import neton.client.config.Constants;
import neton.client.database.DBUtil;

public class IpInfo extends DnsInfo {
    public static final int ERROR_PORT = -1;
    public static final String FAIL_COUNT = "failCount";
    public static final String FAIL_TIME = "failTime";

    /* renamed from: IP */
    public static final String f823IP = "ip";
    public static final String LOCAL = "local";
    public static final long MAX_FAIL_TIME = 1800000;
    public static final String PORT = "port";

    /* renamed from: SP */
    public static final String f824SP = "sp";
    public static final String WEIGHT = "weight";
    private int failCount = 0;
    private long failTime = 0;

    /* renamed from: ip */
    private String f825ip;
    private String local;
    private int port = -1;

    /* renamed from: sp */
    private String f826sp = BuildConfig.FLAVOR;
    private int weight = 0;

    public IpInfo(String str, int i) {
        super(str, i);
    }

    public String getIp() {
        return this.f825ip;
    }

    public void setIp(String str) {
        this.f825ip = str;
    }

    public int getPort() {
        return this.port;
    }

    public void setPort(int i) {
        if (i == 0) {
            this.port = 80;
        }
        this.port = i;
    }

    public String getLocal() {
        return this.local;
    }

    public void setLocal(String str) {
        this.local = str;
    }

    public String getSp() {
        return this.f826sp;
    }

    public void setSp(String str) {
        this.f826sp = str;
    }

    public int getWeight() {
        return this.weight;
    }

    public void setWeight(int i) {
        this.weight = i;
    }

    public synchronized int getFailCount() {
        return this.failCount;
    }

    public synchronized void setFailCount(int i) {
        this.failCount = i;
    }

    public long getFailTime() {
        return this.failTime;
    }

    public void setFailTime(long j) {
        this.failTime = j;
    }

    public void updateFailCount(final Context context, final int i) {
        ThreadPoolUtil.execute(new Runnable() {
            public void run() {
                if (NetHelper.isConnectNet(context)) {
                    synchronized (this) {
                        IpInfo.this.setFailCount(i);
                        IpInfo.this.setFailTime(System.currentTimeMillis());
                        DBUtil.updateIpInfo(context, this);
                    }
                }
            }
        });
    }

    public boolean checkSelect() {
        if (getFailCount() == 0 || (getFailCount() > 0 && System.currentTimeMillis() - getFailTime() > MAX_FAIL_TIME)) {
            return true;
        }
        return false;
    }

    public boolean checkSet() {
        String stringData = ConfigManager.getInstance().getStringData(Constants.KEY_HEADER_SET);
        if (TextUtils.isEmpty(stringData) || stringData.equalsIgnoreCase(getLocal())) {
            return true;
        }
        return false;
    }

    public String toString() {
        return this.host + ",dnsType:" + this.dnsType + ",ip:" + this.f825ip + "," + this.port + "," + this.weight + "," + this.f826sp + "," + this.local + "," + this.failCount + "," + this.failTime;
    }

    public static List<IpInfo> parseIpInfoList(String str, int i, List<String> list) {
        if (list == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        int i2 = 0;
        while (true) {
            int i3 = i2;
            if (i3 >= list.size()) {
                return arrayList;
            }
            IpInfo parseIpInfo = parseIpInfo(str, i, list.get(i3));
            if (parseIpInfo != null) {
                arrayList.add(parseIpInfo);
            }
            i2 = i3 + 1;
        }
    }

    public static IpInfo parseIpInfoByIp(String str, int i, String str2) {
        if (TextUtils.isEmpty(str2)) {
            LogUtil.m787d("parseIpInfoByIp--url:" + str2);
            return null;
        }
        IpInfo ipInfo = new IpInfo(str, i);
        ipInfo.setIp(str2);
        return ipInfo;
    }

    public static IpInfo parseIpInfo(String str, int i, String str2) {
        if (TextUtils.isEmpty(str2)) {
            LogUtil.m787d("parseIpInfo--url is null:" + str2);
            return null;
        }
        String[] split = str2.trim().split(",");
        if (split.length >= 5 || split.length <= 0) {
            try {
                IpInfo ipInfo = new IpInfo(str, i);
                ipInfo.setIp(split[0]);
                ipInfo.setPort(Integer.parseInt(split[1]));
                ipInfo.setTtl(Util.parseLong(split[2]));
                ipInfo.setWeight(Integer.parseInt(split[3]));
                ipInfo.setSp(split[4]);
                ipInfo.setLocal(split.length > 5 ? split[5] : BuildConfig.FLAVOR);
                LogUtil.m787d("parseIpInfo--url:" + str2 + ",info:" + ipInfo.toString());
                return ipInfo;
            } catch (Exception e) {
                LogUtil.m790e("parseIpInfo--Exception:" + e.getMessage());
                return null;
            }
        } else {
            LogUtil.m787d("parseIpInfo--strs:" + Arrays.toString(split));
            return parseIpInfoByIp(str, i, split[0]);
        }
    }

    public static IpInfo getIpInfoByCursor(String str, int i, Cursor cursor) {
        if (cursor == null || cursor.isClosed()) {
            return null;
        }
        IpInfo ipInfo = new IpInfo(str, i);
        ipInfo.setIp(cursor.getString(cursor.getColumnIndex("ip")));
        ipInfo.setPort(cursor.getInt(cursor.getColumnIndex(PORT)));
        ipInfo.setTtl(cursor.getLong(cursor.getColumnIndex(DnsInfo.TTL)));
        ipInfo.setWeight(cursor.getInt(cursor.getColumnIndex(WEIGHT)));
        ipInfo.setLocal(cursor.getString(cursor.getColumnIndex(LOCAL)));
        ipInfo.setSp(cursor.getString(cursor.getColumnIndex(f824SP)));
        ipInfo.setFailCount(cursor.getInt(cursor.getColumnIndex(FAIL_COUNT)));
        ipInfo.setFailTime(cursor.getLong(cursor.getColumnIndex(FAIL_TIME)));
        return ipInfo;
    }

    public boolean isIpInfoValid() {
        if (!TextUtils.isEmpty(getIp())) {
            return true;
        }
        return false;
    }
}
