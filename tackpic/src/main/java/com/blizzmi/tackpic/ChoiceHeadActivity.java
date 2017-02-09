package com.blizzmi.tackpic;

import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.blizzmi.tackpic.utils.ImageUtils;

import java.io.File;
import java.io.IOException;

import static android.R.attr.name;

/**
 * Date： 2016/11/19
 * Description:
 * 从相册中选择图片作为头像activity
 *
 * @author WangLizhi
 * @version 1.0
 */
public class ChoiceHeadActivity extends AppCompatActivity {
    public static final int REQUEST_CODE = 1101;
    public static final int RESULT_SUCCESS = 1102;
    public static final int RESULT_FAIL = 1103;
    public static final String IMG_NAME = "imageName";

    private static final int REQ_ALBUM_CODE = 1201;//相册

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        choicePhoto();
    }


    /**
     * 选择相册图片
     */
    private void choicePhoto() {
        Intent getAlbum = new Intent(Intent.ACTION_GET_CONTENT);
        getAlbum.setType("image/*");
        startActivityForResult(getAlbum, REQ_ALBUM_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQ_ALBUM_CODE && data != null) {
            Uri originalUri = data.getData();        //获得图片的uri
            ContentResolver resolver = getContentResolver();
            ImageUtils.getBitmapSize()
        }
        setResult(RESULT_FAIL);
    }

    /**
     * 修改图片尺寸
     */
    private Bitmap changeImageSize(Bitmap bm) {
        Matrix matrix = new Matrix();
        matrix.postScale(0.3f, 0.3f); //长和宽放大缩小的比例
        Bitmap resizeBmp = Bitmap.createBitmap(bm, 0, 0, bm.getWidth(), bm.getHeight(), matrix, true);
        return resizeBmp;
    }
}
