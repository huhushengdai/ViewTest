package com.blizzmi.viewtest.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.Gravity;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.blizzmi.viewtest.R;



/**
 * Date： 2016/9/22
 * Description:
 * 标题头
 *
 * @author WangLizhi
 * @version 1.0
 */
public class TitleLayout extends RelativeLayout {
    //左边view默认属性
    private static final int LEFT_DRAWABLE_PADDING = 5;
    private static final int LEFT_PADDING_LEFT = 12;
    private static final int LEFT_PADDING_TOP = 0;
    private static final int LEFT_PADDING_RIGHT = 12;
    private static final int LEFT_PADDING_BOTTOM = 0;
    private static final int LEFT_PADDING = -1;
    private static final int LEFT_TEXT_SIZE = 16;//默认左边字体大小
    private static final int LEFT_TEXT_COLOR = 0xff008EFF;//左边默认字体颜色
    //右边view默认属性
    private static final int RIGHT_DRAWABLE_PADDING = 5;
    private static final int RIGHT_PADDING_LEFT = 12;
    private static final int RIGHT_PADDING_TOP = 0;
    private static final int RIGHT_PADDING_RIGHT = 12;
    private static final int RIGHT_PADDING_BOTTOM = 0;
    private static final int RIGHT_PADDING = -1;
    private static final int RIGHT_TEXT_SIZE = 16;//默认右边字体大小
    private static final int RIGHT_TEXT_COLOR = 0xff008EFF;//右边默认字体颜色
    //中间view默认属性
    private static final int MID_TEXT_SIZE = 18;//默认中间字体大小
    private static final int MID_PADDING_LEFT = 5;
    private static final int MID_PADDING_TOP = 15;
    private static final int MID_PADDING_RIGHT = 5;
    private static final int MID_PADDING_BOTTOM = 15;
    private static final int MID_PADDING = -1;
    private static final int MID_TEXT_COLOR = 0xff343535;//中间默认字体颜色

    private TextView left;
    private TextView mid;
    private TextView right;

    public TitleLayout(Context context) {
        this(context, null);
    }

    public TitleLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TitleLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        left = new TextView(context);
        mid = new TextView(context);
        right = new TextView(context);
        //左边view属性
        int leftPadding = LEFT_PADDING;
        int leftPaddingLeft = LEFT_PADDING_LEFT;
        int leftPaddingTop = LEFT_PADDING_TOP;
        int leftPaddingRight = LEFT_PADDING_RIGHT;
        int leftPaddingBottom = LEFT_PADDING_BOTTOM;
        int leftDrawablePadding = LEFT_DRAWABLE_PADDING;
        ColorStateList leftTextColor = ColorStateList.valueOf(LEFT_TEXT_COLOR);//左边字体颜色
        float leftTextSize = LEFT_TEXT_SIZE;//左边字体大小
        //右边view属性
        int rightPadding = RIGHT_PADDING;
        int rightPaddingLeft = RIGHT_PADDING_LEFT;
        int rightPaddingTop = RIGHT_PADDING_TOP;
        int rightPaddingRight = RIGHT_PADDING_RIGHT;
        int rightPaddingBottom = RIGHT_PADDING_BOTTOM;
        int rightDrawablePadding = RIGHT_DRAWABLE_PADDING;
        ColorStateList rightTextColor = ColorStateList.valueOf(RIGHT_TEXT_COLOR);//右边字体颜色
        float rightTextSize = RIGHT_TEXT_SIZE;//右边字体大小
        //中边view属性
        int midPadding = MID_PADDING;
        int midPaddingLeft = MID_PADDING_LEFT;
        int midPaddingTop = MID_PADDING_TOP;
        int midPaddingRight = MID_PADDING_RIGHT;
        int midPaddingBottom = MID_PADDING_BOTTOM;
        ColorStateList midTextColor = ColorStateList.valueOf(MID_TEXT_COLOR);
        float midTextSize = MID_TEXT_SIZE;

        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.TitleLayout, defStyleAttr, 0);
        int count = a.getIndexCount();
        for (int i = 0; i < count; i++) {
            int attr = a.getIndex(i);
            switch (attr) {
                //左边view属性
                case R.styleable.TitleLayout_leftDrawable:
                    left.setCompoundDrawablesWithIntrinsicBounds(a.getDrawable(attr), null, null, null);
                    break;
                case R.styleable.TitleLayout_leftDrawablePadding:
                    leftDrawablePadding = a.getDimensionPixelSize(attr, LEFT_DRAWABLE_PADDING);
                    break;
                case R.styleable.TitleLayout_leftPadding:
                    leftPadding = a.getDimensionPixelOffset(attr, LEFT_PADDING);
                    break;
                case R.styleable.TitleLayout_leftPaddingLeft:
                    leftPaddingLeft = a.getDimensionPixelOffset(attr, LEFT_PADDING_LEFT);
                    break;
                case R.styleable.TitleLayout_leftPaddingTop:
                    leftPaddingTop = a.getDimensionPixelOffset(attr, LEFT_PADDING_TOP);
                    break;
                case R.styleable.TitleLayout_leftPaddingRight:
                    leftPaddingRight = a.getDimensionPixelOffset(attr, LEFT_PADDING_RIGHT);
                    break;
                case R.styleable.TitleLayout_leftPaddingBottom:
                    leftPaddingBottom = a.getDimensionPixelOffset(attr, LEFT_PADDING_BOTTOM);
                    break;
                case R.styleable.TitleLayout_leftText:
                    left.setText(a.getString(attr));
                    break;
                case R.styleable.TitleLayout_leftTextColor:
                    leftTextColor = a.getColorStateList(attr);
                    break;
                case R.styleable.TitleLayout_leftTextSize:
                    leftTextSize = a.getDimensionPixelSize(attr, LEFT_TEXT_SIZE);
                    break;

                //右边边view属性
                case R.styleable.TitleLayout_rightDrawable:
                    right.setCompoundDrawablesWithIntrinsicBounds(null, null, a.getDrawable(attr), null);
                    break;
                case R.styleable.TitleLayout_rightDrawablePadding:
                    rightDrawablePadding = a.getDimensionPixelSize(attr, RIGHT_DRAWABLE_PADDING);
                    break;
                case R.styleable.TitleLayout_rightPadding:
                    rightPadding = a.getDimensionPixelOffset(attr, RIGHT_PADDING);
                    break;
                case R.styleable.TitleLayout_rightPaddingLeft:
                    rightPaddingLeft = a.getDimensionPixelOffset(attr, RIGHT_PADDING_LEFT);
                    break;
                case R.styleable.TitleLayout_rightPaddingTop:
                    rightPaddingTop = a.getDimensionPixelOffset(attr, RIGHT_PADDING_TOP);
                    break;
                case R.styleable.TitleLayout_rightPaddingRight:
                    rightPaddingRight = a.getDimensionPixelOffset(attr, RIGHT_PADDING_RIGHT);
                    break;
                case R.styleable.TitleLayout_rightPaddingBottom:
                    rightPaddingBottom = a.getDimensionPixelOffset(attr, RIGHT_PADDING_BOTTOM);
                    break;
                case R.styleable.TitleLayout_rightText:
                    right.setText(a.getString(attr));
                    break;
                case R.styleable.TitleLayout_rightTextColor:
                    rightTextColor = a.getColorStateList(attr);
                    break;
                case R.styleable.TitleLayout_rightTextSize:
                    rightTextSize = a.getDimensionPixelSize(attr, RIGHT_TEXT_SIZE);
                    break;

                //中间view属性
                case R.styleable.TitleLayout_midText:
                    mid.setText(a.getString(attr));
                    break;
                case R.styleable.TitleLayout_midTextSize:
                    midTextSize = a.getDimensionPixelSize(attr, MID_TEXT_SIZE);
                    break;
                case R.styleable.TitleLayout_midTextColor:
                    midTextColor = a.getColorStateList(attr);
                    break;
                case R.styleable.TitleLayout_midPadding:
                    midPadding = a.getDimensionPixelOffset(attr, MID_PADDING);
                    break;
                case R.styleable.TitleLayout_midPaddingLeft:
                    midPaddingLeft = a.getDimensionPixelOffset(attr, MID_PADDING_LEFT);
                    break;
                case R.styleable.TitleLayout_midPaddingTop:
                    midPaddingTop = a.getDimensionPixelOffset(attr, MID_PADDING_TOP);
                    break;
                case R.styleable.TitleLayout_midPaddingRight:
                    midPaddingRight = a.getDimensionPixelOffset(attr, MID_PADDING_RIGHT);
                    break;
                case R.styleable.TitleLayout_midPaddingBottom:
                    midPaddingBottom = a.getDimensionPixelOffset(attr, MID_PADDING_BOTTOM);
                    break;
            }
        }
        a.recycle();
        //左边view  padding
        if (leftPadding == -1) {
            left.setPadding(leftPaddingLeft, leftPaddingTop, leftPaddingRight, leftPaddingBottom);
        } else {
            left.setPadding(leftPadding, leftPadding, leftPadding, leftPadding);
        }
        left.setTextColor(leftTextColor);//设置左边字体颜色
        left.setTextSize(leftTextSize);//设置左边字体大小
        left.setCompoundDrawablePadding(leftDrawablePadding);

        //右边view padding
        if (rightPadding == -1) {
            right.setPadding(rightPaddingLeft, rightPaddingTop, rightPaddingRight, rightPaddingBottom);
        } else {
            right.setPadding(rightPadding, rightPadding, rightPadding, rightPadding);
        }
        right.setTextColor(rightTextColor);//设置右边字体颜色
        right.setTextSize(rightTextSize);//设置右边字体大小
        right.setCompoundDrawablePadding(rightDrawablePadding);

        //中间view padding
        if (midPadding == -1) {
            mid.setPadding(midPaddingLeft, midPaddingTop, midPaddingRight, midPaddingBottom);
        } else {
            mid.setPadding(midPadding, midPadding, midPadding, midPadding);
        }
        mid.setTextColor(midTextColor);//设置中间字体颜色
        mid.setTextSize(midTextSize);//设置中间字体大小

        //加入父布局
        addView(left);
        addView(mid);
        addView(right);

        //左边view布局
        left.setGravity(Gravity.CENTER_VERTICAL);
        LayoutParams leftParams = (LayoutParams) left.getLayoutParams();
//        leftParams.width = -2;
//        leftParams.height = -1;
        left.setLayoutParams(leftParams);
        //中间view布局
        LayoutParams params = (LayoutParams) mid.getLayoutParams();
//        params.width = -2;
//        params.height = -2;
        params.addRule(RelativeLayout.CENTER_IN_PARENT);
        mid.setLayoutParams(params);
        //右边view布局
        right.setGravity(Gravity.CENTER_VERTICAL);
        LayoutParams rightParams = (LayoutParams) right.getLayoutParams();
//        rightParams.width = -2;
//        rightParams.height = -1;
        rightParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        rightParams.addRule(RelativeLayout.CENTER_VERTICAL);
        right.setLayoutParams(rightParams);

    }

    /**
     * 左边view点击事件
     */
    public void setLeftClickListener(OnClickListener listener) {
        left.setOnClickListener(listener);
    }

    /**
     * 设置左边view是否显示
     */
    public void setLeftVisibility(int visibility) {
        left.setVisibility(visibility);
    }

    /**
     * 设置中间view文字
     */
    public void setMidText(String text) {
        mid.setText(text);
    }


    /**
     * 设置右边view点击事件
     */
    public void setRightClickListener(OnClickListener listener) {
        right.setOnClickListener(listener);
    }

    /**
     * 设置右边view是否显示
     */
    public void setRightVisibility(int visibility) {
        right.setVisibility(visibility);
    }
}
