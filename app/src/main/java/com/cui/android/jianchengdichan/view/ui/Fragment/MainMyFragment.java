package com.cui.android.jianchengdichan.view.ui.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.cui.android.jianchengdichan.R;
import com.cui.android.jianchengdichan.utils.LogUtils;
import com.cui.android.jianchengdichan.utils.SPKey;
import com.cui.android.jianchengdichan.utils.SPUtils;
import com.cui.android.jianchengdichan.utils.ToastUtil;
import com.cui.android.jianchengdichan.view.ui.FeedbackActivity;
import com.cui.android.jianchengdichan.view.ui.LoginActivity;
import com.cui.android.jianchengdichan.view.ui.PayFeesActivity;
import com.cui.android.jianchengdichan.view.ui.SetingActivity;
import com.cui.android.jianchengdichan.view.ui.customview.CircleImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class MainMyFragment extends Fragment {
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

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


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
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.iv_my_top_back, R.id.tv_content_name, R.id.iv_top_right_set, R.id.civ_my_head_portrait, R.id.tv_my_user_name, R.id.iv_my_member, R.id.tv_my_collect, R.id.tv_my_sign, R.id.tv_my_order, R.id.tv_my_delivery, R.id.tv_my_pickup, R.id.tv_my_datails, R.id.tv_my_feed_back, R.id.tv_my_contact_us})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_my_top_back:
                LogUtils.i("iv_my_top_back=");

                break;
            case R.id.tv_content_name:
                LogUtils.i("tv_content_name=");

                break;
            case R.id.iv_top_right_set:
                LogUtils.i("iv_top_right_set=");
                startActivity(new Intent(getContext(),SetingActivity.class));

                break;
            case R.id.civ_my_head_portrait:
                LogUtils.i("civ_my_head_portrait=");

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
                boolean isLogin =(boolean) SPUtils.INSTANCE.getSPValue(SPKey.SP_LOAGIN_KEY,SPUtils.DATA_BOOLEAN);
                if(isLogin){
//                    startActivity(new Intent(getContext(), PayFeesActivity.class));
                    ToastUtil.makeToast("已经登录，签到成功");
                }else{
                    startActivity(new Intent(getContext(), LoginActivity.class));
                }

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
                LogUtils.i("tv_my_feed_back=");
                startActivity(new Intent(getContext(),FeedbackActivity.class));

                break;
            case R.id.tv_my_contact_us:
                LogUtils.i("tv_my_contact_us=");

                break;
        }
    }
}
