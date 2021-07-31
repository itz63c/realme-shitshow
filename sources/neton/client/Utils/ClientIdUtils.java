package neton.client.Utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Environment;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import java.io.File;
import java.lang.reflect.Method;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;
import neton.client.config.ConfigManager;
import neton.client.config.Constants;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

public final class ClientIdUtils {
    public static final String DEFAULT_CLIENT_ID = "000000000000000";
    private static final String EXTRAS_KEY_CLIENT_ID = "clientId";
    private static final int EXTRAS_KEY_CLIENT_ID_LEN = 15;
    private static final String EXTRAS_KEY_DEFAULT_VALUE = "";
    private static final String EXTRAS_KEY_UNKNOWN = "unknown";
    private static final String EXTRAS_KEY_ZERO = "0";
    private static final int MAX_RETRY_COUNT = 3;
    private static final String MCS_CONTROL_PULL_MSG_INFO_FILE_NAME = "mcs_msg.ini";
    private static final String MCS_CONTROL_PULL_MSG_INFO_FILE_PATH = (MCS_HIDDEN_FILE_STORAGE_PATH + File.separator + MCS_CONTROL_PULL_MSG_INFO_FILE_NAME);
    private static final String MCS_FILE_SUFFIX_NAME = ".ini";
    private static final String MCS_HIDDEN_FILE_STORAGE_PATH = (Environment.getExternalStorageDirectory().getPath() + File.separator + MCS_HIDDEN_SD_CARD_FOLDER);
    private static final String MCS_HIDDEN_SD_CARD_FOLDER = ".mcs";
    private static final long MONTH_MILLIONS = 2592000000L;
    private static final long TIMER_DELAY_PERIOD = 3000;
    private static final long TIMER_DELAY_TIME = 3000;
    private static String sClientId = "";
    /* access modifiers changed from: private */
    public static final AtomicInteger sRetryCount = new AtomicInteger(0);
    private static Timer sTimer = null;

    private ClientIdUtils() {
    }

    public static String getClientId(Context context, boolean z) {
        if (!ApkInfoUtil.isRegionCN() || z) {
            return DEFAULT_CLIENT_ID;
        }
        if (context == null) {
            throw new NullPointerException("context is null.");
        }
        Context applicationContext = context.getApplicationContext();
        if (System.currentTimeMillis() - ConfigManager.getInstance().getLongData(Constants.KEY_CLINET_ID_MODIFY_TIME).longValue() > MONTH_MILLIONS) {
            ConfigManager.getInstance().setStringData(Constants.KEY_CLINET_ID, "");
        }
        String stringData = ConfigManager.getInstance().getStringData(Constants.KEY_CLINET_ID, DEFAULT_CLIENT_ID);
        sClientId = stringData;
        if (TextUtils.isEmpty(stringData) || DEFAULT_CLIENT_ID.equals(sClientId)) {
            synchronized (ClientIdUtils.class) {
                if (TextUtils.isEmpty(sClientId) || DEFAULT_CLIENT_ID.equals(sClientId)) {
                    sClientId = getClientIdFromFile(applicationContext);
                    ConfigManager.getInstance().setStringData(Constants.KEY_CLINET_ID, sClientId);
                    ConfigManager.getInstance().setLongData(Constants.KEY_CLINET_ID_MODIFY_TIME, Long.valueOf(System.currentTimeMillis()));
                }
            }
        }
        return TextUtils.isEmpty(sClientId) ? DEFAULT_CLIENT_ID : sClientId;
    }

    public static String getClientId(Context context) {
        return getClientId(context, true);
    }

    private static String getClientIdFromFile(Context context) {
        String localClientId = getLocalClientId();
        if (isNullOrEmpty(localClientId)) {
            localClientId = getClientIdByOS(context);
            if (localClientId == null) {
                startRetryTimer(context);
            } else if (isInvalidClientId(localClientId)) {
                localClientId = buildClientId();
            }
            setLocalClientId(localClientId);
        }
        return localClientId;
    }

    public static String getClientIdByOS(Context context) {
        String reflectColorImei = reflectColorImei(context);
        if (reflectColorImei == null) {
            return getDeviceId(context);
        }
        return reflectColorImei;
    }

    private static void startRetryTimer(final Context context) {
        if (sTimer == null) {
            sTimer = new Timer();
            sTimer.schedule(new TimerTask() {
                public final void run() {
                    String clientIdByOS = ClientIdUtils.getClientIdByOS(context);
                    if (clientIdByOS != null) {
                        if (ClientIdUtils.isInvalidClientId(clientIdByOS)) {
                            clientIdByOS = ClientIdUtils.buildClientId();
                        }
                        ClientIdUtils.setLocalClientId(clientIdByOS);
                        cancel();
                        ClientIdUtils.cancelTimer();
                    } else if (ClientIdUtils.MAX_RETRY_COUNT <= ClientIdUtils.sRetryCount.incrementAndGet()) {
                        cancel();
                        ClientIdUtils.cancelTimer();
                    }
                }
            }, 3000, 3000);
        }
    }

    /* access modifiers changed from: private */
    public static void cancelTimer() {
        if (sTimer != null) {
            sTimer.cancel();
            sRetryCount.set(0);
            sTimer = null;
        }
    }

    /* access modifiers changed from: private */
    public static boolean isInvalidClientId(String str) {
        return EXTRAS_KEY_UNKNOWN.equalsIgnoreCase(str) || "null".equalsIgnoreCase(str) || EXTRAS_KEY_ZERO.equalsIgnoreCase(str);
    }

    @SuppressLint({"HardwareIds"})
    private static String getDeviceId(Context context) {
        try {
            return ((TelephonyManager) context.getSystemService("phone")).getDeviceId();
        } catch (Exception e) {
            LogUtil.m790e(e.getMessage());
            return null;
        }
    }

    private static String reflectColorImei(Context context) {
        try {
            Class<?> cls = Class.forName("android.telephony.ColorOSTelephonyManager");
            Method method = cls.getMethod("getDefault", new Class[]{Context.class});
            return (String) cls.getMethod("colorGetImei", new Class[]{Integer.TYPE}).invoke(method.invoke(cls, new Object[]{context}), new Object[]{0});
        } catch (RuntimeException e) {
            throw e;
        } catch (Exception e2) {
            LogUtil.m790e("reflectColorImei--Exception");
            return null;
        }
    }

    private static String getLocalClientId() {
        String loadData = loadData(MCS_CONTROL_PULL_MSG_INFO_FILE_PATH);
        if (!isNullOrEmpty(loadData)) {
            return getString(parseObject(loadData, (JSONObject) null), EXTRAS_KEY_CLIENT_ID, "");
        }
        return null;
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0035, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0036, code lost:
        neton.client.Utils.LogUtil.m790e(r0.getMessage());
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void setLocalClientId(java.lang.String r3) {
        /*
            r0 = 0
            java.lang.String r1 = MCS_CONTROL_PULL_MSG_INFO_FILE_PATH     // Catch:{ Exception -> 0x003e }
            java.lang.String r1 = loadData(r1)     // Catch:{ Exception -> 0x003e }
            boolean r2 = isNullOrEmpty(r1)     // Catch:{ Exception -> 0x003e }
            if (r2 != 0) goto L_0x0012
            r0 = 0
            org.json.JSONObject r0 = parseObject(r1, r0)     // Catch:{ Exception -> 0x003e }
        L_0x0012:
            if (r0 != 0) goto L_0x0047
            org.json.JSONObject r0 = new org.json.JSONObject     // Catch:{ Exception -> 0x003e }
            r0.<init>()     // Catch:{ Exception -> 0x003e }
            r1 = r0
        L_0x001a:
            boolean r0 = isNullOrEmpty(r3)     // Catch:{ JSONException -> 0x0035 }
            if (r0 != 0) goto L_0x0025
            java.lang.String r0 = "clientId"
            r1.put(r0, r3)     // Catch:{ JSONException -> 0x0035 }
        L_0x0025:
            java.lang.String r0 = r1.toString()     // Catch:{ Exception -> 0x003e }
            boolean r1 = isNullOrEmpty(r0)     // Catch:{ Exception -> 0x003e }
            if (r1 != 0) goto L_0x0034
            java.lang.String r1 = MCS_CONTROL_PULL_MSG_INFO_FILE_PATH     // Catch:{ Exception -> 0x003e }
            saveData(r1, r0)     // Catch:{ Exception -> 0x003e }
        L_0x0034:
            return
        L_0x0035:
            r0 = move-exception
            java.lang.String r0 = r0.getMessage()     // Catch:{ Exception -> 0x003e }
            neton.client.Utils.LogUtil.m790e((java.lang.String) r0)     // Catch:{ Exception -> 0x003e }
            goto L_0x0025
        L_0x003e:
            r0 = move-exception
            java.lang.String r0 = r0.getMessage()
            neton.client.Utils.LogUtil.m790e((java.lang.String) r0)
            goto L_0x0034
        L_0x0047:
            r1 = r0
            goto L_0x001a
        */
        throw new UnsupportedOperationException("Method not decompiled: neton.client.Utils.ClientIdUtils.setLocalClientId(java.lang.String):void");
    }

    /* access modifiers changed from: private */
    public static String buildClientId() {
        String str = getTimeStamp().substring(0, 6) + getUUIDHashCode();
        if (str.length() < EXTRAS_KEY_CLIENT_ID_LEN) {
            str = (str + "123456789012345").substring(0, EXTRAS_KEY_CLIENT_ID_LEN);
        }
        return replaceNonHexChar(str);
    }

    private static String getUUIDHashCode() {
        UUID randomUUID = UUID.randomUUID();
        StringBuilder sb = new StringBuilder();
        sb.append(String.valueOf(randomUUID.toString().hashCode()));
        if (sb.length() < 9) {
            while (sb.length() < 9) {
                sb.append(EXTRAS_KEY_ZERO);
            }
        }
        return sb.substring(0, 9);
    }

    private static String loadData(String str) {
        StringBuilder readFile;
        if (!isExternalStorageMediaMounted() || (readFile = readFile(str, "utf-8")) == null) {
            return null;
        }
        return readFile.toString();
    }

    private static JSONObject parseObject(String str, JSONObject jSONObject) {
        Object parse = parse(str, jSONObject);
        return parse instanceof JSONObject ? (JSONObject) parse : jSONObject;
    }

    private static Object parse(String str, Object obj) {
        if (isNullOrEmpty(str)) {
            return obj;
        }
        try {
            return new JSONTokener(str).nextValue();
        } catch (JSONException e) {
            LogUtil.m790e(e.getMessage());
            return obj;
        }
    }

    private static String getString(JSONObject jSONObject, String str, String str2) {
        Object objectValue = getObjectValue(jSONObject, str, str2);
        return objectValue == null ? "" : objectValue.toString();
    }

    private static Object getObjectValue(JSONObject jSONObject, String str, Object obj) {
        if (jSONObject == null) {
            return obj;
        }
        try {
            if (!jSONObject.isNull(str)) {
                return jSONObject.get(str);
            }
            return obj;
        } catch (Exception e) {
            LogUtil.m790e(e.getMessage());
            return obj;
        }
    }

    private static boolean isExternalStorageMediaMounted() {
        return Environment.getExternalStorageState() != null && Environment.getExternalStorageState().equals("mounted");
    }

    /* JADX WARNING: Removed duplicated region for block: B:16:0x0049 A[SYNTHETIC, Splitter:B:16:0x0049] */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x0068 A[SYNTHETIC, Splitter:B:28:0x0068] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static java.lang.StringBuilder readFile(java.lang.String r5, java.lang.String r6) {
        /*
            r0 = 0
            java.io.File r2 = new java.io.File
            r2.<init>(r5)
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r3 = ""
            r1.<init>(r3)
            boolean r3 = r2.isFile()
            if (r3 != 0) goto L_0x0014
        L_0x0013:
            return r0
        L_0x0014:
            java.io.InputStreamReader r4 = new java.io.InputStreamReader     // Catch:{ IOException -> 0x0078, all -> 0x0064 }
            java.io.FileInputStream r3 = new java.io.FileInputStream     // Catch:{ IOException -> 0x0078, all -> 0x0064 }
            r3.<init>(r2)     // Catch:{ IOException -> 0x0078, all -> 0x0064 }
            r4.<init>(r3, r6)     // Catch:{ IOException -> 0x0078, all -> 0x0064 }
            java.io.BufferedReader r3 = new java.io.BufferedReader     // Catch:{ IOException -> 0x0078, all -> 0x0064 }
            r3.<init>(r4)     // Catch:{ IOException -> 0x0078, all -> 0x0064 }
        L_0x0023:
            java.lang.String r0 = r3.readLine()     // Catch:{ IOException -> 0x003e }
            if (r0 == 0) goto L_0x004e
            java.lang.String r2 = r1.toString()     // Catch:{ IOException -> 0x003e }
            java.lang.String r4 = ""
            boolean r2 = r2.equals(r4)     // Catch:{ IOException -> 0x003e }
            if (r2 != 0) goto L_0x003a
            java.lang.String r2 = "\r\n"
            r1.append(r2)     // Catch:{ IOException -> 0x003e }
        L_0x003a:
            r1.append(r0)     // Catch:{ IOException -> 0x003e }
            goto L_0x0023
        L_0x003e:
            r0 = move-exception
            r2 = r0
        L_0x0040:
            java.lang.String r0 = r2.getMessage()     // Catch:{ all -> 0x0075 }
            neton.client.Utils.LogUtil.m790e((java.lang.String) r0)     // Catch:{ all -> 0x0075 }
            if (r3 == 0) goto L_0x004c
            r3.close()     // Catch:{ IOException -> 0x005b }
        L_0x004c:
            r0 = r1
            goto L_0x0013
        L_0x004e:
            r3.close()     // Catch:{ IOException -> 0x0052 }
            goto L_0x004c
        L_0x0052:
            r0 = move-exception
            java.lang.String r0 = r0.getMessage()
            neton.client.Utils.LogUtil.m790e((java.lang.String) r0)
            goto L_0x004c
        L_0x005b:
            r0 = move-exception
            java.lang.String r0 = r0.getMessage()
            neton.client.Utils.LogUtil.m790e((java.lang.String) r0)
            goto L_0x004c
        L_0x0064:
            r1 = move-exception
            r3 = r0
        L_0x0066:
            if (r3 == 0) goto L_0x006b
            r3.close()     // Catch:{ IOException -> 0x006c }
        L_0x006b:
            throw r1
        L_0x006c:
            r0 = move-exception
            java.lang.String r0 = r0.getMessage()
            neton.client.Utils.LogUtil.m790e((java.lang.String) r0)
            goto L_0x006b
        L_0x0075:
            r0 = move-exception
            r1 = r0
            goto L_0x0066
        L_0x0078:
            r2 = move-exception
            r3 = r0
            goto L_0x0040
        */
        throw new UnsupportedOperationException("Method not decompiled: neton.client.Utils.ClientIdUtils.readFile(java.lang.String, java.lang.String):java.lang.StringBuilder");
    }

    private static void saveData(String str, String str2) {
        if (isExternalStorageMediaMounted()) {
            writeFile(str, str2, false);
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:18:0x0046 A[SYNTHETIC, Splitter:B:18:0x0046] */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x004f A[SYNTHETIC, Splitter:B:23:0x004f] */
    /* JADX WARNING: Removed duplicated region for block: B:34:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static boolean writeFile(java.lang.String r5, java.lang.String r6, boolean r7) {
        /*
            r0 = 0
            boolean r1 = isNullOrEmpty(r6)
            if (r1 == 0) goto L_0x0008
        L_0x0007:
            return r0
        L_0x0008:
            r2 = 0
            makeDirs(r5)     // Catch:{ IOException -> 0x002d }
            java.io.File r1 = new java.io.File     // Catch:{ IOException -> 0x002d }
            r1.<init>(r5)     // Catch:{ IOException -> 0x002d }
            boolean r3 = r1.exists()     // Catch:{ IOException -> 0x002d }
            if (r3 != 0) goto L_0x001d
            boolean r1 = r1.createNewFile()     // Catch:{ IOException -> 0x002d }
            if (r1 == 0) goto L_0x0007
        L_0x001d:
            java.io.FileWriter r3 = new java.io.FileWriter     // Catch:{ IOException -> 0x002d }
            r3.<init>(r5, r7)     // Catch:{ IOException -> 0x002d }
            r3.write(r6)     // Catch:{ IOException -> 0x005a, all -> 0x0057 }
            r3.flush()     // Catch:{ IOException -> 0x005a, all -> 0x0057 }
            r3.close()     // Catch:{ IOException -> 0x0053 }
        L_0x002b:
            r0 = 1
            goto L_0x0007
        L_0x002d:
            r1 = move-exception
        L_0x002e:
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ all -> 0x004c }
            java.lang.String r4 = "writeFile--IOException:"
            r3.<init>(r4)     // Catch:{ all -> 0x004c }
            java.lang.String r1 = r1.getMessage()     // Catch:{ all -> 0x004c }
            java.lang.StringBuilder r1 = r3.append(r1)     // Catch:{ all -> 0x004c }
            java.lang.String r1 = r1.toString()     // Catch:{ all -> 0x004c }
            neton.client.Utils.LogUtil.m790e((java.lang.String) r1)     // Catch:{ all -> 0x004c }
            if (r2 == 0) goto L_0x0007
            r2.close()     // Catch:{ IOException -> 0x004a }
            goto L_0x0007
        L_0x004a:
            r1 = move-exception
            goto L_0x0007
        L_0x004c:
            r0 = move-exception
        L_0x004d:
            if (r2 == 0) goto L_0x0052
            r2.close()     // Catch:{ IOException -> 0x0055 }
        L_0x0052:
            throw r0
        L_0x0053:
            r0 = move-exception
            goto L_0x002b
        L_0x0055:
            r1 = move-exception
            goto L_0x0052
        L_0x0057:
            r0 = move-exception
            r2 = r3
            goto L_0x004d
        L_0x005a:
            r1 = move-exception
            r2 = r3
            goto L_0x002e
        */
        throw new UnsupportedOperationException("Method not decompiled: neton.client.Utils.ClientIdUtils.writeFile(java.lang.String, java.lang.String, boolean):boolean");
    }

    private static boolean makeDirs(String str) {
        String folderName = getFolderName(str);
        if (isNullOrEmpty(folderName)) {
            return false;
        }
        File file = new File(folderName);
        if (file.exists() || file.mkdirs()) {
            return true;
        }
        return false;
    }

    private static String getFolderName(String str) {
        if (isNullOrEmpty(str)) {
            return str;
        }
        int lastIndexOf = str.lastIndexOf(File.separator);
        return lastIndexOf == -1 ? "" : str.substring(0, lastIndexOf);
    }

    private static String getTimeStamp() {
        return new SimpleDateFormat("yyMMddHHmmssSSS", Locale.getDefault()).format(new Date());
    }

    private static String replaceNonHexChar(String str) {
        if (isNullOrEmpty(str)) {
            return str;
        }
        byte[] bytes = str.getBytes(Charset.defaultCharset());
        for (int i = 0; i < bytes.length; i++) {
            if (!isHexDigit(bytes[i])) {
                bytes[i] = 48;
            }
        }
        return new String(bytes, Charset.defaultCharset());
    }

    private static boolean isHexDigit(byte b) {
        return (b >= 48 && b <= 57) || (b >= 97 && b <= 122) || (b >= 65 && b <= 90);
    }

    private static boolean isNullOrEmpty(String str) {
        return str == null || "".equals(str.trim());
    }
}
