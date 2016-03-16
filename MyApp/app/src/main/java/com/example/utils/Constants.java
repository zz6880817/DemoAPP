package com.example.utils;

import android.os.Environment;

import java.io.File;

public interface Constants {
    public interface URL{
    	public static final String CLOSEURL = "http://api2.17zwd.com/rest/goods/search/?zdid=42&keyword=&from=mobile";
        public static final String CLOSEDETAILURL = "http://api2.17zwd.com/rest/goods/get_item/?zdid=42&goods_id=%d&from=mobile";
        public static final String LONGIMAGURL = "https://img.alicdn.com/imgextra/i4/1671109767/TB2JvIrgXXXXXctXpXXXXXXXXXX_!!1671109767.jpg";
    }
    
    public interface Path{
    	public static final File ImageCachePATH = new File(
				Environment.getExternalStorageDirectory()
						+ "/MAPP/iamges/");
    }
}
