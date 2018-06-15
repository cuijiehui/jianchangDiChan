package com.cui.android.jianchengdichan.model.model;

import com.cui.android.jianchengdichan.MyApplication;
import com.cui.android.jianchengdichan.http.RetrofitFactory;
import com.cui.android.jianchengdichan.http.base.BaseBean;
import com.cui.android.jianchengdichan.http.base.BaseObserver;
import com.cui.android.jianchengdichan.http.bean.HomeCivilianListBean;
import com.cui.android.jianchengdichan.model.base.BaseModel;
import com.cui.android.jianchengdichan.model.interfaces.CallBack;

/**
 * @author CUI
 * @data 2018/6/12.
 * @details  便民服务首页界面Model
 */
public class CivilianListModel extends BaseModel<BaseBean<HomeCivilianListBean>>{
    @Override
    public void execute(final CallBack<BaseBean<HomeCivilianListBean>> callback) {
        RetrofitFactory.getInstence().API()
                .getHomeCivilianList()
                .compose(MyApplication.getInstance().<BaseBean<HomeCivilianListBean>>setThread())
                .subscribe(new BaseObserver<HomeCivilianListBean>() {
                    @Override
                    protected void onSuccees(BaseBean<HomeCivilianListBean> t) throws Exception {
                        callback.onSuccess(t);
                    }

                    @Override
                    protected void onCodeError(BaseBean<HomeCivilianListBean> t) throws Exception {
                        callback.onFailure(t.getMsg());
                    }

                    @Override
                    protected void onError(Throwable e, boolean isNetWorkError) throws Exception {
                        callback.onError();

                    }

                });
    }
}
