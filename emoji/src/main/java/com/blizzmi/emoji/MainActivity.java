package com.blizzmi.emoji;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.blizzmi.emoji.databinding.ActivityMainBinding;
import com.blizzmi.emoji.vm.EditVm;

public class MainActivity extends AppCompatActivity {
    EditVm vm = new EditVm();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding binding =  DataBindingUtil.setContentView(this,R.layout.activity_main);
        binding.setEdit(vm);
        String text = "我崽崽，后面几次重新才喊做惨，我跟罗淳文还有他们班几个一起重修";
        String su = text.substring(0,16);
        System.out.println(su);
    }


}
