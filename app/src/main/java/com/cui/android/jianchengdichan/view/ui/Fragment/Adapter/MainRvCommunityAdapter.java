package com.cui.android.jianchengdichan.view.ui.Fragment.Adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.cui.android.jianchengdichan.R;
import com.cui.android.jianchengdichan.view.ui.Fragment.Adapter.AdapterBean.CommunityBean;

import java.util.ArrayList;
import java.util.List;

public class MainRvCommunityAdapter extends RecyclerView.Adapter<MainRvCommunityAdapter.ViewHolder>{

    private List<CommunityBean> dataList = new ArrayList<>();

    public MainRvCommunityAdapter(List<CommunityBean> dataList) {
        this.dataList = dataList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ViewHolder viewHolder = new ViewHolder( LayoutInflater.from(parent.getContext()).inflate(R.layout.community_item_layout,parent,false));
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.imageView.setBackgroundResource(dataList.get(position).getImgResource());
        holder.textView.setText(dataList.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return dataList.size();
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
