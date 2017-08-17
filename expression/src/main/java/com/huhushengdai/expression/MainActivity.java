package com.huhushengdai.expression;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.ImageSpan;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText edit = (EditText) findViewById(R.id.main_edit);
        edit.setText("我是表情[睡觉]");

        Button button = (Button) findViewById(R.id.main_btn);
        button.setText("[睡觉]");

        String str = "abcd";

        TextView text = (TextView) findViewById(R.id.main_text);
        text.setText("我是表情[睡觉]我是表情[睡觉][睡觉][睡觉]");
    }

    /**
     * 添加表情
     */
    public SpannableString addFace(Context context, int imgId,
                                   String spannableString) {
        if (TextUtils.isEmpty(spannableString)) {
            return null;
        }
        Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(),
                imgId);
        bitmap = Bitmap.createScaledBitmap(bitmap, 65, 65, true);
        ImageSpan imageSpan = new ImageSpan(context, bitmap);
        SpannableString spannable = new SpannableString(spannableString);
        spannable.setSpan(imageSpan, 0, spannableString.length(),
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        return spannable;
    }

    public ImageSpan createImageSpan(Context context, @DrawableRes int resourceId) {
        Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(),
                resourceId);
        bitmap = Bitmap.createScaledBitmap(bitmap, 55, 55, true);
        return new ImageSpan(context, bitmap);
    }

//    public void add(View view) {
//        TextView textView = (TextView) findViewById(R.id.main_text);
//        String text = textView.getText().toString();
//        SpannableString spannable = new SpannableString(text);
//        int start;
//        int end;
//        int from = 0;
//        String content;
//
//        while (true) {
//            start = text.indexOf("[", from);
//            from = start + 1;
//            end = text.indexOf("]", from);
//            if (start == -1 || end == -1) {
//                break;
//            }
//            content = text.substring(start, end + 1);
//            if ("[睡觉]".equals(content)) {
//                ImageSpan imageSpan = createImageSpan(this, R.mipmap.ic_sleep);
//
//                spannable.setSpan(imageSpan, start, end + 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
//            }
//        }
//        textView.setText(spannable);
//
//        System.out.println("-------------:" + textView.getText());
//    }

    public void add(View view) {
        EditText edit = (EditText) findViewById(R.id.main_edit);
        String e = "[睡觉]";
        SpannableString spannableString = addFace(this, R.mipmap.ic_sleep, e);

        Editable editable = edit.getText();
        int start = edit.getSelectionStart();
        editable.insert(start, spannableString);
        edit.setText(editable);
        edit.setSelection(start + e.length());

        TextView text = (TextView) findViewById(R.id.main_text);
        text.setText(edit.getText());
        System.out.println("-------------:"+edit.getText());
        Button button = (Button) findViewById(R.id.main_btn);
        button.setText("[睡觉]");
    }

    public void delete(View view){
        EditText edit = (EditText) findViewById(R.id.main_edit);
        edit.dispatchKeyEvent(new KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_DEL));
    }
}
