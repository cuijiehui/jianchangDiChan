package com.cui.android.jianchengdichan.model.model;

import com.cui.android.jianchengdichan.MyApplication;
import com.cui.android.jianchengdichan.http.RetrofitFactory;
import com.cui.android.jianchengdichan.http.base.BaseBean;
import com.cui.android.jianchengdichan.http.base.BaseObserver;
import com.cui.android.jianchengdichan.http.bean.CommunityBean;
import com.cui.android.jianchengdichan.http.bean.CreateOrderBean;
import com.cui.android.jianchengdichan.model.base.BaseModel;
import com.cui.android.jianchengdichan.model.interfaces.CallBack;

import java.util.List;

import okhttp3.RequestBody;

public class CreateOrderModel extends BaseModel<BaseBean<CreateOrderBean>> {
    @Override
    public void execute(final CallBack<BaseBean<CreateOrderBean>> callback) {
        RequestBody body= RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),mParams[0]);
        RetrofitFactory.getInstence().API()
                .getCreateOrder(body)
                .compose(MyApplication.getInstance().<BaseBean<CreateOrderBean>>setThread())
                .subscribe(new BaseObserver<CreateOrderBean>() {
                    @Override
                    protected void onSuccees(BaseBean<CreateOrderBean> t) throws Exception {
                        callback.onSuccess(t);
                    }

                    @Override
                    protected void onCodeError(BaseBean<CreateOrderBean> t) throws Exception {
                        callback.onFailure(t.getMsg());
                    }


                    @Override
                    protected void onError(Throwable e, boolean isNetWorkError) throws Exception {
                        callback.onError();

                    }
                });
    }
}
