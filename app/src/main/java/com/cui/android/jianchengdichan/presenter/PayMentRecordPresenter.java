package com.cui.android.jianchengdichan.presenter;

import com.cui.android.jianchengdichan.http.base.BaseBean;
import com.cui.android.jianchengdichan.http.bean.PayRecordsBean;
import com.cui.android.jianchengdichan.model.DataModel;
import com.cui.android.jianchengdichan.model.Token;
import com.cui.android.jianchengdichan.model.interfaces.CallBack;
import com.cui.android.jianchengdichan.utils.LogUtils;
import com.cui.android.jianchengdichan.view.ui.avtivity.PayMentRecordActivity;
import com.google.gson.JsonObject;

import java.util.List;

public class PayMentRecordPresenter extends BasePresenter<PayMentRecordActivity> {
   public void  getChargeRecordAll(int uid,String token,int page){
       LogUtils.i("getCates()");
       if (!isViewAttached()) {
           //如果没有View引用就不加载数据
           return;
       }
       JsonObject jsonObject = new JsonObject();
       jsonObject.addProperty("uid",uid);
       jsonObject.addProperty("token",token);
       jsonObject.addProperty("page",page);
       String json =jsonObject.toString();

       DataModel.request(Token.API_CHARGE_RECORD_DATA)
               .params(json)
               .execute(new CallBack<BaseBean<List<PayRecordsBean>>>() {

                   @Override
                   public void onSuccess(BaseBean<List<PayRecordsBean>> data) {
                       getView().getChargeRecordAll(data.getData());
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
                       getView().hideLoading();
                   }
               });
   }
    public void  getChargeRecordOther(int uid,String token,int page,int status,int cate_id){
        LogUtils.i("getCates()");
        if (!isViewAttached()) {
            //如果没有View引用就不加载数据
            return;
        }
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("uid",uid);
        jsonObject.addProperty("token",token);
        jsonObject.addProperty("page",page);
        jsonObject.addProperty("status",status);
        jsonObject.addProperty("cate_id",cate_id);
        String json =jsonObject.toString();

        DataModel.request(Token.API_CHARGE_RECORD_DATA)
                .params(json)
                .execute(new CallBack<BaseBean<List<PayRecordsBean>>>() {

                    @Override
                    public void onSuccess(BaseBean<List<PayRecordsBean>> data) {
                        getView().getChargeRecordAll(data.getData());
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
                        getView().hideLoading();
                    }
                });
    }
}
