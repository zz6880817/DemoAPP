package com.example.utils;

import android.widget.Toast;

import com.example.base.BaseApplication;

/**
 * Created by Administrator on 2016/3/9.
 */
public class ToastUtils {
    //在UI线程
    public static void showToastInUIThread(String content){
        Toast.makeText(BaseApplication.getContext(), content, Toast.LENGTH_SHORT).show();
    }

    //在工作线程
    public static void showToastInThread(final String content){
        ThreadPoolUtils.runTaskInThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(BaseApplication.getContext(), content, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
