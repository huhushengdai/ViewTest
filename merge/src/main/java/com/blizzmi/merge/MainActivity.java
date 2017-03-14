package com.blizzmi.merge;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.scroll_view_test);
        StringBuilder sb = new StringBuilder();
        sb.append("12345678901234567890");
        System.out.println(sb.substring(0,16));
    }
}
