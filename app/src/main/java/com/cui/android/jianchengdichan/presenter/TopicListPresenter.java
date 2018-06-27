package com.cui.android.jianchengdichan.presenter;

import com.cui.android.jianchengdichan.http.base.BaseBean;
import com.cui.android.jianchengdichan.http.bean.CommentActBean;
import com.cui.android.jianchengdichan.http.bean.TopicListBean;
import com.cui.android.jianchengdichan.model.DataModel;
import com.cui.android.jianchengdichan.model.Token;
import com.cui.android.jianchengdichan.model.interfaces.CallBack;
import com.cui.android.jianchengdichan.utils.LogUtils;
import com.cui.android.jianchengdichan.view.ui.avtivity.TopicListActivity;
import com.google.gson.JsonObject;

import java.util.List;

/**
 * @author CUI
 * @data 2018/6/22.
 * @details
 */
public class TopicListPresenter extends BasePresenter<TopicListActivity> {
    /**
     * 参数名	必选	类型	说明
     uid	否	int	用户id
     page	否	int	页数 默认为1
     type	否	int	类别 1:话题 2：拼车 3：跳蚤市场 为空则是全部
     */
    public void getTopicList(int uid,int page,int type){
        LogUtils.i("getData()");
        if (!isViewAttached()) {
            //如果没有View引用就不加载数据
            return;
        }
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("uid", uid);
        jsonObject.addProperty("page", page);
        if(type!=0){
            jsonObject.addProperty("type", type);

        }
        String json = jsonObject.toString();
        LogUtils.i("json=()" + json);
        DataModel.request(Token.API_TOPIC_LIST_MODEL)
                .params(json)
                .execute(new CallBack<BaseBean<List<TopicListBean>>>() {
                    @Override
                    public void onSuccess(BaseBean<List<TopicListBean>> data) {
                        getView().getTopicList(data.getData());
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
