package com.blizzmi.viewtest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;

import java.util.ArrayList;

import static android.R.attr.data;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        ArrayList<String> data = new ArrayList<>();
        for (int i = 0; i < 40; i++) {
            if (i%4!=0){
                data.add("aaaaaa");
            }else {
                data.add("bbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbb");
            }
        }
        ChatAdapter adapter = new ChatAdapter(this,data,R.layout.layout_msg_send);
        ((ListView)findViewById(R.id.chat_list)).setAdapter(adapter);
        Log.i(TAG,"---------2");
    }

}
