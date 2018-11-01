package com.cui.android.jianchengdichan.view.ui.avtivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.baoyz.widget.PullRefreshLayout;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.cui.android.jianchengdichan.R;
import com.cui.android.jianchengdichan.http.bean.CarChargeLogBean;
import com.cui.android.jianchengdichan.http.bean.CarCostBean;
import com.cui.android.jianchengdichan.presenter.BasePresenter;
import com.cui.android.jianchengdichan.presenter.CheckedCarPresenter;
import com.cui.android.jianchengdichan.view.base.BaseActivity;
import com.cui.android.jianchengdichan.view.ui.adapter.CarChargeDataAdapter;
import com.cui.android.jianchengdichan.view.ui.adapter.CarGoingDataAdapter;
import com.cui.android.jianchengdichan.view.ui.customview.PayPwdEditText;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CheckedCarActivity extends BaseActivity {
    public static final String TYPE_KEY = "type_key";
    @BindView(R.id.top_back)
    RelativeLayout topBack;
    @BindView(R.id.tv_content_name)
    TextView tvContentName;
    @BindView(R.id.ppet_car_number)
    PayPwdEditText ppetCarNumber;
    @BindView(R.id.rv_charge_data)
    RecyclerView rvChargeData;
    @BindView(R.id.prl_refreshable)
    PullRefreshLayout prlRefreshable;
    CarChargeDataAdapter mAdapter;
    List<CarChargeLogBean> dataList = new ArrayList<>();
    int page =1;
    public static Intent getStartIntent(Context context,String type) {
        Intent intent = new Intent(context, CheckedCarActivity.class);
        intent.putExtra(TYPE_KEY,type);
        return intent;
    }

    CheckedCarPresenter mCheckedCarPresenter = new CheckedCarPresenter();

    @Override
    public BasePresenter initPresenter() {
        return mCheckedCarPresenter;
    }

    @Override
    public void initParms(Bundle parms) {

    }

    @Override
    public int bindLayout() {
        return R.layout.activity_checked_car;
    }

    @Override
    public void initView(View view) {
        tvContentName.setText("找车缴费");
        initRv();
    }

    @Override
    public void doBusiness(Context mContext) {

    }

    @Override
    public View initBack() {
        return topBack;
    }


    @OnClick(R.id.bt_goto_checked)
    public void onViewClicked() {
        Log.i("测试", "onViewClicked: " + ppetCarNumber.getPwdText());

        StringBuffer carNo = new StringBuffer();

//        mCheckedCarPresenter.checkedCarCost("粤AFN898",null);
        mCheckedCarPresenter.getChargeLog(ppetCarNumber.getPwdText(), page+"");
    }

    public void checkedCarCost(List<CarCostBean> data) {

    }

    private void initRv() {
        mAdapter = new CarChargeDataAdapter(dataList);
        rvChargeData.setLayoutManager(new LinearLayoutManager(mContext));
        rvChargeData.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                //TODO 跳界面
            }
        });
    }

    public void getChargeLog(List<CarChargeLogBean> data) {
        dataList.clear();
        dataList.addAll(data);
        mAdapter.notifyDataSetChanged();

    }


}
