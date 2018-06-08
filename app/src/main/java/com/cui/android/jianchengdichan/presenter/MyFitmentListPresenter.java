package com.cui.android.jianchengdichan.presenter;

import com.cui.android.jianchengdichan.http.base.BaseBean;
import com.cui.android.jianchengdichan.http.bean.RenovationBean;
import com.cui.android.jianchengdichan.model.DataModel;
import com.cui.android.jianchengdichan.model.Token;
import com.cui.android.jianchengdichan.model.interfaces.CallBack;
import com.cui.android.jianchengdichan.utils.LogUtils;
import com.cui.android.jianchengdichan.view.ui.MyFitmentListActivity;
import com.google.gson.JsonObject;

import java.util.List;

/**
 * @author CUI
 * @data 2018/6/7.
 * @details
 */
public class MyFitmentListPresenter extends BasePresenter<MyFitmentListActivity> {
    /**
     * {
     "uid":1,
     "token":"ANDrbTAtcbAheTgqQaYJ1owlDZGfDagD",
     "page":"1"
     }
     */
    public void getRenovationInfoList(int uid,String token,String page){
        LogUtils.i("getRenovationInfoList()");
        if (!isViewAttached()) {
            //如果没有View引用就不加载数据
            return;
        }
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("uid", uid);
        jsonObject.addProperty("token", token);
        jsonObject.addProperty("page", page);
        String json = jsonObject.toString();
        LogUtils.i("json=()" + json);
        DataModel.request(Token.API_GET_REN_LIST_MODEL)
                .params(json)
                .execute(new CallBack<BaseBean<List<RenovationBean>>>() {
                    @Override
                    public void onSuccess(BaseBean<List<RenovationBean>> data) {
                        getView().getRenovationInfoList(data.getData());
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
