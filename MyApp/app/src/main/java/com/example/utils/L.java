package com.example.utils;

import android.util.Log;

/**
 * @author CXX
 *
 */
public class L{
   private static String TAG = "CXX";
   private static boolean DEBUG = true;
 
   public static void d(String logContent){
	   Log.d(TAG, logContent);
   }
   
   public static void e(String logContent){
	  Log.e(TAG, logContent);
   }
}
