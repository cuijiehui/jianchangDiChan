package com.cui.android.jianchengdichan.view.ui.adapter;

import android.support.annotation.Nullable;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.cui.android.jianchengdichan.MyApplication;
import com.cui.android.jianchengdichan.R;
import com.cui.android.jianchengdichan.http.bean.CivilianserviceBean;

import java.util.List;

/**
 * @author CUI
 * @data 2018/6/14.
 * @details
 */
public class CellTypeAdapter extends BaseQuickAdapter<CivilianserviceBean, BaseViewHolder> {
    TextView tv_type_text;
    LinearLayout v_vut_off;
    int position = 0;

    public CellTypeAdapter(int layoutResId, @Nullable List<CivilianserviceBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, CivilianserviceBean item) {
        helper.setText(R.id.tv_type_text, item.getName());
        if (helper.getLayoutPosition() == position) {
            tv_type_text = helper.getView(R.id.tv_type_text);
            tv_type_text.setTextColor(MyApplication.getAppContext().getResources().getColor(R.color.main_top_text_col));
            v_vut_off = helper.getView(R.id.ll_vut_off);
            v_vut_off.setBackgroundColor(MyApplication.getAppContext().getResources().getColor(R.color.main_top_text_col));
        }else{
            helper.setTextColor(R.id.tv_type_text,MyApplication.getAppContext().getResources().getColor(R.color.black));
            helper.setBackgroundColor(R.id.ll_vut_off,MyApplication.getAppContext().getResources().getColor(R.color.white));
        }
        helper.addOnClickListener(R.id.ll_type_click);
    }
    public void setClick(int position, TextView textView, LinearLayout linearLayout){
        if(tv_type_text!=null){
            tv_type_text.setTextColor(MyApplication.getAppContext().getResources().getColor(R.color.black));
        }
        if(v_vut_off!=null){
            v_vut_off.setBackgroundColor(MyApplication.getAppContext().getResources().getColor(R.color.white));
        }
        tv_type_text=textView;
        v_vut_off=linearLayout;
        tv_type_text.setTextColor(MyApplication.getAppContext().getResources().getColor(R.color.main_top_text_col));
        v_vut_off.setBackgroundColor(MyApplication.getAppContext().getResources().getColor(R.color.main_top_text_col));
        this.position=position;
    }
}
