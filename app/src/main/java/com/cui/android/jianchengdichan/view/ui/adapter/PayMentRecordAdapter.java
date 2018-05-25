package com.cui.android.jianchengdichan.view.ui.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.cui.android.jianchengdichan.R;
import com.cui.android.jianchengdichan.http.bean.PayRecordsBean;

import java.util.List;

import butterknife.BindView;

public class PayMentRecordAdapter extends RecyclerView.Adapter<PayMentRecordAdapter.ViewHolder> {
    List<PayRecordsBean> dataList ;
    public PayMentRecordAdapter(List<PayRecordsBean> dataList) {
        this.dataList = dataList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ViewHolder viewHolder = new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_pay_ment_layout, parent, false));
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {


        PayRecordsBean payRecordsBean =   dataList.get(position);

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
