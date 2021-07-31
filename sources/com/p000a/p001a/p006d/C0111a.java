package com.p000a.p001a.p006d;

import com.p000a.p001a.p003b.C0098q;
import java.io.Closeable;
import java.io.EOFException;
import java.io.IOException;
import java.io.Reader;
import neton.client.dns.DnsMode;
import neton.internal.platform.Platform;

/* renamed from: com.a.a.d.a */
/* compiled from: JsonReader */
public class C0111a implements Closeable {

    /* renamed from: a */
    private static final char[] f193a = ")]}'\n".toCharArray();

    /* renamed from: b */
    private final C0119i f194b = new C0119i();

    /* renamed from: c */
    private final Reader f195c;

    /* renamed from: d */
    private boolean f196d = false;

    /* renamed from: e */
    private final char[] f197e = new char[1024];

    /* renamed from: f */
    private int f198f = 0;

    /* renamed from: g */
    private int f199g = 0;

    /* renamed from: h */
    private int f200h = 1;

    /* renamed from: i */
    private int f201i = 1;

    /* renamed from: j */
    private C0114d[] f202j = new C0114d[32];

    /* renamed from: k */
    private int f203k = 0;
    /* access modifiers changed from: private */

    /* renamed from: l */
    public C0115e f204l;
    /* access modifiers changed from: private */

    /* renamed from: m */
    public String f205m;
    /* access modifiers changed from: private */

    /* renamed from: n */
    public String f206n;

    /* renamed from: o */
    private int f207o;

    /* renamed from: p */
    private int f208p;

    /* renamed from: q */
    private boolean f209q;

    static {
        C0098q.f166a = new C0112b();
    }

    public C0111a(Reader reader) {
        m220a(C0114d.EMPTY_DOCUMENT);
        this.f209q = false;
        this.f195c = reader;
    }

    /* renamed from: a */
    public final void mo130a(boolean z) {
        this.f196d = z;
    }

    /* renamed from: p */
    public final boolean mo131p() {
        return this.f196d;
    }

    /* renamed from: a */
    public void mo16a() {
        m221a(C0115e.BEGIN_ARRAY);
    }

    /* renamed from: b */
    public void mo17b() {
        m221a(C0115e.END_ARRAY);
    }

    /* renamed from: c */
    public void mo18c() {
        m221a(C0115e.BEGIN_OBJECT);
    }

    /* renamed from: d */
    public void mo20d() {
        m221a(C0115e.END_OBJECT);
    }

    /* renamed from: a */
    private void m221a(C0115e eVar) {
        mo22f();
        if (this.f204l != eVar) {
            throw new IllegalStateException("Expected " + eVar + " but was " + mo22f() + " at line " + m233r() + " column " + m234s());
        }
        mo31o();
    }

    /* renamed from: e */
    public boolean mo21e() {
        mo22f();
        return (this.f204l == C0115e.END_OBJECT || this.f204l == C0115e.END_ARRAY) ? false : true;
    }

    /* renamed from: f */
    public C0115e mo22f() {
        int i = 0;
        if (this.f204l != null) {
            return this.f204l;
        }
        switch (C0113c.f210a[this.f202j[this.f203k - 1].ordinal()]) {
            case 1:
                if (this.f196d) {
                    m227d(true);
                    this.f198f--;
                    if (this.f198f + f193a.length <= this.f199g || m222a(f193a.length)) {
                        while (true) {
                            if (i >= f193a.length) {
                                this.f198f += f193a.length;
                            } else if (this.f197e[this.f198f + i] == f193a[i]) {
                                i++;
                            }
                        }
                    }
                }
                this.f202j[this.f203k - 1] = C0114d.NONEMPTY_DOCUMENT;
                C0115e q = m232q();
                if (this.f196d || this.f204l == C0115e.BEGIN_ARRAY || this.f204l == C0115e.BEGIN_OBJECT) {
                    return q;
                }
                throw new IOException("Expected JSON document to start with '[' or '{' but was " + this.f204l + " at line " + m233r() + " column " + m234s());
            case DnsMode.DNS_MODE_HTTP /*2*/:
                return m224b(true);
            case 3:
                return m224b(false);
            case Platform.INFO /*4*/:
                return m226c(true);
            case Platform.WARN /*5*/:
                switch (m227d(true)) {
                    case 58:
                        break;
                    case 61:
                        m235t();
                        if ((this.f198f < this.f199g || m222a(1)) && this.f197e[this.f198f] == '>') {
                            this.f198f++;
                            break;
                        }
                    default:
                        throw m217a("Expected ':'");
                }
                this.f202j[this.f203k - 1] = C0114d.NONEMPTY_OBJECT;
                return m232q();
            case 6:
                return m226c(false);
            case 7:
                if (m227d(false) == -1) {
                    return C0115e.END_DOCUMENT;
                }
                this.f198f--;
                if (this.f196d) {
                    return m232q();
                }
                throw m217a("Expected EOF");
            case 8:
                throw new IllegalStateException("JsonReader is closed");
            default:
                throw new AssertionError();
        }
    }

    /* renamed from: o */
    private C0115e mo31o() {
        mo22f();
        C0115e eVar = this.f204l;
        this.f204l = null;
        this.f206n = null;
        this.f205m = null;
        return eVar;
    }

    /* renamed from: g */
    public String mo23g() {
        mo22f();
        if (this.f204l != C0115e.NAME) {
            throw new IllegalStateException("Expected a name but was " + mo22f() + " at line " + m233r() + " column " + m234s());
        }
        String str = this.f205m;
        mo31o();
        return str;
    }

    /* renamed from: h */
    public String mo24h() {
        mo22f();
        if (this.f204l == C0115e.STRING || this.f204l == C0115e.NUMBER) {
            String str = this.f206n;
            mo31o();
            return str;
        }
        throw new IllegalStateException("Expected a string but was " + mo22f() + " at line " + m233r() + " column " + m234s());
    }

    /* renamed from: i */
    public boolean mo25i() {
        mo22f();
        if (this.f204l != C0115e.BOOLEAN) {
            throw new IllegalStateException("Expected a boolean but was " + this.f204l + " at line " + m233r() + " column " + m234s());
        }
        boolean z = this.f206n == "true";
        mo31o();
        return z;
    }

    /* renamed from: j */
    public void mo26j() {
        mo22f();
        if (this.f204l != C0115e.NULL) {
            throw new IllegalStateException("Expected null but was " + this.f204l + " at line " + m233r() + " column " + m234s());
        }
        mo31o();
    }

    /* renamed from: k */
    public double mo27k() {
        mo22f();
        if (this.f204l == C0115e.STRING || this.f204l == C0115e.NUMBER) {
            double parseDouble = Double.parseDouble(this.f206n);
            if (parseDouble >= 1.0d && this.f206n.startsWith("0")) {
                throw new C0118h("JSON forbids octal prefixes: " + this.f206n + " at line " + m233r() + " column " + m234s());
            } else if (this.f196d || (!Double.isNaN(parseDouble) && !Double.isInfinite(parseDouble))) {
                mo31o();
                return parseDouble;
            } else {
                throw new C0118h("JSON forbids NaN and infinities: " + this.f206n + " at line " + m233r() + " column " + m234s());
            }
        } else {
            throw new IllegalStateException("Expected a double but was " + this.f204l + " at line " + m233r() + " column " + m234s());
        }
    }

    /* renamed from: l */
    public long mo28l() {
        long j;
        mo22f();
        if (this.f204l == C0115e.STRING || this.f204l == C0115e.NUMBER) {
            try {
                j = Long.parseLong(this.f206n);
            } catch (NumberFormatException e) {
                double parseDouble = Double.parseDouble(this.f206n);
                j = (long) parseDouble;
                if (((double) j) != parseDouble) {
                    throw new NumberFormatException("Expected a long but was " + this.f206n + " at line " + m233r() + " column " + m234s());
                }
            }
            if (j < 1 || !this.f206n.startsWith("0")) {
                mo31o();
                return j;
            }
            throw new C0118h("JSON forbids octal prefixes: " + this.f206n + " at line " + m233r() + " column " + m234s());
        }
        throw new IllegalStateException("Expected a long but was " + this.f204l + " at line " + m233r() + " column " + m234s());
    }

    /* renamed from: m */
    public int mo29m() {
        int i;
        mo22f();
        if (this.f204l == C0115e.STRING || this.f204l == C0115e.NUMBER) {
            try {
                i = Integer.parseInt(this.f206n);
            } catch (NumberFormatException e) {
                double parseDouble = Double.parseDouble(this.f206n);
                i = (int) parseDouble;
                if (((double) i) != parseDouble) {
                    throw new NumberFormatException("Expected an int but was " + this.f206n + " at line " + m233r() + " column " + m234s());
                }
            }
            if (((long) i) < 1 || !this.f206n.startsWith("0")) {
                mo31o();
                return i;
            }
            throw new C0118h("JSON forbids octal prefixes: " + this.f206n + " at line " + m233r() + " column " + m234s());
        }
        throw new IllegalStateException("Expected an int but was " + this.f204l + " at line " + m233r() + " column " + m234s());
    }

    public void close() {
        this.f206n = null;
        this.f204l = null;
        this.f202j[0] = C0114d.CLOSED;
        this.f203k = 1;
        this.f195c.close();
    }

    /* renamed from: n */
    public void mo30n() {
        this.f209q = true;
        int i = 0;
        do {
            try {
                C0115e o = mo31o();
                if (o == C0115e.BEGIN_ARRAY || o == C0115e.BEGIN_OBJECT) {
                    i++;
                    continue;
                } else if (o == C0115e.END_ARRAY || o == C0115e.END_OBJECT) {
                    i--;
                    continue;
                }
            } finally {
                this.f209q = false;
            }
        } while (i != 0);
    }

    /* renamed from: a */
    private void m220a(C0114d dVar) {
        if (this.f203k == this.f202j.length) {
            C0114d[] dVarArr = new C0114d[(this.f203k * 2)];
            System.arraycopy(this.f202j, 0, dVarArr, 0, this.f203k);
            this.f202j = dVarArr;
        }
        C0114d[] dVarArr2 = this.f202j;
        int i = this.f203k;
        this.f203k = i + 1;
        dVarArr2[i] = dVar;
    }

    /* renamed from: b */
    private C0115e m224b(boolean z) {
        if (z) {
            this.f202j[this.f203k - 1] = C0114d.NONEMPTY_ARRAY;
        } else {
            switch (m227d(true)) {
                case 44:
                    break;
                case 59:
                    m235t();
                    break;
                case 93:
                    this.f203k--;
                    C0115e eVar = C0115e.END_ARRAY;
                    this.f204l = eVar;
                    return eVar;
                default:
                    throw m217a("Unterminated array");
            }
        }
        switch (m227d(true)) {
            case 44:
            case 59:
                break;
            case 93:
                if (z) {
                    this.f203k--;
                    C0115e eVar2 = C0115e.END_ARRAY;
                    this.f204l = eVar2;
                    return eVar2;
                }
                break;
            default:
                this.f198f--;
                return m232q();
        }
        m235t();
        this.f198f--;
        this.f206n = "null";
        C0115e eVar3 = C0115e.NULL;
        this.f204l = eVar3;
        return eVar3;
    }

    /* renamed from: c */
    private C0115e m226c(boolean z) {
        if (z) {
            switch (m227d(true)) {
                case 125:
                    this.f203k--;
                    C0115e eVar = C0115e.END_OBJECT;
                    this.f204l = eVar;
                    return eVar;
                default:
                    this.f198f--;
                    break;
            }
        } else {
            switch (m227d(true)) {
                case 44:
                case 59:
                    break;
                case 125:
                    this.f203k--;
                    C0115e eVar2 = C0115e.END_OBJECT;
                    this.f204l = eVar2;
                    return eVar2;
                default:
                    throw m217a("Unterminated object");
            }
        }
        int d = m227d(true);
        switch (d) {
            case 34:
                break;
            case 39:
                m235t();
                break;
            default:
                m235t();
                this.f198f--;
                this.f205m = m230e(false);
                if (this.f205m.length() == 0) {
                    throw m217a("Expected name");
                }
                break;
        }
        this.f205m = m218a((char) d);
        this.f202j[this.f203k - 1] = C0114d.DANGLING_NAME;
        C0115e eVar3 = C0115e.NAME;
        this.f204l = eVar3;
        return eVar3;
    }

    /* JADX WARNING: Removed duplicated region for block: B:33:0x00bd  */
    /* renamed from: q */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private com.p000a.p001a.p006d.C0115e m232q() {
        /*
            r10 = this;
            r9 = 101(0x65, float:1.42E-43)
            r3 = 76
            r8 = 69
            r7 = 57
            r6 = 48
            r0 = 1
            int r0 = r10.m227d((boolean) r0)
            switch(r0) {
                case 34: goto L_0x0041;
                case 39: goto L_0x003e;
                case 91: goto L_0x0034;
                case 123: goto L_0x002a;
                default: goto L_0x0012;
            }
        L_0x0012:
            int r0 = r10.f198f
            int r0 = r0 + -1
            r10.f198f = r0
            r0 = 1
            java.lang.String r0 = r10.m230e((boolean) r0)
            r10.f206n = r0
            int r0 = r10.f208p
            if (r0 != 0) goto L_0x004d
            java.lang.String r0 = "Expected literal value"
            java.io.IOException r0 = r10.m217a((java.lang.String) r0)
            throw r0
        L_0x002a:
            com.a.a.d.d r0 = com.p000a.p001a.p006d.C0114d.EMPTY_OBJECT
            r10.m220a((com.p000a.p001a.p006d.C0114d) r0)
            com.a.a.d.e r0 = com.p000a.p001a.p006d.C0115e.BEGIN_OBJECT
            r10.f204l = r0
        L_0x0033:
            return r0
        L_0x0034:
            com.a.a.d.d r0 = com.p000a.p001a.p006d.C0114d.EMPTY_ARRAY
            r10.m220a((com.p000a.p001a.p006d.C0114d) r0)
            com.a.a.d.e r0 = com.p000a.p001a.p006d.C0115e.BEGIN_ARRAY
            r10.f204l = r0
            goto L_0x0033
        L_0x003e:
            r10.m235t()
        L_0x0041:
            char r0 = (char) r0
            java.lang.String r0 = r10.m218a((char) r0)
            r10.f206n = r0
            com.a.a.d.e r0 = com.p000a.p001a.p006d.C0115e.STRING
            r10.f204l = r0
            goto L_0x0033
        L_0x004d:
            int r0 = r10.f207o
            r1 = -1
            if (r0 == r1) goto L_0x0220
            int r0 = r10.f208p
            r1 = 4
            if (r0 != r1) goto L_0x00c4
            r0 = 110(0x6e, float:1.54E-43)
            char[] r1 = r10.f197e
            int r2 = r10.f207o
            char r1 = r1[r2]
            if (r0 == r1) goto L_0x006b
            r0 = 78
            char[] r1 = r10.f197e
            int r2 = r10.f207o
            char r1 = r1[r2]
            if (r0 != r1) goto L_0x00c4
        L_0x006b:
            r0 = 117(0x75, float:1.64E-43)
            char[] r1 = r10.f197e
            int r2 = r10.f207o
            int r2 = r2 + 1
            char r1 = r1[r2]
            if (r0 == r1) goto L_0x0083
            r0 = 85
            char[] r1 = r10.f197e
            int r2 = r10.f207o
            int r2 = r2 + 1
            char r1 = r1[r2]
            if (r0 != r1) goto L_0x00c4
        L_0x0083:
            r0 = 108(0x6c, float:1.51E-43)
            char[] r1 = r10.f197e
            int r2 = r10.f207o
            int r2 = r2 + 2
            char r1 = r1[r2]
            if (r0 == r1) goto L_0x0099
            char[] r0 = r10.f197e
            int r1 = r10.f207o
            int r1 = r1 + 2
            char r0 = r0[r1]
            if (r3 != r0) goto L_0x00c4
        L_0x0099:
            r0 = 108(0x6c, float:1.51E-43)
            char[] r1 = r10.f197e
            int r2 = r10.f207o
            int r2 = r2 + 3
            char r1 = r1[r2]
            if (r0 == r1) goto L_0x00af
            char[] r0 = r10.f197e
            int r1 = r10.f207o
            int r1 = r1 + 3
            char r0 = r0[r1]
            if (r3 != r0) goto L_0x00c4
        L_0x00af:
            java.lang.String r0 = "null"
            r10.f206n = r0
            com.a.a.d.e r0 = com.p000a.p001a.p006d.C0115e.NULL
        L_0x00b5:
            r10.f204l = r0
            com.a.a.d.e r0 = r10.f204l
            com.a.a.d.e r1 = com.p000a.p001a.p006d.C0115e.STRING
            if (r0 != r1) goto L_0x00c0
            r10.m235t()
        L_0x00c0:
            com.a.a.d.e r0 = r10.f204l
            goto L_0x0033
        L_0x00c4:
            int r0 = r10.f208p
            r1 = 4
            if (r0 != r1) goto L_0x0128
            r0 = 116(0x74, float:1.63E-43)
            char[] r1 = r10.f197e
            int r2 = r10.f207o
            char r1 = r1[r2]
            if (r0 == r1) goto L_0x00dd
            r0 = 84
            char[] r1 = r10.f197e
            int r2 = r10.f207o
            char r1 = r1[r2]
            if (r0 != r1) goto L_0x0128
        L_0x00dd:
            r0 = 114(0x72, float:1.6E-43)
            char[] r1 = r10.f197e
            int r2 = r10.f207o
            int r2 = r2 + 1
            char r1 = r1[r2]
            if (r0 == r1) goto L_0x00f5
            r0 = 82
            char[] r1 = r10.f197e
            int r2 = r10.f207o
            int r2 = r2 + 1
            char r1 = r1[r2]
            if (r0 != r1) goto L_0x0128
        L_0x00f5:
            r0 = 117(0x75, float:1.64E-43)
            char[] r1 = r10.f197e
            int r2 = r10.f207o
            int r2 = r2 + 2
            char r1 = r1[r2]
            if (r0 == r1) goto L_0x010d
            r0 = 85
            char[] r1 = r10.f197e
            int r2 = r10.f207o
            int r2 = r2 + 2
            char r1 = r1[r2]
            if (r0 != r1) goto L_0x0128
        L_0x010d:
            char[] r0 = r10.f197e
            int r1 = r10.f207o
            int r1 = r1 + 3
            char r0 = r0[r1]
            if (r9 == r0) goto L_0x0121
            char[] r0 = r10.f197e
            int r1 = r10.f207o
            int r1 = r1 + 3
            char r0 = r0[r1]
            if (r8 != r0) goto L_0x0128
        L_0x0121:
            java.lang.String r0 = "true"
            r10.f206n = r0
            com.a.a.d.e r0 = com.p000a.p001a.p006d.C0115e.BOOLEAN
            goto L_0x00b5
        L_0x0128:
            int r0 = r10.f208p
            r1 = 5
            if (r0 != r1) goto L_0x01a3
            r0 = 102(0x66, float:1.43E-43)
            char[] r1 = r10.f197e
            int r2 = r10.f207o
            char r1 = r1[r2]
            if (r0 == r1) goto L_0x0141
            r0 = 70
            char[] r1 = r10.f197e
            int r2 = r10.f207o
            char r1 = r1[r2]
            if (r0 != r1) goto L_0x01a3
        L_0x0141:
            r0 = 97
            char[] r1 = r10.f197e
            int r2 = r10.f207o
            int r2 = r2 + 1
            char r1 = r1[r2]
            if (r0 == r1) goto L_0x0159
            r0 = 65
            char[] r1 = r10.f197e
            int r2 = r10.f207o
            int r2 = r2 + 1
            char r1 = r1[r2]
            if (r0 != r1) goto L_0x01a3
        L_0x0159:
            r0 = 108(0x6c, float:1.51E-43)
            char[] r1 = r10.f197e
            int r2 = r10.f207o
            int r2 = r2 + 2
            char r1 = r1[r2]
            if (r0 == r1) goto L_0x016f
            char[] r0 = r10.f197e
            int r1 = r10.f207o
            int r1 = r1 + 2
            char r0 = r0[r1]
            if (r3 != r0) goto L_0x01a3
        L_0x016f:
            r0 = 115(0x73, float:1.61E-43)
            char[] r1 = r10.f197e
            int r2 = r10.f207o
            int r2 = r2 + 3
            char r1 = r1[r2]
            if (r0 == r1) goto L_0x0187
            r0 = 83
            char[] r1 = r10.f197e
            int r2 = r10.f207o
            int r2 = r2 + 3
            char r1 = r1[r2]
            if (r0 != r1) goto L_0x01a3
        L_0x0187:
            char[] r0 = r10.f197e
            int r1 = r10.f207o
            int r1 = r1 + 4
            char r0 = r0[r1]
            if (r9 == r0) goto L_0x019b
            char[] r0 = r10.f197e
            int r1 = r10.f207o
            int r1 = r1 + 4
            char r0 = r0[r1]
            if (r8 != r0) goto L_0x01a3
        L_0x019b:
            java.lang.String r0 = "false"
            r10.f206n = r0
            com.a.a.d.e r0 = com.p000a.p001a.p006d.C0115e.BOOLEAN
            goto L_0x00b5
        L_0x01a3:
            com.a.a.d.i r0 = r10.f194b
            char[] r1 = r10.f197e
            int r2 = r10.f207o
            int r3 = r10.f208p
            java.lang.String r0 = r0.mo139a(r1, r2, r3)
            r10.f206n = r0
            char[] r3 = r10.f197e
            int r2 = r10.f207o
            int r4 = r10.f208p
            char r0 = r3[r2]
            r1 = 45
            if (r0 != r1) goto L_0x0224
            int r1 = r2 + 1
            char r0 = r3[r1]
        L_0x01c1:
            if (r0 != r6) goto L_0x01d8
            int r0 = r1 + 1
            char r1 = r3[r0]
        L_0x01c7:
            r5 = 46
            if (r1 != r5) goto L_0x01ef
            int r0 = r0 + 1
            char r1 = r3[r0]
        L_0x01cf:
            if (r1 < r6) goto L_0x01ef
            if (r1 > r7) goto L_0x01ef
            int r0 = r0 + 1
            char r1 = r3[r0]
            goto L_0x01cf
        L_0x01d8:
            r5 = 49
            if (r0 < r5) goto L_0x01eb
            if (r0 > r7) goto L_0x01eb
            int r0 = r1 + 1
            char r1 = r3[r0]
        L_0x01e2:
            if (r1 < r6) goto L_0x01c7
            if (r1 > r7) goto L_0x01c7
            int r0 = r0 + 1
            char r1 = r3[r0]
            goto L_0x01e2
        L_0x01eb:
            com.a.a.d.e r0 = com.p000a.p001a.p006d.C0115e.STRING
            goto L_0x00b5
        L_0x01ef:
            if (r1 == r9) goto L_0x01f3
            if (r1 != r8) goto L_0x0218
        L_0x01f3:
            int r1 = r0 + 1
            char r0 = r3[r1]
            r5 = 43
            if (r0 == r5) goto L_0x01ff
            r5 = 45
            if (r0 != r5) goto L_0x0203
        L_0x01ff:
            int r1 = r1 + 1
            char r0 = r3[r1]
        L_0x0203:
            if (r0 < r6) goto L_0x0214
            if (r0 > r7) goto L_0x0214
            int r0 = r1 + 1
            char r1 = r3[r0]
        L_0x020b:
            if (r1 < r6) goto L_0x0218
            if (r1 > r7) goto L_0x0218
            int r0 = r0 + 1
            char r1 = r3[r0]
            goto L_0x020b
        L_0x0214:
            com.a.a.d.e r0 = com.p000a.p001a.p006d.C0115e.STRING
            goto L_0x00b5
        L_0x0218:
            int r1 = r2 + r4
            if (r0 != r1) goto L_0x0220
            com.a.a.d.e r0 = com.p000a.p001a.p006d.C0115e.NUMBER
            goto L_0x00b5
        L_0x0220:
            com.a.a.d.e r0 = com.p000a.p001a.p006d.C0115e.STRING
            goto L_0x00b5
        L_0x0224:
            r1 = r2
            goto L_0x01c1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.p000a.p001a.p006d.C0111a.m232q():com.a.a.d.e");
    }

    /* renamed from: a */
    private boolean m222a(int i) {
        char[] cArr = this.f197e;
        int i2 = this.f200h;
        int i3 = this.f201i;
        int i4 = this.f198f;
        for (int i5 = 0; i5 < i4; i5++) {
            if (cArr[i5] == 10) {
                i2++;
                i3 = 1;
            } else {
                i3++;
            }
        }
        this.f200h = i2;
        this.f201i = i3;
        if (this.f199g != this.f198f) {
            this.f199g -= this.f198f;
            System.arraycopy(cArr, this.f198f, cArr, 0, this.f199g);
        } else {
            this.f199g = 0;
        }
        this.f198f = 0;
        do {
            int read = this.f195c.read(cArr, this.f199g, cArr.length - this.f199g);
            if (read == -1) {
                return false;
            }
            this.f199g = read + this.f199g;
            if (this.f200h == 1 && this.f201i == 1 && this.f199g > 0 && cArr[0] == 65279) {
                this.f198f++;
                this.f201i--;
            }
        } while (this.f199g < i);
        return true;
    }

    /* access modifiers changed from: private */
    /* renamed from: r */
    public int m233r() {
        int i = this.f200h;
        for (int i2 = 0; i2 < this.f198f; i2++) {
            if (this.f197e[i2] == 10) {
                i++;
            }
        }
        return i;
    }

    /* access modifiers changed from: private */
    /* renamed from: s */
    public int m234s() {
        int i = this.f201i;
        for (int i2 = 0; i2 < this.f198f; i2++) {
            if (this.f197e[i2] == 10) {
                i = 1;
            } else {
                i++;
            }
        }
        return i;
    }

    /* renamed from: d */
    private int m227d(boolean z) {
        boolean z2;
        char[] cArr = this.f197e;
        int i = this.f198f;
        int i2 = this.f199g;
        while (true) {
            if (i == i2) {
                this.f198f = i;
                if (m222a(1)) {
                    i = this.f198f;
                    i2 = this.f199g;
                } else if (!z) {
                    return -1;
                } else {
                    throw new EOFException("End of input at line " + m233r() + " column " + m234s());
                }
            }
            int i3 = i + 1;
            char c = cArr[i];
            switch (c) {
                case 9:
                case 10:
                case 13:
                case ' ':
                    i = i3;
                    break;
                case '#':
                    this.f198f = i3;
                    m235t();
                    m236u();
                    i = this.f198f;
                    i2 = this.f199g;
                    break;
                case '/':
                    this.f198f = i3;
                    if (i3 == i2) {
                        this.f198f--;
                        boolean a = m222a(2);
                        this.f198f++;
                        if (!a) {
                            return c;
                        }
                    }
                    m235t();
                    switch (cArr[this.f198f]) {
                        case '*':
                            this.f198f++;
                            while (true) {
                                if (this.f198f + "*/".length() <= this.f199g || m222a("*/".length())) {
                                    int i4 = 0;
                                    while (i4 < "*/".length()) {
                                        if (this.f197e[this.f198f + i4] == "*/".charAt(i4)) {
                                            i4++;
                                        } else {
                                            this.f198f++;
                                        }
                                    }
                                    z2 = true;
                                } else {
                                    z2 = false;
                                }
                            }
                            if (z2) {
                                i = this.f198f + 2;
                                i2 = this.f199g;
                                break;
                            } else {
                                throw m217a("Unterminated comment");
                            }
                        case '/':
                            this.f198f++;
                            m236u();
                            i = this.f198f;
                            i2 = this.f199g;
                            break;
                        default:
                            return c;
                    }
                default:
                    this.f198f = i3;
                    return c;
            }
        }
    }

    /* renamed from: t */
    private void m235t() {
        if (!this.f196d) {
            throw m217a("Use JsonReader.setLenient(true) to accept malformed JSON");
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:4:0x000d  */
    /* renamed from: u */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void m236u() {
        /*
            r3 = this;
        L_0x0000:
            int r0 = r3.f198f
            int r1 = r3.f199g
            if (r0 < r1) goto L_0x000d
            r0 = 1
            boolean r0 = r3.m222a((int) r0)
            if (r0 == 0) goto L_0x001f
        L_0x000d:
            char[] r0 = r3.f197e
            int r1 = r3.f198f
            int r2 = r1 + 1
            r3.f198f = r2
            char r0 = r0[r1]
            r1 = 13
            if (r0 == r1) goto L_0x001f
            r1 = 10
            if (r0 != r1) goto L_0x0000
        L_0x001f:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.p000a.p001a.p006d.C0111a.m236u():void");
    }

    /* renamed from: a */
    private String m218a(char c) {
        int i;
        char[] cArr = this.f197e;
        StringBuilder sb = null;
        do {
            int i2 = this.f198f;
            int i3 = this.f199g;
            int i4 = i2;
            int i5 = i2;
            while (i5 < i3) {
                int i6 = i5 + 1;
                char c2 = cArr[i5];
                if (c2 == c) {
                    this.f198f = i6;
                    if (this.f209q) {
                        return "skipped!";
                    }
                    if (sb == null) {
                        return this.f194b.mo139a(cArr, i4, (i6 - i4) - 1);
                    }
                    sb.append(cArr, i4, (i6 - i4) - 1);
                    return sb.toString();
                }
                if (c2 == '\\') {
                    this.f198f = i6;
                    if (sb == null) {
                        sb = new StringBuilder();
                    }
                    sb.append(cArr, i4, (i6 - i4) - 1);
                    sb.append(m237v());
                    int i7 = this.f198f;
                    i = this.f199g;
                    i4 = i7;
                    i6 = i7;
                } else {
                    i = i3;
                }
                i3 = i;
                i5 = i6;
            }
            if (sb == null) {
                sb = new StringBuilder();
            }
            sb.append(cArr, i4, i5 - i4);
            this.f198f = i5;
        } while (m222a(1));
        throw m217a("Unterminated string");
    }

    /* renamed from: e */
    private String m230e(boolean z) {
        String str = null;
        this.f207o = -1;
        this.f208p = 0;
        int i = 0;
        StringBuilder sb = null;
        while (true) {
            if (this.f198f + i < this.f199g) {
                switch (this.f197e[this.f198f + i]) {
                    case 9:
                    case 10:
                    case 12:
                    case 13:
                    case ' ':
                    case ',':
                    case ':':
                    case '[':
                    case ']':
                    case '{':
                    case '}':
                        break;
                    case '#':
                    case '/':
                    case ';':
                    case '=':
                    case '\\':
                        m235t();
                        break;
                    default:
                        i++;
                        continue;
                }
            } else if (i >= this.f197e.length) {
                if (sb == null) {
                    sb = new StringBuilder();
                }
                sb.append(this.f197e, this.f198f, i);
                this.f208p += i;
                this.f198f = i + this.f198f;
                if (!m222a(1)) {
                    i = 0;
                } else {
                    i = 0;
                }
            } else if (!m222a(i + 1)) {
                this.f197e[this.f199g] = 0;
            }
        }
        if (z && sb == null) {
            this.f207o = this.f198f;
        } else if (this.f209q) {
            str = "skipped!";
        } else if (sb == null) {
            str = this.f194b.mo139a(this.f197e, this.f198f, i);
        } else {
            sb.append(this.f197e, this.f198f, i);
            str = sb.toString();
        }
        this.f208p += i;
        this.f198f += i;
        return str;
    }

    public String toString() {
        return getClass().getSimpleName() + " at line " + m233r() + " column " + m234s();
    }

    /* renamed from: v */
    private char m237v() {
        int i;
        if (this.f198f != this.f199g || m222a(1)) {
            char[] cArr = this.f197e;
            int i2 = this.f198f;
            this.f198f = i2 + 1;
            char c = cArr[i2];
            switch (c) {
                case 'b':
                    return 8;
                case 'f':
                    return 12;
                case 'n':
                    return 10;
                case 'r':
                    return 13;
                case 't':
                    return 9;
                case 'u':
                    if (this.f198f + 4 <= this.f199g || m222a(4)) {
                        char c2 = 0;
                        int i3 = this.f198f;
                        int i4 = i3 + 4;
                        while (i3 < i4) {
                            char c3 = this.f197e[i3];
                            char c4 = (char) (c2 << 4);
                            if (c3 >= '0' && c3 <= '9') {
                                i = c3 - '0';
                            } else if (c3 >= 'a' && c3 <= 'f') {
                                i = (c3 - 'a') + 10;
                            } else if (c3 < 'A' || c3 > 'F') {
                                throw new NumberFormatException("\\u" + this.f194b.mo139a(this.f197e, this.f198f, 4));
                            } else {
                                i = (c3 - 'A') + 10;
                            }
                            c2 = (char) (c4 + i);
                            i3++;
                        }
                        this.f198f += 4;
                        return c2;
                    }
                    throw m217a("Unterminated escape sequence");
                default:
                    return c;
            }
        } else {
            throw m217a("Unterminated escape sequence");
        }
    }

    /* renamed from: a */
    private IOException m217a(String str) {
        throw new C0118h(str + " at line " + m233r() + " column " + m234s());
    }
}
