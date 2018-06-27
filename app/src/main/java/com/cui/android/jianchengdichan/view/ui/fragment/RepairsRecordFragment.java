package com.cui.android.jianchengdichan.view.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cui.android.jianchengdichan.R;
import com.cui.android.jianchengdichan.http.bean.RepairsBean;
import com.cui.android.jianchengdichan.presenter.RepairsPresenter;
import com.cui.android.jianchengdichan.utils.LogUtils;
import com.cui.android.jianchengdichan.utils.SPKey;
import com.cui.android.jianchengdichan.utils.SPUtils;
import com.cui.android.jianchengdichan.view.ui.fragment.adapter.RepairsAdapter;

import java.util.ArrayList;
import java.util.List;

public class RepairsRecordFragment extends Fragment {

    private View view;
    private RecyclerView recycle_repairs;
    private int uid;
    private String token;
    private int page;
    private List<RepairsBean> allList;
    private RepairsAdapter adapter;
    private boolean isMore;

    private static RepairsPresenter mRepairsPresenter;
    private static RepairsRecordFragment instance;
    public static RepairsRecordFragment getInstance(RepairsPresenter repairsPresenter){
        if(instance==null){
            instance=new RepairsRecordFragment();
        }
        mRepairsPresenter=repairsPresenter;
        return instance;
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_repairs_record , null);

        initView();
        initData(page);

        return view;
    }

    private void initView() {
        allList = new ArrayList<>();
        uid = (int) SPUtils.INSTANCE.getSPValue(SPKey.SP_USER_UID_KEY, SPUtils.DATA_INT);

        page = 1;
        recycle_repairs = (RecyclerView) view.findViewById(R.id.recycle_repairs);
        adapter = new RepairsAdapter(allList);
        recycle_repairs.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        recycle_repairs.setAdapter(adapter);
        recycle_repairs.addItemDecoration(new DividerItemDecoration(getContext(),DividerItemDecoration.VERTICAL));

        recycle_repairs.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                RecyclerView.LayoutManager manager = recyclerView.getLayoutManager();

                int lastCompletelyVisibleItemPosition = ((LinearLayoutManager) manager).findLastCompletelyVisibleItemPosition();
                if (adapter.getItemCount() > 4 && lastCompletelyVisibleItemPosition == adapter.getItemCount() - 1 && isMore) {
                    LogUtils.i("加载下一页-----------------");
                    page++;
                    initData(page);
                    isMore = false;
                }
            }
        });
    }
    public void  setData(List<RepairsBean> data){
        allList.addAll(data);
        adapter.notifyDataSetChanged();

    }
    private void initData(int page) {
        String token = (String) SPUtils.INSTANCE.getSPValue(SPKey.SP_USER_TOKEN_KEY, SPUtils.DATA_STRING);
        mRepairsPresenter.getRepairInfoList(uid,token,page);

    }
}
