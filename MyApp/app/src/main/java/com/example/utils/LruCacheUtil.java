package com.example.utils;

import android.graphics.Bitmap;
import android.util.LruCache;

/**
 * 单例缓存
 */
public class LruCacheUtil {
    private static LruCacheUtil instance = new LruCacheUtil();
    public LruCache<String, Bitmap> lruCache = null;
    private LruCacheUtil(){
		long maxMemory = Runtime.getRuntime().maxMemory();
		int maxSize = (int) maxMemory / 4;
		lruCache = new LruCache<String, Bitmap>(maxSize) {
			// Returns the number of bytes used to store this bitmap's pixels.
			protected int sizeOf(String key, Bitmap value) {
				return value.getHeight() * value.getRowBytes();
			}
		};
    }
    
    public static LruCacheUtil getInstance() {  
        return instance;  
        } 


}
