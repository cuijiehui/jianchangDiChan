package com.cui.android.jianchengdichan.model.model;

import com.cui.android.jianchengdichan.MyApplication;
import com.cui.android.jianchengdichan.http.RetrofitFactory;
import com.cui.android.jianchengdichan.http.base.BaseBean;
import com.cui.android.jianchengdichan.http.base.BaseObserver;
import com.cui.android.jianchengdichan.http.bean.LoginBean;
import com.cui.android.jianchengdichan.model.base.BaseModel;
import com.cui.android.jianchengdichan.model.interfaces.CallBack;

import okhttp3.RequestBody;

/**
 * @author CUI
 * @data 2018/6/8.
 * @details
 */
public class GetUserInfoModel extends BaseModel<BaseBean<LoginBean>> {
    @Override
    public void execute(final CallBack<BaseBean<LoginBean>> callback) {
        RequestBody body= RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),mParams[0]);
        RetrofitFactory.getInstence().API()
                .getUserInfo(body)
                .compose(MyApplication.getInstance().<BaseBean<LoginBean>>setThread())
                .subscribe(new BaseObserver<LoginBean>() {
                    @Override
                    protected void onSuccees(BaseBean<LoginBean> t) throws Exception {
                        callback.onSuccess(t);
                    }

                    @Override
                    protected void onCodeError(BaseBean<LoginBean> t) throws Exception {
                        callback.onFailure(t.getMsg());
                    }

                    @Override
                    protected void onFailure(Throwable e, boolean isNetWorkError) throws Exception {
                        callback.onError();
                    }
                });
    }
}
