package com.cui.android.jianchengdichan.view.ui.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextSwitcher;
import android.widget.TextView;

import com.cui.android.jianchengdichan.R;
import com.cui.android.jianchengdichan.utils.LogUtils;
import com.cui.android.jianchengdichan.view.ui.Fragment.Adapter.CommRvAdapter;
import com.cui.android.jianchengdichan.view.ui.Fragment.Adapter.interfaces.OnRecyclerViewItemClickListener;
import com.cui.android.jianchengdichan.view.ui.customview.RefreshableView;
import com.youth.banner.Banner;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


public class MainCommFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    @BindView(R.id.tv_comm_top_qrcode)
    TextView tvCommTopQrcode;
    @BindView(R.id.et_comm_top_seek)
    EditText etCommTopSeek;
    @BindView(R.id.iv_comm_top_add)
    ImageView ivCommTopAdd;
    @BindView(R.id.rl_comm_top)
    RelativeLayout rlCommTop;
    @BindView(R.id.bn_main_comm_adv)
    Banner bnMainCommAdv;
    @BindView(R.id.tv_main_comm_notice_new)
    TextSwitcher tvMainCommNoticeNew;
    @BindView(R.id.rv_comm_top)
    RecyclerView rvCommTop;
    @BindView(R.id.iv_comm_con)
    ImageView ivCommCon;
    @BindView(R.id.rv_comm)
    RecyclerView rvComm;
    @BindView(R.id.rv_comm_refreshable)
    RefreshableView rvCommRefreshable;
    Unbinder unbinder;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private List<Integer> dataTop = new ArrayList<>();
    private List<Integer> dataRv= new ArrayList<>();

    public MainCommFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MainCommFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MainCommFragment newInstance(String param1, String param2) {
        LogUtils.i("newInstance");

        MainCommFragment fragment = new MainCommFragment();
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
        dataTop.add(R.drawable.courier_icon);
        dataTop.add(R.drawable.laundry_icon);
        dataTop.add(R.drawable.doctor_icon);
        dataTop.add(R.drawable.dining_hall_icon);
        dataTop.add(R.drawable.bank_icon);
        dataTop.add(R.drawable.readbook_icon);

        dataRv.add(R.drawable.parking_car);
        dataRv.add(R.drawable.leasing_center);
        dataRv.add(R.drawable.parking_management);
        dataRv.add(R.drawable.pay_cost);
        dataRv.add(R.drawable.blacklist);
        dataRv.add(R.drawable.more_service);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_main_comm, container, false);
        unbinder = ButterKnife.bind(this, view);

        initRecyclerView();
        return view;
    }

    private void initRecyclerView() {
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2);
        rvCommTop.setLayoutManager(gridLayoutManager);
        CommRvAdapter commRvAdapter = new CommRvAdapter(dataTop, new OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {

            }

            @Override
            public void onItemLongClick(View view, int position) {

            }
        });
        rvCommTop.setAdapter(commRvAdapter);

        GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 2);
        rvComm.setLayoutManager(layoutManager);
        CommRvAdapter commRvAdapter1 = new CommRvAdapter(dataRv, new OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {

            }

            @Override
            public void onItemLongClick(View view, int position) {

            }
        });
        rvComm.setAdapter(commRvAdapter1);

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
