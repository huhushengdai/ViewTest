package com.blizzmi.tackpic;

import android.Manifest;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.blizzmi.tackpic.utils.ImageUtils;
import com.blizzmi.tackpic.utils.PathUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

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
        PermissionUtils.requestPermissions(this, 102, PERMISSIONS);
        img = (ImageView) findViewById(R.id.main_img);
    }

    public void tack(View view) {
        if (!PermissionUtils.isGranted(this, PERMISSIONS[1])) {
            Toast.makeText(this, "没有拍照权限", Toast.LENGTH_SHORT).show();
            return;
        }
        Intent startCamera = new Intent("android.media.action.IMAGE_CAPTURE");
        //系统相机拍照成功后照片
        File tempPicture = new File(PathUtils.getCameraTempPicture());
        if (tempPicture.exists()) {
            tempPicture.delete();
        }
        try {
            tempPicture.createNewFile();
            startCamera.putExtra(MediaStore.Images.Media.ORIENTATION, 0);//相片角度
            //指定相片输出地址
            startCamera.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(tempPicture));
            startActivityForResult(startCamera, 103);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 150) {
            String name = data.getStringExtra(ChoiceHeadActivity.IMG_NAME);
            Log.i(TAG, "图片名称：" + name);
            Intent i = new Intent(this, ImageActivity.class);
            i.putExtra(ImageActivity.IMG, name);
            startActivity(i);
        } else if (requestCode == 103) {
            //拍照
            File picture = new File(PathUtils.getCameraTempPicture());
            if (!picture.exists()) {
                Toast.makeText(this, "拍照失败", Toast.LENGTH_SHORT).show();
                return;
            }
            Bitmap bitmap = ImageUtils.ratio(picture.getAbsolutePath(), 150, 400);
            System.out.println("bitmap size:"+ImageUtils.getBitmapSize(bitmap));
            saveBitmap(bitmap, PathUtils.BASE_URL + "picture.jpg");
            bitmap.recycle();
        }
    }

    /**
     * 保存图片
     *
     * @param bitmap 图片
     * @param path   保存路径
     * @return 是否保存成功
     */
    public static boolean saveBitmap(Bitmap bitmap, String path) {
        if (bitmap == null) {
            return false;
        }
        File f = new File(path);
        if (f.exists()) {
            f.delete();
        }
        FileOutputStream out = null;
        try {
            if (!f.createNewFile()) {
                Log.i(TAG, "创建文件失败:" + path);
            }
            out = new FileOutputStream(f);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, out);
            out.flush();
            Log.i(TAG, "图片已经保存:" + path);
            return true;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return false;
    }
}
