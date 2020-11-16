package com.jtmcompany.ViewModel;

import android.app.Application;
import android.os.AsyncTask;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.room.Room;

import com.jtmcompany.Model.UserProfile;
import com.jtmcompany.Model.UserProfieDatabase;
import com.jtmcompany.Model.UserProfileDao;

import java.util.List;

public class UserProfileViewModel extends AndroidViewModel {
    public LiveData<List<UserProfile>> userProfileList;
    private UserProfileDao userProfileDao;
    public UserProfileViewModel(@NonNull Application application) {
        super(application);
        UserProfieDatabase db= Room.databaseBuilder(application,UserProfieDatabase.class,"userProfile").build();
        userProfileDao=db.getUserProfileDao();

        //라이브데이터를 db에 연결
        userProfileList=userProfileDao.getAll();
        Log.d("tag","UserProfileViewModel");
    }

    public void insert(UserProfile userProfile){
        new InsertUserProfileAsyncTask().execute(userProfile);
    }

    public void update(UserProfile userProfile){
        new UpdateUserProfileAsyncTask().execute(userProfile);
    }

    public void delete(UserProfile userProfile){
        new DeleteUserProfileAsyncTask().execute(userProfile);
    }



    private class InsertUserProfileAsyncTask extends AsyncTask<UserProfile,Void,Void>{

        @Override
        protected Void doInBackground(UserProfile... userProfiles) {
            userProfileDao.insert(userProfiles[0]);
            return null;
        }
    }

    private  class UpdateUserProfileAsyncTask extends AsyncTask<UserProfile,Void,Void>{
        @Override
        protected Void doInBackground(UserProfile... userProfiles) {
            userProfileDao.update(userProfiles[0]);
            return null;
        }
    }

    private class DeleteUserProfileAsyncTask extends AsyncTask<UserProfile,Void,Void>{
        @Override
        protected Void doInBackground(UserProfile... userProfiles) {
            userProfileDao.delete(userProfiles[0]);
            return null;
        }
    }



}
