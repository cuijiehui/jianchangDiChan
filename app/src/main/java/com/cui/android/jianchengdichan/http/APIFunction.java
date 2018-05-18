package com.cui.android.jianchengdichan.http;

import com.cui.android.jianchengdichan.http.bean.BaseBean;
import com.cui.android.jianchengdichan.http.bean.LoginBean;
import com.cui.android.jianchengdichan.http.bean.SplashAdvBean;
import com.cui.android.jianchengdichan.http.config.URLConfig;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * @author CUI
 * @data 2018/5/16.
 * @description 网络请求类
 */
public interface APIFunction {
    @GET(URLConfig.GET_FULLPAGE_ADS_URL)
    Observable<BaseBean<SplashAdvBean>> getSplashAdv();

    @POST(URLConfig.POST_LOGIN_URL)
    Observable<BaseBean<LoginBean>> getLogin(@Query("mobile") String mobile, @Query("pwd") String pwd);

}
