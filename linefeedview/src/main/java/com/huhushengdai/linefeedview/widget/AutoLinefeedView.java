package com.huhushengdai.linefeedview.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

/**
 * Date： 2017/4/10
 * Description:
 * 自动换行view
 * 当一行子view排满后，自动由下一行开始
 *
 * @author WangLizhi
 * @version 1.0
 */
public class AutoLinefeedView extends ViewGroup {
    private int mHorizontalSpacing = 16;//列间距
    private int mVerticalSpacing = 16;//行间距

    public AutoLinefeedView(Context context) {
        super(context);
    }

    public AutoLinefeedView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public AutoLinefeedView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int childCount = getChildCount();
        if (childCount == 0) {
            return;
        }
        int width = getMeasuredWidth();//view宽度
        int proX = 0;//上一个子view的x坐标
        int proY = 0;//上一个子view的y坐标
        for (int i = 0; i < childCount; i++) {
            View view = getChildAt(i);
            int childWidth = view.getMeasuredWidth();
            int childHeight = view.getMeasuredHeight();
            if (proX + childWidth + mVerticalSpacing > width) {
                proX = 0;
                proY += childHeight + mHorizontalSpacing;
            }
            view.layout(proX, proY, proX + childWidth, proY + childHeight);
            proX += childWidth + mVerticalSpacing;
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int childCount = getChildCount();
        if (childCount == 0) {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
            return;
        }
        int width = MeasureSpec.getSize(widthMeasureSpec);//view宽度
        int height = 0;//view高度度
        int proX = 0;//上一个子view的x坐标
        for (int i = 0; i < childCount; i++) {
            View view = getChildAt(i);
            view.measure(MeasureSpec.UNSPECIFIED, MeasureSpec.UNSPECIFIED);
            int childWidth = view.getMeasuredWidth();
            int childHeight = view.getMeasuredHeight();
            if (proX + childWidth + mVerticalSpacing > width) {
                proX = 0;
                height += mHorizontalSpacing;
            }
            if (proX == 0) {
                height += childHeight;
            }
            proX += childWidth + mVerticalSpacing;
        }
        setMeasuredDimension(width, height);
    }
}
