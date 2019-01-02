package com.cui.android.jianchengdichan.presenter;

import com.cui.android.jianchengdichan.http.bean.AliPayBean;
import com.cui.android.jianchengdichan.http.base.BaseBean;
import com.cui.android.jianchengdichan.http.bean.WeChatPayBean;
import com.cui.android.jianchengdichan.model.DataModel;
import com.cui.android.jianchengdichan.model.Token;
import com.cui.android.jianchengdichan.model.interfaces.CallBack;
import com.cui.android.jianchengdichan.view.ui.avtivity.PayingActivity;
import com.google.gson.JsonObject;

public class PayingPresenter extends BasePresenter<PayingActivity> {
    public void getWeixin(int uid, String token, String id,String type) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("uid", uid);
        jsonObject.addProperty("token", token);
        jsonObject.addProperty("id", id);
        jsonObject.addProperty("type", type);
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

    public void getAliPay(int uid, String token, String id) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("uid", uid);
        jsonObject.addProperty("token", token);
        jsonObject.addProperty("id", id);
        String json = jsonObject.toString();
        DataModel.request(Token.API_ALI_PAY_IMG)
                .params(json)
                .execute(new CallBack<BaseBean<AliPayBean>>() {
                    @Override
                    public void onSuccess(BaseBean<AliPayBean> data) {
                        getView().getAliPay(data.getData());
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
