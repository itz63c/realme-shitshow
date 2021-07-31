package neton.client.dns;

import android.content.Context;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import neton.client.Utils.LogUtil;
import neton.client.Utils.ThreadPoolUtil;
import neton.client.database.DBUtil;
import neton.internal.Util;

public abstract class DnsImp implements Dns {
    static final float DELETE_WEIGHT = 0.75f;
    static final String regex = "(2[5][0-5]|2[0-4]\\d|1\\d{2}|\\d{1,2})\\.(25[0-5]|2[0-4]\\d|1\\d{2}|\\d{1,2})\\.(25[0-5]|2[0-4]\\d|1\\d{2}|\\d{1,2})\\.(25[0-5]|2[0-4]\\d|1\\d{2}|\\d{1,2})";
    protected Map<String, AddressInfo> mAddressInfos = new HashMap();
    protected Context mContext;

    public abstract int getDnsType();

    public DnsImp(Context context) {
        this.mContext = context;
    }

    public void load() {
        ThreadPoolUtil.execute(new Runnable() {
            public void run() {
                synchronized (DnsImp.this.mAddressInfos) {
                    LinkedList<AddressInfo> addressInfoList = DBUtil.getAddressInfoList(DnsImp.this.mContext, DnsImp.this.getDnsType());
                    if (addressInfoList != null && addressInfoList.size() > 0) {
                        for (AddressInfo next : addressInfoList) {
                            LinkedList<IpInfo> ipInfoList = DBUtil.getIpInfoList(DnsImp.this.mContext, DnsImp.this.getDnsType(), next.getHost());
                            if (ipInfoList != null) {
                                next.setIpInfoList(ipInfoList);
                            }
                            DnsImp.this.mAddressInfos.put(next.getHost(), next);
                        }
                    }
                }
                LogUtil.m787d("DnsImp--load--type:" + DnsImp.this.getDnsType() + ",mAddressInfos:" + DnsImp.this.mAddressInfos);
            }
        });
    }

    public boolean checkNeedDelete(AddressInfo addressInfo) {
        if (addressInfo.getTimeStamp() == 0 || addressInfo.getTimeStamp() >= System.currentTimeMillis()) {
            List<IpInfo> ipInfoList = addressInfo.getIpInfoList();
            if (ipInfoList == null || ipInfoList.size() <= 0) {
                return false;
            }
            int i = 0;
            for (IpInfo failCount : ipInfoList) {
                i = failCount.getFailCount() + i;
            }
            if (((float) i) <= ((float) ipInfoList.size()) * DELETE_WEIGHT) {
                return false;
            }
            LogUtil.m787d("DnsImp--lookup--checkNeedDelete--fail overtimes");
            return true;
        }
        LogUtil.m787d("DnsImp--lookup--checkNeedDelete--timeout");
        return true;
    }

    public AddressInfo getAddressInfo(String str) {
        AddressInfo addressInfo;
        synchronized (this.mAddressInfos) {
            if (!this.mAddressInfos.containsKey(str)) {
                this.mAddressInfos.put(str, new AddressInfo(str, getDnsType()));
            }
            addressInfo = this.mAddressInfos.get(str);
        }
        return addressInfo;
    }

    public void saveAddressInfo(String str, List<IpInfo> list, int i) {
        final AddressInfo addressInfo = getAddressInfo(str);
        if (list != null && list.size() != 0) {
            final List<IpInfo> list2 = list;
            final String str2 = str;
            final int i2 = i;
            ThreadPoolUtil.executeSingle(new Runnable() {
                public void run() {
                    synchronized (addressInfo) {
                        Iterator it = list2.iterator();
                        while (true) {
                            if (it.hasNext()) {
                                IpInfo ipInfo = (IpInfo) it.next();
                                if (ipInfo != null && ipInfo.getTtl() > 0) {
                                    addressInfo.setTtl(ipInfo.getTtl());
                                    addressInfo.setTimeStamp((ipInfo.getTtl() * 1000) + System.currentTimeMillis());
                                    break;
                                }
                            } else {
                                break;
                            }
                        }
                        addressInfo.setIpInfoList(list2);
                        if (DnsImp.this.getDnsType() == DnsInfo.TYPE_HTTP) {
                            DBUtil.addAddressInfo(DnsImp.this.mContext, addressInfo);
                            DBUtil.deleteIpInfosByHostAndDnsType(DnsImp.this.mContext, str2, DnsImp.this.getDnsType());
                            for (IpInfo addIpInfo : list2) {
                                DBUtil.addIpInfo(DnsImp.this.mContext, addIpInfo);
                            }
                        }
                        addressInfo.setLooking(false);
                        LogUtil.m787d("DnsImp--saveAddressInfo--type:" + i2);
                    }
                }
            });
        }
    }

    public void delete(String str) {
        AddressInfo addressInfo = getAddressInfo(str);
        addressInfo.setTtl(0);
        addressInfo.setTimeStamp(0);
        addressInfo.setIpInfoList((List<IpInfo>) null);
        DBUtil.updateAddressInfo(this.mContext, addressInfo);
        DBUtil.deleteIpInfosByHostAndDnsType(this.mContext, str, getDnsType());
        LogUtil.m787d("DnsImp--delete -host:" + str + ",type:" + getDnsType());
    }

    public static boolean isHostIp(String str) {
        if (Util.verifyAsIpAddress(str)) {
            return true;
        }
        return false;
    }
}
