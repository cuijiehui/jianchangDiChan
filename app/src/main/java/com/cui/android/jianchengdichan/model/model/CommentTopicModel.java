package com.cui.android.jianchengdichan.model.model;

import com.cui.android.jianchengdichan.MyApplication;
import com.cui.android.jianchengdichan.http.RetrofitFactory;
import com.cui.android.jianchengdichan.http.base.BaseBean;
import com.cui.android.jianchengdichan.http.base.BaseObserver;
import com.cui.android.jianchengdichan.http.bean.CommentActBean;
import com.cui.android.jianchengdichan.http.bean.CommentTopicBean;
import com.cui.android.jianchengdichan.http.bean.TopicListBean;
import com.cui.android.jianchengdichan.model.base.BaseModel;
import com.cui.android.jianchengdichan.model.interfaces.CallBack;

import java.util.List;

/**
 * @author CUI
 * @data 2018/6/15.
 * @details
 */
public class CommentTopicModel extends BaseModel<BaseBean<List<TopicListBean>>> {
    @Override
    public void execute(final CallBack<BaseBean<List<TopicListBean>>> callback) {
        RetrofitFactory.getInstence().API()
                .getCommentTopic()
                .compose(MyApplication.getInstance().<BaseBean<List<TopicListBean>>>setThread())
                .subscribe(new BaseObserver<List<TopicListBean>>() {
                    @Override
                    protected void onSuccees(BaseBean<List<TopicListBean>> t) throws Exception {
                        callback.onSuccess(t);
                    }

                    @Override
                    protected void onCodeError(BaseBean<List<TopicListBean>> t) throws Exception {
                        callback.onFailure(t.getMsg());
                    }


                    @Override
                    protected void onError(Throwable e, boolean isNetWorkError) throws Exception {
                        callback.onError();
                    }
                });
    }
}
