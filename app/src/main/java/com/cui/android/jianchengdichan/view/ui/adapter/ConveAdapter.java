package com.cui.android.jianchengdichan.view.ui.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.cui.android.jianchengdichan.R;
import com.cui.android.jianchengdichan.view.ui.Fragment.Adapter.AdapterBean.CommunityBean;
import com.cui.android.jianchengdichan.view.ui.Fragment.Adapter.interfaces.OnRecyclerViewItemClickListener;

import java.util.ArrayList;
import java.util.List;

public class ConveAdapter extends RecyclerView.Adapter<ConveAdapter.ViewHolder> implements View.OnClickListener, View.OnLongClickListener {

    private List<CommunityBean> dataList = new ArrayList<>();
    private OnRecyclerViewItemClickListener mOnItemClickListener = null;

    public ConveAdapter(List<CommunityBean> dataList, OnRecyclerViewItemClickListener mOnItemClickListener) {
        this.dataList = dataList;
        this.mOnItemClickListener = mOnItemClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view =LayoutInflater.from(parent.getContext()).inflate(R.layout.item_conve_community_layout,parent,false);
        view.setOnClickListener(this);
        view.setOnLongClickListener(this);
        ViewHolder viewHolder = new ViewHolder(view);
         return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.itemView.setTag(position);
        holder.imageView.setBackgroundResource(dataList.get(position).getImgResource());
        holder.textView.setText(dataList.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    @Override
    public void onClick(View v) {
        if (mOnItemClickListener != null) {      //getTag获取的即是点击位置
            mOnItemClickListener.onItemClick(v,(int)v.getTag());
        }
    }

    @Override
    public boolean onLongClick(View v) {
        if (mOnItemClickListener != null) {
            mOnItemClickListener.onItemLongClick(v,(int)v.getTag());
        }
        return true;

    }

    class ViewHolder extends  RecyclerView.ViewHolder{
        ImageView imageView ;
        TextView textView;
        public ViewHolder(View itemView) {
            super(itemView);
            imageView= itemView.findViewById(R.id.iv_community_icon);
            textView= itemView.findViewById(R.id.tv_community_mane);
        }
    }
}
