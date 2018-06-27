package com.cui.android.jianchengdichan.view.ui.fragment.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.cui.android.jianchengdichan.R;
import com.cui.android.jianchengdichan.view.ui.fragment.adapter.adapterBean.CommunityBean;

import java.util.List;

public class MainRvCommunityAdapter extends BaseQuickAdapter<CommunityBean,BaseViewHolder> {
    public MainRvCommunityAdapter( @Nullable List<CommunityBean> data) {
        super(R.layout.community_item_layout, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, CommunityBean item) {
        helper.setBackgroundRes(R.id.iv_community_icon,item.getImgResource());
        helper.setText(R.id.tv_community_mane,item.getName());
    }

}
