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
    String POST_REGISTER_CODE_URL="app/index.php?i=1&c=entry&m=ewei_shopv2&do=mobile&r=account.apiVerifycode2";//获取验证码
    String POST_HOME_DATA_URL = "/api/Home/homeData";//获取主页信息
    String POST_CATES_URL = "/api/Charge/getCates";//缴费类列表
   String POST_CHARGE_CATES_URL="/api/Charge/getChargeCate"; //待缴费列表
   String POST_CHARGES_RECORD_URL="/api/Charge/chargeRecord"; //缴费记录
    String POSTH_FEEDBACK_URL ="/api/Opinion/submitOpinion";//意见反馈接口
    String POSTH_FEEDBACK_LIST_URL ="/api/Opinion/getOpinionList";//反馈意见列表
    String POSTH_DEL_FEEDBACK_URL ="/api/Opinion/delOpinion";//反馈意见列表
    String POSTH_CITY_LIST_URL ="/api/City/getArea";//获取城市列表接口
    String POSTH_RENT_LIST_URL ="/api/Rent/rentList";//获取租赁信息列表接口
    String POSTH_RENT_DETAIL_URL ="/api/Rent/rentDetail";//租赁信息详情接口
    String POSTH_UPLOAD_IMG_URL ="/api/Home/uploadImg";//图片上传接口
    String POSTH_WX_PAY_URL ="/api/Wxpay/genChargeOrder";//用微信支付统一下单
    String POSTH_ALI_PAY_URL ="api/Alipay/genChargeOrder";//用支付宝支付统一下单
    String POSTH_USER_ENTRAN_URL ="/api/Area/getUserEntrance";//门禁申请记录接口
    String POSTH_USER_COMMUNIT_URL ="/api/Area/setUserCommunity";//提交申请信息（姓名，身份，社区楼栋房间信息）
    String POSTH_COMMUNITY_LIST_URL ="/api/Area/getCommunityList";//获取社区楼栋列表接口
    String POSTH_UNIT_LIST_URL ="/api/Area/getUnitList";//获取社区楼栋列表接口
    String POSTH_PROPERTY_LIST_URL ="/api/Area/getPropertyList";//获取社区楼栋列表接口


}
