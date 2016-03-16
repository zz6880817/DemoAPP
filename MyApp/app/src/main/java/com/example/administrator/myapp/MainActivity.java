package com.example.administrator.myapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.android.volley.VolleyError;
import com.example.adapter.MainLvAdapter;
import com.example.bean.Bean;
import com.example.utils.CommonUtils;
import com.example.utils.Constants;
import com.example.utils.L;
import com.example.utils.ToastUtils;
import com.google.gson.Gson;
import com.lidroid.xutils.BitmapUtils;


public class MainActivity extends Activity implements CommonUtils.OnRequestListener,AdapterView.OnItemClickListener{
    private MainLvAdapter adapter;
    private ListView lv;
    private Bean mBean;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_lv);
        lv = (ListView) findViewById(R.id.lv);
        CommonUtils.loadString(Constants.URL.CLOSEURL, this);

    }

    @Override
    protected void onResume() {
        super.onResume();
        initListener();
    }

    /**
     * 初始化监听
     */
    private void initListener() {
        lv.setOnItemClickListener(this);
    }

    @Override
    public void onSuccess(String response) {
        if(response == null) return;
        handleDatas(response);
    }

    /**
     * 处理成功返回的数据
     * @param response
     */
    private void handleDatas(String response) {
        Gson gson = new Gson();
        mBean = gson.fromJson(response, Bean.class);
        adapter = new MainLvAdapter(this);
        adapter.setDatas(mBean);
        lv.setAdapter(adapter);
    }

    @Override
    public void onError(VolleyError error) {
        ToastUtils.showToastInUIThread("联网失败");
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent();
        intent.setClass(MainActivity.this, ItemDetailActivity.class);
        if(mBean != null) {
            intent.putExtra("ID", mBean.getGoods_items_list_get_response().getItems().get(position).getGoods_id());
        }
        startActivity(intent);

    }
}
