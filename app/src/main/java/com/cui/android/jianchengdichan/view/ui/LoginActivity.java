package com.cui.android.jianchengdichan.view.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Selection;
import android.text.Spannable;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.cui.android.jianchengdichan.R;
import com.cui.android.jianchengdichan.presenter.BasePresenter;
import com.cui.android.jianchengdichan.presenter.LoginPresenter;
import com.cui.android.jianchengdichan.utils.LogUtils;
import com.cui.android.jianchengdichan.utils.PhoneNumUtil;
import com.cui.android.jianchengdichan.utils.SPKey;
import com.cui.android.jianchengdichan.utils.SPUtils;
import com.cui.android.jianchengdichan.utils.ToastUtil;
import com.cui.android.jianchengdichan.view.BaseActivtity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends BaseActivtity {


    @BindView(R.id.tv_login_register)
    TextView tvLoginRegister;
    @BindView(R.id.login_username_icon)
    ImageView loginUsernameIcon;
    @BindView(R.id.et_login_user_name)
    EditText etLoginUserName;
    @BindView(R.id.iv_login_delete)
    ImageView ivLoginDelete;
    @BindView(R.id.login_pwd_icon)
    ImageView loginPwdIcon;
    @BindView(R.id.et_login_pwd)
    EditText etLoginPwd;
    @BindView(R.id.iv_login_look)
    ImageView ivLoginLook;
    @BindView(R.id.tv_login_forget_pwd)
    TextView tvLoginForgetPwd;
    @BindView(R.id.bt_login_login)
    Button btLoginLogin;

    LoginPresenter mLoginPresenter;
    private boolean isPwdSee=false;
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
      String userName =(String)  SPUtils.INSTANCE.getSPValue(SPKey.SP_USER_NAME_KEY,SPUtils.DATA_STRING);
      if(!TextUtils.isEmpty(userName)){
          etLoginUserName.setText(userName);
      }
    }

    @Override
    public void doBusiness(Context mContext) {
    }

    @Override
    public void widgetClick(View v) {

    }

    /**
     * 登录返回的信息
     * @param msg
     */
    public void showView(String msg,int type) {
        LogUtils.i("showView="+msg);
        if(type==200){
            String userName = etLoginUserName.getText().toString();
            String pwd = etLoginPwd.getText().toString();
            SPUtils.INSTANCE.setSPValue(SPKey.SP_USER_NAME_KEY,userName);
            SPUtils.INSTANCE.setSPValue(SPKey.SP_USER_PWD_KEY,pwd);
            SPUtils.INSTANCE.setSPValue(SPKey.SP_LOAGIN_KEY,true);
            startActivity(new Intent(getContext(),MainActivity.class));
        }
        hideLoading();
    }

    private void changePwdIsSee(){
        if(isPwdSee){
            isPwdSee=false;
            etLoginPwd.setTransformationMethod(PasswordTransformationMethod.getInstance());
        }else{
            isPwdSee=true;
            etLoginPwd.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
        }
        ivLoginLook.setSelected(isPwdSee);
        //切换后将EditText光标置于末尾
        CharSequence charSequence = etLoginPwd.getText();
        if (charSequence instanceof Spannable) {
            Spannable spanText = (Spannable) charSequence;
            Selection.setSelection(spanText, charSequence.length());
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.tv_login_register,  R.id.iv_login_delete,  R.id.iv_login_look, R.id.tv_login_forget_pwd, R.id.bt_login_login})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_login_register:
                LogUtils.i("tv_login_register");
                startActivity(new Intent(getContext(),RegisterActivity.class));
                break;

            case R.id.iv_login_delete:
                LogUtils.i("iv_login_delete");
                etLoginUserName.setText("");
                etLoginPwd.setText("");
                break;

            case R.id.iv_login_look:
                LogUtils.i("iv_login_look");
                changePwdIsSee();
                break;
            case R.id.tv_login_forget_pwd:
                LogUtils.i("tv_login_forget_pwd");
                startActivity(new Intent(getContext(),ForgetPwdActivity.class));

                break;
            case R.id.bt_login_login:
                LogUtils.i("bt_login_login");
                toLogin();
                break;
        }
    }

    private void toLogin() {
        String userName = etLoginUserName.getText().toString();
        if(TextUtils.isEmpty(userName)){
            ToastUtil.makeToast("用户名不能为空");
            return;
        }
        if(!PhoneNumUtil.verifyPhone(userName)){
            ToastUtil.makeToast("请输入正确的用户名格式");
            return;

        }
        String pws = etLoginPwd.getText().toString();
        if(TextUtils.isEmpty(pws)){
            ToastUtil.makeToast("密码不能为空");
            return;

        }
        mLoginPresenter.login(userName,pws);
        showLoading();
    }
}
