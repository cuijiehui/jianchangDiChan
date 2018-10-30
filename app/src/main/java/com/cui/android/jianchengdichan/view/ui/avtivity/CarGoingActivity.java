package com.cui.android.jianchengdichan.view.ui.avtivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.cui.android.jianchengdichan.R;
import com.cui.android.jianchengdichan.http.bean.CarGoingBean;
import com.cui.android.jianchengdichan.presenter.BasePresenter;
import com.cui.android.jianchengdichan.presenter.CarGoingPresenter;
import com.cui.android.jianchengdichan.view.base.BaseActivtity;
import com.cui.android.jianchengdichan.view.ui.adapter.CarGoingDataAdapter;
import com.youth.banner.Banner;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CarGoingActivity extends BaseActivtity {

    CarGoingPresenter mCarGoingPresenter;
    @BindView(R.id.top_back)
    RelativeLayout topBack;
    @BindView(R.id.tv_content_name)
    TextView tvContentName;
    @BindView(R.id.tv_top_right)
    TextView tvTopRight;
    @BindView(R.id.iv_top_right)
    ImageView ivTopRight;
    @BindView(R.id.et_seek)
    EditText etSeek;
    @BindView(R.id.bn_adv)
    Banner bnAdv;
    @BindView(R.id.rv_parking_lot)
    RecyclerView rvParkingLot;
    CarGoingDataAdapter mCarGoingDataAdapter;
    List<CarGoingBean> carGoingBeans = new ArrayList<>();

    public static Intent getStartIntent(Context context){
        Intent intent = new Intent(context,CarGoingActivity.class);
        return intent;
    }
    @Override
    public BasePresenter initPresenter() {
        mCarGoingPresenter = new CarGoingPresenter();
        return mCarGoingPresenter;
    }

    @Override
    public void initParms(Bundle parms) {

    }

    @Override
    public int bindLayout() {
        return R.layout.activity_car_going;
    }

    @Override
    public void initView(View view) {
        tvContentName.setText("车辆出行");
        ivTopRight.setBackgroundResource(R.drawable.main_qrcode_button);
        ivTopRight.setVisibility(View.VISIBLE);
    }

    @Override
    public void doBusiness(Context mContext) {
        initRv();
        mCarGoingPresenter.getCarGoingInfo();
    }

    private void initRv() {
        mCarGoingDataAdapter =new CarGoingDataAdapter(carGoingBeans);
        rvParkingLot.setLayoutManager(new LinearLayoutManager(mContext));
        rvParkingLot.setAdapter(mCarGoingDataAdapter);
        mCarGoingDataAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                //TODO 跳界面

            }
        });
    }

    @Override
    public View initBack() {
        return topBack;
    }


    @OnClick({R.id.tv_address, R.id.rl_find_car})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_address:
                break;
            case R.id.rl_find_car:
                startActivity(CheckedCarActivity.getStartIntent(mContext));
                break;
        }
    }

    public void getCarGoingInfo(List<CarGoingBean> data) {

    }
}
