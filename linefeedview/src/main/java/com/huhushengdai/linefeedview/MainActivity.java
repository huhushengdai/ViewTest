package com.huhushengdai.linefeedview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.huhushengdai.linefeedview.widget.LineFeedGroupView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LineFeedGroupView group = (LineFeedGroupView) findViewById(R.id.main_line);
        group.setAdapter(new BaseAdapter() {
            @Override
            public int getCount() {
                return 9;
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
                if (view == null) {
                    view = View.inflate(MainActivity.this, R.layout.item_line, null);
                }

                return view;
            }
        });
    }
}
