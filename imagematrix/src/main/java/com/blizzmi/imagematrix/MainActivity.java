package com.blizzmi.imagematrix;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private ImageView iv2;
    private Canvas canvas;
    private Paint paint;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        iv2 = (ImageView) findViewById(R.id.iv2);
        Bitmap bitmap1 = BitmapFactory.decodeResource(getResources(),
                R.mipmap.ic_head);
        Bitmap updateBitmap = Bitmap.createBitmap(bitmap1.getWidth() * 2,
                bitmap1.getHeight() * 2, bitmap1.getConfig());
        canvas = new Canvas(updateBitmap);
        paint = new Paint();
        paint.setColor(Color.BLACK);
        Matrix matrix = new Matrix();
         setMirrorOne(bitmap1, matrix);
         setInvertedImage(bitmap1, matrix);
        setBaseChange(matrix);
        canvas.drawBitmap(bitmap1, matrix, paint);
        setImageSynthesis(matrix);

        iv2.setImageBitmap(updateBitmap);

    }

    /**
     * 还有一些基本变化
     */
    private void setBaseChange(Matrix matrix) {
        // matrix.setRotate(60);// 这是旋转多少度
        // matrix.setRotate(degrees, px, py);//这个方法是以哪个点为中心进行旋转多少度
        // matrix.setSkew(kx, ky);//设置倾斜，以x轴倾斜，还是y轴
        // 倾斜x和y轴，以（100，100）为中心。
        // matrix.postSkew(0 .2f, 0 .2f, 100 , 100 );
        // matrix.setScale(0.5f, 1);//缩放宽度变为原来的一半，高度不变
        matrix.setTranslate(100, 500);
    }

    /**
     * 设置倒影效果
     *
     * @param bitmap1
     * @param matrix
     */
    private void setInvertedImage(Bitmap bitmap1, Matrix matrix) {
        matrix.setScale(1, -1);
        matrix.postTranslate(0, bitmap1.getHeight());
    }

    /**
     * 设置镜面效果方法一
     *
     * @param bitmap1
     * @param matrix
     */
    private void setMirrorOne(Bitmap bitmap1, Matrix matrix) {
        matrix.setTranslate(bitmap1.getWidth(), 0);// 这个是移动
        matrix.preScale(-1, 1);
    }

    // ---------------------------------------------------------
    /**
     * 解释：镜面效果方法一和二的区别：
     * 不知道大家看没看出来，其实这两种方法的效果是一样的，只不过是设置步骤不一样，post是后乘，当前的矩阵乘以参数给出的矩阵
     * 。可以连续多次使用post，来完成所需的整个变换。 pre是前乘，参数给出的矩阵乘以当前的矩阵。所以操作是在当前矩阵的最前面发生的。
     * 可以连续多次使用post
     * ，来完成所需的整个变换，但是不可以连续使用set来完成矩阵的整个变换，为什么呢？set是直接设置Matrix的值，每次set一次
     * ，整个Matrix的数组都会变掉，第一次可以使用set,之后的变换必须换成post或者pre，也可以一直用post也行
     */
    // ---------------------------------------------------------

    /**
     * 设置镜面效果方法二
     *
     * @param bitmap1
     * @param matrix
     */
    private void setMirrorTwo(Bitmap bitmap1, Matrix matrix) {
        matrix.setScale(-1, 1);
        matrix.postTranslate(bitmap1.getWidth(), 0);
    }

    /**
     * 设置图片的合成
     *
     * @param matrix
     */
    private void setImageSynthesis(Matrix matrix) {
        Bitmap bitmap2 = BitmapFactory.decodeResource(getResources(),
                R.mipmap.ic_launcher);
        // 设置图片合成时的各种模式
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DARKEN));
        // 图片的合成很简单，就是再以bitmap2为基图往目标图片上画一次
        canvas.drawBitmap(bitmap2, matrix, paint);
    }
}
