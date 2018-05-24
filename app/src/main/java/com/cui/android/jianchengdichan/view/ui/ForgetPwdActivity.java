package com.cui.android.jianchengdichan.view.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
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
import com.cui.android.jianchengdichan.presenter.ForgetPwdPresenter;
import com.cui.android.jianchengdichan.presenter.LoginPresenter;
import com.cui.android.jianchengdichan.presenter.RegisterPresenter;
import com.cui.android.jianchengdichan.utils.LogUtils;
import com.cui.android.jianchengdichan.utils.PhoneNumUtil;
import com.cui.android.jianchengdichan.utils.SPKey;
import com.cui.android.jianchengdichan.utils.SPUtils;
import com.cui.android.jianchengdichan.utils.ToastUtil;
import com.cui.android.jianchengdichan.view.BaseActivtity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ForgetPwdActivity extends BaseActivtity {
    @BindView(R.id.top_back)
    ImageView topBack;
    @BindView(R.id.tv_content_name)
    TextView tvContentName;
    @BindView(R.id.tv_top_right)
    TextView tvTopRight;
    @BindView(R.id.iv_top_right)
    ImageView ivTopRight;
    @BindView(R.id.et_forget_user_name)
    EditText etForgetUserName;
    @BindView(R.id.tv_forget_number_again)
    TextView tvForgetNumberAgain;
    @BindView(R.id.iv_forget_number)
    ImageView ivForgetNumber;
    @BindView(R.id.et_forget_user_number)
    EditText etForgetUserNumber;
    @BindView(R.id.iv_forget_delete)
    ImageView ivForgetDelete;
    @BindView(R.id.et_forget_pwd)
    EditText etForgetPwd;
    @BindView(R.id.iv_forget_look)
    ImageView ivForgetLook;
    @BindView(R.id.et_forget_pwd_again)
    EditText etForgetPwdAgain;
    @BindView(R.id.bt_forget_forget)
    Button btForgetForget;
    ForgetPwdPresenter mForgetPwdPresenter;
    private Handler mhandle = new Handler();
    private int codeNum = 30;
    private boolean isPwdSee=false;

    @Override
    public BasePresenter initPresenter() {
        mForgetPwdPresenter = new ForgetPwdPresenter();
        return mForgetPwdPresenter;
    }


    @Override
    public void initParms(Bundle parms) {

    }

    @Override
    public int bindLayout() {
        return R.layout.activity_forget_pwd;
    }

    @Override
    public void initView(View view) {
        tvContentName.setText("找回密码");

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
    public void showView(String msg , int type){
        if(type == 200){
            ToastUtil.makeToast("修改密码成功！");
            String userName = etForgetUserName.getText().toString();
            String pwd = etForgetPwd.getText().toString();
            SPUtils.INSTANCE.setSPValue(SPKey.SP_USER_NAME_KEY,userName);
            SPUtils.INSTANCE.setSPValue(SPKey.SP_USER_PWD_KEY,pwd);
            startActivity(new Intent(mContext,LoginActivity.class));
        }
        hideLoading();

    }
    private void toRegisterCode() {
        String userName = etForgetUserName.getText().toString();
        if (!PhoneNumUtil.verifyPhone(userName)) {
            ToastUtil.makeToast("请输入正确的用户名格式");
            return;
        }
        mForgetPwdPresenter.getCode(userName, "sms_reg", "0");
        tvForgetNumberAgain.setVisibility(View.VISIBLE);
        ivForgetNumber.setVisibility(View.GONE);
        mhandle.post(timeRunable);
    }
    private void changePwdIsSee(){
        if(isPwdSee){
            isPwdSee=false;
            etForgetPwd.setTransformationMethod(PasswordTransformationMethod.getInstance());
            etForgetPwdAgain.setTransformationMethod(PasswordTransformationMethod.getInstance());
        }else{
            isPwdSee=true;
            etForgetPwd.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            etForgetPwdAgain.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
        }
        ivForgetLook.setSelected(isPwdSee);
        //切换后将EditText光标置于末尾
        CharSequence charSequence = etForgetPwd.getText();
        if (charSequence instanceof Spannable) {
            Spannable spanText = (Spannable) charSequence;
            Selection.setSelection(spanText, charSequence.length());
        }
    }
    @OnClick({R.id.top_back, R.id.iv_forget_number, R.id.iv_forget_delete, R.id.iv_forget_look, R.id.bt_forget_forget})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.top_back:
                LogUtils.i("top_back");

                break;
            case R.id.iv_forget_number:
                LogUtils.i("iv_forget_number");
                toRegisterCode();
                break;
            case R.id.iv_forget_delete:
                LogUtils.i("iv_forget_delete");
                etForgetUserName.setText("");
                etForgetPwd.setText("");
                etForgetPwdAgain.setText("");
                etForgetUserNumber.setText("");
                break;
            case R.id.iv_forget_look:
                LogUtils.i("iv_forget_look");
                changePwdIsSee();
                break;
            case R.id.bt_forget_forget:
                LogUtils.i("bt_forget_forget");
                forgetPwd();
                break;
        }
    }

    private void forgetPwd() {
        String name = etForgetUserName.getText().toString();
        if(TextUtils.isEmpty(name)){
            ToastUtil.makeToast("用户名不能为空");
            return;
        }
        String code = etForgetUserNumber.getText().toString();
        if(TextUtils.isEmpty(code)){
            ToastUtil.makeToast("验证码不能为空");
            return;
        }
        String pwd = etForgetPwd.getText().toString();
        if(TextUtils.isEmpty(pwd)){
            ToastUtil.makeToast("密码不能为空");
            return;
        }else{
            if(pwd.length()<6||pwd.length()>20){
                ToastUtil.makeToast("密码长度6位到20位之间");
                return;
            }
        }
        String pwdAgain = etForgetPwdAgain.getText().toString();
        if(!pwdAgain.equals(pwd)){
            ToastUtil.makeToast("两次密码不相等");
            return;
        }
        mForgetPwdPresenter.forgetPwd(name,pwd,code);
    }

    /*****************计时器*******************/
    private Runnable timeRunable = new Runnable() {
        @Override
        public void run() {
            codeNum--;
            tvForgetNumberAgain.setText("(" + codeNum + ")重获");
            if (codeNum > 0) {
                //递归调用本runable对象，实现每隔一秒一次执行任务
                mhandle.postDelayed(this, 1000);
            } else {
                tvForgetNumberAgain.setVisibility(View.GONE);
                ivForgetNumber.setVisibility(View.VISIBLE);
                codeNum=30;
            }
        }
    };



/*****************计时器*******************/
}
