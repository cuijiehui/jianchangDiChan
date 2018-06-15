package com.cui.android.jianchengdichan.view.ui.customview;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * @author CUI
 * @data 2018/6/15.
 * @details
 */
@SuppressLint("AppCompatCustomView")
public class SquareImageView extends ImageView{
    public SquareImageView(Context context) {
        super(context);
    }

    public SquareImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SquareImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        //高度就是宽度值
        super.onMeasure(widthMeasureSpec, widthMeasureSpec);
    }
}
