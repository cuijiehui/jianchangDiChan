package com.cui.android.jianchengdichan.view.ui;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.cui.android.jianchengdichan.R;
import com.cui.android.jianchengdichan.presenter.BasePresenter;
import com.cui.android.jianchengdichan.presenter.LoginPresenter;
import com.cui.android.jianchengdichan.utils.LogUtils;
import com.cui.android.jianchengdichan.view.BaseActivtity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SetingActivity extends BaseActivtity {

    LoginPresenter mLoginPresenter;
    @BindView(R.id.top_back)
    ImageView topBack;
    @BindView(R.id.tv_content_name)
    TextView tvContentName;
    @BindView(R.id.tv_top_right)
    TextView tvTopRight;
    @BindView(R.id.iv_top_right)
    ImageView ivTopRight;
    @BindView(R.id.rl_setting_question)
    RelativeLayout rlSettingQuestion;
    @BindView(R.id.rl_setting_clear)
    RelativeLayout rlSettingClear;
    @BindView(R.id.tv_setting_version)
    TextView tvSettingVersion;

    @Override
    public BasePresenter initPresenter() {
        mLoginPresenter = new LoginPresenter();
        return mLoginPresenter;
    }

    @Override
    public void initParms(Bundle parms) {

    }

    @Override
    public int bindLayout() {
        return R.layout.activity_seting;
    }

    @Override
    public void initView(View view) {
        tvContentName.setText("设置");
        tvTopRight.setVisibility(View.GONE);
        ivTopRight.setVisibility(View.GONE);

    }

    @Override
    public void doBusiness(Context mContext) {

    }

    @Override
    public void widgetClick(View v) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.top_back, R.id.rl_setting_question, R.id.rl_setting_clear, R.id.tv_setting_version})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.top_back:
                LogUtils.i("top_back");
                break;
            case R.id.rl_setting_question:
                LogUtils.i("rl_setting_question");

                break;
            case R.id.rl_setting_clear:
                LogUtils.i("rl_setting_clear");

                break;
            case R.id.tv_setting_version:
                LogUtils.i("tv_setting_version");

                break;
        }
    }
}
