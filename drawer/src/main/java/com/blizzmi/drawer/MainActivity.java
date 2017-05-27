package com.blizzmi.drawer;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.blizzmi.drawer.widget.BubbleImageView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BubbleImageView img = (BubbleImageView) findViewById(R.id.main_img);
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.mipmap.m_bg);
//        img.setExpectedWidth(300);
        img.setImageBitmap(bitmap);
//        BubbleImageView img = (BubbleImageView) findViewById(R.id.main_img);
//        Drawable drawable = getResources().getDrawable(R.mipmap.m_bg);
//        Bitmap bitmap = img.rBitmap(drawable);
//        img.setImageBitmap(bitmap);
//        Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.mipmap.m_bg);
//        img.setLocalImageBitmap(bitmap,R.drawable.mesbox_y_9);
    }


}
