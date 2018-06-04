package com.cui.android.jianchengdichan.view.ui.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.cui.android.jianchengdichan.R;
import com.cui.android.jianchengdichan.http.bean.NoticeBean;
import com.cui.android.jianchengdichan.view.ui.Fragment.Adapter.CommRvAdapter;

import java.util.List;

public class NoticeRvAdpter extends RecyclerView.Adapter<NoticeRvAdpter.MyViewHolder>{
    List<NoticeBean> dataList ;

    public NoticeRvAdpter(List<NoticeBean> dataList) {
        this.dataList = dataList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        MyViewHolder viewHolder = new MyViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_notice_list, parent, false));
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        NoticeBean noticeBean = dataList.get(position);
        holder.tv_notice_content.setText(noticeBean.getContent());
        holder.tv_notice_signature.setText("发布人："+noticeBean.getSignature());
        holder.tv_notice_time.setText("发布时间："+noticeBean.getCreate_time());
        holder.tv_notice_title.setText(noticeBean.getTitle());
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tv_notice_title,tv_notice_content,tv_notice_signature,tv_notice_time ;

        public MyViewHolder(View itemView) {
            super(itemView);
            tv_notice_title=  itemView.findViewById(R.id.tv_notice_title);
            tv_notice_content=  itemView.findViewById(R.id.tv_notice_content);
            tv_notice_signature=  itemView.findViewById(R.id.tv_notice_signature);
            tv_notice_time=  itemView.findViewById(R.id.tv_notice_time);
        }
    }
}
