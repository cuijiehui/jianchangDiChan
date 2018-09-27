package com.cui.android.jianchengdichan.presenter;

import com.cui.android.jianchengdichan.http.base.BaseBean;
import com.cui.android.jianchengdichan.http.bean.LoginBean;
import com.cui.android.jianchengdichan.model.DataModel;
import com.cui.android.jianchengdichan.model.Token;
import com.cui.android.jianchengdichan.model.interfaces.CallBack;
import com.cui.android.jianchengdichan.utils.LogUtils;
import com.cui.android.jianchengdichan.view.ui.fragment.MainMyFragment;
import com.google.gson.JsonObject;

/**
 * @author CUI
 * @data 2018/6/8.
 * @details
 */
public class MainMyPresenter  extends BasePresenter<MainMyFragment>{
    public void getUserInfo(int uid,String token){
        LogUtils.i("getRenovationInfoList()");
        if (!isViewAttached()) {
            //如果没有View引用就不加载数据
            return;
        }
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("uid", uid);
        jsonObject.addProperty("token", token);
        String json = jsonObject.toString();
        DataModel.request(Token.API_GET_USER_INFO_MODEL)
                .params(json)
                .execute(new CallBack<BaseBean<LoginBean>>() {
                    @Override
                    public void onSuccess(BaseBean<LoginBean> data) {
                        getView().getUserInfo(data.getData());
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
