package com.cui.android.jianchengdichan.view.ui;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.cui.android.jianchengdichan.R;
import com.cui.android.jianchengdichan.presenter.BasePresenter;
import com.cui.android.jianchengdichan.presenter.LoginPresenter;
import com.cui.android.jianchengdichan.utils.AndroidPermissionUtils;
import com.cui.android.jianchengdichan.utils.LogUtils;
import com.cui.android.jianchengdichan.view.BaseActivtity;
import com.cui.android.jianchengdichan.view.ui.Fragment.Adapter.MainPagerAdapter;
import com.cui.android.jianchengdichan.view.ui.Fragment.MainCommFragment;
import com.cui.android.jianchengdichan.view.ui.Fragment.MainHomeFragment;
import com.cui.android.jianchengdichan.view.ui.Fragment.MainMyFragment;
import com.cui.android.jianchengdichan.view.ui.Fragment.MainShopFragment;
import com.cui.android.jianchengdichan.view.ui.customview.MainNavigationView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivtity {

    LoginPresenter loginPresenter;
    @BindView(R.id.main_navigation_view)
    MainNavigationView mainNavigationView;
    @BindView(R.id.vp_main_pager)
   public ViewPager vpMainPager;

    private MainPagerAdapter mMainPagerAdapter;
    private List<Fragment> mDataList =new ArrayList<>();
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
        return R.layout.activity_main;
    }

    @Override
    public void initView(View view) {
        LogUtils.i("initView");
        setCallBack();
        setViewPager();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            checkAllPermission();
        }
    }
    private void checkAllPermission() {
        String[] unCheckPermissions = AndroidPermissionUtils.checkPermission(this);
        if (unCheckPermissions.length != 0) {
            ActivityCompat.requestPermissions(this, unCheckPermissions, 100);
        }
    }
    private void setViewPager() {
        mDataList.clear();
        mDataList.add(MainHomeFragment.newInstance("MainHome","MainHome"));
        mDataList.add(MainCommFragment.newInstance("MainComm","MainComm"));
        mDataList.add(MainShopFragment.newInstance("MainShop","MainShop"));
        mDataList.add(MainMyFragment.newInstance("MainMy","MainMy"));
        mMainPagerAdapter=new MainPagerAdapter(getSupportFragmentManager(),getContext(),mDataList);
        vpMainPager.setAdapter(mMainPagerAdapter);
        vpMainPager.setCurrentItem(0);
        vpMainPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                LogUtils.i("onPageSelected=" + position);
                mainNavigationView.setPager(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    public void doBusiness(Context mContext) {

    }

    private void setCallBack() {
        mainNavigationView.setCallBack(new MainNavigationView.SelectedCallBack() {
            @Override
            public void onBackSelected(int conut) {
                LogUtils.i("onBackSelected=" + conut);
                vpMainPager.setCurrentItem(conut);

            }
        });
    }

    @Override
    public View initBack() {
        return null;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
