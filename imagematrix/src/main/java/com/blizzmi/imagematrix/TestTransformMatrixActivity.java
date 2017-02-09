package com.blizzmi.imagematrix;

/**
 * Date： 2017/1/19
 * Description:
 *
 * @author WangLizhi
 * @version 1.0
 */

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

public class TestTransformMatrixActivity extends Activity
        implements
        OnTouchListener {
    private TransformMatrixView view;
    private static final String TAG = "MatrixActivity";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        view = new TransformMatrixView(this);
        view.setScaleType(ImageView.ScaleType.MATRIX);
        view.setOnTouchListener(this);

        setContentView(view);
    }

    class TransformMatrixView extends ImageView {
        private Bitmap bitmap;
        private Matrix matrix;

        public TransformMatrixView(Context context) {
            super(context);
            setBackgroundColor(Color.GREEN);
            bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_head);
            matrix = new Matrix();
        }

        @Override
        protected void onDraw(Canvas canvas) {
            // 画出原图像
//            canvas.drawBitmap(bitmap, 0, 0, null);
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

    public boolean onTouch(View v, MotionEvent e) {
        int count = e.getPointerCount();
        switch (count) {
            case 1:
//                Log.i(TAG,"1 count");
                onePoint(v, e);
                break;
            case 2:
//                Log.i(TAG,"2 count");
                twoPoint(v, e);
                break;
        }
        return true;
    }

    public void onePoint(View v, MotionEvent e) {
        Matrix matrix = view.getMatrix();
        if (e.getAction() == MotionEvent.ACTION_MOVE) {
            // 输出图像的宽度和高度(162 x 251)
//            matrix.postScale(2f, 2f);
            matrix.postScale(be, be);
//            view.setImageMatrix(matrix);
//            // 1. 平移
            matrix.postTranslate(e.getX() - view.getImageBitmap().getWidth()*be / 2
                    , e.getY() - view.getImageBitmap().getHeight()*be / 2);
            view.setImageMatrix(matrix);
            view.invalidate();
        }
    }

    float mArea;
    float x1,x2;
    float be = 1;
    public void twoPoint(View v, MotionEvent e) {
        if (e.getAction() == MotionEvent.ACTION_MOVE) {
            if (x1==0&&x2==0){
                x1 = e.getX(0);
                x2 = e.getX(1);
                return;
            }
            float n1 = e.getX(0);
            float n2 = e.getX(1);
            be =  Math.abs(n1-n2)/Math.abs(x1-x2);
            Matrix matrix = view.getMatrix();
            matrix.postScale(be, be);
            view.setImageMatrix(matrix);
            view.invalidate();
        }
    }

    public float getArea(float x0, float y0, float x1, float y1) {
        return Math.abs(x0 - x1) * Math.abs(y0 - y1);
    }
}