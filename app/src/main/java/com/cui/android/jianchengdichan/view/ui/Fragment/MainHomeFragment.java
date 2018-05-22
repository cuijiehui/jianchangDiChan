package com.cui.android.jianchengdichan.view.ui.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.cui.android.jianchengdichan.R;
import com.cui.android.jianchengdichan.utils.LogUtils;
import com.cui.android.jianchengdichan.view.ui.Fragment.Adapter.AdapterBean.CommunityBean;
import com.cui.android.jianchengdichan.view.ui.Fragment.Adapter.MainRecyclerAdapter;
import com.cui.android.jianchengdichan.view.ui.Fragment.Adapter.MainRvCommunityAdapter;
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


public class MainHomeFragment extends Fragment {
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
    @BindView(R.id.tv_main_notice_new1)
    TextView tvMainNoticeNew1;
    @BindView(R.id.tv_main_notice_new2)
    TextView tvMainNoticeNew2;
    @BindView(R.id.rv_main_rent)
    RecyclerView rvMainRent;
    @BindView(R.id.rv_main_community)
    RecyclerView rvMainCommunity;

    private Unbinder unbinder;
    private List<String> mDataList = new ArrayList<>();
    private List<String> rentDataList = new ArrayList<>();
    private List<CommunityBean> communityBeanList = new ArrayList<>();
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


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
        rentDataList.add("第一");
        rentDataList.add("第二");
        rentDataList.add("第三");
        rentDataList.add("第四");
        rentDataList.add("第五");
        communityBeanList.add(new CommunityBean(R.drawable.main_rent_centre,"租贷中心"));
        communityBeanList.add(new CommunityBean(R.drawable.main_tenement_pay,"物业缴费"));
        communityBeanList.add(new CommunityBean(R.drawable.main_service,"便民服务"));
        communityBeanList.add(new CommunityBean(R.drawable.main_pwd_icon,"门禁密码"));
        communityBeanList.add(new CommunityBean(R.drawable.main_car_go_out,"车辆出行"));
        communityBeanList.add(new CommunityBean(R.drawable.main_apply_server,"申请服务"));
        communityBeanList.add(new CommunityBean(R.drawable.main_breakdown,"故障处理"));
        communityBeanList.add(new CommunityBean(R.drawable.main_more_button,"更多"));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        LogUtils.i("onCreateView");
        View view = inflater.inflate(R.layout.fragment_main_home, container, false);
        unbinder = ButterKnife.bind(this, view);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setRefresh();
        initAdvViewPage();
        initRecycler();

    }

    /**
     * 初始化recycler
     */
    private void initRecycler(){
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        rvMainRent.setLayoutManager(linearLayoutManager);
        MainRecyclerAdapter adapter = new MainRecyclerAdapter(rentDataList);
        rvMainRent.setAdapter(adapter);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(),4);
        rvMainCommunity.setLayoutManager(gridLayoutManager);
        MainRvCommunityAdapter mainRvCommunityAdapter = new MainRvCommunityAdapter(communityBeanList);
        rvMainCommunity.setAdapter(mainRvCommunityAdapter);
    }
    /**
     * 初始化图片轮播图
     */
    private void initAdvViewPage() {
        bnMainAdv.setImageLoader(new GlideImageLoader()); //设置图片加载器
        bnMainAdv.setImages(mDataList);//设置图片集合
        //设置自动轮播，默认为true
        bnMainAdv.isAutoPlay(true);
        //设置轮播时间
        bnMainAdv.setDelayTime(1500);
        //设置指示器位置（当banner模式中有指示器时）
        bnMainAdv.setIndicatorGravity(BannerConfig.CENTER);
        bnMainAdv.start();
    }

    /**
     * 初始化下拉刷新
     */
    private void setRefresh() {
        rvMainRefreshable.setOnRefreshListener(new RefreshableView.PullToRefreshListener() {
            @Override
            public void onRefresh() {
                LogUtils.i("setOnRefreshListener");
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

    @OnClick({R.id.iv_main_top_qrcode, R.id.iv_main_top_add})
    public void onViewClicked(View view) {
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

                break;
        }
    }
}
