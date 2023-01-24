package com.example.tesmagang;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.example.tesmagang.adapter.UsersAdapter;
import com.example.tesmagang.api.UsersAPI;
import com.example.tesmagang.model.Users;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ActivityScreen_3 extends AppCompatActivity {

    RecyclerView recyclerView;
    UsersAdapter Adapter;
    List<Users> userModelList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen_3);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Screen Three");

        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        getUsers();

    }

    private void getUsers() {
        Call<List<Users>> call = UsersAPI.getInstance().getUsersAPI().getUsers();
        call.enqueue(new Callback<List<Users>>() {
            @Override
            public void onResponse(Call<List<Users>> call, Response<List<Users>> response) {
                userModelList = response.body();
                Adapter = new UsersAdapter(getApplicationContext(), userModelList);
                recyclerView.setAdapter(Adapter);
            }
            @Override
            public void onFailure(Call<List<Users>> call, Throwable t) {
                Log.e("Error", t.getMessage());
            }
        });
    }
}