package com.cui.android.jianchengdichan.view.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.cui.android.jianchengdichan.R;
import com.cui.android.jianchengdichan.http.bean.BaseBean;
import com.cui.android.jianchengdichan.http.bean.CatesBean;
import com.cui.android.jianchengdichan.http.bean.ChargeCateBean;
import com.cui.android.jianchengdichan.presenter.BasePresenter;
import com.cui.android.jianchengdichan.presenter.PayFeesPresenter;
import com.cui.android.jianchengdichan.presenter.RegisterPresenter;
import com.cui.android.jianchengdichan.utils.SPKey;
import com.cui.android.jianchengdichan.utils.SPUtils;
import com.cui.android.jianchengdichan.utils.ToastUtil;
import com.cui.android.jianchengdichan.view.BaseActivtity;
import com.cui.android.jianchengdichan.view.ui.adapter.PaymentAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PayFeesActivity extends BaseActivtity {
    @BindView(R.id.top_back)
    RelativeLayout topBack;
    @BindView(R.id.tv_content_name)
    TextView tvContentName;
    @BindView(R.id.tv_top_right)
    TextView tvTopRight;
    @BindView(R.id.iv_top_right)
    ImageView ivTopRight;
    @BindView(R.id.elv_pay_expandable)
    ExpandableListView elvPayExpandable;

    private PaymentAdapter paymentAdapter;
    PayFeesPresenter payFeesPresenter;
    private List<String> typeList = new ArrayList<>();
    private List<List<ChargeCateBean.PayTypeBean>> itemList = new ArrayList<>();
    @Override
    public BasePresenter initPresenter() {
        payFeesPresenter = new PayFeesPresenter();
        return payFeesPresenter;
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
       int uid =(int) SPUtils.INSTANCE.getSPValue(SPKey.SP_USER_UID_KEY,SPUtils.DATA_INT);
        String token =(String) SPUtils.INSTANCE.getSPValue(SPKey.SP_USER_TOKEN_KEY,SPUtils.DATA_STRING);
       int com_id =(int) SPUtils.INSTANCE.getSPValue(SPKey.SP_USER_COM_ID_KEY,SPUtils.DATA_INT);
        payFeesPresenter.getCates(uid,token,com_id);
    }

    @Override
    public View initBack() {
        return topBack;
    }


    @OnClick({R.id.top_back, R.id.tv_top_right})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.top_back:
                break;
            case R.id.tv_top_right:
                startActivity(new Intent(mContext,PayMentRecordActivity.class));
                break;
        }
    }
    public void showView(Object msg,int type){
        if(type==200){
            List<CatesBean> data =(List<CatesBean>)msg;
            initListView();
        }
    }

    public void getCatesBean(List<CatesBean> data){
        typeList.clear();
        for (CatesBean catesBean:data){
            typeList.add(catesBean.getName());
        }
        int uid =(int) SPUtils.INSTANCE.getSPValue(SPKey.SP_USER_UID_KEY,SPUtils.DATA_INT);
        String token =(String) SPUtils.INSTANCE.getSPValue(SPKey.SP_USER_TOKEN_KEY,SPUtils.DATA_STRING);
        int com_id =(int) SPUtils.INSTANCE.getSPValue(SPKey.SP_USER_COM_ID_KEY,SPUtils.DATA_INT);
        payFeesPresenter.getChargeCate(uid,token,com_id);
    }
    public void getChargeCate(List<ChargeCateBean> data){
        itemList.clear();
        for(ChargeCateBean chargeCateBean :data){
            itemList.add(chargeCateBean.getData());
        }
        initListView();
    }
    private void initListView() {
        paymentAdapter = new PaymentAdapter(typeList , itemList , PayFeesActivity.this);
        elvPayExpandable.setAdapter(paymentAdapter);
    }
}
