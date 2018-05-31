package com.cui.android.jianchengdichan.model.model;

import com.cui.android.jianchengdichan.MyApplication;
import com.cui.android.jianchengdichan.http.RetrofitFactory;
import com.cui.android.jianchengdichan.http.base.BaseObserver;
import com.cui.android.jianchengdichan.http.bean.BaseBean;
import com.cui.android.jianchengdichan.model.base.BaseModel;
import com.cui.android.jianchengdichan.model.interfaces.CallBack;
import com.cui.android.jianchengdichan.view.ui.customview.ChildCommunityBean;

import java.util.List;

import okhttp3.RequestBody;

public class PropertyListModel extends BaseModel<BaseBean<List<ChildCommunityBean>>> {
    @Override
    public void execute(final CallBack<BaseBean<List<ChildCommunityBean>>> callback) {
        RequestBody body= RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),mParams[0]);
        RetrofitFactory.getInstence().API()
                .getPropertyList(body)
                .compose(MyApplication.getInstance().<BaseBean<List<ChildCommunityBean>>>setThread())
                .subscribe(new BaseObserver<List<ChildCommunityBean>>() {
                    @Override
                    protected void onSuccees(BaseBean<List<ChildCommunityBean>> t) throws Exception {
                        callback.onSuccess(t);
                    }

                    @Override
                    protected void onCodeError(BaseBean<List<ChildCommunityBean>> t) throws Exception {
                        callback.onFailure(t.getMsg());
                    }

                    @Override
                    protected void onFailure(Throwable e, boolean isNetWorkError) throws Exception {
                        callback.onError();

                    }
                });
    }
}
