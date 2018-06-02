package com.cui.android.jianchengdichan.utils;

import android.os.Handler;
import android.os.Message;

import com.cui.android.jianchengdichan.MyApplication;
import com.cui.android.jianchengdichan.http.bean.UplodeImgBean;
import com.cui.android.jianchengdichan.http.config.HttpConfig;
import com.cui.android.jianchengdichan.http.config.URLConfig;
import com.google.gson.Gson;

import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
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

    public void uplodeImg(String num,String url,Callback callback){
        File file = new File(url);
        MediaType MEDIA_TYPE = MediaType.parse("image/jpeg");
        MultipartBody.Builder multipartBodyBuilder = new MultipartBody.Builder();
        multipartBodyBuilder.setType(MultipartBody.FORM);
        multipartBodyBuilder.addFormDataPart("num",num);
        multipartBodyBuilder.addFormDataPart("file1", file.getName(), RequestBody.create(MEDIA_TYPE, file));
        RequestBody requestBody = multipartBodyBuilder.build();
        Request.Builder RequestBuilder = new Request.Builder();
        RequestBuilder.url(HttpConfig.BASE_URL+URLConfig.POSTH_UPLOAD_IMG_URL);// 添加URL地址
        RequestBuilder.post(requestBody);
        Request request = RequestBuilder.build();
        mOkHttpClient.newCall(request).enqueue(callback);
    }
    public void uplodeImgList(int num,List<String> imgUrl,Callback callback){
        MediaType MEDIA_TYPE = MediaType.parse("image/jpeg");
        MultipartBody.Builder multipartBodyBuilder = new MultipartBody.Builder();
        multipartBodyBuilder.setType(MultipartBody.FORM);
        multipartBodyBuilder.addFormDataPart("num",num+"");
        for(int i=0;i<imgUrl.size();i++){
            String url = imgUrl.get(i);
            File file = new File(url);
            String name = "file"+(i+1);
            multipartBodyBuilder.addFormDataPart(name, file.getName(), RequestBody.create(MEDIA_TYPE, file));
        }
        RequestBody requestBody = multipartBodyBuilder.build();
        Request.Builder RequestBuilder = new Request.Builder();
        RequestBuilder.url(HttpConfig.BASE_URL+URLConfig.POSTH_UPLOAD_IMG_URL);// 添加URL地址
        RequestBuilder.post(requestBody);
        Request request = RequestBuilder.build();
        mOkHttpClient.newCall(request).enqueue(callback);
    }
}
