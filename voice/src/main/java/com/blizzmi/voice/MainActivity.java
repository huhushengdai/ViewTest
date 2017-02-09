package com.blizzmi.voice;

import android.Manifest;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

import com.blizzmi.voice.utils.MediaManager;
import com.blizzmi.voice.utils.PermissionUtils;

public class MainActivity extends AppCompatActivity {
    private MediaManager manager;
    private static final String[] PERMISSIONS = {
            Manifest.permission.RECORD_AUDIO
            , Manifest.permission.CAMERA
            , Manifest.permission.WRITE_EXTERNAL_STORAGE
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btn = (Button) findViewById(R.id.main_voice);
        manager = new MediaManager(MainActivity.this);
        ActivityCompat.requestPermissions(this,
                new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.RECORD_AUDIO},
                102);
        btn.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (!PermissionUtils.isGranted(MainActivity.this, PERMISSIONS[0])) {
                    PermissionUtils.requestPermissions(MainActivity.this, 102, PERMISSIONS[0]);
                    return false;
                }
                if (!PermissionUtils.isGranted(MainActivity.this, PERMISSIONS[2])) {
                    PermissionUtils.requestPermissions(MainActivity.this, 102, PERMISSIONS[2]);
                    return false;
                }
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    //按下时，开启录音
                    manager.radioStart();
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    manager.radioStop();
                }
                return true;
            }
        });
    }

    public void start(View view){
        manager.radioStart();
    }

    public void stop(View view){
        manager.radioStop();
    }
}
