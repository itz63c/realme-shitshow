package com.tencent.bugly.p014a;

import com.coloros.neton.BuildConfig;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/* renamed from: com.tencent.bugly.a.s */
/* compiled from: BUGLY */
public class C0242s extends C0208a {

    /* renamed from: d */
    protected HashMap<String, byte[]> f592d = null;

    /* renamed from: e */
    private HashMap<String, Object> f593e = new HashMap<>();

    /* renamed from: f */
    private C0247x f594f = new C0247x();

    /* renamed from: a */
    public final /* bridge */ /* synthetic */ void mo348a(String str) {
        super.mo348a(str);
    }

    /* renamed from: l */
    public void mo392l() {
        this.f592d = new HashMap<>();
    }

    /* renamed from: a */
    public <T> void mo349a(String str, T t) {
        if (this.f592d == null) {
            super.mo349a(str, t);
        } else if (str == null) {
            throw new IllegalArgumentException("put key can not is null");
        } else if (t == null) {
            throw new IllegalArgumentException("put value can not is null");
        } else if (t instanceof Set) {
            throw new IllegalArgumentException("can not support Set");
        } else {
            C0249z zVar = new C0249z();
            zVar.mo410a(this.f402b);
            zVar.mo416a((Object) t, 0);
            this.f592d.put(str, C0210ab.m520a(zVar.mo411a()));
        }
    }

    /* renamed from: b */
    public final <T> T mo391b(String str, T t) {
        byte[] bArr;
        if (this.f592d != null) {
            if (!this.f592d.containsKey(str)) {
                return null;
            }
            if (this.f593e.containsKey(str)) {
                return this.f593e.get(str);
            }
            try {
                this.f594f.mo406a(this.f592d.get(str));
                this.f594f.mo400a(this.f402b);
                T a = this.f594f.mo403a(t, 0, true);
                if (a == null) {
                    return a;
                }
                this.f593e.put(str, a);
                return a;
            } catch (Exception e) {
                throw new C0241r(e);
            }
        } else if (!this.f401a.containsKey(str)) {
            return null;
        } else {
            if (this.f593e.containsKey(str)) {
                return this.f593e.get(str);
            }
            byte[] bArr2 = new byte[0];
            Iterator it = ((HashMap) this.f401a.get(str)).entrySet().iterator();
            if (it.hasNext()) {
                Map.Entry entry = (Map.Entry) it.next();
                entry.getKey();
                bArr = (byte[]) entry.getValue();
            } else {
                bArr = bArr2;
            }
            try {
                this.f594f.mo406a(bArr);
                this.f594f.mo400a(this.f402b);
                T a2 = this.f594f.mo403a(t, 0, true);
                this.f593e.put(str, a2);
                return a2;
            } catch (Exception e2) {
                throw new C0241r(e2);
            }
        }
    }

    /* renamed from: a */
    public byte[] mo351a() {
        if (this.f592d == null) {
            return super.mo351a();
        }
        C0249z zVar = new C0249z(0);
        zVar.mo410a(this.f402b);
        zVar.mo419a(this.f592d, 0);
        return C0210ab.m520a(zVar.mo411a());
    }

    /* renamed from: a */
    public void mo350a(byte[] bArr) {
        try {
            super.mo350a(bArr);
        } catch (Exception e) {
            this.f594f.mo406a(bArr);
            this.f594f.mo400a(this.f402b);
            HashMap hashMap = new HashMap(1);
            hashMap.put(BuildConfig.FLAVOR, new byte[0]);
            this.f592d = this.f594f.mo404a(hashMap);
        }
    }
}
