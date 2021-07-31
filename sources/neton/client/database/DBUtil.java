package neton.client.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import neton.client.Utils.LogUtil;
import neton.client.dns.AddressInfo;
import neton.client.dns.DnsInfo;
import neton.client.dns.IpInfo;

public class DBUtil {
    public static final Object mLockObject = new Object();

    private static SQLiteDatabase initDatabase(Context context) {
        SQLiteDatabase openDatabase;
        synchronized (mLockObject) {
            DatabaseManager.initializeInstance(DBHelper.getInstance(context));
            openDatabase = DatabaseManager.getInstance().openDatabase();
        }
        return openDatabase;
    }

    private static void closeDatabase(SQLiteDatabase sQLiteDatabase) {
    }

    private static void closeCursor(Cursor cursor) {
        if (cursor != null) {
            try {
                if (!cursor.isClosed()) {
                    cursor.close();
                }
            } catch (Exception e) {
                LogUtil.m787d("closeCursor--Exception");
            }
        }
    }

    private static void closeSource(SQLiteDatabase sQLiteDatabase, Cursor cursor) {
        closeCursor(cursor);
        closeDatabase(sQLiteDatabase);
    }

    /* JADX WARNING: Removed duplicated region for block: B:31:0x008e A[Catch:{ Exception -> 0x005d, all -> 0x0082 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.util.LinkedList<neton.client.dns.AddressInfo> getAddressInfoList(android.content.Context r10, int r11) {
        /*
            r8 = 0
            java.lang.String r0 = "getAddressInfoList: start."
            neton.client.Utils.LogUtil.m793i(r0)
            java.lang.Object r9 = mLockObject
            monitor-enter(r9)
            android.database.sqlite.SQLiteDatabase r0 = initDatabase(r10)     // Catch:{ Exception -> 0x005d, all -> 0x0082 }
            java.lang.String r3 = "dnsType=?"
            r1 = 1
            java.lang.String[] r4 = new java.lang.String[r1]     // Catch:{ Exception -> 0x00ba, all -> 0x00ab }
            r1 = 0
            java.lang.String r2 = java.lang.String.valueOf(r11)     // Catch:{ Exception -> 0x00ba, all -> 0x00ab }
            r4[r1] = r2     // Catch:{ Exception -> 0x00ba, all -> 0x00ab }
            java.lang.String r1 = "address_info"
            r2 = 0
            r5 = 0
            r6 = 0
            r7 = 0
            android.database.Cursor r2 = r0.query(r1, r2, r3, r4, r5, r6, r7)     // Catch:{ Exception -> 0x00ba, all -> 0x00ab }
            java.util.LinkedList r4 = new java.util.LinkedList     // Catch:{ Exception -> 0x00bf, all -> 0x00b0 }
            r4.<init>()     // Catch:{ Exception -> 0x00bf, all -> 0x00b0 }
            if (r2 == 0) goto L_0x003d
            boolean r1 = r2.moveToLast()     // Catch:{ Exception -> 0x00c3, all -> 0x00b4 }
            if (r1 == 0) goto L_0x003d
        L_0x0030:
            neton.client.dns.AddressInfo r1 = neton.client.dns.AddressInfo.getAddressInfoByCursor(r2)     // Catch:{ Exception -> 0x00c3, all -> 0x00b4 }
            r4.add(r1)     // Catch:{ Exception -> 0x00c3, all -> 0x00b4 }
            boolean r1 = r2.moveToPrevious()     // Catch:{ Exception -> 0x00c3, all -> 0x00b4 }
            if (r1 != 0) goto L_0x0030
        L_0x003d:
            java.lang.String r1 = "getAddressInfoList: finish."
            neton.client.Utils.LogUtil.m793i(r1)     // Catch:{ all -> 0x00a8 }
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ all -> 0x00a8 }
            java.lang.String r3 = "getAddressInfoList size:"
            r1.<init>(r3)     // Catch:{ all -> 0x00a8 }
            int r3 = r4.size()     // Catch:{ all -> 0x00a8 }
            java.lang.StringBuilder r1 = r1.append(r3)     // Catch:{ all -> 0x00a8 }
            java.lang.String r1 = r1.toString()     // Catch:{ all -> 0x00a8 }
            neton.client.Utils.LogUtil.m787d(r1)     // Catch:{ all -> 0x00a8 }
            closeSource(r0, r2)     // Catch:{ all -> 0x00a8 }
        L_0x005b:
            monitor-exit(r9)     // Catch:{ all -> 0x00a8 }
            return r4
        L_0x005d:
            r0 = move-exception
            r1 = r0
            r2 = r8
            r3 = r8
            r4 = r8
        L_0x0062:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ all -> 0x00b7 }
            java.lang.String r5 = "getAddressInfoList:"
            r0.<init>(r5)     // Catch:{ all -> 0x00b7 }
            java.lang.String r1 = r1.getMessage()     // Catch:{ all -> 0x00b7 }
            java.lang.StringBuilder r0 = r0.append(r1)     // Catch:{ all -> 0x00b7 }
            java.lang.String r0 = r0.toString()     // Catch:{ all -> 0x00b7 }
            neton.client.Utils.LogUtil.m790e((java.lang.String) r0)     // Catch:{ all -> 0x00b7 }
            java.lang.String r0 = "getAddressInfoList: finish."
            neton.client.Utils.LogUtil.m793i(r0)     // Catch:{ all -> 0x00a8 }
            closeSource(r3, r2)     // Catch:{ all -> 0x00a8 }
            r4 = r8
            goto L_0x005b
        L_0x0082:
            r0 = move-exception
            r1 = r0
            r2 = r8
            r3 = r8
            r4 = r8
        L_0x0087:
            java.lang.String r0 = "getAddressInfoList: finish."
            neton.client.Utils.LogUtil.m793i(r0)     // Catch:{ all -> 0x00a8 }
            if (r4 == 0) goto L_0x00a4
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ all -> 0x00a8 }
            java.lang.String r5 = "getAddressInfoList size:"
            r0.<init>(r5)     // Catch:{ all -> 0x00a8 }
            int r4 = r4.size()     // Catch:{ all -> 0x00a8 }
            java.lang.StringBuilder r0 = r0.append(r4)     // Catch:{ all -> 0x00a8 }
            java.lang.String r0 = r0.toString()     // Catch:{ all -> 0x00a8 }
            neton.client.Utils.LogUtil.m787d(r0)     // Catch:{ all -> 0x00a8 }
        L_0x00a4:
            closeSource(r3, r2)     // Catch:{ all -> 0x00a8 }
            throw r1     // Catch:{ all -> 0x00a8 }
        L_0x00a8:
            r0 = move-exception
            monitor-exit(r9)     // Catch:{ all -> 0x00a8 }
            throw r0
        L_0x00ab:
            r1 = move-exception
            r2 = r8
            r3 = r0
            r4 = r8
            goto L_0x0087
        L_0x00b0:
            r1 = move-exception
            r3 = r0
            r4 = r8
            goto L_0x0087
        L_0x00b4:
            r1 = move-exception
            r3 = r0
            goto L_0x0087
        L_0x00b7:
            r0 = move-exception
            r1 = r0
            goto L_0x0087
        L_0x00ba:
            r1 = move-exception
            r2 = r8
            r3 = r0
            r4 = r8
            goto L_0x0062
        L_0x00bf:
            r1 = move-exception
            r3 = r0
            r4 = r8
            goto L_0x0062
        L_0x00c3:
            r1 = move-exception
            r3 = r0
            goto L_0x0062
        */
        throw new UnsupportedOperationException("Method not decompiled: neton.client.database.DBUtil.getAddressInfoList(android.content.Context, int):java.util.LinkedList");
    }

    /* JADX WARNING: Removed duplicated region for block: B:40:0x0096  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.util.LinkedList<neton.client.dns.AddressInfo> getAddressInfoList(android.content.Context r10, int r11, java.lang.String r12) {
        /*
            r8 = 0
            java.lang.String r0 = "getAddressInfoList: start."
            neton.client.Utils.LogUtil.m793i(r0)
            java.lang.Object r9 = mLockObject
            monitor-enter(r9)
            android.database.sqlite.SQLiteDatabase r0 = initDatabase(r10)     // Catch:{ Exception -> 0x0063, all -> 0x008a }
            java.lang.String r3 = "host=? and dnsType=?"
            r1 = 2
            java.lang.String[] r4 = new java.lang.String[r1]     // Catch:{ Exception -> 0x00bf, all -> 0x00b0 }
            r1 = 0
            r4[r1] = r12     // Catch:{ Exception -> 0x00bf, all -> 0x00b0 }
            r1 = 1
            java.lang.String r2 = java.lang.String.valueOf(r11)     // Catch:{ Exception -> 0x00bf, all -> 0x00b0 }
            r4[r1] = r2     // Catch:{ Exception -> 0x00bf, all -> 0x00b0 }
            java.lang.String r1 = "address_info"
            r2 = 0
            r5 = 0
            r6 = 0
            r7 = 0
            android.database.Cursor r2 = r0.query(r1, r2, r3, r4, r5, r6, r7)     // Catch:{ Exception -> 0x00bf, all -> 0x00b0 }
            if (r2 == 0) goto L_0x0041
            boolean r1 = r2.moveToLast()     // Catch:{ Exception -> 0x00c4, all -> 0x00b5 }
            if (r1 == 0) goto L_0x0041
            java.util.LinkedList r4 = new java.util.LinkedList     // Catch:{ Exception -> 0x00c4, all -> 0x00b5 }
            r4.<init>()     // Catch:{ Exception -> 0x00c4, all -> 0x00b5 }
        L_0x0033:
            neton.client.dns.AddressInfo r1 = neton.client.dns.AddressInfo.getAddressInfoByCursor(r2)     // Catch:{ Exception -> 0x00c8, all -> 0x00b9 }
            r4.add(r1)     // Catch:{ Exception -> 0x00c8, all -> 0x00b9 }
            boolean r1 = r2.moveToPrevious()     // Catch:{ Exception -> 0x00c8, all -> 0x00b9 }
            if (r1 != 0) goto L_0x0033
            r8 = r4
        L_0x0041:
            java.lang.String r1 = "getAddressInfoList: finish."
            neton.client.Utils.LogUtil.m793i(r1)     // Catch:{ all -> 0x0087 }
            if (r8 == 0) goto L_0x005e
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ all -> 0x0087 }
            java.lang.String r3 = "getAddressInfoList size:"
            r1.<init>(r3)     // Catch:{ all -> 0x0087 }
            int r3 = r8.size()     // Catch:{ all -> 0x0087 }
            java.lang.StringBuilder r1 = r1.append(r3)     // Catch:{ all -> 0x0087 }
            java.lang.String r1 = r1.toString()     // Catch:{ all -> 0x0087 }
            neton.client.Utils.LogUtil.m793i(r1)     // Catch:{ all -> 0x0087 }
        L_0x005e:
            closeSource(r0, r2)     // Catch:{ all -> 0x0087 }
        L_0x0061:
            monitor-exit(r9)     // Catch:{ all -> 0x0087 }
            return r8
        L_0x0063:
            r0 = move-exception
            r1 = r0
            r2 = r8
            r3 = r8
            r4 = r8
        L_0x0068:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ all -> 0x00bc }
            java.lang.String r5 = "getAddressInfoList:"
            r0.<init>(r5)     // Catch:{ all -> 0x00bc }
            java.lang.String r1 = r1.getMessage()     // Catch:{ all -> 0x00bc }
            java.lang.StringBuilder r0 = r0.append(r1)     // Catch:{ all -> 0x00bc }
            java.lang.String r0 = r0.toString()     // Catch:{ all -> 0x00bc }
            neton.client.Utils.LogUtil.m790e((java.lang.String) r0)     // Catch:{ all -> 0x00bc }
            java.lang.String r0 = "getAddressInfoList: finish."
            neton.client.Utils.LogUtil.m793i(r0)     // Catch:{ all -> 0x0087 }
            closeSource(r3, r2)     // Catch:{ all -> 0x0087 }
            goto L_0x0061
        L_0x0087:
            r0 = move-exception
            monitor-exit(r9)     // Catch:{ all -> 0x0087 }
            throw r0
        L_0x008a:
            r0 = move-exception
            r1 = r0
            r2 = r8
            r3 = r8
            r4 = r8
        L_0x008f:
            java.lang.String r0 = "getAddressInfoList: finish."
            neton.client.Utils.LogUtil.m793i(r0)     // Catch:{ all -> 0x0087 }
            if (r4 == 0) goto L_0x00ac
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ all -> 0x0087 }
            java.lang.String r5 = "getAddressInfoList size:"
            r0.<init>(r5)     // Catch:{ all -> 0x0087 }
            int r4 = r4.size()     // Catch:{ all -> 0x0087 }
            java.lang.StringBuilder r0 = r0.append(r4)     // Catch:{ all -> 0x0087 }
            java.lang.String r0 = r0.toString()     // Catch:{ all -> 0x0087 }
            neton.client.Utils.LogUtil.m793i(r0)     // Catch:{ all -> 0x0087 }
        L_0x00ac:
            closeSource(r3, r2)     // Catch:{ all -> 0x0087 }
            throw r1     // Catch:{ all -> 0x0087 }
        L_0x00b0:
            r1 = move-exception
            r2 = r8
            r3 = r0
            r4 = r8
            goto L_0x008f
        L_0x00b5:
            r1 = move-exception
            r3 = r0
            r4 = r8
            goto L_0x008f
        L_0x00b9:
            r1 = move-exception
            r3 = r0
            goto L_0x008f
        L_0x00bc:
            r0 = move-exception
            r1 = r0
            goto L_0x008f
        L_0x00bf:
            r1 = move-exception
            r2 = r8
            r3 = r0
            r4 = r8
            goto L_0x0068
        L_0x00c4:
            r1 = move-exception
            r3 = r0
            r4 = r8
            goto L_0x0068
        L_0x00c8:
            r1 = move-exception
            r3 = r0
            goto L_0x0068
        */
        throw new UnsupportedOperationException("Method not decompiled: neton.client.database.DBUtil.getAddressInfoList(android.content.Context, int, java.lang.String):java.util.LinkedList");
    }

    public static AddressInfo getAddressInfo(Context context, int i, String str) {
        SQLiteDatabase sQLiteDatabase;
        Cursor cursor;
        Throwable th;
        Exception e;
        AddressInfo addressInfo = null;
        LogUtil.m793i("getAddressInfo: start..--dnsType:" + i + ",url:" + str);
        synchronized (mLockObject) {
            try {
                SQLiteDatabase initDatabase = initDatabase(context);
                try {
                    cursor = initDatabase.query(DBHelper.TABLE_IP_INFO, (String[]) null, "host=? and dnsType=?", new String[]{str, String.valueOf(i)}, (String) null, (String) null, (String) null);
                } catch (Exception e2) {
                    e = e2;
                    cursor = null;
                    sQLiteDatabase = initDatabase;
                } catch (Throwable th2) {
                    th = th2;
                    cursor = null;
                    sQLiteDatabase = initDatabase;
                    LogUtil.m793i("getAppMessageByMessageId: finish.");
                    closeSource(sQLiteDatabase, cursor);
                    throw th;
                }
                try {
                    addressInfo = AddressInfo.getAddressInfoByCursor(cursor);
                    LogUtil.m793i("getAppMessageByMessageId: finish.");
                    closeSource(initDatabase, cursor);
                } catch (Exception e3) {
                    e = e3;
                    sQLiteDatabase = initDatabase;
                    try {
                        LogUtil.m790e("getAddressInfo:" + e.getMessage());
                        LogUtil.m793i("getAppMessageByMessageId: finish.");
                        closeSource(sQLiteDatabase, cursor);
                        return addressInfo;
                    } catch (Throwable th3) {
                        th = th3;
                        LogUtil.m793i("getAppMessageByMessageId: finish.");
                        closeSource(sQLiteDatabase, cursor);
                        throw th;
                    }
                } catch (Throwable th4) {
                    th = th4;
                    sQLiteDatabase = initDatabase;
                    LogUtil.m793i("getAppMessageByMessageId: finish.");
                    closeSource(sQLiteDatabase, cursor);
                    throw th;
                }
            } catch (Exception e4) {
                e = e4;
                cursor = null;
                sQLiteDatabase = null;
                LogUtil.m790e("getAddressInfo:" + e.getMessage());
                LogUtil.m793i("getAppMessageByMessageId: finish.");
                closeSource(sQLiteDatabase, cursor);
                return addressInfo;
            } catch (Throwable th5) {
                th = th5;
                cursor = null;
                sQLiteDatabase = null;
                LogUtil.m793i("getAppMessageByMessageId: finish.");
                closeSource(sQLiteDatabase, cursor);
                throw th;
            }
        }
        return addressInfo;
    }

    /* JADX WARNING: Unknown top exception splitter block from list: {B:30:0x0066=Splitter:B:30:0x0066, B:15:0x003c=Splitter:B:15:0x003c} */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.util.LinkedList<neton.client.dns.IpInfo> getIpInfoList(android.content.Context r10, int r11, java.lang.String r12) {
        /*
            r8 = 0
            java.lang.Object r9 = mLockObject
            monitor-enter(r9)
            android.database.sqlite.SQLiteDatabase r0 = initDatabase(r10)     // Catch:{ Exception -> 0x0041, all -> 0x0062 }
            java.lang.String r3 = "host=? and dnsType=?"
            r1 = 2
            java.lang.String[] r4 = new java.lang.String[r1]     // Catch:{ Exception -> 0x0074, all -> 0x006a }
            r1 = 0
            r4[r1] = r12     // Catch:{ Exception -> 0x0074, all -> 0x006a }
            r1 = 1
            java.lang.String r2 = java.lang.String.valueOf(r11)     // Catch:{ Exception -> 0x0074, all -> 0x006a }
            r4[r1] = r2     // Catch:{ Exception -> 0x0074, all -> 0x006a }
            java.lang.String r1 = "ip_info"
            r2 = 0
            r5 = 0
            r6 = 0
            r7 = 0
            android.database.Cursor r2 = r0.query(r1, r2, r3, r4, r5, r6, r7)     // Catch:{ Exception -> 0x0074, all -> 0x006a }
            if (r2 == 0) goto L_0x003c
            boolean r1 = r2.moveToLast()     // Catch:{ Exception -> 0x0078, all -> 0x006e }
            if (r1 == 0) goto L_0x003c
            java.util.LinkedList r1 = new java.util.LinkedList     // Catch:{ Exception -> 0x0078, all -> 0x006e }
            r1.<init>()     // Catch:{ Exception -> 0x0078, all -> 0x006e }
        L_0x002e:
            neton.client.dns.IpInfo r3 = neton.client.dns.IpInfo.getIpInfoByCursor(r12, r11, r2)     // Catch:{ Exception -> 0x0078, all -> 0x006e }
            r1.add(r3)     // Catch:{ Exception -> 0x0078, all -> 0x006e }
            boolean r3 = r2.moveToPrevious()     // Catch:{ Exception -> 0x0078, all -> 0x006e }
            if (r3 != 0) goto L_0x002e
            r8 = r1
        L_0x003c:
            closeSource(r0, r2)     // Catch:{ all -> 0x005f }
        L_0x003f:
            monitor-exit(r9)     // Catch:{ all -> 0x005f }
            return r8
        L_0x0041:
            r0 = move-exception
            r1 = r0
            r2 = r8
            r3 = r8
        L_0x0045:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ all -> 0x0071 }
            java.lang.String r4 = "getIpInfoList:"
            r0.<init>(r4)     // Catch:{ all -> 0x0071 }
            java.lang.String r1 = r1.getMessage()     // Catch:{ all -> 0x0071 }
            java.lang.StringBuilder r0 = r0.append(r1)     // Catch:{ all -> 0x0071 }
            java.lang.String r0 = r0.toString()     // Catch:{ all -> 0x0071 }
            neton.client.Utils.LogUtil.m790e((java.lang.String) r0)     // Catch:{ all -> 0x0071 }
            closeSource(r3, r2)     // Catch:{ all -> 0x005f }
            goto L_0x003f
        L_0x005f:
            r0 = move-exception
            monitor-exit(r9)     // Catch:{ all -> 0x005f }
            throw r0
        L_0x0062:
            r0 = move-exception
            r1 = r0
            r2 = r8
            r3 = r8
        L_0x0066:
            closeSource(r3, r2)     // Catch:{ all -> 0x005f }
            throw r1     // Catch:{ all -> 0x005f }
        L_0x006a:
            r1 = move-exception
            r2 = r8
            r3 = r0
            goto L_0x0066
        L_0x006e:
            r1 = move-exception
            r3 = r0
            goto L_0x0066
        L_0x0071:
            r0 = move-exception
            r1 = r0
            goto L_0x0066
        L_0x0074:
            r1 = move-exception
            r2 = r8
            r3 = r0
            goto L_0x0045
        L_0x0078:
            r1 = move-exception
            r3 = r0
            goto L_0x0045
        */
        throw new UnsupportedOperationException("Method not decompiled: neton.client.database.DBUtil.getIpInfoList(android.content.Context, int, java.lang.String):java.util.LinkedList");
    }

    public static void addAddressInfo(Context context, AddressInfo addressInfo) {
        LogUtil.m793i("addAddressInfo start.:" + addressInfo.toString());
        synchronized (mLockObject) {
            try {
                ContentValues contentValues = new ContentValues();
                contentValues.put(DnsInfo.HOST, addressInfo.getHost());
                contentValues.put(DnsInfo.DNS_TYPE, Integer.valueOf(addressInfo.getDnsType()));
                contentValues.put(DnsInfo.TTL, Long.valueOf(addressInfo.getTtl()));
                contentValues.put(AddressInfo.TIMESTAMP, Long.valueOf(addressInfo.getTimeStamp()));
                addDataToDB(context, DBHelper.TABLE_ADDRESS_INFO, contentValues);
                LogUtil.m793i("addAddressInfo finish,");
            } catch (Exception e) {
                LogUtil.m790e("addAddressInfo--Exception");
            }
        }
    }

    public static void addIpInfo(Context context, IpInfo ipInfo) {
        LogUtil.m793i("addIpInfo start.:" + ipInfo);
        synchronized (mLockObject) {
            try {
                ContentValues contentValues = new ContentValues();
                contentValues.put(DnsInfo.HOST, ipInfo.getHost());
                contentValues.put(DnsInfo.DNS_TYPE, Integer.valueOf(ipInfo.getDnsType()));
                contentValues.put("ip", ipInfo.getIp());
                contentValues.put(IpInfo.PORT, Integer.valueOf(ipInfo.getPort()));
                contentValues.put(IpInfo.LOCAL, ipInfo.getLocal());
                contentValues.put(IpInfo.WEIGHT, Integer.valueOf(ipInfo.getWeight()));
                contentValues.put(IpInfo.f824SP, ipInfo.getSp());
                contentValues.put(IpInfo.FAIL_COUNT, Integer.valueOf(ipInfo.getFailCount()));
                contentValues.put(IpInfo.FAIL_TIME, Long.valueOf(ipInfo.getFailTime()));
                addDataToDB(context, DBHelper.TABLE_IP_INFO, contentValues);
                LogUtil.m793i("addIpInfo finish,");
            } catch (Exception e) {
                LogUtil.m790e("addIpInfo--Exception");
            }
        }
    }

    public static void updateAddressInfo(Context context, AddressInfo addressInfo) {
        LogUtil.m793i("updateAddressInfo start.:" + addressInfo.toString());
        ContentValues contentValues = new ContentValues();
        contentValues.put(DnsInfo.HOST, addressInfo.getHost());
        contentValues.put(DnsInfo.DNS_TYPE, Integer.valueOf(addressInfo.getDnsType()));
        contentValues.put(DnsInfo.TTL, Long.valueOf(addressInfo.getTtl()));
        contentValues.put(AddressInfo.TIMESTAMP, Long.valueOf(addressInfo.getTimeStamp()));
        synchronized (mLockObject) {
            SQLiteDatabase sQLiteDatabase = null;
            try {
                sQLiteDatabase = initDatabase(context);
                sQLiteDatabase.update(DBHelper.TABLE_ADDRESS_INFO, contentValues, "host=? and dnsType=?", new String[]{addressInfo.getHost(), new StringBuilder().append(addressInfo.getDnsType()).toString()});
                closeDatabase(sQLiteDatabase);
            } catch (Exception e) {
                LogUtil.m790e("updateAddressInfo--Exception:" + e.toString());
                closeDatabase(sQLiteDatabase);
            } catch (Throwable th) {
                closeDatabase(sQLiteDatabase);
                throw th;
            }
        }
    }

    public static void updateIpInfo(Context context, IpInfo ipInfo) {
        LogUtil.m793i("updateIpInfo start.:" + ipInfo.toString());
        ContentValues contentValues = new ContentValues();
        contentValues.put(IpInfo.FAIL_COUNT, Integer.valueOf(ipInfo.getFailCount()));
        contentValues.put(IpInfo.FAIL_TIME, Long.valueOf(ipInfo.getFailTime()));
        synchronized (mLockObject) {
            SQLiteDatabase sQLiteDatabase = null;
            try {
                sQLiteDatabase = initDatabase(context);
                sQLiteDatabase.update(DBHelper.TABLE_IP_INFO, contentValues, "host=? and dnsType=? and ip=? and port=?", new String[]{ipInfo.getHost(), new StringBuilder().append(ipInfo.getDnsType()).toString(), ipInfo.getIp(), new StringBuilder().append(ipInfo.getPort()).toString()});
                closeDatabase(sQLiteDatabase);
            } catch (Exception e) {
                LogUtil.m790e("updateIpInfo--Exception:" + e.toString());
                closeDatabase(sQLiteDatabase);
            } catch (Throwable th) {
                closeDatabase(sQLiteDatabase);
                throw th;
            }
        }
    }

    /* JADX WARNING: Unknown top exception splitter block from list: {B:27:0x0073=Splitter:B:27:0x0073, B:35:0x0095=Splitter:B:35:0x0095, B:11:0x0037=Splitter:B:11:0x0037} */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static void addDataToDB(android.content.Context r6, java.lang.String r7, android.content.ContentValues r8) {
        /*
            r1 = 0
            java.lang.Object r2 = mLockObject
            monitor-enter(r2)
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ all -> 0x003c }
            java.lang.String r3 = "addDataToDB-table:"
            r0.<init>(r3)     // Catch:{ all -> 0x003c }
            java.lang.StringBuilder r0 = r0.append(r7)     // Catch:{ all -> 0x003c }
            java.lang.String r3 = ",values:"
            java.lang.StringBuilder r0 = r0.append(r3)     // Catch:{ all -> 0x003c }
            java.lang.StringBuilder r0 = r0.append(r8)     // Catch:{ all -> 0x003c }
            java.lang.String r0 = r0.toString()     // Catch:{ all -> 0x003c }
            neton.client.Utils.LogUtil.m793i(r0)     // Catch:{ all -> 0x003c }
            if (r8 != 0) goto L_0x0024
            monitor-exit(r2)     // Catch:{ all -> 0x003c }
        L_0x0023:
            return
        L_0x0024:
            android.database.sqlite.SQLiteDatabase r1 = initDatabase(r6)     // Catch:{ Exception -> 0x0057 }
            r1.beginTransaction()     // Catch:{ Exception -> 0x0057 }
            r0 = 0
            r1.insert(r7, r0, r8)     // Catch:{ Exception -> 0x0057 }
            r1.setTransactionSuccessful()     // Catch:{ Exception -> 0x0057 }
            if (r1 == 0) goto L_0x0037
            r1.endTransaction()     // Catch:{ Exception -> 0x003f }
        L_0x0037:
            closeDatabase(r1)     // Catch:{ all -> 0x003c }
        L_0x003a:
            monitor-exit(r2)     // Catch:{ all -> 0x003c }
            goto L_0x0023
        L_0x003c:
            r0 = move-exception
            monitor-exit(r2)     // Catch:{ all -> 0x003c }
            throw r0
        L_0x003f:
            r0 = move-exception
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ all -> 0x003c }
            java.lang.String r4 = "addDataToDB--endTransaction--Exception:"
            r3.<init>(r4)     // Catch:{ all -> 0x003c }
            java.lang.String r0 = r0.toString()     // Catch:{ all -> 0x003c }
            java.lang.StringBuilder r0 = r3.append(r0)     // Catch:{ all -> 0x003c }
            java.lang.String r0 = r0.toString()     // Catch:{ all -> 0x003c }
            neton.client.Utils.LogUtil.m790e((java.lang.String) r0)     // Catch:{ all -> 0x003c }
            goto L_0x0037
        L_0x0057:
            r0 = move-exception
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ all -> 0x008f }
            java.lang.String r4 = "addDataToDB--Exception:"
            r3.<init>(r4)     // Catch:{ all -> 0x008f }
            java.lang.String r0 = r0.toString()     // Catch:{ all -> 0x008f }
            java.lang.StringBuilder r0 = r3.append(r0)     // Catch:{ all -> 0x008f }
            java.lang.String r0 = r0.toString()     // Catch:{ all -> 0x008f }
            neton.client.Utils.LogUtil.m790e((java.lang.String) r0)     // Catch:{ all -> 0x008f }
            if (r1 == 0) goto L_0x0073
            r1.endTransaction()     // Catch:{ Exception -> 0x0077 }
        L_0x0073:
            closeDatabase(r1)     // Catch:{ all -> 0x003c }
            goto L_0x003a
        L_0x0077:
            r0 = move-exception
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ all -> 0x003c }
            java.lang.String r4 = "addDataToDB--endTransaction--Exception:"
            r3.<init>(r4)     // Catch:{ all -> 0x003c }
            java.lang.String r0 = r0.toString()     // Catch:{ all -> 0x003c }
            java.lang.StringBuilder r0 = r3.append(r0)     // Catch:{ all -> 0x003c }
            java.lang.String r0 = r0.toString()     // Catch:{ all -> 0x003c }
            neton.client.Utils.LogUtil.m790e((java.lang.String) r0)     // Catch:{ all -> 0x003c }
            goto L_0x0073
        L_0x008f:
            r0 = move-exception
            if (r1 == 0) goto L_0x0095
            r1.endTransaction()     // Catch:{ Exception -> 0x0099 }
        L_0x0095:
            closeDatabase(r1)     // Catch:{ all -> 0x003c }
            throw r0     // Catch:{ all -> 0x003c }
        L_0x0099:
            r3 = move-exception
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ all -> 0x003c }
            java.lang.String r5 = "addDataToDB--endTransaction--Exception:"
            r4.<init>(r5)     // Catch:{ all -> 0x003c }
            java.lang.String r3 = r3.toString()     // Catch:{ all -> 0x003c }
            java.lang.StringBuilder r3 = r4.append(r3)     // Catch:{ all -> 0x003c }
            java.lang.String r3 = r3.toString()     // Catch:{ all -> 0x003c }
            neton.client.Utils.LogUtil.m790e((java.lang.String) r3)     // Catch:{ all -> 0x003c }
            goto L_0x0095
        */
        throw new UnsupportedOperationException("Method not decompiled: neton.client.database.DBUtil.addDataToDB(android.content.Context, java.lang.String, android.content.ContentValues):void");
    }

    private static void deleteAllInTable(Context context, String str) {
        SQLiteDatabase sQLiteDatabase = null;
        synchronized (mLockObject) {
            LogUtil.m793i("deleteAllInTable-table:" + str);
            try {
                sQLiteDatabase = initDatabase(context);
                sQLiteDatabase.delete(str, (String) null, (String[]) null);
                closeDatabase(sQLiteDatabase);
            } catch (Exception e) {
                LogUtil.m790e("deleteAllInTable--Exception");
                closeDatabase(sQLiteDatabase);
            } catch (Throwable th) {
                closeDatabase(sQLiteDatabase);
                throw th;
            }
        }
    }

    public static void deleteAddressInTable(Context context, String str, String str2, int i) {
        synchronized (mLockObject) {
            LogUtil.m793i("deleteAddressInTable-table:" + str + ",url:" + str2 + ",dnsType:" + i);
            SQLiteDatabase sQLiteDatabase = null;
            try {
                sQLiteDatabase = initDatabase(context);
                sQLiteDatabase.delete(str, "host=? and dnsType=?", new String[]{str2, String.valueOf(i)});
                closeDatabase(sQLiteDatabase);
            } catch (Exception e) {
                LogUtil.m790e("deleteAddressInTable--Exception");
                closeDatabase(sQLiteDatabase);
            } catch (Throwable th) {
                closeDatabase(sQLiteDatabase);
                throw th;
            }
        }
    }

    public static void deleteIpInfosByHostAndDnsType(Context context, String str, int i) {
        synchronized (mLockObject) {
            LogUtil.m793i("deleteIpInfosByHostAndDnsType-host:" + str + ",dnsType:" + i);
            SQLiteDatabase sQLiteDatabase = null;
            try {
                sQLiteDatabase = initDatabase(context);
                LogUtil.m793i("deleteIpInfosByHostAndDnsType-host:" + str + ",dnsType:" + i + ",count:" + sQLiteDatabase.delete(DBHelper.TABLE_IP_INFO, "host=? and dnsType=?", new String[]{str, String.valueOf(i)}));
                closeDatabase(sQLiteDatabase);
            } catch (Exception e) {
                LogUtil.m790e("deleteIpInfosByHostAndDnsType--Exception:" + e.getMessage());
                closeDatabase(sQLiteDatabase);
            } catch (Throwable th) {
                closeDatabase(sQLiteDatabase);
                throw th;
            }
        }
    }

    public static void delAllTables(Context context) {
        LogUtil.m793i("delAllTables start.");
        synchronized (mLockObject) {
            int i = 0;
            while (i < DBHelper.tables.length) {
                try {
                    deleteAllInTable(context, DBHelper.tables[i]);
                    i++;
                } catch (Exception e) {
                    LogUtil.m790e("delAllTables--Exception");
                }
            }
        }
        LogUtil.m793i("delAllTables end.");
    }
}
