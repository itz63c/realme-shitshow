package neton.internal.publicsuffix;

import com.coloros.neton.NetonException;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.InterruptedIOException;
import java.net.IDN;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicBoolean;
import neton.internal.Util;
import neton.internal.platform.Platform;
import okio.BufferedSource;
import okio.GzipSource;
import okio.Okio;
import okio.Source;

public final class PublicSuffixDatabase {
    private static final String[] EMPTY_RULE = new String[0];
    private static final byte EXCEPTION_MARKER = 33;
    private static final String[] PREVAILING_RULE = {"*"};
    public static final String PUBLIC_SUFFIX_RESOURCE = "publicsuffixes.gz";
    private static final byte[] WILDCARD_LABEL = {42};
    private static final PublicSuffixDatabase instance = new PublicSuffixDatabase();
    private final AtomicBoolean listRead = new AtomicBoolean(false);
    private byte[] publicSuffixExceptionListBytes;
    private byte[] publicSuffixListBytes;
    private final CountDownLatch readCompleteLatch = new CountDownLatch(1);

    public static PublicSuffixDatabase get() {
        return instance;
    }

    public final String getEffectiveTldPlusOne(String str) {
        int length;
        if (str == null) {
            throw new NetonException((Throwable) new NullPointerException("domain == null"));
        }
        String[] split = IDN.toUnicode(str).split("\\.");
        String[] findMatchingRule = findMatchingRule(split);
        if (split.length == findMatchingRule.length && findMatchingRule[0].charAt(0) != '!') {
            return null;
        }
        if (findMatchingRule[0].charAt(0) == '!') {
            length = split.length - findMatchingRule.length;
        } else {
            length = split.length - (findMatchingRule.length + 1);
        }
        StringBuilder sb = new StringBuilder();
        String[] split2 = str.split("\\.");
        while (length < split2.length) {
            sb.append(split2[length]).append('.');
            length++;
        }
        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }

    /* JADX WARNING: Removed duplicated region for block: B:37:0x0072 A[LOOP:3: B:37:0x0072->B:46:0x009e, LOOP_START, PHI: r1 
      PHI: (r1v6 int) = (r1v0 int), (r1v7 int) binds: [B:36:0x0070, B:46:0x009e] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARNING: Removed duplicated region for block: B:43:0x0082  */
    /* JADX WARNING: Removed duplicated region for block: B:47:0x00a1  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private java.lang.String[] findMatchingRule(java.lang.String[] r8) {
        /*
            r7 = this;
            r5 = 1
            r3 = 0
            r1 = 0
            java.util.concurrent.atomic.AtomicBoolean r0 = r7.listRead
            boolean r0 = r0.get()
            if (r0 != 0) goto L_0x002b
            java.util.concurrent.atomic.AtomicBoolean r0 = r7.listRead
            boolean r0 = r0.compareAndSet(r1, r5)
            if (r0 == 0) goto L_0x002b
            r7.readTheListUninterruptibly()
        L_0x0016:
            monitor-enter(r7)
            byte[] r0 = r7.publicSuffixListBytes     // Catch:{ all -> 0x0028 }
            if (r0 != 0) goto L_0x0033
            com.coloros.neton.NetonException r0 = new com.coloros.neton.NetonException     // Catch:{ all -> 0x0028 }
            java.lang.IllegalStateException r1 = new java.lang.IllegalStateException     // Catch:{ all -> 0x0028 }
            java.lang.String r2 = "Unable to load publicsuffixes.gz resource from the classpath."
            r1.<init>(r2)     // Catch:{ all -> 0x0028 }
            r0.<init>((java.lang.Throwable) r1)     // Catch:{ all -> 0x0028 }
            throw r0     // Catch:{ all -> 0x0028 }
        L_0x0028:
            r0 = move-exception
            monitor-exit(r7)     // Catch:{ all -> 0x0028 }
            throw r0
        L_0x002b:
            java.util.concurrent.CountDownLatch r0 = r7.readCompleteLatch     // Catch:{ InterruptedException -> 0x0031 }
            r0.await()     // Catch:{ InterruptedException -> 0x0031 }
            goto L_0x0016
        L_0x0031:
            r0 = move-exception
            goto L_0x0016
        L_0x0033:
            monitor-exit(r7)     // Catch:{ all -> 0x0028 }
            int r0 = r8.length
            byte[][] r6 = new byte[r0][]
            r0 = r1
        L_0x0038:
            int r2 = r8.length
            if (r0 >= r2) goto L_0x0048
            r2 = r8[r0]
            java.nio.charset.Charset r4 = neton.internal.Util.UTF_8
            byte[] r2 = r2.getBytes(r4)
            r6[r0] = r2
            int r0 = r0 + 1
            goto L_0x0038
        L_0x0048:
            r0 = r1
        L_0x0049:
            int r2 = r6.length
            if (r0 >= r2) goto L_0x00c6
            byte[] r2 = r7.publicSuffixListBytes
            java.lang.String r2 = binarySearchBytes(r2, r6, r0)
            if (r2 == 0) goto L_0x0098
        L_0x0054:
            int r0 = r6.length
            if (r0 <= r5) goto L_0x00c4
            java.lang.Object r0 = r6.clone()
            byte[][] r0 = (byte[][]) r0
            r4 = r1
        L_0x005e:
            int r5 = r0.length
            int r5 = r5 + -1
            if (r4 >= r5) goto L_0x00c4
            byte[] r5 = WILDCARD_LABEL
            r0[r4] = r5
            byte[] r5 = r7.publicSuffixListBytes
            java.lang.String r5 = binarySearchBytes(r5, r0, r4)
            if (r5 == 0) goto L_0x009b
            r4 = r5
        L_0x0070:
            if (r4 == 0) goto L_0x0080
        L_0x0072:
            int r0 = r6.length
            int r0 = r0 + -1
            if (r1 >= r0) goto L_0x0080
            byte[] r0 = r7.publicSuffixExceptionListBytes
            java.lang.String r0 = binarySearchBytes(r0, r6, r1)
            if (r0 == 0) goto L_0x009e
            r3 = r0
        L_0x0080:
            if (r3 == 0) goto L_0x00a1
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r1 = "!"
            r0.<init>(r1)
            java.lang.StringBuilder r0 = r0.append(r3)
            java.lang.String r0 = r0.toString()
            java.lang.String r1 = "\\."
            java.lang.String[] r0 = r0.split(r1)
        L_0x0097:
            return r0
        L_0x0098:
            int r0 = r0 + 1
            goto L_0x0049
        L_0x009b:
            int r4 = r4 + 1
            goto L_0x005e
        L_0x009e:
            int r1 = r1 + 1
            goto L_0x0072
        L_0x00a1:
            if (r2 != 0) goto L_0x00a8
            if (r4 != 0) goto L_0x00a8
            java.lang.String[] r0 = PREVAILING_RULE
            goto L_0x0097
        L_0x00a8:
            if (r2 == 0) goto L_0x00be
            java.lang.String r0 = "\\."
            java.lang.String[] r1 = r2.split(r0)
        L_0x00b0:
            if (r4 == 0) goto L_0x00c1
            java.lang.String r0 = "\\."
            java.lang.String[] r0 = r4.split(r0)
        L_0x00b8:
            int r2 = r1.length
            int r3 = r0.length
            if (r2 <= r3) goto L_0x0097
            r0 = r1
            goto L_0x0097
        L_0x00be:
            java.lang.String[] r1 = EMPTY_RULE
            goto L_0x00b0
        L_0x00c1:
            java.lang.String[] r0 = EMPTY_RULE
            goto L_0x00b8
        L_0x00c4:
            r4 = r3
            goto L_0x0070
        L_0x00c6:
            r2 = r3
            goto L_0x0054
        */
        throw new UnsupportedOperationException("Method not decompiled: neton.internal.publicsuffix.PublicSuffixDatabase.findMatchingRule(java.lang.String[]):java.lang.String[]");
    }

    private static String binarySearchBytes(byte[] bArr, byte[][] bArr2, int i) {
        byte b;
        int i2;
        int i3;
        int i4;
        int length = bArr.length;
        int i5 = 0;
        while (i5 < length) {
            int i6 = (i5 + length) / 2;
            while (i6 >= 0 && bArr[i6] != 10) {
                i6--;
            }
            int i7 = i6 + 1;
            int i8 = 1;
            while (bArr[i7 + i8] != 10) {
                i8++;
            }
            int i9 = (i7 + i8) - i7;
            int i10 = 0;
            int i11 = 0;
            boolean z = false;
            int i12 = i;
            while (true) {
                if (z) {
                    b = 46;
                    z = false;
                } else {
                    b = bArr2[i12][i10] & 255;
                }
                i2 = b - (bArr[i7 + i11] & 255);
                if (i2 != 0) {
                    i3 = i11;
                    i4 = i10;
                    break;
                }
                i11++;
                i4 = i10 + 1;
                if (i11 == i9) {
                    break;
                }
                if (bArr2[i12].length == i4) {
                    if (i12 == bArr2.length - 1) {
                        break;
                    }
                    i12++;
                    i4 = -1;
                    z = true;
                }
                i10 = i4;
            }
            i3 = i11;
            if (i2 < 0) {
                length = i7 - 1;
            } else if (i2 > 0) {
                i5 = i8 + i7 + 1;
            } else {
                int i13 = i9 - i3;
                int length2 = bArr2[i12].length - i4;
                for (int i14 = i12 + 1; i14 < bArr2.length; i14++) {
                    length2 += bArr2[i14].length;
                }
                if (length2 < i13) {
                    length = i7 - 1;
                } else if (length2 <= i13) {
                    return new String(bArr, i7, i9, Util.UTF_8);
                } else {
                    i5 = i8 + i7 + 1;
                }
            }
        }
        return null;
    }

    private void readTheListUninterruptibly() {
        boolean z;
        boolean z2 = false;
        while (true) {
            try {
                z = z2;
                readTheList();
                break;
            } catch (InterruptedIOException e) {
                z2 = true;
            } catch (IOException e2) {
                Platform.get().log(5, "Failed to read public suffix list", e2);
                if (z) {
                    Thread.currentThread().interrupt();
                    return;
                }
                return;
            } catch (Throwable th) {
                if (z) {
                    Thread.currentThread().interrupt();
                }
                throw th;
            }
        }
        if (z) {
            Thread.currentThread().interrupt();
        }
    }

    private void readTheList() {
        InputStream resourceAsStream = PublicSuffixDatabase.class.getResourceAsStream(PUBLIC_SUFFIX_RESOURCE);
        if (resourceAsStream != null) {
            BufferedSource buffer = Okio.buffer((Source) new GzipSource(Okio.source(resourceAsStream)));
            try {
                byte[] bArr = new byte[buffer.readInt()];
                buffer.readFully(bArr);
                byte[] bArr2 = new byte[buffer.readInt()];
                buffer.readFully(bArr2);
                synchronized (this) {
                    this.publicSuffixListBytes = bArr;
                    this.publicSuffixExceptionListBytes = bArr2;
                }
                this.readCompleteLatch.countDown();
            } finally {
                Util.closeQuietly((Closeable) buffer);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public final void setListBytes(byte[] bArr, byte[] bArr2) {
        this.publicSuffixListBytes = bArr;
        this.publicSuffixExceptionListBytes = bArr2;
        this.listRead.set(true);
        this.readCompleteLatch.countDown();
    }
}
