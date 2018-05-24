package com.cui.android.jianchengdichan.view.ui;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;

import com.cui.android.jianchengdichan.R;
import com.cui.android.jianchengdichan.presenter.BasePresenter;
import com.cui.android.jianchengdichan.presenter.RegisterPresenter;
import com.cui.android.jianchengdichan.utils.ToastUtil;
import com.cui.android.jianchengdichan.view.BaseActivtity;
import com.cui.android.jianchengdichan.view.ui.adapter.PaymentAdapter;
import com.cui.android.jianchengdichan.view.ui.beans.PayTypeBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PayFeesActivity extends BaseActivtity {
    @BindView(R.id.top_back)
    ImageView topBack;
    @BindView(R.id.tv_content_name)
    TextView tvContentName;
    @BindView(R.id.tv_top_right)
    TextView tvTopRight;
    @BindView(R.id.iv_top_right)
    ImageView ivTopRight;
    @BindView(R.id.elv_pay_expandable)
    ExpandableListView elvPayExpandable;

    private PaymentAdapter paymentAdapter;
    RegisterPresenter mRegisterPresenter;
    private List<String> typeList = new ArrayList<>();
    private List<List<PayTypeBean>> itemList = new ArrayList<>();
    @Override
    public BasePresenter initPresenter() {
        mRegisterPresenter = new RegisterPresenter();
        return mRegisterPresenter;
    }

    @Override
    public void initParms(Bundle parms) {

    }

    @Override
    public int bindLayout() {
        return R.layout.activity_pay_fees;
    }

    @Override
    public void initView(View view) {
        tvContentName.setText("物业缴费");
        tvTopRight.setText("缴费记录");
        tvTopRight.setVisibility(View.VISIBLE);
        elvPayExpandable.setGroupIndicator(null);
        elvPayExpandable.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            @Override
            public void onGroupExpand(int groupPosition) {
                if (itemList.get(groupPosition).size() == 0){
                    ToastUtil.makeToast("无未缴费");
                }
            }
        });
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

    @OnClick({R.id.top_back, R.id.tv_top_right})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.top_back:
                break;
            case R.id.tv_top_right:
                break;
        }
    }
    public void showView(String msg,int type){
        if(type==200){
            initListView();
        }
    }

    private void initListView() {

        paymentAdapter = new PaymentAdapter(typeList , itemList , PayFeesActivity.this);
        elvPayExpandable.setAdapter(paymentAdapter);

    }
}
