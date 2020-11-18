package com.jtmcompany.mvvmrecyclerapp;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.jtmcompany.mvvmrecyclerapp.databinding.ItemMainBinding;
import com.jtmcompany.mvvmrecyclerapp.model.ItemModel;

import java.util.List;

public class Myadapter extends RecyclerView.Adapter<Myadapter.ItemViewHolder>{
    List<ItemModel> articles;

    public Myadapter(List<ItemModel> articles) {
        this.articles = articles;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //바인딩은 inflate할떄 아이템레이아웃을 생략가능
        ItemMainBinding binding=ItemMainBinding.
                inflate(LayoutInflater.from(parent.getContext()),parent,false);

        //뷰홀더로 매개변수로 바인딩을 넘겨준다
        return new ItemViewHolder(binding);

    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        ItemModel model= articles.get(position);
        //xml과 데이터바인딩
        holder.binding.setItem(model);
    }

    @Override
    public int getItemCount() {
        return articles.size();
    }

    //첫번째 매개변수는 xml에서 이것을 사용하기위해 정의된뷰, 두번쨰매개변수는 xml에서 정의한 속성값
    @BindingAdapter("bind:publishedAt")
    public static void publishedAt(TextView view, String date){
        view.setText(AppUtils.getDate(date)+" at "+AppUtils.getTime(date));
    }

    @BindingAdapter("bind:urlToImage")
    public static void urlToImage(ImageView view, String url){
        Glide.with(MainActivity.getContext()).load(url).override(250,200).into(view);
    }


    static class ItemViewHolder extends RecyclerView.ViewHolder{
        ItemMainBinding binding;
        public ItemViewHolder(ItemMainBinding binding) {
            super(binding.getRoot());
            this.binding=binding;
        }
    }
}
