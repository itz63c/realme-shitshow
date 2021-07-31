package com.coloros.neton;

import android.text.TextUtils;
import neton.Interceptor;
import neton.Response;
import neton.client.Utils.LogUtil;
import neton.client.config.ConfigManager;
import neton.client.config.Constants;

public class OppoSetInterceptor implements Interceptor {
    public Response intercept(Interceptor.Chain chain) {
        Response proceed = chain.proceed(chain.request());
        setOppoSet(proceed);
        return proceed;
    }

    private void setOppoSet(Response response) {
        String stringData = ConfigManager.getInstance().getStringData(Constants.KEY_HEADER_SET);
        String str = null;
        if (response != null && response.isSuccessful()) {
            str = response.header(Constants.KEY_HEADER_SET);
            LogUtil.m787d("setOppoSet--response:" + str);
        }
        if (!TextUtils.isEmpty(str) && !str.equalsIgnoreCase(stringData)) {
            LogUtil.m787d("setOppoSet--final:" + str);
            ConfigManager.getInstance().setStringData(Constants.KEY_HEADER_SET, str);
        }
    }
}
