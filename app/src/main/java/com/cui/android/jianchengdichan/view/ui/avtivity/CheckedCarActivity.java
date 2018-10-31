package com.cui.android.jianchengdichan.view.ui.avtivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.cui.android.jianchengdichan.R;
import com.cui.android.jianchengdichan.http.bean.CarCostBean;
import com.cui.android.jianchengdichan.presenter.BasePresenter;
import com.cui.android.jianchengdichan.presenter.CheckedCarPresenter;
import com.cui.android.jianchengdichan.view.base.BaseActivity;
import com.cui.android.jianchengdichan.view.ui.customview.PayPwdEditText;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class CheckedCarActivity extends BaseActivity {
    @BindView(R.id.top_back)
    RelativeLayout topBack;
    @BindView(R.id.tv_content_name)
    TextView tvContentName;
    @BindView(R.id.ppet_car_number)
    PayPwdEditText ppetCarNumber;

    public static Intent getStartIntent(Context context){
        Intent intent = new Intent(context,CheckedCarActivity.class);
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
        Log.i("测试", "onViewClicked: "+ppetCarNumber.getPwdText());

        StringBuffer carNo = new StringBuffer();

        mCheckedCarPresenter.checkedCarCost("粤AFN898",null);
    }

    public void checkedCarCost(List<CarCostBean> data) {

    }
}
