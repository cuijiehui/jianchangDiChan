package com.cui.android.jianchengdichan.view.ui.fragment.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.cui.android.jianchengdichan.R;
import com.cui.android.jianchengdichan.view.ui.fragment.adapter.adapterBean.CommRvBean;

import java.util.List;

public class CommRvBottomAdapter extends BaseQuickAdapter<CommRvBean,BaseViewHolder> {

    public CommRvBottomAdapter( @Nullable List<CommRvBean> data) {
        super(R.layout.item_comm_bottom_layout, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, CommRvBean item) {
        helper.setText(R.id.tv_item_comm_bottom_title,item.getTitle());
        helper.setText(R.id.tv_item_comm_bottom_content,item.getContent());
        helper.setBackgroundRes(R.id.iv_item_comm_botton_icon,item.getIconId());
        helper.addOnClickListener(R.id.rl__bottom_item);
    }

}
