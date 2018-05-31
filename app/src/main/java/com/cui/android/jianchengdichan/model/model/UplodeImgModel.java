package com.cui.android.jianchengdichan.model.model;

import com.cui.android.jianchengdichan.MyApplication;
import com.cui.android.jianchengdichan.http.RetrofitFactory;
import com.cui.android.jianchengdichan.http.base.BaseObserver;
import com.cui.android.jianchengdichan.http.bean.BaseBean;
import com.cui.android.jianchengdichan.http.bean.UplodeImgBean;
import com.cui.android.jianchengdichan.model.base.BaseModel;
import com.cui.android.jianchengdichan.model.interfaces.CallBack;
import com.cui.android.jianchengdichan.utils.LogUtils;
import com.cui.android.jianchengdichan.utils.LogcatHelper;
import com.google.gson.JsonObject;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class UplodeImgModel extends BaseModel<BaseBean<List<UplodeImgBean>>> {
    @Override
    public void execute(final CallBack<BaseBean<List<UplodeImgBean>>> callback) {
        JsonObject jsonObject = new JsonObject();
//        jsonObject.addProperty("uid",mParams[0]);
//        jsonObject.addProperty("token",mParams[1]);
        jsonObject.addProperty("num",mParams[2]);
        String json =jsonObject.toString();
        LogUtils.i("json="+json);
        RequestBody body= RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),json);
        //多张图片
        int num = new Integer(mParams[2]);
//        List<MultipartBody.Part> parts = new ArrayList<>();
//        for (int i = 0; i < num; i++) {
            File file = new File(mParams[3]);
            final RequestBody requestFile =
                    RequestBody.create(MediaType.parse("image/jpg"), file);

            MultipartBody.Part bodys =
                    MultipartBody.Part.createFormData("file1", "image1.jpg", requestFile);
//            parts.add(bodys);
//        }
        LogcatHelper.getInstance(MyApplication.getAppContext()).start();

        RetrofitFactory.getInstence().API()
                .uploadImg(body,bodys)
                .compose(MyApplication.getInstance().<BaseBean<List<UplodeImgBean>>>setThread())
                .subscribe(new BaseObserver<List<UplodeImgBean>>() {
                    @Override
                    protected void onSuccees(BaseBean<List<UplodeImgBean>> t) throws Exception {
                        callback.onSuccess(t);
                        LogcatHelper.getInstance(MyApplication.getAppContext()).stop();

                    }
                    @Override
                    protected void onCodeError(BaseBean<List<UplodeImgBean>> t) throws Exception {
                        callback.onFailure(t.getMsg());
                        LogcatHelper.getInstance(MyApplication.getAppContext()).stop();

                    }
                    @Override
                    protected void onFailure(Throwable e, boolean isNetWorkError) throws Exception {
                        callback.onError();
                        LogcatHelper.getInstance(MyApplication.getAppContext()).stop();

                    }
                });
    }
}
