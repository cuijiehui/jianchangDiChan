package com.cui.android.jianchengdichan.model.model;

import com.cui.android.jianchengdichan.MyApplication;
import com.cui.android.jianchengdichan.http.RetrofitFactory;
import com.cui.android.jianchengdichan.http.base.BaseObserver;
import com.cui.android.jianchengdichan.http.base.BaseBean;
import com.cui.android.jianchengdichan.http.bean.HomeDataBean;
import com.cui.android.jianchengdichan.model.base.BaseModel;
import com.cui.android.jianchengdichan.model.interfaces.CallBack;
import com.cui.android.jianchengdichan.utils.LogUtils;

public class HomeDataModel extends BaseModel<BaseBean<HomeDataBean>> {
    @Override
    public void execute(final CallBack<BaseBean<HomeDataBean>> callback) {
        RetrofitFactory.getInstence().API()
                .getHomeData()
                .compose(MyApplication.getInstance().<BaseBean<HomeDataBean>>setThread())
                .subscribe(new BaseObserver<HomeDataBean>() {
                    @Override
                    protected void onSuccees(BaseBean<HomeDataBean> t) throws Exception {
                        LogUtils.i("onResponse" + t.getData().toString());
                        callback.onSuccess(t);
                    }

                    @Override
                    protected void onCodeError(BaseBean<HomeDataBean> t) throws Exception {
                        LogUtils.i("onResponse" + t.toString());
                        callback.onFailure(t.getMsg());

                    }

                    @Override
                    protected void onError(Throwable e, boolean isNetWorkError) throws Exception {
                        LogUtils.i("onResponse" + e.getLocalizedMessage());
                        callback.onError();
                    }
                });
    }



}
