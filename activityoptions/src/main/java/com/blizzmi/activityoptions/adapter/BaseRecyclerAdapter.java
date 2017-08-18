package com.blizzmi.activityoptions.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.graphics.Bitmap;
import android.support.annotation.ColorInt;
import android.support.annotation.DrawableRes;
import android.support.annotation.IdRes;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.blizzmi.activityoptions.annotation.LayoutId;

import java.util.List;

/**
 * Date： 2017/2/5
 * Description:
 *
 * @author WangLizhi
 * @version 1.0
 */
public abstract class BaseRecyclerAdapter<T> extends
        RecyclerView.Adapter<BaseRecyclerAdapter.BaseHolder> {
    protected Context mContext;
    protected List<T> mData;
    protected LayoutInflater mInflater;

    protected BaseRecyclerAdapter.ChildLongClickListener mChildLongListener;//子view长按点击事件
    protected ChildClickListener mChildClickListener;//子view监听事件
    private ItemClickListener mItemClick;//item 点击事件
    private ItemLongClickListener mItemLongClick;//item 长按点击事件


    public BaseRecyclerAdapter(Context context, List<T> data) {
        this.mContext = context;
        this.mData = data;
        mInflater = LayoutInflater.from(context);
    }

    /**
     * 获取布局id
     *
     * @return layoutId
     */
    public int getLayoutId(int viewType) {
        LayoutId layoutId = this.getClass().getAnnotation(LayoutId.class);
        if (layoutId == null) {
            return 0;
        } else {
            return layoutId.value();
        }
    }


    @Override
    public BaseRecyclerAdapter.BaseHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new BaseHolder(mInflater.inflate(getLayoutId(viewType), parent, false));
    }


    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size();
    }

    public void setItemClick(ItemClickListener itemClick) {
        this.mItemClick = itemClick;
    }

    public void setItemLongClick(ItemLongClickListener itemLongClick) {
        this.mItemLongClick = itemLongClick;
    }

    /**
     * 设置子view点击监听事件
     *
     * @param listener 子view点击监听事件
     */
    public void setChildClickListener(ChildClickListener listener) {
        mChildClickListener = listener;
    }

    /**
     * 设置子View 长按点击监听事件
     *
     * @param listener 监听事件
     */
    public void setChildLongListener(BaseRecyclerAdapter.ChildLongClickListener listener) {
        this.mChildLongListener = listener;
    }

    //---------------------------interface---------------------------------

    /**
     * item点击事件
     */
    public interface ItemClickListener {
        void onItemClick(View itemView, int position);
    }

    public interface ItemLongClickListener {
        boolean onItemLongClick(View itemView, int position);
    }


    public interface ChildClickListener {
        void childOnClick(View parent, View childView, int position);
    }

    public interface ChildLongClickListener {
        boolean childLongClick(View parent, View childView, int position);
    }

    public class BaseHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener, View.OnLongClickListener {
        private ViewDataBinding mBinding;

        public BaseHolder(View itemView) {
            super(itemView);
            try {
                //防止View 没有使用data binding
                mBinding = DataBindingUtil.bind(itemView);
            } catch (Exception e) {
                e.printStackTrace();
            }
            //item 点击事件
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mItemClick != null) {
                        mItemClick.onItemClick(v, getAdapterPosition());
                    }
                }
            });
            //item 长按点击事件
            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    if (mItemLongClick != null) {
                        return mItemLongClick.onItemLongClick(v, getAdapterPosition());
                    }
                    return false;
                }
            });
        }

        public ViewDataBinding getBinding() {
            return mBinding;
        }
        //----------------------子view设置--------------------------------

        public View findViewById(@IdRes int childId) {
            return itemView.findViewById(childId);
        }

        public void setBackgroundResource(@IdRes int childId, @DrawableRes int resid) {
            View child = itemView.findViewById(childId);
            if (child != null) {
                child.setBackgroundResource(resid);
            }
        }

        public void setText(@IdRes int childId, CharSequence text) {
            View child = itemView.findViewById(childId);
            if (child != null && child instanceof TextView) {
                ((TextView) child).setText(text);
            }
        }

        public void setText(@IdRes int childId, byte text) {
            View child = itemView.findViewById(childId);
            if (child != null && child instanceof TextView) {
                ((TextView) child).setText(text);
            }
        }

        public void setText(@IdRes int childId, int text) {
            View child = itemView.findViewById(childId);
            if (child != null && child instanceof TextView) {
                ((TextView) child).setText(text);
            }
        }

        public void setTextColor(@IdRes int childId, @ColorInt int color) {
            View child = itemView.findViewById(childId);
            if (child != null && child instanceof TextView) {
                ((TextView) child).setTextColor(color);
            }
        }

        public void setTextSize(@IdRes int childId, float size) {
            View child = itemView.findViewById(childId);
            if (child != null && child instanceof TextView) {
                ((TextView) child).setTextSize(size);
            }
        }

        public void setImage(@IdRes int childId, @DrawableRes int resId) {
            View child = itemView.findViewById(childId);
            if (child != null && child instanceof ImageView) {
                ((ImageView) child).setImageResource(resId);
            }
        }

        public void setVisibility(@IdRes int childId, int visibility) {
            View child = itemView.findViewById(childId);
            if (child != null) {
                child.setVisibility(visibility);
            }
        }

        public View getView(@IdRes int childId) {
            return itemView.findViewById(childId);
        }


        public void setImage(@IdRes int childId, Bitmap bm) {
            View child = itemView.findViewById(childId);
            if (child != null && child instanceof ImageView) {
                ((ImageView) child).setImageBitmap(bm);
            }
        }

        /**
         * 设置子view宽高
         *
         * @param childId
         * @param width
         * @param height
         */
        public void setWidthAndHeight(@IdRes int childId, int width, int height) {
            View child = itemView.findViewById(childId);
            if (child != null) {
                ViewGroup.LayoutParams params = child.getLayoutParams();
                params.width = width;
                params.height = height;
                child.setLayoutParams(params);
            }
        }

        public void setDrawables(@IdRes int childId,
                                 @DrawableRes int left,
                                 @DrawableRes int top,
                                 @DrawableRes int right,
                                 @DrawableRes int bottom) {
            View child = itemView.findViewById(childId);
            if (child != null && child instanceof TextView) {
                ((TextView) child).
                        setCompoundDrawablesWithIntrinsicBounds(left, top, right, bottom);
            }
        }

        //----------------------------------监听事件-----------------------------

        /**
         * 给子view添加点击事件监听
         *
         * @param childId 需要添加监听的子view
         */
        public void setChildListener(@IdRes int childId) {
            View child = itemView.findViewById(childId);
            if (child != null) {
                itemView.findViewById(childId).setOnClickListener(this);
            }
        }

        public void cancelChildListener(@IdRes int childId) {
            View child = itemView.findViewById(childId);
            if (child != null) {
                child.setOnClickListener(null);
            }
        }

        /**
         * 设置子view 长按点击事件
         *
         * @param childId 子view
         */
        public void setChildLongListener(@IdRes int childId) {
            View child = itemView.findViewById(childId);
            if (child != null) {
                child.setOnLongClickListener(this);
            }
        }

        @Override
        public void onClick(View v) {
            if (mChildClickListener != null) {
                mChildClickListener.childOnClick(itemView, v, getAdapterPosition());
            }
        }

        @Override
        public boolean onLongClick(View v) {
            if (mChildLongListener != null) {
                return mChildLongListener.childLongClick(itemView, v, getAdapterPosition());
            }
            return false;
        }
    }
}
