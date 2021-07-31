package neton.client.cookie;

import android.content.Context;
import java.util.List;
import neton.Cookie;
import neton.CookieJar;
import neton.HttpUrl;
import neton.client.Utils.LogUtil;

public class CookiesManager implements CookieJar {
    private static PersistentCookieStore cookieStore;

    public CookiesManager(Context context) {
        cookieStore = new PersistentCookieStore(context);
    }

    public void saveFromResponse(HttpUrl httpUrl, List<Cookie> list) {
        LogUtil.m787d("CookiesManager--saveFromResponse--url:" + httpUrl + ",cookies:" + list);
        if (list != null && list.size() > 0) {
            for (Cookie add : list) {
                cookieStore.add(httpUrl, add);
            }
        }
    }

    public List<Cookie> loadForRequest(HttpUrl httpUrl) {
        List<Cookie> list = cookieStore.get(httpUrl);
        LogUtil.m787d("CookiesManager--loadForRequest--url:" + httpUrl + ",cookies:" + list);
        return list;
    }
}
