package com.huhushengdai.testdrawable;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;

public class Main2Activity extends AppCompatActivity {
    private static final String TAG = "Main2Activity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        ImageView img = (ImageView) findViewById(R.id.main2_img);
//        TextDrawable drawable = new TextDrawable(this);
//        drawable.setText("abcd");
//        drawable.setBackgroundColor(Color.parseColor("#6ff93f57"));
        Text2Drawable drawable = new Text2Drawable(this);
        drawable.setText("abbccccc");

        img.setImageDrawable(drawable);
    }

    @Override
    protected void onPause() {
        super.onPause();
        ImageView img = (ImageView) findViewById(R.id.main2_img);
        Log.i(TAG, "width:" + img.getWidth() + ",height:" + img.getHeight());
    }
}
