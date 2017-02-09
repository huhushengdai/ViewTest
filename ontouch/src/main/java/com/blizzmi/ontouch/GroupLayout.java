package com.blizzmi.ontouch;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.LinearLayout;

import static android.R.attr.action;

/**
 * Date： 2016/12/9
 * Description:
 *
 * @author WangLizhi
 * @version 1.0
 */
public class GroupLayout extends LinearLayout{
    private static final String TAG = "GroupLayout";

    public GroupLayout(Context context) {
        super(context);
    }

    public GroupLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public GroupLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {

        Log.e(TAG,"dispatchTouchEvent"+"-----"+getAction(ev));
//        return super.dispatchTouchEvent(ev);
//        return true;
        return false;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        Log.e(TAG,"onInterceptTouchEvent"+"-----"+getAction(ev));
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        Log.e(TAG,"onTouchEvent"+"-----"+getAction(ev));
        return super.onTouchEvent(ev);
//        return true;
//        return false;
    }

    public String getAction(MotionEvent ev){
        String action = "action";
        switch (ev.getAction()){
            case MotionEvent.ACTION_UP:
                action = "up";
                break;
            case MotionEvent.ACTION_DOWN:
                action = "down";
                break;
            case MotionEvent.ACTION_MOVE:
                action = "ACTION_MOVE";
                break;
        }
        return action;
    }
}
