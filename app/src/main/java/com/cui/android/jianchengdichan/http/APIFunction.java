package com.cui.android.jianchengdichan.http;

import com.cui.android.jianchengdichan.http.base.BasesBean;
import com.cui.android.jianchengdichan.http.bean.AliPayBean;
import com.cui.android.jianchengdichan.http.base.BaseBean;
import com.cui.android.jianchengdichan.http.bean.AnotherBatchBean;
import com.cui.android.jianchengdichan.http.bean.CatesBean;
import com.cui.android.jianchengdichan.http.bean.ChargeCateBean;
import com.cui.android.jianchengdichan.http.bean.CityListBean;
import com.cui.android.jianchengdichan.http.bean.CivilianAdvBean;
import com.cui.android.jianchengdichan.http.bean.CivilianDetailBean;
import com.cui.android.jianchengdichan.http.bean.CivilianListBean;
import com.cui.android.jianchengdichan.http.bean.CommentActBean;
import com.cui.android.jianchengdichan.http.bean.CommentTopicBean;
import com.cui.android.jianchengdichan.http.bean.HomeCivilianListBean;
import com.cui.android.jianchengdichan.http.bean.CivilianserviceBean;
import com.cui.android.jianchengdichan.http.bean.CommunityBean;
import com.cui.android.jianchengdichan.http.bean.HistoryDataBean;
import com.cui.android.jianchengdichan.http.bean.HomeDataBean;
import com.cui.android.jianchengdichan.http.bean.LeaseRoomBean;
import com.cui.android.jianchengdichan.http.bean.LoginBean;
import com.cui.android.jianchengdichan.http.bean.MyApplyBean;
import com.cui.android.jianchengdichan.http.bean.NoticeBean;
import com.cui.android.jianchengdichan.http.bean.NoticeThreelistBean;
import com.cui.android.jianchengdichan.http.bean.PayRecordsBean;
import com.cui.android.jianchengdichan.http.bean.RenovationBean;
import com.cui.android.jianchengdichan.http.bean.RentDetailBean;
import com.cui.android.jianchengdichan.http.bean.RepairCateBean;
import com.cui.android.jianchengdichan.http.bean.RepairsBean;
import com.cui.android.jianchengdichan.http.bean.SplashAdvBean;
import com.cui.android.jianchengdichan.http.bean.TelephoneBean;
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

    @POST(URLConfig.POSTH_SUBMIT_RENOVATION_INFO_URL)
    Observable<BasesBean> submitRenovationInfo(@Body RequestBody route);

    @POST(URLConfig.POSTH_DEL_RENOVATION_INFO_URL)
    Observable<BasesBean> delRenovationInfo(@Body RequestBody route);

    @POST(URLConfig.POSTH_UPDATE_RENT_URL)
    Observable<BasesBean> updateRent(@Body RequestBody route);

    @POST(URLConfig.POSTH_REPAIR_MY_APPLY_URL)
    Observable<BaseBean<List<MyApplyBean>>> myApply(@Body RequestBody route);

    @POST(URLConfig.POSTH_GET_RENOVATION_INFO_URL)
    Observable<BaseBean<List<RenovationBean>>> getRenovationList(@Body RequestBody route);

    @POST(URLConfig.POSTH_GET_REPAIR_CATE_URL)
    Observable<BaseBean<List<RepairCateBean>>> getRepairCate();

    @POST(URLConfig.POSTH_GET_NOTICE_LIST_URL)
    Observable<BaseBean<List<NoticeBean>>> getNoticeList(@Body RequestBody route);

    @POST(URLConfig.POSTH_ANOTHER_BATCH_URL)
    Observable<BaseBean<AnotherBatchBean>> getAnotherBatch();

    @POST(URLConfig.POSTH_AD_GET_LIST_URL)
    Observable<BaseBean<List<HomeDataBean.AdBean>>> getAdList(@Body RequestBody route);

    @POST(URLConfig.POSTH_NOTICE_GET_THREELIST_URL)
    Observable<BaseBean<List<NoticeThreelistBean>>> getNoticeThreelist(@Body RequestBody route);
    @POST(URLConfig.POSTH_GET_USER_INFO_URL)
    Observable<BaseBean<LoginBean>> getUserInfo(@Body RequestBody route);

    @POST(URLConfig.POSTH_LEAVE_MSG_LIST_URL)
    Observable<BaseBean<LoginBean>> leaveMsgList(@Body RequestBody route);

    @POST(URLConfig.POSTH_CIVILIAN_SERVICE_URL)
    Observable<BaseBean<HomeCivilianListBean>> getHomeCivilianList();

    @POST(URLConfig.POSTH_CIVILIAN_DETAIL_URL)
    Observable<BaseBean<CivilianDetailBean>> getCivilianDetail(@Body RequestBody route);

    @POST(URLConfig.POSTH_TELEPHONE_LIST_URL)
    Observable<BaseBean<List<TelephoneBean>>> getTelephone(@Body RequestBody route);

    @POST(URLConfig.POSTH_CIVILIANSERVICE_CATE_URL)
    Observable<BaseBean<List<CivilianserviceBean>>> getCivilianservice();

    @POST(URLConfig.POSTH_CIVILIANSERVICE_LIST_URL)
    Observable<BaseBean<List<CivilianListBean>>> getCivilianList(@Body RequestBody route);

    @POST(URLConfig.POSTH_CIVILIANSERVICE_AD_URL)
    Observable<BaseBean<List<CivilianAdvBean>>> getCivilianAdv(@Body RequestBody route);

    @POST(URLConfig.POSTH_COMMENT_ACTIVITY_URL)
    Observable<BaseBean<CommentActBean>> getCommentAct();

    @POST(URLConfig.POSTH_COMMENT_TOPIC_URL)
    Observable<BaseBean<List<CommentTopicBean>>> getCommentTopic();

    @POST(URLConfig.POSTH_RELEASE_TOPIC_URL)
    Observable<BaseBean<Object>> setReleaseTopic(@Body RequestBody route);
}
