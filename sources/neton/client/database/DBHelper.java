package neton.client.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.text.TextUtils;
import java.util.Arrays;
import neton.client.Utils.LogUtil;

public class DBHelper extends SQLiteOpenHelper {
    private static final String COL_ID = "_id";
    private static final String DB_NAME = "com_coloros_net";
    private static final int DB_VERSION = 1;
    public static final String TABLE_ADDRESS_INFO = "address_info";
    public static final String TABLE_IP_INFO = "ip_info";
    private static DBHelper mInstance;
    static final String[] tables = {TABLE_ADDRESS_INFO, TABLE_IP_INFO};

    private DBHelper(Context context) {
        super(context, DB_NAME, (SQLiteDatabase.CursorFactory) null, 1);
    }

    public static DBHelper getInstance(Context context) {
        DBHelper dBHelper;
        synchronized (DBUtil.mLockObject) {
            if (mInstance == null) {
                mInstance = new DBHelper(context);
            }
            dBHelper = mInstance;
        }
        return dBHelper;
    }

    public void onCreate(SQLiteDatabase sQLiteDatabase) {
        createTables(sQLiteDatabase);
    }

    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        LogUtil.m787d("DBHelper-onUpgrade-oldVersion:" + i + ",newVersion:" + i2 + ",tables:" + Arrays.toString(tables));
        upgradeTables(sQLiteDatabase, tables);
    }

    private void createTables(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("CREATE TABLE address_info (_id INTEGER PRIMARY KEY AUTOINCREMENT,host TEXT,dnsType INTEGER,timeStamp LONG,ttl LONG);");
        sQLiteDatabase.execSQL("CREATE TABLE ip_info (_id INTEGER PRIMARY KEY AUTOINCREMENT,host TEXT,dnsType INTEGER,ttl LONG,ip TEXT,port INTEGER,weight INTEGER,failCount INTEGER,failTime LONG,sp TEXT,local TEXT);");
    }

    private void deleteTables(SQLiteDatabase sQLiteDatabase, String[] strArr) {
        int i = 0;
        while (strArr != null) {
            try {
                if (i < strArr.length) {
                    sQLiteDatabase.execSQL("DROP TABLE IF EXISTS " + strArr[i]);
                    i++;
                } else {
                    return;
                }
            } catch (Exception e) {
                e.printStackTrace();
                return;
            }
        }
    }

    private void upgradeTables(SQLiteDatabase sQLiteDatabase, String[] strArr) {
        if (strArr != null) {
            try {
                if (strArr.length != 0) {
                    sQLiteDatabase.beginTransaction();
                    for (String str : strArr) {
                        if (tabIsExist(sQLiteDatabase, str)) {
                            sQLiteDatabase.execSQL("ALTER TABLE " + str + " RENAME TO " + (str + "_temp"));
                        }
                    }
                    createTables(sQLiteDatabase);
                    for (String str2 : strArr) {
                        String str3 = str2 + "_temp";
                        if (tabIsExist(sQLiteDatabase, str3)) {
                            String columnNames = getColumnNames(sQLiteDatabase, str3);
                            if (!TextUtils.isEmpty(columnNames)) {
                                try {
                                    sQLiteDatabase.execSQL("INSERT INTO " + str2 + " (" + columnNames + ")  SELECT " + columnNames + " FROM " + str3);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                                sQLiteDatabase.execSQL("DROP TABLE IF EXISTS " + str3);
                            }
                        }
                    }
                    sQLiteDatabase.setTransactionSuccessful();
                    try {
                        sQLiteDatabase.endTransaction();
                        return;
                    } catch (Exception e2) {
                        e2.printStackTrace();
                        return;
                    }
                }
            } catch (Exception e3) {
                deleteTables(sQLiteDatabase, strArr);
                createTables(sQLiteDatabase);
                e3.printStackTrace();
                try {
                    sQLiteDatabase.endTransaction();
                    return;
                } catch (Exception e4) {
                    e4.printStackTrace();
                    return;
                }
            } catch (Throwable th) {
                try {
                    sQLiteDatabase.endTransaction();
                } catch (Exception e5) {
                    e5.printStackTrace();
                }
                throw th;
            }
        }
        try {
            sQLiteDatabase.endTransaction();
        } catch (Exception e6) {
            e6.printStackTrace();
        }
    }

    private boolean tabIsExist(SQLiteDatabase sQLiteDatabase, String str) {
        Cursor cursor = null;
        boolean z = false;
        if (str != null) {
            try {
                Cursor rawQuery = sQLiteDatabase.rawQuery("select count(*) as c from sqlite_master where type ='table' and name ='" + str.trim() + "' ", (String[]) null);
                if (rawQuery.moveToNext() && rawQuery.getInt(0) > 0) {
                    z = true;
                }
                if (rawQuery != null) {
                    try {
                        if (!rawQuery.isClosed()) {
                            rawQuery.close();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            } catch (Exception e2) {
                e2.printStackTrace();
                if (cursor != null) {
                    try {
                        if (!cursor.isClosed()) {
                            cursor.close();
                        }
                    } catch (Exception e3) {
                        e3.printStackTrace();
                    }
                }
            } catch (Throwable th) {
                if (cursor != null) {
                    try {
                        if (!cursor.isClosed()) {
                            cursor.close();
                        }
                    } catch (Exception e4) {
                        e4.printStackTrace();
                    }
                }
                throw th;
            }
            LogUtil.m787d("tabIsExist tabName:" + str + ",result:" + z);
        }
        return z;
    }

    /* JADX WARNING: Removed duplicated region for block: B:28:0x005b A[SYNTHETIC, Splitter:B:28:0x005b] */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x0066  */
    /* JADX WARNING: Removed duplicated region for block: B:47:0x0083 A[SYNTHETIC, Splitter:B:47:0x0083] */
    /* JADX WARNING: Removed duplicated region for block: B:54:0x0092  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private java.lang.String getColumnNames(android.database.sqlite.SQLiteDatabase r8, java.lang.String r9) {
        /*
            r7 = this;
            r0 = 0
            r1 = 0
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x00d1, all -> 0x007f }
            java.lang.String r3 = "PRAGMA table_info("
            r2.<init>(r3)     // Catch:{ Exception -> 0x00d1, all -> 0x007f }
            java.lang.StringBuilder r2 = r2.append(r9)     // Catch:{ Exception -> 0x00d1, all -> 0x007f }
            java.lang.String r3 = ")"
            java.lang.StringBuilder r2 = r2.append(r3)     // Catch:{ Exception -> 0x00d1, all -> 0x007f }
            java.lang.String r2 = r2.toString()     // Catch:{ Exception -> 0x00d1, all -> 0x007f }
            r3 = 0
            android.database.Cursor r3 = r8.rawQuery(r2, r3)     // Catch:{ Exception -> 0x00d1, all -> 0x007f }
            if (r3 == 0) goto L_0x0068
            java.lang.String r2 = "name"
            int r5 = r3.getColumnIndex(r2)     // Catch:{ Exception -> 0x00d5 }
            r2 = -1
            if (r2 != r5) goto L_0x0039
            if (r3 == 0) goto L_0x0032
            boolean r0 = r3.isClosed()     // Catch:{ Exception -> 0x0034 }
            if (r0 != 0) goto L_0x0032
            r3.close()     // Catch:{ Exception -> 0x0034 }
        L_0x0032:
            r0 = r1
        L_0x0033:
            return r0
        L_0x0034:
            r0 = move-exception
            r0.printStackTrace()
            goto L_0x0032
        L_0x0039:
            int r2 = r3.getCount()     // Catch:{ Exception -> 0x00d5 }
            java.lang.String[] r4 = new java.lang.String[r2]     // Catch:{ Exception -> 0x00d5 }
            r3.moveToFirst()     // Catch:{ Exception -> 0x0055 }
            r2 = r0
        L_0x0043:
            boolean r6 = r3.isAfterLast()     // Catch:{ Exception -> 0x0055 }
            if (r6 != 0) goto L_0x0069
            java.lang.String r6 = r3.getString(r5)     // Catch:{ Exception -> 0x0055 }
            r4[r2] = r6     // Catch:{ Exception -> 0x0055 }
            int r2 = r2 + 1
            r3.moveToNext()     // Catch:{ Exception -> 0x0055 }
            goto L_0x0043
        L_0x0055:
            r2 = move-exception
        L_0x0056:
            r2.printStackTrace()     // Catch:{ all -> 0x00cf }
            if (r3 == 0) goto L_0x0064
            boolean r2 = r3.isClosed()     // Catch:{ Exception -> 0x007a }
            if (r2 != 0) goto L_0x0064
            r3.close()     // Catch:{ Exception -> 0x007a }
        L_0x0064:
            if (r4 != 0) goto L_0x0092
            r0 = r1
            goto L_0x0033
        L_0x0068:
            r4 = r1
        L_0x0069:
            if (r3 == 0) goto L_0x0064
            boolean r2 = r3.isClosed()     // Catch:{ Exception -> 0x0075 }
            if (r2 != 0) goto L_0x0064
            r3.close()     // Catch:{ Exception -> 0x0075 }
            goto L_0x0064
        L_0x0075:
            r2 = move-exception
            r2.printStackTrace()
            goto L_0x0064
        L_0x007a:
            r2 = move-exception
            r2.printStackTrace()
            goto L_0x0064
        L_0x007f:
            r0 = move-exception
            r3 = r1
        L_0x0081:
            if (r3 == 0) goto L_0x008c
            boolean r1 = r3.isClosed()     // Catch:{ Exception -> 0x008d }
            if (r1 != 0) goto L_0x008c
            r3.close()     // Catch:{ Exception -> 0x008d }
        L_0x008c:
            throw r0
        L_0x008d:
            r1 = move-exception
            r1.printStackTrace()
            goto L_0x008c
        L_0x0092:
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
        L_0x0097:
            int r2 = r4.length
            if (r0 >= r2) goto L_0x00b3
            int r2 = r4.length
            int r2 = r2 + -1
            if (r0 != r2) goto L_0x00a7
            r2 = r4[r0]
            r1.append(r2)
        L_0x00a4:
            int r0 = r0 + 1
            goto L_0x0097
        L_0x00a7:
            r2 = r4[r0]
            java.lang.StringBuilder r2 = r1.append(r2)
            java.lang.String r3 = ","
            r2.append(r3)
            goto L_0x00a4
        L_0x00b3:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r2 = "DBHelper-getColumnNames:"
            r0.<init>(r2)
            java.lang.String r2 = r1.toString()
            java.lang.StringBuilder r0 = r0.append(r2)
            java.lang.String r0 = r0.toString()
            neton.client.Utils.LogUtil.m793i(r0)
            java.lang.String r0 = r1.toString()
            goto L_0x0033
        L_0x00cf:
            r0 = move-exception
            goto L_0x0081
        L_0x00d1:
            r2 = move-exception
            r3 = r1
            r4 = r1
            goto L_0x0056
        L_0x00d5:
            r2 = move-exception
            r4 = r1
            goto L_0x0056
        */
        throw new UnsupportedOperationException("Method not decompiled: neton.client.database.DBHelper.getColumnNames(android.database.sqlite.SQLiteDatabase, java.lang.String):java.lang.String");
    }
}
