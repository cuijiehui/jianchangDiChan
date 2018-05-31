package com.cui.android.jianchengdichan.presenter;

import com.cui.android.jianchengdichan.http.bean.BaseBean;
import com.cui.android.jianchengdichan.http.bean.HomeDataBean;
import com.cui.android.jianchengdichan.model.DataModel;
import com.cui.android.jianchengdichan.model.Token;
import com.cui.android.jianchengdichan.model.interfaces.CallBack;
import com.cui.android.jianchengdichan.utils.LogUtils;
import com.cui.android.jianchengdichan.view.ui.Fragment.MainHomeFragment;

public class MainHomePresenter extends BasePresenter<MainHomeFragment> {
    public void getData(){
        LogUtils.i("getData()");
        if (!isViewAttached()) {
            //如果没有View引用就不加载数据
            return;
        }
        getView().showLoading();
        DataModel.request(Token.API_HOME_DATA)
                .params()
                .execute(new CallBack<BaseBean<HomeDataBean>>() {

                    @Override
                    public void onSuccess(BaseBean<HomeDataBean> data) {
                        getView().showView(data.toString(),200);
                    }

                    @Override
                    public void onFailure(String msg) {
                        getView().onFailure(msg);
                    }

                    @Override
                    public void onError() {
                        getView().onError();
                    }

                    @Override
                    public void onComplete() {
                        getView().hideLoading();
                    }
                });
    }
}
