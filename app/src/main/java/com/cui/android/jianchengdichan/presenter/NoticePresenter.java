package com.cui.android.jianchengdichan.presenter;

import com.cui.android.jianchengdichan.http.base.BaseBean;
import com.cui.android.jianchengdichan.http.bean.NoticeBean;
import com.cui.android.jianchengdichan.model.DataModel;
import com.cui.android.jianchengdichan.model.Token;
import com.cui.android.jianchengdichan.model.interfaces.CallBack;
import com.cui.android.jianchengdichan.utils.LogUtils;
import com.cui.android.jianchengdichan.view.ui.NoticeAcitivty;
import com.google.gson.JsonObject;

import java.util.List;

public class NoticePresenter extends BasePresenter<NoticeAcitivty> {
    /**
     * 参数名	必选	类型	说明
     uid	是	int	用户id
     token	是	string	token
     terminal	是	int	终端类型 1：门口机 2：移动端 3:管理机
     page	是	int	页码
     */
    public void getNoticeList(int uid ,String token ,String terminal ,int page) {
        LogUtils.i("getCates()");
        if (!isViewAttached()) {
            //如果没有View引用就不加载数据
            return;
        }
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("uid",uid);
        jsonObject.addProperty("token",token);
        jsonObject.addProperty("terminal",terminal);
        jsonObject.addProperty("page",page);
        String json =jsonObject.toString();
        DataModel.request(Token.API_GET_NOTICE_LIST_MODEL)
                .params(json)
                .execute(new CallBack<BaseBean<List<NoticeBean>>>() {
                    @Override
                    public void onSuccess(BaseBean<List<NoticeBean>> data) {
                        getView().getNoticeList(data.getData());
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
