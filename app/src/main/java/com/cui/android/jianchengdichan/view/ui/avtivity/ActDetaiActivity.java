package com.cui.android.jianchengdichan.view.ui.avtivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.cui.android.jianchengdichan.R;
import com.cui.android.jianchengdichan.http.bean.ActivityDetailBean;
import com.cui.android.jianchengdichan.presenter.ActDetaiPresenter;
import com.cui.android.jianchengdichan.presenter.BasePresenter;
import com.cui.android.jianchengdichan.utils.Okhttp3Utils;
import com.cui.android.jianchengdichan.view.BaseActivtity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ActDetaiActivity extends BaseActivtity {
    @BindView(R.id.top_back)
    RelativeLayout topBack;
    @BindView(R.id.tv_content_name)
    TextView tvContentName;
    @BindView(R.id.tv_top_right)
    TextView tvTopRight;
    @BindView(R.id.iv_top_right)
    ImageView ivTopRight;
    @BindView(R.id.iv_act_detai_pic)
    ImageView ivActDetaiPic;
    @BindView(R.id.tv_act_datail_title)
    TextView tvActDatailTitle;
    @BindView(R.id.tv_act_datail_browse)
    TextView tvActDatailBrowse;
    @BindView(R.id.tv_act_datail_content)
    TextView tvActDatailContent;
    @BindView(R.id.tv_act_datail_addres)
    TextView tvActDatailAddres;
    @BindView(R.id.tv_act_datail_name)
    TextView tvActDatailName;
    @BindView(R.id.tv_act_datail_phone)
    TextView tvActDatailPhone;
    @BindView(R.id.bt_act_detai_sub)
    Button btActDetaiSub;
    ActDetaiPresenter actDetaiPresenter =new ActDetaiPresenter();
    int id ;
    @Override
    public BasePresenter initPresenter() {
        return actDetaiPresenter;
    }

    @Override
    public void initParms(Bundle parms) {
        if(parms!=null){
            id=parms.getInt("id");
        }
    }

    @Override
    public int bindLayout() {
        return R.layout.activity_act_detai;
    }

    @Override
    public void initView(View view) {
        tvContentName.setText("活动详情");
    }

    @Override
    protected void onResume() {
        super.onResume();
        actDetaiPresenter.getData(id);
    }

    @Override
    public void doBusiness(Context mContext) {

    }

    @Override
    public View initBack() {
        return topBack;
    }

    @OnClick({R.id.iv_top_right, R.id.bt_act_detai_sub})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_top_right:
                break;
            case R.id.bt_act_detai_sub:
                break;
        }
    }

    public void getData(ActivityDetailBean data) {
        Okhttp3Utils.getInstance().glide(mContext,data.getPic(),ivActDetaiPic);
        tvActDatailTitle.setText(data.getTitle());
        tvActDatailPhone.setText("联系电话："+data.getPhone());
        tvActDatailAddres.setText("活动地址："+data.getAddress());
        tvActDatailName.setText("活动时间："+data.getStartdate()+"至"+data.getEnddate());
        tvActDatailContent.setText(data.getContent());
//        tvActDatailBrowse.setText("已有"+data.getEnddate()+"次浏览");
    }
}
