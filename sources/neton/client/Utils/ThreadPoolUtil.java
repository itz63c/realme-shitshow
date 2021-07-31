package neton.client.Utils;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPoolUtil {
    private static ExecutorService mFixedThreadExecutor;
    private static ExecutorService mSingleExecutor;

    public static synchronized void execute(Runnable runnable) {
        synchronized (ThreadPoolUtil.class) {
            if (mFixedThreadExecutor == null) {
                mFixedThreadExecutor = Executors.newFixedThreadPool(10);
            }
            mFixedThreadExecutor.execute(runnable);
        }
    }

    public static synchronized void executeSingle(Runnable runnable) {
        synchronized (ThreadPoolUtil.class) {
            if (mSingleExecutor == null) {
                mSingleExecutor = Executors.newSingleThreadExecutor();
            }
            mSingleExecutor.execute(runnable);
        }
    }

    public static void close() {
        if (mFixedThreadExecutor != null) {
            mFixedThreadExecutor.shutdown();
            mFixedThreadExecutor = null;
        }
        if (mSingleExecutor != null) {
            mSingleExecutor.shutdown();
            mSingleExecutor = null;
        }
    }
}
