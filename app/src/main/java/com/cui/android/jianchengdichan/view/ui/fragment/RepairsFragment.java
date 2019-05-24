package com.cui.android.jianchengdichan.view.ui.fragment;

import android.annotation.SuppressLint;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.cui.android.jianchengdichan.R;
import com.cui.android.jianchengdichan.http.bean.RepairCateBean;
import com.cui.android.jianchengdichan.http.bean.UplodeImgBean;
import com.cui.android.jianchengdichan.presenter.RepairsPresenter;
import com.cui.android.jianchengdichan.utils.LogUtils;
import com.cui.android.jianchengdichan.utils.Okhttp3Utils;
import com.cui.android.jianchengdichan.utils.SPKey;
import com.cui.android.jianchengdichan.utils.SPUtils;
import com.cui.android.jianchengdichan.utils.ToastUtil;
import com.cui.android.jianchengdichan.view.base.BaseActivity;
import com.cui.android.jianchengdichan.view.ui.adapter.DatailedDrawingAdapter;
import com.cui.android.jianchengdichan.view.ui.beans.ReleaseImgBean;
import com.cui.android.jianchengdichan.view.ui.customview.CameraPopupWindows;
import com.cui.android.jianchengdichan.view.ui.fragment.adapter.RepairsTypeAdapter;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class RepairsFragment extends Fragment {

    @BindView(R.id.tv_repairs_type)
    TextView tvRepairsType;
    @BindView(R.id.rv_repairs_type)
    RecyclerView rvRepairsType;
    @BindView(R.id.rel_repairs_type)
    RelativeLayout relRepairsType;
    @BindView(R.id.tv_repairs_title)
    TextView tvRepairsTitle;
    @BindView(R.id.ed_title)
    EditText edTitle;
    @BindView(R.id.rel_repairs_title)
    RelativeLayout relRepairsTitle;
    @BindView(R.id.tv_repairs_name)
    TextView tvRepairsName;
    @BindView(R.id.ed_name)
    EditText edName;
    @BindView(R.id.rel_repairs_name)
    RelativeLayout relRepairsName;
    @BindView(R.id.tv_repairs_phone)
    TextView tvRepairsPhone;
    @BindView(R.id.ed_tel)
    EditText edTel;
    @BindView(R.id.rel_repairs_phone)
    RelativeLayout relRepairsPhone;
    @BindView(R.id.et_feedback_txt)
    EditText etFeedbackTxt;
    @BindView(R.id.tv_submit)
    TextView tvSubmit;
    @BindView(R.id.rv_add_img)
    RecyclerView rv_add_img;
    public Uri themUrl = null;
    public DatailedDrawingAdapter datailedDrawingAdapter;
    Unbinder unbinder;
    @BindView(R.id.tv_repairs_num)
    TextView tvRepairsNum;
    @BindView(R.id.ed_repairs_num)
    EditText edRepairsNum;
    @BindView(R.id.rel_repairs_num)
    RelativeLayout relRepairsNum;

    private View view;
    private int uid;
    private String area;
    private static RepairsPresenter mRepairsPresenter;
    private static RepairsFragment instance;
    private RepairsTypeAdapter repairsAdapter;
    private List<String> dataList = new ArrayList<>();
    CameraPopupWindows cameraPopupWindows;
    public final static int UPLODE_IMG = 300;
    public final static int UPLODE_IMG_FAIL = -300;
    public LinkedList<ReleaseImgBean> detailDrawingData = new LinkedList<>();
    private LinkedList<String> uplodeUrl = new LinkedList<>();

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
                    submitData();

                    break;
                case UPLODE_IMG_FAIL:
                    ToastUtil.makeToast("上传图片失败");
                    break;
            }
        }
    };

    @Override
    public void onDestroy() {
        super.onDestroy();
        mHandler.removeMessages(UPLODE_IMG);
    }

    public RepairsFragment() {
    }

    public static RepairsFragment getInstance(RepairsPresenter repairsPresenter) {
        if (instance == null) {
            instance = new RepairsFragment();
        }
        mRepairsPresenter = repairsPresenter;
        return instance;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        area = "gz";
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_repairs, null);

        uid = (int) SPUtils.INSTANCE.getSPValue(SPKey.SP_USER_UID_KEY, SPUtils.DATA_INT);

        unbinder = ButterKnife.bind(this, view);

        initView();
        mRepairsPresenter.getRepairCate();
        return view;
    }

    public void uplodeImg() {
        LinkedList imgList = new LinkedList();
        if (detailDrawingData.size() == 1) {
            submitData();
        } else {
            for (ReleaseImgBean bean : detailDrawingData) {
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



    private void initView() {

        tvSubmit.setOnClickListener(clickListener);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);//horizontal
        rvRepairsType.setLayoutManager(linearLayoutManager);
        repairsAdapter = new RepairsTypeAdapter(dataList);
        rvRepairsType.setAdapter(repairsAdapter);

        LinearLayoutManager linearLayoutManager2 = new LinearLayoutManager(getContext());
        linearLayoutManager2.setOrientation(LinearLayoutManager.HORIZONTAL);
        rv_add_img.setLayoutManager(linearLayoutManager2);
        if (detailDrawingData.size() == 0) {
            detailDrawingData.add(new ReleaseImgBean("", "", -1));
        }
        datailedDrawingAdapter = new DatailedDrawingAdapter(detailDrawingData);
        rv_add_img.setAdapter(datailedDrawingAdapter);
        datailedDrawingAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                //点击添加图片
                if (detailDrawingData.size() > 3) {
                    ToastUtil.makeToast("故障图为1-3张");
                    return;
                }
                ReleaseImgBean releaseImgBean = detailDrawingData.get(position);
                if(releaseImgBean.getType()==-1){
                    cameraPopupWindows = new CameraPopupWindows((BaseActivity) getActivity(), view);
                }else{

                }
            }
        });
        datailedDrawingAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {

                detailDrawingData.remove(position);
                datailedDrawingAdapter.notifyDataSetChanged();
            }
        });
    }

    public Uri getThemUrl() {
        if (cameraPopupWindows != null) {
            return cameraPopupWindows.getPhotoUri();

        }
        return null;
    }

    View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.tv_submit:
                    uplodeImg();
                    break;
            }
        }
    };

    private void submitData() {
        String name = edName.getText().toString().trim();
        String tel = edTel.getText().toString().trim();
        String content = etFeedbackTxt.getText().toString().trim();
        String title = edTitle.getText().toString().trim();
//        String num =edRepairsNum.getText().toString().trim();
        String token = (String) SPUtils.INSTANCE.getSPValue(SPKey.SP_USER_TOKEN_KEY, SPUtils.DATA_STRING);

        if (TextUtils.isEmpty(name)) {
            ToastUtil.makeToast("请输入联系人姓名");
            return;
        }

        if (TextUtils.isEmpty(tel)) {
            ToastUtil.makeToast("请输入联系人电话");
            return;
        } else {
            if (!verifyPhone(tel)) {
                ToastUtil.makeToast("请输入正确的手机号");
                return;
            }
        }

//        if (TextUtils.isEmpty(num)) {
//            ToastUtil.makeToast("请输入房间号");
//            return;
//        }
        if (TextUtils.isEmpty(content)) {
            ToastUtil.makeToast("请输入报修内容");
            return;
        }
        if (TextUtils.isEmpty(title)) {
            ToastUtil.makeToast("请输入故障地点");
            return;
        }
        StringBuffer pics = new StringBuffer();
        for (String url : uplodeUrl) {
            pics.append(url);
            if (uplodeUrl.size() > 1) {
                pics.append(",");

            }
        }
        String type = dataList.get(repairsAdapter.selePosition);

        mRepairsPresenter.submitRepairInfo(uid
                , token
                , tel
                , name
                , content
                , pics.toString()
                , "1"
                , type
                , title);
    }


    private boolean verifyPhone(String phone) {
        //在将数据写入数据库之前,判断用户输入的邮箱格式是否正确
        String filter = "^0?1[3|4|5|8|7][0-9]\\d{8}$";
        Pattern p = Pattern.compile(filter);
        Matcher matcher = p.matcher(phone);
        if (!matcher.find()) {
            return false;
        }
        return true;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    public void setTypeData(List<RepairCateBean> data) {
        if (data != null) {
            dataList.clear();
            for (RepairCateBean repairCateBean : data) {
                dataList.add(repairCateBean.getName());
            }
            repairsAdapter.notifyDataSetChanged();
        }
    }
}
