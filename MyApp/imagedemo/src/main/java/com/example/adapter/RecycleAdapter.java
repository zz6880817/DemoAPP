package com.example.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.administrator.imagedemo.R;
import com.example.bean.Bean;

import java.util.List;

/**
 * Created by Administrator on 2016/3/11.
 */
public class RecycleAdapter extends RecyclerView.Adapter<RecycleAdapter.MViewHolder>{
    private Context mContext;
    private List<Bean.GoodsItemsListGetResponseEntity.ItemsEntity> entity;
    private OnItemClickListener mOnItemClickListener;


    public RecycleAdapter(Context context,Bean bean){
        this.mContext = context;
        entity = bean.getGoods_items_list_get_response().getItems();
    }
    @Override
    public MViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MViewHolder holder = new MViewHolder(LayoutInflater.from(mContext).inflate(R.layout.lv_main_item,parent,false));
        return holder;
    }

    @Override
    public void onBindViewHolder(MViewHolder holder, final int position) {
             holder.tvContent.setText("*"+entity.get(position).getPrice2());
             holder.tvTitle.setText(entity.get(position).getTitle());
             holder.rl.setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View v) {
                     mOnItemClickListener.onClick(position,v);
                 }
             });
    }

    @Override
    public int getItemCount() {
        return entity.size();
    }

    class MViewHolder extends RecyclerView.ViewHolder{
        TextView tvTitle;
        TextView tvContent;
        RelativeLayout rl;
        public MViewHolder(View itemView) {
            super(itemView);
            tvTitle = (TextView) itemView.findViewById(R.id.tv_title);
            tvContent = (TextView) itemView.findViewById(R.id.tv_content);
            rl = (RelativeLayout) itemView.findViewById(R.id.rl);
        }
    }
    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
          this.mOnItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener{
        public void onClick(int position,View view);
    }
}
