package com.blizzmi.imagematrix;

import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.PointF;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;

import java.util.ArrayList;

public class ThirdActivity extends AppCompatActivity {
    private static final String TAG = "ThirdActivity";

    private ImageView imageView;
    Matrix matrix;
    float mScale;
    float mMinScale;//最小缩小率
    float mMaxScale;//最大放大倍率
    float mWidth;
    float mScreenWidth = 1080;

    private int currentItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //隐藏状态栏
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_third);

        ViewPager pager = (ViewPager) findViewById(R.id.third_pager);
        final ArrayList<View> views = new ArrayList<>(5);
        for (int i = 0; i < 5; i++) {
            MatrixImageView img = new MatrixImageView(this);
            img.setImageResource(R.mipmap.ic_head);
            img.setBackgroundColor(Color.GREEN);
            views.add(img);
        }
        PagerAdapter adapter = new PagerAdapter() {
            @Override
            public int getCount() {
                return 5;
            }

            @Override
            public boolean isViewFromObject(View view, Object object) {
                return view == object;
            }

            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                View view = views.get(position);
                if (view.getParent() == null) {
                    container.addView(view);
                }
                return view;
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                container.removeView(views.get(position));
            }
        };
        pager.setAdapter(adapter);
        pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                if (position != currentItem) {
                    MatrixImageView view = (MatrixImageView) views.get(currentItem);
                    view.initMatrix();
                    currentItem = position;
                }
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        currentItem = pager.getCurrentItem();
//        imageView = (ImageView) this.findViewById(R.id.imageView);
//        mWidth = imageView.getDrawable().getIntrinsicWidth();
//        mScale = mScreenWidth / mWidth;
//        mMinScale = (float) (mScale * 0.8);
//        mMaxScale = (float) (mScale * 2.0);
//        matrix = imageView.getImageMatrix();
//        matrix.postScale(mScale, mScale);
//        imageView.setImageMatrix(matrix);
//        imageView.setOnTouchListener(new TouchListener());

    }

    private final class TouchListener implements View.OnTouchListener {

        /**
         * 记录是拖拉照片模式还是放大缩小照片模式
         */
        private int mode = 0;// 初始状态
        /**
         * 拖拉照片模式
         */
        private static final int MODE_DRAG = 1;
        /**
         * 放大缩小照片模式
         */
        private static final int MODE_ZOOM = 2;

        /**
         * 用于记录开始时候的坐标位置
         */
        private PointF startPoint = new PointF();
        /**
         * 用于记录拖拉图片移动的坐标位置
         */
//        private Matrix matrix = new Matrix();
        private Matrix matrix = new Matrix(ThirdActivity.this.matrix);
        /**
         * 用于记录图片要进行拖拉时候的坐标位置
         */
        private Matrix currentMatrix = new Matrix();

        /**
         * 两个手指的开始距离
         */
        private float startDis;
        /**
         * 两个手指的中间点
         */
        private PointF midPoint;

        @Override
        public boolean onTouch(View v, MotionEvent event) {
            /** 通过与运算保留最后八位 MotionEvent.ACTION_MASK = 255 */
            switch (event.getAction() & MotionEvent.ACTION_MASK) {
                // 手指压下屏幕
                case MotionEvent.ACTION_DOWN:
                    mode = MODE_DRAG;
                    // 记录ImageView当前的移动位置
                    currentMatrix.set(imageView.getImageMatrix());
                    startPoint.set(event.getX(), event.getY());
                    break;
                // 手指在屏幕上移动，改事件会被不断触发
                case MotionEvent.ACTION_MOVE:
                    // 拖拉图片
                    if (mode == MODE_DRAG) {
                        float dx = event.getX() - startPoint.x; // 得到x轴的移动距离
                        //
                        float[] values = new float[9];
                        currentMatrix.getValues(values);
                        float imgX = values[2];
                        float scaleX = values[0] * mWidth;
                        Log.i(TAG, "当前的imgX:" + imgX);
                        if (imgX + dx > 0) {
                            dx = -imgX;
                        } else if (imgX + dx < -(scaleX - mScreenWidth)) {
                            dx = -imgX - (scaleX - mScreenWidth);
                        }
                        //
                        float dy = event.getY() - startPoint.y; // 得到y轴的移动距离
                        Log.i(TAG, "x轴的移动距离:" + dx + ",y轴的移动距离:" + dy);
                        // 在没有移动之前的位置上进行移动
                        matrix.set(currentMatrix);
                        matrix.postTranslate(dx, dy);
                    }
                    // 放大缩小图片
                    else if (mode == MODE_ZOOM) {
                        float endDis = distance(event);// 结束距离
                        if (endDis > 10f) { // 两个手指并拢在一起的时候像素大于10
                            float scale = endDis / startDis;// 得到缩放倍数
                            float[] values = new float[9];
                            currentMatrix.getValues(values);
                            float currentScale = values[0];
                            if (scale * currentScale > mMaxScale) {
                                scale = mMaxScale / currentScale;
                            } else if (scale * currentScale < mMinScale) {
                                scale = mMinScale / currentScale;
                            }
                            matrix.set(currentMatrix);
                            matrix.postScale(scale, scale, midPoint.x, midPoint.y);
                        }
                    }
                    break;
                // 手指离开屏幕
                case MotionEvent.ACTION_UP:
                    // 当触点离开屏幕，但是屏幕上还有触点(手指)
                case MotionEvent.ACTION_POINTER_UP:
                    mode = 0;
                    float[] values = new float[9];
                    matrix.getValues(values);
                    float currentScale = values[0];
                    if (currentScale < mScale) {
//                        matrix.set(currentMatrix);
                        matrix.postScale(mScale / currentScale, mScale / currentScale, midPoint.x, midPoint.y);
                    }
                    break;
                // 当屏幕上已经有触点(手指)，再有一个触点压下屏幕
                case MotionEvent.ACTION_POINTER_DOWN:
                    mode = MODE_ZOOM;
                    /** 计算两个手指间的距离 */
                    startDis = distance(event);
                    /** 计算两个手指间的中间点 */
                    if (startDis > 10f) { // 两个手指并拢在一起的时候像素大于10
                        midPoint = mid(event);
                        //记录当前ImageView的缩放倍数
                        currentMatrix.set(imageView.getImageMatrix());
                    }
                    break;
            }
            imageView.setImageMatrix(matrix);
            return true;
        }

        /**
         * 计算两个手指间的距离
         */
        private float distance(MotionEvent event) {
            float dx = event.getX(1) - event.getX(0);
            float dy = event.getY(1) - event.getY(0);
            /** 使用勾股定理返回两点之间的距离 */
            return (float) Math.sqrt(dx * dx + dy * dy);
        }

        /**
         * 计算两个手指间的中间点
         */
        private PointF mid(MotionEvent event) {
            float midX = (event.getX(1) + event.getX(0)) / 2;
            float midY = (event.getY(1) + event.getY(0)) / 2;
            return new PointF(midX, midY);
        }

    }
}
