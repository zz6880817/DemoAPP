package com.example.administrator.myapp;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapRegionDecoder;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.example.bean.DetailBean;
import com.example.utils.BigBitmapUtils;
import com.example.utils.BitmapRunable;
import com.example.utils.CommonUtils;
import com.example.utils.Constants;
import com.example.utils.FileUtils;
import com.example.utils.L;
import com.example.utils.ThreadPoolUtils;
import com.example.utils.ToastUtils;
import com.facebook.drawee.view.SimpleDraweeView;
import com.google.gson.Gson;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.shizhefei.view.largeimage.LongImageView;

import java.io.InputStream;
import java.util.logging.LogRecord;

/**
 * 详细页
 * Created by Administrator on 2016/3/9.
 */
public class ItemDetailActivity extends Activity implements CommonUtils.OnRequestListener{
    private int goodId;
    private String mUrl;
    private ImageView ivDetailTop,ivSale;
    private TextView tvDetailTitle,tvDetailPrice,tvSaleName,tvSaleAddress;
    private LongImageView longimageView;
    private Button btnSubmit;
    private DetailBean mBean;
    private DetailBean.GoodsItemGetResponseEntity.ItemEntity mEntity;
    private ImageLoader mLoader;
    private RelativeLayout rl_load;

    private  Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if(msg != null){
               InputStream in = (InputStream) msg.obj;
                longimageView.setImage(in);
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_itemdetail);
        goodId = getIntent().getIntExtra("ID", -1);
        mUrl = String.format(Constants.URL.CLOSEDETAILURL, goodId);
        initView();
        CommonUtils.loadString(mUrl, this);
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtils.showToastInUIThread("点击了不知道要干嘛...");
            }
        });


    }

    private void initView() {
        ivDetailTop = (ImageView) findViewById(R.id.iv_detail_top);
        ivSale = (ImageView) findViewById(R.id.iv_sale);
        tvDetailTitle = (TextView) findViewById(R.id.tv_detail_title);
        tvDetailPrice = (TextView) findViewById(R.id.tv_detail_price);
        tvSaleName = (TextView) findViewById(R.id.tv_sale_name);
        tvSaleAddress = (TextView) findViewById(R.id.tv_sale_address);
        btnSubmit = (Button) findViewById(R.id.btn_submit);
        longimageView = (LongImageView) findViewById(R.id.longimageView);
        rl_load = (RelativeLayout) findViewById(R.id.rl_load);
    }

    /**
     * 成功回调
     * @param response
     */
    @Override
    public void onSuccess(String response) {
          if(response == null){
              return;}
          rl_load.setVisibility(View.GONE);
          handleDatas(response);
    }

    /**
     * 处理数据
     * @param response
     */
    private void handleDatas(String response) {
        Gson gson = new Gson();
        mBean = gson.fromJson(response, DetailBean.class);
        mEntity = mBean.getGoods_item_get_response().getItem();
        //商品描述头部
        tvDetailTitle.setText(mEntity.getTitle());
        //价格
        tvDetailPrice.setText("￥"+mEntity.getPrice1());
        //店铺名称
        tvSaleName.setText(mEntity.getShop_name());
        //店铺地址
        tvSaleAddress.setText("[" + mEntity.getShop_dangkou() + "]" + mEntity.getShop_market() + "-" + mEntity.getShop_floor());
        //加载图片
        mLoader = ImageLoader.getInstance();
        mLoader.displayImage("http:" + mEntity.getTb_img(), ivDetailTop, CommonUtils.normalOptions(R.drawable.ic_launcher, R.drawable.ic_launcher));
// mLoader.displayImage(Constants.URL.LONGIMAGURL,ivBigdetail,CommonUtils.normalOptions(R.drawable.ic_launcher, R.drawable.ic_launcher));
        BigBitmapUtils bmUtil = new BigBitmapUtils();
        bmUtil.getBitmapByCache(Constants.URL.LONGIMAGURL, new BigBitmapUtils.LoadCompleteListener() {
            @Override
            public void complete(Bitmap bitmap) {
                if(bitmap != null){
                    InputStream in = FileUtils.Bitmap2IS(bitmap);
                        longimageView.setImage(in);
               }
            }
        });
    }

    /**
     * 失败回调
     * @param error
     */
    @Override
    public void onError(VolleyError error) {
        ToastUtils.showToastInUIThread("联网失败...");
    }
}
