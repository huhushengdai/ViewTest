package com.huhushengdai.testdrawable;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImageView img = (ImageView) findViewById(R.id.main_img);
        TextDrawable drawable = new TextDrawable(this);
        drawable.setText("A");
        drawable.setTextSize(30);
//        drawable.setBounds(0,0,100,100);
        img.setImageDrawable(drawable);
    }
}
