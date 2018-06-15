package com.cui.android.jianchengdichan.model.model;

import com.cui.android.jianchengdichan.MyApplication;
import com.cui.android.jianchengdichan.http.RetrofitFactory;
import com.cui.android.jianchengdichan.http.base.BaseObserver;
import com.cui.android.jianchengdichan.http.base.BaseBean;
import com.cui.android.jianchengdichan.http.bean.UserCommunityBean;
import com.cui.android.jianchengdichan.model.base.BaseModel;
import com.cui.android.jianchengdichan.model.interfaces.CallBack;
import com.cui.android.jianchengdichan.utils.LogUtils;

import okhttp3.RequestBody;

public class UserCommunityModel extends BaseModel<BaseBean<UserCommunityBean>> {
    @Override
    public void execute(final CallBack<BaseBean<UserCommunityBean>> callback) {
        RequestBody body= RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),mParams[0]);
        RetrofitFactory.getInstence().API()
                .setUserCommunity(body)
                .compose(MyApplication.getInstance().<BaseBean<UserCommunityBean>>setThread())
                .subscribe(new BaseObserver<UserCommunityBean>() {
                    @Override
                    protected void onSuccees(BaseBean<UserCommunityBean> t) throws Exception {
                        callback.onSuccess(t);
                    }

                    @Override
                    protected void onCodeError(BaseBean<UserCommunityBean> t) throws Exception {
                        LogUtils.i(t.getMsg());
                        callback.onFailure(t.getMsg());

                    }

                    @Override
                    protected void onError(Throwable e, boolean isNetWorkError) throws Exception {
                        callback.onError();

                    }
                });
    }
}
