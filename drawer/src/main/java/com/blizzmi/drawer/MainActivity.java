package com.blizzmi.drawer;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void startPassword(View view) {
        startActivity(new Intent(this, Main2Activity.class));
    }

    public void startDrawer(View view){
        DrawerLayout drawerLayout = (DrawerLayout) findViewById(R.id.activity_main);
        drawerLayout.openDrawer(Gravity.LEFT);
    }
}
