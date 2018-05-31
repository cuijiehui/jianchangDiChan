package com.cui.android.jianchengdichan.view.ui.adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.cui.android.jianchengdichan.R;
import com.cui.android.jianchengdichan.http.bean.UserEntranceBean;

import java.util.List;

public class SectionAdapter extends RecyclerView.Adapter<SectionAdapter.ViewHodler>{

    List<UserEntranceBean> data;

    public SectionAdapter(List<UserEntranceBean> data) {
        this.data = data;
    }

    @NonNull
    @Override
    public ViewHodler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        SectionAdapter.ViewHodler viewHolder = new SectionAdapter.ViewHodler(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.com_app_content, parent, false));
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHodler holder, int position) {
        UserEntranceBean userEntranceBean=data.get(position);
        holder.com_app_community_name.setText("社区名称：" + userEntranceBean.getCommunity());
        holder.com_app_unit_name.setText( "单元名称：" + userEntranceBean.getUnit());
        holder.com_app_room_name.setText("门号名称："+userEntranceBean.getProperty());
        int status = userEntranceBean.getStatus();
        if (status==2){
            holder.img_unit_add_status.setImageResource(R.drawable.unit_add_pass);
        }else{
            holder.img_unit_add_status.setImageResource( R.drawable.unit_add_check);
        }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHodler extends RecyclerView.ViewHolder {

        ImageView app_com_unit_logo,img_unit_add_status;
        TextView com_app_community_name;
        TextView com_app_unit_name;
        TextView com_app_room_name;


        public ViewHodler(View itemView) {
            super(itemView);
            app_com_unit_logo = (ImageView) itemView.findViewById(R.id.app_com_unit_logo);
            img_unit_add_status = (ImageView) itemView.findViewById(R.id.img_unit_add_status);
            com_app_community_name = (TextView) itemView.findViewById(R.id.com_app_community_name);
            com_app_unit_name = (TextView) itemView.findViewById(R.id.com_app_unit_name);
            com_app_room_name = (TextView) itemView.findViewById(R.id.com_app_room_name);

        }
    }
}
