package com.blizzmi.viewpager;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.blizzmi.viewpager.widget.MyViewPager;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private String[] textStr = {
            "群组聊天"
            , "搜索好友"
            , "私密聊天"
            , "注册账号"
            , "登录账号"
    };
    private String[] textStr2 = {
            "随时随地，多人一起聊天"
            , "寻找志趣小伙伴"
            , "好友二人世界单独密聊"
            , "这个测试text"
            , "我已经无话可说"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final ArrayList<View> data = new ArrayList<>(5);
        for (int i = 0; i < 5; i++) {
            ImageView imageView = new ImageView(this);
            imageView.setImageResource(R.mipmap.ic_launcher);
            if (i % 2 == 0) {
                imageView.setBackgroundColor(Color.GREEN);
            } else {
                imageView.setBackgroundColor(Color.RED);
            }
            data.add(imageView);
        }
        PagerAdapter adapter = new PagerAdapter() {
            @Override
            public int getCount() {
                return Integer.MAX_VALUE;
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
                if (position % 2 == 0) {
                    imageView.setBackgroundColor(Color.GREEN);
                } else {
                    imageView.setBackgroundColor(Color.RED);
                }
                container.addView(imageView);
                return imageView;
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                container.removeView(data.get(position%5));
            }
        };

        pager = (MyViewPager) findViewById(R.id.main_pager);
        pager.setAdapter(adapter);

        pager.setOnScrollProgressListener(new MyViewPager.OnScrollProgressListener() {
            @Override
            public void onScrollProgress(float progress) {
//                TextView text = (TextView) findViewById(R.id.main_text_s);
//                float y = pager.getY() - text.getHeight();
//                if (progress == 1) {
//                    text.setY(y);
//                    text.setAlpha(1);
//                } else {
//                    text.setY(y - 300 * progress);
//                    text.setAlpha(1 - progress);
//                }
                setSy(progress);
                setBy(progress);
            }
        });
        ((TextView) findViewById(R.id.main_text_s)).setText(textStr2[pager.getCurrentItem()]);
        ((TextView) findViewById(R.id.main_text_b)).setText(textStr[pager.getCurrentItem()]);
        ((TextView) findViewById(R.id.main_text_b)).setText(Html.fromHtml(getString(R.string.title)));
    }

    MyViewPager pager;

    private void setSy(float progress) {
        TextView text = (TextView) findViewById(R.id.main_text_s);
        text.setText(textStr2[pager.getCurrentItem()%5]);
        float y = pager.getY() - text.getHeight();
        float alpha = 1 - 4 * (-progress * progress + progress);
        Log.i(TAG, "alpha =" + alpha);
        text.setAlpha(alpha);
        text.setY(y - 150 * 4 * (-progress * progress + progress));
    }

    private void setBy(float progress) {
        TextView text = (TextView) findViewById(R.id.main_text_b);
        text.setText(textStr[pager.getCurrentItem()%5]);
        float y = pager.getY() - findViewById(R.id.main_text_s).getHeight() - text.getHeight();
        float alpha = 1 - 4 * (-progress * progress + progress);
        Log.i(TAG, "alpha =" + alpha);
        text.setAlpha(alpha);
        text.setY(y - 150 * 6 * (-progress * progress + progress));
    }
}
