package com.huhushengdai.adaptation;

import android.content.Context;
import android.os.Bundle;
import android.view.WindowManager;

import com.zhy.autolayout.AutoLayoutActivity;

public class MainActivity extends AutoLayoutActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        WindowManager wm = (WindowManager) this
                .getSystemService(Context.WINDOW_SERVICE);

        int width = wm.getDefaultDisplay().getWidth();
        int height = wm.getDefaultDisplay().getHeight();

        System.out.println("-------width:"+width);
        System.out.println("-------height:"+height);
    }
}
