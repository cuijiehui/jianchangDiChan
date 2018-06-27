package com.cui.android.jianchengdichan.view.ui.customview;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.widget.EditText;

import com.cui.android.jianchengdichan.utils.LogUtils;

/**
 * @author CUI
 * @data 2018/6/21.
 * @details
 */
@SuppressLint("AppCompatCustomView")
public class EditTextView extends EditText {
    public EditTextView(Context context) {
        super(context);
    }

    public EditTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public EditTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
    @Override
    public boolean onKeyPreIme(int keyCode, KeyEvent event) {
        LogUtils.i(keyCode+"-keyCode=");
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == 1) {
            super.onKeyPreIme(keyCode, event);
            onKeyBoardHideListener.onKeyHide(keyCode, event);
            return false;
        }
        return super.onKeyPreIme(keyCode, event);
    }

    /**
     *键盘监听接口
     */
    OnKeyBoardHideListener onKeyBoardHideListener;
    public void setOnKeyBoardHideListener(OnKeyBoardHideListener onKeyBoardHideListener) {
        this.onKeyBoardHideListener = onKeyBoardHideListener;
    }

    public interface OnKeyBoardHideListener{
        void onKeyHide(int keyCode, KeyEvent event);
    }
}
