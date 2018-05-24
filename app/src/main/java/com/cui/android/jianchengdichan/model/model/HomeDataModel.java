package com.cui.android.jianchengdichan.model.model;

import com.cui.android.jianchengdichan.MyApplication;
import com.cui.android.jianchengdichan.http.RetrofitFactory;
import com.cui.android.jianchengdichan.http.base.BaseObserver;
import com.cui.android.jianchengdichan.http.bean.BaseBean;
import com.cui.android.jianchengdichan.http.bean.HomeDataBean;
import com.cui.android.jianchengdichan.http.bean.LoginBean;
import com.cui.android.jianchengdichan.model.base.BaseModel;
import com.cui.android.jianchengdichan.model.interfaces.CallBack;
import com.cui.android.jianchengdichan.utils.LogUtils;
import com.cui.android.jianchengdichan.utils.SPKey;
import com.cui.android.jianchengdichan.utils.SPUtils;
import com.google.gson.Gson;

import java.util.List;

import io.reactivex.Observer;

public class HomeDataModel extends BaseModel<BaseBean<HomeDataBean>> {
    @Override
    public void execute(final CallBack<BaseBean<HomeDataBean>> callback) {
        RetrofitFactory.getInstence().API()
                .getHomeData()
                .compose(MyApplication.getInstance().<BaseBean<HomeDataBean>>setThread())
                .subscribe(new BaseObserver<HomeDataBean>() {
                    @Override
                    protected void onSuccees(BaseBean<HomeDataBean> t) throws Exception {
                        LogUtils.i("onResponse" + t.getData().toString());
                        saveData(t.getData());
                        callback.onSuccess(t);
                    }

                    @Override
                    protected void onCodeError(BaseBean<HomeDataBean> t) throws Exception {
                        LogUtils.i("onResponse" + t.toString());
                        callback.onFailure(t.getMsg());

                    }

                    @Override
                    protected void onFailure(Throwable e, boolean isNetWorkError) throws Exception {
                        LogUtils.i("onResponse" + e.getLocalizedMessage());
                        callback.onError();
                    }
                });
    }

    private void saveData(HomeDataBean data) {
        Gson gson = new Gson();
        List<HomeDataBean.AdBean> adBeanList = data.getAd();
        String adBeans = gson.toJson(adBeanList);
//        LogUtils.i("AdBean=" + adBeans);
        SPUtils.INSTANCE.setSPValue(SPKey.SP_HOME_DATA_ADV_KEY, adBeans);
        List<HomeDataBean.FavorBean> favorBeanList = data.getFavor();
        String favorBeans = gson.toJson(favorBeanList);
//        LogUtils.i("favorBeans=" + favorBeans);
        SPUtils.INSTANCE.setSPValue(SPKey.SP_HOME_DATA_FAVOR_KEY, favorBeans);
        List<HomeDataBean.NewgoodBean> newgoodBeanList = data.getNewgood();
        String newGoodBeans = gson.toJson(newgoodBeanList);
//        LogUtils.i("newGoodBeans=" + newGoodBeans);
        SPUtils.INSTANCE.setSPValue(SPKey.SP_HOME_DATA_NEWGOOD_KEY, newGoodBeans);
        List<HomeDataBean.NoticeBean> noticeBeanList = data.getNotice();
        String noticeBeans = gson.toJson(noticeBeanList);
//        LogUtils.i("noticeBeans=" + noticeBeans);
        SPUtils.INSTANCE.setSPValue(SPKey.SP_HOME_DATA_NOTICE_KEY, noticeBeans);
        List<HomeDataBean.RentBean> rentBeanList = data.getRent();
        String rentBeans = gson.toJson(rentBeanList);
//        LogUtils.i("rentBeans=" + rentBeans);
        SPUtils.INSTANCE.setSPValue(SPKey.SP_HOME_DATA_RENT_KEY, rentBeans);
        HomeDataBean.LimitTimeBean limit_time = data.getLimit_time();
        String limitTime = gson.toJson(limit_time);
//        LogUtils.i("limitTime=" + limitTime);
        SPUtils.INSTANCE.setSPValue(SPKey.SP_HOME_DATA_LIMIT_TIME_KEY, limitTime);

    }

}
