package com.cui.android.jianchengdichan.utils;

import android.app.Activity;
import android.app.Application;
import android.content.ComponentCallbacks;
import android.content.res.Configuration;
import android.support.annotation.NonNull;
import android.util.DisplayMetrics;

/**
 * 适配屏幕
 */
public class DensityUtils {
    private static float sNoncompatDensity;
    private static float sNoncompatScaledDensity;

    public static void setCusTomDensity(@NonNull Activity activity , @NonNull final Application application){
        try{
            final DisplayMetrics displayMetrics = application.getResources().getDisplayMetrics();

            if(sNoncompatDensity == 0){
                sNoncompatDensity = displayMetrics.density;
                sNoncompatScaledDensity = displayMetrics.scaledDensity;
                application.registerComponentCallbacks(new ComponentCallbacks() {
                    @Override
                    public void onConfigurationChanged(Configuration newConfig) {
                        if(newConfig != null && newConfig.fontScale > 0){
                            sNoncompatScaledDensity = application.getResources().getDisplayMetrics().scaledDensity;
                        }
                    }

                    @Override
                    public void onLowMemory() {

                    }
                });
            }
            //今日头条源码有个bug 小数点后被吃掉了
            final float targetDensity = Float.valueOf(displayMetrics.widthPixels) / 375f;
            final float tragetScaledDensity = targetDensity * (sNoncompatScaledDensity / sNoncompatDensity);
            final int targerDensityDpi = (int) (160 * targetDensity);

            displayMetrics.density = targetDensity;
            displayMetrics.scaledDensity = tragetScaledDensity;
            displayMetrics.densityDpi = targerDensityDpi;

            final DisplayMetrics activityDispllayMetrics = activity.getResources().getDisplayMetrics();
            activityDispllayMetrics.density = targetDensity;
            activityDispllayMetrics.scaledDensity = tragetScaledDensity;
            activityDispllayMetrics.densityDpi = targerDensityDpi;
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
