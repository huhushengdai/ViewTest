package com.huhushengdai.testdrawable;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.drawable.Drawable;
import android.text.TextPaint;

/**
 * Date： 2017/7/19
 * Description:
 *
 * @author WangLizhi
 * @version 1.0
 */
public class MyDrawable extends Drawable {

    private TextPaint mTextPaint;
    private String text;

    public MyDrawable(Context context) {
        mTextPaint = new TextPaint(Paint.ANTI_ALIAS_FLAG);
        mTextPaint.density = context.getResources().getDisplayMetrics().density;
        mTextPaint.setDither(true);
        mTextPaint.setTextSize(24);
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public void draw(Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(Color.GREEN);
        canvas.drawRect(0,0,100,100,paint);

        canvas.drawText(text,0,50,mTextPaint);
    }

    @Override
    public void setAlpha(int alpha) {
        if (mTextPaint.getAlpha() != alpha) {
            mTextPaint.setAlpha(alpha);
        }
    }

    @Override
    public int getOpacity() {
        return PixelFormat.TRANSPARENT;
    }

    @Override
    public void setColorFilter(ColorFilter cf) {
        if (mTextPaint.getColorFilter() != cf) {
            mTextPaint.setColorFilter(cf);
        }
    }
}
