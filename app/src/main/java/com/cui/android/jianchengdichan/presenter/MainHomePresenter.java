package com.cui.android.jianchengdichan.presenter;

import android.text.TextUtils;

import com.cui.android.jianchengdichan.http.base.BaseBean;
import com.cui.android.jianchengdichan.http.bean.AnotherBatchBean;
import com.cui.android.jianchengdichan.http.bean.CarCostBean;
import com.cui.android.jianchengdichan.http.bean.HomeDataBean;
import com.cui.android.jianchengdichan.model.DataModel;
import com.cui.android.jianchengdichan.model.Token;
import com.cui.android.jianchengdichan.model.interfaces.CallBack;
import com.cui.android.jianchengdichan.utils.LogUtils;
import com.cui.android.jianchengdichan.utils.Okhttp3Utils;
import com.cui.android.jianchengdichan.view.ui.fragment.MainHomeFragment;
import com.google.gson.JsonObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class MainHomePresenter extends BasePresenter<MainHomeFragment> {
    public void getData(){
        LogUtils.i("getData()");
        if (!isViewAttached()) {
            //如果没有View引用就不加载数据
            return;
        }
        DataModel.request(Token.API_HOME_DATA)
                .params()
                .execute(new CallBack<BaseBean<HomeDataBean>>() {

                    @Override
                    public void onSuccess(BaseBean<HomeDataBean> data) {
                        getView().getData(data.getData());
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
                        getView().hideLoading();
                    }
                });
    }

    public void getAnotherBatch(){
        LogUtils.i("getAnotherBatch()");
        if (!isViewAttached()) {
            //如果没有View引用就不加载数据
            return;
        }
        DataModel.request(Token.API_GET_ANOTHER_BATCH_MODEL)
                .params()
                .execute(new CallBack<BaseBean<AnotherBatchBean>>() {
                    @Override
                    public void onSuccess(BaseBean<AnotherBatchBean> data) {
                        getView().getAnotherBatch(data.getData().getPics());
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
                        getView().hideLoading();
                    }
                });
    }
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
                        getView().onCallBack(data.getData());
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

    public void getParkCode(String result) {
        if (!isViewAttached()) {
            //如果没有View引用就不加载数据
            return;
        }
        Okhttp3Utils.getInstance().doGet(result, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                getView().onError();

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String body = response.body().string();
                getView().getParkCode(body);
            }
        });
    }
}
