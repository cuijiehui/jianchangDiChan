package com.cui.android.jianchengdichan.view.ui.avtivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.cui.android.jianchengdichan.R;
import com.cui.android.jianchengdichan.http.bean.UplodeImgBean;
import com.cui.android.jianchengdichan.http.bean.UserInfoPicBean;
import com.cui.android.jianchengdichan.presenter.BasePresenter;
import com.cui.android.jianchengdichan.presenter.PersonalDataPresenter;
import com.cui.android.jianchengdichan.utils.Bimp;
import com.cui.android.jianchengdichan.utils.LogUtils;
import com.cui.android.jianchengdichan.utils.Okhttp3Utils;
import com.cui.android.jianchengdichan.utils.SPKey;
import com.cui.android.jianchengdichan.utils.SPUtils;
import com.cui.android.jianchengdichan.utils.ToastUtil;
import com.cui.android.jianchengdichan.view.base.BaseActivity;
import com.cui.android.jianchengdichan.view.ui.customview.CameraPopupWindows;
import com.cui.android.jianchengdichan.view.ui.customview.CircleImageView;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class PersonalDataActivity extends BaseActivity {
    @BindView(R.id.top_back)
    RelativeLayout topBack;
    @BindView(R.id.tv_content_name)
    TextView tvContentName;
    @BindView(R.id.tv_top_right)
    TextView tvTopRight;
    @BindView(R.id.iv_top_right)
    ImageView ivTopRight;
    @BindView(R.id.tv_img)
    TextView tvImg;
    @BindView(R.id.img_userhead)
    CircleImageView imgUserhead;
    @BindView(R.id.rel_user_img)
    RelativeLayout relUserImg;
    @BindView(R.id.rel_user_nickname)
    RelativeLayout relUserNickname;
    @BindView(R.id.rel_user_real_name)
    RelativeLayout relUserRealName;
    @BindView(R.id.tv_user_id)
    TextView tvUserId;
    @BindView(R.id.rel_user_id)
    RelativeLayout relUserId;
    @BindView(R.id.bt_log_out)
    Button btLogOut;
    @BindView(R.id.lin_1)
    LinearLayout lin1;
    String userName;
    String pic;
    String trueName;
    String phone="";
    CameraPopupWindows cameraPopupWindows;
    @BindView(R.id.tv_user_nickname)
    TextView tvUserNickname;
    @BindView(R.id.tv_user_real_name)
    TextView tvUserRealName;
    private Uri themUrl = null;
    PersonalDataPresenter personalDataPresenter;

    public final static int UPLOAD_IMG_OK = 2002;
    public final static int UPLOAD_IMG_FAIL = -2002;
    String key="";
    @SuppressLint("HandlerLeak")
    Handler handler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case UPLOAD_IMG_OK:
                    int uid = (Integer) SPUtils.INSTANCE.getSPValue(SPKey.SP_USER_UID_KEY, SPUtils.DATA_INT);
                    String token = (String) SPUtils.INSTANCE.getSPValue(SPKey.SP_USER_TOKEN_KEY, SPUtils.DATA_STRING);
                    String pic = (String) msg.obj;
                    key="pic";
                    personalDataPresenter.setUserInfo(uid, token, key, pic);
                    break;
                case UPLOAD_IMG_FAIL:
                    ToastUtil.makeToast("更新头像失败，请重试");
                    break;
            }
        }
    };

    @Override
    public BasePresenter initPresenter() {
        personalDataPresenter = new PersonalDataPresenter();
        return personalDataPresenter;
    }

    @Override
    public void initParms(Bundle parms) {

    }

    @Override
    public int bindLayout() {
        return R.layout.activity_personal_data;
    }

    @Override
    public void initView(View view) {
        userName = (String) SPUtils.INSTANCE.getSPValue(SPKey.SP_USER_NAME_KEY, SPUtils.DATA_STRING);
        pic = (String) SPUtils.INSTANCE.getSPValue(SPKey.SP_USER_PIC_URL_KEY, SPUtils.DATA_STRING);
        trueName = (String) SPUtils.INSTANCE.getSPValue(SPKey.SP_USER_TRUE_NAME_KEY, SPUtils.DATA_STRING);
        phone = (String) SPUtils.INSTANCE.getSPValue(SPKey.SP_USER_SIP_NUMBER_KEY, SPUtils.DATA_STRING);
        tvContentName.setText("个人资料");
        tvUserNickname.setText(userName);
        tvUserRealName.setText(trueName);
        tvUserId.setText(phone);
        LogUtils.i("pic="+pic);
        if (!TextUtils.isEmpty(pic)) {
            Okhttp3Utils.getInstance().glide(mContext,pic,imgUserhead);

        }
    }

    @Override
    public void doBusiness(Context mContext) {

    }

    @Override
    public View initBack() {
        return topBack;
    }


    @OnClick({R.id.img_userhead, R.id.bt_log_out,R.id.rel_user_nickname,R.id.rel_user_real_name,R.id.rel_user_reset})
    public void onViewClicked(View view) {
        Bundle bundle = new Bundle();

        switch (view.getId()) {
            case R.id.img_userhead:
                cameraPopupWindows = new CameraPopupWindows(PersonalDataActivity.this, getRootView());

                break;
            case R.id.bt_log_out:

                SPUtils.INSTANCE.setSPValue(SPKey.SP_LOAGIN_KEY,false);
                SPUtils.INSTANCE.setSPValue(SPKey.SP_USER_TOKEN_KEY,"");
                SPUtils.INSTANCE.setSPValue(SPKey.SP_USER_UID_KEY,0);
                SPUtils.INSTANCE.setSPValue(SPKey.SP_USER_COM_ID_KEY,"");
                SPUtils.INSTANCE.setSPValue(SPKey.SP_USER_COMMUNITY_ID_KEY,"");
                SPUtils.INSTANCE.setSPValue(SPKey.SP_USER_UNIT_ID_KEY,"");
                SPUtils.INSTANCE.setSPValue(SPKey.SP_USER_PROPERTY_ID_KEY,"");
                SPUtils.INSTANCE.setSPValue(SPKey.SP_USER_SIP_NUMBER_KEY,"");
                SPUtils.INSTANCE.setSPValue(SPKey.SP_USER_SIP_PWD_KEY,"");
                SPUtils.INSTANCE.setSPValue(SPKey.SP_USER_PIC_URL_KEY, "");
                startActivity(LoginActivity.class);
                finish();
                break;
            case R.id.rel_user_real_name:
                bundle.putString("name","修改真实名字");
                bundle.putString("hint","请输入真实名字");
                bundle.putString("key","name");
                startActivity(SetUserMsgActivity.class,bundle);
                finish();

                break;
            case R.id.rel_user_nickname:
                bundle.putString("name","修改昵称");
                bundle.putString("hint","请输入昵称");
                bundle.putString("key","nickname");
                startActivity(SetUserMsgActivity.class,bundle);
                finish();

                break;
            case R.id.rel_user_reset:
                bundle.putString("type", "2");
                startActivity(ForgetPwdActivity.class, bundle);

                break;
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case CameraPopupWindows.TAKE_PICTURE:
                if (resultCode == -1) {// 拍照
                    themUrl = Bimp.startPhotoZoom(PersonalDataActivity.this, cameraPopupWindows.getPhotoUri());
//                    LogUtils.i("path="+cameraPopupWindows.getPhotoUri());
//                    LogUtils.i("path="+cameraPopupWindows.getPhotoUri().getPath());

//                    if(isSurface){
//                        Bitmap bitmap = Bimp.getLoacalBitmap(cameraPopupWindows.getPhotoUri().getPath());
//                        ivSurfacePlot.setImageBitmap(bitmap);
//                        surfacePlotUrl=cameraPopupWindows.getPhotoUri();
//                        bitmap=null;
//
//                    }else{
//                        detailDrawingData.addFirst(cameraPopupWindows.getPhotoUri().getPath());
//                        initRecyclerView();
//                    }
                }
                break;
            case CameraPopupWindows.RESULT_LOAD_IMAGE:
                if (resultCode == RESULT_OK && null != data) {// 相册返回
                    Uri uri = data.getData();
                    if (uri != null) {
                        themUrl = Bimp.startPhotoZoom(PersonalDataActivity.this, uri);
//                        if(isSurface){
//                            Bitmap bitmap = Bimp.getLoacalBitmap(uri.getPath());
//                            ivSurfacePlot.setImageBitmap(bitmap);
//                            surfacePlotUrl=uri;
//                            bitmap=null;
//                        }else{
//                            detailDrawingData.addFirst(uri.getPath());
//                            initRecyclerView();
//                        }
                    }
                }
                break;
            case CameraPopupWindows.CUT_PHOTO_REQUEST_CODE:
                if (resultCode == RESULT_OK && null != data) {// 裁剪返回
                    Bitmap bitmap = Bimp.getLoacalBitmap(themUrl.getPath());
                    imgUserhead.setImageBitmap(bitmap);
                    pic = themUrl.getPath();
                    Okhttp3Utils.getInstance().uplodeImg("1", pic, callback);
                }
                break;
            case CameraPopupWindows.SELECTIMG_SEARCH:
                if (resultCode == RESULT_OK && null != data) {
                    String text = "#" + data.getStringExtra("topic") + "#";
//                    text = comment_content.getText().toString() + text;
//                    comment_content.setText(text);
//
//                    comment_content.getViewTreeObserver().addOnPreDrawListener(
//                            new OnPreDrawListener() {
//                                public boolean onPreDraw() {
//                                    comment_content.setEnabled(true);
//                                    // 设置光标为末尾
//                                    CharSequence cs = comment_content.getText();
//                                    if (cs instanceof Spannable) {
//                                        Spannable spanText = (Spannable) cs;
//                                        Selection.setSelection(spanText,
//                                                cs.length());
//                                    }
//                                    comment_content.getViewTreeObserver()
//                                            .removeOnPreDrawListener(this);
//                                    return false;
//                                }
//                            });

                }

                break;
        }
    }

    Callback callback = new Callback() {
        @Override
        public void onFailure(Call call, IOException e) {
//                call.cancel();
            Message message = new Message();
            message.what = UPLOAD_IMG_FAIL;
            handler.sendMessage(message);
        }

        @Override
        public void onResponse(Call call, Response response) throws IOException {
            String str = response.body().string();
            LogUtils.i("uplodeImg" + "num=" + str);
            Gson gson = new Gson();
            UplodeImgBean uplodeImgBean = gson.fromJson(str, UplodeImgBean.class);
            String pic = uplodeImgBean.getData().getPics();
            LogUtils.i("uplodeImg" + pic);
            Message message = new Message();
            message.what = UPLOAD_IMG_OK;
            message.obj = pic;
            handler.sendMessage(message);
        }
    };

    public void uplodaImg(List<UplodeImgBean> data) {

    }

    public void setUserInfo(UserInfoPicBean data) {
            String value = data.getPic();
                SPUtils.INSTANCE.setSPValue(SPKey.SP_USER_PIC_URL_KEY, value);
        ToastUtil.makeToast("更新头像成功");
    }


}
