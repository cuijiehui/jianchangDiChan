package com.cui.android.jianchengdichan.view.ui.avtivity;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.cui.android.jianchengdichan.R;
import com.cui.android.jianchengdichan.http.bean.CommunityBean;
import com.cui.android.jianchengdichan.http.bean.UserCommunityBean;
import com.cui.android.jianchengdichan.presenter.BasePresenter;
import com.cui.android.jianchengdichan.presenter.InCommunityPresenter;
import com.cui.android.jianchengdichan.utils.LogUtils;
import com.cui.android.jianchengdichan.utils.SPKey;
import com.cui.android.jianchengdichan.utils.SPUtils;
import com.cui.android.jianchengdichan.utils.ToastUtil;
import com.cui.android.jianchengdichan.view.BaseActivtity;
import com.cui.android.jianchengdichan.view.ui.customview.ChildCommunityBean;
import com.cui.android.jianchengdichan.view.ui.customview.ChooseCodePop;
import com.cui.android.jianchengdichan.view.ui.customview.ChooseComPop;
import com.cui.android.jianchengdichan.view.ui.customview.ChooseIdentityPop;
import com.cui.android.jianchengdichan.view.ui.customview.interfaces.CodeResultListener;
import com.cui.android.jianchengdichan.view.ui.customview.interfaces.ComResultListener;
import com.cui.android.jianchengdichan.view.ui.customview.interfaces.IdentityResultListener;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class InCommunityActivity extends BaseActivtity {


    Button btGotoSave;
    InCommunityPresenter inCommunityPresenter;
    @BindView(R.id.top_back)
    RelativeLayout topBack;
    @BindView(R.id.tv_content_name)
    TextView tvContentName;
    @BindView(R.id.tv_top_right)
    TextView tvTopRight;
    @BindView(R.id.iv_top_right)
    ImageView ivTopRight;
    @BindView(R.id.text1)
    TextView text1;
    @BindView(R.id.ed_name)
    EditText edName;
    @BindView(R.id.text2)
    TextView text2;
    @BindView(R.id.ed_identity)
    TextView edIdentity;
    @BindView(R.id.text3)
    TextView text3;
    @BindView(R.id.tv_community_name)
    TextView tvCommunityName;
    @BindView(R.id.rel_community_name)
    RelativeLayout relCommunityName;



    @BindView(R.id.tv_submit)
    TextView tvSubmit;
    @BindView(R.id.lin_bg)
    LinearLayout linBg;
    private ChooseComPop chooseComPop;
    private String communityName;
    private String unit_id;
    private String codeId;
    private String community_id;
    private ChooseCodePop codePop;
    private String userName;
    private ChooseIdentityPop identityPop;
    private String identity;
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 200:
                    showPop2(unit_id);
                    break;
            }
        }
    };

    @Override
    public BasePresenter initPresenter() {
        inCommunityPresenter = new InCommunityPresenter();
        return inCommunityPresenter;
    }

    @Override
    public void initParms(Bundle parms) {
    }

    @Override
    public int bindLayout() {
        return R.layout.activity_in_community;
    }

    @Override
    public void initView(View view) {
        tvContentName.setText("入驻申请");

    }

    @Override
    public void doBusiness(Context mContext) {

    }

    @Override
    public View initBack() {
        return topBack;
    }


    public void getUserEnterance(UserCommunityBean data) {
                ToastUtil.makeToast("尊敬的用户，请耐心等我们审核完毕～");
               finish();
    }

    private void showPop() {

        if (chooseComPop == null) {
            chooseComPop = new ChooseComPop(InCommunityActivity.this, comResultListener, inCommunityPresenter);
            chooseComPop.setOnDismissListener(dismissListener);
        }

        if (!chooseComPop.isShowing()) {
            chooseComPop.showAtLocation(linBg, Gravity.BOTTOM, 0, 0);
            chooseComPop.isShowing();
        } else {
            chooseComPop.dismiss();
        }
    }

    PopupWindow.OnDismissListener dismissListener = new PopupWindow.OnDismissListener() {
        @Override
        public void onDismiss() {
            chooseComPop = null;
            codePop = null;
//            identityPop = null;
        }
    };
    ComResultListener comResultListener = new ComResultListener() {
        @Override
        public void resultBean(CommunityBean groupBean, ChildCommunityBean childBean) {
            communityName = groupBean.getName() + "/" + childBean.getName();
            community_id = groupBean.getId();
            unit_id = childBean.getId();
            //TODO
//            tvCommunityName.setText(communityName);
//
            codeId = "";
            handler.obtainMessage(200 ).sendToTarget();

            LogUtils.i("选择地社区" + communityName);
        }
    };


    @OnClick({R.id.ed_identity, R.id.rel_community_name, R.id.tv_submit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ed_identity:
                showIdentityPop();

                break;
            case R.id.rel_community_name:
                showPop();

                break;

            case R.id.tv_submit:
//
                userName = edName.getText().toString().trim();

                if (TextUtils.isEmpty(userName)) {
                    ToastUtil.makeToast("名字不能为空");
                    return;
                }

                if (TextUtils.isEmpty(identity)) {
                    ToastUtil.makeToast("身份不能为空");
                    return;
                }
//
                if (TextUtils.isEmpty(communityName)) {
                    ToastUtil.makeToast("社区不能为空");
                    return;
                }
//
                if (TextUtils.isEmpty(codeId)) {
                    ToastUtil.makeToast("单元号不能为空");
                    return;
                }
//
//                AuditBean auditBean = new AuditBean();
//                auditBean.setUid(cUid);
//                auditBean.setToken(token);
                    String type ="5";
                if (identity.equals("业主")){
                    type ="1";
                }else if(identity.equals("家人")){
                    type ="2";
                }else if (identity.equals("物业员工")){
                    type ="3";
                }else if (identity.equals("物业高管")){
                    type ="4";
                }else if (identity.equals("其他")){
                    type ="5";
                }
//
//                auditBean.setName(userName);
//                auditBean.setCommunity_id(community_id);
//                auditBean.setUnit_id(unit_id);
//                auditBean.setProperty_id(codeId);
//
//                auditData(auditBean);
               int cUid =(int) SPUtils.INSTANCE.getSPValue(SPKey.SP_USER_UID_KEY,SPUtils.DATA_INT);
               String token = (String)SPUtils.INSTANCE.getSPValue(SPKey.SP_USER_TOKEN_KEY,SPUtils.DATA_STRING);
                inCommunityPresenter.setUserCommunity(
                        cUid
                        ,token
                        ,userName
                        ,type
                        ,community_id
                        ,unit_id
                        ,codeId
                );
                break;
        }
    }
    private void showIdentityPop() {
        if (identityPop == null) {
            identityPop = new ChooseIdentityPop(mContext, identityResultListener);
            identityPop.setOnDismissListener(dismissListener);
        }

        if (!identityPop.isShowing()) {
            identityPop.showAtLocation(linBg, Gravity.BOTTOM, 0, 0);
            identityPop.isShowing();
        } else {
            identityPop.dismiss();
        }
    }
    IdentityResultListener identityResultListener = new IdentityResultListener() {
        @Override
        public void identityResult(String result) {

            edIdentity.setText(result);
            identity = result;

        }
    };
    private void showPop2(String id) {

        if (codePop == null) {
            codePop = new ChooseCodePop(mContext, id, codeResultListener,inCommunityPresenter);
            codePop.setOnDismissListener(dismissListener);
        }

        if (!codePop.isShowing()) {
            codePop.showAtLocation(linBg, Gravity.BOTTOM, 0, 0);
            codePop.isShowing();
        } else {
            codePop.dismiss();
        }

    }
    CodeResultListener codeResultListener = new CodeResultListener() {
        @Override
        public void codeBean(ChildCommunityBean bean) {
             tvCommunityName.setText(communityName+bean.getName());
            codeId = bean.getId();
        }
    };
    public void getCommunityList(List<CommunityBean> data) {
        chooseComPop.getCommunityList(data);
    }

    public void getUnitList(List<ChildCommunityBean> data) {
        codePop.getUnitList(data);
    }



}
