package com.blizzmi.activityoptions;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    public static final String IMG_ID = "imgId";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public Intent createIntent(int id) {
        Intent i = new Intent(this, SecondActivity.class);
        i.putExtra(IMG_ID, id);
        return i;
    }

    public void animation(View view) {
        startActivity(new Intent(this, SecondActivity.class));

//        overridePendingTransition(R.anim.activity_open_from_bottom, 0);
        overridePendingTransition(0, 0);
    }

    public void makeClipRevealAnimation(View view) {
        ActivityOptionsCompat compat = ActivityOptionsCompat.makeCustomAnimation(this,
                R.anim.start, R.anim.start);
        ActivityCompat.startActivity(this,
                createIntent(R.mipmap.bx_female), compat.toBundle());
    }

    public void makeCustomAnimation(View view) {
        ActivityOptionsCompat compat = ActivityOptionsCompat.makeScaleUpAnimation(view,
                view.getWidth() / 2, view.getHeight() / 2, 0, 0);

        ActivityCompat.startActivity(this, createIntent(R.mipmap.bx_group_head),
                compat.toBundle());
    }

    public void makeScaleUpAnimation(View view) {
        ActivityOptionsCompat compat =
                ActivityOptionsCompat.makeSceneTransitionAnimation(this,
                        view, "img");
        ActivityCompat.startActivity(this, createIntent(R.mipmap.bx_male), compat.toBundle());
    }

    public void makeSceneTransitionAnimation(View view) {

    }

    public void makeThumbnailScaleUpAnimation(View view) {

    }
}
