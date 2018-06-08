package com.cui.android.jianchengdichan.view.ui.customview;


import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;

import com.cui.android.jianchengdichan.R;
import com.cui.android.jianchengdichan.utils.LogUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class MainNavigationView extends LinearLayout {

    OnClickListener listener;
    @BindView(R.id.iv_main_home_icon)
    ImageView ivMainHomeIcon;
    @BindView(R.id.tv_main_home)
    TextView tvMainHome;
    @BindView(R.id.ll_main_home)
    LinearLayout llMainHome;
    @BindView(R.id.iv_main_community_icon)
    ImageView ivMainCommunityIcon;
    @BindView(R.id.tv_main_community)
    TextView tvMainCommunity;
    @BindView(R.id.ll_main_community)
    LinearLayout llMainCommunity;
    @BindView(R.id.iv_main_shop_icon)
    ImageView ivMainShopIcon;
    @BindView(R.id.tv_main_shop)
    TextView tvMainShop;
    @BindView(R.id.ll_main_shop)
    LinearLayout llMainShop;
    @BindView(R.id.iv_main_my_icon)
    ImageView ivMainMyIcon;
    @BindView(R.id.tv_main_my)
    TextView tvMainMy;
    @BindView(R.id.ll_main_my)
    LinearLayout llMainMy;


    private ImageView mImageView;
    private SelectedCallBack mCallBack;

    public MainNavigationView(Context context) {
        super(context);
    }

    public MainNavigationView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.main_navigation_include, this);
        ButterKnife.bind(view);
        mImageView = ivMainHomeIcon;
        ivMainHomeIcon.setSelected(true);
    }

    public MainNavigationView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @OnClick({R.id.ll_main_home, R.id.ll_main_community, R.id.ll_main_shop, R.id.ll_main_my})
    public void onViewClicked(View view) {
        if (mCallBack == null) {
            return;
        }
        switch (view.getId()) {
            case R.id.ll_main_home:
                LogUtils.i("ll_main_home");
                setSelected(ivMainHomeIcon);
                mCallBack.onBackSelected(0);
                break;
            case R.id.ll_main_community:
                LogUtils.i("ll_main_community");
                setSelected(ivMainCommunityIcon);
                mCallBack.onBackSelected(1);

                break;
            case R.id.ll_main_shop:
                LogUtils.i("ll_main_shop");
//                setSelected(ivMainShopIcon);
                mCallBack.onBackSelected(2);

                break;
            case R.id.ll_main_my:
                LogUtils.i("ll_main_my");
                setSelected(ivMainMyIcon);
                mCallBack.onBackSelected(3);

                break;
        }
    }

    private void setSelected(ImageView view) {
        LogUtils.i("setSelected");
        if (mImageView != null) {
            if (view.getId() != mImageView.getId()) {
                ivMainHomeIcon.setSelected(false);
                ivMainCommunityIcon.setSelected(false);
                ivMainShopIcon.setSelected(false);
                ivMainMyIcon.setSelected(false);
                view.setSelected(true);
            }
        } else {
            LogUtils.i("mImageView==null");
            view.setSelected(true);

        }
        mImageView = view;

    }
    public void setPager(int count){
        switch (count) {
            case 0:
                setSelected(ivMainHomeIcon);
                break;
            case 1:
                setSelected(ivMainCommunityIcon);
                break;
            case 2:
                setSelected(ivMainShopIcon);
                break;
            case 3:
                setSelected(ivMainMyIcon);
                break;
        }
    }
    public void setCallBack(SelectedCallBack callBack) {
        mCallBack = callBack;
    }

    public interface SelectedCallBack {
        void onBackSelected(int conut);
    }
//    @OnClick({R.id.ll_main_home, R.id.ll_main_community, R.id.ll_main_shop, R.id.ll_main_my})
//    public void onViewClicked(View view) {
//        switch (view.getId()) {
//            case R.id.ll_main_home:
//                LogUtils.i("ll_main_home");
//                ivMainHomeIcon.setSelected(true);
//                break;
//            case R.id.ll_main_community:
//                LogUtils.i("ll_main_community");
//                ivMainCommunityIcon.setSelected(true);
//                break;
//            case R.id.ll_main_shop:
//                LogUtils.i("ll_main_shop");
//                ivMainShopIcon.setSelected(true);
//                break;
//            case R.id.ll_main_my:
//                LogUtils.i("ll_main_my");
//                ivMainMyIcon.setSelected(true);
//
//                break;
//        }
//    }
}
