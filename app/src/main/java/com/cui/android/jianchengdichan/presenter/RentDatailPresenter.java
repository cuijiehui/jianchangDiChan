package com.cui.android.jianchengdichan.presenter;

import com.cui.android.jianchengdichan.http.bean.BaseBean;
import com.cui.android.jianchengdichan.http.bean.RentDetailBean;
import com.cui.android.jianchengdichan.model.DataModel;
import com.cui.android.jianchengdichan.model.Token;
import com.cui.android.jianchengdichan.model.interfaces.CallBack;
import com.cui.android.jianchengdichan.utils.LogUtils;
import com.cui.android.jianchengdichan.view.ui.RentDatailActivity;
import com.google.gson.JsonObject;

public class RentDatailPresenter extends BasePresenter<RentDatailActivity> {
    public void rentDetail(int uid, String token, String id) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("uid",uid);
        jsonObject.addProperty("token",token);
        jsonObject.addProperty("id",id);
        String json =jsonObject.toString();
        LogUtils.i("json=()"+json);
        DataModel.request(Token.API_RENT_DEATAIL)
                .params(json)
                .execute(new CallBack<BaseBean<RentDetailBean>>() {
                    @Override
                    public void onSuccess(BaseBean<RentDetailBean> data) {
                        getView().getRentDetail(data.getData());
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
