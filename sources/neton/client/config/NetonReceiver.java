package neton.client.config;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import java.util.ArrayList;
import neton.client.Utils.LogUtil;
import neton.client.Utils.ThreadPoolUtil;

public class NetonReceiver extends BroadcastReceiver {
    private static final String ACTION_CONNECTIVITY_CHANGE = "android.net.conn.CONNECTIVITY_CHANGE";
    private static final String COLUMN_NAME_XML = "xml";
    private static final Uri CONTENT_URI = Uri.parse("content://com.nearme.romupdate.provider.db/update_list");

    public void onReceive(Context context, Intent intent) {
        processNetonAction(context, intent);
    }

    public static void processNetonAction(final Context context, final Intent intent) {
        final String action = intent.getAction();
        LogUtil.m787d("NetonReceiver action = " + action);
        ThreadPoolUtil.execute(new Runnable() {
            public final void run() {
                if (action.equals(Constants.ACTION_ROM_UPDATED)) {
                    ArrayList<String> stringArrayListExtra = intent.getStringArrayListExtra(Constants.KEY_CONFIG_LIST);
                    LogUtil.m787d("NetonReceiver changedFilterNameList = " + stringArrayListExtra);
                    if (stringArrayListExtra != null && !stringArrayListExtra.isEmpty() && stringArrayListExtra.contains(Constants.ROMUPDATE_NETON_FILTER)) {
                        String access$000 = NetonReceiver.getDataFromProvider(context, Constants.ROMUPDATE_NETON_FILTER);
                        LogUtil.m787d("NetonReceiver jsonValue = " + access$000);
                        if (!TextUtils.isEmpty(access$000)) {
                            new JsonConfigParser().parse(access$000);
                            return;
                        }
                        return;
                    }
                    return;
                }
                action.equals(NetonReceiver.ACTION_CONNECTIVITY_CHANGE);
            }
        });
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Removed duplicated region for block: B:12:0x0045  */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x006c  */
    /* JADX WARNING: Removed duplicated region for block: B:29:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String getDataFromProvider(android.content.Context r7, java.lang.String r8) {
        /*
            r6 = 0
            r0 = 1
            java.lang.String[] r2 = new java.lang.String[r0]
            r0 = 0
            java.lang.String r1 = "xml"
            r2[r0] = r1
            android.content.ContentResolver r0 = r7.getContentResolver()     // Catch:{ Exception -> 0x0049, all -> 0x0068 }
            android.net.Uri r1 = CONTENT_URI     // Catch:{ Exception -> 0x0049, all -> 0x0068 }
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0049, all -> 0x0068 }
            java.lang.String r4 = "filtername=\""
            r3.<init>(r4)     // Catch:{ Exception -> 0x0049, all -> 0x0068 }
            java.lang.StringBuilder r3 = r3.append(r8)     // Catch:{ Exception -> 0x0049, all -> 0x0068 }
            java.lang.String r4 = "\""
            java.lang.StringBuilder r3 = r3.append(r4)     // Catch:{ Exception -> 0x0049, all -> 0x0068 }
            java.lang.String r3 = r3.toString()     // Catch:{ Exception -> 0x0049, all -> 0x0068 }
            r4 = 0
            r5 = 0
            android.database.Cursor r1 = r0.query(r1, r2, r3, r4, r5)     // Catch:{ Exception -> 0x0049, all -> 0x0068 }
            if (r1 == 0) goto L_0x0076
            int r0 = r1.getCount()     // Catch:{ Exception -> 0x0072 }
            if (r0 <= 0) goto L_0x0076
            boolean r0 = r1.moveToFirst()     // Catch:{ Exception -> 0x0072 }
            if (r0 == 0) goto L_0x0076
            java.lang.String r0 = "xml"
            int r0 = r1.getColumnIndex(r0)     // Catch:{ Exception -> 0x0072 }
            java.lang.String r6 = r1.getString(r0)     // Catch:{ Exception -> 0x0072 }
            r0 = r6
        L_0x0043:
            if (r1 == 0) goto L_0x0048
            r1.close()
        L_0x0048:
            return r0
        L_0x0049:
            r0 = move-exception
            r1 = r6
        L_0x004b:
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ all -> 0x0070 }
            java.lang.String r3 = "getDataFromProvider--Exception:"
            r2.<init>(r3)     // Catch:{ all -> 0x0070 }
            java.lang.String r0 = r0.toString()     // Catch:{ all -> 0x0070 }
            java.lang.StringBuilder r0 = r2.append(r0)     // Catch:{ all -> 0x0070 }
            java.lang.String r0 = r0.toString()     // Catch:{ all -> 0x0070 }
            neton.client.Utils.LogUtil.m790e((java.lang.String) r0)     // Catch:{ all -> 0x0070 }
            if (r1 == 0) goto L_0x0074
            r1.close()
            r0 = r6
            goto L_0x0048
        L_0x0068:
            r0 = move-exception
            r1 = r6
        L_0x006a:
            if (r1 == 0) goto L_0x006f
            r1.close()
        L_0x006f:
            throw r0
        L_0x0070:
            r0 = move-exception
            goto L_0x006a
        L_0x0072:
            r0 = move-exception
            goto L_0x004b
        L_0x0074:
            r0 = r6
            goto L_0x0048
        L_0x0076:
            r0 = r6
            goto L_0x0043
        */
        throw new UnsupportedOperationException("Method not decompiled: neton.client.config.NetonReceiver.getDataFromProvider(android.content.Context, java.lang.String):java.lang.String");
    }
}
