package com.blizzmi.activityoptions;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_second);
        int id = getIntent().getIntExtra(MainActivity.IMG_ID, 0);
//        ((ImageView) findViewById(R.id.second_img)).setBackgroundResource(id);
        final ArrayList<ImageView> imgs = new ArrayList<>(5);
        for (int i = 0; i < 5; i++) {
            ImageView img = new ImageView(this);
            img.setBackgroundResource(id);
            imgs.add(img);
        }
        final ViewPager pager = (ViewPager) findViewById(R.id.second_pager);
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
        Intent i = getIntent();
        float x = i.getFloatExtra("x", 0);
        float y = i.getFloatExtra("yyy", 0);
        int w = i.getIntExtra("w", 0);
        int h = i.getIntExtra("h", 0);
        ViewGroup.LayoutParams params = pager.getLayoutParams();
        params.width = w;
        params.height = h;
        pager.setLayoutParams(params);
        pager.setX(x);
        pager.setY(y);

        Animation animation = AnimationUtils.loadAnimation(this, R.anim.activity_open_from_bottom);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                ViewGroup.LayoutParams params = pager.getLayoutParams();
                params.width = -1;
                params.height = -2;
                pager.setLayoutParams(params);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        pager.startAnimation(animation);
        ObjectAnimator anim = ObjectAnimator.ofFloat(pager, "scaleY", 1f, 5f, 1f);

    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        ActivityCompat.finishAfterTransition(this);
    }

    public void click(View view) {
        Toast.makeText(this, "aaa", Toast.LENGTH_SHORT).show();
    }
}
