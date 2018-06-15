package com.cui.android.jianchengdichan.http.base;

import android.accounts.NetworkErrorException;
import android.content.Context;

import com.cui.android.jianchengdichan.utils.LogUtils;
import com.cui.android.jianchengdichan.utils.ProgressDialog;

import java.net.ConnectException;
import java.net.UnknownHostException;
import java.util.concurrent.TimeoutException;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * @author CUI
 * @data 2018/5/16.
 * @description Observer的基类
 */
public abstract class BasesObserver implements Observer<BasesBean> {
    protected Context mContext;

    public BasesObserver(Context cxt) {
        this.mContext = cxt;
    }

    public BasesObserver() {

    }

    @Override
    public void onSubscribe(Disposable d) {
        onRequestStart();

    }

    @Override
    public void onNext(BasesBean tBaseEntity) {
        LogUtils.w( "onNext: " );//这里可以打印错误信息
        onRequestEnd();
        if (tBaseEntity.isSuccess()) {
            try {
                onSuccees(tBaseEntity);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            try {
                onCodeError(tBaseEntity);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onError(Throwable e) {
        LogUtils.w( "onError: " );//这里可以打印错误信息
        onRequestEnd();
        try {
            if (e instanceof ConnectException
                    || e instanceof TimeoutException
                    || e instanceof NetworkErrorException
                    || e instanceof UnknownHostException) {
                onError(e, true);
            } else {
                onError(e, false);
            }
        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }

    @Override
    public void onComplete() {
    }

    /**
     * 返回成功
     *
     * @param t
     * @throws Exception
     */
    protected abstract void onSuccees(BasesBean t) throws Exception;

    /**
     * 返回成功了,但是code错误
     *
     * @param t
     * @throws Exception
     */
    protected abstract void onCodeError(BasesBean t) throws Exception ;
    /**
     * 返回失败
     *
     * @param e
     * @param isNetWorkError 是否是网络错误
     * @throws Exception
     */
    protected abstract void onError(Throwable e, boolean isNetWorkError) throws Exception;

    protected void onRequestStart() {
    }

    protected void onRequestEnd() {
        closeProgressDialog();
    }

    public void showProgressDialog() {
        ProgressDialog.show(mContext, false, "请稍后");
    }
    public void closeProgressDialog() {
        ProgressDialog.cancle();
    }

}
