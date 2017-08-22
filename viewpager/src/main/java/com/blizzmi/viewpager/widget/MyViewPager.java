package com.blizzmi.viewpager.widget;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;

/**
 * Date： 2017/8/21
 * Description:
 *
 * @author WangLizhi
 * @version 1.0
 */
public class MyViewPager extends ViewPager {
    private static final String TAG = "MyViewPager";
    private OnScrollProgressListener mOnScrollProgressListener;

    public MyViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyViewPager(Context context) {
        super(context);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        Log.i(TAG, "scrollX:" + getScrollX());
        Log.i(TAG, "view width:" + getWidth());
        return super.onTouchEvent(ev);
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        if (mOnScrollProgressListener != null) {
            float width = getWidth();
            float left = l;
            int page = (int) (left / width);
            if (left % width == 0 && l > oldl) {
                page--;
            }
            mOnScrollProgressListener.onScrollProgress(left / width - page);
        }
    }

    public void setOnScrollProgressListener(OnScrollProgressListener onScrollProgressListener) {
        this.mOnScrollProgressListener = onScrollProgressListener;
    }

    public interface OnScrollProgressListener {
        void onScrollProgress(float progress);
    }
}
