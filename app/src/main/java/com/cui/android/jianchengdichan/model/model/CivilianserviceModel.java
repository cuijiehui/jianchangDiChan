package com.cui.android.jianchengdichan.model.model;

import com.cui.android.jianchengdichan.MyApplication;
import com.cui.android.jianchengdichan.http.RetrofitFactory;
import com.cui.android.jianchengdichan.http.base.BaseBean;
import com.cui.android.jianchengdichan.http.base.BaseObserver;
import com.cui.android.jianchengdichan.http.bean.CivilianserviceBean;
import com.cui.android.jianchengdichan.model.base.BaseModel;
import com.cui.android.jianchengdichan.model.interfaces.CallBack;

import java.util.List;

/**
 * @author CUI
 * @data 2018/6/14.
 * @details
 */
public class CivilianserviceModel extends BaseModel<BaseBean<List<CivilianserviceBean>>> {
    @Override
    public void execute(final CallBack<BaseBean<List<CivilianserviceBean>>> callback) {
        RetrofitFactory.getInstence().API()
                .getCivilianservice()
                .compose(MyApplication.getInstance().<BaseBean<List<CivilianserviceBean>>>setThread())
                .subscribe(new BaseObserver<List<CivilianserviceBean>>() {
                    @Override
                    protected void onSuccees(BaseBean<List<CivilianserviceBean>> t) throws Exception {
                        callback.onSuccess(t);
                    }

                    @Override
                    protected void onCodeError(BaseBean<List<CivilianserviceBean>> t) throws Exception {
                        callback.onFailure(t.getMsg());
                    }


                    @Override
                    protected void onError(Throwable e, boolean isNetWorkError) throws Exception {
                        callback.onError();
                    }
                });
    }
}
