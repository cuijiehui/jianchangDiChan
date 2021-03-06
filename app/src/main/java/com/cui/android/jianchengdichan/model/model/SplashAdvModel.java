package com.cui.android.jianchengdichan.model.model;

import com.cui.android.jianchengdichan.MyApplication;
import com.cui.android.jianchengdichan.http.RetrofitFactory;
import com.cui.android.jianchengdichan.http.base.BaseObserver;
import com.cui.android.jianchengdichan.http.base.BaseBean;
import com.cui.android.jianchengdichan.http.bean.LoginBean;
import com.cui.android.jianchengdichan.model.base.BaseModel;
import com.cui.android.jianchengdichan.model.interfaces.CallBack;
import com.cui.android.jianchengdichan.utils.LogUtils;

import okhttp3.RequestBody;

/**
 * @author CUI
 * @data 2018/5/17.
 * @description 你只有非常努力，才能看起来毫不费力
 */
public class SplashAdvModel extends BaseModel<BaseBean<LoginBean>>{

    @Override
    public void execute(final CallBack<BaseBean<LoginBean>> callback) {
        LogUtils.i("SplashAdvModel.execute()");
        RequestBody body= RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),mParams[0]);
        RetrofitFactory.getInstence().API()
                .getLogin(body)
                .compose(MyApplication.getInstance().<BaseBean<LoginBean>>setThread())
                .subscribe(new BaseObserver<LoginBean>() {
                    @Override
                    protected void onSuccees(BaseBean<LoginBean> t) throws Exception {
                        LogUtils.i("onResponse"+t.toString());
                        callback.onSuccess(t);
                    }

                    @Override
                    protected void onCodeError(BaseBean<LoginBean> t) throws Exception {
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
