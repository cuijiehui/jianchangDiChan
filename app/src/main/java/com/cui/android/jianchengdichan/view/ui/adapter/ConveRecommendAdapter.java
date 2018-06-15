package com.cui.android.jianchengdichan.view.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cui.android.jianchengdichan.MyApplication;
import com.cui.android.jianchengdichan.R;
import com.cui.android.jianchengdichan.http.bean.HomeCivilianListBean;
import com.cui.android.jianchengdichan.utils.LogUtils;
import com.cui.android.jianchengdichan.utils.Okhttp3Utils;
import com.cui.android.jianchengdichan.view.ui.avtivity.ConveDetailsActivity;

import java.util.List;

/**
 * @author CUI
 * @data 2018/6/12.
 * @details
 */
public class ConveRecommendAdapter extends RecyclerView.Adapter<ConveRecommendAdapter.MyViewHolder>{

    List<HomeCivilianListBean.InfoBean> dataList ;
    Context mContext;
    public ConveRecommendAdapter(List<HomeCivilianListBean.InfoBean> dataList, Context context) {
        this.dataList = dataList;
        mContext= context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        MyViewHolder myViewHolder = new MyViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_conve_recommend_layout, parent, false));
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
       final HomeCivilianListBean.InfoBean infoBean = dataList.get(position);
        holder.tv_conve_name.setText(infoBean.getTitle());
        holder.tv_conve_address.setText("地址："+infoBean.getAddress());
        holder.tv_conve_phone.setText("电话："+infoBean.getPhone());
        Okhttp3Utils.getInstance().glide(MyApplication.getAppContext(),infoBean.getPic(),holder.tv_conve_pic);
        holder.ll_recommend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                LogUtils.i(infoBean.getId()+"");
                bundle.putString("id",infoBean.getId()+"");
                Intent intent = new Intent(mContext,ConveDetailsActivity.class);
                intent.putExtras(bundle);
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView tv_conve_pic ;
        TextView tv_conve_name,tv_conve_address,tv_conve_phone;
        LinearLayout ll_recommend;
        public MyViewHolder(View itemView) {
            super(itemView);
            tv_conve_pic=  itemView.findViewById(R.id.tv_conve_pic);
            tv_conve_name=  itemView.findViewById(R.id.tv_conve_name);
            tv_conve_address=  itemView.findViewById(R.id.tv_conve_address);
            tv_conve_phone=  itemView.findViewById(R.id.tv_conve_phone);
            ll_recommend=  itemView.findViewById(R.id.ll_recommend);

        }
    }
}
