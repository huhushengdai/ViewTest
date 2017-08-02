package com.huhushengdai.testdrawable;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.drawable.Drawable;
import android.support.annotation.ColorInt;
import android.support.annotation.NonNull;
import android.text.Layout;
import android.text.TextPaint;

/**
 * Date： 2017/8/2
 * Description:
 *
 * @author WangLizhi
 * @version 1.0
 */
public class Text2Drawable extends Drawable {
    private String mText;
    private int mTextWidth;
    private TextPaint mTextPaint;
    private Paint mBackgroundPaint;


    public Text2Drawable(Context context) {
        mTextPaint = new TextPaint();
        mTextPaint.setAntiAlias(true);
        mTextPaint.setTextSize(60);
        mTextPaint.setTextAlign(Paint.Align.CENTER);
        mBackgroundPaint = new Paint();
        mBackgroundPaint.setColor(Color.parseColor("#fff49893"));
    }

    @Override
    public void draw(@NonNull Canvas canvas) {
        int width = getIntrinsicWidth();
        int height = getIntrinsicHeight();
        canvas.drawRect(0, 0, width, height, mBackgroundPaint);

        Paint.FontMetrics fontMetrics = mTextPaint.getFontMetrics();
        float top = fontMetrics.top;//为基线到字体上边框的距离,即上图中的top
        float bottom = fontMetrics.bottom;//为基线到字体下边框的距离,即上图中的bottom

        int baseLineY = (int) (height/2 - top/2 - bottom/2);//基线中间点的y轴计算公式

        canvas.drawText(mText, width / 2, baseLineY, mTextPaint);
    }

    public void setText(String text) {
        mText = text;
        mTextWidth = (int) Layout.getDesiredWidth(mText, mTextPaint);
    }


    @Override
    public void setAlpha(int alpha) {

    }

    @Override
    public void setColorFilter(ColorFilter colorFilter) {

    }

    @Override
    public int getOpacity() {
        return PixelFormat.TRANSPARENT;
    }

    @Override
    public int getIntrinsicWidth() {
        return (int) (mTextWidth * 1.5);
    }

    @Override
    public int getIntrinsicHeight() {
        return (int) (mTextWidth * 1.5);
    }

    /**
     * 设置背景颜色
     *
     * @param color 背景颜色
     */
    public void setBackgroundColor(@ColorInt int color) {
        mBackgroundPaint.setColor(color);
    }

}
