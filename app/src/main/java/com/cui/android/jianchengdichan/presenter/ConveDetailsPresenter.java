package com.cui.android.jianchengdichan.presenter;

import com.cui.android.jianchengdichan.http.base.BaseBean;
import com.cui.android.jianchengdichan.http.bean.CivilianDetailBean;
import com.cui.android.jianchengdichan.model.DataModel;
import com.cui.android.jianchengdichan.model.Token;
import com.cui.android.jianchengdichan.model.interfaces.CallBack;
import com.cui.android.jianchengdichan.utils.LogUtils;
import com.cui.android.jianchengdichan.view.ui.avtivity.ConveDetailsActivity;
import com.google.gson.JsonObject;

/**
 * @author CUI
 * @data 2018/6/12.
 * @details
 */
public class ConveDetailsPresenter extends BasePresenter<ConveDetailsActivity> {
   public void  getDetail(String id){
       LogUtils.i("getDetail()");
       if (!isViewAttached()) {
           //如果没有View引用就不加载数据
           return;
       }
       JsonObject jsonObject = new JsonObject();
       jsonObject.addProperty("id",id);
       String json =jsonObject.toString();
       LogUtils.i("json=()"+json);
       DataModel.request(Token.API_CIVILIAN_DETAIL_MODEL)
               .params(json)
               .execute(new CallBack<BaseBean<CivilianDetailBean>>() {
                   @Override
                   public void onSuccess(BaseBean<CivilianDetailBean> data) {
                       getView().getDetail(data.getData());
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
