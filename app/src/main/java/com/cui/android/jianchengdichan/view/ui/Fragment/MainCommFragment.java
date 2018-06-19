package com.cui.android.jianchengdichan.view.ui.Fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
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
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextSwitcher;
import android.widget.TextView;
import android.widget.ViewSwitcher;

import com.baoyz.widget.PullRefreshLayout;
import com.cui.android.jianchengdichan.MyApplication;
import com.cui.android.jianchengdichan.R;
import com.cui.android.jianchengdichan.http.bean.CommentActBean;
import com.cui.android.jianchengdichan.http.bean.CommentTopicBean;
import com.cui.android.jianchengdichan.http.bean.HomeDataBean;
import com.cui.android.jianchengdichan.http.bean.NoticeThreelistBean;
import com.cui.android.jianchengdichan.presenter.MainCommPresenter;
import com.cui.android.jianchengdichan.utils.LogUtils;
import com.cui.android.jianchengdichan.utils.Okhttp3Utils;
import com.cui.android.jianchengdichan.utils.SPKey;
import com.cui.android.jianchengdichan.utils.SPUtils;
import com.cui.android.jianchengdichan.view.interfaces.IBaseView;
import com.cui.android.jianchengdichan.view.ui.Fragment.Adapter.AdapterBean.CommRvBean;
import com.cui.android.jianchengdichan.view.ui.Fragment.Adapter.CommRvAdapter;
import com.cui.android.jianchengdichan.view.ui.Fragment.Adapter.CommRvBottomAdapter;
import com.cui.android.jianchengdichan.view.ui.Fragment.Adapter.interfaces.OnRecyclerViewItemClickListener;
import com.cui.android.jianchengdichan.view.ui.adapter.CommentTopicAdapter;
import com.cui.android.jianchengdichan.view.ui.avtivity.ActListActivity;
import com.cui.android.jianchengdichan.view.ui.avtivity.ConveColumnActivity;
import com.cui.android.jianchengdichan.view.ui.avtivity.LeaseCentreActivity;
import com.cui.android.jianchengdichan.view.ui.avtivity.LoginActivity;
import com.cui.android.jianchengdichan.view.ui.avtivity.NoticeAcitivty;
import com.cui.android.jianchengdichan.view.ui.avtivity.PayFeesActivity;
import com.cui.android.jianchengdichan.view.ui.avtivity.PlazaActivity;
import com.cui.android.jianchengdichan.view.ui.avtivity.ReleaseTopicActivity;
import com.cui.android.jianchengdichan.view.ui.avtivity.RepairsActivity;
import com.cui.android.jianchengdichan.view.ui.customview.GlideImageLoader;
import com.cui.android.jianchengdichan.view.ui.customview.UIImageView;
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


public class MainCommFragment extends Fragment implements IBaseView {



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
    @BindView(R.id.iv_comm_floating)
    ImageView ivCommFloating;
    @BindView(R.id.tv_comm_shop_look)
    TextView tvCommShopLook;
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

    private String mParam1;
    private String mParam2;
    private List<CommRvBean> dataTop = new ArrayList<>();
    private List<CommRvBean> dataRv = new ArrayList<>();
    private List<HomeDataBean.AdBean> mDataList = new ArrayList<>();//轮播图数据
    List<CommentTopicBean> topicData = new ArrayList<>();//活动数据
    private List<String> noticeList = new ArrayList<>();//公告数据
    public static final int NEWS_MESSAGE_TEXTVIEW = 300;//通知公告信息
    Unbinder unbinder;
    MainCommPresenter mainCommPresenter = new MainCommPresenter();
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private int index = 0;//textview上下滚动下标
    CommentTopicAdapter commentTopicAdapter;
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
        dataTop.add(new CommRvBean(R.drawable.comm_express_icon, "快递站点", "家里的衣服这里洗"));
        dataTop.add(new CommRvBean(R.drawable.comm_washer_icon, "优质洗衣", "集多项服务于一体"));
        dataTop.add(new CommRvBean(R.drawable.comm_hospital_icon, "生病就医", "集多项服务于一体"));
        dataTop.add(new CommRvBean(R.drawable.comm_canteen_icon, "饿了食堂", "各色菜系人你选择"));
        dataTop.add(new CommRvBean(R.drawable.comm_bank_icon, "附近银行", "有钱没钱来这里"));
        dataTop.add(new CommRvBean(R.drawable.comm_school_icon, "学生读书", "给孩子一个好学校"));

        dataRv.add(new CommRvBean(R.drawable.comm_stop_car, "我要停车", "线上支付更快捷"));
        dataRv.add(new CommRvBean(R.drawable.comm_lease_icon, "租赁中心", "超多房源任你挑选"));
        dataRv.add(new CommRvBean(R.drawable.comm_carport_icon, "车位管理", "查询并管理车位"));
        dataRv.add(new CommRvBean(R.drawable.comm_pay_icon, "物业缴费", "每月线上缴费服务"));
        dataRv.add(new CommRvBean(R.drawable.comm_black_list_icon, "黑白名单", "管理分组和下发"));
        dataRv.add(new CommRvBean(R.drawable.comm_more_icon, "报事报修", "线上预约更方便"));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_main_comm, container, false);
        unbinder = ButterKnife.bind(this, view);
        initAdvViewPage();
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

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        getData();
    }

    public void getData() {
        int uid = (Integer) SPUtils.INSTANCE.getSPValue(SPKey.SP_USER_UID_KEY, SPUtils.DATA_INT);
        String token = (String) SPUtils.INSTANCE.getSPValue(SPKey.SP_USER_TOKEN_KEY, SPUtils.DATA_STRING);
        mainCommPresenter.getAdList(uid, token, "2", "2");
        mainCommPresenter.getNoticeList(uid, token, "2");
        mainCommPresenter.getCommentAct();
        mainCommPresenter.getCommentTopic();
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
        prl_comm_refreshable.setOnRefreshListener(new PullRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // start refresh
                getData();

            }
        });
    }

    private void initRecyclerView() {
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2);
        rvCommTop.setLayoutManager(gridLayoutManager);
        CommRvAdapter commRvAdapter = new CommRvAdapter(dataTop, new OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Bundle bundle = new Bundle();
                bundle.putInt("type",position+1);
                Intent  intent =new Intent(getContext(),ConveColumnActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }

            @Override
            public void onItemLongClick(View view, int position) {

            }
        });
        rvCommTop.setAdapter(commRvAdapter);

        GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 2);
        rvComm.setLayoutManager(layoutManager);
//        rvComm.addItemDecoration(new GridDivider(getActivity(), 10
//                , this.getResources().getColor(R.color.like_back_col)));
        CommRvBottomAdapter commRvAdapter1 = new CommRvBottomAdapter(dataRv, new OnRecyclerViewItemClickListener() {
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
                        startActivity(new Intent(getContext(), RepairsActivity.class));

                        break;
                }
            }

            @Override
            public void onItemLongClick(View view, int position) {

            }
        });
        rvComm.setAdapter(commRvAdapter1);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        rvCommTopicData.setLayoutManager(linearLayoutManager);
        commentTopicAdapter= new CommentTopicAdapter(R.layout.item_comm_topic_layout,topicData);
        rvCommTopicData.setAdapter(commentTopicAdapter);
        rvCommTopicData.addItemDecoration(new DividerItemDecoration(getContext(),DividerItemDecoration.VERTICAL));
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
            bnMainCommAdv.update(data);
        }
    }

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

    @OnClick({R.id.ll_comm_notice, R.id.iv_comm_floating, R.id.tv_comm_shop_look, R.id.uiv_comm_act_more, R.id.tv_comm_act_like, R.id.tv_comm_act_topic, R.id.uiv_comm_topic_more})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_comm_floating:
                startActivity(new Intent(getContext(), ReleaseTopicActivity.class));

                break;
            case R.id.tv_comm_shop_look:
                break;
            case R.id.uiv_comm_act_more:
                startActivity(new Intent(getContext(), ActListActivity.class));
                break;
            case R.id.tv_comm_act_like:
                break;
            case R.id.tv_comm_act_topic:
                break;
            case R.id.uiv_comm_topic_more:
                startActivity(new Intent(getContext(), PlazaActivity.class));

                break;
            case R.id.ll_comm_notice:
                startActivity(new Intent(getContext(), NoticeAcitivty.class));
                break;
        }
    }

    public void getCommentAct(CommentActBean data) {
        if(data!=null){
//            uivCommActPic
            Okhttp3Utils.getInstance().glide(getContext(),data.getPic(),uivCommActPic);
            tvCommActTime.setText(data.getCreate_time());
        }

    }

    public void getCommentTopic(List<CommentTopicBean> data) {
        topicData.clear();
        if(data!=null){
            topicData.addAll(data);
        }
        commentTopicAdapter.notifyDataSetChanged();
    }
}
