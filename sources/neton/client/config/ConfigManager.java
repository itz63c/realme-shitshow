package neton.client.config;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import com.coloros.neton.BuildConfig;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

public class ConfigManager {
    private static String CONFIG_PREFS_NAME = "com.coloros.net";
    private static final byte[] LOCK = new byte[0];
    private boolean isPrepared;
    SharedPreferences.Editor mEditor;
    private SharedPreferences mSharedPref;

    class InstanceHolder {
        static ConfigManager ourInstance = new ConfigManager();

        private InstanceHolder() {
        }
    }

    private ConfigManager() {
    }

    public static ConfigManager getInstance() {
        return InstanceHolder.ourInstance;
    }

    public void init(Context context, String str) {
        if (TextUtils.isEmpty(str)) {
            str = CONFIG_PREFS_NAME;
        }
        this.mSharedPref = context.getSharedPreferences(str, 0);
        this.mEditor = this.mSharedPref.edit();
        new JsonConfigParser().parse(Constants.DEFAULT_CONFIG);
    }

    public void init(Context context) {
        init(context, (String) null);
    }

    public void commit() {
        this.mEditor.commit();
    }

    public void clear() {
        this.mEditor.clear().apply();
    }

    public void setIntData(String str, int i) {
        this.mEditor.putInt(str, i);
        this.mEditor.apply();
    }

    public int getIntData(String str, int i) {
        return this.mSharedPref.getInt(str, i);
    }

    public int getIntData(String str) {
        return this.mSharedPref.getInt(str, 0);
    }

    public void setFloatData(String str, float f) {
        this.mEditor.putFloat(str, f);
        this.mEditor.apply();
    }

    public float getFloatData(String str, float f) {
        return this.mSharedPref.getFloat(str, f);
    }

    public float getFloatData(String str) {
        return this.mSharedPref.getFloat(str, 0.0f);
    }

    public void setLongData(String str, Long l) {
        this.mEditor.putLong(str, l.longValue());
        this.mEditor.apply();
    }

    public Long getLongData(String str, Long l) {
        return Long.valueOf(this.mSharedPref.getLong(str, l.longValue()));
    }

    public Long getLongData(String str) {
        return Long.valueOf(this.mSharedPref.getLong(str, 0));
    }

    public void setBooleanData(String str, boolean z) {
        this.mEditor.putBoolean(str, z);
        this.mEditor.apply();
    }

    public boolean getBooleanData(String str, boolean z) {
        return this.mSharedPref.getBoolean(str, z);
    }

    public boolean getBooleanData(String str) {
        return this.mSharedPref.getBoolean(str, false);
    }

    public void setStringData(String str, String str2) {
        this.mEditor.putString(str, str2);
        this.mEditor.apply();
    }

    public String getStringData(String str, String str2) {
        return this.mSharedPref.getString(str, str2);
    }

    public String getStringData(String str) {
        return this.mSharedPref.getString(str, BuildConfig.FLAVOR);
    }

    public void setStringSet(String str, HashSet<String> hashSet) {
        this.mEditor.putStringSet(str, hashSet);
        this.mEditor.apply();
    }

    public HashSet<String> getStringSet(String str, HashSet<String> hashSet) {
        return (HashSet) this.mSharedPref.getStringSet(str, hashSet);
    }

    public List<String> getListData(String str) {
        HashSet<String> stringSet = getStringSet(str, new HashSet());
        ArrayList arrayList = new ArrayList();
        Iterator<String> it = stringSet.iterator();
        while (it.hasNext()) {
            arrayList.add(it.next());
        }
        return arrayList;
    }

    public void setListData(String str, List<String> list) {
        if (list != null) {
            HashSet hashSet = new HashSet();
            for (String add : list) {
                hashSet.add(add);
            }
            setStringSet(str, hashSet);
        }
    }
}
