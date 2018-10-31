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
        helper.setText(R.id.tv_park_name,item.getParkName());
        helper.setText(R.id.tv_pst_time,item.getPst());
        helper.setText(R.id.tv_leave_time,item.getLeaveTime());
        helper.setText(R.id.tv_park_time,item.getParkTime());
        helper.setText(R.id.tv_edit_fee,item.getEditfee());
    }

}
