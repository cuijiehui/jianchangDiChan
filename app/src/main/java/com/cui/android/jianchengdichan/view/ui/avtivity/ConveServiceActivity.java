package com.cui.android.jianchengdichan.view.ui.avtivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.cui.android.jianchengdichan.R;
import com.cui.android.jianchengdichan.http.bean.HomeCivilianListBean;
import com.cui.android.jianchengdichan.presenter.BasePresenter;
import com.cui.android.jianchengdichan.presenter.ConveServicePresenter;
import com.cui.android.jianchengdichan.view.base.BaseActivity;
import com.cui.android.jianchengdichan.view.ui.fragment.adapter.adapterBean.CommunityBean;
import com.cui.android.jianchengdichan.view.ui.adapter.ConveAdapter;
import com.cui.android.jianchengdichan.view.ui.adapter.ConveAdvLoader;
import com.cui.android.jianchengdichan.view.ui.adapter.ConveRecommendAdapter;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class ConveServiceActivity extends BaseActivity {
    ConveServicePresenter conveServicePresenter = new ConveServicePresenter();
    @BindView(R.id.top_back)
    RelativeLayout topBack;
    @BindView(R.id.tv_content_name)
    TextView tvContentName;
    @BindView(R.id.tv_top_right)
    TextView tvTopRight;
    @BindView(R.id.iv_top_right)
    ImageView ivTopRight;
    @BindView(R.id.et_conve_seek)
    EditText etConveSeek;
    @BindView(R.id.bn_conve_adv)
    Banner bnConveAdv;
    @BindView(R.id.rv_conve_column)
    RecyclerView rvConveColumn;
    @BindView(R.id.rv_conve_recommend)
    RecyclerView rvConveRecommend;

    ConveAdapter conveAdapter;  //社区管家
    @BindView(R.id.iv_conve_icon)
    ImageView ivConveIcon;
    private List<CommunityBean> communityBeanList = new ArrayList<>();//社区管家数据
    private List<CommunityBean> communityBeanListAll = new ArrayList<>();//社区管家数据
    List<HomeCivilianListBean.InfoBean> dataList = new ArrayList<>();
    ConveRecommendAdapter recommendAdapter;
    boolean isUnfold =false;
    @Override
    public BasePresenter initPresenter() {
        return conveServicePresenter;
    }

    @Override
    public void initParms(Bundle parms) {

    }

    @Override
    public int bindLayout() {
        return R.layout.activity_conve_service;
    }

    @Override
    public void initView(View view) {
        tvContentName.setText("便民服务");
        initAdvViewPage();
        initRecyclerView();

    }

    public void initRecyclerView() {
        communityBeanList.add(new CommunityBean(R.drawable.comm_express_icon, "快递"));
        communityBeanList.add(new CommunityBean(R.drawable.comm_washer_icon, "洗衣"));
        communityBeanList.add(new CommunityBean(R.drawable.comm_hospital_icon, "医院"));
        communityBeanList.add(new CommunityBean(R.drawable.comm_canteen_icon, "外卖"));

        communityBeanListAll.add(new CommunityBean(R.drawable.comm_express_icon, "快递"));
        communityBeanListAll.add(new CommunityBean(R.drawable.comm_washer_icon, "洗衣"));
        communityBeanListAll.add(new CommunityBean(R.drawable.comm_hospital_icon, "医院"));
        communityBeanListAll.add(new CommunityBean(R.drawable.comm_canteen_icon, "外卖"));
        communityBeanListAll.add(new CommunityBean(R.drawable.comm_bank_icon, "银行"));
        communityBeanListAll.add(new CommunityBean(R.drawable.comm_school_icon, "学校"));
        communityBeanListAll.add(new CommunityBean(R.drawable.homemaking_icon, "家政"));
        communityBeanListAll.add(new CommunityBean(R.drawable.shop_comm_icon, "便利店"));
        communityBeanListAll.add(new CommunityBean(R.drawable.nurse_icon, "护理"));
        communityBeanListAll.add(new CommunityBean(R.drawable.train_icon, "培训"));
        GridLayoutManager gridLayoutManager = new GridLayoutManager(mContext, 4);
        rvConveColumn.setLayoutManager(gridLayoutManager);
        conveAdapter = new ConveAdapter(communityBeanList);
        rvConveColumn.setAdapter(conveAdapter);
        rvConveColumn.setHasFixedSize(true);
        rvConveColumn.setNestedScrollingEnabled(false);
        conveAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                CommunityBean communityBean =(CommunityBean)  adapter.getItem(position);
                Bundle bundle = new Bundle();
                bundle.putInt("type", position + 1);
                bundle.putString("typeName", communityBean.getName());
                startActivity(ConveColumnActivity.class, bundle);
            }
        });

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recommendAdapter = new ConveRecommendAdapter(dataList);
        rvConveRecommend.setLayoutManager(linearLayoutManager);
        rvConveRecommend.setAdapter(recommendAdapter);
        rvConveRecommend.setHasFixedSize(true);
        rvConveRecommend.setNestedScrollingEnabled(false);
        recommendAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                Bundle bundle = new Bundle();
                bundle.putString("id",dataList.get(position).getId()+"");
                Intent intent = new Intent(mContext,ConveDetailsActivity.class);
                intent.putExtras(bundle);
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        conveServicePresenter.getData();
    }

    @Override
    public void doBusiness(Context mContext) {

    }

    @Override
    public View initBack() {
        return topBack;
    }

    /**
     * 初始化图片轮播图
     */
    private void initAdvViewPage() {
        bnConveAdv.setImageLoader(new ConveAdvLoader()); //设置图片加载器
//        bnMainAdv.setImages(mDataList);//设置图片集合
        //设置自动轮播，默认为true
        bnConveAdv.isAutoPlay(true);
        //设置轮播时间
        bnConveAdv.setDelayTime(3000);
        //设置指示器位置（当banner模式中有指示器时）
        bnConveAdv.setIndicatorGravity(BannerConfig.CENTER);
        bnConveAdv.start();
    }


    @OnClick({R.id.iv_top_right, R.id.bt_conve_seek, R.id.tv_conve_more, R.id.tv_conve_property, R.id.tv_conve_cell, R.id.rl_conve_icon})
    public void onViewClicked(View view) {
        Bundle bundle = new Bundle();

        switch (view.getId()) {
            case R.id.iv_top_right:
                break;
            case R.id.bt_conve_seek:
                break;
            case R.id.tv_conve_more:
                break;
            case R.id.tv_conve_property:
                bundle.putString("type", "1");
                startActivity(CellPhoneActivity.class, bundle);
                break;
            case R.id.tv_conve_cell:
                bundle.putString("type", "2");
                startActivity(CellPhoneActivity.class, bundle);
                break;
            case R.id.rl_conve_icon:
                if(isUnfold){//关闭操作
                    isUnfold=false;
                    conveAdapter.setNewData(communityBeanList);
                    ivConveIcon.setBackgroundResource(R.drawable.conve_dow_icon);
                }else{//打开操作
                    isUnfold=true;
                    conveAdapter.setNewData(communityBeanListAll);
                    ivConveIcon.setBackgroundResource(R.drawable.conve_up_icon);

                }
                break;
        }
    }

    public void getData(HomeCivilianListBean data) {
        List<HomeCivilianListBean.AdBean> ad = data.getAd();
        if (ad != null) {
            bnConveAdv.update(ad);
        }
        List<HomeCivilianListBean.InfoBean> info = data.getInfo();
        dataList.clear();
        if (info != null) {
            dataList.addAll(info);
        }
        recommendAdapter.notifyDataSetChanged();
    }

}
