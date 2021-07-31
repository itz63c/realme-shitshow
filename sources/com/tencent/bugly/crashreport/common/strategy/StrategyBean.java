package com.tencent.bugly.crashreport.common.strategy;

import android.os.Parcel;
import android.os.Parcelable;
import com.tencent.bugly.p014a.C0208a;
import java.util.Map;

/* compiled from: BUGLY */
public class StrategyBean implements Parcelable {
    public static final Parcelable.Creator<StrategyBean> CREATOR = new C0258a();

    /* renamed from: a */
    public static String f712a;

    /* renamed from: t */
    private static String f713t = "http://android.bugly.qq.com/rqd/async";

    /* renamed from: u */
    private static String f714u = "http://rqd.uu.qq.com/rqd/sync";

    /* renamed from: b */
    public long f715b;

    /* renamed from: c */
    public long f716c;

    /* renamed from: d */
    public boolean f717d;

    /* renamed from: e */
    public boolean f718e;

    /* renamed from: f */
    public boolean f719f;

    /* renamed from: g */
    public boolean f720g;

    /* renamed from: h */
    public boolean f721h;

    /* renamed from: i */
    public boolean f722i;

    /* renamed from: j */
    public boolean f723j;

    /* renamed from: k */
    public boolean f724k;

    /* renamed from: l */
    public long f725l;

    /* renamed from: m */
    public long f726m;

    /* renamed from: n */
    public String f727n;

    /* renamed from: o */
    public String f728o;

    /* renamed from: p */
    public String f729p;

    /* renamed from: q */
    public String f730q;

    /* renamed from: r */
    public Map<String, String> f731r;

    /* renamed from: s */
    public int f732s;

    public StrategyBean() {
        this.f715b = -1;
        this.f716c = -1;
        this.f717d = true;
        this.f718e = true;
        this.f719f = true;
        this.f720g = true;
        this.f721h = true;
        this.f722i = true;
        this.f723j = true;
        this.f724k = true;
        this.f726m = 30000;
        this.f727n = f713t;
        this.f728o = f713t;
        this.f729p = f714u;
        this.f732s = 10;
        this.f716c = System.currentTimeMillis();
        StringBuilder sb = new StringBuilder();
        sb.append("S(@L@L@)");
        f712a = sb.toString();
        sb.setLength(0);
        sb.append("*^@K#K@!");
        this.f730q = sb.toString();
    }

    public StrategyBean(Parcel parcel) {
        boolean z;
        boolean z2;
        boolean z3;
        boolean z4;
        boolean z5;
        boolean z6;
        boolean z7 = true;
        this.f715b = -1;
        this.f716c = -1;
        this.f717d = true;
        this.f718e = true;
        this.f719f = true;
        this.f720g = true;
        this.f721h = true;
        this.f722i = true;
        this.f723j = true;
        this.f724k = true;
        this.f726m = 30000;
        this.f727n = f713t;
        this.f728o = f713t;
        this.f729p = f714u;
        this.f732s = 10;
        try {
            f712a = "S(@L@L@)";
            this.f716c = parcel.readLong();
            this.f717d = parcel.readByte() == 1;
            if (parcel.readByte() == 1) {
                z = true;
            } else {
                z = false;
            }
            this.f718e = z;
            if (parcel.readByte() == 1) {
                z2 = true;
            } else {
                z2 = false;
            }
            this.f719f = z2;
            this.f727n = parcel.readString();
            this.f728o = parcel.readString();
            this.f730q = parcel.readString();
            this.f731r = C0208a.m491b(parcel);
            if (parcel.readByte() == 1) {
                z3 = true;
            } else {
                z3 = false;
            }
            this.f720g = z3;
            if (parcel.readByte() == 1) {
                z4 = true;
            } else {
                z4 = false;
            }
            this.f723j = z4;
            if (parcel.readByte() == 1) {
                z5 = true;
            } else {
                z5 = false;
            }
            this.f724k = z5;
            this.f726m = parcel.readLong();
            if (parcel.readByte() == 1) {
                z6 = true;
            } else {
                z6 = false;
            }
            this.f721h = z6;
            this.f722i = parcel.readByte() != 1 ? false : z7;
            this.f725l = parcel.readLong();
            this.f732s = parcel.readInt();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        int i2;
        int i3;
        int i4;
        int i5;
        int i6;
        int i7;
        int i8 = 1;
        parcel.writeLong(this.f716c);
        parcel.writeByte((byte) (this.f717d ? 1 : 0));
        if (this.f718e) {
            i2 = 1;
        } else {
            i2 = 0;
        }
        parcel.writeByte((byte) i2);
        if (this.f719f) {
            i3 = 1;
        } else {
            i3 = 0;
        }
        parcel.writeByte((byte) i3);
        parcel.writeString(this.f727n);
        parcel.writeString(this.f728o);
        parcel.writeString(this.f730q);
        C0208a.m492b(parcel, this.f731r);
        if (this.f720g) {
            i4 = 1;
        } else {
            i4 = 0;
        }
        parcel.writeByte((byte) i4);
        if (this.f723j) {
            i5 = 1;
        } else {
            i5 = 0;
        }
        parcel.writeByte((byte) i5);
        if (this.f724k) {
            i6 = 1;
        } else {
            i6 = 0;
        }
        parcel.writeByte((byte) i6);
        parcel.writeLong(this.f726m);
        if (this.f721h) {
            i7 = 1;
        } else {
            i7 = 0;
        }
        parcel.writeByte((byte) i7);
        if (!this.f722i) {
            i8 = 0;
        }
        parcel.writeByte((byte) i8);
        parcel.writeLong(this.f725l);
        parcel.writeInt(this.f732s);
    }
}
