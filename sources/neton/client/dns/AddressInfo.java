package neton.client.dns;

import android.database.Cursor;
import java.util.List;

public class AddressInfo extends DnsInfo {
    public static final String TIMESTAMP = "timeStamp";
    private List<IpInfo> infos;
    private IpInfo ipInfo;
    private boolean isLooking = false;
    private long timeStamp = 0;

    public AddressInfo(String str, int i) {
        super(str, i);
    }

    public AddressInfo() {
    }

    public boolean isLooking() {
        return this.isLooking;
    }

    public void setLooking(boolean z) {
        this.isLooking = z;
    }

    public synchronized IpInfo getIpInfo() {
        return this.ipInfo;
    }

    public synchronized void setIpInfo(IpInfo ipInfo2) {
        this.ipInfo = ipInfo2;
    }

    public long getTimeStamp() {
        return this.timeStamp;
    }

    public void setTimeStamp(long j) {
        this.timeStamp = j;
    }

    public List<IpInfo> getIpInfoList() {
        return this.infos;
    }

    public void setIpInfoList(List<IpInfo> list) {
        this.infos = list;
    }

    public static AddressInfo getAddressInfoByCursor(Cursor cursor) {
        if (cursor == null || cursor.isClosed()) {
            return null;
        }
        AddressInfo addressInfo = new AddressInfo();
        addressInfo.setHost(cursor.getString(cursor.getColumnIndex(DnsInfo.HOST)));
        addressInfo.setDnsType(cursor.getInt(cursor.getColumnIndex(DnsInfo.DNS_TYPE)));
        addressInfo.setTtl(cursor.getLong(cursor.getColumnIndex(DnsInfo.TTL)));
        addressInfo.setTimeStamp(cursor.getLong(cursor.getColumnIndex(TIMESTAMP)));
        return addressInfo;
    }

    public String toString() {
        return "host:" + this.host + ",dsnType:" + this.dnsType + ",ttl:" + this.ttl + ",timeStamp:" + this.timeStamp + ",list:" + this.infos + ",ipInfo:" + this.ipInfo;
    }
}
