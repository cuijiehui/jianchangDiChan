package com.cui.android.jianchengdichan.view.ui.avtivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.cui.android.jianchengdichan.R;
import com.cui.android.jianchengdichan.presenter.BasePresenter;
import com.cui.android.jianchengdichan.presenter.ReleaseTopicPresenter;
import com.cui.android.jianchengdichan.utils.Bimp;
import com.cui.android.jianchengdichan.utils.SPKey;
import com.cui.android.jianchengdichan.utils.SPUtils;
import com.cui.android.jianchengdichan.utils.ToastUtil;
import com.cui.android.jianchengdichan.view.BaseActivtity;
import com.cui.android.jianchengdichan.view.ui.Fragment.Adapter.interfaces.OnRecyclerViewItemClickListener;
import com.cui.android.jianchengdichan.view.ui.adapter.DatailedDrawingAdapter;
import com.cui.android.jianchengdichan.view.ui.beans.ReleaseImgBean;
import com.cui.android.jianchengdichan.view.ui.customview.CameraPopupWindows;

import java.util.LinkedList;

import butterknife.BindView;
import butterknife.OnClick;

public class ReleaseTopicActivity extends BaseActivtity {
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
    DatailedDrawingAdapter datailedDrawingAdapter;
    CameraPopupWindows cameraPopupWindows;
    public Uri themUrl = null;
    ReleaseTopicPresenter releaseTopicPresenter = new ReleaseTopicPresenter();
    @Override
    public BasePresenter initPresenter() {
        return releaseTopicPresenter;
    }

    @Override
    public void initParms(Bundle parms) {

    }

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
        datailedDrawingAdapter = new DatailedDrawingAdapter(addImgList, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //点击添加图片
                if (addImgList.size() > 9) {
                    ToastUtil.makeToast("故障图为1-9张");
                    return;
                }
                cameraPopupWindows = new CameraPopupWindows(ReleaseTopicActivity.this, getRootView());
            }
        }, listener);
        rvTopicAddImg.setAdapter(datailedDrawingAdapter);
    }
    OnRecyclerViewItemClickListener listener = new OnRecyclerViewItemClickListener() {
        @Override
        public void onItemClick(View view, int position) {
            addImgList.remove(position);
            datailedDrawingAdapter.notifyDataSetChanged();
        }

        @Override
        public void onItemLongClick(View view, int position) {

        }
    };
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
                submit();
                break;
        }
    }
    public void submit(){
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
        int uid = (int) SPUtils.INSTANCE.getSPValue(SPKey.SP_USER_UID_KEY, SPUtils.DATA_INT);
        String token = (String) SPUtils.INSTANCE.getSPValue(SPKey.SP_USER_TOKEN_KEY, SPUtils.DATA_STRING);
        releaseTopicPresenter.topic(uid,token,title,content,"","1");
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
}
