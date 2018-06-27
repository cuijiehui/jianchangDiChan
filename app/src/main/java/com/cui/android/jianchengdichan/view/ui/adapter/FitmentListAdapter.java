package com.cui.android.jianchengdichan.view.ui.adapter;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.cui.android.jianchengdichan.R;
import com.cui.android.jianchengdichan.http.bean.RenovationBean;

import java.util.ArrayList;
import java.util.List;

/**
 * @author CUI
 * @data 2018/6/7.
 * @details
 */
public class FitmentListAdapter extends BaseQuickAdapter<RenovationBean,BaseViewHolder> {
    public FitmentListAdapter( @Nullable List<RenovationBean> data) {
        super(R.layout.item_fitment_list_layout, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, RenovationBean item) {
        helper.setText(R.id.tv_item_company,item.getCompany());
        helper.setText(R.id.tv_item_time,"装修时间："+item.getStartdate()+"至"+item.getEnddate());
        switch (item.getStatus()){
            case 1:
                helper.setImageResource(R.id.iv_item_state,R.drawable.under_review_iocon);
                break;
            case 2:
                helper.setImageResource(R.id.iv_item_state,R.drawable.being_processed_icon);
                break;
            case 4:
                helper.setImageResource(R.id.iv_item_state,R.drawable.accomplish_icon);
                break;
        }
    }


}
