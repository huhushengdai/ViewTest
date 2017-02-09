package com.blizzmi.horizontalrecycler;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView2();
    }

    private void initView() {
        final RecyclerView recyclerView = (RecyclerView) findViewById(R.id.main_recycler);
        LinearLayoutManager manager =  new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(new HAdapter(this));
        recyclerView.setOnTouchListener(new View.OnTouchListener() {
            float down;
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction()==MotionEvent.ACTION_DOWN){
                    down = event.getX();
                }else if (event.getAction()==MotionEvent.ACTION_UP){
                    recyclerView.scrollToPosition(down-event.getX()>0?1:0);
                }
                return false;
            }
        });
    }

    private void initView2(){
        final RecyclerView recyclerView = (RecyclerView) findViewById(R.id.main_recycler);
        LinearLayoutManager manager =  new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);
        ArrayList<String> data = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            data.add(i+"-----");
        }
        recyclerView.setAdapter(new HorizontalDeleteAdapter(this,data));
    }

}
