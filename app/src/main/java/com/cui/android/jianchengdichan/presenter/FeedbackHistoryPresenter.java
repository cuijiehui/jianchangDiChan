package com.cui.android.jianchengdichan.presenter;

import com.cui.android.jianchengdichan.http.base.BaseBean;
import com.cui.android.jianchengdichan.http.bean.HistoryDataBean;
import com.cui.android.jianchengdichan.model.DataModel;
import com.cui.android.jianchengdichan.model.Token;
import com.cui.android.jianchengdichan.model.interfaces.CallBack;
import com.cui.android.jianchengdichan.utils.LogUtils;
import com.cui.android.jianchengdichan.view.ui.FeedbackHistoryActivity;
import com.google.gson.JsonObject;

import java.util.List;

public class FeedbackHistoryPresenter extends BasePresenter<FeedbackHistoryActivity> {
    /**
     * uid	是	int	用户id
     token	是	string	token
     page	是	int	页码
     */
    public void getOpinionList(int uid , String token,int page){
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("uid",uid);
        jsonObject.addProperty("token",token);
        jsonObject.addProperty("page",page);
        String json =jsonObject.toString();
        LogUtils.i("json=()"+json);
        DataModel.request(Token.API_FEEDBACK_HISTORY)
                .params(json)
                .execute(new CallBack<BaseBean<List<HistoryDataBean>>>() {

                    @Override
                    public void onSuccess(BaseBean<List<HistoryDataBean>> data) {
                        getView().getOpinionList(data.getData());
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

    /**
     * uid	是	int	用户id
     token	是	string	token
     id	是	int	意见反馈id
     */
    public void delOpinion(int uid ,String token,int id){
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("uid",uid);
        jsonObject.addProperty("token",token);
        jsonObject.addProperty("id",id);
        String json =jsonObject.toString();
        LogUtils.i("json=()"+json);
        DataModel.request(Token.API_DEL_FEEDBACK_HISTORY)
                .params(json)
                .execute(new CallBack<BaseBean>() {

                    @Override
                    public void onSuccess(BaseBean data) {
                        getView().delOpinion();
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
