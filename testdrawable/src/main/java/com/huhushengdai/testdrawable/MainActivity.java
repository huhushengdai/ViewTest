package com.huhushengdai.testdrawable;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        ImageView img = (ImageView) findViewById(R.id.main_img);


        Text2Drawable drawable = new Text2Drawable(this);
        drawable.setText("45");
//        drawable.setTextColor(Color.GREEN);
//        drawable.setTextSize(24);
        drawable.setBackgroundColor(Color.YELLOW);
//        drawable.setBounds(0,0,10,10);
//        img.setImageDrawable(drawable);


        ImageView img2 = (ImageView) findViewById(R.id.main_img2);
        img2.setImageDrawable(drawable);
    }
}
