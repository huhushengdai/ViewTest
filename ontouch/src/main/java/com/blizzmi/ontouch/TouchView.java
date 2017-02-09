package com.blizzmi.ontouch;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.TextView;

/**
 * Date： 2016/12/9
 * Description:
 *
 * @author WangLizhi
 * @version 1.0
 */
public class TouchView extends TextView {
    private static final String TAG = "TouchView";

    public TouchView(Context context) {
        super(context);
    }

    public TouchView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TouchView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        Log.e(TAG, "dispatchTouchEvent"+"-----"+getAction(ev));
        return super.dispatchTouchEvent(ev);
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.e(TAG, "onTouchEvent"+"-----"+getAction(event));
        return super.onTouchEvent(event);
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
