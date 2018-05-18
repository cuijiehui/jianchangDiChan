package com.cui.android.jianchengdichan.model.model;

import com.cui.android.jianchengdichan.MyApplication;
import com.cui.android.jianchengdichan.http.RetrofitFactory;
import com.cui.android.jianchengdichan.http.base.BaseObserver;
import com.cui.android.jianchengdichan.http.bean.BaseBean;
import com.cui.android.jianchengdichan.http.bean.LoginBean;
import com.cui.android.jianchengdichan.model.base.BaseModel;
import com.cui.android.jianchengdichan.model.interfaces.CallBack;
import com.cui.android.jianchengdichan.utils.LogUtils;

public class LoginModel  extends BaseModel<BaseBean<LoginBean>> {
    @Override
    public void execute(final CallBack<BaseBean<LoginBean>> callback) {
        LogUtils.i("LoginModel.execute()");
        RetrofitFactory.getInstence().API()
                .getLogin(mParams[0],mParams[1])
                .compose(MyApplication.getInstance().<BaseBean<LoginBean>>setThread())
                .subscribe(new BaseObserver<LoginBean>() {
                    @Override
                    protected void onSuccees(BaseBean<LoginBean> t) throws Exception {
                        LogUtils.i("onResponse"+t.toString());
                        callback.onSuccess(t);
                    }

                    @Override
                    protected void onFailure(Throwable e, boolean isNetWorkError) throws Exception {
                        LogUtils.i("onResponse"+e.getLocalizedMessage());

                    }
                });
    }
}
