package com.example.utils;

/**
 * 线程池工厂类
 */
public class ThreadPoolFactory {
    private static ThreadPoolProxy commonThreadPool;

    public static final int Common_CORE_POOL_SIZE = 5;
    public static final int Common_MAX_POOL_SIZE = 5;
    public static final int Common_KEEP_LIVE_TIME = 1;

    //单例模式
    public static ThreadPoolProxy getCommonThreadPool() {
        if (commonThreadPool == null) {
            synchronized (ThreadPoolFactory.class) {
                if (commonThreadPool == null) {
                    commonThreadPool = new ThreadPoolProxy(Common_CORE_POOL_SIZE, Common_MAX_POOL_SIZE, Common_KEEP_LIVE_TIME);
                }
            }
        }
        return commonThreadPool;
    }
}
