package com.cui.android.jianchengdichan.view.ui.customview;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * @author CUI
 * @data 2018/6/12.
 * @details
 */
@SuppressLint("AppCompatCustomView")
public class UIImageView extends ImageView {
    public UIImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    public UIImageView(Context context) {
        super(context);
    }
    public UIImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
    }
    @Override
    protected void onDraw(Canvas canvas) {
        Path clipPath = new Path();
        int w = this.getWidth();
        int h = this.getHeight();
        clipPath.addRoundRect(new RectF(0, 0, w, h), 10.0f, 10.0f, Path.Direction.CW);
        canvas.clipPath(clipPath);
        super.onDraw(canvas);
    }
}
