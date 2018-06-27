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

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.cui.android.jianchengdichan.R;
import com.cui.android.jianchengdichan.http.bean.PayRecordsBean;
import com.cui.android.jianchengdichan.http.bean.UserEntranceBean;

import java.util.List;

public class SectionAdapter extends BaseQuickAdapter<UserEntranceBean,BaseViewHolder> {

    public SectionAdapter(@Nullable List<UserEntranceBean> data) {
        super(R.layout.com_app_content,data);
    }

    @Override
    protected void convert(BaseViewHolder helper, UserEntranceBean item) {
        helper.setText(R.id.com_app_community_name,"社区名称：" + item.getCommunity());
        helper.setText(R.id.com_app_unit_name,"单元名称：" + item.getUnit());
        helper.setText(R.id.com_app_room_name,"门号名称：" + item.getProperty());
        int status = item.getStatus();
        if (status==2){
            helper.setImageResource(R.id.img_unit_add_status,R.drawable.unit_add_pass);
        }else{
            helper.setImageResource(R.id.img_unit_add_status,R.drawable.unit_add_check);
        }
    }

}
