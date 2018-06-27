package com.cui.android.jianchengdichan.view.ui.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.cui.android.jianchengdichan.R;
import com.cui.android.jianchengdichan.view.ui.fragment.adapter.adapterBean.CommunityBean;

import java.util.List;

public class ConveAdapter extends BaseQuickAdapter<CommunityBean,BaseViewHolder> {
    public ConveAdapter( @Nullable List<CommunityBean> data) {
        super(R.layout.item_conve_community_layout, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, CommunityBean item) {
        helper.setText(R.id.tv_community_mane,item.getName());
        helper.setBackgroundRes(R.id.iv_community_icon,item.getImgResource());
    }
}
