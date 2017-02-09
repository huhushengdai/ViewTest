//package com.blizzmi.recycler;
//
//import android.content.Context;
//import android.databinding.DataBindingUtil;
//import android.databinding.ViewDataBinding;
//import android.support.annotation.LayoutRes;
//import android.support.v7.widget.RecyclerView;
//import android.view.View;
//import android.view.ViewGroup;
//
//import java.util.List;
//
///**
// * Date： 2016/9/26
// * Description:
// *
// * @author WangLizhi
// * @version 1.0
// */
//public abstract class BaseRecyclerAdapter<T,B extends ViewDataBinding> extends RecyclerView.Adapter<BaseRecyclerAdapter.BaseViewHolder>{
//    protected Context mContext;
//    protected List<T> mData;
//    protected int mLayoutId;
//
//
//    public BaseRecyclerAdapter(Context context, List<T> data, @LayoutRes int layoutId) {
//        this.mContext = context;
//        this.mData = data;
//        this.mLayoutId = layoutId;
//    }
//
//
//    @Override
//    /**
//     * 创建ViewHolder
//     */
//    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        return new BaseViewHolder(View.inflate(mContext,mLayoutId,null));
//    }
////    @Override
////    public void onBindViewHolder(BaseViewHolder holder, int position) {
////        bindData(holder, position);
////    }
////
////    public abstract void bindData(BaseViewHolder holder,int position);
//
//    @Override
//    public int getItemCount() {
//        return mData == null ? 0 : mData.size();
//    }
//
//    public class BaseViewHolder extends RecyclerView.ViewHolder{
//
//        B binding;
//
//        public BaseViewHolder(View itemView) {
//            super(itemView);
//            binding = DataBindingUtil.bind(itemView);
//        }
//    }
//}
