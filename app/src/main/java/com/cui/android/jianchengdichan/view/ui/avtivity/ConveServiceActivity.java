package com.cui.android.jianchengdichan.view.ui.avtivity;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.cui.android.jianchengdichan.R;
import com.cui.android.jianchengdichan.http.bean.HomeCivilianListBean;
import com.cui.android.jianchengdichan.presenter.BasePresenter;
import com.cui.android.jianchengdichan.presenter.ConveServicePresenter;
import com.cui.android.jianchengdichan.view.BaseActivtity;
import com.cui.android.jianchengdichan.view.ui.Fragment.Adapter.AdapterBean.CommunityBean;
import com.cui.android.jianchengdichan.view.ui.Fragment.Adapter.interfaces.OnRecyclerViewItemClickListener;
import com.cui.android.jianchengdichan.view.ui.adapter.ConveAdapter;
import com.cui.android.jianchengdichan.view.ui.adapter.ConveAdvLoader;
import com.cui.android.jianchengdichan.view.ui.adapter.ConveRecommendAdapter;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class ConveServiceActivity extends BaseActivtity {
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
    private List<CommunityBean> communityBeanList = new ArrayList<>();//社区管家数据
    List<HomeCivilianListBean.InfoBean> dataList = new ArrayList<>();
    ConveRecommendAdapter recommendAdapter;
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
    public void initRecyclerView(){
        communityBeanList.add(new CommunityBean(R.drawable.comm_express_icon, "快递"));
        communityBeanList.add(new CommunityBean(R.drawable.comm_washer_icon, "洗衣"));
        communityBeanList.add(new CommunityBean(R.drawable.comm_hospital_icon, "医院"));
        communityBeanList.add(new CommunityBean(R.drawable.comm_canteen_icon, "外卖"));
        communityBeanList.add(new CommunityBean(R.drawable.comm_bank_icon, "银行"));
        communityBeanList.add(new CommunityBean(R.drawable.comm_school_icon, "学校"));
        GridLayoutManager gridLayoutManager = new GridLayoutManager(mContext,4);
        rvConveColumn.setLayoutManager(gridLayoutManager);
        conveAdapter = new ConveAdapter(communityBeanList, listener);
        rvConveColumn.setAdapter(conveAdapter);


        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
         recommendAdapter=new ConveRecommendAdapter(dataList,mContext);
        rvConveRecommend.setLayoutManager(linearLayoutManager);
        rvConveRecommend.setAdapter(recommendAdapter);

    }

    @Override
    protected void onResume() {
        super.onResume();
        conveServicePresenter.getData();
    }

    OnRecyclerViewItemClickListener listener = new OnRecyclerViewItemClickListener() {
        @Override
        public void onItemClick(View view, int position) {
            Bundle bundle = new Bundle();
            bundle.putInt("type",position+1);
            startActivity(ConveColumnActivity.class,bundle);
        }

        @Override
        public void onItemLongClick(View view, int position) {

        }
    };
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



    @OnClick({R.id.iv_top_right, R.id.bt_conve_seek, R.id.tv_conve_more, R.id.tv_conve_property, R.id.tv_conve_cell})
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
                bundle.putString("type","1");
                startActivity(CellPhoneActivity.class,bundle);
                break;
            case R.id.tv_conve_cell:
                bundle.putString("type","2");
                startActivity(CellPhoneActivity.class,bundle);
                break;
        }
    }

    public void getData(HomeCivilianListBean data) {
        List<HomeCivilianListBean.AdBean> ad = data.getAd();
        if(ad!=null){
            bnConveAdv.update(ad);
        }
        List<HomeCivilianListBean.InfoBean> info = data.getInfo();
        if(info!=null){
            dataList.addAll(info);
        }
        recommendAdapter.notifyDataSetChanged();
    }
}
