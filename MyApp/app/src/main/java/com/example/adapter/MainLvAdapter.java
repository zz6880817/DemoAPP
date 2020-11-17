package com.example.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.myapp.R;
import com.example.bean.Bean.GoodsItemsListGetResponseEntity.ItemsEntity;

import com.example.bean.Bean;
import com.example.utils.CommonUtils;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/3/8.
 */
public class MainLvAdapter extends BaseAdapter {
    private Context mContext;
    private List<ItemsEntity> mDatas;
    private LayoutInflater inflater;
    private ImageLoader loader;

    public MainLvAdapter(Context context) {
        this.mContext = context;
        mDatas = new ArrayList<ItemsEntity>();
        inflater = LayoutInflater.from(context);

    }

    public void setDatas(Bean mData) {
        this.mDatas = mData.getGoods_items_list_get_response().getItems();
        this.notifyDataSetChanged();
    }

    public void addDatas(Bean mData) {
        this.mDatas.addAll(mData.getGoods_items_list_get_response().getItems());
        this.notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return mDatas.size();
    }

    @Override
    public Object getItem(int position) {
        return mDatas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.lv_main_item, parent, false);
            holder = new ViewHolder();
            holder.ivPhoto = (ImageView) convertView.findViewById(R.id.iv);
            holder.tvTitle = (TextView) convertView.findViewById(R.id.tv_title);
            holder.tvContent = (TextView) convertView.findViewById(R.id.tv_content);
            holder.lineView = convertView.findViewById(R.id.line);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        if(position == mDatas.size() - 1){
            holder.lineView.setVisibility(View.GONE);
        }else{
            holder.lineView.setVisibility(View.VISIBLE);
        }
        holder.tvTitle.setText(mDatas.get(position).getTitle());
        holder.tvContent.setText("￥"+mDatas.get(position).getPrice2() + "");
        loader = ImageLoader.getInstance();
        loader.displayImage(mDatas.get(position).getTb_img(), holder.ivPhoto, CommonUtils.normalOptions(R.drawable.ic_launcher, R.drawable.ic_launcher));
        return convertView;
    }

    static class ViewHolder {
        ImageView ivPhoto;
        TextView tvTitle;
        TextView tvContent;
        View lineView;
    }
}
