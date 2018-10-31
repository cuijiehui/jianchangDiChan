package com.cui.android.jianchengdichan.view.ui.avtivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.cui.android.jianchengdichan.R;
import com.cui.android.jianchengdichan.presenter.BasePresenter;
import com.cui.android.jianchengdichan.presenter.SplashPresenter;
import com.cui.android.jianchengdichan.utils.LogUtils;
import com.cui.android.jianchengdichan.view.base.BaseActivity;

import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;


public class SplashActivity extends BaseActivity {

    @BindView(R.id.img_splash)
    ImageView img_splash;
    @BindView(R.id.bt_fullpageads_pass)
    Button bt_fullpageads_pass;
    @BindView(R.id.rl_fullpage_adv_countdown)
    RelativeLayout rl_fullpage_adv_countdown;
    @BindView(R.id.tv_fullpage_txt2)
    TextView tv_fullpage_txt2;

    private SplashPresenter mSplashPresenter;

    @Override
    public BasePresenter initPresenter() {
        mSplashPresenter = new SplashPresenter();
        return mSplashPresenter;
    }

    @Override
    public void initParms(Bundle parms) {
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                /**
                 *要执行的操作
                 */
                startActivity(MainActivity.class);
                finish();
            }
        };
        Timer timer = new Timer();
        timer.schedule(task, 5000);//3秒后执行TimeTask的run方法
    }

    @Override
    public int bindLayout() {
        return R.layout.activity_splash;
    }

    @Override
    public void initView(View view) {
        steepStatusBar();
    }

    @Override
    public void doBusiness(Context mContext) {
        getAdvList();
    }

    @Override
    public View initBack() {
        return null;
    }


    public void getAdvList() {
        LogUtils.i("mSplashPresenter.getAdvList()");
//        mSplashPresenter.getAdvList();

    }

    public void showAdvData() {
        LogUtils.i("showAdvData");

    }

    public void onFail(String msg) {
        LogUtils.i("onFail="+msg);

    }
}
