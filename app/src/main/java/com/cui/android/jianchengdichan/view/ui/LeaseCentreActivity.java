package com.cui.android.jianchengdichan.view.ui;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.cui.android.jianchengdichan.R;
import com.cui.android.jianchengdichan.presenter.BasePresenter;
import com.cui.android.jianchengdichan.presenter.LoginPresenter;
import com.cui.android.jianchengdichan.utils.LogUtils;
import com.cui.android.jianchengdichan.view.BaseActivtity;
import com.cui.android.jianchengdichan.view.ui.adapter.LeaseAdapter;
import com.cui.android.jianchengdichan.view.ui.beans.LeaseRoomBean;
import com.cui.android.jianchengdichan.view.ui.customview.ChildCommunityBean;
import com.cui.android.jianchengdichan.view.ui.customview.ChooseAreaPop;
import com.cui.android.jianchengdichan.view.ui.customview.LeaseMorePop;
import com.cui.android.jianchengdichan.view.ui.customview.LeaseRentPop;
import com.cui.android.jianchengdichan.view.ui.customview.LeaseUnitPop;
import com.cui.android.jianchengdichan.view.ui.customview.interfaces.IdentityResultListener;
import com.cui.android.jianchengdichan.view.ui.customview.interfaces.ReturnMoreListener;
import com.cui.android.jianchengdichan.view.ui.customview.interfaces.ReturnPriceListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LeaseCentreActivity extends BaseActivtity implements AdapterView.OnItemSelectedListener {
    LoginPresenter loginPresenter;
    @BindView(R.id.tv_content_name)
    TextView tvContentName;
    @BindView(R.id.top_back)
    ImageView topBack;
    @BindView(R.id.tv_address)
    TextView tvAddress;
    @BindView(R.id.iv_address_index)
    ImageView ivAddressIndex;
    @BindView(R.id.ll_choose_address)
    LinearLayout llChooseAddress;
    @BindView(R.id.iv_awards_type_search)
    ImageView ivAwardsTypeSearch;
    @BindView(R.id.et_lease_search)
    EditText etLeaseSearch;
    @BindView(R.id.iv_search)
    ImageView ivSearch;
    @BindView(R.id.relative_awards_type_title)
    RelativeLayout relativeAwardsTypeTitle;
    @BindView(R.id.tv_choose_area)
    TextView tvChooseArea;
    @BindView(R.id.lin_choose_area)
    LinearLayout linChooseArea;
    @BindView(R.id.tv_lease_rent)
    TextView tvLeaseRent;
    @BindView(R.id.lin_lease_rent)
    LinearLayout linLeaseRent;
    @BindView(R.id.tv_lease_unit)
    TextView tvLeaseUnit;
    @BindView(R.id.lin_lease_unit)
    LinearLayout linLeaseUnit;
    @BindView(R.id.tv_lease_more)
    TextView tvLeaseMore;
    @BindView(R.id.lin_lease_more)
    LinearLayout linLeaseMore;
    @BindView(R.id.rc_lease)
    RecyclerView rcLease;


    private ChooseAreaPop chooseAreaPop;
    private LeaseRentPop leaseRentPop;
    private LeaseUnitPop leaseUnitPop;
    private LeaseMorePop leaseMorePop;
    private String chooseCity = "广州";
    private static final int REQUEST_CODE_PICK_CITY = 233;
    private List<ChildCommunityBean> allList = new ArrayList<>();
    private List<LeaseRoomBean> leaseRoomBeans = new ArrayList<LeaseRoomBean>();

    private LeaseAdapter leaseAdapter ;

    private boolean isClickArea = false;
    private boolean isClickRent = false;
    private boolean isClickUnit = false;
    private boolean isClickMore = false;
    @Override
    public BasePresenter initPresenter() {
        loginPresenter = new LoginPresenter();
        return loginPresenter;
    }

    @Override
    public void initParms(Bundle parms) {

    }

    @Override
    public int bindLayout() {
        return R.layout.activity_lease_centre;
    }

    @Override
    public void initView(View view) {
        tvContentName.setText("租贷中心");
        allList.add(new ChildCommunityBean("0","天河区"));
        allList.add(new ChildCommunityBean("1","白云区"));
        allList.add(new ChildCommunityBean("2","番禺区"));
        allList.add(new ChildCommunityBean("3","萝岗区"));
        allList.add(new ChildCommunityBean("4","花都区"));
        allList.add(new ChildCommunityBean("5","越秀区"));
        initRcLease();
    }

    private void initRcLease() {
        rcLease.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        leaseAdapter = new LeaseAdapter(leaseRoomBeans , this);

        rcLease.setAdapter(leaseAdapter);

        rcLease.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                RecyclerView.LayoutManager manager = recyclerView.getLayoutManager();
                LogUtils.i("加载下一页-----------------");

                int lastCompletelyVisibleItemPosition = ((LinearLayoutManager) manager).findLastCompletelyVisibleItemPosition();
//                if (leaseAdapter.getItemCount() > 3 && lastCompletelyVisibleItemPosition == leaseAdapter.getItemCount() - 1 && isMore) {
//                    LogUtils.i("加载下一页-----------------");
//                    page++;
//                    initData(page , leaseScreenBean);
//                    isMore = false;
//                }
            }
        });
    }

    @Override
    public void doBusiness(Context mContext) {

    }

    @Override
    public void widgetClick(View v) {

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//        snLeaseCity.set
        LogUtils.i("position=" + position);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
    IdentityResultListener listener = new IdentityResultListener() {
        @Override
        public void identityResult(String identity) {
//            allList.clear();
//            leaseAdapter.notifyDataSetChanged();

//            leaseScreenBean.setArea(identity);
//            page = 1;
//            initData(page , leaseScreenBean);
        }
    };

    private void showPop() {
        if (chooseAreaPop == null) {
            chooseAreaPop = new ChooseAreaPop(this, chooseCity ,allList, listener);
            chooseAreaPop.setOnDismissListener(dismissListener);
        }

        if (!chooseAreaPop.isShowing()) {
            chooseAreaPop.showAtLocation(linChooseArea, Gravity.BOTTOM, 0, 0);
            chooseAreaPop.isShowing();
        } else {
            chooseAreaPop.dismiss();
        }
    }
    private void showRentPop(){

        if (leaseRentPop == null) {
            leaseRentPop = new LeaseRentPop(this , returnPriceListener);
            leaseRentPop.setOnDismissListener(dismissListener);
        }

        if (!leaseRentPop.isShowing()) {
            leaseRentPop.showAtLocation(linChooseArea, Gravity.BOTTOM, 0, 0);
            leaseRentPop.isShowing();
        } else {
            leaseRentPop.dismiss();
        }
    }
    private void showUnitPop(){

        if (leaseUnitPop == null) {
            leaseUnitPop = new LeaseUnitPop(this , listener2);
            leaseUnitPop.setOnDismissListener(dismissListener);
        }

        if (!leaseUnitPop.isShowing()) {
            leaseUnitPop.showAtLocation(linChooseArea, Gravity.BOTTOM, 0, 0);
            leaseUnitPop.isShowing();
        } else {
            leaseUnitPop.dismiss();
        }
    }
    private void showMorePop(){
        if (leaseMorePop == null) {
            leaseMorePop = new LeaseMorePop(this , moreListener);
            leaseMorePop.setOnDismissListener(dismissListener);
        }

        if (!leaseMorePop.isShowing()) {
            leaseMorePop.showAtLocation(linChooseArea, Gravity.BOTTOM, 0, 0);
            leaseMorePop.isShowing();
        } else {
            leaseMorePop.dismiss();
        }
    }
    ReturnMoreListener moreListener = new ReturnMoreListener() {
        @Override
        public void returnPrice(String orientations, String rent_type, String charge_pay) {
//
//            allList.clear();
//            leaseAdapter.notifyDataSetChanged();
//
//            leaseScreenBean.setOrientations(orientations);
//            leaseScreenBean.setCharge_pay(charge_pay);
//            leaseScreenBean.setRent_type(rent_type);
//            page = 1;
//            initData(page , leaseScreenBean);

        }
    } ;
    IdentityResultListener listener2 = new IdentityResultListener() {
        @Override
        public void identityResult(String identity) {
//            allList.clear();
//            leaseAdapter.notifyDataSetChanged();
//
//            leaseScreenBean.setHouse_type(identity);
//            page = 1;
//            initData(page , leaseScreenBean);
        }
    };
    ReturnPriceListener returnPriceListener = new ReturnPriceListener() {
        @Override
        public void returnPrice(String minP, String maxP) {
//            allList.clear();
//            leaseAdapter.notifyDataSetChanged();
//
//            leaseScreenBean.setMinprice(minP);
//            leaseScreenBean.setMaxprice(maxP);
//            page = 1;
//            initData(page , leaseScreenBean);
        }
    };
    PopupWindow.OnDismissListener dismissListener = new PopupWindow.OnDismissListener() {
        @Override
        public void onDismiss() {
            LogUtils.i("---------------------------------------------");
        }
    };
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CODE_PICK_CITY && resultCode == RESULT_OK){
            if (data != null){
//                chooseCity = data.getStringExtra(CityPickerActivity.KEY_PICKED_CITY);
                tvAddress.setText(chooseCity);
            }
        }
    }

    @OnClick({R.id.top_back, R.id.ll_choose_address, R.id.lin_choose_area, R.id.lin_lease_rent, R.id.lin_lease_unit, R.id.lin_lease_more})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.top_back:
                break;
            case R.id.ll_choose_address:

//                Intent intent = new Intent(LeaseActivity.this , CityPickerActivity.class);
//                //startActivity(intent);
//                startActivityForResult(intent , REQUEST_CODE_PICK_CITY);

                break;
            case R.id.lin_choose_area:
                //区域
                showPop();

                if (isClickArea){
                    tvChooseArea.setTextColor(Color.parseColor("#666666"));
                    isClickArea = false;
                }else{
                    tvChooseArea.setTextColor(Color.parseColor("#ea5205"));
                    isClickArea = true;
                }
                tvLeaseRent.setTextColor(Color.parseColor("#666666"));
                tvLeaseUnit.setTextColor(Color.parseColor("#666666"));
                tvLeaseMore.setTextColor(Color.parseColor("#666666"));

                //isClickArea = false;
                isClickRent = false;
                isClickUnit = false;
                isClickMore = false;


                if (leaseRentPop != null &&leaseRentPop.isShowing()){
                    leaseRentPop.dismiss();
                }

                if (leaseUnitPop != null &&leaseUnitPop.isShowing()){
                    leaseUnitPop.dismiss();
                }

                if (leaseMorePop != null &&leaseMorePop.isShowing()){
                    leaseMorePop.dismiss();
                }

                break;
            case R.id.lin_lease_rent:
                showRentPop();

                if (isClickRent){
                    tvLeaseRent.setTextColor(Color.parseColor("#666666"));
                    isClickRent = false;
                }else{
                    tvLeaseRent.setTextColor(Color.parseColor("#ea5205"));
                    isClickRent = true;
                }

                tvChooseArea.setTextColor(Color.parseColor("#666666"));
                tvLeaseUnit.setTextColor(Color.parseColor("#666666"));
                tvLeaseMore.setTextColor(Color.parseColor("#666666"));


                isClickArea = false;
                //isClickRent = false;
                isClickUnit = false;
                isClickMore = false;


                if (chooseAreaPop != null && chooseAreaPop.isShowing()){
                    chooseAreaPop.dismiss();
                }

                if (leaseUnitPop != null && leaseUnitPop.isShowing()){
                    leaseUnitPop.dismiss();
                }

                if (leaseMorePop != null && leaseMorePop.isShowing()){
                    leaseMorePop.dismiss();
                }

                break;
            case R.id.lin_lease_unit:
                showUnitPop();

                if (isClickUnit){
                    tvLeaseUnit.setTextColor(Color.parseColor("#666666"));
                    isClickUnit = false;
                }else{
                    tvLeaseUnit.setTextColor(Color.parseColor("#ea5205"));
                    isClickUnit = true;
                }

                tvChooseArea.setTextColor(Color.parseColor("#666666"));
                tvLeaseRent.setTextColor(Color.parseColor("#666666"));
                //tv_lease_unit.setTextColor(Color.parseColor("#ea5205"));
                tvLeaseMore.setTextColor(Color.parseColor("#666666"));

                isClickArea = false;
                isClickRent = false;
                //isClickUnit = false;
                isClickMore = false;


                if (chooseAreaPop != null && chooseAreaPop.isShowing()){
                    chooseAreaPop.dismiss();
                }

                if (leaseRentPop != null && leaseRentPop.isShowing()){
                    leaseRentPop.dismiss();
                }

                if (leaseMorePop != null && leaseMorePop.isShowing()){
                    leaseMorePop.dismiss();
                }


                break;
            case R.id.lin_lease_more:
                showMorePop();

                if (isClickMore){
                    tvLeaseMore.setTextColor(Color.parseColor("#666666"));
                    isClickMore = false;
                }else{
                    tvLeaseMore.setTextColor(Color.parseColor("#ea5205"));
                    isClickMore = true;
                }

                tvChooseArea.setTextColor(Color.parseColor("#666666"));
                tvLeaseRent.setTextColor(Color.parseColor("#666666"));
                tvLeaseUnit.setTextColor(Color.parseColor("#666666"));
                //tv_lease_more.setTextColor(Color.parseColor("#ea5205"));

                isClickArea = false;
                isClickRent = false;
                isClickUnit = false;
                //isClickMore = false;

                if (chooseAreaPop != null && chooseAreaPop.isShowing()){
                    chooseAreaPop.dismiss();
                }

                if (leaseRentPop != null && leaseRentPop.isShowing()){
                    leaseRentPop.dismiss();
                }

                if (leaseUnitPop != null && leaseUnitPop.isShowing()){
                    leaseUnitPop.dismiss();
                }

                break;
        }
    }
}
