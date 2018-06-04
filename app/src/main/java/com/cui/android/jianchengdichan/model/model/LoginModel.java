package com.cui.android.jianchengdichan.model.model;

import com.cui.android.jianchengdichan.MyApplication;
import com.cui.android.jianchengdichan.http.RetrofitFactory;
import com.cui.android.jianchengdichan.http.base.BaseObserver;
import com.cui.android.jianchengdichan.http.base.BaseBean;
import com.cui.android.jianchengdichan.http.bean.LoginBean;
import com.cui.android.jianchengdichan.model.base.BaseModel;
import com.cui.android.jianchengdichan.model.interfaces.CallBack;
import com.cui.android.jianchengdichan.utils.LogUtils;
import com.cui.android.jianchengdichan.utils.SPKey;
import com.cui.android.jianchengdichan.utils.SPUtils;

import okhttp3.RequestBody;

public class LoginModel  extends BaseModel<BaseBean<LoginBean>> {
    @Override
    public void execute(final CallBack<BaseBean<LoginBean>> callback) {
        LogUtils.i("LoginModel.execute()");
        RequestBody body= RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),mParams[0]);
        RetrofitFactory.getInstence().API()
                .getLogin(body)
                .compose(MyApplication.getInstance().<BaseBean<LoginBean>>setThread())
                .subscribe(new BaseObserver<LoginBean>() {
                    @Override
                    protected void onSuccees(BaseBean<LoginBean> t) throws Exception {
                        LogUtils.i("onResponse"+t.toString());
                        saveData(t.getData());
                        callback.onSuccess(t);
                    }

                    @Override
                    protected void onCodeError(BaseBean<LoginBean> t) throws Exception {
                        LogUtils.i("onResponse"+t.toString());
                        callback.onFailure(t.getMsg());

                    }

                    @Override
                    protected void onFailure(Throwable e, boolean isNetWorkError) throws Exception {
                        LogUtils.i("onResponse"+e.getLocalizedMessage());
                        callback.onError();
                    }
                });
    }

    private void saveData(LoginBean data) {
        SPUtils.INSTANCE.setSPValue(SPKey.SP_USER_TOKEN_KEY,data.getToken());
        SPUtils.INSTANCE.setSPValue(SPKey.SP_USER_UID_KEY,data.getUid());
        SPUtils.INSTANCE.setSPValue(SPKey.SP_USER_COM_ID_KEY,data.getCom_id());
        SPUtils.INSTANCE.setSPValue(SPKey.SP_USER_COMMUNITY_ID_KEY,data.getCommunity_id());
        SPUtils.INSTANCE.setSPValue(SPKey.SP_USER_UNIT_ID_KEY,data.getUnit_id());
        SPUtils.INSTANCE.setSPValue(SPKey.SP_USER_PROPERTY_ID_KEY,data.getProperty_id());
        SPUtils.INSTANCE.setSPValue(SPKey.SP_USER_PIC_URL_KEY,data.getPic());
        SPUtils.INSTANCE.setSPValue(SPKey.SP_USER_TRUE_NAME_KEY,data.getName());
        SPUtils.INSTANCE.setSPValue(SPKey.SP_USER_NAME_KEY,data.getNickname());

    }
}
