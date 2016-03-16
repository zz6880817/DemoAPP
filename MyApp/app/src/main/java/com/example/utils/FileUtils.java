package com.example.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;


/**
 * 文件操作类
 * 
 * @author Administrator
 */
public class FileUtils {

	public static String APP_PATH = Environment.getExternalStorageDirectory().getPath()+"/IMAGEDEMO";

	public static InputStream Bitmap2IS(Bitmap bm){
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		bm.compress(Bitmap.CompressFormat.JPEG, 100, baos);
		InputStream sbs = new ByteArrayInputStream(baos.toByteArray());
		return sbs;
	}
	
	/**
	 * 取文件
	 * @param url
	 * @return
	 */
	public static Bitmap getBitmapFile(String url){
		String imgName=url.substring(url.lastIndexOf("/")+1,url.length());
		String filePath=APP_PATH+"/"+imgName+".png";
		
		
		File file=new File(filePath);
		if(!file.exists()){
			return null;
		}
		
		FileInputStream fis=null;
		Bitmap bitmap = null;
		try {
			fis=new FileInputStream(filePath);
			bitmap=BitmapFactory.decodeStream(fis);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				fis.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		
		return bitmap;
	}

	
	/**
	 * 存文件
	 * @param bitmap
	 * @param
	 */
	public static void putBitmapFile(Bitmap bitmap, String url) {
		
		String imgName=url.substring(url.lastIndexOf("/")+1,url.length());
		String filePath = APP_PATH + "/" + imgName + ".png";
		File appFile = new File(APP_PATH);

		if (!appFile.exists()) {
			appFile.mkdirs();
		}

		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream(filePath);
			bitmap.compress(Bitmap.CompressFormat.PNG, 30, fos);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				fos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

}
