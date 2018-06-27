package com.cui.android.jianchengdichan.presenter;

import com.cui.android.jianchengdichan.http.base.BaseBean;
import com.cui.android.jianchengdichan.http.bean.LeaveMsgListBean;
import com.cui.android.jianchengdichan.http.bean.RentDetailBean;
import com.cui.android.jianchengdichan.model.DataModel;
import com.cui.android.jianchengdichan.model.Token;
import com.cui.android.jianchengdichan.model.interfaces.CallBack;
import com.cui.android.jianchengdichan.utils.LogUtils;
import com.cui.android.jianchengdichan.view.ui.avtivity.RentDatailActivity;
import com.google.gson.JsonObject;

import java.util.List;

public class RentDatailPresenter extends BasePresenter<RentDatailActivity> {

    public void rentDetail(int uid, String token, String id) {
        if (!isViewAttached()) {
            //如果没有View引用就不加载数据
            return;
        }
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

    /**
     * 参数名	必选	类型	说明
     id	是	int	租赁信息id
     page	是	int	页数
     uid	是	int	用户id
     token	是	string	用户token
     */
    public void leaveMsgList(String id,int page,int uid ,String token){
        if (!isViewAttached()) {
            //如果没有View引用就不加载数据
            return;
        }
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("uid",uid);
        jsonObject.addProperty("token",token);
        jsonObject.addProperty("id",id);
        jsonObject.addProperty("page",page);
        String json =jsonObject.toString();
        LogUtils.i("json=()"+json);
        DataModel.request(Token.API_LEAVE_MSG_LIST_MODEL)
                .params(json)
                .execute(new CallBack<BaseBean<List<LeaveMsgListBean>>>() {
                    @Override
                    public void onSuccess(BaseBean<List<LeaveMsgListBean>> data) {
                        getView().leaveMsgList(data.getData());
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
    /**
     参数名	必选	类型	说明
     id	是	int	租赁信息id
     content	是	string	留言内容
     uid	是	int	用户id
     token	是	string	用户token
     */
    public void leaveMsg(String id,int uid ,String token,String content){
        if (!isViewAttached()) {
            //如果没有View引用就不加载数据
            return;
        }
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("uid",uid);
        jsonObject.addProperty("token",token);
        jsonObject.addProperty("id",id);
        jsonObject.addProperty("content",content);
        String json =jsonObject.toString();
        LogUtils.i("json=()"+json);
        DataModel.request(Token.API_LEAVE_MSG_MODEL)
                .params(json)
                .execute(new CallBack<BaseBean<Object>>() {
                    @Override
                    public void onSuccess(BaseBean<Object> data) {
                        getView().leaveMsg();
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
