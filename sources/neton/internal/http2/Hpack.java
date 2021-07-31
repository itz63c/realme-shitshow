package neton.internal.http2;

import com.coloros.neton.BuildConfig;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import neton.client.config.Constants;
import neton.client.dns.DnsInfo;
import neton.internal.Util;
import neton.internal.http.HttpMethod;
import okio.Buffer;
import okio.BufferedSource;
import okio.ByteString;
import okio.Okio;
import okio.Source;

final class Hpack {
    static final Map<ByteString, Integer> NAME_TO_FIRST_INDEX = nameToFirstIndex();
    private static final int PREFIX_4_BITS = 15;
    private static final int PREFIX_5_BITS = 31;
    private static final int PREFIX_6_BITS = 63;
    private static final int PREFIX_7_BITS = 127;
    static final Header[] STATIC_HEADER_TABLE;

    static {
        Header[] headerArr = new Header[61];
        headerArr[0] = new Header(Header.TARGET_AUTHORITY, BuildConfig.FLAVOR);
        headerArr[1] = new Header(Header.TARGET_METHOD, HttpMethod.GET);
        headerArr[2] = new Header(Header.TARGET_METHOD, HttpMethod.POST);
        headerArr[3] = new Header(Header.TARGET_PATH, "/");
        headerArr[4] = new Header(Header.TARGET_PATH, "/index.html");
        headerArr[5] = new Header(Header.TARGET_SCHEME, "http");
        headerArr[6] = new Header(Header.TARGET_SCHEME, "https");
        headerArr[7] = new Header(Header.RESPONSE_STATUS, "200");
        headerArr[8] = new Header(Header.RESPONSE_STATUS, "204");
        headerArr[9] = new Header(Header.RESPONSE_STATUS, "206");
        headerArr[10] = new Header(Header.RESPONSE_STATUS, "304");
        headerArr[11] = new Header(Header.RESPONSE_STATUS, "400");
        headerArr[12] = new Header(Header.RESPONSE_STATUS, "404");
        headerArr[13] = new Header(Header.RESPONSE_STATUS, "500");
        headerArr[14] = new Header("accept-charset", BuildConfig.FLAVOR);
        headerArr[PREFIX_4_BITS] = new Header("accept-encoding", "gzip, deflate");
        headerArr[16] = new Header("accept-language", BuildConfig.FLAVOR);
        headerArr[17] = new Header("accept-ranges", BuildConfig.FLAVOR);
        headerArr[18] = new Header("accept", BuildConfig.FLAVOR);
        headerArr[19] = new Header("access-control-allow-origin", BuildConfig.FLAVOR);
        headerArr[20] = new Header("age", BuildConfig.FLAVOR);
        headerArr[21] = new Header("allow", BuildConfig.FLAVOR);
        headerArr[22] = new Header("authorization", BuildConfig.FLAVOR);
        headerArr[23] = new Header("cache-control", BuildConfig.FLAVOR);
        headerArr[24] = new Header("content-disposition", BuildConfig.FLAVOR);
        headerArr[25] = new Header("content-encoding", BuildConfig.FLAVOR);
        headerArr[26] = new Header("content-language", BuildConfig.FLAVOR);
        headerArr[27] = new Header("content-length", BuildConfig.FLAVOR);
        headerArr[28] = new Header("content-location", BuildConfig.FLAVOR);
        headerArr[29] = new Header("content-range", BuildConfig.FLAVOR);
        headerArr[30] = new Header("content-type", BuildConfig.FLAVOR);
        headerArr[PREFIX_5_BITS] = new Header("cookie", BuildConfig.FLAVOR);
        headerArr[32] = new Header("date", BuildConfig.FLAVOR);
        headerArr[33] = new Header("etag", BuildConfig.FLAVOR);
        headerArr[34] = new Header("expect", BuildConfig.FLAVOR);
        headerArr[35] = new Header("expires", BuildConfig.FLAVOR);
        headerArr[36] = new Header("from", BuildConfig.FLAVOR);
        headerArr[37] = new Header(DnsInfo.HOST, BuildConfig.FLAVOR);
        headerArr[38] = new Header("if-match", BuildConfig.FLAVOR);
        headerArr[39] = new Header("if-modified-since", BuildConfig.FLAVOR);
        headerArr[40] = new Header("if-none-match", BuildConfig.FLAVOR);
        headerArr[41] = new Header("if-range", BuildConfig.FLAVOR);
        headerArr[42] = new Header("if-unmodified-since", BuildConfig.FLAVOR);
        headerArr[43] = new Header("last-modified", BuildConfig.FLAVOR);
        headerArr[44] = new Header("link", BuildConfig.FLAVOR);
        headerArr[45] = new Header("location", BuildConfig.FLAVOR);
        headerArr[46] = new Header("max-forwards", BuildConfig.FLAVOR);
        headerArr[47] = new Header("proxy-authenticate", BuildConfig.FLAVOR);
        headerArr[48] = new Header("proxy-authorization", BuildConfig.FLAVOR);
        headerArr[49] = new Header("range", BuildConfig.FLAVOR);
        headerArr[50] = new Header("referer", BuildConfig.FLAVOR);
        headerArr[51] = new Header("refresh", BuildConfig.FLAVOR);
        headerArr[52] = new Header("retry-after", BuildConfig.FLAVOR);
        headerArr[53] = new Header("server", BuildConfig.FLAVOR);
        headerArr[54] = new Header("set-cookie", BuildConfig.FLAVOR);
        headerArr[55] = new Header("strict-transport-security", BuildConfig.FLAVOR);
        headerArr[56] = new Header("transfer-encoding", BuildConfig.FLAVOR);
        headerArr[57] = new Header("user-agent", BuildConfig.FLAVOR);
        headerArr[58] = new Header("vary", BuildConfig.FLAVOR);
        headerArr[59] = new Header("via", BuildConfig.FLAVOR);
        headerArr[60] = new Header("www-authenticate", BuildConfig.FLAVOR);
        STATIC_HEADER_TABLE = headerArr;
    }

    private Hpack() {
    }

    final class Reader {
        Header[] dynamicTable;
        int dynamicTableByteCount;
        int headerCount;
        private final List<Header> headerList;
        private final int headerTableSizeSetting;
        private int maxDynamicTableByteCount;
        int nextHeaderIndex;
        private final BufferedSource source;

        Reader(int i, Source source2) {
            this(i, i, source2);
        }

        Reader(int i, int i2, Source source2) {
            this.headerList = new ArrayList();
            this.dynamicTable = new Header[8];
            this.nextHeaderIndex = this.dynamicTable.length - 1;
            this.headerCount = 0;
            this.dynamicTableByteCount = 0;
            this.headerTableSizeSetting = i;
            this.maxDynamicTableByteCount = i2;
            this.source = Okio.buffer(source2);
        }

        /* access modifiers changed from: package-private */
        public final int maxDynamicTableByteCount() {
            return this.maxDynamicTableByteCount;
        }

        private void adjustDynamicTableByteCount() {
            if (this.maxDynamicTableByteCount >= this.dynamicTableByteCount) {
                return;
            }
            if (this.maxDynamicTableByteCount == 0) {
                clearDynamicTable();
            } else {
                evictToRecoverBytes(this.dynamicTableByteCount - this.maxDynamicTableByteCount);
            }
        }

        private void clearDynamicTable() {
            Arrays.fill(this.dynamicTable, (Object) null);
            this.nextHeaderIndex = this.dynamicTable.length - 1;
            this.headerCount = 0;
            this.dynamicTableByteCount = 0;
        }

        private int evictToRecoverBytes(int i) {
            int i2 = 0;
            if (i > 0) {
                int length = this.dynamicTable.length;
                while (true) {
                    length--;
                    if (length < this.nextHeaderIndex || i <= 0) {
                        System.arraycopy(this.dynamicTable, this.nextHeaderIndex + 1, this.dynamicTable, this.nextHeaderIndex + 1 + i2, this.headerCount);
                        this.nextHeaderIndex += i2;
                    } else {
                        i -= this.dynamicTable[length].hpackSize;
                        this.dynamicTableByteCount -= this.dynamicTable[length].hpackSize;
                        this.headerCount--;
                        i2++;
                    }
                }
                System.arraycopy(this.dynamicTable, this.nextHeaderIndex + 1, this.dynamicTable, this.nextHeaderIndex + 1 + i2, this.headerCount);
                this.nextHeaderIndex += i2;
            }
            return i2;
        }

        /* access modifiers changed from: package-private */
        public final void readHeaders() {
            while (!this.source.exhausted()) {
                byte readByte = this.source.readByte() & 255;
                if (readByte == 128) {
                    throw new IOException("index == 0");
                } else if ((readByte & 128) == 128) {
                    readIndexedHeader(readInt(readByte, Hpack.PREFIX_7_BITS) - 1);
                } else if (readByte == 64) {
                    readLiteralHeaderWithIncrementalIndexingNewName();
                } else if ((readByte & 64) == 64) {
                    readLiteralHeaderWithIncrementalIndexingIndexedName(readInt(readByte, Hpack.PREFIX_6_BITS) - 1);
                } else if ((readByte & Constants.PROTOCOL_VERSION) == 32) {
                    this.maxDynamicTableByteCount = readInt(readByte, Hpack.PREFIX_5_BITS);
                    if (this.maxDynamicTableByteCount < 0 || this.maxDynamicTableByteCount > this.headerTableSizeSetting) {
                        throw new IOException("Invalid dynamic table size update " + this.maxDynamicTableByteCount);
                    }
                    adjustDynamicTableByteCount();
                } else if (readByte == 16 || readByte == 0) {
                    readLiteralHeaderWithoutIndexingNewName();
                } else {
                    readLiteralHeaderWithoutIndexingIndexedName(readInt(readByte, Hpack.PREFIX_4_BITS) - 1);
                }
            }
        }

        public final List<Header> getAndResetHeaderList() {
            ArrayList arrayList = new ArrayList(this.headerList);
            this.headerList.clear();
            return arrayList;
        }

        private void readIndexedHeader(int i) {
            if (isStaticHeader(i)) {
                this.headerList.add(Hpack.STATIC_HEADER_TABLE[i]);
                return;
            }
            int dynamicTableIndex = dynamicTableIndex(i - Hpack.STATIC_HEADER_TABLE.length);
            if (dynamicTableIndex < 0 || dynamicTableIndex > this.dynamicTable.length - 1) {
                throw new IOException("Header index too large " + (i + 1));
            }
            this.headerList.add(this.dynamicTable[dynamicTableIndex]);
        }

        private int dynamicTableIndex(int i) {
            return this.nextHeaderIndex + 1 + i;
        }

        private void readLiteralHeaderWithoutIndexingIndexedName(int i) {
            this.headerList.add(new Header(getName(i), readByteString()));
        }

        private void readLiteralHeaderWithoutIndexingNewName() {
            this.headerList.add(new Header(Hpack.checkLowercase(readByteString()), readByteString()));
        }

        private void readLiteralHeaderWithIncrementalIndexingIndexedName(int i) {
            insertIntoDynamicTable(-1, new Header(getName(i), readByteString()));
        }

        private void readLiteralHeaderWithIncrementalIndexingNewName() {
            insertIntoDynamicTable(-1, new Header(Hpack.checkLowercase(readByteString()), readByteString()));
        }

        private ByteString getName(int i) {
            if (isStaticHeader(i)) {
                return Hpack.STATIC_HEADER_TABLE[i].name;
            }
            return this.dynamicTable[dynamicTableIndex(i - Hpack.STATIC_HEADER_TABLE.length)].name;
        }

        private boolean isStaticHeader(int i) {
            return i >= 0 && i <= Hpack.STATIC_HEADER_TABLE.length + -1;
        }

        private void insertIntoDynamicTable(int i, Header header) {
            this.headerList.add(header);
            int i2 = header.hpackSize;
            if (i != -1) {
                i2 -= this.dynamicTable[dynamicTableIndex(i)].hpackSize;
            }
            if (i2 > this.maxDynamicTableByteCount) {
                clearDynamicTable();
                return;
            }
            int evictToRecoverBytes = evictToRecoverBytes((this.dynamicTableByteCount + i2) - this.maxDynamicTableByteCount);
            if (i == -1) {
                if (this.headerCount + 1 > this.dynamicTable.length) {
                    Header[] headerArr = new Header[(this.dynamicTable.length * 2)];
                    System.arraycopy(this.dynamicTable, 0, headerArr, this.dynamicTable.length, this.dynamicTable.length);
                    this.nextHeaderIndex = this.dynamicTable.length - 1;
                    this.dynamicTable = headerArr;
                }
                int i3 = this.nextHeaderIndex;
                this.nextHeaderIndex = i3 - 1;
                this.dynamicTable[i3] = header;
                this.headerCount++;
            } else {
                this.dynamicTable[evictToRecoverBytes + dynamicTableIndex(i) + i] = header;
            }
            this.dynamicTableByteCount = i2 + this.dynamicTableByteCount;
        }

        private int readByte() {
            return this.source.readByte() & 255;
        }

        /* access modifiers changed from: package-private */
        public final int readInt(int i, int i2) {
            int i3 = i & i2;
            if (i3 < i2) {
                return i3;
            }
            int i4 = 0;
            while (true) {
                int readByte = readByte();
                if ((readByte & 128) == 0) {
                    return (readByte << i4) + i2;
                }
                i2 += (readByte & Hpack.PREFIX_7_BITS) << i4;
                i4 += 7;
            }
        }

        /* access modifiers changed from: package-private */
        public final ByteString readByteString() {
            int readByte = readByte();
            boolean z = (readByte & 128) == 128;
            int readInt = readInt(readByte, Hpack.PREFIX_7_BITS);
            if (z) {
                return ByteString.m801of(Huffman.get().decode(this.source.readByteArray((long) readInt)));
            }
            return this.source.readByteString((long) readInt);
        }
    }

    private static Map<ByteString, Integer> nameToFirstIndex() {
        LinkedHashMap linkedHashMap = new LinkedHashMap(STATIC_HEADER_TABLE.length);
        for (int i = 0; i < STATIC_HEADER_TABLE.length; i++) {
            if (!linkedHashMap.containsKey(STATIC_HEADER_TABLE[i].name)) {
                linkedHashMap.put(STATIC_HEADER_TABLE[i].name, Integer.valueOf(i));
            }
        }
        return Collections.unmodifiableMap(linkedHashMap);
    }

    final class Writer {
        private static final int SETTINGS_HEADER_TABLE_SIZE = 4096;
        private static final int SETTINGS_HEADER_TABLE_SIZE_LIMIT = 16384;
        Header[] dynamicTable;
        int dynamicTableByteCount;
        private boolean emitDynamicTableSizeUpdate;
        int headerCount;
        int headerTableSizeSetting;
        int maxDynamicTableByteCount;
        int nextHeaderIndex;
        private final Buffer out;
        private int smallestHeaderTableSizeSetting;
        private final boolean useCompression;

        Writer(Buffer buffer) {
            this(SETTINGS_HEADER_TABLE_SIZE, true, buffer);
        }

        Writer(int i, boolean z, Buffer buffer) {
            this.smallestHeaderTableSizeSetting = Integer.MAX_VALUE;
            this.dynamicTable = new Header[8];
            this.nextHeaderIndex = this.dynamicTable.length - 1;
            this.headerCount = 0;
            this.dynamicTableByteCount = 0;
            this.headerTableSizeSetting = i;
            this.maxDynamicTableByteCount = i;
            this.useCompression = z;
            this.out = buffer;
        }

        private void clearDynamicTable() {
            Arrays.fill(this.dynamicTable, (Object) null);
            this.nextHeaderIndex = this.dynamicTable.length - 1;
            this.headerCount = 0;
            this.dynamicTableByteCount = 0;
        }

        private int evictToRecoverBytes(int i) {
            int i2 = 0;
            if (i > 0) {
                int length = this.dynamicTable.length;
                while (true) {
                    length--;
                    if (length < this.nextHeaderIndex || i <= 0) {
                        System.arraycopy(this.dynamicTable, this.nextHeaderIndex + 1, this.dynamicTable, this.nextHeaderIndex + 1 + i2, this.headerCount);
                        Arrays.fill(this.dynamicTable, this.nextHeaderIndex + 1, this.nextHeaderIndex + 1 + i2, (Object) null);
                        this.nextHeaderIndex += i2;
                    } else {
                        i -= this.dynamicTable[length].hpackSize;
                        this.dynamicTableByteCount -= this.dynamicTable[length].hpackSize;
                        this.headerCount--;
                        i2++;
                    }
                }
                System.arraycopy(this.dynamicTable, this.nextHeaderIndex + 1, this.dynamicTable, this.nextHeaderIndex + 1 + i2, this.headerCount);
                Arrays.fill(this.dynamicTable, this.nextHeaderIndex + 1, this.nextHeaderIndex + 1 + i2, (Object) null);
                this.nextHeaderIndex += i2;
            }
            return i2;
        }

        private void insertIntoDynamicTable(Header header) {
            int i = header.hpackSize;
            if (i > this.maxDynamicTableByteCount) {
                clearDynamicTable();
                return;
            }
            evictToRecoverBytes((this.dynamicTableByteCount + i) - this.maxDynamicTableByteCount);
            if (this.headerCount + 1 > this.dynamicTable.length) {
                Header[] headerArr = new Header[(this.dynamicTable.length * 2)];
                System.arraycopy(this.dynamicTable, 0, headerArr, this.dynamicTable.length, this.dynamicTable.length);
                this.nextHeaderIndex = this.dynamicTable.length - 1;
                this.dynamicTable = headerArr;
            }
            int i2 = this.nextHeaderIndex;
            this.nextHeaderIndex = i2 - 1;
            this.dynamicTable[i2] = header;
            this.headerCount++;
            this.dynamicTableByteCount = i + this.dynamicTableByteCount;
        }

        /* access modifiers changed from: package-private */
        public final void writeHeaders(List<Header> list) {
            int i;
            int i2;
            if (this.emitDynamicTableSizeUpdate) {
                if (this.smallestHeaderTableSizeSetting < this.maxDynamicTableByteCount) {
                    writeInt(this.smallestHeaderTableSizeSetting, Hpack.PREFIX_5_BITS, 32);
                }
                this.emitDynamicTableSizeUpdate = false;
                this.smallestHeaderTableSizeSetting = Integer.MAX_VALUE;
                writeInt(this.maxDynamicTableByteCount, Hpack.PREFIX_5_BITS, 32);
            }
            int size = list.size();
            for (int i3 = 0; i3 < size; i3++) {
                Header header = list.get(i3);
                ByteString asciiLowercase = header.name.toAsciiLowercase();
                ByteString byteString = header.value;
                Integer num = Hpack.NAME_TO_FIRST_INDEX.get(asciiLowercase);
                if (num != null) {
                    int intValue = num.intValue() + 1;
                    if (intValue > 1 && intValue < 8) {
                        if (Util.equal(Hpack.STATIC_HEADER_TABLE[intValue - 1].value, byteString)) {
                            i = intValue;
                            i2 = intValue;
                        } else if (Util.equal(Hpack.STATIC_HEADER_TABLE[intValue].value, byteString)) {
                            i2 = intValue + 1;
                            i = intValue;
                        }
                    }
                    i = intValue;
                    i2 = -1;
                } else {
                    i = -1;
                    i2 = -1;
                }
                if (i2 == -1) {
                    int i4 = this.nextHeaderIndex + 1;
                    int length = this.dynamicTable.length;
                    while (true) {
                        if (i4 >= length) {
                            break;
                        }
                        if (Util.equal(this.dynamicTable[i4].name, asciiLowercase)) {
                            if (Util.equal(this.dynamicTable[i4].value, byteString)) {
                                i2 = (i4 - this.nextHeaderIndex) + Hpack.STATIC_HEADER_TABLE.length;
                                break;
                            } else if (i == -1) {
                                i = (i4 - this.nextHeaderIndex) + Hpack.STATIC_HEADER_TABLE.length;
                            }
                        }
                        i4++;
                    }
                }
                if (i2 != -1) {
                    writeInt(i2, Hpack.PREFIX_7_BITS, 128);
                } else if (i == -1) {
                    this.out.writeByte(64);
                    writeByteString(asciiLowercase);
                    writeByteString(byteString);
                    insertIntoDynamicTable(header);
                } else if (!asciiLowercase.startsWith(Header.PSEUDO_PREFIX) || Header.TARGET_AUTHORITY.equals(asciiLowercase)) {
                    writeInt(i, Hpack.PREFIX_6_BITS, 64);
                    writeByteString(byteString);
                    insertIntoDynamicTable(header);
                } else {
                    writeInt(i, Hpack.PREFIX_4_BITS, 0);
                    writeByteString(byteString);
                }
            }
        }

        /* access modifiers changed from: package-private */
        public final void writeInt(int i, int i2, int i3) {
            if (i < i2) {
                this.out.writeByte(i3 | i);
                return;
            }
            this.out.writeByte(i3 | i2);
            int i4 = i - i2;
            while (i4 >= 128) {
                this.out.writeByte((i4 & Hpack.PREFIX_7_BITS) | 128);
                i4 >>>= 7;
            }
            this.out.writeByte(i4);
        }

        /* access modifiers changed from: package-private */
        public final void writeByteString(ByteString byteString) {
            if (!this.useCompression || Huffman.get().encodedLength(byteString) >= byteString.size()) {
                writeInt(byteString.size(), Hpack.PREFIX_7_BITS, 0);
                this.out.write(byteString);
                return;
            }
            Buffer buffer = new Buffer();
            Huffman.get().encode(byteString, buffer);
            ByteString readByteString = buffer.readByteString();
            writeInt(readByteString.size(), Hpack.PREFIX_7_BITS, 128);
            this.out.write(readByteString);
        }

        /* access modifiers changed from: package-private */
        public final void setHeaderTableSizeSetting(int i) {
            this.headerTableSizeSetting = i;
            int min = Math.min(i, SETTINGS_HEADER_TABLE_SIZE_LIMIT);
            if (this.maxDynamicTableByteCount != min) {
                if (min < this.maxDynamicTableByteCount) {
                    this.smallestHeaderTableSizeSetting = Math.min(this.smallestHeaderTableSizeSetting, min);
                }
                this.emitDynamicTableSizeUpdate = true;
                this.maxDynamicTableByteCount = min;
                adjustDynamicTableByteCount();
            }
        }

        private void adjustDynamicTableByteCount() {
            if (this.maxDynamicTableByteCount >= this.dynamicTableByteCount) {
                return;
            }
            if (this.maxDynamicTableByteCount == 0) {
                clearDynamicTable();
            } else {
                evictToRecoverBytes(this.dynamicTableByteCount - this.maxDynamicTableByteCount);
            }
        }
    }

    static ByteString checkLowercase(ByteString byteString) {
        int i = 0;
        int size = byteString.size();
        while (i < size) {
            byte b = byteString.getByte(i);
            if (b < 65 || b > 90) {
                i++;
            } else {
                throw new IOException("PROTOCOL_ERROR response malformed: mixed case name: " + byteString.utf8());
            }
        }
        return byteString;
    }
}
