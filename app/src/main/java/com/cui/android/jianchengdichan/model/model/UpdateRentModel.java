package com.cui.android.jianchengdichan.model.model;

import com.cui.android.jianchengdichan.MyApplication;
import com.cui.android.jianchengdichan.http.RetrofitFactory;
import com.cui.android.jianchengdichan.http.base.BasesBean;
import com.cui.android.jianchengdichan.http.base.BasesObserver;
import com.cui.android.jianchengdichan.model.base.BaseModel;
import com.cui.android.jianchengdichan.model.interfaces.CallBack;

import okhttp3.RequestBody;

public class UpdateRentModel extends BaseModel<BasesBean> {
    @Override
    public void execute( final CallBack<BasesBean> callback) {
        RequestBody body= RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),mParams[0]);
        RetrofitFactory.getInstence().API()
                .updateRent(body)
                .compose(MyApplication.getInstance().<BasesBean>setThread())
                .subscribe(new BasesObserver() {
                    @Override
                    protected void onSuccees(BasesBean t) throws Exception {
                        callback.onSuccess(t);
                    }

                    @Override
                    protected void onCodeError(BasesBean t) throws Exception {
                        callback.onFailure(t.getMsg());
                    }

                    @Override
                    protected void onError(Throwable e, boolean isNetWorkError) throws Exception {
                        callback.onError();
                    }
                });
    }
}
