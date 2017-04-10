package com.huhushengdai.linefeedview;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ScrollView;
import android.widget.TextView;

import com.huhushengdai.linefeedview.widget.AutoLinefeedView;
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

        auto = (AutoLinefeedView) findViewById(R.id.main_auto);
        TextView textView = null;
        for (int i = 0; i < 133; i++) {
            textView = new TextView(this);
            textView.setLayoutParams(new ViewGroup.LayoutParams(-2, 70));
            textView.setPadding(10, 10, 10, 10);
            textView.setBackgroundColor(Color.GREEN);
            textView.setTextSize(36);
            Integer index = Integer.valueOf(i);
            textView.setText(i + "a");
            auto.addView(textView);
        }
        textView.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
        ScrollView scrollView = (ScrollView) findViewById(R.id.main_scroll);
        ViewGroup.LayoutParams params = scrollView.getLayoutParams();
        params.height = textView.getMeasuredHeight() * 2 + 16;
        scrollView.setLayoutParams(params);
    }

    AutoLinefeedView auto;

    @Override
    protected void onPause() {
        super.onPause();
    }
}
