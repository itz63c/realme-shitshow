package com.coloros.mcssdk.p012e;

import android.text.TextUtils;

/* renamed from: com.coloros.mcssdk.e.a */
public final class C0183a extends C0185c {

    /* renamed from: a */
    String f353a;

    /* renamed from: b */
    String f354b;

    /* renamed from: c */
    long f355c;

    /* renamed from: d */
    long f356d;

    /* renamed from: e */
    int f357e;

    /* renamed from: f */
    String f358f = "08:00-22:00";

    /* renamed from: g */
    String f359g;

    /* renamed from: h */
    int f360h = 0;

    /* renamed from: i */
    int f361i = 0;

    /* renamed from: a */
    public final int mo220a() {
        return 4098;
    }

    /* renamed from: a */
    public final void mo221a(int i) {
        this.f357e = i;
    }

    /* renamed from: a */
    public final void mo222a(long j) {
        this.f355c = j;
    }

    /* renamed from: a */
    public final void mo223a(String str) {
        this.f354b = str;
    }

    /* renamed from: b */
    public final void mo224b(int i) {
        this.f360h = i;
    }

    /* renamed from: b */
    public final void mo225b(long j) {
        this.f356d = j;
    }

    /* renamed from: b */
    public final void mo226b(String str) {
        if (!TextUtils.isEmpty(str)) {
            this.f358f = str;
        }
    }

    /* renamed from: c */
    public final void mo227c(int i) {
        this.f361i = i;
    }

    /* renamed from: c */
    public final void mo228c(String str) {
        this.f353a = str;
    }

    /* renamed from: d */
    public final void mo229d(String str) {
        this.f359g = str;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("messageID:" + this.f370j);
        sb.append(",taskID:" + this.f372l);
        sb.append(",appPackage:" + this.f371k);
        sb.append(",title:" + this.f353a);
        sb.append(",balanceTime:" + this.f357e);
        sb.append(",startTime:" + this.f355c);
        sb.append(",endTime:" + this.f356d);
        sb.append(",balanceTime:" + this.f357e);
        sb.append(",timeRanges:" + this.f358f);
        sb.append(",forcedDelivery:" + this.f360h);
        sb.append(",distinctBycontent:" + this.f361i);
        return sb.toString();
    }
}
