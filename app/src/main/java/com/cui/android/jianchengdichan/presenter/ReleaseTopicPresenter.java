package com.cui.android.jianchengdichan.presenter;

import android.text.TextUtils;

import com.cui.android.jianchengdichan.http.base.BaseBean;
import com.cui.android.jianchengdichan.model.DataModel;
import com.cui.android.jianchengdichan.model.Token;
import com.cui.android.jianchengdichan.model.interfaces.CallBack;
import com.cui.android.jianchengdichan.utils.LogUtils;
import com.cui.android.jianchengdichan.view.ui.avtivity.ReleaseTopicActivity;
import com.google.gson.JsonObject;

/**
 * @author CUI
 * @data 2018/6/15.
 * @details
 */
public class ReleaseTopicPresenter extends BasePresenter<ReleaseTopicActivity> {
    /**
     * uid	是	int	用户名
     * token	是	string	密码
     * title	是	string	标题
     * content	是	string	内容
     * pic	是	string	图片，支持多图，以逗号拼接
     * type	是	int	1：话题 2：拼车 3：跳蚤市场
     */
    public void topic(int uid, String token, String title, String content, String pic, String type) {
        LogUtils.i("getDetail()");
        if (!isViewAttached()) {
            //如果没有View引用就不加载数据
            return;
        }
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("uid", uid);
        jsonObject.addProperty("token", token);
        jsonObject.addProperty("title", title);
        jsonObject.addProperty("content", content);
        jsonObject.addProperty("pic", pic);
        jsonObject.addProperty("type", type);
        String json = jsonObject.toString();
        LogUtils.i("json=()" + json);
        DataModel.request(Token.API_RELEASE_TOPIC_MODEL)
                .params(json)
                .execute(new CallBack<BaseBean<Object>>() {
                    @Override
                    public void onSuccess(BaseBean<Object> data) {
                        getView().topic();
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
