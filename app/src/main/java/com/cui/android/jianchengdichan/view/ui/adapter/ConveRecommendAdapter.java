package com.cui.android.jianchengdichan.view.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.cui.android.jianchengdichan.MyApplication;
import com.cui.android.jianchengdichan.R;
import com.cui.android.jianchengdichan.http.bean.HomeCivilianListBean;
import com.cui.android.jianchengdichan.utils.LogUtils;
import com.cui.android.jianchengdichan.utils.Okhttp3Utils;
import com.cui.android.jianchengdichan.view.ui.avtivity.ConveDetailsActivity;

import java.util.List;

/**
 * @author CUI
 * @data 2018/6/12.
 * @details
 */
public class ConveRecommendAdapter extends BaseQuickAdapter<HomeCivilianListBean.InfoBean,BaseViewHolder> {
    public ConveRecommendAdapter( @Nullable List<HomeCivilianListBean.InfoBean> data) {
        super(R.layout.item_conve_recommend_layout, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, HomeCivilianListBean.InfoBean item) {
        helper.setText(R.id.tv_conve_name,item.getTitle());
        helper.setText(R.id.tv_conve_address,item.getAddress());
        helper.setText(R.id.tv_conve_phone,item.getPhone());
        ImageView tv_conve_pic=helper.getView(R.id.tv_conve_pic);
        if(!TextUtils.isEmpty(item.getPic())){
            Okhttp3Utils.getInstance().glide(MyApplication.getAppContext(),item.getPic(),tv_conve_pic);
        }
        helper.addOnClickListener(R.id.ll_recommend);

    }

}
