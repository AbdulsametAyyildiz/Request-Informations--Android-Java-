package com.example.webservicelist.RestApi;

import com.example.webservicelist.Models.Result;
import com.example.webservicelist.Models.UserList;

import java.util.List;

import retrofit2.Call;

public class ManagerAll extends BaseManager{
    private static ManagerAll ourInstance = new ManagerAll();

    public static synchronized ManagerAll getInstance(){
        return ourInstance;
    }

    public Call<List<UserList>> getLists(){
        Call<List<UserList>> x = getRestApi().getList();
        return x;
    }

    public  Call<Result> deleteItem(String id){
        Call<Result> y = getRestApi().dltItem(id);
        return y;
    }
}
