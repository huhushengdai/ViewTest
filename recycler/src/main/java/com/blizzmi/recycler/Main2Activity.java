package com.blizzmi.recycler;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class Main2Activity extends AppCompatActivity {
    RecyclerView r;
    boolean frozen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        r = (RecyclerView) findViewById(R.id.main2_recycler);
        r.setLayoutManager(new StaggeredGridLayoutManager(5, StaggeredGridLayoutManager.HORIZONTAL));
        r.setAdapter(new RecyclerView.Adapter() {
            @Override
            public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                return new RecyclerView.ViewHolder(View.inflate(Main2Activity.this, R.layout.item_text, null)) {
                };
            }

            @Override
            public void onBindViewHolder(RecyclerView.ViewHolder holder,final int position) {
                int max = 500;
                int min = 300;
                Random random = new Random();

                int s = random.nextInt(max) % (max - min + 1) + min;
                TextView textView = (TextView) holder.itemView.findViewById(R.id.text);
                textView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(Main2Activity.this, ""+position, Toast.LENGTH_SHORT).show();
                    }
                });
                ViewGroup.LayoutParams params = textView.getLayoutParams();
                params.width = s;
                textView.setLayoutParams(params);
            }

            @Override
            public int getItemCount() {
                return 200;
            }
        });
    }

    public void frozen(View view) {
        r.setLayoutFrozen(frozen);
        frozen = !frozen;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        System.out.println("action"+event.getAction());
        return super.onTouchEvent(event);
    }
}
