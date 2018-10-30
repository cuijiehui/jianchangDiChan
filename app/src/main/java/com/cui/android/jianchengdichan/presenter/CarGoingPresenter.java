package com.cui.android.jianchengdichan.presenter;

import com.cui.android.jianchengdichan.http.base.BaseBean;
import com.cui.android.jianchengdichan.http.bean.CarGoingBean;
import com.cui.android.jianchengdichan.model.DataModel;
import com.cui.android.jianchengdichan.model.Token;
import com.cui.android.jianchengdichan.model.interfaces.CallBack;
import com.cui.android.jianchengdichan.utils.LogUtils;
import com.cui.android.jianchengdichan.view.ui.avtivity.CarGoingActivity;

import java.util.List;

public class CarGoingPresenter extends BasePresenter<CarGoingActivity> {
    public void getCarGoingInfo(){
        LogUtils.i("getPhoneList()");
        if (!isViewAttached()) {
            //如果没有View引用就不加载数据
            return;
        }

        DataModel.request(Token.API_CARGOING_MODEL)
                .execute(new CallBack<BaseBean<List<CarGoingBean>>>() {
                    @Override
                    public void onSuccess(BaseBean<List<CarGoingBean>> data) {
                        getView().getCarGoingInfo(data.getData());
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

                    }
                });
    }
}
