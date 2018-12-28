package com.cui.android.jianchengdichan.view.ui.avtivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.cui.android.jianchengdichan.R;
import com.cui.android.jianchengdichan.http.bean.UplodeImgBean;
import com.cui.android.jianchengdichan.presenter.BasePresenter;
import com.cui.android.jianchengdichan.presenter.ReleaseTopicPresenter;
import com.cui.android.jianchengdichan.utils.Bimp;
import com.cui.android.jianchengdichan.utils.LogUtils;
import com.cui.android.jianchengdichan.utils.Okhttp3Utils;
import com.cui.android.jianchengdichan.utils.SPKey;
import com.cui.android.jianchengdichan.utils.SPUtils;
import com.cui.android.jianchengdichan.utils.ToastUtil;
import com.cui.android.jianchengdichan.view.base.BaseActivity;
import com.cui.android.jianchengdichan.view.ui.adapter.DatailedDrawingAdapter;
import com.cui.android.jianchengdichan.view.ui.beans.ReleaseImgBean;
import com.cui.android.jianchengdichan.view.ui.customview.CameraPopupWindows;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.LinkedList;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class ReleaseTopicActivity extends BaseActivity {


    @BindView(R.id.top_back)
    RelativeLayout topBack;
    @BindView(R.id.tv_content_name)
    TextView tvContentName;
    @BindView(R.id.tv_top_right)
    TextView tvTopRight;
    @BindView(R.id.iv_top_right)
    ImageView ivTopRight;
    @BindView(R.id.tv_topic_type_car)
    TextView tvTopicTypeCar;
    @BindView(R.id.tv_topic_type_topic)
    TextView tvTopicTypeTopic;
    @BindView(R.id.tv_topic_type_shop)
    TextView tvTopicTypeShop;
    @BindView(R.id.et_topic_title)
    EditText etTopicTitle;
    @BindView(R.id.et_topic_txt)
    EditText etTopicTxt;
    @BindView(R.id.rv_topic_add_img)
    RecyclerView rvTopicAddImg;
    @BindView(R.id.tv_topic_submit)
    TextView tvTopicSubmit;
    private String typeView = "我要拼车";
    private String typeViewId = "1";
    public LinkedList<ReleaseImgBean> addImgList = new LinkedList<>();
    private LinkedList<String> uplodeUrl = new LinkedList<>();

    DatailedDrawingAdapter datailedDrawingAdapter;
    CameraPopupWindows cameraPopupWindows;
    public Uri themUrl = null;
    ReleaseTopicPresenter releaseTopicPresenter = new ReleaseTopicPresenter();
    public final static int UPLODE_IMG = 300;
    public final static int UPLODE_IMG_FAIL = -300;
    @Override
    public BasePresenter initPresenter() {
        return releaseTopicPresenter;
    }

    @Override
    public void initParam(Bundle param) {

    }
    @SuppressLint("HandlerLeak")
    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case UPLODE_IMG:
                    String uplode = (String) msg.obj;
                    String[] uplodes = uplode.split(",");
                    for (String url : uplodes) {
                        LogUtils.i("url=" + url);
                        uplodeUrl.addFirst(url);
                    }
                    submit();

                    break;
                case UPLODE_IMG_FAIL:
                    ToastUtil.makeToast("上传图片失败");
                    break;
            }
        }
    };
    @Override
    public int bindLayout() {
        return R.layout.activity_release_topic;
    }

    @Override
    public void initView(View view) {
        tvContentName.setText("发布");
        GridLayoutManager gridLayoutManager = new GridLayoutManager(mContext,3);
        rvTopicAddImg.setLayoutManager(gridLayoutManager);
        if (addImgList.size() == 0) {
            addImgList.add(new ReleaseImgBean("", "", -1));
        }
        datailedDrawingAdapter = new DatailedDrawingAdapter(addImgList);
        rvTopicAddImg.setAdapter(datailedDrawingAdapter);
        datailedDrawingAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                addImgList.remove(position);
                datailedDrawingAdapter.notifyDataSetChanged();
            }
        });
        datailedDrawingAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                //点击添加图片
                if (addImgList.size() > 9) {
                    ToastUtil.makeToast("图片为1-9张");
                    return;
                }
                ReleaseImgBean releaseImgBean = addImgList.get(position);
                if(releaseImgBean.getType()==-1){
                    cameraPopupWindows = new CameraPopupWindows(ReleaseTopicActivity.this, getRootView());
                }else{
                }
            }
        });
    }
    @Override
    public void doBusiness(Context mContext) {

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case CameraPopupWindows.TAKE_PICTURE:
                if (resultCode == -1) {// 拍照
                    themUrl = Bimp.startPhotoZoom(ReleaseTopicActivity.this, cameraPopupWindows.getPhotoUri());
                }
                break;
            case CameraPopupWindows.RESULT_LOAD_IMAGE:
                if (resultCode == Activity.RESULT_OK && null != data) {// 相册返回
                    Uri uri = data.getData();
                    if (uri != null) {
                        themUrl = Bimp.startPhotoZoom(ReleaseTopicActivity.this, uri);
                    }
                }
                break;
            case CameraPopupWindows.CUT_PHOTO_REQUEST_CODE:
                if (resultCode == Activity.RESULT_OK && null != data) {// 裁剪返回
                    addImgList.addFirst(new ReleaseImgBean("",themUrl.getPath(),1));
                    datailedDrawingAdapter.notifyDataSetChanged();
                }
                break;
            case CameraPopupWindows.SELECTIMG_SEARCH:
                if (resultCode == Activity.RESULT_OK && null != data) {
                    String text = "#" + data.getStringExtra("topic") + "#";

                }

                break;
        }
    }
    @Override
    public View initBack() {
        return topBack;
    }

    @OnClick({R.id.tv_topic_type_car, R.id.tv_topic_type_topic, R.id.tv_topic_type_shop, R.id.tv_topic_submit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_topic_type_car:
                setTypeView(1);
                break;
            case R.id.tv_topic_type_topic:
                setTypeView(2);
                break;
            case R.id.tv_topic_type_shop:
                setTypeView(3);
                break;
            case R.id.tv_topic_submit:
                check();
                break;
        }
    }
    public void check(){
        String title = etTopicTitle.getText().toString().trim();
        if(TextUtils.isEmpty(title)){
            ToastUtil.makeToast("标题不能为空！");
            return;
        }
        String content = etTopicTxt.getText().toString().trim();
        if(TextUtils.isEmpty(content)){
            ToastUtil.makeToast("内容不能为空！");
            return;
        }
        if(addImgList.size()<1){
            ToastUtil.makeToast("图片不能为空！");
            return;
        }
        uplodeImg();
    }
    public void submit(){
        String title = etTopicTitle.getText().toString().trim();
        String content = etTopicTxt.getText().toString().trim();
        int uid = (int) SPUtils.INSTANCE.getSPValue(SPKey.SP_USER_UID_KEY, SPUtils.DATA_INT);
        String token = (String) SPUtils.INSTANCE.getSPValue(SPKey.SP_USER_TOKEN_KEY, SPUtils.DATA_STRING);
        StringBuffer pics = new StringBuffer();
        for(String url :uplodeUrl){
            pics.append(url);
            if (uplodeUrl.size() > 1) {
                pics.append(",");
            }
        }
        releaseTopicPresenter.topic(uid,token,title,content,pics.toString(),typeViewId);
    }
    public void uplodeImg() {

        LinkedList imgList = new LinkedList();
        if (addImgList.size() == 1) {
            submit();
        } else {
            for (ReleaseImgBean bean : addImgList) {
                if (bean.getType() == 1) {
                    imgList.add(bean.getPath());
                }
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

    }
    public void setTypeView(int type) {
        Drawable backColorSele = getResources().getDrawable(R.drawable.shape_circular_bead_blue_col);
        Drawable backColor = getResources().getDrawable(R.drawable.shape_circular_bead_gray_col);
        int textColorSele = getResources().getColor(R.color.white);
        int textColor = getResources().getColor(R.color.main_text_col);
        switch (type) {
            case 1:
                if (typeView.equals("我要拼车")) {
                    return;
                }
                typeViewId="1";
                typeView = "我要拼车";
                tvTopicTypeCar.setBackground(backColorSele);
                tvTopicTypeTopic.setBackground(backColor);
                tvTopicTypeShop.setBackground(backColor);
                tvTopicTypeCar.setTextColor(textColorSele);
                tvTopicTypeTopic.setTextColor(textColor);
                tvTopicTypeShop.setTextColor(textColor);
                break;
            case 2:
                if (typeView.equals("话题")) {
                    return;
                }
                typeViewId="2";

                typeView = "话题";
                tvTopicTypeCar.setBackground(backColor);
                tvTopicTypeTopic.setBackground(backColorSele);
                tvTopicTypeShop.setBackground(backColor);
                tvTopicTypeCar.setTextColor(textColor);
                tvTopicTypeTopic.setTextColor(textColorSele);
                tvTopicTypeShop.setTextColor(textColor);
                break;
            case 3:
                if (typeView.equals("跳蚤市场")) {
                    return;
                }
                typeViewId="3";
                typeView = "跳蚤市场";
                tvTopicTypeCar.setBackground(backColor);
                tvTopicTypeTopic.setBackground(backColor);
                tvTopicTypeShop.setBackground(backColorSele);
                tvTopicTypeCar.setTextColor(textColor);
                tvTopicTypeTopic.setTextColor(textColor);
                tvTopicTypeShop.setTextColor(textColorSele);
                break;

        }
    }

    public void topic() {
        ToastUtil.makeToast("发布成功");
        finish();
    }
}
