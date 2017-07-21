package com.blizzmi.drawer;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;

import com.blizzmi.drawer.widget.BubbleImageView;
import com.squareup.picasso.Picasso;

public class MainActivity extends AppCompatActivity {
//    private String url = "http://bl-im-file.cs.mdd.today/obj_1500602262_06333";
    private String url = "http://bl-im-file.cs.mdd.today/obj_1500542693_29224";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.mipmap.m_bg);
//        img.setImageBitmap(bitmap);
//        BubbleImageView img = (BubbleImageView) findViewById(R.id.main_img);
//        Drawable drawable = getResources().getDrawable(R.mipmap.m_bg);
//        Bitmap bitmap = img.rBitmap(drawable);
//        img.setImageBitmap(bitmap);
//        Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.mipmap.m_bg);
//        img.setLocalImageBitmap(bitmap,R.drawable.mesbox_y_9);
    }

    public void load(View view) {
        Drawable drawable = getResources().getDrawable(R.mipmap.ic_launcher);
        BubbleImageView img = (BubbleImageView) findViewById(R.id.main_img);
        Picasso.with(this).load(url).placeholder(drawable).into(img);
    }

    public void params(View view){
        BubbleImageView img = (BubbleImageView) findViewById(R.id.main_img);
        ViewGroup.LayoutParams params = img.getLayoutParams();
        params.width = 240;
        params.height = 360;
        img.setLayoutParams(params);
    }

}
