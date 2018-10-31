package com.cui.android.jianchengdichan.model.model;

import com.cui.android.jianchengdichan.MyApplication;
import com.cui.android.jianchengdichan.http.RetrofitFactory;
import com.cui.android.jianchengdichan.http.base.BaseBean;
import com.cui.android.jianchengdichan.http.base.BaseObserver;
import com.cui.android.jianchengdichan.http.bean.CarChargeLogBean;
import com.cui.android.jianchengdichan.http.bean.CarEnterLogBean;
import com.cui.android.jianchengdichan.model.base.BaseModel;
import com.cui.android.jianchengdichan.model.interfaces.CallBack;

import java.util.List;

import okhttp3.RequestBody;

public class CarChargeLogModel extends BaseModel<BaseBean<List<CarChargeLogBean>>> {
    @Override
    public void execute(final CallBack<BaseBean<List<CarChargeLogBean>>> callback) {
        RequestBody body= RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),mParams[0]);
        RetrofitFactory.getInstence().API()
                .getCarChargeLog(body)
                .compose(MyApplication.getInstance().<BaseBean<List<CarChargeLogBean>>>setThread())
                .subscribe(new BaseObserver<List<CarChargeLogBean>>() {
                    @Override
                    protected void onSuccees(BaseBean<List<CarChargeLogBean>> t) throws Exception {
                        callback.onSuccess(t);
                    }

                    @Override
                    protected void onCodeError(BaseBean<List<CarChargeLogBean>> t) throws Exception {
                        callback.onFailure(t.getMsg());
                    }

                    @Override
                    protected void onError(Throwable e, boolean isNetWorkError) throws Exception {
                        callback.onError();
                    }
                });
    }
}
