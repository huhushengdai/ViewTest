package com.blizzmi.horizontalrecycler;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

/**
 * Date： 2016/10/14
 * Description:
 *
 * @author WangLizhi
 * @version 1.0
 */
public class ListAdapter extends BaseAdapter{
    private Context mContext;

    public ListAdapter(Context context) {
        this.mContext = context;
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        ListViewHolder holder;
        if (view ==null){
            view = View.inflate(mContext,R.layout.item_view,null);
            holder = new ListViewHolder(view);
            view.setTag(holder);
        }else {
            holder = (ListViewHolder) view.getTag();
        }
        if (position%2==0){
            holder.textView.setBackgroundColor(Color.GREEN);
            ViewGroup.LayoutParams params = holder.textView.getLayoutParams();
            params.width = 1080;
            holder.textView.setLayoutParams(params);
        }
        return view;
    }

    class ListViewHolder{
        TextView textView;
        public ListViewHolder(View convertView){
            textView = (TextView) convertView.findViewById(R.id.item_text);
        }
    }
}
