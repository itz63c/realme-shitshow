package neton.client.cookie;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.util.Log;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import neton.Cookie;
import neton.HttpUrl;
import neton.client.Utils.LogUtil;

public class PersistentCookieStore {
    private static final String COOKIE_PREFS = "Cookies_Prefs";
    private static final String LOG_TAG = "PersistentCookieStore";
    private final SharedPreferences cookiePrefs;
    private final Map<String, ConcurrentHashMap<String, Cookie>> cookies = new HashMap();

    public PersistentCookieStore(Context context) {
        Cookie decodeCookie;
        this.cookiePrefs = context.getSharedPreferences(COOKIE_PREFS, 0);
        for (Map.Entry next : this.cookiePrefs.getAll().entrySet()) {
            for (String str : TextUtils.split((String) next.getValue(), ",")) {
                String string = this.cookiePrefs.getString(str, (String) null);
                if (!(string == null || (decodeCookie = decodeCookie(string)) == null)) {
                    if (!this.cookies.containsKey(next.getKey())) {
                        this.cookies.put(next.getKey(), new ConcurrentHashMap());
                    }
                    this.cookies.get(next.getKey()).put(str, decodeCookie);
                }
            }
        }
    }

    /* access modifiers changed from: protected */
    public String getCookieToken(Cookie cookie) {
        return cookie.name() + "@" + cookie.domain();
    }

    public void add(HttpUrl httpUrl, Cookie cookie) {
        String cookieToken = getCookieToken(cookie);
        LogUtil.m787d("PersistentCookieStore-add--url:" + httpUrl + ",cookie.persistent():" + cookie.persistent());
        if (cookie.persistent()) {
            if (!this.cookies.containsKey(httpUrl.host())) {
                this.cookies.put(httpUrl.host(), new ConcurrentHashMap());
            }
            this.cookies.get(httpUrl.host()).put(cookieToken, cookie);
            SharedPreferences.Editor edit = this.cookiePrefs.edit();
            edit.putString(httpUrl.host(), TextUtils.join(",", this.cookies.get(httpUrl.host()).keySet()));
            edit.putString(cookieToken, encodeCookie(new SerializableOkHttpCookies(cookie)));
            edit.apply();
        } else if (this.cookies.containsKey(httpUrl.host())) {
            this.cookies.get(httpUrl.host()).remove(cookieToken);
        }
    }

    public List<Cookie> get(HttpUrl httpUrl) {
        ArrayList arrayList = new ArrayList();
        if (this.cookies.containsKey(httpUrl.host())) {
            arrayList.addAll(this.cookies.get(httpUrl.host()).values());
        }
        return arrayList;
    }

    public boolean removeAll() {
        SharedPreferences.Editor edit = this.cookiePrefs.edit();
        edit.clear();
        edit.apply();
        this.cookies.clear();
        return true;
    }

    public boolean remove(HttpUrl httpUrl, Cookie cookie) {
        String cookieToken = getCookieToken(cookie);
        if (!this.cookies.containsKey(httpUrl.host()) || !this.cookies.get(httpUrl.host()).containsKey(cookieToken)) {
            return false;
        }
        this.cookies.get(httpUrl.host()).remove(cookieToken);
        SharedPreferences.Editor edit = this.cookiePrefs.edit();
        if (this.cookiePrefs.contains(cookieToken)) {
            edit.remove(cookieToken);
        }
        edit.putString(httpUrl.host(), TextUtils.join(",", this.cookies.get(httpUrl.host()).keySet()));
        edit.apply();
        return true;
    }

    public List<Cookie> getCookies() {
        ArrayList arrayList = new ArrayList();
        for (String str : this.cookies.keySet()) {
            arrayList.addAll(this.cookies.get(str).values());
        }
        return arrayList;
    }

    /* access modifiers changed from: protected */
    public String encodeCookie(SerializableOkHttpCookies serializableOkHttpCookies) {
        if (serializableOkHttpCookies == null) {
            return null;
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            new ObjectOutputStream(byteArrayOutputStream).writeObject(serializableOkHttpCookies);
            return byteArrayToHexString(byteArrayOutputStream.toByteArray());
        } catch (IOException e) {
            Log.d(LOG_TAG, "IOException in encodeCookie", e);
            return null;
        }
    }

    /* access modifiers changed from: protected */
    public Cookie decodeCookie(String str) {
        try {
            return ((SerializableOkHttpCookies) new ObjectInputStream(new ByteArrayInputStream(hexStringToByteArray(str))).readObject()).getCookies();
        } catch (IOException e) {
            Log.d(LOG_TAG, "IOException in decodeCookie", e);
            return null;
        } catch (ClassNotFoundException e2) {
            Log.d(LOG_TAG, "ClassNotFoundException in decodeCookie", e2);
            return null;
        }
    }

    /* access modifiers changed from: protected */
    public String byteArrayToHexString(byte[] bArr) {
        StringBuilder sb = new StringBuilder(bArr.length * 2);
        for (byte b : bArr) {
            byte b2 = b & 255;
            if (b2 < 16) {
                sb.append('0');
            }
            sb.append(Integer.toHexString(b2));
        }
        return sb.toString().toUpperCase(Locale.US);
    }

    /* access modifiers changed from: protected */
    public byte[] hexStringToByteArray(String str) {
        int length = str.length();
        byte[] bArr = new byte[(length / 2)];
        for (int i = 0; i < length; i += 2) {
            bArr[i / 2] = (byte) ((Character.digit(str.charAt(i), 16) << 4) + Character.digit(str.charAt(i + 1), 16));
        }
        return bArr;
    }
}
