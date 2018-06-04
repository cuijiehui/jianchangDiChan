package com.cui.android.jianchengdichan.model.model;

import com.cui.android.jianchengdichan.MyApplication;
import com.cui.android.jianchengdichan.http.RetrofitFactory;
import com.cui.android.jianchengdichan.http.base.BaseBean;
import com.cui.android.jianchengdichan.http.base.BaseObserver;
import com.cui.android.jianchengdichan.http.bean.NoticeBean;
import com.cui.android.jianchengdichan.model.base.BaseModel;
import com.cui.android.jianchengdichan.model.interfaces.CallBack;
import com.cui.android.jianchengdichan.view.ui.MyApplyActivity;

import java.util.List;

import okhttp3.RequestBody;

public class NoticeListModel extends BaseModel<BaseBean<List<NoticeBean>>> {
    @Override
    public void execute(final CallBack<BaseBean<List<NoticeBean>>> callback) {
        RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), mParams[0]);
        RetrofitFactory.getInstence().API()
                .getNoticeList(body)
                .compose(MyApplication.getInstance().<BaseBean<List<NoticeBean>>>setThread())
                .subscribe(new BaseObserver<List<NoticeBean>>() {
                    @Override
                    protected void onSuccees(BaseBean<List<NoticeBean>> t) throws Exception {
                        callback.onSuccess(t);
                    }

                    @Override
                    protected void onCodeError(BaseBean<List<NoticeBean>> t) throws Exception {
                        callback.onFailure(t.getMsg());
                    }

                    @Override
                    protected void onFailure(Throwable e, boolean isNetWorkError) throws Exception {
                        callback.onError();
                    }
                });
    }
}
