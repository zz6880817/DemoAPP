package com.example.utils;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 线程代理类
 */
public class ThreadPoolProxy {
    private ThreadPoolExecutor mExecutor;
    private int corePoolSize;
    private int maximumPoolSize;
    private long keepAliveTime;

    public ThreadPoolProxy(int corePoolSize, int maximumPoolSize, long keepAliveTime) {
        this.corePoolSize = corePoolSize;
        this.maximumPoolSize = maximumPoolSize;
        this.keepAliveTime = keepAliveTime;
    }
    //双重锁机制
    public ThreadPoolExecutor initThreadPoolExecutor() {
        if (mExecutor == null) {
            synchronized (ThreadPoolProxy.class) {
                if (mExecutor == null) {
                    BlockingQueue<Runnable> workQueue = new LinkedBlockingDeque<Runnable>();
                    ThreadFactory threadFactory = Executors.defaultThreadFactory();
                    RejectedExecutionHandler handler = new ThreadPoolExecutor.AbortPolicy();
                    mExecutor = new ThreadPoolExecutor(corePoolSize,
                            maximumPoolSize,
                            keepAliveTime,
                            TimeUnit.SECONDS,
                            workQueue,
                            threadFactory,
                            handler);
                }
            }
        }
        return mExecutor;
    }

    //试行任务
    public void execute(Runnable task) {
        initThreadPoolExecutor();
        mExecutor.execute(task);
    }

    //移除任务
    public void remove(Runnable task) {
        initThreadPoolExecutor();
        mExecutor.remove(task);
    }

    //提交任务(Future+execute(Runnable task)
    public Future<?> submit(Runnable task) {
        initThreadPoolExecutor();
        return mExecutor.submit(task);
    }
}
