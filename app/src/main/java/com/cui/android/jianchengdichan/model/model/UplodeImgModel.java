package com.cui.android.jianchengdichan.model.model;

import com.cui.android.jianchengdichan.http.base.BaseBean;
import com.cui.android.jianchengdichan.http.bean.UplodeImgBean;
import com.cui.android.jianchengdichan.model.base.BaseModel;
import com.cui.android.jianchengdichan.model.interfaces.CallBack;

import java.util.List;

public class UplodeImgModel extends BaseModel<BaseBean<List<UplodeImgBean>>> {
    @Override
    public void execute(final CallBack<BaseBean<List<UplodeImgBean>>> callback) {
//        JsonObject jsonObject = new JsonObject();
////        jsonObject.addProperty("uid",mParams[0]);
////        jsonObject.addProperty("token",mParams[1]);
//        jsonObject.addProperty("num",mParams[2]);
//        String json =jsonObject.toString();
//        LogUtils.i("json="+json);
//        RequestBody body= RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),json);
//        //多张图片
//        int num = new Integer(mParams[2]);
////        List<MultipartBody.Part> parts = new ArrayList<>();
////        for (int i = 0; i < num; i++) {
//            File file = new File(mParams[3]);
//            final RequestBody requestFile =
//                    RequestBody.create(MediaType.parse("image/jpeg"), file);
//
//            MultipartBody.Part bodys =
//                    MultipartBody.Part.createFormData("file1", "image1.jpeg", requestFile);
////            parts.add(bodys);
////        }
//        LogcatHelper.getInstance(MyApplication.getAppContext()).start();
//        Map<String, RequestBody> clubUploadPicRequest = new HashMap<>();
//        RequestBody nums = RequestBody.create(MediaType.parse("form-data"),mParams[2] );
//        clubUploadPicRequest.put("num",nums);
////        RequestBody headpic = RequestBody.create(MediaType.parse("multipart/form-data"), new File(mParams[3]));
////        clubUploadPicRequest.put("file1",headpic);
//
//        RetrofitFactory.getInstence().API()
//                .uploadImg(clubUploadPicRequest)
//                .compose(MyApplication.getInstance().<BaseBean<List<UplodeImgBean>>>setThread())
//                .subscribe(new BaseObserver<List<UplodeImgBean>>() {
//                    @Override
//                    protected void onSuccees(BaseBean<List<UplodeImgBean>> t) throws Exception {
//                        callback.onSuccess(t);
//                        LogcatHelper.getInstance(MyApplication.getAppContext()).stop();
//
//                    }
//                    @Override
//                    protected void onCodeError(BaseBean<List<UplodeImgBean>> t) throws Exception {
//                        callback.onFailure(t.getMsg());
//                        LogcatHelper.getInstance(MyApplication.getAppContext()).stop();
//
//                    }
//                    @Override
//                    protected void onFailure(Throwable e, boolean isNetWorkError) throws Exception {
//                        callback.onError();
//                        LogcatHelper.getInstance(MyApplication.getAppContext()).stop();
//
//                    }
//                });

    }



//    public void uploadPicData(String picPath){
//        String fileKey = "files";
//        Map<String, RequestBody> clubUploadPicRequest = getBasePhpRequest();
//        RequestBody headpic = RequestBody.create(MediaType.parse("multipart/form-data"), new File(picPath));
//        fileKey += "\"; filename=\"" + new File(picPath).getName();
//        clubUploadPicRequest.put(fileKey, headpic);
//        RequestBody type = RequestBody.create(MediaType.parse("form-data"), "3");
//        clubUploadPicRequest.put("type" , type);
//        RequestBody liveid = RequestBody.create(MediaType.parse("form-data"), activeid);
//        clubUploadPicRequest.put("liveid" , liveid);
//        appAction.uploadPicData(new CallBack<UploadPicResponse>(ClubActivityDetailActivity.this) {
//            @Override
//            public void onCompleted() {
//                SimpleHUD.dismiss();
//            }
//            @Override
//            public void onError(Throwable e) {
//                super.onError(e);
//                SimpleHUD.dismiss();
//            }
//            @Override
//            public void onNext(UploadPicResponse uploadPicResponse) {
//                String json = JsonUtil.GsonString(uploadPicResponse);
//                LogHelper.v("lee", "uploadPicResponse:" + json);
//            }
//        }, clubUploadPicRequest);
//    }
//    public Map<String, RequestBody> getBasePhpRequest(){
//        Map<String, RequestBody> request = new HashMap<>();
//        RequestBody userid = RequestBody.create(MediaType.parse("form-data"), PreferenceHelper.get(ClubActivityDetailActivity.this, PreferenceHelper.PREFERENCE_LOGIN, "userid", 0) + "");
//        request.put("userid", userid);
//        RequestBody accessToken = RequestBody.create(MediaType.parse("form-data"),Config.ACCESSTOKEN);
//        request.put("accessToken",accessToken);
//        RequestBody clientVersion = RequestBody.create(MediaType.parse("form-data"),Config.CLIENTVERSION);
//        request.put("clientVersion",clientVersion);
//        RequestBody clientName = RequestBody.create(MediaType.parse("form-data"),Config.CLIENTNAME);
//        request.put("clientName",clientName);
//        RequestBody version = RequestBody.create(MediaType.parse("form-data"),Config.HTTP_VERSION);
//        request.put("version",version);
//        return request;
//    }

}
