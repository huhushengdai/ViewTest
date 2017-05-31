package com.blizzmi.tackpic.utils;

import android.content.ContentResolver;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.net.Uri;
import android.util.Log;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import static android.graphics.BitmapFactory.decodeStream;

/**
 * Date： 2016/11/23
 * Description:
 * 图片裁剪压缩工具
 *
 * @author WangLizhi
 * @version 1.0
 */
public class ImageUtils {
    private static final String TAG = "ImageUtils";

    /**
     * 按指定宽高，压缩图片
     *
     * @param targetW 指定宽
     * @param targetH 指定高
     * @return 获取到的图片
     * @throws IOException
     */
    public static Bitmap ratio(ContentResolver cr, Uri uri, float targetW, float targetH) throws IOException {
        if (uri == null || cr == null) {
            return null;
        }

        InputStream input = cr.openInputStream(uri);
        BitmapFactory.Options onlyBoundsOptions = new BitmapFactory.Options();
        onlyBoundsOptions.inJustDecodeBounds = true;
        onlyBoundsOptions.inDither = true;//optional
        onlyBoundsOptions.inPreferredConfig = Bitmap.Config.RGB_565;//optional
        BitmapFactory.decodeStream(input, null, onlyBoundsOptions);
        input.close();
        //比例压缩
        int be = getInSampleSize(onlyBoundsOptions.outWidth, onlyBoundsOptions.outHeight, (int) targetW, (int) targetH);

        BitmapFactory.Options bitmapOptions = new BitmapFactory.Options();
        bitmapOptions.inSampleSize = be;//设置缩放比例
        bitmapOptions.inDither = true;//optional
        bitmapOptions.inPreferredConfig = Bitmap.Config.RGB_565;//optional
        input = cr.openInputStream(uri);
        Bitmap bitmap = BitmapFactory.decodeStream(input, null, bitmapOptions);
        input.close();

        return bitmap;
    }

    /**
     * 指定图片大小，进行质量压缩
     *
     * @param bitmap 需要压缩的图片
     * @param size   指定压缩大小 单位kb
     * @return bitmap
     */
    public static Bitmap compress(Bitmap bitmap, int size) {
        if (bitmap == null) {
            return null;
        }
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        int quality = 100;//压缩质量
        bitmap.compress(Bitmap.CompressFormat.JPEG, quality, os);
        while (os.toByteArray().length > size
                && quality > 0) {
            os.reset();
            Log.i(TAG, "压缩质量：" + quality);
            bitmap.compress(Bitmap.CompressFormat.JPEG, quality, os);
            quality = quality - 30;//压缩质量
        }
        ByteArrayInputStream is = new ByteArrayInputStream(os.toByteArray());
        return decodeStream(is);
    }

    /**
     * 缩放图片
     *
     * @param bitmap 缩放图片
     * @param scale  缩放比例
     * @return 结果
     */
    public static Bitmap scale(Bitmap bitmap, float scale) {
        if (bitmap == null) {
            return null;
        }
        Matrix matrix = new Matrix();
        matrix.postScale(scale, scale); //长和宽放大缩小的比例
        return Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
    }

    public static Bitmap scale(Bitmap bitmap, float targetW, float targetH) {
        if (bitmap == null) {
            return null;
        }
        float be = getInSampleSize(bitmap.getWidth(), bitmap.getHeight(), (int) targetW, (int) targetH);
        return scale(bitmap, 1 / be);
    }

    /**
     * 获取图片大小
     *
     * @param bitmap 图片
     * @return 尺寸
     */
    public static int getBitmapSize(Bitmap bitmap) {
        if (bitmap == null) {
            return 0;
        }
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, os);
        int size = os.size();
        try {
            os.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return size;
    }

    /**
     * 获取压缩比例
     *
     * @param originalW 原图宽
     * @param originalH 原图高
     * @param targetW   压缩目标的宽
     * @param targetH   压缩目标的高
     * @return 压缩比例
     */
    private static int getInSampleSize(int originalW, int originalH, int targetW, int targetH) {
        int be = 1;//be=1表示不缩放
        if (originalW >= originalH && originalW > targetW) {//如果宽度大的话根据宽度固定大小缩放
            be = originalW / targetW;
        } else if (originalW < originalH && originalH > targetH) {//如果高度高的话根据宽度固定大小缩放
            be = originalH / targetH;
        }
        if (be <= 0) {
            be = 1;
        }
        return be;
    }

    public static Bitmap ratio(String imgFile, int vWidth, int hWidth) {
        BitmapFactory.Options onlyBoundsOptions = new BitmapFactory.Options();
        onlyBoundsOptions.inJustDecodeBounds = true;

        BitmapFactory.decodeFile(imgFile, onlyBoundsOptions);
        //比例
        onlyBoundsOptions.inSampleSize = getCompressionRatio(
                onlyBoundsOptions.outWidth
                , onlyBoundsOptions.outHeight
                , vWidth, hWidth);

        onlyBoundsOptions.inJustDecodeBounds = false;
        onlyBoundsOptions.inDither = true;//防抖
        onlyBoundsOptions.inPreferredConfig = Bitmap.Config.ARGB_8888;//像素
        return BitmapFactory.decodeFile(imgFile, onlyBoundsOptions);
    }

    /**
     * 获取压缩比例
     *
     * @param width  宽
     * @param height 高
     * @param vWidth 图片垂直展示（宽小于高）的 最大宽度
     * @param hWidth 图片水平展示（宽大于高）的 最大宽度
     * @return 压缩比例
     */
    private static int getCompressionRatio(int width, int height, int vWidth, int hWidth) {
        int ratio;
        if (width > height) {
            //水平显示
            ratio = width / hWidth;
        } else {
            //垂直显示
            ratio = width / vWidth;
        }
        return ratio < 1 ? 1 : ratio;
    }
}
