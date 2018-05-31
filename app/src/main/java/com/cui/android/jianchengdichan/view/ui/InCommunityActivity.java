package com.cui.android.jianchengdichan.view.ui;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.cui.android.jianchengdichan.R;
import com.cui.android.jianchengdichan.http.bean.CommunityBean;
import com.cui.android.jianchengdichan.http.bean.UserCommunityBean;
import com.cui.android.jianchengdichan.presenter.BasePresenter;
import com.cui.android.jianchengdichan.presenter.InCommunityPresenter;
import com.cui.android.jianchengdichan.utils.ToastUtil;
import com.cui.android.jianchengdichan.view.BaseActivtity;
import com.cui.android.jianchengdichan.view.ui.customview.ChildCommunityBean;
import com.cui.android.jianchengdichan.view.ui.customview.ChooseComPop;
import com.cui.android.jianchengdichan.view.ui.customview.interfaces.ComResultListener;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
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
    @BindView(R.id.text4)
    TextView text4;
    @BindView(R.id.tv_community_code)
    TextView tvCommunityCode;
    @BindView(R.id.rel_community_code)
    RelativeLayout relCommunityCode;
    @BindView(R.id.tv_submit)
    TextView tvSubmit;
    private ChooseComPop chooseComPop;

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

    }

    @Override
    public void doBusiness(Context mContext) {

    }

    @Override
    public View initBack() {
        return topBack;
    }


    public void getUserEnterance(UserCommunityBean data) {
        if (data.getStatus().equals("0")) {
            ToastUtil.makeToast("成功");

        } else {
            ToastUtil.makeToast("失败");

        }
    }

    private void showPop() {

        if (chooseComPop == null) {
            chooseComPop = new ChooseComPop(InCommunityActivity.this, comResultListener,inCommunityPresenter);
            chooseComPop.setOnDismissListener(dismissListener);
        }

        if (!chooseComPop.isShowing()) {
//            chooseComPop.showAtLocation(lin_bg, Gravity.BOTTOM, 0, 0);
//            chooseComPop.isShowing();
        } else {
            chooseComPop.dismiss();
        }
    }

    PopupWindow.OnDismissListener dismissListener = new PopupWindow.OnDismissListener() {
        @Override
        public void onDismiss() {
            chooseComPop = null;
//            codePop = null;
//            identityPop = null;
        }
    };
    ComResultListener comResultListener = new ComResultListener() {
        @Override
        public void resultBean(CommunityBean groupBean, ChildCommunityBean childBean) {
//            communityName = groupBean.getName() + "," + childBean.getName();
//            community_id = groupBean.getId();
//            unit_id = childBean.getId();
//            tv_community_name.setText(communityName);
//
//            codeId = "";
//            tv_community_code.setText("");
//
//            LogUtil.i("选择地社区" + communityName);
        }
    };


    @OnClick({R.id.ed_identity, R.id.rel_community_name, R.id.rel_community_code, R.id.tv_submit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ed_identity:
//                showIdentityPop();

                break;
            case R.id.rel_community_name:
                showPop();

                break;
            case R.id.rel_community_code:
//                if (unit_id != null) {
//                    showPop2(unit_id);
//                }
                break;
            case R.id.tv_submit:
//
//                userName = ed_name.getText().toString().trim();
//
//                if (TextUtils.isEmpty(userName)) {
//                    ToastUtil.makeToast("名字不能为空");
//                    return;
//                }
//
//                if (TextUtils.isEmpty(identity)) {
//                    ToastUtil.makeToast("身份不能为空");
//                    return;
//                }
//
//                if (TextUtils.isEmpty(communityName)) {
//                    ToastUtil.makeToast("社区不能为空");
//                    return;
//                }
//
//                if (TextUtils.isEmpty(codeId)) {
//                    ToastUtil.makeToast("单元号不能为空");
//                    return;
//                }
//
//                AuditBean auditBean = new AuditBean();
//                auditBean.setUid(cUid);
//                auditBean.setToken(token);
//
//                if (identity.equals("业主")){
//                    auditBean.setType("1");
//                }else if(identity.equals("家人")){
//                    auditBean.setType("2");
//                }else if (identity.equals("物业员工")){
//                    auditBean.setType("3");
//                }else if (identity.equals("物业高管")){
//                    auditBean.setType("4");
//                }else if (identity.equals("其他")){
//                    auditBean.setType("5");
//                }
//
//                auditBean.setName(userName);
//                auditBean.setCommunity_id(community_id);
//                auditBean.setUnit_id(unit_id);
//                auditBean.setProperty_id(codeId);
//
//                auditData(auditBean);

                break;
        }
    }

    public void getCommunityList(List<CommunityBean> data) {
        chooseComPop.getCommunityList(data);
    }

    public void getUnitList(List<ChildCommunityBean> data) {
    }
}
