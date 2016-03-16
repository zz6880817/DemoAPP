package com.example.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

/**
 * 网络加载图片
 * Created by CXX on 2016/3/10.
 */
public abstract class BitmapRunable implements Runnable{
    private InputStream in;
    private String url;
    private Bitmap bm;
    public BitmapRunable(String url){
           this.url = url;
    }
    @Override
    public void run() {
        HttpURLConnection conn = null;
        try {
            conn = (HttpURLConnection)new URL(url)
                    .openConnection();
            conn.setRequestMethod("GET");
            conn.setConnectTimeout(2000);
            conn.setReadTimeout(10*2000);

            if(conn.getResponseCode() == 200){
               in = conn.getInputStream();
               Bitmap bm = BitmapFactory.decodeStream(in);
                if(bm != null) {
                    onSuccess(bm);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public abstract void onSuccess(Bitmap bitmap);
}
