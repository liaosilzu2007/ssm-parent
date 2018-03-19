package com.lzumetal.mybatispages.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ExecutorUtil {

    private static ExecutorService cachedThreadPool = Executors.newCachedThreadPool();

    public static void execute(Runnable command) {
        cachedThreadPool.execute(command);
    }

    public static Future submit(Callable command) {
        return cachedThreadPool.submit(command);
    }

}
