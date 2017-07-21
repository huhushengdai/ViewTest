package com.huhushengdai.testdrawable;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImageView img = (ImageView) findViewById(R.id.main_img);
//        @ColorInt int myColor = 0x77456173;
        img.setBackgroundColor(0x456173);
        MyDrawable drawable = new MyDrawable(this);
        drawable.setText("aaaa");
//        TextDrawable drawable = new TextDrawable(this);
//        drawable.setText("ABc");
//        drawable.setTextColor(Color.GREEN);
//        drawable.setTextSize(14);
//        drawable.setBounds(0,0,100,100);
//        img.setImageDrawable(drawable);

    }
}
