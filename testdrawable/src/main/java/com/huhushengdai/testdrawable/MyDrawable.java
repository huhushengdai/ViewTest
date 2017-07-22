package com.huhushengdai.testdrawable;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.text.Layout;
import android.text.StaticLayout;
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
    private String mText;
    private StaticLayout mTextLayout;
    private Layout.Alignment mTextAlignment = Layout.Alignment.ALIGN_NORMAL;
    private Rect mTextBounds;
    public MyDrawable(Context context) {
        mTextPaint = new TextPaint(Paint.ANTI_ALIAS_FLAG);
        mTextPaint.density = context.getResources().getDisplayMetrics().density;
        mTextPaint.setDither(true);
        mTextPaint.setTextSize(24);
        mTextBounds = new Rect();
//        mTextPaint.setTextAlign(Paint.Align.LEFT);
    }

    public void setText(String text) {
        mText = text;
        float desired = Layout.getDesiredWidth(mText, mTextPaint);
        mTextLayout = new StaticLayout(mText, mTextPaint, (int)desired,
                mTextAlignment, 1.0f, 0.0f, false);
//        int size = mTextLayout.getWidth()>mTextLayout.getHeight()?mTextLayout.getWidth():mTextLayout.getHeight();
//        mTextBounds.set(0, 0, size, size);
        mTextBounds.set(0, 0,mTextLayout.getWidth(),mTextLayout.getHeight());
    }

    @Override
    public void draw(Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(Color.RED);
//        canvas.drawRect(0, 0, 100, 100, paint);

        if (mTextLayout != null) {

            canvas.drawRect(0,0,getIntrinsicWidth(),getIntrinsicHeight(),paint);
            mTextLayout.draw(canvas);
        }
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

    @Override
    public int getIntrinsicHeight() {
        //Return the vertical bounds measured, or -1 if none
        if (mTextBounds.isEmpty()) {
            return -1;
        } else {
            return (mTextBounds.bottom - mTextBounds.top);
        }
    }

    @Override
    public int getIntrinsicWidth() {
        //Return the horizontal bounds measured, or -1 if none
        if (mTextBounds.isEmpty()) {
            return -1;
        } else {
            return (mTextBounds.right - mTextBounds.left);
        }
    }

    @Override
    protected void onBoundsChange(Rect bounds) {
//        mTextBounds.set(bounds);
    }
}
