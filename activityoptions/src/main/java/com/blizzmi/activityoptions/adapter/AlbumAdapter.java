package com.blizzmi.activityoptions.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.android.annotations.NonNull;
import com.blizzmi.activityoptions.R;
import com.blizzmi.activityoptions.widget.MatrixImageView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Date： 2017/2/5
 * Description:
 * 相册适配器
 *
 * @author WangLizhi
 * @version 1.0
 */
public class AlbumAdapter extends PagerAdapter {
    private static final String TAG = "AlbumAdapter";
    private List<String> mData;
    private Context mContext;
    private View.OnClickListener mListener;
    private ArrayList<View> mViews = new ArrayList<>();
    private ImageView.ScaleType mCurrentScaleType;//当前图片view的状态

    public AlbumAdapter(List<String> data, Context context, @NonNull View.OnClickListener listener) {
        this.mData = data;
        this.mContext = context;
        this.mListener = listener;
        initViews();
    }

    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
        initViews();
    }

    private void initViews() {
        mViews.clear();
        int size = mData.size();
        for (int i = 0; i < size; i++) {
            mViews.add(createView(mData.get(i)));
        }
    }

    @Override
    public int getCount() {
        return mData == null || mData.size() == 0 ? 0 : mData.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View img = mViews.get(position);
        if (img != null && img.getParent() == null) {
            container.addView(img);
        }
        return img;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(mViews.get(position));
    }

    private static final String BASE_IMG_URL = "http://bl-im-file-dev.riak-cs1.dev.blizzmi.local/";

    private View createView(final String ori) {
        final ImageView img = new MatrixImageView(mContext);
        img.setScaleType(ImageView.ScaleType.FIT_CENTER);
//        img.setImageResource(R.mipmap.ori);
//        final ImageView img = new ImageView(mContext);
//        img.setScaleType(ImageView.ScaleType.MATRIX);
        Picasso.with(img.getContext())
                .load(BASE_IMG_URL + ori).placeholder(R.mipmap.bx_male).into(img);

        img.setOnClickListener(mListener);
        return img;
    }

    public void setScaleType(ImageView.ScaleType scaleType) {
        if (mCurrentScaleType == scaleType) {
            return;
        }
        mCurrentScaleType = scaleType;
        int size = mViews.size();
        for (int i = 0; i < size; i++) {
            ((ImageView) mViews.get(i)).setScaleType(scaleType);
        }
    }

    /**
     * 华为手机竟然不自动回收这个集合
     * 所以手动清空，并且只activity的onDestroy方法中调用
     */
    public void clearData() {
        mViews.clear();
    }
}
