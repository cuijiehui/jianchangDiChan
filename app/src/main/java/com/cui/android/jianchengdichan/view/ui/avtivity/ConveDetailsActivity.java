package com.cui.android.jianchengdichan.view.ui.avtivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.cui.android.jianchengdichan.R;
import com.cui.android.jianchengdichan.http.bean.CivilianDetailBean;
import com.cui.android.jianchengdichan.presenter.BasePresenter;
import com.cui.android.jianchengdichan.presenter.ConveDetailsPresenter;
import com.cui.android.jianchengdichan.utils.Okhttp3Utils;
import com.cui.android.jianchengdichan.view.base.BaseActivtity;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class ConveDetailsActivity extends BaseActivtity {
    @BindView(R.id.top_back)
    RelativeLayout topBack;
    @BindView(R.id.tv_content_name)
    TextView tvContentName;
    @BindView(R.id.tv_top_right)
    TextView tvTopRight;
    @BindView(R.id.iv_top_right)
    ImageView ivTopRight;
    @BindView(R.id.bn_conve_detail_adv)
    Banner bnConveDetailAdv;
    @BindView(R.id.tv_datail_title)
    TextView tvDatailTitle;
    @BindView(R.id.tv_datail_browse)
    TextView tvDatailBrowse;
    @BindView(R.id.tv_datail_content)
    TextView tvDatailContent;
    @BindView(R.id.tv_datail_addres)
    TextView tvDatailAddres;
    @BindView(R.id.tv_datail_name)
    TextView tvDatailName;
    @BindView(R.id.tv_datail_phone)
    TextView tvDatailPhone;
    ConveDetailsPresenter conveDetailsPresenter = new ConveDetailsPresenter();
    List<String> imgUrl = new ArrayList<>();
    String id= "";
    @Override
    public BasePresenter initPresenter() {
        return conveDetailsPresenter;
    }

    @Override
    public void initParms(Bundle parms) {
        id=parms.getString("id");
    }

    @Override
    public int bindLayout() {
        return R.layout.activity_conve_details;
    }

    @Override
    public void initView(View view) {
        tvContentName.setText("详情");
        initAdvViewPage();
    }

    @Override
    public void doBusiness(Context mContext) {
        conveDetailsPresenter.getDetail(id);
    }

    @Override
    public View initBack() {
        return topBack;
    }

    @OnClick(R.id.tv_top_right)
    public void onViewClicked() {
    }

    public void getDetail(CivilianDetailBean data) {
        if(data!=null){
            setView(data);
        }
    }

    private void setView(CivilianDetailBean data) {
        String url =data.getBigimg();
        imgUrl.clear();
        if(url!=null){
            imgUrl.add(url);
        }
        bnConveDetailAdv.update(imgUrl);
        tvDatailTitle.setText(data.getTitle());
        tvDatailPhone.setText("电话："+data.getPhone());
        tvDatailAddres.setText("地址："+data.getAddress());
        tvDatailName.setText("联系人："+data.getContact());
        tvDatailContent.setText(data.getDescribe());
        tvDatailBrowse.setText("已有"+data.getFlow()+"次浏览");
    }

    /**
     * 初始化图片轮播图
     */
    private void initAdvViewPage() {
        bnConveDetailAdv.setImageLoader(new MyImageLoader()); //设置图片加载器
//        bnMainAdv.setImages(mDataList);//设置图片集合
        //设置自动轮播，默认为true
        bnConveDetailAdv.isAutoPlay(true);
        //设置轮播时间
        bnConveDetailAdv.setDelayTime(3000);
        //设置指示器位置（当banner模式中有指示器时）
        bnConveDetailAdv.setIndicatorGravity(BannerConfig.CENTER);
        bnConveDetailAdv.start();
    }
    class MyImageLoader extends ImageLoader{
        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            String url = (String )path;
            Okhttp3Utils.getInstance().glide(context,url,imageView);
        }
    }
}
