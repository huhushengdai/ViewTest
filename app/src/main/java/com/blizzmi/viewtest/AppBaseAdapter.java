package com.blizzmi.viewtest;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.annotation.ColorInt;
import android.support.annotation.DrawableRes;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Date： 2016/7/14
 * Description:
 * ListView的公共适配器
 * 只需要实现 initView方法，进行UI更新
 * 不需要每一个adapter都写ViewHolder
 * 更新子view时，只需要调用BaseViewHolder里面的方法，指定需要更新的子viewID跟内容即可
 *
 * @author WangLizhi
 * @version 1.0
 */
public abstract class AppBaseAdapter<T> extends BaseAdapter {
    protected Context mContext;
    protected List<T> mData;
    protected int mLayoutId;

    private ChildClickListener mChildClickListener;//子view监听事件

    public AppBaseAdapter(Context context, List<T> data, @LayoutRes int layoutId) {
        this.mContext = context;
        this.mData = data;
        this.mLayoutId = layoutId;
    }


    @Override
    public int getCount() {
        return mData == null ? 0 : mData.size();
    }

    @Override
    public Object getItem(int position) {
        return mData == null ? null : mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        BaseViewHolder holder = null;
        if (view == null) {
            view = View.inflate(mContext, mLayoutId, null);
            holder = new BaseViewHolder(view);
            view.setTag(holder);
        } else {
            holder = (BaseViewHolder) view.getTag();
        }
        holder.setPosition(position);
        refreshView(position, holder, parent);
        return view;
    }

    /**
     * 更新Item
     */
    public abstract void refreshView(int position, BaseViewHolder holder, ViewGroup parent);

    /**
     * 设置子view点击监听事件
     *
     * @param listener 子view点击监听事件
     */
    public void setChildClickListener(ChildClickListener listener) {
        mChildClickListener = listener;
    }

    /**
     * ListView 的  ViewHolder
     */
    public class BaseViewHolder implements View.OnClickListener {
        private View mConvertView;
        private int mPosition;

        public BaseViewHolder(View itemView) {
            mConvertView = itemView;
        }

        /**
         * 设置当前选项
         *
         * @param position 当前选项
         */
        public void setPosition(int position) {
            mPosition = position;
        }


        //----------------------子view设置--------------------------------

        public View findViewById(@IdRes int childId){
            return mConvertView.findViewById(childId);
        }

        public void setText(@IdRes int childId, CharSequence text) {
            ((TextView) mConvertView.findViewById(childId)).setText(text);
        }

        public void setText(@IdRes int childId, byte text) {
            ((TextView) mConvertView.findViewById(childId)).setText(text);
        }

        public void setText(@IdRes int childId, int text) {
            ((TextView) mConvertView.findViewById(childId)).setText(text);
        }

        public void setTextColor(@IdRes int childId, @ColorInt int color) {
            ((TextView) mConvertView.findViewById(childId)).setTextColor(color);
        }

        public void setTextSize(@IdRes int childId, float size) {
            ((TextView) mConvertView.findViewById(childId)).setTextSize(size);
        }

        public void setImage(@IdRes int childId, @DrawableRes int resId) {
            ((ImageView) mConvertView.findViewById(childId)).setImageResource(resId);
        }

        public void setVisibility(@IdRes int childId, int visibility) {
            mConvertView.findViewById(childId).setVisibility(visibility);
        }

        public View getView(@IdRes int childId) {
            return mConvertView.findViewById(childId);
        }


        public void setImage(@IdRes int childId, Bitmap bm) {
            ((ImageView) mConvertView.findViewById(childId)).setImageBitmap(bm);
        }

        public void setDrawables(@IdRes int childId,
                                 @DrawableRes int left,
                                 @DrawableRes int top,
                                 @DrawableRes int right,
                                 @DrawableRes int bottom) {
            ((TextView) mConvertView.findViewById(childId)).
                    setCompoundDrawablesWithIntrinsicBounds(left, top, right, bottom);
        }


        //----------------------------------监听事件-----------------------------

        /**
         * 给子view添加点击事件监听
         *
         * @param childId 需要添加监听的子view
         */
        public void setChildListener(@IdRes int childId) {
            mConvertView.findViewById(childId).setOnClickListener(this);
        }

        /**
         * 取消子view的点击监听
         *
         * @param childId 需要取消监听的子view
         */
        public void cancelChildListener(@IdRes int childId) {
            mConvertView.findViewById(childId).setOnClickListener(null);
        }

        @Override
        public void onClick(View v) {
            if (mChildClickListener != null) {
                mChildClickListener.childOnClick(mConvertView, v, mPosition, mData.get(mPosition));
            }
        }

    }

    public interface ChildClickListener {
        void childOnClick(View parent, View childView, int position, Object data);
    }
}
