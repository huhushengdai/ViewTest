package com.blizzmi.edit;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    private EditText edit;
    private static final String z = "@.+";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void clickEvent(View view){
        MediaPlayer mPlayer = new MediaPlayer();
        mPlayer.reset();
        try {
            mPlayer.setDataSource(this,Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.input_password_new_again));
            mPlayer.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //播放
        mPlayer.start();
    }
}
