package com.jtmcompany.mvvmrecyclerapp.room;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.jtmcompany.mvvmrecyclerapp.model.ItemModel;

@Database(entities ={ItemModel.class},version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract ArticleDAO getArticleDAO();
}
