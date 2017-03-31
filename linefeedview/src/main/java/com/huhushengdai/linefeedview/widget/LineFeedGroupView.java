package com.huhushengdai.linefeedview.widget;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

/**
 * Date： 2017/3/30
 * Description:
 * 自动换行view
 * 只实现基本9宫格功能，并未深入优化
 * 默认为wrap_content
 *
 * @author WangLizhi
 * @version 1.0
 */
public class LineFeedGroupView extends ViewGroup {

    private int mHorizontalSpacing = 16;//列间距
    private int mNumColumns = 3;//行数
    private int mVerticalSpacing = 16;//行间距

    private BaseAdapter mAdapter;

    public LineFeedGroupView(Context context) {
        this(context, null);
    }

    public LineFeedGroupView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LineFeedGroupView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setAdapter(@NonNull BaseAdapter adapter) {
        if (getChildCount() > 0) {
            return;
        }
        mAdapter = adapter;
        int count = mAdapter.getCount();
        for (int i = 0; i < count; i++) {
            addView(adapter.getView(i, null, null));
        }
        requestLayout();
        invalidate();
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        //因为只使用九宫格，不会超出屏幕宽度，所以没有计算屏幕宽高和是否超出屏幕
        int childCount = getChildCount();
        View child;//子view
        int width = 0;//子view 的宽
        int height = 0;//子view 的高
        int left = 0;//子view 左边的坐标（相对父布局）
        int top = 0;//子view 上边的坐标（相对父布局）
        int right = 0;//子view 右边的坐标（相对父布局）
        int bottom = 0;//子view 下边的坐标（相对父布局）
        int row = -1;//行数
        int hSpacing = 0;//行间距
        for (int i = 0; i < childCount; i++) {
            child = getChildAt(i);
            width = child.getMeasuredWidth();
            height = child.getMeasuredHeight();
            if (i % mNumColumns == 0) {
                row++;
                top = (height + mVerticalSpacing) * row;
                left = 0;
            }
            if ((i + 1) % mNumColumns == 0) {
                hSpacing = 0;
            } else {
                hSpacing = mHorizontalSpacing;
            }
            right = left + width + hSpacing;
            bottom = top + height;
            child.layout(left, top, right, bottom);
            left = right;
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        int childCount = getChildCount();
        View child = null;
        for (int i = 0; i < childCount; i++) {
            child = getChildAt(i);
            child.measure(MeasureSpec.UNSPECIFIED, MeasureSpec.UNSPECIFIED);
        }
        if (child == null) {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
            return;
        }
        // 设置容器所需的宽度和高度
        int childWidth = child.getMeasuredWidth();
        int childHeight = child.getMeasuredHeight();
        int width = childWidth * mNumColumns + mHorizontalSpacing * (mNumColumns - 1);
        int row = childCount / mNumColumns;
        if (childCount % mNumColumns != 0) {
            row++;
        }
        int height;
        if (row > 1) {
            height = childHeight * row + mVerticalSpacing * (row - 1);
        } else {
            height = childHeight * row;
        }
        setMeasuredDimension(width, height);
    }
}
