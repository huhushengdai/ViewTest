package com.blizzmi.merge;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.scroll_view_test);
        StringBuilder sb = new StringBuilder();
        sb.append("12345678901234567890");
        System.out.println(sb.substring(0,16));
        String a = "1234567";
        ArrayList<Integer> i = new ArrayList<>(7);
        char[] c = a.toCharArray();
        for (int j = 0; j < 7; j++) {
            i.add(Integer.valueOf(String.valueOf(c[j])));
        }
        for (Integer index:i){
            System.out.println(index);
        }
    }
}
