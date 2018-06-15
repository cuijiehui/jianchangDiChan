package com.cui.android.jianchengdichan.presenter;

import com.cui.android.jianchengdichan.http.base.BaseBean;
import com.cui.android.jianchengdichan.http.bean.LoginBean;
import com.cui.android.jianchengdichan.model.DataModel;
import com.cui.android.jianchengdichan.model.Token;
import com.cui.android.jianchengdichan.model.interfaces.CallBack;
import com.cui.android.jianchengdichan.utils.LogUtils;
import com.cui.android.jianchengdichan.view.ui.avtivity.SplashActivity;

/**
 * @author CUI
 * @data 2018/5/17.
 * @description 你只有非常努力，才能看起来毫不费力
 */
public class SplashPresenter extends BasePresenter<SplashActivity> {

    public void getAdvList() {
        LogUtils.i("SplashPresenter.getAdvList()");
        if (!isViewAttached()) {
            //如果没有View引用就不加载数据
            return;
        }
        DataModel.request(Token.API_SPLASH_ADV)
                .params("13229971658","123456")
                .execute(new CallBack<BaseBean<LoginBean>>() {

                    @Override
                    public void onSuccess(BaseBean<LoginBean> data) {

                    }

                    @Override
                    public void onFailure(String msg) {

                    }

                    @Override
                    public void onError() {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
