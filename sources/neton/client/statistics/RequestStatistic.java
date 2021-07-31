package neton.client.statistics;

public class RequestStatistic {
    public static final String DNS_END_TIME = "dnsStartTime";
    public static final String DNS_START_TIME = "dnsStartTime";
    public static final String END_SSL_TIME = "endSslTime";
    public static final String END_TIME = "endTime";
    public static final String NETWORK_REQUEST_START_TIME = "networkRequestStartTime";
    public static final String NETWORK_TYPE = "networkType";
    public static final String REQUEST_IP = "ip";
    public static final String REQUEST_URL = "url";
    public static final String RESPONSE_CODE = "responseCode";
    public static final String RESPONSE_MESSAGE = "responseMessage";
    public static final String SERVICE_METHOD = "serviceMethod";
    public static final String START_SSL_TIME = "startSslTime";
    public static final String START_TIME = "startTime";
    public static final String TRACE_ID = "traceID";
    private long dnsEndTime;
    private long dnsStartTime;
    private long endSslTime;
    private long endTime;

    /* renamed from: ip */
    private String f827ip;
    private long networkRequestStartTime;
    private String networkType;
    private int responseCode;
    private String responseMessage;
    private String serviceMethod;
    private long startSslTime;
    private long startTime;
    private String traceID;
    private String url;

    public RequestStatistic(String str) {
        setTraceID(str);
        setStartTime(System.currentTimeMillis());
    }

    public String getTraceID() {
        return this.traceID;
    }

    public void setTraceID(String str) {
        this.traceID = str;
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String str) {
        this.url = str;
    }

    public String getIp() {
        return this.f827ip;
    }

    public void setIp(String str) {
        this.f827ip = str;
    }

    public long getStartTime() {
        return this.startTime;
    }

    public void setStartTime(long j) {
        this.startTime = j;
    }

    public long getDnsStartTime() {
        return this.dnsStartTime;
    }

    public void setDnsStartTime(long j) {
        this.dnsStartTime = j;
    }

    public long getDnsEndTime() {
        return this.dnsEndTime;
    }

    public void setDnsEndTime(long j) {
        this.dnsEndTime = j;
    }

    public long getNetworkRequestStartTime() {
        return this.networkRequestStartTime;
    }

    public void setNetworkRequestStartTime(long j) {
        this.networkRequestStartTime = j;
    }

    public long getStartSslTime() {
        return this.startSslTime;
    }

    public void setStartSslTime(long j) {
        this.startSslTime = j;
    }

    public long getEndSslTime() {
        return this.endSslTime;
    }

    public void setEndSslTime(long j) {
        this.endSslTime = j;
    }

    public long getEndTime() {
        return this.endTime;
    }

    public void setEndTime(long j) {
        this.endTime = j;
    }

    public int getResponseCode() {
        return this.responseCode;
    }

    public void setResponseCode(int i) {
        this.responseCode = i;
    }

    public String getResponseMessage() {
        return this.responseMessage;
    }

    public void setResponseMessage(String str) {
        this.responseMessage = str;
    }

    public String getNetworkType() {
        return this.networkType;
    }

    public void setNetworkType(String str) {
        this.networkType = str;
    }

    public String getServiceMethod() {
        return this.serviceMethod;
    }

    public void setServiceMethod(String str) {
        this.serviceMethod = str;
    }

    public String toString() {
        return "traceID:" + this.traceID + ",url:" + this.url + ",ip:" + this.f827ip + ",serviceMethod:" + this.serviceMethod + ",startTime:" + this.startTime + ",dnsStartTime:" + this.dnsStartTime + ",dnsEndTime:" + this.dnsEndTime + ",networkRequestStartTime:" + this.networkRequestStartTime + ",startSslTime:" + this.startSslTime + ",endSslTime:" + this.endSslTime + ",endTime:" + this.endTime + ",responseCode:" + this.responseCode + ",responseMessage:" + this.responseMessage + ",networkType:" + this.networkType;
    }
}
