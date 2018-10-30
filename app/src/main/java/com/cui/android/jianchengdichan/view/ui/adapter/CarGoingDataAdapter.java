package com.cui.android.jianchengdichan.view.ui.adapter;

import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.cui.android.jianchengdichan.R;
import com.cui.android.jianchengdichan.http.bean.CarGoingBean;
import com.cui.android.jianchengdichan.utils.Okhttp3Utils;

import java.util.List;

public class CarGoingDataAdapter extends BaseQuickAdapter<CarGoingBean,BaseViewHolder> {
    public CarGoingDataAdapter( @Nullable List<CarGoingBean> data) {
        super(R.layout.item_car_going, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, CarGoingBean item) {

//            if(!TextUtils.isEmpty(item.getIconPath())){
//                ImageView iv_car_pic = helper.getView(R.id.iv_car_pic);
//                Okhttp3Utils.getInstance().glide(mContext,item.getIconPath(),iv_car_pic);
//            }
//            helper.setText(R.id.tv_car_title,item.getTitle());
//            helper.setText(R.id.tv_car_distance,item.getTitle());//距离
//            helper.setText(R.id.tv_car_all,item.getTitle());
    }
}
