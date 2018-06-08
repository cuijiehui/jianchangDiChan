package com.cui.android.jianchengdichan.view.ui.Fragment.Adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.cui.android.jianchengdichan.R;
import com.cui.android.jianchengdichan.view.ui.Fragment.Adapter.AdapterBean.CommRvBean;
import com.cui.android.jianchengdichan.view.ui.Fragment.Adapter.interfaces.OnRecyclerViewItemClickListener;

import java.util.List;

public class CommRvAdapter extends RecyclerView.Adapter<CommRvAdapter.ViewHolder>{
    List<CommRvBean> data ;
    OnRecyclerViewItemClickListener listener;

    public CommRvAdapter(List<CommRvBean> data, OnRecyclerViewItemClickListener listener) {
        this.data = data;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ViewHolder viewHolder = new ViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_comm_rl_layout, parent, false));
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        CommRvBean bean = data.get(position);
        holder.iv_item_comm_icon.setBackgroundResource(bean.getIconId());
        holder.tv_item_comm_title.setText(bean.getTitle());
        holder.tv_item_comm_content.setText(bean.getContent());
        holder.rl_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClick(v,position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView iv_item_comm_icon ;
    TextView tv_item_comm_title,tv_item_comm_content;
    RelativeLayout rl_item;
        public ViewHolder(View itemView) {
            super(itemView);
            iv_item_comm_icon=  itemView.findViewById(R.id.iv_item_comm_icon);
            tv_item_comm_title=  itemView.findViewById(R.id.tv_item_comm_title);
            tv_item_comm_content=  itemView.findViewById(R.id.tv_item_comm_content);
            rl_item=  itemView.findViewById(R.id.rl_item);
        }
    }
}
