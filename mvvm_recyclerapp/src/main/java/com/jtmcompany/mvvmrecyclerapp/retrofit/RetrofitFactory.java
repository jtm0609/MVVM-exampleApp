package com.jtmcompany.mvvmrecyclerapp.retrofit;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitFactory {
    private static String BASE_URL="https://newsapi.org";
    public static RetrofitService create(){
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        //create하면 내부에서 인터페이스를 구현해서 객체를 반환해준다
        return retrofit.create(RetrofitService.class);
    }
}
