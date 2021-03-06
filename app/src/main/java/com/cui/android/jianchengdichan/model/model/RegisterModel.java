package com.cui.android.jianchengdichan.model.model;

import com.cui.android.jianchengdichan.MyApplication;
import com.cui.android.jianchengdichan.http.RetrofitFactory;
import com.cui.android.jianchengdichan.http.base.BaseObserver;
import com.cui.android.jianchengdichan.http.base.BaseBean;
import com.cui.android.jianchengdichan.model.base.BaseModel;
import com.cui.android.jianchengdichan.model.interfaces.CallBack;
import com.cui.android.jianchengdichan.utils.LogUtils;

import okhttp3.RequestBody;


public class RegisterModel extends BaseModel<BaseBean<Object>>  {

    @Override
    public void execute(final CallBack<BaseBean<Object>> callback) {
        LogUtils.i("RegisterModel.execute()");
        RequestBody body= RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),mParams[0]);
        RetrofitFactory.getInstence().API()
                .getRegister(body)
                .compose(MyApplication.getInstance().<BaseBean<Object>>setThread())
                .subscribe(new BaseObserver<Object>() {
                    @Override
                    protected void onSuccees(BaseBean<Object> t) throws Exception {
                        LogUtils.i("onResponse"+t.toString());
                        callback.onSuccess(t);
                    }

                    @Override
                    protected void onCodeError(BaseBean<Object> t) throws Exception {
                        LogUtils.i("onResponse"+t.toString());
                        callback.onFailure(t.getMsg());

                    }

                    @Override
                    protected void onError(Throwable e, boolean isNetWorkError) throws Exception {
                        LogUtils.i("onResponse"+e.getLocalizedMessage());
                        callback.onError();
                    }
                });
    }

}
