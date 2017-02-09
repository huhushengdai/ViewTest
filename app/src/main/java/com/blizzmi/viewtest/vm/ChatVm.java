package com.blizzmi.viewtest.vm;

import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;

/**
 * Date： 2017/1/4
 * Description:
 *
 * @author WangLizhi
 * @version 1.0
 */
public class ChatVm extends BaseVm {
    public final ObservableField<String> text = new ObservableField<>("bbbbbb");
    public final ObservableBoolean isShow = new ObservableBoolean(false);
}
