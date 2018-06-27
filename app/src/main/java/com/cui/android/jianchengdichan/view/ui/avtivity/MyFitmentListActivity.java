package com.cui.android.jianchengdichan.view.ui.avtivity;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.cui.android.jianchengdichan.R;
import com.cui.android.jianchengdichan.http.bean.RenovationBean;
import com.cui.android.jianchengdichan.presenter.BasePresenter;
import com.cui.android.jianchengdichan.presenter.MyFitmentListPresenter;
import com.cui.android.jianchengdichan.utils.SPKey;
import com.cui.android.jianchengdichan.utils.SPUtils;
import com.cui.android.jianchengdichan.view.base.BaseActivtity;
import com.cui.android.jianchengdichan.view.ui.adapter.FitmentListAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class MyFitmentListActivity extends BaseActivtity {
    @BindView(R.id.top_back)
    RelativeLayout topBack;
    @BindView(R.id.tv_content_name)
    TextView tvContentName;
    @BindView(R.id.tv_top_right)
    TextView tvTopRight;
    @BindView(R.id.iv_top_right)
    ImageView ivTopRight;
    @BindView(R.id.rv_fitment_list)
    RecyclerView rvFitmentList;
    MyFitmentListPresenter myFitmentListPresenter = new MyFitmentListPresenter();

    List<RenovationBean> data = new ArrayList<>();
    FitmentListAdapter fitmentListAdapter;

    @Override
    public BasePresenter initPresenter() {
        return myFitmentListPresenter;
    }

    @Override
    protected void onResume() {
        super.onResume();
        int uid = (Integer) SPUtils.INSTANCE.getSPValue(SPKey.SP_USER_UID_KEY, SPUtils.DATA_INT);
        String token = (String) SPUtils.INSTANCE.getSPValue(SPKey.SP_USER_TOKEN_KEY, SPUtils.DATA_STRING);
        myFitmentListPresenter.getRenovationInfoList(uid, token, "1");

    }

    @Override
    public void initParms(Bundle parms) {

    }

    @Override
    public int bindLayout() {
        return R.layout.activity_my_fitment_list;
    }

    @Override
    public void initView(View view) {
        tvContentName.setText("我的装修");
        tvTopRight.setText("申请");
        tvTopRight.setVisibility(View.VISIBLE);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
        rvFitmentList.setLayoutManager(linearLayoutManager);
        fitmentListAdapter = new FitmentListAdapter(data);
        rvFitmentList.setAdapter(fitmentListAdapter);
        //添加Android自带的分割线
        rvFitmentList.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
    }

    @Override
    public void doBusiness(Context mContext) {

    }

    @Override
    public View initBack() {
        return topBack;
    }


    public void getRenovationInfoList(List<RenovationBean> dataList) {
        data.clear();
        if (data != null) {
            this.data.addAll(dataList);
        }
        fitmentListAdapter.notifyDataSetChanged();
    }

    @OnClick(R.id.tv_top_right)
    public void onViewClicked() {
        startActivity(FitmentActivity.class);
    }
}
