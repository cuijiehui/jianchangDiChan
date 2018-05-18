package com.cui.android.jianchengdichan.model.model;

import com.cui.android.jianchengdichan.MyApplication;
import com.cui.android.jianchengdichan.http.RetrofitFactory;
import com.cui.android.jianchengdichan.http.base.BaseObserver;
import com.cui.android.jianchengdichan.http.bean.BaseBean;
import com.cui.android.jianchengdichan.http.bean.LoginBean;
import com.cui.android.jianchengdichan.http.bean.SplashAdvBean;
import com.cui.android.jianchengdichan.http.config.RxSchedulers;
import com.cui.android.jianchengdichan.model.base.BaseModel;
import com.cui.android.jianchengdichan.model.interfaces.CallBack;
import com.cui.android.jianchengdichan.utils.LogUtils;

import io.reactivex.ObservableTransformer;
import io.reactivex.Observer;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * @author CUI
 * @data 2018/5/17.
 * @description 你只有非常努力，才能看起来毫不费力
 */
public class SplashAdvModel extends BaseModel<BaseBean<LoginBean>>{

    @Override
    public void execute(final CallBack<BaseBean<LoginBean>> callback) {
        LogUtils.i("SplashAdvModel.execute()");
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
