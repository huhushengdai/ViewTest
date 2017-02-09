package com.blizzmi.activityoptions;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import java.util.ArrayList;

public class ThirdActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);
        final int id = getIntent().getIntExtra(MainActivity.IMG_ID, 0);

        GridView grid = (GridView) findViewById(R.id.third_grid);
        grid.setAdapter(new BaseAdapter() {
            @Override
            public int getCount() {
                return 30;
            }

            @Override
            public Object getItem(int position) {
                return null;
            }

            @Override
            public long getItemId(int position) {
                return 0;
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view = convertView;
                if (view == null){
                    view = View.inflate(ThirdActivity.this,R.layout.item_grid,null);
                }
                ImageView img = (ImageView) view.findViewById(R.id.item_img);
                img.setImageResource(id);
                img.setOnClickListener(ThirdActivity.this);
                return view;
            }
        });
    }

    @Override
    public void onClick(View v) {

    }
}
