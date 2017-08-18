package com.blizzmi.activityoptions;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.blizzmi.activityoptions.adapter.BaseRecyclerAdapter;
import com.blizzmi.activityoptions.adapter.ListAdapter;

import java.util.ArrayList;

public class ListActivity extends AppCompatActivity {
    public static final String IMG_ID = "imgId";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        RecyclerView rec = (RecyclerView) findViewById(R.id.list_recycler);
        rec.setLayoutManager(new LinearLayoutManager(this));
        ArrayList<Integer> data = new ArrayList<>(20);
        for (int i = 0; i < 20; i++) {
            data.add(i);
        }
        ListAdapter adapter = new ListAdapter(this, data);
        rec.setAdapter(adapter);
        adapter.setChildClickListener(new BaseRecyclerAdapter.ChildClickListener() {
            @Override
            public void childOnClick(View parent, View childView, int position) {
                AlbumActivity.start(childView, "obj_1502950118_47486", "obj_1502950118_40892", ListActivity.this);
            }
        });
    }

    public void start(View view) {
        Intent i = new Intent(ListActivity.this, SecondActivity.class);
        i.putExtra(IMG_ID, R.mipmap.ori);
        ActivityOptionsCompat compat =
                ActivityOptionsCompat.makeSceneTransitionAnimation(ListActivity.this,
                        view, "img");
        ActivityCompat.startActivity(ListActivity.this, i, compat.toBundle());

//        Intent i = new Intent(ListActivity.this, SecondActivity.class);
//        i.putExtra(IMG_ID, R.mipmap.ori);
//        i.putExtra("x", view.getX());
//        i.putExtra("yyy", view.getY());
//        i.putExtra("w", view.getWidth());
//        i.putExtra("h", view.getHeight());
//
//        startActivity(i);
//        overridePendingTransition(0, 0);

    }
}
