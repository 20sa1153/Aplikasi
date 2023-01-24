package com.example.tesmagang.api;

import com.example.tesmagang.model.Users;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface UsersAPI {
    String BASE_URL = "https://reqres.in/api/";
    @GET("users?page=2")
    Call<List<Users>> getUsers();

    static APIService getInstance() {

        return APIService.getInstance();
    }
}
