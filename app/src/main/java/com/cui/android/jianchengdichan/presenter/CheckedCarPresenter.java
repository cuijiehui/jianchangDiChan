package com.cui.android.jianchengdichan.presenter;

import android.text.TextUtils;

import com.alibaba.fastjson.JSON;
import com.cui.android.jianchengdichan.http.base.BaseBean;
import com.cui.android.jianchengdichan.http.bean.CarChargeLogBean;
import com.cui.android.jianchengdichan.http.bean.CarCostBean;
import com.cui.android.jianchengdichan.model.DataModel;
import com.cui.android.jianchengdichan.model.Token;
import com.cui.android.jianchengdichan.model.interfaces.CallBack;
import com.cui.android.jianchengdichan.utils.LogUtils;
import com.cui.android.jianchengdichan.view.ui.avtivity.CheckedCarActivity;
import com.google.gson.JsonObject;

import java.util.List;

public class CheckedCarPresenter extends BasePresenter<CheckedCarActivity> {
    public void checkedCarCost(String carNo, String parkCode) {
        LogUtils.i("getPhoneList()");
        if (!isViewAttached()) {
            //如果没有View引用就不加载数据
            return;
        }
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("carNo", carNo);
        if (!TextUtils.isEmpty(parkCode)) {
            jsonObject.addProperty("parkCode", parkCode);
        }
        String json = jsonObject.toString();
        DataModel.request(Token.API_CARGOING_COST_MODEL)
                .params(json)
                .execute(new CallBack<BaseBean<CarCostBean>>() {
                    @Override
                    public void onSuccess(BaseBean<CarCostBean> data) {
                        getView().checkedCarCost(data.getData());
                    }

                    @Override
                    public void onFailure(String msg) {
                        getView().onFailure(msg);
                    }

                    @Override
                    public void onError() {
                        getView().onError();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
    public void getChargeLog(String carNo, String page) {
        LogUtils.i("getPhoneList()");
        if (!isViewAttached()) {
            //如果没有View引用就不加载数据
            return;
        }
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("carNo", carNo);
        jsonObject.addProperty("page", page);
        String json = jsonObject.toString();
        DataModel.request(Token.API_CAR_CHARGE_LOG_MODEL)
                .params(json)
                .execute(new CallBack<BaseBean<List<CarChargeLogBean>>>() {
                    @Override
                    public void onSuccess(BaseBean<List<CarChargeLogBean>> data) {
                        getView().getChargeLog(data.getData());
                    }

                    @Override
                    public void onFailure(String msg) {
                        getView().onFailure(msg);
                    }

                    @Override
                    public void onError() {
                        getView().onError();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
