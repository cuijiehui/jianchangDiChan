package com.cui.android.jianchengdichan.model.model;

import com.cui.android.jianchengdichan.MyApplication;
import com.cui.android.jianchengdichan.http.RetrofitFactory;
import com.cui.android.jianchengdichan.http.base.BaseBean;
import com.cui.android.jianchengdichan.http.base.BaseObserver;
import com.cui.android.jianchengdichan.http.bean.CivilianserviceBean;
import com.cui.android.jianchengdichan.http.bean.CommentActBean;
import com.cui.android.jianchengdichan.model.base.BaseModel;
import com.cui.android.jianchengdichan.model.interfaces.CallBack;

import java.util.List;

/**
 * @author CUI
 * @data 2018/6/15.
 * @details
 */
public class CommentActModel extends BaseModel<BaseBean<CommentActBean>> {
    @Override
    public void execute(final CallBack<BaseBean<CommentActBean>> callback) {
        RetrofitFactory.getInstence().API()
                .getCommentAct()
                .compose(MyApplication.getInstance().<BaseBean<CommentActBean>>setThread())
                .subscribe(new BaseObserver<CommentActBean>() {
                    @Override
                    protected void onSuccees(BaseBean<CommentActBean> t) throws Exception {
                        callback.onSuccess(t);
                    }

                    @Override
                    protected void onCodeError(BaseBean<CommentActBean> t) throws Exception {
                        callback.onFailure(t.getMsg());
                    }


                    @Override
                    protected void onError(Throwable e, boolean isNetWorkError) throws Exception {
                        callback.onError();
                    }
                });
    }
}
