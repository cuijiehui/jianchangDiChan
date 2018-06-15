package com.cui.android.jianchengdichan.view.ui.avtivity;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.cui.android.jianchengdichan.R;
import com.cui.android.jianchengdichan.http.bean.CivilianAdvBean;
import com.cui.android.jianchengdichan.http.bean.CivilianListBean;
import com.cui.android.jianchengdichan.presenter.BasePresenter;
import com.cui.android.jianchengdichan.presenter.ConveColumnPresenter;
import com.cui.android.jianchengdichan.utils.Okhttp3Utils;
import com.cui.android.jianchengdichan.view.BaseActivtity;
import com.cui.android.jianchengdichan.view.ui.adapter.ColumnDataAdapter;
import com.cui.android.jianchengdichan.view.ui.adapter.ConveAdvLoader;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ConveColumnActivity extends BaseActivtity {

    @BindView(R.id.top_back)
    RelativeLayout topBack;
    @BindView(R.id.tv_content_name)
    TextView tvContentName;
    @BindView(R.id.tv_top_right)
    TextView tvTopRight;
    @BindView(R.id.iv_top_right)
    ImageView ivTopRight;
    @BindView(R.id.bn_column_adv)
    Banner bnColumnAdv;
    @BindView(R.id.rv_column_data)
    RecyclerView rvColumnData;
    ConveColumnPresenter conveColumnPresenter = new ConveColumnPresenter();
    List<CivilianListBean> dataList = new ArrayList<>();
    List<CivilianAdvBean> advDataList = new ArrayList<>();
    ColumnDataAdapter columnDataAdapter ;
    int type =0;
    int page = 1;
    String key="";
    String is_recommend="";
    @Override
    public BasePresenter initPresenter() {
        return conveColumnPresenter;
    }

    @Override
    public void initParms(Bundle parms) {
        if(parms!=null){
            type= parms.getInt("type");
        }
    }

    @Override
    public int bindLayout() {
        return R.layout.activity_conve_column;
    }

    @Override
    public void initView(View view) {
        switch (type){
            case 1:
                tvContentName.setText("快递");
                break;
            case 2:

                tvContentName.setText("洗衣");
                break;
            case 3:
                tvContentName.setText("医院");
                break;
            case 4:
                tvContentName.setText("外卖");
                break;
            case 5:
                tvContentName.setText("银行");
                break;
            case 6:
                tvContentName.setText("学校");
                break;
        }
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
        rvColumnData.setLayoutManager(linearLayoutManager);
        columnDataAdapter = new ColumnDataAdapter(R.layout.item_column_date_ayout,dataList);
        rvColumnData.setAdapter(columnDataAdapter);
        initAdvViewPage();
        bnColumnAdv.setVisibility(View.GONE);
    }

    @Override
    protected void onResume() {
        super.onResume();
        conveColumnPresenter.getColumnData(type,key,is_recommend,page);
        conveColumnPresenter.getColumnAdv(type);

    }
    /**
     * 初始化图片轮播图
     */
    private void initAdvViewPage() {
        bnColumnAdv.setImageLoader(new AdvLoader()); //设置图片加载器
//        bnMainAdv.setImages(mDataList);//设置图片集合
        //设置自动轮播，默认为true
        bnColumnAdv.isAutoPlay(true);
        //设置轮播时间
        bnColumnAdv.setDelayTime(3000);
        //设置指示器位置（当banner模式中有指示器时）
        bnColumnAdv.setIndicatorGravity(BannerConfig.CENTER);
        bnColumnAdv.start();
    }

    @Override
    public void doBusiness(Context mContext) {

    }

    @Override
    public View initBack() {
        return topBack;
    }

    public void getColumnData(List<CivilianListBean> data) {
        dataList.clear();
        if(data!=null){
            dataList.addAll(data);
        }
        columnDataAdapter.notifyDataSetChanged();
    }

    public void getColumnAdv(List<CivilianAdvBean> data) {
        advDataList.clear();
        if(data!=null){
            advDataList.addAll(data);
            if(advDataList.size()>0){
                bnColumnAdv.setVisibility(View.VISIBLE);
                bnColumnAdv.update(advDataList);
            }

        }else{
            bnColumnAdv.setVisibility(View.GONE);
        }

    }

    class AdvLoader extends ImageLoader{
        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            CivilianAdvBean civilianAdvBean =(CivilianAdvBean) path;
            Okhttp3Utils.getInstance().glide(mContext,civilianAdvBean.getPic(),imageView);
        }
    }
}
