package com.cui.android.jianchengdichan.presenter;

import android.text.TextUtils;

import com.cui.android.jianchengdichan.http.base.BaseBean;
import com.cui.android.jianchengdichan.http.bean.CarGoingBean;
import com.cui.android.jianchengdichan.http.bean.CreateOrderBean;
import com.cui.android.jianchengdichan.http.bean.WeChatPayBean;
import com.cui.android.jianchengdichan.model.DataModel;
import com.cui.android.jianchengdichan.model.Token;
import com.cui.android.jianchengdichan.model.interfaces.CallBack;
import com.cui.android.jianchengdichan.utils.LogUtils;
import com.cui.android.jianchengdichan.view.ui.avtivity.ParkingPaymentActivity;
import com.google.gson.JsonObject;

import java.util.List;

public class ParkingPaymentPresenter extends BasePresenter<ParkingPaymentActivity> {

    public void createOrder(String carNo,String parkCode){
        if (!isViewAttached()) {
            //如果没有View引用就不加载数据
            return;
        }
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("carNo",carNo);
        if (!TextUtils.isEmpty(parkCode)) {
            jsonObject.addProperty("parkCode",parkCode);
        }
        String json =jsonObject.toString();
        DataModel.request(Token.API_CREATE_ORDER_MODEL)
                .params(json)
                .execute(new CallBack<BaseBean<CreateOrderBean>>() {
                    @Override
                    public void onSuccess(BaseBean<CreateOrderBean> data) {
                        getView().getCreateOrder(data.getData());
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
    public void cancelOrder(String orderNo){
        if (!isViewAttached()) {
            //如果没有View引用就不加载数据
            return;
        }
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("orderNo",orderNo);
        String json =jsonObject.toString();
        DataModel.request(Token.API_CREATE_CANCEL_MODEL)
                .params(json)
                .execute(new CallBack<BaseBean<String>>() {
                    @Override
                    public void onSuccess(BaseBean<String> data) {
                        getView().cancel();
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
    public void getWeixin(int uid, String token, String id) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("uid", uid);
        jsonObject.addProperty("token", token);
        jsonObject.addProperty("id", id);
        String json = jsonObject.toString();
        DataModel.request(Token.API_WE_CHAT_IMG)
                .params(json)
                .execute(new CallBack<BaseBean<WeChatPayBean>>() {
                    @Override
                    public void onSuccess(BaseBean<WeChatPayBean> data) {
                        getView().getWeChatPay(data.getData());
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
