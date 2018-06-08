package com.cui.android.jianchengdichan.view.ui.Fragment.Adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.cui.android.jianchengdichan.MyApplication;
import com.cui.android.jianchengdichan.R;
import com.cui.android.jianchengdichan.http.bean.HomeDataBean;
import com.cui.android.jianchengdichan.utils.Okhttp3Utils;
import com.cui.android.jianchengdichan.view.ui.Fragment.Adapter.AdapterBean.CommunityBean;
import com.cui.android.jianchengdichan.view.ui.Fragment.Adapter.AdapterBean.NewGoodsBean;

import java.util.ArrayList;
import java.util.List;

public class MainRvNewGoodsAdapter extends RecyclerView.Adapter<MainRvNewGoodsAdapter.ViewHolder>{

    private List<HomeDataBean.NewgoodBean> dataList = new ArrayList<>();
    public MainRvNewGoodsAdapter(List<HomeDataBean.NewgoodBean> dataList) {
        this.dataList = dataList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ViewHolder viewHolder = new ViewHolder( LayoutInflater.from(parent.getContext()).inflate(R.layout.item_new_goods_layout,parent,false));
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        HomeDataBean.NewgoodBean newgoods = dataList.get(position);
        holder.tv_new_goods_name.setText(newgoods.getShorttitle());
        holder.tv_new_goods_price.setText(newgoods.getMarketprice());
        Okhttp3Utils.getInstance().glide(MyApplication.getAppContext(),newgoods.getThumb(),holder.iv_new_goods_pic);


    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    class ViewHolder extends  RecyclerView.ViewHolder{
        ImageView iv_new_goods_add, iv_new_goods_pic;
        TextView tv_new_goods_name,tv_new_goods_price;
        public ViewHolder(View itemView) {
            super(itemView);
            iv_new_goods_add= itemView.findViewById(R.id.iv_new_goods_add);
            iv_new_goods_pic= itemView.findViewById(R.id.iv_new_goods_pic);
            tv_new_goods_name= itemView.findViewById(R.id.tv_new_goods_name);
            tv_new_goods_price= itemView.findViewById(R.id.tv_new_goods_price);
        }
    }
}
