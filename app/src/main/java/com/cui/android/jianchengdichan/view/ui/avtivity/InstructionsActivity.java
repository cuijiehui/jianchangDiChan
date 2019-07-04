package com.cui.android.jianchengdichan.view.ui.avtivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import com.cui.android.jianchengdichan.presenter.BasePresenter;
import com.cui.android.jianchengdichan.presenter.InstructionsPresenter;
import com.cui.android.jianchengdichan.view.base.BaseActivity;

public class InstructionsActivity extends BaseActivity {
    InstructionsPresenter presenter = new InstructionsPresenter();
    @Override
    public BasePresenter initPresenter() {
        return presenter;
    }

    @Override
    public void initParam(Bundle param) {
        
    }

    @Override
    public int bindLayout() {
        return 0;
    }

    @Override
    public void initView(View view) {

    }

    @Override
    public void doBusiness(Context mContext) {

    }

    @Override
    public View initBack() {
        return null;
    }
}
