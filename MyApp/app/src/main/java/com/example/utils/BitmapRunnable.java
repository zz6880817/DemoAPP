package com.example.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Administrator on 2016/3/11.
 */
public abstract class BitmapRunnable implements Runnable{
    private String murl;
    public BitmapRunnable(String url){
        this.murl = url;
    }


    @Override
    public void run() {
        try {
            HttpURLConnection conn = (HttpURLConnection) new URL(murl).openConnection();
            conn.setRequestMethod("GET");
            conn.setReadTimeout(5000);
            conn.setConnectTimeout(10 * 1000);

            if(conn.getResponseCode() == 200){
                InputStream in = conn.getInputStream();
                Bitmap bitmap = BitmapFactory.decodeStream(in);
                if(bitmap != null){
                    getBitmap(bitmap);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public abstract void getBitmap(Bitmap bitmap);

}
