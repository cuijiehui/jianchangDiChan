package com.cui.android.jianchengdichan.http;

import com.cui.android.jianchengdichan.http.bean.BaseBean;
import com.cui.android.jianchengdichan.http.bean.HomeDataBean;
import com.cui.android.jianchengdichan.http.bean.LoginBean;
import com.cui.android.jianchengdichan.http.bean.SplashAdvBean;
import com.cui.android.jianchengdichan.http.config.URLConfig;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * @author CUI
 * @data 2018/5/16.
 * @description 网络请求类
 */
public interface APIFunction {
    @GET(URLConfig.GET_FULLPAGE_ADS_URL)
    Observable<BaseBean<SplashAdvBean>> getSplashAdv();

    @POST(URLConfig.POST_LOGIN_URL)
    Observable<BaseBean<LoginBean>> getLogin(@Body RequestBody route);

    @POST(URLConfig.POST_REGISTER_URL)
    Observable<BaseBean> getRegister(@Body RequestBody route);

    @POST(URLConfig.POST_REGISTER_CODE_URL)
    Observable<BaseBean> getRegisterCode(@Body RequestBody route);

    @POST(URLConfig.POST_FORGET_PWD_URL)
    Observable<BaseBean> getForgetPwd(@Body RequestBody route);

    @POST(URLConfig.POST_HOME_DATA_URL)
    Observable<BaseBean<HomeDataBean>> getHomeData();


}
