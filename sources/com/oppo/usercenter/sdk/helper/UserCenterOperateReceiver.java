package com.oppo.usercenter.sdk.helper;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.text.TextUtils;
import com.coloros.neton.BuildConfig;
import com.nearme.aidl.UserEntity;
import com.oppo.usercenter.sdk.C0198a;
import com.oppo.usercenter.sdk.p013a.C0199a;

public class UserCenterOperateReceiver extends BroadcastReceiver {

    /* renamed from: a */
    private static final String f399a = UserCenterOperateReceiver.class.getSimpleName();

    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        C0199a.m459a("action = " + action + " ,RECEIVER PKG = " + context.getPackageName());
        if (!TextUtils.isEmpty(action)) {
            if ("com.oppo.usercenter.account_logout".equals(action)) {
                if (intent != null && "com.oppo.usercenter".equals(C0205b.m467a(intent.getStringExtra("com.oppo.usercenter.aescoder_key")))) {
                    C0199a.m459a("receive logout and verify clear data");
                    C0204a.m461a(context);
                }
            } else if ("com.oppo.usercenter.modify_name".equals(action)) {
                if (intent != null) {
                    String stringExtra = intent.getStringExtra("OldUserName");
                    String stringExtra2 = intent.getStringExtra("UserName");
                    String a = C0205b.m467a(stringExtra);
                    if (!TextUtils.isEmpty(a) && a.equals(C0204a.m464b(context))) {
                        String a2 = C0205b.m467a(stringExtra2);
                        if (!TextUtils.isEmpty(a2)) {
                            C0199a.m459a("oldName = " + a);
                            C0199a.m459a("newName  = " + a2);
                            C0204a.m463a(context, a2);
                        }
                    }
                }
            } else if ("com.oppo.usercenter.account_login".equals(action) && intent != null) {
                String stringExtra3 = intent.getStringExtra("extra_broadcast_action_userentity_key");
                boolean booleanExtra = intent.getBooleanExtra("extra_broadcast_action_userentity_key_need_callback", true);
                if (!TextUtils.isEmpty(stringExtra3)) {
                    UserEntity d = UserEntity.m451d(C0205b.m467a(stringExtra3));
                    if (d == null || d.mo329b() != 30001001) {
                        C0204a.m461a(context);
                    } else {
                        C0199a.m459a("onreceive login result = " + d.toString() + " , isNeed2Callback = " + booleanExtra);
                        C0204a.m462a(context, d);
                    }
                    if (booleanExtra) {
                        C0198a.m458a(d);
                    }
                } else if (booleanExtra) {
                    C0198a.m458a(new UserEntity("Already canceled!", BuildConfig.FLAVOR, BuildConfig.FLAVOR));
                }
            }
            new Handler().postDelayed(new C0206c(this), 5000);
        }
    }
}
