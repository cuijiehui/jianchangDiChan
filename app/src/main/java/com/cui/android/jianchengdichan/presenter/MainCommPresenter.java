package com.cui.android.jianchengdichan.presenter;

import com.cui.android.jianchengdichan.http.base.BaseBean;
import com.cui.android.jianchengdichan.http.bean.HomeDataBean;
import com.cui.android.jianchengdichan.http.bean.NoticeThreelistBean;
import com.cui.android.jianchengdichan.model.DataModel;
import com.cui.android.jianchengdichan.model.Token;
import com.cui.android.jianchengdichan.model.interfaces.CallBack;
import com.cui.android.jianchengdichan.utils.LogUtils;
import com.cui.android.jianchengdichan.view.ui.Fragment.MainCommFragment;
import com.google.gson.JsonObject;

import java.util.List;

public class MainCommPresenter  extends BasePresenter<MainCommFragment> {
    public void getAdList(int uid,String token,String terminal,String display){
        LogUtils.i("getAdList()");
        if (!isViewAttached()) {
            //如果没有View引用就不加载数据
            return;
        }
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("uid",uid);
        jsonObject.addProperty("token",token);
        jsonObject.addProperty("terminal",terminal);
        jsonObject.addProperty("display",display);
        String json =jsonObject.toString();
        DataModel.request(Token.API_GET_GETAD_LIST_MODEL)
                .params(json)
                .execute(new CallBack<BaseBean<List<HomeDataBean.AdBean>>>() {
                    @Override
                    public void onSuccess(BaseBean<List<HomeDataBean.AdBean>> data) {
                        getView().getAdList(data.getData());
                    }

                    @Override
                    public void onFailure(String msg) {

                    }

                    @Override
                    public void onError() {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
    public void getNoticeList(int uid,String token,String terminal){
        LogUtils.i("getAdList()");
        if (!isViewAttached()) {
            //如果没有View引用就不加载数据
            return;
        }
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("uid",uid);
        jsonObject.addProperty("token",token);
        jsonObject.addProperty("terminal",terminal);
        String json =jsonObject.toString();
        DataModel.request(Token.API_NOTICE_LIST_MODEL)
                .params(json)
                .execute(new CallBack<BaseBean<List<NoticeThreelistBean>>>() {
                    @Override
                    public void onSuccess(BaseBean<List<NoticeThreelistBean>> data) {
                        getView().getNoticeList(data.getData());
                    }

                    @Override
                    public void onFailure(String msg) {

                    }

                    @Override
                    public void onError() {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
