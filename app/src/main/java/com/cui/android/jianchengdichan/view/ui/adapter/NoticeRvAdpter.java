package com.cui.android.jianchengdichan.view.ui.adapter;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.cui.android.jianchengdichan.R;
import com.cui.android.jianchengdichan.http.bean.NoticeBean;

import java.util.List;

public class NoticeRvAdpter extends BaseQuickAdapter<NoticeBean,BaseViewHolder> {
    public NoticeRvAdpter( @Nullable List<NoticeBean> data) {
        super(R.layout.item_notice_list, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, NoticeBean item) {
        helper.setText(R.id.tv_notice_content,item.getContent());
        helper.setText(R.id.tv_notice_signature,"发布人："+item.getSignature());
        helper.setText(R.id.tv_notice_time,"发布时间："+item.getCreate_time());
        helper.setText(R.id.tv_notice_title,item.getTitle());
    }





}
