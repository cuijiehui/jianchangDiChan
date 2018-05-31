package com.cui.android.jianchengdichan.presenter;

import com.cui.android.jianchengdichan.http.bean.BaseBean;
import com.cui.android.jianchengdichan.http.bean.UserEntranceBean;
import com.cui.android.jianchengdichan.model.DataModel;
import com.cui.android.jianchengdichan.model.Token;
import com.cui.android.jianchengdichan.model.interfaces.CallBack;
import com.cui.android.jianchengdichan.utils.LogUtils;
import com.cui.android.jianchengdichan.view.ui.CommAddListAtivity;
import com.google.gson.JsonObject;

import java.util.List;

public class CommAddListPresenter extends BasePresenter<CommAddListAtivity> {

    public void getUserEntrance(int uid,String token){
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("uid",uid);
        jsonObject.addProperty("token",token);
        String json =jsonObject.toString();
        LogUtils.i("json=()"+json);
        DataModel.request(Token.API_USER_ENTRANCE_IMG)
                .params()
                .execute(new CallBack<BaseBean<List<UserEntranceBean>>>() {
                    @Override
                    public void onSuccess(BaseBean<List<UserEntranceBean>> data) {
                        getView().getUserEnterance(data.getData());
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
