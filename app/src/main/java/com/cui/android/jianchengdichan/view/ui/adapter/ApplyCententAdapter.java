package com.cui.android.jianchengdichan.view.ui.adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.cui.android.jianchengdichan.MyApplication;
import com.cui.android.jianchengdichan.R;
import com.cui.android.jianchengdichan.http.bean.MyApplyBean;
import com.cui.android.jianchengdichan.presenter.MyApplyPresenter;
import com.cui.android.jianchengdichan.utils.Okhttp3Utils;
import com.cui.android.jianchengdichan.utils.SPKey;
import com.cui.android.jianchengdichan.utils.SPUtils;
import com.cui.android.jianchengdichan.utils.TimeUtil;
import com.cui.android.jianchengdichan.view.ui.avtivity.ReleaseRentActivity;
import com.google.gson.Gson;

import java.util.List;

public class ApplyCententAdapter extends BaseQuickAdapter<MyApplyBean,BaseViewHolder> {
    public ApplyCententAdapter( @Nullable List<MyApplyBean> data) {
        super(R.layout.item_apply_layout, data);
    }
    @Override
    protected void convert(BaseViewHolder helper, MyApplyBean item) {
        helper.setText(R.id.tv_apply_title,item.getTitle()+" "+item.getHouse_type_info());
        helper.setText(R.id.iv_apply_type,item.getBan_type());
        helper.setText(R.id.tv_apply_username,item.getContact());
        helper.setText(R.id.tv_apply_time,item.getCreate_time());
        String day= TimeUtil.getDateDetail(item.getCreate_time());
        helper.setText(R.id.tv_apply_time_day,"已发布"+day+"天");
        ImageView iv_apply_checked = helper.getView(R.id.iv_apply_checked);
        ImageView iv_apply_img = helper.getView(R.id.iv_apply_img);
        if(item.getIs_show().equals("0")){
          iv_apply_checked.setBackgroundResource(R.drawable.under_review);
        }else{
           iv_apply_checked.setBackgroundResource(R.drawable.checked_icon);
        }
        if (item.getIs_pay()==0) {
            helper.setGone(R.id.ll_paying,true);
            helper.setText(R.id.tv_pay_amount, String.format(MyApplication.getAppContext().getString(R.string.amount_paid),item.getPay_money()+""));
            helper.addOnClickListener(R.id.bt_paying);
        }else{
            helper.setGone(R.id.ll_paying,false);

        }
        if(!TextUtils.isEmpty(item.getPic())){
            Okhttp3Utils.getInstance().glide(mContext,item.getPic(),iv_apply_img);
        }
        helper.addOnClickListener(R.id.iv_apply_del);
        helper.addOnClickListener(R.id.iv_apply_com);
    }

}
