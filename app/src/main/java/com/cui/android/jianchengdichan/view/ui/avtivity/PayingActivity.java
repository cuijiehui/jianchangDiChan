package com.cui.android.jianchengdichan.view.ui.avtivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alipay.sdk.app.PayTask;
import com.cui.android.jianchengdichan.R;
import com.cui.android.jianchengdichan.http.bean.AliPayBean;
import com.cui.android.jianchengdichan.http.bean.WeChatPayBean;
import com.cui.android.jianchengdichan.presenter.BasePresenter;
import com.cui.android.jianchengdichan.presenter.PayingPresenter;
import com.cui.android.jianchengdichan.utils.CheckApp;
import com.cui.android.jianchengdichan.utils.LogUtils;
import com.cui.android.jianchengdichan.utils.SPKey;
import com.cui.android.jianchengdichan.utils.SPUtils;
import com.cui.android.jianchengdichan.utils.ToastUtil;
import com.cui.android.jianchengdichan.utils.WXPayUtil2;
import com.cui.android.jianchengdichan.view.base.BaseActivtity;
import com.cui.android.jianchengdichan.view.ui.adapter.PayResult;
import com.cui.android.jianchengdichan.view.ui.beans.PayingBean;

import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

public class PayingActivity extends BaseActivtity {

    @BindView(R.id.top_back)
    RelativeLayout topBack;
    @BindView(R.id.tv_content_name)
    TextView tvContentName;
    @BindView(R.id.tv_top_right)
    TextView tvTopRight;
    @BindView(R.id.iv_top_right)
    ImageView ivTopRight;
    @BindView(R.id.iv_paying_icon)
    ImageView ivPayingIcon;
    @BindView(R.id.tv_paying_name)
    TextView tvPayingName;
    @BindView(R.id.tv_paying_time)
    TextView tvPayingTime;
    @BindView(R.id.tv_paying_num)
    TextView tvPayingNum;
    @BindView(R.id.iv_paying_wechat_select)
    ImageView ivPayingWechatSelect;
    @BindView(R.id.iv_paying_alipay_select)
    ImageView ivPayingAlipaySelect;
    @BindView(R.id.et_paying_remarks)
    EditText etPayingRemarks;
    @BindView(R.id.bt_paying_ok)
    Button btPayingOk;
    @BindView(R.id.tv_paying_price)
    TextView tvPayingPrice;

    PayingPresenter payingPresenter;

    private boolean isPaySelect = true;
    PayingBean mBean;
    String mTypeName = "";

    @Override
    public BasePresenter initPresenter() {
        payingPresenter=new PayingPresenter();
        return payingPresenter;
    }


    @Override
    public void initParms(Bundle parms) {
        mBean = (PayingBean) parms.getSerializable("bean");
        mTypeName = parms.getString("typeName");
        LogUtils.i("-------------------" + mTypeName);

    }

    @Override
    public int bindLayout() {
        return R.layout.activity_paying;
    }

    @Override
    public void initView(View view) {
        LogUtils.i("-------------------" + mBean.toString());
        tvContentName.setText("缴费中");
        ivPayingWechatSelect.setSelected(true);
        tvPayingName.setText(mBean.getName());
        tvPayingTime.setText(mBean.getTime());
        tvPayingNum.setText(mBean.getNum());
        tvPayingPrice.setText(mBean.getPrice()+"元");
    }

    @Override
    public void doBusiness(Context mContext) {

    }

    @Override
    public View initBack() {
        return topBack;
    }


    @OnClick({R.id.top_back, R.id.iv_paying_wechat_select, R.id.iv_paying_alipay_select, R.id.bt_paying_ok})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.top_back:
                break;
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
            case R.id.bt_paying_ok:
                if (isPaySelect) {
                    ToastUtil.makeToast("微信支付");
                    if (CheckApp.isWeixinAvilible(mContext)) {
//                        WXPayUtil2 wxpay = new WXPayUtil2(mContext);
                      int uid= (int) SPUtils.INSTANCE.getSPValue(SPKey.SP_USER_UID_KEY,SPUtils.DATA_INT);
                      String token =(String)  SPUtils.INSTANCE.getSPValue(SPKey.SP_USER_TOKEN_KEY,SPUtils.DATA_STRING);
                        payingPresenter.getWeixin(uid,token,mBean.getId());
                    } else {
                        ToastUtil.makeToast("您未安装微信");
                    }
                } else {
                    ToastUtil.makeToast("支付宝支付");
                    int uid= (int) SPUtils.INSTANCE.getSPValue(SPKey.SP_USER_UID_KEY,SPUtils.DATA_INT);
                    String token =(String)  SPUtils.INSTANCE.getSPValue(SPKey.SP_USER_TOKEN_KEY,SPUtils.DATA_STRING);
                    payingPresenter.getAliPay(uid,token,mBean.getId());
                }
                break;
        }
    }


    public void getWeChatPay(WeChatPayBean data) {
        WXPayUtil2 wxpay = new WXPayUtil2(mContext);
        wxpay.payExChange(data);
    }
  public final static int   SDK_PAY_FLAG = 22;
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
    public void getAliPay(final AliPayBean data) {

        Runnable payRunnable = new Runnable() {

            @Override
            public void run() {

                PayTask payTask = new PayTask(PayingActivity.this);
                LogUtils.e("current alipay version -->" + payTask.getVersion());
                Map<String, String> results = payTask.payV2(data.getOrderinfo(), true);

                Message msg = new Message();
                msg.what = SDK_PAY_FLAG;
                msg.obj = results;
                mHandler.sendMessage(msg);
            }
        };
        Thread payThread = new Thread(payRunnable);
        payThread.start();
    }
}
