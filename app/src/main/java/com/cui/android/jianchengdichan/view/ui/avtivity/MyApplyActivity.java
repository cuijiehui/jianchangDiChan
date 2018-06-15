package com.cui.android.jianchengdichan.view.ui.avtivity;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.cui.android.jianchengdichan.R;
import com.cui.android.jianchengdichan.http.bean.MyApplyBean;
import com.cui.android.jianchengdichan.presenter.BasePresenter;
import com.cui.android.jianchengdichan.presenter.MyApplyPresenter;
import com.cui.android.jianchengdichan.utils.SPKey;
import com.cui.android.jianchengdichan.utils.SPUtils;
import com.cui.android.jianchengdichan.view.BaseActivtity;
import com.cui.android.jianchengdichan.view.ui.adapter.ApplyCententAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class MyApplyActivity extends BaseActivtity {

    @BindView(R.id.top_back)
    RelativeLayout topBack;
    @BindView(R.id.tv_content_name)
    TextView tvContentName;
    @BindView(R.id.tv_top_right)
    TextView tvTopRight;
    @BindView(R.id.iv_top_right)
    ImageView ivTopRight;
    @BindView(R.id.rv_apply_centent)
    RecyclerView rvApplyCentent;
    ApplyCententAdapter applyCententAdapter;
    List<MyApplyBean> myApplyBeans=new ArrayList<>();
    MyApplyPresenter myApplyPresenter=new MyApplyPresenter();
    @Override
    public BasePresenter initPresenter() {
        return myApplyPresenter;
    }


    @Override
    public void initParms(Bundle parms) {

    }

    @Override
    public int bindLayout() {
        return R.layout.activity_my_apply;
    }

    @Override
    public void initView(View view) {
        tvContentName.setText("已发布");
        LinearLayoutManager layoutManager1 = new LinearLayoutManager(getContext());
        rvApplyCentent.setLayoutManager(layoutManager1);
        applyCententAdapter=new ApplyCententAdapter(myApplyBeans,MyApplyActivity.this,myApplyPresenter);
        rvApplyCentent.setAdapter(applyCententAdapter);
    }

    @Override
    public void doBusiness(Context mContext) {
        int uid = (Integer) SPUtils.INSTANCE.getSPValue(SPKey.SP_USER_UID_KEY, SPUtils.DATA_INT);
        String token = (String) SPUtils.INSTANCE.getSPValue(SPKey.SP_USER_TOKEN_KEY, SPUtils.DATA_STRING);
        myApplyPresenter.getMyApplyModel(uid,token,1);
    }

    @Override
    public View initBack() {
        return topBack;
    }

    public void getMyApplyModel(List<MyApplyBean> data) {
        if(data!=null){
            myApplyBeans.clear();
            myApplyBeans.addAll(data);
            applyCententAdapter.notifyDataSetChanged();
        }
    }

    public void delRentInfo() {
        int uid = (Integer) SPUtils.INSTANCE.getSPValue(SPKey.SP_USER_UID_KEY, SPUtils.DATA_INT);
        String token = (String) SPUtils.INSTANCE.getSPValue(SPKey.SP_USER_TOKEN_KEY, SPUtils.DATA_STRING);
        myApplyPresenter.getMyApplyModel(uid,token,1);
    }
}
