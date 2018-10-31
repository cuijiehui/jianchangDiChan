package com.cui.android.jianchengdichan.view.ui.avtivity;

import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.cui.android.jianchengdichan.R;
import com.cui.android.jianchengdichan.presenter.BasePresenter;
import com.cui.android.jianchengdichan.presenter.LoginPresenter;
import com.cui.android.jianchengdichan.utils.AndroidPermissionUtils;
import com.cui.android.jianchengdichan.utils.LogUtils;
import com.cui.android.jianchengdichan.utils.ToastUtil;
import com.cui.android.jianchengdichan.view.base.BaseActivity;
import com.cui.android.jianchengdichan.view.ui.fragment.adapter.MainPagerAdapter;
import com.cui.android.jianchengdichan.view.ui.fragment.MainCommFragment;
import com.cui.android.jianchengdichan.view.ui.fragment.MainHomeFragment;
import com.cui.android.jianchengdichan.view.ui.fragment.MainMyFragment;
import com.cui.android.jianchengdichan.view.ui.fragment.MainShopFragment;
import com.cui.android.jianchengdichan.view.ui.customview.MainNavigationView;
import com.cui.android.jianchengdichan.view.ui.customview.viewpager.CustomViewPager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity {

    LoginPresenter loginPresenter;
    @BindView(R.id.main_navigation_view)
    MainNavigationView mainNavigationView;
    @BindView(R.id.vp_main_pager)
   public CustomViewPager vpMainPager;
    private boolean exit = false;//是否退出应用

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
//            checkAllPermission();
        }
    }
//    private void checkAllPermission() {
//        String[] unCheckPermissions = AndroidPermissionUtils.checkPermission(this);
//        if (unCheckPermissions.length != 0) {
//            ActivityCompat.requestPermissions(this, unCheckPermissions, 100);
//        }
//    }
    private void setViewPager() {
        mDataList.clear();
        mDataList.add(MainHomeFragment.newInstance(null));
        mDataList.add(MainCommFragment.newInstance(null));
        mDataList.add(MainShopFragment.newInstance("MainShop","MainShop"));
        mDataList.add(MainMyFragment.newInstance(null));
        mMainPagerAdapter=new MainPagerAdapter(getSupportFragmentManager(),getContext(),mDataList);
        vpMainPager.setAdapter(mMainPagerAdapter);
        vpMainPager.setOffscreenPageLimit(3);
        vpMainPager.setCurrentItem(0);

        vpMainPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                LogUtils.i("onPageSelected=" + position);
                mainNavigationView.setPager(position);
//                if(position==2){
//                    Intent intent = new Intent(getContext(), WebViewActivity.class);
//                    intent.putExtra("data", "http://www.baidu.com");
//                    getContext().startActivity(intent);
//                }

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    public void onBackPressed() {
            if (getSupportFragmentManager().getBackStackEntryCount() == 0) {
//                super.onBackPressed();
                if (!exit) {
                    exit = true;
                    ToastUtil.makeToast("再按一次退出程序");
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            exit = false;
                        }
                    }, 2000);
                } else {
                    //退出程序
                    finish();
                }
            } else {
                getSupportFragmentManager().popBackStack();
            }
    }

    @Override
    public void doBusiness(Context mContext) {

    }

    private void setCallBack() {
        mainNavigationView.setCallBack(new MainNavigationView.SelectedCallBack() {
            @Override
            public void onBackSelected(int conut) {
                LogUtils.i("onBackSelected=" + conut);
                if(conut==2){
                    Intent intent = new Intent(getContext(), WebViewActivity.class);
                    Bundle bundle ;
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        bundle = ActivityOptions.makeSceneTransitionAnimation(MainActivity.this).toBundle();
                        bundle.putString("data", "http://wx.szshide.shop/app/index.php?i=1&c=entry&m=ewei_shopv2&do=mobile");
                        intent.putExtras(bundle);
                        getContext().startActivity(intent);
                    }else{
                        bundle=new Bundle();
                        bundle.putString("data", "http://wx.szshide.shop/app/index.php?i=1&c=entry&m=ewei_shopv2&do=mobile");
                        intent.putExtras(bundle);
                        getContext().startActivity(intent);
                    }

                }else{
                    vpMainPager.setCurrentItem(conut);

                }
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
