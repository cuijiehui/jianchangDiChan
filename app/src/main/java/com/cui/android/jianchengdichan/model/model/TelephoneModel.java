package com.cui.android.jianchengdichan.model.model;

import com.cui.android.jianchengdichan.MyApplication;
import com.cui.android.jianchengdichan.http.RetrofitFactory;
import com.cui.android.jianchengdichan.http.base.BaseBean;
import com.cui.android.jianchengdichan.http.base.BaseObserver;
import com.cui.android.jianchengdichan.http.bean.TelephoneBean;
import com.cui.android.jianchengdichan.model.base.BaseModel;
import com.cui.android.jianchengdichan.model.interfaces.CallBack;

import java.util.List;

import okhttp3.RequestBody;

/**
 * @author CUI
 * @data 2018/6/14.
 * @details
 */
public class TelephoneModel extends BaseModel<BaseBean<List<TelephoneBean>>> {
    @Override
    public void execute(final CallBack<BaseBean<List<TelephoneBean>>> callback) {
        RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), mParams[0]);

        RetrofitFactory.getInstence().API()
                .getTelephone(body)
                .compose(MyApplication.getInstance().<BaseBean<List<TelephoneBean>>>setThread())
                .subscribe(new BaseObserver<List<TelephoneBean>>() {
                    @Override
                    protected void onSuccees(BaseBean<List<TelephoneBean>> t) throws Exception {
                        callback.onSuccess(t);
                    }

                    @Override
                    protected void onCodeError(BaseBean<List<TelephoneBean>> t) throws Exception {
                        callback.onFailure(t.getMsg());
                    }

                    @Override
                    protected void onError(Throwable e, boolean isNetWorkError) throws Exception {
                        callback.onError();
                    }
                });
    }
}
