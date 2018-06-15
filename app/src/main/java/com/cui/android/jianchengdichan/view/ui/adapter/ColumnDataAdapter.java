package com.cui.android.jianchengdichan.view.ui.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.cui.android.jianchengdichan.R;
import com.cui.android.jianchengdichan.http.bean.CivilianListBean;
import com.cui.android.jianchengdichan.utils.Okhttp3Utils;
import com.cui.android.jianchengdichan.view.ui.customview.UIImageView;

import java.util.List;

/**
 * @author CUI
 * @data 2018/6/14.
 * @details
 */
public class ColumnDataAdapter extends BaseQuickAdapter<CivilianListBean,BaseViewHolder> {
    public ColumnDataAdapter(int layoutResId, @Nullable List<CivilianListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, CivilianListBean item) {
        helper.setText(R.id.tv_column_title,item.getTitle());
        helper.setText(R.id.tv_column_address,"地址："+item.getAddress());
        helper.setText(R.id.tv_column_phone,"电话："+item.getPhone());
        helper.setText(R.id.tv_column_browse,item.getFlow()+"");
        UIImageView uiImageView = helper.getView(R.id.uiv_column_pic);
        Okhttp3Utils.getInstance().glide(mContext,item.getPic(),uiImageView);

    }
}
