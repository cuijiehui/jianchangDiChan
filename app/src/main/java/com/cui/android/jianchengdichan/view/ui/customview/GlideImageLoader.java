package com.cui.android.jianchengdichan.view.ui.customview;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.cui.android.jianchengdichan.MyApplication;
import com.cui.android.jianchengdichan.http.bean.HomeDataBean;
import com.cui.android.jianchengdichan.utils.LogUtils;
import com.cui.android.jianchengdichan.utils.Okhttp3Utils;
import com.cui.android.jianchengdichan.view.ui.WebViewActivity;
import com.youth.banner.loader.ImageLoader;

public class GlideImageLoader extends ImageLoader {


    @Override
    public void displayImage(final Context context, Object path, ImageView imageView) {
        //Glide 加载图片简单用法
       final HomeDataBean.AdBean adBean =(HomeDataBean.AdBean)path;
        LogUtils.i("轮播图测试:"+adBean.getPic());
        Okhttp3Utils.getInstance().glide(context,adBean.getPic(),imageView);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!TextUtils.isEmpty(adBean.getUrl())){
                    Intent intent = new Intent(context, WebViewActivity.class);
                    intent.putExtra("data", adBean.getUrl());
                    context.startActivity(intent);
                }
            }
        });
    }

}
