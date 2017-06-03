package com.blizzmi.drawer.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.ImageView;

import com.blizzmi.drawer.R;


public class BubbleImageView extends ImageView {

    private int mMaxWidth = -1;
    private int mMinWidth = -1;
    private int mExpectedWidth = -1;
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
                case R.styleable.Overlap_maxWidth:
                    mMaxWidth = a.getInteger(attr, -1);
                    break;
                case R.styleable.Overlap_expectedWidth:
                    int expectedWidth = a.getInteger(attr, -1);
                    if (expectedWidth != -1) {
                        setExpectedWidth(expectedWidth);
                    }
                    break;
                case R.styleable.Overlap_minWidth:
                    mMinWidth = a.getInteger(attr, -1);
                    break;
                case R.styleable.Overlap_dstBg:
                    bgDrawable = a.getDrawable(attr);
                    break;
            }
        }
        a.recycle();
    }

    /**
     * 设置图片期望的宽度(必须在setImageBitmap 方法调用前才有效)
     * 如果有 最大宽 限制，当 期望宽 大于 最大宽 时，期望宽 为 最大宽
     * 如果有 最小宽 限制，当 期望宽 小于 最小宽 时，期望宽 为 最小宽
     *
     * @param expectedWidth 图片期望宽度
     */
    public void setExpectedWidth(int expectedWidth) {
        if (mMaxWidth != -1 && expectedWidth > mMaxWidth) {
            this.mExpectedWidth = mMaxWidth;
        } else if (mMinWidth != -1 && expectedWidth < mMinWidth) {
            this.mExpectedWidth = mMinWidth;
        } else {
            this.mExpectedWidth = expectedWidth;
        }
    }

    @Override
    public void setImageBitmap(Bitmap bm) {
        super.setImageBitmap(getRoundCornerImage(bm));
    }

    public Bitmap getRoundCornerImage(Bitmap bitmap_in) {
        if (bgDrawable == null) {
            return bitmap_in;
        }
        int width = bitmap_in.getWidth();
        int height = bitmap_in.getHeight();
        if (mExpectedWidth != -1 && width != mExpectedWidth) {
            height = (int) (mExpectedWidth * 1.00 / width * height);
            width = mExpectedWidth;
        }

        Bitmap roundConcerImage = Bitmap.createBitmap(width, height,
                Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(roundConcerImage);
        Paint paint = new Paint();
        Rect rect = new Rect(0, 0, width, height);
        Rect rectF = new Rect(0, 0, bitmap_in.getWidth(), bitmap_in.getHeight());
        paint.setAntiAlias(true);

        bgDrawable.setBounds(0, 0, width, height);
        bgDrawable.draw(canvas);

        paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
        canvas.drawBitmap(bitmap_in, rectF, rect, paint);
        bitmap_in.recycle();

        return roundConcerImage;
    }
}
