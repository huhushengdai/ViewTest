package com.blizzmi.edit;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private EditText edit;
    private static final String z = "@.+";

    boolean flag;

    ArrayList<String> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        edit = (EditText) findViewById(R.id.main_edit);
//        edit.setOnLongClickListener(new View.OnLongClickListener() {
//            @Override
//            public boolean onLongClick(View v) {
//                return flag;
//            }
//        });
//        for (int i = 0; i < 10; i++) {
//            list.add("" + i);
//        }

        System.out.println("结果：" + (0%120));
        ImageView imageView = (ImageView) findViewById(R.id.main_img);
        imageView.setAlpha(0.5f);
    }

    private static final String SERVICE_REGEX = ".*";//service的正则表达式

    public void clickEvent(View view) {
        flag = !flag;
        list.remove("1");
        System.out.println("size:" + list.size());
    }
}
