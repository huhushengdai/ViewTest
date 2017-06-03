package com.blizzmi.drawer.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.ImageView;

import com.blizzmi.drawer.R;


public class BubbleImage2View extends ImageView {

    private int maxWidth = -1;
    private int minWidth = -1;
    private int expectedWidth = -1;
    private Drawable bgDrawable;

    public BubbleImage2View(Context context) {
        this(context, null);
    }

    public BubbleImage2View(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BubbleImage2View(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.Overlap, defStyle, 0);
        int count = a.getIndexCount();
        for (int i = 0; i < count; i++) {
            int attr = a.getIndex(i);
            switch (attr) {
                case R.styleable.Overlap_maxWidth:
                    maxWidth = a.getInteger(attr, -1);
                    break;
                case R.styleable.Overlap_expectedWidth:
                    expectedWidth = a.getInteger(attr, -1);
                    break;
                case R.styleable.Overlap_minWidth:
                    minWidth = a.getInteger(attr, -1);
                    break;
                case R.styleable.Overlap_dstBg:
                    bgDrawable = a.getDrawable(attr);
                    break;
            }
        }
        a.recycle();
    }


    @Override//图片缩放还未处理
    public void draw(Canvas canvas) {
        if (getDrawable() == null) {
            return;
        }
        if (!(getDrawable() instanceof BitmapDrawable) || bgDrawable == null) {
            super.draw(canvas);
            return;
        }
        BitmapDrawable drawable = (BitmapDrawable) getDrawable();

        int width = drawable.getIntrinsicWidth();
        int height = drawable.getIntrinsicHeight();

        Paint paint = drawable.getPaint();
        paint.setAntiAlias(true);


        int sc = canvas.saveLayer(0, 0, width, height
                , paint, Canvas.ALL_SAVE_FLAG);


//        patch.draw(canvas, rect);//目标
        bgDrawable.setBounds(0, 0, width, height);
        bgDrawable.draw(canvas);


        paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
        drawable.draw(canvas);//原文件

        canvas.restoreToCount(sc);
    }

    /**
     * 设置图片期望的宽度(必须在setImageBitmap 方法调用前才有效)
     * 如果有 最大宽 限制，当 期望宽 大于 最大宽 时，期望宽 为 最大宽
     * 如果有 最小宽 限制，当 期望宽 小于 最小宽 时，期望宽 为 最小宽
     *
     * @param expectedWidth 图片期望宽度
     */
    public void setExpectedWidth(int expectedWidth) {
        if (maxWidth != -1 && expectedWidth > maxWidth) {
            this.expectedWidth = maxWidth;
        } else if (minWidth != -1 && expectedWidth < minWidth) {
            this.expectedWidth = minWidth;
        } else {
            this.expectedWidth = expectedWidth;
        }
    }

    @Override
    public void setImageBitmap(Bitmap bm) {
        if (expectedWidth != -1 && bm.getWidth() != expectedWidth) {
            int width = bm.getWidth();
            int height = bm.getHeight();
            float scale = (float) (expectedWidth * 1.0 / width);

            Matrix matrix = new Matrix();
            matrix.postScale(scale, scale);
            Bitmap newBm = Bitmap.createBitmap(bm, 0, 0, width, height, matrix, false);
            if (!bm.isRecycled()) {
                bm.recycle();
            }
            super.setImageBitmap(newBm);
        } else {
            super.setImageBitmap(bm);
        }
    }
}
