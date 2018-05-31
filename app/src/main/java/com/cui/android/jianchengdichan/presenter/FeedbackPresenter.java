package com.cui.android.jianchengdichan.presenter;

import com.cui.android.jianchengdichan.http.bean.BaseBean;
import com.cui.android.jianchengdichan.model.DataModel;
import com.cui.android.jianchengdichan.model.Token;
import com.cui.android.jianchengdichan.model.interfaces.CallBack;
import com.cui.android.jianchengdichan.utils.LogUtils;
import com.cui.android.jianchengdichan.view.ui.FeedbackActivity;
import com.google.gson.JsonObject;

public class FeedbackPresenter extends BasePresenter<FeedbackActivity> {
    /**
     * 	必选	类型	说明
     uid	是	int	用户id
     token	是	string	token
     phone	是	string	手机号
     content	是	string	反馈内容
     */
    public void getOpinion(int uid ,String token ,String phone,String content){
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("uid",uid);
        jsonObject.addProperty("token",token);
        jsonObject.addProperty("phone",phone);
        jsonObject.addProperty("content",content);
        String json =jsonObject.toString();
        LogUtils.i("json=()"+json);
        DataModel.request(Token.API_FEEDBACK)
                .params(json)
                .execute(new CallBack<BaseBean>() {

                    @Override
                    public void onSuccess(BaseBean data) {
                        getView().getOpinion();
                    }

                    @Override
                    public void onFailure(String msg) {
                        getView().onFailure();
                    }

                    @Override
                    public void onError() {
                        getView().onError();
                    }

                    @Override
                    public void onComplete() {
                        getView().hideLoading();
                    }
                });
    }
}
