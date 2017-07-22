package com.huhushengdai.testdrawable;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * Date： 2017/7/18
 * Description:
 *
 * @author WangLizhi
 * @version 1.0
 */
public class MyCirView extends ImageView {
    public MyCirView(Context context) {
        super(context);
    }

    public MyCirView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyCirView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (getDrawable() == null) {
            super.onDraw(canvas);
        }
    }

    @Override
    public void draw(Canvas canvas) {
        if (getDrawable() == null) {
            super.draw(canvas);
            return;
        }

    }
}
