package com.cui.android.jianchengdichan.presenter;

import com.cui.android.jianchengdichan.http.bean.BaseBean;
import com.cui.android.jianchengdichan.http.bean.LoginBean;
import com.cui.android.jianchengdichan.model.DataModel;
import com.cui.android.jianchengdichan.model.Token;
import com.cui.android.jianchengdichan.model.interfaces.CallBack;
import com.cui.android.jianchengdichan.utils.LogUtils;
import com.cui.android.jianchengdichan.view.ui.LoginActivity;

public class LoginPresenter extends BasePresenter<LoginActivity> {
    public void login(String username,String pwd){
        LogUtils.i("login()");
        if (!isViewAttached()) {
            //如果没有View引用就不加载数据
            return;
        }
        getView().showLoading();
        DataModel.request(Token.API_LOGIN)
                .params(username,pwd)
                .execute(new CallBack<BaseBean<LoginBean>>() {

                    @Override
                    public void onSuccess(BaseBean<LoginBean> data) {
                    getView().showView(data.toString(),200);
                    }

                    @Override
                    public void onFailure(String msg) {
                        getView().showView(msg,-200);
                    }

                    @Override
                    public void onError() {
                        getView().showView("",-200);
                    }

                    @Override
                    public void onComplete() {
                        getView().hideLoading();
                    }
                });
    }
}
