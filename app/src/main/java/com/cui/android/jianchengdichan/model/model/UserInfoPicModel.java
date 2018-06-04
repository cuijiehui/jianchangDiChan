package com.cui.android.jianchengdichan.model.model;

import com.cui.android.jianchengdichan.MyApplication;
import com.cui.android.jianchengdichan.http.RetrofitFactory;
import com.cui.android.jianchengdichan.http.base.BaseBean;
import com.cui.android.jianchengdichan.http.base.BaseObserver;
import com.cui.android.jianchengdichan.http.base.BasesBean;
import com.cui.android.jianchengdichan.http.base.BasesObserver;
import com.cui.android.jianchengdichan.http.bean.UserInfoPicBean;
import com.cui.android.jianchengdichan.model.base.BaseModel;
import com.cui.android.jianchengdichan.model.interfaces.CallBack;

import okhttp3.RequestBody;

public class UserInfoPicModel extends BaseModel<BaseBean<UserInfoPicBean>> {
    @Override
    public void execute(final CallBack<BaseBean<UserInfoPicBean>> callback) {
        RequestBody body= RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),mParams[0]);

        RetrofitFactory.getInstence().API()
                .setUserInfoPic(body)
                .compose(MyApplication.getInstance().<BaseBean<UserInfoPicBean>>setThread())
                .subscribe(new BaseObserver<UserInfoPicBean>() {
                    @Override
                    protected void onSuccees(BaseBean<UserInfoPicBean> t) throws Exception {
                        callback.onSuccess(t);
                    }

                    @Override
                    protected void onCodeError(BaseBean<UserInfoPicBean> t) throws Exception {
                        callback.onFailure(t.getMsg());
                    }

                    @Override
                    protected void onFailure(Throwable e, boolean isNetWorkError) throws Exception {
                        callback.onError();
                    }

                });
    }
}
