package com.blizzmi.imagematrix;

import android.graphics.Matrix;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

public class TimeMatrixActivity extends AppCompatActivity {

    private float count;
    private ImageView img;
    private Handler mHandler = new Handler(Looper.getMainLooper()) {
        @Override
        public void handleMessage(Message msg) {
            count++;
            if (count == 10) {
                count = 0;
            }
            Matrix matrix = img.getImageMatrix();
            Matrix newM = new Matrix(matrix);
            float scale = (float) (1 + count * 0.5);
            System.out.println("------------------scale:" + scale);
            newM.setScale(scale, scale);
            Drawable drawable = img.getDrawable();
            int width = (int) (drawable.getIntrinsicWidth()*scale);
            int height = (int) (drawable.getIntrinsicHeight()*scale);
            newM.postTranslate((img.getWidth()-width)/2,(img.getHeight()-height)/2);
//            newM.postTranslate(100,100);
            img.setImageMatrix(newM);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_matrix);
        img = (ImageView) findViewById(R.id.time_matrix_img);
    }

    public void start(View view) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while (true) {
                        Thread.sleep(700);
                        mHandler.sendEmptyMessage(1);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
