package com.cui.android.jianchengdichan.utils;

import android.os.Handler;

import com.cui.android.jianchengdichan.MyApplication;

import java.net.URLEncoder;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class Okhttp3Utils {
    private OkHttpClient mOkHttpClient;
    private static Okhttp3Utils mInstance;
    private Handler okHttpHandler;//全局处理子线程和M主线程通信
    private Okhttp3Utils(){
        //初始化OkHttpClient
        mOkHttpClient = new OkHttpClient().newBuilder()
                .connectTimeout(30, TimeUnit.SECONDS)//设置超时时间
                .readTimeout(30, TimeUnit.SECONDS)//设置读取超时时间
                .writeTimeout(30, TimeUnit.SECONDS)//设置写入超时时间
                .build();
        //初始化Handler
        okHttpHandler = new Handler(MyApplication.getAppContext().getMainLooper());
    }
    public static Okhttp3Utils getInstance(){
        Okhttp3Utils instan = mInstance;
        if(instan==null){
            synchronized (Okhttp3Utils.class) {
                instan = mInstance;
                if (instan == null) {
                    instan = new Okhttp3Utils();
                    mInstance = instan;
                }
            }
        }
        return instan;
    }

    /**
     * Get请求
     *
     * @param url
     * @param callback
     */
    public void doGet(String url, Callback callback) {
        Request request = new Request.Builder()
                .url(url)
                .build();
        Call call = mOkHttpClient.newCall(request);
        call.enqueue(callback);
    }
    /**
     * Post请求发送键值对数据
     *
     * @param url
     * @param mapParams
     * @param callback
     */
    public void doPost(String url, Map<String, String> mapParams, Callback callback) {
        FormBody.Builder builder = new FormBody.Builder();
        for (String key : mapParams.keySet()) {
            builder.add(key, mapParams.get(key));
        }
        Request request = new Request.Builder()
                .url(url)
                .addHeader("Accept", "application/json")
                .addHeader("Content-Type", "application/json; charset=UTF-8")
                .post(builder.build())
                .build();
        Call call = mOkHttpClient.newCall(request);
        call.enqueue(callback);
    }
}
