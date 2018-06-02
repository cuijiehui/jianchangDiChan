package com.cui.android.jianchengdichan.view.ui;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.cui.android.jianchengdichan.R;
import com.cui.android.jianchengdichan.presenter.BasePresenter;
import com.cui.android.jianchengdichan.presenter.SetUserMsgPresenter;
import com.cui.android.jianchengdichan.utils.LogUtils;
import com.cui.android.jianchengdichan.utils.SPKey;
import com.cui.android.jianchengdichan.utils.SPUtils;
import com.cui.android.jianchengdichan.utils.ToastUtil;
import com.cui.android.jianchengdichan.view.BaseActivtity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SetUserMsgActivity extends BaseActivtity {

    @BindView(R.id.top_back)
    RelativeLayout topBack;
    @BindView(R.id.tv_content_name)
    TextView tvContentName;
    @BindView(R.id.tv_top_right)
    TextView tvTopRight;
    @BindView(R.id.iv_top_right)
    ImageView ivTopRight;
    @BindView(R.id.et_set_text)
    EditText etSetText;
    @BindView(R.id.bt_set_ok)
    Button btSetOk;
    String key;
    String name;
    String hine;
    SetUserMsgPresenter setUserMsgPresenter =new SetUserMsgPresenter();
    @Override
    public BasePresenter initPresenter() {
        return setUserMsgPresenter;
    }

    @Override
    public void initParms(Bundle parms) {
        name = parms.getString("name");
       hine= parms.getString("hint");
       key= parms.getString("key");


    }

    @Override
    public int bindLayout() {
        return R.layout.activity_set_user_msg;
    }

    @Override
    public void initView(View view) {
        tvContentName.setText(name);
        etSetText.setHint(hine);
    }

    @Override
    public void doBusiness(Context mContext) {
    }

    @Override
    public View initBack() {
        return topBack;
    }




    @OnClick(R.id.bt_set_ok)
    public void onViewClicked() {
        String vlues =etSetText.getText().toString();
        if(TextUtils.isEmpty(vlues)){
            ToastUtil.makeToast("数值不能为空");
            return;
        }
        int uid = (Integer) SPUtils.INSTANCE.getSPValue(SPKey.SP_USER_UID_KEY, SPUtils.DATA_INT);
        String token = (String) SPUtils.INSTANCE.getSPValue(SPKey.SP_USER_TOKEN_KEY, SPUtils.DATA_STRING);
        setUserMsgPresenter.setUserInfo(uid,token,key,vlues);
    }

    public void setUserInfo() {
        LogUtils.i("setUserInfo"+key);
        String vlues =etSetText.getText().toString();
        if(key.equals("name")){
            SPUtils.INSTANCE.setSPValue(SPKey.SP_USER_TRUE_NAME_KEY, vlues);//真实名字
        }else{
            SPUtils.INSTANCE.setSPValue(SPKey.SP_USER_NAME_KEY, vlues);
        }
        startActivity(PersonalDataActivity.class);

    }
}
