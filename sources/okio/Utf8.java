package okio;

public final class Utf8 {
    private Utf8() {
    }

    public static long size(String str) {
        return size(str, 0, str.length());
    }

    public static long size(String str, int i, int i2) {
        if (str == null) {
            throw new IllegalArgumentException("string == null");
        } else if (i < 0) {
            throw new IllegalArgumentException("beginIndex < 0: " + i);
        } else if (i2 < i) {
            throw new IllegalArgumentException("endIndex < beginIndex: " + i2 + " < " + i);
        } else if (i2 > str.length()) {
            throw new IllegalArgumentException("endIndex > string.length: " + i2 + " > " + str.length());
        } else {
            long j = 0;
            while (i < i2) {
                char charAt = str.charAt(i);
                if (charAt < 128) {
                    i++;
                    j++;
                } else if (charAt < 2048) {
                    i++;
                    j = 2 + j;
                } else if (charAt < 55296 || charAt > 57343) {
                    i++;
                    j = 3 + j;
                } else {
                    char charAt2 = i + 1 < i2 ? str.charAt(i + 1) : 0;
                    if (charAt > 56319 || charAt2 < 56320 || charAt2 > 57343) {
                        i++;
                        j++;
                    } else {
                        i += 2;
                        j = 4 + j;
                    }
                }
            }
            return j;
        }
    }
}
