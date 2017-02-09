package com.blizzmi.tackpic;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.blizzmi.tackpic.utils.PathUtils;
import com.squareup.picasso.Picasso;

import java.io.File;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    int request = 1001;
    private ImageView img;
    //权限
    private static final String[] PERMISSIONS = {
            Manifest.permission.RECORD_AUDIO
            , Manifest.permission.CAMERA
            , Manifest.permission.WRITE_EXTERNAL_STORAGE
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        PermissionUtils.requestPermissions(this, 101, Manifest.permission.CAMERA);
        PermissionUtils.requestPermissions(this,102,PERMISSIONS);
        img = (ImageView) findViewById(R.id.main_img);
    }

    public void tack(View view) {
        if (!PermissionUtils.isGranted(this, PERMISSIONS[1])) {
            Toast.makeText(this, "没有拍照权限", Toast.LENGTH_SHORT).show();
            return;
        }
//        Context context = view.getContext();
//        context.startActivity(new Intent(context,Main2Activity.class));
//        startActivityForResult(new Intent(this, ChoiceHeadActivity.class), 150);
        Intent getImageByCamera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(getImageByCamera, 103);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        Bitmap thunbnail = data.getParcelableExtra("data");
////        Uri uri = data.getData();
//        Log.i("MainActivity","data");
        if (requestCode == 150) {
            String name = data.getStringExtra(ChoiceHeadActivity.IMG_NAME);
            Log.i(TAG,"图片名称："+name);
//            Picasso.with(this).load(new File(PathUtils.getOriginalImgPath(name)))
//                    .error(R.mipmap.ic_launcher)
//                    .into(img);
            Intent i = new Intent(this,ImageActivity.class);
            i.putExtra(ImageActivity.IMG,name);
            startActivity(i);
        }
    }
}
