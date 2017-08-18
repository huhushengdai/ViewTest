package com.blizzmi.activityoptions;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;

import com.blizzmi.activityoptions.adapter.AlbumAdapter;

import java.util.ArrayList;

/**
 * Date： 2017/2/5
 * Description:
 * 相册activity
 *
 * @author WangLizhi
 * @version 1.0
 */
public class AlbumActivity extends AppCompatActivity {

    private ArrayList<String> mData = new ArrayList<>();
    private AlbumAdapter mAdapter;
    private ViewPager mPager;


    public static void start(View view,String thu,String ori, Activity context) {
        Intent i = new Intent(context, AlbumActivity.class);
        i.putExtra("thu",thu);
        i.putExtra("ori",ori);
        //启动动画效果
        ActivityOptionsCompat compat =
                ActivityOptionsCompat.makeSceneTransitionAnimation(context,
                        view, "img");
        ActivityCompat.startActivity(context, i, compat.toBundle());
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_album);
        init();
        after();
    }

    protected void init() {
        mPager = (ViewPager) findViewById(R.id.album_pager);
        mAdapter = new AlbumAdapter(mData, this, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                exit();
            }
        });
        mPager.setAdapter(mAdapter);
    }

//    @Override
//    public boolean dispatchTouchEvent(MotionEvent event) {
//        if (event.getAction()==MotionEvent.ACTION_DOWN){
//            mAdapter.setScaleType(ImageView.ScaleType.MATRIX);
//        }
//        return super.dispatchTouchEvent(event);
//    }



    protected void after() {
        String ori = getIntent().getStringExtra("ori");
        for (int i = 0; i < 5; i++) {
            mData.add(ori);
        }
        mAdapter.notifyDataSetChanged();

    }
    Handler handler = new Handler(Looper.getMainLooper());
    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onStart() {
        super.onStart();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                mAdapter.setScaleType(ImageView.ScaleType.MATRIX);
            }
        },1000);
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            exit();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    public void exit() {
        mAdapter.setScaleType(ImageView.ScaleType.FIT_CENTER);
        ActivityCompat.finishAfterTransition(AlbumActivity.this);
    }

    @Override
    protected void onDestroy() {
        mAdapter.clearData();
        mAdapter = null;
        super.onDestroy();
    }
}
