package com.cui.android.jianchengdichan.view.ui.avtivity;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.baoyz.widget.PullRefreshLayout;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.cui.android.jianchengdichan.R;
import com.cui.android.jianchengdichan.http.bean.CommentActBean;
import com.cui.android.jianchengdichan.http.bean.HomeDataBean;
import com.cui.android.jianchengdichan.http.bean.TopicListBean;
import com.cui.android.jianchengdichan.presenter.BasePresenter;
import com.cui.android.jianchengdichan.presenter.PlazaPresenter;
import com.cui.android.jianchengdichan.utils.Okhttp3Utils;
import com.cui.android.jianchengdichan.utils.SPKey;
import com.cui.android.jianchengdichan.utils.SPUtils;
import com.cui.android.jianchengdichan.view.base.BaseActivity;
import com.cui.android.jianchengdichan.view.ui.adapter.TopicListDataAdapter;
import com.cui.android.jianchengdichan.view.ui.customview.FullyLinearLayoutManager;
import com.cui.android.jianchengdichan.view.ui.customview.GlideImageLoader;
import com.cui.android.jianchengdichan.view.ui.customview.UIImageView;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class PlazaActivity extends BaseActivity {
    @BindView(R.id.top_back)
    RelativeLayout topBack;
    @BindView(R.id.tv_content_name)
    TextView tvContentName;
    @BindView(R.id.tv_top_right)
    TextView tvTopRight;
    @BindView(R.id.iv_top_right)
    ImageView ivTopRight;
    @BindView(R.id.bn_plaza_adv)
    Banner bnPlazaAdv;
    @BindView(R.id.uiv_topic_pic)
    UIImageView uivTopicPic;
    @BindView(R.id.tv_topic_content)
    TextView tvTopicContent;
    @BindView(R.id.tv_bazaar_tx)
    TextView tvBazaarTx;
    @BindView(R.id.rv_topic_list_data)
    RecyclerView rvTopicListData;
    @BindView(R.id.prl_comm_refreshable)
    PullRefreshLayout prl_comm_refreshable;

    PlazaPresenter plazaPresenter = new PlazaPresenter();
    private List<HomeDataBean.AdBean> mDataList = new ArrayList<>();//轮播图数据
    List<TopicListBean> dataList = new LinkedList<>();
    TopicListDataAdapter topicListDataAdapter;
    int page =1;
    @Override
    public BasePresenter initPresenter() {
        return plazaPresenter;
    }

    @Override
    public void initParms(Bundle parms) {

    }

    @Override
    public int bindLayout() {
        return R.layout.activity_plaza;
    }

    @Override
    public void initView(View view) {
        tvContentName.setText("广场");
        tvTopRight.setText("发布");
        tvTopRight.setVisibility(View.VISIBLE);
        initAdvViewPage();
        initRecyclerView();
        setRefresh();
    }

    private void initRecyclerView() {
        FullyLinearLayoutManager linearLayoutManager = new FullyLinearLayoutManager(mContext);
        rvTopicListData.setLayoutManager(linearLayoutManager);
        topicListDataAdapter=new TopicListDataAdapter(R.layout.item_comm_topic_layout,dataList,PlazaActivity.this);

        rvTopicListData.setAdapter(topicListDataAdapter);
        rvTopicListData.addItemDecoration(new DividerItemDecoration(mContext,DividerItemDecoration.VERTICAL));
        rvTopicListData.setHasFixedSize(true);
        rvTopicListData.setNestedScrollingEnabled(false);
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
                            plazaPresenter.doPraise(topicListBean.getId(),uid,token);
                        }else{
                            topicListBean.setIs_praise(0);
                            int praiseNum = new Integer(topicListBean.getPraise_num());
                            praiseNum-=1;
                            topicListBean.setPraise_num(praiseNum+"");
                            plazaPresenter.cancelPraise(topicListBean.getId(),uid,token);

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
    /**
     * 初始化图片轮播图
     */
    private void initAdvViewPage() {
        bnPlazaAdv.setImageLoader(new GlideImageLoader()); //设置图片加载器
//        bnMainCommAdv.setImages(mDataList);//设置图片集合
        //设置自动轮播，默认为true
        bnPlazaAdv.isAutoPlay(true);
        //设置轮播时间
        bnPlazaAdv.setDelayTime(3000);
        //设置指示器位置（当banner模式中有指示器时）
        bnPlazaAdv.setIndicatorGravity(BannerConfig.CENTER);
        bnPlazaAdv.start();
    }
    @Override
    public View initBack() {
        return topBack;
    }

    @Override
    protected void onResume() {
        super.onResume();
        getData();
    }
    /**
     * 初始化下拉刷新
     */
    private void setRefresh() {
        prl_comm_refreshable.setOnRefreshListener(new PullRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // start refresh
                getData();

            }
        });
    }
    public void getData(){
        int uid = (Integer) SPUtils.INSTANCE.getSPValue(SPKey.SP_USER_UID_KEY, SPUtils.DATA_INT);
        String token = (String) SPUtils.INSTANCE.getSPValue(SPKey.SP_USER_TOKEN_KEY, SPUtils.DATA_STRING);
        plazaPresenter.getAdList(uid, token, "2", "5");
        plazaPresenter.getCommentTopic();
        plazaPresenter.getCommentAct();

    }
    @OnClick({R.id.tv_top_right, R.id.rl_act_button, R.id.rl_car_button, R.id.tv_topic_more,R.id.tv_bazaar_button})
    public void onViewClicked(View view) {
        Bundle bundle =new Bundle();
        switch (view.getId()) {
            case R.id.tv_top_right:
                startActivity(ReleaseTopicActivity.class);
                break;
            case R.id.rl_act_button:
                startActivity(ActListActivity.class);
                break;
            case R.id.rl_car_button:
                bundle.putInt("type",2);
                startActivity(TopicListActivity.class,bundle);
                break;
            case R.id.tv_topic_more:
                bundle.putInt("type",1);
                startActivity(TopicListActivity.class,bundle);
                break;
            case R.id.tv_bazaar_button:
                bundle.putInt("type",3);
                startActivity(TopicListActivity.class,bundle);
                break;
        }
    }

    public void getAdList(List<HomeDataBean.AdBean> data) {
        mDataList.clear();
        if (data != null) {
            mDataList.addAll(data);
            bnPlazaAdv.update(data);
        }
    }
    public void getCommentAct(CommentActBean data) {
        if (prl_comm_refreshable != null) {
            prl_comm_refreshable.setRefreshing(false);
        }
        Okhttp3Utils.getInstance().glide(mContext,data.getPic(),uivTopicPic);
        tvTopicContent.setText(data.getTitle());
    }

    public void doPraise() {
        //点赞
    }

    public void cancelPraise() {
        //取消点赞

    }

    public void getCommentTopic(List<TopicListBean> data) {
        dataList.clear();
        if(data!=null){
            dataList.addAll(data);
        }
        topicListDataAdapter.notifyDataSetChanged();
    }
}
