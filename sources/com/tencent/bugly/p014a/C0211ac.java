package com.tencent.bugly.p014a;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import java.util.List;
import java.util.Map;

/* renamed from: com.tencent.bugly.a.ac */
/* compiled from: BUGLY */
public final class C0211ac {

    /* renamed from: a */
    private static C0211ac f405a = null;

    /* renamed from: b */
    private static C0212ad f406b = null;

    /* renamed from: a */
    public static synchronized C0211ac m521a() {
        C0211ac acVar;
        synchronized (C0211ac.class) {
            acVar = f405a;
        }
        return acVar;
    }

    /* renamed from: a */
    public final long mo357a(String str, ContentValues contentValues) {
        return m529b(str, contentValues);
    }

    /* renamed from: a */
    public final Cursor mo358a(String str, String[] strArr, String str2) {
        return m530b(str, strArr, str2);
    }

    /* renamed from: a */
    public final int mo356a(String str, String str2) {
        return m528b(str, str2);
    }

    /* renamed from: b */
    private synchronized long m529b(String str, ContentValues contentValues) {
        long j;
        try {
            SQLiteDatabase writableDatabase = f406b.getWritableDatabase();
            if (writableDatabase == null || contentValues == null) {
                j = 0;
            } else {
                j = writableDatabase.replace(str, "_id", contentValues);
                if (j >= 0) {
                    C0221am.m597c("[db] insert %s success", str);
                } else {
                    C0221am.m598d("[db] replace %s error", str);
                }
            }
        } catch (Throwable th) {
            if (!C0221am.m594a(th)) {
                th.printStackTrace();
            }
            j = 0;
        }
        return j;
    }

    /* renamed from: b */
    private synchronized Cursor m530b(String str, String[] strArr, String str2) {
        Cursor cursor;
        try {
            SQLiteDatabase writableDatabase = f406b.getWritableDatabase();
            if (writableDatabase != null) {
                cursor = writableDatabase.query(false, str, strArr, str2, (String[]) null, (String) null, (String) null, (String) null, (String) null);
            } else {
                cursor = null;
            }
        } catch (Throwable th) {
            if (!C0221am.m594a(th)) {
                th.printStackTrace();
            }
            cursor = null;
        }
        return cursor;
    }

    /* renamed from: b */
    private synchronized int m528b(String str, String str2) {
        int i;
        i = 0;
        try {
            SQLiteDatabase writableDatabase = f406b.getWritableDatabase();
            if (writableDatabase != null) {
                i = writableDatabase.delete(str, str2, (String[]) null);
            }
        } catch (Throwable th) {
            if (!C0221am.m594a(th)) {
                th.printStackTrace();
            }
        }
        return i;
    }

    /* renamed from: a */
    public static boolean m525a(int i, String str, byte[] bArr) {
        return m533b(i, str, bArr);
    }

    /* renamed from: b */
    public static Map<String, byte[]> m532b() {
        return m539d();
    }

    /* renamed from: a */
    public static boolean m527a(String str) {
        return m535b(str);
    }

    /* renamed from: b */
    private static boolean m533b(int i, String str, byte[] bArr) {
        try {
            C0213ae aeVar = new C0213ae();
            aeVar.f410a = (long) i;
            aeVar.f415f = str;
            aeVar.f414e = System.currentTimeMillis();
            aeVar.f416g = bArr;
            return m534b(aeVar);
        } catch (Throwable th) {
            if (C0221am.m594a(th)) {
                return false;
            }
            th.printStackTrace();
            return false;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:15:0x002c  */
    /* JADX WARNING: Removed duplicated region for block: B:26:? A[RETURN, SYNTHETIC] */
    /* renamed from: d */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static java.util.Map<java.lang.String, byte[]> m539d() {
        /*
            r0 = 0
            java.util.List r2 = m540e()     // Catch:{ Throwable -> 0x0034 }
            java.util.HashMap r1 = new java.util.HashMap     // Catch:{ Throwable -> 0x0034 }
            r1.<init>()     // Catch:{ Throwable -> 0x0034 }
            java.util.Iterator r2 = r2.iterator()     // Catch:{ Throwable -> 0x0024 }
        L_0x000e:
            boolean r0 = r2.hasNext()     // Catch:{ Throwable -> 0x0024 }
            if (r0 == 0) goto L_0x0030
            java.lang.Object r0 = r2.next()     // Catch:{ Throwable -> 0x0024 }
            com.tencent.bugly.a.ae r0 = (com.tencent.bugly.p014a.C0213ae) r0     // Catch:{ Throwable -> 0x0024 }
            byte[] r3 = r0.f416g     // Catch:{ Throwable -> 0x0024 }
            if (r3 == 0) goto L_0x000e
            java.lang.String r0 = r0.f415f     // Catch:{ Throwable -> 0x0024 }
            r1.put(r0, r3)     // Catch:{ Throwable -> 0x0024 }
            goto L_0x000e
        L_0x0024:
            r2 = move-exception
            r0 = r1
        L_0x0026:
            boolean r1 = com.tencent.bugly.p014a.C0221am.m594a(r2)     // Catch:{ all -> 0x0032 }
            if (r1 != 0) goto L_0x002f
            r2.printStackTrace()     // Catch:{ all -> 0x0032 }
        L_0x002f:
            return r0
        L_0x0030:
            r0 = r1
            goto L_0x002f
        L_0x0032:
            r0 = move-exception
            throw r0
        L_0x0034:
            r1 = move-exception
            r2 = r1
            goto L_0x0026
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.bugly.p014a.C0211ac.m539d():java.util.Map");
    }

    /* renamed from: a */
    public static boolean m526a(C0213ae aeVar) {
        ContentValues c;
        try {
            SQLiteDatabase writableDatabase = f406b.getWritableDatabase();
            if (writableDatabase == null || (c = m536c(aeVar)) == null) {
                return false;
            }
            long replace = writableDatabase.replace("t_lr", "_id", c);
            if (replace < 0) {
                return false;
            }
            C0221am.m597c("insert %s success!", "t_lr");
            aeVar.f410a = replace;
            return true;
        } catch (Throwable th) {
            if (!C0221am.m594a(th)) {
                th.printStackTrace();
            }
            return false;
        }
    }

    /* renamed from: b */
    private static boolean m534b(C0213ae aeVar) {
        ContentValues d;
        try {
            SQLiteDatabase writableDatabase = f406b.getWritableDatabase();
            if (writableDatabase == null || (d = m538d(aeVar)) == null) {
                return false;
            }
            long replace = writableDatabase.replace("t_pf", "_id", d);
            if (replace < 0) {
                return false;
            }
            C0221am.m597c("insert %s success!", "t_pf");
            aeVar.f410a = replace;
            return true;
        } catch (Throwable th) {
            if (!C0221am.m594a(th)) {
                th.printStackTrace();
            }
            return false;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:18:0x003f, code lost:
        r0 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0040, code lost:
        r1 = r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:36:0x0075, code lost:
        r0 = th;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x0075 A[ExcHandler: all (th java.lang.Throwable), Splitter:B:11:0x0025] */
    /* renamed from: c */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.util.List<com.tencent.bugly.p014a.C0213ae> m537c() {
        /*
            r8 = 0
            com.tencent.bugly.a.ad r0 = f406b
            android.database.sqlite.SQLiteDatabase r0 = r0.getWritableDatabase()
            if (r0 == 0) goto L_0x0055
            java.lang.String r3 = "_tp = 3"
            java.lang.String r1 = "t_lr"
            r2 = 0
            r4 = 0
            r5 = 0
            r6 = 0
            r7 = 0
            android.database.Cursor r2 = r0.query(r1, r2, r3, r4, r5, r6, r7)     // Catch:{ Throwable -> 0x00c0, all -> 0x00ba }
            if (r2 != 0) goto L_0x0025
            if (r2 == 0) goto L_0x0023
            boolean r0 = r2.isClosed()
            if (r0 != 0) goto L_0x0023
            r2.close()
        L_0x0023:
            r0 = r8
        L_0x0024:
            return r0
        L_0x0025:
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x003f, all -> 0x0075 }
            r3.<init>()     // Catch:{ Throwable -> 0x003f, all -> 0x0075 }
            java.util.ArrayList r1 = new java.util.ArrayList     // Catch:{ Throwable -> 0x003f, all -> 0x0075 }
            r1.<init>()     // Catch:{ Throwable -> 0x003f, all -> 0x0075 }
        L_0x002f:
            boolean r4 = r2.moveToNext()     // Catch:{ Throwable -> 0x003f, all -> 0x0075 }
            if (r4 == 0) goto L_0x0082
            com.tencent.bugly.a.ae r4 = m522a((android.database.Cursor) r2)     // Catch:{ Throwable -> 0x003f, all -> 0x0075 }
            if (r4 == 0) goto L_0x0057
            r1.add(r4)     // Catch:{ Throwable -> 0x003f, all -> 0x0075 }
            goto L_0x002f
        L_0x003f:
            r0 = move-exception
            r1 = r2
        L_0x0041:
            boolean r2 = com.tencent.bugly.p014a.C0221am.m594a(r0)     // Catch:{ all -> 0x00bd }
            if (r2 != 0) goto L_0x004a
            r0.printStackTrace()     // Catch:{ all -> 0x00bd }
        L_0x004a:
            if (r1 == 0) goto L_0x0055
            boolean r0 = r1.isClosed()
            if (r0 != 0) goto L_0x0055
            r1.close()
        L_0x0055:
            r0 = r8
            goto L_0x0024
        L_0x0057:
            java.lang.String r4 = "_id"
            int r4 = r2.getColumnIndex(r4)     // Catch:{ Throwable -> 0x006b, all -> 0x0075 }
            long r4 = r2.getLong(r4)     // Catch:{ Throwable -> 0x006b, all -> 0x0075 }
            java.lang.String r6 = " or _id = "
            java.lang.StringBuilder r6 = r3.append(r6)     // Catch:{ Throwable -> 0x006b, all -> 0x0075 }
            r6.append(r4)     // Catch:{ Throwable -> 0x006b, all -> 0x0075 }
            goto L_0x002f
        L_0x006b:
            r4 = move-exception
            java.lang.String r4 = "unknown id!"
            r5 = 0
            java.lang.Object[] r5 = new java.lang.Object[r5]     // Catch:{ Throwable -> 0x003f, all -> 0x0075 }
            com.tencent.bugly.p014a.C0221am.m598d(r4, r5)     // Catch:{ Throwable -> 0x003f, all -> 0x0075 }
            goto L_0x002f
        L_0x0075:
            r0 = move-exception
        L_0x0076:
            if (r2 == 0) goto L_0x0081
            boolean r1 = r2.isClosed()
            if (r1 != 0) goto L_0x0081
            r2.close()
        L_0x0081:
            throw r0
        L_0x0082:
            java.lang.String r3 = r3.toString()     // Catch:{ Throwable -> 0x003f, all -> 0x0075 }
            int r4 = r3.length()     // Catch:{ Throwable -> 0x003f, all -> 0x0075 }
            if (r4 <= 0) goto L_0x00ac
            r4 = 4
            java.lang.String r3 = r3.substring(r4)     // Catch:{ Throwable -> 0x003f, all -> 0x0075 }
            java.lang.String r4 = "t_lr"
            r5 = 0
            int r0 = r0.delete(r4, r3, r5)     // Catch:{ Throwable -> 0x003f, all -> 0x0075 }
            java.lang.String r3 = "deleted %s illegle data %d"
            r4 = 2
            java.lang.Object[] r4 = new java.lang.Object[r4]     // Catch:{ Throwable -> 0x003f, all -> 0x0075 }
            r5 = 0
            java.lang.String r6 = "t_lr"
            r4[r5] = r6     // Catch:{ Throwable -> 0x003f, all -> 0x0075 }
            r5 = 1
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)     // Catch:{ Throwable -> 0x003f, all -> 0x0075 }
            r4[r5] = r0     // Catch:{ Throwable -> 0x003f, all -> 0x0075 }
            com.tencent.bugly.p014a.C0221am.m598d(r3, r4)     // Catch:{ Throwable -> 0x003f, all -> 0x0075 }
        L_0x00ac:
            if (r2 == 0) goto L_0x00b7
            boolean r0 = r2.isClosed()
            if (r0 != 0) goto L_0x00b7
            r2.close()
        L_0x00b7:
            r0 = r1
            goto L_0x0024
        L_0x00ba:
            r0 = move-exception
            r2 = r8
            goto L_0x0076
        L_0x00bd:
            r0 = move-exception
            r2 = r1
            goto L_0x0076
        L_0x00c0:
            r0 = move-exception
            r1 = r8
            goto L_0x0041
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.bugly.p014a.C0211ac.m537c():java.util.List");
    }

    /* renamed from: a */
    public static void m524a(List<C0213ae> list) {
        SQLiteDatabase writableDatabase;
        if (list != null && list.size() != 0 && (writableDatabase = f406b.getWritableDatabase()) != null) {
            StringBuilder sb = new StringBuilder();
            for (C0213ae aeVar : list) {
                sb.append(" or _id = ").append(aeVar.f410a);
            }
            String sb2 = sb.toString();
            if (sb2.length() > 0) {
                sb2 = sb2.substring(4);
            }
            sb.setLength(0);
            try {
                C0221am.m597c("deleted %s data %d", "t_lr", Integer.valueOf(writableDatabase.delete("t_lr", sb2, (String[]) null)));
            } catch (Throwable th) {
                if (!C0221am.m594a(th)) {
                    th.printStackTrace();
                }
            }
        }
    }

    /* renamed from: a */
    public static void m523a(int i) {
        String str = null;
        SQLiteDatabase writableDatabase = f406b.getWritableDatabase();
        if (writableDatabase != null) {
            if (i >= 0) {
                try {
                    str = "_tp = " + i;
                } catch (Throwable th) {
                    if (!C0221am.m594a(th)) {
                        th.printStackTrace();
                        return;
                    }
                    return;
                }
            }
            C0221am.m597c("deleted %s data %d", "t_lr", Integer.valueOf(writableDatabase.delete("t_lr", str, (String[]) null)));
        }
    }

    /* renamed from: c */
    private static ContentValues m536c(C0213ae aeVar) {
        if (aeVar == null) {
            return null;
        }
        try {
            ContentValues contentValues = new ContentValues();
            if (aeVar.f410a > 0) {
                contentValues.put("_id", Long.valueOf(aeVar.f410a));
            }
            contentValues.put("_tp", Integer.valueOf(aeVar.f411b));
            contentValues.put("_pc", aeVar.f412c);
            contentValues.put("_th", aeVar.f413d);
            contentValues.put("_tm", Long.valueOf(aeVar.f414e));
            if (aeVar.f416g != null) {
                contentValues.put("_dt", aeVar.f416g);
            }
            return contentValues;
        } catch (Throwable th) {
            if (C0221am.m594a(th)) {
                return null;
            }
            th.printStackTrace();
            return null;
        }
    }

    /* renamed from: a */
    private static C0213ae m522a(Cursor cursor) {
        if (cursor == null) {
            return null;
        }
        try {
            C0213ae aeVar = new C0213ae();
            aeVar.f410a = cursor.getLong(cursor.getColumnIndex("_id"));
            aeVar.f411b = cursor.getInt(cursor.getColumnIndex("_tp"));
            aeVar.f412c = cursor.getString(cursor.getColumnIndex("_pc"));
            aeVar.f413d = cursor.getString(cursor.getColumnIndex("_th"));
            aeVar.f414e = cursor.getLong(cursor.getColumnIndex("_tm"));
            aeVar.f416g = cursor.getBlob(cursor.getColumnIndex("_dt"));
            return aeVar;
        } catch (Throwable th) {
            if (C0221am.m594a(th)) {
                return null;
            }
            th.printStackTrace();
            return null;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:18:0x003f, code lost:
        r0 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0040, code lost:
        r1 = r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:36:0x0075, code lost:
        r0 = th;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x0075 A[ExcHandler: all (th java.lang.Throwable), Splitter:B:11:0x0025] */
    /* renamed from: e */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static java.util.List<com.tencent.bugly.p014a.C0213ae> m540e() {
        /*
            r8 = 0
            com.tencent.bugly.a.ad r0 = f406b     // Catch:{ Throwable -> 0x00c1, all -> 0x00bb }
            android.database.sqlite.SQLiteDatabase r0 = r0.getWritableDatabase()     // Catch:{ Throwable -> 0x00c1, all -> 0x00bb }
            if (r0 == 0) goto L_0x0055
            java.lang.String r3 = "_id = 555"
            java.lang.String r1 = "t_pf"
            r2 = 0
            r4 = 0
            r5 = 0
            r6 = 0
            r7 = 0
            android.database.Cursor r2 = r0.query(r1, r2, r3, r4, r5, r6, r7)     // Catch:{ Throwable -> 0x00c1, all -> 0x00bb }
            if (r2 != 0) goto L_0x0025
            if (r2 == 0) goto L_0x0023
            boolean r0 = r2.isClosed()
            if (r0 != 0) goto L_0x0023
            r2.close()
        L_0x0023:
            r0 = r8
        L_0x0024:
            return r0
        L_0x0025:
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x003f, all -> 0x0075 }
            r4.<init>()     // Catch:{ Throwable -> 0x003f, all -> 0x0075 }
            java.util.ArrayList r1 = new java.util.ArrayList     // Catch:{ Throwable -> 0x003f, all -> 0x0075 }
            r1.<init>()     // Catch:{ Throwable -> 0x003f, all -> 0x0075 }
        L_0x002f:
            boolean r5 = r2.moveToNext()     // Catch:{ Throwable -> 0x003f, all -> 0x0075 }
            if (r5 == 0) goto L_0x0082
            com.tencent.bugly.a.ae r5 = m531b((android.database.Cursor) r2)     // Catch:{ Throwable -> 0x003f, all -> 0x0075 }
            if (r5 == 0) goto L_0x0057
            r1.add(r5)     // Catch:{ Throwable -> 0x003f, all -> 0x0075 }
            goto L_0x002f
        L_0x003f:
            r0 = move-exception
            r1 = r2
        L_0x0041:
            boolean r2 = com.tencent.bugly.p014a.C0221am.m594a(r0)     // Catch:{ all -> 0x00be }
            if (r2 != 0) goto L_0x004a
            r0.printStackTrace()     // Catch:{ all -> 0x00be }
        L_0x004a:
            if (r1 == 0) goto L_0x0055
            boolean r0 = r1.isClosed()
            if (r0 != 0) goto L_0x0055
            r1.close()
        L_0x0055:
            r0 = r8
            goto L_0x0024
        L_0x0057:
            java.lang.String r5 = "_tp"
            int r5 = r2.getColumnIndex(r5)     // Catch:{ Throwable -> 0x006b, all -> 0x0075 }
            java.lang.String r5 = r2.getString(r5)     // Catch:{ Throwable -> 0x006b, all -> 0x0075 }
            java.lang.String r6 = " or _tp = "
            java.lang.StringBuilder r6 = r4.append(r6)     // Catch:{ Throwable -> 0x006b, all -> 0x0075 }
            r6.append(r5)     // Catch:{ Throwable -> 0x006b, all -> 0x0075 }
            goto L_0x002f
        L_0x006b:
            r5 = move-exception
            java.lang.String r5 = "unknown id!"
            r6 = 0
            java.lang.Object[] r6 = new java.lang.Object[r6]     // Catch:{ Throwable -> 0x003f, all -> 0x0075 }
            com.tencent.bugly.p014a.C0221am.m598d(r5, r6)     // Catch:{ Throwable -> 0x003f, all -> 0x0075 }
            goto L_0x002f
        L_0x0075:
            r0 = move-exception
        L_0x0076:
            if (r2 == 0) goto L_0x0081
            boolean r1 = r2.isClosed()
            if (r1 != 0) goto L_0x0081
            r2.close()
        L_0x0081:
            throw r0
        L_0x0082:
            int r5 = r4.length()     // Catch:{ Throwable -> 0x003f, all -> 0x0075 }
            if (r5 <= 0) goto L_0x00ad
            java.lang.String r5 = " and _id = 555"
            r4.append(r5)     // Catch:{ Throwable -> 0x003f, all -> 0x0075 }
            r4 = 4
            java.lang.String r3 = r3.substring(r4)     // Catch:{ Throwable -> 0x003f, all -> 0x0075 }
            java.lang.String r4 = "t_pf"
            r5 = 0
            int r0 = r0.delete(r4, r3, r5)     // Catch:{ Throwable -> 0x003f, all -> 0x0075 }
            java.lang.String r3 = "deleted %s illegle data %d"
            r4 = 2
            java.lang.Object[] r4 = new java.lang.Object[r4]     // Catch:{ Throwable -> 0x003f, all -> 0x0075 }
            r5 = 0
            java.lang.String r6 = "t_pf"
            r4[r5] = r6     // Catch:{ Throwable -> 0x003f, all -> 0x0075 }
            r5 = 1
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)     // Catch:{ Throwable -> 0x003f, all -> 0x0075 }
            r4[r5] = r0     // Catch:{ Throwable -> 0x003f, all -> 0x0075 }
            com.tencent.bugly.p014a.C0221am.m598d(r3, r4)     // Catch:{ Throwable -> 0x003f, all -> 0x0075 }
        L_0x00ad:
            if (r2 == 0) goto L_0x00b8
            boolean r0 = r2.isClosed()
            if (r0 != 0) goto L_0x00b8
            r2.close()
        L_0x00b8:
            r0 = r1
            goto L_0x0024
        L_0x00bb:
            r0 = move-exception
            r2 = r8
            goto L_0x0076
        L_0x00be:
            r0 = move-exception
            r2 = r1
            goto L_0x0076
        L_0x00c1:
            r0 = move-exception
            r1 = r8
            goto L_0x0041
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.bugly.p014a.C0211ac.m540e():java.util.List");
    }

    /* renamed from: b */
    private static boolean m535b(String str) {
        String str2;
        try {
            SQLiteDatabase writableDatabase = f406b.getWritableDatabase();
            if (writableDatabase == null) {
                return false;
            }
            if (str == null || str.trim().length() <= 0) {
                str2 = "_id = 555";
            } else {
                str2 = "_id = 555 and _tp = \"" + str + "\"";
            }
            int delete = writableDatabase.delete("t_pf", str2, (String[]) null);
            C0221am.m597c("deleted %s data %d", "t_pf", Integer.valueOf(delete));
            if (delete > 0) {
                return true;
            }
            return false;
        } catch (Throwable th) {
            if (!C0221am.m594a(th)) {
                th.printStackTrace();
            }
            return false;
        }
    }

    /* renamed from: d */
    private static ContentValues m538d(C0213ae aeVar) {
        boolean z;
        if (aeVar != null) {
            String str = aeVar.f415f;
            if (str == null || str.trim().length() <= 0) {
                z = true;
            } else {
                z = false;
            }
            if (!z) {
                try {
                    ContentValues contentValues = new ContentValues();
                    if (aeVar.f410a > 0) {
                        contentValues.put("_id", Long.valueOf(aeVar.f410a));
                    }
                    contentValues.put("_tp", aeVar.f415f);
                    contentValues.put("_tm", Long.valueOf(aeVar.f414e));
                    if (aeVar.f416g == null) {
                        return contentValues;
                    }
                    contentValues.put("_dt", aeVar.f416g);
                    return contentValues;
                } catch (Throwable th) {
                    if (!C0221am.m594a(th)) {
                        th.printStackTrace();
                    }
                    return null;
                }
            }
        }
        return null;
    }

    /* renamed from: b */
    private static C0213ae m531b(Cursor cursor) {
        if (cursor == null) {
            return null;
        }
        try {
            C0213ae aeVar = new C0213ae();
            aeVar.f410a = cursor.getLong(cursor.getColumnIndex("_id"));
            aeVar.f414e = cursor.getLong(cursor.getColumnIndex("_tm"));
            aeVar.f415f = cursor.getString(cursor.getColumnIndex("_tp"));
            aeVar.f416g = cursor.getBlob(cursor.getColumnIndex("_dt"));
            return aeVar;
        } catch (Throwable th) {
            if (C0221am.m594a(th)) {
                return null;
            }
            th.printStackTrace();
            return null;
        }
    }
}
