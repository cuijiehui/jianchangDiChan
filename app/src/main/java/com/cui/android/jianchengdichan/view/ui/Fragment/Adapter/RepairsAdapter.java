package com.cui.android.jianchengdichan.view.ui.Fragment.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.cui.android.jianchengdichan.R;
import com.cui.android.jianchengdichan.http.bean.RepairsBean;

import java.util.List;

public class RepairsAdapter extends RecyclerView.Adapter<RepairsAdapter.MyViewHolder> {

    private Context mContext;
    private LayoutInflater mIflater;
    private List<RepairsBean> list;

    public RepairsAdapter(Context mContext,  List<RepairsBean> list) {
        this.mContext = mContext;
        this.list = list;
        mIflater = LayoutInflater.from(mContext);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RepairsAdapter.MyViewHolder holder = new RepairsAdapter.MyViewHolder(mIflater.inflate(R.layout.item_repairs_record , parent, false));
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        RepairsBean bean = list.get(position);
        holder.tv_repairs_record_name.setText(bean.getName());
        holder.tv_repairs_record_time.setText(bean.getCreate_time());
        /**
         * 状态 1:未处理 2：处理中 3：回绝 4：已维修
         */
        switch (bean.getType()){
            case "1":
                holder.iv_repairs_record_dispose.setBackgroundResource(R.drawable.untreated_icon);
                break;
            case "2":
                holder.iv_repairs_record_dispose.setBackgroundResource(R.drawable.being_processed_icon);
                break;
            case "3":
                holder.iv_repairs_record_dispose.setBackgroundResource(R.drawable.being_processed_icon);
                break;
            case "4":
                holder.iv_repairs_record_dispose.setBackgroundResource(R.drawable.accomplish_icon);
                break;
        }

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tv_repairs_record_name,tv_repairs_record_time;
        ImageView iv_repairs_record_dispose;
        public MyViewHolder(View itemView) {
            super(itemView);
            tv_repairs_record_name = (TextView) itemView.findViewById(R.id.tv_repairs_record_name);
            tv_repairs_record_time = (TextView) itemView.findViewById(R.id.tv_repairs_record_time);
            iv_repairs_record_dispose = (ImageView) itemView.findViewById(R.id.iv_repairs_record_dispose);
        }
    }
}
