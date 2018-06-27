package com.cui.android.jianchengdichan.view.ui.fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextSwitcher;
import android.widget.TextView;
import android.widget.ViewSwitcher;

import com.baoyz.widget.PullRefreshLayout;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.cui.android.jianchengdichan.MyApplication;
import com.cui.android.jianchengdichan.R;
import com.cui.android.jianchengdichan.http.bean.CommentActBean;
import com.cui.android.jianchengdichan.http.bean.HomeDataBean;
import com.cui.android.jianchengdichan.http.bean.NoticeThreelistBean;
import com.cui.android.jianchengdichan.http.bean.TopicListBean;
import com.cui.android.jianchengdichan.presenter.BasePresenter;
import com.cui.android.jianchengdichan.presenter.MainCommPresenter;
import com.cui.android.jianchengdichan.utils.LogUtils;
import com.cui.android.jianchengdichan.utils.Okhttp3Utils;
import com.cui.android.jianchengdichan.utils.SPKey;
import com.cui.android.jianchengdichan.utils.SPUtils;
import com.cui.android.jianchengdichan.view.base.BaseFragment;
import com.cui.android.jianchengdichan.view.ui.adapter.TopicListDataAdapter;
import com.cui.android.jianchengdichan.view.ui.avtivity.ActListActivity;
import com.cui.android.jianchengdichan.view.ui.avtivity.CommentActivity;
import com.cui.android.jianchengdichan.view.ui.avtivity.ConveColumnActivity;
import com.cui.android.jianchengdichan.view.ui.avtivity.LeaseCentreActivity;
import com.cui.android.jianchengdichan.view.ui.avtivity.LoginActivity;
import com.cui.android.jianchengdichan.view.ui.avtivity.NoticeAcitivty;
import com.cui.android.jianchengdichan.view.ui.avtivity.PayFeesActivity;
import com.cui.android.jianchengdichan.view.ui.avtivity.PlazaActivity;
import com.cui.android.jianchengdichan.view.ui.avtivity.RepairsActivity;
import com.cui.android.jianchengdichan.view.ui.customview.FullyLinearLayoutManager;
import com.cui.android.jianchengdichan.view.ui.customview.GlideImageLoader;
import com.cui.android.jianchengdichan.view.ui.customview.SpaceItemDecoration;
import com.cui.android.jianchengdichan.view.ui.customview.UIImageView;
import com.cui.android.jianchengdichan.view.ui.fragment.adapter.CommRvAdapter;
import com.cui.android.jianchengdichan.view.ui.fragment.adapter.CommRvBottomAdapter;
import com.cui.android.jianchengdichan.view.ui.fragment.adapter.adapterBean.CommRvBean;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;


public class MainCommFragment extends BaseFragment {


    @BindView(R.id.tv_comm_top_qrcode)
    TextView tvCommTopQrcode;
    @BindView(R.id.et_comm_top_seek)
    EditText etCommTopSeek;
    @BindView(R.id.iv_comm_top_add)
    ImageView ivCommTopAdd;
    @BindView(R.id.rl_comm_top)
    RelativeLayout rlCommTop;
    @BindView(R.id.bn_main_comm_adv)
    Banner bnMainCommAdv;
    @BindView(R.id.tv_main_comm_notice_new)
    TextSwitcher tvMainCommNoticeNew;
    @BindView(R.id.rv_comm_top)
    RecyclerView rvCommTop;
    @BindView(R.id.iv_comm_con)
    ImageView ivCommCon;
    @BindView(R.id.rv_comm)
    RecyclerView rvComm;
    @BindView(R.id.prl_comm_refreshable)
    PullRefreshLayout prl_comm_refreshable;
    @BindView(R.id.ll_comm_notice)
    LinearLayout llCommNotice;
    @BindView(R.id.uiv_comm_act_more)
    TextView uivCommActMore;
    @BindView(R.id.uiv_comm_act_pic)
    UIImageView uivCommActPic;
    @BindView(R.id.tv_comm_act_like)
    TextView tvCommActLike;
    @BindView(R.id.tv_comm_act_topic)
    TextView tvCommActTopic;
    @BindView(R.id.tv_comm_act_time)
    TextView tvCommActTime;
    @BindView(R.id.uiv_comm_topic_more)
    TextView uivCommTopicMore;
    @BindView(R.id.rv_comm_topic_data)
    RecyclerView rvCommTopicData;
    @BindView(R.id.rl_plaza_go)
    RelativeLayout rl_plaza_go;

    private List<CommRvBean> dataTop = new ArrayList<>();
    private List<CommRvBean> dataRv = new ArrayList<>();
    private List<HomeDataBean.AdBean> mDataList = new ArrayList<>();//轮播图数据
    List<TopicListBean> topDataList = new ArrayList<>();//活动数据
    private List<String> noticeList = new ArrayList<>();//公告数据
    public static final int NEWS_MESSAGE_TEXTVIEW = 300;//通知公告信息
    MainCommPresenter mainCommPresenter = new MainCommPresenter();
    private int index = 0;//textview上下滚动下标
    TopicListDataAdapter topicListDataAdapter;
    List<HomeDataBean.AdBean> dataList = new ArrayList<>();
    List<String> notifi = new ArrayList();
    private static MainCommFragment instance;
    public MainCommFragment() {
    }
    public static MainCommFragment newInstance(Bundle bundle) {
        LogUtils.i("newInstance");
        if (instance==null){
            synchronized(MainCommFragment.class){
                if(instance==null){
                    instance=new MainCommFragment();
                }
            }
        }
        if(bundle!=null){
            instance.setArguments(bundle);
        }
        return instance;
    }

    @Override
    public int bindLayout() {
        return R.layout.fragment_main_comm;
    }

    @Override
    public void initData() {
        dataTop.add(new CommRvBean(R.drawable.comm_express_icon, "快递站点", "集多项服务于一体"));
        dataTop.add(new CommRvBean(R.drawable.shop_comm_icon, "便利商店", "生活超市全程服务"));
        dataTop.add(new CommRvBean(R.drawable.homemaking_icon, "家政保洁", "专业成就好的品牌"));
        dataTop.add(new CommRvBean(R.drawable.comm_canteen_icon, "饿了食堂", "各色菜系人你选择"));
        dataTop.add(new CommRvBean(R.drawable.nurse_icon, "护理保健", "专业成就好的品牌"));
        dataTop.add(new CommRvBean(R.drawable.train_icon, "个人培训", "生活超市全程服务"));

        dataRv.add(new CommRvBean(R.drawable.comm_stop_car, "我要停车", "线上支付更快捷"));
        dataRv.add(new CommRvBean(R.drawable.comm_lease_icon, "租赁中心", "超多房源任你挑选"));
        dataRv.add(new CommRvBean(R.drawable.comm_carport_icon, "车位管理", "查询并管理车位"));
        dataRv.add(new CommRvBean(R.drawable.comm_pay_icon, "物业缴费", "每月线上缴费服务"));
        dataRv.add(new CommRvBean(R.drawable.comm_black_list_icon, "黑白名单", "管理分组和下发"));
        dataRv.add(new CommRvBean(R.drawable.comm_more_icon, "报事报修", "线上预约更方便"));
    }

    @Override
    public void initView() {
        initAdvViewPage();
        initRecyclerView();
        setRefresh();
        initNotice();
    }

    @Override
    public BasePresenter initPresenter() {
        return mainCommPresenter;
    }

    @Override
    public void doBusiness() {
        getData();

    }

    @SuppressLint("HandlerLeak")
    Handler noticeHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case NEWS_MESSAGE_TEXTVIEW:
                    if (tvMainCommNoticeNew != null) {
                        tvMainCommNoticeNew.setText(noticeList.get(index));
                        index++;
                        if (index == noticeList.size()) {
                            index = 0;
                        }
                    }

                    break;
                default:
                    break;
            }
        }
    };



    /**
     * 初始化公告
     */
    public void initNotice() {
        //公告
        tvMainCommNoticeNew.setFactory(new ViewSwitcher.ViewFactory() {
            @Override
            public View makeView() {
                TextView textView = new TextView(MyApplication.getAppContext());
                textView.setSingleLine();
                textView.setTextSize(14);//字号
                textView.setTextColor(Color.parseColor("#000000"));
                textView.setEllipsize(TextUtils.TruncateAt.END);
                FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(
                        ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                params.gravity = Gravity.CENTER_VERTICAL;
                textView.setLayoutParams(params);
                return textView;
            }
        });
    }

    ;

    public void getData() {
        int uid = (Integer) SPUtils.INSTANCE.getSPValue(SPKey.SP_USER_UID_KEY, SPUtils.DATA_INT);
        String token = (String) SPUtils.INSTANCE.getSPValue(SPKey.SP_USER_TOKEN_KEY, SPUtils.DATA_STRING);
        mainCommPresenter.getAdList(uid, token, "2", "2");
        mainCommPresenter.getNoticeList(uid, token, "2");
        mainCommPresenter.getCommentAct();
        mainCommPresenter.getCommentTopic();
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

    private void initRecyclerView() {
        GridLayoutManager gridLayoutManager = new GridLayoutManager(mContext, 2);
        rvCommTop.setLayoutManager(gridLayoutManager);
        rvCommTop.addItemDecoration(new SpaceItemDecoration(0));
        CommRvAdapter commRvAdapter = new CommRvAdapter(dataTop);
        rvCommTop.setAdapter(commRvAdapter);
        rvCommTop.setHasFixedSize(true);
        rvCommTop.setNestedScrollingEnabled(false);
        commRvAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                Bundle bundle = new Bundle();
                bundle.putInt("type", position + 1);
                Intent intent = new Intent(mContext, ConveColumnActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        GridLayoutManager layoutManager = new GridLayoutManager(mContext, 2);
        rvComm.setLayoutManager(layoutManager);
//        rvComm.addItemDecoration(new GridDivider(getActivity(), 10
//                , this.getResources().getColor(R.color.like_back_col)));
        CommRvBottomAdapter commRvAdapter1 = new CommRvBottomAdapter(dataRv);
        rvComm.setAdapter(commRvAdapter1);
        rvComm.setHasFixedSize(true);
        rvComm.setNestedScrollingEnabled(false);
        commRvAdapter1.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                boolean isLogin = (boolean) SPUtils.INSTANCE.getSPValue(SPKey.SP_LOAGIN_KEY, SPUtils.DATA_BOOLEAN);
                if (isLogin) {
//            startActivity(new Intent(mContext, PayFeesActivity.class));
                } else {
                    startActivity(LoginActivity.class);
                    return;
                }
                switch (position) {
                    case 0:
                        break;
                    case 1:
                        startActivity(LeaseCentreActivity.class);
                        break;
                    case 2:
                        break;
                    case 3:
                        startActivity(PayFeesActivity.class);
                        break;
                    case 4:
                        break;
                    case 5:
                        startActivity(RepairsActivity.class);
                        break;
                }
            }
        });
        FullyLinearLayoutManager linearLayoutManager = new FullyLinearLayoutManager(mContext);
        rvCommTopicData.setLayoutManager(linearLayoutManager);
        topicListDataAdapter = new TopicListDataAdapter(R.layout.item_comm_topic_layout, topDataList,mActivity);

        rvCommTopicData.setAdapter(topicListDataAdapter);
        rvCommTopicData.setHasFixedSize(true);
        rvCommTopicData.setNestedScrollingEnabled(false);
        rvCommTopicData.addItemDecoration(new DividerItemDecoration(mContext, DividerItemDecoration.VERTICAL));
        topicListDataAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()) {
                    case R.id.tv_topic_like:
                        TopicListBean topicListBean = topDataList.get(position);
                        int uid = (Integer) SPUtils.INSTANCE.getSPValue(SPKey.SP_USER_UID_KEY, SPUtils.DATA_INT);
                        String token = (String) SPUtils.INSTANCE.getSPValue(SPKey.SP_USER_TOKEN_KEY, SPUtils.DATA_STRING);
                        if (topicListBean.getIs_praise() == 0) {
                            topicListBean.setIs_praise(1);
                            int praiseNum = new Integer(topicListBean.getPraise_num());
                            praiseNum += 1;
                            topicListBean.setPraise_num(praiseNum + "");
                            mainCommPresenter.doPraise(topicListBean.getId(), uid, token);
                        } else {
                            topicListBean.setIs_praise(0);
                            int praiseNum = new Integer(topicListBean.getPraise_num());
                            praiseNum -= 1;
                            topicListBean.setPraise_num(praiseNum + "");
                            mainCommPresenter.cancelPraise(topicListBean.getId(), uid, token);

                        }
                        topicListDataAdapter.notifyItemChanged(position, "type");

                        break;
                    case R.id.tv_comm_act_topic:
                        Bundle bundle = new Bundle();
                        bundle.putSerializable("data", topDataList.get(position));
                        startActivity(CommentActivity.class,bundle);
                        break;

                }
            }
        });
        topicListDataAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Bundle bundle = new Bundle();
                bundle.putSerializable("data", topDataList.get(position));
                startActivity(CommentActivity.class,bundle);

            }
        });
    }

    /**
     * 初始化图片轮播图
     */
    private void initAdvViewPage() {
        bnMainCommAdv.setImageLoader(new GlideImageLoader()); //设置图片加载器
//        bnMainCommAdv.setImages(mDataList);//设置图片集合
        //设置自动轮播，默认为true
        bnMainCommAdv.isAutoPlay(true);
        //设置轮播时间
        bnMainCommAdv.setDelayTime(3000);
        //设置指示器位置（当banner模式中有指示器时）
        bnMainCommAdv.setIndicatorGravity(BannerConfig.CENTER);
        bnMainCommAdv.start();
    }

    /**
     * 获取广告
     *
     * @param data 广告数据
     */
    public void getAdList(List<HomeDataBean.AdBean> data) {
        mDataList.clear();
        if (data != null) {
            mDataList.addAll(data);
            bnMainCommAdv.update(data);
        }
    }

    /**
     * 获取广播
     *
     * @param data 广播数据
     */
    public void getNoticeList(List<NoticeThreelistBean> data) {
        if (prl_comm_refreshable != null) {
            prl_comm_refreshable.setRefreshing(false);
        }
        for (NoticeThreelistBean bean : data) {
            noticeList.add(bean.getTitle());
        }
        notice(noticeList);

    }

    private void notice(final List<String> list) {
        new Thread() {
            @Override
            public void run() {
                //LogUtil.i("-------------------------------" + newsList.size());
                while (index < list.size()) {
                    synchronized (this) {
                        noticeHandler.sendEmptyMessage(NEWS_MESSAGE_TEXTVIEW);
                        SystemClock.sleep(5000);//每隔4秒滚动一次
                    }
                }
            }
        }.start();
    }

    @OnClick({R.id.ll_comm_notice, R.id.rl_plaza_go, R.id.uiv_comm_act_more, R.id.tv_comm_act_like, R.id.tv_comm_act_topic, R.id.uiv_comm_topic_more})
    public void onViewClicked(View view) {
        switch (view.getId()) {


            case R.id.rl_plaza_go:  //去广场界面
            startActivity(PlazaActivity.class);
                break;
            case R.id.uiv_comm_act_more://去活动列表界面
                startActivity(ActListActivity.class);
                break;
            case R.id.tv_comm_act_like:
                break;
            case R.id.tv_comm_act_topic:
                break;
            case R.id.uiv_comm_topic_more: //去广场界面
                startActivity(PlazaActivity.class);
                break;
            case R.id.ll_comm_notice://去公告界面
                startActivity(NoticeAcitivty.class);
                break;
        }
    }

    /**
     * 促销活动
     *
     * @param data 促销活动数据
     */
    public void getCommentAct(CommentActBean data) {
        if (data != null) {
            Okhttp3Utils.getInstance().glide(mContext, data.getPic(), uivCommActPic);
            tvCommActTime.setText(data.getCreate_time());
        }

    }

    /**
     * 话题数据
     *
     * @param data 话题数据
     */
    public void getCommentTopic(List<TopicListBean> data) {
        topDataList.clear();
        if (data != null) {
            topDataList.addAll(data);
        }
        topicListDataAdapter.notifyDataSetChanged();
    }

    public void doPraise() {

    }

    public void cancelPraise() {

    }
}
