package com.blizzmi.viewpager;

import android.graphics.Color;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final ArrayList<View> data = new ArrayList<>(5);
        for (int i = 0; i < 5; i++) {
            ImageView imageView = new ImageView(this);
            imageView.setImageResource(R.mipmap.ic_launcher);
            if (i%2==0){
                imageView.setBackgroundColor(Color.GREEN);
            }else {
                imageView.setBackgroundColor(Color.RED);
            }
            data.add(imageView);
        }
        PagerAdapter adapter = new PagerAdapter() {
            @Override
            public int getCount() {
                return 5;
            }

            @Override
            public boolean isViewFromObject(View view, Object object) {
                return view == object;
            }

            @Override
            public Object instantiateItem(ViewGroup container, int position) {
//                View view = data.get(position);
//                container.addView(view);
//                return view;
                ImageView imageView = new ImageView(MainActivity.this);
                imageView.setImageResource(R.mipmap.ic_launcher);
                if (position%2==0){
                    imageView.setBackgroundColor(Color.GREEN);
                }else {
                    imageView.setBackgroundColor(Color.RED);
                }
                container.addView(imageView);
                return imageView;
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                container.removeView(data.get(position));
            }
        };

        ViewPager pager = (ViewPager) findViewById(R.id.main_pager);
        pager.setAdapter(adapter);
    }
}
