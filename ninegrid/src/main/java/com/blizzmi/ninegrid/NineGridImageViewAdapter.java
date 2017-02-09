package com.blizzmi.ninegrid;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.ImageView;

/**
 * Date： 2016/11/21
 * Description:
 *
 * @author WangLizhi
 * @version 1.0
 */
public abstract class NineGridImageViewAdapter<T> {
    private static final String TAG = "NineAdapter";
    protected abstract void onDisplayImage(Context context, ImageView imageView, T t);

    protected ImageView generateImageView(Context context){
//        ImageView imageView = new ImageView(context);
        ImageView imageView = new CircleImageView(context);
        imageView.setImageResource(R.mipmap.ic_launcher);
        Log.i(TAG,"图片："+imageView.getDrawable());
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        return imageView;
    }

    //这里可以添加你所需要的事件之类的方法
}
