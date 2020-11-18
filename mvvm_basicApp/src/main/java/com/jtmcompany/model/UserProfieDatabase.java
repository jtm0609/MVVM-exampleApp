package com.jtmcompany.model;

import androidx.room.Database;
import androidx.room.RoomDatabase;

//데이터베이스 이용을 위한 DAO 객체 획득 함수를 제공하는 클래스
@Database(entities = {UserProfile.class},version = 1)
public abstract class UserProfieDatabase extends RoomDatabase {
    public abstract UserProfileDao getUserProfileDao();
}
