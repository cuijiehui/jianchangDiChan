package com.cui.android.jianchengdichan.view.ui.avtivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.cui.android.jianchengdichan.R;
import com.cui.android.jianchengdichan.http.bean.CityListBean;
import com.cui.android.jianchengdichan.http.bean.LeaseRoomBean;
import com.cui.android.jianchengdichan.presenter.BasePresenter;
import com.cui.android.jianchengdichan.presenter.LeaseCentrePresenter;
import com.cui.android.jianchengdichan.utils.LogUtils;
import com.cui.android.jianchengdichan.utils.SPKey;
import com.cui.android.jianchengdichan.utils.SPUtils;
import com.cui.android.jianchengdichan.view.base.BaseActivity;
import com.cui.android.jianchengdichan.view.ui.adapter.LeaseAdapter;
import com.cui.android.jianchengdichan.view.ui.customview.ChildCommunityBean;
import com.cui.android.jianchengdichan.view.ui.customview.ChooseAreaPop;
import com.cui.android.jianchengdichan.view.ui.customview.LeaseMorePop;
import com.cui.android.jianchengdichan.view.ui.customview.LeaseRentPop;
import com.cui.android.jianchengdichan.view.ui.customview.LeaseUnitPop;
import com.cui.android.jianchengdichan.view.ui.customview.interfaces.IdentityResultListener;
import com.cui.android.jianchengdichan.view.ui.customview.interfaces.ReturnMoreListener;
import com.cui.android.jianchengdichan.view.ui.customview.interfaces.ReturnPriceListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

public class LeaseCentreActivity extends BaseActivity {
    @BindView(R.id.tv_content_name)
    TextView tvContentName;
    @BindView(R.id.top_back)
    RelativeLayout topBack;
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
    @BindView(R.id.tv_top_right)
    TextView tvTopRight;
    @BindView(R.id.iv_top_right)
    ImageView ivTopRight;


    private ChooseAreaPop chooseAreaPop;
    private LeaseRentPop leaseRentPop;
    private LeaseUnitPop leaseUnitPop;
    private LeaseMorePop leaseMorePop;
    private String chooseCity = "广州";
    private static final int REQUEST_CODE_PICK_CITY = 233;
    private List<ChildCommunityBean> allList = new ArrayList<>();
    private List<LeaseRoomBean> leaseRoomBeans = new ArrayList<LeaseRoomBean>();
    private int page = 1;
    private LeaseAdapter leaseAdapter;
    LeaseCentrePresenter mLeaseCentrePresenter;

    private boolean isClickArea = false;
    private boolean isClickRent = false;
    private boolean isClickUnit = false;
    private boolean isClickMore = false;
    private boolean isMore = true;
    Map<String, String> data = new HashMap<>();

    @Override
    public BasePresenter initPresenter() {
        mLeaseCentrePresenter = new LeaseCentrePresenter();
        return mLeaseCentrePresenter;
    }

    @Override
    public void initParam(Bundle param) {
        data.put("key", "");
        data.put("area", "");
        data.put("house_type", "");
        data.put("orientations", "");
        data.put("minprice", "");
        data.put("maxprice", "");
        data.put("rent_type", "");
        data.put("charge_pay", "");
    }

    @Override
    public int bindLayout() {
        return R.layout.activity_lease_centre;
    }

    @Override
    public void initView(View view) {
        tvContentName.setText("租赁中心");
        tvTopRight.setText("发布");
        tvTopRight.setVisibility(View.VISIBLE);
        initRcLease();

    }

    @Override
    protected void onResume() {
        super.onResume();
        rentList(page, data);

    }

    private void initRcLease() {
        rcLease.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        leaseAdapter = new LeaseAdapter(leaseRoomBeans);

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
                if (leaseAdapter.getItemCount() > 3 && lastCompletelyVisibleItemPosition == leaseAdapter.getItemCount() - 1 && isMore) {
                    LogUtils.i("加载下一页-----------------");
                    page++;
                    rentList(page, data);
                    isMore = false;
                }
            }
        });
        leaseAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                Bundle bundle = new Bundle();
                bundle.putString("id",leaseRoomBeans.get(position).getId());
                startActivity(RentDatailActivity.class,bundle);
            }
        });
    }

    @Override
    public void doBusiness(Context mContext) {
        mLeaseCentrePresenter.getCityList(chooseCity);
    }

    public void rentList(int page, Map data) {
        int uid = (int) SPUtils.INSTANCE.getSPValue(SPKey.SP_USER_UID_KEY, SPUtils.DATA_INT);
        String token = (String) SPUtils.INSTANCE.getSPValue(SPKey.SP_USER_TOKEN_KEY, SPUtils.DATA_STRING);
        mLeaseCentrePresenter.getRentList(uid, token, chooseCity, page, data);
    }


    @Override
    public View initBack() {
        return topBack;
    }



    IdentityResultListener listener = new IdentityResultListener() {
        @Override
        public void identityResult(String identity) {
            leaseRoomBeans.clear();
            leaseAdapter.notifyDataSetChanged();
            LogUtils.i(identity);
            data.put("area",identity);
            page=1;
            rentList(page,data);
        }
    };

    private void showPop() {
        if (chooseAreaPop == null) {
            chooseAreaPop = new ChooseAreaPop(this, chooseCity, allList, listener);
            chooseAreaPop.setOnDismissListener(dismissListener);
        }

        if (!chooseAreaPop.isShowing()) {
            chooseAreaPop.showAtLocation(linChooseArea, Gravity.BOTTOM, 0, 0);
            chooseAreaPop.isShowing();
        } else {
            chooseAreaPop.dismiss();
        }
    }

    private void showRentPop() {

        if (leaseRentPop == null) {
            leaseRentPop = new LeaseRentPop(this, returnPriceListener);
            leaseRentPop.setOnDismissListener(dismissListener);
        }

        if (!leaseRentPop.isShowing()) {
            leaseRentPop.showAtLocation(linChooseArea, Gravity.BOTTOM, 0, 0);
            leaseRentPop.isShowing();
        } else {
            leaseRentPop.dismiss();
        }
    }

    private void showUnitPop() {

        if (leaseUnitPop == null) {
            leaseUnitPop = new LeaseUnitPop(this, listener2);
            leaseUnitPop.setOnDismissListener(dismissListener);
        }

        if (!leaseUnitPop.isShowing()) {
            leaseUnitPop.showAtLocation(linChooseArea, Gravity.BOTTOM, 0, 0);
            leaseUnitPop.isShowing();
        } else {
            leaseUnitPop.dismiss();
        }
    }

    private void showMorePop() {
        if (leaseMorePop == null) {
            leaseMorePop = new LeaseMorePop(this, moreListener);
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

            leaseRoomBeans.clear();
            leaseAdapter.notifyDataSetChanged();
            LogUtils.i(orientations);
            LogUtils.i(rent_type);
            LogUtils.i(charge_pay);
            data.put("orientations",orientations);
            data.put("rent_type",rent_type);
            data.put("charge_pay",charge_pay);
            page=1;
            rentList(page,data);
        }
    };
    IdentityResultListener listener2 = new IdentityResultListener() {
        @Override
        public void identityResult(String identity) {
            leaseRoomBeans.clear();
            leaseAdapter.notifyDataSetChanged();
            LogUtils.i(identity);
            data.put("house_type",identity);
            page=1;
            rentList(page,data);
        }
    };
    ReturnPriceListener returnPriceListener = new ReturnPriceListener() {
        @Override
        public void returnPrice(String minP, String maxP) {
            leaseRoomBeans.clear();
            leaseAdapter.notifyDataSetChanged();
   LogUtils.i(minP+"--"+maxP);
            data.put("minprice",minP);
            data.put("maxprice",maxP);
            page=1;
            rentList(page,data);
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
        LogUtils.i("resultCode=" + resultCode);
        LogUtils.i("requestCode=" + requestCode);
        if (requestCode == REQUEST_CODE_PICK_CITY && resultCode == RESULT_OK) {
            if (data != null) {
                chooseCity = data.getStringExtra(CityPickerActivity.KEY_PICKED_CITY);
                tvAddress.setText(chooseCity);
                mLeaseCentrePresenter.getCityList(chooseCity);

            }
        }
    }

    @OnClick({R.id.ll_choose_address, R.id.lin_choose_area, R.id.lin_lease_rent, R.id.lin_lease_unit, R.id.tv_top_right, R.id.lin_lease_more,R.id.iv_search})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_top_right:
                LogUtils.i("tv_top_right");

                startActivity(ReleaseRentActivity.class);
                break;
            case R.id.ll_choose_address:
                LogUtils.i("ll_choose_address");

                Intent intent = new Intent(LeaseCentreActivity.this, CityPickerActivity.class);
                startActivityForResult(intent, REQUEST_CODE_PICK_CITY);

                break;
            case R.id.lin_choose_area:
                //区域
                showPop();
                LogUtils.i("lin_choose_area");

                if (isClickArea) {
                    tvChooseArea.setTextColor(Color.parseColor("#666666"));
                    isClickArea = false;
                } else {
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


                if (leaseRentPop != null && leaseRentPop.isShowing()) {
                    leaseRentPop.dismiss();
                }

                if (leaseUnitPop != null && leaseUnitPop.isShowing()) {
                    leaseUnitPop.dismiss();
                }

                if (leaseMorePop != null && leaseMorePop.isShowing()) {
                    leaseMorePop.dismiss();
                }

                break;
            case R.id.lin_lease_rent:
                showRentPop();
                LogUtils.i("lin_lease_rent");

                if (isClickRent) {
                    tvLeaseRent.setTextColor(Color.parseColor("#666666"));
                    isClickRent = false;
                } else {
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


                if (chooseAreaPop != null && chooseAreaPop.isShowing()) {
                    chooseAreaPop.dismiss();
                }

                if (leaseUnitPop != null && leaseUnitPop.isShowing()) {
                    leaseUnitPop.dismiss();
                }

                if (leaseMorePop != null && leaseMorePop.isShowing()) {
                    leaseMorePop.dismiss();
                }

                break;
            case R.id.lin_lease_unit:
                showUnitPop();
                LogUtils.i("lin_lease_unit");

                if (isClickUnit) {
                    tvLeaseUnit.setTextColor(Color.parseColor("#666666"));
                    isClickUnit = false;
                } else {
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


                if (chooseAreaPop != null && chooseAreaPop.isShowing()) {
                    chooseAreaPop.dismiss();
                }

                if (leaseRentPop != null && leaseRentPop.isShowing()) {
                    leaseRentPop.dismiss();
                }

                if (leaseMorePop != null && leaseMorePop.isShowing()) {
                    leaseMorePop.dismiss();
                }


                break;
            case R.id.lin_lease_more:
                showMorePop();
                LogUtils.i("lin_lease_more");

                if (isClickMore) {
                    tvLeaseMore.setTextColor(Color.parseColor("#666666"));
                    isClickMore = false;
                } else {
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

                if (chooseAreaPop != null && chooseAreaPop.isShowing()) {
                    chooseAreaPop.dismiss();
                }

                if (leaseRentPop != null && leaseRentPop.isShowing()) {
                    leaseRentPop.dismiss();
                }

                if (leaseUnitPop != null && leaseUnitPop.isShowing()) {
                    leaseUnitPop.dismiss();
                }

                break;
            case R.id.iv_search:
                 String key = etLeaseSearch.getText().toString();
                 data.put("key",key);
                 page=1;
                rentList(page,data);

                break;
        }
    }

    public void getCityList(List<CityListBean> dataList) {
        allList.clear();
        for (CityListBean cityListBean : dataList) {
            allList.add(new ChildCommunityBean(cityListBean.getId(), cityListBean.getArea()));
        }
        data.put("area", "");
    }


    public void getRentList(List<LeaseRoomBean> data) {
        if (page == 1) {
            leaseRoomBeans.clear();
        }
        if (data != null) {
            leaseRoomBeans.addAll(data);
            isMore = true;

        } else {
            isMore = false;
        }
        leaseAdapter.notifyDataSetChanged();
    }


}
