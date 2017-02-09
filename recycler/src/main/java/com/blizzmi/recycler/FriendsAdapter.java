//package com.blizzmi.recycler;
//
//import android.content.Context;
//
//import com.blizzmi.recycler.databinding.ItemFriendsBinding;
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
//public class FriendsAdapter extends BaseRecyclerAdapter<FriendsVm,ItemFriendsBinding>{
//    public FriendsAdapter(Context context, List<FriendsVm> data) {
//        super(context, data, R.layout.item_friends);
//    }
//
//    @Override
//    public void onBindViewHolder(BaseViewHolder holder, int position) {
//        holder.binding.setFriend(mData.get(position));
//    }
//
////    @Override
////    public void bindData(BaseViewHolder holder, int position) {
////        holder.binding.setFriend(mData.get(position));
////    }
//}
