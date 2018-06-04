package com.cui.android.jianchengdichan.view.ui.Fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
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

import com.cui.android.jianchengdichan.MyApplication;
import com.cui.android.jianchengdichan.R;
import com.cui.android.jianchengdichan.http.bean.HomeDataBean;
import com.cui.android.jianchengdichan.http.bean.NoticeThreelistBean;
import com.cui.android.jianchengdichan.presenter.MainCommPresenter;
import com.cui.android.jianchengdichan.utils.LogUtils;
import com.cui.android.jianchengdichan.utils.SPKey;
import com.cui.android.jianchengdichan.utils.SPUtils;
import com.cui.android.jianchengdichan.view.interfaces.IBaseView;
import com.cui.android.jianchengdichan.view.ui.Fragment.Adapter.CommRvAdapter;
import com.cui.android.jianchengdichan.view.ui.Fragment.Adapter.interfaces.OnRecyclerViewItemClickListener;
import com.cui.android.jianchengdichan.view.ui.LeaseCentreActivity;
import com.cui.android.jianchengdichan.view.ui.LoginActivity;
import com.cui.android.jianchengdichan.view.ui.NoticeAcitivty;
import com.cui.android.jianchengdichan.view.ui.PayFeesActivity;
import com.cui.android.jianchengdichan.view.ui.customview.GlideImageLoader;
import com.cui.android.jianchengdichan.view.ui.customview.RefreshableView;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


public class MainCommFragment extends Fragment implements IBaseView {


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
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
    @BindView(R.id.rv_comm_refreshable)
    RefreshableView rvCommRefreshable;
    Unbinder unbinder;
    MainCommPresenter mainCommPresenter = new MainCommPresenter();
    @BindView(R.id.ll_comm_notice)
    LinearLayout llCommNotice;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private List<Integer> dataTop = new ArrayList<>();
    private List<Integer> dataRv = new ArrayList<>();
    private List<HomeDataBean.AdBean> mDataList = new ArrayList<>();//轮播图数据
    private List<String> noticeList = new ArrayList<>();//公告数据
    public static final int NEWS_MESSAGE_TEXTVIEW = 300;//通知公告信息
    private int index = 0;//textview上下滚动下标
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
    List<HomeDataBean.AdBean> dataList = new ArrayList<>();
    List<String> notifi = new ArrayList();

    public MainCommFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MainCommFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MainCommFragment newInstance(String param1, String param2) {
        LogUtils.i("newInstance");

        MainCommFragment fragment = new MainCommFragment();
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
        dataTop.add(R.drawable.courier_icon);
        dataTop.add(R.drawable.laundry_icon);
        dataTop.add(R.drawable.doctor_icon);
        dataTop.add(R.drawable.dining_hall_icon);
        dataTop.add(R.drawable.bank_icon);
        dataTop.add(R.drawable.readbook_icon);

        dataRv.add(R.drawable.parking_car);
        dataRv.add(R.drawable.leasing_center);
        dataRv.add(R.drawable.parking_management);
        dataRv.add(R.drawable.pay_cost);
        dataRv.add(R.drawable.blacklist);
        dataRv.add(R.drawable.more_service);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_main_comm, container, false);
        unbinder = ButterKnife.bind(this, view);

        initRecyclerView();
        setRefresh();
        mainCommPresenter.attachView(this);
        mainCommPresenter.setTransformer(setThread());
        //公告
        tvMainCommNoticeNew.setFactory(new ViewSwitcher.ViewFactory() {
            @Override
            public View makeView() {
                TextView textView = new TextView(MyApplication.getAppContext());
                textView.setSingleLine();
                textView.setTextSize(16);//字号
                textView.setTextColor(Color.parseColor("#000000"));
                textView.setEllipsize(TextUtils.TruncateAt.END);
                FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(
                        ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                params.gravity = Gravity.CENTER_VERTICAL;
                textView.setLayoutParams(params);
                return textView;
            }
        });
        int uid = (Integer) SPUtils.INSTANCE.getSPValue(SPKey.SP_USER_UID_KEY, SPUtils.DATA_INT);
        String token = (String) SPUtils.INSTANCE.getSPValue(SPKey.SP_USER_TOKEN_KEY, SPUtils.DATA_STRING);
        mainCommPresenter.getAdList(uid, token, "2", "2");
        mainCommPresenter.getNoticeList(uid, token, "2");
        return view;
    }

    public <T> ObservableTransformer<T, T> setThread() {
        return new ObservableTransformer<T, T>() {
            @Override
            public ObservableSource<T> apply(Observable<T> upstream) {
                return upstream.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
            }
        };
    }

    /**
     * 初始化下拉刷新
     */
    private void setRefresh() {
        rvCommRefreshable.setOnRefreshListener(new RefreshableView.PullToRefreshListener() {
            @Override
            public void onRefresh() {
                LogUtils.i("setOnRefreshListener");
                TimerTask task = new TimerTask() {
                    @Override
                    public void run() {
                        /**
                         *要执行的操作
                         */
                        rvCommRefreshable.finishRefreshing();
                    }
                };
                Timer timer = new Timer();
                timer.schedule(task, 3000);//3秒后执行TimeTask的run方法
//                mainHomePresenter.getData();
            }
        }, 1);
    }

    private void initRecyclerView() {
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2);
        rvCommTop.setLayoutManager(gridLayoutManager);
        CommRvAdapter commRvAdapter = new CommRvAdapter(dataTop, new OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {

            }

            @Override
            public void onItemLongClick(View view, int position) {

            }
        });
        rvCommTop.setAdapter(commRvAdapter);

        GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 2);
        rvComm.setLayoutManager(layoutManager);
        CommRvAdapter commRvAdapter1 = new CommRvAdapter(dataRv, new OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                boolean isLogin = (boolean) SPUtils.INSTANCE.getSPValue(SPKey.SP_LOAGIN_KEY, SPUtils.DATA_BOOLEAN);
                if (isLogin) {
//            startActivity(new Intent(getContext(), PayFeesActivity.class));
                } else {
                    startActivity(new Intent(getContext(), LoginActivity.class));
                    return;
                }
                switch (position) {
                    case 0:
                        break;
                    case 1:
                        startActivity(new Intent(getContext(), LeaseCentreActivity.class));
                        break;
                    case 2:
                        break;
                    case 3:
                        startActivity(new Intent(getContext(), PayFeesActivity.class));
                        break;
                    case 4:
                        break;
                    case 5:
                        break;
                }
            }

            @Override
            public void onItemLongClick(View view, int position) {

            }
        });
        rvComm.setAdapter(commRvAdapter1);

    }

    /**
     * 初始化图片轮播图
     */
    private void initAdvViewPage() {
        if (mDataList == null || mDataList.size() == 0) {
            return;
        }
        bnMainCommAdv.setImageLoader(new GlideImageLoader()); //设置图片加载器
        bnMainCommAdv.setImages(mDataList);//设置图片集合
        //设置自动轮播，默认为true
        bnMainCommAdv.isAutoPlay(true);
        //设置轮播时间
        bnMainCommAdv.setDelayTime(3000);
        //设置指示器位置（当banner模式中有指示器时）
        bnMainCommAdv.setIndicatorGravity(BannerConfig.CENTER);
        bnMainCommAdv.start();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
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

    @Override
    public void onFailure(String msg) {

    }

    @Override
    public void onError() {

    }

    public void getAdList(List<HomeDataBean.AdBean> data) {
        mDataList.clear();
        if (data != null) {
            mDataList.addAll(data);
            initAdvViewPage();
        }
    }

    public void getNoticeList(List<NoticeThreelistBean> data) {
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

    @OnClick(R.id.ll_comm_notice)
    public void onViewClicked() {
        startActivity(new Intent(getContext(), NoticeAcitivty.class));
    }
}
