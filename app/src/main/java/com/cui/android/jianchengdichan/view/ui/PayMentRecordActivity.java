package com.cui.android.jianchengdichan.view.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.cui.android.jianchengdichan.R;
import com.cui.android.jianchengdichan.http.bean.PayRecordsBean;
import com.cui.android.jianchengdichan.presenter.BasePresenter;
import com.cui.android.jianchengdichan.presenter.PayMentRecordPresenter;
import com.cui.android.jianchengdichan.utils.LogUtils;
import com.cui.android.jianchengdichan.utils.SPKey;
import com.cui.android.jianchengdichan.utils.SPUtils;
import com.cui.android.jianchengdichan.view.BaseActivtity;
import com.cui.android.jianchengdichan.view.ui.Fragment.Adapter.MainRvYouLikeAdapter;
import com.cui.android.jianchengdichan.view.ui.adapter.PayMentRecordAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PayMentRecordActivity extends BaseActivtity {

    @BindView(R.id.top_back)
    ImageView topBack;
    @BindView(R.id.tv_content_name)
    TextView tvContentName;
    @BindView(R.id.tv_top_right)
    TextView tvTopRight;
    @BindView(R.id.iv_top_right)
    ImageView ivTopRight;
    @BindView(R.id.tv_all)
    TextView tvAll;
    @BindView(R.id.v_pay_all_bt)
    View vPayAllBt;
    @BindView(R.id.lin_all)
    RelativeLayout linAll;
    @BindView(R.id.tv_has_pay)
    TextView tvHasPay;
    @BindView(R.id.v_pay_has_bt)
    View vPayHasBt;
    @BindView(R.id.lin_has_pay)
    RelativeLayout linHasPay;
    @BindView(R.id.tv_pay_no)
    TextView tvPayNo;
    @BindView(R.id.v_pay_no_bt)
    View vPayNoBt;
    @BindView(R.id.lin_pay_no)
    RelativeLayout linPayNo;
    @BindView(R.id.rv_pay_data)
    RecyclerView rvPayData;

    private int TYPE = 1;
    private final static int TYPR_ALL_PAY = 1;
    private final static int TYPR_PAY_PAY = 2;
    private final static int TYPR_NO_PAY = 3;
    PayMentRecordPresenter payMentRecordPresenter;
    List<PayRecordsBean> dataList = new ArrayList<>();
    List<PayRecordsBean> showDataList = new ArrayList<>();
    PayMentRecordAdapter mainRvYouLikeAdapter;
    private void showNavigation(int type) {
        switch (type) {
            case TYPR_ALL_PAY:
                vPayNoBt.setVisibility(View.GONE);
                vPayHasBt.setVisibility(View.GONE);
                vPayAllBt.setVisibility(View.VISIBLE);
                break;
            case TYPR_PAY_PAY:
                vPayNoBt.setVisibility(View.GONE);
                vPayHasBt.setVisibility(View.VISIBLE);
                vPayAllBt.setVisibility(View.GONE);
                break;
            case TYPR_NO_PAY:
                vPayNoBt.setVisibility(View.VISIBLE);
                vPayHasBt.setVisibility(View.GONE);
                vPayAllBt.setVisibility(View.GONE);
                break;
        }
    }
    @Override
    public BasePresenter initPresenter() {
        payMentRecordPresenter=new PayMentRecordPresenter();
        return payMentRecordPresenter;
    }

    @Override
    public void initParms(Bundle parms) {

    }

    @Override
    public int bindLayout() {
        return R.layout.activity_pay_ment_record;
    }

    @Override
    public void initView(View view) {
//        initRecyclerView();
    }

    @Override
    public void doBusiness(Context mContext) {
        int uid =(int) SPUtils.INSTANCE.getSPValue(SPKey.SP_USER_UID_KEY,SPUtils.DATA_INT);
        String token =(String) SPUtils.INSTANCE.getSPValue(SPKey.SP_USER_TOKEN_KEY,SPUtils.DATA_STRING);
        payMentRecordPresenter.getChargeRecordAll(uid,token,1);
    }

    @Override
    public void widgetClick(View v) {

    }




    @OnClick({R.id.top_back, R.id.lin_all, R.id.lin_has_pay, R.id.lin_pay_no})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.top_back:
                break;
            case R.id.lin_all:
                showNavigation(TYPR_ALL_PAY);
                initShowData(1);

                break;
            case R.id.lin_has_pay:
                showNavigation(TYPR_PAY_PAY);
                initShowData(3);

                break;
            case R.id.lin_pay_no:
                showNavigation(TYPR_NO_PAY);

                initShowData(2);

                break;
        }
    }

    public void onFailure(String msg) {
    }

    public void onError() {
    }

    public void getChargeRecordAll(List<PayRecordsBean> data) {
        initData(data);
        initShowData(1);
    }

    private void initData(List<PayRecordsBean> data) {
        dataList.clear();
        dataList = data;
    }
    private void initShowData(int type){
        LogUtils.i("initShowData="+type);
        showDataList.clear();
        switch (type){
            case 1:
                showDataList.addAll(dataList);
                break;
            case 2: //未缴费
                LogUtils.i("dataList="+dataList.size());
                for (PayRecordsBean payRecordsBean :dataList){
                    LogUtils.i("payRecordsBean="+payRecordsBean.getPay_status());
                    if(payRecordsBean.getPay_status().equals("1")){

                        showDataList.add(payRecordsBean);
                    }
                }
                break;
            case 3://缴费
                for (PayRecordsBean payRecordsBean :dataList){
                    LogUtils.i("payRecordsBean="+payRecordsBean.getPay_status());
                    if(payRecordsBean.getPay_status().equals("2")){
                        showDataList.add(payRecordsBean);
                    }
                }
                break;
        }
        LogUtils.i("showDataList.size="+showDataList.size());

        initRecyclerView();
    }
    private void initRecyclerView() {
        LinearLayoutManager layoutManager1 = new LinearLayoutManager(getContext());
        rvPayData.setLayoutManager(layoutManager1);
        mainRvYouLikeAdapter = new PayMentRecordAdapter(showDataList);
        rvPayData.setAdapter(mainRvYouLikeAdapter);
    }
}
