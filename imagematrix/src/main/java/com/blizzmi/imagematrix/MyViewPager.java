package com.blizzmi.imagematrix;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;

/**
 * Date： 2017/2/8
 * Description:
 *
 * @author WangLizhi
 * @version 1.0
 */
public class MyViewPager extends ViewPager {
    private static final String TAG = "MyViewPager";

    private float mPointX;
    private MatrixImageView mMatrixView;
    private boolean isFirstIntercept = true;//是否第一拦截事件
    public MyViewPager(Context context) {
        super(context);
    }

    public MyViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        Log.i(TAG, "onInterceptTouchEvent:" + ev.getAction());

        if (!(getAdapter().instantiateItem(this,getCurrentItem()) instanceof MatrixImageView)) {
            return super.onInterceptTouchEvent(ev);
        }
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            mPointX = ev.getX();
        } else if (ev.getAction() == MotionEvent.ACTION_MOVE) {
            mMatrixView = (MatrixImageView) getAdapter().instantiateItem(this,getCurrentItem());
            float dx = ev.getX() - mPointX;//位移
            if ((mMatrixView.isLeftLimit() && dx > 0) ||(mMatrixView.isRightLimit() && dx < 0)) {
                if (isFirstIntercept){
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
//        return false;
    }
}
