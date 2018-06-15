package com.cui.android.jianchengdichan.presenter;

import android.text.TextUtils;

import com.cui.android.jianchengdichan.http.base.BaseBean;
import com.cui.android.jianchengdichan.http.bean.CivilianAdvBean;
import com.cui.android.jianchengdichan.http.bean.CivilianListBean;
import com.cui.android.jianchengdichan.model.DataModel;
import com.cui.android.jianchengdichan.model.Token;
import com.cui.android.jianchengdichan.model.interfaces.CallBack;
import com.cui.android.jianchengdichan.utils.LogUtils;
import com.cui.android.jianchengdichan.view.ui.avtivity.ConveColumnActivity;
import com.google.gson.JsonObject;

import java.util.List;

/**
 * @author CUI
 * @data 2018/6/14.
 * @details
 */
public class ConveColumnPresenter extends BasePresenter<ConveColumnActivity> {
    /**
     * {
     "type":1,
     "key":"医院",
     "is_recommend":1,
     "page":1
     }
     */
    public void getColumnData(int type,String key,String is_recommend,int page) {
        LogUtils.i("getDetail()");
        if (!isViewAttached()) {
            //如果没有View引用就不加载数据
            return;
        }
        JsonObject jsonObject = new JsonObject();
        if(type!=0){
            jsonObject.addProperty("type", type);
        }
        if(!TextUtils.isEmpty(key)){
            jsonObject.addProperty("key", key);
        }
        if(!TextUtils.isEmpty(is_recommend)){
            jsonObject.addProperty("is_recommend", is_recommend);
        }
        jsonObject.addProperty("page", page);

        String json = jsonObject.toString();
        LogUtils.i("json=()" + json);
        DataModel.request(Token.API_CIVILIAN_LISTS_MODEL)
                .params(json)
                .execute(new CallBack<BaseBean<List<CivilianListBean>>>() {
                    @Override
                    public void onSuccess(BaseBean<List<CivilianListBean>> data) {
                        getView().getColumnData(data.getData());
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
    public void getColumnAdv(int type) {
        LogUtils.i("getDetail()");
        if (!isViewAttached()) {
            //如果没有View引用就不加载数据
            return;
        }
        JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("type", type);
        String json = jsonObject.toString();
        LogUtils.i("json=()" + json);
        DataModel.request(Token.API_CIVILIAN_ADV_MODEL)
                .params(json)
                .execute(new CallBack<BaseBean<List<CivilianAdvBean>>>() {
                    @Override
                    public void onSuccess(BaseBean<List<CivilianAdvBean>> data) {
                        getView().getColumnAdv(data.getData());
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
