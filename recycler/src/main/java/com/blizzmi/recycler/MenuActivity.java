package com.blizzmi.recycler;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.blizzmi.recycler.widget.SlidingMenuView;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        SlidingMenuView sliding = (SlidingMenuView) findViewById(R.id.menu_sliding);
        sliding.setLayout(View.inflate(this, R.layout.menu, null)
                , View.inflate(this, R.layout.content, null));
    }
}
