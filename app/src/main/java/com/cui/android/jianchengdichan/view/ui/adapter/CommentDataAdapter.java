package com.cui.android.jianchengdichan.view.ui.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.cui.android.jianchengdichan.R;
import com.cui.android.jianchengdichan.http.bean.CommentListBean;
import com.cui.android.jianchengdichan.utils.Okhttp3Utils;
import com.cui.android.jianchengdichan.utils.TimeUtil;
import com.cui.android.jianchengdichan.view.ui.customview.CircleImageView;

import java.util.List;

/**
 * @author CUI
 * @data 2018/6/21.
 * @details
 */
public class CommentDataAdapter extends BaseQuickAdapter<CommentListBean,BaseViewHolder> {
    public CommentDataAdapter(int layoutResId, @Nullable List<CommentListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, CommentListBean item) {
        CircleImageView circleImageView = helper.getView(R.id.civ_comm_list_pic);
        Okhttp3Utils.getInstance().glide(mContext,item.getHeadimg(),circleImageView);
        helper.setText(R.id.tv_comm_list_name,item.getNickname());
        helper.setText(R.id.tv_comm_list_content,item.getContent());
        String time = TimeUtil.getTimeDetail(item.getCreate_time(),"yyyy-MM-dd HH:mm:ss");//2018-06-13 18:17:21
        helper.setText(R.id.tv_comm_list_time,time);

    }
}
