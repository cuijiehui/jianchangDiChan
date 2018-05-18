package com.cui.android.jianchengdichan.view.ui.Fragment.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.view.View;

import com.cui.android.jianchengdichan.utils.LogUtils;

import java.util.List;

public class MainPagerAdapter extends FragmentPagerAdapter {

    private Context mContext;
    private List<Fragment> mDataList;

    public MainPagerAdapter(FragmentManager fm, Context mContext, List<Fragment> mDataList) {
        super(fm);
        LogUtils.i("MainPagerAdapter="+mDataList.size());
        this.mContext = mContext;
        this.mDataList = mDataList;
    }

    @Override
    public int getCount() {
        return mDataList==null?0:mDataList.size();
    }
    @Override
    public android.support.v4.app.Fragment getItem(int position) {
        return mDataList.get(position);
    }

}
