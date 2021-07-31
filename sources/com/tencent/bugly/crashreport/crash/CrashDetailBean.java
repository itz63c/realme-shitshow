package com.tencent.bugly.crashreport.crash;

import android.os.Parcel;
import android.os.Parcelable;
import com.coloros.neton.BuildConfig;
import com.tencent.bugly.crashreport.common.info.PlugInBean;
import com.tencent.bugly.p014a.C0208a;
import java.util.Map;
import java.util.UUID;

/* compiled from: BUGLY */
public class CrashDetailBean implements Parcelable, Comparable<CrashDetailBean> {
    public static final Parcelable.Creator<CrashDetailBean> CREATOR = new C0263b();

    /* renamed from: A */
    public String f745A = BuildConfig.FLAVOR;

    /* renamed from: B */
    public long f746B = -1;

    /* renamed from: C */
    public long f747C = -1;

    /* renamed from: D */
    public long f748D = -1;

    /* renamed from: E */
    public long f749E = -1;

    /* renamed from: F */
    public long f750F = -1;

    /* renamed from: G */
    public long f751G = -1;

    /* renamed from: H */
    public String f752H = BuildConfig.FLAVOR;

    /* renamed from: I */
    public String f753I = BuildConfig.FLAVOR;

    /* renamed from: J */
    public String f754J = BuildConfig.FLAVOR;

    /* renamed from: K */
    public String f755K = BuildConfig.FLAVOR;

    /* renamed from: L */
    public long f756L = -1;

    /* renamed from: M */
    public boolean f757M = false;

    /* renamed from: N */
    public Map<String, String> f758N = null;

    /* renamed from: O */
    public int f759O = -1;

    /* renamed from: P */
    public int f760P = -1;

    /* renamed from: Q */
    public Map<String, String> f761Q = null;

    /* renamed from: R */
    public Map<String, String> f762R = null;

    /* renamed from: S */
    public byte[] f763S = null;

    /* renamed from: T */
    private String f764T = BuildConfig.FLAVOR;

    /* renamed from: a */
    public long f765a = -1;

    /* renamed from: b */
    public int f766b = 0;

    /* renamed from: c */
    public String f767c = UUID.randomUUID().toString();

    /* renamed from: d */
    public boolean f768d = false;

    /* renamed from: e */
    public String f769e = BuildConfig.FLAVOR;

    /* renamed from: f */
    public String f770f = BuildConfig.FLAVOR;

    /* renamed from: g */
    public String f771g = BuildConfig.FLAVOR;

    /* renamed from: h */
    public Map<String, PlugInBean> f772h = null;

    /* renamed from: i */
    public Map<String, PlugInBean> f773i = null;

    /* renamed from: j */
    public boolean f774j = false;

    /* renamed from: k */
    public boolean f775k = false;

    /* renamed from: l */
    public int f776l = 0;

    /* renamed from: m */
    public String f777m = BuildConfig.FLAVOR;

    /* renamed from: n */
    public String f778n = BuildConfig.FLAVOR;

    /* renamed from: o */
    public String f779o = BuildConfig.FLAVOR;

    /* renamed from: p */
    public String f780p = BuildConfig.FLAVOR;

    /* renamed from: q */
    public String f781q = BuildConfig.FLAVOR;

    /* renamed from: r */
    public long f782r = -1;

    /* renamed from: s */
    public String f783s = null;

    /* renamed from: t */
    public int f784t = 0;

    /* renamed from: u */
    public String f785u = BuildConfig.FLAVOR;

    /* renamed from: v */
    public String f786v = BuildConfig.FLAVOR;

    /* renamed from: w */
    public String f787w = null;

    /* renamed from: x */
    public byte[] f788x = null;

    /* renamed from: y */
    public Map<String, String> f789y = null;

    /* renamed from: z */
    public String f790z = BuildConfig.FLAVOR;

    public /* bridge */ /* synthetic */ int compareTo(Object obj) {
        CrashDetailBean crashDetailBean = (CrashDetailBean) obj;
        if (crashDetailBean != null) {
            long j = this.f782r - crashDetailBean.f782r;
            if (j <= 0) {
                return j < 0 ? -1 : 0;
            }
        }
        return 1;
    }

    public CrashDetailBean() {
    }

    public CrashDetailBean(Parcel parcel) {
        boolean z;
        boolean z2;
        boolean z3 = true;
        this.f766b = parcel.readInt();
        this.f767c = parcel.readString();
        this.f768d = parcel.readByte() == 1;
        this.f769e = parcel.readString();
        this.f770f = parcel.readString();
        this.f771g = parcel.readString();
        if (parcel.readByte() == 1) {
            z = true;
        } else {
            z = false;
        }
        this.f774j = z;
        if (parcel.readByte() == 1) {
            z2 = true;
        } else {
            z2 = false;
        }
        this.f775k = z2;
        this.f776l = parcel.readInt();
        this.f777m = parcel.readString();
        this.f778n = parcel.readString();
        this.f779o = parcel.readString();
        this.f780p = parcel.readString();
        this.f781q = parcel.readString();
        this.f782r = parcel.readLong();
        this.f783s = parcel.readString();
        this.f784t = parcel.readInt();
        this.f785u = parcel.readString();
        this.f786v = parcel.readString();
        this.f787w = parcel.readString();
        this.f789y = C0208a.m491b(parcel);
        this.f790z = parcel.readString();
        this.f745A = parcel.readString();
        this.f746B = parcel.readLong();
        this.f747C = parcel.readLong();
        this.f748D = parcel.readLong();
        this.f749E = parcel.readLong();
        this.f750F = parcel.readLong();
        this.f751G = parcel.readLong();
        this.f752H = parcel.readString();
        this.f764T = parcel.readString();
        this.f753I = parcel.readString();
        this.f754J = parcel.readString();
        this.f755K = parcel.readString();
        this.f756L = parcel.readLong();
        this.f757M = parcel.readByte() != 1 ? false : z3;
        this.f758N = C0208a.m491b(parcel);
        this.f772h = C0208a.m479a(parcel);
        this.f773i = C0208a.m479a(parcel);
        this.f759O = parcel.readInt();
        this.f760P = parcel.readInt();
        this.f761Q = C0208a.m491b(parcel);
        this.f762R = C0208a.m491b(parcel);
        this.f763S = parcel.createByteArray();
        this.f788x = parcel.createByteArray();
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        int i2;
        int i3;
        int i4 = 1;
        parcel.writeInt(this.f766b);
        parcel.writeString(this.f767c);
        parcel.writeByte((byte) (this.f768d ? 1 : 0));
        parcel.writeString(this.f769e);
        parcel.writeString(this.f770f);
        parcel.writeString(this.f771g);
        if (this.f774j) {
            i2 = 1;
        } else {
            i2 = 0;
        }
        parcel.writeByte((byte) i2);
        if (this.f775k) {
            i3 = 1;
        } else {
            i3 = 0;
        }
        parcel.writeByte((byte) i3);
        parcel.writeInt(this.f776l);
        parcel.writeString(this.f777m);
        parcel.writeString(this.f778n);
        parcel.writeString(this.f779o);
        parcel.writeString(this.f780p);
        parcel.writeString(this.f781q);
        parcel.writeLong(this.f782r);
        parcel.writeString(this.f783s);
        parcel.writeInt(this.f784t);
        parcel.writeString(this.f785u);
        parcel.writeString(this.f786v);
        parcel.writeString(this.f787w);
        C0208a.m492b(parcel, this.f789y);
        parcel.writeString(this.f790z);
        parcel.writeString(this.f745A);
        parcel.writeLong(this.f746B);
        parcel.writeLong(this.f747C);
        parcel.writeLong(this.f748D);
        parcel.writeLong(this.f749E);
        parcel.writeLong(this.f750F);
        parcel.writeLong(this.f751G);
        parcel.writeString(this.f752H);
        parcel.writeString(this.f764T);
        parcel.writeString(this.f753I);
        parcel.writeString(this.f754J);
        parcel.writeString(this.f755K);
        parcel.writeLong(this.f756L);
        if (!this.f757M) {
            i4 = 0;
        }
        parcel.writeByte((byte) i4);
        C0208a.m492b(parcel, this.f758N);
        C0208a.m480a(parcel, this.f772h);
        C0208a.m480a(parcel, this.f773i);
        parcel.writeInt(this.f759O);
        parcel.writeInt(this.f760P);
        C0208a.m492b(parcel, this.f761Q);
        C0208a.m492b(parcel, this.f762R);
        parcel.writeByteArray(this.f763S);
        parcel.writeByteArray(this.f788x);
    }
}
