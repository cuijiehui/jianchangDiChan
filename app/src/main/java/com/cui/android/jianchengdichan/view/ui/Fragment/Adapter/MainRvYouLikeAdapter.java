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
import com.cui.android.jianchengdichan.view.ui.Fragment.Adapter.AdapterBean.NewGoodsBean;
import com.cui.android.jianchengdichan.view.ui.Fragment.Adapter.AdapterBean.YouLikeBean;

import java.util.ArrayList;
import java.util.List;

public class MainRvYouLikeAdapter extends RecyclerView.Adapter<MainRvYouLikeAdapter.ViewHolder>{

    private List<HomeDataBean.FavorBean> dataList = new ArrayList<>();

    public MainRvYouLikeAdapter(List<HomeDataBean.FavorBean> dataList) {
        this.dataList = dataList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ViewHolder viewHolder = new ViewHolder( LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_you_like_layout,parent,false));
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        HomeDataBean.FavorBean favorBean = dataList.get(position);
        holder.tv_you_like_name.setText(favorBean.getShorttitle());
        holder.tv_you_like_price.setText(favorBean.getMarketprice()+"元");
        holder.tv_you_like_price_end.setText(favorBean.getMarketprice()+"元");
        holder.tv_you_like_content.setText(favorBean.getTitle());
        Glide.with(MyApplication.getAppContext()).load(favorBean.getThumb()).into(holder.iv_you_like_pic);

    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    class ViewHolder extends  RecyclerView.ViewHolder{
        ImageView iv_you_like_pic, iv_you_like_add_shop;
        TextView tv_you_like_name,tv_you_like_price,tv_you_like_content,tv_you_like_price_end,tv_like_shop,tv_like_service,tv_like_collect;
        public ViewHolder(View itemView) {
            super(itemView);
            iv_you_like_pic= itemView.findViewById(R.id.iv_you_like_pic);
            iv_you_like_add_shop= itemView.findViewById(R.id.iv_you_like_add_shop);
            tv_you_like_name= itemView.findViewById(R.id.tv_you_like_name);
            tv_you_like_price= itemView.findViewById(R.id.tv_you_like_price);
            tv_you_like_content= itemView.findViewById(R.id.tv_you_like_content);
            tv_you_like_price_end= itemView.findViewById(R.id.tv_you_like_price_end);
            tv_like_shop= itemView.findViewById(R.id.tv_like_shop);
            tv_like_service= itemView.findViewById(R.id.tv_like_service);
            tv_like_collect= itemView.findViewById(R.id.tv_like_collect);
        }
    }
}
