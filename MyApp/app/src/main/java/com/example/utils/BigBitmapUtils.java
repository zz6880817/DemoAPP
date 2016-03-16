package com.example.utils;

import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Message;

/**
 * 三级缓存加载图片
 * Created by CXX on 2016/3/10.
 */
public class BigBitmapUtils {
    public LruCacheUtil lruInstance;
    public BigBitmapUtils(){
        lruInstance = LruCacheUtil.getInstance();
    }

    public void getBitmapByCache(final String url, final LoadCompleteListener listener){
        final Handler handler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                Bitmap bitmap = (Bitmap)msg.obj;
                listener.complete(bitmap);
            }
        };
         Bitmap bm = lruInstance.lruCache.get(url);
        //在内存中获取
        if(bm == null){
             //在磁盘获取
             bm = FileUtils.getBitmapFile(url);
             if(bm == null){
                 //取网络获取
                 ThreadPoolUtils.runTaskInThread(new BitmapRunable(url) {
                     @Override
                     public void onSuccess(Bitmap bitmap) {
                         if(bitmap == null) return;
                         //存放在内存和磁盘中
                         lruInstance.lruCache.put(url,bitmap);
                         FileUtils.putBitmapFile(bitmap, url);
                         Message msg = Message.obtain();
                         msg.obj = bitmap;
                         handler.sendMessage(msg);
                     }
                 });
             }else{
                 Message msg = Message.obtain();
                 msg.obj = bm;
                 handler.sendMessage(msg);
             }
         }else{
              Message msg = Message.obtain();
              msg.obj = bm;
              handler.sendMessage(msg);
         }

    }

    public interface LoadCompleteListener{
        /**
         * 当图片下载完成后将通过此接口将bitmap暴露给主线程操作
         * @param bitmap
         */
        public void complete(Bitmap bitmap);
    }
}
