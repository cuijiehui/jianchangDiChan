package com.cui.android.jianchengdichan.view.ui.avtivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.TimePickerView;
import com.cui.android.jianchengdichan.R;
import com.cui.android.jianchengdichan.http.bean.UplodeImgBean;
import com.cui.android.jianchengdichan.presenter.BasePresenter;
import com.cui.android.jianchengdichan.presenter.ReleaseActPresenter;
import com.cui.android.jianchengdichan.utils.Bimp;
import com.cui.android.jianchengdichan.utils.LogUtils;
import com.cui.android.jianchengdichan.utils.Okhttp3Utils;
import com.cui.android.jianchengdichan.utils.SPKey;
import com.cui.android.jianchengdichan.utils.SPUtils;
import com.cui.android.jianchengdichan.utils.TimeUtil;
import com.cui.android.jianchengdichan.utils.ToastUtil;
import com.cui.android.jianchengdichan.view.base.BaseActivity;
import com.cui.android.jianchengdichan.view.ui.customview.CameraPopupWindows;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class ReleaseActActivity extends BaseActivity {
    @BindView(R.id.top_back)
    RelativeLayout topBack;
    @BindView(R.id.tv_content_name)
    TextView tvContentName;
    @BindView(R.id.tv_top_right)
    TextView tvTopRight;
    @BindView(R.id.iv_top_right)
    ImageView ivTopRight;
    @BindView(R.id.et_activity_title)
    EditText etActivityTitle;
    @BindView(R.id.tv_activity_time)
    TextView tvActivityTime;
    @BindView(R.id.tv_act_start_time)
    TextView tvActStartTime;
    @BindView(R.id.tv_act_end_time)
    TextView tvActEndTime;
    @BindView(R.id.tv_act_addres)
    TextView tvActAddres;
    @BindView(R.id.et_act_addres)
    EditText etActAddres;
    @BindView(R.id.tv_act_phone)
    TextView tvActPhone;
    @BindView(R.id.et_act_phone)
    EditText etActPhone;
    @BindView(R.id.et_act_txt)
    EditText etActTxt;
    @BindView(R.id.iv_act_plot)
    ImageView ivActPlot;
    @BindView(R.id.tv_act_submit)
    TextView tvActSubmit;
    TimePickerView timePickerView;
    CameraPopupWindows cameraPopupWindows;
    private Uri themUrl = null;
    private Uri surfacePlotUrl = null;
    String startTime = "";
    String endTime = "";
    String updateUrl="";
    boolean isSTime = true;
    public final static int UPLODE_IMG = 300;
    public final static int UPLODE_IMG_FAIL = -300;
    ReleaseActPresenter releaseActPresenter = new ReleaseActPresenter();
    @Override
    public BasePresenter initPresenter() {
        return releaseActPresenter;
    }
    @SuppressLint("HandlerLeak")
    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case UPLODE_IMG:
                    updateUrl = (String) msg.obj;
                    submit();
                    break;
                case UPLODE_IMG_FAIL:
                    ToastUtil.makeToast("上传图片失败");
                    break;
            }
        }
    };
    @Override
    public void initParms(Bundle parms) {

    }

    @Override
    public int bindLayout() {
        return R.layout.activity_release_act;
    }

    @Override
    public void initView(View view) {
        tvContentName.setText("活动发布");
        initTimePicker();
    }

    @Override
    public void doBusiness(Context mContext) {

    }

    @Override
    public View initBack() {
        return topBack;
    }
    public void check(){
        String title = etActivityTitle.getText().toString().trim();
        if(TextUtils.isEmpty(title)){
            ToastUtil.makeToast("标题不能为空！");
            return;
        }
        if(TextUtils.isEmpty(startTime)||TextUtils.isEmpty(endTime)){
            ToastUtil.makeToast("时间不能为空！");
            return;
        }
        String address = etActAddres.getText().toString().trim();
        if(TextUtils.isEmpty(address)){
            ToastUtil.makeToast("地址不能为空！");
            return;
        }
        String phone = etActPhone.getText().toString().trim();
        if(TextUtils.isEmpty(phone)){
            ToastUtil.makeToast("电话不能为空！");
            return;
        }
        String content = etActTxt.getText().toString().trim();
        if(TextUtils.isEmpty(content)){
            ToastUtil.makeToast("内容不能为空！");
            return;
        }
        if(surfacePlotUrl==null){
            ToastUtil.makeToast("封面不能为空！");
            return;
        }
        uplodeImg();
    }
    public void submit(){
        int uid = (int) SPUtils.INSTANCE.getSPValue(SPKey.SP_USER_UID_KEY, SPUtils.DATA_INT);
        String token = (String) SPUtils.INSTANCE.getSPValue(SPKey.SP_USER_TOKEN_KEY, SPUtils.DATA_STRING);
        String phone = etActPhone.getText().toString().trim();
        String content = etActTxt.getText().toString().trim();
        String address = etActAddres.getText().toString().trim();
        String title = etActivityTitle.getText().toString().trim();
        releaseActPresenter.releas(uid,token,phone
                ,content,updateUrl,address,startTime,endTime,title);
    }
    public void uplodeImg() {
        LinkedList imgList = new LinkedList();
        if (surfacePlotUrl != null) {
            String path = surfacePlotUrl.getPath();
            imgList.addFirst(path);
        }
        Okhttp3Utils.getInstance().uplodeImgList(imgList.size(), imgList, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
//                call.request().body()
                LogUtils.i(call.request().body().toString());
                Message message = new Message();
                message.what = UPLODE_IMG_FAIL;
                mHandler.sendMessage(message);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String str = response.body().string();
                Gson gson = new Gson();
                UplodeImgBean uplodeImgBean = gson.fromJson(str, UplodeImgBean.class);
                String pic = uplodeImgBean.getData().getPics();
                LogUtils.i(pic);
                Message message = new Message();
                message.what = UPLODE_IMG;
                message.obj = pic;
                mHandler.sendMessage(message);
            }
        });
    }



    public void initTimePicker() {
        Calendar selectedDate = Calendar.getInstance();
        Calendar startDate = Calendar.getInstance();
        //startDate.set(2013,1,1);
        final Calendar endDate = Calendar.getInstance();
        //endDate.set(2020,1,1);

        //正确设置方式 原因：注意事项有说明
        startDate.set(2013, 0, 1);
        endDate.set(2020, 11, 31);

        timePickerView = new TimePickerBuilder(this, new OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {//选中事件回调
//                tvTime.setText(getTime(date));
                if (isSTime) {
                    startTime=  TimeUtil.timeString(date.getTime(),"yyyy-MM-dd HH:mm");
                    LogUtils.i(startTime);
                    tvActStartTime.setText(startTime);
                }else{
                    endTime=  TimeUtil.timeString(date.getTime(),"yyyy-MM-dd HH:mm");
                    LogUtils.i(endTime);
                    tvActEndTime.setText(endTime);
                }
            }
        })
                .setType(new boolean[]{true, true, true, true, true, false})// 默认全部显示
                .setCancelText("取消")//取消按钮文字
                .setSubmitText("确认")//确认按钮文字
//                .setContentSize(18)//滚轮文字大小
                .setTitleSize(20)//标题文字大小
                .setTitleText("")//标题文字
                .setOutSideCancelable(false)//点击屏幕，点在控件外部范围时，是否取消显示
                .isCyclic(true)//是否循环滚动
                .setTitleColor(Color.WHITE)//标题文字颜色
                .setSubmitColor(Color.BLACK)//确定按钮文字颜色
                .setCancelColor(Color.BLACK)//取消按钮文字颜色
                .setTitleBgColor(Color.WHITE)//标题背景颜色 Night mode
                .setBgColor(Color.WHITE)//滚轮背景颜色 Night mode
                .setDate(selectedDate)// 如果不设置的话，默认是系统时间*/
                .setRangDate(startDate, endDate)//起始终止年月日设定
                .setLabel("年", "月", "日", "时", "分", "秒")//默认设置为年月日时分秒
                .isCenterLabel(false) //是否只显示中间选中项的label文字，false则每项item全部都带有label。
                .isDialog(true)//是否显示为对话框样式
                .build();
    }

    @OnClick({R.id.tv_act_start_time, R.id.tv_act_end_time, R.id.iv_act_plot, R.id.tv_act_submit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_act_start_time:
                isSTime=true;
                if(timePickerView!=null){
                    timePickerView.show();
                }
                break;
            case R.id.tv_act_end_time:
                isSTime=false;
                if(timePickerView!=null){
                    timePickerView.show();
                }
                break;
            case R.id.iv_act_plot:
                cameraPopupWindows = new CameraPopupWindows(ReleaseActActivity.this, getRootView());
                break;
            case R.id.tv_act_submit:
                check();
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case CameraPopupWindows.TAKE_PICTURE:
                if (resultCode == -1) {// 拍照
                    themUrl = Bimp.startPhotoZoom(ReleaseActActivity.this, cameraPopupWindows.getPhotoUri());
                }
                break;
            case CameraPopupWindows.RESULT_LOAD_IMAGE:
                if (resultCode == RESULT_OK && null != data) {// 相册返回
                    Uri uri = data.getData();
                    if (uri != null) {
                        themUrl = Bimp.startPhotoZoom(ReleaseActActivity.this, uri);

                    }
                }
                break;
            case CameraPopupWindows.CUT_PHOTO_REQUEST_CODE:
                if (resultCode == RESULT_OK && null != data) {// 裁剪返回
                    Bitmap bitmap = Bimp.getLoacalBitmap(themUrl.getPath());
                    ivActPlot.setImageBitmap(bitmap);
                    surfacePlotUrl = themUrl;
                    bitmap = null;
                }
                break;
            case CameraPopupWindows.SELECTIMG_SEARCH:
                if (resultCode == RESULT_OK && null != data) {
                    String text = "#" + data.getStringExtra("topic") + "#";
                }

                break;
        }
    }

    public void releas() {
        ToastUtil.makeToast("提交发布活动成功，等待后台审核!");
        finish();
    }
}
