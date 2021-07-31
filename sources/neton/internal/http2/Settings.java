package neton.internal.http2;

import java.util.Arrays;

public final class Settings {
    static final int COUNT = 10;
    static final int DEFAULT_INITIAL_WINDOW_SIZE = 65535;
    static final int ENABLE_PUSH = 2;
    static final int HEADER_TABLE_SIZE = 1;
    static final int INITIAL_WINDOW_SIZE = 7;
    static final int MAX_CONCURRENT_STREAMS = 4;
    static final int MAX_FRAME_SIZE = 5;
    static final int MAX_HEADER_LIST_SIZE = 6;
    private int set;
    private final int[] values = new int[COUNT];

    /* access modifiers changed from: package-private */
    public final void clear() {
        this.set = 0;
        Arrays.fill(this.values, 0);
    }

    /* access modifiers changed from: package-private */
    public final Settings set(int i, int i2) {
        if (i >= 0 && i < this.values.length) {
            this.set = (1 << i) | this.set;
            this.values[i] = i2;
        }
        return this;
    }

    /* access modifiers changed from: package-private */
    public final boolean isSet(int i) {
        if (((1 << i) & this.set) != 0) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: package-private */
    public final int get(int i) {
        return this.values[i];
    }

    /* access modifiers changed from: package-private */
    public final int size() {
        return Integer.bitCount(this.set);
    }

    /* access modifiers changed from: package-private */
    public final int getHeaderTableSize() {
        if ((this.set & 2) != 0) {
            return this.values[1];
        }
        return -1;
    }

    /* access modifiers changed from: package-private */
    public final boolean getEnablePush(boolean z) {
        return ((this.set & 4) != 0 ? this.values[2] : z ? 1 : 0) == 1;
    }

    /* access modifiers changed from: package-private */
    public final int getMaxConcurrentStreams(int i) {
        return (this.set & 16) != 0 ? this.values[4] : i;
    }

    /* access modifiers changed from: package-private */
    public final int getMaxFrameSize(int i) {
        return (this.set & 32) != 0 ? this.values[5] : i;
    }

    /* access modifiers changed from: package-private */
    public final int getMaxHeaderListSize(int i) {
        return (this.set & 64) != 0 ? this.values[MAX_HEADER_LIST_SIZE] : i;
    }

    /* access modifiers changed from: package-private */
    public final int getInitialWindowSize() {
        return (this.set & 128) != 0 ? this.values[INITIAL_WINDOW_SIZE] : DEFAULT_INITIAL_WINDOW_SIZE;
    }

    /* access modifiers changed from: package-private */
    public final void merge(Settings settings) {
        for (int i = 0; i < COUNT; i++) {
            if (settings.isSet(i)) {
                set(i, settings.get(i));
            }
        }
    }
}
