package com.cui.android.jianchengdichan.presenter;

import com.cui.android.jianchengdichan.http.base.BaseBean;
import com.cui.android.jianchengdichan.http.bean.UserInfoPicBean;
import com.cui.android.jianchengdichan.model.DataModel;
import com.cui.android.jianchengdichan.model.Token;
import com.cui.android.jianchengdichan.model.interfaces.CallBack;
import com.cui.android.jianchengdichan.utils.LogUtils;
import com.cui.android.jianchengdichan.view.ui.avtivity.PersonalDataActivity;
import com.google.gson.JsonObject;

public class PersonalDataPresenter extends BasePresenter<PersonalDataActivity> {
    /**
     * 参数名	必选	类型	说明
     uid	是	int	用户id
     token	是	string	token
     key	是	string	字段名 头像（pic）、真实姓名（name）、昵称（nickname）
     value	是	string	取值
     */
    public void setUserInfo(int uid,String token,String key,String value){
        LogUtils.i("getCates()");
        if (!isViewAttached()) {
            //如果没有View引用就不加载数据
            return;
        }
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("uid",uid);
        jsonObject.addProperty("token",token);
        jsonObject.addProperty("key",key);
        jsonObject.addProperty("value",value);
        String json =jsonObject.toString();
        DataModel.request(Token.API_INFO_PIC_INFO)
                .params(json)
                .execute(new CallBack<BaseBean<UserInfoPicBean>>() {
                    @Override
                    public void onSuccess(BaseBean<UserInfoPicBean> data) {
                        getView().setUserInfo(data.getData());

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

        /**
         *  {
        @Override
        public void onSuccess(BaseBean data) {
        getView().setUserInfo();
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
        }
         */
    }
}
