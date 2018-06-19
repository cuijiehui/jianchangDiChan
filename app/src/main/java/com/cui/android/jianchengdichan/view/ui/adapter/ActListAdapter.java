package com.cui.android.jianchengdichan.view.ui.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.cui.android.jianchengdichan.R;
import com.cui.android.jianchengdichan.http.bean.CommentActBean;
import com.cui.android.jianchengdichan.utils.Okhttp3Utils;
import com.cui.android.jianchengdichan.view.ui.customview.UIImageView;

import java.util.List;

/**
 * @author CUI
 * @data 2018/6/19.
 * @details
 */
public class ActListAdapter extends BaseQuickAdapter<CommentActBean,BaseViewHolder> {
    public ActListAdapter(int layoutResId, @Nullable List<CommentActBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, CommentActBean item) {
        helper.setText(R.id.tv_act_title,item.getTitle());
        helper.setText(R.id.tv_act_time,item.getCreate_time());
        UIImageView uiImageView = helper.getView(R.id.uiv_act_pic);
        Okhttp3Utils.getInstance().glide(mContext,item.getPic(),uiImageView);
        helper.addOnClickListener(R.id.ll_act_title);
    }
}
