package com.cui.android.jianchengdichan.http;

import com.cui.android.jianchengdichan.http.base.BasesBean;
import com.cui.android.jianchengdichan.http.bean.AliPayBean;
import com.cui.android.jianchengdichan.http.base.BaseBean;
import com.cui.android.jianchengdichan.http.bean.AnotherBatchBean;
import com.cui.android.jianchengdichan.http.bean.CatesBean;
import com.cui.android.jianchengdichan.http.bean.ChargeCateBean;
import com.cui.android.jianchengdichan.http.bean.CityListBean;
import com.cui.android.jianchengdichan.http.bean.CommunityBean;
import com.cui.android.jianchengdichan.http.bean.HistoryDataBean;
import com.cui.android.jianchengdichan.http.bean.HomeDataBean;
import com.cui.android.jianchengdichan.http.bean.LeaseRoomBean;
import com.cui.android.jianchengdichan.http.bean.LoginBean;
import com.cui.android.jianchengdichan.http.bean.MyApplyBean;
import com.cui.android.jianchengdichan.http.bean.NoticeBean;
import com.cui.android.jianchengdichan.http.bean.NoticeThreelistBean;
import com.cui.android.jianchengdichan.http.bean.PayRecordsBean;
import com.cui.android.jianchengdichan.http.bean.RentDetailBean;
import com.cui.android.jianchengdichan.http.bean.RepairsBean;
import com.cui.android.jianchengdichan.http.bean.SplashAdvBean;
import com.cui.android.jianchengdichan.http.bean.UserCommunityBean;
import com.cui.android.jianchengdichan.http.bean.UserEntranceBean;
import com.cui.android.jianchengdichan.http.bean.UserInfoPicBean;
import com.cui.android.jianchengdichan.http.bean.WeChatPayBean;
import com.cui.android.jianchengdichan.http.config.URLConfig;
import com.cui.android.jianchengdichan.view.ui.customview.ChildCommunityBean;

import java.util.List;

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

    @POST(URLConfig.POST_CATES_URL)
    Observable<BaseBean<List<CatesBean>>> getCates(@Body RequestBody route);

    @POST(URLConfig.POST_CHARGE_CATES_URL)
    Observable<BaseBean<List<ChargeCateBean>>> getChargeCates(@Body RequestBody route);

    @POST(URLConfig.POST_CHARGES_RECORD_URL)
    Observable<BaseBean<List<PayRecordsBean>>> getChargeRecord(@Body RequestBody route);

    @POST(URLConfig.POSTH_FEEDBACK_URL)
    Observable<BaseBean> getOpinion(@Body RequestBody route);

    @POST(URLConfig.POSTH_FEEDBACK_LIST_URL)
    Observable<BaseBean<List<HistoryDataBean>>> getOpinionList(@Body RequestBody route);

    @POST(URLConfig.POSTH_DEL_FEEDBACK_URL)
    Observable<BaseBean> delOpinion(@Body RequestBody route);

    @POST(URLConfig.POSTH_CITY_LIST_URL)
    Observable<BaseBean<List<CityListBean>>> getCityList(@Body RequestBody route);

    @POST(URLConfig.POSTH_RENT_LIST_URL)
    Observable<BaseBean<List<LeaseRoomBean>>> getRentList(@Body RequestBody route);
    @POST(URLConfig.POSTH_RENT_DETAIL_URL)
    Observable<BaseBean<RentDetailBean>> getRentDetail(@Body RequestBody route);

    @POST(URLConfig.POSTH_WX_PAY_URL)
    Observable<BaseBean<WeChatPayBean>> getWeChatPay(@Body RequestBody route);
    @POST(URLConfig.POSTH_ALI_PAY_URL)
    Observable<BaseBean<AliPayBean>> getAliPay(@Body RequestBody route);

    @POST(URLConfig.POSTH_USER_ENTRAN_URL)
    Observable<BaseBean<List<UserEntranceBean>>> getUserEntrance(@Body RequestBody route);
    @POST(URLConfig.POSTH_USER_COMMUNIT_URL)
    Observable<BaseBean<UserCommunityBean>> setUserCommunity(@Body RequestBody route);

    @POST(URLConfig.POSTH_COMMUNITY_LIST_URL)
    Observable<BaseBean<List<CommunityBean>>> getCommunityList(@Body RequestBody route);

    @POST(URLConfig.POSTH_UNIT_LIST_URL)
    Observable<BaseBean<List<ChildCommunityBean>>> getUnitList(@Body RequestBody route);

    @POST(URLConfig.POSTH_PROPERTY_LIST_URL)
    Observable<BaseBean<List<ChildCommunityBean>>> getPropertyList(@Body RequestBody route);
    @POST(URLConfig.POSTH_USER_INFO_URL)
    Observable<BasesBean> setUserInfo(@Body RequestBody route);

    @POST(URLConfig.POSTH_USER_INFO_URL)
    Observable<BaseBean<UserInfoPicBean>> setUserInfoPic(@Body RequestBody route);

    @POST(URLConfig.POSTH_REPAIR_INFO_URL)
    Observable<BasesBean> submitRepairInfo(@Body RequestBody route);


    @POST(URLConfig.POSTH_REPAIR_INFO_LIST_URL)
    Observable<BaseBean<List<RepairsBean>>> getRepairInfoList(@Body RequestBody route);

    @POST(URLConfig.POSTH_REPAIR_RENT_INFO_URL)
    Observable<BasesBean> publishRentInfo(@Body RequestBody route);

    @POST(URLConfig.POSTH_REPAIR_DEL_RENTINFO_URL)
    Observable<BasesBean> delRentInfo(@Body RequestBody route);

    @POST(URLConfig.POSTH_REPAIR_MY_APPLY_URL)
    Observable<BaseBean<List<MyApplyBean>>> myApply(@Body RequestBody route);


    @POST(URLConfig.POSTH_GET_NOTICE_LIST_URL)
    Observable<BaseBean<List<NoticeBean>>> getNoticeList(@Body RequestBody route);

    @POST(URLConfig.POSTH_ANOTHER_BATCH_URL)
    Observable<BaseBean<AnotherBatchBean>> getAnotherBatch();

    @POST(URLConfig.POSTH_AD_GET_LIST_URL)
    Observable<BaseBean<List<HomeDataBean.AdBean>>> getAdList(@Body RequestBody route);

    @POST(URLConfig.POSTH_NOTICE_GET_THREELIST_URL)
    Observable<BaseBean<List<NoticeThreelistBean>>> getNoticeThreelist(@Body RequestBody route);
}
