package com.blizzmi.viewtest;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.view.ViewGroup;

import com.blizzmi.viewtest.databinding.LayoutMsgSendBinding;
import com.blizzmi.viewtest.vm.ChatVm;

import java.util.List;

/**
 * Date： 2017/1/9
 * Description:
 *
 * @author WangLizhi
 * @version 1.0
 */
public class ChatAdapter extends BaseBindingAdapter<String,LayoutMsgSendBinding>{


    public ChatAdapter(Context context, List<String> data, @LayoutRes int layoutId) {
        super(context, data, layoutId);
    }

    @Override
    public void bindingData(int position, LayoutMsgSendBinding binding, ViewGroup parent) {
        ChatVm vm = new ChatVm();
        vm.text.set(mData.get(position));
        binding.setVm(vm);
    }
}
