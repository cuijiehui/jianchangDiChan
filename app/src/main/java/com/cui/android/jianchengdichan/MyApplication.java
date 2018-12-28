package com.cui.android.jianchengdichan;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Bundle;

import com.cui.android.jianchengdichan.utils.LogcatHelper;

import java.util.Stack;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * @author CUI
 * @data 2018/5/16.
 * @description Application基类
 */
public class MyApplication extends Application {
    private static MyApplication instance;
    private static Context appContext;
    private Stack<Activity> activityStack = new Stack<>();

    public static synchronized MyApplication getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        appContext = instance.getApplicationContext();
        registerActivityLifecycleCallbacks(callbacks);

    }

    @Override
    public void onTerminate() {
        unregisterActivityLifecycleCallbacks(callbacks);
        super.onTerminate();
    }

    public static Context getAppContext() {
        return appContext;
    }

    ActivityLifecycleCallbacks callbacks = new ActivityLifecycleCallbacks() {
        @Override
        public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
            if (activity!=null){
                activityStack.add(activity);
            }
        }

        @Override
        public void onActivityStarted(Activity activity) {

        }

        @Override
        public void onActivityResumed(Activity activity) {

        }

        @Override
        public void onActivityPaused(Activity activity) {

        }

        @Override
        public void onActivityStopped(Activity activity) {

        }

        @Override
        public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

        }

        @Override
        public void onActivityDestroyed(Activity activity) {
            if (activity!=null) {
                activityStack.remove(activity);
            }
        }
    };

    public Activity getActivityById(int id){
        for (Activity activity : activityStack) {
            if (activity.getTaskId()==id) {
                return activity;
            }
        }
        return null;
    }
    public void finishActivityByName(String className){
        for (Activity activity : activityStack) {
            if (className.equals(activity.getLocalClassName())) {
                activity.finish();
            }
        }
    }
    public <T> ObservableTransformer<T, T> setThread() {
        return new ObservableTransformer<T, T>() {
            @Override
            public ObservableSource<T> apply(Observable<T> upstream) {
                return upstream.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
            }
        };
    }
}
