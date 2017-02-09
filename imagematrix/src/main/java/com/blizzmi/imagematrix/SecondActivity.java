package com.blizzmi.imagematrix;

import android.graphics.Matrix;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        findViewById(R.id.second_head).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() ==MotionEvent.ACTION_MOVE) {
                    ImageView img = (ImageView) v;
                    Matrix matrix = img.getImageMatrix();
                    matrix.postTranslate(event.getX(), event.getY());
                    img.setImageMatrix(matrix);
//                    img.invalidate();
                }
                return true;
            }
        });
    }
}
