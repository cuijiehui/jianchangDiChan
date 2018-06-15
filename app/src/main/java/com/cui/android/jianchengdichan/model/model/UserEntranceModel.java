package com.cui.android.jianchengdichan.model.model;

import com.cui.android.jianchengdichan.MyApplication;
import com.cui.android.jianchengdichan.http.RetrofitFactory;
import com.cui.android.jianchengdichan.http.base.BaseObserver;
import com.cui.android.jianchengdichan.http.base.BaseBean;
import com.cui.android.jianchengdichan.http.bean.UserEntranceBean;
import com.cui.android.jianchengdichan.model.base.BaseModel;
import com.cui.android.jianchengdichan.model.interfaces.CallBack;

import java.util.List;

import okhttp3.RequestBody;

public class UserEntranceModel extends BaseModel<BaseBean<List<UserEntranceBean>>> {
    @Override
    public void execute(final CallBack<BaseBean<List<UserEntranceBean>>> callback) {
        RequestBody body= RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),mParams[0]);
        RetrofitFactory.getInstence().API()
                .getUserEntrance(body)
                .compose(MyApplication.getInstance().<BaseBean<List<UserEntranceBean>>>setThread())
                .subscribe(new BaseObserver<List<UserEntranceBean>>() {
                    @Override
                    protected void onSuccees(BaseBean<List<UserEntranceBean>> t) throws Exception {
                        callback.onSuccess(t);
                    }

                    @Override
                    protected void onCodeError(BaseBean<List<UserEntranceBean>> t) throws Exception {
                        callback.onFailure(t.getMsg());

                    }

                    @Override
                    protected void onError(Throwable e, boolean isNetWorkError) throws Exception {
                        callback.onError();

                    }
                });
    }
}
