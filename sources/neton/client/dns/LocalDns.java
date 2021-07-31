package neton.client.dns;

import android.content.Context;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import neton.client.Utils.LogUtil;
import neton.client.Utils.MathUtils;

public class LocalDns extends DnsImp {
    public LocalDns(Context context) {
        super(context);
        load();
    }

    public int getDnsType() {
        return DnsInfo.TYPE_LOCAL;
    }

    public IpInfo lookup(String str) {
        IpInfo ipInfo;
        LogUtil.m787d("LocalDns--lookup--start--host:" + str + ",mAddressInfos:" + this.mAddressInfos);
        AddressInfo addressInfo = getAddressInfo(str);
        synchronized (addressInfo) {
            ipInfo = addressInfo.getIpInfo();
            if (ipInfo == null || !ipInfo.checkSelect()) {
                List<IpInfo> ipInfoList = addressInfo.getIpInfoList();
                if (ipInfoList != null && ipInfoList.size() > 0) {
                    if (checkNeedDelete(addressInfo)) {
                        delete(str);
                    } else {
                        ipInfo = select(ipInfoList);
                        if (ipInfo == null) {
                            delete(str);
                        }
                    }
                }
                LogUtil.m787d("LocalDns--real lookup dns start --ipInfoList:" + ipInfoList);
                try {
                    List<InetAddress> asList = Arrays.asList(InetAddress.getAllByName(str));
                    LogUtil.m787d("LocalDns--real lookup dns end--inetAddresses:" + asList);
                    if (asList != null && asList.size() > 0) {
                        ArrayList arrayList = new ArrayList();
                        for (InetAddress hostAddress : asList) {
                            IpInfo ipInfo2 = new IpInfo(str, getDnsType());
                            ipInfo2.setIp(hostAddress.getHostAddress());
                            arrayList.add(ipInfo2);
                        }
                        ipInfo = select(arrayList);
                        saveAddressInfo(str, arrayList, getDnsType());
                    }
                    LogUtil.m787d("LocalDns--lookup--end --host:" + str);
                } catch (UnknownHostException e) {
                    LogUtil.m787d("LocalDns--UnknownHostException--:" + e.getMessage());
                    throw e;
                }
            } else {
                LogUtil.m787d("LocalDns--lookup--success ipInfo:" + ipInfo);
            }
        }
        return ipInfo;
    }

    public IpInfo select(List<IpInfo> list) {
        LogUtil.m787d("selectIpInfo-:" + list);
        ArrayList arrayList = new ArrayList();
        for (IpInfo next : list) {
            if (next.checkSelect()) {
                arrayList.add(next);
            }
        }
        int size = arrayList.size();
        if (size == 0) {
            return null;
        }
        if (size > 0) {
            return (IpInfo) arrayList.get(MathUtils.random(size));
        }
        return (IpInfo) arrayList.get(0);
    }
}
