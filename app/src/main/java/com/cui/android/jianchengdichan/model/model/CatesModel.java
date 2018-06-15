package com.cui.android.jianchengdichan.model.model;

import com.cui.android.jianchengdichan.MyApplication;
import com.cui.android.jianchengdichan.http.RetrofitFactory;
import com.cui.android.jianchengdichan.http.base.BaseObserver;
import com.cui.android.jianchengdichan.http.base.BaseBean;
import com.cui.android.jianchengdichan.http.bean.CatesBean;
import com.cui.android.jianchengdichan.model.base.BaseModel;
import com.cui.android.jianchengdichan.model.interfaces.CallBack;
import com.cui.android.jianchengdichan.utils.LogUtils;

import java.util.List;

import okhttp3.RequestBody;

public class CatesModel extends BaseModel<BaseBean<List<CatesBean>>> {
    @Override
    public void execute(final CallBack<BaseBean<List<CatesBean>>> callback) {
        LogUtils.i("CatesModel.execute()");
        RequestBody body= RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),mParams[0]);
        RetrofitFactory.getInstence().API()
                .getCates(body)
                .compose(MyApplication.getInstance().<BaseBean<List<CatesBean>>>setThread())
                .subscribe(new BaseObserver<List<CatesBean>>() {
                    @Override
                    protected void onSuccees(BaseBean<List<CatesBean>> t) throws Exception {
                        LogUtils.i("onResponse"+t.toString());
                        callback.onSuccess(t);
                    }

                    @Override
                    protected void onCodeError(BaseBean<List<CatesBean>> t) throws Exception {
                        LogUtils.i("onResponse"+t.toString());
                        callback.onFailure(t.getMsg());

                    }


                    @Override
                    protected void onError(Throwable e, boolean isNetWorkError) throws Exception {
                        callback.onError();

                    }
                });

    }
}
