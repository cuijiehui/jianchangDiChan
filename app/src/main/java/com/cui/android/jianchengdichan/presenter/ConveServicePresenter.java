package com.cui.android.jianchengdichan.presenter;

import com.cui.android.jianchengdichan.http.base.BaseBean;
import com.cui.android.jianchengdichan.http.bean.HomeCivilianListBean;
import com.cui.android.jianchengdichan.model.DataModel;
import com.cui.android.jianchengdichan.model.Token;
import com.cui.android.jianchengdichan.model.interfaces.CallBack;
import com.cui.android.jianchengdichan.utils.LogUtils;
import com.cui.android.jianchengdichan.view.ui.avtivity.ConveServiceActivity;

/**
 * @author CUI
 * @data 2018/6/12.
 * @details
 */
public class ConveServicePresenter extends BasePresenter<ConveServiceActivity> {
  public void getData(){
      LogUtils.i("getData()");
      if (!isViewAttached()) {
          //如果没有View引用就不加载数据
          return;
      }
      DataModel.request(Token.API_CIVILIAN_LIST_MODEL)
              .execute(new CallBack<BaseBean<HomeCivilianListBean>>() {
                  @Override
                  public void onSuccess(BaseBean<HomeCivilianListBean> data) {
                    getView().getData(data.getData());
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
