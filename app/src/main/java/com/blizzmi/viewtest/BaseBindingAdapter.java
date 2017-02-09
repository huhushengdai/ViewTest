package com.blizzmi.viewtest;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.annotation.LayoutRes;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

/**
 * Date： 2016/7/14
 * Description:
 * ListView的公共适配器使用 data binding 绑定
 * 在 bindingData 方法中进行数据绑定
 * 泛型说明：
 * T：表示绑定数据的实体，需要继承BaseObservable
 * B：binding,绑定实例，用于绑定数据，根据不同的xml会生成不同的binding类，所以这里使用泛型
 * @author WangLizhi
 * @version 1.0
 */
public abstract class BaseBindingAdapter<T, B extends ViewDataBinding>
        extends BaseAdapter {
    protected Context mContext;
    protected List<T> mData;
    protected int mLayoutId;


    public BaseBindingAdapter(Context context, List<T> data, @LayoutRes int layoutId) {
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

        bindingData(position, holder.getBinding(), parent);
        return view;
    }

    /**
     * 更新Item
     * 替换绑定的data
     */
    public abstract void bindingData(int position, B binding, ViewGroup parent);

    /**
     * ListView 的  ViewHolder
     */
    public class BaseViewHolder {

        private B mBinding;

        public BaseViewHolder(View itemView) {
            mBinding = DataBindingUtil.bind(itemView);
        }

        public B getBinding() {
            return mBinding;
        }
    }

}
