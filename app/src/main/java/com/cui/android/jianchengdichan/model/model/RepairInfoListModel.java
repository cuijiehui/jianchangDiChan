package com.cui.android.jianchengdichan.model.model;

import com.cui.android.jianchengdichan.MyApplication;
import com.cui.android.jianchengdichan.http.RetrofitFactory;
import com.cui.android.jianchengdichan.http.base.BaseBean;
import com.cui.android.jianchengdichan.http.base.BaseObserver;
import com.cui.android.jianchengdichan.http.bean.RepairsBean;
import com.cui.android.jianchengdichan.model.base.BaseModel;
import com.cui.android.jianchengdichan.model.interfaces.CallBack;

import java.util.List;

import okhttp3.RequestBody;

public class RepairInfoListModel extends BaseModel<BaseBean<List<RepairsBean>>> {
    @Override
    public void execute(final CallBack<BaseBean<List<RepairsBean>>> callback) {
        RequestBody body= RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),mParams[0]);
        RetrofitFactory.getInstence().API()
                .getRepairInfoList(body)
        .compose(MyApplication.getInstance().<BaseBean<List<RepairsBean>>>setThread())
        .subscribe(new BaseObserver<List<RepairsBean>>() {
            @Override
            protected void onSuccees(BaseBean<List<RepairsBean>> t) throws Exception {
                callback.onSuccess(t);
            }

            @Override
            protected void onCodeError(BaseBean<List<RepairsBean>> t) throws Exception {
                callback.onFailure(t.getMsg());
            }

            @Override
            protected void onFailure(Throwable e, boolean isNetWorkError) throws Exception {
                callback.onError();
            }
        });

    }
}
