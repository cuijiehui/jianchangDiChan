package com.cui.android.jianchengdichan.utils;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.net.Uri;

public class MapUtils {
    public final static String BAIDU_PKG = "com.baidu.BaiduMap"; //百度地图的包名

    public final static String GAODE_PKG = "com.autonavi.minimap";//高德地图的包名

    public static void openGaoDe(Context context, double latitude, double longitude) {
        Intent intent = new Intent("android.intent.action.VIEW", android.net.Uri.parse("androidamap://navi?sourceApplication=你的APP名称&lat=" + latitude + "&lon=" + longitude + "&dev=0"));
        intent.setPackage("com.autonavi.minimap");
        context.startActivity(intent);
    }

    public static void openBaidu(Context context, double latitude, double longitude) {
        Intent i1 = new Intent();
        i1.setData(Uri.parse("baidumap://map/geocoder?location=" + latitude + "," + longitude));
        context.startActivity(i1);
    }

    /**
     * 检测地图应用是否安装
     *
     * @param context
     * @param packagename
     * @return
     */
    public static boolean checkMapAppsIsExist(Context context, String packagename) {
        PackageInfo packageInfo;
        try {
            packageInfo = context.getPackageManager().getPackageInfo(packagename, 0);
        } catch (Exception e) {
            packageInfo = null;
            e.printStackTrace();
        }
        if (packageInfo == null) {
            return false;
        } else {
            return true;
        }
    }


}
