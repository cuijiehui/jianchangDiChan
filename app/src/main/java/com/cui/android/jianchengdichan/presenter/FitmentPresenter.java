package com.cui.android.jianchengdichan.presenter;

import com.cui.android.jianchengdichan.http.base.BasesBean;
import com.cui.android.jianchengdichan.model.DataModel;
import com.cui.android.jianchengdichan.model.Token;
import com.cui.android.jianchengdichan.model.interfaces.CallBack;
import com.cui.android.jianchengdichan.utils.LogUtils;
import com.cui.android.jianchengdichan.view.ui.avtivity.FitmentActivity;
import com.google.gson.JsonObject;

/**
 * @author CUI
 * @data 2018/6/7.
 * @details
 */
public class FitmentPresenter extends BasePresenter<FitmentActivity> {
    /**
     * 参数名	必选	类型	说明
     * uid	是	string	用户名
     * token	是	string	密码
     * name	是	string	业主
     * phone	是	string	联系电话
     * remarks	是	string	备注
     * company	是	string	装修公司
     * room_number	是	string	房号
     * startdate	是	string	起始时间
     * enddate	是	string	结束时间
     */
    public void submitRenovationInfo(
            int uid
            , String token
            , String name
            , String phone
            , String remarks
            , String company
            , String room_number
            , String startdate
            , String enddate
    ) {
        LogUtils.i("submitRenovationInfo()");
        if (!isViewAttached()) {
            //如果没有View引用就不加载数据
            return;
        }
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("uid", uid);
        jsonObject.addProperty("token", token);
        jsonObject.addProperty("name", name);
        jsonObject.addProperty("phone", phone);
        jsonObject.addProperty("remarks", remarks);
        jsonObject.addProperty("company", company);
        jsonObject.addProperty("room_number", room_number);
        jsonObject.addProperty("startdate", startdate);
        jsonObject.addProperty("enddate", enddate);
        String json = jsonObject.toString();
        LogUtils.i("json=()" + json);
        DataModel.request(Token.API_SUBMIT_REN_INFO_MODEL)
                .params(json)
                .execute(new CallBack<BasesBean>() {
                    @Override
                    public void onSuccess(BasesBean data) {
                        getView().submitRenovationInfo();
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
