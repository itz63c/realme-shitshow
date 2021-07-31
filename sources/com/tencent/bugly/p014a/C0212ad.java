package com.tencent.bugly.p014a;

import android.annotation.TargetApi;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.tencent.bugly.C0207a;
import java.io.File;
import java.util.Iterator;
import java.util.List;

/* renamed from: com.tencent.bugly.a.ad */
/* compiled from: BUGLY */
public final class C0212ad extends SQLiteOpenHelper {

    /* renamed from: a */
    private static int f407a = 8;

    /* renamed from: b */
    private Context f408b;

    /* renamed from: c */
    private List<C0207a> f409c;

    public final synchronized void onCreate(SQLiteDatabase sQLiteDatabase) {
        try {
            StringBuilder sb = new StringBuilder();
            sb.setLength(0);
            sb.append(" CREATE TABLE t_ui ( _id INTEGER PRIMARY KEY , _tm int , _ut int , _tp int , _dt blob , _pc text ) ");
            String sb2 = sb.toString();
            C0221am.m597c("create %s", sb2);
            sQLiteDatabase.execSQL(sb2);
            sb.setLength(0);
            sb.append(" CREATE TABLE t_lr ( _id INTEGER PRIMARY KEY , _tp int , _tm int , _pc text , _th text , _dt blob ) ");
            String sb3 = sb.toString();
            C0221am.m597c("create %s", sb3);
            sQLiteDatabase.execSQL(sb3);
            sb.setLength(0);
            sb.append(" CREATE TABLE t_pf ( _id integer , _tp text , _tm int , _dt blob,primary key(_id,_tp )) ");
            String sb4 = sb.toString();
            C0221am.m597c("create %s", sb4);
            sQLiteDatabase.execSQL(sb4);
        } catch (Throwable th) {
            if (!C0221am.m596b(th)) {
                th.printStackTrace();
            }
        }
        if (this.f409c != null) {
            Iterator<C0207a> it = this.f409c.iterator();
            while (it.hasNext()) {
                it.next();
            }
        }
    }

    /* renamed from: a */
    private synchronized boolean m544a(SQLiteDatabase sQLiteDatabase) {
        boolean z = true;
        synchronized (this) {
            try {
                String[] strArr = {"t_cr", "t_lr", "t_ui", "t_pf"};
                for (int i = 0; i < 4; i++) {
                    sQLiteDatabase.execSQL("DROP TABLE IF EXISTS " + strArr[i]);
                }
            } catch (Throwable th) {
                if (!C0221am.m596b(th)) {
                    th.printStackTrace();
                }
                z = false;
            }
        }
        return z;
    }

    public final synchronized void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        C0221am.m598d("upgrade %d to %d , drop tables!", Integer.valueOf(i), Integer.valueOf(i2));
        if (this.f409c != null) {
            Iterator<C0207a> it = this.f409c.iterator();
            while (it.hasNext()) {
                it.next();
            }
        }
        if (m544a(sQLiteDatabase)) {
            onCreate(sQLiteDatabase);
        } else {
            C0221am.m598d("drop fail delete db", new Object[0]);
            File databasePath = this.f408b.getDatabasePath("bugly_db");
            if (databasePath != null && databasePath.canWrite()) {
                databasePath.delete();
            }
        }
    }

    @TargetApi(11)
    public final synchronized void onDowngrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        if (C0208a.m498d() >= 11) {
            C0221am.m598d("drowngrade %d to %d drop tables!}", Integer.valueOf(i), Integer.valueOf(i2));
            if (this.f409c != null) {
                Iterator<C0207a> it = this.f409c.iterator();
                while (it.hasNext()) {
                    it.next();
                }
            }
            if (m544a(sQLiteDatabase)) {
                onCreate(sQLiteDatabase);
            } else {
                C0221am.m598d("drop fail delete db", new Object[0]);
                File databasePath = this.f408b.getDatabasePath("bugly_db");
                if (databasePath != null && databasePath.canWrite()) {
                    databasePath.delete();
                }
            }
        }
    }

    public final synchronized SQLiteDatabase getReadableDatabase() {
        SQLiteDatabase sQLiteDatabase;
        int i = 0;
        synchronized (this) {
            sQLiteDatabase = null;
            while (sQLiteDatabase == null && i < 5) {
                i++;
                try {
                    sQLiteDatabase = super.getReadableDatabase();
                } catch (Throwable th) {
                    C0221am.m598d("try db count %d", Integer.valueOf(i));
                    if (i == 5) {
                        C0221am.m599e("get db fail!", new Object[0]);
                    }
                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return sQLiteDatabase;
    }

    public final synchronized SQLiteDatabase getWritableDatabase() {
        SQLiteDatabase sQLiteDatabase;
        int i = 0;
        synchronized (this) {
            sQLiteDatabase = null;
            while (sQLiteDatabase == null && i < 5) {
                i++;
                try {
                    sQLiteDatabase = super.getWritableDatabase();
                } catch (Throwable th) {
                    C0221am.m598d("try db %d", Integer.valueOf(i));
                    if (i == 5) {
                        C0221am.m599e("get db fail!", new Object[0]);
                    }
                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
            if (sQLiteDatabase == null) {
                C0221am.m598d("db error delay error record 1min", new Object[0]);
            }
        }
        return sQLiteDatabase;
    }
}
