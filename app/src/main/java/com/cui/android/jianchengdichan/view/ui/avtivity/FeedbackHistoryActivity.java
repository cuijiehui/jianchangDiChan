package com.cui.android.jianchengdichan.view.ui.avtivity;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.cui.android.jianchengdichan.R;
import com.cui.android.jianchengdichan.http.bean.HistoryDataBean;
import com.cui.android.jianchengdichan.presenter.BasePresenter;
import com.cui.android.jianchengdichan.presenter.FeedbackHistoryPresenter;
import com.cui.android.jianchengdichan.utils.SPKey;
import com.cui.android.jianchengdichan.utils.SPUtils;
import com.cui.android.jianchengdichan.view.base.BaseActivtity;
import com.cui.android.jianchengdichan.view.ui.adapter.HistorysAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class FeedbackHistoryActivity extends BaseActivtity  {
    FeedbackHistoryPresenter mfeedbackHistoryPresenter;
    @BindView(R.id.top_back)
    RelativeLayout topBack;
    @BindView(R.id.tv_content_name)
    TextView tvContentName;
    @BindView(R.id.rv_historys_data)
    RecyclerView rvHistorysData;
    HistorysAdapter historysAdapter;

    List<HistoryDataBean> mDataList = new ArrayList<>();

    @Override
    public BasePresenter initPresenter() {
        mfeedbackHistoryPresenter = new FeedbackHistoryPresenter();
        return mfeedbackHistoryPresenter;
    }

    @Override
    public View initBack() {
        return topBack;
    }

    @Override
    public void initParms(Bundle parms) {

    }

    @Override
    public int bindLayout() {
        return R.layout.activity_feedback_history;
    }

    @Override
    public void initView(View view) {
        tvContentName.setText("反馈历史");

    }

    @Override
    public void doBusiness(Context mContext) {
        int uid =(int) SPUtils.INSTANCE.getSPValue(SPKey.SP_USER_UID_KEY,SPUtils.DATA_INT);
        String token = (String)SPUtils.INSTANCE.getSPValue(SPKey.SP_USER_TOKEN_KEY,SPUtils.DATA_STRING);
        mfeedbackHistoryPresenter.getOpinionList(uid,token,1);
    }


  public void initRecyclerView(){
      LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
      rvHistorysData.setLayoutManager(linearLayoutManager);
       historysAdapter = new HistorysAdapter(mDataList);
      rvHistorysData.setAdapter(historysAdapter);
      historysAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
          @Override
          public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
              int uid =(int) SPUtils.INSTANCE.getSPValue(SPKey.SP_USER_UID_KEY,SPUtils.DATA_INT);
              String token = (String)SPUtils.INSTANCE.getSPValue(SPKey.SP_USER_TOKEN_KEY,SPUtils.DATA_STRING);
              mfeedbackHistoryPresenter.delOpinion(uid,token,mDataList.get(position).getId());
          }
      });
  }

    public void getOpinionList(List<HistoryDataBean> dataList) {
        mDataList.clear();
        mDataList.addAll(dataList);
        initRecyclerView();
    }

    public void onFailure() {
    }

    public void onError() {
    }

    public void delOpinion() {
    }
}
