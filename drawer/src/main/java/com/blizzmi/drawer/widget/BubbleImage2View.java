package com.blizzmi.drawer.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
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

        int width = drawable.getIntrinsicWidth();//需要绘制的宽度
        int height = drawable.getIntrinsicHeight();//需要绘制的高度



        Paint paint = drawable.getPaint();
        paint.setAntiAlias(true);

        paint.setXfermode(null);
        int sc = canvas.saveLayer(0, 0, width, height
                , paint, Canvas.ALL_SAVE_FLAG);


        bgDrawable.setBounds(0, 0, width, height);
        bgDrawable.draw(canvas);


        paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
        drawable.draw(canvas);//原文件

        canvas.restoreToCount(sc);
    }

//    @Override
//    public void draw(Canvas canvas) {
////        super.onDraw(canvas);
//        rectF.top=0;
//        /**
//         * 设置View的离屏缓冲。在绘图的时候新建一个“层”，所有的操作都在该层而不会影响该层以外的图像
//         * 必须设置，否则设置的PorterDuffXfermode会无效，具体原因不明
//         */
//        paint.setXfermode(null);
//        int sc=canvas.saveLayer(0,0,totalW,totalH,paint, Canvas.ALL_SAVE_FLAG);
//        canvas.drawBitmap(bitmap,0,0,null);
//        paint.setXfermode(xfermode);
//        paint.setColor(Color.RED);
//        Bitmap src = BitmapFactory.decodeResource(getResources(),R.mipmap.m_bg);
//        canvas.drawBitmap(src,0,0,paint);
//        /**
//         * 还原画布，与canvas.saveLayer配套使用
//         */
//        canvas.restoreToCount(sc);
//    }
}
