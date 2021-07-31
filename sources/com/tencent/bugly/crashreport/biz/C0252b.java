package com.tencent.bugly.crashreport.biz;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.os.Parcel;
import com.tencent.bugly.crashreport.common.info.C0257c;
import com.tencent.bugly.p014a.C0208a;
import com.tencent.bugly.p014a.C0209aa;
import com.tencent.bugly.p014a.C0211ac;
import com.tencent.bugly.p014a.C0215ag;
import com.tencent.bugly.p014a.C0216ah;
import com.tencent.bugly.p014a.C0221am;
import com.tencent.bugly.p014a.C0235l;
import com.tencent.bugly.p014a.C0240q;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import neton.client.config.Constants;

/* renamed from: com.tencent.bugly.crashreport.biz.b */
/* compiled from: BUGLY */
public final class C0252b {

    /* renamed from: a */
    private Context f645a;

    /* renamed from: b */
    private int f646b;

    /* renamed from: a */
    static /* synthetic */ void m714a(UserInfoBean userInfoBean) {
        if (userInfoBean != null) {
            long a = C0211ac.m521a().mo357a("t_ui", m716b(userInfoBean));
            if (a >= 0) {
                C0221am.m597c("insert %s success! %d", "t_ui", Long.valueOf(a));
                userInfoBean.f626a = a;
            }
        }
    }

    /* renamed from: a */
    public final synchronized void mo428a() {
        ArrayList arrayList;
        boolean z;
        boolean z2;
        int i;
        int i2 = 1;
        synchronized (this) {
            String str = C0257c.m731a(this.f645a).f689d;
            ArrayList arrayList2 = new ArrayList();
            List<UserInfoBean> a = m713a(str);
            if (a != null) {
                int size = a.size() - 10;
                if (size > 0) {
                    for (int i3 = 0; i3 < a.size() - 1; i3++) {
                        for (int i4 = i3 + 1; i4 < a.size(); i4++) {
                            if (a.get(i3).f630e > a.get(i4).f630e) {
                                a.set(i3, a.get(i4));
                                a.set(i4, a.get(i3));
                            }
                        }
                    }
                    for (int i5 = 0; i5 < size; i5++) {
                        arrayList2.add(a.get(i5));
                    }
                }
                Iterator<UserInfoBean> it = a.iterator();
                int i6 = 0;
                while (it.hasNext()) {
                    UserInfoBean next = it.next();
                    if (next.f631f != -1) {
                        it.remove();
                        if (next.f630e < C0208a.m508j()) {
                            arrayList2.add(next);
                        }
                    }
                    if (next.f630e <= System.currentTimeMillis() - Constants.DEFAULT_LIVE_ON_TIME || !(next.f627b == 1 || next.f627b == 4)) {
                        i = i6;
                    } else {
                        i = i6 + 1;
                    }
                    i6 = i;
                }
                if (i6 > 15) {
                    C0221am.m598d("[userinfo] userinfo too many times in 10 min: %d", Integer.valueOf(i6));
                    z2 = false;
                } else {
                    z2 = true;
                }
                arrayList = a;
                z = z2;
            } else {
                arrayList = new ArrayList();
                z = true;
            }
            if (arrayList2.size() > 0) {
                m715a((List<UserInfoBean>) arrayList2);
            }
            if (!(!z || arrayList == null || arrayList.size() == 0)) {
                C0221am.m597c("[userinfo] do userinfo, size: %d", Integer.valueOf(arrayList.size()));
                if (this.f646b != 1) {
                    i2 = 2;
                }
                C0240q a2 = C0208a.m472a(arrayList, i2);
                if (a2 == null) {
                    C0221am.m598d("[biz] create uPkg fail!", new Object[0]);
                } else {
                    byte[] a3 = C0208a.m485a((C0209aa) a2);
                    if (a3 == null) {
                        C0221am.m598d("[biz] send encode fail!", new Object[0]);
                    } else {
                        C0235l a4 = C0208a.m469a(this.f645a, 640, a3);
                        if (a4 == null) {
                            C0221am.m598d("request package is null.", new Object[0]);
                        } else {
                            C0216ah.m553a().mo369a(1001, a4, (C0215ag) new C0253c(this, arrayList));
                        }
                    }
                }
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:4:0x000d, code lost:
        if (r8.trim().length() > 0) goto L_0x000f;
     */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static java.util.List<com.tencent.bugly.crashreport.biz.UserInfoBean> m713a(java.lang.String r8) {
        /*
            r2 = 1
            r1 = 0
            r0 = 0
            if (r8 == 0) goto L_0x002b
            java.lang.String r3 = r8.trim()     // Catch:{ Throwable -> 0x00dd, all -> 0x00da }
            int r3 = r3.length()     // Catch:{ Throwable -> 0x00dd, all -> 0x00da }
            if (r3 <= 0) goto L_0x002b
        L_0x000f:
            if (r1 == 0) goto L_0x002d
            r1 = r0
        L_0x0012:
            com.tencent.bugly.a.ac r2 = com.tencent.bugly.p014a.C0211ac.m521a()     // Catch:{ Throwable -> 0x00dd, all -> 0x00da }
            java.lang.String r3 = "t_ui"
            r4 = 0
            android.database.Cursor r2 = r2.mo358a((java.lang.String) r3, (java.lang.String[]) r4, (java.lang.String) r1)     // Catch:{ Throwable -> 0x00dd, all -> 0x00da }
            if (r2 != 0) goto L_0x0043
            if (r2 == 0) goto L_0x002a
            boolean r1 = r2.isClosed()
            if (r1 != 0) goto L_0x002a
            r2.close()
        L_0x002a:
            return r0
        L_0x002b:
            r1 = r2
            goto L_0x000f
        L_0x002d:
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x00dd, all -> 0x00da }
            java.lang.String r2 = "_pc = '"
            r1.<init>(r2)     // Catch:{ Throwable -> 0x00dd, all -> 0x00da }
            java.lang.StringBuilder r1 = r1.append(r8)     // Catch:{ Throwable -> 0x00dd, all -> 0x00da }
            java.lang.String r2 = "'"
            java.lang.StringBuilder r1 = r1.append(r2)     // Catch:{ Throwable -> 0x00dd, all -> 0x00da }
            java.lang.String r1 = r1.toString()     // Catch:{ Throwable -> 0x00dd, all -> 0x00da }
            goto L_0x0012
        L_0x0043:
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x005d }
            r3.<init>()     // Catch:{ Throwable -> 0x005d }
            java.util.ArrayList r1 = new java.util.ArrayList     // Catch:{ Throwable -> 0x005d }
            r1.<init>()     // Catch:{ Throwable -> 0x005d }
        L_0x004d:
            boolean r4 = r2.moveToNext()     // Catch:{ Throwable -> 0x005d }
            if (r4 == 0) goto L_0x009f
            com.tencent.bugly.crashreport.biz.UserInfoBean r4 = m712a((android.database.Cursor) r2)     // Catch:{ Throwable -> 0x005d }
            if (r4 == 0) goto L_0x0073
            r1.add(r4)     // Catch:{ Throwable -> 0x005d }
            goto L_0x004d
        L_0x005d:
            r1 = move-exception
        L_0x005e:
            boolean r3 = com.tencent.bugly.p014a.C0221am.m594a(r1)     // Catch:{ all -> 0x0091 }
            if (r3 != 0) goto L_0x0067
            r1.printStackTrace()     // Catch:{ all -> 0x0091 }
        L_0x0067:
            if (r2 == 0) goto L_0x002a
            boolean r1 = r2.isClosed()
            if (r1 != 0) goto L_0x002a
            r2.close()
            goto L_0x002a
        L_0x0073:
            java.lang.String r4 = "_id"
            int r4 = r2.getColumnIndex(r4)     // Catch:{ Throwable -> 0x0087 }
            long r4 = r2.getLong(r4)     // Catch:{ Throwable -> 0x0087 }
            java.lang.String r6 = " or _id = "
            java.lang.StringBuilder r6 = r3.append(r6)     // Catch:{ Throwable -> 0x0087 }
            r6.append(r4)     // Catch:{ Throwable -> 0x0087 }
            goto L_0x004d
        L_0x0087:
            r4 = move-exception
            java.lang.String r4 = "unknown id!"
            r5 = 0
            java.lang.Object[] r5 = new java.lang.Object[r5]     // Catch:{ Throwable -> 0x005d }
            com.tencent.bugly.p014a.C0221am.m598d(r4, r5)     // Catch:{ Throwable -> 0x005d }
            goto L_0x004d
        L_0x0091:
            r0 = move-exception
            r1 = r0
        L_0x0093:
            if (r2 == 0) goto L_0x009e
            boolean r0 = r2.isClosed()
            if (r0 != 0) goto L_0x009e
            r2.close()
        L_0x009e:
            throw r1
        L_0x009f:
            java.lang.String r3 = r3.toString()     // Catch:{ Throwable -> 0x005d }
            int r4 = r3.length()     // Catch:{ Throwable -> 0x005d }
            if (r4 <= 0) goto L_0x00cc
            r4 = 4
            java.lang.String r3 = r3.substring(r4)     // Catch:{ Throwable -> 0x005d }
            com.tencent.bugly.a.ac r4 = com.tencent.bugly.p014a.C0211ac.m521a()     // Catch:{ Throwable -> 0x005d }
            java.lang.String r5 = "t_ui"
            int r3 = r4.mo356a((java.lang.String) r5, (java.lang.String) r3)     // Catch:{ Throwable -> 0x005d }
            java.lang.String r4 = "[session] deleted %s error data %d"
            r5 = 2
            java.lang.Object[] r5 = new java.lang.Object[r5]     // Catch:{ Throwable -> 0x005d }
            r6 = 0
            java.lang.String r7 = "t_ui"
            r5[r6] = r7     // Catch:{ Throwable -> 0x005d }
            r6 = 1
            java.lang.Integer r3 = java.lang.Integer.valueOf(r3)     // Catch:{ Throwable -> 0x005d }
            r5[r6] = r3     // Catch:{ Throwable -> 0x005d }
            com.tencent.bugly.p014a.C0221am.m598d(r4, r5)     // Catch:{ Throwable -> 0x005d }
        L_0x00cc:
            if (r2 == 0) goto L_0x00d7
            boolean r0 = r2.isClosed()
            if (r0 != 0) goto L_0x00d7
            r2.close()
        L_0x00d7:
            r0 = r1
            goto L_0x002a
        L_0x00da:
            r1 = move-exception
            r2 = r0
            goto L_0x0093
        L_0x00dd:
            r1 = move-exception
            r2 = r0
            goto L_0x005e
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.bugly.crashreport.biz.C0252b.m713a(java.lang.String):java.util.List");
    }

    /* renamed from: a */
    private static void m715a(List<UserInfoBean> list) {
        if (list.size() != 0) {
            StringBuilder sb = new StringBuilder();
            for (UserInfoBean userInfoBean : list) {
                sb.append(" or _id = ").append(userInfoBean.f626a);
            }
            String sb2 = sb.toString();
            if (sb2.length() > 0) {
                sb2 = sb2.substring(4);
            }
            sb.setLength(0);
            try {
                C0221am.m597c("deleted %s data %d", "t_ui", Integer.valueOf(C0211ac.m521a().mo356a("t_ui", sb2)));
            } catch (Throwable th) {
                if (!C0221am.m594a(th)) {
                    th.printStackTrace();
                }
            }
        }
    }

    /* renamed from: b */
    private static ContentValues m716b(UserInfoBean userInfoBean) {
        if (userInfoBean == null) {
            return null;
        }
        try {
            ContentValues contentValues = new ContentValues();
            if (userInfoBean.f626a > 0) {
                contentValues.put("_id", Long.valueOf(userInfoBean.f626a));
            }
            contentValues.put("_tm", Long.valueOf(userInfoBean.f630e));
            contentValues.put("_ut", Long.valueOf(userInfoBean.f631f));
            contentValues.put("_tp", Integer.valueOf(userInfoBean.f627b));
            contentValues.put("_pc", userInfoBean.f628c);
            Parcel obtain = Parcel.obtain();
            userInfoBean.writeToParcel(obtain, 0);
            byte[] marshall = obtain.marshall();
            obtain.recycle();
            contentValues.put("_dt", marshall);
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
    private static UserInfoBean m712a(Cursor cursor) {
        if (cursor == null) {
            return null;
        }
        try {
            byte[] blob = cursor.getBlob(cursor.getColumnIndex("_dt"));
            if (blob == null) {
                return null;
            }
            long j = cursor.getLong(cursor.getColumnIndex("_id"));
            UserInfoBean userInfoBean = (UserInfoBean) C0208a.m474a(blob, UserInfoBean.CREATOR);
            if (userInfoBean == null) {
                return userInfoBean;
            }
            userInfoBean.f626a = j;
            return userInfoBean;
        } catch (Throwable th) {
            if (!C0221am.m594a(th)) {
                th.printStackTrace();
            }
            return null;
        }
    }
}
