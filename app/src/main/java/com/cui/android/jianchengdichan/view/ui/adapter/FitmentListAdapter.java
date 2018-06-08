package com.cui.android.jianchengdichan.view.ui.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.cui.android.jianchengdichan.R;
import com.cui.android.jianchengdichan.http.bean.RenovationBean;

import java.util.ArrayList;
import java.util.List;

/**
 * @author CUI
 * @data 2018/6/7.
 * @details
 */
public class FitmentListAdapter extends RecyclerView.Adapter<FitmentListAdapter.MyViewHodler> {

    List<RenovationBean> data ;

    public FitmentListAdapter(List<RenovationBean> data) {
        this.data = data;
    }

    @NonNull
    @Override
    public MyViewHodler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        MyViewHodler myViewHodler = new MyViewHodler(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_fitment_list_layout, parent, false));
        return myViewHodler;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHodler holder, int position) {
        RenovationBean renovationBean = data.get(position);
        holder.tv_item_company.setText(renovationBean.getCompany());
        holder.tv_item_time.setText("装修时间："+renovationBean.getStartdate()+"至"+renovationBean.getEnddate());
        switch (renovationBean.getStatus()){
            case 1:
                holder.iv_item_state.setImageResource(R.drawable.under_review_iocon);
                break;
            case 2:
                holder.iv_item_state.setImageResource(R.drawable.being_processed_icon);
                break;
            case 4:
                holder.iv_item_state.setImageResource(R.drawable.accomplish_icon);
                break;
        }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class MyViewHodler extends RecyclerView.ViewHolder {

        ImageView iv_item_state;
        TextView tv_item_company,tv_item_time;


        public MyViewHodler(View itemView) {
            super(itemView);
            iv_item_state = (ImageView) itemView.findViewById(R.id.iv_item_state);
            tv_item_company = (TextView) itemView.findViewById(R.id.tv_item_company);
            tv_item_time = (TextView) itemView.findViewById(R.id.tv_item_time);
        }
    }
}
