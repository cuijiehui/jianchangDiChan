package com.cui.android.jianchengdichan.view.ui;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.cui.android.jianchengdichan.R;
import com.cui.android.jianchengdichan.http.bean.UplodeImgBean;
import com.cui.android.jianchengdichan.presenter.BasePresenter;
import com.cui.android.jianchengdichan.presenter.ReleaseRentPresenter;
import com.cui.android.jianchengdichan.utils.Bimp;
import com.cui.android.jianchengdichan.utils.ChooseCityUtil;
import com.cui.android.jianchengdichan.utils.CityData;
import com.cui.android.jianchengdichan.utils.FileUtils;
import com.cui.android.jianchengdichan.utils.LogUtils;
import com.cui.android.jianchengdichan.utils.SPKey;
import com.cui.android.jianchengdichan.utils.SPUtils;
import com.cui.android.jianchengdichan.view.BaseActivtity;
import com.cui.android.jianchengdichan.view.interfaces.ChooseCityInterface;
import com.cui.android.jianchengdichan.view.ui.adapter.DatailedDrawingAdapter;
import com.cui.android.jianchengdichan.view.ui.customview.CameraPopupWindows;
import com.cui.android.jianchengdichan.view.ui.customview.WheelView;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ReleaseRentActivity extends BaseActivtity {

    @BindView(R.id.top_back)
    RelativeLayout topBack;
    @BindView(R.id.tv_content_name)
    TextView tvContentName;
    @BindView(R.id.tv_top_right)
    TextView tvTopRight;
    @BindView(R.id.iv_top_right)
    ImageView ivTopRight;
    @BindView(R.id.tv_release_type_1)
    TextView tvReleaseType1;
    @BindView(R.id.tv_release_type_2)
    TextView tvReleaseType2;
    @BindView(R.id.tv_release_type_3)
    TextView tvReleaseType3;
    @BindView(R.id.tv_release_type_4)
    TextView tvReleaseType4;
    @BindView(R.id.tv_release_way_1)
    TextView tvReleaseWay1;
    @BindView(R.id.tv_release_way_2)
    TextView tvReleaseWay2;
    @BindView(R.id.tv_release_way_3)
    TextView tvReleaseWay3;
    @BindView(R.id.tv_release_way_4)
    TextView tvReleaseWay4;
    @BindView(R.id.tv_release_pay_1)
    TextView tvReleasePay1;
    @BindView(R.id.tv_release_pay_2)
    TextView tvReleasePay2;
    @BindView(R.id.tv_release_pay_3)
    TextView tvReleasePay3;
    @BindView(R.id.et_release_home_name)
    EditText etReleaseHomeName;
    @BindView(R.id.et_release_area)
    EditText etReleaseArea;
    @BindView(R.id.tv_release_ori)
    TextView tvReleaseOri;
    @BindView(R.id.et_release_room)
    EditText etReleaseRoom;
    @BindView(R.id.et_release_office)
    EditText etReleaseOffice;
    @BindView(R.id.et_release_wc)
    EditText etReleaseWc;
    @BindView(R.id.et_release_floor)
    EditText etReleaseFloor;
    @BindView(R.id.tv_release_area)
    TextView tvReleaseArea;
    @BindView(R.id.et_release_address)
    EditText etReleaseAddress;
    @BindView(R.id.et_release_month_money)
    EditText etReleaseMonthMoney;
    @BindView(R.id.et_release_real_money)
    EditText etReleaseRealMoney;
    @BindView(R.id.et_release_user_name)
    EditText etReleaseUserName;
    @BindView(R.id.et_release_phone)
    EditText etReleasePhone;
    @BindView(R.id.iv_surface_plot)
    ImageView ivSurfacePlot;
    @BindView(R.id.rv_detailed_drawing)
    RecyclerView rvDetailedDrawing;
    @BindView(R.id.et_release_describe)
    EditText etReleaseDescribe;
    @BindView(R.id.iv_selease_rel)
    ImageView ivSeleaseRel;
    @BindView(R.id.bt_release_save)
    Button btReleaseSave;

    ReleaseRentPresenter releaseRentPresenter;
    private String province = "广东";
    private String city = "广州";
    private String county = "天河";
    CameraPopupWindows cameraPopupWindows ;
    private Uri surfacePlotUrl = null;
    private Uri themUrl = null;
    private LinkedList<String> detailDrawingData = new LinkedList<>();
    private boolean isSurface=true;
    @Override
    public BasePresenter initPresenter() {
        releaseRentPresenter = new ReleaseRentPresenter();
        return releaseRentPresenter;
    }

    @Override
    public void initParms(Bundle parms) {

    }

    @Override
    public int bindLayout() {
        return R.layout.activity_release_rent;
    }

    @Override
    public void initView(View view) {
        tvContentName.setText("发布");
        detailDrawingData.add("-1");
        initRecyclerView();
    }

    @Override
    public void doBusiness(Context mContext) {

    }

    @Override
    public View initBack() {
        return topBack;
    }


    @OnClick({R.id.top_back, R.id.tv_release_type_1, R.id.tv_release_type_2, R.id.tv_release_type_3, R.id.tv_release_type_4, R.id.tv_release_way_1, R.id.tv_release_way_2, R.id.tv_release_way_3, R.id.tv_release_way_4, R.id.tv_release_pay_1, R.id.tv_release_pay_2, R.id.tv_release_pay_3, R.id.tv_release_ori, R.id.tv_release_area, R.id.iv_surface_plot, R.id.iv_selease_rel, R.id.bt_release_save})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.top_back:
                break;
            case R.id.tv_release_type_1:
                setTypeView(1);
                break;
            case R.id.tv_release_type_2:
                setTypeView(2);

                break;
            case R.id.tv_release_type_3:
                setTypeView(3);

                break;
            case R.id.tv_release_type_4:
                setTypeView(4);

                break;
            case R.id.tv_release_way_1:
                setWayView(1);
                break;
            case R.id.tv_release_way_2:
                setWayView(2);

                break;
            case R.id.tv_release_way_3:
                setWayView(3);

                break;
            case R.id.tv_release_way_4:
                setWayView(4);

                break;
            case R.id.tv_release_pay_1:
                setPaview(1);
                break;
            case R.id.tv_release_pay_2:
                setPaview(2);

                break;
            case R.id.tv_release_pay_3:
                setPaview(3);

                break;
            case R.id.tv_release_ori:
                showOriPopwindow();
                break;
            case R.id.tv_release_area:
                showCityWindow();
                break;
            case R.id.iv_surface_plot:
                //封面图
                isSurface=true;
                 cameraPopupWindows   = new CameraPopupWindows(ReleaseRentActivity.this,getRootView());
                break;
            case R.id.iv_selease_rel:
                LogUtils.i("iv_selease_rel"+detailDrawingData.size());
                int uid = (int)SPUtils.INSTANCE.getSPValue(SPKey.SP_USER_UID_KEY,SPUtils.DATA_INT);
                String token = (String) SPUtils.INSTANCE.getSPValue(SPKey.SP_USER_TOKEN_KEY,SPUtils.DATA_STRING);
                LinkedList imgList =new LinkedList();
                imgList.addAll(detailDrawingData);
                imgList.remove(detailDrawingData.size()-1);
                releaseRentPresenter.uplodeImg(uid+"",token,"1",imgList);
                break;
            case R.id.bt_release_save:
                break;
        }
    }
    public  void initRecyclerView(){
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        rvDetailedDrawing.setLayoutManager(linearLayoutManager);
        DatailedDrawingAdapter datailedDrawingAdapter = new DatailedDrawingAdapter(detailDrawingData, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //点击添加图片
                isSurface=false;
                cameraPopupWindows   = new CameraPopupWindows(ReleaseRentActivity.this,getRootView());
            }
        }, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //点击查看图片

            }
        });
        rvDetailedDrawing.setAdapter(datailedDrawingAdapter);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case CameraPopupWindows.TAKE_PICTURE:
                if (resultCode == -1) {// 拍照
                    themUrl=  Bimp.startPhotoZoom(ReleaseRentActivity.this,cameraPopupWindows.getPhotoUri());
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
                if ( resultCode == RESULT_OK && null != data) {// 相册返回
                    Uri uri = data.getData();
                    if (uri != null) {
                        themUrl=   Bimp.startPhotoZoom(ReleaseRentActivity.this,uri);
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
                    if (isSurface) {
                        Bitmap bitmap = Bimp.getLoacalBitmap(themUrl.getPath());
                        ivSurfacePlot.setImageBitmap(bitmap);
                        surfacePlotUrl = themUrl;
                        bitmap = null;
                    } else {
                        detailDrawingData.addFirst(themUrl.getPath());
                        initRecyclerView();
                    }
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

    private void showCityWindow() {
        ChooseCityUtil chooseCityUtil = new ChooseCityUtil();
        String[] data = new String[]{province, city, county};
        chooseCityUtil.createWindow(mContext, data, getRootView(), new ChooseCityInterface() {
            @Override
            public void sure(String[] newCityArray) {
                LogUtils.i("newCityArray =" + newCityArray[0]);
                LogUtils.i("newCityArray =" + newCityArray[1]);
                LogUtils.i("newCityArray =" + newCityArray[2]);
                province = newCityArray[0];
                city = newCityArray[1];
                county = newCityArray[2];
            }
        });
    }

    /**
     * 显示popupWindow
     */
    private void showOriPopwindow() {
        List<String> oriData = new ArrayList();
        oriData.add("朝南");
        oriData.add("朝东");
        oriData.add("朝西");
        oriData.add("朝北");
        oriData.add("东西");
        oriData.add("东北");
        oriData.add("西南");
        oriData.add("西北");
        // 利用layoutInflater获得View
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.popwindow_ori_layout, null);
        // 下面是两种方法得到宽度和高度 getWindow().getDecorView().getWidth()
        final PopupWindow window = new PopupWindow(view, WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.MATCH_PARENT);
        WheelView wv = (WheelView) view
                .findViewById(R.id.wv_ori_data);
        Button saveBt = (Button) view.findViewById(R.id.bt_ori_save);
        wv.setOffset(1);
        wv.setItems(oriData);// 实际内容
        wv.setSeletion(0);// 设置默认被选中的项目
        wv.setOnWheelViewListener(new WheelView.OnWheelViewListener() {
            @Override
            public void onSelected(int selectedIndex, String item) {
                // 选中后的处理事件
                LogUtils.d("[Dialog]selectedIndex: " + selectedIndex
                        + ", item: " + item);
            }
        });
        saveBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                window.dismiss();

            }
        });
        // 设置popWindow弹出窗体可点击，这句话必须添加，并且是true
        window.setFocusable(true);
        // window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        // 实例化一个ColorDrawable颜色为半透明
        ColorDrawable dw = new ColorDrawable(0xb0000000);
        window.setBackgroundDrawable(dw);
        // 设置PopupWindow是否能响应外部点击事件
        window.setOutsideTouchable(true);
        // 设置PopupWindow是否能响应点击事件
        window.setTouchable(true);
        // 设置popWindow的显示和消失动画
        // window.setAnimationStyle(R.style.mypopwindow_anim_style);
        // 在底部显示
        window.showAtLocation(getRootView(), Gravity.CENTER, 0, 0);

    }

    public void setTypeView(int type) {
        int backColorSele = getResources().getColor(R.color.main_top_col);
        int backColor = getResources().getColor(R.color.et_bac_col);
        int textColorSele = getResources().getColor(R.color.white);
        int textColor = getResources().getColor(R.color.main_text_col);
        switch (type) {
            case 1:
                tvReleaseType1.setBackgroundColor(backColorSele);
                tvReleaseType2.setBackgroundColor(backColor);
                tvReleaseType3.setBackgroundColor(backColor);
                tvReleaseType4.setBackgroundColor(backColor);
                tvReleaseType1.setTextColor(textColorSele);
                tvReleaseType2.setTextColor(textColor);
                tvReleaseType3.setTextColor(textColor);
                tvReleaseType4.setTextColor(textColor);
                break;
            case 2:
                tvReleaseType1.setBackgroundColor(backColor);
                tvReleaseType2.setBackgroundColor(backColorSele);
                tvReleaseType3.setBackgroundColor(backColor);
                tvReleaseType4.setBackgroundColor(backColor);
                tvReleaseType1.setTextColor(textColor);
                tvReleaseType2.setTextColor(textColorSele);
                tvReleaseType3.setTextColor(textColor);
                tvReleaseType4.setTextColor(textColor);
                break;
            case 3:
                tvReleaseType1.setBackgroundColor(backColor);
                tvReleaseType2.setBackgroundColor(backColor);
                tvReleaseType3.setBackgroundColor(backColorSele);
                tvReleaseType4.setBackgroundColor(backColor);
                tvReleaseType1.setTextColor(textColor);
                tvReleaseType2.setTextColor(textColor);
                tvReleaseType3.setTextColor(textColorSele);
                tvReleaseType4.setTextColor(textColor);
                break;
            case 4:
                tvReleaseType1.setBackgroundColor(backColor);
                tvReleaseType2.setBackgroundColor(backColor);
                tvReleaseType3.setBackgroundColor(backColor);
                tvReleaseType4.setBackgroundColor(backColorSele);
                tvReleaseType1.setTextColor(textColor);
                tvReleaseType2.setTextColor(textColor);
                tvReleaseType3.setTextColor(textColor);
                tvReleaseType4.setTextColor(textColorSele);
                break;
        }
    }

    public void setWayView(int type) {
        int backColorSele = getResources().getColor(R.color.main_top_col);
        int backColor = getResources().getColor(R.color.et_bac_col);
        int textColorSele = getResources().getColor(R.color.white);
        int textColor = getResources().getColor(R.color.main_text_col);
        switch (type) {
            case 1:
                tvReleaseWay1.setBackgroundColor(backColorSele);
                tvReleaseWay2.setBackgroundColor(backColor);
                tvReleaseWay3.setBackgroundColor(backColor);
                tvReleaseWay4.setBackgroundColor(backColor);
                tvReleaseWay1.setTextColor(textColorSele);
                tvReleaseWay2.setTextColor(textColor);
                tvReleaseWay3.setTextColor(textColor);
                tvReleaseWay4.setTextColor(textColor);
                break;
            case 2:
                tvReleaseWay1.setBackgroundColor(backColor);
                tvReleaseWay2.setBackgroundColor(backColorSele);
                tvReleaseWay3.setBackgroundColor(backColor);
                tvReleaseWay4.setBackgroundColor(backColor);
                tvReleaseWay1.setTextColor(textColor);
                tvReleaseWay2.setTextColor(textColorSele);
                tvReleaseWay3.setTextColor(textColor);
                tvReleaseWay4.setTextColor(textColor);
                break;
            case 3:
                tvReleaseWay1.setBackgroundColor(backColor);
                tvReleaseWay2.setBackgroundColor(backColor);
                tvReleaseWay3.setBackgroundColor(backColorSele);
                tvReleaseWay4.setBackgroundColor(backColor);
                tvReleaseWay1.setTextColor(textColor);
                tvReleaseWay2.setTextColor(textColor);
                tvReleaseWay3.setTextColor(textColorSele);
                tvReleaseWay4.setTextColor(textColor);
                break;
            case 4:
                tvReleaseWay1.setBackgroundColor(backColor);
                tvReleaseWay2.setBackgroundColor(backColor);
                tvReleaseWay3.setBackgroundColor(backColor);
                tvReleaseWay4.setBackgroundColor(backColorSele);
                tvReleaseWay1.setTextColor(textColor);
                tvReleaseWay2.setTextColor(textColor);
                tvReleaseWay3.setTextColor(textColor);
                tvReleaseWay4.setTextColor(textColorSele);
                break;
        }
    }

    public void setPaview(int type) {
        int backColorSele = getResources().getColor(R.color.main_top_col);
        int backColor = getResources().getColor(R.color.et_bac_col);
        int textColorSele = getResources().getColor(R.color.white);
        int textColor = getResources().getColor(R.color.main_text_col);
        switch (type) {
            case 1:
                tvReleasePay1.setBackgroundColor(backColorSele);
                tvReleasePay2.setBackgroundColor(backColor);
                tvReleasePay3.setBackgroundColor(backColor);
                tvReleasePay1.setTextColor(textColorSele);
                tvReleasePay2.setTextColor(textColor);
                tvReleasePay3.setTextColor(textColor);
                break;
            case 2:
                tvReleasePay1.setBackgroundColor(backColor);
                tvReleasePay2.setBackgroundColor(backColorSele);
                tvReleasePay3.setBackgroundColor(backColor);
                tvReleasePay1.setTextColor(textColor);
                tvReleasePay2.setTextColor(textColorSele);
                tvReleasePay3.setTextColor(textColor);
                break;
            case 3:
                tvReleasePay1.setBackgroundColor(backColor);
                tvReleasePay2.setBackgroundColor(backColor);
                tvReleasePay3.setBackgroundColor(backColorSele);
                tvReleasePay1.setTextColor(textColor);
                tvReleasePay2.setTextColor(textColor);
                tvReleasePay3.setTextColor(textColorSele);
                break;

        }
    }

    public void uplodaImg(List<UplodeImgBean> data) {

    }
}
