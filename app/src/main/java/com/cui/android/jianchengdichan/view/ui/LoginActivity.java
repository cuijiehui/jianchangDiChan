package com.cui.android.jianchengdichan.view.ui;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.cui.android.jianchengdichan.R;
import com.cui.android.jianchengdichan.presenter.BasePresenter;
import com.cui.android.jianchengdichan.presenter.LoginPresenter;
import com.cui.android.jianchengdichan.utils.ToastUtils;
import com.cui.android.jianchengdichan.view.BaseActivtity;

import butterknife.BindView;

public class LoginActivity extends BaseActivtity {

    @BindView(R.id.bt_login_login)
    Button bt_login_login;
    @BindView(R.id.et_login_password)
    EditText et_login_password;
    @BindView(R.id.et_login_phone)
    EditText et_login_phone;
    @BindView(R.id.tv_login_forget_password)
    TextView tv_login_forget_password;
    @BindView(R.id.tv_login_register)
    TextView tv_login_register;
    @BindView(R.id.iv_recharge_back)
    ImageView iv_recharge_back;

    LoginPresenter mLoginPresenter;

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
        return R.layout.activity_login;
    }

    @Override
    public void initView(View view) {

    }

    @Override
    public void doBusiness(Context mContext) {
        setListn();
    }

    private void setListn() {
        bt_login_login.setOnClickListener(this);
        tv_login_forget_password.setOnClickListener(this);
        tv_login_register.setOnClickListener(this);
        iv_recharge_back.setOnClickListener(this);
    }

    private void login() {
        String mobile = et_login_phone.getText().toString();
        String pwd = et_login_password.getText().toString();
        if (TextUtils.isEmpty(mobile)) {
            ToastUtils.showShort(this, "账号不能为空");
            return;
        }
        if (TextUtils.isEmpty(pwd)) {
            ToastUtils.showShort(this, "密码不能为空");
            return;
        }
        mLoginPresenter.login(mobile, pwd);
    }

    @Override
    public void widgetClick(View v) {
        switch (v.getId()) {
            case R.id.bt_login_login:
                login();
                break;
            case R.id.tv_login_forget_password:
                //TODO 忘记密码界面
                break;
            case R.id.tv_login_register:
                //TODO 注册界面
                break;
            case R.id.iv_recharge_back:
                //TODO 返回界面
                break;

        }
    }

    public void showView(String msg) {
//        textview.setText(msg);
    }
}
