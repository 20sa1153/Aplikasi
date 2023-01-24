package com.example.tesmagang.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIService {
    private static APIService instance = null;
    private UsersAPI users;

    private APIService() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(UsersAPI.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        users = retrofit.create(UsersAPI.class);
    }
    public static synchronized APIService getInstance() {
        if (instance == null) {
            instance = new APIService();
        }
        return instance;
    }
    public UsersAPI getUsersAPI() {
        return users;
    }
}
