package com.cui.android.jianchengdichan.model.model;

import com.cui.android.jianchengdichan.MyApplication;
import com.cui.android.jianchengdichan.http.RetrofitFactory;
import com.cui.android.jianchengdichan.http.base.BaseBean;
import com.cui.android.jianchengdichan.http.base.BaseObserver;
import com.cui.android.jianchengdichan.http.bean.CivilianDetailBean;
import com.cui.android.jianchengdichan.model.base.BaseModel;
import com.cui.android.jianchengdichan.model.interfaces.CallBack;

import okhttp3.RequestBody;

/**
 * @author CUI
 * @data 2018/6/12.
 * @details
 */
public class CivilianDetailModel extends BaseModel<BaseBean<CivilianDetailBean>> {
    @Override
    public void execute(final CallBack<BaseBean<CivilianDetailBean>> callback) {
        RequestBody body= RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),mParams[0]);
        RetrofitFactory.getInstence().API()
                .getCivilianDetail(body)
                .compose(MyApplication.getInstance().<BaseBean<CivilianDetailBean>>setThread())
                .subscribe(new BaseObserver<CivilianDetailBean>() {
                    @Override
                    protected void onSuccees(BaseBean<CivilianDetailBean> t) throws Exception {
                        callback.onSuccess(t);
                    }

                    @Override
                    protected void onCodeError(BaseBean<CivilianDetailBean> t) throws Exception {
                        callback.onFailure(t.getMsg());
                    }


                    @Override
                    protected void onError(Throwable e, boolean isNetWorkError) throws Exception {
                        callback.onError();

                    }
                });
    }
}
