package com.example.administrator.imagedemo;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.example.adapter.RecycleAdapter;
import com.example.base.BaseApplication;
import com.example.base.CommonUtils;
import com.example.base.Constants;
import com.example.bean.Bean;
import com.google.gson.Gson;

/**
 * Created by Administrator on 2016/3/11.
 */
public class RecyclerViewActivity extends Activity implements CommonUtils.OnRequestListener{
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler);
        recyclerView = (RecyclerView) findViewById(R.id.recycler);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        //设置Item增加、移除动画
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        CommonUtils.loadString(Constants.URL.CLOSEURL,this);
    }

    @Override
    public void onSuccess(String response) {
        if(response != null){
            Gson gson = new Gson();
            Bean bean = gson.fromJson(response, Bean.class);
            RecycleAdapter adapter = new RecycleAdapter(this,bean);
            adapter.setOnItemClickListener(new RecycleAdapter.OnItemClickListener() {
                @Override
                public void onClick(int position, View view) {
                        Toast.makeText(BaseApplication.getContext(),"点击了"+position,Toast.LENGTH_SHORT).show();
                }
            });
            recyclerView.setAdapter(adapter);
        }
    }

    @Override
    public void onError(VolleyError error) {

    }
}
