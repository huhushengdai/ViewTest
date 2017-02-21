package com.blizzmi.emoji;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.blizzmi.emoji.databinding.ActivityMainBinding;
import com.blizzmi.emoji.vm.EditVm;

public class MainActivity extends AppCompatActivity {
    EditVm vm = new EditVm();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding binding =  DataBindingUtil.setContentView(this,R.layout.activity_main);
        binding.setEdit(vm);
    }

    public void click(View view){

        vm.edit.set(new String(Character.toChars(0x1f603)));
    }
}
