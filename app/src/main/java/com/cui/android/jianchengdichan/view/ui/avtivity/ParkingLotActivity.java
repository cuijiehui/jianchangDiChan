package com.cui.android.jianchengdichan.view.ui.avtivity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.cui.android.jianchengdichan.R;
import com.cui.android.jianchengdichan.http.bean.CarGoingBean;
import com.cui.android.jianchengdichan.http.bean.CarInfoBean;
import com.cui.android.jianchengdichan.presenter.BasePresenter;
import com.cui.android.jianchengdichan.presenter.ParkingLotPresenter;
import com.cui.android.jianchengdichan.utils.LocationUtils;
import com.cui.android.jianchengdichan.utils.ScreenUtils;
import com.cui.android.jianchengdichan.view.base.BaseActivity;
import com.cui.android.jianchengdichan.view.ui.adapter.CarGoingDataAdapter;
import com.cui.android.jianchengdichan.view.ui.customview.MapPopupWindows;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class ParkingLotActivity extends BaseActivity {
    ParkingLotPresenter mPresenter = new ParkingLotPresenter();
    public static final String CAR_NO_KEY = "car_no_key";
    public static final String TYPE_KEY = "type_key";

    @BindView(R.id.top_back)
    RelativeLayout topBack;
    @BindView(R.id.tv_content_name)
    TextView tvContentName;
    @BindView(R.id.rv_parking_data)
    RecyclerView rvParkingData;
    CarGoingDataAdapter mCarGoingDataAdapter;
    List<CarGoingBean> dataList = new ArrayList<>();
    LocationManager locationManager;
    Location mBest;
    @BindView(R.id.tv_top_right)
    TextView tvTopRight;

    String carNo="";
    String mType = "3";
    public static Intent getStartIntent(Context context,String carNo,String type) {
        Intent intent = new Intent(context, ParkingLotActivity.class);
        intent.putExtra(CAR_NO_KEY,carNo);
        intent.putExtra(TYPE_KEY,type);
        return intent;
    }

    public static Intent getStartIntent(Context context) {
        Intent intent = new Intent(context, ParkingLotActivity.class);
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
        return R.layout.activity_parking_lot;
    }

    @Override
    public void initView(View view) {
        tvContentName.setText("附近车场");
        tvTopRight.setVisibility(View.VISIBLE);
        tvTopRight.setText("订单记录");
        initRv();
        checkPermission(new CheckPermListener() {
            @Override
            public void superPermission() {

            }
        }, R.string.perm_tip, Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION);
    }

    @Override
    public void doBusiness(Context mContext) {
        carNo=getIntent().getStringExtra(CAR_NO_KEY);
        mType=getIntent().getStringExtra(TYPE_KEY);
        getBestLocation();
        mPresenter.getCarGoingInfo();
    }


    @Override
    public View initBack() {
        return null;
    }

    private void initRv() {
        mCarGoingDataAdapter = new CarGoingDataAdapter(dataList);
        rvParkingData.setLayoutManager(new LinearLayoutManager(mContext));
        rvParkingData.setAdapter(mCarGoingDataAdapter);
        mCarGoingDataAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                //TODO 跳界面
            }
        });
        mCarGoingDataAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {

//                Log.i("onclick测试", "onItemChildClick: "+position);
                CarGoingBean carGoingBean = dataList.get(position);
                double latitude = new Double(carGoingBean.getLatitude());
                double loingitude = new Double(carGoingBean.getLongitude());
                MapPopupWindows mapPopupWindows = new MapPopupWindows(ParkingLotActivity.this, getRootView(), latitude, loingitude);
            }
        });
        mCarGoingDataAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                if ("0".equals(mType)) {
                    CarGoingBean carGoingBean = dataList.get(position);
                    CarInfoBean carInfoBean =new CarInfoBean(carNo,carGoingBean.getParkCode(),carGoingBean.getParkName());
                    startActivity(CheckedCarActivity.getStartIntent(mContext,"0",carInfoBean));
                    finish();
                }else if("1".equals(mType)){
                    CarGoingBean carGoingBean = dataList.get(position);
                    CarInfoBean carInfoBean =new CarInfoBean(carNo,carGoingBean.getParkCode(),carGoingBean.getParkName());
                    startActivity(CheckedCarActivity.getStartIntent(mContext,"1",carInfoBean));
                    finish();
                }
            }
        });
    }

    public void getCarGoingInfo(List<CarGoingBean> data) {
        dataList.clear();
        if (mBest != null) {
            for (CarGoingBean datum : data) {
                double lat_a = new Double(datum.getLatitude());
                double lng_a = new Double(datum.getLongitude());
                datum.setDistance(Double.toString(ScreenUtils.gps2m(lat_a, lng_a, mBest.getLatitude(), mBest.getLongitude())));
            }
        } else {
            for (CarGoingBean datum : data) {
                datum.setDistance("未知");
            }
        }
        dataList.addAll(data);
        mCarGoingDataAdapter.notifyDataSetChanged();
    }

    /**
     * 采用最好的方式获取定位信息
     */
    private void getBestLocation() {
        Criteria c = new Criteria();//Criteria类是设置定位的标准信息（系统会根据你的要求，匹配最适合你的定位供应商），一个定位的辅助信息的类
        c.setPowerRequirement(Criteria.POWER_LOW);//设置低耗电
        c.setAltitudeRequired(true);//设置需要海拔
        c.setBearingAccuracy(Criteria.ACCURACY_COARSE);//设置COARSE精度标准
        c.setAccuracy(Criteria.ACCURACY_LOW);//设置低精度
        //... Criteria 还有其他属性，就不一一介绍了
        mBest = LocationUtils.getBestLocation(this, c);
//        if (mBest == null) {
//            Toast.makeText(this, " best location is null", Toast.LENGTH_SHORT).show();
//        } else {
//            Toast.makeText(this, "best location: lat==" + mBest.getLatitude() + " lng==" + mBest.getLongitude(), Toast.LENGTH_SHORT).show();
//        }
    }
    @OnClick(R.id.tv_top_right)
    public void onTopRightClick(){
        startActivity(CheckedCarActivity.getStartIntent(mContext,"1"));
    }
    @OnClick(R.id.top_back)
    public void onBack(){
        if ("0".equals(mType)) {
            CarInfoBean carInfoBean =new CarInfoBean();
            carInfoBean.setCarNo(carNo);
            startActivity(CheckedCarActivity.getStartIntent(mContext,"0",carInfoBean));
            finish();
        }else if("1".equals(mType)){
            CarInfoBean carInfoBean =new CarInfoBean();
            carInfoBean.setCarNo(carNo);
            startActivity(CheckedCarActivity.getStartIntent(mContext,"1",carInfoBean));
            finish();
        }else{
            finish();
        }
    }

    @Override
    public void onBackPressed() {
        if ("0".equals(mType)) {
            CarInfoBean carInfoBean =new CarInfoBean();
            carInfoBean.setCarNo(carNo);
            startActivity(CheckedCarActivity.getStartIntent(mContext,"0",carInfoBean));
            finish();
        }else if("1".equals(mType)){
            CarInfoBean carInfoBean =new CarInfoBean();
            carInfoBean.setCarNo(carNo);
            startActivity(CheckedCarActivity.getStartIntent(mContext,"1",carInfoBean));
            finish();
        }else{
            super.onBackPressed();
        }
    }
}
