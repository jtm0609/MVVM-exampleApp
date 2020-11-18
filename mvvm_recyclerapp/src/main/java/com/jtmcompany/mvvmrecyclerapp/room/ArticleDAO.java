package com.jtmcompany.mvvmrecyclerapp.room;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.jtmcompany.mvvmrecyclerapp.model.ItemModel;

import java.util.List;

@Dao
public interface ArticleDAO {
    @Insert
    void insertAll(ItemModel... users);

    @Query("SELECT * FROM article")
    List<ItemModel> getAll();

    @Query("DELETE FROM article")
    void deleteAll();

}
