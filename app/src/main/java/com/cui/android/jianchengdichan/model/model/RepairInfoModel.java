package com.cui.android.jianchengdichan.model.model;

import com.cui.android.jianchengdichan.MyApplication;
import com.cui.android.jianchengdichan.http.RetrofitFactory;
import com.cui.android.jianchengdichan.http.base.BaseBean;
import com.cui.android.jianchengdichan.http.base.BaseObserver;
import com.cui.android.jianchengdichan.model.base.BaseModel;
import com.cui.android.jianchengdichan.model.interfaces.CallBack;

import okhttp3.RequestBody;

public class RepairInfoModel extends BaseModel<BaseBean<Object>> {
    @Override
    public void execute(final CallBack<BaseBean<Object>> callback) {
        RequestBody body= RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),mParams[0]);

        RetrofitFactory.getInstence().API()
                .submitRepairInfo(body)
                .compose(MyApplication.getInstance().<BaseBean<Object>>setThread())
                .subscribe(new BaseObserver<Object>() {
                    @Override
                    protected void onSuccees(BaseBean<Object> t) throws Exception {
                        callback.onSuccess(t);
                    }

                    @Override
                    protected void onCodeError(BaseBean<Object> t) throws Exception {
                        callback.onFailure(t.getMsg());
                    }

                    @Override
                    protected void onError(Throwable e, boolean isNetWorkError) throws Exception {
                        callback.onError();
                    }

                });
    }
}
