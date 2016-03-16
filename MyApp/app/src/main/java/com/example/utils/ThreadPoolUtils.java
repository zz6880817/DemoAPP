package com.example.utils;

import android.os.Handler;

/**
 * 线程池工具类
 */
public class ThreadPoolUtils {
    //在非UI线程中执行
    public static void runTaskInThread(Runnable task){
          ThreadPoolFactory.getCommonThreadPool().execute(task);
    }

    //在UI线程中执行
    private static Handler handler = new Handler();
    public static void runTaskInUIThread(Runnable task){
             handler.post(task);
    }
}
