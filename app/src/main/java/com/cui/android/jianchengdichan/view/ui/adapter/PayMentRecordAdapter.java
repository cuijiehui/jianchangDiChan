package com.cui.android.jianchengdichan.view.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.cui.android.jianchengdichan.R;
import com.cui.android.jianchengdichan.http.bean.NoticeBean;
import com.cui.android.jianchengdichan.http.bean.PayRecordsBean;
import com.cui.android.jianchengdichan.utils.LogUtils;
import com.cui.android.jianchengdichan.view.ui.avtivity.PayingActivity;
import com.cui.android.jianchengdichan.view.ui.beans.PayingBean;

import java.util.List;

public class PayMentRecordAdapter extends BaseQuickAdapter<PayRecordsBean,BaseViewHolder> {

    public PayMentRecordAdapter(@Nullable List<PayRecordsBean> data) {
        super(R.layout.item_pay_ment_layout,data);
    }

    @Override
    protected void convert(BaseViewHolder helper, PayRecordsBean item) {

        if (item.getCate().equals("水费")) {
            helper.setImageResource(R.id.iv_pay_type_icon,R.drawable.icon_pay_water);
        }else if (item.getCate().equals("电费")) {
            helper.setImageResource(R.id.iv_pay_type_icon,R.drawable.icon_energy_charge);
        }else if (item.getCate().equals("煤气费")) {
            helper.setImageResource(R.id.iv_pay_type_icon,R.drawable.icon_fuel_gas);
        }else if (item.getCate().equals("物业费")) {
            helper.setImageResource(R.id.iv_pay_type_icon,R.drawable.icon_property_fee);
        }else if (item.getCate().equals("停车费")) {
            helper.setImageResource(R.id.iv_pay_type_icon,R.drawable.icon_parking_charge);
        }else if (item.getCate().equals("管理费")) {
            helper.setImageResource(R.id.iv_pay_type_icon,R.drawable.icon_pay_other);
        }else{
            helper.setImageResource(R.id.iv_pay_type_icon,R.drawable.icon_pay_other);
        }
        helper.setText(R.id.tv_pay_ment_num,item.getNum()+item.getUnit());
        helper.setText(R.id.tv_pay_ment_sum,"$"+item.getSum());
        helper.setText(R.id.tv_pay_ment_instruct,item.getCreate_time());
        if(item.getPay_status().equals("1")){
            helper.setText(R.id.tv_pay_ment_go,"去缴费");
        }else{
            helper.setText(R.id.tv_pay_ment_go,"已缴费");
        }
        helper.addOnClickListener(R.id.tv_pay_ment_go);
    }

}
