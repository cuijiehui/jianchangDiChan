package com.cui.android.jianchengdichan.model.model;

import com.cui.android.jianchengdichan.MyApplication;
import com.cui.android.jianchengdichan.http.RetrofitFactory;
import com.cui.android.jianchengdichan.http.base.BaseObserver;
import com.cui.android.jianchengdichan.http.base.BaseBean;
import com.cui.android.jianchengdichan.http.bean.ChargeCateBean;
import com.cui.android.jianchengdichan.model.base.BaseModel;
import com.cui.android.jianchengdichan.model.interfaces.CallBack;
import com.cui.android.jianchengdichan.utils.LogUtils;

import java.util.List;

import okhttp3.RequestBody;

public class ChargeCateModel extends BaseModel<BaseBean<List<ChargeCateBean>>> {
    @Override
    public void execute(final CallBack<BaseBean<List<ChargeCateBean>>> callback) {
        LogUtils.i("ChargeCateModel.execute()");
        RequestBody body= RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),mParams[0]);
        RetrofitFactory.getInstence().API()
                .getChargeCates(body)
                .compose(MyApplication.getInstance().<BaseBean<List<ChargeCateBean>>>setThread())
                .subscribe(new BaseObserver<List<ChargeCateBean>>() {
                    @Override
                    protected void onSuccees(BaseBean<List<ChargeCateBean>> t) throws Exception {
                        LogUtils.i("onResponse"+t.toString());
                        callback.onSuccess(t);
                    }

                    @Override
                    protected void onCodeError(BaseBean<List<ChargeCateBean>> t) throws Exception {
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
