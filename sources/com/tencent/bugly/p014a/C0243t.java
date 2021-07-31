package com.tencent.bugly.p014a;

import com.coloros.neton.BuildConfig;
import java.nio.ByteBuffer;
import java.util.HashMap;

/* renamed from: com.tencent.bugly.a.t */
/* compiled from: BUGLY */
public final class C0243t extends C0242s {

    /* renamed from: f */
    private static HashMap<String, byte[]> f595f = null;

    /* renamed from: g */
    private static HashMap<String, HashMap<String, byte[]>> f596g = null;

    /* renamed from: e */
    private C0245v f597e = new C0245v();

    public C0243t() {
        this.f597e.f602a = 2;
    }

    /* renamed from: a */
    public final <T> void mo349a(String str, T t) {
        if (str.startsWith(".")) {
            throw new IllegalArgumentException("put name can not startwith . , now is " + str);
        }
        super.mo349a(str, t);
    }

    /* renamed from: l */
    public final void mo392l() {
        super.mo392l();
        this.f597e.f602a = 3;
    }

    /* renamed from: a */
    public final byte[] mo351a() {
        if (this.f597e.f602a != 2) {
            if (this.f597e.f604c == null) {
                this.f597e.f604c = BuildConfig.FLAVOR;
            }
            if (this.f597e.f605d == null) {
                this.f597e.f605d = BuildConfig.FLAVOR;
            }
        } else if (this.f597e.f604c.equals(BuildConfig.FLAVOR)) {
            throw new IllegalArgumentException("servantName can not is null");
        } else if (this.f597e.f605d.equals(BuildConfig.FLAVOR)) {
            throw new IllegalArgumentException("funcName can not is null");
        }
        C0249z zVar = new C0249z(0);
        zVar.mo410a(this.f402b);
        if (this.f597e.f602a == 2) {
            zVar.mo419a(this.f401a, 0);
        } else {
            zVar.mo419a(this.f592d, 0);
        }
        this.f597e.f606e = C0210ab.m520a(zVar.mo411a());
        C0249z zVar2 = new C0249z(0);
        zVar2.mo410a(this.f402b);
        this.f597e.mo353a(zVar2);
        byte[] a = C0210ab.m520a(zVar2.mo411a());
        int length = a.length;
        ByteBuffer allocate = ByteBuffer.allocate(length + 4);
        allocate.putInt(length + 4).put(a).flip();
        return allocate.array();
    }

    /* renamed from: a */
    public final void mo350a(byte[] bArr) {
        if (bArr.length < 4) {
            throw new IllegalArgumentException("decode package must include size head");
        }
        try {
            C0247x xVar = new C0247x(bArr, (byte) 0);
            xVar.mo400a(this.f402b);
            this.f597e.mo352a(xVar);
            if (this.f597e.f602a == 3) {
                C0247x xVar2 = new C0247x(this.f597e.f606e);
                xVar2.mo400a(this.f402b);
                if (f595f == null) {
                    HashMap<String, byte[]> hashMap = new HashMap<>();
                    f595f = hashMap;
                    hashMap.put(BuildConfig.FLAVOR, new byte[0]);
                }
                this.f592d = xVar2.mo404a(f595f);
                return;
            }
            C0247x xVar3 = new C0247x(this.f597e.f606e);
            xVar3.mo400a(this.f402b);
            if (f596g == null) {
                f596g = new HashMap<>();
                HashMap hashMap2 = new HashMap();
                hashMap2.put(BuildConfig.FLAVOR, new byte[0]);
                f596g.put(BuildConfig.FLAVOR, hashMap2);
            }
            this.f401a = xVar3.mo404a(f596g);
            new HashMap();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /* renamed from: b */
    public final void mo393b(String str) {
        this.f597e.f604c = str;
    }

    /* renamed from: c */
    public final void mo394c(String str) {
        this.f597e.f605d = str;
    }

    /* renamed from: m */
    public final void mo395m() {
        this.f597e.f603b = 1;
    }
}
