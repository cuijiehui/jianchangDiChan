package com.cui.android.jianchengdichan.presenter;

import com.cui.android.jianchengdichan.http.base.BaseBean;
import com.cui.android.jianchengdichan.http.config.URLConfig;
import com.cui.android.jianchengdichan.model.DataModel;
import com.cui.android.jianchengdichan.model.Token;
import com.cui.android.jianchengdichan.model.interfaces.CallBack;
import com.cui.android.jianchengdichan.utils.LogUtils;
import com.cui.android.jianchengdichan.utils.Okhttp3Utils;
import com.cui.android.jianchengdichan.view.ui.ForgetPwdActivity;
import com.google.gson.JsonObject;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class ForgetPwdPresenter extends BasePresenter<ForgetPwdActivity>{
    /**
     *  获取验证码
     * @param mobile 用户名
     * @param temp sms_reg | 注册 sms_forget | 忘记密码 sms_bind | 绑定 sms_reset | 重置密码
     * @param imgcode 默认0
     */
    public void getCode(String mobile,String temp,String imgcode){
        LogUtils.i("getCode()");
        if (!isViewAttached()) {
            //如果没有View引用就不加载数据
            return;
        }
        Map map = new HashMap();
        map.put("mobile",mobile);
        map.put("temp",temp);
        map.put("imgcode",imgcode);
        Okhttp3Utils.getInstance().doPost("http://wx.szshide.shop/" + URLConfig.POST_REGISTER_CODE_URL, map, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                LogUtils.e("onFailure()"+e.getMessage());
            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String json =response.body().string();
                LogUtils.e("onResponse()="+json);
                try {
                    JSONObject jsonObject = new JSONObject(json);
                    int id = jsonObject.optInt("code");
                    String message = jsonObject.optString("message");

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }
    public void forgetPwd(String username,String pwd,String code){
        LogUtils.i("forgetPwd()");
        if (!isViewAttached()) {
            //如果没有View引用就不加载数据
            return;
        }
        getView().showLoading();
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("username",username);
        jsonObject.addProperty("pwd",pwd);
        jsonObject.addProperty("code",code);
        String json =jsonObject.toString();
        LogUtils.i("json=()"+json);
        DataModel.request(Token.API_FORGET_PWD)
                .params(json)
                .execute(new CallBack<BaseBean>() {

                    @Override
                    public void onSuccess(BaseBean data) {
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
