package com.cui.android.jianchengdichan.view.ui;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
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
import com.cui.android.jianchengdichan.http.bean.MyApplyBean;
import com.cui.android.jianchengdichan.http.bean.UplodeImgBean;
import com.cui.android.jianchengdichan.presenter.BasePresenter;
import com.cui.android.jianchengdichan.presenter.ReleaseRentPresenter;
import com.cui.android.jianchengdichan.utils.Bimp;
import com.cui.android.jianchengdichan.utils.ChooseCityUtil;
import com.cui.android.jianchengdichan.utils.CityData;
import com.cui.android.jianchengdichan.utils.FileUtils;
import com.cui.android.jianchengdichan.utils.LogUtils;
import com.cui.android.jianchengdichan.utils.Okhttp3Utils;
import com.cui.android.jianchengdichan.utils.SPKey;
import com.cui.android.jianchengdichan.utils.SPUtils;
import com.cui.android.jianchengdichan.utils.ToastUtil;
import com.cui.android.jianchengdichan.view.BaseActivtity;
import com.cui.android.jianchengdichan.view.interfaces.ChooseCityInterface;
import com.cui.android.jianchengdichan.view.ui.adapter.DatailedDrawingAdapter;
import com.cui.android.jianchengdichan.view.ui.customview.CameraPopupWindows;
import com.cui.android.jianchengdichan.view.ui.customview.WheelView;
import com.google.gson.Gson;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

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
    CameraPopupWindows cameraPopupWindows;
    private Uri surfacePlotUrl = null;
    private Uri themUrl = null;
    private LinkedList<String> detailDrawingData = new LinkedList<>();
    private LinkedList<String> uplodeUrl = new LinkedList<>();
    private boolean isSurface = true;
    private String typeView = "普通住宅";
    private String wayView = "整租";
    private String paview = "押一付一";
    private String selectedori = "";//朝向
    public final static int UPLODE_IMG = 300;
    public final static int UPLODE_IMG_FAIL = -300;
    MyApplyBean myApplyBean;
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
                    releaseData();

                    break;
                case UPLODE_IMG_FAIL:

                    break;
            }
        }
    };

    @Override
    public BasePresenter initPresenter() {
        releaseRentPresenter = new ReleaseRentPresenter();
        return releaseRentPresenter;
    }

    @Override
    public void initParms(Bundle parms) {
        if (parms != null) {
            String json = parms.getString("json");
            Gson gson = new Gson();
            myApplyBean = gson.fromJson(json, MyApplyBean.class);
            LogUtils.i(json);
        }

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
        if (myApplyBean != null) {
            initApplyBean();

        }
    }

    private void initApplyBean() {
        String banType = myApplyBean.getBan_type();//类型
        switch (banType) {
            case "普通住宅":
                setTypeView(1);
                break;
            case "公寓":
                setTypeView(2);
                break;
            case "别墅":
                setTypeView(3);
                break;
            case "商铺":
                setTypeView(4);
                break;
        }

        String rentType = myApplyBean.getRent_type();//租贷方式
        switch (rentType) {
            case "整租":
                setWayView(1);
                break;
            case "合租":
                setWayView(2);
                break;
            case "短租":
                setWayView(3);
                break;
            case "公寓":
                setWayView(4);
                break;
        }
        String charge_pay = myApplyBean.getCharge_pay();//押付方式
        switch (charge_pay) {
            case "押一付一":
                setPaview(1);
                break;
            case "押二付一":
                setPaview(2);
                break;
            case "押一付三":
                setPaview(3);
                break;
        }

        String title = myApplyBean.getTitle();//小区名称
        etReleaseHomeName.setText(title);
        String area = myApplyBean.getAcreage();//面积
        etReleaseArea.setText(area);
        String local_floor = myApplyBean.getLocal_floor();
        String total_floor = myApplyBean.getTotal_floor();
        etReleaseFloor.setText(local_floor+"/"+total_floor);
        String address = myApplyBean.getAddress();
        etReleaseAddress.setText(address);
        String rental = myApplyBean.getRental();//租金
        etReleaseMonthMoney.setText(rental);
        String fee = myApplyBean.getFee();//物业费
        etReleaseRealMoney.setText(fee);
        String mobile = myApplyBean.getMobile();//电话
        etReleasePhone.setText(mobile);
        String contact = myApplyBean.getContact();//l联系人
        etReleaseUserName.setText(contact);
        selectedori=myApplyBean.getOrientations();
        tvReleaseOri.setText(selectedori);

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
                isSurface = true;
                cameraPopupWindows = new CameraPopupWindows(ReleaseRentActivity.this, getRootView());
                break;
            case R.id.iv_selease_rel:
                LogUtils.i("iv_selease_rel" + detailDrawingData.size());

                uplodeImg();
                break;
            case R.id.bt_release_save:
                break;
        }
    }

    private void releaseData() {
        String homeHame = etReleaseHomeName.getText().toString();//小区的名字
        if (TextUtils.isEmpty(homeHame)) {
            ToastUtil.makeToast("小区名称不能为空");
            return;
        }
        String area = etReleaseArea.getText().toString();//面积
        if (TextUtils.isEmpty(area)) {
            ToastUtil.makeToast("面积不能为空");
            return;
        }
        if (TextUtils.isEmpty(selectedori)) {//朝向
            ToastUtil.makeToast("朝向不能为空");
            return;
        }
        String room = etReleaseRoom.getText().toString();   //室
        String office = etReleaseOffice.getText().toString();//厅
        String wc = etReleaseWc.getText().toString();//卫生间
        if (TextUtils.isEmpty(room) || TextUtils.isEmpty(office) || TextUtils.isEmpty(wc)) {
            ToastUtil.makeToast("户型不能为空");
            return;
        }
        String floor = etReleaseFloor.getText().toString();//楼层
        String local_floor = "";
        String total_floor = "";
        if (TextUtils.isEmpty(floor)) {
            ToastUtil.makeToast("楼层不能为空");
            return;
        } else {
            String[] flooes = floor.split("/");
            if (flooes.length != 2) {
                ToastUtil.makeToast("楼层格式有误");
                return;
            }
            local_floor = flooes[0];
            total_floor = flooes[1];
        }
        String sddress = etReleaseAddress.getText().toString();//地址
        if (TextUtils.isEmpty(sddress)) {
            ToastUtil.makeToast("地址不能为空");
            return;
        }
        String monthMoney = etReleaseMonthMoney.getText().toString();//月租
        if (TextUtils.isEmpty(monthMoney)) {
            ToastUtil.makeToast("月租不能为空");
            return;
        }
        String realMoney = etReleaseRealMoney.getText().toString();//物业费
        if (TextUtils.isEmpty(realMoney)) {
            ToastUtil.makeToast("物业费不能为空");
            return;
        }
        String userName = etReleaseUserName.getText().toString();//姓名
        if (TextUtils.isEmpty(userName)) {
            ToastUtil.makeToast("姓名不能为空");
            return;
        }
        String phone = etReleasePhone.getText().toString();//手机号码
        if (TextUtils.isEmpty(phone)) {
            ToastUtil.makeToast("手机号码不能为空");
            return;
        }
        String describe = etReleaseDescribe.getText().toString();//房屋描述
        if (TextUtils.isEmpty(describe)) {
            ToastUtil.makeToast("房屋描述不能为空");
            return;
        }
        /**
         *   private String typeView = "普通住宅";
         private String wayView = "整租";
         private String paview = "押一付一";
         */
        int uid = (int) SPUtils.INSTANCE.getSPValue(SPKey.SP_USER_UID_KEY, SPUtils.DATA_INT);
        String token = (String) SPUtils.INSTANCE.getSPValue(SPKey.SP_USER_TOKEN_KEY, SPUtils.DATA_STRING);
        String house_type = "0";
        String pic = "";
        if (uplodeUrl.size() > 0) {
            pic = uplodeUrl.getFirst();
        }
        uplodeUrl.removeFirst();
        StringBuffer pics = new StringBuffer();

        for (String url : uplodeUrl) {
            pics.append(url + ",");
        }
        releaseRentPresenter.publishRentInfo(
                uid
                , token
                , typeView
                , homeHame
                , house_type
                , room + "室" + office + "厅" + wc + "卫"
                , monthMoney
                , selectedori
                , area
                , pic
                , pics.toString()
                , local_floor
                , total_floor
                , describe
                , phone
                , userName
                , "1"
                , realMoney
                , sddress
                , wayView
                , paview
        );
    }

    public void publishRentInfo() {
        ToastUtil.makeToast("发布成功，等待审批");
    }

    public void uplodeImg() {


        LinkedList imgList = new LinkedList();
        imgList.addAll(detailDrawingData);
        imgList.remove(detailDrawingData.size() - 1);
        if (surfacePlotUrl != null) {
            String path = surfacePlotUrl.getPath();
            imgList.addFirst(path);
        }
        Okhttp3Utils.getInstance().uplodeImgList(imgList.size(), imgList, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
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

    public void initRecyclerView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        rvDetailedDrawing.setLayoutManager(linearLayoutManager);
        DatailedDrawingAdapter datailedDrawingAdapter = new DatailedDrawingAdapter(detailDrawingData, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //点击添加图片
                isSurface = false;
                cameraPopupWindows = new CameraPopupWindows(ReleaseRentActivity.this, getRootView());
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
                    themUrl = Bimp.startPhotoZoom(ReleaseRentActivity.this, cameraPopupWindows.getPhotoUri());
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
                        themUrl = Bimp.startPhotoZoom(ReleaseRentActivity.this, uri);
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
                tvReleaseArea.setText(province + "/" + city + "/" + county);
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
        final WheelView wv = (WheelView) view
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
                selectedori = wv.getSeletedItem();
                tvReleaseOri.setText(selectedori);
                tvReleaseOri.setTextColor(getResources().getColor(R.color.black));
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
                if (typeView.equals("普通住宅")) {
                    return;
                }
                typeView = "普通住宅";
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
                if (typeView.equals("公寓")) {
                    return;
                }
                typeView = "公寓";
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
                if (typeView.equals("别墅")) {
                    return;
                }
                typeView = "别墅";
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
                if (typeView.equals("商铺")) {
                    return;
                }
                typeView = "商铺";
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
                if (wayView.equals("整租")) {
                    return;
                }
                wayView = "整租";
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
                if (wayView.equals("合租")) {
                    return;
                }
                wayView = "合租";
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
                if (wayView.equals("短租")) {
                    return;
                }
                wayView = "短租";
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
                if (wayView.equals("公寓")) {
                    return;
                }
                wayView = "公寓";
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
                if (paview.equals("押一付一")) {
                    return;
                }
                paview = "押一付一";
                tvReleasePay1.setBackgroundColor(backColorSele);
                tvReleasePay2.setBackgroundColor(backColor);
                tvReleasePay3.setBackgroundColor(backColor);
                tvReleasePay1.setTextColor(textColorSele);
                tvReleasePay2.setTextColor(textColor);
                tvReleasePay3.setTextColor(textColor);
                break;
            case 2:
                if (paview.equals("押二付一")) {
                    return;
                }
                paview = "押二付一";
                tvReleasePay1.setBackgroundColor(backColor);
                tvReleasePay2.setBackgroundColor(backColorSele);
                tvReleasePay3.setBackgroundColor(backColor);
                tvReleasePay1.setTextColor(textColor);
                tvReleasePay2.setTextColor(textColorSele);
                tvReleasePay3.setTextColor(textColor);
                break;
            case 3:
                if (paview.equals("押一付三")) {
                    return;
                }
                paview = "押一付三";
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
