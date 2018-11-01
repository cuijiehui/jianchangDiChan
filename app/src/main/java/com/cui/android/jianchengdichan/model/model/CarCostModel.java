package com.cui.android.jianchengdichan.model.model;

import com.cui.android.jianchengdichan.MyApplication;
import com.cui.android.jianchengdichan.http.RetrofitFactory;
import com.cui.android.jianchengdichan.http.base.BaseBean;
import com.cui.android.jianchengdichan.http.base.BaseObserver;
import com.cui.android.jianchengdichan.http.bean.CarCostBean;
import com.cui.android.jianchengdichan.model.base.BaseModel;
import com.cui.android.jianchengdichan.model.interfaces.CallBack;

import java.util.List;

import okhttp3.RequestBody;

public class CarCostModel extends BaseModel<BaseBean<CarCostBean>> {
    @Override
    public void execute(final CallBack<BaseBean<CarCostBean>> callback) {
        RequestBody body= RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),mParams[0]);
        RetrofitFactory.getInstence().API()
                .parkingGetCost(body)
                .compose(MyApplication.getInstance().<BaseBean<CarCostBean>>setThread())
                .subscribe(new BaseObserver<CarCostBean>() {
                    @Override
                    protected void onSuccees(BaseBean<CarCostBean> t) throws Exception {
                        callback.onSuccess(t);
                    }

                    @Override
                    protected void onCodeError(BaseBean<CarCostBean> t) throws Exception {
                        callback.onFailure(t.getMsg());
                    }

                    @Override
                    protected void onError(Throwable e, boolean isNetWorkError) throws Exception {
                        callback.onError();
                    }
                });
    }
}
