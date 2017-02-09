package com.blizzmi.horizontalrecycler;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Date： 2016/10/14
 * Description:
 *
 * @author WangLizhi
 * @version 1.0
 */
public class HAdapter extends RecyclerView.Adapter<HAdapter.BaseViewHolder>{
    private Context mContext;

    public HAdapter(Context context) {
        this.mContext = context;
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new BaseViewHolder(View.inflate(mContext,R.layout.item_view,null));
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        if (position%2==0){
            TextView textView = (TextView) holder.itemView.findViewById(R.id.item_text);
            textView.setBackgroundColor(Color.GREEN);
            ViewGroup.LayoutParams params = textView.getLayoutParams();
            params.width = 1080;
            textView.setLayoutParams(params);
        }
    }

    @Override
    public int getItemCount() {
        return 2;
    }

    class BaseViewHolder extends RecyclerView.ViewHolder{
        View itemView;

        public BaseViewHolder(View itemView) {
            super(itemView);
            this.itemView = itemView;
        }
    }
}
