package neton.client.Utils;

public class ModeUtil {
    public static final int MODE_RELEASE = 0;
    public static final int MODE_TEST = 1;
    private static int sMode = 0;

    public static int getMode() {
        return sMode;
    }

    public static void setMode(int i) {
        if (i < 0 || i > 1) {
            i = 0;
        }
        sMode = i;
    }
}
