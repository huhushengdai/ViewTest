package com.blizzmi.imagematrix;

import android.content.Context;
import android.graphics.Matrix;
import android.graphics.Point;
import android.graphics.PointF;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

/**
 * Date： 2017/2/7
 * Description:
 * 缩放平移ImageView
 * 1.缩放：指定最大、最小缩放率，图片缩放不能超过（放大）或小于（缩小）该缩放率
 * 如果放大超过最大缩放率，则只显示最大缩放率 放大效果
 * 如果缩小小于最小缩放率，则只显示最小缩放率 缩小效果，同时当手指抬起时，恢复到默认缩放率
 * 2.平移：默认缩放率下不能平移，
 * 只有放大效果可以平移，且限定平移后，图片四边不在屏幕内（上下左右图片四边
 * 不会出现在屏幕四边，最多贴紧屏幕四边，避免出现空白现象）
 * 3.默认缩放率：此缩放率不是1，而是指图片放大或缩小到刚好匹配屏幕宽度的缩放率
 * <p>
 * 注意：
 * 1.最好在全屏模式（没有标题栏、状态栏）下使用，因为没有计算状态栏高度和标题栏高度，显示会不居中
 *
 * @author WangLizhi
 * @version 1.0
 */
public class MatrixImageView extends ImageView {
    private static final String TAG = "MatrixImageView";
    private static final float MIN_RATIO = 0.8f;//最小能缩小到屏幕宽
    private static final float MAX_RATIO = 3.0f;//最大能放大到屏幕宽
    /**
     * 极限放大率
     * 如果放大超过这个比率（最大比率是 MAX_RATIO）
     * 则手指抬起时，恢复到极限放大率
     */
    private static final float LIMIT_RATIO = 2.5f;

    private Drawable mDrawable;
    private MatrixTouchListener mListener;//手势监听
    private boolean isInitData;//是否初始化过相关数据
    private OnClickListener mClickListener;//点击监听
    /**
     * 修改之后矩阵中的值
     */
    private float[] mValues = new float[9];

    //    private Matrix mMatrix;
    private float mDefaultScale = 1;//默认缩放率
    private float mMinScale = 1;//最小缩小率
    private float mMaxScale = 1;//最大放大倍率
    private float mLimitScale = 1;//极限放大率（比 mMaxScale 小）
    private float mDrawableWidth;//图片真实宽度
    private float mDrawableHeight;//图片真实高度
    private float mScreenWidth = 1080;
    private float mScreenHeight = 1920;


    public MatrixImageView(Context context) {
        this(context, null);
    }

    public MatrixImageView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MatrixImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        setScaleType(ScaleType.MATRIX);//缩放、平移、旋转等效果必须使用matrix类型
        initMatrix();
    }

    @Override
    public void setImageDrawable(Drawable drawable) {
        super.setImageDrawable(drawable);
        initMatrix();
    }

    @Override
    public void setImageResource(int resId) {
        super.setImageResource(resId);
        initMatrix();
    }

    public void initMatrix() {
        mDrawable = getDrawable();
        if (mDrawable == null) {
            return;
        }
        if (!isInitData) {
            initData();
            isInitData = true;
        }
        //设置图片后，重新整理图片，使图片宽度缩放到刚好符合屏幕宽度
        mDrawableWidth = mDrawable.getIntrinsicWidth();
        mDrawableHeight = mDrawable.getIntrinsicHeight();

        mDefaultScale = mScreenWidth / mDrawableWidth;//默认匹配屏幕的缩放率
        mMinScale = mDefaultScale * MIN_RATIO;
        mMaxScale = mDefaultScale * MAX_RATIO;
        mLimitScale = mDefaultScale * LIMIT_RATIO;


        Matrix matrix = new Matrix();
        matrix.postScale(mDefaultScale, mDefaultScale);
        //y 位置修正
        float correctY = 0;
        if ((mDrawableHeight * mDefaultScale - mScreenHeight) < 0) {
            correctY = (mScreenHeight - mDrawableHeight * mDefaultScale) / 2f;
        }
        matrix.postTranslate(0, correctY);

        setImageMatrix(matrix);
        if (mListener == null) {
            mListener = new MatrixTouchListener();
            super.setOnTouchListener(mListener);//根据监听到手势，对图片进行缩放、平移
        }
        mListener.setMatrix(matrix);
    }

    @Override
    public void setOnClickListener(OnClickListener l) {
        super.setOnClickListener(l);
        mClickListener = l;
    }

    /**
     * 初始化数据
     * 获取屏幕宽高
     * 图片真实宽高
     * 默认缩放比率
     * 最大最小缩放比率
     */
    private void initData() {
        //获取屏幕宽高
        WindowManager wm = (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);
        Point outSize = new Point();
        wm.getDefaultDisplay().getSize(outSize);
        mScreenWidth = outSize.x;
        mScreenHeight = outSize.y;
    }

    @Override
    public void setOnTouchListener(OnTouchListener l) {
        super.setOnTouchListener(l);
        throw new RuntimeException("缩放、平移 需要手势监听，所以不额外接收监听");
    }

    /**
     * 图片左边是否贴紧屏幕左边
     */
    public boolean isLeftLimit() {
        getImageMatrix().getValues(mValues);
        float currentX = mValues[2];//按下时图片左边坐标
        return currentX == 0;
    }

    /**
     * 图片右边是否贴紧屏幕右边
     */
    public boolean isRightLimit() {
        getImageMatrix().getValues(mValues);
        float currentX = mValues[2];//按下时图片左边坐标
        float scaleWidth = mValues[0] * mDrawableWidth;//按下时被缩放后图片的宽度
        return currentX == mScreenWidth - scaleWidth;
    }

    /**
     * ImageView手势监听，根据手势进行缩放，平移
     */
    private final class MatrixTouchListener implements OnTouchListener {

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
        private Matrix matrix;
        /**
         * 用于记录图片要进行拖拉时候的坐标位置
         */
        private Matrix currentMatrix = new Matrix();
        /**
         * 当前矩阵中的值
         */
        private float[] mCurrentValues = new float[9];

        /**
         * 两个手指的开始距离
         */
        private float startDis;
        /**
         * 两个手指的中间点
         */
        private PointF midPoint;

        public void setMatrix(Matrix matrix) {
            this.matrix = new Matrix(matrix);
        }

        @Override
        public boolean onTouch(View v, MotionEvent event) {
            Log.i(TAG, "listener onTouchEvent:" + event.getAction());
            if (mDrawable == null) {
                //如果没有图片，则不处理事件监听
                return false;
            }
            /** 通过与运算保留最后八位 MotionEvent.ACTION_MASK = 255 */
            switch (event.getAction() & MotionEvent.ACTION_MASK) {
                // 手指压下屏幕
                case MotionEvent.ACTION_DOWN:
                    mode = MODE_DRAG;
                    // 记录ImageView当前的移动位置
                    currentMatrix.set(getImageMatrix());
                    currentMatrix.getValues(mCurrentValues);
                    startPoint.set(event.getX(), event.getY());
                    break;
                // 手指在屏幕上移动，改事件会被不断触发
                case MotionEvent.ACTION_MOVE:
                    // 拖拉图片
                    if (mode == MODE_DRAG) {

                        //限制x 移动范围
                        float dx = event.getX() - startPoint.x; // 得到x轴的移动距离
                        float currentX = mCurrentValues[2];//按下时图片左边坐标
                        float scaleWidth = mCurrentValues[0] * mDrawableWidth;//按下时被缩放后图片的宽度
                        //右移到边界时，继续右移，则不处理该事件
                        //或者 左移到边界，继续左移，同样不处理
                        if ((currentX == 0 && dx > 0) || (currentX == mScreenWidth - scaleWidth && dx < 0)) {
                            return false;
                        }
                        if (currentX + dx > 0) {
                            //如果向右平移时，图片左边超出，出现这屏幕中
                            //则修改平移量，使得图片左边刚好移动到屏幕左边
                            dx = -currentX;
                        } else if (currentX + dx < -(scaleWidth - mScreenWidth)) {
                            //左移限制，跟右移类似
                            dx = -currentX - (scaleWidth - mScreenWidth);
                        }
                        //限制y 移动范围
                        float dy = event.getY() - startPoint.y; // 得到y轴的移动距离
                        float currentY = mCurrentValues[5];//图片上边坐标
                        float scaleHeight = mCurrentValues[4] * mDrawableHeight;//图片缩放后的高
                        if (scaleHeight < mScreenHeight) {
                            dy = 0;
                        } else if (currentY + dy > 0) {
                            dy = -currentY;
                        } else if (currentY + dy < -(scaleHeight - mScreenHeight)) {
                            dy = -currentY - (scaleHeight - mScreenHeight);
                        }
                        // 在没有移动之前的位置上进行移动
                        matrix.set(currentMatrix);
                        matrix.postTranslate(dx, dy);
                    }
                    // 放大缩小图片
                    else if (mode == MODE_ZOOM) {
                        float endDis = distance(event);// 结束距离
                        if (endDis > 10f) { // 两个手指并拢在一起的时候像素大于10
                            float scale = endDis / startDis;// 得到缩放倍数
                            float currentScale = mCurrentValues[0];

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
                    //限定缩放比率
                    matrix.getValues(mValues);
                    float currentScale = mValues[0];
                    if (currentScale < mDefaultScale) {
                        //如果是缩小，则还原到符合屏幕宽度状态
                        matrix.postScale(mDefaultScale / currentScale, mDefaultScale / currentScale, midPoint.x, midPoint.y);
                    } else if (currentScale > mLimitScale) {
                        //如果放大超过极限放大率，则还原到极限放大率
                        matrix.postScale(mLimitScale / currentScale, mLimitScale / currentScale, midPoint.x, midPoint.y);
                    }
                    //修正平移
                    matrix.getValues(mValues);
                    float correctX = 0;//修正平移x
                    float correctY = 0;//修正平移y
                    float scaleX = mValues[0];
                    float scaleY = mValues[4];
                    float x = mValues[2];
                    float y = mValues[5];
                    if (x > 0) {
                        correctX = -x;
                    } else if (x < -(mDrawableWidth * scaleX - mScreenWidth)) {
                        correctX = -x - (mDrawableWidth * scaleX - mScreenWidth);
                    }
                    if ((mDrawableHeight * scaleY - mScreenHeight) < 0) {
                        //如果放大后的图片高度小于屏幕高度
                        //则把图片居中
                        correctY = -y + (mScreenHeight - mDrawableHeight * scaleY) / 2f;
                    } else if (y > 0) {
                        correctY = -y;
                    } else if (y < -(mDrawableHeight * scaleY - mScreenHeight)) {
                        correctY = -y - (mDrawableHeight * scaleY - mScreenHeight);
                    }
                    matrix.postTranslate(correctX, correctY);
                    //判断是否为点击事件
                    if (startPoint.x == event.getX() || startPoint.y == event.getY() && mClickListener != null) {
                        mClickListener.onClick(MatrixImageView.this);
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
                        currentMatrix.set(getImageMatrix());
                    }
                    break;
            }
            setImageMatrix(matrix);
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
