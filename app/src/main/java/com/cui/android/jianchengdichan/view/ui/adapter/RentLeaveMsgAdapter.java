package com.cui.android.jianchengdichan.view.ui.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.cui.android.jianchengdichan.R;
import com.cui.android.jianchengdichan.http.bean.LeaveMsgListBean;
import com.cui.android.jianchengdichan.utils.TimeUtil;

import java.util.List;

/**
 * @author CUI
 * @data 2018/6/27.
 * @details
 */
public class RentLeaveMsgAdapter extends BaseQuickAdapter<LeaveMsgListBean,BaseViewHolder> {
    public RentLeaveMsgAdapter(int layoutResId, @Nullable List<LeaveMsgListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, LeaveMsgListBean item) {
        helper.setText(R.id.tv_leave_list_name,item.getUsername());
        helper.setText(R.id.tv_leave_list_content,item.getContent());
        String time = TimeUtil.getTimeDetail(item.getCreate_time(),"yyyy-MM-dd HH:mm:ss");//2018-06-13 18:17:21
        helper.setText(R.id.tv_leave_list_time,time);
    }
}
