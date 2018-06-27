package com.cui.android.jianchengdichan.view.ui.avtivity;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.cui.android.jianchengdichan.R;
import com.cui.android.jianchengdichan.http.bean.TopicListBean;
import com.cui.android.jianchengdichan.presenter.BasePresenter;
import com.cui.android.jianchengdichan.presenter.TopicListPresenter;
import com.cui.android.jianchengdichan.utils.SPKey;
import com.cui.android.jianchengdichan.utils.SPUtils;
import com.cui.android.jianchengdichan.view.base.BaseActivtity;
import com.cui.android.jianchengdichan.view.ui.adapter.TopicListDataAdapter;
import com.cui.android.jianchengdichan.view.ui.customview.FullyLinearLayoutManager;

import java.util.LinkedList;
import java.util.List;

import butterknife.BindView;

public class TopicListActivity extends BaseActivtity {
    @BindView(R.id.top_back)
    RelativeLayout topBack;
    @BindView(R.id.tv_content_name)
    TextView tvContentName;
    @BindView(R.id.tv_top_right)
    TextView tvTopRight;
    @BindView(R.id.iv_top_right)
    ImageView ivTopRight;
    @BindView(R.id.rv_topic_list)
    RecyclerView rvTopicList;
    TopicListPresenter topicListPresenter =new TopicListPresenter();
    TopicListDataAdapter topicListDataAdapter;
    List<TopicListBean> dataList = new LinkedList<>();
    int type=0;
    int page=1;
    @Override
    public BasePresenter initPresenter() {
        return topicListPresenter;
    }

    @Override
    public void initParms(Bundle parms) {
        if(parms!=null){
            type=  parms.getInt("type");
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        int uid = (Integer) SPUtils.INSTANCE.getSPValue(SPKey.SP_USER_UID_KEY, SPUtils.DATA_INT);
        topicListPresenter.getTopicList(uid,page,type);
    }

    @Override
    public int bindLayout() {
        return R.layout.activity_topic_list;
    }

    @Override
    public void initView(View view) {
        switch (type){
            case 0://全部
                tvContentName.setText("话题");
                break;
            case 1://话题
                tvContentName.setText("话题");
                break;
            case 2://拼车
                tvContentName.setText("我要拼车");
                break;
            case 3://跳蚤市场
                tvContentName.setText("跳蚤市场");
                break;
        }
        initRecyclerView();
    }
    private void initRecyclerView() {
        FullyLinearLayoutManager linearLayoutManager = new FullyLinearLayoutManager(mContext);
        rvTopicList.setLayoutManager(linearLayoutManager);
        topicListDataAdapter=new TopicListDataAdapter(R.layout.item_comm_topic_layout,dataList,TopicListActivity.this);

        rvTopicList.setAdapter(topicListDataAdapter);
        rvTopicList.addItemDecoration(new DividerItemDecoration(mContext,DividerItemDecoration.VERTICAL));
        topicListDataAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()){
                    case R.id.tv_topic_like:
                        TopicListBean topicListBean = dataList.get(position);
                        int uid = (Integer) SPUtils.INSTANCE.getSPValue(SPKey.SP_USER_UID_KEY, SPUtils.DATA_INT);
                        String token = (String) SPUtils.INSTANCE.getSPValue(SPKey.SP_USER_TOKEN_KEY, SPUtils.DATA_STRING);
                        if(topicListBean.getIs_praise()==0){
                            topicListBean.setIs_praise(1);
                            int praiseNum = new Integer(topicListBean.getPraise_num());
                            praiseNum+=1;
                            topicListBean.setPraise_num(praiseNum+"");
                            topicListPresenter.doPraise(topicListBean.getId(),uid,token);
                        }else{
                            topicListBean.setIs_praise(0);
                            int praiseNum = new Integer(topicListBean.getPraise_num());
                            praiseNum-=1;
                            topicListBean.setPraise_num(praiseNum+"");
                            topicListPresenter.cancelPraise(topicListBean.getId(),uid,token);

                        }
                        topicListDataAdapter.notifyItemChanged(position,"type");

                        break;
                    case R.id.tv_comm_act_topic:
                        Bundle bundle = new Bundle();
                        bundle.putSerializable("data",dataList.get(position));
                        startActivity(CommentActivity.class,bundle);
                        break;

                }
            }
        });
        topicListDataAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Bundle bundle = new Bundle();
                bundle.putSerializable("data",dataList.get(position));
                startActivity(CommentActivity.class,bundle);
            }
        });
    }

    @Override
    public void doBusiness(Context mContext) {

    }

    @Override
    public View initBack() {
        return topBack;
    }

    public void getTopicList(List<TopicListBean> data) {
        dataList.clear();
        if(data!=null){
            dataList.addAll(data);
        }
        topicListDataAdapter.notifyDataSetChanged();
    }

    public void cancelPraise() {

    }

    public void doPraise() {

    }
}
