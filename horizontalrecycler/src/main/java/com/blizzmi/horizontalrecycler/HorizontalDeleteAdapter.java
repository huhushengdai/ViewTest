package com.blizzmi.horizontalrecycler;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.TextView;

import com.blizzmi.horizontalrecycler.databinding.ItemContentBinding;

import java.util.HashMap;
import java.util.List;

/**
 * Date： 2016/10/14
 * Description:
 *
 * @author WangLizhi
 * @version 1.0
 */
public class HorizontalDeleteAdapter extends RecyclerView.Adapter<HorizontalDeleteAdapter.BaseViewHolder> {
    private static final int LAYOUT_CONTENT = R.layout.item_content;
    private static final int LAYOUT_DELETE = R.layout.item_delete;
    private static final int LAYOUT_CONTAINER = R.layout.item_horizontal;

    private int screenWidth;

    private Context mContext;
    private List<String> mData;
    private HashMap<Integer, RecyclerView> map;

    public HorizontalDeleteAdapter(Context context, List<String> data) {
        this.mContext = context;
        this.mData = data;
        map = new HashMap<>();
        WindowManager wm = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);

        screenWidth = wm.getDefaultDisplay().getWidth();
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new BaseViewHolder(View.inflate(mContext, LAYOUT_CONTAINER, null));
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, final int position) {
        final RecyclerView recycler = (RecyclerView) holder.itemView.findViewById(R.id.item_recycler);
        LinearLayoutManager manager = new LinearLayoutManager(mContext);
        manager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recycler.setLayoutManager(manager);
        map.put(position, recycler);
        recycler.setAdapter(new HorizontalAdapter(position));
        recycler.setOnTouchListener(new View.OnTouchListener() {
            float down;

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    down = event.getX();
                    for (HashMap.Entry<Integer, RecyclerView> entry : map.entrySet()) {
                        if (entry.getKey() != position) {
                            entry.getValue().scrollToPosition(0);
                        }
                    }
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    recycler.scrollToPosition(down - event.getX() > 0 ? 1 : 0);
                }
                return false;
            }
        });
    }

    @Override
    public int getItemCount() {
        return mData != null && mData.size() > 0 ? mData.size() : 0;
    }

    class BaseViewHolder extends RecyclerView.ViewHolder {
        View itemView;

        public BaseViewHolder(View itemView) {
            super(itemView);
            this.itemView = itemView;
        }
    }
    class ContentViewHolder extends BaseViewHolder {
        View itemView;

        public ContentViewHolder(View itemView) {
            super(itemView);
            this.itemView = itemView;
            ItemContentBinding binding = DataBindingUtil.bind(itemView);
        }
    }

    class HorizontalAdapter extends RecyclerView.Adapter<BaseViewHolder> implements View.OnClickListener {
        private static final int COUNT = 2;//横向布局数量
        //布局类型
        private static final int TYPE_CONTENT = 1;
        private static final int TYPE_DELETE = 2;
        //
        private int currentPosition;

        public HorizontalAdapter(int position) {
            this.currentPosition = position;
        }

        @Override
        public int getItemViewType(int position) {
            return position % 2 == 0 ? TYPE_CONTENT : TYPE_DELETE;
        }

        @Override
        public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return viewType == TYPE_CONTENT ?
                    new ContentViewHolder(View.inflate(mContext, LAYOUT_CONTENT, null)) :
                    new BaseViewHolder(View.inflate(mContext, LAYOUT_DELETE, null));
        }

        @Override
        public void onBindViewHolder(BaseViewHolder holder, int position) {
            if (position == 0) {
                TextView textView = (TextView) holder.itemView.findViewById(R.id.item_content_text);
                textView.setBackgroundColor(Color.GREEN);
                ViewGroup.LayoutParams params = textView.getLayoutParams();
                params.width = screenWidth;
                textView.setLayoutParams(params);
                textView.setText(mData.get(currentPosition));
            } else if (position == 1) {
                holder.itemView.findViewById(R.id.item_delete_text).setOnClickListener(this);
            }
        }

        @Override
        public int getItemCount() {
            return COUNT;
        }

        @Override
        public void onClick(View v) {
            mData.remove(currentPosition);
            HorizontalDeleteAdapter.this.notifyDataSetChanged();
        }
    }
}
