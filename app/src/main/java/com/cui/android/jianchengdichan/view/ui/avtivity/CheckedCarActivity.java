package com.cui.android.jianchengdichan.view.ui.avtivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.cui.android.jianchengdichan.R;
import com.cui.android.jianchengdichan.http.bean.CarChargeLogBean;
import com.cui.android.jianchengdichan.http.bean.CarCostBean;
import com.cui.android.jianchengdichan.http.bean.CarInfoBean;
import com.cui.android.jianchengdichan.presenter.BasePresenter;
import com.cui.android.jianchengdichan.presenter.CheckedCarPresenter;
import com.cui.android.jianchengdichan.utils.SPKey;
import com.cui.android.jianchengdichan.utils.SPUtils;
import com.cui.android.jianchengdichan.utils.ToastUtil;
import com.cui.android.jianchengdichan.view.base.BaseActivity;
import com.cui.android.jianchengdichan.view.ui.adapter.CarChargeDataAdapter;
import com.cui.android.jianchengdichan.view.ui.customview.PayPwdEditText;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class CheckedCarActivity extends BaseActivity {
    public static final String TYPE_KEY = "type_key";
    public static final String DATA_KEY = "data_key";

    @BindView(R.id.top_back)
    RelativeLayout topBack;
    @BindView(R.id.tv_content_name)
    TextView tvContentName;
    @BindView(R.id.tv_car_name)
    TextView tv_car_name;
    @BindView(R.id.ppet_car_number)
    PayPwdEditText ppetCarNumber;
    @BindView(R.id.rv_charge_data)
    RecyclerView rvChargeData;
    @BindView(R.id.ll_checked)
    LinearLayout llChecked;
    CarChargeDataAdapter mAdapter;
    List<CarChargeLogBean> dataList = new ArrayList<>();
    boolean isChargeLog = false;
    int page = 1;
    String type = "0";
    String mCarNo = "";
    String mParkCode = "20070580001";
    String mParkName = "";
    CarInfoBean mCarInfoBean;

    public static Intent getStartIntent(Context context, String type, CarInfoBean carInfoBean) {
        Intent intent = new Intent(context, CheckedCarActivity.class);
        intent.putExtra(TYPE_KEY, type);
        intent.putExtra(DATA_KEY, carInfoBean);
        return intent;
    }

    public static Intent getStartIntent(Context context, String type) {
        Intent intent = new Intent(context, CheckedCarActivity.class);
        intent.putExtra(TYPE_KEY, type);
        return intent;
    }

    CheckedCarPresenter mCheckedCarPresenter = new CheckedCarPresenter();

    @Override
    public BasePresenter initPresenter() {
        return mCheckedCarPresenter;
    }

    @Override
    public void initParam(Bundle param) {

    }

    @Override
    public int bindLayout() {
        return R.layout.activity_checked_car;
    }

    @Override
    public void initView(View view) {
        type = getIntent().getStringExtra(TYPE_KEY);
        mCarInfoBean = (CarInfoBean) getIntent().getSerializableExtra(DATA_KEY);
        mCarNo = (String) SPUtils.INSTANCE.getSPValue(SPKey.SP_CAR_NO_KEY, SPUtils.DATA_STRING);

        if (mCarInfoBean != null) {
            if (!TextUtils.isEmpty(mCarInfoBean.getCarNo())) {
                mCarNo = mCarInfoBean.getCarNo();
            }
            mParkCode = mCarInfoBean.getParkCode();
            mParkName = mCarInfoBean.getParkName();
            if (!TextUtils.isEmpty(mParkName)) {
                tv_car_name.setText(mParkName);
            }
        }
        ppetCarNumber.setEditText(mCarNo);

        if ("0".equals(type)) {
            tvContentName.setText("找车缴费");
        } else {
            tvContentName.setText("查询车俩缴费记录");
        }
        initRv();
    }

    @Override
    public void doBusiness(Context mContext) {

    }

    @Override
    public View initBack() {
        return null;
    }


    @OnClick(R.id.bt_goto_checked)
    public void onViewClicked() {
//        Log.i("测试", "onViewClicked: " + ppetCarNumber.getPwdText());

        String carNo = ppetCarNumber.getPwdText();
        if (TextUtils.isEmpty(carNo)) {
            ToastUtil.makeToast("车牌不能为空！");
            return;
        }
        mCarNo = carNo;
//        mCarNo="粤A9P9T6";
        if ("0".equals(type)) {
//            mCheckedCarPresenter.checkedCarCost("粤A9P9T6", "20070580001");
            mCheckedCarPresenter.checkedCarCost(carNo, mParkCode);
        } else {
            mCheckedCarPresenter.getChargeLog(carNo,mParkCode, page + "");
            isChargeLog = true;
        }
        showLoading();
    }


    public void checkedCarCost(CarCostBean data) {
        hideLoading();
        if (TextUtils.isEmpty(mCarNo)) {
            ToastUtil.makeToast("车牌号不能为空！");
            return;
        }
        if ("1".equals(data.getIs_free())) {
            ToastUtil.makeToast("车俩使用月卡不用缴费！");
            return;
        }
        data.setCarNo(mCarNo);
        data.setParkCode(mParkCode);
        startActivity(ParkingPaymentActivity.getStartIntent(mContext, data));
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
        mAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                CarChargeLogBean carChargeLogBean = dataList.get(position);
                if (carChargeLogBean != null) {
                    mCheckedCarPresenter.cancelOrder(carChargeLogBean.getOrderNo());
                }
            }
        });
    }

    public void getChargeLog(List<CarChargeLogBean> data) {
        if (data == null) {
            ToastUtil.makeToast("车俩没有缴费订单！");
            return;
        }
        hideLoading();
        dataList.clear();
        dataList.addAll(data);
        mAdapter.notifyDataSetChanged();
        changeView();
    }

    public void changeView() {
        if (isChargeLog) {
            llChecked.setVisibility(View.GONE);
            rvChargeData.setVisibility(View.VISIBLE);
        } else {
            llChecked.setVisibility(View.VISIBLE);
            rvChargeData.setVisibility(View.GONE);
        }
    }

    @OnClick({R.id.top_back})
    public void onBack() {
        if (isChargeLog) {
            isChargeLog = false;
            changeView();
        } else {
            finish();
        }
    }

    @Override
    public void onBackPressed() {
        if (isChargeLog) {
            isChargeLog = false;
            changeView();
        } else {
            finish();
        }
    }

    @OnClick(R.id.rl_car_park)
    public void onCarPark() {
        mCarNo = ppetCarNumber.getPwdText();
        startActivity(ParkingLotActivity.getStartIntent(mContext, mCarNo, type));
        finish();
    }

    public void cancel() {
        ToastUtil.makeToast("取消订单成功");
    }
}
