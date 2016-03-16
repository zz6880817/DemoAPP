package com.example.base;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Environment;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.nostra13.universalimageloader.cache.memory.impl.LruMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.download.BaseImageDownloader;

import java.io.File;

/**
 * Created by Administrator on 2016/3/8.
 */
public class BaseApplication extends Application{
    private static Context mContext;
    private static SharedPreferences sp;
    private static SharedPreferences.Editor ed;
    private ImageLoaderConfiguration mConfiguration;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
        initShare();
        initConfiguration();

    }

    /**
     * 初始化ImageLoader框架配置参数
     */
    private void initConfiguration() {
        mConfiguration = new ImageLoaderConfiguration.Builder(mContext)
                .memoryCacheExtraOptions(400, 800)
                .threadPoolSize(6)  //设置线程池的大小
                .threadPriority(Thread.NORM_PRIORITY - 2)
                .denyCacheImageMultipleSizesInMemory()
                .discCacheSize(50 * 1024 *1024) // 设置磁盘缓存的大小
                .memoryCache(new LruMemoryCache((int)Runtime.getRuntime().maxMemory() / 8))
                .memoryCacheSize((int)Runtime.getRuntime().maxMemory() / 8) // 设置内存缓存的大小
                .defaultDisplayImageOptions(DisplayImageOptions.createSimple())
                .imageDownloader(new BaseImageDownloader(mContext, 5 * 1000, 30 * 1000))
                .writeDebugLogs() //Remove for release App
                .build();
        ImageLoader.getInstance().init(mConfiguration);
    }
    /**
     * 根据SD卡的状态来获取照片存储到存储卡的位置
     * @param context 上下文
     * @param uniqueName  保存路径的包名
     * @return
     */
    private File getDiskCacheDir(Context context, String uniqueName)
    {
        String cachePath;
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())
                || !Environment.isExternalStorageRemovable()){
            cachePath = context.getExternalCacheDir().getPath();
        }else{
            cachePath = context.getCacheDir().getPath();
        }
        return new File(cachePath + File.separator + uniqueName);
    }

    public static Context getContext(){
        return mContext;
    }
    public static void putString(String key,String content){
         ed.putString(key,content);
         ed.commit();
    }
    public static String getString(String key){
         return sp.getString(key,null);
    }

    /**
     * 初始化SharedPreferences
     */
    private void initShare() {
        sp = getSharedPreferences("config",MODE_PRIVATE);
        ed = sp.edit();
    }


}
