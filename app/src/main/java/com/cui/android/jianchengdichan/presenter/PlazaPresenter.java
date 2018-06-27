package com.cui.android.jianchengdichan.presenter;

import com.cui.android.jianchengdichan.http.base.BaseBean;
import com.cui.android.jianchengdichan.http.bean.CommentActBean;
import com.cui.android.jianchengdichan.http.bean.CommentTopicBean;
import com.cui.android.jianchengdichan.http.bean.HomeDataBean;
import com.cui.android.jianchengdichan.http.bean.TopicListBean;
import com.cui.android.jianchengdichan.model.DataModel;
import com.cui.android.jianchengdichan.model.Token;
import com.cui.android.jianchengdichan.model.interfaces.CallBack;
import com.cui.android.jianchengdichan.utils.LogUtils;
import com.cui.android.jianchengdichan.view.ui.avtivity.PlazaActivity;
import com.google.gson.JsonObject;

import java.util.List;

/**
 * @author CUI
 * @data 2018/6/19.
 * @details
 */
public class PlazaPresenter extends BasePresenter<PlazaActivity> {
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


    public void getCommentTopic(){
        LogUtils.i("getCommentTopic()");
        if (!isViewAttached()) {
            //如果没有View引用就不加载数据
            return;
        }
        DataModel.request(Token.API_COMMENT_TOPIC_MODEL)
                .execute(new CallBack<BaseBean<List<TopicListBean>>>() {
                    @Override
                    public void onSuccess(BaseBean<List<TopicListBean>> data) {
                        getView().getCommentTopic(data.getData());
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
    public void getCommentAct(){
        LogUtils.i("getCommentAct()");
        if (!isViewAttached()) {
            //如果没有View引用就不加载数据
            return;
        }
        DataModel.request(Token.API_COMMENT_ACT_MODEL)
                .execute(new CallBack<BaseBean<CommentActBean>>() {
                    @Override
                    public void onSuccess(BaseBean<CommentActBean> data) {
                        getView().getCommentAct(data.getData());
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
}
