package neton.client.dns;

import android.content.Context;
import android.text.TextUtils;
import neton.client.Utils.LogUtil;
import neton.client.Utils.ThreadPoolUtil;
import neton.client.config.ConfigManager;
import neton.client.config.Constants;

public class DnsManager implements Dns {
    /* access modifiers changed from: private */
    public HttpDns mHttpDns;
    /* access modifiers changed from: private */
    public LocalDns mLocalDns;

    class InstanceHolder {
        static DnsManager ourInstance = new DnsManager();

        private InstanceHolder() {
        }
    }

    public static DnsManager getInstance() {
        return InstanceHolder.ourInstance;
    }

    private DnsManager() {
    }

    public void init(Context context) {
        this.mHttpDns = new HttpDns(context);
        this.mLocalDns = new LocalDns(context);
    }

    public IpInfo lookup(String str) {
        IpInfo ipInfo = null;
        if (!TextUtils.isEmpty(str)) {
            int intData = ConfigManager.getInstance().getIntData(Constants.KEY_DNS_MODE, 1);
            if (intData == 0) {
                ipInfo = this.mHttpDns.lookup(str);
                if (ipInfo == null || !ipInfo.isIpInfoValid()) {
                    ipInfo = this.mLocalDns.lookup(str);
                }
            } else if (1 == intData) {
                ipInfo = this.mLocalDns.lookup(str);
            } else if (2 == intData) {
                ipInfo = this.mHttpDns.lookup(str);
            }
            LogUtil.m787d("DnsManager--lookup--ipInfo:" + ipInfo);
        }
        return ipInfo;
    }

    public void updateIpInfo(final IpInfo ipInfo) {
        ThreadPoolUtil.execute(new Runnable() {
            public void run() {
                if (ipInfo != null && ipInfo.checkSelect()) {
                    if (DnsInfo.TYPE_HTTP == ipInfo.getDnsType()) {
                        DnsManager.this.mHttpDns.getAddressInfo(ipInfo.getHost()).setIpInfo(ipInfo);
                    } else if (DnsInfo.TYPE_LOCAL == ipInfo.getDnsType()) {
                        DnsManager.this.mLocalDns.getAddressInfo(ipInfo.getHost()).setIpInfo(ipInfo);
                    }
                }
            }
        });
    }
}
