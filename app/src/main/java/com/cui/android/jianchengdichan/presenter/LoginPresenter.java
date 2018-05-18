package com.cui.android.jianchengdichan.presenter;

import com.cui.android.jianchengdichan.http.bean.BaseBean;
import com.cui.android.jianchengdichan.http.bean.LoginBean;
import com.cui.android.jianchengdichan.model.DataModel;
import com.cui.android.jianchengdichan.model.Token;
import com.cui.android.jianchengdichan.model.interfaces.CallBack;
import com.cui.android.jianchengdichan.utils.LogUtils;
import com.cui.android.jianchengdichan.view.ui.LoginActivity;

public class LoginPresenter extends BasePresenter<LoginActivity> {
    public void login(String mobile,String pwd){
        LogUtils.i("login()");
        if (!isViewAttached()) {
            //如果没有View引用就不加载数据
            return;
        }
        getView().showLoading();
        DataModel.request(Token.API_LOGIN)
                .params(mobile,pwd)
                .execute(new CallBack<BaseBean<LoginBean>>() {

                    @Override
                    public void onSuccess(BaseBean<LoginBean> data) {
                    getView().showView(data.toString());
                    }

                    @Override
                    public void onFailure(String msg) {

                    }

                    @Override
                    public void onError() {

                    }

                    @Override
                    public void onComplete() {
                        getView().hideLoading();
                    }
                });
    }
}
