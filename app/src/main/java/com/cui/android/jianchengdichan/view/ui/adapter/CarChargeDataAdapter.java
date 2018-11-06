package com.cui.android.jianchengdichan.view.ui.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.cui.android.jianchengdichan.R;
import com.cui.android.jianchengdichan.http.bean.CarChargeLogBean;
import com.cui.android.jianchengdichan.http.bean.CarGoingBean;

import java.util.List;

public class CarChargeDataAdapter extends BaseQuickAdapter<CarChargeLogBean,BaseViewHolder> {
    public CarChargeDataAdapter(@Nullable List<CarChargeLogBean> data) {
        super(R.layout.item_charge_layout, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, CarChargeLogBean item) {
        helper.setText(R.id.tv_car_no,item.getCarNo());
        helper.setText(R.id.tv_park_name,"文德先生停车场");
        helper.setText(R.id.tv_order_reference,item.getOrderNo());
        helper.setText(R.id.tv_pst_time,item.getEntryTime());
        helper.setText(R.id.tv_create_time,item.getCreateTime());
        helper.setText(R.id.tv_park_time,item.getStopTime());
        helper.setText(R.id.tv_edit_fee,item.getPayFee());
        String state = item.getTradeState();
        String stateS="";
        switch (state) {
            case "0":
                stateS="未支付";
                helper.setGone(R.id.bt_cancel,true);
                break;
            case "1":
                stateS="已支付";
                helper.setGone(R.id.bt_cancel,false);

                break;
            case "2":
                stateS="退款";
                helper.setGone(R.id.bt_cancel,false);

                break;
            case "3":
                stateS="已关闭";
                helper.setGone(R.id.bt_cancel,false);

                break;
            case "4":
                stateS="已撤销";
                helper.setGone(R.id.bt_cancel,false);

                break;
        }
        helper.setText(R.id.tv_state_pay,stateS);
        helper.addOnClickListener(R.id.bt_cancel);
    }

}
