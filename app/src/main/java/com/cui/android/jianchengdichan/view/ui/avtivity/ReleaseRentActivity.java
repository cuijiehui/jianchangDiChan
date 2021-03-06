package com.cui.android.jianchengdichan.view.ui.avtivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
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

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.cui.android.jianchengdichan.R;
import com.cui.android.jianchengdichan.http.bean.MyApplyBean;
import com.cui.android.jianchengdichan.http.bean.UplodeImgBean;
import com.cui.android.jianchengdichan.presenter.BasePresenter;
import com.cui.android.jianchengdichan.presenter.ReleaseRentPresenter;
import com.cui.android.jianchengdichan.utils.Bimp;
import com.cui.android.jianchengdichan.utils.ChooseCityUtil;
import com.cui.android.jianchengdichan.utils.CityData;
import com.cui.android.jianchengdichan.utils.LogUtils;
import com.cui.android.jianchengdichan.utils.Okhttp3Utils;
import com.cui.android.jianchengdichan.utils.SPKey;
import com.cui.android.jianchengdichan.utils.SPUtils;
import com.cui.android.jianchengdichan.utils.ToastUtil;
import com.cui.android.jianchengdichan.view.base.BaseActivity;
import com.cui.android.jianchengdichan.view.interfaces.ChooseCityInterface;
import com.cui.android.jianchengdichan.view.ui.adapter.DatailedDrawingAdapter;
import com.cui.android.jianchengdichan.view.ui.beans.ReleaseImgBean;
import com.cui.android.jianchengdichan.view.ui.customview.CameraPopupWindows;
import com.cui.android.jianchengdichan.view.ui.customview.WheelView;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class ReleaseRentActivity extends BaseActivity {

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
    @BindView(R.id.et_release_total_floor)
    EditText et_release_total_floor;
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
    Button ivSeleaseRel;
    @BindView(R.id.bt_release_save)
    Button btReleaseSave;
    DatailedDrawingAdapter datailedDrawingAdapter;
    ReleaseRentPresenter releaseRentPresenter;
    private String province = "广东";
    private String city = "广州";
    private String county = "天河";
    CameraPopupWindows cameraPopupWindows;
    private Uri surfacePlotUrl = null;
    private Uri themUrl = null;
    private LinkedList<ReleaseImgBean> detailDrawingData = new LinkedList<>();
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
                        if(myApplyBean!=null&&surfacePlotUrl!=null){
                            uplodeUrl.removeLast();
                            uplodeUrl.addLast(url);
                        }else{
                            uplodeUrl.addFirst(url);

                        }
                    }
                    releaseData();

                    break;
                case UPLODE_IMG_FAIL:
                    ToastUtil.makeToast("上传图片失败");
                    hideLoading();
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
    public void initParam(Bundle param) {
        if (param != null) {
            String json = param.getString("json");
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
        if (myApplyBean != null) {
            initApplyBean();
        }
        tvContentName.setText("发布");
        detailDrawingData.add(new ReleaseImgBean("","",-1));
        initRecyclerView();

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
        //户型
        int shiIndex  = myApplyBean.getHouse_type_info().indexOf("室");
        int tingIndex  = myApplyBean.getHouse_type_info().indexOf("厅");
        int weiIndex  = myApplyBean.getHouse_type_info().indexOf("卫");
        if(shiIndex>=0){
            String shi = myApplyBean.getHouse_type_info().substring(0,shiIndex);
            String ting = myApplyBean.getHouse_type_info().substring(shiIndex+1,tingIndex);
            String wei = myApplyBean.getHouse_type_info().substring(tingIndex+1,weiIndex);
            etReleaseRoom.setText(shi);
            etReleaseOffice.setText(ting);
            etReleaseWc.setText(wei);
        }
        String title = myApplyBean.getTitle();//小区名称
        etReleaseHomeName.setText(title);
        String area = myApplyBean.getAcreage();//面积
        etReleaseArea.setText(area);
        String local_floor = myApplyBean.getLocal_floor();
        String total_floor = myApplyBean.getTotal_floor();
        etReleaseFloor.setText(local_floor);
        et_release_total_floor.setText(total_floor);
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
        String detail = myApplyBean.getDetail();
        etReleaseDescribe.setText(detail);
        String pic = myApplyBean.getPic();
        if(pic!=null){
            Okhttp3Utils.getInstance().glide(mContext,pic,ivSurfacePlot);
        }
        if(myApplyBean.getPics()!=null){
            String[] pics = myApplyBean.getPics().split(",");
            for (String picss:pics){
                detailDrawingData.addFirst(new ReleaseImgBean(picss,"",2));
            }
        }
        String[] savePics =myApplyBean.getSavepics().split(",");
        for(String savePicss:savePics){
            LogUtils.i(savePicss);
            uplodeUrl.addFirst(savePicss);
        }
        String proviceT=CityData.getProvince(myApplyBean.getProvince());
        String cityT=CityData.getCity(myApplyBean.getCity());
        String areaT=CityData.getArea(myApplyBean.getArea());
        if(!TextUtils.isEmpty(proviceT)){
            province=proviceT;
        }
        if(!TextUtils.isEmpty(cityT)){
            city=cityT;
        }
        if(!TextUtils.isEmpty(areaT)){
            county=areaT;
        }
       tvReleaseArea.setText(province + "/" + city + "/" + county);

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
                check();
                break;
            case R.id.bt_release_save:
                break;
        }
    }
    private void check(){
        if (TextUtils.isEmpty(etReleaseHomeName.getText().toString())) {
            ToastUtil.makeToast("小区名称不能为空");
            return;
        }
        if (TextUtils.isEmpty(etReleaseArea.getText().toString())) {
            ToastUtil.makeToast("面积不能为空");
            return;
        }
        if (TextUtils.isEmpty(selectedori)) {//朝向
            ToastUtil.makeToast("朝向不能为空");
            return;
        }

        if (TextUtils.isEmpty(etReleaseRoom.getText().toString())
                || TextUtils.isEmpty(etReleaseOffice.getText().toString())
                || TextUtils.isEmpty(etReleaseWc.getText().toString())) {
            ToastUtil.makeToast("户型不能为空");
            return;
        }

        if (TextUtils.isEmpty(etReleaseFloor.getText().toString())) {
            ToastUtil.makeToast("楼层不能为空");
            return;
        }
        if (TextUtils.isEmpty( et_release_total_floor.getText().toString())) {
            ToastUtil.makeToast("总楼层不能为空");
            return;
        }
        if (TextUtils.isEmpty(etReleaseAddress.getText().toString())) {
            ToastUtil.makeToast("地址不能为空");
            return;
        }
        if (TextUtils.isEmpty(etReleaseMonthMoney.getText().toString())) {
            ToastUtil.makeToast("月租不能为空");
            return;
        }
        if (TextUtils.isEmpty(etReleaseRealMoney.getText().toString())) {
            ToastUtil.makeToast("物业费不能为空");
            return;
        }
        if (TextUtils.isEmpty(etReleaseUserName.getText().toString())) {
            ToastUtil.makeToast("姓名不能为空");
            return;
        }
        if (TextUtils.isEmpty(etReleasePhone.getText().toString())) {
            ToastUtil.makeToast("手机号码不能为空");
            return;
        }
        if (TextUtils.isEmpty(etReleaseDescribe.getText().toString())) {
            ToastUtil.makeToast("房屋描述不能为空");
            return;
        }
        if(surfacePlotUrl==null){
            ToastUtil.makeToast("封面图不能为空");
            return;
        }
        if(detailDrawingData.size()<2){
            ToastUtil.makeToast("细节图不能为空或者小于2");
            return;
        }
        uplodeImg();
    }
    private void releaseData() {

        String homeHame = etReleaseHomeName.getText().toString();//小区的名字
        String area = etReleaseArea.getText().toString();//面积
        String room = etReleaseRoom.getText().toString();   //室
        String office = etReleaseOffice.getText().toString();//厅
        String wc = etReleaseWc.getText().toString();//卫生间
        String sddress = etReleaseAddress.getText().toString();//地址
        String local_floor =  etReleaseFloor.getText().toString();//在哪个楼层
        String total_floor = et_release_total_floor.getText().toString();//一共多少个楼层
        String monthMoney = etReleaseMonthMoney.getText().toString();//月租
        String realMoney = etReleaseRealMoney.getText().toString();//物业费
        String userName = etReleaseUserName.getText().toString();//姓名
        String phone = etReleasePhone.getText().toString();//手机号码
        String describe = etReleaseDescribe.getText().toString();//房屋描述

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
            pic = uplodeUrl.getLast();
        }
        uplodeUrl.removeLast();
        StringBuffer pics = new StringBuffer();
        for (String url : uplodeUrl) {
            pics.append(url );
            if(uplodeUrl.size()!=1){
                pics.append(",");
            }
        }
        if(myApplyBean!=null){

            releaseRentPresenter.updateRent(
                    myApplyBean.getId(),
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
        }else{
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

    }

    public void publishRentInfo() {
        hideLoading();
        ToastUtil.makeToast("发布成功，等待审批");
        finish();
    }

    public void uplodeImg() {
        showLoading();
        LinkedList imgList = new LinkedList();
        for(ReleaseImgBean bean:detailDrawingData){
            if(bean.getType()==1){
                imgList.add(bean.getPath());
            }
        }
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

    public void initRecyclerView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        rvDetailedDrawing.setLayoutManager(linearLayoutManager);
        datailedDrawingAdapter = new DatailedDrawingAdapter(detailDrawingData);
        rvDetailedDrawing.setAdapter(datailedDrawingAdapter);
        datailedDrawingAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                detailDrawingData.remove(position);
                datailedDrawingAdapter.notifyDataSetChanged();
            }


        });
        datailedDrawingAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                ReleaseImgBean releaseImgBean = detailDrawingData.get(position);
                if(releaseImgBean.getType()==-1){
                    if(detailDrawingData.size()>6){
                        ToastUtil.makeToast("细节图不能大于6张");
                        return;
                    }
                    //点击添加图片
                    isSurface = false;
                    cameraPopupWindows = new CameraPopupWindows(ReleaseRentActivity.this, getRootView());
                }else{

                }

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case CameraPopupWindows.TAKE_PICTURE:
                if (resultCode == -1) {// 拍照
                    themUrl = Bimp.startPhotoZoom(ReleaseRentActivity.this, cameraPopupWindows.getPhotoUri());
                }
                break;
            case CameraPopupWindows.RESULT_LOAD_IMAGE:
                if (resultCode == RESULT_OK && null != data) {// 相册返回
                    Uri uri = data.getData();
                    if (uri != null) {
                        themUrl = Bimp.startPhotoZoom(ReleaseRentActivity.this, uri);

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
                        detailDrawingData.addFirst(new ReleaseImgBean("",themUrl.getPath(),1));
                        datailedDrawingAdapter.notifyDataSetChanged();
                    }
                }
                break;
            case CameraPopupWindows.SELECTIMG_SEARCH:
                if (resultCode == RESULT_OK && null != data) {
                    String text = "#" + data.getStringExtra("topic") + "#";

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
        Drawable backColorSele = getResources().getDrawable(R.drawable.shape_circular_bead_blue_col);
        Drawable backColor = getResources().getDrawable(R.drawable.shape_circular_bead_gray_col);
        int textColorSele = getResources().getColor(R.color.white);
        int textColor = getResources().getColor(R.color.main_text_col);
        switch (type) {
            case 1:
                if (typeView.equals("普通住宅")) {
                    return;
                }
                typeView = "普通住宅";
                tvReleaseType1.setBackground(backColorSele);
                tvReleaseType2.setBackground(backColor);
                tvReleaseType3.setBackground(backColor);
                tvReleaseType4.setBackground(backColor);
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
                tvReleaseType1.setBackground(backColor);
                tvReleaseType2.setBackground(backColorSele);
                tvReleaseType3.setBackground(backColor);
                tvReleaseType4.setBackground(backColor);
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
                tvReleaseType1.setBackground(backColor);
                tvReleaseType2.setBackground(backColor);
                tvReleaseType3.setBackground(backColorSele);
                tvReleaseType4.setBackground(backColor);
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
                tvReleaseType1.setBackground(backColor);
                tvReleaseType2.setBackground(backColor);
                tvReleaseType3.setBackground(backColor);
                tvReleaseType4.setBackground(backColorSele);
                tvReleaseType1.setTextColor(textColor);
                tvReleaseType2.setTextColor(textColor);
                tvReleaseType3.setTextColor(textColor);
                tvReleaseType4.setTextColor(textColorSele);
                break;
        }
    }

    public void setWayView(int type) {
        Drawable backColorSele = getResources().getDrawable(R.drawable.shape_circular_bead_blue_col);
        Drawable backColor = getResources().getDrawable(R.drawable.shape_circular_bead_gray_col);
        int textColorSele = getResources().getColor(R.color.white);
        int textColor = getResources().getColor(R.color.main_text_col);
        switch (type) {
            case 1:
                if (wayView.equals("整租")) {
                    return;
                }
                wayView = "整租";
                tvReleaseWay1.setBackground(backColorSele);
                tvReleaseWay2.setBackground(backColor);
                tvReleaseWay3.setBackground(backColor);
                tvReleaseWay4.setBackground(backColor);
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
                tvReleaseWay1.setBackground(backColor);
                tvReleaseWay2.setBackground(backColorSele);
                tvReleaseWay3.setBackground(backColor);
                tvReleaseWay4.setBackground(backColor);
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
                tvReleaseWay1.setBackground(backColor);
                tvReleaseWay2.setBackground(backColor);
                tvReleaseWay3.setBackground(backColorSele);
                tvReleaseWay4.setBackground(backColor);
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
                tvReleaseWay1.setBackground(backColor);
                tvReleaseWay2.setBackground(backColor);
                tvReleaseWay3.setBackground(backColor);
                tvReleaseWay4.setBackground(backColorSele);
                tvReleaseWay1.setTextColor(textColor);
                tvReleaseWay2.setTextColor(textColor);
                tvReleaseWay3.setTextColor(textColor);
                tvReleaseWay4.setTextColor(textColorSele);
                break;
        }
    }

    public void setPaview(int type) {
        Drawable backColorSele = getResources().getDrawable(R.drawable.shape_circular_bead_blue_col);
        Drawable backColor = getResources().getDrawable(R.drawable.shape_circular_bead_gray_col);
        int textColorSele = getResources().getColor(R.color.white);
        int textColor = getResources().getColor(R.color.main_text_col);
        switch (type) {
            case 1:
                if (paview.equals("押一付一")) {
                    return;
                }
                paview = "押一付一";
                tvReleasePay1.setBackground(backColorSele);
                tvReleasePay2.setBackground(backColor);
                tvReleasePay3.setBackground(backColor);
                tvReleasePay1.setTextColor(textColorSele);
                tvReleasePay2.setTextColor(textColor);
                tvReleasePay3.setTextColor(textColor);
                break;
            case 2:
                if (paview.equals("押二付一")) {
                    return;
                }
                paview = "押二付一";
                tvReleasePay1.setBackground(backColor);
                tvReleasePay2.setBackground(backColorSele);
                tvReleasePay3.setBackground(backColor);
                tvReleasePay1.setTextColor(textColor);
                tvReleasePay2.setTextColor(textColorSele);
                tvReleasePay3.setTextColor(textColor);
                break;
            case 3:
                if (paview.equals("押一付三")) {
                    return;
                }
                paview = "押一付三";
                tvReleasePay1.setBackground(backColor);
                tvReleasePay2.setBackground(backColor);
                tvReleasePay3.setBackground(backColorSele);
                tvReleasePay1.setTextColor(textColor);
                tvReleasePay2.setTextColor(textColor);
                tvReleasePay3.setTextColor(textColorSele);
                break;

        }
    }

    public void uplodaImg(List<UplodeImgBean> data) {

    }
}
