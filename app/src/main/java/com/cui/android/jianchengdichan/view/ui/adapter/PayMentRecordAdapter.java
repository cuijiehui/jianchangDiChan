package com.cui.android.jianchengdichan.view.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.cui.android.jianchengdichan.R;
import com.cui.android.jianchengdichan.http.bean.PayRecordsBean;
import com.cui.android.jianchengdichan.utils.LogUtils;
import com.cui.android.jianchengdichan.view.ui.avtivity.PayingActivity;
import com.cui.android.jianchengdichan.view.ui.beans.PayingBean;

import java.util.List;

public class PayMentRecordAdapter extends RecyclerView.Adapter<PayMentRecordAdapter.ViewHolder> {
    List<PayRecordsBean> dataList ;
    Context mContext ;
    public PayMentRecordAdapter(List<PayRecordsBean> dataList,Context context) {
        this.dataList = dataList;
        this.mContext = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ViewHolder viewHolder = new ViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_pay_ment_layout, parent, false));
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {


        final PayRecordsBean payRecordsBean =   dataList.get(position);

        if (payRecordsBean.getCate().equals("水费")) {
            holder.iv_pay_type_icon.setImageResource(R.drawable.icon_pay_water);
        }else if (payRecordsBean.getCate().equals("电费")) {
            holder.iv_pay_type_icon.setImageResource(R.drawable.icon_energy_charge);
        }else if (payRecordsBean.getCate().equals("煤气费")) {
            holder.iv_pay_type_icon.setImageResource(R.drawable.icon_fuel_gas);
        }else if (payRecordsBean.getCate().equals("物业费")) {
            holder.iv_pay_type_icon.setImageResource(R.drawable.icon_property_fee);
        }else if (payRecordsBean.getCate().equals("停车费")) {
            holder.iv_pay_type_icon.setImageResource(R.drawable.icon_parking_charge);
        }else if (payRecordsBean.getCate().equals("管理费")) {
            holder.iv_pay_type_icon.setImageResource(R.drawable.icon_pay_other);
        }else{
            holder.iv_pay_type_icon.setImageResource(R.drawable.icon_pay_other);
        }

        holder.tv_pay_ment_num.setText(payRecordsBean.getNum()+payRecordsBean.getUnit());
        holder.tv_pay_ment_sum.setText("$"+payRecordsBean.getSum());
        holder.tv_pay_ment_instruct.setText(payRecordsBean.getCreate_time());
        if(payRecordsBean.getPay_status().equals("1")){
            holder.tv_pay_ment_go.setText("去缴费");
        }else{
            holder.tv_pay_ment_go.setText("已缴费");
        }

        final PayingBean payingBean = new PayingBean(payRecordsBean.getId()
                ,"path"
                ,"广州寰城海航广场"
                ,payRecordsBean.getCreate_time()
                ,payRecordsBean.getNum()+payRecordsBean.getUnit()
                ,payRecordsBean.getSum());
        holder.tv_pay_ment_go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(payRecordsBean.getPay_status().equals("1")){
                    LogUtils.i("-------------------" + payRecordsBean.getId());
                    Intent intent = new Intent(mContext , PayingActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("bean" , payingBean);
                    bundle.putString( "typeName" , "typaName");
                    intent.putExtras(bundle);
                    mContext.startActivity(intent);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView iv_pay_type_icon;
        TextView tv_pay_ment_instruct,tv_pay_ment_num,tv_pay_ment_sum,tv_pay_ment_go;
        public ViewHolder(View itemView) {
            super(itemView);
             tv_pay_ment_instruct = itemView.findViewById(R.id.tv_pay_ment_instruct);
             tv_pay_ment_num=itemView.findViewById(R.id.tv_pay_ment_num);
             tv_pay_ment_sum= itemView.findViewById(R.id.tv_pay_ment_sum);
             tv_pay_ment_go=itemView.findViewById(R.id.tv_pay_ment_go);
             iv_pay_type_icon=itemView.findViewById(R.id.iv_pay_type_icon);

        }
    }
}
