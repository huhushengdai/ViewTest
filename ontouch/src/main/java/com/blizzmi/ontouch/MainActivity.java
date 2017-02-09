package com.blizzmi.ontouch;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    //http://www.ithome.com/html/it/279053.htm    网址
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btn = (Button) findViewById(R.id.main_btn);
        btn.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                System.out.println("Action = " +getAction(event)+",x = "+event.getX()+",y = "+event.getY());
                return false;
            }
        });
        System.out.println("------btn x="+btn.getX()+",y="+btn.getY());
    }

//    @Override
//    public boolean dispatchTouchEvent(MotionEvent ev) {
//        Log.e(TAG, "dispatchTouchEvent"+"-----"+getAction(ev));
//        return super.dispatchTouchEvent(ev);
////        return true;
////        return false;
//    }
//
//
//    @Override
//    public boolean onTouchEvent(MotionEvent event) {
//        Log.e(TAG, "onTouchEvent"+"-----"+getAction(event));
//        return super.onTouchEvent(event);
//    }

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
