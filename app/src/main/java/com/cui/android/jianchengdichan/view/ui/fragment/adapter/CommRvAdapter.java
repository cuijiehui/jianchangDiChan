package com.cui.android.jianchengdichan.view.ui.fragment.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.cui.android.jianchengdichan.R;
import com.cui.android.jianchengdichan.view.ui.fragment.adapter.adapterBean.CommRvBean;

import java.util.List;

public class CommRvAdapter extends BaseQuickAdapter<CommRvBean,BaseViewHolder> {
    public CommRvAdapter( @Nullable List<CommRvBean> data) {
        super(R.layout.item_comm_rl_layout, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, CommRvBean item) {
        helper.setBackgroundRes(R.id.iv_item_comm_icon,item.getIconId());
        helper.setText(R.id.tv_item_comm_title,item.getTitle());
        helper.setText(R.id.tv_item_comm_content,item.getContent());
        helper.addOnClickListener(R.id.rl_item);
    }



}
