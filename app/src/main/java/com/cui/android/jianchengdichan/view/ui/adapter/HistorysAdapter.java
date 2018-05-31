package com.cui.android.jianchengdichan.view.ui.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.cui.android.jianchengdichan.R;
import com.cui.android.jianchengdichan.http.bean.HistoryDataBean;

import java.util.List;

public class HistorysAdapter extends RecyclerView.Adapter<HistorysAdapter.ViewHolder> {
    List<HistoryDataBean> dataList;
    ClickCallBack callBack;

    public HistorysAdapter(List<HistoryDataBean> dataList, ClickCallBack callBack) {
        this.dataList = dataList;
        this.callBack = callBack;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ViewHolder viewHolder = new ViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_history_layout, parent, false));
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
       final HistoryDataBean dataBean = dataList.get(position);
        holder.tv_history_name.setText(dataBean.getContent());
        holder.tv_history_time.setText(dataBean.getCreate_time());
        holder.iv_history_del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callBack.onClickItem(dataBean.getId());
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_history_name, tv_history_time;
        ImageView iv_history_del;

        public ViewHolder(View itemView) {
            super(itemView);
//            itemView.findViewById()
            tv_history_name = itemView.findViewById(R.id.tv_history_name);
            tv_history_time = itemView.findViewById(R.id.tv_history_time);
            iv_history_del = itemView.findViewById(R.id.iv_history_del);
        }
    }

    public interface ClickCallBack {
        void onClickItem(int id);
    }
}
