package com.huhushengdai.testdrawable;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.ImageView;


/**
 * Date： 2016/7/26
 * Description:
 * 圆形ImageView
 * app:border_color="#0000"
 * app:border_width="1dp"
 *
 * @author WangLizhi
 * @version 1.0
 */
public class CircleImageView extends ImageView {


    private static final Bitmap.Config BITMAP_CONFIG = Bitmap.Config.ARGB_8888;
    private static final int COLORDRAWABLE_DIMENSION = 1;

    private static final int DEFAULT_BORDER_WIDTH = 0;
    private static final int DEFAULT_BORDER_COLOR = Color.WHITE;

    private final Paint mBorderPaint = new Paint();
    private int mBorderColor = DEFAULT_BORDER_COLOR;
    private int mBorderWidth = DEFAULT_BORDER_WIDTH;


    public CircleImageView(Context context) {
        this(context, null);
    }

    public CircleImageView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CircleImageView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        setScaleType(ScaleType.CENTER);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.CircleImageView, defStyle, 0);

        mBorderWidth = a.getDimensionPixelSize(R.styleable.CircleImageView_circle_border_width, DEFAULT_BORDER_WIDTH);
        mBorderColor = a.getColor(R.styleable.CircleImageView_circle_border_color, DEFAULT_BORDER_COLOR);

        a.recycle();
        mBorderPaint.setColor(mBorderColor);
        mBorderPaint.setAntiAlias(true);
    }

    @Override
    public void draw(Canvas canvas) {
        Drawable drawable = getDrawable();
        if (drawable == null || !(drawable instanceof BitmapDrawable)) {
            super.draw(canvas);
            return;
        }
        BitmapDrawable bitmapDrawable = (BitmapDrawable) drawable;

        int radius = getMeasuredWidth() >= getMeasuredHeight() ?
                getMeasuredHeight() / 2 : getMeasuredWidth() / 2;//半径

        //外圆
        canvas.drawCircle(radius, radius, radius, mBorderPaint);
        //内圆
        Paint paint = bitmapDrawable.getPaint();
        paint.setAntiAlias(true);
        paint.setXfermode(null);
        paint.setColor(mBorderColor);
        int sc = canvas.saveLayer(0, 0, radius*2, radius*2
                , paint, Canvas.ALL_SAVE_FLAG);
        canvas.drawCircle(radius, radius, radius - mBorderWidth, paint);//内圆轮廓
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        //图片大小
        int dWidth = drawable.getIntrinsicWidth();
        int dHeight = drawable.getIntrinsicHeight();
        Rect rect = new Rect(0, 0, dWidth, dHeight);//图片大小
        //显示大小
        int showWidth;
        int showHeight;
        if (dWidth > dHeight) {
            showHeight = (radius-mBorderWidth) * 2;
            showWidth = (int) (((double) showHeight / dHeight) * dWidth);
        } else {
            showWidth = (radius-mBorderWidth) * 2;
            showHeight = (int) (((double) showWidth / dWidth) * dHeight);
        }
        RectF rectF = new RectF((radius*2-showWidth)/2
                , (radius*2-showHeight)/2
                , showWidth+(radius*2-showWidth)/2
                , showHeight+(radius*2-showHeight)/2);//画布大小
        canvas.drawBitmap(bitmapDrawable.getBitmap(), rect, rectF, paint);

        canvas.restoreToCount(sc);
    }


    @Override
    public void setImageDrawable(Drawable drawable) {
        if (drawable == null || drawable instanceof BitmapDrawable) {
            super.setImageDrawable(drawable);
        } else {
            BitmapDrawable bitmapDrawable = createBitmapDrawable(drawable);
            super.setImageDrawable(bitmapDrawable);
        }
    }

    private BitmapDrawable createBitmapDrawable(Drawable drawable) {
        if (drawable == null) {
            return null;
        }
        BitmapDrawable bitmapDrawable;
        Bitmap bitmap;
        if (drawable instanceof ColorDrawable) {
            bitmap = Bitmap.createBitmap(COLORDRAWABLE_DIMENSION, COLORDRAWABLE_DIMENSION, BITMAP_CONFIG);
        } else {
            bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), BITMAP_CONFIG);
        }
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        drawable.draw(canvas);
        bitmapDrawable = new BitmapDrawable(getResources(), bitmap);
        return bitmapDrawable;
    }
}