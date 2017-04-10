package com.huhushengdai.notice;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.i(TAG,"onCreate");
    }

    public void sendNotice(View view) {
        startService(new Intent(this, NoticeService.class));
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG,"onStart");
        Log.i(TAG,"onStart:"+getIntent().getAction());
        if ("send".equals(getIntent().getAction())) {
            startActivity(new Intent(this, Main2Activity.class));
        }
    }

    @Override
    protected void onResume() {
        Log.i(TAG,"onResume");
        super.onResume();
    }

    @Override
    protected void onPause() {Log.i(TAG,"onPause");
        super.onPause();
    }

    @Override
    protected void onStop() {Log.i(TAG,"onStop");
        super.onStop();
    }

    @Override
    protected void onRestart() {Log.i(TAG,"onRestart");
        super.onRestart();
    }

    @Override
    protected void onDestroy() {Log.i(TAG,"onDestroy");
        super.onDestroy();
    }
}
