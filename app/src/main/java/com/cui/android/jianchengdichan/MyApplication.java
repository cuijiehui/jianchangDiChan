package com.cui.android.jianchengdichan;

import android.app.Application;
import android.content.Context;

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


    public static synchronized MyApplication getInstance(){
        return instance;
    }
    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        appContext = instance.getApplicationContext();
    }

    public static Context getAppContext(){
        return appContext;
    }

    public <T> ObservableTransformer<T,T> setThread(){
        return new ObservableTransformer<T,T>() {
            @Override
            public ObservableSource<T> apply(Observable<T> upstream) {
                return upstream.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
            }
        };
    }
}
