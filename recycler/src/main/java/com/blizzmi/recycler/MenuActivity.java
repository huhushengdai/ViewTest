package com.blizzmi.recycler;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.blizzmi.recycler.fragment.MenuFragment;
import com.blizzmi.recycler.widget.SlidingMenuView;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        SlidingMenuView sliding = (SlidingMenuView) findViewById(R.id.menu_sliding);
        sliding.setLayout(R.layout.menu, R.layout.content);
//        getFragmentManager().beginTransaction().add(R.id.menu_container,new MenuFragment()).commit();
//        getFragmentManager().beginTransaction().re
        View view = findViewById(R.id.aaa);
        System.out.println("---------------"+view);
    }
}
