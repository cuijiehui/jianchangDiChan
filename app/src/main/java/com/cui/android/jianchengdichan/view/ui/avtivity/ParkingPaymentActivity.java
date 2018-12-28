package com.cui.android.jianchengdichan.view.ui.avtivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.cui.android.jianchengdichan.R;
import com.cui.android.jianchengdichan.http.bean.CarCostBean;
import com.cui.android.jianchengdichan.http.bean.CreateOrderBean;
import com.cui.android.jianchengdichan.http.bean.WeChatPayBean;
import com.cui.android.jianchengdichan.presenter.BasePresenter;
import com.cui.android.jianchengdichan.presenter.ParkingPaymentPresenter;
import com.cui.android.jianchengdichan.utils.CheckApp;
import com.cui.android.jianchengdichan.utils.LogUtils;
import com.cui.android.jianchengdichan.utils.ToastUtil;
import com.cui.android.jianchengdichan.utils.WXPayUtil2;
import com.cui.android.jianchengdichan.view.base.BaseActivity;
import com.cui.android.jianchengdichan.view.ui.adapter.PayResult;

import java.util.Map;

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
    private boolean isPaySelect = true;

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
    public void initParam(Bundle param) {

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
                isPaySelect = true;

                break;
            case R.id.iv_paying_alipay_select:
                ivPayingWechatSelect.setSelected(false);
                ivPayingAlipaySelect.setSelected(true);
                isPaySelect = false;

                break;
            case R.id.bt_paying:
//                ToastUtil.makeToast("支付功能正在申请中。");
                if (isPaySelect) {
                    ToastUtil.makeToast("微信支付");
                    if (CheckApp.isWeixinAvilible(mContext)) {
//                        WXPayUtil2 wxpay = new WXPayUtil2(mContext);
//                        int uid= (int) SPUtils.INSTANCE.getSPValue(SPKey.SP_USER_UID_KEY,SPUtils.DATA_INT);
//                        String token =(String)  SPUtils.INSTANCE.getSPValue(SPKey.SP_USER_TOKEN_KEY,SPUtils.DATA_STRING);
                        mPresenter.createOrder(mCarCostBean.getCarNo(),mCarCostBean.getParkCode());

                    } else {
                        ToastUtil.makeToast("您未安装微信");
                    }
                } else {
                    ToastUtil.makeToast("支付宝支付");
//                    mPresenter.createOrder(mCarCostBean.getCarNo(),"20070580001");

                }
                break;
        }
    }

    @OnClick(R.id.bt_cancel)
    public void onCancel(){
        mPresenter.cancelOrder(order_no);
    }

    private String order_no = "";
    public void getCreateOrder(CreateOrderBean data) {
        order_no=data.getOrder_no();
        LogUtils.i("order_no="+order_no);
        mPresenter.getWeixin(data.getOrder_no());
    }

    public void cancel() {
        ToastUtil.makeToast("取消订单成功");
    }

    public void getWeChatPay(WeChatPayBean data) {
        WXPayUtil2 wxpay = new WXPayUtil2(mContext);
        wxpay.payExChange(data);
    }
    public final static int SDK_PAY_FLAG = 22;
    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case SDK_PAY_FLAG:
//                    ToastUtil.makeToast("支付成功");
                    PayResult result = new PayResult((Map<String, String>) msg.obj);
                    String resultStatus = result.getResultStatus();
                    String resultinfo = result.getResult();
                    LogUtils.i("resultinfo=" + resultinfo);
                    LogUtils.i("resultStatus=" + resultStatus);
                    if (TextUtils.equals(resultStatus, "9000")) {
//                        EventBus.getDefault().post(new FirstEvent("4"));
//                        Intent intent2 = new Intent(CommunityPayAct.this, StoreOrderAct.class);
//                        intent2.putExtra("flag" , "0");
//                        startActivity(intent2);
//                        LogUtil.e("支付成功");
                        ToastUtil.makeToast("支付成功");
//                        CommunityPayAct.this.finish();
                    } else {
//                        LogUtil.e("resultCode:" + resultStatus + "\nresultInfo:" + resultinfo);
                        ToastUtil.makeToast("支付失败");
                    }
                    break;
            }
        }
    };
}
