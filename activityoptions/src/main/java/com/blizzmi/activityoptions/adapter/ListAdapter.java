package com.blizzmi.activityoptions.adapter;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.blizzmi.activityoptions.R;

import java.util.List;

/**
 * Date： 2017/8/17
 * Description:
 *
 * @author WangLizhi
 * @version 1.0
 */
//@LayoutId(R.layout.item_list_left)
public class ListAdapter extends BaseRecyclerAdapter<Integer> {

    private static final double IMG_MIN_WIDTH = 300;//显示图片最小宽度
    private static final double IMG_MAX_WIDTH = 550;//显示图片最大宽度

    public ListAdapter(Context context, List<Integer> data) {
        super(context, data);
    }

    @Override
    public int getItemViewType(int position) {
        return mData.get(position) % 3 == 0 ? 0 : 1;
    }

    @Override
    public int getLayoutId(int viewType) {
        return viewType == 0 ? R.layout.item_list_left : R.layout.item_list_right;
    }

    @Override
    public void onBindViewHolder(BaseRecyclerAdapter.BaseHolder holder, int position) {
        holder.setChildListener(R.id.item_list_img);
//        holder.setChildListener(R.id.item_list_img_p);
        setImageViewSize((ImageView) holder.findViewById(R.id.item_list_img));
    }

    private void setImageViewSize(ImageView img) {
        //计算图片显示宽高
        int viewWidth;
        int viewHeight;
        int thuWidth = 195;
        if (thuWidth > IMG_MAX_WIDTH) {
            //图片宽度大于最大显示宽度
            viewWidth = (int) IMG_MAX_WIDTH;
            viewHeight = (int) (IMG_MAX_WIDTH / thuWidth * 260);
        } else if (thuWidth < IMG_MIN_WIDTH) {
            //图片宽度小于最小显示宽度
            viewWidth = (int) IMG_MIN_WIDTH;
            viewHeight = (int) (IMG_MIN_WIDTH / thuWidth * 260);
        } else {
            viewWidth = thuWidth;
            viewHeight = 260;
        }
        ViewGroup.LayoutParams params = img.getLayoutParams();
        params.width = viewWidth;
        params.height = viewHeight == 0 ? 450 : viewHeight;
        img.setLayoutParams(params);

    }
}
