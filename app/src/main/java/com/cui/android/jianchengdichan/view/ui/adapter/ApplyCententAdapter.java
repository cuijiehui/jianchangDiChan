package com.cui.android.jianchengdichan.view.ui.adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.cui.android.jianchengdichan.R;
import com.cui.android.jianchengdichan.http.bean.MyApplyBean;
import com.cui.android.jianchengdichan.presenter.MyApplyPresenter;
import com.cui.android.jianchengdichan.utils.SPKey;
import com.cui.android.jianchengdichan.utils.SPUtils;
import com.cui.android.jianchengdichan.utils.TimeUtil;
import com.cui.android.jianchengdichan.view.ui.ReleaseRentActivity;
import com.google.gson.Gson;

import java.util.List;

public class ApplyCententAdapter extends RecyclerView.Adapter<ApplyCententAdapter.MyViewHolder>  {
    List<MyApplyBean> myApplyBeans;
    Activity context;
    MyApplyPresenter myApplyPresenter;
    MyApplyBean myApplyBean;
    public ApplyCententAdapter(List<MyApplyBean> myApplyBeans, Activity context, MyApplyPresenter myApplyPresenter) {
        this.myApplyBeans = myApplyBeans;
        this.context=context;
        this.myApplyPresenter=myApplyPresenter;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        MyViewHolder myViewHolder  = new MyViewHolder( LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_apply_layout,parent,false));
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
       myApplyBean =myApplyBeans.get(position);
        holder.tv_apply_title.setText(myApplyBean.getTitle()+" "+myApplyBean.getHouse_type_info());
        holder.iv_apply_type.setText(myApplyBean.getBan_type());
        holder.tv_apply_username.setText(myApplyBean.getContact());
        holder.tv_apply_time.setText(myApplyBean.getCreate_time());
       String day= TimeUtil.getDateDetail(myApplyBean.getCreate_time());
       holder.tv_apply_time_day.setText("已发布"+day+"天");
        if(myApplyBean.getIs_show().equals("0")){
            holder.iv_apply_checked.setBackgroundResource(R.drawable.under_review);
        }else{
            holder.iv_apply_checked.setBackgroundResource(R.drawable.checked_icon);

        }
//        holder.iv_apply_icon
        if(!TextUtils.isEmpty(myApplyBean.getPic())){
            Glide.with(context).load(myApplyBean.getPic()).into(holder.iv_apply_img);

        }
        holder.iv_apply_del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                showDialog();
            }
        });
        holder.iv_apply_com.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,ReleaseRentActivity.class);
                Bundle bundle =new Bundle();
                Gson gson =new Gson();
               String json = gson.toJson(myApplyBean);
                bundle.putString("json",json);
                intent.putExtras(bundle);
                context.startActivity(intent);
            }
        });
    }

    private void showDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("")
                .setMessage("是否删除发布的租赁")
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                int uid = (Integer) SPUtils.INSTANCE.getSPValue(SPKey.SP_USER_UID_KEY, SPUtils.DATA_INT);
                String token = (String) SPUtils.INSTANCE.getSPValue(SPKey.SP_USER_TOKEN_KEY, SPUtils.DATA_STRING);
                myApplyPresenter.delRentInfo(uid,token,myApplyBean.getId());
                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                    }
                });
        builder.show();
    }

    @Override
    public int getItemCount() {
        return myApplyBeans.size();
    }

    class MyViewHolder extends  RecyclerView.ViewHolder{
        ImageView iv_apply_icon, iv_apply_del,iv_apply_com,iv_apply_img,iv_apply_checked;
        TextView iv_apply_type,tv_apply_title,tv_apply_time_day,tv_apply_username,tv_apply_time;
        public MyViewHolder(View itemView) {
            super(itemView);
            iv_apply_icon= itemView.findViewById(R.id.iv_apply_icon);
            iv_apply_del= itemView.findViewById(R.id.iv_apply_del);
            iv_apply_com= itemView.findViewById(R.id.iv_apply_com);
            iv_apply_img= itemView.findViewById(R.id.iv_apply_img);
            iv_apply_checked= itemView.findViewById(R.id.iv_apply_checked);

            iv_apply_type= itemView.findViewById(R.id.iv_apply_type);
            tv_apply_title= itemView.findViewById(R.id.tv_apply_title);
            tv_apply_time_day= itemView.findViewById(R.id.tv_apply_time_day);
            tv_apply_username= itemView.findViewById(R.id.tv_apply_username);
            tv_apply_time= itemView.findViewById(R.id.tv_apply_time);
        }
    }
}
