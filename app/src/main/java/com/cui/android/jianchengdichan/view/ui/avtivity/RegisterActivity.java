package com.cui.android.jianchengdichan.view.ui.avtivity;

import android.content.Context;
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
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.cui.android.jianchengdichan.MyApplication;
import com.cui.android.jianchengdichan.R;
import com.cui.android.jianchengdichan.presenter.BasePresenter;
import com.cui.android.jianchengdichan.presenter.RegisterPresenter;
import com.cui.android.jianchengdichan.utils.LogUtils;
import com.cui.android.jianchengdichan.utils.PhoneNumUtil;
import com.cui.android.jianchengdichan.utils.SPKey;
import com.cui.android.jianchengdichan.utils.SPUtils;
import com.cui.android.jianchengdichan.utils.ToastUtil;
import com.cui.android.jianchengdichan.view.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegisterActivity extends BaseActivity {
    @BindView(R.id.top_back)
    RelativeLayout topBack;
    @BindView(R.id.tv_content_name)
    TextView tvContentName;
    @BindView(R.id.tv_top_right)
    TextView tvTopRight;
    @BindView(R.id.iv_top_right)
    ImageView ivTopRight;
    @BindView(R.id.et_register_user_name)
    EditText etRegisterUserName;
    @BindView(R.id.tv_register_number_again)
    TextView tvRegisterNumberAgain;
    @BindView(R.id.iv_register_number)
    ImageView ivRegisterNumber;
    @BindView(R.id.et_register_user_number)
    EditText etRegisterUserNumber;
    @BindView(R.id.iv_register_delete)
    ImageView ivRegisterDelete;
    @BindView(R.id.et_register_pwd)
    EditText etRegisterPwd;
    @BindView(R.id.iv_register_look)
    ImageView ivRegisterLook;
    @BindView(R.id.et_register_pwd_again)
    EditText etRegisterPwdAgain;
    @BindView(R.id.bt_register_register)
    Button btRegisterRegister;
    @BindView(R.id.iv_register_protocol)
    ImageView ivRegisterProtocol;
    @BindView(R.id.tv_register_protocol)
    TextView tvRegisterProtocol;

    private int codeNum = 30;
    private boolean isPwdSee=false;
    private boolean isProtocol=true;
    RegisterPresenter mRegisterPresenter;
    private Handler mhandle = new Handler();
    private String mpwd;
    private String loginName="";

    @Override
    public BasePresenter initPresenter() {
        mRegisterPresenter = new RegisterPresenter();
        return mRegisterPresenter;
    }

    @Override
    public void initParam(Bundle param) {
        loginName = param.getString(LoginActivity.STACK_NAME_KEY);
    }

    @Override
    public int bindLayout() {
        return R.layout.activity_register;
    }

    @Override
    public void initView(View view) {
        tvContentName.setText("注册");
        tvTopRight.setVisibility(View.GONE);
        ivTopRight.setVisibility(View.GONE);
        ivRegisterProtocol.setSelected(true);
    }

    @Override
    public void doBusiness(Context mContext) {

    }
    @Override
    public View initBack() {
        return topBack;
    }


    public void showView(String msg, int type) {
        String userName = etRegisterUserName.getText().toString();
        String pwd = etRegisterPwd.getText().toString();
        hideLoading();
        if(type==100){
            SPUtils.INSTANCE.setSPValue(SPKey.SP_USER_SIP_NUMBER_KEY, userName);
            SPUtils.INSTANCE.setSPValue(SPKey.SP_LOAGIN_KEY, true);
            SPUtils.INSTANCE.setSPValue(SPKey.SP_USER_NAME_KEY,userName);
            SPUtils.INSTANCE.setSPValue(SPKey.SP_USER_PWD_KEY,pwd);
            ToastUtil.makeToast("注册成功");
            Bundle bundle = new Bundle();
            MyApplication.getInstance().finishActivityByName(loginName);
            bundle.putInt(InCommunityActivity.TYPE_KEY,type);
            bundle.putString(InCommunityActivity.NAME_KEY,userName);
            startActivity(InCommunityActivity.class,bundle);
            finish();
        }else if (type == 123 ){
            mRegisterPresenter.login(userName, pwd);
        }
        hideLoading();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }

    @OnClick({ R.id.iv_register_number, R.id.iv_register_delete, R.id.iv_register_look, R.id.bt_register_register,R.id.iv_register_protocol, R.id.tv_register_protocol})
    public void onViewClicked(View view) {
        switch (view.getId()) {

            case R.id.iv_register_number:
                LogUtils.i("iv_register_number");
                toRegisterCode();
                break;
            case R.id.iv_register_delete:
                LogUtils.i("iv_register_delete");
                etRegisterUserName.setText("");
                etRegisterUserNumber.setText("");
                etRegisterPwd.setText("");
                etRegisterPwdAgain.setText("");
                break;
            case R.id.iv_register_look:
                LogUtils.i("iv_register_look");
                changePwdIsSee();
                break;
            case R.id.bt_register_register:
                LogUtils.i("bt_register_register");
                register();
                break;
            case R.id.iv_register_protocol:
                LogUtils.i("iv_register_protocol");
                changeProtocaol();
                break;
            case R.id.tv_register_protocol:
                LogUtils.i("tv_register_protocol");

                break;
        }
    }

    private void register() {
        String name = etRegisterUserName.getText().toString();
        if(TextUtils.isEmpty(name)){
            ToastUtil.makeToast("用户名不能为空");
            return;
        }
        String code = etRegisterUserNumber.getText().toString();
        if(TextUtils.isEmpty(code)){
            ToastUtil.makeToast("验证码不能为空");
            return;
        }
        mpwd = etRegisterPwd.getText().toString();
        if(TextUtils.isEmpty(mpwd)){
            ToastUtil.makeToast("密码不能为空");
            return;
        }else{
            if(mpwd.length()<6|| mpwd.length()>20){
                ToastUtil.makeToast("密码长度6位到20位之间");
                return;
            }
        }
        String pwdAgain = etRegisterPwdAgain.getText().toString();
        if(!pwdAgain.equals(mpwd)){
            ToastUtil.makeToast("两次密码不相等");
            return;
        }
        if(!isProtocol){
            ToastUtil.makeToast("请同意用户协议");
            return;
        }
        showLoading();
        mRegisterPresenter.register(name, mpwd,code);
    }

    private void changeProtocaol() {
        if(isProtocol){
            isProtocol=false;
        }else{
            isProtocol=true;
        }
        ivRegisterProtocol.setSelected(isProtocol);

    }

    private void changePwdIsSee(){
        if(isPwdSee){
            isPwdSee=false;
            etRegisterPwd.setTransformationMethod(PasswordTransformationMethod.getInstance());
            etRegisterPwdAgain.setTransformationMethod(PasswordTransformationMethod.getInstance());
        }else{
            isPwdSee=true;
            etRegisterPwd.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            etRegisterPwdAgain.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
        }
        ivRegisterLook.setSelected(isPwdSee);
        //切换后将EditText光标置于末尾
        CharSequence charSequence = etRegisterPwd.getText();
        if (charSequence instanceof Spannable) {
            Spannable spanText = (Spannable) charSequence;
            Selection.setSelection(spanText, charSequence.length());
        }
    }
    private void toRegisterCode() {
        String userName = etRegisterUserName.getText().toString();
        if (!PhoneNumUtil.verifyPhone(userName)) {
            ToastUtil.makeToast("请输入正确的用户名格式");
            return;
        }
        mRegisterPresenter.getCode(userName, "sms_reg", "0");
        tvRegisterNumberAgain.setVisibility(View.VISIBLE);
        ivRegisterNumber.setVisibility(View.GONE);
        mhandle.post(timeRunable);
    }

    /*****************计时器*******************/
    private Runnable timeRunable = new Runnable() {
        @Override
        public void run() {
            codeNum--;
            tvRegisterNumberAgain.setText("(" + codeNum + ")重获");
            if (codeNum > 0) {
                //递归调用本runable对象，实现每隔一秒一次执行任务
                mhandle.postDelayed(this, 1000);
            } else {
                tvRegisterNumberAgain.setVisibility(View.GONE);
                ivRegisterNumber.setVisibility(View.VISIBLE);
                codeNum=30;
            }
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mhandle!=null) {
            mhandle.removeCallbacks(timeRunable);
        }
    }
}

