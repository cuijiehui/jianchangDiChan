package com.cui.android.jianchengdichan.http.config;

/**
 * @author CUI
 * @data 2018/5/16.
 * @description url类
 */
public interface URLConfig {
    String baidu_url="s";
    String login_token_url="获取新token的地址";
    String GET_FULLPAGE_ADS_URL="adlist_large";
    String POST_LOGIN_URL="/api/Login/doLogin";//登录
    String POST_REGISTER_URL="/api/Login/register";//注册
    String POST_GET_TOKEN_URL="/api/System/getServerInfo";//获取token
    String POST_FORGET_PWD_URL="/api/Pwd/setPwd";//忘记密码
    String POST_REGISTER_CODE_URL="app/index.php?i=1&c=entry&m=ewei_shopv2&do=mobile&r=account.apiVerifycode2";
    String POST_HOME_DATA_URL = "/api/Home/homeData";
}
