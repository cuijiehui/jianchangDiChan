package com.cui.android.jianchengdichan.view.ui.Fragment;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.cui.android.jianchengdichan.R;
import com.cui.android.jianchengdichan.http.bean.LoginBean;
import com.cui.android.jianchengdichan.presenter.MainMyPresenter;
import com.cui.android.jianchengdichan.utils.LogUtils;
import com.cui.android.jianchengdichan.utils.Okhttp3Utils;
import com.cui.android.jianchengdichan.utils.SPKey;
import com.cui.android.jianchengdichan.utils.SPUtils;
import com.cui.android.jianchengdichan.utils.ToastUtil;
import com.cui.android.jianchengdichan.view.interfaces.IBaseView;
import com.cui.android.jianchengdichan.view.ui.CommAddListAtivity;
import com.cui.android.jianchengdichan.view.ui.FeedbackActivity;
import com.cui.android.jianchengdichan.view.ui.LoginActivity;
import com.cui.android.jianchengdichan.view.ui.MainActivity;
import com.cui.android.jianchengdichan.view.ui.MyApplyActivity;
import com.cui.android.jianchengdichan.view.ui.PersonalDataActivity;
import com.cui.android.jianchengdichan.view.ui.SetingActivity;
import com.cui.android.jianchengdichan.view.ui.customview.CircleImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class MainMyFragment extends Fragment implements IBaseView {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    @BindView(R.id.iv_my_top_back)
    ImageView ivMyTopBack;
    @BindView(R.id.tv_content_name)
    TextView tvContentName;
    @BindView(R.id.iv_top_right_set)
    ImageView ivTopRightSet;
    @BindView(R.id.civ_my_head_portrait)
    CircleImageView civMyHeadPortrait;
    @BindView(R.id.tv_my_user_name)
    TextView tvMyUserName;
    @BindView(R.id.iv_my_member)
    ImageView ivMyMember;
    @BindView(R.id.tv_my_collect)
    TextView tvMyCollect;
    @BindView(R.id.tv_my_sign)
    TextView tvMySign;
    @BindView(R.id.tv_my_order)
    TextView tvMyOrder;
    @BindView(R.id.tv_my_delivery)
    TextView tvMyDelivery;
    @BindView(R.id.tv_my_pickup)
    TextView tvMyPickup;
    @BindView(R.id.tv_my_datails)
    TextView tvMyDatails;
    @BindView(R.id.tv_my_feed_back)
    TextView tvMyFeedBack;
    @BindView(R.id.tv_my_contact_us)
    TextView tvMyContactUs;
    Unbinder unbinder;
    @BindView(R.id.tv_my_in_comm)
    TextView tvMyInComm;
    @BindView(R.id.tv_my_release_record)
    TextView tvMyReleaseRecord;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    MainMyPresenter mainMyPresenter =new MainMyPresenter();
    public MainMyFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MainMyFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MainMyFragment newInstance(String param1, String param2) {
        LogUtils.i("newInstance");

        MainMyFragment fragment = new MainMyFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LogUtils.i("onCreate");

        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        mainMyPresenter.attachView(this);
        mainMyPresenter.setTransformer(setThread());
    }
    public <T> ObservableTransformer<T, T> setThread() {
        return new ObservableTransformer<T, T>() {
            @Override
            public ObservableSource<T> apply(Observable<T> upstream) {
                return upstream.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
            }
        };
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_main_my, container, false);
        unbinder = ButterKnife.bind(this, view);


        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        String pic = (String) SPUtils.INSTANCE.getSPValue(SPKey.SP_USER_PIC_URL_KEY, SPUtils.DATA_STRING);
        if (!TextUtils.isEmpty(pic)) {
            Okhttp3Utils.getInstance().glide(getContext(),pic,civMyHeadPortrait);
        }
        boolean isLogin = (boolean) SPUtils.INSTANCE.getSPValue(SPKey.SP_LOAGIN_KEY, SPUtils.DATA_BOOLEAN);
        String userName = (String) SPUtils.INSTANCE.getSPValue(SPKey.SP_USER_NAME_KEY, SPUtils.DATA_STRING);
        if(isLogin){
            tvMyUserName.setText(userName);
        }
        int uid =(int) SPUtils.INSTANCE.getSPValue(SPKey.SP_USER_UID_KEY,SPUtils.DATA_INT);
        String token = (String)SPUtils.INSTANCE.getSPValue(SPKey.SP_USER_TOKEN_KEY,SPUtils.DATA_STRING);
        mainMyPresenter.getUserInfo(uid,token);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.iv_my_top_back, R.id.tv_content_name, R.id.iv_top_right_set, R.id.civ_my_head_portrait, R.id.tv_my_user_name, R.id.iv_my_member, R.id.tv_my_collect, R.id.tv_my_sign, R.id.tv_my_order, R.id.tv_my_delivery, R.id.tv_my_pickup, R.id.tv_my_datails
            , R.id.tv_my_feed_back, R.id.tv_my_contact_us,R.id.tv_my_in_comm, R.id.tv_my_release_record})
    public void onViewClicked(View view) {
        boolean isLogin = (boolean) SPUtils.INSTANCE.getSPValue(SPKey.SP_LOAGIN_KEY, SPUtils.DATA_BOOLEAN);
        if (isLogin) {
        } else {
            startActivity(new Intent(getContext(), LoginActivity.class));
            return;
        }
        switch (view.getId()) {
            case R.id.iv_my_top_back:
                LogUtils.i("iv_my_top_back=");
                MainActivity mainActivity =(MainActivity)getActivity();
                mainActivity.vpMainPager.setCurrentItem(0);
                break;
            case R.id.tv_content_name:
                LogUtils.i("tv_content_name=");

                break;
            case R.id.iv_top_right_set:
                LogUtils.i("iv_top_right_set=");
                startActivity(new Intent(getContext(), SetingActivity.class));

                break;
            case R.id.civ_my_head_portrait:
                LogUtils.i("civ_my_head_portrait=");
                startActivity(new Intent(getContext(), PersonalDataActivity.class));

                break;
            case R.id.tv_my_user_name:
                LogUtils.i("tv_my_user_name=");

                break;
            case R.id.iv_my_member:
                LogUtils.i("iv_my_member=");

                break;
            case R.id.tv_my_collect:
                LogUtils.i("tv_my_collect=");

                break;
            case R.id.tv_my_sign:
                LogUtils.i("tv_my_sign=");
//                ToastUtil.makeToast("签到成功");
                showPopwindow();
                break;
            case R.id.tv_my_order:
                LogUtils.i("tv_my_order=");

                break;
            case R.id.tv_my_delivery:
                LogUtils.i("tv_my_delivery=");

                break;
            case R.id.tv_my_pickup:
                LogUtils.i("tv_my_pickup=");

                break;
            case R.id.tv_my_datails:
                LogUtils.i("tv_my_datails=");

                break;
            case R.id.tv_my_feed_back:
                    startActivity(new Intent(getContext(), FeedbackActivity.class));
                break;
            case R.id.tv_my_contact_us:
                LogUtils.i("tv_my_contact_us=");
                break;
            case R.id.tv_my_in_comm:
                    startActivity(new Intent(getContext(), CommAddListAtivity.class));
                break;
            case R.id.tv_my_release_record:

                startActivity(new Intent(getContext(), MyApplyActivity.class));

                break;
        }
    }
    /**
     * 显示popupWindow
     */
    private void showPopwindow() {
        // 利用layoutInflater获得View
        LayoutInflater inflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.feedback_popupwindow_layout, null);

        // 下面是两种方法得到宽度和高度 getWindow().getDecorView().getWidth()

        final PopupWindow window = new PopupWindow(view, WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.MATCH_PARENT);
        ImageView feedback_icon = view.findViewById(R.id.feedback_icon);
        TextView tv_text_cntent = view.findViewById(R.id.tv_text_cntent);
        feedback_icon.setBackgroundResource(R.drawable.my_sign_in_icon);
        tv_text_cntent.setText("签到成功，获得5积分！");
        // 设置popWindow弹出窗体可点击，这句话必须添加，并且是true
        window.setFocusable(true);
//                 window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        // 实例化一个ColorDrawable颜色为半透明
        ColorDrawable dw = new ColorDrawable(0xb0000000);
        window.setBackgroundDrawable(dw);
        // 设置PopupWindow是否能响应外部点击事件
        window.setOutsideTouchable(true);
        // 设置PopupWindow是否能响应点击事件
        window.setTouchable(true);
        // 设置popWindow的显示和消失动画
//                 window.setAnimationStyle(R.style.mypopwindow_anim_style);
        // 在底部显示
        window.showAtLocation(getView(), Gravity.CENTER, 0, 0);

        // 这里检验popWindow里的button是否可以点击
        ImageView iv_feeback_popup_del = (ImageView) view.findViewById(R.id.iv_feeback_popup_del);
        iv_feeback_popup_del.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                System.out.println("第一个按钮被点击了");
                window.dismiss();
            }
        });

        // popWindow消失监听方法
//                 window.setOnDismissListener(new PopupWindow.OnDismissListener() {
//
//                     @Override
//             public void onDismiss() {
//                                 System.out.println("popWindow消失");
//                             }
//         });

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showToast(String msg) {

    }

    @Override
    public void showErr() {

    }

    @Override
    public void onFailure(String msg) {

    }

    @Override
    public void onError() {

    }

    public void getUserInfo(LoginBean data) {
        /**
         *  "community_id": null,
         "unit_id": null,
         "property_id": null,
         "com_id": null,
         "name": "吴女士",
         "nickname": "",
         "pic": ""
         */
        if (data.getCom_id()!=null){
            SPUtils.INSTANCE.setSPValue(SPKey.SP_USER_COM_ID_KEY,data.getCom_id());
        }
        if (data.getCommunity_id()!=null){
            SPUtils.INSTANCE.setSPValue(SPKey.SP_USER_COMMUNITY_ID_KEY,data.getCommunity_id());
        }
        if (data.getUnit_id()!=null){
            SPUtils.INSTANCE.setSPValue(SPKey.SP_USER_UNIT_ID_KEY,data.getUnit_id());

        }
        if (data.getProperty_id()!=null){
            SPUtils.INSTANCE.setSPValue(SPKey.SP_USER_PROPERTY_ID_KEY,data.getProperty_id());

        }
        if(data.getPic()!=null){
            SPUtils.INSTANCE.setSPValue(SPKey.SP_USER_PIC_URL_KEY,data.getPic());
            Okhttp3Utils.getInstance().glide(getContext(),data.getPic(),civMyHeadPortrait);

        }
        if(data.getName()!=null){
            SPUtils.INSTANCE.setSPValue(SPKey.SP_USER_TRUE_NAME_KEY,data.getName());

        }
        if(data.getNickname()!=null){
            SPUtils.INSTANCE.setSPValue(SPKey.SP_USER_NAME_KEY,data.getNickname());
            boolean isLogin = (boolean) SPUtils.INSTANCE.getSPValue(SPKey.SP_LOAGIN_KEY, SPUtils.DATA_BOOLEAN);
            if(isLogin){
                tvMyUserName.setText(data.getNickname());
            }
        }

    }
}
