package com.cui.android.jianchengdichan.utils;

import android.widget.Toast;

import com.cui.android.jianchengdichan.MyApplication;

public class ToastUtil {
    /**
     * 吐丝信息
     *
     * @param msg 要显示的提示信息
     */
    public static void makeToast(String msg) {
        Toast.makeText(MyApplication.getAppContext(), msg, Toast.LENGTH_SHORT).show();
    }
}
