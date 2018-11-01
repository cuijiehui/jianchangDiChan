package com.cui.android.jianchengdichan.view.ui.avtivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.cui.android.jianchengdichan.R;
import com.cui.android.jianchengdichan.http.bean.CarCostBean;
import com.cui.android.jianchengdichan.presenter.BasePresenter;
import com.cui.android.jianchengdichan.presenter.ParkingPaymentPresenter;
import com.cui.android.jianchengdichan.utils.ToastUtil;
import com.cui.android.jianchengdichan.view.base.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

public class ParkingPaymentActivity extends BaseActivity {
    public static final String BEAN_KEY="bean_key";
    ParkingPaymentPresenter mPresenter = new ParkingPaymentPresenter();
    @BindView(R.id.top_back)
    RelativeLayout topBack;
    @BindView(R.id.tv_content_name)
    TextView tvContentName;
    @BindView(R.id.tv_approach_time)
    TextView tvApproachTime;
    @BindView(R.id.tv_stop_time)
    TextView tvStopTime;
    @BindView(R.id.tv_paying_money)
    TextView tvPayingMoney;
    @BindView(R.id.iv_paying_wechat_select)
    ImageView ivPayingWechatSelect;
    @BindView(R.id.iv_paying_alipay_select)
    ImageView ivPayingAlipaySelect;

    CarCostBean mCarCostBean ;
    public static Intent getStartIntent(Context context , CarCostBean carCostBean){
        Intent intent = new Intent(context,ParkingPaymentActivity.class);
        intent.putExtra(BEAN_KEY,carCostBean);
        return intent;
    }

    @Override
    public BasePresenter initPresenter() {
        return mPresenter;
    }

    @Override
    public void initParms(Bundle parms) {

    }

    @Override
    public int bindLayout() {
        return R.layout.activity_parking_payment;
    }

    @Override
    public void initView(View view) {

    }

    @Override
    public void doBusiness(Context mContext) {
        tvContentName.setText("停车缴费");
        mCarCostBean=getIntent().getParcelableExtra(BEAN_KEY);
        ivPayingWechatSelect.setSelected(true);
        ivPayingAlipaySelect.setSelected(false);
        if(mCarCostBean!=null){
            tvApproachTime.setText(mCarCostBean.getStartTime());
            tvStopTime.setText(mCarCostBean.getParkTime());
            tvPayingMoney.setText(mCarCostBean.getTotalCharge());
        }

    }

    @Override
    public View initBack() {
        return topBack;
    }



    @OnClick({R.id.iv_paying_wechat_select, R.id.iv_paying_alipay_select, R.id.bt_paying})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_paying_wechat_select:
                ivPayingWechatSelect.setSelected(true);
                ivPayingAlipaySelect.setSelected(false);
                break;
            case R.id.iv_paying_alipay_select:
                ivPayingWechatSelect.setSelected(false);
                ivPayingAlipaySelect.setSelected(true);
                break;
            case R.id.bt_paying:
                ToastUtil.makeToast("支付功能正在申请中。");
                break;
        }
    }
}
