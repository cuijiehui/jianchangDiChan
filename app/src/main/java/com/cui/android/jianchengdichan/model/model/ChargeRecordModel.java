package com.cui.android.jianchengdichan.model.model;

import com.cui.android.jianchengdichan.MyApplication;
import com.cui.android.jianchengdichan.http.RetrofitFactory;
import com.cui.android.jianchengdichan.http.base.BaseObserver;
import com.cui.android.jianchengdichan.http.bean.BaseBean;
import com.cui.android.jianchengdichan.http.bean.PayRecordsBean;
import com.cui.android.jianchengdichan.model.base.BaseModel;
import com.cui.android.jianchengdichan.model.interfaces.CallBack;

import java.util.List;

import okhttp3.RequestBody;

public class ChargeRecordModel extends BaseModel<BaseBean<List<PayRecordsBean>>> {
    @Override
    public void execute(final CallBack<BaseBean<List<PayRecordsBean>>> callback) {
        RequestBody body= RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),mParams[0]);

        RetrofitFactory.getInstence().API()
                .getChargeRecord(body)
                .compose(MyApplication.getInstance().<BaseBean<List<PayRecordsBean>>>setThread())
                .subscribe(new BaseObserver<List<PayRecordsBean>>() {
                    @Override
                    protected void onSuccees(BaseBean<List<PayRecordsBean>> t) throws Exception {
                        callback.onSuccess(t);
                    }

                    @Override
                    protected void onCodeError(BaseBean<List<PayRecordsBean>> t) throws Exception {
                        callback.onError();
                    }

                    @Override
                    protected void onFailure(Throwable e, boolean isNetWorkError) throws Exception {
                        callback.onFailure(e.getMessage());

                    }
                });
    }
}
