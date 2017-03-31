package com.blizzmi.recycler.widget;

import android.content.Context;
import android.graphics.Point;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

/**
 * Date： 2017/3/31
 * Description:
 * 侧滑菜单view
 *
 * @author WangLizhi
 * @version 1.0
 */
public class SlidingMenuView extends RecyclerView {

    private View mMenuView;//侧滑菜单的布局
    private View mContentView;//内容布局
    private int mMenuWidth;//菜单的宽度 默认为屏幕宽度80%,可以设置
    private int mContentWidth;//内容的宽度 一般默认为屏幕宽度
    private float mMove;//手指滚动的位移
    private float mDown;//手指按下的坐标
    private boolean mIsShow;//是否显示菜单
    private SlidingMenuAdapter mAdapter;
    private OnStateChangeListener mChangeListener;//状态改变监听

    public SlidingMenuView(Context context) {
        this(context, null);
    }

    public SlidingMenuView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        //获取屏幕宽度
        WindowManager wm = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        Point point = new Point();
        wm.getDefaultDisplay().getSize(point);
        mContentWidth = point.x;
        mMenuWidth = (int) (mContentWidth * 0.8);
    }

    public void setLayout(View menuView, View contentView) {
        mMenuView = menuView;
        mContentView = contentView;
        if (mAdapter == null) {
            setLayoutManager(new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.HORIZONTAL));
            mAdapter = new SlidingMenuAdapter();
            setAdapter(mAdapter);
            addOnScrollListener(mScrollListener);
        } else {
            mAdapter.notifyDataSetChanged();
        }
        scrollToPosition(1);
    }

    public void openMenu() {
        smoothScrollToPosition(0);
        mIsShow = true;
        sendStateChange();
    }

    public void closeMenu() {
        smoothScrollToPosition(1);
        mIsShow = false;
        sendStateChange();
    }

    private void sendStateChange() {
        if (mChangeListener != null) {
            mChangeListener.onChange(mIsShow);
        }
    }

    public void setChangeListener(OnStateChangeListener changeListener) {
        this.mChangeListener = changeListener;
    }

    /**
     * 获取是否显示菜单
     *
     * @return true 显示菜单
     */
    public boolean isShow() {
        return mIsShow;
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent e) {
        switch (e.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mDown = e.getX();
                break;
            case MotionEvent.ACTION_MOVE:
                mMove = e.getX() - mDown;
                break;
        }
        return super.dispatchTouchEvent(e);
    }

    /**
     * 滚动监听
     */
    private OnScrollListener mScrollListener = new OnScrollListener() {
        private static final String TAG = "OnScrollListener";
        private int previousState = -1;//前一个状态

        @Override
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            Log.i(TAG, "state:" + newState);
            switch (newState) {
                case SCROLL_STATE_SETTLING:
                    //快速滑动
                    if (previousState == SCROLL_STATE_DRAGGING) {
                        if (mMove < 0) {
                            closeMenu();
                        } else {
                            openMenu();
                        }
                    }
                    break;
                case SCROLL_STATE_IDLE:
                    if (previousState == SCROLL_STATE_DRAGGING) {
                        if (mMove > 350 || (mMove < 0 && mMove > -350)) {
                            openMenu();
                        } else {
                            closeMenu();
                        }
                    }
                    break;
            }
            previousState = newState;
        }

        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        }
    };

    //---------------------------------interface---------------------------

    /**
     * 菜单栏状态改变监听
     */
    public interface OnStateChangeListener {
        void onChange(boolean isShowMenu);
    }


    //---------------------------------adapter------------------------------

    private class SlidingMenuAdapter extends RecyclerView.Adapter<SlidingViewHolder> {
        private static final int TYPE_MENU = 1;//菜单
        private static final int TYPE_CONTENT = 0;//内容

        @Override
        public SlidingViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return viewType == TYPE_MENU ?
                    new SlidingViewHolder(mMenuView)
                    : new SlidingViewHolder(mContentView)
                    ;
        }

        @Override
        public int getItemViewType(int position) {
            return position == 0 ? TYPE_MENU : TYPE_CONTENT;
        }

        @Override
        public void onBindViewHolder(SlidingViewHolder holder, int position) {
            int width;
            if (getItemViewType(position) == TYPE_MENU) {
                //侧滑菜单 宽度设置
                width = mMenuWidth;
            } else {
                //内容 宽度设置
                width = mContentWidth;
            }
            holder.itemView.setLayoutParams(new ViewGroup.LayoutParams(width, -1));
        }

        @Override
        public int getItemCount() {
            return 2;
        }
    }

    private class SlidingViewHolder extends ViewHolder {

        public SlidingViewHolder(View itemView) {
            super(itemView);
        }
    }
}
