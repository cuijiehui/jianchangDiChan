package com.cui.android.jianchengdichan.view.ui.adapter;

import android.content.Context;
import android.widget.ImageView;

import com.cui.android.jianchengdichan.http.bean.HomeCivilianListBean;
import com.cui.android.jianchengdichan.utils.LogUtils;
import com.cui.android.jianchengdichan.utils.Okhttp3Utils;
import com.youth.banner.loader.ImageLoader;

/**
 * @author CUI
 * @data 2018/6/12.
 * @details
 */
public class ConveAdvLoader extends ImageLoader {
    @Override
    public void displayImage(final Context context, Object path, ImageView imageView) {
        //Glide 加载图片简单用法
        final HomeCivilianListBean.AdBean adBean =(HomeCivilianListBean.AdBean)path;
        LogUtils.i("轮播图测试:"+adBean.getPic());
        Okhttp3Utils.getInstance().glide(context,adBean.getPic(),imageView);
    }
}
