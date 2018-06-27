package com.cui.android.jianchengdichan.presenter;

import com.cui.android.jianchengdichan.http.base.BaseBean;
import com.cui.android.jianchengdichan.http.bean.CommentListBean;
import com.cui.android.jianchengdichan.http.bean.UserEntranceBean;
import com.cui.android.jianchengdichan.model.DataModel;
import com.cui.android.jianchengdichan.model.Token;
import com.cui.android.jianchengdichan.model.interfaces.CallBack;
import com.cui.android.jianchengdichan.utils.LogUtils;
import com.cui.android.jianchengdichan.view.ui.avtivity.CommentActivity;
import com.google.gson.JsonObject;

import java.util.List;

/**
 * @author CUI
 * @data 2018/6/21.
 * @details
 */
public class CommentPresenter extends BasePresenter<CommentActivity> {
    /**
     * topic_id	是	int	话题id
     page	是	int	页数
     uid	否	int	用户id，用户登录要传参数uid，未登录传空字符串
     * @param uid
     */
    public void getCommList(int uid,String topic_id,int page){
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("uid",uid);
        jsonObject.addProperty("topic_id",topic_id);
        jsonObject.addProperty("page",page);
        String json =jsonObject.toString();
        LogUtils.i("json=()"+json);
        DataModel.request(Token.API_COMM_LIST_MODEL)
                .params(json)
                .execute(new CallBack<BaseBean<List<CommentListBean>>>() {
                    @Override
                    public void onSuccess(BaseBean<List<CommentListBean>> data) {
                        getView().getCommList(data.getData());
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
     topic_id	是	int	话题id
     uid	是	string	token
     token	是	int	用户id
     */
    public void doPraise(String topic_id,int uid,String token){
        LogUtils.i("doPraise()");
        if (!isViewAttached()) {
            //如果没有View引用就不加载数据
            return;
        }
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("uid",uid);
        jsonObject.addProperty("token",token);
        jsonObject.addProperty("topic_id",topic_id);
        String json =jsonObject.toString();
        DataModel.request(Token.API_DO_PRAISE_MODEL)
                .params(json)
                .execute(new CallBack<BaseBean<Object>>() {
                    @Override
                    public void onSuccess(BaseBean<Object> data) {
                        getView().doPraise();
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

    public void cancelPraise(String topic_id,int uid,String token){
        LogUtils.i("doPraise()");
        if (!isViewAttached()) {
            //如果没有View引用就不加载数据
            return;
        }
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("uid",uid);
        jsonObject.addProperty("token",token);
        jsonObject.addProperty("topic_id",topic_id);
        String json =jsonObject.toString();
        DataModel.request(Token.API_CANCEL_PRAISE_MODEL)
                .params(json)
                .execute(new CallBack<BaseBean<Object>>() {
                    @Override
                    public void onSuccess(BaseBean<Object> data) {
                        getView().cancelPraise();
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
     topic_id	是	int	话题id
     content	是	string	评论内容
     uid	是	int	用户id
     token	是	string	token
     */
    public void senMsg(String topic_id,String content,int uid,String token){
        LogUtils.i("doPraise()");
        if (!isViewAttached()) {
            //如果没有View引用就不加载数据
            return;
        }
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("uid",uid);
        jsonObject.addProperty("token",token);
        jsonObject.addProperty("topic_id",topic_id);
        jsonObject.addProperty("content",content);
        String json =jsonObject.toString();
        DataModel.request(Token.API_RELEASE_COM_TOPIC_MODEL)
                .params(json)
                .execute(new CallBack<BaseBean<Object>>() {
                    @Override
                    public void onSuccess(BaseBean<Object> data) {
                        getView().senMsg();
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
