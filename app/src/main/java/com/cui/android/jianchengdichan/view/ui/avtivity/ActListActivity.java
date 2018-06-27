package com.cui.android.jianchengdichan.view.ui.avtivity;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.cui.android.jianchengdichan.R;
import com.cui.android.jianchengdichan.http.bean.CommentActBean;
import com.cui.android.jianchengdichan.presenter.ActListPresenter;
import com.cui.android.jianchengdichan.presenter.BasePresenter;
import com.cui.android.jianchengdichan.view.base.BaseActivtity;
import com.cui.android.jianchengdichan.view.ui.adapter.ActListAdapter;

import java.util.LinkedList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class ActListActivity extends BaseActivtity {
    @BindView(R.id.top_back)
    RelativeLayout topBack;
    @BindView(R.id.tv_content_name)
    TextView tvContentName;
    @BindView(R.id.tv_top_right)
    TextView tvTopRight;
    @BindView(R.id.iv_top_right)
    ImageView ivTopRight;
    @BindView(R.id.rv_act_list)
    RecyclerView rvActList;
    ActListAdapter actListAdapter;
    List<CommentActBean> dataList =new LinkedList<>();
    ActListPresenter actListPresenter =new ActListPresenter();
    int page=1;
    @Override
    public BasePresenter initPresenter() {
        return actListPresenter;
    }

    @Override
    public void initParms(Bundle parms) {

    }

    @Override
    public int bindLayout() {
        return R.layout.activity_act_list;
    }

    @Override
    protected void onResume() {
        super.onResume();
        actListPresenter.getData(page);
    }

    @Override
    public void initView(View view) {
        tvContentName.setText("活动");
        tvTopRight.setVisibility(View.VISIBLE);
        tvTopRight.setText("发布活动");
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
        rvActList.setLayoutManager(linearLayoutManager);
        actListAdapter = new ActListAdapter(R.layout.item_act_list_layout,dataList);
        rvActList.setAdapter(actListAdapter);
        actListAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                Bundle bundle = new Bundle();
                CommentActBean commentActBean = dataList.get(position);
                bundle.putInt("id",commentActBean.getId());
                startActivity(ActDetaiActivity.class,bundle);
            }
        });
        rvActList.addItemDecoration(new DividerItemDecoration(mContext,DividerItemDecoration.VERTICAL));
    }

    @Override
    public void doBusiness(Context mContext) {

    }

    @Override
    public View initBack() {
        return topBack;
    }


    @OnClick(R.id.tv_top_right)
    public void onViewClicked() {
        startActivity(ReleaseActActivity.class);
    }

    public void getData(List<CommentActBean> data) {
        dataList.clear();
        if(data!=null){
            dataList.addAll(data);
        }
        actListAdapter.notifyDataSetChanged();
    }

}
