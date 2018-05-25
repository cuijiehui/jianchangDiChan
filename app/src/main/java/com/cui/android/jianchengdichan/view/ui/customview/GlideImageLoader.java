package com.cui.android.jianchengdichan.view.ui.customview;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.cui.android.jianchengdichan.MyApplication;
import com.cui.android.jianchengdichan.http.bean.HomeDataBean;
import com.cui.android.jianchengdichan.utils.LogUtils;
import com.youth.banner.loader.ImageLoader;

public class GlideImageLoader extends ImageLoader {


    @Override
    public void displayImage(Context context, Object path, ImageView imageView) {
        //Glide 加载图片简单用法
//        Glide.with(context).load(path).into(imageView);
        HomeDataBean.AdBean adBean =(HomeDataBean.AdBean)path;
        Glide.with(MyApplication.getAppContext()).load(adBean.getPic()).into(imageView);
    }
}
