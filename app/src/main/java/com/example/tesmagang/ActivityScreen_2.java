package com.example.tesmagang;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ActivityScreen_2 extends AppCompatActivity {

    private TextView namaUser;
    private Button btn_all;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen_2);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Screen Second");

        namaUser = findViewById(R.id.textNama);
        btn_all = findViewById(R.id.btn_allUser);

        String nama = getIntent().getStringExtra("nama");
        namaUser.setText(nama);

        btn_all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivityScreen_2.this, ActivityScreen_3.class);
                startActivity(intent);
            }
        });
    }
}