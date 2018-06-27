package com.cui.android.jianchengdichan.view.ui.adapter;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.cui.android.jianchengdichan.R;
import com.cui.android.jianchengdichan.http.bean.HistoryDataBean;

import java.util.List;

public class HistorysAdapter extends BaseQuickAdapter<HistoryDataBean,BaseViewHolder> {
    @Override
    protected void convert(BaseViewHolder helper, HistoryDataBean item) {
        helper.setText(R.id.tv_history_name,item.getContent());
        helper.setText(R.id.tv_history_time,item.getCreate_time());
        helper.setText(R.id.tv_history_time,item.getCreate_time());
        helper.addOnClickListener(R.id.iv_history_del);
    }

    public HistorysAdapter( @Nullable List<HistoryDataBean> data) {
        super(R.layout.item_history_layout, data);
    }



}
