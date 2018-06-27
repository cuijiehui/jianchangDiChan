package com.cui.android.jianchengdichan.view.base;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cui.android.jianchengdichan.presenter.BasePresenter;
import com.cui.android.jianchengdichan.utils.ToastUtil;
import com.cui.android.jianchengdichan.view.interfaces.IBaseView;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * @author CUI
 * @data 2018/6/25.
 * @details
 */
public abstract class BaseFragment extends Fragment implements IBaseView {
    public Activity mActivity;
    public Context mContext;
    public Unbinder unbinder;
    private BasePresenter<IBaseView> mPresenter;
    public View rootView ;



    /**
     * 绑定的view id
     *
     * @return
     */
    public abstract int bindLayout();

    /**
     * 初始化数据 比initView早 比如拿到上界面传递过来的数据 或者是初始化全局变量
     *
     */
    public abstract void initData();

    /**
     * 初始化界面 比initData晚
     */
    public abstract void initView();

    /**
     * 初始化 P层
     * @return
     */
    public abstract BasePresenter initPresenter();
    /**
     * [业务操作]
     *  如去访问网络
     */
    public abstract void doBusiness();


    /**
     * 显示Toast消息
     *
     * @param message 消息文本字符串
     */
    public final void showToast(@NonNull String message) {
        if (TextUtils.isEmpty(message))
            return;
        ToastUtil.makeToast(message);
    }
    /**
     * 跳转到指定的Activity
     *
     * @param targetActivity 要跳转的目标Activity
     */
    protected final void startActivity(@NonNull Class<?> targetActivity) {
        startActivity(targetActivity,null);
    }
    /**
     * 跳转到指定的Activity
     *
     * @param targetActivity 要跳转的目标Activity
     */
    protected final void startActivity(@NonNull Class<?> targetActivity, Bundle bundle) {
        Intent intent = new Intent(mContext,targetActivity);
        if(bundle!=null){
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }
    public <T> ObservableTransformer<T, T> setThread() {
        return new ObservableTransformer<T, T>() {
            @Override
            public ObservableSource<T> apply(Observable<T> upstream) {
                return upstream.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
            }
        };
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(bindLayout(), container, false);
        unbinder = ButterKnife.bind(this, view);
        rootView=view;
        mActivity = getActivity();
        mContext = getContext();
        initData();
        initView();
        mPresenter=initPresenter();
        if(mPresenter!=null){
            mPresenter.attachView(this);
            mPresenter.setTransformer(setThread());
        }
        doBusiness();
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (unbinder != null) {
            unbinder.unbind();
        }
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showErr() {

    }

    @Override
    public void onFailure(String msg) {
        showToast(msg);
    }

    @Override
    public void onError() {
        showToast("网络异常");
    }
}
