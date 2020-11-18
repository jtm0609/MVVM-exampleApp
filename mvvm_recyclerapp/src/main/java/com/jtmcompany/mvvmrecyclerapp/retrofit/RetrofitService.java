package com.jtmcompany.mvvmrecyclerapp.retrofit;

import com.jtmcompany.mvvmrecyclerapp.model.PageListModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RetrofitService {
    @GET("/v2/everything")
    Call<PageListModel> getList(@Query("q") String q,
                                @Query("apiKey")String apiKey,
                                @Query("page")long page,
                                @Query("pageSize") int pageSize);
}
