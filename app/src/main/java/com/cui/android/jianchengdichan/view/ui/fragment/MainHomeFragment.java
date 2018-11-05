package com.cui.android.jianchengdichan.view.ui.fragment;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextSwitcher;
import android.widget.TextView;
import android.widget.ViewSwitcher;

import com.baoyz.widget.PullRefreshLayout;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.cui.android.jianchengdichan.MyApplication;
import com.cui.android.jianchengdichan.R;
import com.cui.android.jianchengdichan.http.bean.HomeDataBean;
import com.cui.android.jianchengdichan.presenter.BasePresenter;
import com.cui.android.jianchengdichan.presenter.MainHomePresenter;
import com.cui.android.jianchengdichan.utils.LogUtils;
import com.cui.android.jianchengdichan.utils.Okhttp3Utils;
import com.cui.android.jianchengdichan.utils.SPKey;
import com.cui.android.jianchengdichan.utils.SPUtils;
import com.cui.android.jianchengdichan.utils.ToastUtil;
import com.cui.android.jianchengdichan.view.base.BaseActivity;
import com.cui.android.jianchengdichan.view.base.BaseFragment;
import com.cui.android.jianchengdichan.view.base.PermissionActivity;
import com.cui.android.jianchengdichan.view.ui.avtivity.CarGoingActivity;
import com.cui.android.jianchengdichan.view.ui.avtivity.CheckedCarActivity;
import com.cui.android.jianchengdichan.view.ui.avtivity.ConveServiceActivity;
import com.cui.android.jianchengdichan.view.ui.avtivity.InCommunityActivity;
import com.cui.android.jianchengdichan.view.ui.avtivity.LeaseCentreActivity;
import com.cui.android.jianchengdichan.view.ui.avtivity.LoginActivity;
import com.cui.android.jianchengdichan.view.ui.avtivity.MainActivity;
import com.cui.android.jianchengdichan.view.ui.avtivity.MyFitmentListActivity;
import com.cui.android.jianchengdichan.view.ui.avtivity.NoticeAcitivty;
import com.cui.android.jianchengdichan.view.ui.avtivity.ParkingLotActivity;
import com.cui.android.jianchengdichan.view.ui.avtivity.PayFeesActivity;
import com.cui.android.jianchengdichan.view.ui.avtivity.RentDatailActivity;
import com.cui.android.jianchengdichan.view.ui.avtivity.RepairsActivity;
import com.cui.android.jianchengdichan.view.ui.avtivity.ScanActivity;
import com.cui.android.jianchengdichan.view.ui.avtivity.WebViewActivity;
import com.cui.android.jianchengdichan.view.ui.customview.GlideImageLoader;
import com.cui.android.jianchengdichan.view.ui.fragment.adapter.MainRecyclerAdapter;
import com.cui.android.jianchengdichan.view.ui.fragment.adapter.MainRvCommunityAdapter;
import com.cui.android.jianchengdichan.view.ui.fragment.adapter.MainRvNewGoodsAdapter;
import com.cui.android.jianchengdichan.view.ui.fragment.adapter.MainRvYouLikeAdapter;
import com.cui.android.jianchengdichan.view.ui.fragment.adapter.adapterBean.CommunityBean;
import com.google.zxing.integration.android.IntentIntegrator;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.Unbinder;


public class MainHomeFragment extends BaseFragment {

    @BindView(R.id.iv_main_top_qrcode)
    ImageView ivMainTopQrcode;
    @BindView(R.id.et_main_top_seek)
    EditText etMainTopSeek;
    @BindView(R.id.iv_main_top_add)
    ImageView ivMainTopAdd;
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
    @BindView(R.id.iv_flash_sale)
    ImageView iv_flash_sale;
    @BindView(R.id.prl_refreshable)
    PullRefreshLayout prl_refreshable;

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
    MainHomePresenter mainHomePresenter = new MainHomePresenter();
    MainRecyclerAdapter recyclAdapter;    //附近租贷
    MainRvCommunityAdapter mainRvCommunityAdapter;  //社区管家
    MainRvNewGoodsAdapter mainRvNewGoodsAdapter; //新品上市
    MainRvYouLikeAdapter mainRvYouLikeAdapter;  //猜你喜欢
    private static MainHomeFragment instance;

    public MainHomeFragment() {
    }

    public static MainHomeFragment newInstance(Bundle bundle) {
        LogUtils.i("newInstance");
        if (instance == null) {
            synchronized (MainHomeFragment.class) {
                if (instance == null) {
                    instance = new MainHomeFragment();
                }
            }
        }
        if (bundle != null) {
            instance.setArguments(bundle);
        }
        return instance;
    }

    @SuppressLint("HandlerLeak")
    Handler noticeHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case NEWS_MESSAGE_TEXTVIEW:
                    if (tvMainNoticeNew1 != null) {
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
    public int bindLayout() {
        return R.layout.fragment_main_home;
    }

    @Override
    public void initData() {
        communityBeanList.add(new CommunityBean(R.drawable.main_rent_centre, "租贷中心"));
        communityBeanList.add(new CommunityBean(R.drawable.main_tenement_pay, "物业缴费"));
        communityBeanList.add(new CommunityBean(R.drawable.main_service, "管家服务"));
        communityBeanList.add(new CommunityBean(R.drawable.main_pwd_icon, "门禁密码"));
        communityBeanList.add(new CommunityBean(R.drawable.main_car_go_out, "车辆出行"));
        communityBeanList.add(new CommunityBean(R.drawable.main_apply_server, "装修申请"));
        communityBeanList.add(new CommunityBean(R.drawable.main_breakdown, "报事报修"));
        communityBeanList.add(new CommunityBean(R.drawable.main_shop_icon, "管家商场"));
    }

    @Override
    public void initView() {
        initAdvViewPage();
        initRecycler();
        initNotice();
        setRefresh();
    }

    @Override
    public BasePresenter initPresenter() {
        return mainHomePresenter;
    }

    @Override
    public void doBusiness() {
        mainHomePresenter.getData();

    }

    /**
     * 初始化公告
     */
    private void initNotice() {
        //公告
        tvMainNoticeNew1.setFactory(new ViewSwitcher.ViewFactory() {
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

    /**
     * 初始化recycler
     */
    private void initRecycler() {
        //社区管家
        GridLayoutManager gridLayoutManager = new GridLayoutManager(mContext, 4);
        rvMainCommunity.setLayoutManager(gridLayoutManager);
        mainRvCommunityAdapter = new MainRvCommunityAdapter(communityBeanList);
        rvMainCommunity.setAdapter(mainRvCommunityAdapter);
        rvMainCommunity.setHasFixedSize(true);
        rvMainCommunity.setNestedScrollingEnabled(false);
        mainRvCommunityAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                boolean isLogin = (boolean) SPUtils.INSTANCE.getSPValue(SPKey.SP_LOAGIN_KEY, SPUtils.DATA_BOOLEAN);
                if (isLogin) {
//            startActivity(new Intent(mContext, PayFeesActivity.class));
                } else {
                    startActivity(LoginActivity.class);

                    return;
                }
                switch (position) {
                    case 0:
                        startActivity(LeaseCentreActivity.class);

                        break;
                    case 1:
                        String com_id = (String) SPUtils.INSTANCE.getSPValue(SPKey.SP_USER_COM_ID_KEY, SPUtils.DATA_STRING);

                        if (TextUtils.isEmpty(com_id)) {
                            startActivity(InCommunityActivity.class);
                        } else {
                            startActivity(PayFeesActivity.class);
                        }
                        break;
                    case 2:
                        startActivity(ConveServiceActivity.class);

                        break;
                    case 3:
                        break;
                    case 4:
                        startActivity(ParkingLotActivity.getStartIntent(mContext));
                        break;
                    case 5:
                        startActivity(MyFitmentListActivity.class);

                        break;
                    case 6:

                        startActivity(RepairsActivity.class);

                        break;
                    case 7:
                        String phone = (String) SPUtils.INSTANCE.getSPValue(SPKey.SP_USER_SIP_NUMBER_KEY, SPUtils.DATA_STRING);
                        if (TextUtils.isEmpty(phone)) {
                            startActivity(LoginActivity.class);
                            return;
                        }
                        Intent intent = new Intent(getContext(), WebViewActivity.class);
                        Bundle bundle;
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                            bundle = ActivityOptions.makeSceneTransitionAnimation(getActivity()).toBundle();
//                            bundle.putString("data", "http://wx.szshide.shop/app/index.php?i=1&c=entry&m=ewei_shopv2&do=mobile");
                            bundle.putString("data", "http://shop.ajunigz.com/app/index.php?i=10&c=entry&m=ewei_shopv2&do=mobile&app_mobile="+phone);
                            intent.putExtras(bundle);
                            getContext().startActivity(intent);
                        } else {
                            bundle = new Bundle();
//                            bundle.putString("data", "http://wx.szshide.shop/app/index.php?i=1&c=entry&m=ewei_shopv2&do=mobile");
                            bundle.putString("data", "http://shop.ajunigz.com/app/index.php?i=10&c=entry&m=ewei_shopv2&do=mobile&app_mobile="+phone);
                            intent.putExtras(bundle);
                            getContext().startActivity(intent);
                        }
                        break;
                }
            }
        });
        if (rentDataList != null) {
            //附近租贷
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
            linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
            rvMainRent.setLayoutManager(linearLayoutManager);
            recyclAdapter = new MainRecyclerAdapter(rentDataList);
            rvMainRent.setAdapter(recyclAdapter);
            rvMainRent.setHasFixedSize(true);
            rvMainRent.setNestedScrollingEnabled(false);
            recyclAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
                @Override
                public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                    boolean isLogin = (boolean) SPUtils.INSTANCE.getSPValue(SPKey.SP_LOAGIN_KEY, SPUtils.DATA_BOOLEAN);
                    if (isLogin) {
                    } else {
                        startActivity(LoginActivity.class);
                        return;
                    }
                    HomeDataBean.RentBean rentBean = rentDataList.get(position);
                    Bundle bundle = new Bundle();
                    bundle.putString("id", rentBean.getId() + "");
                    startActivity(RentDatailActivity.class, bundle);
                }
            });
        }
        if (newGoodsBeanList != null) {
            //新品上市
            LinearLayoutManager layoutManager = new LinearLayoutManager(mContext);
            layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
            rvMainNewGoods.setLayoutManager(layoutManager);
            mainRvNewGoodsAdapter = new MainRvNewGoodsAdapter(newGoodsBeanList);
            rvMainNewGoods.setAdapter(mainRvNewGoodsAdapter);
        }
        if (youLikeBeanList != null) {
            //猜你喜欢
            GridLayoutManager gridLayoutManager2 = new GridLayoutManager(mContext, 2);
            rvYouLike.setLayoutManager(gridLayoutManager2);
            mainRvYouLikeAdapter = new MainRvYouLikeAdapter(youLikeBeanList);
            rvYouLike.setAdapter(mainRvYouLikeAdapter);
        }


    }

    /**
     * 初始化图片轮播图
     */
    private void initAdvViewPage() {
        bnMainAdv.setImageLoader(new GlideImageLoader()); //设置图片加载器
//        bnMainAdv.setImages(mDataList);//设置图片集合
        //设置自动轮播，默认为true
        bnMainAdv.isAutoPlay(true);
        //设置轮播时间
        bnMainAdv.setDelayTime(3000);
        //设置指示器位置（当banner模式中有指示器时）
        bnMainAdv.setIndicatorGravity(BannerConfig.CENTER);
        bnMainAdv.start();
    }

    /**
     * 更新数据
     *
     * @param data 首页数据
     */
    private void updateData(HomeDataBean data) {
        mDataList.clear();
        rentDataList.clear();
        newGoodsBeanList.clear();
        youLikeBeanList.clear();
        noticeList.clear();
        //广告图片
        if (data.getAd() != null) {
            mDataList.addAll(data.getAd());

        }

        //公告信息
        if (data.getNotice() != null) {
            List<HomeDataBean.NoticeBean> noticeBeans = data.getNotice();
            StringBuffer json = new StringBuffer();
            for (HomeDataBean.NoticeBean noticeBean : noticeBeans) {
                noticeList.add(noticeBean.getTitle());
                json.append(noticeBean.getTitle() + ",");
            }
            SPUtils.INSTANCE.setSPValue(SPKey.SP_HOME_DATA_NOTICE_KEY, json);
        }


        //租贷数据
        if (data.getRent() != null) {
            rentDataList.addAll(data.getRent());

        }
        //抢购数据
        if (data.getLimit_time() != null) {
            limit_time = data.getLimit_time();

        }

        //最新上市数据
        if (data.getNewgood() != null) {
            newGoodsBeanList.addAll(data.getNewgood());

        }
        //猜你喜欢数据
        if (data.getFavor() != null) {
            youLikeBeanList.addAll(data.getFavor());
        }

        bnMainAdv.update(mDataList);

        recyclAdapter.notifyDataSetChanged();
        if (noticeList != null) {
            tvMainNoticeNew1.setText(noticeList.get(0));
            notice(noticeList);
        }

//        mainRvNewGoodsAdapter.notifyDataSetChanged();
//        mainRvYouLikeAdapter.notifyDataSetChanged();
    }

    /**
     * 初始化下拉刷新
     */
    private void setRefresh() {

        // listen refresh event
        prl_refreshable.setOnRefreshListener(new PullRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // start refresh
                mainHomePresenter.getData();

            }
        });

// refresh complete
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

    @OnClick({R.id.iv_main_top_qrcode, R.id.iv_main_top_add, R.id.tv_rent_more, R.id.ll_new_notice, R.id.tv_main_updata, R.id.iv_flash_sale})
    public void onViewClicked(View view) {
        boolean isLogin = (boolean) SPUtils.INSTANCE.getSPValue(SPKey.SP_LOAGIN_KEY, SPUtils.DATA_BOOLEAN);
        if (isLogin) {
//            startActivity(new Intent(mContext, PayFeesActivity.class));
        } else {
            startActivity(LoginActivity.class);
            return;
        }
        switch (view.getId()) {
            case R.id.iv_main_top_qrcode:
                LogUtils.i("iv_main_top_qrcode");
                BaseActivity activity = (BaseActivity) getActivity();
                activity.checkPermission(new PermissionActivity.CheckPermListener() {
                    @Override
                    public void superPermission() {
                        new IntentIntegrator(getActivity())
                                .setOrientationLocked(false)
                                .setCaptureActivity(ScanActivity.class) // 设置自定义的activity是ScanActivity
                                .initiateScan(); // 初始化扫描
                    }
                }, R.string.perm_tip, Manifest.permission.CAMERA);

                break;
            case R.id.iv_main_top_add:
                LogUtils.i("iv_main_top_add");
                startActivity(InCommunityActivity.class);

                break;
            case R.id.iv_join:
                LogUtils.i("iv_join");
                ToastUtil.makeToast("活动还没开始");
                break;
            case R.id.tv_rent_more:
                startActivity(LeaseCentreActivity.class);

                break;
            case R.id.ll_new_notice:
                startActivity(NoticeAcitivty.class);

                break;
            case R.id.tv_main_updata:
                mainHomePresenter.getAnotherBatch();

                break;
            case R.id.iv_flash_sale:
                String phone = (String) SPUtils.INSTANCE.getSPValue(SPKey.SP_USER_SIP_NUMBER_KEY, SPUtils.DATA_STRING);
                if (TextUtils.isEmpty(phone)) {
                    startActivity(LoginActivity.class);
                    return;
                }
                Intent intent = new Intent(getContext(), WebViewActivity.class);
                Bundle bundle ;
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    bundle = ActivityOptions.makeSceneTransitionAnimation(getActivity()).toBundle();
//                        bundle.putString("data", "http://wx.szshide.shop/app/index.php?i=1&c=entry&m=ewei_shopv2&do=mobile");
                    bundle.putString("data", "http://shop.ajunigz.com/app/index.php?i=10&c=entry&m=ewei_shopv2&do=mobile&app_mobile="+phone);
                    intent.putExtras(bundle);
                    getContext().startActivity(intent);
                }else{
                    bundle=new Bundle();
                    bundle.putString("data", "http://shop.ajunigz.com/app/index.php?i=10&c=entry&m=ewei_shopv2&do=mobile&app_mobile="+phone);
//                        bundle.putString("data", "http://wx.szshide.shop/app/index.php?i=1&c=entry&m=ewei_shopv2&do=mobile");
                    intent.putExtras(bundle);
                    getContext().startActivity(intent);
                }
                break;
        }
    }


    private void initRimit_time() {
        if (limit_time != null) {
            Okhttp3Utils.getInstance().glide(mContext, limit_time.getThumb(), iv_flash_sale);
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

    /**
     * 获取首页数据
     *
     * @param data
     */
    public void getData(HomeDataBean data) {
        if (prl_refreshable != null) {
            prl_refreshable.setRefreshing(false);
        }
        updateData(data);
    }

    /**
     * 商品
     *
     * @param data
     */
    public void getAnotherBatch(List<HomeDataBean.NewgoodBean> data) {
        newGoodsBeanList.clear();
        if (data != null && data.size() > 0) {
            newGoodsBeanList.addAll(data);
            mainRvNewGoodsAdapter.notifyDataSetChanged();
        }
    }

    @OnClick(R.id.rl_checked_car)
    public void onCheckedCar() {
//        startActivity(CheckedCarActivity.getStartIntent(mContext, "0"));
        startActivity(ParkingLotActivity.getStartIntent(mContext));

    }

    @OnClick(R.id.rl_nearby_car)
    public void onNearbyCar() {
        startActivity(ParkingLotActivity.getStartIntent(mContext));
    }
}
