package com.blizzmi.tackpic;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.blizzmi.tackpic.utils.PathUtils;
import com.squareup.picasso.Picasso;

import java.io.File;

public class ImageActivity extends AppCompatActivity {
    public static final String IMG = "image";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);
        ImageView img = (ImageView) findViewById(R.id.img);
        String name = getIntent().getStringExtra(IMG);
        Picasso.with(this).load(new File(PathUtils.getOriginalImgPath(name)))
                .error(R.mipmap.ic_launcher)
                .into(img);
    }
}
