package com.blizzmi.recycler;

import android.databinding.ObservableField;

import java.util.Observable;

/**
 * Date： 2016/9/26
 * Description:
 *
 * @author WangLizhi
 * @version 1.0
 */
public class FriendsVm extends Observable{
    public ObservableField<String> name;
    public FriendsVm(String name){
        this.name = new ObservableField<>(name);
    }

}
