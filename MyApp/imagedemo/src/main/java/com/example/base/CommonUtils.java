package com.example.base;


import android.graphics.Bitmap.Config;

import com.android.volley.RequestQueue;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;


public class CommonUtils {
	private static StringRequest mStringRequest;
	private static RequestQueue mRequestQueue;

	public static RequestQueue newRequestQueue() {
		if (mRequestQueue == null) {
			synchronized (CommonUtils.class) {
				if (mRequestQueue == null) {
					mRequestQueue = Volley.newRequestQueue(BaseApplication
							.getContext());
				}
			}
		}
		return mRequestQueue;
	}

    /**
     * @param url  加载的地址
     * @param onRequestListener
     */
	public static void loadString(String url,
			final OnRequestListener onRequestListener) {
		mRequestQueue = newRequestQueue();
		mStringRequest = new StringRequest(url, new Listener<String>() {
			@Override
			public void onResponse(String response) {
				if (onRequestListener != null) {
					onRequestListener.onSuccess(response);
				}
			}
		}, new ErrorListener() {
			@Override
			public void onErrorResponse(VolleyError error) {
				onRequestListener.onError(error);
			}
		});
		mRequestQueue.add(mStringRequest);
	}

	
	/**
	 * @param loadImg 准备加载的图片
	 * @param errorImg 加载失败的图片资源
	 * @return
	 */
	public static DisplayImageOptions normalOptions(int loadImg,int errorImg){
		DisplayImageOptions options;
		options = new DisplayImageOptions.Builder()
				.showImageOnLoading(loadImg) //设置图片在下载期间显示的图片
				.showImageForEmptyUri(errorImg) //设置图片uri为空或错误的时候显示的图片
				.showImageOnFail(errorImg) //设置图片加载/解码过程中显示的图片
				.cacheInMemory(true) //设置下载的图片是否缓存在内存中
				.cacheOnDisk(true)//设置下载的图片是否缓存在sd卡中
				.considerExifParams(true) //是否考虑JPEG图像EXIF参数（旋转，翻转）
				.bitmapConfig(Config.RGB_565) //设置图片解码的类型
				.imageScaleType(ImageScaleType.NONE) //设置图片样式（不被缩放）
				.resetViewBeforeLoading(true) //设置图片在下载前是否重置，复位
				.build(); //构建完成
		return options;
	}

	public interface OnRequestListener {
		//成功回调
		public void onSuccess(String response);
        //失败回调
		public void onError(VolleyError error);
	}
	

}
