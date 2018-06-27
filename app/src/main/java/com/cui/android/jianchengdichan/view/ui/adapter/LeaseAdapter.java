package com.cui.android.jianchengdichan.view.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.cui.android.jianchengdichan.R;
import com.cui.android.jianchengdichan.http.bean.LeaseRoomBean;
import com.cui.android.jianchengdichan.utils.LogUtils;
import com.cui.android.jianchengdichan.utils.Okhttp3Utils;
import com.cui.android.jianchengdichan.view.ui.avtivity.RentDatailActivity;

import java.util.List;

/**
 * Created by Android on 2017/7/18.
 */

public class LeaseAdapter extends BaseQuickAdapter<LeaseRoomBean,BaseViewHolder> {
    public LeaseAdapter( @Nullable List<LeaseRoomBean> data) {
        super(R.layout.item_lease_list, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, LeaseRoomBean item) {
        ImageView iv_room_pic = helper.getView(R.id.iv_room_pic);
        Okhttp3Utils.getInstance().glide(mContext,item.getPic(),iv_room_pic);
        helper.setText(R.id.tv_room_name,item.getTitle());
        helper.setText(R.id.tv_room_address,item.getAddress());
        helper.setText(R.id.tv_room_price,"¥" + item.getRental() + "/月");
        helper.setText(R.id.tv_room_attr,item.getHouse_type() + "-" + item.getAcreage() + "㎡-" + item.getOrientations());
        if (!TextUtils.isEmpty(item.getCharge_pay())){
            helper.setText(R.id.tv_room_attr1,item.getCharge_pay());
        }
        if (!TextUtils.isEmpty(item.getDecoration())){
            helper.setText(R.id.tv_room_attr2,item.getDecoration());
        }
        helper.addOnClickListener(R.id.rel_content);
    }

}
