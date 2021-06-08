package com.example.webservicelist.RestApi;

import com.example.webservicelist.Models.Result;
import com.example.webservicelist.Models.UserList;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface RestApi {
    @GET("/list.php")
    Call<List<UserList>> getList();

    @FormUrlEncoded
    @POST("/delete.php")
    Call<Result> dltItem(@Field("id") String id);
}
