package com.cui.android.jianchengdichan.view.ui;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.cui.android.jianchengdichan.R;
import com.cui.android.jianchengdichan.presenter.BasePresenter;
import com.cui.android.jianchengdichan.presenter.LoginPresenter;
import com.cui.android.jianchengdichan.utils.LogUtils;
import com.cui.android.jianchengdichan.view.BaseActivtity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FeedbackActivity extends BaseActivtity {
    LoginPresenter mLoginPresenter;
    @BindView(R.id.top_back)
    ImageView topBack;
    @BindView(R.id.tv_content_name)
    TextView tvContentName;
    @BindView(R.id.tv_top_right)
    TextView tvTopRight;
    @BindView(R.id.iv_top_right)
    ImageView ivTopRight;

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
        return R.layout.activity_feed_back_layout;
    }

    @Override
    public void initView(View view) {
        tvContentName.setText("意见反馈");
        ivTopRight.setVisibility(View.GONE);
        tvTopRight.setText("反馈历史");
    }

    @Override
    public void doBusiness(Context mContext) {

    }

    @Override
    public void widgetClick(View v) {

    }



    @OnClick({R.id.top_back, R.id.tv_top_right})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.top_back:
                LogUtils.i("top_back");
                break;
            case R.id.tv_top_right:
                LogUtils.i("tv_top_right");

                break;
        }
    }
}
