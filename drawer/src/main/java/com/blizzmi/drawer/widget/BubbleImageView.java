package com.blizzmi.drawer.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.ImageView;

import com.blizzmi.drawer.R;


public class BubbleImageView extends ImageView {

    private Drawable bgDrawable;

    public BubbleImageView(Context context) {
        this(context, null);
    }

    public BubbleImageView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BubbleImageView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.Overlap, defStyle, 0);
        int count = a.getIndexCount();
        for (int i = 0; i < count; i++) {
            int attr = a.getIndex(i);
            switch (attr) {

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
        Bitmap srcBitmap = drawable.getBitmap();//源图片

        int width = getMeasuredWidth();
        int height = getMeasuredHeight();

        Paint paint = drawable.getPaint();
        paint.setAntiAlias(true);

        paint.setXfermode(null);
        int sc = canvas.saveLayer(0, 0, width, height
                , paint, Canvas.ALL_SAVE_FLAG);


        bgDrawable.setBounds(0, 0, width, height);
        bgDrawable.draw(canvas);


        paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
        RectF rect = new RectF(0, 0, width, height);
        Rect rectF = new Rect(0, 0, width, height);
        canvas.drawBitmap(srcBitmap, rectF, rect, paint);

        canvas.restoreToCount(sc);
    }


}
