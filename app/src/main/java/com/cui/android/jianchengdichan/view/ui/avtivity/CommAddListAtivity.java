package com.cui.android.jianchengdichan.view.ui.avtivity;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.cui.android.jianchengdichan.R;
import com.cui.android.jianchengdichan.http.bean.UserEntranceBean;
import com.cui.android.jianchengdichan.presenter.BasePresenter;
import com.cui.android.jianchengdichan.presenter.CommAddListPresenter;
import com.cui.android.jianchengdichan.utils.SPKey;
import com.cui.android.jianchengdichan.utils.SPUtils;
import com.cui.android.jianchengdichan.view.BaseActivtity;
import com.cui.android.jianchengdichan.view.ui.adapter.SectionAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class CommAddListAtivity extends BaseActivtity {

    @BindView(R.id.top_back)
    RelativeLayout topBack;
    @BindView(R.id.tv_content_name)
    TextView tvContentName;
    @BindView(R.id.tv_top_right)
    TextView tvTopRight;
    @BindView(R.id.iv_top_right)
    ImageView ivTopRight;
    @BindView(R.id.ex_com_app)
    RecyclerView exComApp;
    @BindView(R.id.com_app_comtainer)
    FrameLayout comAppComtainer;
    List<UserEntranceBean> userEntranceBeans = new ArrayList<>();
    CommAddListPresenter commAddListPresenter ;

    @Override
    public BasePresenter initPresenter() {
        commAddListPresenter=new CommAddListPresenter();
        return commAddListPresenter;
    }


    @Override
    public void initParms(Bundle parms) {

    }

    @Override
    public int bindLayout() {
        return R.layout.activity_comm_add_list_ativity;
    }

    @Override
    public void initView(View view) {
        tvContentName.setText("小区申请");
        ivTopRight.setVisibility(View.VISIBLE);
        ivTopRight.setBackgroundResource(R.drawable.main_add_button);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        exComApp.setLayoutManager(layoutManager);
        SectionAdapter sectionAdapter =new SectionAdapter(userEntranceBeans);
        exComApp.setAdapter(sectionAdapter);
    }

    @Override
    public void doBusiness(Context mContext) {
        int uid =(int) SPUtils.INSTANCE.getSPValue(SPKey.SP_USER_UID_KEY,SPUtils.DATA_INT);
        String token = (String)SPUtils.INSTANCE.getSPValue(SPKey.SP_USER_TOKEN_KEY,SPUtils.DATA_STRING);
        commAddListPresenter.getUserEntrance(uid,token);
    }

    @Override
    public View initBack() {
        return topBack;
    }


    @OnClick({R.id.top_back, R.id.iv_top_right})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.top_back:
                break;
            case R.id.iv_top_right:
            startActivity(InCommunityActivity.class);
                break;
        }
    }

    public void getUserEnterance(List<UserEntranceBean> data) {
        userEntranceBeans.clear();
        userEntranceBeans.addAll(data);
        SectionAdapter sectionAdapter =new SectionAdapter(userEntranceBeans);
        exComApp.setAdapter(sectionAdapter);
    }
}
