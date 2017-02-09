package com.blizzmi.imagematrix;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * Date： 2017/1/16
 * Description:
 *
 * @author WangLizhi
 * @version 1.0
 */
public class TransformMatrixView extends ImageView {
    private Bitmap bitmap;
    private Matrix matrix;

    public TransformMatrixView(Context context, AttributeSet attrs) {
        super(context, attrs);
        bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_head);
        matrix = new Matrix();
    }

    public TransformMatrixView(Context context) {
        super(context);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        // 画出原图像
        canvas.drawBitmap(bitmap, 0, 0, null);
        // 画出变换后的图像
        canvas.drawBitmap(bitmap, matrix, null);
        super.onDraw(canvas);
    }

    @Override
    public void setImageMatrix(Matrix matrix) {
        this.matrix.set(matrix);
        super.setImageMatrix(matrix);
    }

    public Bitmap getImageBitmap() {
        return bitmap;
    }
}