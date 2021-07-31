package neton.client.database;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import java.util.Locale;
import neton.client.Utils.LogUtil;

public class DatabaseManager {
    private static DatabaseManager instance;
    private static SQLiteOpenHelper mDatabaseHelper;
    private SQLiteDatabase mDatabase;

    public static void initializeInstance(SQLiteOpenHelper sQLiteOpenHelper) {
        synchronized (DBUtil.mLockObject) {
            if (instance == null) {
                instance = new DatabaseManager();
                mDatabaseHelper = sQLiteOpenHelper;
            }
        }
    }

    public static DatabaseManager getInstance() {
        DatabaseManager databaseManager;
        synchronized (DBUtil.mLockObject) {
            if (instance == null) {
                throw new IllegalStateException(DatabaseManager.class.getSimpleName() + " is not initialized, call initializeInstance(..) method first.");
            }
            databaseManager = instance;
        }
        return databaseManager;
    }

    public SQLiteDatabase openDatabase() {
        SQLiteDatabase sQLiteDatabase;
        synchronized (DBUtil.mLockObject) {
            try {
                if (this.mDatabase == null || !this.mDatabase.isOpen()) {
                    this.mDatabase = mDatabaseHelper.getWritableDatabase();
                    this.mDatabase.setLocale(Locale.getDefault());
                    if (Build.VERSION.SDK_INT >= 11) {
                        this.mDatabase.enableWriteAheadLogging();
                    }
                }
            } catch (Exception e) {
                LogUtil.m790e("openDatabase--Exception");
                e.printStackTrace();
            }
            sQLiteDatabase = this.mDatabase;
        }
        return sQLiteDatabase;
    }

    public void closeDatabase() {
        synchronized (DBUtil.mLockObject) {
            try {
                if (this.mDatabase != null && this.mDatabase.isOpen()) {
                    this.mDatabase.close();
                    this.mDatabase = null;
                }
            } catch (Exception e) {
                LogUtil.m790e("closeDatabase--Exception");
            }
        }
    }
}
