package com.cui.android.jianchengdichan.presenter;

import com.cui.android.jianchengdichan.http.base.BaseBean;
import com.cui.android.jianchengdichan.http.bean.CommentActBean;
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

    /**
     * 参数名	必选	类型	说明
     uid	否	int	用户id
     page	否	int	页数 默认为1
     type	否	int	类别 1:话题 2：拼车 3：跳蚤市场 为空则是全部
     */
    public void getTopicList(int uid,int page,int type){
        LogUtils.i("getTopicList()");
        if (!isViewAttached()) {
            //如果没有View引用就不加载数据
            return;
        }
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("uid",uid);
        jsonObject.addProperty("page",page);
        if(type!=0){
            jsonObject.addProperty("type",type);
        }
        String json =jsonObject.toString();
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
}
