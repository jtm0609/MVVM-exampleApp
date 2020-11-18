package com.jtmcompany.mvvmrecyclerapp;

import android.app.Application;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.room.Room;

import com.jtmcompany.mvvmrecyclerapp.model.ItemModel;
import com.jtmcompany.mvvmrecyclerapp.model.PageListModel;
import com.jtmcompany.mvvmrecyclerapp.retrofit.RetrofitFactory;
import com.jtmcompany.mvvmrecyclerapp.retrofit.RetrofitService;
import com.jtmcompany.mvvmrecyclerapp.room.AppDatabase;
import com.jtmcompany.mvvmrecyclerapp.room.ArticleDAO;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyViewModel extends AndroidViewModel {
    private static final String QUERY="travel";
    private static final String API_KIY="6ce7a035900b42ef8dc91da10cc158c4";


    RetrofitService networkService= RetrofitFactory.create();
    Application application;
    ArticleDAO dao;
    public MyViewModel(@NonNull Application application) {
        super(application);
        AppDatabase db= Room.databaseBuilder(application,AppDatabase.class,"myDB").build();
        dao=db.getArticleDAO();
        this.application=application;
        Log.d("tag","start");
    }

    public MutableLiveData<List<ItemModel>> getNews(){

        //네트워크 상태 파악
        ConnectivityManager connectivityManager=(ConnectivityManager) application.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo=connectivityManager.getActiveNetworkInfo();
        if(networkInfo!=null) return getNewsFromNetWork();
        else{
            MutableLiveData<List<ItemModel>> liveData= new MutableLiveData<>();
            new GetAllThread(liveData).start();
            return liveData;
        }
    }

    private MutableLiveData<List<ItemModel>> getNewsFromNetWork(){
        MutableLiveData<List<ItemModel>> liveData=new MutableLiveData<>();
        //retrofit을 통해 요청
        networkService.getList(QUERY,API_KIY,1,10)
                .enqueue(new Callback<PageListModel>() {
                    @Override
                    public void onResponse(Call<PageListModel> call, Response<PageListModel> response) {
                        //받는것이 성공적이었다면
                        if(response.isSuccessful()){
                            liveData.postValue(response.body().articles);
                            new InsertDataThread(response.body().articles).start();
                            Log.d("tag","성공");
                        }
                    }

                    @Override
                    public void onFailure(Call<PageListModel> call, Throwable t) {
                        Log.d("tag","실패");
                    }
                });
        return liveData;
    }


class GetAllThread extends Thread{
        MutableLiveData<List<ItemModel>>liveData;
        public GetAllThread(MutableLiveData<List<ItemModel>> liveData){
            this.liveData=liveData;
        }

    @Override
    public void run() {
        super.run();
        List<ItemModel> daoData=dao.getAll();
        liveData.postValue(daoData);
    }
}

class InsertDataThread extends Thread{
        List<ItemModel> articles;
        public InsertDataThread(List<ItemModel>articles){
            this.articles=articles;
        }

    @Override
    public void run() {
        super.run();
        dao.deleteAll();
        dao.insertAll(articles.toArray(new ItemModel[articles.size()]));
    }
}
}
