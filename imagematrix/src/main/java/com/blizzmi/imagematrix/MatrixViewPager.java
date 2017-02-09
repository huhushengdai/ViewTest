package com.blizzmi.imagematrix;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Date： 2017/2/8
 * Description:
 * 子view是MatrixImageView的ViewPager
 * 重写事件拦截，避免与MatrixImageView焦点事件冲突
 * @author WangLizhi
 * @version 1.0
 */
public class MatrixViewPager extends ViewPager {

    private float mPointX;
    private MatrixImageView mMatrixView;
    private boolean isFirstIntercept = true;//是否第一拦截事件
    public MatrixViewPager(Context context) {
        super(context);
    }

    public MatrixViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        //如果子view不是MatrixImageView，则直接调用ViewPager的事件拦截方法
        if (!(getAdapter().instantiateItem(this,getCurrentItem()) instanceof MatrixImageView)) {
            return super.onInterceptTouchEvent(ev);
        }

        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            //记录下点击的点
            mPointX = ev.getX();
        } else if (ev.getAction() == MotionEvent.ACTION_MOVE) {
            //当前ViewPager中显示的MatrixImageView
            mMatrixView = (MatrixImageView) getAdapter().instantiateItem(this,getCurrentItem());
            float dx = ev.getX() - mPointX;//位移
            //如果MatrixImageView 中图片的左边已经贴紧屏幕左边，而继续右滑
            //则 ViewPager拦截该事件（左滑同理）
            //并且调用父类的方法（不能只返回true，因为在父类事件拦截方法中有处理ViewPager滑动事件）
            //返回false，表示ViewPager不处理该事件，由子view处理
            if ((mMatrixView.isLeftLimit() && dx > 0) ||(mMatrixView.isRightLimit() && dx < 0)) {
                if (isFirstIntercept){
                    //此判断处理ViewPager滑动跳跃问题
                    //不具体描叙，想看效果把这段判断注释即可
                    isFirstIntercept = false;
                    ev.setAction(MotionEvent.ACTION_DOWN);
                }
                return super.onInterceptTouchEvent(ev);
            }else {
                return false;
            }
        }else if (ev.getAction() == MotionEvent.ACTION_UP){
            isFirstIntercept = true;
        }

        return super.onInterceptTouchEvent(ev);
    }
}
