package com.cui.android.jianchengdichan.view.ui.avtivity;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.cui.android.jianchengdichan.R;
import com.cui.android.jianchengdichan.http.bean.NoticeBean;
import com.cui.android.jianchengdichan.presenter.BasePresenter;
import com.cui.android.jianchengdichan.presenter.NoticePresenter;
import com.cui.android.jianchengdichan.utils.SPKey;
import com.cui.android.jianchengdichan.utils.SPUtils;
import com.cui.android.jianchengdichan.view.BaseActivtity;
import com.cui.android.jianchengdichan.view.ui.adapter.NoticeRvAdpter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NoticeAcitivty extends BaseActivtity {
    @BindView(R.id.top_back)
    RelativeLayout topBack;
    @BindView(R.id.tv_content_name)
    TextView tvContentName;
    @BindView(R.id.tv_top_right)
    TextView tvTopRight;
    @BindView(R.id.iv_top_right)
    ImageView ivTopRight;
    @BindView(R.id.rv_notice)
    RecyclerView rvNotice;
    NoticePresenter noticePresenter = new NoticePresenter();
    NoticeRvAdpter noticeRvAdpter;
    List<NoticeBean> dataList=new ArrayList<>();

    @Override
    public BasePresenter initPresenter() {
        return noticePresenter;
    }

    @Override
    public void initParms(Bundle parms) {

    }

    @Override
    public int bindLayout() {
        return R.layout.activity_notice_acitivty;
    }

    @Override
    public void initView(View view) {
        tvContentName.setText("公告");
         noticeRvAdpter =new NoticeRvAdpter(dataList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
        rvNotice.setLayoutManager(linearLayoutManager);
        rvNotice.setAdapter(noticeRvAdpter);
    }

    @Override
    public void doBusiness(Context mContext) {
        int cUid =(int) SPUtils.INSTANCE.getSPValue(SPKey.SP_USER_UID_KEY,SPUtils.DATA_INT);
        String token = (String)SPUtils.INSTANCE.getSPValue(SPKey.SP_USER_TOKEN_KEY,SPUtils.DATA_STRING);
        noticePresenter.getNoticeList(cUid,token,"2",1);
    }

    @Override
    public View initBack() {
        return topBack;
    }


    public void getNoticeList(List<NoticeBean> data) {
        if(data!=null&&data.size()>0){
            dataList.clear();
            dataList.addAll(data);
            noticeRvAdpter.notifyDataSetChanged();
        }
    }
}
