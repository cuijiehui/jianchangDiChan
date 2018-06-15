package com.cui.android.jianchengdichan.presenter;

import com.cui.android.jianchengdichan.http.base.BaseBean;
import com.cui.android.jianchengdichan.http.base.BasesBean;
import com.cui.android.jianchengdichan.http.bean.MyApplyBean;
import com.cui.android.jianchengdichan.model.DataModel;
import com.cui.android.jianchengdichan.model.Token;
import com.cui.android.jianchengdichan.model.interfaces.CallBack;
import com.cui.android.jianchengdichan.utils.LogUtils;
import com.cui.android.jianchengdichan.view.ui.avtivity.MyApplyActivity;
import com.google.gson.JsonObject;

import java.util.List;

public class MyApplyPresenter extends BasePresenter<MyApplyActivity> {
    /**
     * 参数名	必选	类型	说明
     uid	是	int	用户id
     token	是	int	token
     page	是	int	页码
     */
    public void getMyApplyModel(int uid,String token,int page){
        LogUtils.i("getMyApplyModel()");
        if (!isViewAttached()) {
            //如果没有View引用就不加载数据
            return;
        }
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("uid",uid);
        jsonObject.addProperty("token",token);
        jsonObject.addProperty("page",page);
        String json =jsonObject.toString();
        DataModel.request(Token.API_MY_APPLY_INFO)
                .params(json)
                .execute(new CallBack<BaseBean<List<MyApplyBean>>>() {
                    @Override
                    public void onSuccess(BaseBean<List<MyApplyBean>> data) {
                        getView().getMyApplyModel(data.getData());
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
     uid	是	int	用户id
     token	是	string	token
     id	是	int	信息id
     */
    public void  delRentInfo(int uid,String token,String id){
        LogUtils.i("getMyApplyModel()");
        if (!isViewAttached()) {
            //如果没有View引用就不加载数据
            return;
        }
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("uid",uid);
        jsonObject.addProperty("token",token);
        jsonObject.addProperty("id",id);
        String json =jsonObject.toString();
        DataModel.request(Token.API_DEL_RENT_INFO_MODEL)
                .params(json)
                .execute(new CallBack<BasesBean>() {
                    @Override
                    public void onSuccess(BasesBean data) {
                        getView().delRentInfo();
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
