package com.cui.android.jianchengdichan.presenter;

import com.cui.android.jianchengdichan.http.base.BaseBean;
import com.cui.android.jianchengdichan.http.bean.ActivityDetailBean;
import com.cui.android.jianchengdichan.http.bean.CommentActBean;
import com.cui.android.jianchengdichan.model.DataModel;
import com.cui.android.jianchengdichan.model.Token;
import com.cui.android.jianchengdichan.model.interfaces.CallBack;
import com.cui.android.jianchengdichan.utils.LogUtils;
import com.cui.android.jianchengdichan.view.ui.avtivity.ActDetaiActivity;
import com.google.gson.JsonObject;

import java.util.List;

/**
 * @author CUI
 * @data 2018/6/19.
 * @details
 */
public class ActDetaiPresenter extends BasePresenter<ActDetaiActivity> {
    /**
     * 参数名	必选	类型	说明
     id	是	int	活动id
     */
    public void getData(int id){
        LogUtils.i("getData()");
        if (!isViewAttached()) {
            //如果没有View引用就不加载数据
            return;
        }
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("id", id);
        String json = jsonObject.toString();
        LogUtils.i("json=()" + json);
        DataModel.request(Token.API_ACT_DETAIL_MODEL)
                .params(json)
                .execute(new CallBack<BaseBean<ActivityDetailBean>>() {
                    @Override
                    public void onSuccess(BaseBean<ActivityDetailBean> data) {
                        getView().getData(data.getData());
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
