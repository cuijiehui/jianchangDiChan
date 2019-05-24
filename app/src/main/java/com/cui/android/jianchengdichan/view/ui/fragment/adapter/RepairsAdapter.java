package com.cui.android.jianchengdichan.view.ui.fragment.adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.cui.android.jianchengdichan.R;
import com.cui.android.jianchengdichan.http.bean.HomeDataBean;
import com.cui.android.jianchengdichan.http.bean.RepairsBean;

import java.util.List;

public class RepairsAdapter extends BaseQuickAdapter<RepairsBean,BaseViewHolder>{

    public RepairsAdapter( @Nullable List<RepairsBean> data) {
        super(R.layout.item_repairs_record, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, RepairsBean item) {
        helper.setText(R.id.tv_repairs_record_name,item.getName());
        helper.setText(R.id.tv_repairs_record_time,"维修时间："+item.getCreate_time());
        helper.setText(R.id.tv_repairs_money,"维修费："+item.getRepair_cost()+"元");
        helper.setText(R.id.tv_repairs_maintain_name,"");
        /**
         * 状态 1:未处理 2：处理中 3：回绝 4：已维修
         */
        if(item.getSuggestion()!=null){
            helper.setText(R.id.tv_repairs_content,""+item.getSuggestion());

        }else{
            helper.setText(R.id.tv_repairs_content,"");

        }
        switch (item.getStatus()){
            case "1":
                helper.setBackgroundRes(R.id.iv_repairs_record_dispose,R.drawable.untreated_icon);
                helper.setGone(R.id.ll_evaluation,false);
                break;
            case "2":
                helper.setBackgroundRes(R.id.iv_repairs_record_dispose,R.drawable.being_processed_icon);
                helper.setText(R.id.tv_repairs_maintain_name,"维修人："+item.getRepair_who());
                helper.setGone(R.id.ll_evaluation,false);
                break;
            case "3":
                helper.setBackgroundRes(R.id.iv_repairs_record_dispose,R.drawable.being_processed_icon);
                helper.setGone(R.id.ll_evaluation,false);
                break;
            case "4":
                helper.setBackgroundRes(R.id.iv_repairs_record_dispose,R.drawable.accomplish_icon);
                helper.setText(R.id.tv_repairs_maintain_name,"维修人："+item.getRepair_who());
                helper.setGone(R.id.ll_evaluation,true);
                break;
        }
    }

}
