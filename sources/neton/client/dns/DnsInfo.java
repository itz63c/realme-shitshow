package neton.client.dns;

public class DnsInfo {
    public static final String DNS_TYPE = "dnsType";
    public static final String HOST = "host";
    public static final String TTL = "ttl";
    public static int TYPE_HTTP = 1;
    public static int TYPE_LOCAL = 0;
    protected int dnsType;
    protected String host;
    protected long ttl = 0;

    public DnsInfo(String str, int i) {
        this.host = str;
        this.dnsType = i;
    }

    public DnsInfo() {
    }

    public long getTtl() {
        return this.ttl;
    }

    public void setTtl(long j) {
        this.ttl = j;
    }

    public int getDnsType() {
        return this.dnsType;
    }

    public void setDnsType(int i) {
        this.dnsType = i;
    }

    public String getHost() {
        return this.host;
    }

    public void setHost(String str) {
        this.host = str;
    }
}
