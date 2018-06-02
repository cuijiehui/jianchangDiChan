package com.cui.android.jianchengdichan.view.ui.Fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextSwitcher;
import android.widget.TextView;
import android.widget.ViewSwitcher;

import com.bumptech.glide.Glide;
import com.cui.android.jianchengdichan.MyApplication;
import com.cui.android.jianchengdichan.R;
import com.cui.android.jianchengdichan.http.bean.HomeDataBean;
import com.cui.android.jianchengdichan.presenter.MainHomePresenter;
import com.cui.android.jianchengdichan.utils.LogUtils;
import com.cui.android.jianchengdichan.utils.SPKey;
import com.cui.android.jianchengdichan.utils.SPUtils;
import com.cui.android.jianchengdichan.utils.ToastUtil;
import com.cui.android.jianchengdichan.view.interfaces.IBaseView;
import com.cui.android.jianchengdichan.view.ui.Fragment.Adapter.AdapterBean.CommunityBean;
import com.cui.android.jianchengdichan.view.ui.Fragment.Adapter.MainRecyclerAdapter;
import com.cui.android.jianchengdichan.view.ui.Fragment.Adapter.MainRvCommunityAdapter;
import com.cui.android.jianchengdichan.view.ui.Fragment.Adapter.MainRvNewGoodsAdapter;
import com.cui.android.jianchengdichan.view.ui.Fragment.Adapter.MainRvYouLikeAdapter;
import com.cui.android.jianchengdichan.view.ui.Fragment.Adapter.interfaces.OnRecyclerViewItemClickListener;
import com.cui.android.jianchengdichan.view.ui.InCommunityActivity;
import com.cui.android.jianchengdichan.view.ui.LeaseCentreActivity;
import com.cui.android.jianchengdichan.view.ui.LoginActivity;
import com.cui.android.jianchengdichan.view.ui.MainActivity;
import com.cui.android.jianchengdichan.view.ui.PayFeesActivity;
import com.cui.android.jianchengdichan.view.ui.RentDatailActivity;
import com.cui.android.jianchengdichan.view.ui.RepairsActivity;
import com.cui.android.jianchengdichan.view.ui.ScanActivity;
import com.cui.android.jianchengdichan.view.ui.customview.GlideImageLoader;
import com.cui.android.jianchengdichan.view.ui.customview.RefreshableView;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


public class MainHomeFragment extends Fragment implements IBaseView, OnRecyclerViewItemClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    @BindView(R.id.iv_main_top_qrcode)
    ImageView ivMainTopQrcode;
    @BindView(R.id.et_main_top_seek)
    EditText etMainTopSeek;
    @BindView(R.id.iv_main_top_add)
    ImageView ivMainTopAdd;
    @BindView(R.id.rv_main_refreshable)
    RefreshableView rvMainRefreshable;
    @BindView(R.id.bn_main_adv)
    Banner bnMainAdv;
    @BindView(R.id.tv_main_notice_new)
    TextSwitcher tvMainNoticeNew1;
    @BindView(R.id.rv_main_rent)
    RecyclerView rvMainRent;
    @BindView(R.id.rv_main_community)
    RecyclerView rvMainCommunity;
    @BindView(R.id.iv_rent_icon)
    ImageView ivRentIcon;
    @BindView(R.id.iv_main_community_icon)
    ImageView ivMainCommunityIcon;
    @BindView(R.id.iv_main_stop_car_icon)
    ImageView ivMainStopCarIcon;
    @BindView(R.id.iv_main_new_goods_icon)
    ImageView ivMainNewGoodsIcon;
    @BindView(R.id.rv_main_new_goods)
    RecyclerView rvMainNewGoods;
    @BindView(R.id.iv_main_you_like_icon)
    ImageView ivMainYouLikeIcon;
    @BindView(R.id.rv_you_like)
    RecyclerView rvYouLike;
    @BindView(R.id.rl_main_top)
    RelativeLayout rlMainTop;
    @BindView(R.id.iv_limit_icon)
    ImageView ivLimitIcon;
    @BindView(R.id.tv_time_h_1)
    TextView tvTimeH1;
    @BindView(R.id.tv_time_h_2)
    TextView tvTimeH2;
    @BindView(R.id.tv_time_m_1)
    TextView tvTimeM1;
    @BindView(R.id.tv_time_m_2)
    TextView tvTimeM2;
    @BindView(R.id.tv_time_s_1)
    TextView tvTimeS1;
    @BindView(R.id.tv_time_s_2)
    TextView tvTimeS2;
    @BindView(R.id.iv_join)
    ImageView ivJoin;
    private int index = 0;//textview上下滚动下标
    public static final int NEWS_MESSAGE_TEXTVIEW = 300;//通知公告信息

    private Unbinder unbinder;
    private List<HomeDataBean.AdBean> mDataList = new ArrayList<>();//轮播图数据
    private List<HomeDataBean.RentBean> rentDataList = new ArrayList<>();//附近租贷数据
    private List<String> noticeList = new ArrayList<>();//公告数据
    private List<CommunityBean> communityBeanList = new ArrayList<>();//社区管家数据
    private List<HomeDataBean.NewgoodBean> newGoodsBeanList = new ArrayList<>();//最新产品数据
    private List<HomeDataBean.FavorBean> youLikeBeanList = new ArrayList<>();//猜你喜欢数据
    private HomeDataBean.LimitTimeBean limit_time;//抢购数据
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    MainHomePresenter mainHomePresenter;
    MainRecyclerAdapter recyclAdapter;    //附近租贷
    MainRvCommunityAdapter mainRvCommunityAdapter;  //社区管家
    MainRvNewGoodsAdapter mainRvNewGoodsAdapter; //新品上市
    MainRvYouLikeAdapter mainRvYouLikeAdapter;  //猜你喜欢

    public MainHomeFragment() {
        // Required empty public constructor
    }


    public static MainHomeFragment newInstance(String param1, String param2) {
        LogUtils.i("newInstance");
        MainHomeFragment fragment = new MainHomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LogUtils.i("onCreate");
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        mainHomePresenter = new MainHomePresenter();
        mainHomePresenter.attachView(this);
        mainHomePresenter.setTransformer(setThread());
        communityBeanList.add(new CommunityBean(R.drawable.main_rent_centre, "租贷中心"));
        communityBeanList.add(new CommunityBean(R.drawable.main_tenement_pay, "物业缴费"));
        communityBeanList.add(new CommunityBean(R.drawable.main_service, "便民服务"));
        communityBeanList.add(new CommunityBean(R.drawable.main_pwd_icon, "门禁密码"));
        communityBeanList.add(new CommunityBean(R.drawable.main_car_go_out, "车辆出行"));
        communityBeanList.add(new CommunityBean(R.drawable.main_apply_server, "申请服务"));
        communityBeanList.add(new CommunityBean(R.drawable.main_breakdown, "故障处理"));
        communityBeanList.add(new CommunityBean(R.drawable.main_more_button, "更多"));


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        LogUtils.i("onCreateView");
        View view = inflater.inflate(R.layout.fragment_main_home, container, false);
        unbinder = ButterKnife.bind(this, view);
        mainHomePresenter.getData();
        //社区管家
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 4);
        rvMainCommunity.setLayoutManager(gridLayoutManager);
        mainRvCommunityAdapter = new MainRvCommunityAdapter(communityBeanList, this);
        rvMainCommunity.setAdapter(mainRvCommunityAdapter);
        //公告
        tvMainNoticeNew1.setFactory(new ViewSwitcher.ViewFactory() {
            @Override
            public View makeView() {
                TextView textView = new TextView(MyApplication.getAppContext());
                textView.setSingleLine();
                textView.setTextSize(16);//字号
                textView.setTextColor(Color.parseColor("#ff3333"));
                textView.setEllipsize(TextUtils.TruncateAt.END);
                FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(
                        ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                params.gravity = Gravity.CENTER_VERTICAL;
                textView.setLayoutParams(params);
                return textView;
            }
        });
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setRefresh();

    }

    @Override
    public void onResume() {
        super.onResume();
    }

    /**
     * 初始化recycler
     */
    private void initRecycler() {
        if (rentDataList != null && rentDataList.size() != 0) {
            //附近租贷
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
            linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
            rvMainRent.setLayoutManager(linearLayoutManager);
            recyclAdapter = new MainRecyclerAdapter(rentDataList, new OnRecyclerViewItemClickListener() {
                @Override
                public void onItemClick(View view, int position) {
                    HomeDataBean.RentBean rentBean = rentDataList.get(position);
                    Intent intent = new Intent(getContext() , RentDatailActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("id",rentBean.getId()+"");
                    intent.putExtras(bundle);
                    getContext().startActivity(intent);
                }

                @Override
                public void onItemLongClick(View view, int position) {

                }
            });
            rvMainRent.setAdapter(recyclAdapter);
        }
        if (newGoodsBeanList != null && newGoodsBeanList.size() != 0) {
            //新品上市
            LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
            layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
            rvMainNewGoods.setLayoutManager(layoutManager);
            mainRvNewGoodsAdapter = new MainRvNewGoodsAdapter(newGoodsBeanList);
            rvMainNewGoods.setAdapter(mainRvNewGoodsAdapter);
        }
        if (youLikeBeanList != null && youLikeBeanList.size() != 0) {
            //猜你喜欢
            LinearLayoutManager layoutManager1 = new LinearLayoutManager(getContext());
            rvYouLike.setLayoutManager(layoutManager1);
            mainRvYouLikeAdapter = new MainRvYouLikeAdapter(youLikeBeanList);
            rvYouLike.setAdapter(mainRvYouLikeAdapter);
        }


    }

    /**
     * 初始化图片轮播图
     */
    private void initAdvViewPage() {
        if (mDataList == null || mDataList.size() == 0) {
            return;
        }
        bnMainAdv.setImageLoader(new GlideImageLoader()); //设置图片加载器
        bnMainAdv.setImages(mDataList);//设置图片集合
        //设置自动轮播，默认为true
        bnMainAdv.isAutoPlay(true);
        //设置轮播时间
        bnMainAdv.setDelayTime(3000);
        //设置指示器位置（当banner模式中有指示器时）
        bnMainAdv.setIndicatorGravity(BannerConfig.CENTER);
        bnMainAdv.start();
    }

    private void updataData(HomeDataBean data) {
        mDataList.clear();
        rentDataList.clear();
        newGoodsBeanList.clear();
        youLikeBeanList.clear();
        noticeList.clear();
        //广告图片
        mDataList = data.getAd();
        //公告信息
        List<HomeDataBean.NoticeBean> noticeBeans = data.getNotice();
        for (HomeDataBean.NoticeBean noticeBean : noticeBeans) {
            noticeList.add(noticeBean.getTitle());
        }
        notice(noticeList);
        //租贷数据
        rentDataList = data.getRent();
        //抢购数据
        limit_time = data.getLimit_time();
        //最新上市数据
        newGoodsBeanList = data.getNewgood();
        //猜你喜欢数据
        youLikeBeanList = data.getFavor();


    }

    /**
     * 初始化下拉刷新
     */
    private void setRefresh() {
        rvMainRefreshable.setOnRefreshListener(new RefreshableView.PullToRefreshListener() {
            @Override
            public void onRefresh() {
                LogUtils.i("setOnRefreshListener");
//                TimerTask task = new TimerTask() {
//                    @Override
//                    public void run() {
//                        /**
//                         *要执行的操作
//                         */
//                        rvMainRefreshable.finishRefreshing();
//                    }
//                };
//                Timer timer = new Timer();
//                timer.schedule(task, 3000);//3秒后执行TimeTask的run方法
                mainHomePresenter.getData();
            }
        }, 1);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onStart() {
        super.onStart();
        if (bnMainAdv != null) {
            bnMainAdv.startAutoPlay();
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        if (bnMainAdv != null) {
            bnMainAdv.stopAutoPlay();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult intentResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (intentResult != null) {
            if (intentResult.getContents() == null) {
                LogUtils.e("intentResult.getContents() == null");
            } else {
                // ScanResult 为 获取到的字符串
                String ScanResult = intentResult.getContents();
                LogUtils.e("ScanResult");
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    @OnClick({R.id.iv_main_top_qrcode, R.id.iv_main_top_add, R.id.iv_join,R.id.tv_rent_more})
    public void onViewClicked(View view) {
        boolean isLogin = (boolean) SPUtils.INSTANCE.getSPValue(SPKey.SP_LOAGIN_KEY, SPUtils.DATA_BOOLEAN);
        if (isLogin) {
//            startActivity(new Intent(getContext(), PayFeesActivity.class));
        } else {
            startActivity(new Intent(getContext(), LoginActivity.class));
            return;
        }
        switch (view.getId()) {
            case R.id.iv_main_top_qrcode:
                LogUtils.i("iv_main_top_qrcode");
                new IntentIntegrator(getActivity())
                        .setOrientationLocked(false)
                        .setCaptureActivity(ScanActivity.class) // 设置自定义的activity是ScanActivity
                        .initiateScan(); // 初始化扫描
                break;
            case R.id.iv_main_top_add:
                LogUtils.i("iv_main_top_add");
                startActivity(new Intent(getContext(), InCommunityActivity.class));
                break;
            case R.id.iv_join:
                LogUtils.i("iv_join");
                ToastUtil.makeToast("活动还没开始");
                break;
            case R.id.tv_rent_more:
                startActivity(new Intent(getContext(), LeaseCentreActivity.class));

                break;
        }
    }

    @Override
    public void onItemClick(View view, int position) {
        LogUtils.i("onItemClick=position=" + position);
        boolean isLogin = (boolean) SPUtils.INSTANCE.getSPValue(SPKey.SP_LOAGIN_KEY, SPUtils.DATA_BOOLEAN);
        if (isLogin) {
//            startActivity(new Intent(getContext(), PayFeesActivity.class));
        } else {
            startActivity(new Intent(getContext(), LoginActivity.class));
            return;
        }
        switch (position) {
            case 0:
                startActivity(new Intent(getContext(), LeaseCentreActivity.class));
                break;
            case 1:
                startActivity(new Intent(getContext(), PayFeesActivity.class));
                break;
            case 2:
                break;
            case 3:
                break;
            case 4:
                break;
            case 5:
                break;
            case 6:

                startActivity(new Intent(getContext(), RepairsActivity.class));

                break;
            case 7:
                MainActivity mainActivity =(MainActivity)getActivity();
                mainActivity.vpMainPager.setCurrentItem(1);
                break;
        }
    }

    @Override
    public void onItemLongClick(View view, int position) {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showToast(String msg) {

    }

    @Override
    public void showErr() {

    }

    public <T> ObservableTransformer<T, T> setThread() {
        return new ObservableTransformer<T, T>() {
            @Override
            public ObservableSource<T> apply(Observable<T> upstream) {
                return upstream.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
            }
        };
    }

    private void updataAdvView() {

        initNotice();
        initAdvViewPage();
        initRecycler();
        initRimit_time();
    }

    private void initRimit_time() {
//        ivLimitIcon
        //rentDataList
        if (limit_time != null) {
            Glide.with(MyApplication.getAppContext()).load(limit_time.getThumb()).into(ivLimitIcon);
        }

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

    @SuppressLint("HandlerLeak")
    Handler noticeHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case NEWS_MESSAGE_TEXTVIEW:
                    if(tvMainNoticeNew1!=null){
                        tvMainNoticeNew1.setText(noticeList.get(index));
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

    @Override
    public void onFailure(String msg) {
        showToast("网络异常，请稍后重试");

    }

    @Override
    public void onError() {
        showToast("网络异常，请稍后重试");

    }

    private void initNotice() {
        if (noticeList == null || noticeList.size() == 0) {
            return;
        }
        tvMainNoticeNew1.setText(noticeList.get(0));
    }

    public void getData(HomeDataBean data) {
        if (rvMainRefreshable != null) {
            rvMainRefreshable.finishRefreshing();
        }
        updataData(data);
        updataAdvView();
    }


}
