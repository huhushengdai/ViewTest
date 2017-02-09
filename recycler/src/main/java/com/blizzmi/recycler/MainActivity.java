package com.blizzmi.recycler;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        ArrayList<FriendsVm> data = new ArrayList<>();
//        for (int i = 0; i < 20; i++) {
//            data.add(new FriendsVm("name"+i));
//        }
        TextView dialog = (TextView) findViewById(R.id.main_dialog);
        SideBar sideBar = (SideBar) findViewById(R.id.main_side_bar);
        sideBar.setTextView(dialog);
    }
}
