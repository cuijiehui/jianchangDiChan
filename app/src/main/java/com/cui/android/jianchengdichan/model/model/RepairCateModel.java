package com.cui.android.jianchengdichan.model.model;

import com.cui.android.jianchengdichan.MyApplication;
import com.cui.android.jianchengdichan.http.RetrofitFactory;
import com.cui.android.jianchengdichan.http.base.BaseBean;
import com.cui.android.jianchengdichan.http.base.BaseObserver;
import com.cui.android.jianchengdichan.http.bean.RenovationBean;
import com.cui.android.jianchengdichan.http.bean.RepairCateBean;
import com.cui.android.jianchengdichan.model.base.BaseModel;
import com.cui.android.jianchengdichan.model.interfaces.CallBack;

import java.util.List;

public class RepairCateModel extends BaseModel<BaseBean<List<RepairCateBean>>> {
    @Override
    public void execute(final CallBack<BaseBean<List<RepairCateBean>>> callback) {
        RetrofitFactory.getInstence().API()
                .getRepairCate()
                .compose(MyApplication.getInstance().<BaseBean<List<RepairCateBean>>>setThread())
                .subscribe(new BaseObserver<List<RepairCateBean>>() {
                    @Override
                    protected void onSuccees(BaseBean<List<RepairCateBean>> t) throws Exception {
                        callback.onSuccess(t);
                    }

                    @Override
                    protected void onCodeError(BaseBean<List<RepairCateBean>> t) throws Exception {
                        callback.onFailure(t.getMsg());
                    }

                    @Override
                    protected void onError(Throwable e, boolean isNetWorkError) throws Exception {
                        callback.onError();

                    }
                });
    }
}
