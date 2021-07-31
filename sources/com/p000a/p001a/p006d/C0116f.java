package com.p000a.p001a.p006d;

import java.io.Closeable;
import java.io.Flushable;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;
import neton.client.dns.DnsMode;
import neton.internal.platform.Platform;

/* renamed from: com.a.a.d.f */
/* compiled from: JsonWriter */
public class C0116f implements Closeable, Flushable {

    /* renamed from: a */
    private static final String[] f231a = new String[128];

    /* renamed from: b */
    private static final String[] f232b;

    /* renamed from: c */
    private final Writer f233c;

    /* renamed from: d */
    private final List<C0114d> f234d = new ArrayList();

    /* renamed from: e */
    private String f235e;

    /* renamed from: f */
    private String f236f;

    /* renamed from: g */
    private boolean f237g;

    /* renamed from: h */
    private boolean f238h;

    /* renamed from: i */
    private String f239i;

    /* renamed from: j */
    private boolean f240j;

    static {
        for (int i = 0; i <= 31; i++) {
            f231a[i] = String.format("\\u%04x", new Object[]{Integer.valueOf(i)});
        }
        f231a[34] = "\\\"";
        f231a[92] = "\\\\";
        f231a[9] = "\\t";
        f231a[8] = "\\b";
        f231a[10] = "\\n";
        f231a[13] = "\\r";
        f231a[12] = "\\f";
        String[] strArr = (String[]) f231a.clone();
        f232b = strArr;
        strArr[60] = "\\u003c";
        f232b[62] = "\\u003e";
        f232b[38] = "\\u0026";
        f232b[61] = "\\u003d";
        f232b[39] = "\\u0027";
    }

    public C0116f(Writer writer) {
        this.f234d.add(C0114d.EMPTY_DOCUMENT);
        this.f236f = ":";
        this.f240j = true;
        if (writer == null) {
            throw new NullPointerException("out == null");
        }
        this.f233c = writer;
    }

    /* renamed from: c */
    public final void mo133c(String str) {
        if (str.length() == 0) {
            this.f235e = null;
            this.f236f = ":";
            return;
        }
        this.f235e = str;
        this.f236f = ": ";
    }

    /* renamed from: b */
    public final void mo132b(boolean z) {
        this.f237g = z;
    }

    /* renamed from: g */
    public final boolean mo136g() {
        return this.f237g;
    }

    /* renamed from: c */
    public final void mo134c(boolean z) {
        this.f238h = z;
    }

    /* renamed from: h */
    public final boolean mo137h() {
        return this.f238h;
    }

    /* renamed from: d */
    public final void mo135d(boolean z) {
        this.f240j = z;
    }

    /* renamed from: i */
    public final boolean mo138i() {
        return this.f240j;
    }

    /* renamed from: b */
    public C0116f mo40b() {
        m261j();
        return m257a(C0114d.EMPTY_ARRAY, "[");
    }

    /* renamed from: c */
    public C0116f mo42c() {
        return m256a(C0114d.EMPTY_ARRAY, C0114d.NONEMPTY_ARRAY, "]");
    }

    /* renamed from: d */
    public C0116f mo44d() {
        m261j();
        return m257a(C0114d.EMPTY_OBJECT, "{");
    }

    /* renamed from: e */
    public C0116f mo45e() {
        return m256a(C0114d.EMPTY_OBJECT, C0114d.NONEMPTY_OBJECT, "}");
    }

    /* renamed from: a */
    private C0116f m257a(C0114d dVar, String str) {
        m260e(true);
        this.f234d.add(dVar);
        this.f233c.write(str);
        return this;
    }

    /* renamed from: a */
    private C0116f m256a(C0114d dVar, C0114d dVar2, String str) {
        C0114d a = mo39a();
        if (a != dVar2 && a != dVar) {
            throw new IllegalStateException("Nesting problem: " + this.f234d);
        } else if (this.f239i != null) {
            throw new IllegalStateException("Dangling name: " + this.f239i);
        } else {
            this.f234d.remove(this.f234d.size() - 1);
            if (a == dVar2) {
                m262k();
            }
            this.f233c.write(str);
            return this;
        }
    }

    /* renamed from: a */
    private C0114d mo39a() {
        int size = this.f234d.size();
        if (size != 0) {
            return this.f234d.get(size - 1);
        }
        throw new IllegalStateException("JsonWriter is closed.");
    }

    /* renamed from: a */
    private void m258a(C0114d dVar) {
        this.f234d.set(this.f234d.size() - 1, dVar);
    }

    /* renamed from: a */
    public C0116f mo37a(String str) {
        if (str == null) {
            throw new NullPointerException("name == null");
        } else if (this.f239i != null) {
            throw new IllegalStateException();
        } else if (this.f234d.isEmpty()) {
            throw new IllegalStateException("JsonWriter is closed.");
        } else {
            this.f239i = str;
            return this;
        }
    }

    /* renamed from: j */
    private void m261j() {
        if (this.f239i != null) {
            C0114d a = mo39a();
            if (a == C0114d.NONEMPTY_OBJECT) {
                this.f233c.write(44);
            } else if (a != C0114d.EMPTY_OBJECT) {
                throw new IllegalStateException("Nesting problem: " + this.f234d);
            }
            m262k();
            m258a(C0114d.DANGLING_NAME);
            m259d(this.f239i);
            this.f239i = null;
        }
    }

    /* renamed from: b */
    public C0116f mo41b(String str) {
        if (str == null) {
            return mo46f();
        }
        m261j();
        m260e(false);
        m259d(str);
        return this;
    }

    /* renamed from: f */
    public C0116f mo46f() {
        if (this.f239i != null) {
            if (this.f240j) {
                m261j();
            } else {
                this.f239i = null;
                return this;
            }
        }
        m260e(false);
        this.f233c.write("null");
        return this;
    }

    /* renamed from: a */
    public C0116f mo38a(boolean z) {
        m261j();
        m260e(false);
        this.f233c.write(z ? "true" : "false");
        return this;
    }

    /* renamed from: a */
    public C0116f mo35a(long j) {
        m261j();
        m260e(false);
        this.f233c.write(Long.toString(j));
        return this;
    }

    /* renamed from: a */
    public C0116f mo36a(Number number) {
        if (number == null) {
            return mo46f();
        }
        m261j();
        String obj = number.toString();
        if (this.f237g || (!obj.equals("-Infinity") && !obj.equals("Infinity") && !obj.equals("NaN"))) {
            m260e(false);
            this.f233c.append(obj);
            return this;
        }
        throw new IllegalArgumentException("Numeric values must be finite, but was " + number);
    }

    public void flush() {
        if (this.f234d.isEmpty()) {
            throw new IllegalStateException("JsonWriter is closed.");
        }
        this.f233c.flush();
    }

    public void close() {
        this.f233c.close();
        int size = this.f234d.size();
        if (size > 1 || (size == 1 && this.f234d.get(size - 1) != C0114d.NONEMPTY_DOCUMENT)) {
            throw new IOException("Incomplete document");
        }
        this.f234d.clear();
    }

    /* JADX WARNING: Removed duplicated region for block: B:15:0x0031  */
    /* renamed from: d */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void m259d(java.lang.String r8) {
        /*
            r7 = this;
            r2 = 0
            boolean r0 = r7.f238h
            if (r0 == 0) goto L_0x0026
            java.lang.String[] r0 = f232b
        L_0x0007:
            java.io.Writer r1 = r7.f233c
            java.lang.String r3 = "\""
            r1.write(r3)
            int r4 = r8.length()
            r3 = r2
            r1 = r2
        L_0x0014:
            if (r3 >= r4) goto L_0x0047
            char r2 = r8.charAt(r3)
            r5 = 128(0x80, float:1.794E-43)
            if (r2 >= r5) goto L_0x0029
            r2 = r0[r2]
            if (r2 != 0) goto L_0x002f
        L_0x0022:
            int r2 = r3 + 1
            r3 = r2
            goto L_0x0014
        L_0x0026:
            java.lang.String[] r0 = f231a
            goto L_0x0007
        L_0x0029:
            r5 = 8232(0x2028, float:1.1535E-41)
            if (r2 != r5) goto L_0x0040
            java.lang.String r2 = "\\u2028"
        L_0x002f:
            if (r1 >= r3) goto L_0x0038
            java.io.Writer r5 = r7.f233c
            int r6 = r3 - r1
            r5.write(r8, r1, r6)
        L_0x0038:
            java.io.Writer r1 = r7.f233c
            r1.write(r2)
            int r1 = r3 + 1
            goto L_0x0022
        L_0x0040:
            r5 = 8233(0x2029, float:1.1537E-41)
            if (r2 != r5) goto L_0x0022
            java.lang.String r2 = "\\u2029"
            goto L_0x002f
        L_0x0047:
            if (r1 >= r4) goto L_0x0050
            java.io.Writer r0 = r7.f233c
            int r2 = r4 - r1
            r0.write(r8, r1, r2)
        L_0x0050:
            java.io.Writer r0 = r7.f233c
            java.lang.String r1 = "\""
            r0.write(r1)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.p000a.p001a.p006d.C0116f.m259d(java.lang.String):void");
    }

    /* renamed from: k */
    private void m262k() {
        if (this.f235e != null) {
            this.f233c.write("\n");
            for (int i = 1; i < this.f234d.size(); i++) {
                this.f233c.write(this.f235e);
            }
        }
    }

    /* renamed from: e */
    private void m260e(boolean z) {
        switch (C0117g.f241a[mo39a().ordinal()]) {
            case 1:
                if (!this.f237g) {
                    throw new IllegalStateException("JSON must have only one top-level value.");
                }
                break;
            case DnsMode.DNS_MODE_HTTP /*2*/:
                break;
            case 3:
                m258a(C0114d.NONEMPTY_ARRAY);
                m262k();
                return;
            case Platform.INFO /*4*/:
                this.f233c.append(',');
                m262k();
                return;
            case Platform.WARN /*5*/:
                this.f233c.append(this.f236f);
                m258a(C0114d.NONEMPTY_OBJECT);
                return;
            default:
                throw new IllegalStateException("Nesting problem: " + this.f234d);
        }
        if (this.f237g || z) {
            m258a(C0114d.NONEMPTY_DOCUMENT);
            return;
        }
        throw new IllegalStateException("JSON must start with an array or an object.");
    }
}
