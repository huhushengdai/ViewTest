package com.blizzmi.activityoptions;

import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_second);
        int id = getIntent().getIntExtra(MainActivity.IMG_ID, 0);
//        ((ImageView) findViewById(R.id.second_img)).setBackgroundResource(id);
        final ArrayList<ImageView> imgs = new ArrayList<>(5);
        for (int i = 0; i < 5; i++) {
            ImageView img = new ImageView(this);
            img.setBackgroundResource(id);
            imgs.add(img);
        }
        ViewPager pager = (ViewPager) findViewById(R.id.second_pager);
        pager.setAdapter(new PagerAdapter() {
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
                View view = imgs.get(position);
                container.addView(view);
                return view;
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                container.removeView(imgs.get(position));
            }
        });
    }



    @Override
    public void onBackPressed() {
        super.onBackPressed();
        ActivityCompat.finishAfterTransition(this);
    }

    public void click(View view){
        Toast.makeText(this, "aaa", Toast.LENGTH_SHORT).show();
    }
}
