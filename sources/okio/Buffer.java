package okio;

import com.coloros.neton.BuildConfig;
import java.io.EOFException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.annotation.Nullable;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

public final class Buffer implements Cloneable, BufferedSink, BufferedSource {
    private static final byte[] DIGITS = {48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 97, 98, 99, 100, 101, 102};
    static final int REPLACEMENT_CHARACTER = 65533;
    @Nullable
    Segment head;
    long size;

    public final long size() {
        return this.size;
    }

    public final Buffer buffer() {
        return this;
    }

    public final OutputStream outputStream() {
        return new OutputStream() {
            public void write(int i) {
                Buffer.this.writeByte((int) (byte) i);
            }

            public void write(byte[] bArr, int i, int i2) {
                Buffer.this.write(bArr, i, i2);
            }

            public void flush() {
            }

            public void close() {
            }

            public String toString() {
                return Buffer.this + ".outputStream()";
            }
        };
    }

    public final Buffer emitCompleteSegments() {
        return this;
    }

    public final BufferedSink emit() {
        return this;
    }

    public final boolean exhausted() {
        return this.size == 0;
    }

    public final void require(long j) {
        if (this.size < j) {
            throw new EOFException();
        }
    }

    public final boolean request(long j) {
        return this.size >= j;
    }

    public final InputStream inputStream() {
        return new InputStream() {
            public int read() {
                if (Buffer.this.size > 0) {
                    return Buffer.this.readByte() & 255;
                }
                return -1;
            }

            public int read(byte[] bArr, int i, int i2) {
                return Buffer.this.read(bArr, i, i2);
            }

            public int available() {
                return (int) Math.min(Buffer.this.size, 2147483647L);
            }

            public void close() {
            }

            public String toString() {
                return Buffer.this + ".inputStream()";
            }
        };
    }

    public final Buffer copyTo(OutputStream outputStream) {
        return copyTo(outputStream, 0, this.size);
    }

    public final Buffer copyTo(OutputStream outputStream, long j, long j2) {
        if (outputStream == null) {
            throw new IllegalArgumentException("out == null");
        }
        Util.checkOffsetAndCount(this.size, j, j2);
        if (j2 != 0) {
            Segment segment = this.head;
            while (j >= ((long) (segment.limit - segment.pos))) {
                j -= (long) (segment.limit - segment.pos);
                segment = segment.next;
            }
            while (j2 > 0) {
                int i = (int) (((long) segment.pos) + j);
                int min = (int) Math.min((long) (segment.limit - i), j2);
                outputStream.write(segment.data, i, min);
                j2 -= (long) min;
                segment = segment.next;
                j = 0;
            }
        }
        return this;
    }

    public final Buffer copyTo(Buffer buffer, long j, long j2) {
        if (buffer == null) {
            throw new IllegalArgumentException("out == null");
        }
        Util.checkOffsetAndCount(this.size, j, j2);
        if (j2 != 0) {
            buffer.size += j2;
            Segment segment = this.head;
            while (j >= ((long) (segment.limit - segment.pos))) {
                j -= (long) (segment.limit - segment.pos);
                segment = segment.next;
            }
            while (j2 > 0) {
                Segment segment2 = new Segment(segment);
                segment2.pos = (int) (((long) segment2.pos) + j);
                segment2.limit = Math.min(segment2.pos + ((int) j2), segment2.limit);
                if (buffer.head == null) {
                    segment2.prev = segment2;
                    segment2.next = segment2;
                    buffer.head = segment2;
                } else {
                    buffer.head.prev.push(segment2);
                }
                j2 -= (long) (segment2.limit - segment2.pos);
                segment = segment.next;
                j = 0;
            }
        }
        return this;
    }

    public final Buffer writeTo(OutputStream outputStream) {
        return writeTo(outputStream, this.size);
    }

    public final Buffer writeTo(OutputStream outputStream, long j) {
        Segment segment;
        if (outputStream == null) {
            throw new IllegalArgumentException("out == null");
        }
        Util.checkOffsetAndCount(this.size, 0, j);
        Segment segment2 = this.head;
        while (j > 0) {
            int min = (int) Math.min(j, (long) (segment2.limit - segment2.pos));
            outputStream.write(segment2.data, segment2.pos, min);
            segment2.pos += min;
            this.size -= (long) min;
            j -= (long) min;
            if (segment2.pos == segment2.limit) {
                segment = segment2.pop();
                this.head = segment;
                SegmentPool.recycle(segment2);
            } else {
                segment = segment2;
            }
            segment2 = segment;
        }
        return this;
    }

    public final Buffer readFrom(InputStream inputStream) {
        readFrom(inputStream, Long.MAX_VALUE, true);
        return this;
    }

    public final Buffer readFrom(InputStream inputStream, long j) {
        if (j < 0) {
            throw new IllegalArgumentException("byteCount < 0: " + j);
        }
        readFrom(inputStream, j, false);
        return this;
    }

    private void readFrom(InputStream inputStream, long j, boolean z) {
        if (inputStream == null) {
            throw new IllegalArgumentException("in == null");
        }
        while (true) {
            if (j > 0 || z) {
                Segment writableSegment = writableSegment(1);
                int read = inputStream.read(writableSegment.data, writableSegment.limit, (int) Math.min(j, (long) (8192 - writableSegment.limit)));
                if (read != -1) {
                    writableSegment.limit += read;
                    this.size += (long) read;
                    j -= (long) read;
                } else if (!z) {
                    throw new EOFException();
                } else {
                    return;
                }
            } else {
                return;
            }
        }
    }

    public final long completeSegmentByteCount() {
        long j = this.size;
        if (j == 0) {
            return 0;
        }
        Segment segment = this.head.prev;
        if (segment.limit >= 8192 || !segment.owner) {
            return j;
        }
        return j - ((long) (segment.limit - segment.pos));
    }

    public final byte readByte() {
        if (this.size == 0) {
            throw new IllegalStateException("size == 0");
        }
        Segment segment = this.head;
        int i = segment.pos;
        int i2 = segment.limit;
        int i3 = i + 1;
        byte b = segment.data[i];
        this.size--;
        if (i3 == i2) {
            this.head = segment.pop();
            SegmentPool.recycle(segment);
        } else {
            segment.pos = i3;
        }
        return b;
    }

    public final byte getByte(long j) {
        Util.checkOffsetAndCount(this.size, j, 1);
        Segment segment = this.head;
        while (true) {
            int i = segment.limit - segment.pos;
            if (j < ((long) i)) {
                return segment.data[segment.pos + ((int) j)];
            }
            j -= (long) i;
            segment = segment.next;
        }
    }

    public final short readShort() {
        if (this.size < 2) {
            throw new IllegalStateException("size < 2: " + this.size);
        }
        Segment segment = this.head;
        int i = segment.pos;
        int i2 = segment.limit;
        if (i2 - i < 2) {
            return (short) (((readByte() & 255) << 8) | (readByte() & 255));
        }
        byte[] bArr = segment.data;
        int i3 = i + 1;
        int i4 = i3 + 1;
        byte b = ((bArr[i] & 255) << 8) | (bArr[i3] & 255);
        this.size -= 2;
        if (i4 == i2) {
            this.head = segment.pop();
            SegmentPool.recycle(segment);
        } else {
            segment.pos = i4;
        }
        return (short) b;
    }

    public final int readInt() {
        if (this.size < 4) {
            throw new IllegalStateException("size < 4: " + this.size);
        }
        Segment segment = this.head;
        int i = segment.pos;
        int i2 = segment.limit;
        if (i2 - i < 4) {
            return ((readByte() & 255) << 24) | ((readByte() & 255) << 16) | ((readByte() & 255) << 8) | (readByte() & 255);
        }
        byte[] bArr = segment.data;
        int i3 = i + 1;
        int i4 = i3 + 1;
        byte b = ((bArr[i] & 255) << 24) | ((bArr[i3] & 255) << 16);
        int i5 = i4 + 1;
        byte b2 = b | ((bArr[i4] & 255) << 8);
        int i6 = i5 + 1;
        byte b3 = b2 | (bArr[i5] & 255);
        this.size -= 4;
        if (i6 == i2) {
            this.head = segment.pop();
            SegmentPool.recycle(segment);
            return b3;
        }
        segment.pos = i6;
        return b3;
    }

    public final long readLong() {
        if (this.size < 8) {
            throw new IllegalStateException("size < 8: " + this.size);
        }
        Segment segment = this.head;
        int i = segment.pos;
        int i2 = segment.limit;
        if (i2 - i < 8) {
            return ((((long) readInt()) & 4294967295L) << 32) | (((long) readInt()) & 4294967295L);
        }
        byte[] bArr = segment.data;
        int i3 = i + 1;
        int i4 = i3 + 1;
        long j = ((((long) bArr[i3]) & 255) << 48) | ((((long) bArr[i]) & 255) << 56);
        int i5 = i4 + 1;
        int i6 = i5 + 1;
        long j2 = j | ((((long) bArr[i4]) & 255) << 40) | ((((long) bArr[i5]) & 255) << 32);
        int i7 = i6 + 1;
        int i8 = i7 + 1;
        long j3 = j2 | ((((long) bArr[i6]) & 255) << 24) | ((((long) bArr[i7]) & 255) << 16);
        int i9 = i8 + 1;
        int i10 = i9 + 1;
        long j4 = (((long) bArr[i9]) & 255) | j3 | ((((long) bArr[i8]) & 255) << 8);
        this.size -= 8;
        if (i10 == i2) {
            this.head = segment.pop();
            SegmentPool.recycle(segment);
            return j4;
        }
        segment.pos = i10;
        return j4;
    }

    public final short readShortLe() {
        return Util.reverseBytesShort(readShort());
    }

    public final int readIntLe() {
        return Util.reverseBytesInt(readInt());
    }

    public final long readLongLe() {
        return Util.reverseBytesLong(readLong());
    }

    /* JADX WARNING: Code restructure failed: missing block: B:32:0x00a7, code lost:
        if (r7 != r12) goto L_0x00c9;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:0x00a9, code lost:
        r18.head = r10.pop();
        okio.SegmentPool.recycle(r10);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:34:0x00b4, code lost:
        if (r4 != false) goto L_0x00bc;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:39:0x00c9, code lost:
        r10.pos = r7;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final long readDecimalLong() {
        /*
            r18 = this;
            r0 = r18
            long r2 = r0.size
            r4 = 0
            int r2 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r2 != 0) goto L_0x0012
            java.lang.IllegalStateException r2 = new java.lang.IllegalStateException
            java.lang.String r3 = "size == 0"
            r2.<init>(r3)
            throw r2
        L_0x0012:
            r8 = 0
            r6 = 0
            r5 = 0
            r4 = 0
            r2 = -7
        L_0x0019:
            r0 = r18
            okio.Segment r10 = r0.head
            byte[] r11 = r10.data
            int r7 = r10.pos
            int r12 = r10.limit
        L_0x0023:
            if (r7 >= r12) goto L_0x00a7
            byte r13 = r11[r7]
            r14 = 48
            if (r13 < r14) goto L_0x0080
            r14 = 57
            if (r13 > r14) goto L_0x0080
            int r14 = 48 - r13
            r16 = -922337203685477580(0xf333333333333334, double:-8.390303882365713E246)
            int r15 = (r8 > r16 ? 1 : (r8 == r16 ? 0 : -1))
            if (r15 < 0) goto L_0x004a
            r16 = -922337203685477580(0xf333333333333334, double:-8.390303882365713E246)
            int r15 = (r8 > r16 ? 1 : (r8 == r16 ? 0 : -1))
            if (r15 != 0) goto L_0x0075
            long r0 = (long) r14
            r16 = r0
            int r15 = (r16 > r2 ? 1 : (r16 == r2 ? 0 : -1))
            if (r15 >= 0) goto L_0x0075
        L_0x004a:
            okio.Buffer r2 = new okio.Buffer
            r2.<init>()
            okio.Buffer r2 = r2.writeDecimalLong((long) r8)
            okio.Buffer r2 = r2.writeByte((int) r13)
            if (r5 != 0) goto L_0x005c
            r2.readByte()
        L_0x005c:
            java.lang.NumberFormatException r3 = new java.lang.NumberFormatException
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            java.lang.String r5 = "Number too large: "
            r4.<init>(r5)
            java.lang.String r2 = r2.readUtf8()
            java.lang.StringBuilder r2 = r4.append(r2)
            java.lang.String r2 = r2.toString()
            r3.<init>(r2)
            throw r3
        L_0x0075:
            r16 = 10
            long r8 = r8 * r16
            long r14 = (long) r14
            long r8 = r8 + r14
        L_0x007b:
            int r7 = r7 + 1
            int r6 = r6 + 1
            goto L_0x0023
        L_0x0080:
            r14 = 45
            if (r13 != r14) goto L_0x008b
            if (r6 != 0) goto L_0x008b
            r5 = 1
            r14 = 1
            long r2 = r2 - r14
            goto L_0x007b
        L_0x008b:
            if (r6 != 0) goto L_0x00a6
            java.lang.NumberFormatException r2 = new java.lang.NumberFormatException
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            java.lang.String r4 = "Expected leading [0-9] or '-' character but was 0x"
            r3.<init>(r4)
            java.lang.String r4 = java.lang.Integer.toHexString(r13)
            java.lang.StringBuilder r3 = r3.append(r4)
            java.lang.String r3 = r3.toString()
            r2.<init>(r3)
            throw r2
        L_0x00a6:
            r4 = 1
        L_0x00a7:
            if (r7 != r12) goto L_0x00c9
            okio.Segment r7 = r10.pop()
            r0 = r18
            r0.head = r7
            okio.SegmentPool.recycle(r10)
        L_0x00b4:
            if (r4 != 0) goto L_0x00bc
            r0 = r18
            okio.Segment r7 = r0.head
            if (r7 != 0) goto L_0x0019
        L_0x00bc:
            r0 = r18
            long r2 = r0.size
            long r6 = (long) r6
            long r2 = r2 - r6
            r0 = r18
            r0.size = r2
            if (r5 == 0) goto L_0x00cc
        L_0x00c8:
            return r8
        L_0x00c9:
            r10.pos = r7
            goto L_0x00b4
        L_0x00cc:
            long r8 = -r8
            goto L_0x00c8
        */
        throw new UnsupportedOperationException("Method not decompiled: okio.Buffer.readDecimalLong():long");
    }

    public final long readHexadecimalUnsignedLong() {
        int i;
        int i2;
        if (this.size == 0) {
            throw new IllegalStateException("size == 0");
        }
        boolean z = false;
        int i3 = 0;
        long j = 0;
        while (true) {
            Segment segment = this.head;
            byte[] bArr = segment.data;
            int i4 = segment.pos;
            int i5 = segment.limit;
            i = i3;
            while (true) {
                if (i4 >= i5) {
                    break;
                }
                byte b = bArr[i4];
                if (b >= 48 && b <= 57) {
                    i2 = b - 48;
                } else if (b >= 97 && b <= 102) {
                    i2 = (b - 97) + 10;
                } else if (b >= 65 && b <= 70) {
                    i2 = (b - 65) + 10;
                } else if (i == 0) {
                    throw new NumberFormatException("Expected leading [0-9a-fA-F] character but was 0x" + Integer.toHexString(b));
                } else {
                    z = true;
                }
                if ((-1152921504606846976L & j) != 0) {
                    throw new NumberFormatException("Number too large: " + new Buffer().writeHexadecimalUnsignedLong(j).writeByte((int) b).readUtf8());
                }
                j = (j << 4) | ((long) i2);
                i++;
                i4++;
            }
            if (i4 == i5) {
                this.head = segment.pop();
                SegmentPool.recycle(segment);
            } else {
                segment.pos = i4;
            }
            if (z || this.head == null) {
                this.size -= (long) i;
            } else {
                i3 = i;
            }
        }
        this.size -= (long) i;
        return j;
    }

    public final ByteString readByteString() {
        return new ByteString(readByteArray());
    }

    public final ByteString readByteString(long j) {
        return new ByteString(readByteArray(j));
    }

    public final int select(Options options) {
        Segment segment = this.head;
        if (segment == null) {
            return options.indexOf(ByteString.EMPTY);
        }
        ByteString[] byteStringArr = options.byteStrings;
        int length = byteStringArr.length;
        for (int i = 0; i < length; i++) {
            ByteString byteString = byteStringArr[i];
            if (this.size >= ((long) byteString.size())) {
                if (rangeEquals(segment, segment.pos, byteString, 0, byteString.size())) {
                    try {
                        skip((long) byteString.size());
                        return i;
                    } catch (EOFException e) {
                        throw new AssertionError(e);
                    }
                }
            }
        }
        return -1;
    }

    /* access modifiers changed from: package-private */
    public final int selectPrefix(Options options) {
        Segment segment = this.head;
        ByteString[] byteStringArr = options.byteStrings;
        int length = byteStringArr.length;
        int i = 0;
        while (i < length) {
            ByteString byteString = byteStringArr[i];
            int min = (int) Math.min(this.size, (long) byteString.size());
            if (min != 0) {
                if (!rangeEquals(segment, segment.pos, byteString, 0, min)) {
                    i++;
                }
            }
            return i;
        }
        return -1;
    }

    public final void readFully(Buffer buffer, long j) {
        if (this.size < j) {
            buffer.write(this, this.size);
            throw new EOFException();
        } else {
            buffer.write(this, j);
        }
    }

    public final long readAll(Sink sink) {
        long j = this.size;
        if (j > 0) {
            sink.write(this, j);
        }
        return j;
    }

    public final String readUtf8() {
        try {
            return readString(this.size, Util.UTF_8);
        } catch (EOFException e) {
            throw new AssertionError(e);
        }
    }

    public final String readUtf8(long j) {
        return readString(j, Util.UTF_8);
    }

    public final String readString(Charset charset) {
        try {
            return readString(this.size, charset);
        } catch (EOFException e) {
            throw new AssertionError(e);
        }
    }

    public final String readString(long j, Charset charset) {
        Util.checkOffsetAndCount(this.size, 0, j);
        if (charset == null) {
            throw new IllegalArgumentException("charset == null");
        } else if (j > 2147483647L) {
            throw new IllegalArgumentException("byteCount > Integer.MAX_VALUE: " + j);
        } else if (j == 0) {
            return BuildConfig.FLAVOR;
        } else {
            Segment segment = this.head;
            if (((long) segment.pos) + j > ((long) segment.limit)) {
                return new String(readByteArray(j), charset);
            }
            String str = new String(segment.data, segment.pos, (int) j, charset);
            segment.pos = (int) (((long) segment.pos) + j);
            this.size -= j;
            if (segment.pos != segment.limit) {
                return str;
            }
            this.head = segment.pop();
            SegmentPool.recycle(segment);
            return str;
        }
    }

    @Nullable
    public final String readUtf8Line() {
        long indexOf = indexOf((byte) 10);
        if (indexOf != -1) {
            return readUtf8Line(indexOf);
        }
        if (this.size != 0) {
            return readUtf8(this.size);
        }
        return null;
    }

    public final String readUtf8LineStrict() {
        return readUtf8LineStrict(Long.MAX_VALUE);
    }

    public final String readUtf8LineStrict(long j) {
        long j2 = Long.MAX_VALUE;
        if (j < 0) {
            throw new IllegalArgumentException("limit < 0: " + j);
        }
        if (j != Long.MAX_VALUE) {
            j2 = j + 1;
        }
        long indexOf = indexOf((byte) 10, 0, j2);
        if (indexOf != -1) {
            return readUtf8Line(indexOf);
        }
        if (j2 < size() && getByte(j2 - 1) == 13 && getByte(j2) == 10) {
            return readUtf8Line(j2);
        }
        Buffer buffer = new Buffer();
        copyTo(buffer, 0, Math.min(32, size()));
        throw new EOFException("\\n not found: limit=" + Math.min(size(), j) + " content=" + buffer.readByteString().hex() + 8230);
    }

    /* access modifiers changed from: package-private */
    public final String readUtf8Line(long j) {
        if (j <= 0 || getByte(j - 1) != 13) {
            String readUtf8 = readUtf8(j);
            skip(1);
            return readUtf8;
        }
        String readUtf82 = readUtf8(j - 1);
        skip(2);
        return readUtf82;
    }

    public final int readUtf8CodePoint() {
        byte b;
        int i;
        byte b2;
        int i2 = 1;
        if (this.size == 0) {
            throw new EOFException();
        }
        byte b3 = getByte(0);
        if ((b3 & 128) == 0) {
            b = b3 & Byte.MAX_VALUE;
            b2 = 0;
            i = 1;
        } else if ((b3 & 224) == 192) {
            b = b3 & 31;
            i = 2;
            b2 = 128;
        } else if ((b3 & 240) == 224) {
            b = b3 & 15;
            i = 3;
            b2 = 2048;
        } else if ((b3 & 248) == 240) {
            b = b3 & 7;
            i = 4;
            b2 = 65536;
        } else {
            skip(1);
            return REPLACEMENT_CHARACTER;
        }
        if (this.size < ((long) i)) {
            throw new EOFException("size < " + i + ": " + this.size + " (to read code point prefixed 0x" + Integer.toHexString(b3) + ")");
        }
        while (i2 < i) {
            byte b4 = getByte((long) i2);
            if ((b4 & 192) == 128) {
                b = (b << 6) | (b4 & 63);
                i2++;
            } else {
                skip((long) i2);
                return REPLACEMENT_CHARACTER;
            }
        }
        skip((long) i);
        return b > 1114111 ? REPLACEMENT_CHARACTER : (b < 55296 || b > 57343) ? b < b2 ? REPLACEMENT_CHARACTER : b : REPLACEMENT_CHARACTER;
    }

    public final byte[] readByteArray() {
        try {
            return readByteArray(this.size);
        } catch (EOFException e) {
            throw new AssertionError(e);
        }
    }

    public final byte[] readByteArray(long j) {
        Util.checkOffsetAndCount(this.size, 0, j);
        if (j > 2147483647L) {
            throw new IllegalArgumentException("byteCount > Integer.MAX_VALUE: " + j);
        }
        byte[] bArr = new byte[((int) j)];
        readFully(bArr);
        return bArr;
    }

    public final int read(byte[] bArr) {
        return read(bArr, 0, bArr.length);
    }

    public final void readFully(byte[] bArr) {
        int i = 0;
        while (i < bArr.length) {
            int read = read(bArr, i, bArr.length - i);
            if (read == -1) {
                throw new EOFException();
            }
            i += read;
        }
    }

    public final int read(byte[] bArr, int i, int i2) {
        Util.checkOffsetAndCount((long) bArr.length, (long) i, (long) i2);
        Segment segment = this.head;
        if (segment == null) {
            return -1;
        }
        int min = Math.min(i2, segment.limit - segment.pos);
        System.arraycopy(segment.data, segment.pos, bArr, i, min);
        segment.pos += min;
        this.size -= (long) min;
        if (segment.pos != segment.limit) {
            return min;
        }
        this.head = segment.pop();
        SegmentPool.recycle(segment);
        return min;
    }

    public final void clear() {
        try {
            skip(this.size);
        } catch (EOFException e) {
            throw new AssertionError(e);
        }
    }

    public final void skip(long j) {
        while (j > 0) {
            if (this.head == null) {
                throw new EOFException();
            }
            int min = (int) Math.min(j, (long) (this.head.limit - this.head.pos));
            this.size -= (long) min;
            j -= (long) min;
            Segment segment = this.head;
            segment.pos = min + segment.pos;
            if (this.head.pos == this.head.limit) {
                Segment segment2 = this.head;
                this.head = segment2.pop();
                SegmentPool.recycle(segment2);
            }
        }
    }

    public final Buffer write(ByteString byteString) {
        if (byteString == null) {
            throw new IllegalArgumentException("byteString == null");
        }
        byteString.write(this);
        return this;
    }

    public final Buffer writeUtf8(String str) {
        return writeUtf8(str, 0, str.length());
    }

    /* JADX WARNING: CFG modification limit reached, blocks count: 146 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final okio.Buffer writeUtf8(java.lang.String r10, int r11, int r12) {
        /*
            r9 = this;
            r8 = 57343(0xdfff, float:8.0355E-41)
            r7 = 128(0x80, float:1.794E-43)
            if (r10 != 0) goto L_0x000f
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            java.lang.String r1 = "string == null"
            r0.<init>(r1)
            throw r0
        L_0x000f:
            if (r11 >= 0) goto L_0x0026
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r2 = "beginIndex < 0: "
            r1.<init>(r2)
            java.lang.StringBuilder r1 = r1.append(r11)
            java.lang.String r1 = r1.toString()
            r0.<init>(r1)
            throw r0
        L_0x0026:
            if (r12 >= r11) goto L_0x0047
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r2 = "endIndex < beginIndex: "
            r1.<init>(r2)
            java.lang.StringBuilder r1 = r1.append(r12)
            java.lang.String r2 = " < "
            java.lang.StringBuilder r1 = r1.append(r2)
            java.lang.StringBuilder r1 = r1.append(r11)
            java.lang.String r1 = r1.toString()
            r0.<init>(r1)
            throw r0
        L_0x0047:
            int r0 = r10.length()
            if (r12 <= r0) goto L_0x0081
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r2 = "endIndex > string.length: "
            r1.<init>(r2)
            java.lang.StringBuilder r1 = r1.append(r12)
            java.lang.String r2 = " > "
            java.lang.StringBuilder r1 = r1.append(r2)
            int r2 = r10.length()
            java.lang.StringBuilder r1 = r1.append(r2)
            java.lang.String r1 = r1.toString()
            r0.<init>(r1)
            throw r0
        L_0x0070:
            int r1 = r0 + r4
            int r3 = r2.limit
            int r1 = r1 - r3
            int r3 = r2.limit
            int r3 = r3 + r1
            r2.limit = r3
            long r2 = r9.size
            long r4 = (long) r1
            long r2 = r2 + r4
            r9.size = r2
            r11 = r0
        L_0x0081:
            if (r11 >= r12) goto L_0x013a
            char r1 = r10.charAt(r11)
            if (r1 >= r7) goto L_0x00b1
            r0 = 1
            okio.Segment r2 = r9.writableSegment(r0)
            byte[] r3 = r2.data
            int r0 = r2.limit
            int r4 = r0 - r11
            int r0 = 8192 - r4
            int r5 = java.lang.Math.min(r12, r0)
            int r0 = r11 + 1
            int r6 = r4 + r11
            byte r1 = (byte) r1
            r3[r6] = r1
        L_0x00a1:
            if (r0 >= r5) goto L_0x0070
            char r6 = r10.charAt(r0)
            if (r6 >= r7) goto L_0x0070
            int r1 = r0 + 1
            int r0 = r0 + r4
            byte r6 = (byte) r6
            r3[r0] = r6
            r0 = r1
            goto L_0x00a1
        L_0x00b1:
            r0 = 2048(0x800, float:2.87E-42)
            if (r1 >= r0) goto L_0x00c6
            int r0 = r1 >> 6
            r0 = r0 | 192(0xc0, float:2.69E-43)
            r9.writeByte((int) r0)
            r0 = r1 & 63
            r0 = r0 | 128(0x80, float:1.794E-43)
            r9.writeByte((int) r0)
            int r11 = r11 + 1
            goto L_0x0081
        L_0x00c6:
            r0 = 55296(0xd800, float:7.7486E-41)
            if (r1 < r0) goto L_0x00cd
            if (r1 <= r8) goto L_0x00e7
        L_0x00cd:
            int r0 = r1 >> 12
            r0 = r0 | 224(0xe0, float:3.14E-43)
            r9.writeByte((int) r0)
            int r0 = r1 >> 6
            r0 = r0 & 63
            r0 = r0 | 128(0x80, float:1.794E-43)
            r9.writeByte((int) r0)
            r0 = r1 & 63
            r0 = r0 | 128(0x80, float:1.794E-43)
            r9.writeByte((int) r0)
            int r11 = r11 + 1
            goto L_0x0081
        L_0x00e7:
            int r0 = r11 + 1
            if (r0 >= r12) goto L_0x0106
            int r0 = r11 + 1
            char r0 = r10.charAt(r0)
        L_0x00f1:
            r2 = 56319(0xdbff, float:7.892E-41)
            if (r1 > r2) goto L_0x00fd
            r2 = 56320(0xdc00, float:7.8921E-41)
            if (r0 < r2) goto L_0x00fd
            if (r0 <= r8) goto L_0x0108
        L_0x00fd:
            r0 = 63
            r9.writeByte((int) r0)
            int r11 = r11 + 1
            goto L_0x0081
        L_0x0106:
            r0 = 0
            goto L_0x00f1
        L_0x0108:
            r2 = 65536(0x10000, float:9.18355E-41)
            r3 = -55297(0xffffffffffff27ff, float:NaN)
            r1 = r1 & r3
            int r1 = r1 << 10
            r3 = -56321(0xffffffffffff23ff, float:NaN)
            r0 = r0 & r3
            r0 = r0 | r1
            int r0 = r0 + r2
            int r1 = r0 >> 18
            r1 = r1 | 240(0xf0, float:3.36E-43)
            r9.writeByte((int) r1)
            int r1 = r0 >> 12
            r1 = r1 & 63
            r1 = r1 | 128(0x80, float:1.794E-43)
            r9.writeByte((int) r1)
            int r1 = r0 >> 6
            r1 = r1 & 63
            r1 = r1 | 128(0x80, float:1.794E-43)
            r9.writeByte((int) r1)
            r0 = r0 & 63
            r0 = r0 | 128(0x80, float:1.794E-43)
            r9.writeByte((int) r0)
            int r11 = r11 + 2
            goto L_0x0081
        L_0x013a:
            return r9
        */
        throw new UnsupportedOperationException("Method not decompiled: okio.Buffer.writeUtf8(java.lang.String, int, int):okio.Buffer");
    }

    public final Buffer writeUtf8CodePoint(int i) {
        if (i < 128) {
            writeByte(i);
        } else if (i < 2048) {
            writeByte((i >> 6) | 192);
            writeByte((i & 63) | 128);
        } else if (i < 65536) {
            if (i < 55296 || i > 57343) {
                writeByte((i >> 12) | 224);
                writeByte(((i >> 6) & 63) | 128);
                writeByte((i & 63) | 128);
            } else {
                writeByte(63);
            }
        } else if (i <= 1114111) {
            writeByte((i >> 18) | 240);
            writeByte(((i >> 12) & 63) | 128);
            writeByte(((i >> 6) & 63) | 128);
            writeByte((i & 63) | 128);
        } else {
            throw new IllegalArgumentException("Unexpected code point: " + Integer.toHexString(i));
        }
        return this;
    }

    public final Buffer writeString(String str, Charset charset) {
        return writeString(str, 0, str.length(), charset);
    }

    public final Buffer writeString(String str, int i, int i2, Charset charset) {
        if (str == null) {
            throw new IllegalArgumentException("string == null");
        } else if (i < 0) {
            throw new IllegalAccessError("beginIndex < 0: " + i);
        } else if (i2 < i) {
            throw new IllegalArgumentException("endIndex < beginIndex: " + i2 + " < " + i);
        } else if (i2 > str.length()) {
            throw new IllegalArgumentException("endIndex > string.length: " + i2 + " > " + str.length());
        } else if (charset == null) {
            throw new IllegalArgumentException("charset == null");
        } else if (charset.equals(Util.UTF_8)) {
            return writeUtf8(str, i, i2);
        } else {
            byte[] bytes = str.substring(i, i2).getBytes(charset);
            return write(bytes, 0, bytes.length);
        }
    }

    public final Buffer write(byte[] bArr) {
        if (bArr != null) {
            return write(bArr, 0, bArr.length);
        }
        throw new IllegalArgumentException("source == null");
    }

    public final Buffer write(byte[] bArr, int i, int i2) {
        if (bArr == null) {
            throw new IllegalArgumentException("source == null");
        }
        Util.checkOffsetAndCount((long) bArr.length, (long) i, (long) i2);
        int i3 = i + i2;
        while (i < i3) {
            Segment writableSegment = writableSegment(1);
            int min = Math.min(i3 - i, 8192 - writableSegment.limit);
            System.arraycopy(bArr, i, writableSegment.data, writableSegment.limit, min);
            i += min;
            writableSegment.limit = min + writableSegment.limit;
        }
        this.size += (long) i2;
        return this;
    }

    public final long writeAll(Source source) {
        if (source == null) {
            throw new IllegalArgumentException("source == null");
        }
        long j = 0;
        while (true) {
            long read = source.read(this, 8192);
            if (read == -1) {
                return j;
            }
            j += read;
        }
    }

    public final BufferedSink write(Source source, long j) {
        while (j > 0) {
            long read = source.read(this, j);
            if (read == -1) {
                throw new EOFException();
            }
            j -= read;
        }
        return this;
    }

    public final Buffer writeByte(int i) {
        Segment writableSegment = writableSegment(1);
        byte[] bArr = writableSegment.data;
        int i2 = writableSegment.limit;
        writableSegment.limit = i2 + 1;
        bArr[i2] = (byte) i;
        this.size++;
        return this;
    }

    public final Buffer writeShort(int i) {
        Segment writableSegment = writableSegment(2);
        byte[] bArr = writableSegment.data;
        int i2 = writableSegment.limit;
        int i3 = i2 + 1;
        bArr[i2] = (byte) ((i >>> 8) & 255);
        bArr[i3] = (byte) (i & 255);
        writableSegment.limit = i3 + 1;
        this.size += 2;
        return this;
    }

    public final Buffer writeShortLe(int i) {
        return writeShort((int) Util.reverseBytesShort((short) i));
    }

    public final Buffer writeInt(int i) {
        Segment writableSegment = writableSegment(4);
        byte[] bArr = writableSegment.data;
        int i2 = writableSegment.limit;
        int i3 = i2 + 1;
        bArr[i2] = (byte) ((i >>> 24) & 255);
        int i4 = i3 + 1;
        bArr[i3] = (byte) ((i >>> 16) & 255);
        int i5 = i4 + 1;
        bArr[i4] = (byte) ((i >>> 8) & 255);
        bArr[i5] = (byte) (i & 255);
        writableSegment.limit = i5 + 1;
        this.size += 4;
        return this;
    }

    public final Buffer writeIntLe(int i) {
        return writeInt(Util.reverseBytesInt(i));
    }

    public final Buffer writeLong(long j) {
        Segment writableSegment = writableSegment(8);
        byte[] bArr = writableSegment.data;
        int i = writableSegment.limit;
        int i2 = i + 1;
        bArr[i] = (byte) ((int) ((j >>> 56) & 255));
        int i3 = i2 + 1;
        bArr[i2] = (byte) ((int) ((j >>> 48) & 255));
        int i4 = i3 + 1;
        bArr[i3] = (byte) ((int) ((j >>> 40) & 255));
        int i5 = i4 + 1;
        bArr[i4] = (byte) ((int) ((j >>> 32) & 255));
        int i6 = i5 + 1;
        bArr[i5] = (byte) ((int) ((j >>> 24) & 255));
        int i7 = i6 + 1;
        bArr[i6] = (byte) ((int) ((j >>> 16) & 255));
        int i8 = i7 + 1;
        bArr[i7] = (byte) ((int) ((j >>> 8) & 255));
        bArr[i8] = (byte) ((int) (j & 255));
        writableSegment.limit = i8 + 1;
        this.size += 8;
        return this;
    }

    public final Buffer writeLongLe(long j) {
        return writeLong(Util.reverseBytesLong(j));
    }

    public final Buffer writeDecimalLong(long j) {
        boolean z;
        long j2;
        int i;
        if (j == 0) {
            return writeByte(48);
        }
        if (j < 0) {
            j2 = -j;
            if (j2 < 0) {
                return writeUtf8("-9223372036854775808");
            }
            z = true;
        } else {
            z = false;
            j2 = j;
        }
        if (j2 < 100000000) {
            i = j2 < 10000 ? j2 < 100 ? j2 < 10 ? 1 : 2 : j2 < 1000 ? 3 : 4 : j2 < 1000000 ? j2 < 100000 ? 5 : 6 : j2 < 10000000 ? 7 : 8;
        } else if (j2 < 1000000000000L) {
            i = j2 < 10000000000L ? j2 < 1000000000 ? 9 : 10 : j2 < 100000000000L ? 11 : 12;
        } else if (j2 < 1000000000000000L) {
            i = j2 < 10000000000000L ? 13 : j2 < 100000000000000L ? 14 : 15;
        } else if (j2 < 100000000000000000L) {
            i = j2 < 10000000000000000L ? 16 : 17;
        } else {
            i = j2 < 1000000000000000000L ? 18 : 19;
        }
        if (z) {
            i++;
        }
        Segment writableSegment = writableSegment(i);
        byte[] bArr = writableSegment.data;
        int i2 = writableSegment.limit + i;
        while (j2 != 0) {
            i2--;
            bArr[i2] = DIGITS[(int) (j2 % 10)];
            j2 /= 10;
        }
        if (z) {
            bArr[i2 - 1] = 45;
        }
        writableSegment.limit += i;
        this.size = ((long) i) + this.size;
        return this;
    }

    public final Buffer writeHexadecimalUnsignedLong(long j) {
        if (j == 0) {
            return writeByte(48);
        }
        int numberOfTrailingZeros = (Long.numberOfTrailingZeros(Long.highestOneBit(j)) / 4) + 1;
        Segment writableSegment = writableSegment(numberOfTrailingZeros);
        byte[] bArr = writableSegment.data;
        int i = writableSegment.limit;
        for (int i2 = (writableSegment.limit + numberOfTrailingZeros) - 1; i2 >= i; i2--) {
            bArr[i2] = DIGITS[(int) (15 & j)];
            j >>>= 4;
        }
        writableSegment.limit += numberOfTrailingZeros;
        this.size = ((long) numberOfTrailingZeros) + this.size;
        return this;
    }

    /* access modifiers changed from: package-private */
    public final Segment writableSegment(int i) {
        if (i <= 0 || i > 8192) {
            throw new IllegalArgumentException();
        } else if (this.head == null) {
            this.head = SegmentPool.take();
            Segment segment = this.head;
            Segment segment2 = this.head;
            Segment segment3 = this.head;
            segment2.prev = segment3;
            segment.next = segment3;
            return segment3;
        } else {
            Segment segment4 = this.head.prev;
            if (segment4.limit + i > 8192 || !segment4.owner) {
                return segment4.push(SegmentPool.take());
            }
            return segment4;
        }
    }

    public final void write(Buffer buffer, long j) {
        if (buffer == null) {
            throw new IllegalArgumentException("source == null");
        } else if (buffer == this) {
            throw new IllegalArgumentException("source == this");
        } else {
            Util.checkOffsetAndCount(buffer.size, 0, j);
            while (j > 0) {
                if (j < ((long) (buffer.head.limit - buffer.head.pos))) {
                    Segment segment = this.head != null ? this.head.prev : null;
                    if (segment != null && segment.owner) {
                        if ((((long) segment.limit) + j) - ((long) (segment.shared ? 0 : segment.pos)) <= 8192) {
                            buffer.head.writeTo(segment, (int) j);
                            buffer.size -= j;
                            this.size += j;
                            return;
                        }
                    }
                    buffer.head = buffer.head.split((int) j);
                }
                Segment segment2 = buffer.head;
                long j2 = (long) (segment2.limit - segment2.pos);
                buffer.head = segment2.pop();
                if (this.head == null) {
                    this.head = segment2;
                    Segment segment3 = this.head;
                    Segment segment4 = this.head;
                    Segment segment5 = this.head;
                    segment4.prev = segment5;
                    segment3.next = segment5;
                } else {
                    this.head.prev.push(segment2).compact();
                }
                buffer.size -= j2;
                this.size += j2;
                j -= j2;
            }
        }
    }

    public final long read(Buffer buffer, long j) {
        if (buffer == null) {
            throw new IllegalArgumentException("sink == null");
        } else if (j < 0) {
            throw new IllegalArgumentException("byteCount < 0: " + j);
        } else if (this.size == 0) {
            return -1;
        } else {
            if (j > this.size) {
                j = this.size;
            }
            buffer.write(this, j);
            return j;
        }
    }

    public final long indexOf(byte b) {
        return indexOf(b, 0, Long.MAX_VALUE);
    }

    public final long indexOf(byte b, long j) {
        return indexOf(b, j, Long.MAX_VALUE);
    }

    public final long indexOf(byte b, long j, long j2) {
        Segment segment;
        Segment segment2;
        long j3;
        if (j < 0 || j2 < j) {
            throw new IllegalArgumentException(String.format("size=%s fromIndex=%s toIndex=%s", new Object[]{Long.valueOf(this.size), Long.valueOf(j), Long.valueOf(j2)}));
        }
        if (j2 > this.size) {
            j2 = this.size;
        }
        if (j == j2 || (segment = this.head) == null) {
            return -1;
        }
        if (this.size - j >= j) {
            long j4 = 0;
            Segment segment3 = segment;
            while (true) {
                long j5 = ((long) (segment2.limit - segment2.pos)) + j3;
                if (j5 >= j) {
                    break;
                }
                segment3 = segment2.next;
                j4 = j5;
            }
        } else {
            j3 = this.size;
            segment2 = segment;
            while (j3 > j) {
                segment2 = segment2.prev;
                j3 -= (long) (segment2.limit - segment2.pos);
            }
        }
        long j6 = j3;
        while (j6 < j2) {
            byte[] bArr = segment2.data;
            int min = (int) Math.min((long) segment2.limit, (((long) segment2.pos) + j2) - j6);
            for (int i = (int) ((((long) segment2.pos) + j) - j6); i < min; i++) {
                if (bArr[i] == b) {
                    return ((long) (i - segment2.pos)) + j6;
                }
            }
            long j7 = ((long) (segment2.limit - segment2.pos)) + j6;
            segment2 = segment2.next;
            j6 = j7;
            j = j7;
        }
        return -1;
    }

    public final long indexOf(ByteString byteString) {
        return indexOf(byteString, 0);
    }

    public final long indexOf(ByteString byteString, long j) {
        Segment segment;
        long j2;
        if (byteString.size() == 0) {
            throw new IllegalArgumentException("bytes is empty");
        } else if (j < 0) {
            throw new IllegalArgumentException("fromIndex < 0");
        } else {
            Segment segment2 = this.head;
            if (segment2 == null) {
                return -1;
            }
            if (this.size - j >= j) {
                long j3 = 0;
                Segment segment3 = segment2;
                while (true) {
                    long j4 = ((long) (segment.limit - segment.pos)) + j2;
                    if (j4 >= j) {
                        break;
                    }
                    segment3 = segment.next;
                    j3 = j4;
                }
            } else {
                j2 = this.size;
                segment = segment2;
                while (j2 > j) {
                    segment = segment.prev;
                    j2 -= (long) (segment.limit - segment.pos);
                }
            }
            byte b = byteString.getByte(0);
            int size2 = byteString.size();
            long j5 = (this.size - ((long) size2)) + 1;
            long j6 = j2;
            Segment segment4 = segment;
            while (j6 < j5) {
                byte[] bArr = segment4.data;
                int min = (int) Math.min((long) segment4.limit, (((long) segment4.pos) + j5) - j6);
                for (int i = (int) ((((long) segment4.pos) + j) - j6); i < min; i++) {
                    if (bArr[i] == b) {
                        if (rangeEquals(segment4, i + 1, byteString, 1, size2)) {
                            return ((long) (i - segment4.pos)) + j6;
                        }
                    }
                }
                long j7 = ((long) (segment4.limit - segment4.pos)) + j6;
                segment4 = segment4.next;
                j6 = j7;
                j = j7;
            }
            return -1;
        }
    }

    public final long indexOfElement(ByteString byteString) {
        return indexOfElement(byteString, 0);
    }

    public final long indexOfElement(ByteString byteString, long j) {
        Segment segment;
        long j2;
        if (j < 0) {
            throw new IllegalArgumentException("fromIndex < 0");
        }
        Segment segment2 = this.head;
        if (segment2 == null) {
            return -1;
        }
        if (this.size - j >= j) {
            long j3 = 0;
            Segment segment3 = segment2;
            while (true) {
                long j4 = ((long) (segment.limit - segment.pos)) + j2;
                if (j4 >= j) {
                    break;
                }
                segment3 = segment.next;
                j3 = j4;
            }
        } else {
            j2 = this.size;
            segment = segment2;
            while (j2 > j) {
                segment = segment.prev;
                j2 -= (long) (segment.limit - segment.pos);
            }
        }
        if (byteString.size() == 2) {
            byte b = byteString.getByte(0);
            byte b2 = byteString.getByte(1);
            while (j2 < this.size) {
                byte[] bArr = segment.data;
                int i = segment.limit;
                for (int i2 = (int) ((((long) segment.pos) + j) - j2); i2 < i; i2++) {
                    byte b3 = bArr[i2];
                    if (b3 == b || b3 == b2) {
                        return j2 + ((long) (i2 - segment.pos));
                    }
                }
                long j5 = ((long) (segment.limit - segment.pos)) + j2;
                segment = segment.next;
                j2 = j5;
                j = j5;
            }
        } else {
            byte[] internalArray = byteString.internalArray();
            while (j2 < this.size) {
                byte[] bArr2 = segment.data;
                int i3 = segment.limit;
                for (int i4 = (int) ((((long) segment.pos) + j) - j2); i4 < i3; i4++) {
                    byte b4 = bArr2[i4];
                    for (byte b5 : internalArray) {
                        if (b4 == b5) {
                            return j2 + ((long) (i4 - segment.pos));
                        }
                    }
                }
                long j6 = ((long) (segment.limit - segment.pos)) + j2;
                segment = segment.next;
                j2 = j6;
                j = j6;
            }
        }
        return -1;
    }

    public final boolean rangeEquals(long j, ByteString byteString) {
        return rangeEquals(j, byteString, 0, byteString.size());
    }

    public final boolean rangeEquals(long j, ByteString byteString, int i, int i2) {
        if (j < 0 || i < 0 || i2 < 0 || this.size - j < ((long) i2) || byteString.size() - i < i2) {
            return false;
        }
        for (int i3 = 0; i3 < i2; i3++) {
            if (getByte(((long) i3) + j) != byteString.getByte(i + i3)) {
                return false;
            }
        }
        return true;
    }

    private boolean rangeEquals(Segment segment, int i, ByteString byteString, int i2, int i3) {
        int i4 = segment.limit;
        byte[] bArr = segment.data;
        int i5 = i;
        Segment segment2 = segment;
        while (i2 < i3) {
            if (i5 == i4) {
                segment2 = segment2.next;
                bArr = segment2.data;
                i5 = segment2.pos;
                i4 = segment2.limit;
            }
            if (bArr[i5] != byteString.getByte(i2)) {
                return false;
            }
            i5++;
            i2++;
        }
        return true;
    }

    public final void flush() {
    }

    public final void close() {
    }

    public final Timeout timeout() {
        return Timeout.NONE;
    }

    /* access modifiers changed from: package-private */
    public final List<Integer> segmentSizes() {
        if (this.head == null) {
            return Collections.emptyList();
        }
        ArrayList arrayList = new ArrayList();
        arrayList.add(Integer.valueOf(this.head.limit - this.head.pos));
        for (Segment segment = this.head.next; segment != this.head; segment = segment.next) {
            arrayList.add(Integer.valueOf(segment.limit - segment.pos));
        }
        return arrayList;
    }

    public final ByteString md5() {
        return digest("MD5");
    }

    public final ByteString sha1() {
        return digest("SHA-1");
    }

    public final ByteString sha256() {
        return digest("SHA-256");
    }

    public final ByteString sha512() {
        return digest("SHA-512");
    }

    private ByteString digest(String str) {
        try {
            MessageDigest instance = MessageDigest.getInstance(str);
            if (this.head != null) {
                instance.update(this.head.data, this.head.pos, this.head.limit - this.head.pos);
                for (Segment segment = this.head.next; segment != this.head; segment = segment.next) {
                    instance.update(segment.data, segment.pos, segment.limit - segment.pos);
                }
            }
            return ByteString.m801of(instance.digest());
        } catch (NoSuchAlgorithmException e) {
            throw new AssertionError();
        }
    }

    public final ByteString hmacSha1(ByteString byteString) {
        return hmac("HmacSHA1", byteString);
    }

    public final ByteString hmacSha256(ByteString byteString) {
        return hmac("HmacSHA256", byteString);
    }

    public final ByteString hmacSha512(ByteString byteString) {
        return hmac("HmacSHA512", byteString);
    }

    private ByteString hmac(String str, ByteString byteString) {
        try {
            Mac instance = Mac.getInstance(str);
            instance.init(new SecretKeySpec(byteString.toByteArray(), str));
            if (this.head != null) {
                instance.update(this.head.data, this.head.pos, this.head.limit - this.head.pos);
                for (Segment segment = this.head.next; segment != this.head; segment = segment.next) {
                    instance.update(segment.data, segment.pos, segment.limit - segment.pos);
                }
            }
            return ByteString.m801of(instance.doFinal());
        } catch (NoSuchAlgorithmException e) {
            throw new AssertionError();
        } catch (InvalidKeyException e2) {
            throw new IllegalArgumentException(e2);
        }
    }

    public final boolean equals(Object obj) {
        long j = 0;
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Buffer)) {
            return false;
        }
        Buffer buffer = (Buffer) obj;
        if (this.size != buffer.size) {
            return false;
        }
        if (this.size == 0) {
            return true;
        }
        Segment segment = this.head;
        Segment segment2 = buffer.head;
        int i = segment.pos;
        int i2 = segment2.pos;
        while (j < this.size) {
            long min = (long) Math.min(segment.limit - i, segment2.limit - i2);
            int i3 = 0;
            while (((long) i3) < min) {
                int i4 = i + 1;
                int i5 = i2 + 1;
                if (segment.data[i] != segment2.data[i2]) {
                    return false;
                }
                i3++;
                i2 = i5;
                i = i4;
            }
            if (i == segment.limit) {
                segment = segment.next;
                i = segment.pos;
            }
            if (i2 == segment2.limit) {
                segment2 = segment2.next;
                i2 = segment2.pos;
            }
            j += min;
        }
        return true;
    }

    public final int hashCode() {
        Segment segment = this.head;
        if (segment == null) {
            return 0;
        }
        int i = 1;
        do {
            int i2 = segment.limit;
            for (int i3 = segment.pos; i3 < i2; i3++) {
                i = (i * 31) + segment.data[i3];
            }
            segment = segment.next;
        } while (segment != this.head);
        return i;
    }

    public final String toString() {
        return snapshot().toString();
    }

    public final Buffer clone() {
        Buffer buffer = new Buffer();
        if (this.size == 0) {
            return buffer;
        }
        buffer.head = new Segment(this.head);
        Segment segment = buffer.head;
        Segment segment2 = buffer.head;
        Segment segment3 = buffer.head;
        segment2.prev = segment3;
        segment.next = segment3;
        for (Segment segment4 = this.head.next; segment4 != this.head; segment4 = segment4.next) {
            buffer.head.prev.push(new Segment(segment4));
        }
        buffer.size = this.size;
        return buffer;
    }

    public final ByteString snapshot() {
        if (this.size <= 2147483647L) {
            return snapshot((int) this.size);
        }
        throw new IllegalArgumentException("size > Integer.MAX_VALUE: " + this.size);
    }

    public final ByteString snapshot(int i) {
        if (i == 0) {
            return ByteString.EMPTY;
        }
        return new SegmentedByteString(this, i);
    }
}
