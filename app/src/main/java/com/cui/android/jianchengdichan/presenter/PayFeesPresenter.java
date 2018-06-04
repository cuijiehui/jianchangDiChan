package com.cui.android.jianchengdichan.presenter;

import com.cui.android.jianchengdichan.http.base.BaseBean;
import com.cui.android.jianchengdichan.http.bean.CatesBean;
import com.cui.android.jianchengdichan.http.bean.ChargeCateBean;
import com.cui.android.jianchengdichan.model.DataModel;
import com.cui.android.jianchengdichan.model.Token;
import com.cui.android.jianchengdichan.model.interfaces.CallBack;
import com.cui.android.jianchengdichan.utils.LogUtils;
import com.cui.android.jianchengdichan.view.ui.PayFeesActivity;
import com.google.gson.JsonObject;

import java.util.List;

public class PayFeesPresenter extends BasePresenter<PayFeesActivity> {
    public void getCates(int uid , String token,String com_id){
        LogUtils.i("getCates()");
        if (!isViewAttached()) {
            //如果没有View引用就不加载数据
            return;
        }
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("uid",uid);
        jsonObject.addProperty("token",token);
        jsonObject.addProperty("com_id",com_id);
        String json =jsonObject.toString();
        DataModel.request(Token.API_CATES_DATA)
                .params(json)
                .execute(new CallBack<BaseBean<List<CatesBean>>>() {

                    @Override
                    public void onSuccess(BaseBean<List<CatesBean>> data) {
                        getView().getCatesBean(data.getData());
                    }

                    @Override
                    public void onFailure(String msg) {
                        getView().showView(msg,-200);
                    }

                    @Override
                    public void onError() {
                        getView().showView("",-200);
                    }

                    @Override
                    public void onComplete() {
                        getView().hideLoading();
                    }
                });
    }
    public void getChargeCate(int uid,String token,String com_id){
        LogUtils.i("getChargeCate()");
        if (!isViewAttached()) {
            //如果没有View引用就不加载数据
            return;
        }
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("uid",uid);
        jsonObject.addProperty("token",token);
        jsonObject.addProperty("com_id",com_id);
        String json =jsonObject.toString();
        DataModel.request(Token.API_CHARGE_CATES_DATA)
                .params(json)
                .execute(new CallBack<BaseBean<List<ChargeCateBean>>>() {

                    @Override
                    public void onSuccess(BaseBean<List<ChargeCateBean>> data) {
                        getView().getChargeCate(data.getData());
                    }

                    @Override
                    public void onFailure(String msg) {
                        getView().showView(msg,-200);
                    }

                    @Override
                    public void onError() {
                        getView().showView("",-200);
                    }

                    @Override
                    public void onComplete() {
                        getView().hideLoading();
                    }
                });
    }
}
