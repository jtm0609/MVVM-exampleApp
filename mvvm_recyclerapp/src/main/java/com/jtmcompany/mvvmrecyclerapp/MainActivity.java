package com.jtmcompany.mvvmrecyclerapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.BindingAdapter;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jtmcompany.mvvmrecyclerapp.model.ItemModel;
import com.jtmcompany.mvvmrecyclerapp.databinding.ActivityMainBinding;
import com.jtmcompany.mvvmrecyclerapp.databinding.ItemMainBinding;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    private static Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context=this;
        //데이터바인딩
        binding= DataBindingUtil.setContentView(this,R.layout.activity_main);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));

        MyViewModel viewModel= new ViewModelProvider(this).get(MyViewModel.class);
        viewModel.getNews().observe(this, new Observer<List<ItemModel>>() {
            @Override
            public void onChanged(List<ItemModel> itemModels) {
                Myadapter myadapter=new Myadapter(itemModels);
                binding.recyclerView.setAdapter(myadapter);

            }
        });



    }
    public static Context getContext(){
        return context;
    }


}


