package com.jtmcompany.model;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

//DBMS를 위해 호출되는 함수를 선언하는 인터페이스
@Dao
public interface UserProfileDao {
    @Insert
    void insert(UserProfile userProfile);

    @Update
    void update(UserProfile userProfile);

    @Delete
    void delete(UserProfile userProfile);



    @Query("SELECT * FROM UserProfile")
    LiveData<List<UserProfile>> getAll();
}
