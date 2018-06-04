package com.cui.android.jianchengdichan.model.model;

import com.cui.android.jianchengdichan.MyApplication;
import com.cui.android.jianchengdichan.http.RetrofitFactory;
import com.cui.android.jianchengdichan.http.base.BaseBean;
import com.cui.android.jianchengdichan.http.base.BaseObserver;
import com.cui.android.jianchengdichan.http.bean.AnotherBatchBean;
import com.cui.android.jianchengdichan.http.bean.HomeDataBean;
import com.cui.android.jianchengdichan.model.base.BaseModel;
import com.cui.android.jianchengdichan.model.interfaces.CallBack;

import java.util.List;

import okhttp3.RequestBody;

public class AnotherBatchModel extends BaseModel<BaseBean<AnotherBatchBean>> {
    @Override
    public void execute(final CallBack<BaseBean<AnotherBatchBean>> callback) {

        RetrofitFactory.getInstence().API()
                .getAnotherBatch()
                .compose(MyApplication.getInstance().<BaseBean<AnotherBatchBean>>setThread())
                .subscribe(new BaseObserver<AnotherBatchBean>() {
                    @Override
                    protected void onSuccees(BaseBean<AnotherBatchBean> t) throws Exception {
                        callback.onSuccess(t);
                    }

                    @Override
                    protected void onCodeError(BaseBean<AnotherBatchBean> t) throws Exception {
                        callback.onFailure(t.getMsg());
                    }

                    @Override
                    protected void onFailure(Throwable e, boolean isNetWorkError) throws Exception {
                        callback.onError();
                    }
                });
    }
}
