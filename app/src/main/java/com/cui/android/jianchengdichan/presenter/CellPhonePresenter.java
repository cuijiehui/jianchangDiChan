package com.cui.android.jianchengdichan.presenter;

import com.cui.android.jianchengdichan.http.base.BaseBean;
import com.cui.android.jianchengdichan.http.bean.CivilianserviceBean;
import com.cui.android.jianchengdichan.http.bean.TelephoneBean;
import com.cui.android.jianchengdichan.model.DataModel;
import com.cui.android.jianchengdichan.model.Token;
import com.cui.android.jianchengdichan.model.interfaces.CallBack;
import com.cui.android.jianchengdichan.utils.LogUtils;
import com.cui.android.jianchengdichan.view.ui.avtivity.CellPhoneActivity;
import com.google.gson.JsonObject;

import java.util.List;

import retrofit2.http.PUT;

/**
 * @author CUI
 * @data 2018/6/14.
 * @details
 */
public class CellPhonePresenter extends BasePresenter<CellPhoneActivity> {
    public void getPhoneType() {
        DataModel.request(Token.API_CIVILIAN_SERVICE_MODEL)
                .execute(new CallBack<BaseBean<List<CivilianserviceBean>>>() {
                    @Override
                    public void onSuccess(BaseBean<List<CivilianserviceBean>> data) {
                        getView().getPhoneType(data.getData());
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

    public void getPhoneList(int type, int page, int cate_id) {
        LogUtils.i("getPhoneList()");
        if (!isViewAttached()) {
            //如果没有View引用就不加载数据
            return;
        }
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("type", type);
        jsonObject.addProperty("page", page);
        if (cate_id != 0) {
            jsonObject.addProperty("cate_id", cate_id);
        }
        String json = jsonObject.toString();
        LogUtils.i("json=()" + json);
        DataModel.request(Token.API_TELE_PHONE_MODEL)
                .params(json)
                .execute(new CallBack<BaseBean<List<TelephoneBean>>>() {
                    @Override
                    public void onSuccess(BaseBean<List<TelephoneBean>> data) {
                        getView().getPhoneList(data.getData());
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
