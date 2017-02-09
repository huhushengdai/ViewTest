package com.blizzmi.task;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    private TestTask task;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        task = new TestTask();
        task.execute();
    }

    public void click(View view){
        System.out.println("取消结果"+task.cancel(true));
    }
    boolean flag = true;
    class TestTask extends AsyncTask<Void,Void,Void>{

        @Override
        protected Void doInBackground(Void... params) {
            try {
                while (flag) {
                    System.out.println("-----");
                }
            }catch (Exception e){
                e.printStackTrace();
            }
            System.out.println("异步任务完成");
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            System.out.println("任务结束--");
        }
    }
}
