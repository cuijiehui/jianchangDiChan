package com.cui.android.jianchengdichan.presenter;

import com.cui.android.jianchengdichan.http.base.BaseBean;
import com.cui.android.jianchengdichan.model.DataModel;
import com.cui.android.jianchengdichan.model.Token;
import com.cui.android.jianchengdichan.model.interfaces.CallBack;
import com.cui.android.jianchengdichan.utils.LogUtils;
import com.cui.android.jianchengdichan.view.ui.avtivity.ReleaseActActivity;
import com.google.gson.JsonObject;

/**
 * @author CUI
 * @data 2018/6/19.
 * @details
 */
public class ReleaseActPresenter extends BasePresenter<ReleaseActActivity> {
    /**
     * 参数名	必选	类型	说明
     uid	是	int	用户id
     token	是	string	token
     phone	是	string	电话
     content	是	string	内容
     pic	是	string	图片
     address	是	string	地址
     startdate	是	string	活动开始日期
     enddate	是	string	活动结束日期
     title	是	string	标题
     */
    public void releas(int uid,String token,String phone,String content,String pic,String address,String startdate,String enddate,String title){
        LogUtils.i("releas()");
        if (!isViewAttached()) {
            //如果没有View引用就不加载数据
            return;
        }
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("uid", uid);
        jsonObject.addProperty("token", token);
        jsonObject.addProperty("phone", phone);
        jsonObject.addProperty("content", content);
        jsonObject.addProperty("pic", pic);
        jsonObject.addProperty("address", address);
        jsonObject.addProperty("startdate", startdate);
        jsonObject.addProperty("enddate", enddate);
        jsonObject.addProperty("title", title);
        String json = jsonObject.toString();
        LogUtils.i("json=()" + json);
        DataModel.request(Token.API_RELEASE_ACT_MODEL)
                .params(json)
                .execute(new CallBack<BaseBean<Object>>() {
                    @Override
                    public void onSuccess(BaseBean<Object> data) {
                        getView().releas();
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
